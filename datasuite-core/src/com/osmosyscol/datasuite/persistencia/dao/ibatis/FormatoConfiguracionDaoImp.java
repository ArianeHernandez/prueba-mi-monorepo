package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.logica.dto.FormatoConfiguracion;
import com.osmosyscol.datasuite.persistencia.dao.FormatoConfiguracionDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class FormatoConfiguracionDaoImp extends BaseSqlMapDao implements FormatoConfiguracionDao {

	public FormatoConfiguracionDaoImp(DaoManager daoManager) {
		super(daoManager);
	}
	
	@SuppressWarnings("unchecked")
	public List<FormatoConfiguracion> obtenerConfiguracionesFormato(Integer id_formato) {
		return (List<FormatoConfiguracion>) queryForList("FormatoConfiguracion.obtenerConfiguracionesFormato", id_formato);
	}

	public FormatoConfiguracion obtenerConfiguracionFormato(Integer id_formato, String tipo) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("id_formato", id_formato);
		params.put("tipo", tipo);
		
		return (FormatoConfiguracion) queryForObject("FormatoConfiguracion.obtenerConfiguracionFormato", params);
	}

}
