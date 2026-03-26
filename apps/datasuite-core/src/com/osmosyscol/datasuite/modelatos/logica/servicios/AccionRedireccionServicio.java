package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.persistencia.dao.AccionRedireccionDao;
import com.osmosyscol.datasuite.logica.dto.AccionRedireccion;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class AccionRedireccionServicio {

	private static AccionRedireccionServicio instancia;
	private static AccionRedireccionDao dao;
	
	
	private AccionRedireccionServicio() {
		if (dao == null) {
			try {
				DaoManager daoManager = DaoConfig.getDaoManager();
				dao = (AccionRedireccionDao) daoManager.getDao(AccionRedireccionDao.class);
			}catch (Exception e) {
				SimpleLogger.setError("Error al inicializar el dao en AccionRedireccionServicio ", e);
			}
		}
	}

	public static AccionRedireccionServicio getInstance() {
		if (instancia == null) {
			instancia = new AccionRedireccionServicio();
		}
		return instancia;
	}
	
	public Integer obtenerSiguienteId () {
		return dao.obtenerSiguienteId();
	}
	
	public AccionRedireccion obtenerAccionRedireccionPorId (Integer id_accion_redireccion) {
		try {
			return dao.obtenerAccionRedireccionPorId(id_accion_redireccion);			
		} catch (Exception e) {
			SimpleLogger.setError("Error en AccionRedireccion.obtenerAccionRedireccionPorId ", e);
			return null;
		}
	}
	
	public List<AccionRedireccion> obtenerAccionesRedireccionPorInstancia (Integer id_instancia) {
		try {
			return dao.obtenerAccionesRedireccionPorInstancia(id_instancia);			
		} catch (Exception e) {
			SimpleLogger.setError("Error en AccionRedireccion.obtenerAccionesRedireccionPorInstancia ", e);
			return null;
		}
	}
	
	public Boolean guardarAccionRedireccion (AccionRedireccion accionRedireccion) {
		try {
			return dao.guardarAccionRedireccion(accionRedireccion);			
		} catch (Exception e) {
			SimpleLogger.setError("Error en AccionRedireccion.obtenerAccionRedireccionPorId ", e);
			return null;
		}
	}
	
	public Boolean actualizarAccionRedireccion (AccionRedireccion accionRedireccion) {
		try {
			return dao.actualizarAccionRedireccion(accionRedireccion);			
		} catch (Exception e) {
			SimpleLogger.setError("Error en AccionRedireccion.obtenerAccionRedireccionPorId ", e);
			return false;
		}
	}
	
	public Boolean actualizarNombreAccionRedireccion (Integer id_accion_redireccion, String nombre) {
		Boolean resultado = false;
		try {
			AccionRedireccion accionRedireccion = dao.obtenerAccionRedireccionPorId(id_accion_redireccion);
			
			if (accionRedireccion != null) {
				accionRedireccion.setNombre(nombre);
				
				resultado = actualizarAccionRedireccion(accionRedireccion);
			}
		} catch (Exception e) {
			SimpleLogger.setError("Error en AccionRedireccion.obtenerAccionRedireccionPorId ", e);
		}
		return resultado;
	}
	
	public Boolean actualizarEndpointAccionRedireccion (Integer id_accion_redireccion, String endpoint) {
		Boolean resultado = false;
		try {
			AccionRedireccion accionRedireccion = dao.obtenerAccionRedireccionPorId(id_accion_redireccion);
			
			if (accionRedireccion != null) {
				accionRedireccion.setEndpoint(endpoint);
				
				resultado = actualizarAccionRedireccion(accionRedireccion);
			}
		} catch (Exception e) {
			SimpleLogger.setError("Error en AccionRedireccion.obtenerAccionRedireccionPorId ", e);
		}
		return resultado;
	}
	
	public Boolean borrarAccionRedireccion (Integer id_accion_redireccion) {
		try {
			return dao.borrarAccionRedireccion(id_accion_redireccion);			
		} catch (Exception e) {
			SimpleLogger.setError("Error en AccionRedireccion.borrarAccionRedireccion ", e);
			return null;
		}
	}	
}
