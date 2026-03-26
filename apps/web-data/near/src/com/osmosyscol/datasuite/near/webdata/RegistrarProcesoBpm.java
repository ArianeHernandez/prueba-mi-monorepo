package com.osmosyscol.datasuite.near.webdata;

import java.util.Objects;

import co.htsoft.commons.util.SMessage;

import com.google.gson.Gson;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.bpm.dto.BPMResponse;
import com.osmosyscol.datasuite.bpm.servicios.BpmServicios;
import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.mein.dtos.WSData;
import com.osmosyscol.datasuite.mein.logica.constantes.Constantes;
import com.osmosyscol.datasuite.mein.servicios.NotificacionServicio;
import com.osmosyscol.datasuite.near.servicios.InfoRadicadoLocalServicio;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

public class RegistrarProcesoBpm implements AccionCarga{
	
	@Override
	public SMessage ejecutar(Integer id_carga){
		
		try {
			String estructura = Objects.toString(InfoRadicadoLocalServicio.getInstance().obtenerNombreEstructuraFormatoPorCarga(id_carga), "");
			Boolean notificarError = false;
			WSData registro = BpmServicios.getInstance().enviarDatosABPM(id_carga, estructura);
			
			try {
				BPMResponse response = new Gson().fromJson(registro.getResponse(), BPMResponse.class);
				
				if (Constantes.RESPUESTA_EXITOSA_ENVIO_BPM.equals(response.getMensaje())) {
					
					String query = "update $" + estructura.toLowerCase() + "$ set $" + estructura.toLowerCase() + ".estado envio bpm$ = $S(" + Constantes.WS_RESPUESTA_EXITOSA + ")$ where idcarga = " + id_carga;
					
					SimpleLogger.setInfo("RegistrarProcesoBpm.ejecutar: Query " + id_carga + " " + query);
					
					DS_SqlUtils.update(query);
					
				} else {
					notificarError = true;
				}
				
			} catch (Exception e) {
				SimpleLogger.setError("RegistrarProcesoBpm: Error al validar respuesta de BPM Solicitud " + id_carga, e);
				notificarError = true;
			}
			
			if (notificarError) {
				NotificacionServicio.getInstance().notificarErrorWS(registro);
			}
			
			return new SMessage(true, "");				
			
		}catch (Exception e) {
			SimpleLogger.setError("Error al enviar datos a BPM: Solicitud " + id_carga, e);

			return new SMessage(true, "");
		}
	}
	
	@Override
	public Boolean visualizar(Integer id_carga, Integer id_administrativo) {
		return true;
	}	

}
