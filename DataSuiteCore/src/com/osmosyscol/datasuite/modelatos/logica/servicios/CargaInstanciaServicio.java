package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.util.Date;
import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.CargaInstancia;
import com.osmosyscol.datasuite.persistencia.dao.CargaInstanciaDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;




public class CargaInstanciaServicio {

	private static CargaInstanciaServicio  servicio;

	private CargaInstanciaServicio() {
	}

	public static CargaInstanciaServicio getInstance() {
		if (servicio == null) {
			servicio = new CargaInstanciaServicio();
		}
		return servicio;
	}
	
	
	/***
	 * Esta crea una relacion entre la carga y la instancia
	 * <br/><br/>
	 * 
	 * @param id_carga 
	 * @param id_instancia
	 * @param fecha_llegada - PUEDE SER NULL
	 * 
	 * @author jcvargasj
	 * 
	 */
	
	public Boolean insertarRelacionCargaInstancia(Integer id_carga,Integer id_instancia, Date fecha_llegada){
		
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			
			CargaInstanciaDao cargaInstanciaDao = (CargaInstanciaDao) daoManager.getDao(CargaInstanciaDao.class);
			
			return cargaInstanciaDao.insertarRelacionCargaInstancia(id_carga, id_instancia, fecha_llegada);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en CargaInstanciaServicio.insertarRelacionCargaInstancia", e);
			return false;
		}
		
	}
	
	public Boolean actualizarFechaSalidaRelacionCargaInstancia(Integer id_carga,Integer id_instancia, Integer id_administrativo){	
		
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			
			CargaInstanciaDao cargaInstanciaDao = (CargaInstanciaDao) daoManager.getDao(CargaInstanciaDao.class);
			
			return cargaInstanciaDao.actualizarFechaSalidaRelacionCargaInstancia(id_carga, id_instancia, id_administrativo);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en CargaInstanciaServicio.actualizarFechaSalidaRelacionCargaInstancia", e);
			return false;
		}
	}
	
		
	
	public Integer obtenerIDUsuarioDeRelacionCargaInstancia(Integer id_carga, Integer id_instancia){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			
			CargaInstanciaDao cargaInstanciaDao = (CargaInstanciaDao) daoManager.getDao(CargaInstanciaDao.class);
			
			return cargaInstanciaDao.obtenerIDUsuarioDeRelacionCargaInstancia(id_carga, id_instancia);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error ", e);
			return null;
		}
		
	}
	
	
	public List<CargaInstancia> obtenerHistorialCargaInstancia (Integer id_carga){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			
			CargaInstanciaDao cargaInstanciaDao = (CargaInstanciaDao) daoManager.getDao(CargaInstanciaDao.class);
			
			return cargaInstanciaDao.obtenerHistorialCargaInstancia(id_carga);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error ", e);
			return null;
		}
		
		
	}
	
	
	/***
	 * Esta funcion se encarga de actualizar la fecha de salida de la relacion carga-instancia para la instancia desde donde sea ejecuta.
	 * A su vez, se encarga de eliminar las relaciones carga-instancia que aun no tengan fecha salida para la carga actual.
	 * <br/><br/>
	 * 
	 * Se utiliza cuando se rechaza o se aprueba una carga
	 * 
	 * @param id_carga
	 * @param id_instancia
	 * @param id_administrativo
	 * @author jcvargasj
	 * 
	 */

	public Boolean finalizarRelacionesCargaInstancia(Integer id_carga,Integer id_instancia_actual, Integer id_administrativo){
		
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			CargaInstanciaDao cargaInstanciaDao = (CargaInstanciaDao) daoManager.getDao(CargaInstanciaDao.class);
			
			//Se inicia la transaccion
			daoManager.startTransaction();
			
			
			try{
				//Se actualiza la fecha de salida de la instancia actual
				Boolean actualizacionExitosa = cargaInstanciaDao.actualizarFechaSalidaRelacionCargaInstancia(id_carga, id_instancia_actual, id_administrativo);
				
				if(actualizacionExitosa){
					Boolean eliminacionExitosa = cargaInstanciaDao.eliminarRelacionesCargaInstanciaSinFechaSalida(id_carga);
					
					if(eliminacionExitosa){
						//Si se pudieron eliminar las relaciones se termina la transaccion
						daoManager.commitTransaction();
						return true;
					}else{
						return false;
					}
					
				}else{
					return false;
				}
			
			}catch (Exception e) {
				SimpleLogger.setError("Error en finalizarRelacionesCargaInstancia ", e);
				return false;
			} finally {
				daoManager.endTransaction();
			}
		
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error ", e);
			
		} 
		
		return false;
		
	}
	
	
	
	public Boolean existeRelacionCargaInstanciaSinFechaSalida(Integer id_carga,
			Integer id_instancia) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			
			CargaInstanciaDao cargaInstanciaDao = (CargaInstanciaDao) daoManager.getDao(CargaInstanciaDao.class);
			
			return cargaInstanciaDao.existeRelacionCargaInstanciaSinFechaSalida(id_carga, id_instancia);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error ", e);
			return null;
		}
		
				
	}

	public List<CargaInstancia> obtenerCargaInstanciaActual (Integer id_carga){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			
			CargaInstanciaDao cargaInstanciaDao = (CargaInstanciaDao) daoManager.getDao(CargaInstanciaDao.class);
			
			return cargaInstanciaDao.obtenerCargaInstanciaActual(id_carga);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error ", e);
			return null;
		}
		
		
	}
	
	public List<CargaInstancia> obtenerCargasInstanciaActualPorInstanciaFecha (String nombre_instancia, Date fecha) {
		try {
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			
			CargaInstanciaDao cargaInstanciaDao = (CargaInstanciaDao) daoManager.getDao(CargaInstanciaDao.class);
			
			return cargaInstanciaDao.obtenerCargasInstanciaActualPorInstanciaFecha(nombre_instancia, fecha);
			
		} catch (Exception e) {
			SimpleLogger.setError("obtenerCargasInstanciaActualPorInstanciaFecha: Ha ocurrido un error ", e);
			return null;
		}
	}
	
	
		
	
	
}
