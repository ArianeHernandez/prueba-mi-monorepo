package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.logica.dto.Revision;

public interface RevisionDao {

	public void actualizarNombreRevision(Integer id_revision, String nombre);

	public void eliminarRevision(Integer id_revision);

	public Integer agregarRevision(Integer id_proceso, Integer id_revision_anterior, String nombre);
	
	public List<Revision> obtenerRevisiones(Integer id_proceso);

	public List<Revision> obtenerRevisionesRevisor(Integer idUsuario, Integer idPersona, Integer idNegocio);

	public Integer contarRevisionesRevisor(Integer idUsuario, Integer idPersona, Integer idNegocio);

	public Revision obtenerRevision(Integer idRevision);

	public void avanzarCargasRevision(Integer id_revision);

	public List <Revision> obtenerRevisionesRevisorProceso(Integer idUsuario, Integer idPersona, Integer pagina, Integer idNegocio, Integer idProceso);
	
	public Integer contarRevisionesRevisorProceso(Integer idUsuario, Integer idPersona, Integer idNegocio, Integer idProceso);
}
