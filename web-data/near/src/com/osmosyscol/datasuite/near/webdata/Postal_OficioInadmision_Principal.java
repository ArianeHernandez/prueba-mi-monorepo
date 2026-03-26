package com.osmosyscol.datasuite.near.webdata;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.osmosyscol.datasuite.near.servicios.IPostalInteraccion;
import com.osmosyscol.datasuite.near.servicios.radica.LadoRemoto;
import com.osmosyscol.datasuite.near.servicios.radica.LadoRemoto2;
import com.osmosyscol.datasuite.near.utils.Either;

public class Postal_OficioInadmision_Principal extends Abstract_Postal_Envio_DocSalida_Principal {
	
	static final Logger logger = LoggerFactory.getLogger(Postal_OficioInadmision_Principal.class);

	@Override
	String getPuntoInteraccion() {
		return IPostalInteraccion.PUNTOINTERACCION_OFICIOINADMISION;
	}
	
	@Override
	Either<Exception, Object> ladoRemoto_SyncMaster(Integer id_carga,	Either<Exception, Map<String, Object>> payloadSource) {
		return LadoRemoto2.getInstance().sync_Master(id_carga,payloadSource.right());
	}
}
