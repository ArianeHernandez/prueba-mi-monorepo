package com.osmosyscol.datasuite.modelatos.persistencia.dao;

import java.util.Date;
import java.util.List;

import com.osmosyscol.datasuite.modelatos.logica.dto.Ejecucion;
import com.osmosyscol.datasuite.modelatos.logica.dto.Log;


public interface EjecucionDao {

	List<Ejecucion> obtenerEjecucionesPorCarga(Integer id_carga);

	Integer obtenerSiguienteID();

	Boolean crearEjecucion(Integer id_ejecucion, Integer id_carga, Integer id_destino, String estado, Date fecha_inicio, Date fecha_final, String login);

	List<Log> obtenerLogEjecucionesCarga(Integer id_carga);
	
	List<Log> obtenerLogErrorEjecucionesCarga(Integer id_carga);
	
	List<Log> obtenerLogEjecucion(Integer id_ejecucion);

}
