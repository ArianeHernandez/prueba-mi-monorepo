package com.osmosyscol.datasuite.servlet;

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
import com.osmosyscol.datasuite.modelatos.logica.servicios.ListaValoresServicio;


public class DescargaArchivoListaValoresServlet extends HttpServlet {
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
			
			
			
			Integer respuesta = 0;
			Session session = (Session) request.getSession().getAttribute("cocoon-session");
			
			Integer id_lista_valores= Integer.parseInt(session.getAttribute("var.id_lista_valores").toString());
			
			File file = ListaValoresServicio.getInstance().generarArchivoDescarga(id_lista_valores);
			
			
			if(file!=null && file.exists()){
				//el archivo se ha creado con exito
				respuesta=1;
				
				int length = 0;
				ServletOutputStream op = response.getOutputStream();
				ServletContext context = getServletConfig().getServletContext();
				String mimetype = context.getMimeType(file.getPath());
	
				response.setContentType((mimetype != null) ? mimetype : "application/octet-stream");
				response.setContentLength((int) file.length());
				response.setHeader("Content-Disposition", "attachment; filename=\"" +"listaValores"+".xls"+ "\"");
				
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
				respuesta= 2;
				SimpleLogger.setError("No se ha podido descargar el archivo");
			}
			
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error descargando el archivo", e);
		}
		
		
	}

}
