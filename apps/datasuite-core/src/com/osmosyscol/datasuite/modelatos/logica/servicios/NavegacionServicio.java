package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.persistencia.dao.NavegacionDao;
import com.osmosyscol.datasuite.persistencia.dao.ParametroAccionDao;
import com.osmosyscol.datasuite.reportedim.dto.Navegacion;
import com.osmosyscol.datasuite.reportedim.dto.ParametroAccion;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;


public class NavegacionServicio {

	private static NavegacionServicio navegacionServicio;

	private NavegacionServicio() {
	}

	public static NavegacionServicio getInstance() {
		if (navegacionServicio == null) {
			navegacionServicio = new NavegacionServicio();
		}
		return navegacionServicio;
	}
	
	public List<Navegacion> obtenerNavegacionPorReporte(String id_reporte){
		
		List<Navegacion> navegaciones = null;
		
		try {
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			NavegacionDao navegacionDao = (NavegacionDao) daoManager.getDao(NavegacionDao.class);
			navegaciones = navegacionDao.obtenerNavegacionesByReporte(id_reporte);
			
			// Buscando Parametros de la navegacion
			for(Navegacion n : navegaciones){
				n.setParametros(ParametroAccionServicio.getInstance().obtenerParametrosAccionPorNavegacion(n.getId_navegacion()));
			}
			
			return navegaciones;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en NavegacionServicio.obtenerNavegacionPorReporte", e);
			return navegaciones;
		}
	}
	
	public Boolean crearNavegacion(Navegacion navegacion){
		
		boolean correcto = true;
		
		try {
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			NavegacionDao navegacionDao = (NavegacionDao) daoManager.getDao(NavegacionDao.class);
			Integer id = navegacionDao.obtenerSiguienteNavegacionId();
			correcto = correcto && navegacionDao.crearNavegacion(id, navegacion.getNombre(), navegacion.getDestino(), navegacion.getId_reporte());
			
			// Creando Parametros de navegacion
			ParametroAccionDao parametroAccionDao = (ParametroAccionDao) daoManager.getDao(ParametroAccionDao.class);
			if(navegacion.getParametros() != null && correcto){
				for(ParametroAccion pa : navegacion.getParametros()){
					pa.setId_navegacion(id);
					Integer id_accion = parametroAccionDao.obtenerSiguienteParametroAccionId();
					correcto = correcto && parametroAccionDao.crearParametroAccion(id_accion, pa.getNombre(), pa.getTipo(), pa.getEncriptado(), pa.getValor(), pa.getId_reporte(), pa.getId_navegacion());
					if(!correcto)
						throw new Exception("Ha ocurrido un error al crear un parametro de la navegacion:\n" + pa.toString());
				}
			}
				
			return correcto;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en NavegacionServicio.crearNavegacion", e);
			return false;
		}
	}
	
}
