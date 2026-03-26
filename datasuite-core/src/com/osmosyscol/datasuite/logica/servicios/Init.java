package com.osmosyscol.datasuite.logica.servicios;

import java.util.ArrayList;
import java.util.List;

import org.apache.cocoon.environment.Request;
import org.apache.cocoon.environment.Session;
import org.apache.cocoon.environment.SourceResolver;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.AutenticacionServicio;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.Administrador;
import com.osmosyscol.datasuite.logica.dto.Contenido;
import com.osmosyscol.datasuite.logica.dto.Credencial;
import com.osmosyscol.datasuite.logica.dto.Negocio;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.dto.TipoDocumento;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.g3a.servicios.AutenticacionServicioG3A;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

import co.htsoft.htsauditoria.client.HTSAuditoriaServicio;
import co.htsoft.htsauditoria.client.dto.RegistroAuditoria;

public class Init {

	private static Init initServicio;

	public static Init getInstance() {
		if (initServicio == null)
			initServicio = new Init();
		return initServicio;
	}

	// -----------------------------------------------------------

	public List<Object> inicializarInformacionUsuario(Request request, SourceResolver resolver) {

		List<Object> info = new ArrayList<Object>();
		try {

			Session session = request.getSession(false);

			Boolean activarSipPagos = StringUtils.esNoVacio(ParametrosInicio.getProperty("sippagos.endpoint"));

			session.setAttribute("activar_SipPagos", activarSipPagos);

			String login = null;

			String endPointG3A = ParametrosInicio.getProperty("loginsso.endpoint");

			Persona personalogin = null;
			if (StringUtils.esNoVacio(endPointG3A)) {
				login = (String) session.getAttribute(AutenticacionServicioG3A.LOGIN);
				personalogin = SincronizacionG3AServicio.getInstance().obtenerPersona(login, session);
			} else {
				login = request.getParameter("auth_username").toUpperCase();
				personalogin = PersonaServicio.getInstance().obtenerPersonaPorLogin(login);
			}

			{
				HTSAuditoriaServicio.setSession_id(session.getId());
				HTSAuditoriaServicio.setLogin(login);
				
				HTSAuditoriaServicio.insertarRegistro(new RegistroAuditoria("LOGIN", true, "Inicio de Sesion."));
			}

			BloqueoCuentas.getInstance().limpiarIntentos(login);

			Credencial credencial = PersonaServicio.getInstance().obtenerCredencialPorLogin(login);
			credencial.setIp_ingreso(request.getRemoteAddr());

			if (credencial == null || !Constantes.CREDENCIAL_ESTADO_ACTIVO.equals(credencial.getEstado())) {
				Contenido contenido = ContenidoServicio.getInstance().obtenerContenido("General", "CUENTA_INACTIVA");
				String msj = "Su cuenta se encuentra bloqueada, por favor comuníquese con el administrador.";
				if (contenido != null) {
					msj = contenido.getTexto();
				}
				AutenticacionServicio.adicionarMensaje(request, msj);
				session.removeAttribute("login");
				return null;
			}

			if (personalogin != null) {

				info.add(personalogin);

				Usuario usuario = UsuarioServicio.getInstance().obtenerUsuarioPorLogin(credencial.getLoginUsuario());

				if (usuario != null && Constantes.NO.equals(usuario.getActivo())) {
					Contenido contenido = ContenidoServicio.getInstance().obtenerContenido("General", "CLIENTE_INACTIVO");
					String msj = "El cliente se encuentra inactivo";
					if (contenido != null) {
						msj = contenido.getTexto();
					}
					AutenticacionServicio.adicionarMensaje(request, msj);
					session.removeAttribute("login");
				}

				Boolean esWebData = "webdata".equalsIgnoreCase(AutenticacionServicio.NOMBRE_APLICACION);

				if (usuario != null && Constantes.SI.equals(usuario.getActivo()) && activarSipPagos && esWebData) {
					// FideicomisoClienteServicio.getInstance().actualizarNegociosUsuario(usuario.getId_persona(), usuario.getId_usuario());
				}

				// ------------- Actualiza los negocios si es director de
				// negocio

				Administrador administrador = AdministradorServicio.getInstance().obtenerAdministradorPorLogin(login);

				if (administrador != null && activarSipPagos) {
					String login2 = login;
					session.setAttribute("id_administrador", administrador.getId_administrador());
					if (login.contains("|")) {
						login2 = org.apache.commons.lang.StringUtils.substringAfter(login, "|");
					}

					Boolean ejecutado = NegocioServicio.getInstance().obtenerNegociosPorAdministrador(resolver, login2, administrador.getId_administrador());

					if (!ejecutado) {
						AutenticacionServicio.adicionarMensaje(request, "No se pudo realizar la actualización de negocios asociados al usuario.");
					}
				}

				List<Negocio> negocios = null;

				if (personalogin.getTipo_persona().equals("N") && usuario != null && administrador == null) {
					negocios = NegocioServicio.getInstance().obtenerNegociosPorUsuario(usuario.getId_usuario());
				} else {
					negocios = NegocioServicio.getInstance().obtenerListadoNegociosPorLogin(login);

				}

				session.setAttribute(Constantes.NEGOCIOS_USUARIO, negocios);

				Boolean confListNeg = Boolean.parseBoolean(ParametrosInicio.getProperty("DisponibleListadoNegocios"));
				if (!confListNeg) {

					Negocio negocio = new Negocio();
					negocio.setId_negocio(0);
					negocio.setCod_negocio("0");
					negocio.setActivo("S");
					negocio.setNombre("Negocio Cero");
					negocio.setId_modelo(1);

					ArrayList<Negocio> negociosList = new ArrayList<Negocio>();
					negociosList.add(negocio);
					negocios = negociosList;
				}

				// --------------

				if (negocios != null && !negocios.isEmpty()) {
					session.setAttribute("id_negocio", negocios.get(0).getId_negocio());
					session.setAttribute("cod_negocio", negocios.get(0).getCod_negocio());
					session.setAttribute("nombre_negocio", negocios.get(0).getNombre());
					session.setAttribute("id_modelo", negocios.get(0).getId_modelo());
					for (int i = 0; i < negocios.size(); i++) {
						info.add(negocios.get(i));
					}
				} else {
					session.setAttribute("id_modelo", 1);
					// TODO: Ajustar el modelo por defecto para los que no
					// tienen negocios.
				}

				session.setAttribute("persona", personalogin);
				session.setAttribute("id_persona", personalogin.getId_persona());
				session.setAttribute("tipo_persona", personalogin.getTipo_persona());
				session.setAttribute("tipo_documento", personalogin.getTipo_documento());
				session.setAttribute("identificacion", personalogin.getIdentificacion());
				session.setAttribute("nombre_persona", personalogin.getNombreCompleto());
				session.setAttribute("credencial", credencial);

				info.add(credencial);

				PersonaServicio.getInstance().actualizarFechaIngreso(login, HorarioServicio.getInstance().obtenerFechaActual());
				PersonaServicio.getInstance().actualizarIPIngreso(login, request.getRemoteAddr());

				Integer id_usuario = null;

				if (usuario != null) {
					session.setAttribute("usuario", usuario);
					session.setAttribute("id_usuario", usuario.getId_usuario());
					session.setAttribute("nombre_usuario", usuario.getNombre() + " " + StringUtils.trimToEmpty(usuario.getApellido()));
					session.setAttribute("identificacion_usuario", usuario.getIdentificacion());
					TipoDocumento tipoDocumentoUsuario = PersonaServicio.getInstance().obtenerTipoDocumento(Integer.parseInt(usuario.getTipo_documento()));
					if (tipoDocumentoUsuario != null) {
						session.setAttribute("tipo_documento_usuario", tipoDocumentoUsuario.getNombre());
						session.setAttribute("tipo_documento_usuario_id", tipoDocumentoUsuario.getId());
					}

					info.add(usuario);

					id_usuario = usuario.getId_usuario();
				}

				Integer id_administrativo = AdministrativoServicio.getInstance().obtenerIdAdministrativoPersona(personalogin.getId_persona(), id_usuario);

				if (id_administrativo != null) {
					session.setAttribute("id_administrativo", id_administrativo);
				}

			}

			return info;
		} catch (Exception e) {

			SimpleLogger.setError("No se puede inicializar session por Error al cargar informacion del usuario", e);

			// remueve el atributo "login" para no ser autorizado
			request.getSession().removeAttribute("login");
		}

		return null;
	}

}
