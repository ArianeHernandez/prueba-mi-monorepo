package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.logica.dto.Accion;
import com.osmosyscol.datasuite.logica.dto.CargaAccionAutomatica;
import com.osmosyscol.datasuite.logica.dto.Instancia;
import com.osmosyscol.datasuite.modelatos.logica.dto.ListaDinamica;


public interface AccionDao {

	public Boolean insertarAccion(Integer id_accion,String nombre,Integer id_instancia);
	
	public Integer obtenerSiguienteIdAccion();
	
	public Accion obtenerAccion(Integer id_accion);
	
	public List<Accion> obtenerAccionesPorInstancia(Integer id_instancia);
	
	public Boolean borrarAccion(Integer id_accion);
	
	public Boolean actualizarNombre(Integer id_accion,String nombre);
	
	public Boolean insertarInstaciaDestino(Integer id_accion,Integer id_instancia_destino);
	
	public Instancia obtenerInstanciaDestino(Integer id_accion,Integer id_instancia_destino);
	
	public List<Integer> obtenerIdInstanciasDestinoPorAccion(Integer id_accion);
	
	public List<Instancia> obtenerInstanciasDestinoPorAccion(Integer id_accion);
	
	public Boolean borrarInstanciaDestino(Integer id_accion,Integer id_instancia_destino);
	
	public List<Instancia> obtenerInstDisponiblesParaAsignar(Integer id_proceso_admin, Integer id_accion);
	
	public List<Accion> obtenerAccionesPorProceso(Integer id_proceso_admin);
	
	public Boolean actualizarOculto(Integer id_accion, String oculto);
	
	public List<Accion> obtenerAccionesConInstanciaDestinoPorInstanciaOrigen(Integer id_instancia_origen);
	
	public List<ListaDinamica> obtenerListasDinamicas();
	
	public Boolean asignarListaDinamica(Integer id_accion, Integer id_lista_dinamica);
	
	public ListaDinamica obtenerAccionAutomatica(Integer id_accion);
	
	public List<CargaAccionAutomatica> obtenerCargasEnAccionesAutomaticas();
	
	public Boolean actualizarMensajeEjecucion (Integer id_accion, String mensaje_ejecucion);
	
	public Boolean actualizarAccion (Accion accion);
}
