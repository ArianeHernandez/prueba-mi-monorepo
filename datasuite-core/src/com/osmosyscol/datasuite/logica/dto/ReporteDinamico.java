package com.osmosyscol.datasuite.logica.dto;

import java.util.List;
import java.util.Map;

import com.osmosyscol.datasuite.reportedim.dto.AccionFila;
import com.osmosyscol.datasuite.reportedim.dto.Navegacion;
import com.osmosyscol.datasuite.reportedim.dto.ParametroReporte;
import com.osmosyscol.datasuite.reportedim.dto.Resultado;

public class ReporteDinamico {

	private String titulo;

	private String nombre;

	private String idReporte;

	private List<Resultado> resultados;

	private Map<String, Object> valoresParametros;

	private List<ParametroReporte> parametros;

	private AccionFila accionfila;

	private List<Navegacion> navegacion;
	
	private Integer intervalo_actualizacion = 5000;
	
	public List<ParametroReporte> getParametros() {
		return parametros;
	}

	public void setParametros(List<ParametroReporte> filtros) {
		this.parametros = filtros;
	}

	public Map<String, Object> getValoresParametros() {
		return valoresParametros;
	}

	public void setValoresParametros(Map<String, Object> parametros) {
		this.valoresParametros = parametros;
	}

	public List<Resultado> getResultados() {
		return resultados;
	}

	public void setResultados(List<Resultado> resultados) {
		this.resultados = resultados;
	}

	public String getIdReporte() {
		return idReporte;
	}

	public void setIdReporte(String idReporte) {
		this.idReporte = idReporte;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String nombre) {
		this.titulo = nombre;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getIntervalo_actualizacion() {
		return intervalo_actualizacion;
	}

	public void setIntervalo_actualizacion(Integer intervaloActualizacion) {
		intervalo_actualizacion = intervaloActualizacion;
	}
	
	
}
