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

import java.io.CharArrayWriter;
import java.io.PrintWriter;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

class JSONRPCResult {

	private Object result = null;
	private int errorCode;

	public final static int CODE_SUCCESS = 0;
	public final static int CODE_REMOTE_EXCEPTION = 490;
	public final static int CODE_ERR_PARSE = 590;
	public final static int CODE_ERR_NOMETHOD = 591;
	public final static int CODE_ERR_UNMARSHALL = 592;
	public final static int CODE_ERR_MARSHALL = 593;

	public final static JSONRPCResult ERR_PARSE = new JSONRPCResult(CODE_ERR_PARSE, "couldn't parse request arguments");

	public final static JSONRPCResult ERR_NOMETHOD = new JSONRPCResult(CODE_ERR_NOMETHOD, "method not found (session may have timed out)");

	public JSONRPCResult(int errorCode, Object o) {
		this.errorCode = errorCode;
		this.result = o;
	}

	public String toString() {
		JSONObject o = new JSONObject();
		if (errorCode == CODE_SUCCESS) {
			o.put("result", result);
		} else if (errorCode == CODE_REMOTE_EXCEPTION) {
			Throwable e = (Throwable) result;

			SimpleLogger.setError("Error en llamado Json", e);

			CharArrayWriter caw = new CharArrayWriter();
			e.printStackTrace(new PrintWriter(caw));
			JSONObject err = new JSONObject();
			err.put("code", new Integer(errorCode));
			err.put("msg", e.getMessage());

			//err.put("trace", caw.toString()); // Se deshabilita por seguridad.

			o.put("error", err);
		} else {

			SimpleLogger.setError("Error en llamado Json: " + result);

			JSONObject err = new JSONObject();
			err.put("code", new Integer(errorCode));
			err.put("msg", "No se puede procesar la solicitud");

			o.put("error", err);
		}
		return o.toString();
	}
}
