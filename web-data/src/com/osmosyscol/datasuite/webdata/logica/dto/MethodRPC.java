package com.osmosyscol.datasuite.webdata.logica.dto;

public class MethodRPC {

	private Object parameters;
	private String method;

	public MethodRPC() {
	}

	public Object getId() {
		return parameters;
	}

	public void setId(Object parameters) {
		this.parameters = parameters;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

}
