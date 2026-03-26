package com.osmosyscol.datasuite.webdata.correval.ordenespago;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.JobServicio;
import com.osmosyscol.datasuite.logica.servicios.ConfiguracionServicio;

public class CronReportePagos {

	private static CronReportePagos syncServicio;

	private CronReportePagos() {

	}

	public static CronReportePagos getInstance() {

		if (syncServicio == null) {
			syncServicio = new CronReportePagos();
		}
		return syncServicio;
	}

	// ------------------------------
	public static void iniciar() {

		SimpleLogger.setDebug("Se inicia servicio cron");

		String expression = ConfiguracionServicio.getInstance().obtenerValorByEtiqueta("REPORTE_ORDENES_PAGOS");

		if (expression != null) {
			JobServicio.getInstance().callService(CronReportePagos.class, "ejecutar", null, expression);
		}

	}

	public void ejecutar() {

		try {
			ReporteDiarioServicio.enviarEmailReporteDiarioPagos();
			SimpleLogger.setInfo("Se enviaron los reportes de ordenes de pago");
		} catch (Exception e) {
			SimpleLogger.setError("Error en servicio cron: ", e);
		}

	}

	public static void finalizar() {
		try {
			JobServicio.getScheduler().shutdown();
			SimpleLogger.setDebug("Se finaliza servicio cron");
		} catch (Throwable e) {
			SimpleLogger.setError("Error finalizando job", e);
		}
	}

}
