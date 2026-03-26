package com.osmosyscol.commons.utils;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 3517661635331120234L;

	public ServiceException() {
		super("Error ejecutando la operación");
	}

	public ServiceException(String mensaje) {
		super(mensaje);
	}

}
