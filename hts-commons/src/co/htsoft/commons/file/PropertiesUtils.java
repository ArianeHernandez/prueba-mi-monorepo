package co.htsoft.commons.file;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import co.htsoft.commons.lang.StringUtils;
import co.htsoft.commons.log.HLogger;

public class PropertiesUtils {

	public static Properties load(String path) {

		try {

			Properties properties = new Properties();

			File archivo = new File(path);
			FileInputStream stream = new FileInputStream(archivo);
			properties.load(stream);
			stream.close();

			return properties;

			// -------------

		} catch (Exception e) {
			HLogger.error(e);
		}

		return null;

	}

	public static String getString(Properties properties, String key, String defaultValue) {
		String val = getString(properties, key);
		if (StringUtils.isBlank(val)) {
			val = defaultValue;
		}

		return val;
	}

	public static String getString(Properties properties, String key) {
		try {
			return StringUtils.trimToNull(properties.getProperty(key));
		} catch (Exception e) {
			HLogger.error(e);
		}
		return null;
	}

	public static Integer getInteger(Properties properties, String key, Integer defaultValue) {
		Integer val = getInteger(properties, key);
		if (val == null) {
			val = defaultValue;
		}

		return val;
	}

	public static Integer getInteger(Properties properties, String key) {
		try {
			return Integer.parseInt(StringUtils.trimToNull(properties.getProperty(key)));
		} catch (Exception e) {
			HLogger.error(e);
		}
		return null;
	}

	public static Double getDouble(Properties properties, String key, Double defaultValue) {
		Double val = getDouble(properties, key);
		if (val == null) {
			val = defaultValue;
		}

		return val;
	}

	public static Double getDouble(Properties properties, String key) {
		try {
			return Double.parseDouble(StringUtils.trimToNull(properties.getProperty(key)));
		} catch (Exception e) {
			HLogger.error(e);
		}
		return null;
	}

	public static Long getLong(Properties properties, String key, Long defaultValue) {
		Long val = getLong(properties, key);
		if (val == null) {
			val = defaultValue;
		}

		return val;
	}

	public static Long getLong(Properties properties, String key) {
		try {
			return Long.parseLong(StringUtils.trimToNull(properties.getProperty(key)));
		} catch (Exception e) {
			HLogger.error(e);
		}
		return null;
	}

	public static Boolean getBoolean(Properties properties, String key, Boolean defaultValue) {
		Boolean val = getBoolean(properties, key);
		if (val == null) {
			val = defaultValue;
		}

		return val;
	}

	public static Boolean getBoolean(Properties properties, String key) {
		try {
			return StringUtils.isTrue(StringUtils.trimToNull(properties.getProperty(key)));
		} catch (Exception e) {
			HLogger.error(e);
		}
		return null;
	}

	public static List<String> getStringList(Properties properties, String key) {

		List<String> d = new ArrayList<>();

		for (int i = 0; i < 200; i++) {

			String nkey = key + "." + i;

			if (properties.containsKey(nkey)) {
				String val = getString(properties, nkey);
				if (val != null) {
					d.add(val);
				}
			}
		}

		return d;
	}

}
