package com.osmosyscol.datasuite.near.webdata;

//import org.apache.commons.lang3.SerializationUtils;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.osmosyscol.datasuite.mein.dtos.RespuestaRequerimiento;
import com.osmosyscol.datasuite.mein.dtos.SolicitudNearSociedad;
import com.osmosyscol.datasuite.mein.servicios.RespuestaRequerimientoServicio;
import com.osmosyscol.datasuite.near.servicios.IPostalInteraccion;
import com.osmosyscol.datasuite.near.servicios.InfoRespuestaRequerimientoLocalServicio;
import com.osmosyscol.datasuite.near.servicios.radica.LadoLocal;
import com.osmosyscol.datasuite.near.servicios.radica.LadoRemoto;
import com.osmosyscol.datasuite.near.servicios.radica.LadoRemoto2;
import com.osmosyscol.datasuite.near.utils.Either;

public class Postal_RespuestaRequerimiento_Principal extends Abstract_Postal_Envio_Principal {

	static final Logger logger = LoggerFactory.getLogger(Postal_RespuestaRequerimiento_Principal.class);
	
	@Override
	String getPuntoInteraccion() {
		return IPostalInteraccion.PUNTOINTERACCION_RESPUESTA_REQUERIMIENTO;
	}

	@Override
	boolean stopIfErrorOnSync() {
		return true;
	}

	@Override
	Either<Exception, Boolean> mapOutputPayload(Integer id_carga, Either<Exception, Object> serviceResponse) {
	    // Obtener el resultado de idRadicadoPostal
	    Either<Exception, String> idRadicadoPostal = LadoLocal.getInstance().obtenerRadicado(id_carga, serviceResponse.right());

	    // Verificar si idRadicadoPostal fue exitoso
	    if (idRadicadoPostal.isRight()) {
	        // Si idRadicadoPostal es exitoso, proceder con la sincronización
	        Either<Exception, Boolean> syncResult = LadoLocal.getInstance().sync_UsingRespuestaReq(id_carga, idRadicadoPostal);

	        // Devolver el resultado de la sincronización (true o false en función del resultado)
	        return syncResult;
	    } else {
	        // Si idRadicadoPostal devuelve una excepción, devolver Either con el lado izquierdo (la excepción)
	        return Either.left(idRadicadoPostal.left());
	    }
	}
	
	@Override
	Either<Exception, Map<String, Object>> mapInputPayload( Integer id_carga) {
		return mapInputPayload_RespuestaRequerimiento(id_carga);
	}
	
	@Override
	Either<Exception, Object> ladoRemoto_SyncMaster(Integer id_carga,	Either<Exception, Map<String, Object>> payloadSource) {
		return LadoRemoto2.getInstance().sync_Master(id_carga,payloadSource.right());
	}
	
	/*
	// IGUAL
	public SMessage ejecutar(Integer id_carga) {
		Either<Exception,Boolean> master_Result = ejecutar_Pipeline(id_carga);
		
		if( master_Result.isLeft() ) {
			String message = String.format("PipelineSolicitud / sincronizar (error) : %s" , master_Result.left().getMessage());
			logger.warn(message);
			return new SMessage(false, message );
		} 
		
		String messageDetail = "Operacion Exitosa" ;
		logger.info ("PipelineAdjuntos (end) {}", messageDetail);
		return new SMessage(master_Result.right(), messageDetail);
		
		
	}
	
	// IGUAL
	public Either<Exception,Boolean> ejecutar_Pipeline(Integer id_carga) {
		
		// leer info local
		
		Either<Exception, Map<String, Object>> payloadSource = mapInputPayload(id_carga);
		
		if( payloadSource.isLeft() ) {
			return Either.left( payloadSource.left() ); 
		}
		
		// sincronizar solicitud remota: (lanza peticion a postal)
		Either<Exception,String> idRadicadoPostal = ladoRemoto_SyncMaster(id_carga, payloadSource.right());

		// sincronizar solicitud local
		return mapOutputPayload(id_carga,idRadicadoPostal );

	}

	
	// IGUAL
	private Either<Exception, Long> ladoLocal_LeeAdjuntosCount(Integer id_carga) {

		logger.info("consultando adjuntos.count [id_carga={}] ", id_carga );
		Long result = LadoLocal.getInstance().leeAdjuntos_Count(id_carga);
		if( null == result) {
			String message = String.format("no se encuentra info adjuntos con id: %s" , id_carga );
			logger.warn(message);
			return Either.left(new Exception(message));
		}
		return Either.right(result);

	}

	// IGUAL
	private Either<Exception, String> ladoRemoto_SyncMaster(Integer id_carga,
			Map<String,Object> payloadSource ) {
		return LadoRemoto.getInstance().sync_Master(id_carga, payloadSource);
	}
	
	// IGUAL
	public Boolean visualizar(Integer id_carga, Integer id_administrativo) {
		return VISUALIZAR_DEFAULT_RESPONSE;
	}
	
	*/
	

	private Either<Exception, SolicitudNearSociedad> ladoLocal_LeeSolicitudLinked( Integer id_carga_solicitud, Integer id_carga_respuestareq ) {
		logger.info("consultando link [actual={},target={}] ", id_carga_respuestareq, id_carga_solicitud );
		SolicitudNearSociedad result = LadoLocal.getInstance().leeMaster(id_carga_solicitud);
		if( null == result) {
			String message = String.format("no se encuentra solicitud con id: %s" , id_carga_solicitud);
			logger.warn(message);
			return Either.left(new Exception(message));
		}
		return Either.right(result);
	}

	private Either<Exception, RespuestaRequerimiento> ladoLocal_LeeRespuestaReq(Integer id_carga) {
		RespuestaRequerimiento result = RespuestaRequerimientoServicio.getInstance().obtenerRespuestaRequerimientoCompleto(id_carga);
		if( null == result) {
			String message = String.format("no se encuentra requerimiento con id: %s" , id_carga);
			logger.warn(message);
			return Either.left(new Exception(message));
		}
		return Either.right(result);
	}
	
	private Either<Exception, RespuestaRequerimiento> ladoLocal_LeeSolicitudPadre( Integer idCargaPadre) {
		logger.info("consultando padre [actual={}] ", idCargaPadre);
		RespuestaRequerimiento result = RespuestaRequerimientoServicio.getInstance().obtenerInformacionSolicitudPadre(idCargaPadre);
		if( null == result) {
			String message = String.format("no se encuentra solicitud padre con id: %s" , idCargaPadre);
			logger.warn(message);
			return Either.left(new Exception(message));
		}
		return Either.right(result);
	}

	
	

	private Either<Exception, Map<String, Object>> mapInputPayload_RespuestaRequerimiento( Integer id_carga) {
		// a. leer solicitud preliminar
		Either<Exception,RespuestaRequerimiento> infoSource = ladoLocal_LeeRespuestaReq( id_carga);

		if( infoSource.isLeft() ) {
			return Either.left( infoSource.left() ); 
		}
		
		// b. leer solicitud enlazada
		Either<Exception,RespuestaRequerimiento> infoLinked = ladoLocal_LeeSolicitudPadre( infoSource.right().getNumero_solicitud());
		
		if( infoLinked.isLeft() ) {
			return Either.left( infoLinked.left() ); 
		}
		
		Either<Exception, Long> adjuntosCount = ladoLocal_LeeAdjuntosCount(id_carga);
		
		
		// B: TRANSFORM: SE LANZA PETICION HACIA SERVICIO POSTAL
		
		Map<String,Object> payloadSource = new HashMap<String,Object>(); // Stream.of
		
		payloadSource.put(IPostalInteraccion.PARAM_PUNTOINTERACCION, IPostalInteraccion.PUNTOINTERACCION_RESPUESTA_REQUERIMIENTO);
		payloadSource.put(IPostalInteraccion.PARAM_CORRELATION_ID1, infoSource.right().getIdcarga() );
		payloadSource.put(IPostalInteraccion.PARAM_SOLICITUD, (Object)(infoSource.right()));
//		payloadSource.put(IPostalInteraccion.PARAM_RADICACIONANTERIORNUMERO, infoLinked.right().getNumero_radicado_postal() );
		payloadSource.put(IPostalInteraccion.PARAM_SOLICITUD_PADRE, (Object) infoLinked.right() );
		
		payloadSource.put(IPostalInteraccion.PARAM_COUNT_ADJUNTOS, adjuntosCount.right());
		
		return Either.right(payloadSource);
	}

}
