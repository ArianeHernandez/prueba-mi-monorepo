package com.osmosyscol.datasuite.utils;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.servicios.SQLServicio;
import com.osmosyscol.datasuite.modelatos.logica.dto.Campo;
import com.osmosyscol.datasuite.modelatos.logica.servicios.CampoServicio;
import com.osmosyscol.datasuite.persistencia.dao.SQLDao;
import com.osmosyscol.datasuite.reportedim.servicio.RDServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class DS_SqlUtils {

	// -----------------------------------------------------------------------------------------

	public static <T> List<T> queryForList(Class<T> clase, String sql) {
		return queryForList(clase, sql, null, null, null);
	}

	// -----------------------------------------------------------------------------------------

	public static <T> T queryForObject(Class<T> clase, String sql) {
		return queryForObject(clase, sql, null);
	}

	// -----------------------------------------------------------------------------------------

	public static <T> T queryForObject(Class<T> clase, String sql, Object param) {
		return queryForObject(clase, sql, ObjectUtils.objectToMap(param));
	}

	public static <T> T queryForObject(Class<T> clase, String sql, Map<String, Object> params) {
		List<T> l = queryForList(clase, sql, params, null, null);

		if (l != null && l.size() > 0) {
			return l.get(0);
		}

		return null;
	}

	// -----------------------------------------------------------------------------------------

	public static <T> List<T> queryForList(Class<T> clase, String sql, Object param) {
		return queryForList(clase, sql, param, null, null);
	}

	public static <T> List<T> queryForList(Class<T> clase, String sql, Integer tamano_pagina, Integer pagina_actual) {
		return queryForList(clase, sql, null, tamano_pagina, pagina_actual);
	}

	public static <T> List<T> queryForList(Class<T> clase, String sql, Map<String, Object> params) {
		return queryForList(clase, sql, params, null, null);
	}

	public static <T> List<T> queryForList(Class<T> clase, String sql, Object param, Integer tamano_pagina, Integer pagina_actual) {
		return queryForList(clase, sql, ObjectUtils.objectToMap(param), tamano_pagina, pagina_actual);
	}

	public static Map<String, Campo> mapCampos = new HashMap<String, Campo>();

	@SuppressWarnings("unchecked")
	public static <T> List<T> queryForList(Class<T> clase, String sql, Map<String, Object> params, Integer tamano_pagina, Integer pagina_actual) {

		try {

			sql = replaceSQL(sql, params);

			System.out.println(sql);

			SQLDao sqlDao = (SQLDao) DaoConfig.getDao(SQLDao.class, 2);

			List<Map<String, Object>> data = null;
			if (tamano_pagina == null || pagina_actual == null) {
				data = SQLServicio.desencriptarMapaStringFormat(sqlDao.selectSQL(sql));
			} else {
				data = SQLServicio.desencriptarMapaStringFormat(sqlDao.selectSQLPaginado(sql, pagina_actual, tamano_pagina));
			}

			List<T> list = new ArrayList<T>();

			if (Integer.class.equals(clase)) {

				if (data != null) {

					for (Map<String, Object> reg : data) {
						try {

							Object oval = reg.get(reg.keySet().toArray()[0].toString());
							list.add(oval == null ? null : ((T) new Integer(oval.toString())));
						} catch (Exception e) {
							list.add(null);
						}
					}
				}

				return list;
			}

			if (String.class.equals(clase)) {

				if (data != null) {

					for (Map<String, Object> reg : data) {
						try {

							Object oval = reg.get(reg.keySet().toArray()[0].toString());
							String val = oval == null ? null : oval.toString();

							list.add((T) val);
						} catch (Exception e) {
							list.add(null);
						}
					}
				}

				return list;
			}

			if (BigDecimal.class.equals(clase)) {

				if (data != null) {

					for (Map<String, Object> reg : data) {
						try {

							Object oval = reg.get(reg.keySet().toArray()[0].toString());

							if (oval != null) {
								String val = oval.toString();
								val = val.replaceAll("\\.", "");
								val = val.replaceAll(",", ".");
								list.add((T) new BigDecimal(val));
							} else {
								list.add(null);
							}

						} catch (Exception e) {
							list.add(null);
						}
					}
				}

				return list;
			}

			if (Date.class.equals(clase)) {
				if (data != null) {
					for (Map<String, Object> reg : data) {
						try {

							Object oval = reg.get(reg.keySet().toArray()[0].toString());

							if (oval != null) {
								String val = oval.toString();
								list.add((T) StringUtils.toDate(val));
							} else {
								list.add(null);
							}
						} catch (Exception e) {
							list.add(null);
						}
					}
				}
				return list;
			}

			Method[] methods = clase.getMethods();

			for (Map<String, Object> reg : data) {

				Map<String, Object> record = new HashMap<String, Object>();

				Set<String> rkeys = reg.keySet();

				for (String rkey : rkeys) {

					if (rkey.toUpperCase().matches("^[C][0-9]+$")) {
						Integer id_campo = Integer.parseInt(rkey.substring(1));
						Campo campo = mapCampos.get("C" + id_campo);

						if (campo == null) {
							campo = CampoServicio.getInstance().obtenerCampo(id_campo);
							mapCampos.put("C" + id_campo, campo);
						}

						try {

							if (campo != null) {
								record.put(campo.getNombre().toUpperCase().replace(" ", "").replace("-", ""), reg.get(rkey));
								record.put(campo.getNombre().toUpperCase().replace(" ", "_").replace("-", ""), reg.get(rkey));
							} else {
								record.put(rkey.toUpperCase(), reg.get(rkey));
							}
						} catch (Exception e) {
							e.printStackTrace();
						}

					} else {
						record.put(rkey.toUpperCase(), reg.get(rkey));
					}

				}

				try {

					T obj = clase.newInstance();

					for (Method method : methods) {

						String methodName = method.getName().toUpperCase();

						String columnName = methodName.substring(3);

						if (methodName.indexOf("SET") == 0 && record.containsKey(columnName)) {

							Class<?>[] parameterTypes = method.getParameterTypes();

							// Si tiene un parametro de entrada
							if (parameterTypes != null && parameterTypes.length == 1) {

								Object value = record.get(columnName);

								if (value != null) {

									// ---

									Class<?> parameterType = parameterTypes[0];

									if (parameterType.equals(Boolean.class)) {
										value = new Boolean(StringUtils.esVerdad(value.toString()));
									}

									if (parameterType.equals(Integer.class) && StringUtils.toString(value).matches("^[-]?[0-9]+$")) {
										value = new Integer(StringUtils.toString(value));
									}

									if (parameterType.equals(Long.class) && StringUtils.toString(value).matches("^[-]?[0-9]+$")) {
										value = new Long(StringUtils.toString(value));
									}

									if (parameterType.equals(Date.class) && StringUtils.toString(value).matches("^[0-9]{2}[/][0-9]{2}[/][0-9]{4}.*$")) {
										value = StringUtils.toDate(StringUtils.toString(value));
									}

									if (parameterType.equals(BigDecimal.class) && StringUtils.toString(value).matches("^[0-9E.,\\-\\+]+$")) {
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
											SimpleLogger.setError("Error ejecutando setter", e);
										}

									}

									// ---
								}
							}

						}

					}

					list.add(obj);

				} catch (Exception e) {
					SimpleLogger.setError("Error asignando setters", e);
				}
			}

			return list;

		} catch (Exception e) {
			SimpleLogger.setError("Error", e);
		}

		return null;

	}

	// -------------------------------------------------------------------------------------------------------------------------

	public static Boolean insert(String sql) {
		return insert(sql, null);
	}

	// ----------

	public static Boolean insert(String sql, Object param) {
		return insert(sql, ObjectUtils.objectToMap(param));
	}

	// -------------------------------------------------------------------------------------------------------------------------

	public static Boolean delete(String sql) {
		return delete(sql, null);
	}

	// ----------

	public static Boolean delete(String sql, Object param) {
		return delete(sql, ObjectUtils.objectToMap(param));
	}

	// ---------

	public static Boolean delete(String sql, Map<String, Object> params) {

		try {

			sql = replaceSQL(sql, params);

			SQLDao sqlDao = (SQLDao) DaoConfig.getDao(SQLDao.class, 2);

			sqlDao.deleteSQL(sql);

			return true;

		} catch (Exception e) {

			SimpleLogger.setError("Error", e);

			return false;
		}

	}

	// ------------------------------------------------------------

	public static Boolean insert(String sql, Map<String, Object> params) {

		try {

			sql = replaceSQL(sql, params);

			SQLDao sqlDao = (SQLDao) DaoConfig.getDao(SQLDao.class, 2);

			sqlDao.insertSQL(sql);

			return true;

		} catch (Exception e) {

			SimpleLogger.setError("Error", e);

			return false;
		}

	}

	// ------------------------------------------------------------

	public static void insertExec(String sql, Map<String, Object> params) {

		sql = replaceSQL(sql, params);

		SQLDao sqlDao = (SQLDao) DaoConfig.getDao(SQLDao.class, 2);

		sqlDao.insertSQL(sql);

	}

	// -------------------------------------------------------------------------------------------------------------------------

	public static Boolean update(String sql) {
		return update(sql, null);
	}

	// ----------

	public static Boolean update(String updatesql, Object param) {
		return update(updatesql, ObjectUtils.objectToMap(param));
	}

	// ----------

	public static Boolean update(String updatesql, Map<String, Object> params) {

		try {

			updatesql = replaceSQL(updatesql, params);

			SQLDao sqlDao = (SQLDao) DaoConfig.getDao(SQLDao.class, 2);

			sqlDao.updateSQL(updatesql);

			return true;

		} catch (Exception e) {

			SimpleLogger.setError("Error", e);

			return false;
		}

	}

	// -------------------------------------------------------------------------------------------------------------------------

	public static Integer count(String sql) {
		return count(sql, null);
	}

	// ----------

	public static Integer count(String sql, Object param) {
		return count(sql, ObjectUtils.objectToMap(param));
	}

	// ----------

	public static Integer count(String sql, Map<String, Object> params) {
		try {

			sql = replaceSQL(sql, params);

			SQLDao sqlDao = (SQLDao) DaoConfig.getDao(SQLDao.class, 2);

			return sqlDao.selectSQLNumber(sql);

		} catch (Exception e) {
			SimpleLogger.setError("Error", e);
			return null;
		}
	}

	// -------------------------------------------------------------------------------------------------------------------------

	public static String replaceSQL(String updatesql, Map<String, Object> params) {
		if (params != null) {

			Set<String> keys = params.keySet();

			for (String key : keys) {

				Object obj = params.get(key);
				String val = StringUtils.trimToNull(StringUtils.toString(obj));

				if (val != null) {
					if (obj instanceof Integer || obj instanceof BigDecimal || obj instanceof Double) {
						updatesql = updatesql.replace("$" + key + "$", val);
					} else if (obj instanceof Date) {
						updatesql = updatesql.replace("$" + key + "$", "TO_DATE('" + val + "', 'DD/MM/YYYY HH24:MI:SS')");
					} else {
						updatesql = updatesql.replace("$" + key + "$", "'" + val.replace("'", "''") + "'");
					}
				} else {
					updatesql = updatesql.replace("$" + key + "$", "null");
				}
				updatesql = updatesql.replace("#" + key + "#", "'" + StringUtils.trimToEmpty(Crypto.E(params.get(key))) + "'");
			}
		}

		updatesql = RDServicio.reemplazarNombres(updatesql);
		return updatesql;
	}

	// -------------------------------------------------------------------------------------------------------------------------

	public static Integer nextId() {
		return queryForObject(Integer.class, "select NEXTVALUE('sst') from dual");
	}

	// -------------------------------------------------------------------------------------------------------------------------

	// -------------------------------------------------------------------------------------------------------------------------

}
