package com.osmosyscol.datasuite.logica.servicios;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cocoon.environment.Session;
import org.apache.cocoon.environment.SourceResolver;

import co.htsoft.commons.rsa.SecureJsonUtils;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.mensajeria.EnviaMails;
import com.osmosyscol.commons.servicio.AutenticacionServicio;
import com.osmosyscol.commons.utils.Mensaje;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.Contenido;
import com.osmosyscol.datasuite.logica.dto.Credencial;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;
public class CuentaServicio {

	private static CuentaServicio instance;

	private CuentaServicio() {
	}

	public static CuentaServicio getInstance() {
		if (instance == null) {
			instance = new CuentaServicio();
		}
		return instance;
	}
	
	public String obtenerHost(){
		return ParametrosInicio.getProperty("HostCorreos");
	}
	
	public Integer validarUsuario(String login, String id_cliente, String clave, Session session){
		
		String loginUsuario = login;
		if (StringUtils.esNoVacio(id_cliente)) {
			loginUsuario+=Constantes.SEPARADOR_LOGIN + id_cliente;
		}
		
		Credencial credencial = PersonaServicio.getInstance().obtenerCredencialPorLogin(loginUsuario);
		if (credencial == null) {
			return 1;
		}
		try {
			clave = SecureJsonUtils.ds(clave);			
		} catch (Exception e) {
			SimpleLogger.setDebug("se intento descifrar una clave que no estaba cifrada");
			//e.printStackTrace();
		}
		Boolean salida = AutenticacionServicio.getInstance().autenticar(loginUsuario, clave, null);
		
		if (salida) {
			session.setAttribute("login", loginUsuario);
			if (credencial.getId_usuario() == null) {
				return -1;	//Delegado
			}
			return 0;
		}
		return 2;
	}
	
	public Integer definirClave(String login, String clave, String nuevaClave, Session session){
		try {
			if (login.equals(session.getAttribute("login"))) {

				Credencial credencial = PersonaServicio.getInstance().obtenerCredencialPorLogin(login);

				if (credencial != null ) {
					
					if (credencial.getEstado().equals(Constantes.CREDENCIAL_ESTADO_CREADO) && confirmarCreacionUsuarios()) {
						credencial.setEstado(Constantes.CREDENCIAL_ESTADO_PENDIENTE_APROBACION);
					}
					else {
						credencial.setEstado(Constantes.CREDENCIAL_ESTADO_ACTIVO);
					}
					
					Boolean rta = PersonaServicio.getInstance().actualizarEstadoCredencial(credencial.getEstado(), login);
					
					if (!rta) {
						return 5; // no se puede actualizar la credencial
					}
					
					Mensaje salida = AutenticacionServicio.getInstance().cambiarClave(login, clave, nuevaClave);
					if (salida.getExitoso()) {
						
						PersonaServicio.getInstance().guardarHistorialLoginClave(login, nuevaClave);
						enviarNotificacionClaveAsignada(login);
						 
						return 0; // Exito
					}
					else {
						return 2; // No cambia la clave
					}
				}
				
				return 3; //usuario o estado invalido
				
			}
			
			return 4; // no esta autorizado
		} catch (Exception e) {
			SimpleLogger.setError("Error al cambiar la clave", e);
		}
		return 1; //Error desconocido
	}
	
	public void enviarNotificacionClaveAsignada(String login) {
		try {
			SimpleLogger.setInfo("Enviando notificacion de clave asignada al login: " + login);
			
			Credencial credencial = PersonaServicio.getInstance().obtenerCredencialPorLogin(login);
			if(credencial.getId_usuario() == null) {
				SimpleLogger.setInfo("Notificacion de clave no enviada porque no es un usuario de cliente: " + login);
				return;
			}
			
			Persona persona = PersonaServicio.getInstance().obtenerPersonaPorLogin(login);
			Map<String, String> archivos = new HashMap<String, String>();
			archivos.put("#logo#", "images/back/logo1.png");
			//archivos.put("#logo_potencia#", "images/back/Logo_potencia.png");
			Map<String, String> parametros = new HashMap<String, String>();
			Contenido contenido_instructivo = ContenidoServicio.getInstance().obtenerContenido("LINKS", "LINK INSTRUCTIVO");
			String link_video = contenido_instructivo != null ? contenido_instructivo.getTexto() : "";
			String link_markup = CuentaServicio.getInstance().obtenerHost() + "/inicio/0.pub?markup=true";
			parametros.put("link_video", link_video);
			parametros.put("link_markup", link_markup);
			SourceResolver source = null;
			EnviaMails.enviar(persona.getCorreo(), persona.getNombreCompleto(), "Activacion Exitosa", "persona/notificacionClaveAsignada.email", archivos, parametros, source );
			SimpleLogger.setInfo("Notificacion de clave asignada enviada al login: " + login);
		} catch (Exception e) {
			SimpleLogger.setError("No se pudo enviar notificacion de clave asignada al login: " + login);
			e.printStackTrace();
		}
	}

	public Boolean confirmarCreacionUsuarios(){
		return StringUtils.esVerdad(ParametrosInicio.getProperty("ConfirmarCreacionUsuarios"));
	}
	
	public Boolean validarUsoToken(String login){
		
		Persona persona = PersonaServicio.getInstance().obtenerPersonaPorLogin(login);
		Boolean validar = true;
		
		if (persona != null) {
			List<String> roles_persona = PersonaServicio.getInstance().obtenerRolesPorPersona(persona.getId_persona());
			if (roles_persona != null) {
				validar = roles_persona.size() <= 0;
				
				String token_activo = null;

				for (String rol: roles_persona) {
					if (Constantes.ROL_ADMIN_CLIENTE.equals(rol)){
						token_activo = ConfiguracionServicio.getInstance().obtenerValorByEtiqueta("TOKEN_SIGNAPP_ADMINCLIENTE");
					} else if (Constantes.ROL_PREPARADOR.equals(rol)) {
						token_activo = ConfiguracionServicio.getInstance().obtenerValorByEtiqueta("TOKEN_SIGNAPP_PREPARADOR");
					} else if (Constantes.ROL_REVISOR.equals(rol)) { 
						token_activo = ConfiguracionServicio.getInstance().obtenerValorByEtiqueta("TOKEN_SIGNAPP_REVISOR");
					} else if (Constantes.ROL_LIBERADOR.equals(rol)) {
						token_activo = ConfiguracionServicio.getInstance().obtenerValorByEtiqueta("TOKEN_SIGNAPP_LIBERADOR");
					}
					
					if (token_activo != null) {
						validar = Constantes.SI.equals(token_activo) || validar;											
					}
					token_activo = null;
				}
			}
		}
		
		return validar;
	}
	
}
