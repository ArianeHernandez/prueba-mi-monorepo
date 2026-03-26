package com.osmosyscol.datasuite.logica.servicios;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.cocoon.environment.Session;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.auditoria.dto.Auditoria;
import com.osmosyscol.datasuite.persistencia.dao.AuditoriaDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class AuditoriaServicio {

	private static AuditoriaServicio auditoriaServicio;

	private AuditoriaServicio() {
	}

	public static AuditoriaServicio getInstance() {
		if (auditoriaServicio == null) {
			auditoriaServicio = new AuditoriaServicio();
		}
		return auditoriaServicio;
	}

	// ----------------------------------------------------

	public Auditoria obtenerAuditoria(Integer id_auditoria) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			AuditoriaDao auditoriaDao = (AuditoriaDao) daoManager.getDao(AuditoriaDao.class);

			return auditoriaDao.obtenerAuditoria(id_auditoria);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un Error en el servicio obtenerAuditoria.", e);
		}

		return null;

	}

	// ----------------------------------------------------

	private Integer obtenerSiguienteIdAuditoria() {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			AuditoriaDao auditoriaDao = (AuditoriaDao) daoManager.getDao(AuditoriaDao.class);

			return auditoriaDao.obtenerSiguienteIdAuditoria();

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un Error en el servicio obtenerSiguienteIdAuditoria.", e);
		}

		return null;

	}

	// ----------------------------------------------------

	public Boolean insertarAuditoria(String accion, Boolean exitoso, String nuevo_valor) {
		return insertarAuditoria(0, accion, null, exitoso, nuevo_valor);
	}

	// ----------------------------------------------------

	public Boolean insertarAuditoria(Integer id_target, String accion, HttpServletRequest request, Boolean exitoso, String nuevo_valor) {

		try {

			String ipHost = "127.0.0.1";
			String login = "?";

			if (request != null) {
				ipHost = request.getHeader("REMOTE_HOST");
				if (ipHost == null) {
					ipHost = request.getRemoteHost();
				}

				HttpSession session = request.getSession(false);

				Session csession = (session != null) ? ((Session) session.getAttribute("cocoon-session")) : null;

				login = csession != null ? csession.getAttribute("login").toString() : "?";
			}

			Auditoria auditoria = new Auditoria();
			auditoria.setCodigo_accion(accion);
			auditoria.setFecha_accion(new Date());
			auditoria.setIp(ipHost);
			auditoria.setNuevo_valor(nuevo_valor);
			auditoria.setTarget(new Integer(id_target));
			auditoria.setUsuario(login.toLowerCase());
			auditoria.setEstado(exitoso ? "Y" : "N");

			return AuditoriaServicio.getInstance().insertarAuditoria(auditoria);

		} catch (Throwable e) {
			SimpleLogger.setError(e);
			return false;
		}

	}

	// ----------------------------------------------------

	public Boolean insertarAuditoria(Auditoria auditoria) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			AuditoriaDao auditoriaDao = (AuditoriaDao) daoManager.getDao(AuditoriaDao.class);

			auditoria.setId_auditoria(this.obtenerSiguienteIdAuditoria());

			return auditoriaDao.insertarAuditoria(auditoria);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un Error en el servicio obtenerAuditoria.", e);
		}

		return null;

	}

	// ----------------------------------------------------

	public List<Auditoria> obtenerAuditoriasEspecificas(Integer target, String... acciones) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			AuditoriaDao auditoriaDao = (AuditoriaDao) daoManager.getDao(AuditoriaDao.class);

			String accionesSeparadasComa = "";
			for (String accion : acciones) {
				accionesSeparadasComa += "'" + accion + "'" + ",";
			}
			accionesSeparadasComa = accionesSeparadasComa.substring(0, accionesSeparadasComa.length() - 1);

			SimpleLogger.setInfo("acciones auditadas :" + accionesSeparadasComa);

			return auditoriaDao.obtenerAuditoriasEspecificas(target, accionesSeparadasComa);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un Error en el servicio obtenerAuditoria.", e);
		}

		return null;

	}

}
