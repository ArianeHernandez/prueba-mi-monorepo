package com.osmosyscol.datasuite.logica.dto;

import java.util.ArrayList;
import java.util.List;

public class RespValidacionPolitica {
	
	private List<String> mensajes_error;
	private Boolean valido;
	
	public RespValidacionPolitica() {
		super();
	}
	
	public RespValidacionPolitica(Boolean valido) {
		super();
		this.mensajes_error = new ArrayList<String>();
		this.valido = valido;
	}
	
	public List<String> getMensajes_error() {
		return mensajes_error;
	}
	public void setMensajes_error(List<String> mensajes_error) {
		this.mensajes_error = mensajes_error;
	}
	public Boolean getValido() {
		return valido;
	}
	public void setValido(Boolean valido) {
		this.valido = valido;
	}
	
}
