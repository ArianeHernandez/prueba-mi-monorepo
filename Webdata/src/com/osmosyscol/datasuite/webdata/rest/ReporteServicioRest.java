package com.osmosyscol.datasuite.webdata.rest;

import java.util.Map;

import javax.servlet.annotation.WebServlet;

import co.htsoft.commons.net.GET;
import co.htsoft.commons.net.POST;
import co.htsoft.commons.net.RestService;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.Mensaje;
import com.osmosyscol.datasuite.logica.servicios.ReporteDimServicio;
import com.osmosyscol.datasuite.reportedim.dto.ResultadoConsulta;

@WebServlet("/api/reportes/*")
public class ReporteServicioRest extends RestService{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1410840685002417186L;
	public static String estado = "OK";

	@GET("/api/reportes/test")
	public String test() {
		return estado;
	}
	
	@POST(value="/api/reportes/obtenerDatosReporte")
	public Mensaje<ResultadoConsulta> obtenerDatosReporte(Map<String, Object> parametrosReporte) {
		
		try {
			ResultadoConsulta datos = ReporteDimServicio.getInstance().obtenerDatosReporte(parametrosReporte);
			Mensaje<ResultadoConsulta> mensaje = new Mensaje<ResultadoConsulta>("OK", true);
			mensaje.setInfo(datos);
			return mensaje;
		} catch (Exception e) {
			SimpleLogger.setError("Error en servicio rest", e);
		}
		return new Mensaje<ResultadoConsulta>("Error", false);
	}

}
