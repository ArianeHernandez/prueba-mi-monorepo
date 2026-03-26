package com.osmosyscol.datasuite.pagos.rest;

public class Consecutivo {

	private String consecutivo_debito;

	public Consecutivo(String consecutivo_debito) {
		this.setConsecutivo_debito(consecutivo_debito);
	}

	public String getConsecutivo_debito() {
		return consecutivo_debito;
	}

	public void setConsecutivo_debito(String consecutivo_debito) {
		this.consecutivo_debito = consecutivo_debito;
	}
	
}
