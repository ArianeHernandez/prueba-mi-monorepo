package com.osmosyscol.datasuite.near.servicios.json;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.config.Constantes;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.dto.Rol;
import com.osmosyscol.datasuite.near.dtos.SolicitudNEAR;
import com.osmosyscol.datasuite.near.servicios.ReporteSolicitudNEARServicio;
import com.osmosyscol.datasuite.webdata.servlet.DescargaArchivoServlet;

public class ReporteSolicitudNEARJsonServicio implements JsonService {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public Integer obtenerTotalSolicitudes(String radicado, String estado, String fecha_solicitud, String solicitante,
			String tipo_solicitante, String fecha_radicacion,String fecha_radicacion_fin, String identificacion, String categoria, String id_instancia,
			String intendencia, String id_solicitud, String proceso, String tramite, String responsable){
		Map<String, String> params = new HashMap<String, String>();
		params.put("radicado", radicado);
		params.put("estado", estado);
		params.put("fecha_solicitud", fecha_solicitud);
		params.put("solicitante", solicitante);
		params.put("tipo_solicitante", tipo_solicitante);
		params.put("fecha_radicacion", fecha_radicacion);
		params.put("fecha_radicacion_fin", fecha_radicacion_fin);
		params.put("identificacion", identificacion);
		params.put("categoria", categoria);
		params.put("id_instancia", id_instancia);
		params.put("intendencia", intendencia);
		params.put("id_solicitud", id_solicitud);
		params.put("proceso", proceso);
		params.put("tramite", tramite);
		params.put("responsable", responsable);
		session.setAttribute("parametros_busqueda_solicitudes", params);
		Integer id_usuario = (Integer) session.getAttribute("id_administrativo");
		return ReporteSolicitudNEARServicio.getInstance().obtenerTotalSolicitudes(params, id_usuario);
	}
	
	public List<SolicitudNEAR> obtenerSolicitudes(Integer pagina_actual){
		@SuppressWarnings("unchecked")
		Map<String, String> params = (Map<String, String>) session.getAttribute("parametros_busqueda_solicitudes");
		Integer id_usuario = (Integer) session.getAttribute("id_administrativo");
		return ReporteSolicitudNEARServicio.getInstance().obtenerSolicitudes(params, pagina_actual, Constantes.PAGINACIONLISTADO, id_usuario);
	}
	
	public List<Map<String, Object>> obtenerSolicitudesMap(Integer pagina_actual){
		@SuppressWarnings("unchecked")
		Map<String, String> params = (Map<String, String>) session.getAttribute("parametros_busqueda_solicitudes");
		Integer id_usuario = (Integer) session.getAttribute("id_administrativo");
		return ReporteSolicitudNEARServicio.getInstance().obtenerSolicitudesMap(params, pagina_actual, Constantes.PAGINACIONLISTADO, id_usuario, false);
	}
	
	public List<Map<String, Object>> obtenerSolicitudesMapParametro(String parametro){
		@SuppressWarnings("unchecked")
		Map<String, String> params = (Map<String, String>) session.getAttribute("parametros_busqueda_solicitudes");
		Integer id_usuario = (Integer) session.getAttribute("id_administrativo");
		return ReporteSolicitudNEARServicio.getInstance().obtenerSolicitudesMapParametro(parametro, params, id_usuario);
	}
	
	public Integer tamanoPagina(){
		return Constantes.PAGINACIONLISTADO;
	}

	public Integer generarReporte(){
		try {
			@SuppressWarnings("unchecked")
			Map<String, String> params = (Map<String, String>) session.getAttribute("parametros_busqueda_solicitudes");
			File wb = ReporteSolicitudNEARServicio.getInstance().generarReporte(params);
			return DescargaArchivoServlet.almacenarArchivo(wb);
		}catch (Exception e){
			SimpleLogger.setError("Ha ocurrido un error", e);
			return -1;
		}
	}
	
	
	public Integer generarReporteMap(){
		try {
			@SuppressWarnings("unchecked")
			Map<String, String> params = (Map<String, String>) session.getAttribute("parametros_busqueda_solicitudes");
			Integer id_usuario = (Integer) session.getAttribute("id_administrativo");
			File wb = ReporteSolicitudNEARServicio.getInstance().generarReporteMap(params, id_usuario);
			return DescargaArchivoServlet.almacenarArchivo(wb);
		}catch (Exception e){
			SimpleLogger.setError("Ha ocurrido un error", e);
			return -1;
		}
	}
	
	public List<Rol> obtenerRolesAdministrativo(){
		Integer id_usuario = (Integer) session.getAttribute("id_administrativo");
		return ReporteSolicitudNEARServicio.getInstance().obtenerRolesAdministrativo(id_usuario);
	}
	
	
	public List<Rol> obtenerRolesActivos(){
		return ReporteSolicitudNEARServicio.getInstance().obtenerRolesActivos();
	}

}
