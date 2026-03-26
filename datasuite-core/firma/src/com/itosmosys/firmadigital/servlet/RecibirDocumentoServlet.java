package com.itosmosys.firmadigital.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FilenameUtils;

import com.itosmosys.firmadigital.firma.PKCS7VerUtil;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.ServletUtils;

public class RecibirDocumentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doImport(request, response);
		} catch (Exception e) {
			SimpleLogger.setError("Error", e);
		} catch (Throwable e) {
			SimpleLogger.setError("Error", e);
		}
	}

	private void doImport(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, Object> params = ServletUtils.getParameters(request);

		Boolean valido = false;

		FileItem fileitem = (FileItem) params.get("fileFirmado");

		if (fileitem != null) {

//			String path = ParametrosInicio.getInstance().getCarpeta();
			String path = "C:/firma";
			String ruta = ServletUtils.copyFileItem(path, fileitem, FilenameUtils.getName(fileitem.getName()));
			
			valido = PKCS7VerUtil.validatePkcs7Signature2(ruta, ruta.split(".p7")[0]);

			response.setContentType("text/html; charset=iso-8859-1");
			try {
				PrintWriter out = response.getWriter();
				out.println(valido);
				out.close();
			} catch (IOException e) {
				SimpleLogger.setError("Error", e);
			}
		}

	}

}
