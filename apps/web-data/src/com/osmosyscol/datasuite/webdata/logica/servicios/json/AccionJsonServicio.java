package com.osmosyscol.datasuite.webdata.logica.servicios.json;

import java.util.List;
import java.util.Map;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.logica.dto.Accion;
import com.osmosyscol.datasuite.logica.dto.Instancia;
import com.osmosyscol.datasuite.modelatos.logica.dto.ListaDinamica;
import com.osmosyscol.datasuite.modelatos.logica.servicios.AccionServicio;

public class AccionJsonServicio implements JsonService {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public Boolean insertarAccion(Integer id_accion,String nombre,Integer id_instancia){
		return AccionServicio.getInstance().insertarAccion(id_accion, nombre, id_instancia);
	}
	
	public Integer obtenerSiguienteIdAccion(){
		return AccionServicio.getInstance().obtenerSiguienteIdAccion();
	}
	
	public Accion obtenerAccion(Integer id_accion){
		return AccionServicio.getInstance().obtenerAccion(id_accion);
	}
	
	public List<Accion> obtenerAccionesPorInstancia(Integer id_instancia){
		return AccionServicio.getInstance().obtenerAccionesPorInstancia(id_instancia);
	}
	
	public Boolean borrarAccion(Integer id_accion){
		return AccionServicio.getInstance().borrarAccion(id_accion);
	}
	
	public Boolean actualizarNombre(Integer id_accion,String nombre){
		return AccionServicio.getInstance().actualizarNombre(id_accion, nombre);
	}
	
	public Boolean insertarInstaciaDestino(Integer id_accion,Integer id_instancia_destino){
		return AccionServicio.getInstance().insertarInstaciaDestino(id_accion, id_instancia_destino);
	}
	
	public Instancia obtenerInstanciaDestino(Integer id_accion,Integer id_instancia_destino){
		return AccionServicio.getInstance().obtenerInstanciaDestino(id_accion, id_instancia_destino);
	}
	
	public List<Instancia> obtenerInstanciasDestinoPorAccion(Integer id_accion){
		return AccionServicio.getInstance().obtenerInstanciasDestinoPorAccion(id_accion);
	}
	
	public Boolean borrarInstanciaDestino(Integer id_accion,Integer id_instancia_destino){
		return AccionServicio.getInstance().borrarInstanciaDestino(id_accion, id_instancia_destino);
	}
	
	public List<Instancia> obtenerInstDisponiblesParaAsignar(Integer id_proceso_admin, Integer id_accion){
		return AccionServicio.getInstance().obtenerInstDisponiblesParaAsignar(id_proceso_admin, id_accion);
	}
	
	public List<Accion> obtenerAccionesPorProceso(Integer id_proceso_admin){
		return AccionServicio.getInstance().obtenerAccionesPorProceso(id_proceso_admin);
	}

	public void inicializarVariablesSession(Integer id_carga, Integer id_instancia){
		this.session.setAttribute("var.id_carga", id_carga);
		this.session.setAttribute("var.id_instancia", id_instancia);
		
	}
		
	public void inicializarVariablesSessionParaAdminHijo(Integer id_carga){
		this.session.setAttribute("var.id_carga_admin_hijo", id_carga);
		
		
	}	
	
	public Boolean actualizarTipoAccion(Integer id_accion, String oculto){
		return AccionServicio.getInstance().actualizarOculto(id_accion, oculto);
	}
	
	public ListaDinamica obtenerAccionAutomatica(Integer id_accion){
		return AccionServicio.getInstance().obtenerAccionAutomatica(id_accion);
	}
	
	public List<ListaDinamica> obtenerListasDinamicas() {
		return AccionServicio.getInstance().obtenerListasDinamicas();
	}
	
	public Boolean asignarListaDinamica(Integer id_accion,
			Integer id_lista_dinamica){
		return AccionServicio.getInstance().asignarListaDinamica(id_accion, id_lista_dinamica);
	}
	
	public Boolean actualizarMensajeEjecucion(Integer id_accion, String mensaje_ejecucion) {
		return AccionServicio.getInstance().actualizarMensajeEjecucion(id_accion, mensaje_ejecucion);
	}
	
	public List<Integer> obtenerIdInstanciasDestinoPorAccion (Integer id_accion) {
		return AccionServicio.getInstance().obtenerIdInstanciasDestinoPorAccion(id_accion);
	}
	
	public Map<Integer, Accion> obtenerAccionInstanciasDestinoPorProcesoAdmin (Integer id_proceso_admin){
		return AccionServicio.getInstance().obtenerAccionInstanciasDestinoPorProcesoAdmin(id_proceso_admin);
	}
	
	public Boolean actualizarAccion (Accion accion) {
		return AccionServicio.getInstance().actualizarAccion(accion);
	}
}
