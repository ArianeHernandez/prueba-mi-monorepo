package com.osmosyscol.osmoautenticador.servicio;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.osmoautenticador.dominio.Aplicacion;
import com.osmosyscol.osmoautenticador.dominio.Rol;
import com.osmosyscol.osmoautenticador.dominio.RolUs;
import com.osmosyscol.osmoautenticador.dominio.Servicio;
import com.osmosyscol.osmoautenticador.dominio.UrlServicio;
import com.osmosyscol.osmoautenticador.dominio.Usuario;
import com.osmosyscol.osmoautenticador.persistencia.dao.AdministracionDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class AdministracionServicio {

	private static AdministracionServicio administracionServicio;
	
	private static final int CANTIDAD_PAGINA = 10; 

	public static AdministracionServicio getInstance() {
		if (administracionServicio == null) {
			administracionServicio = new AdministracionServicio();
		}
		return administracionServicio;
	}

	// ---------------------

	public List<Aplicacion> obtenerAplicaciones() {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			AdministracionDao administradorDao = (AdministracionDao) daoManager.getDao(AdministracionDao.class);

			return administradorDao.obtenerAplicaciones();

		} catch (Exception e) {
			SimpleLogger.setError("Error en servicio obtenerAplicaciones", e);
		}

		return null;
	}

	public List<RolUs> obtenerRolesUsuario(String login) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			AdministracionDao administradorDao = (AdministracionDao) daoManager.getDao(AdministracionDao.class);

			return administradorDao.obtenerRolesLogin(login);
		} catch (Exception e) {
			SimpleLogger.setError("Error en servicio obtenerAplicaciones", e);
		}
		return null;
	}

	public Integer contarUsuariosRolAplicacion(String nombreAplicacion, Integer rol) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			AdministracionDao administradorDao = (AdministracionDao) daoManager.getDao(AdministracionDao.class);

			return administradorDao.contarUsuariosRolAplicacion(nombreAplicacion, rol);
		} catch (Exception e) {
			SimpleLogger.setError("Error en servicio contarUsuariosRolAplicacion", e);
		}
		return null;
	}

	public Integer numeroUsuariosRolCliente(String nombreAplicacion, Integer rol, String login) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			AdministracionDao administradorDao = (AdministracionDao) daoManager.getDao(AdministracionDao.class);

			return administradorDao.numeroUsuariosRolCliente(nombreAplicacion, rol, login);
		} catch (Exception e) {
			SimpleLogger.setError("Error en servicio numeroUsuariosRolCliente", e);
		}
		return null;
	}

	/**
	 * Obtener el listado de aplicaciones sin contenido
	 * 
	 * @return
	 */
	public List<Aplicacion> obtenerAplicacionesSimple() {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			AdministracionDao administradorDao = (AdministracionDao) daoManager.getDao(AdministracionDao.class);

			return administradorDao.obtenerAplicacionesSimple();

		} catch (Exception e) {
			SimpleLogger.setError("Error en servicio obtenerAplicacionesSimple", e);
		}

		return null;
	}

	public List<Rol> obtenerRolesAplicacion(Integer idAplicacion) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			AdministracionDao administradorDao = (AdministracionDao) daoManager.getDao(AdministracionDao.class);

			return administradorDao.obtenerRolesAplicacion(idAplicacion);

		} catch (Exception e) {
			SimpleLogger.setError("Error", e);
		}
		return null;
	}

	public List<Servicio> obtenerServiciosRol(Integer idRol) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			AdministracionDao administradorDao = (AdministracionDao) daoManager.getDao(AdministracionDao.class);

			return administradorDao.obtenerServiciosRol(idRol);

		} catch (Exception e) {
			SimpleLogger.setError("Error", e);
		}
		return null;
	}

	public List<Usuario> obtenerUsuariosRol(Integer idRol, Integer pagina) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			AdministracionDao administradorDao = (AdministracionDao) daoManager.getDao(AdministracionDao.class);

			return administradorDao.obtenerUsuariosRol(idRol, pagina, CANTIDAD_PAGINA);

		} catch (Exception e) {
			SimpleLogger.setError("Error", e);
		}
		return null;
	}

	public List<UrlServicio> obtenerUrlsServicio(Integer idServicio) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			AdministracionDao administradorDao = (AdministracionDao) daoManager.getDao(AdministracionDao.class);

			return administradorDao.obtenerUrlsServicio(idServicio);

		} catch (Exception e) {
			SimpleLogger.setError("Error", e);
		}
		return null;

	}

	public Integer contarUsuariosRol(Integer idRol) {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			AdministracionDao administradorDao = (AdministracionDao) daoManager.getDao(AdministracionDao.class);

			return administradorDao.contarUsuariosRol(idRol);

		} catch (Exception e) {
			SimpleLogger.setError("Error", e);
		}
		return null;
	}

}
// --------------------------------------

