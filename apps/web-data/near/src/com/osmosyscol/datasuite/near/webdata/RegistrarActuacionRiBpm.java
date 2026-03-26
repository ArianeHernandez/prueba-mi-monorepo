package com.osmosyscol.datasuite.near.webdata;

import co.htsoft.commons.util.SMessage;

import com.google.gson.Gson;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.bpm.dto.BPMResponse;
import com.osmosyscol.datasuite.bpm.servicios.BpmServicios;
import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.mein.dtos.WSData;
import com.osmosyscol.datasuite.mein.logica.constantes.Constantes;
import com.osmosyscol.datasuite.mein.servicios.NotificacionServicio;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

public class RegistrarActuacionRiBpm implements AccionCarga{
	
	@Override
	public SMessage ejecutar(Integer id_carga){
		try {
			
			WSData registro = BpmServicios.getInstance().enviarProcesoActuacionABPM(id_carga);
			Boolean notificarError = false;
			
			try {
				
				BPMResponse response = new Gson().fromJson(registro.getResponse(), BPMResponse.class);
				
				if (Constantes.RESPUESTA_EXITOSA_ENVIO_BPM.equals(response.getMensaje())) {
					
					String query = "update $respuesta requerimiento$ set $respuesta requerimiento.estado envio bpm$ = $S(" + Constantes.WS_RESPUESTA_EXITOSA + ")$ where idcarga = " + id_carga;
					
					SimpleLogger.setInfo("RegistrarActuacionRiBpm.ejecutar: Query " + id_carga + " " + query);
					
					DS_SqlUtils.update(query);
					
				} else {
					notificarError = true;
				}
				
			} catch (Exception e) {
				SimpleLogger.setError("RegistrarActuacionRiBpm: Error al validar respuesta de BPM Solicitud " + id_carga, e);
				notificarError = true;
			}
			
			if (notificarError) {
				NotificacionServicio.getInstance().notificarErrorWS(registro);
			}
			
			return new SMessage(true, "");
			
		}catch (Exception e) {
			SimpleLogger.setError("Error al registrar actuacion de Regimen de Insolvencia en BPM: Solicitud " + id_carga, e);

			return new SMessage(true, "");
		}
	}
	
	@Override
	public Boolean visualizar(Integer id_carga, Integer id_administrativo) {
		return true;
	}	

}
