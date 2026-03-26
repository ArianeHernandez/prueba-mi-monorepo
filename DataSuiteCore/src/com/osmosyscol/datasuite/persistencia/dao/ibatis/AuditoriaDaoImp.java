package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.auditoria.dto.Auditoria;
import com.osmosyscol.datasuite.persistencia.dao.AuditoriaDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class AuditoriaDaoImp extends BaseSqlMapDao implements AuditoriaDao {

	public AuditoriaDaoImp(DaoManager daoManager) {
		super(daoManager);
	}
	
	public Boolean insertarAuditoria(Auditoria auditoria){		
		try {
			insert("Auditoria.insertarAuditoria", auditoria);
			return true;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en AuditoriaDaoImp.insertarAuditoria", e);
			return false;
		}
		
	}
	
	public Integer obtenerSiguienteIdAuditoria(){
		return (Integer) queryForObject("Auditoria.obtenerSiguienteIdAuditoria", null);
	}
	
	public Auditoria obtenerAuditoria(Integer id_Auditoria){
		return (Auditoria) queryForObject("Auditoria.obtenerAuditoria", id_Auditoria);
	}
	
	@SuppressWarnings("unchecked")
	public List<Auditoria> obtenerAuditoriasEspecificas(Integer target, String accionesSeparadasComa){
		
		Map<String, Object> mapa = new HashMap<String, Object>();
		
		mapa.put("target", target);
		mapa.put("acciones", accionesSeparadasComa);
		
		return (List<Auditoria>) queryForList("Auditoria.obtenerAuditoriasEspecificas", mapa);
	}
	
}
