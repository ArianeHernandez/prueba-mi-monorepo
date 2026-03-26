package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.persistencia.dao.DatosEstructuraDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class DatosEstructuraDaoImp extends BaseSqlMapDao implements DatosEstructuraDao{

	public DatosEstructuraDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> obtenerDatosEstructura(String sql) {
		return queryForList("DatosEstructura.obtenerDatosEstructura", sql);
	}

	public Integer obtenerSiguiente() {
		return (Integer) queryForObject("DatosEstructura.obtenerSiguiente", null);
	}

	public void insertSQL(String sql) {
		insert("DatosEstructura.insertSQL", sql);
	}

	public Integer updateSQL(String sql) {
		return update("DatosEstructura.updateSQL", sql);
	}
	
	public void deleteSQL(String sql) {
		delete("DatosEstructura.deleteSQL", sql);
	}
	
}
