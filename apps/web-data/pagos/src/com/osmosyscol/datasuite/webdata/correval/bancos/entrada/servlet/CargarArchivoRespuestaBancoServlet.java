package com.osmosyscol.datasuite.webdata.correval.bancos.entrada.servlet;

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
import com.osmosyscol.datasuite.pagos.acciones.aplicardevolucion.AplicarDevolucion;
import com.osmosyscol.datasuite.webdata.correval.bancos.entrada.ManejadorDeInterpretesRespuestaBanco;
import com.osmosyscol.datasuite.webdata.correval.bancos.entrada.RespuestaBanco;
import com.osmosyscol.datasuite.webdata.correval.servicios.RetiroServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class CargarArchivoRespuestaBancoServlet extends HttpServlet {
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
		Integer registrosActualizados = 0;
		Integer totalRegistrosParaActualizar = 0;
		try {
			String id_banco = (String) param.get("id_banco");
			FileItem fileItem = (FileItem) param.get("filename");
			String rutabase = ParametrosInicio.getProperty("file.carpeta") + "/FILES";

			if (fileItem != null) {

				String ruta = ServletUtils.copyFileItem(rutabase, fileItem, "/archivo_respuesta_" + id_banco + ".osm");

				File file = new File(ruta);
				if (file.exists()) {

					// Se revisa si el archivo es valido
					Boolean esArchivoValido = ManejadorDeInterpretesRespuestaBanco.getInstance().validarArchivo(ruta, id_banco);

					if (esArchivoValido) {

						// Se intepreta el archivo y se tiene las respuestas por banco
						List<RespuestaBanco> listaRespuestaBancos = ManejadorDeInterpretesRespuestaBanco.getInstance().obtenerRespuestasPorBanco(ruta, id_banco);

						if (listaRespuestaBancos != null && listaRespuestaBancos.size() > 0) {

							totalRegistrosParaActualizar = listaRespuestaBancos.size();

							// Para lista de respuesta se actualizan los retiros correspondientes
							registrosActualizados = RetiroServicio.getInstance().actualizarRetirosPorRespuestasBanco(listaRespuestaBancos, request);

							request.getSession().setAttribute("listaRespuestaBancos", listaRespuestaBancos);
							// Si la cantidad de registros actualizados es igual al total de registros la transaccion fue culminada con exito
							
							new Thread( new Runnable() {
								public void run() {
									AplicarDevolucion.ejecutar();
								}
							}).start();
							
							if (totalRegistrosParaActualizar.equals(registrosActualizados)) {
								respuesta = 1;// Actgualizacion terminada con exito

							} else if (registrosActualizados.equals(0)) {
								respuesta = 5;// No se pudieron actualizar ningun registro

							} else {
								respuesta = 6;// la totalidad de registros no fueron actualizados
							}

						} else {
							respuesta = 2; // No hay registros para el archivo
						}

					} else {
						respuesta = 7;// El archivo no es valido para ese banco
					}

				} else {
					respuesta = 4; // Archivo no existe
				}

			} else {
				respuesta = 3; // No es un archivo excel
			}

		} catch (Exception e) {
			e.printStackTrace();
			SimpleLogger.setError("No se puede realizar la carga del archivo.", e);
		}

		String destino = "respuesta_bancos/29.2.do?ans=" + respuesta + "&all=" + totalRegistrosParaActualizar + "&act=" + registrosActualizados;
		response.resetBuffer();
		response.setStatus(302);

		ServletUtils.sendRedirect(request, response, destino);
	}
}
