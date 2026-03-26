package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.persistencia.dao.AccionFilaDao;
import com.osmosyscol.datasuite.reportedim.dto.AccionFila;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class AccionFilaDaoImp extends BaseSqlMapDao implements AccionFilaDao {

	public AccionFilaDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	public AccionFila obtenerAccionFila(String id_reporte) {
		return (AccionFila) queryForObject("AccionFila.obtenerAccionFila", id_reporte);
	}

	public Integer totalAccionesFila() {
		return (Integer) queryForObject("AccionFila.totalAccionesFila");
	}

	public Boolean crearAccionFila(String destino, String subreporte, String id_reporte) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("destino", destino);
		map.put("subreporte", subreporte);
		map.put("id_reporte", id_reporte);
		
		Boolean conf = false;
		try {
			insert("AccionFila.crearAccionFila", map);
			conf=true;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en AccionFilaDaoImp.crearAccionFila", e);
		}
		return conf;
	}

	public Boolean actualizarAccionFila(AccionFila accionFila) {
		Integer cantidad = update("AccionFila.actualizarAccionFila", accionFila) ;
		return cantidad > 0;
	}

	public Boolean eliminarAccionFila(String id_reporte) {
		delete("AccionFila.eliminarAccionFila", id_reporte);
		return true;
	}

	public AccionFila obtenerAccionByAccion(AccionFila accionFila){
		return (AccionFila) queryForObject("AccionFila.obtenerAccionBySubReporteDestino", accionFila);
	}
		
}
