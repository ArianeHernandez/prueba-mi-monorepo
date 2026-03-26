package com.osmosyscol.datasuite.mein.dtos;

import java.math.BigDecimal;

public class ParametroCatEmpresa {
	private String grupo;
	private BigDecimal valor_hasta;
	private BigDecimal valor_desde;
	private Integer tipoSolicitud;
	
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public BigDecimal getValor_hasta() {
		return valor_hasta;
	}
	public void setValor_hasta(BigDecimal valor_hasta) {
		this.valor_hasta = valor_hasta;
	}
	public BigDecimal getValor_desde() {
		return valor_desde;
	}
	public void setValor_desde(BigDecimal valor_desde) {
		this.valor_desde = valor_desde;
	}
	public Integer getTipoSolicitud() {
		return tipoSolicitud;
	}
	public void setTipoSolicitud(Integer tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}
}
