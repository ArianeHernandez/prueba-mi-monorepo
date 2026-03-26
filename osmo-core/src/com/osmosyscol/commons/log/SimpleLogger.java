// CVS $Id$
package com.osmosyscol.commons.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.osmosyscol.commons.utils.StringUtils;

public class SimpleLogger {

	private static final String TIPO_DEBUG = "DEBUG";

	private static final String TIPO_INFO = "INFO";

	private static final String TIPO_WARN = "WARN";

	private static final String TIPO_ERROR = "ERROR";

	// -------------------------------------------

	private static Boolean saveLog = false;

	public static StringBuffer contenidoLog = new StringBuffer();
	private static String appname = "ITO";
	private static Boolean isdebug = false;
	private static int maxsize = 200;
	private static String lastdate = "?";
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
	private static SimpleDateFormat simpleDateFormatDay = new SimpleDateFormat("d-MMM-yyyy");

	static {
		new File("logs").mkdirs();
	}

	public static boolean isDebug() {
		return isdebug;
	}

	public static void setMaxsize(int maxsize) {
		SimpleLogger.maxsize = maxsize;
	}

	public static void activateDebug(Boolean activo) {
		isdebug = activo;
		if (isdebug) {
			setInfo("Se activa Modo Debug");
		}
	}

	public static void activateSave(Boolean activo) {
		saveLog = activo;
	}

	// ------------------------------------------------------

	public static void setAppname(String appname) {
		SimpleLogger.appname = appname;
	}

	public static String getAppname() {
		return appname;
	}

	// ------------------------------------------------------

	public static synchronized void setError(String msg) {
		setError(msg, null);
	}

	public static synchronized void setError(String msg, Throwable e) {
		printlog(TIPO_ERROR, msg, e);
	}

	public static synchronized void setError(Throwable e) {
		printlog(TIPO_ERROR, "ERROR", e);
	}

	// ------------------------------------------------------

	public static synchronized void setWarn(String msg) {
		setWarn(msg, null);
	}

	public static synchronized void setWarn(String msg, Throwable e) {
		printlog(TIPO_WARN, msg, e);
	}

	public static synchronized void setWarn(Throwable e) {
		printlog(TIPO_WARN, "WARN", e);
	}

	// ------------------------------------------------------

	public static synchronized void setInfo(String msg) {
		setInfo(msg, null);
	}

	public static synchronized void setInfo(String msg, Throwable e) {
		printlog(TIPO_INFO, msg, e);
	}

	public static synchronized void setInfo(Throwable e) {
		printlog(TIPO_INFO, "INFO", e);
	}

	// ------------------------------------------------------

	public static synchronized void setDebug(String msg) {
		setDebug(msg, null);
	}

	public static synchronized void setDebug(String msg, Throwable e) {

		if (isdebug) {
			printlog(TIPO_DEBUG, msg, e);
		}
	}

	public static synchronized void setDebug(Throwable e) {

		if (isdebug) {
			printlog(TIPO_DEBUG, "INFO", e);
		}
	}

	// ------------------------------------------------------

	private static synchronized void printlog(String pre, String msg, Throwable e) {

		try {

			if(e != null){
				e.printStackTrace();
			}
			
			// Si esta activo log4j redirige el log.
			if (loggerEnabled) {
				printLogToLog4j(pre, msg, e);
				return;
			}

			msg = StringUtils.trimToEmpty(msg);

			if (msg.length() > maxsize) {
				msg = msg.substring(0, maxsize) + "...";
			}

			Date current = new Date();
			String ndate = simpleDateFormat.format(current);
			String fdate = simpleDateFormatDay.format(current);

			StringBuffer salida = new StringBuffer("");

			salida.append("\r\n");
			if (!ndate.equals(lastdate) || pre.equalsIgnoreCase(TIPO_ERROR)) {
				salida.append("\r\n" + "[" + appname + ":TIME] - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\r\n");
				salida.append("\r\n" + " " + ndate + " ");
				lastdate = ndate;
				salida.append("\r\n");
			}

			salida.append("\r\n" + "[" + appname + ":" + pre + "] - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\r\n\r\n" + msg);
			if (e != null) {
				salida.append("\r\n" + "\r\n[" + appname + ":EXCEPTION]\r\n\r\n" + e);
			}
			salida.append("\r\n");

			if (pre.equalsIgnoreCase(TIPO_ERROR) && e != null) {

				if (e.getStackTrace() != null) {

					salida.append("\r\n" + "\r\n[" + appname + ":TRACE]\r\n");

					printTrace(salida, e);

				}
				if (e.getCause() != null) {
					salida.append("\r\n" + "\r\n[" + appname + ":CAUSE]: " + e.getCause().getClass() + ": " + e.getCause().getMessage() + "\r\n");

					printTrace(salida, e.getCause());
				}

			}

			if (saveLog) {
				contenidoLog.append(salida.toString());
			}

			// Imprime a consola el log.
			System.out.println(salida);

			// Guarda en archivo por fecha el log.
			printtofile.addLog(fdate, salida.toString());
			if (TIPO_ERROR.equals(pre)) {
				// Si es error guarda en archivo de error el log de errores.
				printtofile.addLog(fdate + "-Error", salida.toString());
			}

		} catch (Exception ee) {
			System.out.println(msg);
		}

	}

	private static void printTrace(StringBuffer salida, Throwable e) {
		String clasepadre = "";
		String trazaClass = "";

		for (StackTraceElement trace : e.getStackTrace()) {

			try {

				if (isdebug || trace.getClassName().indexOf("com.osmo") == 0 || trace.getClassName().indexOf("co.com.it") == 0 || trace.getClassName().indexOf("com.it") == 0 || trace.getClassName().indexOf("_xsp") > 0) {
					if (!clasepadre.equalsIgnoreCase(trace.getClassName())) {

						if (trazaClass.length() > 0) {
							salida.append(" { " + trazaClass + " } \n");
							trazaClass = "";
						}

						if (isdebug) {
							salida.append("\r\n" + " " + trace.getClassName() + "\r\n");
						} else {
							salida.append("\r\n" + " " + StringUtils.derecha(trace.getClassName().substring(trace.getClassName().lastIndexOf(".")), 22));
						}

						clasepadre = trace.getClassName();
					}

					if (trazaClass.length() > 0) {
						trazaClass = " >> " + trazaClass;
					}
					trazaClass = trace.getMethodName() + " [Line:" + trace.getLineNumber() + "]" + trazaClass;

				} else {

					if (trazaClass.length() > 0) {
						salida.append(" { " + trazaClass + " } \r\n");
						trazaClass = "";
					}
				}

			} catch (Exception e2) {
				salida.append("... \r\n");
			}

		}

		if (trazaClass.length() > 0) {
			salida.append("\r\n" + " { " + trazaClass + " } \r\n");
			trazaClass = "";
		}
	}

	/**
	 * ========================================================================== Se agrega funcionalidad para permitir el uso de log4j. ==========================================================================
	 */
	/**
	 * Flag para determinar si es usa el logger de log4j.
	 */
	private static boolean loggerEnabled = false;

	/**
	 * @return the loggerEnabled
	 */
	public static boolean isLoggerEnabled() {
		return loggerEnabled;
	}

	/**
	 * Activa o inactiva la redirección del log hacia log4j.
	 * 
	 * @param loggerEnabled
	 *            - true para activar el log / false para desactivar, se recomienda llamar al iniciar cada aplicación.
	 */
	public static void setLoggerEnabled(boolean loggerEnabled) {
		SimpleLogger.loggerEnabled = loggerEnabled;
	}

	/**
	 * Envía la salida de log a log4j dependiendo el prefijo.
	 * 
	 * @param pre
	 * @param msg
	 * @param e
	 */
	private static void printLogToLog4j(String pre, String msg, Throwable e) {

		String invokerClass = getInvokerClass();

		Logger classLogger = LoggerFactory.getLogger(invokerClass);

		if (TIPO_DEBUG.equals(pre)) {
			classLogger.debug(msg, e);
		} else if (TIPO_ERROR.equals(pre)) {
			classLogger.error(msg, e);
		} else if (TIPO_INFO.equals(pre)) {
			classLogger.info(msg, e);
		} else if (TIPO_WARN.equals(pre)) {
			classLogger.warn(msg, e);
		} else {
			classLogger.trace(msg, e);
		}

	}

	/**
	 * Retorna la clase que invoca el método de SimpleLogger.
	 * 
	 * @return - ClassName
	 */
	private static String getInvokerClass() {
		StackTraceElement[] calls = Thread.currentThread().getStackTrace();

		int i = 1;
		String className = calls[i++].getClassName();

		while (SimpleLogger.class.getName().toString().equalsIgnoreCase(className)) {
			className = calls[i++].getClassName();
		}

		return className;
	}

	/**
	 * ========================================================================== FIN DE funcionalidad para permitir el uso de log4j. ==========================================================================
	 */

}

class printtofile implements Runnable {

	private static printtofile instance = new printtofile();

	private Queue<LogMessage> mensajes = new ConcurrentLinkedQueue<LogMessage>();

	private Boolean running = false;

	private printtofile() {
	}

	public static void addLog(String fdate, String salida) {

		LogMessage msj = new LogMessage(salida, fdate);
		instance.mensajes.add(msj);
		if (!instance.running) {
			instance.running = true;
			Thread hilo = new Thread(instance);
			hilo.setPriority(Thread.MIN_PRIORITY);
			hilo.start();
		}
	}

	public void run() {

		try {
			running = true;
			while (!instance.mensajes.isEmpty()) {
				LogMessage logMessage = instance.mensajes.poll();

				String filestr = "logs/" + logMessage.getFdate() + ".txt";
				FileWriter fstream = new FileWriter(filestr, true);
				BufferedWriter out = new BufferedWriter(fstream);
				out.write(logMessage.getSalida());
				out.close();
			}

			running = false;

		} catch (Exception e4) {
			running = false;
		}

	}

}

class LogMessage {

	private String salida = "";
	private String fdate = "";

	public LogMessage(String salida, String fdate) {
		super();
		this.salida = salida;
		this.fdate = fdate;
	}

	public String getSalida() {
		return salida;
	}

	public void setSalida(String salida) {
		this.salida = salida;
	}

	public String getFdate() {
		return fdate;
	}

	public void setFdate(String fdate) {
		this.fdate = fdate;
	}

}