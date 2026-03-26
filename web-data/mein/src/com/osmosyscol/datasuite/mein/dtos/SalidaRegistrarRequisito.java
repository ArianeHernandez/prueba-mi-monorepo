package com.osmosyscol.datasuite.mein.dtos;

public class SalidaRegistrarRequisito {
	
	private String response;
	private PasanteError error;
	
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public PasanteError getError() {
		return error;
	}
	public void setError(PasanteError error) {
		this.error = error;
	}
	
	

}
