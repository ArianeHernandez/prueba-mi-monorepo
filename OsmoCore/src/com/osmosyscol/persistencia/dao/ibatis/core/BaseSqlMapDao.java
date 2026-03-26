package com.osmosyscol.persistencia.dao.ibatis.core;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.template.SqlMapDaoTemplate;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.AutenticacionServicio;

public class BaseSqlMapDao extends SqlMapDaoTemplate {

	protected static final int PAGE_SIZE = 4;

	public BaseSqlMapDao(DaoManager daoManager) {
		super(daoManager);
	}

	public String databaseName() {
		String url = DaoConfig.getUrl(daoManager);

		if (url.indexOf("oracle") > 0) {
			return "oracle";
		}

		if (url.indexOf("postgresql") > 0) {
			return "postgresql";
		}

		if (url.indexOf("sqlserver") > 0) {
			return "sqlserver";
		}

		return "undefined";
	}

	public String verifyQuery(String query) {

		if (query == null) {
			return null;
		}

		String url = DaoConfig.getUrl(daoManager);

		if (url.indexOf("oracle") > 0) {
			query = query.replace("localtimestamp", "SYSDATE");
			query = query.replace("localtimestamp", "sysdate");

			query = query.replace("CAST(null as bigint)", "null");
			query = query.replace("CAST(null as character)", "null");
			query = query.replace("CAST(null as text)", "null");

			query = query.replace("::text", "");
			query = query.replace("::bigint", "");
			query = query.replace("::character", "");
			query = query.replace("IF EXISTS", "");
		}

		else if (url.indexOf("postgresql") > 0) {
			query = query.replace("SYSDATE", "localtimestamp");
			query = query.replace("sysdate", "localtimestamp");
		}

		return query;
	}

	private Map<String, Object> getTipos(Map<String, Object> mapValues) {

		if (null == mapValues) {
			mapValues = new HashMap<String, Object>();
		}

		// ---------

		String url = DaoConfig.getUrl(daoManager);

		if (url.indexOf("oracle") > 0) {

			mapValues.put("MODIFY", "modify");
			mapValues.put("SET", "");
			mapValues.put("SET_NULL", "set null");

			mapValues.put("CHAR_TYPE", "char");
			mapValues.put("NUMBER_TYPE", "number");
			mapValues.put("VARCHAR2_TYPE", "varchar2");
			mapValues.put("SYSDATE", "localtimestamp");
		}

		if (url.indexOf("postgresql") > 0) {
			mapValues.put("MODIFY", "alter");
			mapValues.put("SET", "set");
			mapValues.put("SET_NULL", "drop not null");

			mapValues.put("CHAR_TYPE", "char");
			mapValues.put("NUMBER_TYPE", "INT8");
			mapValues.put("VARCHAR2_TYPE", "varchar");
			mapValues.put("SYSDATE", "localtimestamp");
		}

		if (url.indexOf("sqlserver") > 0) {
			mapValues.put("MODIFY", "alter");
			mapValues.put("SET", "set");
			mapValues.put("SET_NULL", "set null");

			mapValues.put("CHAR_TYPE", "char");
			mapValues.put("NUMBER_TYPE", "numeric");
			mapValues.put("VARCHAR2_TYPE", "varchar");
			mapValues.put("SYSDATE", "GETDATE()");
		}

		return mapValues;
	}

	public String getSqlTipo(String tiposql) {

		Map<String, String> mapValues = new HashMap<String, String>();

		String url = DaoConfig.getUrl(daoManager);

		if (url.indexOf("oracle") > 0) {
			mapValues.put("NUMBER", "NUMBER");
			mapValues.put("VARCHAR2", "VARCHAR2");
		}

		if (url.indexOf("postgresql") > 0) {
			mapValues.put("NUMBER", "INT8");
			mapValues.put("VARCHAR2", "VARCHAR");
		}

		if (url.indexOf("sqlserver") > 0) {
			mapValues.put("NUMBER", "NUMERIC");
			mapValues.put("VARCHAR2", "VARCHAR");
		}

		tiposql = tiposql.toUpperCase();

		String data = mapValues.get(tiposql);

		return data == null ? tiposql : data;
	}

	private Object addMap(Object data) {
		return addMap(data, false);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Object addMap(Object data, boolean preserveOriginalMap) {
		if (data instanceof Map) {
			Map dd = (Map) data;
			Map param = null;
			if (!preserveOriginalMap) {
				param = getTipos(null);
				param.putAll(dd);
				return param;
			} else {
				param = getTipos(dd);
				return param;
			}

		}

		return data;
	}

	// ------------------------------

	@SuppressWarnings({ "rawtypes" })
	public List queryForListPag(String strquery, Object data, Integer numpagina, Integer tampagina) {

		if (numpagina == null) {
			return queryForList(strquery, data);
		} else {
			return queryForList(strquery, data, (numpagina - 1) * tampagina, tampagina);
		}

	}

	public int update(String strquery, Object data) {
		return update(strquery, data, false);
	}

	public int update(String strquery, Object data, boolean preserveOriginalMap) {
		long timeservice_osmcoresystem = System.currentTimeMillis();

		int obj = super.update(strquery, addMap(data, preserveOriginalMap));

		timeservice_osmcoresystem = System.currentTimeMillis() - timeservice_osmcoresystem;
		AutenticacionServicio.addLogPage("Ibatis://" + strquery, timeservice_osmcoresystem);

		return obj;
	}

	public Object insert(String strquery, Object data) {

		long timeservice_osmcoresystem = System.currentTimeMillis();

		Object obj = super.insert(strquery, addMap(data));

		timeservice_osmcoresystem = System.currentTimeMillis() - timeservice_osmcoresystem;
		AutenticacionServicio.addLogPage("Ibatis://" + strquery, timeservice_osmcoresystem);

		return obj;

	}

	@SuppressWarnings({ "rawtypes" })
	public List queryForList(String strquery) {
		return queryForList(strquery, null);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List queryForList(String strquery, Object data) {

		long timeservice_osmcoresystem = System.currentTimeMillis();

		List list = verifyMapList(super.queryForList(strquery, addMap(data)));

		timeservice_osmcoresystem = System.currentTimeMillis() - timeservice_osmcoresystem;
		AutenticacionServicio.addLogPage("Ibatis://" + strquery, timeservice_osmcoresystem);

		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List queryForList(String strquery, Object data, int desde, int max) {

		long timeservice_osmcoresystem = System.currentTimeMillis();

		List list = verifyMapList(super.queryForList(strquery, addMap(data), desde, max));

		timeservice_osmcoresystem = System.currentTimeMillis() - timeservice_osmcoresystem;
		AutenticacionServicio.addLogPage("Ibatis://" + strquery, timeservice_osmcoresystem);

		return list;
	}

	public Object queryForObject(String strquery) {
		return queryForObject(strquery, null);
	}

	@SuppressWarnings("rawtypes")
	public Object queryForObject(String strquery, Object data) {

		long timeservice_osmcoresystem = System.currentTimeMillis();

		List listado = super.queryForList(strquery, addMap(data), 0, 1);

		Object obj = null;
		if (listado != null && listado.size() > 0) {
			obj = listado.get(0);
		}

		timeservice_osmcoresystem = System.currentTimeMillis() - timeservice_osmcoresystem;
		AutenticacionServicio.addLogPage("Ibatis://" + strquery, timeservice_osmcoresystem);

		return obj;
	}

	public Object queryForObject(String strquery, Object data, Object arg2) {

		long timeservice_osmcoresystem = System.currentTimeMillis();

		Object obj = super.queryForObject(strquery, addMap(data), arg2);

		timeservice_osmcoresystem = System.currentTimeMillis() - timeservice_osmcoresystem;
		AutenticacionServicio.addLogPage("Ibatis://" + strquery, timeservice_osmcoresystem);

		return obj;
	}

	// ---------------------

	private List<Map<String, Object>> verifyMapList(List<Map<String, Object>> resp) {
		if (resp == null) {
			return null;
		}

		try {
			List<Map<String, Object>> datos = resp;
			List<Map<String, Object>> newdatos = new ArrayList<Map<String, Object>>();

			for (Map<String, Object> map : datos) {
				Set<String> keys = map.keySet();
				Map<String, Object> newmap = new HashMap<String, Object>();
				for (String key : keys) {
					newmap.put(key.toUpperCase(), map.get(key));
				}
				newdatos.add(newmap);
			}

			return newdatos;
		} catch (Exception e) {
			return resp;
		}
	}

	public Integer nextValue(String secuence) {

		String url = DaoConfig.getUrl(daoManager).toLowerCase();

		SqlMapClient sqlMapClient = DaoConfig.getSqlMapClient(daoManager);

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		CallableStatement cstmt = null;

		try {
			con = sqlMapClient.getDataSource().getConnection();

			// Para oracle y postgres ( es necesario que exista dual y
			// nextvalue)
			if (url.indexOf("oracle") > 0 || url.indexOf("postgresql") > 0) {
				ps = con.prepareStatement("select nextvalue('" + secuence + "') as id from dual");
				rs = ps.executeQuery();

				if (rs.next()) {
					Integer valor = rs.getInt(1);
					rs.close();
					ps.close();
					return valor;
				} else {
					rs.close();
					ps.close();
				}
			}

			if (url.indexOf("sqlserver") > 0) {

				System.out.println(secuence);
				ps = con.prepareStatement("select next value for " + secuence); //SELECT NEXT VALUE FOR [SequenceName];
				rs = ps.executeQuery();

				if (rs.next()) {
					Integer valor = rs.getInt(1);
					rs.close();
					ps.close();
					return valor;
				} else {
					rs.close();
					ps.close();
				}
			}

		} catch (Throwable e) {
			SimpleLogger.setError("Error", e);

		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Throwable e) {
				}
			}

			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e2) {
				}
			}

			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e3) {
				}
			}

			if (cstmt != null) {
				try {
					cstmt.close();
				} catch (Exception e3) {
				}
			}
		}

		return null;
	}

}
