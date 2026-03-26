package com.osmosyscol.datasuite.webdata.validacion.accion;

import java.util.List;
import java.util.Set;

import org.apache.cocoon.environment.Request;
import org.apache.cocoon.environment.Session;

import com.itosmosys.firmadigital.json.servicios.FirmaJsonServicio;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.AutenticacionServicio;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.commons.validacion.ResultadoValidacion;
import com.osmosyscol.commons.validacion.ValidacionAccion;
import com.osmosyscol.datasuite.logica.dto.Credencial;
import com.osmosyscol.datasuite.logica.dto.Menu;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.modelatos.logica.servicios.AsignacionReporteServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.MenuServicio;
import com.osmosyscol.datasuite.reportedim.dto.AsignacionReporte;
import com.osmosyscol.datasuite.webdata.logica.dto.MenuGrupoFormato;
import com.osmosyscol.datasuite.webdata.logica.servicios.AccesoRolServicio;
import com.osmosyscol.datasuite.webdata.logica.servicios.MenuGrupoFormatoServicio;
import com.osmosyscol.g3a.servicios.AutenticacionServicioG3A;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class Validacion implements ValidacionAccion {

	public ResultadoValidacion validar(Request request) {

		ResultadoValidacion resultadoValidacion = new ResultadoValidacion();

		// Verifica que el sistema tiene la informacion de la Persona en sesion
		try {
			if (request.getSession(false).getAttribute("persona") == null) {
				resultadoValidacion.setPermitido(false);
				resultadoValidacion.setSiguientePagina(AutenticacionServicio.PAGINA_INICIO);
				resultadoValidacion.setMensaje("Se han presentado Inconsistencias con la informacion de la session. La session se ha cerrado.\\nSi el problema continua, consulte con el administrador.");

				return resultadoValidacion;
			}
		} catch (Exception e) {
			SimpleLogger.setError("Problemas al realizar la validacion general", e);
			resultadoValidacion.setPermitido(false);
			resultadoValidacion.setSiguientePagina(AutenticacionServicio.PAGINA_ACCESODENEGADO);
		}

		// -----------------

		Boolean inactivoG3A = StringUtils.esVacio(ParametrosInicio.getProperty(AutenticacionServicioG3A.G3A_ENPOINT));

		// Verifica que el usuario tenga acceso desde la ip

		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		Integer id_usuario = (usuario != null) ? usuario.getId_usuario() : null;

		if (resultadoValidacion.isPermitido() && id_usuario != null && inactivoG3A) {

			String ip_valida = (String) request.getSession().getAttribute("IP_VALIDA");

			// si no se ha validado la ip lo redirecciona a la pagina de
			// validacion de ip
			if (ip_valida == null && !request.getRequestURI().equals(request.getContextPath() + "/validacion_ip/valida.do") && !request.getRequestURI().equals(request.getContextPath() + "/configuracion/80.do") && !request.getRequestURI().equals(request.getContextPath() + "/personas/terminos_condiciones.do")) {
				// resultadoValidacion.setPermitido(false);
				// resultadoValidacion.setSiguientePagina("/validacion_ip/valida.do");
			}

			if ("N".equalsIgnoreCase(ip_valida)) {
				request.getSession().removeAttribute("login");
				SimpleLogger.setError("La ip no es valida. Se niega el acceso");
				resultadoValidacion.setPermitido(false);
				resultadoValidacion.setSiguientePagina(AutenticacionServicio.PAGINA_INICIO);
				resultadoValidacion.setMensaje("La ubicación desde la cual esta accediendo actualmente no es permitida.\\nSi necesita mas información, consulte con el administrador.");

				return resultadoValidacion;
			}
		}

		// -----------------
		// Verifica si recibe parametros de firma

		if (request.getParameter("IDPXFRM") != null) {
			request.setAttribute("ADD_PARAMETERS", FirmaJsonServicio.parametros.get(request.getParameter("IDPXFRM")));
		}

		FirmaJsonServicio.parametros.remove(request.getParameter("IDPXFRM"));

		// -----------------
		// Verifica si el usuario acepta terminos y condiciones
		Credencial credencial = (Credencial) request.getSession().getAttribute("credencial");

		if (credencial != null) {

			// verifica si debe ingresar el token de ingreso de sesion

			Boolean solicitarTokenCliente = credencial.getId_usuario() != null && StringUtils.esVerdad(ParametrosInicio.getProperty("tokeningreso.cliente"));
			Boolean tokenSesionIngresado = (Boolean) request.getSession().getAttribute("tokenSesionIngresado");

			if (tokenSesionIngresado == null) {
				tokenSesionIngresado = false;
			}

			if (solicitarTokenCliente && !tokenSesionIngresado) {

				if (!request.getRequestURI().equals(request.getContextPath() + "/configuracion/80.do")) {

					resultadoValidacion.setPermitido(false);
					resultadoValidacion.setSiguientePagina("/configuracion/80.do");
				}

			} else {

				// primero valida que haya aceptado terminos y condiciones

				if (inactivoG3A && !StringUtils.esVerdad(credencial.getAcepta_terminos()) && !request.getRequestURI().equals(request.getContextPath() + "/personas/terminos_condiciones.do") && !request.getRequestURI().equals(request.getContextPath() + "/configuracion/80.do")) {

					System.out.println("VA A LA PAGINA DE TERMINOS = " + tokenSesionIngresado);

					resultadoValidacion.setPermitido(false);
					resultadoValidacion.setSiguientePagina("/personas/terminos_condiciones.do");

				} else {
					
					// verifica is debe cambiar la clave
					if (inactivoG3A && StringUtils.esVerdad(credencial.getAcepta_terminos()) && StringUtils.esVerdad(credencial.getCambiar_clave()) && !request.getRequestURI().equals(request.getContextPath() + "/configuracion/5.4.do") && !request.getRequestURI().equals(request.getContextPath() + "/configuracion/5.2.do") && !request.getRequestURI().equals(request.getContextPath() + "/validacion_ip/valida.do")) {
						
						System.out.println("VA A LA PAGINA DE CAMBIAR CLAVE");

						resultadoValidacion.setPermitido(false);
						resultadoValidacion.setSiguientePagina("/configuracion/5.4.do");
					}
				}

			}

		} else {
			System.out.println("LA CREDENCIAL NO ES VALIDA. SE CERRARA SESSION.");
			resultadoValidacion.setPermitido(false);
			resultadoValidacion.setSiguientePagina("/inicio/0.pub");
		}

		// -----------------------
		// Cargar menu para usuario preparador

		Session session = request.getSession();

		Integer id_negocio = (Integer) request.getSession().getAttribute("id_negocio");
		Integer id_persona = (Integer) request.getSession().getAttribute("id_persona");

		if (id_usuario != null) {
			List<MenuGrupoFormato> menusPreparador = MenuGrupoFormatoServicio.getInstance().obtenerMenusGrupoFormatoPorPreparador(id_usuario, id_negocio, id_persona, session);

			if (menusPreparador != null && menusPreparador.size() > 0) {
				session.setAttribute("MENU_PREPARADOR", menusPreparador);
			}
		}

		// -----------------------
		// Cargar reportes del usuario

		Integer id_administrativo = (Integer) session.getAttribute("id_administrativo");

		if (session.getAttribute("MENU_REPORTE") == null) {

			String login = (String) session.getAttribute("login");

			Set<AsignacionReporte> menuReporte = AsignacionReporteServicio.getInstance().obtenerReportesAsignadosUsuario(login, id_usuario, id_administrativo);

			session.setAttribute("MENU_REPORTE", menuReporte);
		}

		// -----------------
		// Verifica si el administrativo tiene acceso a la url sólo si la url hace
		// parte de un servicio
		String url = request.getRequestURI().substring(request.getContextPath().length());

		if (id_administrativo != null && id_usuario == null && AccesoRolServicio.getInstance().obtenerIdServicioPorURL(url) != null) {
			if (!AccesoRolServicio.getInstance().validarAccesoAdministrativoPorUrl(id_administrativo, url)) {
				SimpleLogger.setError("El administrativo no tiene permiso para ingresar a la url.. " + url);
				resultadoValidacion.setPermitido(false);
				resultadoValidacion.setSiguientePagina(AutenticacionServicio.PAGINA_INICIO);
				resultadoValidacion.setMensaje("No tiene permisos para acceder a la URL solicitada.\\nSi necesita mas información, consulte con el administrador.");
			}
		}

		if (id_usuario != null) {

			List<Menu> menus = MenuServicio.obtenerMenusPorAplicacion("WebData", session);

			if (menus != null) {

				boolean ok = true;

				for (Menu menu : menus) {

					if (menu.getAccion().equals(url)) {
						if (menu.getId_tipo_proceso() != null && !MenuServicio.validarAccesoPersona(usuario, id_persona, menu.getId_tipo_proceso(), StringUtils.esVerdad(menu.getValida_proceso()))) {
							ok = false;
							break;
						}
					}
				}

				if (!ok) {
					SimpleLogger.setError("El usuario no tiene permiso para ingresar a la url");
					resultadoValidacion.setPermitido(false);
					resultadoValidacion.setSiguientePagina(AutenticacionServicio.PAGINA_INICIO);
					resultadoValidacion.setMensaje("No tiene permisos para acceder a la URL solicitada.\\nSi necesita mas información, consulte con el administrador.");
				}
			}
		}

		
		// ----------------------------------------------------------------------------------------
		
		return resultadoValidacion;
	}

}
