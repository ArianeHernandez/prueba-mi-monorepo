package com.osmosyscol.datasuite.logica.servicios;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.mensajeria.EnviaMails;
import com.osmosyscol.commons.utils.Mensaje;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.CambioPinSolicitud;
import com.osmosyscol.datasuite.logica.dto.Contenido;
import com.osmosyscol.datasuite.logica.dto.Credencial;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.dto.RespuestaSolicitudRegistro;
import com.osmosyscol.datasuite.modelatos.logica.clientes.ClienteWS;
import com.osmosyscol.osmoautenticador.servicioweb.RolesUsuario.TipoElementoSalidalistarRolesporLogin;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class CambioPinServicio {

	private static HashMap<Integer, CambioPinSolicitud> SOLICITUDES = new HashMap<Integer, CambioPinSolicitud>();
	private static int contador = 0;
	private static CambioPinServicio registroServicio;

	private CambioPinServicio() {
	}

	public static CambioPinServicio getInstance() {

		if (registroServicio == null) {
			registroServicio = new CambioPinServicio();
		}

		return registroServicio;
	}

	public static Mensaje<RespuestaSolicitudRegistro> validarRegistro(String login) {

		try {

			Credencial credencialLogin = PersonaServicio.getInstance().obtenerCredencialPorLogin(login);
			
			if (credencialLogin == null ) {
				return new Mensaje<RespuestaSolicitudRegistro>("99", "Lo sentimos, El nombre de usuario no es valido.", false);
			}
			if (Constantes.CREDENCIAL_ESTADO_CREADO.equals(credencialLogin.getEstado()) ) {
				PersonaServicio.getInstance().regenerarClaveUsuario(credencialLogin.getId_usuario(), credencialLogin.getId_persona());
				return new Mensaje<RespuestaSolicitudRegistro>("89", "No se ha realizado la activación de la cuenta del usuario, se ha enviado nuevamente al correo electrónico registrado, el mensaje para que realice la activación de su cuenta. "
																+ "En caso de no recibir el mensaje de activación de cuenta en los próximos 10 min por favor revise su bandeja del spam o contacte al área de soporte de la Entidad, comunicándose "
																+ "al teléfono 2201000 ext. 3020 o al correo soporte@supersociedades.gov.co", false);
			}else if (Constantes.CREDENCIAL_ESTADO_INVALIDO.equals(credencialLogin.getEstado())) {
				return new Mensaje<RespuestaSolicitudRegistro>("89", "Su cuenta se encuentra bloqueada, por favor contacte al área de soporte de la Entidad, "
																+ "comunicandose al telefono 2201000 ext 3020 o al correo soporte@supersociedades.gov.co.", false);
			}
			
			Integer id_persona = credencialLogin.getId_persona();
			
			Persona persona = PersonaServicio.getInstance().obtenerPersona(id_persona);
			
			// realiza solicitud de cambio de pin
			{
				Integer id_solicitud = contador;
				contador++;
				// define le pin a enviar en el correo
				String token = generarPin();

				// registra la solicitud
				
				
				{
					CambioPinSolicitud solicitud = new CambioPinSolicitud();
					solicitud.setCorreo(persona.getCorreo());
					solicitud.setPin(token);
					solicitud.setNumeroIdentificacion(persona.getIdentificacion());
					solicitud.setLogin(login);
					SOLICITUDES.put(id_solicitud, solicitud );
//					id_solicitud = guardarRegistro(registro);
				}

				// obtiene el nombre de la empresa para enviarlo en los mensajes
				String nombre = ParametrosInicio.getProperty("nombreEmpresa");

				Boolean pinEnviado = false;

				// sino se pudo enviar, lo envia por correo

				String texto = "";

				Contenido contenido = ContenidoServicio.getInstance().obtenerContenido("General", "TEXTO CORREO CAMBIO PIN");
				if (contenido != null && contenido.getEtiqueta() != null) {
					texto = contenido.getTexto();
				} else {
					texto = "<h4>Registro {NOMBRE_EMPRESA}</h4> <p>El token para realizar el cambio de clave es: <strong>{PIN}</strong></p>";
				}
				Map<String, String> params = new HashMap<String, String>();
				params.put("NOMBRE_EMPRESA", nombre);
				params.put("NOMBRE_CLIENTE", persona.getNombreCompleto());
				params.put("PIN", token);

				String html = "plano:" + reemplazarTextos(texto, params);

				try {
					EnviaMails.enviar(persona.getCorreo(), persona.getNombreCompleto(), "Cambio de clave " + nombre, html, null);
					pinEnviado = true;
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (!pinEnviado) {
					return new Mensaje<RespuestaSolicitudRegistro>("98", "Lo sentimos, en el momento no es posible enviarte un mensaje por SMS.", false);
				}

				Boolean existeOsmoautenticador = false;

				// verifica si tiene roles en el osmoautenticador

				if (login != null) {
					TipoElementoSalidalistarRolesporLogin[] roles = ClienteWS.getInstance().obtenerRolesPorLogin(login);
					existeOsmoautenticador = (roles != null && roles.length > 0);
				}

				RespuestaSolicitudRegistro resp = new RespuestaSolicitudRegistro(id_solicitud.toString(), existeOsmoautenticador);
				return new Mensaje<RespuestaSolicitudRegistro>("0", "Solicitud de registro creada con éxito", true, resp);

			}

		} catch (Exception e) {
			e.printStackTrace();
			return new Mensaje<RespuestaSolicitudRegistro>("99", "Lo sentimos, en el momento no es posible realizar el registro.", false);
		}

	}

	private static String generarPin() {
		return StringUtils.randomString(6, "0123456789");
	}

	public static String reemplazarTextos(String texto, Map<String, String> params) {
		if (texto == null) {
			return null;
		}

		Set<String> keys = params.keySet();
		for (String key : keys) {
			String value = params.get(key);

			texto = texto.replace("{" + key + "}", value.toString());
		}

		return texto;
	}

	public static Mensaje<Boolean> finalizarCambioPin(Integer codigoSolicitud, String pinCelular, String claveNueva) {
		try {
			CambioPinSolicitud solicitud = null;
			
			if(SOLICITUDES.containsKey(codigoSolicitud)){
				solicitud = SOLICITUDES.get( codigoSolicitud);
			}

			if (solicitud== null || !pinCelular.equals(solicitud.getPin())) {
				return new Mensaje<Boolean>("97", "El código de verificacion no coincide.", false);
			}

			boolean guardado = com.osmosyscol.commons.servicio.UsuarioServicio.getInstance().guardarUsuario(solicitud.getLogin(), claveNueva, new int[] {});

			if (!guardado) {
				return new Mensaje<Boolean>("98", "Lo sentimos, en estos momentos no es posible vincular la cuenta.", false);
			}

			return new Mensaje<Boolean>("0", "Registro Exitoso", true);
		} catch (Exception e) {
			e.printStackTrace();
			return new Mensaje<Boolean>("80", "Ocurrio un error.", true);
		}
		
	}
	
	

}
