package com.osmosyscol.datapi.logica.servicios;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.osmosyscol.commons.logging.dto.LogDto;

public class LogServicio {
	
	// #region 01 logger declaration ---------------------
	private static final Logger logger = LoggerFactory.getLogger(LogServicio.class);
	// #endregion 01 -------------------------------------

	
	// -- Posibles estado de la operacion
	public enum Estado {
		EXITO, FALLO
	}

	// -- Parametros cuando existen null en el log (login o nombre de usuario)
	public static final String NO_LOGIN = "NO_LOGIN";
	public static final String NO_NAME = "NO_NAME";
	public static String G3A_URL_LOG = "g3a.url.log";
	public static final String G3A_URL_LOG_PARAM = "g3a.url.log";

	// -- Nombre de la conexion que se usa para guardar el log en la BD
	public static final String NAME_BD_LOG = "LOG_BD";

	private static LogServicio logServicio;

	private LogServicio() {
	}

	public static LogServicio getInstance() {
		if (logServicio == null) {
			logServicio = new LogServicio();
		}
		return logServicio;
	}

	private LogDto construirLog(String url, String servicio, String ip, String login, String nombreUsuario, String aplicacion,
			String estado, String descripcion, String numeroConfirmacion, String numeroTransaccion) {
		LogDto dto = new LogDto();
		if (StringUtils.isBlank(login)) {
			login = NO_LOGIN;
		}
		if (StringUtils.isBlank(nombreUsuario)) {
			nombreUsuario = NO_NAME;
		}
		dto.setUrl(url);
		dto.setServicio(servicio);
		dto.setIp(ip);
		dto.setLogin(login);
		dto.setNombreUsuario(nombreUsuario);
		dto.setAplicacion(aplicacion);
		dto.setEstado(estado);
		dto.setDescripcion(descripcion);
		dto.setFecha(new Date());
		dto.setNumeroConfirmacion(numeroConfirmacion);
		dto.setNumeroTransaccion(numeroTransaccion);
		return dto;
	}

	public void registrarAccionLog(String url, String servicio, String ip, String login, String nombreUsuario, String aplicacion,
			Estado estado, String descripcion) {
		registrarAccionLog(url, servicio, ip, login, nombreUsuario, aplicacion, estado, descripcion, null, null);
	}

	public void registrarAccionLog(Integer id, String url, String servicio, String ip, String login, String nombreUsuario,
			String aplicacion,
			String estado, String descripcion, String numeroConfirmacion, String numeroTransaccion) {
		try {

			LogDto dto = construirLog(id, url, servicio, ip, login, nombreUsuario, aplicacion, estado, descripcion,
					numeroConfirmacion, numeroTransaccion);

//			registrarAccionLog(dto);
		} catch (Exception e) {
			logger.error("[LOG] No es posible guardar el registro en la base de datos " + NAME_BD_LOG, e);
		}
	}

	private LogDto construirLog(Integer id, String url, String servicio, String ip, String login, String nombreUsuario,
			String aplicacion, String estado, String descripcion, String numeroConfirmacion, String numeroTransaccion) {
		LogDto dto = construirLog(url, servicio, ip, login, nombreUsuario, aplicacion, estado, descripcion, numeroConfirmacion,
				numeroTransaccion);
		dto.setId(id);
		return dto;
	}

	public void registrarAccionLog(String url, String servicio, String ip, String login, String nombreUsuario, String aplicacion,
			Estado estado, String descripcion, String numeroConfirmacion, String numeroTransaccion) {
		try {

			LogDto dto = construirLog(url, servicio, ip, login, nombreUsuario, aplicacion, estado.name(), descripcion,
					numeroConfirmacion, numeroTransaccion);

//			registrarAccionLog(dto);
		} catch (Exception e) {
			logger.error("[LOG] No es posible guardar el registro en la base de datos " + NAME_BD_LOG, e);
		}
	}

//	public void registrarAccionLog(LogDto dto) {
//		String urlLog = ParametrosInicio.getProperty(G3A_URL_LOG_PARAM);
//
//		if (urlLog != null) {
//			G3A_URL_LOG = urlLog;
//		}
//
//		if (DaoConfig.existDaoManager(NAME_BD_LOG)) {
//			DaoManager daoManager = DaoConfig.getDaoManager(NAME_BD_LOG);
//			LogDao logDao = (LogDao) daoManager.getDao(LogDao.class);
//			logDao.guardar(dto);
//		} else {
//			logger.Info("[LOG] No configurada la conexión a la BD " + NAME_BD_LOG + " para almacenar el log en la BD");
//		}
//	}
//
//	public static Integer obtenerNuevoConsecutivo() throws Exception {
//		DaoManager daoManager = DaoConfig.getDaoManager(NAME_BD_LOG);
//		LogDao logDao = (LogDao) daoManager.getDao(LogDao.class);
//
//		return logDao.obtenerNuevoConsecutivo();
//
//	}
}
