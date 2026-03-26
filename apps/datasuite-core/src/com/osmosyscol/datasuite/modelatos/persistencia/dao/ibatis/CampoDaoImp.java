package com.osmosyscol.datasuite.modelatos.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.modelatos.logica.dto.Campo;
import com.osmosyscol.datasuite.modelatos.logica.dto.Estructura;
import com.osmosyscol.datasuite.modelatos.logica.dto.TipoCampo;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.CampoDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class CampoDaoImp extends BaseSqlMapDao implements CampoDao {

	public CampoDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	// --------------------------------------------------------

	@SuppressWarnings("unchecked")
	public List<Campo> obtenerCamposPorEstructura(Integer id_estructura) {
		Estructura estructura = new Estructura();
		estructura.setId_estructura(id_estructura);
		return queryForList("Campo.obtenerCamposPorEstructura", estructura);
	}
	
	@SuppressWarnings("unchecked")
	public List<Campo> obtenerCamposPorEstructuraOrdenId(Integer id_estructura) {
		Estructura estructura = new Estructura();
		estructura.setId_estructura(id_estructura);
		return queryForList("Campo.obtenerCamposPorEstructuraOrdenId", estructura);
	}

	public boolean guardarCampo(Campo campo) {

		if (campo.getId_campo() == null) {
			Integer id_campo = (Integer) queryForObject("Campo.siguienteId", null);

			campo.setId_campo(id_campo);
			insert("Campo.guardarCampo", campo);
		} else {
			update("Campo.actualizarCampo", campo);
		}

		return true;
	}

	public boolean eliminarCampo(Campo campo) {
		delete("Campo.eliminarCampo", campo);

		return true;
	}

	public TipoCampo obtenerTipoCampo(Campo campo) {
		return (TipoCampo) queryForObject("TipoCampo.obtenerTipoCampoPorId", campo.getId_tipocampo());
	}

	public boolean campoTieneListaValores(Integer id_campo) {
		Integer cantidad = (Integer) queryForObject("Campo.campoTieneListaValores", id_campo);
		return cantidad != null && cantidad > 0;
	}

	public Campo obtenerCampo(Integer id_campo) {
		return (Campo) queryForObject("Campo.obtenerCampo", id_campo);
	}

	@SuppressWarnings("unchecked")
	public List<Campo> obtenerCampos() {
		return queryForList("Campo.obtenerCampos", null);
	}

	public Boolean actualizarOrdenCampo(Campo campo) {
		update("Campo.actualizarOrdenCampo", campo);
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Campo> obtenerCamposLlavePrimaria(Integer id_estructura) {
		return queryForList("Campo.obtenerCamposLlavePrimaria", id_estructura);
	}

	@Override
	public List<Campo> obtenerCamposInfoAdicionalDisponiblesPorAccion( Integer id_accion ) {
		return queryForList("Campo.obtenerCamposInfoAdicionalDisponiblesPorAccion", id_accion );
	}

	@Override
	public List<Campo> obtenerCamposInfoAdicionalPorAccion(Integer id_accion) {
		return queryForList("Campo.obtenerCamposInfoAdicionalPorAccion", id_accion );
	}

	@Override
	public Boolean insertarAccionCampoInfoAdicional(Integer id_campo, Integer id_accion) {
		Map<String, Integer> map = new HashMap<String, Integer>();

		map.put("id_accion", id_accion);
		map.put("id_campo", id_campo);

		try {
			insert("Campo.insertarAccionCampoInfoAdicional", map);
			return true;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en insertarAccionCampoInfoAdicional", e);
			return false;
		}
	}

	@Override
	public Boolean borrarAccionCampoInfoAdicional(Integer id_campo,	Integer id_accion) {
		Map<String, Integer> map = new HashMap<String, Integer>();

		map.put("id_accion", id_accion);
		map.put("id_campo", id_campo);

		try {
			delete("Campo.borrarAccionCampoInfoAdicional", map);
			return true;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en borrarAccionCampoInfoAdicional", e);
			return false;
		}
	}

}
