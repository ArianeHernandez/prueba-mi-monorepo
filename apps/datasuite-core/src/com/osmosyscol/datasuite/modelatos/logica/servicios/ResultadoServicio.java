package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.util.ArrayList;
import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.persistencia.dao.ResultadoDao;
import com.osmosyscol.datasuite.reportedim.dto.Resultado;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;


public class ResultadoServicio {

	private static ResultadoServicio resultadoServicio;

	private ResultadoServicio() {
	}

	public static ResultadoServicio getInstance() {
		if (resultadoServicio == null) {
			resultadoServicio = new ResultadoServicio();
		}
		return resultadoServicio;
	}
	
	public List<Resultado> obtenerResultadosPorReporte(String id_reporte){
		
		try {
			List<Resultado> resultadosReporte = new ArrayList<Resultado>();
			
			// Consultar de Resultados Reporte
			DaoManager daoManager = DaoConfig.getDaoManager();
			ResultadoDao resultadoServicioDao = (ResultadoDao) daoManager.getDao(ResultadoDao.class);
			resultadosReporte = resultadoServicioDao.obtenerResultadosByReporte(id_reporte);
			
			return resultadosReporte;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ResultadoServicio.obtenerResultadoPorReporte", e);
			return null;
		}
	}

	public Integer obtenerSiguienteResultadoId(){
		
		try {			
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			ResultadoDao resultadoServicioDao = (ResultadoDao) daoManager.getDao(ResultadoDao.class);
			return resultadoServicioDao.obtenerSiguienteResultadoId();
			

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ResultadoServicio.obtenerSiguienteResultadoId", e);
			return null;
		}
	}

	
	public Boolean insertarResultado(Integer id, String titulo, String nombre, String tipo, Integer orden, String encriptado, String oculto, String id_reporte){
		try {
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			ResultadoDao resultadoServicioDao = (ResultadoDao) daoManager.getDao(ResultadoDao.class);
			return resultadoServicioDao.crearResultado(id, titulo, nombre, tipo, orden, encriptado, oculto, id_reporte);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ResultadoServicio.insertarResultado", e);
			return null;
		}
	}
	
}
