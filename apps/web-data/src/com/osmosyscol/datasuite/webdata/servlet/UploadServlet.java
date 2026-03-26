package com.osmosyscol.datasuite.webdata.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.cocoon.environment.Session;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.io.FilenameUtils;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.JobServicio;
import com.osmosyscol.commons.utils.ServletUtils;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.servicios.HorarioServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.FormatoServicio;
import com.osmosyscol.datasuite.webdata.logica.dto.NumeroValor;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;
import com.osmosyscol.datasuite.webdata.persistencia.dao.CargaDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		HttpSession session = request.getSession();
		try {
			doUpload(session, request, response);
		} catch (FileUploadException e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
	}

	private void doUpload(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws IOException, FileUploadException {

		Map<String, Object> param = ServletUtils.getParameters(request);

		Integer respuesta = 0; // Error
		Boolean exitoso;
		try {

			Integer id_formato = null;
			if (request.getParameter("IDF") != null) {
				id_formato = Integer.parseInt(request.getParameter("IDF"));
			} else {
				id_formato = Integer.parseInt((String) param.get("id_formato"));
			}

			String id_archivo = request.getParameter("IDPXFRM");

			FileItem fileItem = (FileItem) param.get("filename");
			Integer usuario = (Integer) session.getAttribute("id_persona");
			String rutabase = ParametrosInicio.getProperty("file.carpeta") + "/FILES";

			if (id_archivo != null || fileItem != null) {

				int id = CargaServicio.getInstance().obtenerSiguiente();

				String ruta = null;
				String nombre = null;
				String extension = null;

				if (id_archivo != null) {

					// Se actualiza el id_carga del registro de auditoria para
					// el Documento firmado
					Boolean esFirmado=com.itosmosys.firmadigital.servicios.FirmaServicio.getInstance()
							.actualizarIDCargaParaDocumentoAuditoria(id_archivo, id);
					if(esFirmado.booleanValue()==true){
						ruta = ParametrosInicio.getProperty("file.carpeta") + "/tmp_" + id_archivo+".p7";
					}else{
						ruta = ParametrosInicio.getProperty("file.carpeta") + "/tmp_" + id_archivo;
					}
					nombre = "File_" + id;
					extension = "xls";
				} else {
					ruta = ServletUtils.copyFileItem(rutabase, fileItem, "/archivo_" + id + ".osm");
					nombre = FilenameUtils.getBaseName(fileItem.getName());
					extension = FilenameUtils.getExtension(fileItem.getName());
				}

				// -------

				Integer id_proceso = session.getAttribute("id_proceso") != null ? (new Integer(
						(String) session.getAttribute("id_proceso"))) : null;
				Integer id_usuario = (Integer) session.getAttribute("id_usuario");
				Integer id_negocio = (Integer) session.getAttribute("id_negocio");

				exitoso = FormatoServicio.getInstance().validarArchivoCarga(id_formato, ruta);
				if (exitoso) {

					Boolean huboErroresDeValidacionDatos = false;

					Session csession = (Session) request.getSession().getAttribute("cocoon-session");
					Map<String, List<String>> mapaErroresPorHoja = CargaServicio.getInstance()
							.validarDatosArchivoCarga(id_formato, ruta, csession);

					if (mapaErroresPorHoja != null) {
						Set<String> strings = mapaErroresPorHoja.keySet();
						if (strings.size() > 0) {
							huboErroresDeValidacionDatos = true;
							session.setAttribute("mapaErrores", mapaErroresPorHoja);
						}

					}

					if (!huboErroresDeValidacionDatos) {
						exitoso = CargaServicio.getInstance()
								.insertar(id, nombre, extension, ruta, id_formato, usuario,
										HorarioServicio.getInstance().obtenerFechaActual(), Constantes.TIPOCARGA_LOTES,
										id_proceso, id_usuario, id_negocio, request.getRemoteHost());
						if (exitoso) {
							NumeroValor numeroValor = CargaServicio.getInstance().obtenerDatosExcel(id);
							DaoManager daoManagerData = DaoConfig.getDaoManager("DATASUITE");
							CargaDao cargaDaoData = (CargaDao) daoManagerData.getDao(CargaDao.class);
							cargaDaoData.actualizarValorTotalCarga(id, Crypto.E(numeroValor.getValor_total()), Crypto.E(numeroValor.getNumero_registros()));
							respuesta = 1;
						} else {
							respuesta = 4; // No se pudo insertar carga
						}
						// -----------

						JobServicio jobServicio = JobServicio.getInstance();

						Object[] parameters = new Object[1];
						parameters[0] = new Integer(id);

						jobServicio.callService(CargaServicio.class, "guardarTablaControl", parameters, 5);

					} else {

						respuesta = 5; // Hubo errores de validacion en los
										// datos
					}
				} else {
					respuesta = 2; // No es el formato
					File f = new File(ruta);
					f.delete();
				}

			} else {
				exitoso = false;
				respuesta = 3; // No es un archivo excel
			}

		} catch (Exception e) {
			SimpleLogger.setError("No se puede realizar la carga del archivo.", e);
			exitoso = false;
		}

		String destino = "/carga_informacion/lotes/1.2.do?ins=" + respuesta;
		response.resetBuffer();
		response.setStatus(302);

		ServletUtils.sendRedirect(request, response, destino);

	}
}
