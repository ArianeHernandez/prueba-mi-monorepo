package com.osmosyscol.datasuite.near.webdata;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.htsoft.commons.util.SMessage;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.ArchivoAdjunto;
import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.near.interop.postal.adjuntos.PostalAdjuntoOut;
import com.osmosyscol.datasuite.near.servicios.IPostalConfig;
import com.osmosyscol.datasuite.near.servicios.IPostalInteraccion;
import com.osmosyscol.datasuite.near.servicios.InfoRadicadoLocalServicio;
import com.osmosyscol.datasuite.near.servicios.PostalAdjuntosRestClient;
import com.osmosyscol.datasuite.near.utils.Either;

public abstract class Abstract_Postal_Envio_Adjuntos implements AccionCarga {
	
	final SMessage EJECUTAR_WHENOK_RESPONSE = new SMessage(true, "OK");;
	final SMessage EJECUTAR_WHENERR_NO_ROWS = new SMessage(true, "Sin adjuntos a sincronizar");  // o deberia fallar?
	final SMessage EJECUTAR_WHENERR_NO_RESPONSE_FROM_SERVER = new SMessage(false, "Sin respuesta del servicio");
	

	static final Logger logger = LoggerFactory.getLogger(Abstract_Postal_Envio_Adjuntos.class);

	public SMessage ejecutar(Integer id_carga) {
		Either<Exception,Boolean> master_Result = ejecutar_Pipeline(id_carga);
		
		if( master_Result.isLeft() ) {
			String message = String.format("PipelineSolicitud Adjunto / sincronizar (error) : %s" , master_Result.left().getMessage());
			logger.warn(message);
			return new SMessage(false, message );
		} 

		String messageDetail = "Operacion Ejecutada" ;
		logger.info ("PipelineAdjuntosl (end) {}", messageDetail);
		return new SMessage(master_Result.right(), messageDetail);

	}
	
	/**
	 * TEMPLATE-METHOD para cargas postal / adjuntos 
	 * @param id_carga
	 * @return
	 */
	Either<Exception,Boolean> ejecutar_Pipeline(Integer id_carga) {
		
		// leer objeto local
		Either<Exception,Map<String,Object>> payloadSource = mapInputPayload(id_carga);

		if( payloadSource.isLeft() ) {
			return Either.left( payloadSource.left() ); 
		}
		
		// lanza peticion a server
		Either<Exception, PostalAdjuntoOut> localResult = ladoRemoto_SyncAdjuntos( payloadSource); //id_carga, estructura, adjuntos, radicadoPostal);
		
		if(localResult.isLeft()) {
			SimpleLogger.setError("Error Postal_RespuestaRequerimiento_Adjuntos", localResult.left());
			return Either.left(localResult.left());
		}
		// se haria el sink local, pero en este caso se ignora el resutlado
		return mapOutputPayload(id_carga, localResult);
	}

	/**
	 * Se sincronizaria la respuesta que entrega el servidor
	 * @param localResult 
	 * @param id_carga 
	 * @return
	 */
	protected Either<Exception, Boolean> mapOutputPayload(Integer id_carga, Either<Exception, PostalAdjuntoOut> localResult) {
		return Either.right(true);
	}

	/**
	 * Lanza la sincronizacion al lado remoto (a traves de servicio expuesto postal)
	 * Notese que en este caso el lador emoto es el componetne IntegracionPostal (y no es postal el que responde directamente)
	 * @param payloadSource
	 * @return
	 */
	private Either<Exception, PostalAdjuntoOut> ladoRemoto_SyncAdjuntos( Either<Exception, Map<String, Object>> payloadSource) {
		return PostalAdjuntosRestClient.getInstance().invocar(payloadSource.right()); //id_carga, estructura, adjuntos, radicadoPostal);
	}
	
	/**
	 * Crear el payload para la invocacion
	 * @param id_carga
	 * @return
	 */
	protected Either<Exception, Map<String, Object>> mapInputPayload(Integer id_carga) {
		
		String estructura = InfoRadicadoLocalServicio.getInstance().obtenerNombreEstructuraFormatoPorCarga(id_carga);
		
		Either<Exception, Collection<ArchivoAdjunto>> adjuntos = ladoLocal_ObtenerAdjuntos(id_carga);
		if( adjuntos.isLeft()) {
			return Either.left(adjuntos.left());
		}

		Either<Exception, String> radicadoPostal = ladoLocal_BuscarRadicadoPostal(id_carga); 
		if( radicadoPostal.isLeft()) {
			return Either.left(radicadoPostal.left());
		}
		
		//if( null == adjuntos || adjuntos.size() == 0) {
		//	return Either.right( true ); // deberia fallar si no tiene adjuntos?
		//}
		
		// B.1: TRANSFORM: SE crea paoylaod entrada a transformar/enviar
		
		Map<String,Object> payload = new HashMap<String,Object>(); // Stream.of
		payload.put(IPostalInteraccion.PARAM_ADJUNTOS_CORRELATION_ID1, id_carga);
		payload.put(IPostalInteraccion.PARAM_ADJUNTOS_ESTRUCTURA, estructura);
		payload.put(IPostalInteraccion.PARAM_ADJUNTOS_LIST, adjuntos.right());
		payload.put(IPostalInteraccion.PARAM_ADJUNTOS_RADICADO_POSTAL, radicadoPostal.right());
		
		return Either.right(payload);
		
	}

	/**
	 * Forma de localizar el radicado que la acción {@link Abstract_Postal_Envio_Principal} ha realizado previamente
	 * @param id_carga
	 * @return
	 */
	abstract protected Either<Exception, String> ladoLocal_BuscarRadicadoPostal(Integer id_carga);
	
	/*
	{
		return EstructuraOwner.getInstance().buscarRadicadoPostal_byEstructura(id_carga, estructura );
	}
	*/

	/**
	 * Forma de definir la forma de traer adjuntos que se van a sinconizar
	 * @param id_carga
	 * @return
	 */
	protected abstract Either<Exception, Collection<ArchivoAdjunto>> ladoLocal_ObtenerAdjuntos(Integer id_carga);

	//private abstract String getPuntoInteraccion_Entidad();
	
	public Boolean visualizar(Integer id_carga, Integer id_administrativo) {
		return true;
	}

	protected abstract String getPuntoInteraccion();
	
	protected Integer getTipoArchivoOfPuntoInteraccion() {
		return IPostalConfig.getInstance(getPuntoInteraccion()).getTipoArchivoOfPuntoInteraccion().right();
	}
	
}
