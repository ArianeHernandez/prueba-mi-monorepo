package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.logica.dto.Atributo;
import com.osmosyscol.datasuite.persistencia.dao.AtributoDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class AtributoDaoImp extends BaseSqlMapDao implements AtributoDao {

	public AtributoDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	// ----------------------------------------------------
	public Integer crearAtributo(Atributo atributo) {
		Map<String, Object> map = new HashMap<>();
		map.put("id_atributo", atributo.getId_atributo());
		map.put("nombre", atributo.getNombre());
		map.put("id_usuario", atributo.getId_usuario());
		try {
			insert("Atributo.crearAtributo", map);
			return atributo.getId_atributo();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	// ----------------------------------------------------
	public Boolean actualizarAtributo(Atributo atributo) {
		Map<String, Object> map = new HashMap<>();
		map.put("id_atributo", atributo.getId_atributo());
		map.put("nombre", atributo.getNombre());
		map.put("id_usuario", atributo.getId_usuario());

		return update("Atributo.actualizarAtributo", map) > 0;

	}

	// ----------------------------------------------------
	public Boolean eliminarAtributo(Integer id_atributo) {
		return delete("Atributo.eliminarAtributo", id_atributo) > 0;
	}

	// ----------------------------------------------------
	public Atributo obtenerAtributo(Integer id_atributo) {
		return (Atributo) queryForObject("Atributo.obtenerAtributo", id_atributo);
	}

	// ----------------------------------------------------
	@SuppressWarnings("unchecked")
	public List<Atributo> obtenerAtributosUsuario(Integer id_usuario) {
		return queryForList("Atributo.obtenerAtributosUsuario", id_usuario);
	}

	// ----------------------------------------------------
	public Integer obtenerIdSiguiente() {
		return (Integer) queryForObject("Atributo.obtenerIdSiguiente");
	}

	// ----------------------------------------------------
	
	

}
