package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.config.Constantes;
import com.osmosyscol.datasuite.logica.dto.Credencial;
import com.osmosyscol.datasuite.logica.dto.Negocio;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.dto.Responsable;
import com.osmosyscol.datasuite.logica.dto.TipoDocumento;
import com.osmosyscol.datasuite.logica.dto.TipoProceso;
import com.osmosyscol.datasuite.logica.dto.TipoProcesoRol;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.persistencia.dao.PersonaDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class PersonaDaoImp extends BaseSqlMapDao implements PersonaDao {

	public PersonaDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	// --------------------------------------------------------

	public Persona obtenerPersonaPorLogin(String login) {

		return (Persona) queryForObject("Persona.obtenerPersonaPorLogin", login);
	}

	public Persona obtenerPersona(Integer id_persona) {
		return (Persona) queryForObject("Persona.obtenerPersona", id_persona);
	}

	public Negocio obtenerNegocio(Integer id_negocio) {
		return (Negocio) queryForObject("Negocio.obtenerNegocio", id_negocio);
	}

	// ---------------------------

	@SuppressWarnings("unchecked")
	public List<Persona> obtenerClientesFormato(Integer id_formato, Integer id_negocio, Integer numero_pagina) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_formato", id_formato);
		map.put("id_negocio", id_negocio);

		return queryForListPag("Persona.obtenerClientesFormato", map, numero_pagina, Constantes.PAGINACIONLISTADO);

	}

	public Integer totalClientesFormato(Integer id_formato, Integer id_negocio) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_formato", id_formato);
		map.put("id_negocio", id_negocio);

		return (Integer) queryForObject("Persona.totalClientesFormato", map);

	}

	@SuppressWarnings("unchecked")
	public List<Persona> obtenerPersonasNegocio(Integer id_negocio) {
		return queryForList("Persona.obtenerPersonasNegocio", id_negocio);
	}

	public Persona guardarPersona(Persona persona) {

		if (persona == null) {
			return null;
		}

		Persona personaExiste = obtenerPersonaPorIdentificacion(persona.getIdentificacion(), persona.getTipo_documento());

		if (personaExiste == null) {
			Integer id_persona = (Integer) queryForObject("Persona.siguienteId", null);
			persona.setId_persona(id_persona);
			insert("Persona.guardarPersona", persona);
		} else {
			persona.setId_persona(personaExiste.getId_persona());
			update("Persona.actualizarPersona", persona);
		}

		return persona;
	}

	@SuppressWarnings("unchecked")
	public List<Persona> obtenerAdministradores() {
		return queryForList("Persona.obtenerAdministradores", null);
	}

	public Persona obtenerPersonaPorIdentificacion(String identificacion, Integer tipoDocumento) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("identificacion", identificacion);
		map.put("tipo_documento", tipoDocumento);

		return (Persona) queryForObject("Persona.obtenerPersonaPorIdentificacion", map);
	}

	public Integer guardarUsuario(Integer id_persona) {

		Integer sig_id = (Integer) queryForObject("Persona.obtenerSiguienteIdUsuario", null);

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_persona", id_persona);
		map.put("id_usuario", sig_id);

		insert("Persona.guardarUsuario", map);

		return sig_id;
	}

	public Integer obtenerIdUsuario(Integer id_persona) {
		Usuario usuario = (Usuario) queryForObject("Persona.obtenerUsuario", id_persona);

		if (usuario == null) {
			return null;
		}

		return usuario.getId_usuario();
	}

	public List<Persona> obtenerPersonasCliente(Integer idCliente, Integer idRol) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_cliente", idCliente);
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Persona> obtenerPersonasUsuario(Integer id_usuario, String rol, String tipoPersona, Integer desde) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_usuario", id_usuario);
		map.put("rol", rol);
		if (tipoPersona != null) {
			map.put("tipo_persona", tipoPersona);
		}
		return queryForListPag("Persona.obtenerPersonasUsuario", map, desde, Constantes.PAGINACIONLISTADO);

	}

	public Integer contarPersonasUsuario(Integer idUsuario, String rol, String tipoPersona) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_usuario", idUsuario);
		map.put("rol", rol);
		if (tipoPersona != null) {
			map.put("tipo_persona", tipoPersona);
		}
		return (Integer) queryForObject("Persona.contarPersonasUsuario", map);
	}

	public Persona obtenerPersonaUsuario(Integer id_usuario, String rol, String tipo_persona, String identificacion, Integer tipo_documento) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_usuario", id_usuario);
		map.put("rol", rol);
		map.put("tipo_persona", tipo_persona);
		map.put("identificacion", identificacion);
		map.put("tipo_documento", tipo_documento);

		return (Persona) queryForObject("Persona.obtenerPersonaUsuario", map);
	}

	@SuppressWarnings("unchecked")
	public List<Persona> obtenerDirectoresNegocio(Integer id_negocio, Integer pagina) {

		return queryForListPag("Persona.obtenerDirectoresNegocio", id_negocio, pagina, Constantes.PAGINACIONLISTADO);
	}

	public Integer contarDirectoresNegocio(Integer idNegocio) {
		return (Integer) queryForObject("Persona.contarDirectoresNegocio", idNegocio);
	}

	// ---------------------------

	public Boolean activarPersonaUsuario(Integer id_persona, Integer id_usuario, String activar, String rol, String todos) {

		Map<String, Object> map = new HashMap<String, Object>();

		String tabla = com.osmosyscol.datasuite.logica.constantes.Constantes.MAP_TABLAS_ROL.get(rol);

		if (StringUtils.isNotEmpty(tabla)) {
			map.put("tabla", tabla);
		} else {
			SimpleLogger.setError("Error al activar persona, no hay una tabla asociada al rol " + rol);
			return false;
		}

		if (todos.equals("S")) {
			map.put("todos", "");
		}

		map.put("id_persona", id_persona);
		if (id_usuario != null) {
			map.put("id_usuario", id_usuario);
		}
		map.put("activo", activar);

		update("Persona.activarPersonaUsuario", map);
		return true;
	}

	public Boolean activarDirectorNegocio(Integer id_persona, Integer id_negocio, String activo, String todos) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_persona", id_persona);
		map.put("id_negocio", id_negocio);
		map.put("activo", activo);

		if (todos.equals("S")) {
			map.put("todos", "");
		}

		update("Persona.activarDirectorNegocio", map);
		return true;
	}

	public Boolean eliminarDirectorNegocio(Integer id_persona, Integer id_negocio) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_persona", id_persona);
		map.put("id_negocio", id_negocio);

		delete("Persona.eliminarDirectorNegocio", map);
		map.put("id_usuario", null);
		delete("Persona.eliminarCredencial", map);
		return true;
	}

	public Boolean eliminarPersonaUsuario(Integer id_persona, Integer id_usuario, String rol) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_persona", id_persona);
		map.put("id_usuario", id_usuario);

		String tabla = com.osmosyscol.datasuite.logica.constantes.Constantes.MAP_TABLAS_ROL.get(rol);

		if (StringUtils.isNotEmpty(tabla)) {
			map.put("tabla", tabla);
			map.put("rol", rol);
		} else {
			SimpleLogger.setError("Error al eliminar persona, no hay una tabla asociada al rol " + rol);
			return false;
		}

		delete("Persona.eliminarPersonaUsuario", map);
		delete("Persona.eliminarCredencial", map);
		return true;
	}

	public Boolean eliminarCredencial(Integer id_persona, Integer id_usuario) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_persona", id_persona);
		map.put("id_usuario", id_usuario);

		delete("Persona.eliminarCredencial", map);

		return true;
	}

	public Boolean eliminarCredencial(String login) {

		delete("Persona.eliminarCredencialPorLogin", login);

		return true;
	}

	public Boolean guardarPersonaUsuario(Integer id_persona, Integer id_usuario, String rol) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_persona", id_persona);
		map.put("id_usuario", id_usuario);

		String tabla = com.osmosyscol.datasuite.logica.constantes.Constantes.MAP_TABLAS_ROL.get(rol);

		if (StringUtils.isNotEmpty(tabla)) {
			map.put("tabla", tabla);
		} else {
			SimpleLogger.setError("Error al guardar persona, no hay una tabla asociada al rol " + rol);
			return false;
		}

		insert("Persona.guardarPersonaUsuario", map);
		return true;

	}

	public Credencial obtenerCredencialPorLogin(String login) {
		return (Credencial) queryForObject("Persona.obtenerCredencialPorLogin", login);
	}

	public Credencial guardarCredencial(Credencial credencial) {
		insert("Persona.guardarCredencial", credencial);
		return credencial;
	}

	public Credencial obtenerCredencialPersonaUsuario(Integer idPersona, Integer idUsuario) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_persona", idPersona);
		map.put("id_usuario", idUsuario);

		return (Credencial) queryForObject("Persona.obtenerCredencialPersonaUsuario", map);
	}

	// ---------------------------

	public Boolean actualizarFechaIngreso(String login, Date fecha_ingreso) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("login", login);
		map.put("fecha_ingreso", new Timestamp(fecha_ingreso.getTime()));

		return update("Persona.actualizarFechaIngreso", map) > 0 ? true : false;
	}

	public Boolean actualizarIPIngreso(String login, String ip_ingreso) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("login", login);
		map.put("ip_ingreso", ip_ingreso);

		return update("Persona.actualizarIPIngreso", map) > 0 ? true : false;
	}

	@SuppressWarnings("unchecked")
	public List<TipoDocumento> obtenerTiposDocumento() {
		return queryForList("Persona.obtenerTiposDocumento", null);
	}

	public TipoDocumento obtenerTipoDocumento(Integer idTipoDocumento) {

		return (TipoDocumento) queryForObject("Persona.obtenerTipoDocumento", idTipoDocumento);
	}

	public Boolean actualizarEstadoCredencial(String estado, String login) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("estado", estado);
		map.put("login", login);

		update("Persona.actualizarEstadoCredencial", map);
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Persona> obtenerAdministrativos(Integer id_usuario, Integer pagina) {

		return queryForListPag("Persona.obtenerAdministrativos", id_usuario, pagina, Constantes.PAGINACIONLISTADO);
	}

	public Integer contarAdministrativos(Integer id_usuario) {
		return (Integer) queryForObject("Persona.contarAdministrativos", id_usuario);
	}

	public Integer obtenerSiguienteIdPersonaRol(String tabla) {
		return (Integer) queryForObject("Persona.obtenerSiguienteIdPersonaRol", tabla);
	}

	public Integer guardarPersonaRol(Integer idPersona, String rol, Integer id_usuario) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_persona", idPersona);
		map.put("id_usuario", id_usuario);

		String tabla = com.osmosyscol.datasuite.logica.constantes.Constantes.MAP_TABLAS_ROL.get(rol);

		if (StringUtils.isNotEmpty(tabla)) {
			map.put("tabla", tabla);
		} else {
			SimpleLogger.setError("Error al guardar persona, no hay una tabla asociada al rol " + rol);
			return null;
		}
		Integer sig_id = obtenerSiguienteIdPersonaRol(tabla);

		map.put("id", sig_id);

		insert("Persona.guardarPersonaRol", map);
		return sig_id;

	}

	public Boolean eliminarPersonaRol(Integer idPersona, String rol, Integer id_usuario) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_persona", idPersona);
		map.put("id_usuario", id_usuario);

		String tabla = com.osmosyscol.datasuite.logica.constantes.Constantes.MAP_TABLAS_ROL.get(rol);

		if (StringUtils.isNotEmpty(tabla)) {
			map.put("tabla", tabla);
			map.put("rol", rol);
		} else {
			SimpleLogger.setError("Error al eliminar persona, no hay una tabla asociada al rol " + rol);
			return false;
		}

		delete("Persona.eliminarPersonaRol", map);
		delete("Persona.eliminarCredencial", map);
		return true;
	}

	public Persona obtenerPersonaRol(String rol, String tipoPersona, String identificacion, Integer tipo_documento, Integer id_usuario) {
		Map<String, Object> map = new HashMap<String, Object>();

		String tabla = com.osmosyscol.datasuite.logica.constantes.Constantes.MAP_TABLAS_ROL.get(rol);

		if (StringUtils.isNotEmpty(tabla)) {
			map.put("tabla", tabla);
			map.put("rol", rol);
		} else {
			SimpleLogger.setError("Error al obtener persona por rol, no hay una tabla asociada al rol " + rol);
			return null;
		}

		map.put("tipo_persona", tipoPersona);
		map.put("identificacion", identificacion);
		map.put("tipo_documento", tipo_documento);
		map.put("id_usuario", id_usuario);

		return (Persona) queryForObject("Persona.obtenerPersonaRol", map);
	}

	public Boolean guardarHistorialLoginClave(String login, String clave) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("login", login);
		map.put("clave", clave);

		insert("Persona.guardarHistorialLoginClave", map);
		return true;
	}

	public Boolean existeHistorialLoginClave(String login, String clave) {

		Boolean existeHistorialLoginClave = false;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("login", login);
		map.put("clave", clave);

		Integer existe = (Integer) queryForObject("Persona.existeHistorialLoginClave", map);

		if (existe > 0) {
			existeHistorialLoginClave = true;
		}

		return existeHistorialLoginClave;

	}

	public String esPersonaRolActiva(Integer id_persona, String rol) {

		String tabla = com.osmosyscol.datasuite.logica.constantes.Constantes.MAP_TABLAS_ROL.get(rol);

		Map<String, Object> map = new HashMap<String, Object>();
		if (tabla != null) {
			map.put("tabla", tabla);
			map.put("id_persona", id_persona);
			return (String) queryForObject("Persona.esPersonaRolActiva", map);
		}
		SimpleLogger.setError("Error al eliminar persona, no hay una tabla asociada al rol " + rol);
		return null;
	}

	public Boolean existeRelacionPersonaUsuarioRol(Integer idPersona, Integer idUsuario, String rol) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_persona", idPersona);
		map.put("id_usuario", idUsuario);

		String tabla = com.osmosyscol.datasuite.logica.constantes.Constantes.MAP_TABLAS_ROL.get(rol);

		if (StringUtils.isNotEmpty(tabla)) {
			map.put("tabla", tabla);

			Integer cuantos = (Integer) queryForObject("Persona.existe_relacion_persona_usuario_rol", map);

			if (cuantos > 0) {
				return true;
			} else {
				return false;
			}

		} else {
			SimpleLogger.setError("Error al guardar persona, no hay una tabla asociada al rol " + rol);
			return null;
		}

	}

	public Boolean aceptarTerminosCondiciones(Credencial credencial) {
		update("Persona.aceptarTerminosCondiciones", credencial);
		return true;
	}

	public Boolean quitarCambioClave(String login) {
		update("Persona.quitarCambioClave", login);
		return true;
	}

	public Boolean colocarCambioClave(String login) {
		update("Persona.colocarCambioClave", login);
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<TipoProceso> obtenerTiposProcesosPorIdUsuario(Integer id_usuario) {
		return queryForList("Persona.obtenerTiposProcesosPorIdUsuario", id_usuario);
	}

	@SuppressWarnings("unchecked")
	public List<TipoProcesoRol> obtenerTipoProcesoRol(Integer id_persona, Integer id_usuario, String rol) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_persona", id_persona);
		map.put("id_usuario", id_usuario);
		map.put("rol", rol);
		return queryForList("Persona.obtenerTipoProcesoRol", map);
	}

	public Boolean guardarProcesoRol(String rol, Integer id_tipo_proceso, Integer id_rol) {
		String tabla = com.osmosyscol.datasuite.logica.constantes.Constantes.MAP_TABLAS_ROL.get(rol);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tabla", tabla);
		map.put("id_tipo_proceso", id_tipo_proceso);
		map.put("id_rol", id_rol);
		insert("Persona.guardarProcesoRol", map);
		return true;
	}

	public Boolean eliminarProcesoRol(String rol, Integer id_rol) {
		String tabla = com.osmosyscol.datasuite.logica.constantes.Constantes.MAP_TABLAS_ROL.get(rol);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tabla", tabla);
		map.put("id_rol", id_rol);
		delete("Persona.eliminarProcesoRol", map);
		return true;
	}

	@Override
	public Integer obtenerIdRol(Integer id_persona, Integer id_usuario, String rol) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_persona", id_persona);
		map.put("id_usuario", id_usuario);
		map.put("rol", rol);
		return (Integer) queryForObject("Persona.obtenerIdRol", map);
	}
	
	@Override
	public List<String> obtenerRolesPorPersona(Integer id_persona) {

		return queryForList("Persona.obtenerRolesPorPersona", id_persona);
	}

	@SuppressWarnings("unchecked")
	public List<TipoProceso> obtenerTiposProcesosPorPersona(Integer id_usuario, Integer id_persona) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_persona", id_persona);
		map.put("id_usuario", id_usuario);
		return queryForList("Persona.obtenerTiposProcesosPorPersona", map);

	}

	public Boolean ajusteProcesoLiberador() {
		delete("Persona.ajusteProcesoLiberador", null);
		return true;
	}

	public Boolean ajusteProcesoPreparador() {
		delete("Persona.ajusteProcesoPreparador", null);
		return true;
	}

	public Boolean ajusteProcesoRevisor() {
		delete("Persona.ajusteProcesoRevisor", null);
		return true;
	}

	// ----------------------------

	public Integer contarProcesosRelacionados(Integer id_usuario, Integer id_persona, Integer id_tipo_proceso) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_persona", id_persona);
		map.put("id_usuario", id_usuario);
		map.put("id_tipo_proceso", id_tipo_proceso);

		Integer cantidad = 0;

		cantidad += (Integer) queryForObject("Persona.contarProcesosRelacionadosPreparador", map);
		cantidad += (Integer) queryForObject("Persona.contarProcesosRelacionadosRevisor", map);
		cantidad += (Integer) queryForObject("Persona.contarProcesosRelacionadosLiberador", map);

		return cantidad;
	}

	@Override
	public Boolean esAdminCliente(Integer id_usuario, Integer id_persona) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_persona", id_persona);
		map.put("id_usuario", id_usuario);
		return ((Integer) queryForObject("Persona.esAdminCliente", map)) > 0;
	}

	public TipoProcesoRol obtenerTipoProcesoRolPorProceso(Integer id_persona, Integer id_usuario, Integer id_tipo_proceso) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_persona", id_persona);
		map.put("id_usuario", id_usuario);
		map.put("id_tipo_proceso", id_tipo_proceso);
		return (TipoProcesoRol) queryForObject("Persona.obtenerTipoProcesoRolPorProceso", map);
	}

	@Override
	public Persona actualizarPersona(Persona persona) {

		if (persona == null) {
			return null;
		}

		update("Persona.actualizarPersona", persona);

		return persona;
	}

	@Override
	public Integer obtenerIdLiberadorCarga(Integer id_carga) {
		return (Integer) queryForObject("Persona.obtenerIdLiberadorCarga", id_carga);
	}
	
	@SuppressWarnings("unchecked")
	public List<Persona> obtenerRevisoresPorUsuario(Integer id_usuario, Integer id_tipo_revisor) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_usuario", id_usuario);
		map.put("id_tipo_revisor", id_tipo_revisor);

		return queryForList("Persona.obtenerRevisoresPorUsuario", map);
	}
	
	@SuppressWarnings("unchecked")
	public Integer obtenerIdTipoRevisorPorPersona(Integer id_persona) {
	    return (Integer) queryForObject("Persona.obtenerIdTipoRevisorPorPersona", id_persona);
	}

	@SuppressWarnings("unchecked")
	public List<Persona> obtenerPreparadoresNoAdminClientePorUsuario(Integer id_usuario){
		return queryForList("Persona.obtenerPreparadoresNoAdminClientePorUsuario", id_usuario);
	}
	
	@SuppressWarnings("unchecked")
	public List<Persona> obtenerAdministradoresClientePorUsuario(Integer id_usuario){
		return queryForList("Persona.obtenerAdministradoresClientePorUsuario", id_usuario);
	}

	@SuppressWarnings("unchecked")
	public List<Persona> obtenerResponsablesAsignados() {
		return queryForList("Persona.obtenerResponsablesAsignados");
	}

	@SuppressWarnings("unchecked")
	public List<Persona> obtenerPosiblesResponsablesCarga(Integer id_carga) {
		return queryForList("Persona.obtenerPosiblesResponsablesCarga", id_carga);
	}

	@Override
	public Responsable obtenerResponsableActualCarga(Integer id_carga) {
		return (Responsable) queryForObject("Persona.obtenerResponsableActualCarga", id_carga);
	}

}
