package com.osmosyscol.datasuite.webdata.logica.servicios;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jcifs.smb.SmbFile;

import org.apache.cocoon.environment.Session;
import org.apache.commons.io.FilenameUtils;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.mensajeria.EnviaMails;
import com.osmosyscol.commons.utils.FileUtils;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.Contenido;
import com.osmosyscol.datasuite.logica.dto.FtpUsuario;
import com.osmosyscol.datasuite.logica.dto.FtpUsuarioArchivo;
import com.osmosyscol.datasuite.logica.dto.FtpUsuarioCorreo;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.dto.Proceso;
import com.osmosyscol.datasuite.logica.servicios.ContenidoServicio;
import com.osmosyscol.datasuite.logica.servicios.FtpUsuarioArchivoServicio;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;
import com.osmosyscol.datasuite.modelatos.logica.dto.Formato;
import com.osmosyscol.datasuite.modelatos.logica.servicios.FormatoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ProcesoServicio;
import com.osmosyscol.datasuite.utils.MD5Checksum;
import com.osmosyscol.datasuite.utils.PgpUtils;
import com.osmosyscol.datasuite.utils.SambaUtils;
import com.osmosyscol.datasuite.utils.ThreadUtils;
import com.osmosyscol.datasuite.utils.dummys.DummySession;
import com.osmosyscol.datasuite.webdata.ValidadorDatosCarga;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class CargaAutomaticaServicio {

	private Boolean EN_USO = false;

	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd.HHmmss");

	private FtpUsuario ftpUsuario = null;

	private static final Integer ID_PERSONA = 0;

	private Session session;

	public static Map<String, Map<String, List<String>>> mapaErrores = new HashMap<String, Map<String, List<String>>>();

	public CargaAutomaticaServicio() {
	}

	// *****************************************************************************************

	private void enviarNotificacionEstado(EstadoConexion estadoConexion) {
		enviarNotificacionEstado(estadoConexion, new HashMap<String, String>());
	}

	private void enviarNotificacionEstado(EstadoConexion estadoConexion, Map<String, String> parametros) {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("subject", estadoConexion.getTituloError());
		params.put("template", estadoConexion.getTemplate());

		Persona persona = PersonaServicio.getInstance().obtenerPersona(com.osmosyscol.datasuite.logica.servicios.UsuarioServicio.getInstance().obtenerUsuario(ftpUsuario.getId_usuario()).getId_persona());
		parametros.put("cliente", persona.getNombreCompleto());
		Contenido contenido = ContenidoServicio.getInstance().obtenerContenido("General", "CLIENTE");
		String textoContenido = "Cliente";
		if (contenido != null && contenido.getTexto() != null && !contenido.getTexto().isEmpty()) {
			textoContenido = contenido.getTexto();
		}
		parametros.put("tipoUsuario", textoContenido);
		
		params.put("parametros", parametros);
		for (FtpUsuarioCorreo correo : ftpUsuario.getCorreos_correval()) {
			params.put("to", correo.getCorreo());
			ThreadUtils.newThread(this, "enviarNotificacion", params);
		}
	}

	// *****************************************************************************************

	public Boolean cargarArchivosSamba(FtpUsuario ftpUsr) {
		if (!EN_USO) {
			try {
				EN_USO = true;
				if (ftpUsr == null) {
					SimpleLogger.setError("Error en CargaAutomaticaServicio.cargarArchivosSamba: no existe el objeto ftpUsuario");
					return false;
				}

				ftpUsuario = ftpUsr;

				Map<String, String> carpetas = new HashMap<String, String>();
				carpetas.put("transito", ftpUsuario.getCarpeta_transito());
				carpetas.put("transito_bk", ftpUsuario.getCarpeta_bk_transito());
				carpetas.put("fallidos", ftpUsuario.getCarpeta_fallidos());
				carpetas.put("fallidos_bk", ftpUsuario.getCarpeta_bk_fallidos());
				carpetas.put("procesados", ftpUsuario.getCarpeta_procesados());
				carpetas.put("procesados_bk", ftpUsuario.getCarpeta_bk_procesados());

				SambaServicio sambaServicio = new SambaServicio();
				String estado = sambaServicio.verificarDatos(ftpUsuario.getId_usuario(), ftpUsuario.getHost(), ftpUsuario.getDominio(), ftpUsuario.getNombre_usuario(), ftpUsuario.getClave(), carpetas);
				sambaServicio = null;

				if (!SambaServicio.VERIFICACION_OK.equals(estado)) {

					if (SambaServicio.VERIFICACION_ERROR_CONEXION.equals(estado)) {
						enviarNotificacionEstado(new EstadoConexion("No se pudo establecer conexiï¿½n", "ordenes_pago/errorConexion.email"));
					}

					if (SambaServicio.VERIFICACION_ERROR_RUTAS.equals(estado)) {
						enviarNotificacionEstado(new EstadoConexion("Error en las Rutas", "ordenes_pago/errorRutas.email"));
					}

					if (SambaServicio.VERIFICACION_ERROR_PERMISOS.equals(estado)) {
						enviarNotificacionEstado(new EstadoConexion("Carpeta sin Permisos de Lectura/Escritura", "ordenes_pago/carpetaSinPermisos.email"));
					}

					return false;
				}

				return leerArchivosSamba();

			} catch (Exception e) {
				enviarNotificacionEstado(new EstadoConexion("No se pudo establecer conexiï¿½n", "ordenes_pago/errorConexion.email"));
				return false;

			} finally {
				ftpUsuario = null;
				EN_USO = false;
			}
		}
		SimpleLogger.setInfo("La carga automï¿½tica no se iniciï¿½ debido a que ya existe una carga en proceso");
		return false;

	}

	// *****************************************************************************************

	private Boolean leerArchivosSamba() throws Exception {
		try {

			SambaUtils smbTransito = new SambaUtils(ftpUsuario.getHost(), ftpUsuario.getCarpeta_transito(), ftpUsuario.getDominio(), ftpUsuario.getNombre_usuario(), ftpUsuario.getClave());
			SambaUtils smbTransitoBk = new SambaUtils(ftpUsuario.getHost(), ftpUsuario.getCarpeta_bk_transito(), ftpUsuario.getDominio(), ftpUsuario.getNombre_usuario(), ftpUsuario.getClave());
			SambaUtils smbProcesados = new SambaUtils(ftpUsuario.getHost(), ftpUsuario.getCarpeta_procesados(), ftpUsuario.getDominio(), ftpUsuario.getNombre_usuario(), ftpUsuario.getClave());
			SambaUtils smbProcesadosBk = new SambaUtils(ftpUsuario.getHost(), ftpUsuario.getCarpeta_bk_procesados(), ftpUsuario.getDominio(), ftpUsuario.getNombre_usuario(), ftpUsuario.getClave());
			SambaUtils smbFallidos = new SambaUtils(ftpUsuario.getHost(), ftpUsuario.getCarpeta_fallidos(), ftpUsuario.getDominio(), ftpUsuario.getNombre_usuario(), ftpUsuario.getClave());
			SambaUtils smbFallidosBk = new SambaUtils(ftpUsuario.getHost(), ftpUsuario.getCarpeta_bk_fallidos(), ftpUsuario.getDominio(), ftpUsuario.getNombre_usuario(), ftpUsuario.getClave());

			smbTransito.connect();
			smbTransitoBk.connect();
			smbProcesados.connect();
			smbProcesadosBk.connect();
			smbFallidos.connect();
			smbFallidosBk.connect();

			List<SmbFile> lista = smbTransito.getListOfFiles();

			if (lista != null && !lista.isEmpty()) {

				File carpetaDestino = new File(ParametrosInicio.getProperty("file.carpeta") + "/CARGA_AUTOMATICA/");

				carpetaDestino.mkdirs();

				for (SmbFile smbFile : lista) {
					if (smbFile.isFile()) {

						String nombre_tmp = simpleDateFormat.format(new Date()) + "." + StringUtils.randomString(4);

						File destino = new File(carpetaDestino.getAbsolutePath() + "/" + nombre_tmp);

						// Obtenemos el archivo
						if (smbFile.canRead() && smbFile.canWrite()) {

							smbTransito.getFile(smbFile.getName(), destino);

							String md5 = MD5Checksum.getMD5CheckSum(new FileInputStream(destino));

							String fileNameBk = simpleDateFormat.format(new Date()) + "." + md5 + "." + smbFile.getName();

							// Copiamos el archivo a la carpeta backup
							smbTransitoBk.setFile(destino, fileNameBk);

							FtpUsuarioArchivo ftpUsuarioArchivo = new FtpUsuarioArchivo();

							ftpUsuarioArchivo.setNombre(smbFile.getName());
							ftpUsuarioArchivo.setFecha_cargue(new Date());
							ftpUsuarioArchivo.setEstado(FtpUsuarioArchivo.ESTADO_TRANSITO);
							ftpUsuarioArchivo.setId_ftp_usuario(ftpUsuario.getId_ftp_usuario());
							ftpUsuarioArchivo.setMd5(md5);
							FtpUsuarioArchivoServicio.getInstance().validarDuplicado(ftpUsuarioArchivo);

							if (FtpUsuarioArchivoServicio.getInstance().guardarFtpUsuarioArchivo(ftpUsuarioArchivo)) {

								if ("S".equals(ftpUsuarioArchivo.getDuplicado())) {

									archivoFallido(ftpUsuarioArchivo, destino, fileNameBk, smbFallidos, smbFallidosBk);

									if (ftpUsuarioArchivo.getId_carga() != null) {
										CargaServicio.getInstance().eliminarCarga(ftpUsuarioArchivo.getId_carga());
									}

									Map<String, String> parametros = new HashMap<String, String>();
									parametros.put("archivo", ftpUsuarioArchivo.getNombre());
									parametros.put("fechaCargue", ftpUsuarioArchivo.getFecha_cargue_formato());

									enviarNotificacionEstado(new EstadoConexion("Archivo Duplicado", "ordenes_pago/archivoDuplicado.email"), parametros);

								} else {

									destino = procesarArchivo(ftpUsuarioArchivo, destino);

									if (ftpUsuarioArchivo.getEstado().equals(FtpUsuarioArchivo.ESTADO_PROCESADO)) {

										archivoProcesado(ftpUsuarioArchivo, destino, fileNameBk, smbProcesados, smbProcesadosBk);

										Carga carga = null;

										boolean valor_total_error = true;

										Integer i = 0;
										do {

											carga = CargaServicio.getInstance().obtenerCarga(ftpUsuarioArchivo.getId_carga());
											valor_total_error = (carga.getValor_total() == null);

											if (valor_total_error) {
												Thread.sleep(10000);
											}

											i++;

										} while (valor_total_error && i < 100);

										Map<String, String> parametros = new HashMap<String, String>();
										parametros.put("archivo", ftpUsuarioArchivo.getNombre());

										parametros.put("valor", CargaServicio.getInstance().obtenerCarga(ftpUsuarioArchivo.getId_carga()).getValor_total_bigdecimal().toString());
										parametros.put("registros", CargaServicio.getInstance().obtenerCarga(ftpUsuarioArchivo.getId_carga()).getNumero_registros_bigdecimal().toString());

										enviarNotificacionEstado(new EstadoConexion("Carga Exitosa", "ordenes_pago/cargaExitosa.email"), parametros);

									} else {
										archivoFallido(ftpUsuarioArchivo, destino, fileNameBk, smbFallidos, smbFallidosBk);
									}

								}

							} else {
								SimpleLogger.setError("No se guardo el registro del archivo en la base de datos");
							}

							smbTransito.deleteFile(smbFile);

						} else {

							Map<String, String> parametros = new HashMap<String, String>();
							parametros.put("archivo", smbFile.getName());
							parametros.put("fechaCargue", StringUtils.toStringFormat(new Date()));

							enviarNotificacionEstado(new EstadoConexion("Archivo sin Permisos de Lectura/Escritura", "ordenes_pago/archivoSinPermisos.email"), parametros);

							return false;
						}

					}
				}

			}

			return true;

		} catch (Exception e) {
			SimpleLogger.setError("Error en CargaAutomaticaServicio.leerArchivosSamba", e);

			enviarNotificacionEstado(new EstadoConexion("No se pudo establecer conexión", "ordenes_pago/errorConexion.email"));

			return false;
		}
	}

	private Boolean archivoFallido(FtpUsuarioArchivo ftpUsuarioArchivo, File file, String fileNameBk, SambaUtils smbFallidos, SambaUtils smbFallidosBk) {
		try {

			ftpUsuarioArchivo.setEstado(FtpUsuarioArchivo.ESTADO_FALLIDO);
			FtpUsuarioArchivoServicio.getInstance().actualizarFtpUsuarioArchivo(ftpUsuarioArchivo);
			return FtpUsuarioArchivoServicio.getInstance().actualizarFtpUsuarioArchivo(ftpUsuarioArchivo) && smbFallidos.setFile(file, ftpUsuarioArchivo.getNombre()) && smbFallidosBk.setFile(file, fileNameBk);
		} catch (Exception e) {
			SimpleLogger.setError("Error en CargaAutomaticaServicio.archivoFallido", e);
			return false;
		}
	}

	private Boolean archivoProcesado(FtpUsuarioArchivo ftpUsuarioArchivo, File file, String fileNameBk, SambaUtils smbProcesados, SambaUtils smbProcesadosBk) {
		try {

			ftpUsuarioArchivo.setEstado(FtpUsuarioArchivo.ESTADO_PROCESADO);
			FtpUsuarioArchivoServicio.getInstance().actualizarFtpUsuarioArchivo(ftpUsuarioArchivo);
			SimpleLogger.setDebug("El archivo " + ftpUsuarioArchivo.getNombre() + " se procesó con exito");
			return FtpUsuarioArchivoServicio.getInstance().actualizarFtpUsuarioArchivo(ftpUsuarioArchivo) && smbProcesados.setFile(file, ftpUsuarioArchivo.getNombre()) && smbProcesadosBk.setFile(file, fileNameBk);
		} catch (Exception e) {
			SimpleLogger.setError("Error en CargaAutomaticaServicio.archivoProcesado", e);
			return false;
		}
	}

	private File procesarArchivo(FtpUsuarioArchivo ftpUsuarioArchivo, File file) {

		File file_enc = null;

		try {

			if ("S".equals(ftpUsuario.getEncripcion())) {

				try {
					String nombre_tmp = "e" + simpleDateFormat.format(new Date()) + "." + StringUtils.randomString(6);
					file_enc = new File(FilenameUtils.getFullPath(file.getAbsolutePath()) + "/" + nombre_tmp);
					FileUtils.copy(file, file_enc);
					InputStream fs = new FileInputStream(file);
					InputStream is = new ByteArrayInputStream(ftpUsuario.getLlave_privada().getBytes());
					char[] psswd = ftpUsuario.getClave_encripcion().toCharArray();
					file = PgpUtils.desencriptarArchivo(fs, is, psswd, file);
				} catch (Exception e) {
					SimpleLogger.setError("Error al desencriptar el archivo", e);
					return null;
				}
			}

			// obtenemos el formato del archivo
			Proceso proceso = ProcesoServicio.getInstance().obtenerProceso(ftpUsuario.getId_proceso());
			List<Formato> listado = FormatoServicio.getInstance().obtenerFormatosProceso(ftpUsuario.getId_proceso());

			if (listado != null && !listado.isEmpty() && listado.size() == 1) {
				Formato formato = listado.get(0);

				File file2 = new File(file.getAbsolutePath() + "_copy");
				FileUtils.copy(file, file2);

				session = new DummySession();

				session.setAttribute("var.id_formato", formato.getId_formato());
				session.setAttribute("id_persona", ID_PERSONA);
				session.setAttribute("id_usuario", ftpUsuario.getId_usuario());
				session.setAttribute("var.id_usuario", ftpUsuario.getId_usuario());
				session.setAttribute("var.id_negocio_carga", proceso.getId_negocio());
				session.setAttribute("ruta_plano", file2.getAbsolutePath());

				ftpUsuarioArchivo.setId_formato(formato.getId_formato());

				Boolean exito = false;

				Integer id_carga = null;

				Map<String, List<String>> mapaErroresPorHoja = null;

				if (formato.getFormato_externo() != null) {

					// TODO: implementar interfaz para cargue de formatos externos

				} else {

					InputStream inp = new FileInputStream(file);

					if (FormatoServicio.getInstance().validarArchivoCarga(formato.getId_formato(), inp)) {

						Boolean huboErroresDeValidacionDatos = false;

						ValidadorDatosCarga validadorDatosCarga = new ValidadorDatosCarga();
						mapaErroresPorHoja = validadorDatosCarga.validarDatosArchivoCarga(formato.getId_formato(), inp, session);

						if (mapaErroresPorHoja != null) {
							Set<String> strings = mapaErroresPorHoja.keySet();
							if (strings.size() > 0) {
								huboErroresDeValidacionDatos = true;
							}
						}

						if (huboErroresDeValidacionDatos) {
							ftpUsuarioArchivo.setDatos_invalidos("S");
							SimpleLogger.setDebug("El archivo " + ftpUsuarioArchivo.getNombre() + " contiene datos inválidos");
						} else {

							id_carga = CargaServicio.getInstance().obtenerSiguiente();

							exito = CargaServicio.getInstance().insertar(id_carga, "Archivo " + ftpUsuarioArchivo.getNombre(), "", file.getAbsolutePath(), formato.getId_formato(), ID_PERSONA, new Date(), Constantes.TIPOCARGA_LOTES, ftpUsuario.getId_proceso(), ftpUsuario.getId_usuario(), proceso.getId_negocio(), "0.0.0.0");
						}

					} else {
						ftpUsuarioArchivo.setFormato_invalido("S");
						SimpleLogger.setDebug("El archivo " + ftpUsuarioArchivo.getNombre() + " no tiene un formato válido");
					}
				}

				if (exito) {

					if (CargaServicio.getInstance().realizarCarga(id_carga, ID_PERSONA, proceso.getId_negocio(), session, null)) {
						ftpUsuarioArchivo.setEstado(FtpUsuarioArchivo.ESTADO_PROCESADO);
						ftpUsuarioArchivo.setId_carga(id_carga);
					}

				} else if ("S".equals(ftpUsuarioArchivo.getDatos_invalidos())) {

					Map<String, String> parametros = new HashMap<String, String>();
					parametros.put("archivo", ftpUsuarioArchivo.getNombre());
					parametros.put("fechaCargue", ftpUsuarioArchivo.getFecha_cargue_formato());
					String id = StringUtils.randomString(10);
					parametros.put("id", id);

					enviarNotificacionEstado(new EstadoConexion("Archivo con Datos Invï¿½lidos", "ordenes_pago/datosInvalidos.email"), parametros);

					mapaErrores.put(id, mapaErroresPorHoja);

				} else if ("S".equals(ftpUsuarioArchivo.getFormato_invalido())) {

					Map<String, String> parametros = new HashMap<String, String>();
					parametros.put("archivo", ftpUsuarioArchivo.getNombre());
					parametros.put("fechaCargue", ftpUsuarioArchivo.getFecha_cargue_formato());

					enviarNotificacionEstado(new EstadoConexion("Formato de Archivo Erroneo", "ordenes_pago/errorFormato.email"), parametros);
				}
			}

		} catch (Exception e) {
			SimpleLogger.setError("Error en CargaAutomaticaServicio.procesarArchivo", e);

		} finally {
			if ("S".equals(ftpUsuario.getEncripcion()) && file_enc != null) {
				file = file_enc;
			}
		}

		return file;

	}

	public void enviarNotificacion(Map<String, Object> params) {
		try {

			Map<String, String> archivos = new HashMap<String, String>();
			archivos.put("#logo#", "images/back/logo1.png");
			//archivos.put("#logo_potencia#", "images/back/Logo_potencia.png");

			String to = (String) params.get("to");
			String subject = (String) params.get("subject");
			String template = (String) params.get("template");
			@SuppressWarnings("unchecked")
			Map<String, String> parametros = (Map<String, String>) params.get("parametros");

			EnviaMails.enviar(to, to, subject, template, archivos, parametros, null);

		} catch (Exception e) {
			SimpleLogger.setError("Error en CargaAutomaticaServicio.enviarNotificacion", e);
		}

	}

	public static Map<String, List<String>> obtenerErrores(String id) {
		return mapaErrores.get(id);
	}

}