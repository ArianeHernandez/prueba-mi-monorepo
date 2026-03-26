package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.util.ArrayList;
import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.Instancia;
import com.osmosyscol.datasuite.logica.dto.ProcesoAdmin;
import com.osmosyscol.datasuite.logica.dto.RestriccionAdministrativo;
import com.osmosyscol.datasuite.logica.servicios.AdministrativoServicio;
import com.osmosyscol.datasuite.modelatos.logica.dto.SemaforoProcesoAdmin;
import com.osmosyscol.datasuite.persistencia.dao.ProcesoAdminDao;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class ProcesoAdminServicio {

	private static ProcesoAdminServicio instancia;

	private ProcesoAdminServicio() {
	}

	public static ProcesoAdminServicio getInstance() {
		if (instancia == null) {
			instancia = new ProcesoAdminServicio();
		}
		return instancia;
	}

	public Boolean insertarProcesoAdmin(Integer id_proceso_admin, Integer id_negocio, Integer id_formato_entrada, String nombre, Integer id_tipo_proceso){
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			ProcesoAdminDao procesoAdminDao = (ProcesoAdminDao) daoManager.getDao(ProcesoAdminDao.class);
			
			return procesoAdminDao.insertarProcesoAdmin(id_proceso_admin, id_negocio, id_formato_entrada, nombre, id_tipo_proceso);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ProcesoAdminServicio.insertarProcesoAdmin", e);
			return false;
		}
	}

	public List<ProcesoAdmin> obtenerProcesosAdminPorNegocio(Integer id_negocio){
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			ProcesoAdminDao procesoAdminDao = (ProcesoAdminDao) daoManager.getDao(ProcesoAdminDao.class);
			return procesoAdminDao.obtenerProcesosAdminPorNegocio(id_negocio);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ProcesoAdminServicio.obtenerProcesosAdminPorNegocio", e);
			return null;
		}
	}
	
	
	public ProcesoAdmin obtenerProcesoAdmin(Integer id_proceso_admin){
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			ProcesoAdminDao procesoAdminDao = (ProcesoAdminDao) daoManager.getDao(ProcesoAdminDao.class);
			return procesoAdminDao.obtenerProcesoAdmin(id_proceso_admin);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ProcesoAdminServicio.obtenerProcesoAdmin", e);
			return null;
		}
	}
	
	public Integer obtenerSiguienteId(){
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			ProcesoAdminDao procesoAdminDao = (ProcesoAdminDao) daoManager.getDao(ProcesoAdminDao.class);
			return procesoAdminDao.obtenerSiguienteId();

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ProcesoAdminServicio.obtenerSiguienteId", e);
			return null;
		}
	}
	
	public Boolean borrarProcesoAdmin(Integer id_proceso_admin){
		
		DaoManager daoManager = DaoConfig.getDaoManager(1);
		
		try {
			
			daoManager.startTransaction();
			
			ProcesoAdminDao procesoAdminDao = (ProcesoAdminDao) daoManager.getDao(ProcesoAdminDao.class);
			
			List<Instancia> instancias = InstanciaServicio.getInstance().obtenerInstanciasPorProceso(id_proceso_admin);
			
			if(instancias!=null){
				for (Instancia instancia : instancias) {
					InstanciaServicio.getInstance().borrarInstancia(instancia.getId_instancia());
				}
			}
			
			procesoAdminDao.borrarProcesoAdmin(id_proceso_admin);
			
			daoManager.commitTransaction();
			
			return true;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ProcesoAdminServicio.borrarProcesoAdmin", e);
			return false;
		}
		finally{
			daoManager.endTransaction();
		}
		
	}
	
	public Boolean actualizarProcesoAdmin(Integer id_proceso_admin,Integer id_negocio, Integer id_formato_entrada, String nombre, Integer id_tipo_proceso){
		
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			ProcesoAdminDao procesoAdminDao = (ProcesoAdminDao) daoManager.getDao(ProcesoAdminDao.class);
			return procesoAdminDao.actualizarProcesoAdmin(id_proceso_admin, id_negocio, id_formato_entrada, nombre, id_tipo_proceso);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ProcesoAdminServicio.actualizarProcesoAdmin", e);
			return false;
		}

	}
	
	public List<ProcesoAdmin> obtenerProcesosAdmin(){
		
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			ProcesoAdminDao procesoAdminDao = (ProcesoAdminDao) daoManager.getDao(ProcesoAdminDao.class);
			return procesoAdminDao.obtenerProcesosAdmin();

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ProcesoAdminServicio.obtenerProcesosAdmin", e);
			return null;
		}
		
	}
	
	public Boolean verificarExistencia(Integer id_negocio, Integer id_formato_entrada,Integer id_proceso_admin){
		
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			ProcesoAdminDao procesoAdminDao = (ProcesoAdminDao) daoManager.getDao(ProcesoAdminDao.class);
			return procesoAdminDao.verificarExistencia(id_negocio, id_formato_entrada,id_proceso_admin);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ProcesoAdminServicio.verificarExistencia", e);
			return false;
		}
		
	}
	
	public List<SemaforoProcesoAdmin> obtenerSemaforoProcesoAdminPorAdministrativo(Integer id_administrativo) {

		Integer intervalos = Constantes.INTERVALOS_SEMAFORO_PROCESOS;

		// Se consultan todos los procesos del administrativo
		ProcesoAdminServicio procesoAdminServicio = ProcesoAdminServicio.getInstance();
		List<ProcesoAdmin> procesos = procesoAdminServicio.obtenerProcesosPorAdministrativo(id_administrativo);
		
		CargaServicio cargaServicio = CargaServicio.getInstance();

		List<SemaforoProcesoAdmin> listadoDeSemaforos = new ArrayList<SemaforoProcesoAdmin>();
		if (procesos != null && procesos.size() > 0) {

			// Por cada proceso se cuentan las cargas
			for (ProcesoAdmin procesoAdmin : procesos) {
				List<Integer> listadoCargasPendientes = new ArrayList<Integer>();
				Integer totalCargas = 0;
				List<Carga> cargasFiltradas = CargaServicio.getInstance().obtenerCargasPorProcesoAdmin(id_administrativo, procesoAdmin.getId_proceso_admin(), null, null, null, null);
				
				// Se cuenta las cargas pendientes para cada uno de los intervalos
				// incluyendo el intervalo 0 en el cual se contabilizan las cargas
				// que aun estan estado REQUIREN_ADJUNTO(Cargas que aun necesitan informacion del cliente );

				for (int i = 0; i <= intervalos; i++) {
					Integer cargasPendientes = cargaServicio.cargasPendientesPorProcesoAdmin(cargasFiltradas, id_administrativo, procesoAdmin.getId_proceso_admin(), intervalos, i);
					totalCargas +=cargasPendientes;
					listadoCargasPendientes.add(cargasPendientes);
				}

				SemaforoProcesoAdmin semaforoProcesoAdmin = new SemaforoProcesoAdmin();
				semaforoProcesoAdmin.setId_proceso_admin(procesoAdmin.getId_proceso_admin());
				semaforoProcesoAdmin.setNombre_proceso(procesoAdmin.getNombre());
				semaforoProcesoAdmin.setNombre_negocio(procesoAdmin.getNombre_negocio());
				semaforoProcesoAdmin.setCargasPorIntervalo(listadoCargasPendientes);
				semaforoProcesoAdmin.setTotal_intervalos(intervalos);
				semaforoProcesoAdmin.setTotal_cargas(totalCargas);
				listadoDeSemaforos.add(semaforoProcesoAdmin);

			}
		}

		return listadoDeSemaforos;

	}
	
	public List<ProcesoAdmin> obtenerProcesosPorAdministrativo(Integer id_administrativo){
		try {
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			ProcesoAdminDao procesoAdminDao = (ProcesoAdminDao) daoManager.getDao(ProcesoAdminDao.class);
			
			//Se consultan todos los procesos asociados a los roles del administrativo
			List<ProcesoAdmin> procesosSinFiltrar = procesoAdminDao.obtenerProcesosPorAdministrativo(id_administrativo);
			
			//Se deben filtrar los procesos segun las restricciones de los roles asociados al administrativo
			AdministrativoServicio administrativoServicio= AdministrativoServicio.getInstance();
			List<RestriccionAdministrativo> restricciones = administrativoServicio.obtenerRestriccionesPorAdministrativo(id_administrativo);
			
			//Lista de procesos filtrados luego de revisar restricciones
			List<ProcesoAdmin> procesosFiltrados;
			
			
			if(restricciones!=null && restricciones.size()>0){
				//Verificamos si tiene permiso para todos los negocios
				if(!tienePermisoParaTodosLosNegocios(restricciones)){
					//Se debe filtrar los procesos por negocio segun las restricciones
					ArrayList<ProcesoAdmin> procesosFiltradosPorNegocio = new ArrayList<ProcesoAdmin>();
					
					for (RestriccionAdministrativo restriccionAdministrativo : restricciones) {
						for (ProcesoAdmin procesoAdmin : procesosSinFiltrar) {
							if(procesoAdmin.getId_negocio().equals(restriccionAdministrativo.getId_negocio())){
								
								procesosFiltradosPorNegocio.add(procesoAdmin);
								
							}
						}
						
					}
					
					procesosFiltrados = procesosFiltradosPorNegocio;
					
				}else{
					procesosFiltrados = procesosSinFiltrar;
				}
			}else{
				//Si no hay restricciones se deben ver todos los procesos
				procesosFiltrados = procesosSinFiltrar;
			}
			
			
			
			return procesosFiltrados;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ProcesoAdminServicio.obtenerProcesosAdminPorNegocio", e);
			
		}
		
		return null;
		
	}
	
	
	private Boolean tienePermisoParaTodosLosNegocios(List<RestriccionAdministrativo> restricciones){
		Boolean  tienePermisoParaTodosLosNegocios = false;
		
		for (RestriccionAdministrativo restriccionAdministrativo : restricciones) {
			if(restriccionAdministrativo.getId_negocio()==null){
				tienePermisoParaTodosLosNegocios=true;
			}
			
		}
		
		return tienePermisoParaTodosLosNegocios;
	} 
	
	
	public ProcesoAdmin obtenerProcesoParaAsociarCarga(Integer id_carga){
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			ProcesoAdminDao procesoAdminDao = (ProcesoAdminDao) daoManager.getDao(ProcesoAdminDao.class);
			return procesoAdminDao.obtenerProcesoParaAsociarCarga(id_carga);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ProcesoAdmin.obtenerProcesoParaAsociarCarga", e);
			return null;
		}
	}
	
	public List<ProcesoAdmin> obtenerProcesosAdminPorTipoProceso(Integer id_tipo_proceso){
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			ProcesoAdminDao procesoAdminDao = (ProcesoAdminDao) daoManager.getDao(ProcesoAdminDao.class);
			return procesoAdminDao.obtenerProcesosAdminPorTipoProceso(id_tipo_proceso);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ProcesoAdmin.obtenerProcesosAdminPorTipoProceso", e);
			return null;
		}
	}
	
	public List<ProcesoAdmin> obtenerProcesosAdminPorTipoProcesoAdministrativo(Integer id_tipo_proceso, Integer id_administrativo){
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			ProcesoAdminDao procesoAdminDao = (ProcesoAdminDao) daoManager.getDao(ProcesoAdminDao.class);
			return procesoAdminDao.obtenerProcesosAdminPorTipoProcesoAdministrativo(id_tipo_proceso, id_administrativo);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ProcesoAdmin.obtenerProcesosAdminPorTipoProcesoAdministrativo", e);
			return null;
		}
	}
	
		
	
}
