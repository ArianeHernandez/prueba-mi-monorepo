package com.osmosyscol.datasuite.webdata.correval.cargaexcel.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.ServletUtils;
import com.osmosyscol.datasuite.webdata.correval.cargaexcel.CargaExcelServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class CargaMasivoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idCargue = "";
		String ruta = "/inicio/0.do";
		try {

			Map<String, Object> param = ServletUtils.getParameters(request);

			FileItem fileItem = (FileItem) param.get("archivo");

			String tipo = param.get("tipo").toString();
			String rutabase = ParametrosInicio.getProperty("file.carpeta") + "/FILES";

			ruta = (String)param.get("url");
			
			if (fileItem != null && "application/vnd.ms-excel".equalsIgnoreCase(fileItem.getContentType())) {

				String archivo = ServletUtils.copyFileItem(rutabase, fileItem, "/masivo_" + System.currentTimeMillis() + ".osm");
				
				idCargue =  CargaExcelServicio.getInstance().iniciarCargaArchivo(archivo, tipo).toString();
				
			}

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		
		response.resetBuffer();
		response.setStatus(302);
		
		ServletUtils.sendRedirect(request, response, ruta + "?idCargue=" + idCargue);

	}

}
