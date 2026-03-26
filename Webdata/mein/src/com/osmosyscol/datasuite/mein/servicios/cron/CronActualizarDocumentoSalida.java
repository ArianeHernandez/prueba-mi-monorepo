package com.osmosyscol.datasuite.mein.servicios.cron;

import java.util.Date;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.JobServicio;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.servicios.ArchivoAdjuntoServicio;
import com.osmosyscol.datasuite.webdata.correval.servicios.RetiroServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;


public class CronActualizarDocumentoSalida {

	private static CronActualizarDocumentoSalida instance;
	
	private CronActualizarDocumentoSalida() {
	}
	
	public static CronActualizarDocumentoSalida getInstance() {
		if (instance == null) {
			instance = new CronActualizarDocumentoSalida();
		}
		return instance;
	}
	
	public static void iniciar() {

		SimpleLogger.setInfo("Se inicia cron de actualizacion de estado interno para documentos de salida");
		
		String expression = ParametrosInicio.getProperty("ActualizarDocumentoSalida.expresion");
		
		if (expression == null) {
			expression = "0 0 8 ? * MON,TUE,WED,THU,FRI *";
		}
		
		JobServicio.getInstance().callService(CronActualizarDocumentoSalida.class, "ejecutar", null, expression);

	}
	
	
	public void ejecutar() {
		
		SimpleLogger.setInfo("Se inicia ejecucion para actualizar de estado interno para documentos de salida");
		
		try {
			if (RetiroServicio.getInstance().esDiaHabil()) {
				
				String diasHabiles_str = ParametrosInicio.getProperty("ActualizarDocumentoSalida.diasHabiles");
				if (StringUtils.esVacio(diasHabiles_str)) {
					SimpleLogger.setInfo("No existe configuracion de dias habiles para actualizacion de documentos de salida");
					return;
				}
				Integer diasHabiles = Integer.valueOf(diasHabiles_str);
				
				Date fecha = RetiroServicio.getInstance().fechaRestandoDiasHabiles(diasHabiles);
				
				Integer documentos_actualizados = ArchivoAdjuntoServicio.getInstance().actualizarDocumentosSalidaInterno(fecha);
				
				SimpleLogger.setInfo("CronActualizarDocumentoSalida: Archivos actualizados " + documentos_actualizados + ", fecha calculada: " + fecha);
				
			} else {
				SimpleLogger.setInfo("CronActualizarDocumentoSalida: Dia no habil");
			}
			
		} catch (Exception e) {
			SimpleLogger.setError("CronActualizarDocumentoSalida: Error ", e);
		}
		
		SimpleLogger.setInfo("CronActualizarDocumentoSalida: Finaliza ejecucion");
		
	}
	
	
	public static void finalizar() {
		try {
			JobServicio.getScheduler().shutdown();
			SimpleLogger.setDebug("Se finaliza servicio cron CronActualizarDocumentoSalida");
		} catch (Throwable e) {
			SimpleLogger.setError("Error finalizando job", e);
		}
	}
}
