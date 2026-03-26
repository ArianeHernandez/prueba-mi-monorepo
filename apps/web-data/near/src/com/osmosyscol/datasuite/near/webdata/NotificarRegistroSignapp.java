package com.osmosyscol.datasuite.near.webdata;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import co.htsoft.commons.util.SMessage;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.mensajeria.EnviaMails;
import com.osmosyscol.commons.utils.JavaToXML;
import com.osmosyscol.datasuite.logica.dto.Contenido;
import com.osmosyscol.datasuite.logica.dto.Notificacion;
import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.logica.servicios.ContenidoServicio;
import com.osmosyscol.datasuite.mein.dtos.EnrolamientoCliente;
import com.osmosyscol.datasuite.webdata.logica.servicios.SolicitudEnrolamientoServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class NotificarRegistroSignapp implements AccionCarga {

	@Override
	public SMessage ejecutar(Integer id_carga) {
		try {
			
			EnrolamientoCliente datos = SolicitudEnrolamientoServicio.getInstance().obtenerEnrolamientoCliente(id_carga);
			
			if (datos != null && datos.getCorreo_electronico() != null) {
				
				String host = ParametrosInicio.getProperty("HostCorreos");
				
				Contenido contenido = ContenidoServicio.getInstance().obtenerContenido("General", "NOTIFICACION REGISTRO SIGNAPP");
				
				String mensaje = contenido.getTexto();
				mensaje = mensaje.replace("#host#", host);
				
				Notificacion notificacion = new Notificacion();
				notificacion.setTitulo("Notificación de Vinculación Biométrica por Mecanismo Alterno");
				notificacion.setFecha_envio(new Date());
				notificacion.setContenido(mensaje);
				Map<String, String> parametros = JavaToXML.objectToParameters(
						"notificacion", notificacion);
				Map<String, String> archivos = new HashMap<String, String>();
				archivos.put("#logo#", "images/back/logo1.png");
				archivos.put("#logo2#", "images/back/logo2.png");
				//archivos.put("#logo_potencia#", "images/back/Logo_potencia.png");
				
				EnviaMails.enviar(datos.getCorreo_electronico(), datos.getCorreo_electronico(), "Notificación de Vinculación Biométrica por Mecanismo Alterno",
						"notificacion/envioNotificacionGeneral.email",
						archivos, parametros);
				
			} else {
				SimpleLogger.setError("notificarRegistroSignapp: No se encuentra correo electronico para notificar registro de la solicitud " + id_carga);
			}
			
		} catch (Exception e) {
			SimpleLogger.setError("notificarRegistroSignapp: ", e);
		}
		
		return new SMessage(true, "");
	}

	@Override
	public Boolean visualizar(Integer id_carga, Integer id_administrativo) {
		return true;
	}

}
