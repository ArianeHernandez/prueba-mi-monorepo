package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.logica.dto.ConexionDTO;

public interface ConexionDao {

	public List<ConexionDTO> obtenerTodos();
	public ConexionDTO obtener(Integer id);
	public void insertar(ConexionDTO conexionDTO);
	public void actualizar(ConexionDTO conexionDTO);
	
}
