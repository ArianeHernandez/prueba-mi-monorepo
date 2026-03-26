package com.osmosyscol.datasuite.modelatos.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.Mensaje;
import com.osmosyscol.commons.utils.ServletUtils;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.modelatos.logica.servicios.EstructuraExcelServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class CargarExcelEstructuraServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doUpload(request, response);

	}

	private void doUpload(HttpServletRequest request, HttpServletResponse response) throws IOException {

		Mensaje mensaje = new Mensaje();
		try {
			
			Map<String, Object> param = ServletUtils.getParameters(request);

			HttpSession session = request.getSession();
			mensaje.setEstado("I");
			mensaje.setMensaje("Iniciando carga...");
			session.setAttribute(EstructuraExcelServicio.ATRIBUTO_MENSAJE, mensaje);


			Integer id_estructura = Integer.parseInt(param.get("id_estructura").toString());

			Boolean reemplazarTodo = StringUtils.esVerdad(param.get("reemplazar_todo").toString());

			FileItem fileItem = (FileItem) param.get("filename");
			String rutabase = ParametrosInicio.getProperty("file.carpeta") + "/FILES";

			if (fileItem != null) {
				long id = System.currentTimeMillis();
				String ruta = ServletUtils.copyFileItem(rutabase, fileItem, "/carga_" + id_estructura + "_" + id + ".osm");

				EstructuraExcelServicio.getInstance().iniciarCargaArchivoExcel(id_estructura, ruta, reemplazarTodo, mensaje);

			}
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			mensaje.setEstado("F");
			mensaje.setMensaje("Error cargando archivo.");
		}
		
		
		String destino = "datosestructura/12.2.do";
		response.resetBuffer();
		response.setStatus(302);
		
		ServletUtils.sendRedirect(request, response, destino);

	}

}
