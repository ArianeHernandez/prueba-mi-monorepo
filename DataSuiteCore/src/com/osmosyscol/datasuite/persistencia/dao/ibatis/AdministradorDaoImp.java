package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.logica.dto.Administrador;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.persistencia.dao.AdministradorDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class AdministradorDaoImp extends BaseSqlMapDao implements AdministradorDao {

	public AdministradorDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	// --------------------------------------------------------

	public Integer actualizarAdministrador(Administrador administrador) {

		Administrador tmp_administrador = obtenerAdministradorPorLogin(administrador.getLogin());

		if (tmp_administrador == null) {
			Integer id_administrador = (Integer) queryForObject("Administrador.obtenerSiguienteId", null);
			administrador.setId_administrador(id_administrador);
			insert("Administrador.crearAdministrador", administrador);
			return id_administrador;
		} else {
			if (administrador.getPersona() == null) {
				actualizarActivoAdministrador(tmp_administrador.getId_administrador(), administrador.getActivo());
			} else {
				update("Administrador.actualizarPersonaAdministrador", administrador.getPersona());
			}

			return tmp_administrador.getId_administrador();
		}
	}

	public void eliminarAdministradorNegocioPorNegocio(Integer id_negocio) {
		update("Administrador.eliminarAdministradorNegocioPorNegocio", id_negocio);
	}

	@SuppressWarnings("unchecked")
	public List<Administrador> obtenerListadoAdministradoresPorNegocio(Integer id_negocio) {
		List<Administrador> ret = queryForList("Administrador.obtenerListadoAdministradoresPorNegocio", id_negocio);

		if (ret != null) {
			for (Administrador administrador : ret) {
				Persona persona = (Persona) queryForObject("Persona.obtenerPersona", administrador.getId_persona());
				administrador.setPersona(persona);
			}
		}

		return ret;
	}

	public Administrador obtenerAdministradorPorLogin(String login) {
		return (Administrador) queryForObject("Administrador.obtenerAdministradorPorLogin", login);
	}

	public void actualizarActivoAdministrador(Integer id_administrador, String activo) {

		if (id_administrador != null) {
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("id_administrador", id_administrador);
			map.put("activo", activo == null ? "N" : activo);

			update("Administrador.actualizarActivoAdministrador", map);
		}
	}

	public void agregarAdministradorNegocio(Integer id_negocio, String activo, Integer id_administrador) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_administrador", id_administrador);
		map.put("id_negocio", id_negocio);
		map.put("activo", activo);

		insert("Administrador.agregarAdministradorNegocio", map);
	}

	public void agregarAdministradorNegocio(Integer id_administrador, String cod_negocio) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_administrador", id_administrador);
		map.put("cod_negocio", cod_negocio);

		insert("Administrador.agregarAdministradorNegocio2", map);
	}

	public void eliminarAdministradorNegocioPorAdministrador(Integer id_administrador) {
		delete("Administrador.eliminarAdministradorNegocioPorAdministrador", id_administrador);
	}

	public Administrador obtenerDirectorNegocioIdentificacionNegocio(String tipoPersona, String identificacion, Integer idNegocio) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("tipo_persona", tipoPersona);
		map.put("identificacion", identificacion);
		map.put("id_negocio", idNegocio);

		return (Administrador) queryForObject("Administrador.obtenerDirectorNegocioIdentificacionNegocio", map);
	}

}
