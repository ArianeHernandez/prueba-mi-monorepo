package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.reportedim.dto.ServicioReporteDim;


public interface ReporteDao {

	public ServicioReporteDim obtenerReporte(String id_reporte);
	
	public Integer totalReportes();
	
	public Boolean crearReporte(String id, String consulta, String nombre_conexion, String nombre);
	
	public Boolean actualizarReporte(ServicioReporteDim reporte);
	
	public Boolean eliminarReporte(String id_reporte);
	
	public List<String> obtenerReportes();
	
	public List<ServicioReporteDim> obtenerTodosReportes();
	
}
