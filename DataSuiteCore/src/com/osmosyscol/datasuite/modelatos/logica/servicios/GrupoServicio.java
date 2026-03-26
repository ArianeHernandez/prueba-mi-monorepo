package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.modelatos.logica.dto.Grupo;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.GrupoDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class GrupoServicio {

	private static GrupoServicio grupoServicio;

	private GrupoServicio() {
	}

	public static GrupoServicio getInstance() {
		if (grupoServicio == null) {
			grupoServicio = new GrupoServicio();
		}
		return grupoServicio;
	}

	// ------------------------------

	public Grupo obtenerGrupo(Integer id_grupo) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			GrupoDao grupoDao = (GrupoDao) daoManager.getDao(GrupoDao.class);

			return grupoDao.obtenerGrupo(id_grupo);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------

	public List<Grupo> obtenerGruposPorEstructura(Integer id_estructura) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			GrupoDao grupoDao = (GrupoDao) daoManager.getDao(GrupoDao.class);

			return grupoDao.obtenerGruposPorEstructura(id_estructura);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------

	public List<Grupo> obtenerGruposPorPersona(Integer id_persona, Integer id_negocio, Integer numero_pagina) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			GrupoDao grupoDao = (GrupoDao) daoManager.getDao(GrupoDao.class);

			return grupoDao.obtenerGruposPorPersona(id_persona, id_negocio, numero_pagina);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------
	
	public Integer totalGruposPorPersona(Integer id_persona, Integer id_negocio) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			GrupoDao grupoDao = (GrupoDao) daoManager.getDao(GrupoDao.class);

			return grupoDao.totalGruposPorPersona(id_persona, id_negocio);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------

	public Boolean guardarGrupo(Grupo grupo, Integer id_persona, Integer id_modelo) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			GrupoDao grupoDao = (GrupoDao) daoManager.getDao(GrupoDao.class);

			return grupoDao.guardarGrupo(grupo, id_persona, id_modelo);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}
	}

	// ------------------------------

	public Boolean eliminarGrupo(Integer id_grupo) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			GrupoDao grupoDao = (GrupoDao) daoManager.getDao(GrupoDao.class);

			return grupoDao.eliminarGrupo(id_grupo);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}
	}
	
	// ------------------------------
	
	public Boolean guardarGrupoEstructura(Integer id_grupo, Integer id_estructura, String accion) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			GrupoDao grupoDao = (GrupoDao) daoManager.getDao(GrupoDao.class);

			return grupoDao.guardarGrupoEstructura(id_grupo, id_estructura, accion);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}
	}
	
	
	// ------------------------------
	
	public Grupo obtenerGrupoPorNombre(String nombreGrupo, Integer id_modelo){
		
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			GrupoDao grupoDao = (GrupoDao) daoManager.getDao(GrupoDao.class);

			return grupoDao.obtenerGrupoPorNombre(nombreGrupo.toUpperCase(), id_modelo);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
//	---------------------------

}
