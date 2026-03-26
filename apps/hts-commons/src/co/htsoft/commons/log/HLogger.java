package co.htsoft.commons.log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class HLogger {

	private static Map<String, org.slf4j.Logger> mapLog = new ConcurrentHashMap<String, org.slf4j.Logger>();

	private static boolean noLogger = false;

	static {

		Set<String> conf = new HashSet<>();

		try {
			Class.forName("org.slf4j.impl.AndroidLoggerFactory");
			conf.add("Android");
		} catch (ClassNotFoundException e) {
		}
		try {
			Class.forName("org.slf4j.impl.JCLLoggerFactory");
			conf.add("JCL");
		} catch (ClassNotFoundException e) {
		}
		try {
			Class.forName("org.slf4j.impl.JDK14LoggerFactory");
			conf.add("JDK14");
		} catch (ClassNotFoundException e) {
		}
		try {
			Class.forName("org.slf4j.impl.Log4jLoggerFactory");
			conf.add("Log4j");
		} catch (ClassNotFoundException e) {
		}
		try {
			Class.forName("org.slf4j.impl.SimpleLoggerFactory");
			conf.add("SimpleLogger");
		} catch (ClassNotFoundException e) {
		}

		System.out.println("HLOGGER DETECT: " + conf);

		noLogger = (conf.size() == 0);

	}

	public static void debug(Throwable e) {

		if (noLogger) {
			e.printStackTrace();
			return;
		}

		org.slf4j.Logger log = getLogger();
		log.debug("Throw", e);
	}

	public static void debug(Object tx) {
		String txt = toString(tx);

		if (noLogger) {
			slog(txt);
			return;
		}

		org.slf4j.Logger log = getLogger();
		log.debug(txt);
	}

	public static void debug(Object tx, Throwable e) {
		String txt = toString(tx);

		if (noLogger) {
			slog(txt);
			e.printStackTrace();
			return;
		}

		org.slf4j.Logger log = getLogger();
		log.debug(txt, e);
	}

	public static void info(Throwable e) {

		if (noLogger) {
			e.printStackTrace();
			return;
		}

		org.slf4j.Logger log = getLogger();
		log.info("Throw", e);
	}

	public static void info(Object tx) {
		String txt = toString(tx);

		if (noLogger) {
			slog(txt);
			return;
		}

		org.slf4j.Logger log = getLogger();
		log.info(txt);
	}

	public static void info(Object tx, Throwable e) {
		String txt = toString(tx);

		if (noLogger) {
			slog(txt);
			e.printStackTrace();
			return;
		}

		org.slf4j.Logger log = getLogger();
		log.info(txt, e);
	}

	public static void warn(Throwable e) {

		if (noLogger) {
			e.printStackTrace();
			return;
		}

		org.slf4j.Logger log = getLogger();
		log.warn("Throw", e);
	}

	public static void warn(Object tx) {

		String txt = toString(tx);

		if (noLogger) {
			slog(txt);
			return;
		}

		org.slf4j.Logger log = getLogger();
		log.warn(txt);
	}

	public static void warn(Object tx, Throwable e) {
		String txt = toString(tx);

		if (noLogger) {
			slog(txt);
			e.printStackTrace();
			return;
		}

		org.slf4j.Logger log = getLogger();
		log.warn(txt, e);
	}

	public static void error(Throwable e) {

		if (noLogger) {
			e.printStackTrace();
			return;
		}

		org.slf4j.Logger log = getLogger();
		log.error("Throw", e);
	}

	public static void error(Object tx) {
		String txt = toString(tx);

		if (noLogger) {
			slog(txt);
			return;
		}

		org.slf4j.Logger log = getLogger();
		log.error(txt);
	}

	public static void error(Object tx, Throwable e) {
		String txt = toString(tx);

		if (noLogger) {
			slog(txt);
			e.printStackTrace();
			return;
		}

		org.slf4j.Logger log = getLogger();
		log.error(txt, e);
	}

	// ----------------------------------------------------------------

	private static String toString(Object tx) {

		if (tx == null) {
			return null;
		}

		if (tx instanceof String) {
			return (String) tx;
		}

		try {
			return new Gson().toJson(tx);
		} catch (Exception e) {
		}

		try {
			return tx.toString();
		} catch (Exception e) {
		}

		return tx.getClass().toString();

	}

	private static org.slf4j.Logger getLogger() {
		String className = Thread.currentThread().getStackTrace()[3].getClassName();

		org.slf4j.Logger log = mapLog.get(mapLog);

		if (log == null) {

			synchronized (mapLog) {
				log = LoggerFactory.getLogger(className);
				mapLog.put(className, log);
			}

		}
		return log;
	}

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static synchronized void slog(String txt) {
		String classname = Thread.currentThread().getStackTrace()[3].getClassName();
		System.out.println(sdf.format(new Date()) + "[" + classname + "]" + txt);
	}

}
