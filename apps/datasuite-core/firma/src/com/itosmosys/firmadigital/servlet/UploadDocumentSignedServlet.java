package com.itosmosys.firmadigital.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.ServletUtils;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class UploadDocumentSignedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			doUpload(session, request, response);
		} catch (FileUploadException e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
	}

	private void doUpload(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException, FileUploadException {

		Map<String, Object> param = ServletUtils.getParameters(request);
		
		try {

			FileItem fileItem = (FileItem) param.get("caja_archivo_firmado");
			
			String rutabase = ParametrosInicio.getProperty("file.carpeta");
			
			String id_archivo = (String)param.get("id_archivo_firmado");
			
			
			if (fileItem != null) {
				
				String rutaArchivoP7Firmado = ServletUtils.copyFileItem(rutabase, fileItem, "/tmp_"+id_archivo+".p7");
				SimpleLogger.setInfo("Se ha subido el archivo firmado, la ruta es: "+rutaArchivoP7Firmado);
				
				String rutaArchivoP7FirmadoAuditoria = ServletUtils.copyFileItem(rutabase+"/ARCHIVOS_FIRMA", fileItem, "/tmp_"+id_archivo+".p7");
				SimpleLogger.setInfo("Se ha creado una copia del archivo firmado, la ruta es: "+rutaArchivoP7FirmadoAuditoria);
				
			}
			

		} catch (Throwable e) {
			SimpleLogger.setError("Error adjuntando el archivo", e);
		}
		
		
		

	}
}
