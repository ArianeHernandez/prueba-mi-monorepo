package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.persistencia.dao.AccionFilaDao;
import com.osmosyscol.datasuite.persistencia.dao.ParametroAccionDao;
import com.osmosyscol.datasuite.reportedim.dto.AccionFila;
import com.osmosyscol.datasuite.reportedim.dto.ParametroAccion;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;


public class AccionFilaServicio {

	private static AccionFilaServicio accionFilaServicio;

	private AccionFilaServicio() {
	}

	public static AccionFilaServicio getInstance() {
		if (accionFilaServicio == null) {
			accionFilaServicio = new AccionFilaServicio();
		}
		return accionFilaServicio;
	}
	
	public AccionFila obtenerAccionFila(String id_reporte){
		
		try {
			AccionFila accionesFila = new AccionFila();
			
			// Consultar de AccionFilas
			DaoManager daoManager = DaoConfig.getDaoManager();
			AccionFilaDao accionFilaServicioDao = (AccionFilaDao) daoManager.getDao(AccionFilaDao.class);
			accionesFila = accionFilaServicioDao.obtenerAccionFila(id_reporte);
			
			if(accionesFila == null) return null;
			
			// Consultar Parametros de Accion Fila
			List<ParametroAccion> listaParametros = ParametroAccionServicio.getInstance().obtenerParametrosAccionPorAccionFila(id_reporte);
			if(listaParametros != null){
				accionesFila.setParametros(listaParametros);
			}
			
			return accionesFila;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en AccionFilaServicio.obtenerAccionFila", e);
			return null;
		}
	}
	
	public Boolean insertarAccionFila(AccionFila accionFila){
		
		boolean correcto = true;
		
		try {
						
			DaoManager daoManager = DaoConfig.getDaoManager();
			AccionFilaDao accionFilaServicioDao = (AccionFilaDao) daoManager.getDao(AccionFilaDao.class);
			correcto = correcto && accionFilaServicioDao.crearAccionFila(accionFila.getDestino(), accionFila.getSubreporte(), accionFila.getId_reporte());
			
			if(!correcto)
				throw new Exception("Ha ocurrido un error al crear la Accion:\n" + accionFila.toString());
			
			// Creando Parametros de navegacion
			ParametroAccionDao parametroAccionDao = (ParametroAccionDao) daoManager.getDao(ParametroAccionDao.class);
			if(accionFila.getParametros() != null){
				for(ParametroAccion pa : accionFila.getParametros()){
					pa.setId_reporte(accionFila.getId_reporte());
					Integer id = parametroAccionDao.obtenerSiguienteParametroAccionId();
					correcto = correcto && parametroAccionDao.crearParametroAccion(id, pa.getNombre(), pa.getTipo(), pa.getEncriptado(), pa.getValor(), pa.getId_reporte(), pa.getId_navegacion());
					if(!correcto)
						throw new Exception("Ha ocurrido un error al crear un parametro de la accion:\n" + pa.toString());
				}
			}

			return correcto;
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en AccionFilaServicio.insertarAccionFila", e);
			return false;
		}
	}
			
}
