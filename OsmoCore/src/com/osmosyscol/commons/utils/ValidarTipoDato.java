package com.osmosyscol.commons.utils;

import org.apache.cocoon.environment.Request;

public class ValidarTipoDato {

	public static String TIPO_CORREO = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";
	public static String TIPO_NUMERO = "^[\\d]+$";
	public static String TIPO_PALABRA = "^[\\w\\sáéíóúñÁÉÍÓÚÑ\\.\\'\\-]+$";
//	public static String TIPO_IP = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
	public static String TIPO_IP = "((^\\s*((([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]))\\s*$)|(^\\s*((([0-9A-Fa-f]{1,4}:){7}([0-9A-Fa-f]{1,4}|:))|(([0-9A-Fa-f]{1,4}:){6}(:[0-9A-Fa-f]{1,4}|((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){5}(((:[0-9A-Fa-f]{1,4}){1,2})|:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){4}(((:[0-9A-Fa-f]{1,4}){1,3})|((:[0-9A-Fa-f]{1,4})?:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){3}(((:[0-9A-Fa-f]{1,4}){1,4})|((:[0-9A-Fa-f]{1,4}){0,2}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){2}(((:[0-9A-Fa-f]{1,4}){1,5})|((:[0-9A-Fa-f]{1,4}){0,3}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){1}(((:[0-9A-Fa-f]{1,4}){1,6})|((:[0-9A-Fa-f]{1,4}){0,4}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(:(((:[0-9A-Fa-f]{1,4}){1,7})|((:[0-9A-Fa-f]{1,4}){0,5}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:)))(%.+)?\\s*$))";

	// ------

	public static String validateParameter(Request request, String param, String tipo, Integer max, Boolean obligatorio) {

		String value = request.getParameter(param);

		if (obligatorio) {
			notNull(value);
		}

		isMaxSize(value, max);

		if (tipo.equalsIgnoreCase("email")) {
			validate(TIPO_CORREO, value);
		}

		if (tipo.equalsIgnoreCase("number")) {
			validate(TIPO_NUMERO, value);
		}

		if (tipo.equalsIgnoreCase("word")) {
			validate(TIPO_PALABRA, value);
		}

		if (tipo.equalsIgnoreCase("ip")) {
			validate(TIPO_IP, value);
		}

		return value;
	}

	// ------

	public static String validateEmail(String value) {
		validate(TIPO_CORREO, value);
		return value;
	}

	public static String validateEmail(String value, Integer max) {
		isMaxSize(value, max);
		validate(TIPO_CORREO, value);
		return value;
	}

	public static void isEmail(String value, Integer max) {
		isMaxSize(value, max);
		validate(TIPO_CORREO, value);
	}

	public static void isEmail(String... values) {
		validate(TIPO_CORREO, values);
	}

	// ------

	public static String validateNumber(String value, int max) {
		isMaxSize(value, max);
		validate(TIPO_NUMERO, value);
		return value;
	}

	public static void isNumber(String value, int max) {
		isMaxSize(value, max);
		validate(TIPO_NUMERO, value);
	}

	public static void isNumber(String... values) {
		validate(TIPO_NUMERO, values);
	}

	// ------

	public static String validateIP(String value) {
		validate(TIPO_IP, value);
		return value;
	}

	// ------

	public static void isAlpha(String... values) {
		validate(TIPO_PALABRA, values);
	}

	public static void isAlpha(String value, int max) {
		isMaxSize(value, max);
		validate(TIPO_PALABRA, value);
	}

	public static String validateAlpha(String value, int max) {
		isMaxSize(value, max);
		validate(TIPO_PALABRA, value);
		return value;
	}

	// ----------------------

	public static void isMaxSize(Object value, Integer max) {

		if (value != null) {

			String str_value = value.toString();

			if (str_value.length() > max) {
				throw new RuntimeException("ValidarTipoDato: Longitud Invalida ( valor: " + str_value.length() + ", max: " + max + " )");
			}
		}

	}

	public static String validateMaxSize(String value, Integer max) {
		isMaxSize(value, max);
		return value;
	}

	public static void isSize(Object value, Integer min, Integer max) {

		if (value != null) {

			String str_value = value.toString();

			if (str_value.length() < min || str_value.length() > max) {
				throw new RuntimeException("ValidarTipoDato: Longitud Invalida ( valor: " + str_value.length() + ", min: " + min + ", max: " + max + " )");
			}
		}

	}

	public static void validate(String expr, String... values) {

		if (expr == null) {
			throw new RuntimeException("ValidarTipoDato: Falta Expresion");
		}

		if (values != null) {
			for (String value : values) {

				value = StringUtils.trimToEmpty(value).toLowerCase();

				if (StringUtils.isNotBlank(value)) {
					if (!StringUtils.validaExpRegular(value, expr)) {
						throw new RuntimeException("Error de validacion. ( value: " + value + ", expr: " + expr + " )");
					}
				}
			}
		}
	}

	// --------

	public static void positive(Integer val) {

		if (val == null) {
			return;
		}

		if (val < 0) {
			throw new RuntimeException("ValidarTipoDato: Valor no positivo. (" + val + ")");
		}
	}

	public static void positive(Long val) {

		if (val == null) {
			return;
		}

		if (val < 0) {
			throw new RuntimeException("ValidarTipoDato: Valor no positivo. (" + val + ")");
		}
	}

	// --------

	public static void range(Integer val, int min, int max) {

		if (val == null) {
			return;
		}

		if (val < min) {
			throw new RuntimeException("ValidarTipoDato: Valor fuera de rango. ( value: " + val + ", min: " + min + " )");
		}

		if (val > max) {
			throw new RuntimeException("ValidarTipoDato: Valor fuera de rango. ( value: " + val + ", max: " + max + " )");
		}

	}

	// --------

	public static void range(Long val, long min, long max) {

		if (val == null) {
			return;
		}

		if (val < min) {
			throw new RuntimeException("ValidarTipoDato: Valor fuera de rango. ( value: " + val + ", min: " + min + " )");
		}

		if (val > max) {
			throw new RuntimeException("ValidarTipoDato: Valor fuera de rango. ( value: " + val + ", max: " + max + " )");
		}

	}

	// --------

	public static void notNull(Object... values) {

		if (values == null) {
			throw new RuntimeException("ValidarTipoDato: Obligatoriedad");
		}

		for (Object value : values) {
			if (value == null) {
				throw new RuntimeException("ValidarTipoDato: Obligatoriedad");
			}
		}

	}

}
