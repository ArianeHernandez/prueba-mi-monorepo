package com.osmosyscol.datasuite.near.webdata;

import java.util.Collection;

import com.osmosyscol.datasuite.logica.dto.ArchivoAdjunto;
import com.osmosyscol.datasuite.near.servicios.IPostalInteraccion;
import com.osmosyscol.datasuite.near.servicios.radica.LadoLocal;
import com.osmosyscol.datasuite.near.utils.Either;

public class Postal_RespuestaRequerimiento_Adjuntos extends Abstract_Postal_Envio_Adjuntos {

	@Override
	protected String getPuntoInteraccion() {
		return IPostalInteraccion.PUNTOINTERACCION_RESPUESTA_REQUERIMIENTO;
	}

	@Override
	protected Either<Exception, Collection<ArchivoAdjunto>> ladoLocal_ObtenerAdjuntos(Integer id_carga) {
		return LadoLocal.getInstance().leeAdjuntos( id_carga, true );
	}

	@Override
	protected Either<Exception, String> ladoLocal_BuscarRadicadoPostal(Integer id_carga) {
		return LadoLocal.getInstance().leeRadicadoPostal_UsingRespuestaReq(id_carga);
	}

}
