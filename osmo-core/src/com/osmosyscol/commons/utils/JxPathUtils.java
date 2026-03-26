package com.osmosyscol.commons.utils;


import java.util.Map;

import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

			if( null == path ) {
				// logger.warn(String.format( "invalid size of array to add the default parameter [path=%s,key=%s]", path, key ));
				path = key;
			}

			value = getValue(jxPathContext,objectContext,path );
			setValue( localResult, path, value, key );	
		}
	}
	
	/**
	 * Evalua serie de propiedades dentro de un mapa, con el fin de llenar llena el objeto con dichos valores
	 * @param jxPathContext
	 * @param objectContext
	 * @param localResult
	 * @param propertyMapRules (Array con [0]: path, [1]: key, [2] valorEstatico si key es null )
	 */
	public void copyPropertiesFromMap( JXPathContext jxPathContext, Object objectContext, Map<String,Object> localResult, Object[][] propertyMapRules  ) {
		String path;
		String key;
		Object value;

		for( Object[] element: propertyMapRules ) {
			path = (String)element[0];
			key = (String)element[1];
			
			if( null == key ) {
				// logger.warn(String.format( "invalid size of array to add the default parameter [path=%s,key=%s]", path, key ));
				value = element[2];
			}
			else {
				value = getValue(localResult,path, key );
			}

			setValue( jxPathContext, objectContext, path, value );	
		}
	}	
	
	/**
	 * Adiciona un valor a un map
	 * @param map
	 * @param path
	 * @param value
	 * @param key
	 */
	public void setValue(Map<String, Object> map, String path,	Object value, String key) {
		map.put(key, value);
	}	
	
	/**
	 * Obtiene un valor de un map
	 * @param map
	 * @param path
	 * @param value
	 * @param key
	 */
	public Object getValue(Map<String, Object> map, String path, String key) {
		return map.get(key);
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
