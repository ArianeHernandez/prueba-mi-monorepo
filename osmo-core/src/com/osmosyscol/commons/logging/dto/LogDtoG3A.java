package com.osmosyscol.commons.logging.dto;

public class LogDtoG3A{

	private RegistroLogG3A registro;

	public void setRegistro(RegistroLogG3A registro) {
		this.registro = registro;
	}

	public RegistroLogG3A getRegistro() {
		return registro;
	}

	public String toString() {
		return registro != null ? registro.toString() : "null";
	}



}
