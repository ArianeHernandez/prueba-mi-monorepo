package com.osmosyscol.datasuite.near.webdata;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.htsoft.commons.util.SMessage;

import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.mein.logica.constantes.Constantes;
import com.osmosyscol.datasuite.near.servicios.IPostalInteraccion;
import com.osmosyscol.datasuite.near.servicios.InfoRadicadoLocalServicio;
import com.osmosyscol.datasuite.near.servicios.radica.LadoLocal;
import com.osmosyscol.datasuite.near.utils.Either;

public abstract class Abstract_Postal_Envio_Principal implements AccionCarga {

	static final Logger logger = LoggerFactory.getLogger(Abstract_Postal_Envio_Principal.class);

	final Boolean VISUALIZAR_DEFAULT_RESPONSE = true;
	final SMessage EJECUTAR_WHENOK_RESPONSE = new SMessage(true, "OK");;
	final SMessage EJECUTAR_WHENERR_INVOCATIONEXC = new SMessage(false, "");
	
	public Abstract_Postal_Envio_Principal() {
		super();
	}

	public SMessage ejecutar(Integer id_carga) {
		
		Either<Exception,Boolean> master_Result = ejecutar_Pipeline(id_carga);
		logger.warn("Is left: {}", master_Result.isLeft());
		if( master_Result.isLeft() ) {
			logger.warn("Error");
			return handleReturn_WhenErr(master_Result);
		} else {
			logger.warn("Exito");
			return handleReturn_WhenOk(master_Result);
		}
		
		
	}

	protected SMessage handleReturn_WhenOk(	Either<Exception, Boolean> master_Result) {
		String messageDetail = "Operacion Exitosa" ;
		logger.info ("PipelinePrincipal (end) {}", messageDetail);
		return new SMessage(true, messageDetail);
	}

	protected SMessage handleReturn_WhenErr( Either<Exception, Boolean> master_Result) {
		String message = String.format("PipelineSolicitud / sincronizar (error) : %s" , master_Result.left().getMessage());
		logger.warn(message);
		return new SMessage(true, message ); // se solicita se escriba "true" siempre
	}
	
	/**
	 * Identificador del punto de interaccion con postal
	 * @return
	 */
	abstract String getPuntoInteraccion();
	
	/**
	 * Identifica si luego de un error detectado en lal sincronizacion
	 * debe continuar o lanzar el error
	 * @return
	 */
	abstract boolean stopIfErrorOnSync();

	/**
	 * TEMPLATE-METHOD para cargas postal / objeto master (sincronizaacion del objeto a radicar) 
	 * @param id_carga
	 * @return
	 */
	public Either<Exception, Boolean> ejecutar_Pipeline(Integer id_carga) {
	    // A: LEER INFO LOCAL
	    Either<Exception, Map<String, Object>> payloadSource = mapInputPayload(id_carga);
	    
	    // Verificar si ocurrió un error en la lectura de la información local
	    if (payloadSource.isLeft()) {
	        return Either.left(payloadSource.left()); // Retorna el error
	    }

	    // B.2: TRANSFORMAR: SE LANZA PETICIÓN HACIA SERVICIO POSTAL (sincronizar solicitud remota)
	    Either<Exception, Object> serviceResponse = ladoRemoto_SyncMaster(id_carga, payloadSource);
	    
	    // Verificar si se produjo un error en la respuesta del servicio postal
	    if (stopIfErrorOnSync() && serviceResponse.isLeft()) {
	        logger.error("Error en respuesta servicio Postal: {}", serviceResponse.left()); // Log de error
	        return Either.left(serviceResponse.left()); // Detener el flujo y devolver el error
	    }

	    // C: SINK: SE CARGA A CPONLINE NUEVA
	    Either<Exception, Boolean> result = mapOutputPayload(id_carga, serviceResponse);

	    // Si la sincronización con CPONLINE también falla, devolver el error
	    if (result.isLeft()) {
	        logger.error("Error en mapOutputPayload: {}", result.left());
	        return Either.left(result.left()); // Detener y devolver el error
	    }

	    // Si todo salió bien, devolver éxito
	    return Either.right(true);
	}

	
	/**
	 * Sincroniza objeto principal, enviando las peticiones hacia postal 
	 * a traves de la interfaz
	 * 
	 * @param id_carga
	 * @param payloadSource
	 * @return
	 */
	abstract Either<Exception, Object> ladoRemoto_SyncMaster(Integer id_carga,	Either<Exception, Map<String, Object>> payloadSource);
	
	/**
	 * Sincronizar objetos locales
	 * @param id_carga
	 * @param idRadicadoPostal
	 * @return
	 */
	abstract Either<Exception, Boolean> mapOutputPayload(Integer id_carga, Either<Exception, Object> idRadicadoPostal);

	/**
	 * A partir de los datos locales, 
	 * crear los payloads del servicio en memoria
	 * para enviar a postal
	 * 
	 * @param id_carga
	 * @return
	 */
	abstract Either<Exception, Map<String, Object>> mapInputPayload(Integer id_carga);

	private Either<Exception, Object> ladoLocal_LeeSolicitud(Integer id_carga) {

		Object result = null;
		String estructura = Objects.toString(InfoRadicadoLocalServicio.getInstance().obtenerNombreEstructuraFormatoPorCarga(id_carga), "");
		
		switch(estructura){
			case Constantes.ESTRUCTURA_NEAR_SOCIEDAD: 
				result =  (Object) LadoLocal.getInstance().leeMaster(id_carga);
				break;
				
			case Constantes.ESTRUCTURA_REGIMEN_INSOLVENCIA:
				result = (Object) LadoLocal.getInstance().leeMasterRegimenInsolvencia(id_carga);
				break;
		}
		
		if( null == result) {
			String message = String.format("no se encuentra requerimiento con id: %s" , id_carga);
			logger.warn(message);
			return Either.left(new Exception(message));
		}
		return Either.right(result);
	}


	/**
	 * Contar los ajduntos asociados a una solicitud
	 * @param id_carga
	 * @return
	 */
	public Either<Exception, Long> ladoLocal_LeeAdjuntosCount(Integer id_carga) {

		logger.info("consultando adjuntos.count [id_carga={}] ", id_carga );
		Long result = LadoLocal.getInstance().leeAdjuntos_Count(id_carga);
		if( null == result) {
			String message = String.format("no se encuentra info adjuntos con id: %s" , id_carga );
			logger.warn(message);
			return Either.left(new Exception(message));
		}
		return Either.right(result);

	}
	
	public Boolean visualizar(Integer id_carga, Integer id_administrativo) {
		return VISUALIZAR_DEFAULT_RESPONSE;
	}
	
	protected Either<Exception, Map<String, Object>> mapInputPayload_Solicitud(Integer id_carga) {
		// A: SOURCE: EXTRACCION SOLICITUD CPONLINE
		//Obteniendo la informacion de las estructuras segun corresponda
		Either<Exception,Object> infoSource = ladoLocal_LeeSolicitud(id_carga);
		
		if( infoSource.isLeft() ) {
			return Either.left( infoSource.left() ); 
		}
		
		Either<Exception, Long> adjuntosCount = ladoLocal_LeeAdjuntosCount(id_carga);
		
		
		// B.1: TRANSFORM: SE crea paoylaod entrada a transformar/enviar
		
		Map<String,Object> payload = new HashMap<String,Object>(); // Stream.of
		
		payload.put(IPostalInteraccion.PARAM_PUNTOINTERACCION, getPuntoInteraccion());
		payload.put(IPostalInteraccion.PARAM_CORRELATION_ID1, id_carga);
		//datos de la estructura
		payload.put(IPostalInteraccion.PARAM_SOLICITUD, infoSource.right() );
		payload.put(IPostalInteraccion.PARAM_COUNT_ADJUNTOS, adjuntosCount.right());
		return Either.right(payload);
	}
	
	

}
