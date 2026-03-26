package com.osmosyscol.datasuite.near.servicios;

import java.util.Collection;

import com.osmosyscol.datasuite.logica.dto.ArchivoAdjunto;
import com.osmosyscol.datasuite.near.servicios.radica.LadoLocal;
import com.osmosyscol.datasuite.near.utils.Either;
import com.osmosyscol.datasuite.near.webdata.Abstract_Postal_Envio_Adjuntos;
import com.osmosyscol.datasuite.webdata.logica.servicios.ParametrosPostalServicio;

public class AccionPublicarAdjuntosCarga extends Abstract_Postal_Envio_Adjuntos {
	@Override
	protected String getPuntoInteraccion() {
		return IPostalInteraccion.PUNTOINTERACCION_RADICACION;
	}
	
	@Override
	protected Either<Exception, Collection<ArchivoAdjunto>> ladoLocal_ObtenerAdjuntos(Integer id_carga) {
		return LadoLocal.getInstance().leeAdjuntos(id_carga, true);
	}

	@Override
	protected Either<Exception, String> ladoLocal_BuscarRadicadoPostal(Integer id_carga) {
		return LadoLocal.getInstance().leeRadicadoPostal_UsingMaster(id_carga);
	}
	

}
