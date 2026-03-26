package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.Revision;
import com.osmosyscol.datasuite.persistencia.dao.RevisionDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class RevisionServicio {

	private static RevisionServicio instancia;

	private RevisionServicio() {
	}

	public static RevisionServicio getInstance() {
		if (instancia == null) {
			instancia = new RevisionServicio();
		}
		return instancia;
	}

	// ----------------------------------

	public Boolean actualizarNombreRevision(Integer id_revision, String nombre) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			RevisionDao revisionDao = (RevisionDao) daoManager.getDao(RevisionDao.class);
			revisionDao.actualizarNombreRevision(id_revision, nombre);

			return true;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}

	}

	// -------------------------------------

	public Boolean eliminarRevision(Integer id_revision) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();

			try {

				daoManager.startTransaction();

				RevisionDao revisionDao = (RevisionDao) daoManager.getDao(RevisionDao.class);
				
				revisionDao.avanzarCargasRevision(id_revision);
				revisionDao.eliminarRevision(id_revision);

				daoManager.commitTransaction();

				return true;

			} catch (Exception e) {
				SimpleLogger.setError("Ha ocurrido un error", e);
				return false;
			} finally {
				daoManager.endTransaction();
			}

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en el daoManager ", e);
			return false;
		}

	}

	// -------------------------------------

	public Integer agregarRevision(Integer id_proceso, Integer id_revision_anterior, String nombre) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();

			try {

				daoManager.startTransaction();

				RevisionDao revisionDao = (RevisionDao) daoManager.getDao(RevisionDao.class);
				Integer id_revision = revisionDao.agregarRevision(id_proceso, id_revision_anterior, nombre);

				daoManager.commitTransaction();

				return id_revision;

			} catch (Exception e) {
				SimpleLogger.setError("Ha ocurrido un error", e);
				return null;
			} finally {
				daoManager.endTransaction();
			}

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en el daoManager ", e);
			return null;
		}

	}
	
	public List<Revision> obtenerRevisiones(Integer id_proceso){		
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			RevisionDao revisionDao = (RevisionDao) daoManager.getDao(RevisionDao.class);
			
			return revisionDao.obtenerRevisiones(id_proceso);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
		
		
	}
	
	public List<Revision> obtenerRevisionesRevisor(Integer id_usuario, Integer id_persona, Integer id_negocio){		
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			RevisionDao revisionDao = (RevisionDao) daoManager.getDao(RevisionDao.class);
			
			return revisionDao.obtenerRevisionesRevisor(id_usuario, id_persona, id_negocio);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
	}
	
	public Integer contarRevisionesRevisor(Integer id_usuario, Integer id_persona, Integer id_negocio){		
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			RevisionDao revisionDao = (RevisionDao) daoManager.getDao(RevisionDao.class);
			
			return revisionDao.contarRevisionesRevisor(id_usuario, id_persona, id_negocio);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
	}

	public Revision obtenerRevision(Integer idRevision) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			RevisionDao revisionDao = (RevisionDao) daoManager
					.getDao(RevisionDao.class);

			return revisionDao.obtenerRevision(idRevision);
		} catch (Exception e) {
			SimpleLogger.setError(
					"Ha ocurrido un error obteniendo  la revision "
							+ idRevision, e);
			return null;
		}
	}

}
