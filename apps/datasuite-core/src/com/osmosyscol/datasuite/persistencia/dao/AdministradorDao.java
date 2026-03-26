package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.logica.dto.Administrador;

public interface AdministradorDao {

	public Administrador obtenerAdministradorPorLogin(String login);

	public Integer actualizarAdministrador(Administrador administrador);

	public void eliminarAdministradorNegocioPorNegocio(Integer id_negocio);

	public void eliminarAdministradorNegocioPorAdministrador(Integer id_administrador);

	public List<Administrador> obtenerListadoAdministradoresPorNegocio(Integer id_negocio);

	public void actualizarActivoAdministrador(Integer id_administrador, String activo);

	// --
	
	public void agregarAdministradorNegocio( Integer id_negocio, String activo, Integer id_administrador);

	public void agregarAdministradorNegocio(Integer id_administrador, String cod_negocio);

	public Administrador obtenerDirectorNegocioIdentificacionNegocio(String tipoPersona, String identificacion, Integer idNegocio);

}
