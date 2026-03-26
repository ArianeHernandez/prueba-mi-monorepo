package com.osmosyscol.datasuite.utils;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;

public class ObjectUtils extends org.apache.commons.lang.ObjectUtils {

	@SuppressWarnings("unchecked")
	public static Map<String, Object> objectToMap(Object objeto) {

		if (objeto instanceof Map) {
			return (Map<String, Object>) objeto;
		}

		if (objeto != null) {
			Map<String, Object> map = objectToParameters("[DS_BASE]", objeto, true);

			Map<String, Object> res = new HashMap<>();

			for (String key : map.keySet()) {
				res.put(key.replace("[DS_BASE].", "").replace("[DS_BASE]", ""), map.get(key));
			}

			return res;

		}
		return null;
	}

	// -------------------------------------------------------------------------------------------------------------------------

	public static Map<String, Object> objectToParameters(String basename, Object objeto, boolean verNulos) {

		Map<String, Object> ret = new HashMap<String, Object>();

		if (objeto == null && verNulos) {
			ret.put(basename, null);
		}

		if (objeto != null) {
			Class<?> tipo = objeto.getClass();

			if (basename == null || basename.trim().length() == 0) {
				basename = tipo.getSimpleName();
			}

			// -- si es arreglo

			try {

				Object[] objs = (Object[]) objeto;

				for (int i = 0; i < objs.length; i++) {
					ret.putAll(objectToParameters(basename + ":" + (i + 1), objs[i], verNulos));
				}

			} catch (Exception e) {
			}

			// -- Si es una lista

			if (tipo.getName().equals("java.util.List")) {
				List<?> lista = (List<?>) objeto;
				int i = 0;
				for (Object object : lista) {
					ret.putAll(objectToParameters(basename + ":" + (i + 1), object, verNulos));
					i++;
				}
			}

			// -- Si es un conjunto

			if (tipo.getName().equals("java.util.Set")) {
				Set<?> lista = (Set<?>) objeto;
				int i = 0;
				for (Object object : lista) {
					ret.putAll(objectToParameters(basename + ":" + (i + 1), object, verNulos));
					i++;
				}
			}

			// -- Si es basico

			if (tipo.getName().equals("java.lang.String") || tipo.getName().equals("java.lang.Integer")
					|| tipo.getName().equals("java.util.Date") || tipo.getName().equals("java.lang.Float")
					|| tipo.getName().equals("java.lang.Double") || tipo.getName().equals("java.lang.Boolean")
					|| tipo.getName().equals("java.math.BigDecimal") || tipo.getName().equals("java.lang.Long")) {
				ret.put(basename, objeto);
			}

			// si es objeto

			if (ret.size() == 0 && tipo.getName().indexOf("java.") < 0) {

				if (basename.indexOf(".") < 0) {
					ret.put(basename + ".CLASSNAME", tipo.getName());
				}

				Method[] nmetos = tipo.getMethods();

				for (Method nmeto : nmetos) {
					// Buscar sobre los metodos

					try {
						if (nmeto.getName().indexOf("get") == 0 && nmeto.getName().length() > 3) {
							// que sean get

							Set<String> nmet = new HashSet<String>();
							nmet.add("getClass");
							nmet.add("getTypeDesc");
							nmet.add("getSerializer");
							nmet.add("getDeserializer");

							if (!nmet.contains(nmeto.getName().trim())) {
								String namemethod = validateName(
										nmeto.getName().substring(3, 4).toLowerCase() + nmeto.getName().substring(4));

								ret.putAll(objectToParameters(basename + "." + namemethod,
										nmeto.invoke(objeto, new Object[0]), verNulos));
							}
						}
					} catch (Exception e) {
						SimpleLogger.setError("Error en ObjectUtils.objectToParameters", e);
					}
				}
			}
		}

		return ret;

	}

	// -------------------------------------------------------------------------------------------------------------------------

	public static <T> List<T> mapToObjects(Class<T> clase, List<Map<String, Object>> data) {

		List<T> list = new ArrayList<T>();

		for (Map<String, Object> reg : data) {
			list.add((T) mapToObject(clase, reg));
		}

		return list;
	}

	// -------------------------------------------------------------------------------------------------------------------------

	public static <T> T mapToObject(Class<T> clase, Map<String, Object> data) {

		Method[] methods = clase.getMethods();

		try {

			T obj = clase.newInstance();

			for (Method method : methods) {

				String methodName = method.getName().toUpperCase();

				String columnName = methodName.substring(3);

				if (methodName.indexOf("SET") == 0 && data.containsKey(columnName)) {

					Class<?>[] parameterTypes = method.getParameterTypes();

					// Si tiene un parametro de entrada
					if (parameterTypes != null && parameterTypes.length == 1) {

						Object value = data.get(columnName);

						if (value != null) {

							// ---

							Class<?> parameterType = parameterTypes[0];

							if (parameterType.equals(Boolean.class)) {
								value = new Boolean(StringUtils.esVerdad(StringUtils.toString(value)));
							}

							if (parameterType.equals(Integer.class)
									&& StringUtils.toString(value).matches("^[-]?[0-9]+$")) {
								value = new Integer(StringUtils.toString(value));
							}

							if (parameterType.equals(Long.class)
									&& StringUtils.toString(value).matches("^[-]?[0-9]+$")) {
								value = new Long(StringUtils.toString(value));
							}

							if (parameterType.equals(Date.class)
									&& StringUtils.toString(value).matches("^[0-9]{2}[/][0-9]{2}[/][0-9]{4}.*$")) {
								value = StringUtils.toDate(value.toString());
							}

							if (parameterType.equals(BigDecimal.class)
									&& StringUtils.toString(value).matches("^[0-9E.,\\-\\+]+$")) {
								String val = StringUtils.toString(value);
								val = val.replaceAll("\\.", "");
								val = val.replaceAll(",", ".");
								value = new BigDecimal(val);
							}

							// ----

							if (value.getClass().equals(parameterType)) {

								try {
									method.invoke(obj, value);
								} catch (Exception e) {
									SimpleLogger.setError("Error en ObjectUtils.mapToObject", e);
								}

							}

							// ---
						}
					}

				}

			}

			return (T) obj;

		} catch (Exception e) {
			SimpleLogger.setError("Error en ObjectUtils.mapToObject", e);
		}
		return null;
	}

	private static String validateName(String name) {

		if (name == null)
			return "_";

		if ("01234567890".indexOf(name.charAt(0) + "") >= 0) {
			name = "N" + name;
		}

		return name.trim().replace("[]", "Array").replace('?', '_').replace(':', '_').replace('/', '_')
				.replace('\"', '_').replaceAll("[ ]", "_").replaceAll("[@]", "").replaceAll("[-]", "_")
				.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\(", "").replaceAll("\\)", "");
	}

	public static void main(String[] args) {

		System.out.println(objectToMap("hola"));

	}

	// -------------------------------------------------------------------------------------------------------------------------

}
