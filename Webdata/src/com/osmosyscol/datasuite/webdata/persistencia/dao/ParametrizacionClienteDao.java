package com.osmosyscol.datasuite.webdata.persistencia.dao;

import com.osmosyscol.datasuite.logica.dto.Horario;



public interface ParametrizacionClienteDao {

	public Boolean crearHorarioLiberacion(Integer id_horario, Integer id_usuario);
	
	public Horario obtenerHorarioLiberacion(Integer id_usuario);
	
}
