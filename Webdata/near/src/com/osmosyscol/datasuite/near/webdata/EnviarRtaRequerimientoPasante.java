package com.osmosyscol.datasuite.near.webdata;

import co.htsoft.commons.util.SMessage;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.mein.dtos.RespuestaRequerimiento;
import com.osmosyscol.datasuite.mein.dtos.ResponsePasante;
import com.osmosyscol.datasuite.mein.logica.constantes.Constantes;
import com.osmosyscol.datasuite.mein.servicios.RespuestaRequerimientoServicio;
import com.osmosyscol.datasuite.mein.servicios.rest.client.PasanteAppRestClient;

public class EnviarRtaRequerimientoPasante implements AccionCarga{
	
	@Override
	public SMessage ejecutar(Integer id_carga) {
		try {
			
			RespuestaRequerimiento informacion = RespuestaRequerimientoServicio.getInstance().obtenerRespuestaRequerimientoBase(id_carga);
			
			if (null == informacion) {
			
				SimpleLogger.setError("Envio Pasante: No se encuentra informacion para la carga " + id_carga);

				return new SMessage(false, "");
				
			} else if (null != informacion && (!Constantes.TIPO_SOLICITUD_NEAR.equals(informacion.getTipo_solicitud())
					|| (Constantes.TIPO_SOLICITUD_NEAR.equals(informacion.getTipo_solicitud()) && Constantes.TIPO_SOLICITANTE_SOCIEDAD.equals(informacion.getTipo_solicitante())))) {
				PasanteAppRestClient pasante = PasanteAppRestClient.getInstance();
				ResponsePasante salidaRequisito = pasante.enviarRtaRequerimiento(id_carga);
				
				if (salidaRequisito == null || (!Constantes.PASANTE_SUCCESS_CODE.equals(salidaRequisito.getError().getCode()) && !Constantes.PASANTE_DATA_ALREADY_CREATED.equals(salidaRequisito.getError().getCode()))) {
					SimpleLogger.setError("Error en el registro a pasante para la solicitud " + id_carga + ":");
					return new SMessage(false, "");
				}
				
				SimpleLogger.setInfo("Envio Pasante: La información con id " + id_carga + " se envió exitosamente");
				
				return new SMessage(true, "Ok");
				
			} else {
				SimpleLogger.setDebug("La carga " + id_carga + " no fue enviada a pasante porque no cumple con los requisitos. Se continua con la siguiente instancia");
				
				return new SMessage(true, "Ok");
				
			} 

		} catch (Exception e) {
			SimpleLogger.setError("Error en el envio de datos a pasante para la carga " + id_carga, e);

			return new SMessage(false, "");
		}
	}

	@Override
	public Boolean visualizar(Integer id_carga, Integer id_administrativo) {
		return true;
	}	

}
