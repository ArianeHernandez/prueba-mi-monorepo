package com.osmosyscol.datasuite.webdata.logica.servicios.json;

import java.util.Date;
import java.util.List;

import com.osmosyscol.datasuite.logica.dto.Franja;
import com.osmosyscol.datasuite.logica.servicios.HorarioServicio;

public class ParametrizacionClienteJSONServicio {

	public List<Franja> obtenerFranjas(Integer id_horario){
		return HorarioServicio.getInstance().obtenerFranjas(id_horario);
	}
	
	public Boolean cumpleHorarioLiberarion(Integer id_horario){
		return cumpleHorario(id_horario);
		
	}
	
	
	public Boolean cumpleHorarioFormato(Integer id_horario){
		return cumpleHorario(id_horario);
	}
	
	public Boolean cumpleHorario(Integer id_horario){

		HorarioServicio horarioServicio = HorarioServicio.getInstance();
		Date fechaActual = horarioServicio.obtenerFechaActual();
		
		return horarioServicio.estaDentroDeHorario(id_horario, fechaActual);

	}
	
}
