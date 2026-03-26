// CVS $Id$
package com.osmosyscol.osmoautenticador.servicio;

import java.util.ArrayList;
import java.util.List;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.osmoautenticador.dominio.GrupoAutenticacion;
import com.osmosyscol.osmoautenticador.dominio.Mensaje;
import com.osmosyscol.osmoautenticador.dominio.TipoAutenticacion;
import com.osmosyscol.osmoautenticador.dominio.Usuario;
import com.osmosyscol.osmoautenticador.persistencia.dao.UsuarioDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class UsuarioServicio {

	private static UsuarioServicio usuarioServicio;

	public static UsuarioServicio getInstance() {
		if (usuarioServicio == null) {
			usuarioServicio = new UsuarioServicio();
		}
		return usuarioServicio;
	}

	private UsuarioServicio() {

	}

	// --------------------------------------

	private static String[] ALGORITMOS = null;

	public static String[] algoritmosClave() {

		if (ALGORITMOS == null) {
			String cifradoAlgoritmo = ParametrosInicio.getProperty("cifrado.algoritmo");

			if (StringUtils.isNotEmpty(cifradoAlgoritmo)) {
				ALGORITMOS = cifradoAlgoritmo.toUpperCase().replace(" ", "").split("[,]");
				return ALGORITMOS;
			}

			ALGORITMOS = new String[] { "MD5" };
		}

		return ALGORITMOS;
	}

	public static String algoritmoClave() {
		algoritmosClave();
		return ALGORITMOS[0];
	}

	public static String generarClave(String login, String clave, String sep, String algoritmo) {

		String usuario = login.toLowerCase().trim() + sep + clave;

		return StringUtils.cifrar(usuario, algoritmo);
	}

	// -----------

	public static String generarClave(String login, String clave) {
		return generarClave(login, clave, "|");
	}

	public static String generarClave(String login, String clave, String sep) {
		return generarClave(login, clave, sep, algoritmoClave());
	}

	// -----------

	public static List<String> generarClaves(String login, String clave) {

		String[] algoritmos = algoritmosClave();

		List<String> claves = new ArrayList<String>();

		for (int i = 0; i < algoritmos.length; i++) {
			claves.add(generarClave(login, clave, "", algoritmos[i]));
			claves.add(generarClave(login, clave, "|", algoritmos[i]));
		}

		return claves;

	}

	// --------------------------------------

	public Boolean autenticar(String login, String clave, String tipoclave, String aplicacion) {

		UsuarioDao usuarioDao = (UsuarioDao) DaoConfig.getDao(UsuarioDao.class);

		if (login == null || clave == null) {
			return false;
		}

		login = login.trim();

		List<String> claves = generarClaves(login, clave);

		Boolean existe = usuarioDao.obtenerUsuarioPorLogin(login) != null;

		if (!existe) {
			SimpleLogger.setInfo("El usuario " + login + " no existe en la autenticacion.");
			return false;
		}

		Boolean autenticado = (usuarioDao.obtenerUsuario(login, claves, tipoclave, aplicacion) != null);

		if (autenticado) {
			usuarioDao.cambiarClave(login, tipoclave, generarClave(login, clave));
		} else {
			SimpleLogger.setInfo("Usuario '" + login + "' No ha sido Autenticado. Clave Incorrecta.");
		}

		return autenticado;
	}

	// --------------------------------------

	public Boolean autorizar(String login, String url, String aplicacion) {
		UsuarioDao usuarioDao = (UsuarioDao) DaoConfig.getDao(UsuarioDao.class);
		Boolean autorizado = usuarioDao.autorizarUsuario(login.trim(), url, aplicacion);

		if (autorizado) {
			SimpleLogger.setInfo("Usuario '" + login + "' ha sido Autorizado. {" + url + " | " + aplicacion + "}");
		} else {
			SimpleLogger.setInfo("Usuario '" + login + "' NO ha sido Autorizado. {" + url + " | " + aplicacion + "}");
		}

		return autorizado;
	}

	// --------------------------------------

	public Mensaje cambiarClave(String login, String clave, String tipoclave, String clave_nueva, String aplicacion) {
		UsuarioDao usuarioDao = (UsuarioDao) DaoConfig.getDao(UsuarioDao.class);
		Mensaje mensaje = new Mensaje();

		if (clave == null) {
			clave = "";
		}

		if (login == null) {
			mensaje.setExitoso(false);
			mensaje.setMensaje("La clave ingresada");
			mensaje.setEstado("FALLO.LOGIN");

			return mensaje;
		}

		login = login.trim();

		if (autenticar(login, clave, tipoclave, aplicacion)) {

			String md5clave_nueva = generarClave(login, clave_nueva);

			try {
				if (usuarioDao.cambiarClave(login, tipoclave, md5clave_nueva)) {
					mensaje.setExitoso(true);
					mensaje.setMensaje("Cambio Exitoso");
					mensaje.setEstado("EXITO");
				}

			} catch (Exception e) {
				mensaje.setExitoso(false);
				mensaje.setMensaje("Ha ocurrido un error al intentar cambia la contraseña. Consulte con su administrador.");
				mensaje.setEstado("FALLO.UPDATE");
			}

		} else {

			mensaje.setExitoso(false);
			mensaje.setMensaje("La clave ingresada no es correcta");
			mensaje.setEstado("FALLO.LOGIN");
		}

		return mensaje;

	}

	// --------------------------------------

	public Mensaje guardarUsuario(String login, String clave, Integer[] roles) {

		UsuarioDao usuarioDao = (UsuarioDao) DaoConfig.getDao(UsuarioDao.class);

		Mensaje mensaje = new Mensaje();

		if (login == null) {
			mensaje.setExitoso(false);
			mensaje.setMensaje("El login ingresado");
			mensaje.setEstado("FALLO.LOGIN");

			return mensaje;
		}

		List<Integer> rolesList = new ArrayList<Integer>();
		if (roles != null && roles.length > 0) {
			for (Integer rol : roles) {
				if (rol != null && rol != 0) {
					rolesList.add(rol);
				}
			}
		}

		login = login.toLowerCase().trim();

		String baseclave = clave;
		if (baseclave == null) {
			baseclave = "";
		}

		String clave_cfr = generarClave(login, baseclave);
		String clave2_cfr = generarClave(login, "AICO");

		if (!loginExiste(login)) {

			try {

				Usuario usuario;

				usuario = usuarioDao.guardarUsuario(login, clave_cfr, clave2_cfr);

				if (usuario != null) {
					usuarioDao.agregarRoles(usuario.getId_usuario(), rolesList);
					mensaje.setExitoso(true);
					mensaje.setMensaje("Roles Agregados");
					mensaje.setEstado("EXITO");
				}

			} catch (Exception e) {
				mensaje.setExitoso(false);
				mensaje.setMensaje("Ha ocurrido un error al intentar crear el usuario. Consulte con su administrador.");
				mensaje.setEstado("FALLO.UPDATE");
			}

		} else {

			Usuario usuario = usuarioDao.obtenerUsuarioPorLogin(login);
			usuarioDao.agregarRoles(usuario.getId_usuario(), rolesList);

			if (clave != null) {
				usuarioDao.cambiarClave(login, "1", clave_cfr);
			}

			mensaje.setExitoso(true);
			mensaje.setMensaje("Roles Modificados");
			mensaje.setEstado("EXITO");
		}

		return mensaje;
	}

	// --------------------------------------

	// --------------------------------------

	public Boolean loginExiste(String login) {
		UsuarioDao usuarioDao = (UsuarioDao) DaoConfig.getDao(UsuarioDao.class);
		return usuarioDao.loginExiste(login);
	}

	// --------------------------------------

	public Integer nextId() {
		UsuarioDao usuarioDao = (UsuarioDao) DaoConfig.getDao(UsuarioDao.class);
		return usuarioDao.nextId();
	}

	// --------------------------------------

	public Mensaje eliminarUsuario(String login) {
		UsuarioDao usuarioDao = (UsuarioDao) DaoConfig.getDao(UsuarioDao.class);

		Mensaje mensaje = new Mensaje();
		mensaje.setExitoso(false);

		if (login == null) {
			mensaje.setMensaje("El login ingresado");
			mensaje.setEstado("FALLO.LOGIN");

			return mensaje;
		}
		Usuario usuario = usuarioDao.obtenerUsuarioPorLogin(login);

		if (usuario == null) {
			mensaje.setMensaje("El login no existe");
			mensaje.setEstado("FALLO.LOGIN");
		} else {
			usuarioDao.eliminarUsuario(usuario.getId_usuario());
			mensaje.setExitoso(true);
			mensaje.setMensaje("Usuario eliminado");
			mensaje.setEstado("EXITO");
		}
		return mensaje;
	}

	// --------------------------------------
	public List<Usuario> listarUsuarios() {
		UsuarioDao usuarioDao = (UsuarioDao) DaoConfig.getDao(UsuarioDao.class);
		return usuarioDao.listarUsuarios();
	}

	// --------------------------------------

	public GrupoAutenticacion obtenerGrupoAutenticacion(String nombreAplicacion) {
		UsuarioDao usuarioDao = (UsuarioDao) DaoConfig.getDao(UsuarioDao.class);
		return usuarioDao.obtenerGrupoAutenticacion(nombreAplicacion);
	}

	public List<TipoAutenticacion> obtenerTiposAutenticacionGrupo(Integer idGrupo) {
		UsuarioDao usuarioDao = (UsuarioDao) DaoConfig.getDao(UsuarioDao.class);
		return usuarioDao.obtenerTiposAutenticacionGrupo(idGrupo);
	}

	public Mensaje eliminarRolLogin(String login, Integer idRol) {

		Mensaje mensaje = new Mensaje();
		mensaje.setExitoso(false);
		String estado = "FALLO";
		String mens = "LA ELIMINACION FALLO";

		try {
			UsuarioDao usuarioDao = (UsuarioDao) DaoConfig.getDao(UsuarioDao.class);
			boolean resultado = usuarioDao.eliminarRolLogin(login, idRol);

			if (resultado) {
				mensaje.setExitoso(true);
				estado = "EXITO";
				mens = "ELIMINACION DE ROL EXITOSA";
			}

		} catch (Exception e) {
			SimpleLogger.setError("Error eliminando el rol " + idRol + " al usuario " + login, e);
		}

		mensaje.setMensaje(mens);
		mensaje.setEstado(estado);
		return mensaje;
	}

	public Mensaje eliminarRolesLogin(String login, List<Integer> roles) {

		Mensaje mensaje = new Mensaje();

		String mens;
		String estado;
		try {
			for (Integer rol : roles) {
				Mensaje men = eliminarRolLogin(login, rol);
				if (!men.getExitoso()) {
					return men;
				}
			}

			mensaje.setExitoso(true);
			estado = "EXITO";
			mens = "LA ELIMINACION DE LOS ROLES FUE EXITOSA";

		} catch (Exception e) {
			mensaje.setExitoso(false);
			estado = "FALLO";
			mens = "LA ELIMINACION FALLO";
			SimpleLogger.setError("Error eliminando los roles del usuario " + login, e);
		}

		mensaje.setMensaje(mens);
		mensaje.setEstado(estado);
		return mensaje;
	}

}
