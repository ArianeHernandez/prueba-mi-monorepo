package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.ProcesoAdmin;
import com.osmosyscol.datasuite.persistencia.dao.ProcesoAdminDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class ProcesoAdminDaoImp extends BaseSqlMapDao implements ProcesoAdminDao {

	public ProcesoAdminDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	public Boolean insertarProcesoAdmin(Integer id_proceso_admin,
			Integer id_negocio, Integer id_formato_entrada, String nombre, 
			Integer id_tipo_proceso) {
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_proceso_admin", id_proceso_admin);
		map.put("id_negocio", id_negocio);
		map.put("id_formato_entrada", id_formato_entrada);
		map.put("nombre", nombre);
		map.put("id_tipo_proceso", id_tipo_proceso);
		
		Boolean conf = false;
		try {
			insert("ProcesoAdmin.insertarProcesoAdmin", map);
			conf=true;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ProcesoAdminDaoImp.insertarProcesoAdmin", e);
		}
		return conf;
	}

	public ProcesoAdmin obtenerProcesoAdmin(Integer id_proceso_admin) {
		return (ProcesoAdmin) queryForObject("ProcesoAdmin.obtenerProcesoAdmin", id_proceso_admin);
	}

	@SuppressWarnings("unchecked")
	public List<ProcesoAdmin> obtenerProcesosAdminPorNegocio(Integer id_negocio) {
		return (List<ProcesoAdmin>) queryForList("ProcesoAdmin.obtenerProcesosAdminPorNegocio", id_negocio);
	}

	public Integer obtenerSiguienteId() {
		return (Integer) queryForObject("ProcesoAdmin.obtenerSiguienteId", null);
	}
	
	public Boolean borrarProcesoAdmin(Integer id_proceso_admin){
		Integer cant = delete("ProcesoAdmin.borrarProcesoAdmin", id_proceso_admin);
		return cant>0;
	}
	
	public Boolean actualizarProcesoAdmin(Integer id_proceso_admin,Integer id_negocio, Integer id_formato_entrada, String nombre, Integer id_tipo_proceso){
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_proceso_admin", id_proceso_admin);
		map.put("id_negocio", id_negocio);
		map.put("id_formato_entrada", id_formato_entrada);
		map.put("nombre", nombre);
		map.put("id_tipo_proceso", id_tipo_proceso);
		
		Integer cant = update("ProcesoAdmin.actualizarProcesoAdmin", map);
		
		return cant > 0;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<ProcesoAdmin> obtenerProcesosAdmin(){
		return queryForList("ProcesoAdmin.obtenerProcesosAdmin", null);
		
	}
	
	public Boolean verificarExistencia(Integer id_negocio, Integer id_formato_entrada, Integer id_proceso_admin){
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_negocio", id_negocio);
		
		String SQL_ID_FORMATO_ENTRADA = "1=1";
		
		if(id_formato_entrada == null){
			SQL_ID_FORMATO_ENTRADA=" id_formato_entrada is null ";
		}else {
			SQL_ID_FORMATO_ENTRADA=" id_formato_entrada = "+id_formato_entrada;
		}
		
		map.put("SQL_ID_FORMATO_ENTRADA", SQL_ID_FORMATO_ENTRADA);
		
		
		
		String SQL_ID_PROCESO_ADMIN = " 1=1 ";
		
		if(id_proceso_admin != null){
			SQL_ID_PROCESO_ADMIN="id_proceso_admin != "+id_proceso_admin;
		}
		
		map.put("SQL_ID_PROCESO_ADMIN", SQL_ID_PROCESO_ADMIN);
		
		Integer cant = (Integer) queryForObject("ProcesoAdmin.verificarExistencia", map);
		
		return cant > 0;
	}

	@SuppressWarnings("unchecked")
	public List<ProcesoAdmin> obtenerProcesosPorAdministrativo(Integer id_administrativo) {
		return queryForList("ProcesoAdmin.obtenerProcesosPorAdministrativo", id_administrativo);
	}

	public ProcesoAdmin obtenerProcesoParaAsociarCarga(Integer id_carga) {
		return (ProcesoAdmin)queryForObject("ProcesoAdmin.obtenerProcesoParaAsociarCarga", id_carga);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProcesoAdmin> obtenerProcesosAdminPorTipoProceso(Integer id_tipo_proceso){
		return queryForList("ProcesoAdmin.obtenerProcesosAdminPorTipoProceso", id_tipo_proceso);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProcesoAdmin> obtenerProcesosAdminPorTipoProcesoAdministrativo(Integer id_tipo_proceso, Integer id_administrativo){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("id_tipo_proceso", id_tipo_proceso);
		map.put("id_administrativo", id_administrativo);
		
		return queryForList("ProcesoAdmin.obtenerProcesosAdminPorTipoProcesoAdministrativo", map);
	}
	
}
