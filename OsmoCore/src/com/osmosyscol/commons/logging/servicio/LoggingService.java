package com.osmosyscol.commons.logging.servicio;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.logging.LoggingProcessor;
import com.osmosyscol.commons.logging.dto.LogDto;
import com.osmosyscol.commons.logging.loggers.Logger;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class LoggingService {

	private static LoggingService servicio;

	private static final int TAMANO_MAX_COLA_DEFAULT = 200;
	private static final int TAMANO_MAX_HILOS_DEFAULT = 5;
	private static final int TAMANO_MAX_LOGGERS_DEFAULT = 5;

	private static final String LOG_MAX_COLA = "log.max.cola";
	private static final String LOG_MAX_HILOS = "log.max.hilos";
	private static final String LOG_MAX_LOGGERS = "log.max.loggers";

	private static boolean realizarLogging = true;

	private static List<Logger> loggers;
	private static BlockingQueue<LogDto> cola;
	private static List<LoggingProcessor> hilosLog;

	private static int tamanoMaxCola;
	private static int tamanoMaxHilos;
	private static int tamanoMaxLoggers;

	public static LoggingService getInstance() {
		if (servicio == null) {
			servicio = new LoggingService();
		}
		return servicio;
	}

	private static boolean servicioIniciado = false;

	public void iniciarLogging() {

		loggers = new LinkedList<Logger>();
		cola = new LinkedBlockingDeque<LogDto>();
		hilosLog = new LinkedList<LoggingProcessor>();

		try {
			setTamanoMaxCola(Integer.valueOf(ParametrosInicio.getProperty(LOG_MAX_COLA)));
		} catch (Exception e) {
			setTamanoMaxCola(TAMANO_MAX_COLA_DEFAULT);
			SimpleLogger.setDebug("La variable log.max.cola no esta definida en el archivo de configuracion");
		}

		try {
			setTamanoMaxHilos(Integer.valueOf(ParametrosInicio.getProperty(LOG_MAX_HILOS)));
		} catch (Exception e) {
			setTamanoMaxHilos(TAMANO_MAX_HILOS_DEFAULT);
			SimpleLogger.setDebug("La variable log.max.hilos no esta definida en el archivo de configuracion");
		}

		try {
			setTamanoMaxLoggers(Integer.valueOf(ParametrosInicio.getProperty(LOG_MAX_LOGGERS)));
		} catch (Exception e) {
			setTamanoMaxLoggers(TAMANO_MAX_LOGGERS_DEFAULT);
			SimpleLogger.setDebug("La variable log.max.loggers no esta definida en el archivo de configuracion");
		}

		setServicioIniciado(true);

		for (int i = 0; i < tamanoMaxHilos; i++) {
			servicio.adicionarLoggingProcessor(new LoggingProcessor());
		}
		
		SimpleLogger.setDebug("Servicio de Logging iniciado. TamMaxCola: " + tamanoMaxCola + " / HilosProcesadoresLog: " + tamanoMaxHilos);

	}

	private LoggingService() {
	}

	public static LogDto obtenerUltimoRegDeCola() {
		try {
			return cola.take();
		} catch (InterruptedException e) {
			SimpleLogger.setError("Error obteniendo último registro de cola log's.", e);
		}
		return null;
	}

	public boolean adicionarLoggingProcessor(LoggingProcessor procesador) {

		if (!servicioIniciado) {
			return false;
		}

		int tamanoActual = hilosLog.size();
		if (tamanoActual < tamanoMaxHilos) {
			if (procesador != null) {
				hilosLog.add(procesador);
				procesador.start();
				return true;
			} else {
				return false;
			}
		} else {
			SimpleLogger.setError("La lista de procesadores de logging ha alcanzado el tamaño maximo");
			return false;
		}
	}

	public boolean addLogDto(LogDto dto) {

		if (!servicioIniciado) {
			return false;
		}

		int tamanoActual = cola.size();
		if (tamanoActual < tamanoMaxCola) {
			try {
				cola.put(dto);
				SimpleLogger.setInfo("Se coloca registro de log en cola. totalRegLogs: " + (tamanoActual + 1));
			} catch (InterruptedException e) {
				SimpleLogger.setError("Error adicionando elemento LogDto a cola de log's.", e);
			}
			return true;
		} else {
			SimpleLogger.setError("La cola de logging ha alcanzado el tamaño maximo.");
			return false;
		}
	}

	public boolean addLogger(Logger logger) {

		if (!servicioIniciado) {
			return false;
		}

		int tamanoActual = loggers.size();
		if (tamanoActual < tamanoMaxLoggers) {
			logger.crearLogger();
			loggers.add(logger);
			return true;
		} else {
			SimpleLogger.setError("La lista de loggers has alcanzado el tamaño maximo");
			return false;
		}
	}

	public static List<Logger> getLoggers() {
		return loggers;
	}

	public void setLoggers(List<Logger> loggers) {
		if (loggers != null) {
			LoggingService.loggers = loggers;
		}
	}

	public static BlockingQueue<LogDto> getCola() {
		return cola;
	}

	public static void setCola(BlockingQueue<LogDto> cola) {
		if (cola != null) {
			LoggingService.cola = cola;
		}
	}

	public List<LoggingProcessor> getHilosLog() {
		return hilosLog;
	}

	public void setHilosLog(List<LoggingProcessor> hilosLog) {
		if (hilosLog != null) {
			LoggingService.hilosLog = hilosLog;
		}
	}

	public static boolean isRealizarlogging() {
		return realizarLogging;
	}

	public static void finalizarServicio() {
		realizarLogging = false;
		for (LoggingProcessor procesador : hilosLog) {
			try {
				procesador.interrupt();
			} catch (Exception e) {
				SimpleLogger.setError("Error finalizando los procesadores de logging", e);
			}
		}
		servicioIniciado = false;
	}

	public void setTamanoMaxCola(int tamanoMaxCola) {
		if (tamanoMaxCola > 0) {
			LoggingService.tamanoMaxCola = tamanoMaxCola;
		}
	}

	public int getTamanoMaxCola() {
		return tamanoMaxCola;
	}

	public void setTamanoMaxHilos(int tamanoMaxHilos) {
		if (tamanoMaxHilos > 0) {
			LoggingService.tamanoMaxHilos = tamanoMaxHilos;
		}
	}

	public int getTamanoMaxHilos() {
		return tamanoMaxHilos;
	}

	public static void setTamanoMaxLoggers(int tamanoMaxLoggers) {
		if (tamanoMaxLoggers > 0) {
			LoggingService.tamanoMaxLoggers = tamanoMaxLoggers;
		}
	}

	public static int getTamanoMaxLoggers() {
		return tamanoMaxLoggers;
	}

	private void setServicioIniciado(boolean servicioIniciado) {
		LoggingService.servicioIniciado = servicioIniciado;
	}

	public boolean isServicioIniciado() {
		return servicioIniciado;
	}

}
