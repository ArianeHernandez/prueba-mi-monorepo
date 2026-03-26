package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.logica.dto.Franja;

public interface FranjaDao {

	Boolean guardarFranjas(List<Franja> franjas, Integer id_horario);

	List<Franja> obtenerFranjas(Integer idHorario);
	
	Integer obtenerNumeroTotalFranjas (Integer id_horario);

}
