package com.osmosyscol.datasuite.modelatos.persistencia.dao.ibatis;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.modelatos.logica.dto.ParametroCarga;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValorParametroCarga;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.ParametroCargaDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class ParametroCargaDaoImp extends BaseSqlMapDao implements ParametroCargaDao {

	public ParametroCargaDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	// --------------------------------------------------------

	public Boolean guardarValoresParametrosCarga(List<ValorParametroCarga> list) {
		
		if(list==null){
			return true;
		}
		
		for (ValorParametroCarga valorParametroCarga : list) {
			insert("ParametroCarga.guardarValorParametroCarga", valorParametroCarga);
		}
		
		
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<ParametroCarga> obtenerParametrosCarga(Integer id_formato) {
		return queryForList("ParametroCarga.obtenerParametrosCarga", id_formato);
	}

	public Boolean guardarParametroCarga(ParametroCarga parametroCarga) {
		
		try {
			if(parametroCarga.getId_parametro_carga()==null){
				Integer id_parametroCarga = (Integer) queryForObject("ParametroCarga.siguienteIdDestino", null);
				parametroCarga.setId_parametro_carga(id_parametroCarga);
				insert("ParametroCarga.guardarParametroCarga", parametroCarga);
			}else{
				update("ParametroCarga.actualizarParametroCarga", parametroCarga);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ParametroCarga> obtenerParametrosCargaPorFormatoSalida(Integer id_formato) {
		return queryForList("ParametroCarga.obtenerParametrosCargaPorFormatoSalida", id_formato);
	}

	@SuppressWarnings("unchecked")
	public List<ValorParametroCarga> obtenerValoresParametrosCarga(Integer id_carga) {
		return queryForList("ParametroCarga.obtenerValoresParametrosCarga", id_carga);
	}
}
