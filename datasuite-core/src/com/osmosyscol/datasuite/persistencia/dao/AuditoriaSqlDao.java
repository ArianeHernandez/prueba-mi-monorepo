package com.osmosyscol.datasuite.persistencia.dao;

import com.osmosyscol.datasuite.logica.dto.AuditoriaSql;


public interface AuditoriaSqlDao {

	public Boolean insertarAuditoria(AuditoriaSql auditoriaSql);

	public Integer obtenerSiguienteIdAuditoriaSql();

}
