package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.persistencia.dao.ReporteDao;
import com.osmosyscol.datasuite.reportedim.dto.ServicioReporteDim;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class ReporteDaoImp extends BaseSqlMapDao implements ReporteDao {

	public ReporteDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	public ServicioReporteDim obtenerReporte(String id_reporte) {
		return (ServicioReporteDim) queryForObject("Reporte.obtenerReporte", id_reporte);
	}

	public Integer totalReportes() {
		return (Integer) queryForObject("Reporte.totalReportes");
	}

	public Boolean crearReporte(String id, String consulta, String nombre_conexion, String nombre) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(StringUtils.esVacio(id)){
			SimpleLogger.setError("Ha ocurrido un error en ReporteDaoImp.crearReporte\nEl ID no puede ser nulo.");
			return false;
		}
		
		map.put("id", id);
		map.put("consulta", consulta);
		map.put("nombre_conexion", nombre_conexion);
		map.put("nombre", nombre);
		
		Boolean conf = false;
		try {
			insert("Reporte.crearReporte", map);
			conf=true;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ReporteDaoImp.crearReporte", e);
		}
		return conf;
	}

	public Boolean actualizarReporte(ServicioReporteDim reporte) {
		Integer cantidad = update("Reporte.actualizarReporte", reporte) ;
		return cantidad > 0;
	}

	public Boolean eliminarReporte(String id_reporte) {
		delete("Reporte.eliminarReporte", id_reporte);
		return true;
	}
	
	public List<String> obtenerReportes(){
		return (List<String>) queryForList("Reporte.obtenerReportes");
	}
	
	public List<ServicioReporteDim> obtenerTodosReportes(){
		return (List<ServicioReporteDim>) queryForList("Reporte.obtenerTodosReportes");
	}
	
}
