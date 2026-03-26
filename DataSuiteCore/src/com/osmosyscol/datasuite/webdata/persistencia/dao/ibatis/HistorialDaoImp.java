package com.osmosyscol.datasuite.webdata.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.webdata.logica.dto.HistorialCarga;
import com.osmosyscol.datasuite.webdata.persistencia.dao.HistorialDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class HistorialDaoImp extends BaseSqlMapDao implements HistorialDao{

	public HistorialDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	public Boolean guardarHistorialCarga(HistorialCarga historialCarga) {
		
		insert("Historial.guardarHistorialCarga", historialCarga);
		
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<HistorialCarga> obtenerHistorialCarga(Integer id_carga) {
		
		return queryForList("Historial.obtenerHistorialCarga", id_carga);
	}
	
	public HistorialCarga obtenerUltimoHistorialCarga(Integer id_carga) {
		
		return (HistorialCarga) queryForObject("Historial.obtenerUltimoHistorialCarga", id_carga);
	}

	public Integer obtenerPesoHistorialCarga(Integer id_carga, String estado_inicial) {
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id_carga", id_carga);
		parametros.put("estado_inicial", estado_inicial);
		
		return (Integer)queryForObject("Historial.obtenerPesoHistorialCarga", parametros);
	}

	public Integer obtenerVecesLiberadoHistorialCarga(Integer id_persona, Integer id_carga, String estado_inicial) {
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id_carga", id_carga);
		parametros.put("id_persona", id_persona);
		parametros.put("estado_inicial", estado_inicial);
		
		return (Integer)queryForObject("Historial.obtenerVecesLiberadoHistorialCarga", parametros);
		
	}

}
