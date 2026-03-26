package com.osmosyscol.datasuite.webdata.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.logica.dto.Horario;
import com.osmosyscol.datasuite.webdata.persistencia.dao.ParametrizacionClienteDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class ParametrizacionClienteDaoImp extends BaseSqlMapDao implements ParametrizacionClienteDao {

	public ParametrizacionClienteDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	public Boolean crearHorarioLiberacion(Integer id_horario, Integer id_usuario) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id_horario", id_horario);
		parametros.put("id_usuario", id_usuario);
		parametros.put("descripcion", "HORARIO LIBERACION");
		
		insert("ParametrizacionCliente.crearHorarioLiberacion", parametros);
		return true;
	}
	
	public Horario obtenerHorarioLiberacion(Integer id_usuario) {
		
		return (Horario)queryForObject("ParametrizacionCliente.obtenerHorarioLiberacion", id_usuario);
	}
	

	
}
