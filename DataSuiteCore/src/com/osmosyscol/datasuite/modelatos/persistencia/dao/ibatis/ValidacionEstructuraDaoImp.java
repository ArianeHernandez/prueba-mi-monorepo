package com.osmosyscol.datasuite.modelatos.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.modelatos.logica.dto.ResultadoValidacion;
import com.osmosyscol.datasuite.modelatos.logica.dto.TipoValidacion;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValidacionCampo;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValidacionEstructura;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.ValidacionEstructuraDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class ValidacionEstructuraDaoImp extends BaseSqlMapDao implements ValidacionEstructuraDao {

	public ValidacionEstructuraDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	private List<Map<String, Object>> desencriptarMapa(List<Map<String, Object>> resp) {

		if (resp == null || resp.isEmpty()) {
			return resp;
		}

		// desencripta
		for (Map<String, Object> registro : resp) {

			Set<String> keys = registro.keySet();
			for (String key : keys) {

				// se verifica si es un campo
				Boolean esCampo;
				try {
					esCampo = (key.toUpperCase().startsWith("C") || key.toUpperCase().startsWith("V"));
				} catch (Exception e) {
					esCampo = false;
				}

				// se verifica si el valor es texto
				if (registro.get(key) instanceof String && esCampo) {
					registro.put(key, Crypto.DF(registro.get(key)));
				}
			}
		}

		return resp;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> obtenerRegistrosDeEstructuraPorCarga(Integer id_carga, Integer id_estructura) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_carga", id_carga);
		map.put("estructura", id_estructura);

		try {
			
			List<Map<String, Object>> datos = queryForList("ValidacionEstructura.obtenerRegistrosDeEstructuraPorCarga", map);
			return datos;

		} catch (Exception e) {

			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}

	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> obtenerRegistrosDeEstructuraPorCargaVista(Integer id_carga, Integer id_estructura) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_carga", id_carga);
		map.put("estructura", id_estructura);

		try {
			
			List<Map<String, Object>> datos = desencriptarMapa(queryForList("ValidacionEstructura.obtenerRegistrosDeEstructuraPorCargaVista", map));
			return datos;

		} catch (Exception e) {

			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public List<ValidacionEstructura> obtenerValidacionesPorEstructura(Integer id_estructura) {

		return queryForList("ValidacionEstructura.obtenerValidacionesPorEstructura", id_estructura);
	}

	@SuppressWarnings("unchecked")
	public List<ValidacionCampo> obtenerValidacionCampoPorValidacion(Integer id_validacion_estructura) {

		return queryForList("ValidacionEstructura.obtenerValidacionCampoPorValidacion", id_validacion_estructura);
	}

	@SuppressWarnings("unchecked")
	public List<TipoValidacion> obtenerTiposDeValidacion() {
		return queryForList("ValidacionEstructura.obtenerTiposDeValidacion", null);
	}

	public TipoValidacion obtenerTipoDeValidacion(String id_tipovalidacion) {
		return (TipoValidacion) queryForObject("ValidacionEstructura.obtenerTipoDeValidacion", id_tipovalidacion);
	}

	public Boolean crearValidacionPorEstructura(ValidacionEstructura validacionEstructura) {

		insert("ValidacionEstructura.crearValidacionPorEstructura", validacionEstructura);
		return true;
	}

	public Integer obtenerSiguienteIDValidacionEstructura() {

		return (Integer) queryForObject("ValidacionEstructura.obtenerSiguienteIDValidacionEstructura", null);
	}

	public Boolean crearRelacionCampoValidacion(ValidacionCampo validacionCampo) {
		insert("ValidacionEstructura.crearRelacionCampoValidacion", validacionCampo);
		return true;
	}

	public Boolean eliminarTodasLasRelacionesCampoValidacion(Integer id_validacion_estructura) {
		delete("ValidacionEstructura.eliminarTodasLasRelacionCampoValidacion", id_validacion_estructura);
		return true;
	}

	public Boolean actualizarValidacionPorEstructura(ValidacionEstructura validacionEstructura) {
		update("ValidacionEstructura.actualizarValidacionPorEstructura", validacionEstructura);
		return true;
	}

	public Boolean eliminarValidacionPorEstructura(Integer id_validacion_estructura) {
		delete("ValidacionEstructura.eliminarValidacionPorEstructura", id_validacion_estructura);
		return true;
	}

	public ValidacionEstructura obtenerValidacionPorEstructura(Integer id_validacion_estructura) {

		return (ValidacionEstructura) queryForObject("ValidacionEstructura.obtenerValidacionPorEstructura", id_validacion_estructura);
	}

	public Boolean borrarResultadoValidacion(Integer id_carga) {
		delete("ValidacionEstructura.borrarResultadoValidacion", id_carga);
		return true;
	}

	public Boolean guardarResultadoValidacion(Integer id_carga, Integer id_registro, Integer id_validacion) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_carga", id_carga);
		map.put("id_registro", id_registro);
		map.put("id_validacion", id_validacion);
		
		insert("ValidacionEstructura.guardarResultadoValidacion", map);
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<ResultadoValidacion> obtenerResultadoValidacion(Integer id_carga) {
		return queryForList("ValidacionEstructura.obtenerResultadoValidacion", id_carga);
	}

}
