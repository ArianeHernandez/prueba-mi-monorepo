package com.osmosyscol.datasuite.logica.servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.mensajeria.EnviaMails;
import com.osmosyscol.commons.servicio.AutenticacionServicio;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.Administrativo;
import com.osmosyscol.datasuite.logica.dto.Credencial;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class BloqueoCuentas {

	private Integer tiempoMaximo; // en minutos
	private Integer numeroIntentosMaximo;

	// ---------------------------------------------------

	private static BloqueoCuentas instance;

	// ---------------------------------------------------

	private BloqueoCuentas() {

		try {
			numeroIntentosMaximo = Integer.parseInt(ParametrosInicio.getProperty("sesion.acceso.numeroIntentos"));
		} catch (Exception e) {
			numeroIntentosMaximo = 5;
		}

		try {
			tiempoMaximo = Integer.parseInt(ParametrosInicio.getProperty("sesion.acceso.minutos_validacion"));
		} catch (Exception e) {
			tiempoMaximo = 30;
		}

	}

	// ---------------------------------------------------

	public static BloqueoCuentas getInstance() {
		if (instance == null) {
			instance = new BloqueoCuentas();
		}
		return instance;
	}

	// ---------------------------------------------------
	// ---------------------------------------------------

	private Map<String, List<Long>> accesosInvalidos = new HashMap<String, List<Long>>();

	// ---------------------------------------------------

	public synchronized void limpiarIntentos(String login) {
		accesosInvalidos.remove(login);
	}

	// ---------------------------------------------------

	public synchronized void registrarAccesoInvalido(String login) {

		List<Long> tiempos_invalidas = accesosInvalidos.get(login);

		if (tiempos_invalidas == null) {
			tiempos_invalidas = new ArrayList<Long>();
			accesosInvalidos.put(login, tiempos_invalidas);
		}

		tiempos_invalidas.add(System.currentTimeMillis());
	}

	// ---------------------------------------------------

	public synchronized Integer numeroIntentos(String login) {

		Long time = System.currentTimeMillis();

		Long maxtime = tiempoMaximo * 60L * 1000L;

		List<Long> tiempos_invalidas = accesosInvalidos.get(login);

		if (tiempos_invalidas == null) {
			return 0;
		}

		int intentos = 0;

		for (Long reg_time : tiempos_invalidas) {

			if (time - reg_time < maxtime) {
				intentos++;
			}

		}

		return intentos;
	}

	// ---------------------------------------------------

	public synchronized Boolean validarCuenta(String login) {

		// si ha sobrepasado el numero de intentos... el usuario se bloquea y envia correo al administrador
		if (numeroIntentosMaximo <= numeroIntentos(login)) {
			bloquearCuenta(login);
			enviarCorreoBloqueo(login);
			return false;
		}

		return true;
	}

	// ---------------------------------------------------

	private void enviarCorreoBloqueo(String login) {

		Usuario usuario = UsuarioServicio.getInstance().obtenerUsuarioPorLogin(login);

		List<Administrativo> administrativos = AdministrativoServicio.getInstance().obtenerAdministrativosActivos();

		if (administrativos != null) {
			for (Administrativo administrativo : administrativos) {

				if (administrativo.getId_usuario() == null || equals(administrativo.getId_usuario(), usuario.getId_usuario())) {

					Credencial ca = CredencialServicio.getInstance().obtenerCredencialPersonaUsuario(administrativo.getId_persona(), administrativo.getId_usuario());

					String url_desbloqueo = "/activacion_cuentas/40.do"; // servicio de activar cuentas

					Boolean valido = AutenticacionServicio.getInstance().autorizar(ca.getLogin(), url_desbloqueo);

					if (valido) {
						enviarNotificacionBloqueo(login, administrativo, ca);
					}
				}
			}
		}

	}

	private void enviarNotificacionBloqueo(String login_bloqueado, Administrativo administrativo, Credencial credencial_admin) {

		try {
			Persona persona_bloqueada = PersonaServicio.getInstance().obtenerPersonaPorLogin(login_bloqueado);
			Credencial credencial = PersonaServicio.getInstance().obtenerCredencialPorLogin(login_bloqueado);

			String html = ContenidoServicio.getInstance().obtenerContenido("EMAILS", "EMAIL NOTIFICACION BLOQUEO USUARIO").getTexto();
			html = EnviaMails.replazarTextos(html, persona_bloqueada, login_bloqueado, credencial);

			Persona persona = PersonaServicio.getInstance().obtenerPersona(credencial_admin.getId_persona());

			EnviaMails.enviarAsync(persona.getCorreo(), persona.getNombreCompleto(), "Cuenta de usuario bloqueada", "plano:" + html, null);
		} catch (Exception e) {
			SimpleLogger.setError(e);
		}

	}

	private static Boolean equals(Integer a, Integer b) {
		return (a == null && b == null) || (a.intValue() == b.intValue());
	}

	// ---------------------------------------------------

	public synchronized Boolean bloquearCuenta(String login) {
		try {
			return PersonaServicio.getInstance().actualizarEstadoCredencial(Constantes.CREDENCIAL_ESTADO_INVALIDO, login);
		} finally {
			SimpleLogger.setInfo("Bloqueo de cuenta: " + login);
		}
	}

	// ---------------------------------------------------

	public synchronized Boolean activarCuenta(String login) {
		return PersonaServicio.getInstance().actualizarEstadoCredencial(Constantes.CREDENCIAL_ESTADO_ACTIVO, login);
	}

	// ---------------------------------------------------
	// ---------------------------------------------------

	public Boolean registroValidacion(String login) {

		String endPointG3A = ParametrosInicio.getProperty("loginsso.endpoint");
		if (endPointG3A == null) {
			registrarAccesoInvalido(login);
			return validarCuenta(login);
		}

		return true;
	}

	// ---------------------------------------------------
	// ---------------------------------------------------

}
