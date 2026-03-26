package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.logica.dto.AccionRedireccion;

public interface AccionRedireccionDao {

	public Integer obtenerSiguienteId ();
	
	public AccionRedireccion obtenerAccionRedireccionPorId (Integer id_accion_redireccion);
	
	public List<AccionRedireccion> obtenerAccionesRedireccionPorInstancia (Integer id_instancia);
	
	public Boolean guardarAccionRedireccion (AccionRedireccion accionRedireccion);
	
	public Boolean actualizarAccionRedireccion(AccionRedireccion accionRedireccion);
	
	public Boolean borrarAccionRedireccion (Integer id_accion_redireccion);
	
}
