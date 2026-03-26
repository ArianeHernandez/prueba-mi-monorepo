package com.osmosyscol.datapi.persistencia.dao.ibatis.core.ext;

import java.sql.Connection;

/**
 * Simple transfer of the connection without unwrap
 * @author ahurtado
 *
 */
public class BypassNativeJdbcExtractor implements INativeJdbcExtractor {

	@Override
	public Connection doGetNativeConnection(Connection con) throws Exception {
		return con;
	}

}
