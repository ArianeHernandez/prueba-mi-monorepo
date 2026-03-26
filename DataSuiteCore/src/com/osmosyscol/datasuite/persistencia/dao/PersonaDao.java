package com.osmosyscol.datasuite.persistencia.dao;

import java.util.Date;
import java.util.List;

import com.osmosyscol.datasuite.logica.dto.Credencial;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.dto.Responsable;
import com.osmosyscol.datasuite.logica.dto.TipoDocumento;
import com.osmosyscol.datasuite.logica.dto.TipoProceso;
import com.osmosyscol.datasuite.logica.dto.TipoProcesoRol;

public interface PersonaDao {

	public Persona obtenerPersonaPorLogin(String login);

	public Persona obtenerPersona(Integer id_persona);

	public List<Persona> obtenerClientesFormato(Integer id_formato, Integer id_negocio, Integer numero_pagina);

	public Integer totalClientesFormato(Integer id_formato, Integer id_negocio);

	public List<Persona> obtenerPersonasNegocio(Integer id_negocio);

	public Persona guardarPersona(Persona persona);

	public List<Persona> obtenerAdministradores();

	public Persona obtenerPersonaPorIdentificacion(String identificacion, Integer tipoDocumento);

	public Integer obtenerIdUsuario(Integer id_persona);

	public Integer guardarUsuario(Integer id_persona);

	public List<Persona> obtenerPersonasUsuario(Integer idCliente, String rol, String tipoPersona, Integer desde);

	public List<Persona> obtenerDirectoresNegocio(Integer id_negocio, Integer pagina);

	public Boolean activarPersonaUsuario(Integer idPersona, Integer id_usuario, String activo, String rol, String todos);

	public Boolean activarDirectorNegocio(Integer id_persona, Integer idNegocio, String activo, String todos);

	public Boolean eliminarDirectorNegocio(Integer idPersona, Integer idNegocio);

	public Boolean eliminarPersonaUsuario(Integer idPersona, Integer idUsuario, String rol);

	public Boolean guardarPersonaUsuario(Integer idPersona, Integer idUsuario, String rol);

	public Boolean eliminarCredencial(String login);

	public Persona obtenerPersonaUsuario(Integer id_usuario, String rol, String tipoPersona, String identificacion, Integer tipoDocumento);

	public Credencial obtenerCredencialPorLogin(String login);

	public Credencial guardarCredencial(Credencial credencial);

	public Credencial obtenerCredencialPersonaUsuario(Integer idPersona, Integer idUsuario);

	public Integer contarPersonasUsuario(Integer idUsuario, String rol, String tipoPersona);

	public Integer contarDirectoresNegocio(Integer idNegocio);

	public Boolean actualizarFechaIngreso(String login, Date fecha_ingreso);

	public Boolean actualizarIPIngreso(String login, String ip_ingreso);

	public List<TipoDocumento> obtenerTiposDocumento();

	public TipoDocumento obtenerTipoDocumento(Integer idTipoDocumento);

	public Boolean actualizarEstadoCredencial(String estado, String login);

	public List<Persona> obtenerAdministrativos(Integer id_usuario, Integer pagina);

	public Integer contarAdministrativos(Integer id_usuario);

	public Integer guardarPersonaRol(Integer idPersona, String string, Integer id_usuario);

	public Boolean eliminarPersonaRol(Integer idPersona, String rol, Integer id_usuario);

	public Persona obtenerPersonaRol(String rol, String tipoPersona, String identificacion, Integer tipoDocumento, Integer id_usuario);

	public Boolean guardarHistorialLoginClave(String login, String clave);

	public Boolean existeHistorialLoginClave(String login, String clave);

	public String esPersonaRolActiva(Integer id_persona, String rol);

	public Boolean existeRelacionPersonaUsuarioRol(Integer idPersona, Integer idUsuario, String rol);

	public Boolean aceptarTerminosCondiciones(Credencial credencial);

	public Boolean quitarCambioClave(String login);

	public Boolean colocarCambioClave(String login);

	public List<TipoProceso> obtenerTiposProcesosPorIdUsuario(Integer id_usuario);

	public List<TipoProcesoRol> obtenerTipoProcesoRol(Integer id_persona, Integer id_usuario, String rol);

	public Boolean guardarProcesoRol(String rol, Integer id_tipo_proceso, Integer id_rol);

	public Boolean eliminarProcesoRol(String rol, Integer id_rol);

	public Integer obtenerIdRol(Integer id_persona, Integer id_usuario, String rol);
	
	public List<String> obtenerRolesPorPersona(Integer id_persona);

	public List<TipoProceso> obtenerTiposProcesosPorPersona(Integer id_usuario, Integer id_persona);

	public Boolean ajusteProcesoLiberador();

	public Boolean ajusteProcesoPreparador();

	public Boolean ajusteProcesoRevisor();

	public Integer contarProcesosRelacionados(Integer id_usuario, Integer id_persona, Integer id_tipo_proceso);

	public Boolean esAdminCliente(Integer id_usuario, Integer id_persona);

	public TipoProcesoRol obtenerTipoProcesoRolPorProceso(Integer id_persona, Integer id_usuario, Integer id_tipo_proceso);

	public Persona actualizarPersona(Persona persona);

	public Integer obtenerIdLiberadorCarga(Integer id_carga);

	List<Persona> obtenerAdministradoresClientePorUsuario(Integer id_usuario);

	List<Persona> obtenerRevisoresPorUsuario(Integer id_usuario, Integer id_tipo_revisor);
	
	public Integer obtenerIdTipoRevisorPorPersona(Integer id_persona);

	List<Persona> obtenerPreparadoresNoAdminClientePorUsuario(Integer id_usuario);

	List<Persona> obtenerResponsablesAsignados();

	public List<Persona> obtenerPosiblesResponsablesCarga(Integer id_carga);

	public Responsable obtenerResponsableActualCarga(Integer id_carga);

}
