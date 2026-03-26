package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.Liberador;
import com.osmosyscol.datasuite.logica.dto.LiberadorTipoProceso;
import com.osmosyscol.datasuite.persistencia.dao.LiberadorDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class LiberadorServicio {

	private static LiberadorServicio instancia;

	private LiberadorServicio() {
	}

	public static LiberadorServicio getInstance() {
		if (instancia == null) {
			instancia = new LiberadorServicio();
		}
		return instancia;
	}

	// ----------------------------------

	public List<Liberador> obtenerLiberadores(Integer id_usuario, Integer id_tipo_proceso) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			LiberadorDao liberadorDao = (LiberadorDao) daoManager.getDao(LiberadorDao.class);

			return liberadorDao.obtenerLiberadores(id_usuario, id_tipo_proceso);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}

	}

	// ----------------------------------

	public Boolean asociarLiberador(Integer id_proceso, Integer id_liberador) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			LiberadorDao liberadorDao = (LiberadorDao) daoManager.getDao(LiberadorDao.class);
			liberadorDao.asociarLiberador(id_proceso, id_liberador);

			return true;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}

	}

	// -------------------------------------

	public Boolean desasociarLiberador(Integer id_liberador) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			LiberadorDao liberadorDao = (LiberadorDao) daoManager.getDao(LiberadorDao.class);
			liberadorDao.desasociarLiberador(id_liberador);

			return true;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}

	}

	public Boolean desasociarLiberadorPorProceso(Integer id_liberador,Integer id_proceso) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			LiberadorDao liberadorDao = (LiberadorDao) daoManager.getDao(LiberadorDao.class);
			liberadorDao.desasociarLiberadorPorProceso(id_liberador, id_proceso);

			return true;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}

	}

	
	
	public List<Liberador> obtenerLiberadoresPorProceso(Integer id_proceso) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			LiberadorDao liberadorDao = (LiberadorDao) daoManager.getDao(LiberadorDao.class);

			return liberadorDao.obtenerLiberadoresPorProceso(id_proceso);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
	}

	// ----------------------------------

	public Liberador obtenerLiberadorPorIdentificacion(Integer id_usuario, String tipo_persona, String identificacion, Integer tipoDocumento) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			LiberadorDao liberadorDao = (LiberadorDao) daoManager.getDao(LiberadorDao.class);

			return liberadorDao.obtenerLiberadorPorIdentificacion(id_usuario, tipo_persona, identificacion, tipoDocumento);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
	}

	// ----------------------------------

	public Boolean actualizarLiberadorTipoProceso(List<LiberadorTipoProceso> listadoTipoProceso, Integer id_usuario, String identificacion, Integer tipoDocumento){
		
		DaoManager daoManager;
		try {
			
			if(listadoTipoProceso != null){
				for (LiberadorTipoProceso liberadorTipoProceso : listadoTipoProceso) {
					Liberador liberador = obtenerLiberadorPorIdentificacion(id_usuario, Constantes.PERSONA_NATURAL, identificacion, tipoDocumento);
					if(liberador != null){
						liberadorTipoProceso.setId_liberador(liberador.getId_liberador());
						
						daoManager = DaoConfig.getDaoManager();
						LiberadorDao liberadorDao= (LiberadorDao) daoManager.getDao(LiberadorDao.class);
						liberadorDao.actualizarLiberadorTipoProceso(liberadorTipoProceso);
					}
				}
			}
			return true;
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;
	}

	public LiberadorTipoProceso obtenerLiberadorTipoProceso(Integer id_liberador, Integer id_tipo_proceso) {
		
		try {
				LiberadorDao liberadorDao= (LiberadorDao) DaoConfig.getDao(LiberadorDao.class);
				return liberadorDao.obtenerLiberadorTipoProceso(id_liberador, id_tipo_proceso);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		
		return null;
	}
	
}
