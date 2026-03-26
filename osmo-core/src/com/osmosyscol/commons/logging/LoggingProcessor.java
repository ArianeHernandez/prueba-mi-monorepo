package com.osmosyscol.commons.logging;

import java.util.List;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.logging.dto.LogDto;
import com.osmosyscol.commons.logging.loggers.Logger;
import com.osmosyscol.commons.logging.servicio.LoggingService;

public class LoggingProcessor extends Thread {

	public LoggingProcessor() {
		//SimpleLogger.setInfo("Iniciando hilo procesador de log. hilo: " + this.getId());
	}

	public void run() {

		try {
			SimpleLogger.setInfo("Procesador de logging corriendo");

			while (LoggingService.isRealizarlogging()) {

				LogDto dto = LoggingService.obtenerUltimoRegDeCola();
				SimpleLogger.setInfo("Inicio proceso guardado LogDto. hilo: " + this.getId());
				List<Logger> loggers = LoggingService.getLoggers();

				if (dto != null) {
					for (Logger logger : loggers) {
						logger.guardarLog(dto);
					}
				}
				SimpleLogger.setInfo("Termina proceso guardado LogDto. hilo: " + this.getId());

			}

			SimpleLogger.setInfo("Procesador de logging terminado. hilo:" + this.getId());
		} catch (Exception e) {
			SimpleLogger.setInfo("Error. Procesador de logging terminado. hilo: " + this.getId() + e);
		}

	}

}
