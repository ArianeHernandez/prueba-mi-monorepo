package com.osmosyscol.datasuite.persistencia.dao.ibatis;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.logica.dto.Horario;
import com.osmosyscol.datasuite.persistencia.dao.HorarioDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class HorarioDaoImp extends BaseSqlMapDao implements HorarioDao{

	public HorarioDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	public Boolean crearHorario(Horario horario) {
		
		insert("Horario.crearHorario", horario);
		return true;
	}

	public Integer obtenerSiguienteIDHorario() {
		return (Integer)queryForObject("Horario.obtenerSiguienteIDHorario", null);
	}

	public Date obtenerHoraActual() {
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		return (Date)queryForObject("Horario.obtenerHoraActual", parametros);
	}
	
	
	
	

}
