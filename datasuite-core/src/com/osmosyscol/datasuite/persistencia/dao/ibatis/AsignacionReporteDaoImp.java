package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.persistencia.dao.AsignacionReporteDao;
import com.osmosyscol.datasuite.reportedim.dto.AsignacionReporte;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class AsignacionReporteDaoImp extends BaseSqlMapDao implements AsignacionReporteDao {

	public AsignacionReporteDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	public AsignacionReporte obtenerAsignacionReporte(Integer id_asignacion) {
		return (AsignacionReporte) queryForObject("AsignacionReporte.obtenerAsignacionReporte", id_asignacion);
	}

	public List<AsignacionReporte> obtenerAsignacionesReporte() {
		return queryForList("AsignacionReporte.obtenerAsignacionesReporte");
	}
	
	public List<AsignacionReporte> obtenerAsignacionesReporteUsuario(Integer id_usuario){
		return queryForList("AsignacionReporte.obtenerAsignacionesReporteUsuario", id_usuario);
	}
	
	public List<AsignacionReporte> obtenerAsignacionesReporteRolTodos(Integer id_rol){
		return queryForList("AsignacionReporte.obtenerAsignacionesReporteRolTodos", id_rol);
	}
	
	public List<AsignacionReporte> obtenerAsignacionReporteAdministrativo(Integer id_rol_auth){
		return queryForList("AsignacionReporte.obtenerAsignacionReporteAdministrativo", id_rol_auth);
	}
	
	public List<AsignacionReporte> obtenerAsignacionesReporteCliente(Integer id_rol, Integer id_usuario){
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("id_rol", id_rol);
		input.put("id_cliente", id_usuario);
		
		return queryForList("AsignacionReporte.obtenerAsignacionReporteCliente", input);
	}

	public Integer totalAsignacionReporte() {
		return (Integer) queryForObject("AsignacionReporte.totalAsignacionReportes");
	}

	public Integer obtenerSiguienteAsignacionReporteId() {
		return (Integer) queryForObject("AsignacionReporte.obtenerSiguienteAsignacionesReporteId");
	}
	
	public Boolean crearAsignacionReporte(Integer id_asignacion, String id_reporte, Integer id_rol, String titulo, String activo, Integer id_cliente, Integer id_rol_auth) {		
		try {
		
			if(id_asignacion == null)
				throw new Exception("No se a recibido ningun id para la nueva asignacion...");

			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("id_asignacion", id_asignacion);
			map.put("id_reporte", id_reporte);
			map.put("id_rol", id_rol);
			map.put("titulo", titulo);
			map.put("activo", activo);
			map.put("id_cliente", id_cliente);
			map.put("id_rol_auth", id_rol_auth);
		
			insert("AsignacionReporte.crearAsignacionReporte", map);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en AsignacionReporteDaoImp.crearAsignacionReporte", e);
			return false;
		}
		return true;
	}
	
	public Boolean eliminarAsignacionReporte(Integer id_asignacion){
		delete("AsignacionReporte.eliminarAsignacionReporte", id_asignacion);
		return true;
	}
	
	public Boolean actualizarAsignacionReporte(AsignacionReporte asignacion){
		try{
		
			if(asignacion.getId_asignacion() == null)
				throw new Exception("No se a recibido ningun id para la nueva asignacion...");
			
			if(asignacion.getActivo() == null)
				asignacion.setActivo("N");
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("id_asignacion", asignacion.getId_asignacion());
			map.put("id_reporte", asignacion.getId_reporte());
			map.put("id_rol", asignacion.getId_rol());
			map.put("titulo", asignacion.getTitulo());
			map.put("activo", asignacion.getActivo());
			map.put("id_cliente", asignacion.getId_cliente());
			map.put("id_rol_auth", asignacion.getId_rol_auth());
			
			update("AsignacionReporte.actualizarAsignacionReporte", asignacion);
		
		}catch(Exception e){
			SimpleLogger.setError("Ha ocurrido un error en AsignacionReporteDaoImp.actualizarAsignacionReporte", e);
			return false;
		}
		return true;
	}
}
