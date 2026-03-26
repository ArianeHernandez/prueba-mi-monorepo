package com.osmosyscol.datasuite.logica.servicios;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.ibatis.dao.client.DaoManager;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.persistencia.dao.SQLDao;
import com.osmosyscol.datasuite.reportedim.servicio.RDServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

/**
 * 
 * Funciones utiles para operaciones SQL
 * 
 * @author oaortizs
 */
public class SQLServicio {

	/***
	 * Retorna el string adecuado de acuerdo al tipo de dato para utilizar en
	 * sentencias SQL
	 * 
	 * @param dato
	 *            - dato a generar
	 * @return valor sql
	 */
	public static String sqlCampo(Object dato) {
		String sql = "";

		if (dato == null) {
			sql = "null";
		} else if (dato instanceof String && dato.toString().startsWith("'") && dato.toString().endsWith("'")) {
			sql = dato.toString();
		}

		else if (dato instanceof String) {
			sql = "'" + StringUtils.escapeSQL((String) dato) + "'";
		} else if (dato instanceof Integer || dato instanceof BigDecimal || dato instanceof Long) {
			sql = "" + dato;
		}

		else if (dato instanceof Date) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			sql = "TO_DATE('" + dateFormat.format((Date) dato) + "', 'DD/MM/YYYY')";
		}

		else if (dato instanceof Boolean) {
			sql = "'" + (((Boolean) dato) ? Constantes.SI : Constantes.NO) + "'";
		}

		return sql;
	}

	/**
	 * Generar la sentencia update
	 * 
	 * @param table
	 * @param mapData
	 * @param condicion
	 * @return
	 */
	public static String createSQLUpdate(String table, Map<String, Object> mapData, Map<String, Object> mapWhere) {

		String sql = "update " + table + " set ";

		sql = camposSetMap(mapData, sql, ", ");
		sql += " where ";
		sql = camposMap(mapWhere, sql, "and");

		return sql;
	}

	private static String camposMap(Map<String, Object> mapData, String sql, String conector) {
		Object[] key = mapData.keySet().toArray();

		StringBuffer bufferSql = new StringBuffer();

		for (int i = 0; i < key.length; i++) {

			if (i > 0) {
				bufferSql.append(" " + conector + " ");
			}

			Object dato = mapData.get(key[i]);
			if (dato == null) {
				bufferSql.append(key[i] + " is ");
			} else {
				bufferSql.append(key[i] + " = ");
			}

			bufferSql.append(sqlCampo(dato));
		}
		return sql + bufferSql.toString();
	}

	private static String camposSetMap(Map<String, Object> mapData, String sql, String conector) {
		Object[] key = mapData.keySet().toArray();

		StringBuffer bufferSql = new StringBuffer();

		for (int i = 0; i < key.length; i++) {

			if (i > 0) {
				bufferSql.append(" " + conector + " ");
			}

			bufferSql.append(key[i] + " = ");

			Object dato = mapData.get(key[i]);

			bufferSql.append(sqlCampo(dato));
		}
		return sql + bufferSql.toString();
	}

	/**
	 * Generar la sentencia insert para los campos
	 * 
	 * @param table
	 *            - nombre de la tabla
	 * @param mapData
	 *            - map (campo, valor)
	 * @return
	 */
	public static String createSQLInsert(String table, Map<String, Object> mapData) {

		StringBuffer sql = new StringBuffer();
		sql.append("insert into " + table + " (");

		Object[] key = mapData.keySet().toArray();

		for (int i = 0; i < key.length; i++) {
			if (i > 0) {
				sql.append(", ");
			}

			sql.append(key[i]);
		}

		sql.append(" ) values ( ");

		for (int i = 0; i < key.length; i++) {
			if (i > 0) {
				sql.append(", ");
			}

			Object dato = mapData.get(key[i]);

			sql.append(sqlCampo(dato));

		}

		sql.append(" )");

		return sql.toString();
	}

	public static void insertTrans(String nombreTabla, Map<String, Object> mapData, DaoManager daoManager) {

		HashMap<String, Object> mapaInsert = new HashMap<String, Object>();
		SQLDao sqlDao = (SQLDao) daoManager.getDao(SQLDao.class);
		// Encriptar datos
		Set<Entry<String, Object>> datos = mapData.entrySet();

		for (Entry<String, Object> registro : datos) {

			String campo = registro.getKey().toUpperCase();
			if (campo.startsWith(":")) {
				campo = (campo.substring(1));
				String encriptado = Crypto.E(registro.getValue());
				mapaInsert.put(campo, encriptado);
			} else {
				campo = RDServicio.reemplazarNombres(campo);
				mapaInsert.put(campo, registro.getValue());
			}
		}

		String insertSQL = createSQLInsert(nombreTabla, mapaInsert);

		insertSQL = RDServicio.reemplazarNombres(insertSQL);

		sqlDao.insertSQL(insertSQL);

	}

	public static void insert(String nombreTabla, Map<String, Object> mapData) {

		DaoManager daoManager = DaoConfig.getDaoManager(2);
		insertTrans(nombreTabla, mapData, daoManager);
	}

	public static List<Map<String, Object>> selectSQL(String sql, Integer id) {

		List<Map<String, Object>> resp = new ArrayList<Map<String, Object>>();

		try {

			SqlMapClient sqlMapClient = null;

			sqlMapClient = DaoConfig.getSqlMapClient((Integer) id);

			Connection conn = sqlMapClient.getDataSource().getConnection();

			Statement s = conn.createStatement();

			ResultSet rs = null;

			try {

				rs = s.executeQuery(sql);

				ResultSetMetaData md = rs.getMetaData();

				int columnas = md.getColumnCount();

				while (rs.next()) {

					Map<String, Object> reg = new HashMap<String, Object>();

					for (int i = 1; i <= columnas; i++) {

						int type = rs.getMetaData().getColumnType(i);

						String nombre = rs.getMetaData().getColumnLabel(i);

						Object valor = null;
						if (type == Types.CHAR || type == Types.VARCHAR || type == Types.LONGVARCHAR) {
							valor = rs.getString(i);
						} else if (type == Types.NUMERIC || type == Types.DECIMAL) {
							valor = rs.getBigDecimal(i);
						} else if (type == Types.FLOAT || type == Types.DOUBLE) {
							valor = new Double(rs.getDouble(i));
						} else if (type == Types.BIGINT || type == Types.REAL) {
							valor = Long.valueOf(rs.getLong(i));
						} else if (type == Types.INTEGER) {
							valor = Integer.valueOf(rs.getInt(i));
						} else if (type == Types.DATE) {
							valor = rs.getTimestamp(i);
							if (valor != null) {
								valor = new Date(((java.sql.Timestamp) valor).getTime());
							}
						} else if (type == Types.TIME) {
							valor = rs.getTime(i);

							if (valor != null) {
								valor = new Date(((java.sql.Time) valor).getTime());
							}

						} else if (type == Types.TIMESTAMP) {
							valor = rs.getTimestamp(i);

							if (valor != null) {
								valor = new Date(((java.sql.Timestamp) valor).getTime());
							}

						} else if (type == Types.BIT) {
							valor = Boolean.valueOf(rs.getBoolean(i));
						} else if (type == Types.TINYINT) {
							valor = Byte.valueOf(rs.getByte(i));
						} else if (type == Types.SMALLINT) {
							valor = Short.valueOf(rs.getShort(i));
						}

						reg.put(nombre, valor);
					}

					resp.add(reg);
				}

			} catch (Exception e) {
				SimpleLogger.setError("Error", e);
			} finally {
				try {
					rs.close();
				} catch (Exception e) {
				}
				try {
					s.close();
				} catch (Exception e) {
				}
				try {
					conn.close();
				} catch (Exception e) {
				}
			}

		} catch (Exception e) {
			SimpleLogger.setError("Error", e);
		}

		return resp;

	}

	public static List<Map<String, Object>> select(String nombreTabla, String where) {

		List<Map<String, Object>> ret = new ArrayList<Map<String, Object>>();

		try {
			DaoManager daoManager = DaoConfig.getDaoManager(2);
			SQLDao dao = (SQLDao) daoManager.getDao(SQLDao.class);

			String select = "select * from " + nombreTabla;
			if (where != null) {
				if (where.length() != 0) {
					select += " where " + where;
				}
			}

			SimpleLogger.setDebug(nombreTabla + " select: " + select);

			select = RDServicio.reemplazarNombres(select);
			ret = dao.selectSQL(select);

			List<Map<String, Object>> newret = new ArrayList<Map<String, Object>>();

			for (Map<String, Object> registro : ret) {

				Set<String> keys = registro.keySet();

				Map<String, Object> re = new HashMap<String, Object>();

				for (String key : keys) {
					re.put(RDServicio.getNombreCampoReal(key), Crypto.DF(registro.get(key)));
				}

				newret.add(re);
			}

			ret = newret;

		} catch (Exception e) {

			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return ret;
	}

	public static List<Map<String, Object>> desencriptarMapa(List<Map<String, Object>> resp) {

		if (CollectionUtils.isEmpty(resp)) {
			return resp;
		}

		// desencripta
		for (Map<String, Object> registro : resp) {

			Set<String> keys = registro.keySet();
			for (String key : keys) {

				// se verifica si el valor es texto
				if (registro.get(key) instanceof String) {
					registro.put(key, Crypto.DF(registro.get(key)));
				}
			}
		}

		return resp;
	}

	public static List<Map<String, Object>> desencriptarMapaStringFormat(List<Map<String, Object>> resp) {

		if (CollectionUtils.isEmpty(resp)) {
			return resp;
		}

		// desencripta
		for (Map<String, Object> registro : resp) {

			Set<String> keys = registro.keySet();
			for (String key : keys) {

				// se verifica si el valor es texto
				if (registro.get(key) instanceof String) {
					registro.put(key, StringUtils.toStringFormat((Crypto.DF(registro.get(key)))));
				}
			}
		}

		return resp;
	}

	public static void commitTransaction(DaoManager daoManager) {
		try {
			daoManager.commitTransaction();
			daoManager.endTransaction();
		} catch (Exception e) {
			SimpleLogger.setError("Error", e);
		}
		
	}
	
}
