package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.Administrativo;
import com.osmosyscol.datasuite.logica.dto.Atributo;
import com.osmosyscol.datasuite.logica.dto.RestriccionAdministrativo;
import com.osmosyscol.datasuite.logica.dto.ValorAtributo;
import com.osmosyscol.datasuite.persistencia.dao.AdministrativoDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class AdministrativoDaoImp extends BaseSqlMapDao implements AdministrativoDao {

	public AdministrativoDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	@SuppressWarnings("unchecked")
	public List<RestriccionAdministrativo> obtenerRestriccionesAdministrativos() {
		return queryForList("Administrativo.obtenerRestriccionesAdministrativos", null);
	}

	public Boolean crearRestriccionAdministrativo(Integer id_rol, Integer id_administrativo, Integer id_usuario, Integer id_negocio, Integer id_restriccion_administrativo) {
		try {

			Map<String, Object> map = new HashMap<String, Object>();

			map.put("id_rol", id_rol);
			map.put("id_administrativo", id_administrativo);
			map.put("id_usuario", id_usuario);
			map.put("id_negocio", id_negocio);
			map.put("id_restriccion_administrativo", id_restriccion_administrativo);

			insert("Administrativo.crearRestriccionAdministrativo", map);

			return true;

		} catch (Exception e) {
			SimpleLogger.setError("Error en AdministrativoDaoImp.crearRestriccionAdministrativo", e);
			return false;
		}

	}

	public Integer obtenerIdSiguiente() {
		return (Integer) queryForObject("Administrativo.obtenerIdSiguiente", null);
	}

	public RestriccionAdministrativo obtenerRestriccionAdministrativo(Integer id_restriccion_administrativo) {
		return (RestriccionAdministrativo) queryForObject("Administrativo.obtenerRestriccionAdministrativo", id_restriccion_administrativo);
	}

	@SuppressWarnings("unchecked")
	public List<RestriccionAdministrativo> obtenerRestriccionesPorAdministrativo(Integer id_administrativo) {

		return queryForList("Administrativo.obtenerRestriccionesPorAdministrativo", id_administrativo);

	}

	public Boolean eliminarRestriccionAdministrativo(Integer id_restriccion_administrativo) {
		try {
			delete("Administrativo.eliminarRestriccionAdministrativo", id_restriccion_administrativo);
			return true;

		} catch (Exception e) {
			SimpleLogger.setError("Error en AdministrativoDaoImp.eliminarRestriccionAdministrativo", e);
			return false;
		}

	}

	public Boolean actualizarRol(Integer id_restriccion_administrativo, Integer id_rol) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_restriccion_administrativo", id_restriccion_administrativo);
		map.put("id_rol", id_rol);

		Integer actualizados = update("Administrativo.actualizarRol", map);

		update("Administrativo.actualizarAdminvPorRol", map);

		return actualizados > 0;
	}

	@SuppressWarnings("unchecked")
	public List<Administrativo> obtenerAdministrativosPorRol(Integer id_rol) {

		return queryForList("Administrativo.obtenerAdministrativosPorRol", id_rol);

	}

	public Boolean actualizarAdministrativo(Integer id_restriccion_administrativo, Integer id_administrativo) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_restriccion_administrativo", id_restriccion_administrativo);
		map.put("id_administrativo", id_administrativo);

		Integer actualizados = update("Administrativo.actualizarAdministrativo", map);

		return actualizados > 0;

	}

	public Boolean actualizarCliente(Integer id_restriccion_administrativo, Integer id_usuario) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_restriccion_administrativo", id_restriccion_administrativo);
		map.put("id_usuario", id_usuario);

		Integer actualizados = update("Administrativo.actualizarCliente", map);

		update("Administrativo.actualizarNegPorUsu", map);

		return actualizados > 0;
	}

	public Boolean actualizarNegocio(Integer id_restriccion_administrativo, Integer id_negocio) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_restriccion_administrativo", id_restriccion_administrativo);
		map.put("id_negocio", id_negocio);

		Integer actualizados = update("Administrativo.actualizarNegocio", map);

		return actualizados > 0;
	}

	public Integer obtenerIdAdministrativoPersona(Integer idPersona, Integer id_usuario) {

		Map<String, Integer> map = new HashMap<>();
		map.put("id_persona", idPersona);
		map.put("id_usuario", id_usuario);
		
		return (Integer) queryForObject("Administrativo.obtenerIdAdministrativoPersona", map);
	}

	@SuppressWarnings("unchecked")
	public List<Administrativo> obtenerAdministrativosActivos() {
		return queryForList("Administrativo.obtenerAdministrativosActivos", null);
	}

	@SuppressWarnings("unchecked")
	public List<Administrativo> obtenerAdministrativosParaNotificacion(Integer id_accion) {
		return queryForList("Administrativo.obtenerAdministrativosParaNotificacion", id_accion);
	}

	public Administrativo obtenerAdministrativoPorID(Integer id_administrativo) {

		return (Administrativo) queryForObject("Administrativo.obtenerAdministrativoPorID", id_administrativo);
	}

	@SuppressWarnings("unchecked")
	public List<Administrativo> obtenerAdminsHijoPorInstanciaParaAdminPadre(Integer id_administrativo_padre, Integer id_instancia) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_administrativo_padre", id_administrativo_padre);
		map.put("id_instancia", id_instancia);

		return queryForList("Administrativo.obtenerAdminsHijoPorInstanciaParaAdminPadre", map);
	}

	public Integer guardarAdministrativo(Administrativo administrativo) {
		if (administrativo.getId_administrativo() == null) {
			Integer id_administrativo = (Integer) queryForObject("Administrativo.obtenerSiguiente");
			administrativo.setId_administrativo(id_administrativo);
			insert("Administrativo.insertAdministrativo", administrativo);
			return id_administrativo;
		} else {
			update("Administrativo.updateAdministrativo", administrativo);
			return administrativo.getId_administrativo();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Atributo> obtenerAtributos(Integer id_usuario) {
		return queryForList("Administrativo.obtenerAtributos", id_usuario);
	}

	@SuppressWarnings("unchecked")
	public List<ValorAtributo> obtenerValoresAtributo(Integer id_atributo, Integer id_administrativo) {
		Map<String, Object> map = new HashMap<>();
		map.put("id_atributo", id_atributo);
		map.put("id_administrativo", id_administrativo);

		return queryForList("Administrativo.obtenerValoresAtributo", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Administrativo> obtenerAdministrativosPor(Integer id_usuario){
		return queryForList("Administrativo.obtenerAdministrativosPor", id_usuario);
	}

	@Override
	public Administrativo obtenerAdministrativoPorCodigo(String codigo) {
		return (Administrativo) queryForObject("Administrativo.obtenerAdministrativoPorCodigo",codigo);
	}
	
}
