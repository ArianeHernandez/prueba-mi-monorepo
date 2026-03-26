package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.logica.dto.Franja;
import com.osmosyscol.datasuite.persistencia.dao.FranjaDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class FranjaDaoImp extends BaseSqlMapDao implements FranjaDao{

	public FranjaDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	public Boolean guardarFranjas(List<Franja> franjas, Integer id_horario) {
		delete("Franja.eliminarFranjas", id_horario);
		for (Franja franja : franjas) {
			franja.setId_horario(id_horario);
			
			insert("Franja.guardarFranja", franja);
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Franja> obtenerFranjas(Integer idHorario) {
		return queryForList("Franja.obtenerFranjas", idHorario);
	}

	public Integer obtenerNumeroTotalFranjas(Integer id_horario) {
		return (Integer)queryForObject("Franja.obtenerNumeroTotalFranjas", id_horario);
	}
	

}
