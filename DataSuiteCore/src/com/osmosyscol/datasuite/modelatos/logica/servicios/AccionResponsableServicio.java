package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.htsoft.commons.lang.P;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.dto.Accion;
import com.osmosyscol.datasuite.logica.dto.Instancia;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.dto.Rol;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;
import com.osmosyscol.datasuite.modelatos.logica.dto.Campo;
import com.osmosyscol.datasuite.modelatos.logica.dto.TipoCampo;
import com.osmosyscol.datasuite.persistencia.dao.AccionResponsableDao;
import com.osmosyscol.datasuite.persistencia.dao.PersonaDao;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class AccionResponsableServicio {

	private static AccionResponsableServicio accionResponsableServicio;

	private AccionResponsableServicio() {
	}

	public static AccionResponsableServicio getInstance() {
		if (accionResponsableServicio == null) {
			accionResponsableServicio = new AccionResponsableServicio();
		}
		return accionResponsableServicio;
	}

	// ------------------------------

	public List<Rol> obtenerRolesPorAccion( Integer id_accion ) {
		try {

			AccionResponsableDao accionResponsableDao = (AccionResponsableDao) DaoConfig.getDao(AccionResponsableDao.class);
			
			return accionResponsableDao.obtenerRolesPorAccion(id_accion);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
	public List<Rol> obtenerRolesActivosDisponiblesAccion(Integer id_accion) {
		try {

			AccionResponsableDao accionResponsableDao = (AccionResponsableDao) DaoConfig.getDao(AccionResponsableDao.class);
			
			return accionResponsableDao.obtenerRolesActivosDisponiblesAccion(id_accion);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	

	public Boolean insertarAccionRol(Integer id_rol, Integer id_accion) {
		try {

			AccionResponsableDao accionResponsableDao = (AccionResponsableDao) DaoConfig.getDao(AccionResponsableDao.class);
			
			return accionResponsableDao.insertarAccionRol(id_rol, id_accion);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public Boolean borrarAccionRol(Integer id_rol,	Integer id_accion) {
		try {

			AccionResponsableDao accionResponsableDao = (AccionResponsableDao) DaoConfig.getDao(AccionResponsableDao.class);
			
			return accionResponsableDao.borrarAccionRol(id_rol, id_accion);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
	public Boolean guardarAccionResponsable(Integer id_carga, Integer id_accion, Integer id_persona){
		
		try {
			
			AccionResponsableDao accionResponsableDao = (AccionResponsableDao) DaoConfig.getDao(AccionResponsableDao.class);
			List<Instancia> instanciasDestino = AccionServicio.getInstance().obtenerInstanciasDestinoPorAccion(id_accion);
			for (Instancia instancia : instanciasDestino) {
				if(obtenerResponsableAdmin(id_carga, instancia.getId_instancia() ) != null){
					accionResponsableDao.actualizarResponsable(id_carga, instancia.getId_instancia(), id_persona);
				}else {
					accionResponsableDao.guardarAccionResponsable(id_carga, instancia.getId_instancia(), id_persona);
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Persona> obtenerListaResponsablesPorAccion(Integer id_accion){
		AccionResponsableDao accionResponsableDao = (AccionResponsableDao) DaoConfig.getDao(AccionResponsableDao.class);
		
		return accionResponsableDao.obtenerListaResponsablesPorAccion(id_accion);
	}

	public boolean tieneRolesAsociados(Integer id_accion){
		
		try {
			AccionResponsableDao accionResponsableDao = (AccionResponsableDao) DaoConfig.getDao(AccionResponsableDao.class);
			List<Rol> roles = accionResponsableDao.obtenerRolesPorAccion(id_accion); 
			return roles.size() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean esResponsable(Integer id_carga, Integer id_instancia, Integer id_administrativo) {
		AccionResponsableDao accionResponsableDao = (AccionResponsableDao) DaoConfig.getDao(AccionResponsableDao.class);
		Integer id_administrativo_responsable = accionResponsableDao.obtenerResponsableAdmin(id_carga, id_instancia);
//		P.println("esResponsable: " + id_carga +" " +  id_instancia + " " + id_administrativo + " - " + id_administrativo_responsable);
		if(id_administrativo_responsable == null){
			return true;
		}else{
			return id_administrativo.equals(id_administrativo_responsable);
		}
	}
	
	public Integer obtenerResponsableAdmin(Integer id_carga, Integer id_instancia) {
		AccionResponsableDao accionResponsableDao = (AccionResponsableDao) DaoConfig.getDao(AccionResponsableDao.class);
		return accionResponsableDao.obtenerResponsableAdmin(id_carga, id_instancia);
	}
	
	public Integer obtenerResponsable(Integer id_carga, Integer id_accion) {
		AccionResponsableDao accionResponsableDao = (AccionResponsableDao) DaoConfig.getDao(AccionResponsableDao.class);
		return accionResponsableDao.obtenerResponsable(id_carga, id_accion);
	}
	
	public boolean actualizarResponsable(Integer id_carga, Integer id_instancia, Integer id_persona) {
		AccionResponsableDao accionResponsableDao = (AccionResponsableDao) DaoConfig.getDao(AccionResponsableDao.class);
		return accionResponsableDao.actualizarResponsable(id_carga, id_instancia, id_persona);
	}
	
	public List<Integer> obtenerResponsablesPorCarga (Integer id_carga) {
		try {
			AccionResponsableDao accionResponsableDao = (AccionResponsableDao) DaoConfig.getDao(AccionResponsableDao.class);
			return accionResponsableDao.obtenerResponsablesPorCarga(id_carga);			
		} catch (Exception e) {
			SimpleLogger.setError("Error en AccionResponsable.obtenerResponsablesPorCarga " + id_carga, e);
			return null;
		}
	}
	
	public List<Persona> obtenerResponsablesAsignados() {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			PersonaDao personaDao = (PersonaDao) daoManager.getDao(PersonaDao.class);
			return personaDao.obtenerResponsablesAsignados();
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
	}

	public Map<String, Object> obtenerInfoResponsableCarga(Integer id_carga) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			PersonaDao personaDao = (PersonaDao) daoManager.getDao(PersonaDao.class);
			personaDao.obtenerResponsablesAsignados();
			Map<String, Object> infoResponsableCarga = new HashMap<String, Object>();
			infoResponsableCarga.put("responsable_actual", PersonaServicio.getInstance().obtenerResponsableActualCarga(id_carga));
			infoResponsableCarga.put("lista_responsables", PersonaServicio.getInstance().obtenerPosiblesResponsablesCarga(id_carga));
			return infoResponsableCarga;
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
	}
	
	public boolean guardarResponsableCarga(Integer id_persona, Integer id_carga, Integer id_instancia) {
		try {
			
			AccionResponsableDao accionResponsableDao = (AccionResponsableDao) DaoConfig.getDao(AccionResponsableDao.class);
			if(obtenerResponsableAdmin(id_carga, id_instancia ) != null){
				return accionResponsableDao.actualizarResponsable(id_carga, id_instancia, id_persona);
			}else {
				return accionResponsableDao.guardarAccionResponsable(id_carga, id_instancia, id_persona);
			}
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}
	}
}
