package com.osmosyscol.datasuite.mein.servicios.rest.api;

import co.htsoft.commons.net.GET;
import co.htsoft.commons.net.POST;
import co.htsoft.commons.net.RestService;

import javax.servlet.annotation.WebServlet;

import com.osmosyscol.datasuite.mein.dtos.JSONResponse;
import com.osmosyscol.datasuite.mein.dtos.WSData;
import com.osmosyscol.datasuite.mein.logica.constantes.Constantes;
import com.osmosyscol.datasuite.mein.servicios.NotificacionServicio;

@WebServlet("/api/notificacion/*")
public class NotificacionRest  extends RestService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@GET("/api/notificacion/test")
	public String test() {
		return Constantes.JSON_RESPONSE_MSG_OK;
	}
		
	@POST(value="/api/notificacion/errorWS")
	public JSONResponse notificarErrorWS (WSData r) {
		JSONResponse response = new JSONResponse();
		
		String notifica = NotificacionServicio.getInstance().notificarErrorWS(r);
		String status = Constantes.JSON_RESPONSE_MSG_OK.equals(notifica) 
				? Constantes.JSON_RESPONSE_STATUS_SUCCESS: Constantes.JSON_RESPONSE_STATUS_FAILED; 
		
		response.setStatus(status);
		response.setMessage(notifica);
		
		return response;
	}
	
}
