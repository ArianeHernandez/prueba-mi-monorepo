package com.osmosyscol.datasuite.webdata.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;
import java.util.Set;

import com.osmosyscol.commons.log.SimpleLogger;

/**
 * Clase para optimizar la inserción en base de datos de multiples registros con
 * la misma sentencia, solo se varian los parametros
 * 
 * @author osmosys
 * 
 */
public class CargaMasivaSqlUtil {

	private Connection connection = null;
	private PreparedStatement preparedStatement;

	public CargaMasivaSqlUtil(Connection connection) {
		this.connection = connection;
	}

	public Boolean prepararSentencia(String sql) {
		try {
			preparedStatement = connection.prepareStatement(sql);

			return true;
		} catch (Exception e) {
			SimpleLogger.setError("No se puede realizar la conexion a la base de datos.", e);
		}
		return false;
	}

	public void insertarRegistro(Map<Integer, String> parametros, Map<Integer, Class<?>> tipos) throws SQLException {

		Set<Integer> set = parametros.keySet();

		for (Integer parametro : set) {

			String valor = parametros.get(parametro);
			Class<?> tipo = tipos.get(parametro);

			if (valor == null) {

				if (tipo != null && Integer.class.equals(tipo)) {
					preparedStatement.setNull(parametro, Types.BIGINT);
				} else {
					preparedStatement.setNull(parametro, Types.VARCHAR);
				}

			} else {

				if (tipo != null && Integer.class.equals(tipo)) {
					preparedStatement.setInt(parametro, new Integer(valor.trim()));
				} else {
					preparedStatement.setObject(parametro, parametros.get(parametro));
				}

			}
		}
		preparedStatement.execute();
	}

	public void commit() throws SQLException {
		try {
			preparedStatement.close();
		} catch (Exception e) {
		}
		connection.commit();
	}

	public void rollback() throws SQLException {
		try {
			preparedStatement.close();
		} catch (Exception e) {
		}
		connection.rollback();
	}

}
