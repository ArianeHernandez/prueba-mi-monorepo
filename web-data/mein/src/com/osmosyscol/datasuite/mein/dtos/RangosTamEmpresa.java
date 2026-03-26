package com.osmosyscol.datasuite.mein.dtos;

import java.math.BigDecimal;

public class RangosTamEmpresa {
	private Integer macrosector;
	private Integer tam;
	private BigDecimal valor_desde;
	private BigDecimal valor_hasta;
	
	public Integer getMacrosector() {
		return macrosector;
	}
	public void setMacrosector(Integer macrosector) {
		this.macrosector = macrosector;
	}
	public Integer getTam() {
		return tam;
	}
	public void setTam(Integer tam) {
		this.tam = tam;
	}
	public BigDecimal getValor_desde() {
		return valor_desde;
	}
	public void setValor_desde(BigDecimal valor_desde) {
		this.valor_desde = valor_desde;
	}
	public BigDecimal getValor_hasta() {
		return valor_hasta;
	}
	public void setValor_hasta(BigDecimal valor_hasta) {
		this.valor_hasta = valor_hasta;
	}
}
