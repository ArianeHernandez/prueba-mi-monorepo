package com.osmosyscol.datasuite.near.webdata;

import co.htsoft.commons.util.SMessage;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.mein.domain.CerlResponse;
import com.osmosyscol.datasuite.mein.domain.Sociedad;
import com.osmosyscol.datasuite.mein.logica.constantes.Constantes;
import com.osmosyscol.datasuite.mein.servicios.NotificacionServicio;
import com.osmosyscol.datasuite.mein.servicios.RuesServicio;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.webdata.logica.dto.SolicitudEnrolamiento;
import com.osmosyscol.datasuite.webdata.logica.servicios.SolicitudEnrolamientoServicio;

public class ConsultarRUES implements AccionCarga {

	@Override
	public SMessage ejecutar(Integer id_carga) {
		
		try {
			
			SolicitudEnrolamiento solicitud = SolicitudEnrolamientoServicio.getInstance().obtenerSolicitudEnrolamiento(id_carga);
			
			CerlResponse ruesRta = RuesServicio.getInstance()
					.obtenerDatosRues(solicitud.getNumero_identificacion(), solicitud.getDatos_representante().getNumero_identificacion());
			
			SimpleLogger.setInfo("Respuesta RUES: " + ruesRta.isRespuesta());
	
			if (ruesRta == null || !ruesRta.isRespuesta()) {
				SimpleLogger.setInfo("Respuesta RUES: Ingresa a envío de correo " );
				DS_SqlUtils.update("update $ENROLAMIENTO CLIENTE$ "
						+ "set $ENROLAMIENTO CLIENTE.ESTADO OPERACION$ = $S(" + Constantes.MENSAJE_SIN_DATOS_RUES + ")$ where ID = " + solicitud.getId());
	
				NotificacionServicio.getInstance().enviarCorreoRuesInvalido(solicitud);
	
			} else {
				SimpleLogger.setInfo("Respuesta RUES: Ingresa a actualización de datos " );
				Sociedad sociedad = ruesRta.getSociedad();
				
				if(sociedad != null && sociedad.getRazon_social() != null){
					
					String queryActRazonSocial = "update $ENROLAMIENTO CLIENTE$ "
							+ "set $ENROLAMIENTO CLIENTE.RAZON SOCIAL$ = $S(" + sociedad.getRazon_social() + ")$ where ID = " + solicitud.getId();
					
					DS_SqlUtils.update(queryActRazonSocial);
					
					SimpleLogger.setInfo("Se actualiza razon social (" + sociedad.getRazon_social() + ") en solicitud " + id_carga);
					
					solicitud.setRazon_social(sociedad.getRazon_social());
				}
				
			}
		} catch (Exception e) {
			SimpleLogger.setError("ConsultarRUES: Error ", e);
		}
		
		return new SMessage(true, "");
	}

	@Override
	public Boolean visualizar(Integer id_carga, Integer id_administrativo) {
		return true;
	}

}
