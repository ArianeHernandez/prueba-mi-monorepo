package com.osmosyscol.datapi.persistencia.dao.ibatis.core.ext;

import java.lang.reflect.Method;
import java.sql.Connection;

/**
 * Spring modified implementation of the NativeJdbcExtractor interface for WebLogic Server.
 * Returns the underlying native Connection to application code instead
 * of WebLogic's wrapper implementation; unwraps the Connection for
 * native statements. The returned JDBC classes can then safely be cast,
 * e.g. to OracleConnection.
 *
 * <p>This NativeJdbcExtractor can be set just to <i>allow</i> working
 * with a WebLogic DataSource: If a given object is not a WebLogic
 * Connection wrapper, it will be returned as-is.
 *
 * <p>Currently only tested with Oracle WebLogic 11g 10.3.3.
 *
 * @author Thomas Risberg
 * @author Juergen Hoeller
 * @see #getNativeConnection
 * @see weblogic.jdbc.extensions.WLConnection#getVendorConnection
 */
public class WlasNativeJdbcExtractor implements INativeJdbcExtractor {
    private static final String JDBC_EXTENSION_NAME = "weblogic.jdbc.extensions.WLConnection";

    private final Class<?> jdbcExtensionClass;

    private final Method getVendorConnectionMethod;
    
    /**
     * This constructor retrieves the WebLogic JDBC extension interface,
     * so we can get the underlying vendor connection using reflection.
     */
    public WlasNativeJdbcExtractor() {
        try {
            this.jdbcExtensionClass = getClass().getClassLoader().loadClass(JDBC_EXTENSION_NAME);
            this.getVendorConnectionMethod = this.jdbcExtensionClass.getMethod("getVendorConnection", (Class[]) null);
        }
        catch (Exception ex) {
            throw new RuntimeException(
                    "Couldn't initialize WebLogicNativeJdbcExtractor because WebLogic API classes are not available", ex);
        }
    }

    /**
     * Return true, as WebLogic returns wrapped Statements.
     */
    public boolean isNativeConnectionNecessaryForNativeStatements() {
        return true;
    }

    /**
     * Return true, as WebLogic returns wrapped PreparedStatements.
     */
    public boolean isNativeConnectionNecessaryForNativePreparedStatements() {
        return true;
    }

    /**
     * Return true, as WebLogic returns wrapped CallableStatements.
     */
    public boolean isNativeConnectionNecessaryForNativeCallableStatements() {
        return true;
    }

    /* (non-Javadoc)
	 * @see com.osmosyscol.datapi.persistencia.dao.ibatis.core.ext.INativeJdbcExtractor#doGetNativeConnection(java.sql.Connection)
	 */
    @Override
	public Connection doGetNativeConnection(Connection con) throws Exception {
        if (this.jdbcExtensionClass.isAssignableFrom(con.getClass())) {
            try {
                return (Connection) this.getVendorConnectionMethod.invoke(con, (Object[]) null);
            }
            catch (Exception ex) {
                throw new RuntimeException("Could not invoke WebLogic's getVendorConnection method", ex);
            }
        }
        return con;
    }

}
