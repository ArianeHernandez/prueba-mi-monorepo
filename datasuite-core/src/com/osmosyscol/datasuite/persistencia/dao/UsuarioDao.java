package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.dto.UsuarioNegocio;

public interface UsuarioDao {

	public List<UsuarioNegocio> obtenerUsuariosPorNegocio(Integer id_negocio, Integer pagina);
	
	public Integer contarUsuariosPorNegocio(Integer id_negocio);

	public Boolean activarUsuario(Integer idUsuario, String activar);

	public Boolean eliminarUsuarioNegocio(Integer idUsuario, Integer idNegocio);

	public UsuarioNegocio obtenerUsuarioPorIdentificacionNegocio(String tipo_persona, String identificacion, Integer idNegocio, Integer tipo_documento);

	public Integer guardarUsuario(Usuario usuario);

	public Usuario obtenerUsuarioPorIdPersona(Integer id_usuario);
	
	public Boolean actualizarUsuario(Usuario usuario);

	public Boolean guardarUsuarioNegocio(Integer idUsuario, Integer idNegocio);
	
	public Integer obtenerSiguienteIdUsuario();
	
	public List<Usuario> obtenerUsuariosPorAdminCliente(Integer id_persona, Integer id_negocio);

	public List<Usuario> obtenerUsuariosActivos();
	
	public List<Integer> obtenerIdUsuariosActivos(Integer paginacion, Integer pagina);
	
	public List<Usuario> obtenerUsuariosOrden(String orden);

	public Usuario obtenerUsuarioPorLogin(String login);

	public Usuario obtenerUsuario(Integer idUsuario);

	public List<Usuario> buscarUsuariosActivos(String texto, String tipo_cliente);

	public Boolean eliminarTodasRelacionesUsuarioNegocio(Integer id_usuario);

	public List<Usuario> obtenerUsuariosSinCredenciales(Integer numPag);

	public Integer contarUsuariosSinCredenciales();

	public Boolean desactivarUsuarios();

	public Usuario obtenerUsuarioPorIdentificacion(String identificacion, Integer tipo_documento);

	public List<Usuario> buscarUsuariosSinCredenciales(String texto);
	
	public List<Usuario> buscarUsuariosConvenio(String texto, String tipo_cliente, Integer negocio);
	
	public Usuario obtenerUsuarioConvenio(Integer idUsuario, Integer negocio);
	
}
