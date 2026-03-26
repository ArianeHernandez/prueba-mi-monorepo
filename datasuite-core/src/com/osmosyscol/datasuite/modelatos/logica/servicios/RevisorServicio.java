package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.util.ArrayList;
import java.util.List;

import co.htsoft.commons.lang.StringUtils;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.dto.Preparador;
import com.osmosyscol.datasuite.logica.dto.Proceso;
import com.osmosyscol.datasuite.logica.dto.Revision;
import com.osmosyscol.datasuite.logica.dto.Revisor;
import com.osmosyscol.datasuite.logica.dto.TipoProcesoRol;
import com.osmosyscol.datasuite.logica.dto.TipoRevisor;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;
import com.osmosyscol.datasuite.persistencia.dao.RevisorDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class RevisorServicio {

	private static RevisorServicio instancia;

	private RevisorServicio() {
	}

	public static RevisorServicio getInstance() {
		if (instancia == null) {
			instancia = new RevisorServicio();
		}
		return instancia;
	}

	// ----------------------------------

	public List<Revisor> obtenerRevisores(Integer id_usuario, Integer id_tipo_proceso) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			RevisorDao revisorDao = (RevisorDao) daoManager.getDao(RevisorDao.class);

			return revisorDao.obtenerRevisores(id_usuario, id_tipo_proceso);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}

	}

	// ----------------------------------

	public Boolean asociarRevisor(Integer id_revision, Integer id_revisor) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			RevisorDao revisorDao = (RevisorDao) daoManager.getDao(RevisorDao.class);

			revisorDao.asociarRevisor(id_revision, id_revisor);

			return true;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}

	}
	
	// -------------------------------------

	public Boolean desasociarRevisor(Integer id_revision, Integer id_revisor) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			RevisorDao revisorDao = (RevisorDao) daoManager.getDao(RevisorDao.class);

			return revisorDao.validarDesasociarRevisor(id_revision, id_revisor);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}

	}
	
	public List<Revisor> obtenerRevisoresPorRevision(Integer id_revision){
		try {
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			RevisorDao revisorDao = (RevisorDao) daoManager.getDao(RevisorDao.class);
			return revisorDao.obtenerRevisoresPorRevision(id_revision);			
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
		
		
	}
	
	public Boolean guardarTipoRevisor(Integer id_usuario, Integer id_tipo_revisor, String identificacion, Integer tipoDocumento, String rol) {

		try {
			SimpleLogger.setInfo("asociarRevisorProcesos");
			if (!StringUtils.equals(Constantes.ROL_REVISOR, rol) ) {
				SimpleLogger.setDebug("No oes revisor " + rol);
				return true;
			}

			Persona persona = PersonaServicio.getInstance().obtenerPersonaPorIdentificacion(identificacion, tipoDocumento);
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			RevisorDao revisorDao = (RevisorDao) daoManager.getDao(RevisorDao.class);
			return revisorDao.guardarTipoRevisor(id_usuario, persona.getId_persona(), id_tipo_revisor);			
			
		} catch (Exception e) {
			SimpleLogger.setError("Error al guardar el proceso Rol: ", e);
		}
		return false;
	}
	
	public List<TipoRevisor> obtenerTiposRevisor() {
		try {
			List<TipoRevisor> listado = new ArrayList<TipoRevisor>();
			TipoRevisor contador = new TipoRevisor();
			contador.setId_tipo_revisor(Constantes.ID_TIPO_REVISOR_CONTADOR);
			contador.setNombre("Contador");
			listado.add(contador );
			
			TipoRevisor revisor_fiscal = new TipoRevisor();
			revisor_fiscal.setId_tipo_revisor(Constantes.ID_TIPO_REVISOR_REVISOR_F);
			revisor_fiscal.setNombre("Revisor Fiscal");
			listado.add(revisor_fiscal );
			
			return listado ;

		} catch (Exception e) {
			SimpleLogger.setError("Se ha generado error al obtener obtenerTiposRevisor", e);
			return null;
		}

	}

}
