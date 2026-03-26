package com.osmosyscol.datasuite.webdata.logica.acciones;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.mensajeria.EnviaMails;
import com.osmosyscol.commons.utils.JavaToXML;
import com.osmosyscol.datasuite.logica.dto.Contenido;
import com.osmosyscol.datasuite.logica.dto.Notificacion;
import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.logica.servicios.ContenidoServicio;
import com.osmosyscol.datasuite.webdata.logica.dto.SolicitudEnrolamiento;
import com.osmosyscol.datasuite.webdata.logica.servicios.SolicitudEnrolamientoServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

import co.htsoft.commons.util.SMessage;

public class EnvioNotificacionFalloEnrolamiento implements AccionCarga {
	
	@Override
	public SMessage ejecutar(Integer id_carga) {
		SolicitudEnrolamiento solicitud = null;
		try {
			solicitud = SolicitudEnrolamientoServicio.getInstance().obtenerSolicitudEnrolamiento(id_carga);
			String correoNuvu = ParametrosInicio.getProperty("errorWS.correoNuvu");
			String correoSSOC = ParametrosInicio.getProperty("errorWS.correoSSOC");
			
			if (solicitud == null) {
				SimpleLogger.setError("No se encuentra una solicitud de enrolamiento para la carga " + id_carga);

				return new SMessage(false, "");
			}
			
			//enviar notificacion
			
			Contenido contenido = ContenidoServicio.getInstance().obtenerContenido("General", "NOTIFICACION FALLO ENROLAMIENTO");
			
			String mensaje = contenido.getTexto();
			mensaje = mensaje.replace("#id_solicitud#", solicitud.getId().toString());
			mensaje = mensaje.replace("#id_carga#", id_carga.toString());
			mensaje = mensaje.replace("#error#", solicitud.getEstado_operacion());
			mensaje = mensaje.replace("#mensaje_error#", solicitud.getMensajeError() != null ? solicitud.getMensajeError() : "");
			
			Notificacion notificacion = new Notificacion();
			notificacion.setTitulo("Notificaciï¿½n Fallo Creaciï¿½n Usuario");
			notificacion.setFecha_envio(new Date());
			notificacion.setContenido(mensaje);
			Map<String, String> parametros = JavaToXML.objectToParameters(
					"notificacion", notificacion);
			Map<String, String> archivos = new HashMap<String, String>();
			archivos.put("#logo#", "images/back/logo1.png");
			//archivos.put("#logo_potencia#", "images/back/Logo_potencia.png");
			
			if(correoNuvu != null ){
				EnviaMails.enviar(correoNuvu, correoNuvu, "Notificación Fallo Creación Usuario",
						"notificacion/envioNotificacion.email",
						archivos, parametros);
			}
			
			if(correoSSOC != null ){
				EnviaMails.enviar(correoSSOC, correoSSOC, "Notificación Fallo Creación Usuario",
						"notificacion/envioNotificacion.email",
						archivos, parametros);
			}
			
			return new SMessage(true, "");
			
		} catch(Exception e) {
			
			SimpleLogger.setError("Error enviando la notificacion:", e);

			return new SMessage(false, "");
			
		}
	}

	@Override
	public Boolean visualizar(Integer id_carga, Integer id_administrativo) {
		// TODO Auto-generated method stub
		return true;
	}

}
