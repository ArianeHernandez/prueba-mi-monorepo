package com.osmosyscol.datasuite.reportedim.dto;

import java.util.ArrayList;
import java.util.List;

import com.osmosyscol.datasuite.config.Constantes;

/**
 * Objeto que representa el XML del servicio que se encuetra en la carpeta
 * servicios/
 * 
 * @author jmgoyesc
 * 
 */
public class ServicioReporteDim {

	private String id;
	private String consulta;
	private String nombre_conexion;
	private String nombre;

	private Integer paginacion = Constantes.PAGINACIONLISTADO;

	private List<ParametroReporte> parametros = new ArrayList<ParametroReporte>();
	private List<Resultado> resultados = new ArrayList<Resultado>();

	private AccionFila accionfila;

	private List<Navegacion> navegacion = new ArrayList<Navegacion>();

	// ----------------------

	public ServicioReporteDim() {

	}

	public ServicioReporteDim(ServicioReporteDim srd) {
		this.id = (srd.getId() == null) ? null : new String(srd.getId());
		this.consulta = (srd.getConsulta() == null) ? null : new String(srd.getConsulta());
		this.nombre_conexion = (srd.getNombre_conexion() == null) ? null : new String(srd.getNombre_conexion());
		this.nombre = (srd.getNombre() == null) ? null : new String(srd.getNombre());
		this.paginacion = (srd.getPaginacion() == null) ? Constantes.PAGINACIONLISTADO : new Integer(srd.getPaginacion());

		// agregar parametros reporte
		this.parametros = new ArrayList<ParametroReporte>();

		if (srd.getParametros() != null) {
			for (ParametroReporte pr : srd.getParametros()) {
				this.parametros.add(new ParametroReporte(pr));
			}
		}

		// agregar resultados reporte
		this.resultados = new ArrayList<Resultado>();
		if (srd.getResultados() != null) {
			for (Resultado r : srd.getResultados()) {
				this.resultados.add(new Resultado(r));
			}
		}

		// accion fila
		this.accionfila = (srd.getAccionfila() == null) ? null : new AccionFila(srd.getAccionfila());

		// agregar lista navegacion reporte
		this.navegacion = new ArrayList<Navegacion>();
		if (srd.getNavegacion() != null) {
			for (Navegacion n : srd.getNavegacion()) {
				this.navegacion.add(new Navegacion(n));
			}
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getPaginacion() {
		return paginacion;
	}

	public void setPaginacion(Integer paginacion) {
		this.paginacion = paginacion;
	}

	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

	public String getNombre_conexion() {
		return nombre_conexion;
	}

	public void setNombre_conexion(String nombre_conexion) {
		this.nombre_conexion = nombre_conexion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<ParametroReporte> getParametros() {
		return parametros;
	}

	public void setParametros(List<ParametroReporte> parametros) {
		this.parametros = parametros;
	}

	public List<Resultado> getResultados() {
		return resultados;
	}

	public void setResultados(List<Resultado> resultados) {
		this.resultados = resultados;
	}

	public AccionFila getAccionfila() {
		return accionfila;
	}

	public void setAccionfila(AccionFila accionfila) {
		this.accionfila = accionfila;
	}

	public List<Navegacion> getNavegacion() {
		return navegacion;
	}

	public void setNavegacion(List<Navegacion> navegacion) {
		this.navegacion = navegacion;
	}

	@Override
	public String toString() {
		return "ServicioReporteDim [id=" + id + ", consulta=" + consulta + ", nombre_conexion=" + nombre_conexion + ", nombre=" + nombre + ", paginacion=" + paginacion
				+ ", parametros=" + parametros + ", resultados=" + resultados + ", accionfila=" + accionfila + ", navegacion=" + navegacion + "]";
	}

}
