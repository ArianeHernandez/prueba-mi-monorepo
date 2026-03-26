/*
 * JSON-RPC-Java - a JSON-RPC to Java Bridge with dynamic invocation
 *
 * $Id$
 *
 * Copyright Metaparadigm Pte. Ltd. 2004.
 * Michael Clark <michael@metaparadigm.com>
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public (LGPL)
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details: http://www.gnu.org/
 *
 */

package com.osmosyscol.commons.rpc.jsonrpc;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.InfoTransaccionServicio;
import com.osmosyscol.commons.servicio.json.Core_JsonServicio;

/**
 * This servlet handles JSON-RPC requests over HTTP and hands them to a JSONRPCBridge instance registered in the HttpSession. </p> By default, the JSONRPCServlet places an instance of the JSONRPCBridge object is automatically in the HttpSession object registered under the attribute "JSONRPCBridge".
 * <p />
 * The following can be added to your web.xml to export the servlet under the URI &quot;<code>/JSON-RPC</code>&quot;
 * <p />
 * <code>
 * &lt;servlet&gt;
 *   &lt;servlet-name&gt;com.metaparadigm.jsonrpc.JSONRPCServlet&lt;/servlet-name&gt;
 *   &lt;servlet-class&gt;com.metaparadigm.jsonrpc.JSONRPCServlet&lt;/servlet-class&gt;
 * &lt;/servlet&gt;
 * &lt;servlet-mapping&gt;
 *   &lt;servlet-name&gt;com.metaparadigm.jsonrpc.JSONRPCServlet&lt;/servlet-name&gt;
 *   &lt;url-pattern&gt;/JSON-RPC&lt;/url-pattern&gt;
 * &lt;/servlet-mapping&gt;
 * </code>
 * <p />
 * You can disable the automatic creation of a JSONRPCBridge in the session by placing the XML below into your web.xml inside the &lt;servlet&gt; element. If you do this, you can add one to the session yourself. If it is disabled, and you have not added one to the session, only the global bridge will be available.
 * <p />
 * <code>
 * &lt;init-param&gt;
 *   &lt;param-name&gt;auto-session-bridge&lt;/param-name&gt;
 *   &lt;param-value&gt;0&lt;/param-value&gt;
 * &lt;/init-param&gt;
 * </code>
 */

@SuppressWarnings("serial")
public class JSONRPCServlet extends HttpServlet {
	private final static Logger log = Logger.getLogger(JSONRPCServlet.class.getName());

	private final static int buf_size = 4096;

	static {

		try {
			JSONRPCBridge.getGlobalBridge().registerClass("pcore", Core_JsonServicio.class);
		} catch (Exception e1) {
		}

	}

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassCastException {
		
		InfoTransaccionServicio.getInstance().guardarInfoSession(request);

		String referer = request.getHeader("referer");
		if (referer == null) {
			referer = request.getHeader("REFERER");
		}

		try {
			referer = referer.substring(referer.indexOf("/", referer.indexOf("/", referer.indexOf("/", referer.indexOf("/", 0) + 1) + 1) + 1));
		} catch (Exception e) {
			// SimpleLogger.setWarn("No se puede identificar la url", e);
		}

		InfoTransaccionServicio.getInstance().getInfoTransaccion().setUrl(referer);

		// Find the JSONRPCBridge for this session or create one
		// if it doesn't exist
		HttpSession session = request.getSession(false);
		JSONRPCBridge json_bridge = null;

		if (session == null) {
			json_bridge = JSONRPCBridge.getGlobalBridge();
		} else {
			json_bridge = (JSONRPCBridge) session.getAttribute("JSONRPCBridge");
			session.setAttribute("HttpServletRequest", request);
		}

		if (json_bridge == null) {
			// Only create a new bridge if not disabled in config
			if ("0".equals(getServletConfig().getInitParameter("auto-session-bridge"))) {
				// Use the global bridge only, and don't set on session.
				json_bridge = JSONRPCBridge.getGlobalBridge();
				if (json_bridge.isDebug())
					log.info("Using global bridge.");
			} else {
				json_bridge = new JSONRPCBridge();
				session.setAttribute("JSONRPCBridge", json_bridge);
				if (json_bridge.isDebug())
					log.info("Created a bridge for this session.");
			}
		}

		// Encode using ISO-8859-1, although We are actually ASCII clean as
		// all unicode data is JSON escaped using backslash u. This is
		// less data efficient for foreign character sets but it is
		// needed to support naughty browsers such as Konqueror and Safari
		// which do not honour the charset set in the response
		response.setContentType("text/plain;charset=ISO-8859-1");
		OutputStream out = response.getOutputStream();

		// Decode using the charset in the request if it exists otherwise
		// use ISO-8859-1 as this is what all browser implementations use.
		// The JSON-RPC-Java JavaScript client is ASCII clean so it
		// although here we can correctly handle data from other clients
		// that do not escape non ASCII data
		String charset = request.getCharacterEncoding();
		if (charset == null)
			charset = "ISO-8859-1";
		BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream(), charset));

		// Read the request
		CharArrayWriter data = new CharArrayWriter();
		char buf[] = new char[buf_size];
		int ret;
		while ((ret = in.read(buf, 0, buf_size)) != -1) {
			data.write(buf, 0, ret);
		}

		if (json_bridge.isDebug())
			log.fine("recieve: " + data.toString());

		SimpleLogger.setDebug("JSON: " + data.toString());

		// Process the request
		JSONObject json_req = null;
		Object json_res = null;
		try {
			json_req = new JSONObject(data.toString());

			// Get method name and arguments
			String methodName = null;
			JSONArray arguments = null;

			try {
				methodName = json_req.getString("method");
			} catch (NoSuchElementException ne) {
			}

			// Back compatibility for <= 0.7 clients
			if (methodName != null) {
				arguments = json_req.getJSONArray("params");
			} else {
				methodName = json_req.getString("methodName");
				arguments = json_req.getJSONArray("arguments");
				log.warning("methodName in request deprecated, " + "please update your JSON-RPC client.");
			}

			// Is this a CallableReference it will have a non-zero objectID
			int object_id = json_req.optInt("objectID");
			if (json_bridge.isDebug())
				if (object_id != 0)
					log.fine("call " + "objectID=" + object_id + " " + methodName + "(" + arguments + ")");
				else
					log.fine("call " + methodName + "(" + arguments + ")");
			json_res = json_bridge.call(new Object[] { request }, object_id, methodName, arguments);
		} catch (ParseException e) {
			log.severe("can't parse call: " + data);
			json_res = JSONRPCResult.ERR_PARSE;
		} catch (NoSuchElementException e) {
			log.severe("no method in request");
			json_res = JSONRPCResult.ERR_NOMETHOD;
		}

		// Write the response
		if (json_bridge.isDebug())
			log.fine("send: " + json_res.toString());
		byte[] bout = json_res.toString().getBytes("ISO-8859-1");
		response.setIntHeader("Content-Length", bout.length);
		response.setHeader("Connection", "keep-alive");

		out.write(bout);
		out.flush();
		out.close();
		
		InfoTransaccionServicio.getInstance().borrarInfoTransaccion();
	}
}
