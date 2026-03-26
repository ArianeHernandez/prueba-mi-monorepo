package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.logica.dto.Preparador;
import com.osmosyscol.datasuite.persistencia.dao.PreparadorDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class PreparadorDaoImp extends BaseSqlMapDao implements PreparadorDao {

	public PreparadorDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	// --------------------------------------------------------

	public void desasociarPreparador(Integer id_preparador) {
		delete("Preparador.desasociarPreparador", id_preparador);
	}

	// --------------------------------------------------------

	@SuppressWarnings("unchecked")
	public List<Preparador> obtenerPreparadores(Integer id_usuario, Integer id_tipo_proceso) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("id_usuario", id_usuario);
		map.put("id_tipo_proceso", id_tipo_proceso);
		
		return queryForList("Preparador.obtenerPreparadores", map);
	}

	// --------------------------------------------------------

	public void asociarPreparador(Integer id_proceso, Integer id_preparador) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_proceso", id_proceso);
		map.put("id_preparador", id_preparador);

		insert("Preparador.asociarPreparador", map);

	}

	// --------------------------------------------------------
	
	public List<Preparador> obtenerPreparadoresPorProceso(Integer id_proceso) {
		return queryForList("Preparador.obtenerPreparadoresPorProceso", id_proceso);
	}

}
