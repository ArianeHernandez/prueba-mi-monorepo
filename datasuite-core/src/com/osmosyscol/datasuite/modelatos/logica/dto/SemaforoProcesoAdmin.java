package com.osmosyscol.datasuite.modelatos.logica.dto;

import java.util.List;

import com.osmosyscol.datasuite.logica.dto.Administrativo;

public class SemaforoProcesoAdmin {

	private Integer id_proceso_admin;
	private List<Integer> cargasPorIntervalo;
	private Integer total_intervalos;
	private String nombre_proceso;
	private String nombre_negocio;
	private Administrativo administrativo;
	private Integer total_cargas;

	public Administrativo getAdministrativo() {
		return administrativo;
	}

	public void setAdministrativo(Administrativo administrativo) {
		this.administrativo = administrativo;
	}

	public String getNombre_proceso() {
		return nombre_proceso;
	}

	public void setNombre_proceso(String nombre_proceso) {
		this.nombre_proceso = nombre_proceso;
	}

	public Integer getId_proceso_admin() {
		return id_proceso_admin;
	}

	public void setId_proceso_admin(Integer id_proceso_admin) {
		this.id_proceso_admin = id_proceso_admin;
	}

	public List<Integer> getCargasPorIntervalo() {
		return cargasPorIntervalo;
	}

	public void setCargasPorIntervalo(List<Integer> cargasPorIntervalo) {
		this.cargasPorIntervalo = cargasPorIntervalo;
	}

	public Integer getTotal_intervalos() {
		return total_intervalos;
	}

	public void setTotal_intervalos(Integer total_intervalos) {
		this.total_intervalos = total_intervalos;
	}

	public String getNombre_negocio() {
		return nombre_negocio;
	}

	public void setNombre_negocio(String nombre_negocio) {
		this.nombre_negocio = nombre_negocio;
	}

	public Integer getTotal_cargas() {
		return total_cargas;
	}
	
	public void setTotal_cargas(Integer total_cargas) {
		this.total_cargas = total_cargas;
	}
	
}
