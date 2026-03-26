package com.osmosyscol.datasuite.logica.dto;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.osmosyscol.datasuite.logica.constantes.Constantes;

public class Credencial {

	private String login;
	private Integer id_persona;
	private Integer id_usuario;
	private Date fecha_ingreso;
	private String ip_ingreso;
	private String estado;
	private String acepta_terminos;
	private String cambiar_clave;

	/***
	 * Aplica para login por cedula
	 * 
	 * @return
	 */
	public String getLogin() {
		if (login != null && login.contains(Constantes.SEPARADOR_LOGIN)) {
			return StringUtils.substringBefore(login, Constantes.SEPARADOR_LOGIN);
		}
		return login;
	}

	public String getLoginUsuario() {
		return login;
	}

	public void setLogin(String login) {

		if (login != null) {
			login = login.toLowerCase();
		}

		this.login = login;
	}

	public Integer getId_persona() {
		return id_persona;
	}

	public void setId_persona(Integer id_persona) {
		this.id_persona = id_persona;
	}

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}

	public Date getFecha_ingreso() {
		return fecha_ingreso;
	}

	public void setFecha_ingreso(Date fechaIngreso) {
		fecha_ingreso = fechaIngreso;
	}

	public String getIp_ingreso() {
		return ip_ingreso;
	}

	public void setIp_ingreso(String ipIngreso) {
		ip_ingreso = ipIngreso;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setAcepta_terminos(String acepta_terminos) {
		this.acepta_terminos = acepta_terminos;
	}

	public String getAcepta_terminos() {
		return acepta_terminos;
	}

	public void setCambiar_clave(String cambiar_clave) {
		this.cambiar_clave = cambiar_clave;
	}

	public String getCambiar_clave() {
		return cambiar_clave;
	}

	@Override
	public String toString() {
		return "Credencial [login=" + login + ", id_persona=" + id_persona
				+ ", id_usuario=" + id_usuario + ", fecha_ingreso="
				+ fecha_ingreso + ", ip_ingreso=" + ip_ingreso + ", estado="
				+ estado + ", acepta_terminos=" + acepta_terminos
				+ ", cambiar_clave=" + cambiar_clave + "]";
	}
	
}
