package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.auditoria.dto.Auditoria;


public interface AuditoriaDao {

	public Boolean insertarAuditoria(Auditoria auditoria);
	
	public Integer obtenerSiguienteIdAuditoria();
	
	public Auditoria obtenerAuditoria(Integer id_auditoria);	
	
	public List<Auditoria> obtenerAuditoriasEspecificas(Integer target, String accionesSeparadasComa);
}
