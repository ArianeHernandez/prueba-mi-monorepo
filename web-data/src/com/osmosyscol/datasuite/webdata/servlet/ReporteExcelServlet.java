package com.osmosyscol.datasuite.webdata.servlet;

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

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.servicios.ReporteDimServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class ReporteExcelServlet extends HttpServlet {
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
		
		
		String idReporte = request.getParameter("id");

		if (StringUtils.esVacio(idReporte)) {
			return;
		}
		String nombreArchivo =  idReporte + ".xls";
		String nombrecarpeta = ParametrosInicio.getProperty("file.carpeta") + "/FORMATOS";
		String filename = nombrecarpeta + "/" + nombreArchivo;

		File f = new File(filename);
		if (!f.exists()) {
			if (!f.createNewFile()) {
				SimpleLogger.setError("No es posible crear el archivo");
			}
		}

		Object objectReportes = request.getSession().getAttribute(ReporteDimServicio.REPORTES);
		Boolean rta = ReporteDimServicio.getInstance().obtenerReporteExcel(idReporte, objectReportes, filename);
		
		if (!rta) {
			SimpleLogger.setError("Error al generar reporte");
		}
		else {
			
			int length = 0;
			ServletOutputStream op = response.getOutputStream();
			ServletContext context = getServletConfig().getServletContext();
			String mimetype = context.getMimeType(filename);
	
			response.setContentType((mimetype != null) ? mimetype : "application/octet-stream");
			response.setContentLength((int) f.length());
			response.setHeader("Content-Disposition", "attachment; filename=\"" + nombreArchivo + "\"");
	
			byte[] bbuf = new byte[10000];
			DataInputStream in = new DataInputStream(new FileInputStream(f));
	
			while ((in != null) && ((length = in.read(bbuf)) != -1)) {
				op.write(bbuf, 0, length);
			}
	
			in.close();
			op.flush();
			op.close();
			
			if (!f.delete()) {
				SimpleLogger.setError("No es posible borrar el archivo");
			}
			
		}
	}

}
