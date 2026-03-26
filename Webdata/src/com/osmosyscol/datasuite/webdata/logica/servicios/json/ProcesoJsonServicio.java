package com.osmosyscol.datasuite.webdata.logica.servicios.json;

import java.util.ArrayList;
import java.util.List;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.logica.dto.Proceso;
import com.osmosyscol.datasuite.modelatos.logica.dto.Formato;
import com.osmosyscol.datasuite.modelatos.logica.servicios.FormatoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.LiberadorServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.PreparadorServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ProcesoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.RevisionServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.RevisorServicio;
import com.osmosyscol.datasuite.webdata.utils.ComparadorFormatos;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

import edu.emory.mathcs.backport.java.util.Collections;

public class ProcesoJsonServicio implements JsonService {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	// -------

	private Integer getIdProceso() {
		try {
			return Integer.parseInt((String) session.getAttribute("var.id_proceso"));
		} catch (Exception e) {
			return null;
		}

	}

	// -------

	public boolean actualizarProceso(String nombre, String estado, Integer id_formato_salida, Integer id_grupoformato, Integer peso) {

		Proceso proceso = new Proceso();
		proceso.setId_proceso(getIdProceso());
		proceso.setNombre(nombre);
		proceso.setEstado(estado);
		proceso.setId_formato_salida(id_formato_salida);
		proceso.setId_grupoformato(id_grupoformato);
		proceso.setPeso(peso);
		

		return ProcesoServicio.getInstance().actualizarProceso(proceso);
	}

	public boolean cambiarEstadoProceso(Integer id_proceso, String estado) {
		return ProcesoServicio.getInstance().cambiarEstadoProceso(id_proceso, estado);
	}

	public int crearProceso(Integer id_usuario, Integer id_negocio, String nombre, Integer id_tipo_proceso) {
		return ProcesoServicio.getInstance().crearProceso(id_usuario, id_negocio, nombre, id_tipo_proceso);
	}

	public boolean actualizarNombreRevision(Integer id_revision, String nombre) {
		return RevisionServicio.getInstance().actualizarNombreRevision(id_revision, nombre);
	}

	public Integer agregarRevision(Integer id_revision_anterior, String nombre) {
		return RevisionServicio.getInstance().agregarRevision(getIdProceso(), id_revision_anterior, nombre);
	}

	public boolean eliminarRevision(Integer id_revision) {
		return RevisionServicio.getInstance().eliminarRevision(id_revision);
	}

	public boolean asociarPreparador(Integer id_preparador) {
		return PreparadorServicio.getInstance().asociarPreparador(getIdProceso(), id_preparador);
	}

	public boolean desasociarPreparador(Integer id_preparador) {
		return PreparadorServicio.getInstance().desasociarPreparador(id_preparador);
	}

	public boolean asociarLiberador(Integer id_liberador) {
		return LiberadorServicio.getInstance().asociarLiberador(getIdProceso(), id_liberador);
	}

	public boolean desasociarLiberador(Integer id_liberador) {
		return LiberadorServicio.getInstance().desasociarLiberador(id_liberador);
	}
	
	public boolean desasociarLiberadorPorProceso(Integer id_liberador,Integer id_proceso) {
		return LiberadorServicio.getInstance().desasociarLiberadorPorProceso(id_liberador,id_proceso);
	}

	public boolean asociarRevisor(Integer id_revision, Integer id_revisor) {
		return RevisorServicio.getInstance().asociarRevisor(id_revision, id_revisor);
	}

	public boolean desasociarRevisor(Integer id_revision, Integer id_revisor) {
		return RevisorServicio.getInstance().desasociarRevisor(id_revision, id_revisor);
	}

	public Boolean agregarFormato(Integer id_formato){
		return ProcesoServicio.getInstance().agregarFormato(id_formato, getIdProceso());
	}

	public Boolean eliminarFormato(Integer id_formato) {
		return ProcesoServicio.getInstance().eliminarFormato(id_formato, getIdProceso());
	}
	
	public List<Object> obtenerFormatosPorGrupoformato(){
		
		Integer id_negocio = (Integer) this.session.getAttribute("id_negocio");
		Integer id_usuario = (Integer) this.session.getAttribute("id_usuario");

		Proceso proceso = ProcesoServicio.getInstance().obtenerProceso(getIdProceso());
		List<Formato> formatosSinFiltrar = null;
		
		Boolean confListNeg = Boolean.parseBoolean(ParametrosInicio.getProperty("DisponibleListadoNegocios"));
		if(!confListNeg){
			formatosSinFiltrar = FormatoServicio.getInstance().obtenerFormatosPorCliente(id_usuario);
		}else{
			formatosSinFiltrar = FormatoServicio.getInstance().obtenerFormatosPorNegocio(id_negocio);
		}
		
		List<Formato> formatosDeEntradaFiltradosPorGrupo = new ArrayList<Formato>();
		List<Formato> formatosDeSalidaFiltradosPorGrupo = new ArrayList<Formato>();
		
		
		if(proceso.getId_grupoformato()!=null && formatosSinFiltrar!=null){
			for (Formato formato : formatosSinFiltrar) {
				if(formato.getId_grupoformato()!=null && formato.getId_grupoformato().equals(proceso.getId_grupoformato())){
					
					if(formato.getTipoformato().equals("E")){
						
						formatosDeEntradaFiltradosPorGrupo.add(formato);
						
					}else if(formato.getTipoformato().equals("S")){
						formatosDeSalidaFiltradosPorGrupo.add(formato);
					}
					
				}
				
				
			}
		}
		
		ArrayList<Object> formatos = new ArrayList<Object>();
		
		Collections.sort(formatosDeEntradaFiltradosPorGrupo, new ComparadorFormatos());
		Collections.sort(formatosDeSalidaFiltradosPorGrupo, new ComparadorFormatos());
		
		formatos.add(formatosDeEntradaFiltradosPorGrupo);
		formatos.add(formatosDeSalidaFiltradosPorGrupo);
		
		return formatos;
		
	}
	
	public Integer obtenerCargasPorProcesoClienteEnTransito(Integer id_proceso){
		
		return ProcesoServicio.getInstance().obtenerCargasPorProcesoClienteEnTransito(id_proceso);
	}
	
	public List<Proceso> listarProcesosAfectadosLiberador(Integer id_liberador, Integer nuevoPesoLiberador, Integer id_tipo_proceso){
		return ProcesoServicio.getInstance().listarProcesosAfectadosLiberador(id_liberador,nuevoPesoLiberador, id_tipo_proceso);
	}
	
	public List<Proceso> listarProcesosAfectadosEliminacionLiberador(Integer id_persona, Integer id_usuario){
		return ProcesoServicio.getInstance().listarProcesosAfectadosEliminacionLiberador(id_persona,id_usuario);
	}
	public Boolean permiteCrearProceso(Integer id_usuario, Integer id_tipo_proceso){
		return ProcesoServicio.getInstance().permiteCrearProceso(id_usuario, id_tipo_proceso);
	}
	
	public Integer diferenciaPesoLiberadoresYProceso(Integer id_proceso){		
		return ProcesoServicio.getInstance().diferenciaPesoLiberadoresYProceso(id_proceso);
	}
}