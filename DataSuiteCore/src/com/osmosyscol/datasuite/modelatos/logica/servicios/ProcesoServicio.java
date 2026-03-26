package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import co.htsoft.commons.lang.StringUtils;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.Liberador;
import com.osmosyscol.datasuite.logica.dto.LiberadorTipoProceso;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.dto.Preparador;
import com.osmosyscol.datasuite.logica.dto.Proceso;
import com.osmosyscol.datasuite.logica.dto.Revision;
import com.osmosyscol.datasuite.logica.dto.Revisor;
import com.osmosyscol.datasuite.logica.dto.TipoProceso;
import com.osmosyscol.datasuite.logica.dto.TipoProcesoRol;
import com.osmosyscol.datasuite.logica.servicios.ConfiguracionServicio;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;
import com.osmosyscol.datasuite.persistencia.dao.LiberadorDao;
import com.osmosyscol.datasuite.persistencia.dao.PersonaDao;
import com.osmosyscol.datasuite.persistencia.dao.ProcesoDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

/**
 * Servicio para administrar procesos del lado del cliente
 *
 */
public class ProcesoServicio {

	private static ProcesoServicio instancia;

	private ProcesoServicio() {
	}

	public static ProcesoServicio getInstance() {
		if (instancia == null) {
			instancia = new ProcesoServicio();
		}
		return instancia;
	}

	// ----------------------------------

	public Boolean cambiarEstadoProceso(Integer id_proceso, String estado) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			ProcesoDao procesoDao = (ProcesoDao) daoManager.getDao(ProcesoDao.class);
			procesoDao.cambiarEstado(id_proceso, estado);

			return true;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}

	}

	// ----------------------------------

	public Integer crearProceso(Integer id_usuario, Integer id_negocio, String nombre, Integer id_tipo_proceso) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			ProcesoDao procesoDao = (ProcesoDao) daoManager.getDao(ProcesoDao.class);
			
			if(id_tipo_proceso == null){
				TipoProceso tipoproceso = PersonaServicio.getInstance().obtenerTipoProcesoDefecto(id_usuario);

				if(tipoproceso != null){
					id_tipo_proceso = tipoproceso.getId_tipo_proceso();
				}else{
					return null;
				}
				
			}

			return procesoDao.crearProceso(id_usuario, id_negocio, nombre, id_tipo_proceso);
			

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error al crear el proceso", e);
			return null;
		}

	}

	// ----------------------------------

	public Boolean actualizarProceso(Proceso proceso) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			ProcesoDao procesoDao = (ProcesoDao) daoManager.getDao(ProcesoDao.class);

			procesoDao.actualizarProceso(proceso);

			return true;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}

	}

	// ----------------------------------

	public List<Proceso> listarProcesos(Integer id_usuario, Integer id_negocio) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			ProcesoDao procesoDao = (ProcesoDao) daoManager.getDao(ProcesoDao.class);
			
			return procesoDao.listarProcesos(id_usuario, id_negocio);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;

	}
	
	public List<Proceso> listarProcesosDefectoTipoSolicitante(Integer tipo_solicitante) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			ProcesoDao procesoDao = (ProcesoDao) daoManager.getDao(ProcesoDao.class);
			
			return procesoDao.listarProcesosDefectoTipoSolicitante(tipo_solicitante);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;

	}
	
	public List<Proceso> listarProcesosPorTipoProceso(Integer id_usuario, Integer id_tipo_proceso){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			ProcesoDao procesoDao = (ProcesoDao) daoManager.getDao(ProcesoDao.class);
			
			return procesoDao.listarProcesosPorTipoProceso(id_usuario, id_tipo_proceso);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public List<Proceso> listarProcesosPreparador(Integer id_usuario, Integer id_negocio, Integer id_persona, Integer pagina) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			ProcesoDao procesoDao = (ProcesoDao) daoManager.getDao(ProcesoDao.class);

			return procesoDao.listarProcesosPreparador(id_usuario, id_negocio, id_persona, pagina);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;

	}
	
	public List<Proceso> listarProcesosPreparadorContar(Integer id_usuario, Integer id_negocio, Integer id_persona, Integer pagina) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			ProcesoDao procesoDao = (ProcesoDao) daoManager.getDao(ProcesoDao.class);

			return procesoDao.listarProcesosPreparadorContar(id_usuario, id_negocio, id_persona, pagina);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;

	}

	public Integer contarProcesosPreparador(Integer id_usuario, Integer id_negocio, Integer id_persona) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			ProcesoDao procesoDao = (ProcesoDao) daoManager.getDao(ProcesoDao.class);

			return procesoDao.contarProcesosPreparador(id_usuario, id_negocio, id_persona);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;

	}

	public List<Proceso> listarProcesosLiberador(Integer id_usuario, Integer id_negocio, Integer id_persona, Integer pagina) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			ProcesoDao procesoDao = (ProcesoDao) daoManager.getDao(ProcesoDao.class);

			return procesoDao.listarProcesosLiberador(id_usuario, id_negocio, id_persona, pagina);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;

	}

	public Integer contarProcesosLiberador(Integer id_usuario, Integer id_negocio, Integer id_persona) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			ProcesoDao procesoDao = (ProcesoDao) daoManager.getDao(ProcesoDao.class);

			return procesoDao.contarProcesosLiberador(id_usuario, id_negocio, id_persona);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;

	}

	public Proceso obtenerProceso(Integer id_proceso) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			ProcesoDao procesoDao = (ProcesoDao) daoManager.getDao(ProcesoDao.class);
			return procesoDao.obtenerProceso(id_proceso);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;

	}

	/**
	 * Relaciona el formato con el proceo 
	 * @param id_formato - id del formato
	 * @param id_proceso - id del proceso
	 * @return
	 * @author oaortizs
	 */
	public Boolean agregarFormato(Integer id_formato, Integer id_proceso) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			ProcesoDao procesoDao = (ProcesoDao) daoManager.getDao(ProcesoDao.class);
			
			return procesoDao.agregarFormato(id_formato, id_proceso);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;
	}

	/**
	 * Eliminar la relación del formato con el proceso
	 * @param id_formato
	 * @param id_proceso
	 * @return
	 */
	public Boolean eliminarFormato(Integer id_formato, Integer id_proceso) {

		try {
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			ProcesoDao procesoDao = (ProcesoDao) daoManager.getDao(ProcesoDao.class);
			
			return procesoDao.eliminarFormato(id_formato, id_proceso);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;
	}
	
	
	/**
	 * fucnion que trae el total de cargas que se encuentran en estado revisio
	 * o estado liberacion por proceso
	 */
	public Integer obtenerCargasPorProcesoClienteEnTransito(Integer id_proceso){
		
		try {
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			ProcesoDao procesoDao = (ProcesoDao) daoManager.getDao(ProcesoDao.class);
			
			return procesoDao.obtenerCargasPorProcesoClienteEnTransito(id_proceso);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}

		
	}

	public Boolean eliminarProceso(Integer id_proceso){
		try {
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			ProcesoDao procesoDao = (ProcesoDao) daoManager.getDao(ProcesoDao.class);
			
			return procesoDao.eliminarProceso(id_proceso);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
		
	}
	
	

	/**
	 * Devuelve la resta suma de los pesos de los liberadores asociados al proceso menos el peso del proceso
	 * En caso de ser cero o positivo el processo cuenta con suficientes liberadores con peso para hacer liberaciones
	 * @param id_proceso
	 * @return
	 */
	
	public Integer diferenciaPesoLiberadoresYProceso(Integer id_proceso){		
		Proceso proceso=obtenerProceso(id_proceso);	
		return diferenciaPesoLiberadoresYProcesoPorProceso(proceso);
	}
	
	/**
	 * Devuelve la resta suma de los pesos de los liberadores asociados al proceso menos el peso del proceso
	 * En caso de ser cero o positivo el proceso cuenta con suficientes liberadores con peso para hacer liberaciones
	 * @param proceso
	 * @return
	 */
	
	public Integer diferenciaPesoLiberadoresYProcesoPorProceso(Proceso proceso){		
		List<Liberador> liberadores=LiberadorServicio.getInstance().obtenerLiberadoresPorProceso(proceso.getId_proceso());
		int sumaPesos=0;

		for(Liberador l:liberadores){
			
			LiberadorTipoProceso liberadorTipoProceso = LiberadorServicio.getInstance().obtenerLiberadorTipoProceso(l.getId_liberador(), proceso.getId_tipo_proceso());
			sumaPesos += liberadorTipoProceso.getPeso();
		}
		
		Integer diferencia=sumaPesos-proceso.getPeso();	
		
		return diferencia;
	}
	
	
	/**
	 * Lista los procesos que no tendrian suficientes liberadores con peso, para ser liberados, en caso de que este liberador
	 * sea eliminado del proceso.
	 * @param id_liberador
	 * @param pesoLiberador
	 * @return
	 */
	public List<Proceso> listarProcesosAfectadosLiberador(Integer id_liberador, Integer nuevoPesoLiberador, Integer id_tipo_proceso){	

		if(id_liberador == null)
			return null;
		
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			LiberadorDao liberadorDao = (LiberadorDao) daoManager.getDao(LiberadorDao.class);
			ProcesoDao procesoDao = (ProcesoDao) daoManager.getDao(ProcesoDao.class);
			
			Liberador liberador=liberadorDao.obtenerLiberadorPorId(id_liberador);
			
			LiberadorTipoProceso liberadorTipoProceso = LiberadorServicio.getInstance().obtenerLiberadorTipoProceso(id_liberador, id_tipo_proceso);
			int peso = liberadorTipoProceso.getPeso();
			
			List<Proceso> procesosAfectados = new ArrayList<Proceso>();
			List<Proceso> procesos = procesoDao.obtenerProcesosPorLiberador(liberador.getId_liberador());
			for(Proceso p:procesos){
				int diferencia = diferenciaPesoLiberadoresYProcesoPorProceso(p);
				if((diferencia - peso + nuevoPesoLiberador)<0){
					procesosAfectados.add(p);
				}
			}
			return procesosAfectados;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
			
	}
	
	public List<Proceso> listarProcesosAfectadosEliminacionLiberador(Integer id_persona, Integer id_usuario){	
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			LiberadorDao liberadorDao = (LiberadorDao) daoManager.getDao(LiberadorDao.class);
			ProcesoDao procesoDao = (ProcesoDao) daoManager.getDao(ProcesoDao.class);
			
			Liberador liberador=liberadorDao.obtenerLiberadorPorPersonaUsuario(id_persona, id_usuario);

			List<Proceso> procesosAfectados=new ArrayList<Proceso>();
			List<Proceso> procesos=procesoDao.obtenerProcesosPorLiberador(liberador.getId_liberador());
			for(Proceso p:procesos){
				
				LiberadorTipoProceso liberadorTipoProceso = LiberadorServicio.getInstance().obtenerLiberadorTipoProceso(liberador.getId_liberador(), p.getId_tipo_proceso());
				int peso = liberadorTipoProceso.getPeso();
				
				// si diferencia es negativa entonces no se puede liberar el proceso
				int diferencia=diferenciaPesoLiberadoresYProcesoPorProceso(p);
				if((diferencia-peso)<0){
					procesosAfectados.add(p);
				}
			}
			
			return procesosAfectados;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
	
	}
	
	public Boolean permiteCrearProceso(Integer id_usuario, Integer id_tipo_proceso){
		DaoManager daoManager;
		try {
			daoManager = DaoConfig.getDaoManager();
			ProcesoDao procesoDao = (ProcesoDao) daoManager.getDao(ProcesoDao.class);
			return procesoDao.permiteCrearProceso(id_usuario, id_tipo_proceso);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;
	}
	
	public TipoProceso obtenerTipoProcesoPorIdTipoProceso(Integer id_tipo_proceso){
		DaoManager daoManager;
		try {
			daoManager = DaoConfig.getDaoManager();
			ProcesoDao procesoDao = (ProcesoDao) daoManager.getDao(ProcesoDao.class);
			return procesoDao.obtenerTipoProcesoPorIdTipoProceso(id_tipo_proceso);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
		
		
	}
	
	public Boolean permiteRevisorMultiplesInstancias(Integer id_tipo_proceso){
		DaoManager daoManager;
		try {
			daoManager = DaoConfig.getDaoManager();
			ProcesoDao procesoDao = (ProcesoDao) daoManager.getDao(ProcesoDao.class);
			return procesoDao.permiteRevisorMultiplesInstancias(id_tipo_proceso);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public Boolean asociarRevisorProcesos(List<TipoProcesoRol> tipoprocesos, Integer id_usuario, String identificacion, Integer tipoDocumento, String rol) {

		try {
			SimpleLogger.setInfo("asociarRevisorProcesos");
			if (!StringUtils.equals(Constantes.ROL_REVISOR, rol) && !StringUtils.equals(Constantes.ROL_PREPARADOR, rol)) {
				SimpleLogger.setDebug("NO es revisor ni preparador " + rol);
				return true;
			}

			String revision_automatica = ConfiguracionServicio.getInstance().obtenerValorByEtiqueta("CREAR_REVISION_AUTOMATICA");
			
			Persona persona = PersonaServicio.getInstance().obtenerPersonaPorIdentificacion(identificacion, tipoDocumento);
			
			
			for (TipoProcesoRol tipoProcesoRol : tipoprocesos) {
				tipoProcesoRol.setId_persona(persona.getId_persona());
			}
			
			
			for (TipoProcesoRol tipoProcesoRol : tipoprocesos) {

				Integer id_tipo_proceso = tipoProcesoRol.getId_tipo_proceso();
				if (id_tipo_proceso != null) {
					List<Proceso> procesos = ProcesoServicio.getInstance().listarProcesosPorTipoProceso(id_usuario, id_tipo_proceso);
//					String rol = tipoProcesoRol.getRol();

					SimpleLogger.setDebug("asociarUsuarioProcesos rol, " + rol + " tipo_proceso " + tipoProcesoRol.getId_tipo_proceso() );
					for (Proceso proceso : procesos) {
						if (StringUtils.equals(Constantes.ROL_REVISOR, rol) && (revision_automatica == null || Constantes.SI.equals(revision_automatica))) {
							List<Revision> revisiones = RevisionServicio.getInstance().obtenerRevisiones(proceso.getId_proceso());
							Revision revision = buscarUltimaRevision(revisiones);
							Integer id_revisionAnt = revision == null ? null : revision.getId_revision();
							Integer id_revision = RevisionServicio.getInstance().agregarRevision(proceso.getId_proceso(), id_revisionAnt, "Contador/Revisor Fiscal");
							
							List<Revisor> revisores = RevisorServicio.getInstance().obtenerRevisores(id_usuario, id_tipo_proceso);
							Revisor revisor = buscarRevisor(revisores, persona.getId_persona());
							SimpleLogger.setDebug("Se asocia revisor  " + revisor.getId_revisor() + " a la revision " + id_revision);
							RevisorServicio.getInstance().asociarRevisor(id_revision, revisor.getId_revisor());
						}
						else {
							List<Preparador> preparadores = PreparadorServicio.getInstance().obtenerPreparadores(id_usuario, id_tipo_proceso);
							Preparador preparador = buscarPreparador(preparadores, persona.getId_persona());
							PreparadorServicio.getInstance().asociarPreparador(proceso.getId_proceso(), preparador.getId_preparador());
						}
					}
				}
			}
			return true;
		} catch (Exception e) {
			SimpleLogger.setError("Error al guardar el proceso Rol: ", e);
		}
		return false;
	
	}
	private Preparador buscarPreparador(List<Preparador> preparadores, Integer id_persona) {
		for (Preparador preparador : preparadores) {
			if (preparador.getId_persona().equals(id_persona)) {
				return preparador;
			}
		}
		return null;
	}

	private Revisor buscarRevisor(List<Revisor> revisores, Integer id_persona) {
		for (Revisor revisor : revisores) {
			if (revisor.getId_persona().equals(id_persona.intValue())) {
				return revisor;
			}
		}
		return null;
	}

	private Revision buscarUltimaRevision(List<Revision> revisiones) {
		if (CollectionUtils.isNotEmpty(revisiones)) {
			
			for (Revision revision : revisiones) {
				if (revision.getId_revision_siguiente() == null) {
					return revision;
				}
			}
		}
		return null;
	}
}



