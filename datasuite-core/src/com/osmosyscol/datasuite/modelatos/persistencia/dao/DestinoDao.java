package com.osmosyscol.datasuite.modelatos.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.modelatos.logica.dto.Destino;


public interface DestinoDao {

	Destino obtenerDestino(Integer id_destino);

	List<Destino> obtenerTodosDestino();

	Destino guardarDestino(Destino destino);

	List<Destino> obtenerDestinosPorFormato(Integer id_formato);

	Boolean eliminarDestino(Integer id_destino);

}
