package com.osmosyscol.datasuite.logica.servicios.json;

import java.util.List;

import org.apache.cocoon.environment.Session;

import co.htsoft.commons.rsa.SecureJsonUtils;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.AutenticacionServicio;
import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.dto.TipoDocumento;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;

public class PersonaJSONServicio implements JsonService {

	private Session session;

	public String loginDisponible(String login) {

		Boolean disponible = PersonaServicio.getInstance().loginDisponible(login);

		if (disponible == null) {
			return "ERROR";
		}

		return disponible ? "S" : "N";

	}

	public Boolean activarDirectorNegocio(Integer id_persona, Integer id_negocio, String activo, String todos) {
		return PersonaServicio.getInstance().activarDirectorNegocio(id_persona, id_negocio, activo, todos);
	}

	public Boolean activarPersonaUsuario(Integer id_persona, Integer id_usuario, String activo, String rol, String todos) {
		return PersonaServicio.getInstance().activarPersonaUsuario(id_persona, id_usuario, activo, rol, todos);
	}

	public Boolean eliminarDirectorNegocio(Integer id_persona, Integer id_negocio) {
		return PersonaServicio.getInstance().eliminarDirectorNegocio(id_persona, id_negocio);
	}

	public Boolean eliminarPersonaUsuario(Integer id_persona, Integer id_usuario, String rol) {
		return PersonaServicio.getInstance().eliminarPersonaUsuario(id_persona, id_usuario, rol);
	}

	public List<TipoDocumento> obtenerTiposDocumento() {
		return PersonaServicio.getInstance().obtenerTiposDocumento();
	}

	public Boolean regenerarClaveUsuario(Integer id_persona, String rol) {
		Integer id_persona_us = (Integer)session.getAttribute("id_persona");
		
		String id_usuario_str = (String)session.getAttribute("var.id_usuario");
		Integer id_usuario = null;
		
		try{
			id_usuario = Integer.valueOf(id_usuario_str);
		}catch(Exception e){
			SimpleLogger.setError("Error id_usuario_str", e);
			id_usuario = (Integer) session.getAttribute("id_usuario");
			// validar si el usuario es administrador del cliente
			
			if(id_usuario != null && !PersonaServicio.getInstance().esAdminCliente(id_usuario,id_persona_us)){
				System.out.println("El usuario " + id_persona_us + " no es admin cliente para el usuario : " + id_usuario);
				return false;
			}
		}
		
		return PersonaServicio.getInstance().regenerarClaveUsuario(id_usuario, id_persona);
	}



	public Boolean eliminarPersonaRol(Integer id_persona, String rol) {
		
		Integer id_usuario = (Integer) session.getAttribute("id_usuario");
		
		return PersonaServicio.getInstance().eliminarPersonaRol(id_persona, rol, id_usuario);
	}

	private String obtenerLogin() {

		try {
			return (String) session.getAttribute("login");

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error.", e);

		}
		return null;
	}

	public Boolean existeHistorialLoginClave(String clave) {

		String login = obtenerLogin();

		PersonaServicio personaServicio = PersonaServicio.getInstance();
		Boolean existeHistorialLoginClave = personaServicio.existeHistorialLoginClave(login, clave);

		return existeHistorialLoginClave;

	}
	
	public Boolean enviarCorreoSobreflex(String login) {
		PersonaServicio personaServicio = PersonaServicio.getInstance();
		return personaServicio.enviarCorreoSobreflex(login);
	}

	public Boolean esClaveActualCorrecta(String clave) {
		String clave_decifrada = SecureJsonUtils.ds(clave);
		String login = obtenerLogin();
		AutenticacionServicio autenticacionServicio = AutenticacionServicio.getInstance();
		Boolean esClaveActualCorrecta = autenticacionServicio.autenticar(login, clave_decifrada, null);

		return esClaveActualCorrecta;
	}

	public void setSession(Session session) {
		this.session = session;
	}
	
	public Persona obtenerPersona(){
		Persona persona = PersonaServicio.getInstance().obtenerPersonaPorLogin(obtenerLogin());
		return persona;
	}
	
	public String obtenerCodigoLugarPorId(String id, String estructura, String nombreColumna){
		return PersonaServicio.getInstance().obtenerCodigoLugarPorId(id, estructura, nombreColumna);
	}
	
	public Integer obtenerIdTipoRevisorPorPersona(Integer id_persona){
		return PersonaServicio.getInstance().obtenerIdTipoRevisorPorPersona(id_persona);
	}
	
	public String obtenerIdentificacionPorCodigo(String codigo, String estructura, String nombreColumna, String nombreId){
		return PersonaServicio.getInstance().obtenerIdentificacionPorCodigo(codigo, estructura, nombreColumna, nombreId);
	}
	
}
