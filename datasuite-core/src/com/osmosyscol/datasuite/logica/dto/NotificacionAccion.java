package com.osmosyscol.datasuite.logica.dto;

public class NotificacionAccion {

	private Integer id_notificacion_accion;
	private Integer id_accion;
	private String tipo;
	private String correo;
	private Integer id_administrativo;
	private String mensaje;

	public Integer getId_notificacion_accion() {
		return id_notificacion_accion;
	}

	public void setId_notificacion_accion(Integer idNotificacionAccion) {
		id_notificacion_accion = idNotificacionAccion;
	}

	public Integer getId_accion() {
		return id_accion;
	}

	public void setId_accion(Integer idAccion) {
		id_accion = idAccion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Integer getId_administrativo() {
		return id_administrativo;
	}

	public void setId_administrativo(Integer idAdministrativo) {
		id_administrativo = idAdministrativo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
