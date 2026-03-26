package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.logica.dto.Atributo;
import com.osmosyscol.datasuite.logica.dto.ValorAtributo;
import com.osmosyscol.datasuite.persistencia.dao.ValorAtributoDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class ValorAtributoDaoImp extends BaseSqlMapDao implements ValorAtributoDao {

	public ValorAtributoDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	// ----------------------------------------------------
	@Override
	public Integer crearValorAtributo(ValorAtributo valorAtributo) {
		Map<String, Object> map = new HashMap<>();
		map.put("id_administrativo", valorAtributo.getId_administrativo());
		map.put("id_atributo", valorAtributo.getId_atributo());
		map.put("valor", valorAtributo.getValor());
		try {
			insert("ValorAtributo.crearValorAtributo", map);
			return valorAtributo.getId_atributo();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// ----------------------------------------------------
	@Override
	public Boolean actualizarValorAtributo(ValorAtributo valorAtributo) {
		Map<String, Object> map = new HashMap<>();
		map.put("id_administrativo", valorAtributo.getId_administrativo());
		map.put("id_atributo", valorAtributo.getId_atributo());
		map.put("valor", valorAtributo.getValor());

		return update("ValorAtributo.actualizarValorAtributo", map) > 0;
	}

	// ----------------------------------------------------
	@Override
	public Boolean eliminarValorAtributo(ValorAtributo valorAtributo) {
		Map<String, Object> map = new HashMap<>();
		map.put("id_administrativo", valorAtributo.getId_administrativo());
		map.put("id_atributo", valorAtributo.getId_atributo());
		map.put("valor", valorAtributo.getValor());
		try {
			return delete("ValorAtributo.eliminarValorAtributo", map) > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// ----------------------------------------------------
	public Boolean eliminarValorAtributosAdministrativo(Integer id_administrativo) {
		return delete("ValorAtributo.eliminarValorAtributosAdministrativo", id_administrativo) > 0;
	}
	
	// ----------------------------------------------------
		public Boolean eliminarTodoValoresAtributo(Integer id_atributo) {
			return delete("ValorAtributo.eliminarTodoValoresAtributo", id_atributo) > 0;
		}
	
	// ----------------------------------------------------
	@SuppressWarnings("unchecked")
	public List<ValorAtributo> obtenerValorAtributo(ValorAtributo valorAtributo) {
		Map<String, Object> map = new HashMap<>();
		map.put("id_administrativo", valorAtributo.getId_administrativo());
		map.put("id_atributo", valorAtributo.getId_atributo());
		map.put("valor", valorAtributo.getValor());
		try {
			return queryForList("ValorAtributo.obtenerValorAtributo", map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// ----------------------------------------------------
	public Integer obtenerIdSiguiente() {
		return (Integer) queryForObject("ValorAtributo.obtenerIdSiguiente");
	}

	// ----------------------------------------------------
	
}
