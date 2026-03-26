package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.OperacionInterna;
import com.osmosyscol.datasuite.persistencia.dao.OperacionInternaDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class OperacionInternaAccionServicio {

	private static OperacionInternaAccionServicio instance;

	private OperacionInternaAccionServicio() {

	}

	public static OperacionInternaAccionServicio getInstance() {
		if (instance == null) {
			instance = new OperacionInternaAccionServicio();
		}
		return instance;
	}

	public List<OperacionInterna> obtenerListaOperacionesInternas() {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			OperacionInternaDao dao = (OperacionInternaDao) daoManager.getDao(OperacionInternaDao.class);
			return dao.listarOperacionesInternas();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<OperacionInterna> obtenerOperacionesInternasPorAccion(Integer id_accion) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			OperacionInternaDao dao = (OperacionInternaDao) daoManager.getDao(OperacionInternaDao.class);
			return dao.listarOperacionesInternasPorAccion(id_accion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Boolean insertarOperacionInternaAccion(Integer idOperacionInterna, Integer idAccion) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			OperacionInternaDao dao = (OperacionInternaDao) daoManager.getDao(OperacionInternaDao.class);
			return dao.insertarOperacionInternaDeAccion(idAccion, idOperacionInterna);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public Boolean borrarOperacionInternaAccion(Integer idOperacionInterna, Integer idAccion) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			OperacionInternaDao dao = (OperacionInternaDao) daoManager.getDao(OperacionInternaDao.class);
			return dao.eliminarOperacionInternaDeAccion(idAccion, idOperacionInterna);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public Boolean borrarTodasOperacionesInternasAccion(Integer idAccion) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			OperacionInternaDao dao = (OperacionInternaDao) daoManager.getDao(OperacionInternaDao.class);
			return dao.eliminarTodasOperacionInternaDeAccion(idAccion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public OperacionInterna obtenerOperacionInterna(Integer id_operacion_interna) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			OperacionInternaDao dao = (OperacionInternaDao) daoManager.getDao(OperacionInternaDao.class);
			return dao.obtenerOperacionInterna(id_operacion_interna);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<OperacionInterna> obtenerOperacionesDisponiblesParaAsignar(Integer id_accion){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			OperacionInternaDao dao = (OperacionInternaDao) daoManager.getDao(OperacionInternaDao.class);
			return dao.obtenerOperacionesDisponiblesParaAsignar(id_accion);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en OperacionInternaAccionServicio.obtenerOperacionesDisponiblesParaAsignar", e);
		}
		return null;
	}

}
