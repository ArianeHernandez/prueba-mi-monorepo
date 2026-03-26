package com.osmosyscol.datasuite.persistencia.dao;


import java.util.Date;

import com.osmosyscol.datasuite.logica.dto.Horario;

public interface HorarioDao {

	Boolean crearHorario(Horario horario);

	Integer obtenerSiguienteIDHorario();
	
	Date obtenerHoraActual();
}
