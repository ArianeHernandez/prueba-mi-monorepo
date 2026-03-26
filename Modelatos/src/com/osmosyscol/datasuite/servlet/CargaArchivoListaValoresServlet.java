package com.osmosyscol.datasuite.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.ServletUtils;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValorLista;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ListaValoresServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class CargaArchivoListaValoresServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			doUpload(session, request, response);
		} catch (FileUploadException e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
	}

	private void doUpload(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException, FileUploadException {

		Map<String, Object> param = ServletUtils.getParameters(request);

		Integer respuesta = 0; // Error
		try {
			FileItem fileItem = (FileItem) param.get("filename");
			String rutabase = ParametrosInicio.getProperty("file.carpeta") + "/FILES";

			if (fileItem != null && "application/vnd.ms-excel".equalsIgnoreCase(fileItem.getContentType())) {

				String ruta = ServletUtils.copyFileItem(rutabase, fileItem, "/listaValores.osm");
				
				File file = new File(ruta);
				if(file.exists()){
					
					List<ValorLista> listaValores = ListaValoresServicio.getInstance().obtenerValoresPorArchivo(ruta);
					
					if(listaValores!=null && listaValores.size()>0){
					
						//Se actualiza la lista de valores
						
						Integer id_lista_valores= Integer.parseInt(session.getAttribute("var.id_lista_valores").toString());
						Boolean actualizacionExitosa = ListaValoresServicio.getInstance().actualizarValoresLV(id_lista_valores, listaValores);
						
						if(actualizacionExitosa){
							respuesta=1; //Cargue exitoso
						}else{
							respuesta=2; //NO se pudo actualizar
						}
						
					}
					
				}
						
				

			} else {
				respuesta = 3; // No es un archivo excel
			}

		} catch (Exception e) {
			SimpleLogger.setError("No se puede realizar la carga del archivo.", e);
		}

		String destino = "listasvalores/3.1.1.do?ans=" + respuesta;
		response.resetBuffer();
		response.setStatus(302);
		
		ServletUtils.sendRedirect(request, response, destino);

	}
}
