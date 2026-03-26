package com.osmosyscol.datasuite.mein.dtos;

import java.math.BigDecimal;

public class Pasivo {
	
	private Integer id;
	private Integer idcarga;
	private Integer tipo_pasivo;
	private BigDecimal valor;
	private PersonaMein	persona;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdcarga() {
		return idcarga;
	}
	public void setIdcarga(Integer idcarga) {
		this.idcarga = idcarga;
	}
	public Integer getTipo_pasivo() {
		return tipo_pasivo;
	}
	public void setTipo_pasivo(Integer tipo_pasivo) {
		this.tipo_pasivo = tipo_pasivo;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public PersonaMein getPersona() {
		return persona;
	}
	public void setPersona(PersonaMein persona) {
		this.persona = persona;
	}
	
}
