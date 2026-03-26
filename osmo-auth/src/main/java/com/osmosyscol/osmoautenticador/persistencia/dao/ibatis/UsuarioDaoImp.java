package com.osmosyscol.osmoautenticador.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.osmoautenticador.dominio.GrupoAutenticacion;
import com.osmosyscol.osmoautenticador.dominio.TipoAutenticacion;
import com.osmosyscol.osmoautenticador.dominio.Usuario;
import com.osmosyscol.osmoautenticador.persistencia.dao.UsuarioDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class UsuarioDaoImp extends BaseSqlMapDao implements UsuarioDao {

	public UsuarioDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	public Usuario obtenerUsuario(String login, List<String> clave, String tipoclave, String aplicacion) {
		Map<String, Object> mapa_datos = new HashMap<String, Object>();

		mapa_datos.put("login", login);
		mapa_datos.put("clave", clave);
		mapa_datos.put("tipoclave", tipoclave == null ? "1" : tipoclave);
		mapa_datos.put("aplicacion", aplicacion);

		return (Usuario) queryForObject("Usuario.obtenerUsuario", mapa_datos);
	}

	public Boolean autorizarUsuario(String login, String url, String aplicacion) {
		Map<String, String> mapa_datos = new HashMap<String, String>();

		mapa_datos.put("login", login);
		mapa_datos.put("url", url);
		mapa_datos.put("aplicacion", aplicacion);

		return ((Integer) queryForObject("Usuario.autorizarUsuario", mapa_datos)) > 0;
	}

	public Boolean cambiarClave(String login, String tipoclave, String clave_nueva) {
		Map<String, String> mapa_datos = new HashMap<String, String>();

		mapa_datos.put("login", login);
		mapa_datos.put("clave_nueva", clave_nueva);

		if (tipoclave == null || "1".equals(tipoclave)) {
			return update("Usuario.cambiarClave1", mapa_datos) > 0;
		}

		if (tipoclave == null || "2".equals(tipoclave)) {
			return update("Usuario.cambiarClave2", mapa_datos) > 0;
		}

		return false;
	}

	public TipoAutenticacion obtenerTipoAutenticacion(String aplicacion) {
		return (TipoAutenticacion) queryForObject("Usuario.obtenerTipoAutenticacion", aplicacion);
	}

	public Boolean loginExiste(String login) {
		Integer existe = (Integer) queryForObject("Usuario.loginExiste", login);
		if (existe > 0) {
			return true;
		}
		return false;
	}

	public Usuario guardarUsuario(String login, String clave, String clave2) {

		Usuario usuario = new Usuario();

		Integer id_usuario = nextId();

		usuario.setId_usuario(id_usuario);
		usuario.setClave(clave);
		usuario.setClave2(clave2);
		usuario.setLogin(login);

		insert("Usuario.guardarUsuario", usuario);
		return usuario;
	}

	// ------------------------

	@SuppressWarnings("unchecked")
	public Boolean agregarRoles(int id_usuario, List<Integer> roles) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id_usuario", id_usuario);

		List<Integer> rolesViejos = queryForList("Usuario.obtenerIdRoles", paramMap);

		// Adiciona los roles nuevos
		for (Integer rol : roles) {
			if (rol != null && rol > 0) {

				boolean existe = false;
				for (Integer rolviejo : rolesViejos) {
					if (rol.intValue() == rolviejo.intValue()) {
						existe = true;
					}
				}

				if (!existe) {
					Map<String, Object> mapa = new HashMap<String, Object>();
					mapa.put("id_usuario", id_usuario);
					mapa.put("id_rol", rol);

					insert("Usuario.agregarRol", mapa);
				}
			}
		}

		// Elimina los roles
		for (Integer rol : roles) {
			if (rol != null && rol < 0) {

				Map<String, Object> mapa = new HashMap<String, Object>();
				mapa.put("id_usuario", id_usuario);
				mapa.put("id_rol", new Integer(-rol));

				insert("Usuario.eliminarRol", mapa);
			}
		}

		return true;
	}

	// ------------------------

	public Usuario obtenerUsuarioPorLogin(String login) {

		return (Usuario) queryForObject("Usuario.obtenerUsuarioPorLogin", login);
	}

	// ------------------------
	@SuppressWarnings("unchecked")
	public Boolean eliminarUsuario(int id_usuario) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id_usuario", id_usuario);

		List<Integer> roles = queryForList("Usuario.obtenerIdRoles", paramMap);

		for (Integer rol : roles) {
			if (rol != null) {

				Map<String, Object> mapa = new HashMap<String, Object>();
				mapa.put("id_usuario", id_usuario);
				mapa.put("id_rol", new Integer(rol));

				insert("Usuario.eliminarRol", mapa);
			}
		}

		delete("Usuario.eliminarUsuario", id_usuario);
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> listarUsuarios() {
		return queryForList("Usuario.listarUsuarios", null);
	}

	public GrupoAutenticacion obtenerGrupoAutenticacion(String nombreAplicacion) {

		return (GrupoAutenticacion) queryForObject("Usuario.obtenerGrupoAutenticacion", nombreAplicacion);
	}

	@SuppressWarnings("unchecked")
	public List<TipoAutenticacion> obtenerTiposAutenticacionGrupo(Integer idGrupo) {
		return queryForList("Usuario.obtenerTiposAutenticacionGrupo", idGrupo);
	}

	public Integer nextId() {
		return nextValue("oss_usuario");
	}

	public boolean eliminarRolLogin(String login, Integer idRol) {
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("login", login);
		mapa.put("id_rol", idRol);

		delete("Usuario.eliminarRolLogin", mapa);
		return true;
	}

	public boolean eliminarRolesLogin(String login, List<Integer> roles) {
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("login", login);
		mapa.put("roles", roles);

		delete("Usuario.eliminarRolesLogin", mapa);
		return true;
	}

	// ------------------------

}
