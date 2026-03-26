package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.AccionRedireccion;
import com.osmosyscol.datasuite.persistencia.dao.AccionRedireccionDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class AccionRedireccionDaoImp extends BaseSqlMapDao implements AccionRedireccionDao {

	public AccionRedireccionDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	public Integer obtenerSiguienteId () {
		Integer nextId = null;
		try {
			nextId = (Integer) queryForObject("AccionRedireccion.obtenerSiguienteId");
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en AccionRedireccionDaoImp.obtenerSiguienteId", e);
		}
		
		return nextId;
	}
	
	@Override
	public AccionRedireccion obtenerAccionRedireccionPorId (Integer id_accion_redireccion){ 
		AccionRedireccion accionRedireccion = null;
		try {
			accionRedireccion = (AccionRedireccion)queryForObject("AccionRedireccion.obtenerAccionRedireccionPorId", id_accion_redireccion);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en AccionRedireccionDaoImp.obtenerAccionRedireccionPorId", e);
		}
		return accionRedireccion;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<AccionRedireccion> obtenerAccionesRedireccionPorInstancia (Integer id_instancia){
		List<AccionRedireccion> accionesRedirecciones = null;
		
		try {
			accionesRedirecciones = queryForList("AccionRedireccion.obtenerAccionesRedireccionPorInstancia", id_instancia);
		} catch ( Exception e ) {
			SimpleLogger.setError("Ha ocurrido un error en AccionRedireccionDaoImp.obtenerAccionesRedireccionPorInstancia", e);
		}
		
		return accionesRedirecciones;
	}
	
	@Override
	public Boolean guardarAccionRedireccion(AccionRedireccion accionRedireccion) {
		Boolean resultado = false;
		try {
			insert("AccionRedireccion.insertarAccionRedireccion", accionRedireccion);
			resultado = true;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en AccionRedireccionDaoImp.guardarAccionRedireccion", e);
		}
		return resultado;
	}

	@Override
	public Boolean actualizarAccionRedireccion(AccionRedireccion accionRedireccion) {
		Boolean resultado = false;
		try {
			update("AccionRedireccion.actualizarAccionRedireccion", accionRedireccion);
			resultado = true;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en AccionRedireccionDaoImp.guardarAccionRedireccion", e);
		}
		return resultado;
	}
	
	@Override
	public Boolean borrarAccionRedireccion(Integer id_accion_redireccion) {
		Boolean resultado = false;
		try {
			delete("AccionRedireccion.borrarAccionRedireccion", id_accion_redireccion);
			resultado = true;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en AccionRedireccionDaoImp.borrarAccionRedireccion", e);
		}
		return resultado;
	}

}
