package com.osmosyscol.datasuite.webdata.correval.soportepago.servlet;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.log.SimpleLogger;

public class DescargaArchivoSoportePagoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doDownload(request, response);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
	}

	private void doDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			
			Session session = (Session) request.getSession().getAttribute("cocoon-session");
			
			String ruta_archivo = session.getAttribute("rutaArchivo").toString();			
//			
			File file = new File(ruta_archivo);

			if(file!=null && file.exists()){
				//el archivo se ha creado con exito
				
				int length = 0;
				ServletOutputStream op = response.getOutputStream();
				ServletContext context = getServletConfig().getServletContext();
				String mimetype = context.getMimeType(file.getPath());
	
				response.setContentType((mimetype != null) ? mimetype : "application/octet-stream");
				response.setContentLength((int) file.length());
				response.setHeader("Content-Disposition", "attachment; filename=\"" + ruta_archivo + "\"");
				
				Integer tamano = new Long(file.length()).intValue();
				byte[] bbuf = new byte[tamano];
				DataInputStream in = new DataInputStream(new FileInputStream(file));
	
				while ((in != null) && ((length = in.read(bbuf)) != -1)) {
					op.write(bbuf, 0, length);
				}
	
				in.close();
				op.flush();
				op.close();
				
				
				
			}else{
				//el archivo no se ha creado con exito
				SimpleLogger.setError("No se ha podido descargar el archivo");
			}
			
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error descargando el archivo", e);
		}
		
		
	}

}
