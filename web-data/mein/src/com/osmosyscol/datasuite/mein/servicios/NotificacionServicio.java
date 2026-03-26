package com.osmosyscol.datasuite.mein.servicios;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import co.htsoft.commons.file.FileUtils;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.mensajeria.EnviaMails;
import com.osmosyscol.commons.utils.JavaToXML;
import com.osmosyscol.datasuite.logica.dto.Contenido;
import com.osmosyscol.datasuite.logica.dto.Notificacion;
import com.osmosyscol.datasuite.logica.dto.Proceso;
import com.osmosyscol.datasuite.logica.dto.TipoDocumento;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.servicios.ContenidoServicio;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;
import com.osmosyscol.datasuite.logica.servicios.UsuarioServicio;
import com.osmosyscol.datasuite.mein.dtos.WSData;
import com.osmosyscol.datasuite.mein.logica.constantes.Constantes;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ProcesoServicio;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.dto.SolicitudEnrolamiento;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class NotificacionServicio {

	private static NotificacionServicio instance = new NotificacionServicio();

	private NotificacionServicio() {
	}
	
	public static NotificacionServicio getInstance() {
		return instance;
	}
	
	public Boolean notificarErrorCargueArchivos(Integer id_carga, String[] archivos) {
		
		try {
		
			Carga carga = CargaServicio.getInstance().obtenerCarga(id_carga);
			
			Usuario usuario = UsuarioServicio.getInstance().obtenerUsuario(carga.getId_usuario());
			
			TipoDocumento tipo_doc = PersonaServicio.getInstance().obtenerTipoDocumento(Integer.parseInt(usuario.getTipo_documento()));
			
			String proceso = "";
			
			if (carga.getId_proceso() != null) {
				Proceso proc = ProcesoServicio.getInstance().obtenerProceso(carga.getId_proceso());
				proceso = "de <b>" + proc.getNombre() + "</b>";
			}
			
			String contenido = "Se informa que el usuario <b>" + usuario.getNombre() + "</b> con <b>" + tipo_doc.getNombre().toUpperCase() + " " + usuario.getIdentificacion() 
					+ "</b> ha diligenciado una solicitud " + proceso + " con número <b>" + id_carga + "</b>, en la cual no se han cargado los siguientes documentos: <br/>"
					+ "<ul style=\"color:#333333;\">";
			
			for (String archivo: archivos) {
				contenido += "<li>" + archivo + "</li>";
			}
			
			contenido += "</ul>"; 
			
			Notificacion notificacion = new Notificacion();
			notificacion.setTitulo("Solicitud con documentos pendientes");
			notificacion.setFecha_envio(new Date());
			notificacion.setContenido(contenido);
			Map<String, String> parametros = JavaToXML.objectToParameters(
					"notificacion", notificacion);
			Map<String, String> adjuntos = new HashMap<String, String>();
			adjuntos.put("#logo#", "images/back/logo1.png");
			adjuntos.put("#logo_potencia#", "images/back/Logo_potencia.png");
			
			String correoSSOC = ParametrosInicio.getProperty("errorCargueArchivos.correoSSOC");
			
			if (correoSSOC != null) {
				EnviaMails.enviar(correoSSOC, correoSSOC, "Solicitud con Documentos Pendientes",
						"notificacion/errorCargueArchivos.email",
						adjuntos, parametros);
			}
			
			String correoNuvu = ParametrosInicio.getProperty("errorCargueArchivos.correoNuvu");
			
			if (correoNuvu != null) {
				EnviaMails.enviar(correoNuvu, correoNuvu, "Solicitud con Documentos Pendientes",
						"notificacion/errorCargueArchivos.email",
						adjuntos, parametros);
			}
			
		} catch (Exception e) {
			SimpleLogger.setError("Error al notificar el fallo en el cargue de archivos para la solicitud " + id_carga, e);
		}
		
		
		return true;
	}
	
	public String notificarErrorWS (WSData integracion) {
		
		Integer id_carga = integracion.getId_carga();
			
		try {
			
			SimpleLogger.setInfo("Enviando notificación de fallo WS para la Solicitud " + id_carga + " Integracion " + integracion.getIntegracion());
			
			Carga carga = CargaServicio.getInstance().obtenerCarga(id_carga);
			
			String proceso = "";
			
			if (carga.getId_proceso() != null) {
				Proceso proc = ProcesoServicio.getInstance().obtenerProceso(carga.getId_proceso());
				proceso = proc.getNombre();
			} else if (Constantes.SSOC_SISTEMA_SIGS.equals(integracion.getSistema())) {
				proceso = "ENROLAMIENTO";
			}
			
			Contenido contenido = ContenidoServicio.getInstance().obtenerContenido("General", "NOTIFICACION ERROR WS");
			
			String req = integracion.getRequest() != null? integracion.getRequest(): "null";
			String res = integracion.getResponse() != null? integracion.getResponse(): "null";
			
			Map<String, String> archivos = new HashMap<String, String>();
			
			if (req.length() > 4800) {
				req = " (Ver adjunto)";
				
				String rutabase = ParametrosInicio.getProperty("adjuntos.carpeta") + "/ADJUNTOS/NOTIFICACION/";
				String ruta_archivo = rutabase + "Request_" + id_carga + ".log";
				
				FileUtils.toFile(integracion.getRequest().getBytes(), ruta_archivo);
				archivos.put("#file_request#", "file://" + ruta_archivo);
			}
			
			if (res.length() > 1000) {
				res = res.substring(0, 1000) + "... (Ver adjunto)";
				
				String rutabase = ParametrosInicio.getProperty("adjuntos.carpeta") + "/ADJUNTOS/NOTIFICACION/";
				String ruta_archivo = rutabase + "Response_" + id_carga + ".log";
				
				FileUtils.toFile(integracion.getResponse().getBytes(), ruta_archivo);
				archivos.put("#file_response#", "file://" + ruta_archivo);
			}
			
			
			String mensaje = contenido.getTexto();
			mensaje = mensaje.replace("#id_solicitud#", id_carga.toString());
			mensaje = mensaje.replace("#nombre_proceso#", proceso);
			mensaje = mensaje.replace("#nombre_integracion#", integracion.getIntegracion());
			mensaje = mensaje.replace("#request#", req);
			mensaje = mensaje.replace("#response#", res);
			
			Notificacion notificacion = new Notificacion();
			notificacion.setTitulo("Notificación Fallo Integración MI - " + integracion.getSistema());
			notificacion.setFecha_envio(new Date());
			notificacion.setContenido(mensaje);
			Map<String, String> parametros = JavaToXML.objectToParameters(
					"notificacion", notificacion);
			archivos.put("#logo#", "images/back/logo1.png");
			//archivos.put("#logo_potencia#", "images/back/Logo_potencia.png");
			
			String correoSSOC = ParametrosInicio.getProperty("errorWS.correoSSOC");
			
			if (correoSSOC != null) {
				EnviaMails.enviar(correoSSOC, correoSSOC, "Notificación Fallo Integración MI - " + integracion.getSistema(),
						"notificacion/envioNotificacionTemplate.email",
						archivos, parametros);
			}
			
			String correoNuvu = ParametrosInicio.getProperty("errorWS.correoNuvu");
			
			if (correoNuvu != null) {
				EnviaMails.enviar(correoNuvu, correoNuvu, "Notificación Fallo Integración MI - " + integracion.getSistema(),
						"notificacion/envioNotificacionTemplate.email",
						archivos, parametros);
			}
			
			return Constantes.JSON_RESPONSE_MSG_OK;
			
		} catch (Exception e) {
			SimpleLogger.setError("NotificacionServicio.notificarErrorWS: Solicitud " + id_carga, e);
			return "Error: " + e.toString();
		}
		
		
	}
	
	public void enviarCorreoRuesInvalido(SolicitudEnrolamiento solicitud) {

		SimpleLogger.setInfo("Notificando Consultado a Rues Invalida para la solicitud " + solicitud.getIdcarga());
		
		try {
		
			String tipoFormulario = solicitud.getTipo_formulario();
			
			String correo = solicitud.getDatos_representante().getCorreo_electronico();
			String nombre = solicitud.getDatos_representante().getNombres() + " " + solicitud.getDatos_representante().getApellidos();
	
			Map<String, String> archivos = new HashMap<String, String>();
			Notificacion notificacion = new Notificacion();
			notificacion.setTitulo("Notificación Registro Fallido");
			notificacion.setFecha_envio(new Date());
	
			String asunto = "Notificacion de solicitud";
	
			Map<String, String> parametros = JavaToXML.objectToParameters("notificacion", notificacion);
			archivos.put("#logo#", "images/back/logo1.png");
			//archivos.put("#logo_potencia#", "images/back/Logo_potencia.png");
	
			if (Constantes.TIPO_SOLICITANTE_SOCIEDAD.toString().equals(tipoFormulario)) {
				
				EnviaMails.enviar(correo, nombre, asunto,"notificacion/envioNotificacionRuesInvalido.email",archivos, parametros);
			
			} else if (Constantes.TIPO_SOLICITANTE_PNC.toString().equals(tipoFormulario)) {
				
				EnviaMails.enviar(correo, nombre, asunto,"notificacion/envioNotificacionRuesInvalidoPNC.email",archivos, parametros);
			}
		} catch (Exception e) {
			SimpleLogger.setError("NotificacionServicio.enviarCorreoRuesInvalido: Error ", e);
		}

	}
	
	public void notificarRechazoRegistro (SolicitudEnrolamiento solicitud) {
		
		SimpleLogger.setInfo("Notificando rechazo de registro por inconsistencia en identificacion para la solicitud " + solicitud.getIdcarga());
		
		try {
			
			Contenido contenido = ContenidoServicio.getInstance().obtenerContenido("General", "NOTIFICACION RECHAZO REGISTRO");
			
			String mensaje = contenido.getTexto();
			String url = ParametrosInicio.getProperty("HostCorreos");
			mensaje = mensaje.replace("#URL_BASE#", url);
			
			Notificacion notificacion = new Notificacion();
			notificacion.setTitulo("Rechazo Registro MI");
			notificacion.setFecha_envio(new Date());
			notificacion.setContenido(mensaje);
			Map<String, String> parametros = JavaToXML.objectToParameters("notificacion", notificacion);
			Map<String, String> archivos = new HashMap<String, String>();
			archivos.put("#logo#", "images/back/logo1.png");
			//archivos.put("#logo_potencia#", "images/back/Logo_potencia.png");
			
			String correo = solicitud.getCorreo_electronico();
			
			if (correo != null) {
				EnviaMails.enviar(correo, correo, "Notificación Rechazo Registro MI",
						"notificacion/envioNotificacionTemplate.email",
						archivos, parametros);
			}
			
			SimpleLogger.setInfo("Finaliza notificación de rechazo de registro para la solicitud " + solicitud.getIdcarga());
			
		} catch (Exception e) {
			SimpleLogger.setError("NotificacionServicio.notificarRechazoRegistro: " + solicitud.getIdcarga() + " Error ", e);
		}
		
	}
	
}
