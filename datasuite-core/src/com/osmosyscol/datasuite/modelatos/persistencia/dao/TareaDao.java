package com.osmosyscol.datasuite.modelatos.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.modelatos.logica.dto.Tarea;

public interface TareaDao {

	public List<Tarea> obtenerTareas(Integer numpagina);

	public Tarea obtenerTarea(Integer idTarea);

	public Integer guardarTarea(Tarea tarea);

	public Integer contarTareas();

	public 	Boolean actualizarTarea(Tarea tarea);

	public Boolean eliminarTarea(Integer idTarea);

}
