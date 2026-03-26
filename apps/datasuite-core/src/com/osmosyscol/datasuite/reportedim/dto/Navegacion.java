package com.osmosyscol.datasuite.reportedim.dto;

import java.util.ArrayList;
import java.util.List;

public class Navegacion {

	private String nombre;

	private String destino;

	private List<ParametroAccion> parametros = new ArrayList<ParametroAccion>();

	private Integer id_navegacion;

	private String id_reporte;

	public Navegacion() {

	}

	public Navegacion(String nombre, String destino, String reporte_id) {
		this.nombre = nombre;
		this.destino = destino;
		id_reporte = reporte_id;
	}

	public Navegacion(Navegacion n) {
		this.nombre = (n.getNombre() == null) ? null : new String(n.getNombre());
		this.destino = (n.getDestino() == null) ? null : new String(n.getDestino());

		this.parametros = new ArrayList<ParametroAccion>();
		for (ParametroAccion pa : n.getParametros()) {
			this.parametros.add(new ParametroAccion(pa));
		}

		this.id_navegacion = (n.getId_navegacion() == null) ? null : new Integer(n.getId_navegacion());
		id_reporte = (n.getId_reporte() == null) ? null : new String(n.getId_reporte());
	}

	public String getId_reporte() {
		return id_reporte;
	}

	public void setId_reporte(String id_reporte) {
		this.id_reporte = id_reporte;
	}

	public Integer getId_navegacion() {
		return id_navegacion;
	}

	public void setId_navegacion(Integer id_navegacion) {
		this.id_navegacion = id_navegacion;
	}

	public List<ParametroAccion> getParametros() {
		return parametros;
	}

	public void setParametros(List<ParametroAccion> parametros) {
		this.parametros = parametros;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	@Override
	public String toString() {
		return "Navegacion [nombre=" + nombre + ", destino=" + destino + ", parametros=" + parametros + ", id_navegacion=" + id_navegacion + ", id_reporte=" + id_reporte + "]";
	}

}
