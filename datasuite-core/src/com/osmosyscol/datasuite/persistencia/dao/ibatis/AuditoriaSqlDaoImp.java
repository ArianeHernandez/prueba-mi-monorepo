package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.AuditoriaSql;
import com.osmosyscol.datasuite.persistencia.dao.AuditoriaSqlDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class AuditoriaSqlDaoImp extends BaseSqlMapDao implements AuditoriaSqlDao {

	public AuditoriaSqlDaoImp(DaoManager daoManager) {
		super(daoManager);
	}
	
	public Boolean insertarAuditoria(AuditoriaSql auditoriaSql){		
		try {
			insert("AuditoriaSql.insertarAuditoria", auditoriaSql);
			return true;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en AuditoriaSqlDaoImp.insertarAuditoria", e);
			return false;
		}
	}
	public Integer obtenerSiguienteIdAuditoriaSql(){
		return (Integer) queryForObject("AuditoriaSql.obtenerSiguienteIdAuditoriaSql", null);
	}
}
