package com.osmosyscol.datasuite.webdata.logica.servicios.json;

import java.util.List;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.modelatos.logica.servicios.AsignacionReporteServicio;
import com.osmosyscol.datasuite.reportedim.dto.AsignacionReporte;

public class AsignacionReporteJsonServicio implements JsonService{

	private Session session;
	
	public void setSession(Session session) {
		this.session = session;
	}
	
	public List<AsignacionReporte> obtenerReportesAsignados(){
		
		return AsignacionReporteServicio.getInstance().obtenerAsignacionesReporte();
		
	}
	
	public AsignacionReporte obtenerReporteAsignado(String id){
		
		id = id.trim();
		int id_asignacion = 0;
		try{
			id_asignacion = Integer.parseInt(id);
			return AsignacionReporteServicio.getInstance().obtenerAsignacionReporte(id_asignacion);
		}catch(Exception e){
			return null;
		}
		
	}
	
	public Boolean borrarAsignacionReporte(Integer id_asignacion){
		
		return AsignacionReporteServicio.getInstance().eliminarAsignacionReporte(id_asignacion);
		
	}
	
	public Integer obtenerSiguienteAsignacionReporteId(){
		
		return AsignacionReporteServicio.getInstance().obtenerSiguienteAsignacionReporteId();
		
	}
		
}
