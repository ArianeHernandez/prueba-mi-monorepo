package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.logica.dto.Preparador;


public interface PreparadorDao {

	public void desasociarPreparador(Integer id_preparador);

	public List<Preparador> obtenerPreparadores(Integer id_usuario, Integer id_tipo_proceso);

	public void asociarPreparador(Integer id_proceso, Integer id_preparador);
	
	public List<Preparador> obtenerPreparadoresPorProceso(Integer id_proceso);

}
