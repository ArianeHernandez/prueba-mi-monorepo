package com.osmosyscol.datasuite.modelatos.persistencia.dao.ibatis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.modelatos.logica.dto.Ejecucion;
import com.osmosyscol.datasuite.modelatos.logica.dto.Log;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.EjecucionDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class EjecucionDaoImp extends BaseSqlMapDao implements EjecucionDao {

	public EjecucionDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	// --------------------------------------------------------

	@SuppressWarnings("unchecked")
	public List<Ejecucion> obtenerEjecucionesPorCarga(Integer id_carga) {
		return queryForList("Ejecucion.obtenerEjecucionesPorCarga", id_carga);
	}

	public Integer obtenerSiguienteID() {
		return (Integer) queryForObject("Ejecucion.obtenerSiguienteID", null);
	}

	public Boolean crearEjecucion(Integer id_ejecucion, Integer id_carga, Integer id_destino, String estado, Date fecha_inicio, Date fecha_final, String login) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_ejecucion", id_ejecucion);
		map.put("id_carga", id_carga);
		map.put("id_destino", id_destino);
		map.put("estado", estado);
		map.put("fecha_inicio", fecha_inicio);
		map.put("fecha_final", fecha_final);
		map.put("login", login);

		Ejecucion ejecucion = (Ejecucion) queryForObject("Ejecucion.obtenerEjecucion", id_ejecucion);
		
		if(ejecucion != null){
			update("Ejecucion.actualizarEjecucion", map);
		}
		else{
			insert("Ejecucion.crearEjecucion", map);
		}
		

		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Log> obtenerLogEjecucionesCarga(Integer id_carga) {
		return queryForList("Ejecucion.obtenerLogEjecucionesCarga", id_carga);
	}
	
	@SuppressWarnings("unchecked")
	public List<Log> obtenerLogErrorEjecucionesCarga(Integer id_carga) {
		return queryForList("Ejecucion.obtenerLogErrorEjecucionesCarga", id_carga);
	}

	@SuppressWarnings("unchecked")
	public List<Log> obtenerLogEjecucion(Integer id_ejecucion) {
		return queryForList("Ejecucion.obtenerLogEjecucion", id_ejecucion);
	}

}
