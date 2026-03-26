package com.osmosyscol.datasuite.webdata.logica.dto;

import java.math.BigDecimal;

import com.osmosyscol.datasuite.modelatos.logica.dto.Campo;
import com.osmosyscol.datasuite.modelatos.logica.dto.Operacion;

public class TablaOperacion {
	
	private Campo campo;
	private Operacion operacion;
	private BigDecimal resultado;
	private String titulo;
	
	//---------------------
	
	public Campo getCampo() {
		return campo;
	}
	public void setCampo(Campo campo) {
		this.campo = campo;
	}
	public BigDecimal getResultado() {
		return resultado;
	}
	public void setResultado(BigDecimal resultado) {
		this.resultado = resultado;
	}
	public Operacion getOperacion() {
		return operacion;
	}
	public void setOperacion(Operacion operacion) {
		this.operacion = operacion;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
}
