package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.config.Constantes;
import com.osmosyscol.datasuite.logica.dto.Revision;
import com.osmosyscol.datasuite.persistencia.dao.RevisionDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class RevisionDaoImp extends BaseSqlMapDao implements RevisionDao {

	public RevisionDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	// --------------------------------------------------------

	public void actualizarNombreRevision(Integer id_revision, String nombre) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_revision", id_revision);
		map.put("nombre", nombre);

		update("Revision.actualizarNombreRevision", map);

	}

	// --------------------------------------------------------

	public void eliminarRevision(Integer id_revision) {

		Integer id_revision_anterior = (Integer) queryForObject("Revision.obtenerIdRevisionAnterior", id_revision);
		Integer id_revision_siguiente = (Integer) queryForObject("Revision.obtenerIdRevisionSiguiente", id_revision);

		if (id_revision_anterior != null) {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id_revision", id_revision_anterior);
			map.put("id_revision_siguiente", id_revision_siguiente);
			update("Revision.actualizarSiguiente", map);

		}

		delete("Revision.eliminarRevisionRevisor", id_revision);
		delete("Revision.eliminarRevision", id_revision);

	}

	// --------------------------------------------------------

	public Integer agregarRevision(Integer id_proceso, Integer id_revision_anterior, String nombre) {

		Integer id_revision = (Integer) queryForObject("Revision.siguienteId", null);

		// --

		Revision revision_anterior = null;

		if (id_revision_anterior != null) {
			revision_anterior = (Revision) queryForObject("Revision.obtenerRevision", id_revision_anterior);
		}

		Integer id_revision_siguiente = null;

		if (revision_anterior != null) {
			id_revision_siguiente = revision_anterior.getId_revision_siguiente();
		} else {
			id_revision_siguiente = (Integer) queryForObject("Revision.obtenerIdPrimeraRevision", id_proceso);
		}

		Revision revision = new Revision();

		revision.setId_revision(id_revision);
		revision.setId_proceso(id_proceso);
		revision.setId_revision_siguiente(id_revision_siguiente);
		revision.setNombre(nombre);

		insert("Revision.agregarRevision", revision);

		// --

		if (id_revision_anterior != null) {
			Map<String, Object> map2 = new HashMap<String, Object>();

			map2.put("id_revision", id_revision_anterior);
			map2.put("id_revision_siguiente", id_revision);

			update("Revision.actualizarSiguiente", map2);
		}

		return id_revision;

	}

	// --------------------------------------------------------

	@SuppressWarnings("unchecked")
	public List<Revision> obtenerRevisiones(Integer id_proceso) {
		return (List<Revision>) queryForList("Revision.obtenerRevisiones", id_proceso);
	}

	@SuppressWarnings("unchecked")
	public List<Revision> obtenerRevisionesRevisor(Integer id_usuario, Integer id_persona, Integer id_negocio) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_persona", id_persona);
		map.put("id_usuario", id_usuario);
		map.put("id_negocio", id_negocio);
		return queryForList("Revision.obtenerRevisionesRevisor", map);
	}

	public Integer contarRevisionesRevisor(Integer id_usuario, Integer id_persona, Integer id_negocio) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_persona", id_persona);
		map.put("id_usuario", id_usuario);
		map.put("id_negocio", id_negocio);
		return (Integer)queryForObject("Revision.contarRevisionesRevisor", map);
	}

	public Revision obtenerRevision(Integer idRevision) {
		return (Revision) queryForObject("Revision.obtenerRevision", idRevision);
	}

	public void avanzarCargasRevision(Integer id_revision) {
		
		Revision rev = (Revision) queryForObject("Revision.obtenerRevision", id_revision);
		
		if(rev.getId_revision_siguiente() == null)
		{
			update("Revision.avanzarCargasALiberacion", id_revision);
			
		}else{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id_revision", id_revision);
			map.put("id_siguiente_revision", rev.getId_revision_siguiente());
			
			update("Revision.avanzarCargasARevision", map);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List <Revision> obtenerRevisionesRevisorProceso(Integer idUsuario, Integer idPersona, Integer pagina, Integer idNegocio, Integer idProceso){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_persona", idPersona);
		map.put("id_usuario", idUsuario);
		map.put("id_negocio", idNegocio);
		map.put("id_proceso", idProceso);
		return queryForListPag("Revision.obtenerRevisionesRevisor", map, pagina,Constantes.PAGINACIONLISTADO);		
	}
	
	public Integer contarRevisionesRevisorProceso(Integer idUsuario, Integer idPersona, Integer idNegocio, Integer idProceso){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_persona", idPersona);
		map.put("id_usuario", idUsuario);
		map.put("id_negocio", idNegocio);
		map.put("id_proceso", idProceso);
		return (Integer)queryForObject("Revision.contarRevisionesRevisor", map);		
	}
	
}
