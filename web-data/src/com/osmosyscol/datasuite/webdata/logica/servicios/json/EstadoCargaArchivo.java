package com.osmosyscol.datasuite.webdata.logica.servicios.json;

import java.util.HashMap;
import java.util.Map;

public class EstadoCargaArchivo {

	// Pasos generales
	private Integer totalPasos = 0;
	private Integer paso = 0;

	// Por registro (Proceso Especifico)
	private Integer totalRegistro = 0;
	private Integer pasoRegistro = 0;

	private String estado;

	// Mapa Estados
	private Map<Integer, String> mapMensajePasoActual = new HashMap<Integer, String>();

	private Map<Integer, Integer> mapErroresXPaso = new HashMap<Integer, Integer>();
	//1: correcto
	//2: error
	//3: advertencia

	public Map<Integer, String> getMapMensajePasoActual() {
		return mapMensajePasoActual;
	}

	public void setMapMensajePasoActual(Integer paso, String mensaje) {
		mapMensajePasoActual.put(paso, mensaje);
	}

	public Map<Integer, Integer> getMapErroresXPaso() {
		return mapErroresXPaso;
	}

	public void setMapErroresXPaso(Integer paso, Integer codError) {
		mapErroresXPaso.put(paso, codError);
	}

	//obtener pasos Generales 
	public Integer getTotalPasos() {
		return totalPasos;
	}
	//Asignar Pasos Generales
	public void setTotalPasos(Integer totalPasos) {
		this.totalPasos = totalPasos;
	}
	// Obtener paso actual del proceso en General
	public Integer getPaso() {
		return paso;
	}
	// Asignar paso actual del proceso en General
	public void setPaso(Integer paso) {
		this.paso = paso;
	}
	//Obtener Total del registro
	public Integer getTotalRegistro() {
		return totalRegistro;
	}
	// Asignar Total del registro
	public void setTotalRegistro(Integer totalRegistros) {
		this.totalRegistro = totalRegistros;
	}
	// Obtener Paso del registro
	public Integer getPasoRegistro() {
		return pasoRegistro;
	}
	// asignar Pasos de Un registro
	public void setPasoRegistro(Integer pasoRegistros) {
		this.pasoRegistro = pasoRegistros;
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "EstadoCargaArchivo [totalPasos=" + totalPasos + ", paso=" + paso + ", totalRegistros=" + totalRegistro + ", pasoRegistro=" + pasoRegistro + ", estado=" + estado
				+ "]";
	}

}
