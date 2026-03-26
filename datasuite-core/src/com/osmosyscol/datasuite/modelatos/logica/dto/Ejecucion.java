package com.osmosyscol.datasuite.modelatos.logica.dto;

import java.util.Date;

public class Ejecucion {

	private Integer id_ejecucion;
	private Integer id_carga;
	private Integer id_destino;
	private String estado;
	private Date fecha_inicio;
	private Date fecha_final;
	private String login;
	//--------------------------------------
	public Integer getId_ejecucion() {
		return id_ejecucion;
	}
	public void setId_ejecucion(Integer id_ejecucion) {
		this.id_ejecucion = id_ejecucion;
	}
	public Integer getId_carga() {
		return id_carga;
	}
	public void setId_carga(Integer id_carga) {
		this.id_carga = id_carga;
	}
	public Integer getId_destino() {
		return id_destino;
	}
	public void setId_destino(Integer id_destino) {
		this.id_destino = id_destino;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getFecha_inicio() {
		return fecha_inicio;
	}
	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}
	public Date getFecha_final() {
		return fecha_final;
	}
	public void setFecha_final(Date fecha_final) {
		this.fecha_final = fecha_final;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String usuario) {
		this.login = usuario;
	}
	
}
