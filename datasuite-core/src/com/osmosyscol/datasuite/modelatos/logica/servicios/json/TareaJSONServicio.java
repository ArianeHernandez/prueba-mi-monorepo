package com.osmosyscol.datasuite.modelatos.logica.servicios.json;

import com.osmosyscol.datasuite.modelatos.logica.servicios.TareaServicio;


public class TareaJSONServicio {

	
	public static Boolean desactivar(Integer id_tarea) {
		return TareaServicio.getInstance().desactivarTarea(id_tarea);
	}

	public static Boolean activar(Integer id_tarea) {
		
		return TareaServicio.getInstance().activarTarea(id_tarea);
	}
	
	public static Boolean esActiva(Integer id_tarea){
		return TareaServicio.getInstance().esTareaActiva(id_tarea);
	}
	
	public static String calcularProximaEjecucion(Integer id_tarea){
		
		return TareaServicio.getInstance().calcularProximaEjecucion(id_tarea);
	}
	
	public Boolean eliminarTarea(Integer id_tarea){
		return TareaServicio.getInstance().eliminarTarea(id_tarea);
	}
}
