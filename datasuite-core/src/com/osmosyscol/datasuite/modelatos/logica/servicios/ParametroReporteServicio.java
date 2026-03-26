package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.util.ArrayList;
import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.persistencia.dao.ParametroReporteDao;
import com.osmosyscol.datasuite.reportedim.dto.ParametroReporte;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;


public class ParametroReporteServicio {

	private static ParametroReporteServicio parametrosReporteServicio;

	private ParametroReporteServicio() {
	}

	public static ParametroReporteServicio getInstance() {
		if (parametrosReporteServicio == null) {
			parametrosReporteServicio = new ParametroReporteServicio();
		}
		return parametrosReporteServicio;
	}
	
	public List<ParametroReporte> obtenerParametroReportePorReporte(String id_reporte){
		
		try {
			List<ParametroReporte> parametrosReporte = new ArrayList<ParametroReporte>();
			
			// Consultar de Parametros Reporte
			DaoManager daoManager = DaoConfig.getDaoManager();
			ParametroReporteDao parametrosReporteServicioDao = (ParametroReporteDao) daoManager.getDao(ParametroReporteDao.class);
			parametrosReporte = parametrosReporteServicioDao.obtenerParametrosByReporte(id_reporte);
			
			return parametrosReporte;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ParametroReporteServicio.obtenerParametroReportePorReporte", e);
			return null;
		}
	}
		
	public Integer obtenerSiguienteParametroReporteId(){
		
		try {
			List<ParametroReporte> parametrosReporte = new ArrayList<ParametroReporte>();
			
			// Consultar de Parametros Reporte
			DaoManager daoManager = DaoConfig.getDaoManager();
			ParametroReporteDao parametrosReporteServicioDao = (ParametroReporteDao) daoManager.getDao(ParametroReporteDao.class);
			return parametrosReporteServicioDao.obtenerSiguienteParametroReporteId();

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ParametroReporteServicio.obtenerSiguienteParametroReporteId", e);
			return null;
		}
	}

	
	public Boolean insertarParametroReporte(Integer id_parametro, String nombre, String tipo, String encriptado, String filtro, String titulo, Integer orden, String id_reporte){
		try {
			List<ParametroReporte> parametrosReporte = new ArrayList<ParametroReporte>();
			
			// Consultar de Parametros Reporte
			DaoManager daoManager = DaoConfig.getDaoManager();
			ParametroReporteDao parametrosReporteServicioDao = (ParametroReporteDao) daoManager.getDao(ParametroReporteDao.class);
			return parametrosReporteServicioDao.crearParametroReporte(id_parametro, nombre, tipo, encriptado, filtro, titulo, orden, id_reporte);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ParametroReporteServicio.insertarParametroReporte", e);
			return null;
		}

	}
	
	public Boolean insertarParametroReporte(Integer id_parametro, String nombre, String tipo, String encriptado, Boolean filtro, String titulo, Integer orden, String id_reporte){
		try {
			
			// Consultar de Parametros Reporte
			DaoManager daoManager = DaoConfig.getDaoManager();
			ParametroReporteDao parametrosReporteServicioDao = (ParametroReporteDao) daoManager.getDao(ParametroReporteDao.class);
			return parametrosReporteServicioDao.crearParametroReporte(id_parametro, nombre, tipo, encriptado, filtro, titulo, orden, id_reporte);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ParametroReporteServicio.insertarParametroReporte", e);
			return null;
		}

	}
	
}
