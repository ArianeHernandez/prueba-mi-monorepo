package com.osmosyscol.datasuite.logica.servicios.json;

import com.osmosyscol.commons.utils.Mensaje;
import com.osmosyscol.datasuite.logica.dto.RespuestaSolicitudRegistro;
import com.osmosyscol.datasuite.logica.servicios.CambioPinServicio;

public class CambioPinJsonServicio {

	public Mensaje<RespuestaSolicitudRegistro> registroCambioPin(String login){
		return CambioPinServicio.validarRegistro(login);
	}
	
	public Mensaje<Boolean> finalizarCambioPin(Integer codigoSolicitud, String pinCelular, String claveNueva) {
		return CambioPinServicio.finalizarCambioPin(codigoSolicitud, pinCelular, claveNueva);
	}
	
}
