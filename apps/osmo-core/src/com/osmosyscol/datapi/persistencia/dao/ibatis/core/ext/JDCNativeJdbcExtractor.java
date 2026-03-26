package com.osmosyscol.datapi.persistencia.dao.ibatis.core.ext;

import java.sql.Connection;

import com.osmosyscol.commons.oraclepool.JDCConnection;

/**
 * Backward compatibility class 
 * because exists a class that holds the OracleConnection
 * @author ahurtado
 *
 */
public class JDCNativeJdbcExtractor implements INativeJdbcExtractor {
	public Connection doGetNativeConnection(Connection connection) throws Exception {
		if( null == connection ) {
			return null;
		}
		return connection = ((JDCConnection) connection).getConnection(); 
    }

}
