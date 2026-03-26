package com.osmosyscol.datasuite.webdata.correval.bancos.salida.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cocoon.environment.Session;

import co.htsoft.commons.file.FileUtils;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.ServletUtils;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.SalidaBancoServicio;

public class DescargarArchivoBancoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {

			Session session = (Session) request.getSession().getAttribute("cocoon-session");

			String str_archivo = null;
			String nombre_archivo = null;
			if (request.getParameter("id_archivo") != null) {
				str_archivo = request.getParameter("id_archivo");
				session.setAttribute("id_archivo", str_archivo);
			}

			str_archivo = session.getAttribute("id_archivo").toString();

			nombre_archivo = (String) session.getAttribute("nombre_archivo_salida_banco");

			if (StringUtils.esVacio(nombre_archivo)) {
				nombre_archivo = request.getParameter("nombre_archivo_d");
			}

			if (StringUtils.esVacio(nombre_archivo)) {
				nombre_archivo = "salida_banco";
			}

			Integer id_archivo = new Integer(str_archivo);

			File datos = SalidaBancoServicio.getInstance().generarArchivo(id_archivo, null);

			String extension = datos.getName().split("[.]")[1].toLowerCase();
			
			if("tmp".equals(extension)){
				extension = "txt";
			}
			
			response.setContentType("application/octet-stream");
			response.setContentLength((int) datos.length());
			response.setHeader("Content-Disposition", "attachment; filename=\"" + nombre_archivo + "." + extension);
			response.setHeader("Cache-Control", "no-store,must-revalidate,no-cache");
			response.setDateHeader("Expires", 0);
			
			FileInputStream inStream = new FileInputStream(datos);
			ServletOutputStream op = response.getOutputStream();
				
			
			byte[] buffer = new byte[4096];
	        int bytesRead = -1;
	        while ((bytesRead = inStream.read(buffer)) != -1) {
	            op.write(buffer, 0, bytesRead);
	        }

			op.flush();
			op.close();
			inStream.close();

		} catch (Exception e) {
			SimpleLogger.setError("Error", e);
		}

		String destino = "agrupar_giros/24.1.do?error";
		response.resetBuffer();
		response.setStatus(302);
		
		ServletUtils.sendRedirect(request, response, destino);

	}

}
