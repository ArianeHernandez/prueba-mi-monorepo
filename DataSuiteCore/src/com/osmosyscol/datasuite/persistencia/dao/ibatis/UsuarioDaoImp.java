package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.config.Constantes;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.dto.UsuarioNegocio;
import com.osmosyscol.datasuite.persistencia.dao.UsuarioDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class UsuarioDaoImp extends BaseSqlMapDao implements UsuarioDao {

	public UsuarioDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	public Integer contarUsuariosPorNegocio(Integer id_negocio) {
		return (Integer) queryForObject("Usuario.contarUsuariosPorNegocio", id_negocio);
	}

	@SuppressWarnings("unchecked")
	public List<UsuarioNegocio> obtenerUsuariosPorNegocio(Integer id_negocio, Integer pagina) {
		return queryForListPag("Usuario.obtenerUsuariosPorNegocio", id_negocio, pagina, Constantes.PAGINACIONLISTADO);
	}

	public Boolean activarUsuario(Integer id_usuario, String activo) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_usuario", id_usuario);
		map.put("activo", activo);

		update("Usuario.activarUsuario", map);

		return true;
	}

	public Boolean eliminarUsuarioNegocio(Integer id_usuario, Integer id_negocio) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_usuario", id_usuario);
		map.put("id_negocio", id_negocio);
		delete("Usuario.eliminarUsuarioNegocio", map);
		return true;
	}

	public UsuarioNegocio obtenerUsuarioPorIdentificacionNegocio(String tipo_persona, String identificacion, Integer id_negocio, Integer tipo_documento) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("tipo_persona", tipo_persona);
		map.put("identificacion", identificacion);
		map.put("id_negocio", id_negocio);
		map.put("tipo_documento", tipo_documento);

		return (UsuarioNegocio) queryForObject("Usuario.obtenerUsuarioPorIdentificacionNegocio", map);
	}

	public Integer guardarUsuario(Usuario usuario) {

		Usuario usuario_act = obtenerUsuarioPorIdPersona(usuario.getId_persona());

		if (usuario_act != null) {
			usuario.setId_usuario(usuario_act.getId_usuario());
			if (actualizarUsuario(usuario)) {
				return usuario_act.getId_usuario();
			} else {
				return -1;
			}
		}

		Integer id = obtenerSiguienteIdUsuario();
		usuario.setId_usuario(id);
		insert("Usuario.guardarUsuario", usuario);

		return id;
	}

	public Integer obtenerSiguienteIdUsuario() {

		return (Integer) queryForObject("Usuario.obtenerSiguienteIdUsuario", null);
	}

	public Boolean actualizarUsuario(Usuario usuario) {

		update("Usuario.actualizarUsuario", usuario);

		return true;
	}

	public Usuario obtenerUsuarioPorIdPersona(Integer id_persona) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_persona", id_persona);

		return (Usuario) queryForObject("Usuario.obtenerUsuarioPorIdPersona", map);

	}

	public Boolean guardarUsuarioNegocio(Integer id_usuario, Integer id_negocio) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_usuario", id_usuario);
		map.put("id_negocio", id_negocio);

		insert("Usuario.guardarUsuarioNegocio", map);

		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> obtenerUsuariosPorAdminCliente(Integer id_persona, Integer id_negocio) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_persona", id_persona);
		map.put("id_negocio", id_negocio);

		return queryForList("Usuario.obtenerUsuariosPorAdminCliente", map);
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> obtenerUsuariosActivos() {
		return queryForList("Usuario.obtenerUsuariosActivos", null);
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> obtenerUsuariosOrden(String orden) {
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("orden", orden);
		
		return queryForList("Usuario.obtenerUsuariosOrden", map);
	}
	
	public Usuario obtenerUsuarioPorLogin(String login) {

		return (Usuario) queryForObject("Usuario.obtenerUsuarioPorLogin", login);
	}

	public Usuario obtenerUsuario(Integer idUsuario) {
		return (Usuario) queryForObject("Usuario.obtenerUsuario", idUsuario);
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> buscarUsuariosActivos(String texto, String tipo_cliente) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("texto", texto);
		if (tipo_cliente.equals("J")) {
			map.put("juridico", true);
		} else if (tipo_cliente.equals("N")) {
			map.put("natural", true);
		}

		return queryForListPag("Usuario.buscarUsuarios", map, 1, 11);
	}

	public Boolean eliminarTodasRelacionesUsuarioNegocio(Integer id_usuario) {
		delete("Usuario.eliminarTodasRelacionesUsuarioNegocio", id_usuario);
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> obtenerUsuariosSinCredenciales(Integer numPag) {
		return queryForListPag("Usuario.obtenerUsuariosSinCredenciales", null, numPag, Constantes.PAGINACIONLISTADO);
	}

	public Integer contarUsuariosSinCredenciales() {
		return (Integer) queryForObject("Usuario.contarUsuariosSinCredenciales", null);
	}

	public Boolean desactivarUsuarios() {
		update("Usuario.desactivarUsuarios", null);
		return true;
	}

	public Usuario obtenerUsuarioPorIdentificacion(String identificacion, Integer tipo_documento) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("identificacion", identificacion);
		map.put("tipo_documento", tipo_documento);

		return (Usuario) queryForObject("Usuario.obtenerUsuarioPorIdentificacion", map);
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> buscarUsuariosSinCredenciales(String texto) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("texto", texto);

		return queryForListPag("Usuario.buscarUsuariosSinCredenciales", map, 1, 11);
	}

	@SuppressWarnings("unchecked")
	public List<Integer> obtenerIdUsuariosActivos(Integer paginacion,
			Integer pagina) {
		return queryForListPag("Usuario.obtenerIdUsuariosActivos", null, pagina, paginacion);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Usuario> buscarUsuariosConvenio(String texto, String tipo_cliente, Integer negocio) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("texto", texto);
		map.put("idNegocio", negocio);
		
		if (tipo_cliente.equals("J")) {
			map.put("juridico", true);
		} else if (tipo_cliente.equals("N")) {
			map.put("natural", true);
		}

		return queryForListPag("Usuario.buscarUsuariosConvenio", map, 1, 11);
	}
	
	public Usuario obtenerUsuarioConvenio(Integer idUsuario, Integer negocio) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_usuario", idUsuario);
		map.put("idNegocio", negocio);
		return (Usuario) queryForObject("Usuario.obtenerUsuarioConvenio", map);
	}


}
