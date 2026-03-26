package com.osmosyscol.datasuite.mein.domain;

public class CerlResponse {

	public static final CerlResponse positivo = new CerlResponse(true);
	public static final CerlResponse negativo = new CerlResponse(false);

	private boolean respuesta;
	private Sociedad sociedad;

	public CerlResponse(boolean respuesta) {
		this.respuesta = respuesta;
	}

	public boolean isRespuesta() {
		return respuesta;
	}

	public void setRespuesta(boolean respuesta) {
		this.respuesta = respuesta;
	}

	public Sociedad getSociedad() {
		return sociedad;
	}

	public void setSociedad(Sociedad sociedad) {
		this.sociedad = sociedad;
	}
 
}
