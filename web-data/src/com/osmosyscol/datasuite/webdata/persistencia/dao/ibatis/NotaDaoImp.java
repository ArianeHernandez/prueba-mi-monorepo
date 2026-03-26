package com.osmosyscol.datasuite.webdata.persistencia.dao.ibatis;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.webdata.logica.dto.Nota;
import com.osmosyscol.datasuite.webdata.persistencia.dao.NotaDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class NotaDaoImp extends BaseSqlMapDao implements NotaDao {

	public NotaDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	public Boolean guardarNota(Integer id_nota, String nota, Date fecha, Integer id_persona, Integer id_carga, String estado, Integer id_revision, String nombre_instancia, Boolean interno) {

		Boolean conf = false;
		try {
			
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("id_nota", id_nota);
			data.put("nota", nota);
			data.put("fecha", new Timestamp(fecha.getTime()));
			data.put("id_persona", id_persona);
			data.put("id_carga", id_carga);
			data.put("estado", estado);
			data.put("id_revision", id_revision);
			data.put("nombre_instancia", nombre_instancia);
			data.put("interno", interno?"S":"N");
			
			insert("Nota.guardarNota", data);
			
			conf = true;
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		
		return conf; 
	}

	@SuppressWarnings("unchecked")
	public List<Nota> obtenerNotasPorCarga(Integer id_carga, Boolean internas) {

		if (internas == null) {
			internas = true;
		}

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("id_carga", id_carga);
		params.put("internas", internas ? "S" : "N");

		return queryForList("Nota.obtenerNotasPorCarga", params);
	}

	public Integer obtenerSiguiente() {
		return (Integer) queryForObject("Nota.obtenerSiguiente", null);
	}

	public Nota obtenerNota(Integer id_nota) {
		return (Nota) queryForObject("Nota.obtenerNota", id_nota);
	}

}
