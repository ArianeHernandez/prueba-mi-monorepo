package com.osmosyscol.datasuite.near.webdata;

import java.util.Collection;

import com.osmosyscol.datasuite.logica.dto.ArchivoAdjunto;
import com.osmosyscol.datasuite.near.servicios.radica.LadoLocal;
import com.osmosyscol.datasuite.near.utils.Either;

public abstract class Abstract_Postal_Envio_DocSalida_Adjuntos extends Abstract_Postal_Envio_Adjuntos {
	
	@Override
	protected Either<Exception, String> ladoLocal_BuscarRadicadoPostal(Integer id_carga) {
		Integer filterIdTipoArchivo = getTipoArchivoOfPuntoInteraccion();
		return LadoLocal.getInstance().leeRadicadoPostal_UsingArchivoAdjunto(id_carga, filterIdTipoArchivo);
	}

	@Override
	protected Either<Exception, Collection<ArchivoAdjunto>> ladoLocal_ObtenerAdjuntos(Integer id_carga) {
		Integer filterIdTipoArchivo = getTipoArchivoOfPuntoInteraccion();
		return LadoLocal.getInstance().leeAdjuntos(id_carga, "S", true, filterIdTipoArchivo);

	}
	
}
