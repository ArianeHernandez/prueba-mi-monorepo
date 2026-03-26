package com.osmosyscol.osmoautenticador.persistencia.dao;

import java.util.List;

import com.osmosyscol.osmoautenticador.dominio.GrupoAutenticacion;
import com.osmosyscol.osmoautenticador.dominio.TipoAutenticacion;
import com.osmosyscol.osmoautenticador.dominio.Usuario;

public interface UsuarioDao {

	public Usuario obtenerUsuario(String login, List<String> pass, String tipoclave, String aplicacion);
	
	public Boolean autorizarUsuario(String login, String url, String aplicacion);

	public Boolean cambiarClave(String login, String tipoclave, String clave_nueva);

	public TipoAutenticacion obtenerTipoAutenticacion(String aplicacion);
	
	public GrupoAutenticacion obtenerGrupoAutenticacion(String nombreAplicacion);

	public Boolean loginExiste(String login);

	public Usuario guardarUsuario(String login, String clave, String clave2);

	public Boolean agregarRoles(int id_usuario, List<Integer> rol);

	public Usuario obtenerUsuarioPorLogin(String login);
	
	public Boolean eliminarUsuario(int integer );
	
	public List<Usuario> listarUsuarios();
	
	public List<TipoAutenticacion> obtenerTiposAutenticacionGrupo(Integer idGrupo);

	public Integer nextId();

	public boolean eliminarRolLogin(String login, Integer idRol);

	public boolean eliminarRolesLogin(String login, List<Integer> roles);
	
}
