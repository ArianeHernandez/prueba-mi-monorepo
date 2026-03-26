package com.osmosyscol.datasuite.correval.thread;

import java.util.Calendar;
import java.util.Date;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.AutenticacionServicio;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.webdata.correval.servicios.RetiroServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class ActualizadorEstadoRetiro implements Runnable {

	public void run() {

		try {
			Thread.sleep(40000);
		} catch (InterruptedException e1) {
			return;
		}

		try {
			String str_horaEjecucion = ParametrosInicio.getProperty("retiros.horaEjecucion");
			String str_cantidadDiasHabilesT = ParametrosInicio.getProperty("retiros.cantidadDiasHabilesT");

			if (StringUtils.esVacio(str_cantidadDiasHabilesT, str_horaEjecucion)) {
				SimpleLogger.setInfo("No existe configuracion para actualizacion de estados de retiros sin respuesta banco y con archivo de salida generado...");
				return;
			}

			Integer horaEjecucion = 0;
			Integer cantidadDiasHabilesT = 0;
			try {
				horaEjecucion = Integer.valueOf(str_horaEjecucion);
				cantidadDiasHabilesT = Integer.valueOf(str_cantidadDiasHabilesT);
			} catch (NumberFormatException nfe) {
				SimpleLogger.setError("Se esperaba un numero en las propiedades de Actualizar Estado Retiro", nfe);
				return;
			}

			if (horaEjecucion > 24 || horaEjecucion < 0) {
				SimpleLogger.setError("La hora de Ejecucion debe ser entre 0 y 24...");
				return;
			}

			SimpleLogger.setInfo("Iniciando Actualizacion de Estado de Retiros");

			while (!AutenticacionServicio.SHUTDOWN) {

				Calendar calendario = Calendar.getInstance();
				Date ahora = calendario.getTime();
				Long milis_ahora = ahora.getTime();

				Long milis_ejecucion = null;

				calendario.set(Calendar.MINUTE, 0);
				calendario.set(Calendar.MILLISECOND, 0);

				Integer horaActual = calendario.get(Calendar.HOUR_OF_DAY);
				if (horaActual < horaEjecucion) {
					calendario.set(Calendar.HOUR_OF_DAY, horaEjecucion);
					Date hora_ejecucion = calendario.getTime();
					milis_ejecucion = hora_ejecucion.getTime();
				} else {
					calendario.add(Calendar.DATE, 1);
					calendario.set(Calendar.HOUR_OF_DAY, horaEjecucion);
					Date hora_ejecucion = calendario.getTime();
					milis_ejecucion = hora_ejecucion.getTime();
				}

				Long intervaloAct = milis_ejecucion - milis_ahora;

				SimpleLogger.setInfo("Esperando " + intervaloAct + " MS para ejecutar ActualizadorEstadoRetiro");

				try {
					Thread.sleep(intervaloAct);
				} catch (Exception e) {
					SimpleLogger.setInfo("Ha finalizado el servicio de ActualizadorEstadoRetiro");
					return;
				}

				try {

					actualizarRetirosAntiguos(cantidadDiasHabilesT);

				} catch (Exception e) {
					SimpleLogger.setError("Error al Actualizar Estados de Retiros", e);
				}

			}

		} catch (Throwable e) {
			SimpleLogger.setError("No se puede inicializar el servicio para Actualizar Estado Retiros.", e);
		}

	}

	public void actualizarRetirosAntiguos(Integer cantidadDiasHabilesT) {

		if (RetiroServicio.getInstance().esDiaHabil()) {

			SimpleLogger.setInfo("Actualizando Estados de Retiros");

			Integer cantidadActualizados = RetiroServicio.getInstance().actualizarEstadosRetirosAntiguos(cantidadDiasHabilesT);

			SimpleLogger.setInfo("Actualizacion de Estados de Retiros antiguos Exitosa. Se actualizaron : " + cantidadActualizados + " registros...");

		} else {
			SimpleLogger.setInfo("No se realiza actualizacion de Estados de Retiros. No se un dia habil.");
		}

	}
}
