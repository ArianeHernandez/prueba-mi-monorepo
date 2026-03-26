package com.osmosyscol.datasuite.webdata.logica.dto;

public class VariableLiberacion {
	String nombreVariableLiberacion;
	String valor;
	Integer id_carga;
	Integer id_campo;
	
	public Integer getId_carga() {
		return id_carga;
	}
	
	public String getNombreVariableLiberacion() {
		return nombreVariableLiberacion;
	}
	
	public String getValor() {
		return valor;
	}
	
	public void setId_carga(Integer id_carga) {
		this.id_carga = id_carga;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public void setNombreVariableLiberacion(String nombreVariableLiberacion) {
		this.nombreVariableLiberacion = nombreVariableLiberacion;
	}
	
	public Integer getId_campo() {
		return id_campo;
	}
	
	public void setId_campo(Integer id_campo) {
		this.id_campo = id_campo;
	}

}
