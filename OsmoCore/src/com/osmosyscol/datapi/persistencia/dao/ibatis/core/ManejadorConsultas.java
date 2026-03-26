package com.osmosyscol.datapi.persistencia.dao.ibatis.core;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.SQLUtils;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datapi.constantes.VariablesAplicacion;
import com.osmosyscol.datapi.logica.dto.Conexion;
import com.osmosyscol.datapi.logica.dto.Consulta;
import com.osmosyscol.datapi.logica.dto.Elemento;
import com.osmosyscol.datapi.logica.dto.Operacion;
import com.osmosyscol.datapi.logica.dto.Parametro;
import com.osmosyscol.datapi.logica.dto.Resultado;
import com.osmosyscol.datapi.logica.dto.ServicioDataPi;
import com.osmosyscol.datapi.logica.servicios.IFiltroConsultaSql;
import com.osmosyscol.datapi.persistencia.dao.ibatis.core.ext.CompositeJdbcExtractorProcessor;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

public class ManejadorConsultas {

	private static final char DECIMAL_SEPARATOR = '.';
	private static final SimpleDateFormat DATE_FORMAT_HOUR = new SimpleDateFormat("HH:mm:ss.S");
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat DATE_FORMAT_FULL1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	private static final SimpleDateFormat DATE_FORMAT_FULL = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

	// #region 01 logger declaration ---------------------

	private static Date parseDateFormat(String dateFormat) throws ParseException {

		if (dateFormat == null) {
			return null;
		}

		try {
			return DATE_FORMAT_FULL.parse(dateFormat);
		} catch (ParseException e) {

			try {
				return DATE_FORMAT_FULL1.parse(dateFormat);
			} catch (ParseException e2) {
				try {
					return DATE_FORMAT.parse(dateFormat);
				} catch (ParseException e1) {
					throw e1;
				}
			}

		}
	}

	// #endregion 01 -------------------------------------

	public static String toSQL(Object valor) {

		if (valor == null) {
			return "null";
		}

		if (valor instanceof String) {
			return "'" + SQLUtils.escapeSql(valor) + "'";
		}

		if (valor instanceof Boolean) {
			return ((Boolean) valor) ? "1" : "0";
		}

		DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols();
		formatSymbols.setDecimalSeparator(DECIMAL_SEPARATOR);

		NumberFormat nf = new DecimalFormat("#.##########", formatSymbols);

		if (valor instanceof BigDecimal || valor instanceof Double || valor instanceof Float) {
			return nf.format(valor);
		}

		if (valor instanceof BigInteger || valor instanceof Long || valor instanceof Integer) {
			return valor.toString();
		}

		return "'" + SQLUtils.escapeSql(valor) + "'";
	}

	public static List<Map<String, Object>> ejecutarConsulta(Conexion conexion, Operacion operacion, Map<String, Object> parametros, List<IFiltroConsultaSql> listFiltrosSql) throws SQLException {
		List<Map<String, Object>> maparsado = new ArrayList<Map<String, Object>>();

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = VariablesAplicacion.getInstance().getConnection(conexion);
			if (connection == null) {
				SimpleLogger.setError("No se puede realizar la consulta. No se puede realizar la conexion a la base de datos.");
				return null;
			}

			connection.setAutoCommit(false);

			String sql = operacion.getConsulta().getSql();

			if (operacion.getParametros() != null) {
				for (Parametro parametro : operacion.getParametros()) {
					if (parametros != null) {
						sql = sql.replace("#" + parametro.getNombre() + "#", toSQL(parametros.get(parametro.getNombre())));
					}
				}
			}

			if (listFiltrosSql != null) {
				SimpleLogger.setDebug("Aplicando filtros sql.");
				for (IFiltroConsultaSql filtro : listFiltrosSql) {
					sql = filtro.filtrarConsultar(sql);
				}
			}

			SimpleLogger.setDebug("SQL --> " + sql);

			if (sql.toLowerCase().trim().indexOf("update") == 0 || sql.toLowerCase().trim().indexOf("insert") == 0 || sql.toLowerCase().trim().indexOf("delete") == 0) {

				Integer actualizados = 0;
				String nombrecampo = "rownums";

				if (operacion.getResultados() != null && operacion.getResultados().size() > 0) {
					nombrecampo = operacion.getResultados().get(0).getNombre();
				}

				try {

					PreparedStatement ps2 = connection.prepareStatement(sql);
					actualizados = ps2.executeUpdate();
					ps2.close();

				} catch (Exception e) {
					SimpleLogger.setError("Ha ocurido un error al ejecutar la sentencia sql (update/insert).", e);
				}

				String driver = DaoConfig.getDaoManagerDTO(conexion.getNombre()).getDriver();
				SimpleLogger.setDebug("driver --> " + driver);
				if (driver.equalsIgnoreCase("com.sybase.jdbc3.jdbc.SybDriver")) {
					sql = "select " + actualizados + " as " + nombrecampo;
					SimpleLogger.setDebug("SQL --> " + sql);
				} else {
					sql = "select " + actualizados + " as " + nombrecampo + " from dual";
					SimpleLogger.setDebug("SQL --> " + sql);
				}

				SimpleLogger.setDebug("SQL --> " + sql);

			}

			ps = connection.prepareStatement(sql);

			rs = ps.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			List<String> columnsnames = new ArrayList<String>();
			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				columnsnames.add(rsmd.getColumnName(i + 1).toUpperCase());
			}

			List<Map<String, Object>> rsCache = new ArrayList<Map<String, Object>>();

			while (rs.next()) {

				Map<String, Object> allparams = new HashMap<String, Object>();

				for (String columna : columnsnames) {

					String val = rs.getString(columna);

					// valida si el tipo de dato es fecha

					String tipo = "string";

					for (Resultado resultado : operacion.getResultados()) {
						if (resultado.getNombre().equalsIgnoreCase(columna)) {
							tipo = resultado.getTipo().toLowerCase();
						}
					}

					if (tipo.equalsIgnoreCase("datetime")) {
						try {
							val = DATE_FORMAT.format(rs.getDate(columna)) + "T" + DATE_FORMAT_HOUR.format(rs.getTime(columna));
						} catch (Exception e) {
						}
					}

					if (tipo.equalsIgnoreCase("date")) {
						try {
							val = DATE_FORMAT.format(rs.getDate(columna));
						} catch (Exception e) {
						}
					}
					
					allparams.put(columna, val);
				}

				rsCache.add(allparams);
			}

			rs.close();
			ps.close();

			for (Map<String, Object> allparams : rsCache) {

				Map<String, Object> mr = new HashMap<String, Object>();

				for (int i = 0; i < operacion.getResultados().size(); i++) {
					Resultado resultado = (Resultado) operacion.getResultados().get(i);

					if (!Parametro.TIPO_SUBQUERY.equalsIgnoreCase(resultado.getTipo())) {
						mr.put(resultado.getNombre().toUpperCase(), allparams.get(resultado.getNombre().toUpperCase()));
					}
				}

				for (int i = 0; i < operacion.getResultados().size(); i++) {
					Resultado resultado = (Resultado) operacion.getResultados().get(i);

					if (Parametro.TIPO_SUBQUERY.equalsIgnoreCase(resultado.getTipo())) {

						Consulta defconsulta = null;
						List<Consulta> subconsultas = operacion.getConsulta().getSubconsulta();

						for (Consulta consulta : subconsultas) {
							if (consulta.getNombre().equalsIgnoreCase(resultado.getNombre())) {
								defconsulta = consulta;
							}
						}

						mr.put(resultado.getNombre().toUpperCase(), ejecutarsubconsulta(connection, parametros, operacion, defconsulta, resultado.getElementos(), allparams, listFiltrosSql));
					}
				}

				maparsado.add(mr);
			}

			connection.commit();
		} catch (Exception e) {

			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e2) {
			}

			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e2) {
			}

			SimpleLogger.setError("Ha ocurrido un error al realizar la consulta.", e);
			return null;

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e2) {
			}

			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e2) {
			}

			if (connection != null) {
				connection.rollback();
				connection.setAutoCommit(true);
				connection.close();
			}
		}
		return maparsado;
	}

	private static List<Map<String, Object>> ejecutarsubconsulta(Connection connection, Map<String, Object> parametros, Operacion operacion, Consulta consulta, List<Elemento> listadoelementos, Map<String, Object> mapaparametros, List<IFiltroConsultaSql> listFiltrosSql) throws Exception {

		List<Map<String, Object>> maparsado = new ArrayList<Map<String, Object>>();

		String sql = consulta.getSql();

		Set<String> listadoparametros = mapaparametros.keySet();

		if (operacion.getParametros() != null) {
			for (Parametro parametro : operacion.getParametros()) {
				if (parametros != null) {
					Object valor = parametros.get(parametro.getNombre());
					sql = sql.replace("#" + parametro.getNombre() + "#", toSQL(valor));
				}
			}
		}

		for (String parametro : listadoparametros) {
			try {
				Object valor = mapaparametros.get(parametro.toUpperCase());
				sql = sql.replace("#" + parametro.toUpperCase() + "#", toSQL(valor));
				sql = sql.replace("#" + parametro.toLowerCase() + "#", toSQL(valor));

			} catch (Exception e) {
			}
		}

		if (listFiltrosSql != null) {
			SimpleLogger.setDebug("Aplicando filtros sql.");
			for (IFiltroConsultaSql filtro : listFiltrosSql) {
				sql = filtro.filtrarConsultar(sql);
			}
		}

		SimpleLogger.setDebug("SQL --> " + sql);

		if (sql.toLowerCase().trim().indexOf("update") == 0 || sql.toLowerCase().trim().indexOf("delete") == 0 || sql.toLowerCase().trim().indexOf("insert") == 0) {

			PreparedStatement ps = connection.prepareStatement(sql);
			Integer actualizados = ps.executeUpdate();
			ps.close();

			String nombrecampo = "rownums";

			if (listadoelementos != null && listadoelementos.size() > 0) {
				nombrecampo = listadoelementos.get(0).getNombre();
			}

			sql = "select " + actualizados + " " + nombrecampo + " from dual";

			SimpleLogger.setDebug("SQL --> " + sql);
		}

		PreparedStatement ps = connection.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		ResultSetMetaData rsmd = rs.getMetaData();
		List<String> columnsnames = new ArrayList<String>();
		for (int i = 0; i < rsmd.getColumnCount(); i++) {
			columnsnames.add(rsmd.getColumnName(i + 1).toUpperCase());
		}

		List<Map<String, Object>> rsCache = new ArrayList<Map<String, Object>>();

		while (rs.next()) {

			Map<String, Object> allparams = new HashMap<String, Object>();

			for (String columna : columnsnames) {

				String val = rs.getString(columna);
				try {
					val = DATE_FORMAT.format(rs.getDate(columna)) + "T" + DATE_FORMAT_HOUR.format(rs.getTime(columna));
				} catch (Exception e) {
				}

				allparams.put(columna, val);
			}

			rsCache.add(allparams);
		}

		rs.close();
		ps.close();

		for (Map<String, Object> allparams : rsCache) {

			Map<String, Object> mr = new HashMap<String, Object>();

			for (int i = 0; i < listadoelementos.size(); i++) {
				Elemento elemento = listadoelementos.get(i);

				if (Parametro.TIPO_SUBQUERY.equalsIgnoreCase(elemento.getTipo())) {

					Consulta defconsulta = null;
					List<Consulta> subconsultas = consulta.getSubconsulta();

					for (Consulta consultat : subconsultas) {
						if (consultat.getNombre().equalsIgnoreCase(elemento.getNombre())) {
							defconsulta = consultat;
						}
					}

					mr.put(elemento.getNombre().toUpperCase(), ejecutarsubconsulta(connection, parametros, operacion, defconsulta, elemento.getElementos(), allparams, listFiltrosSql));
				} else {
					mr.put(elemento.getNombre().toUpperCase(), allparams.get(elemento.getNombre().toUpperCase()));
				}
			}

			maparsado.add(mr);
		}

		return maparsado;
	}

	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> ejecutarProcedimiento(Conexion conexion, Operacion operacion, Map<String, Object> parametros) throws SQLException {
		List<Map<String, Object>> maparsado = new ArrayList<Map<String, Object>>();

		Connection connection = null;

		CallableStatement cs = null;

		try {
			connection = VariablesAplicacion.getInstance().getConnection(conexion);

			if (connection == null) {
				SimpleLogger.setError("No se puede ejecutar el procedimiento. No se puede realizar la conexion a la base de datos.");
				return null;
			}

			connection.setAutoCommit(false);

			String sql = operacion.getProcedimiento();

			SimpleLogger.setDebug("SQL --> " + sql);

			// -- obtiene el nombre del procedimiento y el paquete
			String pname = sql.toUpperCase();
			pname = pname.substring(pname.indexOf("CALL ") + 4).trim();
			pname = pname.substring(0, pname.indexOf("(")).trim();

			String ppack = "%";
			String puser = connection.getMetaData().getUserName();

			if (pname.indexOf(".") > 0) {

				String[] names = pname.split("[.]");

				pname = names[names.length - 1].toUpperCase();

				if (names.length - 2 >= 0) {
					ppack = names[names.length - 2].toUpperCase();
				}

				if (names.length - 3 >= 0) {
					puser = names[names.length - 3].toUpperCase();
				}

			}

			cs = connection.prepareCall(sql);

			if (operacion.getParametros() != null) {

				for (Parametro parametro : operacion.getParametros()) {
					if (StringUtils.isBlank(parametro.getNombre())) {
						throw new RuntimeException("El parametro no tiene nombre");
					}
					Object valorParametro = parametros.get(parametro.getNombre());
					if (valorParametro == null) {

						if (StringUtils.equals(parametro.getTipo(), Parametro.TIPO_BOOLEAN)) {
							cs.setNull(parametro.getOrden(), java.sql.Types.BOOLEAN);
						} else if (StringUtils.equals(parametro.getTipo(), Parametro.TIPO_STRING)) {
							cs.setNull(parametro.getOrden(), java.sql.Types.VARCHAR);
						} else if (StringUtils.equals(parametro.getTipo(), Parametro.TIPO_DATE)) {
							cs.setNull(parametro.getOrden(), java.sql.Types.DATE);
						} else if (StringUtils.equals(parametro.getTipo(), Parametro.TIPO_DATETIME)) {
							cs.setNull(parametro.getOrden(), java.sql.Types.TIMESTAMP);
						} else if (StringUtils.equals(parametro.getTipo(), Parametro.TIPO_INT)) {
							cs.setNull(parametro.getOrden(), java.sql.Types.INTEGER);
						} else if (StringUtils.equals(parametro.getTipo(), Parametro.TIPO_LONG)) {
							cs.setNull(parametro.getOrden(), java.sql.Types.NUMERIC);
						} else if (StringUtils.equals(parametro.getTipo(), Parametro.TIPO_FLOAT)) {
							cs.setNull(parametro.getOrden(), java.sql.Types.NUMERIC);
						} else if (StringUtils.equals(parametro.getTipo(), Parametro.TIPO_DOUBLE)) {
							cs.setNull(parametro.getOrden(), java.sql.Types.NUMERIC);
						} else if (StringUtils.equals(parametro.getTipo(), Parametro.TIPO_CLOB)) {
							cs.setNull(parametro.getOrden(), java.sql.Types.CLOB);
						} else if (StringUtils.equals(parametro.getTipo(), Parametro.TIPO_ARRAY)) {

							String table_id = parametro.getId().split("[:]")[0].toUpperCase();
							cs.setNull(parametro.getOrden(), oracle.jdbc.OracleTypes.ARRAY, table_id);
						}

					} else {
						String valor = SQLUtils.escapeSql(valorParametro);

						if (StringUtils.equals(parametro.getTipo(), Parametro.TIPO_STRING)) {
							cs.setString(parametro.getOrden(), valor);
						} else if (StringUtils.equals(parametro.getTipo(), Parametro.TIPO_BOOLEAN)) {
							cs.setBoolean(parametro.getOrden(), StringUtils.esVerdad(valor));
						} else if (StringUtils.equals(parametro.getTipo(), Parametro.TIPO_DATE)) {
							cs.setDate(parametro.getOrden(), new java.sql.Date(DATE_FORMAT.parse(valor).getTime()));
						} else if (StringUtils.equals(parametro.getTipo(), Parametro.TIPO_DATETIME)) {
							cs.setTimestamp(parametro.getOrden(), new java.sql.Timestamp(parseDateFormat(valor).getTime()));
						} else if (StringUtils.equals(parametro.getTipo(), Parametro.TIPO_INT)) {
							cs.setBigDecimal(parametro.getOrden(), new BigDecimal(valor));
						} else if (StringUtils.equals(parametro.getTipo(), Parametro.TIPO_LONG)) {
							cs.setBigDecimal(parametro.getOrden(), new BigDecimal(valor));
						} else if (StringUtils.equals(parametro.getTipo(), Parametro.TIPO_FLOAT)) {
							cs.setFloat(parametro.getOrden(), new Float(valor));
						} else if (StringUtils.equals(parametro.getTipo(), Parametro.TIPO_DOUBLE)) {
							cs.setDouble(parametro.getOrden(), new Double(valor));
						} else if (StringUtils.equals(parametro.getTipo(), Parametro.TIPO_CLOB)) {
							// cs.setClob(parametro.getOrden(), new
							// StringReader(valor));
							cs.setString(parametro.getOrden(), valor);
						} else if (StringUtils.equals(parametro.getTipo(), Parametro.TIPO_ARRAY)) {

							List<Map<String, String>> listado = (List<Map<String, String>>) valorParametro;

							Record[] deptArray = new Record[listado.size()];

							for (int i = 0; i < listado.size(); i++) {
								Object[] datos_registros = new Object[parametro.getElementos().size()];
								for (Elemento elemento : parametro.getElementos()) {
									int npos = elemento.getOrden() - 1;
									String valor_elemento = listado.get(i).get(elemento.getNombre());

									if (valor_elemento == null) {
										datos_registros[npos] = null;
									} else if (StringUtils.equals(elemento.getTipo(), Parametro.TIPO_STRING)) {
										datos_registros[npos] = valor_elemento;
									} else if (StringUtils.equals(elemento.getTipo(), Parametro.TIPO_BOOLEAN)) {
										datos_registros[npos] = StringUtils.esVerdad(valor_elemento);
									} else if (StringUtils.equals(elemento.getTipo(), Parametro.TIPO_INT)) {
										datos_registros[npos] = new Long(valor_elemento);
									} else if (StringUtils.equals(parametro.getTipo(), Parametro.TIPO_LONG)) {
										datos_registros[npos] = new Long(valor_elemento);
									} else if (StringUtils.equals(elemento.getTipo(), Parametro.TIPO_FLOAT)) {
										datos_registros[npos] = new Float(valor_elemento);
									} else if (StringUtils.equals(elemento.getTipo(), Parametro.TIPO_DOUBLE)) {
										datos_registros[npos] = new Double(valor_elemento);
									} else if (StringUtils.equals(elemento.getTipo(), Parametro.TIPO_CLOB)) {
										datos_registros[npos] = valor_elemento;
									} else if (StringUtils.equals(elemento.getTipo(), Parametro.TIPO_DATE)) {
										datos_registros[npos] = new java.sql.Date(DATE_FORMAT.parse(valor_elemento).getTime());
									} else if (StringUtils.equals(elemento.getTipo(), Parametro.TIPO_DATETIME)) {
										datos_registros[npos] = new java.sql.Timestamp(parseDateFormat(valor_elemento).getTime());
									}
								}
								String obj_id = parametro.getId().split("[:]")[1].toUpperCase();
								deptArray[i] = new Record(obj_id, datos_registros);
							}
							String table_id = parametro.getId().split("[:]")[0].toUpperCase();

							Connection ora_connection = extractOracleConnection(connection);
							ArrayDescriptor arrayDept = ArrayDescriptor.createDescriptor(table_id, ora_connection);
							ARRAY deptArrayObject = new ARRAY(arrayDept, ora_connection, deptArray);
							cs.setArray(parametro.getOrden(), deptArrayObject);

						}

					}
				}

			}

			for (Resultado resultado : operacion.getResultados()) {
				Integer type = null;
				if (StringUtils.equals(resultado.getTipo(), Parametro.TIPO_STRING)) {
					type = Types.VARCHAR;
				} else if (StringUtils.equals(resultado.getTipo(), Parametro.TIPO_DATE)) {
					type = Types.DATE;
				} else if (StringUtils.equals(resultado.getTipo(), Parametro.TIPO_DATETIME)) {
					type = Types.TIMESTAMP;
				} else if (StringUtils.equals(resultado.getTipo(), Parametro.TIPO_INT)) {
					type = Types.INTEGER;
				} else if (StringUtils.equals(resultado.getTipo(), Parametro.TIPO_LONG)) {
					type = Types.NUMERIC;
				} else if (StringUtils.equals(resultado.getTipo(), Parametro.TIPO_BOOLEAN)) {
					type = Types.BOOLEAN;
				} else if (StringUtils.equals(resultado.getTipo(), Parametro.TIPO_FLOAT)) {
					type = Types.DECIMAL;
				} else if (StringUtils.equals(resultado.getTipo(), Parametro.TIPO_DOUBLE)) {
					type = Types.DECIMAL;
				} else if (StringUtils.equals(resultado.getTipo(), Parametro.TIPO_ARRAY)) {
					type = OracleTypes.CURSOR;
				} else if (StringUtils.equals(resultado.getTipo(), Parametro.TIPO_BLOB)) {
					type = Types.BLOB;
				} else if (StringUtils.equals(resultado.getTipo(), Parametro.TIPO_CLOB)) {
					type = Types.CLOB;
				}
				if (type != null) {
					if (resultado.getOrden() > 0) {
						cs.registerOutParameter(resultado.getOrden(), type);
					}
				}
			}

			cs.execute();

			Map<String, Object> mr = new HashMap<String, Object>();
			String key = createKey(ppack, pname, 1, 1);
			if (!metamap.containsKey(key)) {
				consultarParametrosProcedimiento(puser, ppack, pname, connection);
			}

			for (Resultado resultado : operacion.getResultados()) {
				if (StringUtils.equals(resultado.getTipo(), Parametro.TIPO_STRING)) {
					String valor = cs.getString(resultado.getOrden());

					try {
						valor = StringUtils.toString(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(valor));
					} catch (Exception e) {
					}

					if (valor != null) {
						mr.put(resultado.getNombre().toUpperCase(), valor);
					}
				} else if (StringUtils.equals(resultado.getTipo(), Parametro.TIPO_INT)) {
					BigDecimal valor = cs.getBigDecimal(resultado.getOrden());
					if (valor != null) {
						mr.put(resultado.getNombre().toUpperCase(), valor);
					}
				} else if (StringUtils.equals(resultado.getTipo(), Parametro.TIPO_LONG)) {
					BigDecimal valor = cs.getBigDecimal(resultado.getOrden());
					if (valor != null) {
						mr.put(resultado.getNombre().toUpperCase(), valor);
					}
				} else if (StringUtils.equals(resultado.getTipo(), Parametro.TIPO_BOOLEAN)) {
					Boolean valor = cs.getBoolean(resultado.getOrden());
					if (valor != null) {
						mr.put(resultado.getNombre().toUpperCase(), valor);
					}
				} else if (StringUtils.equals(resultado.getTipo(), Parametro.TIPO_DATE) || StringUtils.equals(resultado.getTipo(), Parametro.TIPO_DATETIME)) { // TODO
																																								// SE
																																								// DEBEN
																																								// SEPARAR
																																								// LOS
																																								// DOS
																																								// TIPOS
					Long time = null;

					{
						Timestamp valor = cs.getTimestamp(resultado.getOrden());
						if (valor != null) {
							time = valor.getTime();
						}
					}

					if (time == null) {
						java.sql.Date valor = cs.getDate(resultado.getOrden());
						if (valor != null) {
							time = valor.getTime();
						}
					}

					if (time != null) {
						String valor_str = DATE_FORMAT_FULL.format(new Date(time));
						mr.put(resultado.getNombre().toUpperCase(), valor_str);
					}

				} else if (StringUtils.equals(resultado.getTipo(), Parametro.TIPO_FLOAT)) {
					BigDecimal valor = cs.getBigDecimal(resultado.getOrden());
					if (valor != null) {
						mr.put(resultado.getNombre().toUpperCase(), valor);
					}
				} else if (StringUtils.equals(resultado.getTipo(), Parametro.TIPO_DOUBLE)) {
					BigDecimal valor = cs.getBigDecimal(resultado.getOrden());
					if (valor != null) {
						mr.put(resultado.getNombre().toUpperCase(), valor);
					}
				} else if (StringUtils.equals(resultado.getTipo(), Parametro.TIPO_BLOB)) {
					Blob valor = cs.getBlob(resultado.getOrden());
					if (valor != null) {
						mr.put(resultado.getNombre().toUpperCase(), com.osmosyscol.commons.utils.StringUtils.toStringB64(valor.getBinaryStream()));
					}
				} else if (StringUtils.equals(resultado.getTipo(), Parametro.TIPO_CLOB)) {
					Clob valor = cs.getClob(resultado.getOrden());
					if (valor != null) {
						mr.put(resultado.getNombre().toUpperCase(), com.osmosyscol.commons.utils.StringUtils.toString(valor.getCharacterStream()));
					}

				} else if (StringUtils.equals(resultado.getTipo(), Parametro.TIPO_ARRAY)) {
					List<Map<String, Object>> array;
					ResultSet rs = null;
					try {

						rs = (ResultSet) cs.getObject(resultado.getOrden());
						if (rs != null) {
							SimpleLogger.setDebug(String.format(" [connection=%s,rs=%s,resultado=%s,puser=%s,ppack=%s,pname=%s] ", (null != connection) ? (connection.getClass().getName()) : (""), (null != rs) ? (rs.toString()) : (""), (null != resultado) ? (resultado.getOrden()) : (""), (null != puser) ? (puser) : (""), (null != ppack) ? (ppack) : (""), (null != pname) ? (pname) : ("")));

							array = recorrerResultSet(connection, rs, resultado, puser, ppack, pname);
							rs.close();
						} else {
							array = new ArrayList<Map<String, Object>>();
						}
					} catch (SQLException e) {
						array = new ArrayList<Map<String, Object>>();
					} finally {
						try {
							if (rs != null) {
								rs.close();
							}
						} catch (Exception e2) {
							SimpleLogger.setError("", e2);
						}
					}
					mr.put(resultado.getNombre().toUpperCase(), array);
				}
			}

			maparsado.add(mr);
			cs.close();

			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			SimpleLogger.setError("Ha ocurrido un error al ejecutar el procedimiento", e);
			SimpleLogger.setError("Procedimiento: " + operacion.getProcedimiento() + "Parametros: " + parametros);
			return null;
		} finally {

			try {
				if (cs != null) {
					cs.close();
				}
			} catch (Exception e2) {
			}

			try {
				if (connection != null) {
					connection.rollback();
					connection.setAutoCommit(true);
					connection.close();
				}
			} catch (Exception e2) {
			}
		}
		return maparsado;
	}

	/**
	 * Used to process the connection wrappers
	 */
	private static CompositeJdbcExtractorProcessor nativeExtractorProcessor = null;

	/**
	 * Initialize jdbcextractor processor
	 */
	static {

		if (SimpleLogger.isDebug())
			SimpleLogger.setDebug("initializing extractor processing (begin)");

		nativeExtractorProcessor = new CompositeJdbcExtractorProcessor();
		nativeExtractorProcessor.addDefaultExtractors();

		if (SimpleLogger.isDebug())
			SimpleLogger.setDebug("initializing extractor processing (end)");

	}

	/**
	 * extracts an oracle connection from wrappers
	 * 
	 * @param connection
	 *            wrapped connection
	 * @return a compatible oracle connection if found
	 * @throws Exception
	 */
	private static Connection extractOracleConnection(Connection connection) throws Exception {
		Connection localResult = nativeExtractorProcessor.doGetNativeConnection(connection);
		if (!nativeExtractorProcessor.isOracleCompatible(localResult)) {
			SimpleLogger.setWarn(String.format("used connection could not be cast to an OracleConnection [wrapper=%s,inner=%s] ", (null != connection) ? (connection.getClass().getName()) : (""), (null != localResult) ? (localResult.getClass().getName()) : ("")));
		}

		return localResult;
	}

	private static String consultarParametrosProcedimiento(String usuario, String paquete, String procedimiento, Connection connection) throws SQLException {

		// Buscar el usuario dueño del objeto
		PreparedStatement psUsuario = connection.prepareStatement(CONSULTA_USUARIO_PAQUETE);
		psUsuario.setString(1, paquete);
		ResultSet resultSet = psUsuario.executeQuery();

		String nusuario = null;

		while (resultSet.next()) {
			nusuario = resultSet.getString(1);

			if (nusuario.equals(usuario)) {
				break;
			}
		}

		if (nusuario != null) {
			usuario = nusuario;
		}

		resultSet.close();

		PreparedStatement ps = connection.prepareStatement(CONSULTA_PARAMETROS_PROCEDIMIENTO);
		resultSet = null;
		String codename = null;

		ps.setString(1, procedimiento);
		ps.setString(2, paquete);
		ps.setString(3, usuario);

		if (StringUtils.isEmpty(paquete)) {
			ps.setNull(2, Types.VARCHAR);
		}

		resultSet = ps.executeQuery();

		List<Map<String, Object>> list = resultSetToArrayList(resultSet);

		resultSet.close();

		if (!CollectionUtils.isEmpty(list)) {

			Integer positionParent = 0;

			for (Map<String, Object> map : list) {
				Integer dataLevel = Integer.parseInt(map.get("DATA_LEVEL").toString());
				Integer position = Integer.parseInt(map.get("POSITION").toString());
				String argumentName = map.get("ARGUMENT_NAME").toString();
				String dataType = map.get("DATA_TYPE").toString();

				if ("REF CURSOR".equals(dataType) || "PL/SQL RECORD".equals(dataType)) {
					positionParent = position;
				} else if (dataLevel == 0) {
					String key = createKey(paquete, procedimiento, position, position);
					metamap.put(key, argumentName);
				} else {
					String key = createKey(paquete, procedimiento, position, positionParent);
					metamap.put(key, argumentName);
				}
			}
		}

		return codename;
	}

	private static List<Map<String, Object>> resultSetToArrayList(ResultSet rs) throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		int columns = md.getColumnCount();
		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();

		while (rs.next()) {
			Map<String, Object> row = new HashMap<String, Object>();
			results.add(row);

			for (int i = 1; i <= columns; i++) {
				row.put(md.getColumnName(i), rs.getObject(i));
			}
		}
		return results;
	}

	/**
	 * @return
	 */
	private static String createKey(Object... params) {
		if (params == null) {
			return null;
		}
		return StringUtils.join(params, "|");
	}

	private static Map<String, String> metamap = new HashMap<String, String>();

	private static List<Map<String, Object>> recorrerResultSet(Connection connection, ResultSet rs, Resultado resultado, String usuario, String paquete, String procedimiento) throws SQLException {

		int columnas = rs.getMetaData().getColumnCount();

		Map<String, String> indexColumnName = new HashMap<String, String>();

		PreparedStatement ps = null;
		ResultSet nrs = null;

		try {
			String sql = generarSql();
			ps = connection.prepareStatement(sql);
			for (int pos = 1; pos <= columnas; pos++) {

				String key = createKey(paquete, procedimiento, pos, resultado.getOrden());

				if (metamap.containsKey(key)) {
					indexColumnName.put("" + pos, metamap.get(key));
				}

			}
		} catch (Exception e) {
			System.out.println("<<ERROR>> recorrerResultSet:");
			SimpleLogger.setError("Error", e);
			System.out.println("\n<<INFO>> Conexion: " + ((null != connection) ? (connection.toString()) : ""));

		} finally {

			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e2) {
			}

			try {
				if (nrs != null) {
					nrs.close();
				}
			} catch (Exception e2) {
			}
		}

		SimpleLogger.setDebug("Resultado : " + indexColumnName);

		List<Map<String, Object>> mapa_resultado = new ArrayList<Map<String, Object>>();
		while (rs.next()) {
			Map<String, Object> registro = new HashMap<String, Object>();
			for (int i = 1; i <= columnas; i++) {
				int type = rs.getMetaData().getColumnType(i);

				String nombre = indexColumnName.get("" + i);

				Object valor = null;
				if (type == Types.CHAR || type == Types.VARCHAR || type == Types.LONGVARCHAR || type == Types.CLOB) {
					valor = rs.getString(i);
				} else if (type == Types.NUMERIC || type == Types.DECIMAL || type == Types.FLOAT || type == Types.DOUBLE) {
					valor = rs.getBigDecimal(i);
				} else if (type == Types.BIGINT || type == Types.REAL || type == Types.INTEGER) {
					valor = Long.valueOf(rs.getLong(i));
				} else if (type == Types.DATE) {
					valor = rs.getDate(i);
					if (valor != null) {
						valor = DATE_FORMAT.format(new Date(((java.sql.Date) valor).getTime()));
					}
				} else if (type == Types.TIME) {
					valor = rs.getTime(i);

					if (valor != null) {
						valor = new SimpleDateFormat("HH:mm:ss").format(new Date(((java.sql.Time) valor).getTime()));
					}

				} else if (type == Types.TIMESTAMP) {
					valor = rs.getTimestamp(i);

					if (valor != null) {
						try {
							Date fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(StringUtils.trim(valor.toString()));
							valor = DATE_FORMAT_FULL.format(fecha);

						} catch (Exception e) {
						}
					}

				} else if (type == Types.BIT) {
					valor = Boolean.valueOf(rs.getBoolean(i));
				} else if (type == Types.TINYINT) {
					valor = Byte.valueOf(rs.getByte(i));
				} else if (type == Types.SMALLINT) {
					valor = Short.valueOf(rs.getShort(i));
				}

				if (valor != null) {

					try {
						Date date = null;
						if (valor instanceof Date) {
							date = (Date) valor;
						} else if (valor instanceof String) {
							String val = valor.toString();
							// "yyyy-MM-dd'T'HH:mm:ss"
							if (val.matches("(\\d{4})-(\\d{2})-(\\d{2})\\s[0-9:a-z\\s]*")) {
								date = parseDateFormat(StringUtils.trim(valor.toString()));

							}
						}
						if (date != null) {
							valor = StringUtils.toString(date);
						}
					} catch (Exception e) {
					}

					registro.put(nombre, valor);
				}
			}

			if (!registro.isEmpty()) {
				mapa_resultado.add(registro);
			}
		}
		return mapa_resultado;
	}

	/**
	 * 
	 * precargaMetadataProcedimientos
	 * 
	 * @return
	 */
	public static String precargaMetadataProcedimientos() {

		Connection connection = null;

		try {
			SimpleLogger.setInfo("Iniciando precarga de servicios DataPi");

			Map<String, ServicioDataPi> servicios = VariablesAplicacion.getInstance().getListadoServicios();

			for (String key : servicios.keySet()) {
				ServicioDataPi dp = servicios.get(key);

				SimpleLogger.setDebug("Cargando servicio " + dp.getNombre());

				for (Operacion op : dp.getOperaciones()) {

					connection = VariablesAplicacion.getInstance().getConnection(dp.getConexion());

					SimpleLogger.setDebug("Cargando operacion " + op.getNombre());

					if (op.getProcedimiento() != null) {

						String usuario = connection.getMetaData().getUserName();
						String sql = op.getProcedimiento();
						String paquete = "%";

						// -- obtiene el nombre del procedimiento y el paquete
						String procedimiento = sql.toUpperCase();
						procedimiento = procedimiento.substring(procedimiento.indexOf("CALL ") + 4).trim();
						procedimiento = procedimiento.substring(0, procedimiento.indexOf("(")).trim();

						if (procedimiento.indexOf(".") > 0) {

							String[] names = procedimiento.split("[.]");

							procedimiento = names[names.length - 1].toUpperCase();

							if (names.length - 2 >= 0) {
								paquete = names[names.length - 2].toUpperCase();

							}

						}

						consultarParametrosProcedimiento(usuario, paquete, procedimiento, connection);
						// buscarParametrosProcedimiento(usuario, paquete,
						// procedimiento, op, connection);

					}

					if (connection != null) {
						try {
							connection.close();
							connection = null;
						} catch (SQLException e) {
							SimpleLogger.setError("Error", e);
						}
					}

				}
			}

			SimpleLogger.setInfo("Fin precarga de servicios DataPi");

		} catch (Exception e) {
			SimpleLogger.setError("Error cargue rapido", e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					SimpleLogger.setError("Error", e);
				}
			}
		}

		return null;
	}

	/**
	 * 
	 * Genera el sql/preparedstatement para buscar el codename de un parametro.
	 * 
	 * @param usuario
	 * @param paquete
	 * @param procedimiento
	 * @param pos
	 * @param orden
	 * @return
	 */
	private static String generarSql() {

		String sql = "";

		sql += "select argument_name, position, ( select max(POSITION) from ALL_ARGUMENTS where ";
		sql += "OWNER = ? and "; // 1. Usuario - string
		sql += " OBJECT_NAME = ? AND "; // 2. procedimiento - string

		sql += "nvl(PACKAGE_NAME,'NULL') = nvl(?,'NULL') and "; // 3. paquete -
																// string

		sql += "data_level = 0 and sequence <= ds.sequence) as param from ALL_ARGUMENTS ds ";
		sql += "where OBJECT_NAME = ? AND "; // 4. procedimiento - string

		sql += "nvl(PACKAGE_NAME,'NULL') = nvl(?,'NULL') AND ";// 5. paquete -
																// string

		sql += "OWNER like ? AND "; // 6 usuario

		sql += "data_type not in ('REF CURSOR', 'PL/SQL RECORD') ";
		sql += "ORDER BY SEQUENCE ";

		return sql;
	}

	private static final String CONSULTA_USUARIO_PAQUETE = "select owner from all_objects" + " where object_name = ? " + "and object_type = 'PACKAGE'";

//	private static final String CONSULTA_PARAMETROS_PROCEDIMIENTO = "SELECT ARGUMENT_NAME, POSITION, SEQUENCE, DATA_LEVEL, DATA_TYPE" + "  FROM ALL_ARGUMENTS ds" + "  WHERE OBJECT_NAME  = ?" + // 1.
//																																																	// procedimiento
//			"  AND NVL(PACKAGE_NAME, 'XXX')  = NVL(?, 'XXX')" + // 2. paquete
//			"  AND OWNER          = ? " + // 3. owner
//			" AND ARGUMENT_NAME IS NOT NULL" + // Busca argumentos que tienen
//												// nombre
//			"  ORDER BY SEQUENCE";
	//TODO: Probar servicios que retornar más de un refcursor
	private static final String CONSULTA_PARAMETROS_PROCEDIMIENTO = 
			"SELECT DISTINCT" + 
			"    nvl(attr_name, ds.argument_name) argument_name," + 
			"    nvl(attr_no, ds.position) position," + 
			"    nvl(attr_no + 100, ds.sequence) sequence," + 
			"    nvl(ds.data_level, 1) data_level," + 
			"    nvl(attr_type_name, ds.data_type) data_type " + 
			"FROM " + 
			"    (" + 
			"        SELECT" + 
			"            argument_name," + 
			"            position," + 
			"            sequence," + 
			"            data_level," + 
			"            data_type," + 
			"            NULL type_name," + 
			"            NULL type_subname," + 
			"            owner," + 
			"            package_name," + 
			"            object_name" + 
			"        FROM" + 
			"            all_arguments" + 
			"        UNION ALL " + 
			"        SELECT" + 
			"            argument_name," + 
			"            position," + 
			"            sequence," + 
			"            data_level," + 
			"            data_type," + 
			"            type_name," + 
			"            type_subname," + 
			"            owner," + 
			"            package_name," + 
			"            object_name" + 
			"        FROM" + 
			"            all_arguments" + 
			"        WHERE" + 
			"            data_type = 'REF CURSOR'" + 
			"    ) ds" + 
			"    LEFT JOIN all_plsql_type_attrs apt ON apt.owner = ds.owner " + 
			"                                          AND apt.type_name = ds.type_subname " + 
			"                                          AND apt.package_name = ds.type_name " + 
			"WHERE " + 
			"    ds.object_name = ?" + //1. Procedimiento
			"    AND nvl(ds.package_name, 'XXX') = nvl(?, 'XXX') " + //2. paquete
			"AND DS.OWNER          = ? " + 	//3. owner 
			"    AND ds.argument_name IS NOT NULL " + 	
			"ORDER BY " + 
			"    sequence";
}

class Record implements SQLData {

	private Object[] data;
	private String typename;

	public Record(String typename, Object[] data) {
		this.data = data;
		this.typename = typename;
	}

	public void readSQL(SQLInput stream, String typeName) throws SQLException {

	}

	public void writeSQL(SQLOutput stream) throws SQLException {

		if (data != null) {
			for (Object item : data) {

				if (item instanceof String) {
					stream.writeString((String) item);
				}

				if (item instanceof Double) {
					stream.writeDouble((Double) item);
				}

				if (item instanceof Integer) {
					stream.writeInt((Integer) item);
				}

				if (item instanceof Long) {
					stream.writeLong((Long) item);
				}

				if (item instanceof java.sql.Date) {
					stream.writeDate((java.sql.Date) item);
				}

				if (item instanceof Boolean) {
					stream.writeBoolean((Boolean) item);
				}

				if (item == null) {
					stream.writeString(null);
				}

			}
		}
	}

	public String getSQLTypeName() throws SQLException {
		return typename;
	}
}
