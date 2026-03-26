package com.osmosyscol.datasuite.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.osmosyscol.commons.servicio.AutenticacionServicio;
import com.osmosyscol.datasuite.logica.servicios.VistasServicio;

public class ServletRegenerarVistas extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		if (com.osmosyscol.datasuite.logica.constantes.Constantes.NOMBRE_APLICACION_MODELATOS.equalsIgnoreCase(AutenticacionServicio.NOMBRE_APLICACION)) {
			Boolean salida = VistasServicio.getInstance().regenerarVistas();
			ServletOutputStream stream = resp.getOutputStream();
			if (salida) {
				stream.print("Exitoso");
			} else {
				stream.print("Fallo");
			}
		}

	}
}
