package com.osmosyscol.datasuite.webdata.logica.acciones;

import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.mail.EmailException;

import com.osmosyscol.commons.mensajeria.EnviaMails;
import com.osmosyscol.commons.utils.JavaToXML;
import com.osmosyscol.datasuite.logica.dto.Notificacion;
import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.dto.SolicitudEnrolamiento;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;
import com.osmosyscol.datasuite.webdata.logica.servicios.SolicitudEnrolamientoServicio;

import co.htsoft.commons.util.SMessage;

public class EnvioNotificacionRechazoSolicitud implements AccionCarga {

	public SMessage ejecutar(Integer id_carga) {
		
		Carga carga = CargaServicio.getInstance().obtenerCarga(id_carga);
		String correo_1 = "";
		String correo_2 = "";
		String nombreCompleto1 = "";
		String nombreCompleto2 = "";
		String nombres1, apellidos1, nombres2, apellidos2;
		
		//SolicitudEnrolamiento
		SolicitudEnrolamiento solicitud = SolicitudEnrolamientoServicio.getInstance().obtenerSolicitudEnrolamiento(id_carga);
		
		if(carga.getId_formato() == -500){
//			correo_1 = DS_SqlUtils.queryForObject(String.class, "SELECT $ENROLAMIENTO CLIENTE.CORREO ELECTRONICO$ FROM $ENROLAMIENTO CLIENTE$ WHERE IDCARGA = ".concat(id_carga.toString()));
//			nombres1 = DS_SqlUtils.queryForObject(String.class, "SELECT $ENROLAMIENTO CLIENTE.NOMBRES$ FROM $ENROLAMIENTO CLIENTE$ WHERE IDCARGA = ".concat(id_carga.toString()));
//			apellidos1 = DS_SqlUtils.queryForObject(String.class, "SELECT $ENROLAMIENTO CLIENTE.APELLIDOS$ FROM $ENROLAMIENTO CLIENTE$ WHERE IDCARGA = ".concat(id_carga.toString()));
			
			correo_1 = solicitud.getCorreo_electronico();
			nombres1 = solicitud.getNombres();
			apellidos1 = solicitud.getApellidos();
			
		}else{
//			correo_1 = DS_SqlUtils.queryForObject(String.class, "SELECT $ENROLAMIENTO REP LEGAL.CORREO ELECTRONICO$ FROM $ENROLAMIENTO REP LEGAL$ WHERE IDCARGA = ".concat(id_carga.toString()));
//			correo_2= DS_SqlUtils.queryForObject(String.class, "SELECT $ENROLAMIENTO USUARIO.CORREO ELECTRONICO$ FROM $ENROLAMIENTO USUARIO$ WHERE IDCARGA = ".concat(id_carga.toString()));
//			
//			nombres1 = DS_SqlUtils.queryForObject(String.class, "SELECT $ENROLAMIENTO REP LEGAL.NOMBRES$ FROM $ENROLAMIENTO REP LEGAL$ WHERE IDCARGA = ".concat(id_carga.toString()));
//			apellidos1 = DS_SqlUtils.queryForObject(String.class, "SELECT $ENROLAMIENTO REP LEGAL.APELLIDOS$ FROM $ENROLAMIENTO REP LEGAL$ WHERE IDCARGA = ".concat(id_carga.toString()));
//			nombres2 = DS_SqlUtils.queryForObject(String.class, "SELECT $ENROLAMIENTO USUARIO.NOMBRES$ FROM $ENROLAMIENTO USUARIO$ WHERE IDCARGA = ".concat(id_carga.toString()));
//			apellidos2 = DS_SqlUtils.queryForObject(String.class, "SELECT $ENROLAMIENTO USUARIO.APELLIDOS$ FROM $ENROLAMIENTO USUARIO$ WHERE IDCARGA = ".concat(id_carga.toString()));
			
			correo_1 = solicitud.getDatos_representante().getCorreo_electronico();
			correo_2= solicitud.getDatos_usuario().getCorreo_electronico();
			
			nombres1 = solicitud.getDatos_representante().getNombres();
			apellidos1 = solicitud.getDatos_representante().getApellidos();
			nombres2 = solicitud.getDatos_usuario().getNombres();
			apellidos2 = nombres2 = solicitud.getDatos_usuario().getApellidos();			
			nombreCompleto2 = validarNombre(nombres2, null,  apellidos2, null); 
		}
		
//			==================================================================================================

		nombreCompleto1 = validarNombre(nombres1, null, apellidos1, null);
//			==================================================================================================
		
		
		
//			==================================================================================================
		//CertidatosServicio.getInstance().crearProyecto(id_carga);
		//ProyectoCertidatos formatoFUV = CertidatosServicio.getInstance().generarFUVPersonaNatural(id_carga);
		//String rutaFUV = CertidatosServicio.getInstance().obtenerRutaProyectoGenerado(formatoFUV.getCodigo_proyecto(), formatoFUV.getCodigo_verificacion());
//			==================================================================================================
		
		

//			==================================================================================================
//			ARCHIVOS A ADJUNTAR EN EL CORREO
		Map<String, String> archivos = new HashMap<String, String>();
		Notificacion notificacion = new Notificacion();
		notificacion.setTitulo("NotificaciÃ³n Rechazo Solicitud Enrolamiento");
		notificacion.setFecha_envio(new Date());
		
		String texto;
		
		if(solicitud.getDatos_usuario() != null){
			texto = "La solicitud de enrolamiento del usuario " + solicitud.getDatos_usuario().getNombres() + " " + solicitud.getDatos_usuario().getApellidos() + "  ha sido Rechazada.";
		}else{
			texto= "La solicitud de enrolamiento del usuario " + solicitud.getNombres() + " " + solicitud.getApellidos() + "  ha sido Rechazada.";
		}
		
		notificacion.setContenido(texto);
		Map<String, String> parametros = JavaToXML.objectToParameters("notificacion", notificacion);
		archivos.put("#logo#", "images/back/logo1.png");
		//archivos.put("#logo_potencia#", "images/back/Logo_potencia.png");
		
//		PersonaServicio.getInstance().obtener
		
		try {
			
			EnviaMails.enviar(correo_1, nombreCompleto1, "Notificación Rechazo", "notificacion/envioNotificacionSolicitudEnrolamiento.email", archivos, parametros);
			
			if(!nombreCompleto2.isEmpty()){
				EnviaMails.enviar(correo_2, nombreCompleto2, "Notificación Rechazo", "notificacion/envioNotificacionSolicitudEnrolamiento.email", archivos, parametros);
			}
			
			return new SMessage(true, "OK");
		} catch (Exception e) {
			e.printStackTrace();
			return new SMessage(false, "ERROR");
		}
		
	}

	public Boolean visualizar(Integer id_carga, Integer id_administrativo) {
		return true;
	}
	
	private String validarNombre(String pN, String sN, String pA, String sA) {
		String tmp = "";
		
		if(pN != null) {
			tmp += (pN + " ");
		}
		
		if(sN != null) {
			tmp += (sN + " ");
		}
		
		if(pA != null) {
			tmp += (pA + " ");
		}
		
		if(sA != null) {
			tmp += (sA);
		}
		
		return tmp;
	}
	
}
