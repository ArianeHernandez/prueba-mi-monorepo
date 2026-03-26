package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pizza.support.booleanArray;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.logica.dto.Revisor;
import com.osmosyscol.datasuite.persistencia.dao.RevisorDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class RevisorDaoImp extends BaseSqlMapDao implements RevisorDao {

	public RevisorDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	// --------------------------------------------------------

	@SuppressWarnings("unchecked")
	public List<Revisor> obtenerRevisores(Integer id_usuario, Integer id_tipo_proceso) {
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_usuario", id_usuario);
		map.put("id_tipo_proceso", id_tipo_proceso);
		
		return queryForList("Revisor.obtenerRevisores", map);
	}

	// --------------------------------------------------------

	public void asociarRevisor(Integer id_revision, Integer id_revisor) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_revision", id_revision);
		map.put("id_revisor", id_revisor);

		insert("Revisor.asociarRevisor", map);
	}

	// --------------------------------------------------------

	@SuppressWarnings("unchecked")
	public void desasociarRevisor(Integer id_revision, Integer id_revisor) {
		
		List<Revisor> listaRevisoresPorRevision = queryForList("Revisor.obtenerRevisoresPorRevision", id_revision);
		if(listaRevisoresPorRevision.size()>1){
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("id_revision", id_revision);
			map.put("id_revisor", id_revisor);

			delete("Revisor.desasociarRevisor", map);
		}

		
	}

	// --------------------------------------------------------
	
	
	// --------------------------------------------------------

		@SuppressWarnings("unchecked")
		public boolean validarDesasociarRevisor(Integer id_revision, Integer id_revisor) {
			
			List<Revisor> listaRevisoresPorRevision = queryForList("Revisor.obtenerRevisoresPorRevision", id_revision);
			if(listaRevisoresPorRevision.size()>1){
				Map<String, Object> map = new HashMap<String, Object>();

				map.put("id_revision", id_revision);
				map.put("id_revisor", id_revisor);

				delete("Revisor.desasociarRevisor", map);
				return true;
			}
			
			return false;

			
		}

		// --------------------------------------------------------

	@SuppressWarnings("unchecked")
	public List<Revisor> obtenerRevisoresPorRevision(Integer id_revision) {
		return queryForList("Revisor.obtenerRevisoresPorRevision", id_revision);
	}

	@Override
	public Boolean guardarTipoRevisor(Integer id_usuario, Integer id_persona, Integer id_tipo_revisor) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_usuario", id_usuario);
		map.put("id_persona", id_persona);
		map.put("id_tipo_revisor", id_tipo_revisor);

		update("Revisor.guardarTipoRevisor", map);
		return true;
	}

}
