package com.osmosyscol.datasuite.webdata.correval.cargueplano.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cocoon.environment.Session;
import org.apache.commons.fileupload.FileItem;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.ServletUtils;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class CargaPlanoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Session session = (Session) request.getSession().getAttribute("cocoon-session");
		Map<String, Object> param = ServletUtils.getParameters(request);
		String id_archivo_firmado = (String)request.getParameter("IDPXFRM");
		String rutaDestino = null;
		
		try {

			session.removeAttribute("id_archivo_firmado_plano");
			String rutabase = ParametrosInicio.getProperty("file.carpeta") + "/FILES";
			
			//Para archivos firmados
			if(id_archivo_firmado!=null){
				rutaDestino = (String)request.getParameter("url");
				
				String contenidoArchivoFirmado = ParametrosInicio.getProperty("file.carpeta")+"/tmp_"+id_archivo_firmado;
				
				if(contenidoArchivoFirmado != null){
					session.setAttribute("ruta_plano", contenidoArchivoFirmado);
					session.setAttribute("id_archivo_firmado_plano", id_archivo_firmado);
				}
				
			}else{
				
				rutaDestino = (String)param.get("url");
			
				FileItem fileItem = (FileItem) param.get("archivo");
				if (fileItem != null ) {
	
				
					String ruta = ServletUtils.copyFileItem(rutabase, fileItem, "/plano_" + System.currentTimeMillis() + ".osm");
					
					if(ruta != null){
						session.setAttribute("ruta_plano", ruta);
					}
					
				}
			
			}

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		
		
		response.resetBuffer();
		response.setStatus(302);

		ServletUtils.sendRedirect(request, response, rutaDestino);
		
	}

}
