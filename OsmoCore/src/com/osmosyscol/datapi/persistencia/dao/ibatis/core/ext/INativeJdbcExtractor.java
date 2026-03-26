package com.osmosyscol.datapi.persistencia.dao.ibatis.core.ext;

import java.sql.Connection;

/**
 * Adapter interface to implement when a connection is wrapped by the container
 * @author ahurtado
 *
 */
public interface INativeJdbcExtractor {

	/**
	 * Retrieve the Connection via WebLogic's <code>getVendorConnection</code> method.
	 */
	Connection doGetNativeConnection(Connection con) throws Exception;

}