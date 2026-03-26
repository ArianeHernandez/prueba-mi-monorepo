package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.reportedim.dto.ParametroReporte;


public interface ParametroReporteDao {

	public ParametroReporte obtenerParametroReporte(Integer id_parametro);
	
	public Integer totalParametroReporte();
	
	public Integer obtenerSiguienteParametroReporteId();
	
	public Boolean crearParametroReporte(Integer id_parametro, String nombre, String tipo, String encriptado, String filtro, String titulo, Integer orden, String id_reporte);
	
	public Boolean crearParametroReporte(Integer id_parametro, String nombre, String tipo, String encriptado, Boolean filtro, String titulo, Integer orden, String id_reporte);
	
	public Boolean actualizarParametroReporte(ParametroReporte parametroReporte);
	
	public Boolean eliminarParametroReporte(Integer id_parametro);
	
	public List<ParametroReporte> obtenerParametrosByReporte(String id_reporte);
}
