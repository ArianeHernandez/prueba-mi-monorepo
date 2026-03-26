package com.osmosyscol.datasuite.webdata.logica.acciones;

import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.mail.EmailException;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.mensajeria.EnviaMails;
import com.osmosyscol.commons.utils.JavaToXML;
import com.osmosyscol.datasuite.logica.dto.Notificacion;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.dto.TipoDocumento;
import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;
import com.osmosyscol.datasuite.logica.servicios.TipoDocumentoServicio;
import com.osmosyscol.datasuite.mein.dtos.PersonaMein;
import com.osmosyscol.datasuite.mein.dtos.RepartoIntendencias;
import com.osmosyscol.datasuite.mein.dtos.SolicitudNearSociedad;
import com.osmosyscol.datasuite.pagos.acciones.movimiento_tesoreria.EnviarMovimientoTesoreria;
import com.osmosyscol.datasuite.test.TestUtils;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.dto.SolicitudEnrolamiento;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;
import com.osmosyscol.datasuite.webdata.logica.servicios.SolicitudEnrolamientoServicio;

import co.htsoft.commons.util.SMessage;

public class EnvioNotificacionSolicitud implements AccionCarga {

	public SMessage ejecutar(Integer id_carga) {
		
		String correo = "";
		String nombreCompleto = "";
		String nombres, apellidos;
		
		//SolicitudEnrolamiento
		SolicitudNearSociedad solicitud = null;
		try {
			solicitud = DS_SqlUtils.queryForObject(SolicitudNearSociedad.class, 
					"select solicitud.* " + 
					"  from $solicitud near sociedad$ solicitud " +
					" left join $SOCIEDAD$ sociedad on solicitud.$solicitud near sociedad.deudor$ = sociedad.id " +
					" where solicitud.idcarga = " + id_carga);
			
			
			PersonaMein persona = new PersonaMein();
			
			Persona dst_persona = PersonaServicio.getInstance().obtenerPersona(solicitud.getDeudor().getRepresentante_legal().getId_persona_mein());
			persona.setCorreo(dst_persona.getCorreo());
			persona.setDireccion(dst_persona.getDireccion());
			persona.setIdentificacion(dst_persona.getIdentificacion());
			persona.setNombre_completo(dst_persona.getNombreCompleto());
			persona.setTelefono(dst_persona.getTelefono());
			
			Notificacion notificacion = new Notificacion();
			notificacion.setTitulo("Notificación Solicitud");
			notificacion.setFecha_envio(new Date());
			notificacion.setContenido("");
			Map<String, String> parametros = JavaToXML.objectToParameters(
					"notificacion", notificacion);
			Map<String, String> archivos = new HashMap<String, String>();
			archivos.put("#logo#", "images/back/logo1.png");
			//archivos.put("#logo_potencia#", "images/back/Logo_potencia.png");
			//EnviaMails.enviar(correo, receptor, notificacion.getTitulo(), "cocoon:/notificacion/envioNotificacion.email", archivos, parametros, resolver);
			EnviaMails.enviar(persona.getCorreo(), persona.getNombre_completo(), "Notificación Recibido",
					"notificacion/envioNotificacionSolicitudEnrolamiento.email",
					archivos, parametros);
			
			return new SMessage(true, "");
		} catch (Exception e) {
			SimpleLogger.setError("Error en el envio de notificacion de solicitud near:", e);

			return new SMessage(false, "");
		}
		
	}
	
	public static void EnviarCorreo(Integer id_carga){

		String correo = "";
		String nombreCompleto = "";
		String nombres, apellidos;
		
		//SolicitudEnrolamiento
		String identificacion_replegal= "";
		String tipo_doc= "";
		String tipo_doc_obj= "";
		Integer idpersonamein = null;
		try {
			identificacion_replegal = DS_SqlUtils.queryForObject(String.class, 
					"select $persona.identificacion$ from $solicitud near sociedad$ solicitud left join $SOCIEDAD$ deudor on solicitud.$solicitud near sociedad.deudor$ = deudor.id left join $PERSONA$ representante_legal on deudor.$sociedad.representante legal$ = representante_legal.id where solicitud.idcarga =" + id_carga);
			
			tipo_doc= DS_SqlUtils.queryForObject(String.class, 
					"select $persona.tipo identificacion$ from $solicitud near sociedad$ solicitud left join $SOCIEDAD$ deudor on solicitud.$solicitud near sociedad.deudor$ = deudor.id left join $PERSONA$ representante_legal on deudor.$sociedad.representante legal$ = representante_legal.id where solicitud.idcarga =" + id_carga);
			
			tipo_doc_obj= DS_SqlUtils.queryForObject(String.class, 
					"select $TIPO DE DOCUMENTO$.* from $tipo de documento$ where $TIPO DE DOCUMENTO.COD_HTS$ = $S("+tipo_doc+")$");
			
			PersonaMein persona = new PersonaMein();
			
			TipoDocumento tipoIdentificacion = null;
			tipoIdentificacion = DS_SqlUtils.queryForObject(TipoDocumento.class, "select $TIPO DE DOCUMENTO$.* from $tipo de documento$ where $TIPO DE DOCUMENTO.COD_HTS$ = $S("+tipo_doc+")$");
			
			Persona dst_persona = PersonaServicio.getInstance().obtenerPersonaPorIdentificacion(identificacion_replegal, 1);
			persona.setCorreo(dst_persona.getCorreo());
			persona.setDireccion(dst_persona.getDireccion());
			persona.setIdentificacion(dst_persona.getIdentificacion());
			persona.setNombre_completo(dst_persona.getNombreCompleto());
			persona.setTelefono(dst_persona.getTelefono());
			
			Notificacion notificacion = new Notificacion();
			notificacion.setTitulo("Notificación Solicitud test");
			notificacion.setFecha_envio(new Date());
			notificacion.setContenido("");
			Map<String, String> parametros = JavaToXML.objectToParameters(
					"notificacion", notificacion);
			Map<String, String> archivos = new HashMap<String, String>();
			archivos.put("#logo#", "images/Liberador.png");
			//archivos.put("#logo_potencia#", "images/back/Logo_potencia.png");
			//EnviaMails.enviar(correo, receptor, notificacion.getTitulo(), "cocoon:/notificacion/envioNotificacion.email", archivos, parametros, resolver);
			//notificacion/envioNotificacionSolicitudEnrolamiento.email
			EnviaMails.enviar(persona.getCorreo(), persona.getNombre_completo(), "Notificación Recibido",
					"notificacion/envioNotificacion.email",
					archivos, parametros);
			System.out.println("ok");
			
		} catch (Exception e) {
			System.out.println("error:" + e);
		}
		
	}

	public Boolean visualizar(Integer id_carga, Integer id_administrativo) {
		return true;
	}
	
	public static void main(String[] args) {
		TestUtils.startUp();
		EnviarCorreo(110264);
	}
	
}
