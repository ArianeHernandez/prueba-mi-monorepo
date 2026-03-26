package com.osmosyscol.datasuite.near.utils;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.htsoft.commons.lang.StringUtils;

import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class ParametrosInicioUtils {
	static final boolean DEFAULT_CAN_BE_EMPTY = true;
	static final Logger logger = LoggerFactory.getLogger(ParametrosInicioUtils.class);
	
	public static Either<Exception,String> $s(String propertyName, boolean canBeEmpty ) {
		String propertyValue =ParametrosInicio.getProperty( propertyName );
		if( StringUtils.isBlank(propertyValue) ) {
			if(!canBeEmpty ) {
				return Either.left(new Exception(String.format("propiedad %s vacia", propertyName )));
			}
			else {
				logger.warn("propiedad {} vacia. skipping", propertyName );
			}
		}
		return Either.right(propertyValue);
	}

	public static Either<Exception,String> $(String propertyName){
		return $(propertyName, DEFAULT_CAN_BE_EMPTY);
	}
	
	public static Either<Exception,String> $(String propertyName, boolean canBeEmpty ){
		return $s(propertyName, canBeEmpty);
	}
	public static Either<Exception,Boolean> $b(String propertyName){
		Either<Exception,String> propertyValue = $s(propertyName,DEFAULT_CAN_BE_EMPTY);
		if( propertyValue.isLeft() ) {
			return Either.left(propertyValue.left());
		}
		Boolean result = Boolean.parseBoolean(propertyValue.right());
		
		Either<Exception,Boolean> eitherResult = Either.right(result);
		return eitherResult ;
	}
	
	
	public static <T> Either<Exception,T> valueOf(Either<Exception,Map<String,Object>> properties,  String key, Class<T> objClass, T replacementWhenEmpty ) {
		if( properties.isLeft()){
			return Either.left(properties.left());
		}
		try {
			
			Object value = properties.right().get(key);
			
			if( String.class.equals(objClass) ) {
				if( null != replacementWhenEmpty && null == value ) value = replacementWhenEmpty;
				//if( blankStringWhenEmpty && null == value ) value = StringUtils.EMPTY;
			} else if( Long.class.equals(objClass) ) {
				value = Long.parseLong((String)value);
			} else if( Integer.class.equals(objClass) ) {
				value = Integer.parseInt((String)value);
			}
			
			return Either.right(objClass.cast(value));
		} catch( Exception e ) {
			return Either.left( new Exception ( String.format("error localizando propiedad %s", key ), e) );
		}
		
	}	
	
	public static <T> T getOrElse(Map<String, Object> payloadSource, String key, Object elseData, Class<T> objClass  ) {
		Object value = (payloadSource.containsKey(key))?(payloadSource.get(key)):(elseData);
		return objClass.cast(value);
	}
	
	
}
