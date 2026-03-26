package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.modelatos.logica.dto.Tarea;
import com.osmosyscol.datasuite.modelatos.logica.hilo.TareaHilo;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.TareaDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class TareaServicio {

	private static TareaServicio tareaServicio;

	private static Map<Integer, TareaHilo> procesoEnEjecucion = new HashMap<Integer, TareaHilo>();

	private TareaServicio() {

	}

	public static TareaServicio getInstance() {
		if (tareaServicio == null) {
			tareaServicio = new TareaServicio();
		}
		return tareaServicio;
	}

	public List<Tarea> obtenerTareas(Integer numPagina) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			TareaDao dao = (TareaDao) daoManager.getDao(TareaDao.class);

			List<Tarea> tareas = dao.obtenerTareas(numPagina);
			return tareas;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;
	}

	public Integer contarTareas() {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			TareaDao dao = (TareaDao) daoManager.getDao(TareaDao.class);

			Integer total = dao.contarTareas();
			return total;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;
	}

	public Tarea obtenerTarea(Integer id_tarea) {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			TareaDao dao = (TareaDao) daoManager.getDao(TareaDao.class);

			Tarea tarea = dao.obtenerTarea(id_tarea);
			return tarea;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;
	}

	public Boolean guardarTarea(Tarea tarea) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			TareaDao dao = (TareaDao) daoManager.getDao(TareaDao.class);

			Integer id = dao.guardarTarea(tarea);
			tarea.setId_tarea(id);
			
			if (tarea.getId_tarea() != null) {
				iniciarTarea(tarea);
			}
			
			return true;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return false;
	}

	public Boolean eliminarTarea(Integer id_tarea) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			TareaDao dao = (TareaDao) daoManager.getDao(TareaDao.class);

			detener(id_tarea);
			Boolean rta = dao.eliminarTarea(id_tarea);
			return rta;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return false;
	}

	public Boolean iniciarTareas() {

		if (!iniciarTareasProgramadas()) {
			return false;
		}
		
		List<Tarea> tareas = obtenerTareas(null);

		Boolean exito = true;
		
		if (tareas != null) {
			for (Tarea tarea : tareas) {
				exito = exito && iniciarTarea(tarea);
			}
		}
		return exito;
	}

	public Boolean finalizarTareas() {

		if (!iniciarTareasProgramadas()) {
			return false;
		}
		
		List<Tarea> tareas = obtenerTareas(null);

		if (tareas != null) {
			for (Tarea tarea : tareas) {
				detener(tarea.getId_tarea());
			}
		}
		return true;
	}
	
	public Boolean iniciarTarea(Tarea tarea) {
		
		if (!iniciarTareasProgramadas()) {
			return false;
		}
		
		Integer id_tarea = null;
		if (tarea != null) {
			tarea.getId_tarea();
		}
		try {
			
			TareaHilo hilo = null;
			
			if (procesoEnEjecucion.containsKey(tarea.getId_tarea())) {
				hilo = procesoEnEjecucion.get(tarea.getId_tarea());
				hilo.reiniciar();
			}
			else {
				hilo = new TareaHilo(tarea.getId_tarea());
				Boolean rta = hilo.iniciar();
				procesoEnEjecucion.put(tarea.getId_tarea(), hilo);
				return rta;
			}
			
			return true;
			
		} catch (Exception e) {
			SimpleLogger.setError("No fue posible iniciar la tarea: "+id_tarea);
		}

		return false;
	}
	
	public Boolean activarTarea(Integer id_tarea){
		Tarea tarea = obtenerTarea(id_tarea);
		if (tarea != null) {
			tarea.setActivar("S");
			guardarTarea(tarea);
			return iniciarTarea(tarea);
		}
		return false;
	}
	
	public TareaHilo obtenerTareaEjecucion(Integer id_tarea){
		return procesoEnEjecucion.get(id_tarea);
	}
	
	public Map<Integer, String> obtenerTiposIntervalos(){
		return Constantes.MAP_TAREAS_INTERVALOS;
	}

	
	public Boolean detener(Integer id_tarea) {
		
		TareaHilo tareaHilo = obtenerTareaEjecucion(id_tarea);
		if (tareaHilo != null) {
			tareaHilo.detener();
			return true;
		}
		return false;
	}

	public Boolean desactivarTarea(Integer id_tarea) {
		
		detener(id_tarea);
		
		Tarea tarea = obtenerTarea(id_tarea);
		if (tarea != null) {
			tarea.setActivar("N");
			return guardarTarea(tarea);
		}
		return false;
	}
	
	public void finalizaTarea(Integer id_tarea){
		TareaHilo hilo = obtenerTareaEjecucion(id_tarea);
		if (hilo != null && !hilo.enEjecucion() && !hilo.iniciado()) {
			procesoEnEjecucion.remove(id_tarea);
		}
	}
	
	public Boolean esProcesoEnEjecucion(Integer id_tarea) {
		TareaHilo tarea = obtenerTareaEjecucion(id_tarea);
		if (tarea == null) {
			return false;
		}
		return tarea.esProcesoEnEjecucion();
	}
	
	public Boolean enEjecucion(Integer id_tarea) {
		TareaHilo tarea = obtenerTareaEjecucion(id_tarea);
		if (tarea == null) {
			return false;
		}
		return tarea.enEjecucion();
	}

	public Boolean iniciarTareasProgramadas(){
		String iniciarTareas = ParametrosInicio.getProperty("EjecutarTareasProgramadas");
		if (StringUtils.esNoVacio(iniciarTareas) && StringUtils.esVerdad(iniciarTareas)) {
			return true;
		}
		return false;
		
	}

	public Boolean esTareaActiva(Integer idTarea) {
		Tarea tarea = obtenerTarea(idTarea);
		if (tarea != null && StringUtils.esVerdad(tarea.getActivar())) {
			return true;
		}
		return false;
	}
	
	public String calcularProximaEjecucion(Integer id_tarea) {
		TareaHilo tareaHilo = obtenerTareaEjecucion(id_tarea);
		if (tareaHilo != null && tareaHilo.getInicioEjecucion() != null) {
			
			
			Date proxEjec = calcularProximaEjecucion(tareaHilo.getInicioEjecucion(), id_tarea);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			return dateFormat.format(proxEjec);
		}
		return null;
		
	}
	public Date calcularProximaEjecucion(Date fechaBase, Integer id_tarea) {

		Tarea tarea = TareaServicio.getInstance().obtenerTarea(id_tarea);
		
		if (tarea == null ) {
			return null;
		}

		Integer intervalo = tarea.getIntervalo_ejecucion();

		Calendar calendar = Calendar.getInstance();
		Calendar calendarAct = Calendar.getInstance();

		calendar.setTime(fechaBase);

		while (calendarAct.after(calendar)) {
			calendar.add(tarea.getTipo_intervalo(), intervalo);
		}

		return calendar.getTime();
	}
	
	public Boolean iniciarSincronizacionSIFI(){
		String iniciar = ParametrosInicio.getProperty("sippagos.endpoint");
		if (!StringUtils.esVacio(iniciar)) {
			return true;
		}
		return false;
		
	}
}
