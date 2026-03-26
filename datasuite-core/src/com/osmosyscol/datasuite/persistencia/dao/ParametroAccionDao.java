package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.reportedim.dto.ParametroAccion;


public interface ParametroAccionDao {

	public ParametroAccion obtenerParametroAccion(Integer id_parametro);
	
	public Integer totalParametroAccion();
	
	public Integer obtenerSiguienteParametroAccionId();
	
	public Boolean crearParametroAccion(Integer id_parametro_accion,String nombre, String tipo, String encriptado, String valor, String id_reporte, Integer id_navegacion);
	
	public Boolean actualizarParametroAccion(ParametroAccion parametroAccion);
	
	public Boolean eliminarParametroAccion(Integer id_parametro);
	
	public List<ParametroAccion> obtenerParametrosByAccion(String id_reporte);
	
	public List<ParametroAccion> obtenerParametrosByNavegacion(Integer id_navegacion);
	
}
