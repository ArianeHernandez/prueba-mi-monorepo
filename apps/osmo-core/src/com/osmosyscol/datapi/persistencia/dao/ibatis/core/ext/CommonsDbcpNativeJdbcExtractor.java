package com.osmosyscol.datapi.persistencia.dao.ibatis.core.ext;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Spring modified implementation of the NativeJdbcExtractor interface for Commonsd Dbcp.
 * Returns the underlying Connection to application code instead
 * of wrapper implementation; 
 * @see weblogic.jdbc.extensions.WLConnection#getVendorConnection
 * NOTE: verify that the parameter accessToUnderlyingConnectionAllowed="true" is active 
 * @author Juergen Hoeller
 * @since 25.08.2003 
 */
public class CommonsDbcpNativeJdbcExtractor implements INativeJdbcExtractor {
	// #region 01 logger declaration ---------------------
	private static final Logger logger = LoggerFactory.getLogger(CommonsDbcpNativeJdbcExtractor.class);

	// #endregion 01 -------------------------------------
	
    private static final String GET_INNERMOST_DELEGATE_METHOD_NAME = "getInnermostDelegate";
    
    /* (non-Javadoc)
	 * @see com.osmosyscol.datapi.persistencia.dao.ibatis.core.ext.INativeJdbcExtractor#doGetNativeConnection(java.sql.Connection)
	 */
    @Override
	public Connection doGetNativeConnection(Connection obj) throws Exception {
    	if( null == obj ) {
    		return null;
    	}
    	try {
	    	Class<?> classToAnalyze = obj.getClass();
			while (!Modifier.isPublic(classToAnalyze.getModifiers())) {
				classToAnalyze = classToAnalyze.getSuperclass();
				if (classToAnalyze == null) {
					// No public provider class found -> fall back to given object.
					return obj;
				}
			}
			Method getInnermostDelegate = classToAnalyze.getMethod(GET_INNERMOST_DELEGATE_METHOD_NAME, (Class[]) null);
			Object delegate = getInnermostDelegate.invoke(obj, (Object[]) null );
			if( logger.isDebugEnabled())
				logger.debug( String.format( "inner connection [class= %s]" ,(null != delegate) ?( delegate.getClass().getName()):("")));
			
			if( null == delegate ) {
                throw new RuntimeException("Invocation of Dbcp's innerMostDelegate method gives a null result");
			}
			return (Connection)delegate;
			// return (delegate != null ? (Connection)delegate : obj);
        }
        catch (NoSuchMethodException ex) {
			return obj;
		}
		catch (SecurityException ex) {
			throw new IllegalStateException("Commons DBCP getInnermostDelegate method is not accessible: " + ex);
		}	        
    }

}

