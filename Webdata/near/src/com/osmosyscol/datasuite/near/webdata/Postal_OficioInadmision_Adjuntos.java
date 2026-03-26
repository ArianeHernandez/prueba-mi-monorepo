package com.osmosyscol.datasuite.near.webdata;

import com.osmosyscol.datasuite.near.servicios.IPostalInteraccion;

public class Postal_OficioInadmision_Adjuntos extends Abstract_Postal_Envio_DocSalida_Adjuntos {
	
	@Override
	protected String getPuntoInteraccion() {
		return IPostalInteraccion.PUNTOINTERACCION_OFICIOINADMISION;
	}
	
}
