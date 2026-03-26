package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.util.ArrayList;
import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.persistencia.dao.ParametroAccionDao;
import com.osmosyscol.datasuite.reportedim.dto.ParametroAccion;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;


public class ParametroAccionServicio {

	private static ParametroAccionServicio parametroAccionServicio;

	private ParametroAccionServicio() {
	}

	public static ParametroAccionServicio getInstance() {
		if (parametroAccionServicio == null) {
			parametroAccionServicio = new ParametroAccionServicio();
		}
		return parametroAccionServicio;
	}
	
	public List<ParametroAccion> obtenerParametrosAccionPorAccionFila(String id_reporte){
		
		try {
			List<ParametroAccion> parametrosAccion = new ArrayList<ParametroAccion>();
			
			// Consultar de Parametros Accion
			DaoManager daoManager = DaoConfig.getDaoManager();
			ParametroAccionDao parametroAccionServicioDao = (ParametroAccionDao) daoManager.getDao(ParametroAccionDao.class);
			parametrosAccion = parametroAccionServicioDao.obtenerParametrosByAccion(id_reporte);
			
			return parametrosAccion;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ParametroAccionServicio.obtenerParametroAccionPorAccionFila", e);
			return null;
		}
	}
	
	public List<ParametroAccion> obtenerParametrosAccionPorNavegacion(Integer id_navegacion){
		
		try {
			List<ParametroAccion> parametrosNavegacion = new ArrayList<ParametroAccion>();
			
			// Consulta de Parametros Navegacion
			DaoManager daoManager = DaoConfig.getDaoManager();
			ParametroAccionDao parametroAccionServicioDao = (ParametroAccionDao) daoManager.getDao(ParametroAccionDao.class);
			parametrosNavegacion = parametroAccionServicioDao.obtenerParametrosByNavegacion(id_navegacion);
			
			return parametrosNavegacion;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ParametroAccionServicio.obtenerParametroAccionPorNavegacion", e);
			return null;
		}
	}
	
	public Integer obtenerSiguienteParametrosAccionId(){
		
		try {
		
			DaoManager daoManager = DaoConfig.getDaoManager();
			ParametroAccionDao parametroAccionServicioDao = (ParametroAccionDao) daoManager.getDao(ParametroAccionDao.class);
			return parametroAccionServicioDao.obtenerSiguienteParametroAccionId();

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ParametroAccionServicio.obtenerSiguienteParametrosAccionId", e);
			return null;
		}
	}

	
	public Boolean insertarParametroAccion(Integer id_parametro_accion, String nombre, String tipo, String encriptado, String valor, String id_accion, Integer id_navegacion){
		try {
			List<ParametroAccion> parametrosNavegacion = new ArrayList<ParametroAccion>();
			
			// Consulta de Parametros Navegacion
			DaoManager daoManager = DaoConfig.getDaoManager();
			ParametroAccionDao parametroAccionServicioDao = (ParametroAccionDao) daoManager.getDao(ParametroAccionDao.class);
			
			return parametroAccionServicioDao.crearParametroAccion(id_parametro_accion, nombre, tipo, encriptado, valor, id_accion, id_navegacion);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ParametroAccionServicio.insertarParametroAccion", e);
			return null;
		}
	}
	
}
