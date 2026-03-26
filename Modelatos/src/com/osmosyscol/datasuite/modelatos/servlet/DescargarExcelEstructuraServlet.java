package com.osmosyscol.datasuite.modelatos.servlet;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.modelatos.logica.dto.Estructura;
import com.osmosyscol.datasuite.modelatos.logica.servicios.EstructuraExcelServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.EstructuraServicio;

public class DescargarExcelEstructuraServlet extends HttpServlet {
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

	private void doDownload(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Integer id_estructura = Integer.parseInt(request.getParameter("id_estructura"));
		Boolean descargarDatos = StringUtils.esVerdad(request.getParameter("descargar_datos"));
		String archivo = EstructuraExcelServicio.getInstance().crearArchivoExcel(id_estructura, descargarDatos);

		File excel = new File(archivo);
		int length = 0;
		ServletOutputStream op = response.getOutputStream();

		response.setContentType("application/octet-stream");
		response.setContentLength((int) excel.length());
		Estructura estructura = EstructuraServicio.getInstance().obtenerEstructura(id_estructura);
		response.setHeader("Content-Disposition", "attachment; filename=\"estructura_" + estructura.getNombre() + ".xls\"");

		byte[] bbuf = new byte[10000];
		DataInputStream in = new DataInputStream(new FileInputStream(excel));

		while ((in != null) && ((length = in.read(bbuf)) != -1)) {
			op.write(bbuf, 0, length);
		}

		in.close();
		op.flush();
		op.close();
	}

	
	
}
