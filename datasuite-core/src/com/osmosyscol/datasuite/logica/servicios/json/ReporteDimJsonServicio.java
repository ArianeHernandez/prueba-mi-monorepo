package com.osmosyscol.datasuite.logica.servicios.json;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.logica.dto.ReporteDinamico;
import com.osmosyscol.datasuite.logica.servicios.ReporteDimServicio;
import com.osmosyscol.datasuite.reportedim.dto.ResultadoConsulta;

public class ReporteDimJsonServicio implements JsonService {

	private Session session;

	public ReporteDimJsonServicio() {
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public ResultadoConsulta obtenerDatosReporte(String idReporteUsuario, Integer numPagina) {

		return ReporteDimServicio.getInstance().obtenerDatosReporte(idReporteUsuario, session, numPagina);
	}

	public ReporteDinamico crearSubReporte(String id, String parametros) {
		return ReporteDimServicio.getInstance().crearSubReporte(id, parametros, session);
	}

	public ResultadoConsulta obtenerDatosReporte2(String idReporteUsuario, String parametros) {

		return ReporteDimServicio.getInstance().obtenerDatosReporte(idReporteUsuario, parametros, session);
	}
	
	public ResultadoConsulta obtenerDatosReporte3(String idReporteUsuario) {

		return ReporteDimServicio.getInstance().obtenerDatosReporte(idReporteUsuario, session);
	}

	public ReporteDinamico obtenerReporteDinamico(String idReporteUsuario){
		return ReporteDimServicio.getInstance().obtenerReporteDinamico(idReporteUsuario, session);
	}
}
