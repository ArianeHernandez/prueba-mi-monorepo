package com.osmosyscol.datasuite.modelatos.logica.servicios.json;


import java.util.LinkedList;
import java.util.List;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.logica.dto.Rol;
import com.osmosyscol.datasuite.modelatos.logica.dto.Formato;
import com.osmosyscol.datasuite.modelatos.logica.dto.FormatoCampo;
import com.osmosyscol.datasuite.modelatos.logica.dto.HistorialConsulta;
import com.osmosyscol.datasuite.modelatos.logica.dto.HistorialConsultaConf;
import com.osmosyscol.datasuite.modelatos.logica.dto.HistorialConsultaDetalle;
import com.osmosyscol.datasuite.modelatos.logica.dto.HistorialConsultaRol;
import com.osmosyscol.datasuite.modelatos.logica.servicios.FormatoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.HistorialConsultaServicio;

public class HistorialConsultaJSONServicio implements JsonService{

	
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}
	
	
	public List<HistorialConsulta> obtenerConsultas () {
		return HistorialConsultaServicio.getInstance().obtenerConsultas();
	}
	
	public List<HistorialConsultaRol> obtenerConsultasRol () {
		return HistorialConsultaServicio.getInstance().obtenerConsultasRol();
	}
	
	public List<HistorialConsultaConf> obtenerConsultasConf () {
		return HistorialConsultaServicio.getInstance().obtenerConsultasConf();
	}
	
	public List<HistorialConsultaConf> obtenerConsultasConfVis () {
		return HistorialConsultaServicio.getInstance().obtenerConsultasConfVis();
	}
	
	public List<HistorialConsultaConf> obtenerConsultasConfFiltro () {
		return HistorialConsultaServicio.getInstance().obtenerConsultasConfFiltro();
	}
	
	public List<HistorialConsultaDetalle> obtenerConsultaDetalle () {
		return HistorialConsultaServicio.getInstance().obtenerConsultasDetalle();
	}
	
	public List<HistorialConsulta> obtenerConsultasId (Integer id_consulta) {
		return HistorialConsultaServicio.getInstance().obtenerConsultasId(id_consulta);
	}
	
	public List<HistorialConsultaConf> obtenerConsultasConfId (Integer id_consulta) {
		return HistorialConsultaServicio.getInstance().obtenerConsultasConfId(id_consulta);
	}
	
	public boolean actualizarConsulta (HistorialConsulta historialConsulta) {
		return HistorialConsultaServicio.getInstance().actualizarConsulta(historialConsulta);
	}
	
	public boolean actualizarConsultaRol (HistorialConsultaRol historialConsultaRol) {
		return HistorialConsultaServicio.getInstance().actualizarConsultaRol(historialConsultaRol);
	}
	
	public boolean eliminarConsultaRol (Integer idHistorialConsultaRol) {
		return HistorialConsultaServicio.getInstance().eliminarConsultaRol(idHistorialConsultaRol);
	}
	
	public boolean guardarConsultaConf (HistorialConsultaConf historialConsulta) {
		return HistorialConsultaServicio.getInstance().guardarConsultaConf(historialConsulta);
	}
	
	public boolean actualizarConsultaConf (HistorialConsultaConf historialConsulta) {
		return HistorialConsultaServicio.getInstance().actualizarConsultaConf(historialConsulta);
	}
	
	public boolean guardarConsultasRol(HistorialConsultaRol historialConsultaRol){
		return HistorialConsultaServicio.getInstance().guardarConsultasRol(historialConsultaRol);
	}
	
	public List<Rol> obtenerRolesActivos() {
		return HistorialConsultaServicio.getInstance().obtenerRolesActivos();
	}
			
}
