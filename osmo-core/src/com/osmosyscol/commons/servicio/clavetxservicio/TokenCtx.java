package com.osmosyscol.commons.servicio.clavetxservicio;

import java.io.Serializable;

import com.osmosyscol.g3a.cliente.entidades.Operacion;
import com.osmosyscol.g3a.cliente.entidades.SessionTicket;

public class TokenCtx implements Serializable{
	private static final long serialVersionUID = 4599614809461728619L;
	
	private Integer servicio;
	private Operacion operacion;
	private SessionTicket ticket;

	public SessionTicket getTicket() {
		return ticket;
	}

	public void setTicket(SessionTicket ticket) {
		this.ticket = ticket;
	}

	public Operacion getOperacion() {
		return operacion;
	}

	public void setOperacion(Operacion operacion) {
		this.operacion = operacion;
	}

	public Integer getServicio() {
		return servicio;
	}

	public void setServicio(Integer servicio) {
		this.servicio = servicio;
	}

}
