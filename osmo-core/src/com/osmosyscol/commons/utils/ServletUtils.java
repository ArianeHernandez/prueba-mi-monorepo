package com.osmosyscol.commons.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cocoon.environment.Request;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.osmosyscol.commons.log.SimpleLogger;

public class ServletUtils {

	@SuppressWarnings({ "deprecation" })
	public static Map<String, Object> getParameters(HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();

		try {
			if (ServletFileUpload.isMultipartContent(request)) {
				ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
				// servletFileUpload.setSizeMax(MAX_UPLOAD_SIZE);
				List<?> fileItemsList = servletFileUpload.parseRequest(request);

				@SuppressWarnings("rawtypes")
				Iterator iter = fileItemsList.iterator();
				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();

					if (item.isFormField()) {
						map.put(item.getFieldName(), item.getString());
					} else {
						map.put(item.getFieldName(), item);
					}
				}
			}
		} catch (Exception e) {
			final String systemTmpDirKey = "java.io.tmpdir";
			SimpleLogger.setError(String.format("ServletUtils.getParameters [%s=%s]", systemTmpDirKey, System.getProperty(systemTmpDirKey)), e);
		}

		return map;
	}

	@SuppressWarnings({ "deprecation" })
	public static Map<String, List<Object>> getParametersList(HttpServletRequest request) {

		Map<String, List<Object>> map = new HashMap<String, List<Object>>();

		try {
			if (ServletFileUpload.isMultipartContent(request)) {
				ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
				List<?> fileItemsList = servletFileUpload.parseRequest(request);

				@SuppressWarnings("rawtypes")
				Iterator iter = fileItemsList.iterator();
				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();

					List<Object> items = map.get(item.getFieldName());

					if (items == null) {
						items = new ArrayList<Object>();
						map.put(item.getFieldName(), items);
					}

					if (item.isFormField()) {
						items.add(item.getString());
					} else {
						items.add(item);
					}
				}
			}
		} catch (Exception e) {
			SimpleLogger.setError("getParametersList", e);
		}

		return map;
	}

	public static String copyFileItem(String dirName, FileItem fileItem, String optionalFileName) {
		String fileName = fileItem.getName();
		if (optionalFileName == null || optionalFileName.trim().equals("")) {
			fileName = FilenameUtils.getName(fileName);
		} else {
			fileName = optionalFileName;
		}

		new File(dirName).mkdirs();
		String ruta = dirName + "/" + fileName;
		try {
			byte[] buf = new byte[1024];
			InputStream in = fileItem.getInputStream();

			FileOutputStream out = new FileOutputStream(ruta);
			int numRead = 0;
			while ((numRead = in.read(buf)) >= 0) {
				out.write(buf, 0, numRead);
			}
			in.close();
			out.close();

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error al copiar FileItem", e);
		}
		return ruta;
	}

	public static Map<String, String> getMapRequest(Request request) {
		Map<String, String> map = new HashMap<String, String>();
		for (@SuppressWarnings("rawtypes")
		Enumeration e = request.getParameterNames(); e.hasMoreElements();) {
			String param = e.nextElement().toString();
			String data = request.getParameter(param);
			if (data != null) {
				data = data.trim();
			}
			map.put(param, data);
		}

		return map;
	}

	/**
	 * Recorre el request, parámetro a parámetro y valida que no contenga los caracteres fin de línea que suponen la inyección de headers
	 * 
	 * @param request
	 * @return boolean true si algún parámetro contiene \r\n
	 */
	public static boolean validarRequestParameter(HttpServletRequest request) {
		@SuppressWarnings("rawtypes")
		Enumeration enumerate = request.getParameterNames();
		String element = "";
		String valueString = "";

		while (enumerate.hasMoreElements()) {
			element = (String) enumerate.nextElement();

			valueString = request.getParameter(element);

			if (valueString != null) {
				if (valueString.contains("%0d%0a") || valueString.contains("\\r\\n") || valueString.contains("\r\n")) {
					return false;
				}
			}
		}
		return true;
	}

	public static void sendRedirect(HttpServletRequest request, HttpServletResponse response, String destino) throws IOException {

		while (destino.charAt(0) == '/') {
			destino = destino.substring(1);
		}

		String serverRequest = request.getRequestURI().substring(request.getContextPath().length());

		while (serverRequest.contains("//")) {
			serverRequest = serverRequest.replace("//", "/");
		}

		int r = (serverRequest.split("/").length - 2);

		for (int i = 0; i < r; i++) {
			destino = "../" + destino;
		}

		System.out.println("(ServletUtils) Redireccionando a destino: " + destino);

		response.sendRedirect(destino);

	}
}
