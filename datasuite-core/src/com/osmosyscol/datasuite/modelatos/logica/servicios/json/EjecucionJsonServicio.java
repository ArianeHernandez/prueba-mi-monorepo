package com.osmosyscol.datasuite.modelatos.logica.servicios.json;

import java.util.List;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.modelatos.logica.dto.Ejecucion;
import com.osmosyscol.datasuite.modelatos.logica.dto.Log;
import com.osmosyscol.datasuite.modelatos.logica.servicios.EjecucionServicio;

public class EjecucionJsonServicio implements JsonService{
	
	public static List<Log> obtenerLogEjecucionesCarga(Integer id_ejecucion) {
		return EjecucionServicio.getInstance().obtenerLogEjecucion(id_ejecucion);
	}

	public List<Ejecucion> obtenerEjecucionesPorCarga(Integer id_carga) {
		return EjecucionServicio.getInstance().obtenerEjecucionesPorCarga(id_carga);
	}

	public void setSession(Session session) {
	}
}
