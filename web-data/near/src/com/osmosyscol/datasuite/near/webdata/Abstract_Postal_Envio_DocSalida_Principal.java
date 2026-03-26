
package com.osmosyscol.datasuite.near.webdata;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.osmosyscol.datasuite.near.servicios.radica.LadoLocal;
import com.osmosyscol.datasuite.near.servicios.radica.LadoRemoto;
import com.osmosyscol.datasuite.near.utils.Either;

public abstract class Abstract_Postal_Envio_DocSalida_Principal extends Abstract_Postal_Envio_Principal {
	
	static final Logger logger = LoggerFactory.getLogger(Abstract_Postal_Envio_DocSalida_Principal.class);

	/**
	 * Para punto interaccion auto, no se puede sobrescribir el radicado postal; 
	 * se debe almacenar en otra ubicacion
	 */
	@Override
	Either<Exception, Boolean> mapOutputPayload(Integer id_carga, Either<Exception, Object> serviceResponse) {
		Either<Exception, String> idRadicadoPostal = LadoLocal.getInstance().obtenerRadicado(id_carga, serviceResponse);
	
		logger.info("escribir radicadoPostal en adjunto");
		
		return LadoLocal.getInstance().sync_UsingAdjuntoActivo(id_carga, idRadicadoPostal, getPuntoInteraccion() );		
		
	}

	@Override
	boolean stopIfErrorOnSync() {
		return true;
	}

	@Override
	Either<Exception, Map<String, Object>> mapInputPayload(Integer id_carga) {
		return mapInputPayload_Solicitud(id_carga);
	}
	

}
