package com.osmosyscol.datasuite.webdata.logica.servicios.utils;


import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public class JxPathUtils {
	
	/**
	 * Evalua serie de propiedades de un contexto xpath y llena el mapa con dichas propiedades
	 * @param jxPathContext
	 * @param objectContext
	 * @param localResult
	 * @param propertyMapRules
	 */
	public void copyPropertiesToMap( JXPathContext jxPathContext, Object objectContext, Map<String,Object> localResult, Object[][] propertyMapRules  ) {
		String path;
		String key;
		Object value;

		for( Object[] element: propertyMapRules ) {
			key = (String)element[0];
			path = (String)element[1];

			value = getValue(jxPathContext,objectContext,path );
			addValue( localResult, path, value, key );	
		}
	}
	
	/**
	 * Adiciona un valor a un map
	 * @param map
	 * @param path
	 * @param value
	 * @param key
	 */
	public void addValue(Map<String, Object> map, String path,	Object value, String key) {
		map.put(key, value);
	}	
	
	private static final Log logger = LogFactory.getLog(JxPathUtils.class);

	/**
	 * Permite fijar un valor de un objeto a traves de jxPath
	 * @param jxPathContext
	 * @param objectContext
	 * @param xPath
	 * @param value
	 */
	public void setValue(JXPathContext jxPathContext, Object objectContext, String xPath, Object value ) {
		if( logger.isDebugEnabled() ) {
			logger.debug( String.format("jxPath.setValue [xpath=%s, value=%s]", xPath, value ) );
		}
		
		
		try {
			jxPathContext.createPathAndSetValue(xPath, value);
		}
		catch( Exception e ) {
			logger.error(e);
		}
	
	}


	/**
	 * Permite obtener un valor de un objeto a traves de jxPath
	 * @param jxPathContext
	 * @param objectContext
	 * @param xPath
	 * @param value
	 */
	public Object getValue(JXPathContext jxPathContext, Object objectContext, String xPath ) {
		Object localResult;
		try {
			localResult = jxPathContext.getValue(xPath);
	
			if( logger.isDebugEnabled() ) {
				logger.debug( String.format("jxPath.getValue [xpath=%s,value=%s]", xPath, localResult ) );
			}
			return localResult;
		} 
		catch (Exception e) {
			logger.error( String.format("jxPath.getValue [xpath=%s,error=%s]", xPath, e.getMessage() ), e );
			return null;
		}
	
	}

}
