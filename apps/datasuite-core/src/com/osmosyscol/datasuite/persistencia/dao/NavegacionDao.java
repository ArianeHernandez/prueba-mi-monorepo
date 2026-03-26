package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.reportedim.dto.Navegacion;


public interface NavegacionDao {

	public Navegacion obtenerNavegacion(Integer id_navegacion);
	
	public Integer totalNavegaciones();
	
	public Integer obtenerSiguienteNavegacionId();
	
	public Boolean crearNavegacion(Integer id_navegacion, String nombre, String destino, String id_reporte);
	
	public Boolean actualizarNavegacion(Navegacion navegacion);
	
	public Boolean eliminarNavegacion(Integer id_navegacion);
	
	public List<Navegacion> obtenerNavegacionesByReporte(String id_reporte);
	
	public Navegacion obtenerNavegacionByNavegacion(Navegacion navegacion);
	
}
