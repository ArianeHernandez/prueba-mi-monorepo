package com.osmosyscol.commons.log.log4j;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;

import com.osmosyscol.commons.log.SimpleLogger;

/**
 * Permite que la configuracion de log4j escriba con el logger anterior de
 * osmocore
 * 
 * @author ahurtado
 * 
 */
public class SimpleLoggerAdapterAppender extends AppenderSkeleton {

	private String applicationName;

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
		SimpleLogger.setAppname(this.applicationName);
	}

	@Override
	protected void append(LoggingEvent loggingEvent) {
		String message = this.layout.format(loggingEvent);

		Level level = loggingEvent.getLevel();

		if (Level.TRACE.equals(level)) {
			SimpleLogger.setDebug(message);
		} else if (Level.DEBUG.equals(level)) {
			SimpleLogger.setDebug(message);
		} else if (Level.INFO.equals(level)) {
			SimpleLogger.setInfo(message);
		} else if (Level.WARN.equals(level)) {
			SimpleLogger.setWarn(message);
		} else if (Level.ERROR.equals(level) || Level.FATAL.equals(level)) {
			Throwable t = (null != loggingEvent.getThrowableInformation()) ? (loggingEvent
					.getThrowableInformation().getThrowable()) : (null);
			if (null != t) {
				SimpleLogger.setError(message, t);
			} else {
				SimpleLogger.setError(message);
			}

		}

	}

	@Override
	public void close() {

	}

	@Override
	public boolean requiresLayout() {
		return false;
	}

}
