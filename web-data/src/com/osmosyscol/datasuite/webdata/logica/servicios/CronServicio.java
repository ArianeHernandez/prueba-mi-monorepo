package com.osmosyscol.datasuite.webdata.logica.servicios;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.JobServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class CronServicio {

	private static CronServicio syncServicio;

	private CronServicio() {

	}

	public static CronServicio getInstance() {

		if (syncServicio == null) {
			syncServicio = new CronServicio();
		}
		return syncServicio;
	}

	// ------------------------------
	public static void iniciar() {

		SimpleLogger.setDebug("Se inicia servicio cron");

		String expression = ParametrosInicio.getProperty("CronServicio");

		if (expression != null) {
			JobServicio.getInstance().callService(CronServicio.class, "ejecutar", null, expression);
		}

	}

	public void ejecutar() {

		try {
			Integer total = FlujoCargaServicio.getInstance().liberarCargas();
			if (total > 0) {
				SimpleLogger.setInfo("Cargas liberadas: " + total);
			}
		} catch (Exception e) {
			SimpleLogger.setError("Error en servicio cron: ", e);
		}

		// Proceso para asociar las cargas subidas a instancias de procesos
		// administrativos
		try {
			Integer total = CargaServicio.getInstance().asociarCargasAInstanciasDeProceso();
			if (total > 0) {
				SimpleLogger.setInfo("Cargas asociadas a instancias de procesos administrativos: " + total);
			}
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
