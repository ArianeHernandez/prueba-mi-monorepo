package com.osmosyscol.datasuite.modelatos.persistencia.dao.ibatis;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.config.Constantes;
import com.osmosyscol.datasuite.modelatos.logica.dto.Tarea;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.TareaDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class TareaDaoImp extends BaseSqlMapDao implements TareaDao {

	public TareaDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	public Integer guardarTarea(Tarea tarea) {
		
		Tarea tareaG = obtenerTarea(tarea.getId_tarea());
		if (tareaG != null) {
			actualizarTarea(tarea);
			return tarea.getId_tarea();
		}
		Integer id = obtenerSiguiente();
		tarea.setId_tarea(id);
		insert("Tarea.guardarTarea", tarea);
		return id;
	}

	public Tarea obtenerTarea(Integer idTarea) {
		return (Tarea)queryForObject("Tarea.obtenerTarea", idTarea);
	}

	@SuppressWarnings("unchecked")
	public List<Tarea> obtenerTareas(Integer numpagina) {
		
		if (numpagina == null) {
			return queryForList("Tarea.obtenerTareas", null);
		}
		return queryForListPag("Tarea.obtenerTareas", null, numpagina, Constantes.PAGINACIONLISTADO);
	}

	public Integer contarTareas() {
		return (Integer)queryForObject("Tarea.contarTareas", null);
	}

	public Boolean actualizarTarea(Tarea tarea) {
		update("Tarea.actualizarTarea", tarea);
		return true;
	}

	public Boolean eliminarTarea(Integer idTarea) {
		delete("Tarea.eliminarTarea", idTarea);
		return true;
	}

	public Integer obtenerSiguiente(){
		return (Integer)queryForObject("Tarea.obtenerSiguiente", null);
	}
	// --------------------------------------------------------

	

}
