
package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.logica.dto.Negocio;

public interface NegocioDao {

	public Negocio obtenerNegocio(Integer id_negocio);

	public List<Negocio> obtenerListadoNegocios();
	
	public List<Negocio> obtenerListadoNegociosActivos();

	public Integer obtenerSiguienteId();

	public Negocio crearNegocio(Negocio negocio);

	public Negocio actualizarNegocio(Negocio negocio);

	public List<Negocio> obtenerListadoNegociosPorLogin(String login);

	public void marcarEliminadoNegociosPorModelo(Integer id_modelo);

	public List<Negocio> obtenerListadoNegociosPorAdministrador(Integer id_administrador);

	public Boolean guardarUsuarioNegocio(Integer id_usuario, Integer id_negocio);

	public Boolean eliminarUsuarioNegocio(Integer id_usuario, Integer id_negocio);

	public List<Negocio> obtenerNegociosPorUsuario(Integer id_usuario);

	public Boolean esPersonaAdministrador(Integer id_persona, Integer id_negocio);

	public Boolean esPersonaCliente(Integer id_persona, Integer id_negocio);

	public void actualizarActivoNegocio(Integer id_negocio, String activo);

	public Boolean eliminarNegocio(Integer idNegocio);

	public Negocio obtenerNegocioPorCodigo(String codNegocio);
	
	public Boolean actualizarNegocioEnCreadatos(Negocio negocio);
	
	public Boolean insertarNegocioEnCreadatos(Negocio negocio);
	
	public Integer existeNegocioEnCreadatos(Integer id_negocio);
	
	public Boolean inactivarTodosLosNegociosEnCreadatos();

	public List<Negocio> obtenerNegociosPorUsuarioFormato(Integer id_usuario, Integer id_formato);

	public List<Negocio> obtenerListadoNegociosPorTipoProceso(Integer id_tipo_proceso);

}
