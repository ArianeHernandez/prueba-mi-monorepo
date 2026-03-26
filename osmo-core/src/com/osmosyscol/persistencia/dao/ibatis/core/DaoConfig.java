package com.osmosyscol.persistencia.dao.ibatis.core;

import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibatis.common.resources.Resources;
import com.ibatis.dao.client.Dao;
import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.DaoManagerBuilder;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class DaoConfig {

	// #region 01 logger declaration ---------------------
	private static final Logger logger = LoggerFactory.getLogger(DaoConfig.class);
	// #endregion 01 -------------------------------------
	
	private static Integer ID_DEFAULT;
	private static Integer idDisponible;

	private static final Map<Integer, DaoManagerDTO> pool = new HashMap<Integer, DaoManagerDTO>();
	private static final Map<String, DaoManagerDTO> poolname = new HashMap<String, DaoManagerDTO>();

	private static String resourceDao = "com/osmosyscol/persistencia/dao/ibatis/config/dao.xml";
	private static String resourceSqlMapConfig = "com/osmosyscol/persistencia/dao/ibatis/config/sql-map-config.xml";

	public static Set<String> getPoolNames() {
		return poolname.keySet();
	}

	public static List<DaoManagerDTO> getManagerDTOs() {

		List<DaoManagerDTO> lista = new ArrayList<DaoManagerDTO>();

		for (String nameList : poolname.keySet()) {
			DaoManagerDTO daoold = poolname.get(nameList);

			String test = "??";
			Connection cc = null;
			PreparedStatement ps = null;

			try {
				cc = daoold.getSqlMapClient().getDataSource().getConnection();

				String query = null;

				query = "select 'test' as test from dual";

				ps = cc.prepareStatement(query);
				ps.executeQuery();
				ps.close();
				cc.close();

				test = "OK";
			} catch (Exception e) {
				test = e.getMessage();

			} finally {

				if (cc != null) {
					try {
						cc.close();
					} catch (Exception e1) {
					}
				}

				if (ps != null) {
					try {
						ps.close();
					} catch (Exception e1) {
					}
				}
			}

			DaoManagerDTO ndao = new DaoManagerDTO(daoold.getId(), daoold.getNombre(), daoold.getUrl(), daoold.getDriver(), null, null, daoold.getUser(), daoold.getPassword());
			ndao.setTest(test);
			lista.add(ndao);
		}

		return lista;

	}

	public static String getDriver(DaoManager daoManager) {

		for (Integer id : pool.keySet()) {
			DaoManagerDTO daoManagerDTO = pool.get(id);

			if (daoManagerDTO.getDaoManager() == daoManager) {
				return daoManagerDTO.getDriver();
			}
		}
		return null;
	}

	public static String getUrl(DaoManager daoManager) {

		for (Integer id : pool.keySet()) {
			DaoManagerDTO daoManagerDTO = pool.get(id);

			if (daoManagerDTO.getDaoManager() == daoManager) {
				return daoManagerDTO.getUrl();
			}
		}
		return null;
	}

	public static void setResourceDao(String newresourseDao) {
		resourceDao = newresourseDao;
	}

	public static void setResourceSqlMapConfig(String newresourceSqlMapConfig) {
		resourceSqlMapConfig = newresourceSqlMapConfig;
	}

	public static DaoManager getDaoManager(Integer id) {
		DaoManagerDTO daoManagerDTO = pool.get(id);
		DaoManager daoManager = daoManagerDTO.getDaoManager();
		return daoManager;
	}

	public static boolean existDaoManager(String name) {
		return poolname.get(name) != null;
	}

	public static DaoManager getDaoManager(String name) {
		DaoManagerDTO daoManagerDTO = poolname.get(name);
		DaoManager daoManager = daoManagerDTO.getDaoManager();
		return daoManager;
	}

	public static DaoManagerDTO getDaoManagerDTO(String name) {
		return poolname.get(name);
	}

	public static SqlMapClient getSqlMapClient(Integer id) {
		DaoManagerDTO daoManagerDTO = pool.get(id);
		SqlMapClient sqlMapClient = daoManagerDTO.getSqlMapClient();
		return sqlMapClient;
	}

	public static SqlMapClient getSqlMapClient(String id) {
		DaoManagerDTO daoManagerDTO = poolname.get(id);
		SqlMapClient sqlMapClient = daoManagerDTO.getSqlMapClient();
		return sqlMapClient;
	}

	public static SqlMapClient getSqlMapClient(DaoManager daoManager) {

		for (Integer id : pool.keySet()) {
			DaoManagerDTO daoManagerDTO = pool.get(id);

			if (daoManagerDTO.getDaoManager() == daoManager) {
				return daoManagerDTO.getSqlMapClient();
			}
		}
		return null;
	}

	public static SqlMapClient getSqlMapClient(String nombre, String driver, String url, String user, String password) {
		Properties properties = loadProperties(driver, url, user, password);
		DaoManager daoManager = initDaoManager(properties);
		SqlMapClient sqlMapClient = initSqlMapCLient(properties);
		DaoManagerDTO daoManagerDTO = new DaoManagerDTO(idDisponible, nombre, url, driver, daoManager, sqlMapClient, user, password);
		pool.put(idDisponible, daoManagerDTO);
		poolname.put(nombre, daoManagerDTO);
		idDisponible = idDisponible + 1;
		return sqlMapClient;
	}

	public static DaoManager getDaoManager(String nombre, String driver, String url, String user, String password) {
		Properties properties = loadProperties(driver, url, user, password);
		DaoManager daoManager = initDaoManager(properties);
		SqlMapClient sqlMapClient = initSqlMapCLient(properties);
		DaoManagerDTO daoManagerDTO = new DaoManagerDTO(idDisponible, nombre, url, driver, daoManager, sqlMapClient, user, password);
		pool.put(idDisponible, daoManagerDTO);
		poolname.put(nombre, daoManagerDTO);
		idDisponible = idDisponible + 1;
		return daoManager;
	}

	public static SqlMapClient getSqlMapClient() throws Exception {
		DaoManagerDTO daoManagerDTO = pool.get(ID_DEFAULT);
		SqlMapClient sqlMapClient = null;
		sqlMapClient = daoManagerDTO.getSqlMapClient();
		return sqlMapClient;
	}

	public static DaoManager getDaoManager() throws Exception {
		return getDaoManager(ID_DEFAULT);
	}

	public static void startConecction(Integer id, String driver, String url, String user, String password, String nombre, boolean defecto, String maxActive, String maxIdle, String maxWait, String validationQuery, String testWhileIdle, String testOnBorrow, String timeBetweenEvictionRunsMillis, String initialSize, Boolean accessToUnderlyingConnectionAllowed) throws Exception {

		logger.info("Iniciando carga de configuracion: " + nombre + " -> " + driver + " -> " + defecto);

		if (defecto) {
			ID_DEFAULT = id;
		}

		// ------------------

		Properties properties = loadProperties(driver, url, user, password, maxActive, maxIdle, maxWait, validationQuery, testWhileIdle, testOnBorrow, timeBetweenEvictionRunsMillis, initialSize, accessToUnderlyingConnectionAllowed);
		DaoManager daoManager = initDaoManager(properties);
		SqlMapClient sqlMapClient = initSqlMapCLient(properties);
		DaoManagerDTO daoManagerDTO = new DaoManagerDTO(id, nombre, url, driver, daoManager, sqlMapClient, user, password);
		pool.put(id, daoManagerDTO);
		poolname.put(nombre, daoManagerDTO);
		if (idDisponible == null || id > idDisponible) {
			idDisponible = id;
		}
	}

	private static Properties loadProperties(String driver, String url, String user, String password) {
		Properties properties = new Properties();

		properties.put("database.driver", driver);
		properties.put("database.url", url);
		properties.put("database.user", user);
		properties.put("database.password", password);

		return properties;
	}

	private static Properties loadProperties(String driver, String url, String user, String password, String maxActive, String maxIdle, String maxWait, String validationQuery, String testWhileIdle, String testOnBorrow, String timeBetweenEvictionRunsMillis, String initialSize, Boolean accessToUnderlyingConnectionAllowed) {
		Properties properties = new Properties();

		properties.put("database.driver", driver);
		properties.put("database.url", url);
		properties.put("database.user", StringUtils.trimToEmpty(user));
		
		properties.put("database.password", StringUtils.trimToEmpty(password));
		
		if (StringUtils.isNotBlank(maxActive)) {
			properties.put("database.maxActive", maxActive);
		} else {
			properties.put("database.maxActive", "100");
		}
		if (StringUtils.isNotBlank(maxIdle)) {
			properties.put("database.maxIdle", maxIdle);
		} else {
			properties.put("database.maxIdle", "-1");
		}
		if (StringUtils.isNotBlank(maxWait)) {
			properties.put("database.maxWait", maxWait);
		} else {
			properties.put("database.maxWait", "10000");
		}
		if (StringUtils.isNotBlank(validationQuery)) {
			properties.put("database.validationQuery", validationQuery);
		} else {
			properties.put("database.validationQuery", "");
		}
		if (StringUtils.isNotBlank(testWhileIdle)) {
			properties.put("database.testWhileIdle", testWhileIdle);
		} else {
			properties.put("database.testWhileIdle", "true");
		}
		if (StringUtils.isNotBlank(testOnBorrow)) {
			properties.put("database.testOnBorrow", testOnBorrow);
		} else {
			properties.put("database.testOnBorrow", "true");
		}
		if (StringUtils.isNotBlank(timeBetweenEvictionRunsMillis)) {
			properties.put("database.timeBetweenEvictionRunsMillis", timeBetweenEvictionRunsMillis);
		} else {
			properties.put("database.timeBetweenEvictionRunsMillis", "300000");
		}
		if (StringUtils.isNotBlank(initialSize)) {
			properties.put("database.initialSize", initialSize);
		} else {
			properties.put("database.initialSize", "30");
		}
		
		// add parameter to get inner connecton of a wrapper pool
		Boolean paramValue = accessToUnderlyingConnectionAllowed;
		String config_PropertyName = IConfigKeys.ACCESSTOUNDERLYINGCONNECTIONALLOWED_EXTERNAL;
		if (null != paramValue ) {
			properties.put(config_PropertyName, paramValue );
		} else {
			properties.put(config_PropertyName, IConfigKeys.ACCESSTOUNDERLYINGCONNECTIONALLOWED_DEFAULT);
		}		
		return properties;
	}

	private static DaoManager initDaoManager(Properties properties) {
		try {
			Reader reader = Resources.getResourceAsReader(resourceDao);
			return DaoManagerBuilder.buildDaoManager(reader, properties);
		} catch (Exception e) {
			logger.error("Ha ocurrido un error al construir el DaoManager", e);
			throw new RuntimeException("Description.  Cause: " + e, e);
		}
	}

	private static SqlMapClient initSqlMapCLient(Properties properties) {
		try {
			Reader reader = Resources.getResourceAsReader(resourceSqlMapConfig);
			SqlMapClient sqlMap = (SqlMapClient) SqlMapClientBuilder.buildSqlMapClient(reader, properties);
			return sqlMap;
		} catch (Exception e) {
			logger.error("Ha ocurrido un error al construir el SqlMapClient", e);
			throw new RuntimeException("Description.  Cause: " + e, e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T getDao(Class<T> clase) {
		try {
			DaoManager daoManager = getDaoManager();
			return (T)daoManager.getDao(clase);

		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T getDao(Class<T> clase, Integer id) {
		try {
			DaoManager daoManager = getDaoManager(id);
			return (T)daoManager.getDao(clase);

		} catch (Exception e) {
			return null;
		}
	}
	
}