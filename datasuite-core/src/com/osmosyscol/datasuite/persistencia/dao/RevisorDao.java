package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.logica.dto.Revisor;


public interface RevisorDao {

	public List<Revisor> obtenerRevisores(Integer id_usuario, Integer id_tipo_proceso);

	public void asociarRevisor(Integer id_revision, Integer id_revisor);

	public void desasociarRevisor(Integer id_revision, Integer id_revisor);
	
	public boolean validarDesasociarRevisor(Integer id_revision, Integer id_revisor);
	
	public List<Revisor> obtenerRevisoresPorRevision(Integer id_revision);

	public Boolean guardarTipoRevisor(Integer id_usuario, Integer id_persona, Integer id_tipo_revisor);

}
