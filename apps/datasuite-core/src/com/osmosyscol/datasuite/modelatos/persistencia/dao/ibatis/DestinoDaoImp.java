package com.osmosyscol.datasuite.modelatos.persistencia.dao.ibatis;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.modelatos.logica.dto.Destino;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.DestinoDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;


public class DestinoDaoImp extends BaseSqlMapDao implements DestinoDao {

	public DestinoDaoImp(DaoManager daoManager) {
		super(daoManager);
	}
	
	// --------------------------------------------------------
	
	public Destino obtenerDestino(Integer id_destino) {
		return (Destino) queryForObject("Destino.obtenerDestino", id_destino);
	}

	@SuppressWarnings("unchecked")
	public List<Destino> obtenerTodosDestino() {
		return queryForList("Destino.obtenerTodosDestino", null);
	}

	public Destino guardarDestino(Destino destino) {
		
		if(destino.getId_destino()==null){
			Integer id_destino = (Integer) queryForObject("Destino.siguienteIdDestino", null);
			destino.setId_destino(id_destino);
			insert("Destino.insertarDestino", destino);
		}else{
			update("Destino.actualizarDestino", destino);
		}
		return destino;
	}

	@SuppressWarnings("unchecked")
	public List<Destino> obtenerDestinosPorFormato(Integer id_formato) {
		return queryForList("Destino.obtenerDestinosPorFormato", id_formato);
	}

	public Boolean eliminarDestino(Integer id_destino) {
		delete("Destino.eliminarDestino", id_destino);
		return true;
	}


	


}
