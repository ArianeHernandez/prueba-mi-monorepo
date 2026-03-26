package com.osmosyscol.datasuite.mein.dtos;

import java.math.BigDecimal;

public class ParametroTamEmpresa {
	private String nombre_parametro;
	private BigDecimal valor_parametro;
	
	public String getNombre_parametro() {
		return nombre_parametro;
	}
	public void setNombre_parametro(String nombre_parametro) {
		this.nombre_parametro = nombre_parametro;
	}
	public BigDecimal getValor_parametro() {
		return valor_parametro;
	}
	public void setValor_parametro(BigDecimal valor_parametro) {
		this.valor_parametro = valor_parametro;
	}
}
