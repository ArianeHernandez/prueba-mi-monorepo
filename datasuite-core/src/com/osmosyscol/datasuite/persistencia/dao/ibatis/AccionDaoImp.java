package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.Accion;
import com.osmosyscol.datasuite.logica.dto.CargaAccionAutomatica;
import com.osmosyscol.datasuite.logica.dto.Instancia;
import com.osmosyscol.datasuite.modelatos.logica.dto.ListaDinamica;
import com.osmosyscol.datasuite.persistencia.dao.AccionDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class AccionDaoImp extends BaseSqlMapDao implements AccionDao {

	public AccionDaoImp(DaoManager daoManager) {
		super(daoManager);
	}
	
	
	public Boolean insertarAccion(Integer id_accion,String nombre,Integer id_instancia){
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_accion", id_accion);
		map.put("nombre", nombre);
		map.put("id_instancia", id_instancia);
		
		try {
			insert("Accion.insertarAccion", map);
			return true;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en AccionDaoImp.insertarAccion", e);
			return false;
		}
		
	}
	
	public Integer obtenerSiguienteIdAccion(){
		return (Integer) queryForObject("Accion.obtenerSiguienteIdAccion", null);
	}
	
	public Accion obtenerAccion(Integer id_accion){
		return (Accion) queryForObject("Accion.obtenerAccion", id_accion);
	}
	
	public List<Accion> obtenerAccionesPorInstancia(Integer id_instancia){
		return queryForList("Accion.obtenerAccionesPorInstancia", id_instancia);
	}
	
	public Boolean borrarAccion(Integer id_accion){
		Integer cant = delete("Accion.borrarAccion", id_accion);
		return cant>0;
	}
	
	public Boolean actualizarNombre(Integer id_accion,String nombre){
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_accion", id_accion);
		map.put("nombre", nombre);
		
		Integer cant = update("Accion.actualizarNombre", map);
		
		return cant>0;
		
	}
	
	public Boolean insertarInstaciaDestino(Integer id_accion,Integer id_instancia_destino){
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_accion", id_accion);
		map.put("id_instancia_destino", id_instancia_destino);
		
		try {
			insert("Accion.insertarInstaciaDestino", map);
			return true;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en AccionDaoImp.insertarInstaciaDestino", e);
			return false;
		}
		
	}
	
	public Instancia obtenerInstanciaDestino(Integer id_accion,Integer id_instancia_destino){
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_accion", id_accion);
		map.put("id_instancia_destino", id_instancia_destino);
		
		return (Instancia) queryForObject("Accion.obtenerInstanciaDestino", map);
	}
	
	public List<Integer> obtenerIdInstanciasDestinoPorAccion(Integer id_accion){
		
		return queryForList("Accion.obtenerIdInstanciasDestinoPorAccion", id_accion);
	}
	
	public List<Instancia> obtenerInstanciasDestinoPorAccion(Integer id_accion){
		
		return queryForList("Accion.obtenerInstanciasDestinoPorAccion", id_accion);
	}
	
	public Boolean borrarInstanciaDestino(Integer id_accion,Integer id_instancia_destino){
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_accion", id_accion);
		map.put("id_instancia_destino", id_instancia_destino);

		Integer cant = delete("Accion.borrarInstanciaDestino", map);
		
		return cant>0;
	}
	
	public List<Instancia> obtenerInstDisponiblesParaAsignar(Integer id_proceso_admin, Integer id_accion){
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_proceso_admin", id_proceso_admin);
		map.put("id_accion", id_accion);
		
		return queryForList("Accion.obtenerInstDisponiblesParaAsignar", map);
		
	}
	
	public List<Accion> obtenerAccionesPorProceso(Integer id_proceso_admin){		
		return queryForList("Accion.obtenerAccionesPorProceso", id_proceso_admin);
	}


	public Boolean actualizarOculto(Integer id_accion, String oculto) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_accion", id_accion);
		map.put("oculto", oculto);
		
		Integer cant = update("Accion.actualizarOculto", map);
		
		return cant>0;
		
	}

	/**
	 * Retorna las acciones de una instancia origen que tengan asociadas instancias destino
	 */

	public List<Accion> obtenerAccionesConInstanciaDestinoPorInstanciaOrigen(
			Integer id_instancia_origen) {
		return queryForList("Accion.obtenerAccionesConInstanciaDestinoPorInstanciaOrigen", id_instancia_origen);
	}

	@Override
	public List<ListaDinamica> obtenerListasDinamicas() {
		// TODO Auto-generated method stub
		return queryForList("Accion.obtenerListasDinamicas");
	}
	
	@Override
	public Boolean asignarListaDinamica(Integer id_accion,
			Integer id_lista_dinamica) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_accion", id_accion);
		map.put("id_lista_dinamica", id_lista_dinamica);

		Integer cant = update("Accion.asignarListaDinamica", map);

		return cant > 0;
	}
	
	@Override
	public ListaDinamica obtenerAccionAutomatica(Integer id_accion) {
		return (ListaDinamica) queryForObject("Accion.obtenerAccionAutomatica", id_accion);
	}

	@Override
	public List<CargaAccionAutomatica> obtenerCargasEnAccionesAutomaticas() {
		return queryForList("Accion.obtenerCargasAccionesAutomaticas");
	}
	
	@Override
	public Boolean actualizarMensajeEjecucion(Integer id_accion,
			String mensaje_ejecucion) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_accion", id_accion);
		map.put("mensaje_ejecucion", mensaje_ejecucion);

		Integer cant = update("Accion.actualizarMensajeEjecucion", map);

		return cant > 0;
	}
	
	public Boolean actualizarAccion (Accion accion) {
		Boolean resultado = false;
		try {
			resultado = update("Accion.actualizarAccion", accion) > 0;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en AccionRedireccionDaoImp.guardarAccionRedireccion", e);
		}
		return resultado;
	}

}
