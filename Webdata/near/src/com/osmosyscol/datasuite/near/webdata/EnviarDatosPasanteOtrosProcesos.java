package com.osmosyscol.datasuite.near.webdata;

import co.htsoft.commons.util.SMessage;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.mein.dtos.RegimenInsolvencia;
import com.osmosyscol.datasuite.mein.dtos.ResponsePasante;
import com.osmosyscol.datasuite.mein.logica.constantes.Constantes;
import com.osmosyscol.datasuite.mein.servicios.rest.client.PasanteAppRestClient;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

public class EnviarDatosPasanteOtrosProcesos implements AccionCarga{
	
	@Override
	public SMessage ejecutar(Integer id_carga) {
		try {
			RegimenInsolvencia informacion = DS_SqlUtils.queryForObject(RegimenInsolvencia.class, "SELECT * FROM $REGIMEN DE INSOLVENCIA$ WHERE IDCARGA = " + id_carga);
			
			if (informacion != null) {
			
				PasanteAppRestClient pasante = PasanteAppRestClient.getInstance();
				ResponsePasante salidaRequisito = pasante.obtenerDatosRegistroOtrosProcesos(id_carga);
				
				if (salidaRequisito == null || (!Constantes.PASANTE_SUCCESS_CODE.equals(salidaRequisito.getError().getCode()) && !Constantes.PASANTE_DATA_ALREADY_CREATED.equals(salidaRequisito.getError().getCode()))) {
					SimpleLogger.setError("Error en el registro a pasante para la solicitud " + id_carga + ":");
					return new SMessage(false, "");
				}
				
				SimpleLogger.setInfo("Envio Pasante: La información con id " + id_carga
						+ " se envió exitosamente");
				
				return new SMessage(true, "Ok");
			} else {
				SimpleLogger.setError("Envio Pasante: No se encuentra informacion de regimen de insolvencia para la carga " + id_carga);

				return new SMessage(false, "");
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
