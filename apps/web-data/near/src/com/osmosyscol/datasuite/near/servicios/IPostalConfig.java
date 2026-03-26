package com.osmosyscol.datasuite.near.servicios;

import static com.osmosyscol.datasuite.near.utils.ParametrosInicioUtils.$;

import java.util.HashMap;
import java.util.Map;

import com.osmosyscol.datasuite.near.utils.Either;
import com.osmosyscol.datasuite.near.utils.ParametrosInicioUtils;

public class IPostalConfig {
	
	public static Map<String,IPostalConfig> _cache = new HashMap<String,IPostalConfig>();
	
	public static IPostalConfig getInstance(String key) {
		if(_cache.containsKey(key)) {
		} else {
			_cache.put(key, new IPostalConfig(key));
		}
		return _cache.get(key);
	}
	
	private String puntoInteraccion;
	private Either<Exception,Map<String,Object>> _data; 
	
	private IPostalConfig(String puntoInteraccion) {
		this.puntoInteraccion = puntoInteraccion;
		this._data = readPuntoInteraccionProperties(puntoInteraccion);
		
	}
	
	public boolean isLeft() {
		return _data.isLeft();
	}
	
	public Exception left() {
		return _data.left();
	}
	
	
	public Either<Exception, String> getEntidadOfPuntoInteraccion() {
		
		return this.valueOf( IPostalInteraccion.SVC_INTERNO_ENTIDAD, String.class );
	}
	
	public Either<Exception, Integer> getTipoArchivoOfPuntoInteraccion() {
		return this.valueOf( IPostalInteraccion.SVC_INTERNO_TIPOARCHIVO, Integer.class );
	}
	
	/*
	static String getEntidadOfPuntoInteraccion( String puntoInteraccion ) {
		if( StringUtils.equals(IPostalInteraccion.PUNTOINTERACCION_RADICACION, puntoInteraccion)) {
			return IPostalInteraccion.ENTIDAD_RADICACION;
		} else if( StringUtils.equals(IPostalInteraccion.PUNTOINTERACCION_RESPUESTA_REQUERIMIENTO, puntoInteraccion)) {
			return IPostalInteraccion.ENTIDAD_RESPUESTA_REQUERIMIENTO;
		} else if( StringUtils.equals(IPostalInteraccion.PUNTOINTERACCION_AUTOADMISION, puntoInteraccion)) {
			return IPostalInteraccion.ENTIDAD_RADICACION;
		}
		return null;
	}
	static Integer getTipoArchivoOfPuntoInteraccion(	String puntoInteraccion) {
		if( StringUtils.equals(IPostalInteraccion.PUNTOINTERACCION_RADICACION, puntoInteraccion)) {
			return IPostalInteraccion.TIPOARCHIVO_RADICACION;
		} else if( StringUtils.equals(IPostalInteraccion.PUNTOINTERACCION_RESPUESTA_REQUERIMIENTO, puntoInteraccion)) {
			return IPostalInteraccion.TIPOARCHIVO_RESPUESTA_REQUERIMIENTO;
		} else if( StringUtils.equals(IPostalInteraccion.PUNTOINTERACCION_AUTOADMISION, puntoInteraccion)) {
			return IPostalInteraccion.TIPOARCHIVO_AUTOADMISION;
		}
		return null;
	}	
	*/
	
	private static String puntoInteraccion_BuildKey( String puntoInteraccion, String key) {
		return String.format("%s.%s", puntoInteraccion, key);
	}
	
	private static <T> Either<Exception,T> valueOf(Either<Exception,Map<String,Object>> properties, String puntoInteraccion, String key, Class<T> objClass, T replacementWhenEmpty ) {
		
		String keyToSearch = puntoInteraccion_BuildKey(puntoInteraccion, key);
		return ParametrosInicioUtils.valueOf(properties, keyToSearch, objClass, replacementWhenEmpty);
	}
	
	
	private static Either<Exception,Map<String,Object>> readPuntoInteraccionProperties(String puntoInteraccion) {
		// Extraer propiedades parametricas asociadas al punto de interaccion
		String[] keyList = new String[] {
			puntoInteraccion_BuildKey(puntoInteraccion, IPostalInteraccion.SVC_TIPO),
			puntoInteraccion_BuildKey(puntoInteraccion, IPostalInteraccion.SVC_FUNCIONARIO_ID),
			puntoInteraccion_BuildKey(puntoInteraccion, IPostalInteraccion.SVC_FUNCIONARIO_NOMBRE),
			puntoInteraccion_BuildKey(puntoInteraccion, IPostalInteraccion.SVC_TIPODOCUMENTAL_CODIGO),
			puntoInteraccion_BuildKey(puntoInteraccion, IPostalInteraccion.SVC_TIPODOCUMENTAL_ID),
			puntoInteraccion_BuildKey(puntoInteraccion, IPostalInteraccion.SVC_TIPODOCUMENTAL_NOMBRE),
			puntoInteraccion_BuildKey(puntoInteraccion, IPostalInteraccion.SVC_MEDIODEENVIO_CODIGO),
			puntoInteraccion_BuildKey(puntoInteraccion, IPostalInteraccion.SVC_MEDIODEENVIO_ID),
			puntoInteraccion_BuildKey(puntoInteraccion, IPostalInteraccion.SVC_MEDIODEENVIO_NOMBRE),
			puntoInteraccion_BuildKey(puntoInteraccion, IPostalInteraccion.SVC_TERMINODIAS),
			puntoInteraccion_BuildKey(puntoInteraccion, IPostalInteraccion.SVC_TRAMITE_CODIGO),
			puntoInteraccion_BuildKey(puntoInteraccion, IPostalInteraccion.SVC_TRAMITE_CODIGO__NEAR),
			puntoInteraccion_BuildKey(puntoInteraccion, IPostalInteraccion.SVC_TRAMITE_NOMBRE__NEAR),
			puntoInteraccion_BuildKey(puntoInteraccion, IPostalInteraccion.SVC_TRAMITE_ID),
			puntoInteraccion_BuildKey(puntoInteraccion, IPostalInteraccion.SVC_TRAMITE_ID__NEAR),
			puntoInteraccion_BuildKey(puntoInteraccion, IPostalInteraccion.SVC_TRAMITE_NOMBRE),
			puntoInteraccion_BuildKey(puntoInteraccion, IPostalInteraccion.SVC_INTERNO_ENTIDAD),
			puntoInteraccion_BuildKey(puntoInteraccion, IPostalInteraccion.SVC_INTERNO_TIPOARCHIVO),
			puntoInteraccion_BuildKey(puntoInteraccion, IPostalInteraccion.SVC_TIPOSEGURIDAD_CODIGO),
			puntoInteraccion_BuildKey(puntoInteraccion, IPostalInteraccion.SVC_TIPOSEGURIDAD_ID),
			puntoInteraccion_BuildKey(puntoInteraccion, IPostalInteraccion.SVC_TIPOSEGURIDAD_NOMBRE),
			puntoInteraccion_BuildKey(puntoInteraccion, IPostalInteraccion.SVC_DOCUMENTO_TIPOSEGURIDAD_ID),
			puntoInteraccion_BuildKey(puntoInteraccion, IPostalInteraccion.SVC_DOCUMENTO_TIPOSEGURIDAD_CODIGO),			
			puntoInteraccion_BuildKey(puntoInteraccion, IPostalInteraccion.SVC_DOCUMENTO_TIPOSEGURIDAD_NOMBRE),
			puntoInteraccion_BuildKey(puntoInteraccion, IPostalInteraccion.SVC_DEPENDENCIA_ID),
			puntoInteraccion_BuildKey(puntoInteraccion, IPostalInteraccion.SVC_DEPENDENCIA_NOMBRE),
			puntoInteraccion_BuildKey(puntoInteraccion, IPostalInteraccion.SVC_DEPENDENCIA_ASIGNADA_ID),
			puntoInteraccion_BuildKey(puntoInteraccion, IPostalInteraccion.SVC_DEPENDENCIA_ASIGNADA_NOMBRE),
			puntoInteraccion_BuildKey(puntoInteraccion, IPostalInteraccion.SVC_CORRESPONSAL_DEPENDENCIA_ID),
			puntoInteraccion_BuildKey(puntoInteraccion, IPostalInteraccion.SVC_CORRESPONSAL_DEPENDENCIA_NOMBRE),
			puntoInteraccion_BuildKey(puntoInteraccion, IPostalInteraccion.SVC_TRAMITE_PROCESO_CODIGO__LSIMPLIFICADA),
			puntoInteraccion_BuildKey(puntoInteraccion, IPostalInteraccion.SVC_TRAMITE_PROCESO_CODIGO__NEAR),
			puntoInteraccion_BuildKey(puntoInteraccion, IPostalInteraccion.SVC_TRAMITE_PROCESO_ID__NEAR),
			puntoInteraccion_BuildKey(puntoInteraccion, IPostalInteraccion.SVC_TRAMITE_PROCESO_CODIGO__RABREVIADA),
			
			
			
			
		};
		
		try {
			Map<String,Object> result = new HashMap<String,Object>();
			for(String key:keyList) {
				result.put(key, $(key).right());
			}
			return Either.right(result);
		}
		catch(Exception e ){
			return Either.left(e);
		}
		
	}

	public <T> Either<Exception, T> valueOf(String key, Class<T> clazz, T replacementWhenEmpty ) {
		return valueOf(this._data, this.puntoInteraccion, key, clazz, replacementWhenEmpty );
	}
	public <T> Either<Exception, T> valueOf(String key, Class<T> clazz ) {
		return this.valueOf(key, clazz, null );
	}
	

}
