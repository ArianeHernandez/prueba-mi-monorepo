package com.osmosyscol.datapi.constantes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.oraclepool.JDCConnectionPool;
import com.osmosyscol.datapi.logica.dto.Conexion;
import com.osmosyscol.datapi.logica.dto.ServicioDataPi;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoManagerDTO;

/**
 * Declaracion de constantes.
 * @author null
 *
 */
public final class VariablesAplicacion {

	/**
	 * Logger declaration.
	 */
	private static Logger logger = LoggerFactory.getLogger(VariablesAplicacion.class);

	/**
	 * Guarda el listado de servicios configurados en la aplicacion (con *.conf).
	 */
	private Map<String, ServicioDataPi> listadoServicios;
	private Map<String, ServicioDataPi> listadoRecursosDataPI;
	private static Map<String, String> operacionTipoConexion = new HashMap<String, String>();

	private static final String CNX_ORACLE = "OR";
	private static final String CNX_IBATIS = "IB";
	private static final String CNX_ESTANDAR = "ES";
	private static final String CNX_BASE_1 = "B1";

	// -------------------------------------------------------------

	private static VariablesAplicacion variablesAplicacion;

	private VariablesAplicacion() {
		listadoServicios = new HashMap<String, ServicioDataPi>();
		listadoRecursosDataPI = new HashMap<String, ServicioDataPi>();
	}

	public static VariablesAplicacion getInstance() {
		if (variablesAplicacion == null) {
			variablesAplicacion = new VariablesAplicacion();
		}
		return variablesAplicacion;
	}

	// -------------------------------------------------------------

	public Map<String, ServicioDataPi> getListadoServicios() {
		return listadoServicios;
	}

	public void setListadoServicios(Map<String, ServicioDataPi> listadoServicios) {
		this.listadoServicios = listadoServicios;
	}

	/**
	 * Permite obtener un objeto java.sql.Connection acorde a la informacion 
	 * de configuracion manejada por el osmocore.
	 * @param conexion objeto que modela datos de conexion a la db
	 * @return objeto java.sql.Connection
	 * @throws Exception si existe algun error de configuracion
	 */
	public Connection getConnection(final Conexion conexion) throws Exception {

		Connection conn = null;

		String tipo_conexion = operacionTipoConexion.get(conexion.getNombre());
		
		logger.debug("TIPOCONEXIONUSADA: " + tipo_conexion );
		

		// ***************************************************

		if (CNX_ORACLE.equals(tipo_conexion)) {

			if (conexion.getNombre().startsWith("ORA:")) {

				JDCConnectionPool pool = JDCConnectionPool.getPool(conexion.getNombre());

				if (pool == null) {
					DaoManagerDTO daoManagerDTO = DaoConfig.getDaoManagerDTO(conexion.getNombre().substring(4).trim());
					DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

					pool = new JDCConnectionPool(conexion.getNombre(), daoManagerDTO.getUrl(), daoManagerDTO.getUser(), daoManagerDTO.getPassword());
				}

				conn = pool.getConnection();
			}

		}

		// ***************************************************
		if (CNX_IBATIS.equals(tipo_conexion)) {
			SqlMapClient sqlMapClient = DaoConfig.getSqlMapClient(conexion.getNombre());
			conn = sqlMapClient.getDataSource().getConnection();
		}

		// ***************************************************
		if (CNX_ESTANDAR.equals(tipo_conexion)) {
			Context initContext = new InitialContext();
			DataSource ds = (DataSource) initContext.lookup(conexion.getNombre());
			conn = ds.getConnection();
		}

		// ***************************************************
		if (CNX_BASE_1.equals(tipo_conexion)) {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup(conexion.getNombre());
			conn = ds.getConnection();
		}

		// ***************************************************
		boolean isValidConnection = false;
		if (conn != null) {
			isValidConnection = testConnection(conexion, conn);
		}

		// ***************************************************
		if (tipo_conexion == null || !isValidConnection) {
			conn = createConnection(conexion);
		}

		if (conn == null) {
			logger.error("No se pudo obtener la conexion para: " + conexion.getNombre());
			return null;
		}

		try {

			if ("Oracle".equalsIgnoreCase(conn.getMetaData().getDatabaseProductName())) {

				conn.setAutoCommit(false);

				try {

					PreparedStatement ps2 = conn.prepareStatement("alter session set NLS_TIME_FORMAT='HH12:MI:SSXFF AM'");
					ps2.executeUpdate();
					ps2.close();

					ps2 = conn.prepareStatement("alter session set NLS_TIMESTAMP_FORMAT='DD/MM/RR HH12:MI:SSXFF AM'");
					ps2.executeUpdate();
					ps2.close();

					ps2 = conn.prepareStatement("alter session set NLS_TIME_TZ_FORMAT='HH12:MI:SSXFF AM TZR'");
					ps2.executeUpdate();
					ps2.close();

					ps2 = conn.prepareStatement("alter session set NLS_TIMESTAMP_TZ_FORMAT='DD/MM/RR HH12:MI:SSXFF AM TZR'");
					ps2.executeUpdate();
					ps2.close();
					
					ps2 = conn.prepareStatement("alter session set NLS_DATE_FORMAT='DD/MM/RR HH24:MI:SS'");
					ps2.executeUpdate();
					ps2.close();

				} catch (Exception e) {
					logger.error("Ha ocurido un error al ejecutar la sentencia sql (update/insert).", e);
				}

				conn.commit();
				
				conn.setAutoCommit(true);

			}

		} catch (Exception e) {
			logger.error("Error definiendo formato de fecha para session", e);
		}

		return conn;
	}

	private boolean testConnection(Conexion conexion, Connection conn) {
		try {
			if (conn.isClosed()) {
				logger.error("<<WARN>> [testConnection] Prueba: Conexion cerrada");
				SimpleLogger.setError("<<WARN>> [testConnection] Prueba: Conexion cerrada");
				return false;
			} else {

				PreparedStatement ps = conn.prepareStatement("select 'ito' from dual");
				ps.execute();
				ps.close();

				return true;
			}
		} catch (SQLException e) {

			logger.error("<<ERROR>> [testConnection] No fue posible realizar la prueba.", e);

			try {
				conn.close();
				conn = null;
			} catch (SQLException e1) {
				logger.error("No se pudo cerrar la conexion. [" + conexion.getNombre() + "].", e1);
			}

			logger.error("No se pudo verificar la validez de la conexion. [" + conexion.getNombre() + "].", e);
			return false;
		}
	}

	private Connection createConnection(Conexion conexion) {
		Connection conn = null;
		Boolean selConexion = false;

		// ------------------------- IDENTIFICA SI ES UNA CONEXION DE ORACLE

		if (!selConexion) {
			try {

				if (conexion.getNombre().startsWith("ORA:")) {
					JDCConnectionPool pool = JDCConnectionPool.getPool(conexion.getNombre());

					if (pool == null) {
						DaoManagerDTO daoManagerDTO = DaoConfig.getDaoManagerDTO(conexion.getNombre().substring(4).trim());
						DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

						pool = new JDCConnectionPool(conexion.getNombre(), daoManagerDTO.getUrl(), daoManagerDTO.getUser(), daoManagerDTO.getPassword());
					}

					conn = pool.getConnection();

					selConexion = true;
					operacionTipoConexion.put(conexion.getNombre(), CNX_ORACLE);
					logger.debug("TIPOCONEXIONIDENTIFICADA: " + CNX_ORACLE );
				}

			} catch (Exception e) {
				if (selConexion) {
					logger.error("No se pudo obtener conexion Oracle [" + conexion.getNombre() + "]", e);
				}
			}
		}

		// ------------------------- IDENTIFICA SI ES UNA CONEXION DE IBATIS

		if (!selConexion) {
			try {

				SqlMapClient sqlMapClient = DaoConfig.getSqlMapClient(conexion.getNombre());

				if (sqlMapClient != null) {
					selConexion = true;
					conn = sqlMapClient.getDataSource().getConnection();
					logger.debug("TIPOCONEXIONIDENTIFICADA: " + CNX_IBATIS );
				}

				operacionTipoConexion.put(conexion.getNombre(), CNX_IBATIS);

			} catch (Exception e) {
				if (selConexion) {
					logger.error("No se pudo obtener conexion Ibatis [" + conexion.getNombre() + "]", e);
				}
			}
		}

		// ------------------------- IDENTIFICA SI ES UNA CONEXION JNDI ESTANDAR

		if (!selConexion) {
			try {

				Context initContext = new InitialContext();
				DataSource ds = (DataSource) initContext.lookup(conexion.getNombre());
				conn = ds.getConnection();

				operacionTipoConexion.put(conexion.getNombre(), CNX_ESTANDAR);

				selConexion = true;
				logger.debug("TIPOCONEXIONIDENTIFICADA: " + CNX_ESTANDAR );

			} catch (Exception e) {
				logger.warn( String.format( "Error obteniendo conexion JNDI. [name=%s,cause=%s,message=%s]", (null!=conexion)?(conexion.getNombre()):(""), e.getCause(),e.getMessage() ));
				if( logger.isTraceEnabled()) {
					logger.trace("error details:", e);
				}
			}
		}

		// ------------------------- IDENTIFICA SI ES UNA CONEXION JNDI (tomcat)

		if (!selConexion) {
			try {

				Context initContext = new InitialContext();
				Context envContext = (Context) initContext.lookup("java:/comp/env");
				DataSource ds = (DataSource) envContext.lookup(conexion.getNombre());
				conn = ds.getConnection();

				operacionTipoConexion.put(conexion.getNombre(), CNX_BASE_1);
				selConexion = true;
				logger.debug("TIPOCONEXIONIDENTIFICADA: " + CNX_BASE_1 );

			} catch (Exception e) {
				logger.warn( String.format( "Error obteniendo conexion JNDI. (with java:/comp/env subdomain) [name=%s,cause=%s,message=%s]", (null!=conexion)?(conexion.getNombre()):(""), e.getCause(),e.getMessage() ));
				if( logger.isTraceEnabled()) {
					logger.trace("error details:", e);
				}
			}
		}
		return conn;
	}

	public Map<String, ServicioDataPi> getListadoRecursosDataPI() {
		return listadoRecursosDataPI;
	}

	public void setListadoRecursosDataPI(Map<String, ServicioDataPi> listadoRecursosDataPI) {
		this.listadoRecursosDataPI = listadoRecursosDataPI;
	}

}
