package com.osmosyscol.datasuite.modelatos.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.JavaToXML;
import com.osmosyscol.datasuite.modelatos.logica.dto.Estructura;
import com.osmosyscol.datasuite.modelatos.logica.dto.Grupo;
import com.osmosyscol.datasuite.modelatos.logica.servicios.EstructuraServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.GrupoServicio;

public class ExportGrupoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doExport(request, response);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
	}

	private void doExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Integer id_grupo = Integer.parseInt(request.getParameter("id_grupo"));
		Grupo grupo = GrupoServicio.getInstance().obtenerGrupo(id_grupo);
		grupo.setId_modelo(null);
		grupo.setId_grupo(null);
		
		List<Estructura> estructuras =  new ArrayList<Estructura>();
		estructuras = EstructuraServicio.getInstance().obtenerEstructurasPorGrupo(id_grupo);
		
		String stringEstructuras = "";
		for (Estructura estructura : estructuras) {
			
			stringEstructuras += UtilsExport.estructuraToXml(estructura);
			
		}
		
		StringBuffer stringGrupo = JavaToXML.exe("Grupo", grupo);
		String cadena ="<ExportGrupo>" + stringGrupo + stringEstructuras + "</ExportGrupo>";
		
		byte[] bbuf = new byte[10000];
		int length = 0;
		
		String filename = grupo.getNombre()+".xml";
		File f = new File(filename);
		ServletOutputStream op = response.getOutputStream();
		ServletContext context = getServletConfig().getServletContext();
		
		String mimetype = context.getMimeType(filename);

		response.setContentType((mimetype != null) ? mimetype : "application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + f.getName() + "\"");

		
		bbuf = cadena.getBytes();
		length = cadena.length();
		
		op.write(bbuf, 0, length);

		op.flush();
		op.close();
	}
	
}
