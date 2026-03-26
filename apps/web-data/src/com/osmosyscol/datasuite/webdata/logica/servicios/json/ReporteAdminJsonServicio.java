package com.osmosyscol.datasuite.webdata.logica.servicios.json;

import java.util.List;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ParametroReporteServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ReporteServicio;
import com.osmosyscol.datasuite.reportedim.builder.ServicioReporteDimBuilder;
import com.osmosyscol.datasuite.reportedim.dto.ParametroReporte;
import com.osmosyscol.datasuite.reportedim.dto.ServicioReporteDim;

public class ReporteAdminJsonServicio implements JsonService {

	@SuppressWarnings("unused")
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public static Boolean borrarReporteAdmin(String id_reporte) {

		return ReporteServicio.getInstance().eliminarReporte(id_reporte);

	}

	public List<ServicioReporteDim> obtenerReportesAdmin() {

		return ReporteServicio.getInstance().obtenerReportes();

	}

	public List<ParametroReporte> obtenerParametrosReportesAdmin(String id_reporte) {
		id_reporte = id_reporte.trim();
		return ParametroReporteServicio.getInstance().obtenerParametroReportePorReporte(id_reporte);

	}

	public ServicioReporteDim obtenerReporte(String id_reporte) {
		id_reporte = id_reporte.trim();
		return ReporteServicio.getInstance().obtenerReporte(id_reporte);

	}

	public String obtenerSiCargado(String id_reporte) {
		id_reporte = id_reporte.trim();
		ServicioReporteDim srd = ServicioReporteDimBuilder.getServicio(id_reporte);
		String returned = (srd == null) ? "" : "true";
		SimpleLogger.setInfo(id_reporte + " encontrado?: " + returned);
		return returned;
	}

	public Boolean AgregarListaServicios(String id_reporte) {
		id_reporte = id_reporte.trim();

		if (!obtenerSiCargado(id_reporte).equalsIgnoreCase("true")) {
			ServicioReporteDim srd = this.obtenerReporte(id_reporte);
			return ServicioReporteDimBuilder.cargaReporteBaseDatos(srd);
		} else {
			SimpleLogger.setInfo("El reporte [" + id_reporte + "] ya ha sido cargado...");
			return false;
		}

	}

	public Boolean QuitarListaServicios(String id_reporte) {
		id_reporte = id_reporte.trim();

		if (obtenerSiCargado(id_reporte).equalsIgnoreCase("true")) {
			return ServicioReporteDimBuilder.quitarReporteBaseDatos(id_reporte);
		} else {
			SimpleLogger.setInfo("El reporte [" + id_reporte + "] no habia sido cargado anteriormente...");
			return false;
		}
	}

	public String obtenerSiguienteReporteId() {
		return System.currentTimeMillis() + "" + Math.round(Math.random() * 1000);
	}
}
