package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;
import java.util.Map;

import com.osmosyscol.datasuite.reportedim.dto.AsignacionReporte;

public interface AsignacionReporteDao {

	public AsignacionReporte obtenerAsignacionReporte(Integer id_asignacion);
	
	public List<AsignacionReporte> obtenerAsignacionesReporte();
	
	public List<AsignacionReporte> obtenerAsignacionesReporteUsuario(Integer id_usuario);
	
	public List<AsignacionReporte> obtenerAsignacionesReporteRolTodos(Integer id_rol);
	
	public List<AsignacionReporte> obtenerAsignacionReporteAdministrativo(Integer id_rol_auth);
	
	public List<AsignacionReporte> obtenerAsignacionesReporteCliente(Integer id_rol, Integer id_usuario);
	
	public Integer totalAsignacionReporte();
	
	public Boolean crearAsignacionReporte(Integer id_asignacion, String id_reporte, Integer id_rol, String titulo, String activo, Integer id_cliente, Integer id_rol_auth);
	
	public Integer obtenerSiguienteAsignacionReporteId();
	
	public Boolean eliminarAsignacionReporte(Integer id_asignacion);
	
	public Boolean actualizarAsignacionReporte(AsignacionReporte asignacion);

	
	
}
