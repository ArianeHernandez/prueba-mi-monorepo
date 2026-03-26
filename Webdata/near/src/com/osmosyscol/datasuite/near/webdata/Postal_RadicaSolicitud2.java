package com.osmosyscol.datasuite.near.webdata;

import java.util.Collection;
import java.util.Map;

import com.osmosyscol.datasuite.logica.dto.ArchivoAdjunto;
import com.osmosyscol.datasuite.near.servicios.AccionPublicarAdjuntosCarga;
import com.osmosyscol.datasuite.near.servicios.IPostalInteraccion;
import com.osmosyscol.datasuite.near.servicios.radica.LadoLocal;
import com.osmosyscol.datasuite.near.servicios.radica.LadoRemoto;
import com.osmosyscol.datasuite.near.servicios.radica.LadoRemoto2;
import com.osmosyscol.datasuite.near.utils.Either;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Postal_RadicaSolicitud2 extends Abstract_Postal_Envio_Principal {
	
	static final Logger logger = LoggerFactory.getLogger(Postal_RadicaSolicitud2.class);
	@Override
	String getPuntoInteraccion() {
		return IPostalInteraccion.PUNTOINTERACCION_RADICACION;
	}
	
	@Override
	boolean stopIfErrorOnSync() {
		return true;
	}
	
	@Override
	public Either<Exception, Boolean> mapOutputPayload(Integer id_carga, Either<Exception, Object> serviceResponse) {
	    if (serviceResponse.isLeft()) {
	        return Either.left(serviceResponse.left());
	    }
	    Either<Exception, String> idRadicadoPostal = LadoLocal.getInstance().obtenerRadicado(id_carga, serviceResponse.right());
	    if (idRadicadoPostal.isLeft()) {
	        return Either.left(idRadicadoPostal.left());
	    }
	    return LadoLocal.getInstance().sync_UsingMaster(id_carga, idRadicadoPostal);
	}
	
	@Override
	Either<Exception, Map<String, Object>> mapInputPayload(Integer id_carga) {
		return mapInputPayload_Solicitud(id_carga);
	}

	@Override
	Either<Exception, Object> ladoRemoto_SyncMaster(Integer id_carga,	Either<Exception, Map<String, Object>> payloadSource) {
		return LadoRemoto2.getInstance().sync_Master(id_carga,payloadSource.right());
	}
	
	/**
	 * @deprecated usar {@link AccionPublicarAdjuntosCarga} en su lugar para adjutnar archivos
	 * @return
	 */
	@Deprecated()
	public Either<Exception,Boolean> ejecutar_PipelineAdjuntos(Integer id_carga) {
		// A: SOURCE: EXTRACCION SOLICITUD CPONLINE
		Either<Exception,Collection<ArchivoAdjunto>> adjuntoList = LadoLocal.getInstance().leeAdjuntos(id_carga, true);
		
		// adjuntoList.stream
		if( !CollectionUtils.isEmpty(adjuntoList.right()) ){
			
			// lanzar el envio de los documentos
			// el primmero que se envia se lanza comp rincipal y el resto secundarios
			int counter = 0;

			final Long TIPO_DOCUMENTO_PRINCIPAL = 4L;
			final Long TIPO_DOCUMENTO_ANEXO = 5L;
			
			// foreach
			for( ArchivoAdjunto adjunto: adjuntoList.right() ){
				Either<Exception, Boolean> result;
				if(0 == counter ) {
					result = LadoRemoto2.getInstance().sync_Adjunto(id_carga, adjunto, TIPO_DOCUMENTO_PRINCIPAL);
				} else {
					result = LadoRemoto2.getInstance().sync_Adjunto(id_carga, adjunto, TIPO_DOCUMENTO_ANEXO);
				}
				 
				// se corta ejecucion si algun attachment falla 
				
				if( result.isLeft() ) {
					return result;
				}
				counter ++;
			}
		}
		
		return Either.right(true);
	}



}
