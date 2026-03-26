package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.logica.dto.ConexionDTO;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class ConexionDao extends BaseSqlMapDao implements com.osmosyscol.datasuite.persistencia.dao.ConexionDao {

	public ConexionDao(DaoManager daoManager) {
		super(daoManager);
	}

	@SuppressWarnings("unchecked")
	public List<ConexionDTO> obtenerTodos() {
		return queryForList("Conexion.obtenerTodos", null);
	}

	public ConexionDTO obtener(Integer id) {
		return (ConexionDTO) queryForObject("Conexion.obtenerUNO", id);
	}

	public void actualizar(ConexionDTO conexionDTO) {
		update("Conexion.actualizar", conexionDTO);
	}

	public void insertar(ConexionDTO conexionDTO) {
		insert("Conexion.insertar", conexionDTO);
	}
}
