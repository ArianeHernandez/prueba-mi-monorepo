package com.osmosyscol.datasuite.logica.servicios.json;

import java.util.List;

import com.osmosyscol.datasuite.logica.dto.Franja;
import com.osmosyscol.datasuite.logica.servicios.HorarioServicio;

public class HorarioJSONServicio {

	public List<Franja> obtenerFranjas(Integer id_horario){
		return HorarioServicio.getInstance().obtenerFranjas(id_horario);
	}
	
	
}
