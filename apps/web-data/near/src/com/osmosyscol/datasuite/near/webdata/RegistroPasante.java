package com.osmosyscol.datasuite.near.webdata;

import co.htsoft.commons.util.SMessage;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.mein.dtos.ResponsePasante;
import com.osmosyscol.datasuite.mein.dtos.SolicitudNearSociedad;
import com.osmosyscol.datasuite.mein.logica.constantes.Constantes;
import com.osmosyscol.datasuite.mein.servicios.rest.client.PasanteAppRestClient;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

public class RegistroPasante implements AccionCarga{
	
	@Override
	public SMessage ejecutar(Integer id_carga) {
		SolicitudNearSociedad solicitud = null;
		try {
			
			solicitud = DS_SqlUtils.queryForObject(SolicitudNearSociedad.class, "SELECT * FROM $SOLICITUD NEAR SOCIEDAD$ WHERE IDCARGA = " + id_carga);
			
			if (solicitud == null) {
				SimpleLogger.setError("Envio Pasante: No se encuentra informacion de solicitud near para la carga " + id_carga);

				return new SMessage(false, "");
			} else if (solicitud != null && Constantes.TIPO_SOLICITANTE_SOCIEDAD.equals(solicitud.getTipo_solicitante())){
				//consumir servicio de registro de pasante
				PasanteAppRestClient pasante = PasanteAppRestClient.getInstance();
				
				ResponsePasante respuesta = pasante
						.obtenerDatosRegistro(id_carga);
				
				if (respuesta == null || (!Constantes.PASANTE_SUCCESS_CODE.equals(respuesta.getError().getCode()) && !Constantes.PASANTE_DATA_ALREADY_CREATED.equals(respuesta.getError().getCode()))){
					SimpleLogger.setError("Error en el registro a pasante para la solicitud " + id_carga + ":");
					return new SMessage(false, "");
				}
				SimpleLogger.setInfo("Envio Pasante: La información con id " + id_carga
						+ " se envió exitosamente");
				
				return new SMessage(true, "");
				
			} else if (solicitud.getTipo_solicitante() == null) {
				SimpleLogger.setError("Envio Pasante: No se encuentra el tipo de solicitante para la carga " + id_carga);

				return new SMessage(false, "");
			} else {
				SimpleLogger.setDebug("La carga " + id_carga + " no se envia a pasante porque el tipo de solicitante es " + solicitud.getTipo_solicitante());
				
				return new SMessage(true, "Ok");
			}
		} catch (Exception e) {
			SimpleLogger.setError("Error en el registro a pasante:", e);

			return new SMessage(false, "");
		}
	}

	@Override
	public Boolean visualizar(Integer id_carga, Integer id_administrativo) {
		return true;
	}	

}
