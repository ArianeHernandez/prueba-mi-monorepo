package com.osmosyscol.datasuite.mein.servicios.cron;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.JobServicio;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.dto.Accion;
import com.osmosyscol.datasuite.logica.dto.CargaInstancia;
import com.osmosyscol.datasuite.modelatos.logica.servicios.AccionServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.CargaInstanciaServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class CronActualizarCargasPendienteAnulacion {

	private static CronActualizarCargasPendienteAnulacion instance;
	
	private CronActualizarCargasPendienteAnulacion() {
	}
	
	public static CronActualizarCargasPendienteAnulacion getInstance() {
		if (instance == null) {
			instance = new CronActualizarCargasPendienteAnulacion();
		}
		return instance;
	}
	
	public static void iniciar() {

		SimpleLogger.setInfo("Se inicia cron de actualizacion de cargas en instancia pendiente anulacion");
		
		String expression = ParametrosInicio.getProperty("ActualizarCargasPendienteAnulacion.expresion");
		
		if (expression == null) {
			expression = "0 0 7 ? * * *";
		}
		
		JobServicio.getInstance().callService(CronActualizarCargasPendienteAnulacion.class, "ejecutar", null, expression);

	}
	
	public void ejecutar() {
		
		SimpleLogger.setInfo("Se inicia ejecucion para actualizar cargas en instancias pendiente anulacion");
		
		try {
			
			String dias_str = ParametrosInicio.getProperty("ActualizarCargasPendienteAnulacion.cantidadDias");
			if (StringUtils.esVacio(dias_str)) {
				SimpleLogger.setInfo("No existe configuracion de cantidad de dias para actualizacion de cargas");
				return;
			}
			Integer dias = Integer.valueOf(dias_str);
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DAY_OF_MONTH, -dias);
			Date fecha = cal.getTime();
			
			List<CargaInstancia> cargas = CargaInstanciaServicio.getInstance().obtenerCargasInstanciaActualPorInstanciaFecha("Validar Tiempo Ejecutoria%", fecha);
			
			for (CargaInstancia carga: cargas) {
				
				List<Accion> acciones = AccionServicio.getInstance().obtenerAccionesPorInstancia(carga.getId_instancia());
				
				if (acciones != null && acciones.size() > 0) {
					
					Accion accion_finalizar = null;
					
					for(Accion accion: acciones) {
						if ("finalizar solicitud".equalsIgnoreCase(accion.getNombre())) {
							accion_finalizar = accion;
							break;
						}
					}
					
					AccionServicio.getInstance().ejecutarAccion(carga.getId_carga(), carga.getId_instancia(), accion_finalizar.getId_accion());
				}
			}
			
		} catch (Exception e) {
			SimpleLogger.setError("CronActualizarCargasPendienteAnulacion: Error ", e);
		}
		
		SimpleLogger.setInfo("CronActualizarCargasPendienteAnulacion: Finaliza ejecucion");
		
	}
	
	public static void finalizar() {
		try {
			JobServicio.getScheduler().shutdown();
			SimpleLogger.setDebug("Se finaliza servicio cron CronActualizarCargasPendienteAnulacion");
		} catch (Throwable e) {
			SimpleLogger.setError("Error finalizando job", e);
		}
	}
	
}
