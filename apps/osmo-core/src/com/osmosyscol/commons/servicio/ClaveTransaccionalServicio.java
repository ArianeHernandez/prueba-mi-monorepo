package com.osmosyscol.commons.servicio;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.clavetxservicio.TokenCtx;
import com.osmosyscol.commons.utils.ParseUtils;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.commons.validacion.ResultadoValidacion;
import com.osmosyscol.datapi.logica.dto.RequiereClaveTX;
import com.osmosyscol.g3a.cliente.entidades.Operacion;
import com.osmosyscol.g3a.cliente.entidades.RespuestaOperacion;
import com.osmosyscol.g3a.cliente.entidades.ResultadoOperacion;
import com.osmosyscol.g3a.servicios.AutenticacionServicioG3A;
import com.osmosyscol.g3a.serviciosweb.wsaplicaciones.Aplicacion;
import com.osmosyscol.osmoautenticador.servicioweb.requiereCTX.RequiereCTXSOAPStub;
import com.osmosyscol.osmoautenticador.servicioweb.requiereCTX.TipoElementoEntradarequiereCTX;
import com.osmosyscol.osmoautenticador.servicioweb.requiereCTX.TipoElementoSalidarequiereCTX;
import com.osmosyscol.osmoautenticador.servicioweb.requiereCTX.TipoEntradarequiereCTX;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class ClaveTransaccionalServicio {

	private static final String SERVICES_WS_REQUIERE_CTX = "/ws/requiereCTX";
	private static final String TOKEN_CTX = "TOKEN_CTX";
	public static final String OK_RESPUESTA_PERMISOS = "0";
	private static final String SERVICE_CLAVE_TRANSACCIONAL = ParametrosInicio.getProperty("clavesso.endpoint");
	public static boolean CTX_ACTIVO = false;

	/** Constructor privado. */
	private ClaveTransaccionalServicio() {
	}

	/** Instancia única. */
	private static ClaveTransaccionalServicio instance;

	/**
	 * Obtener la instancia única del objeto de tipo ClaveTransaccionalServicio.
	 * 
	 * @return instancia de la clase ClaveTransaccionalServicio
	 */
	public static synchronized ClaveTransaccionalServicio getInstance() {
		if (instance == null) {
			instance = new ClaveTransaccionalServicio();
		}
		return instance;
	}

	/**
	 * Verifica si el servicio al que pertenece la página ingresada requiere clave transaccional
	 * 
	 * @param url
	 *            url a verificar
	 * @param usuario
	 *            usuario contra el cual verificar
	 * @return retorna: La pareja (id_servicio, S) si se requiere clave transaccional<br/>
	 *         La pareja (SIN_SERVICIO, N) Si no se requiere clave transaccional, o el usuario o el servicio no existe null si hubo un error
	 * @throws IOException
	 *             si ocurre un error de conexion con el servicio
	 */
	public RequiereClaveTX requiereClaveTransaccional(String url, String usuario) throws IOException {

		if (!CTX_ACTIVO || SERVICE_CLAVE_TRANSACCIONAL == null || AutenticacionServicio.getEndpoint() == null) {
			RequiereClaveTX requierectx = new RequiereClaveTX();
			requierectx.setId_servicio(null);
			requierectx.setRequerido(false);
			return requierectx;
		}

		URL urlServicio = new URL(AutenticacionServicio.getEndpoint() + SERVICES_WS_REQUIERE_CTX);

		RequiereCTXSOAPStub stub = new RequiereCTXSOAPStub(urlServicio, null);

		TipoElementoEntradarequiereCTX elementoEntrada = new TipoElementoEntradarequiereCTX(usuario, url);
		TipoEntradarequiereCTX entrada = new TipoEntradarequiereCTX(elementoEntrada);
		TipoElementoSalidarequiereCTX[] salida = stub.requiereCTX(entrada);

		RequiereClaveTX requierectx = new RequiereClaveTX();

		if (salida != null && salida.length > 0) {
			requierectx.setId_servicio(salida[0].getId_servicio());
			requierectx.setRequerido(StringUtils.esVerdad(salida[0].getRequiere()));
		}

		return requierectx;
	}

	public boolean limpiarToken(Session session) {
		session.removeAttribute(TOKEN_CTX);
		return true;
	}

	public ResultadoValidacion validarClaveTransaccional(Integer idServicio, Session session, String urlRetorno, String urlCancelacion) {

		Aplicacion aplicacion = obtenerAplicacionActual(session);
		String contexto = aplicacion.getEndpoint();

		// agregar contexto a las direcciones de redireccion

		if (!urlRetorno.startsWith("/")) {
			urlRetorno = "/" + urlRetorno;
		}
		if (!urlCancelacion.startsWith("/")) {
			urlCancelacion = "/" + urlCancelacion;
		}

		urlRetorno = contexto + urlRetorno;
		urlCancelacion = contexto + urlCancelacion;

		ResultadoValidacion resultado = new ResultadoValidacion();
		TokenCtx token = (TokenCtx) session.getAttribute(TOKEN_CTX);

		// Si tiene token en sesion
		if (token != null) {
			// si es del mismo servicio
			if (token.getServicio().equals(idServicio)) {

				// Verificar en G3A si la clave fue ingresada correctamente
				RespuestaOperacion respuestaPermisos = AutenticacionServicioG3A.getInstance().verificarPermisosTransaccion(session, token.getOperacion());

				if (OK_RESPUESTA_PERMISOS.equals(respuestaPermisos.getResultado().getOk())) {

					AutenticacionServicioG3A.getInstance().actualizarTicket(session, respuestaPermisos.getTicket());

					resultado.setPermitido(true);

					return resultado;
				}
			}

		}

		Integer idPersona = (Integer) session.getAttribute("id_persona");
		Integer idOper = ParseUtils.parseInt(idServicio + "" + idPersona, 0, false);

		Operacion op = new Operacion();
		op.setId(idOper);
		op.setTipoOperacion(idServicio);
		op.setUrlRetornoOperacion(urlRetorno);
		op.setUrlRetornoCancelacion(urlCancelacion);

		// Solicitar token
		RespuestaOperacion respuestaInicio = AutenticacionServicioG3A.getInstance().iniciarTransaccion(session, op);

		limpiarToken(session);

		// Redirigir a G3A para ingresar 2da clave se valida la respuesta del inicio de transaccion
		boolean inicioExitoso = false;
		try {
			
			inicioExitoso = OK_RESPUESTA_PERMISOS.equals(respuestaInicio.getResultado().getOk());

			if (inicioExitoso) {

				TokenCtx tokenNuevo = new TokenCtx();
				tokenNuevo.setOperacion(op);
				tokenNuevo.setServicio(idServicio);
				tokenNuevo.setTicket(respuestaInicio.getTicket());
				session.setAttribute(TOKEN_CTX, tokenNuevo);
			}

		} catch (Exception e) {
			SimpleLogger.setError("Error al iniciar la transaccion", e);
		}

		// si no fue exitoso se redirige a la pagina de cancelacion
		if (!inicioExitoso) {

			ResultadoOperacion resp = respuestaInicio.getResultado();
			String mensaje = resp == null ? "" : "( " + resp.getCodigoRespuesta() + " - " + resp.getMensajeRespuesta() + " ). ";

			resultado.setSiguientePagina(urlRetorno);
			resultado.setPermitido(false);
			resultado.setMensaje(mensaje);

			SimpleLogger.setDebug("Inicio de transaccion no exitoso. " + mensaje + " Redirigiendo a " + urlCancelacion);

		} else {// si fue exitoso se redirige a la pagina del SSO

			AutenticacionServicioG3A.getInstance().actualizarTicket(session, respuestaInicio.getTicket());

			String sessionId = respuestaInicio.getTicket().getSsid();
			String tokenValue = respuestaInicio.getTicket().getValorTicket();
			String filial = AutenticacionServicio.ID_APLICACION;

			// resultado.setSiguientePagina(SERVICE_CLAVE_TRANSACCIONAL + idOper + "&urlop=" + urlRetorno);
			resultado.setSiguientePagina(SERVICE_CLAVE_TRANSACCIONAL + "?ret=" + urlRetorno + "&id=" + idOper + "&SSID=" + sessionId + "&TVALUE=" + tokenValue + "&FIL=" + filial);
			resultado.setPermitido(false);

			SimpleLogger.setDebug("Redirigiendo a pagina de clave transaccional");
		}

		return resultado;
	}

	private Aplicacion obtenerAplicacionActual(Session session) {
		String login = (String) session.getAttribute(AutenticacionServicio.LOGIN_ATRIBUTO);
		Integer idApp = Integer.parseInt(AutenticacionServicio.ID_APLICACION);
		// calcular contexto para redireccion absoluta (requiere parametro g3a.aplicaciones.servicio.endpoint)
		List<Aplicacion> aplicaciones = AutenticacionServicioG3A.getInstance().obtenerAplicaciones(login);

		for (Aplicacion aplicacion : aplicaciones) {
			Integer appI = aplicacion.getId_aplicacion();
			boolean cond = appI.equals(idApp);
			if (cond) {
				return aplicacion;
			}
		}
		return null;
	}

}
