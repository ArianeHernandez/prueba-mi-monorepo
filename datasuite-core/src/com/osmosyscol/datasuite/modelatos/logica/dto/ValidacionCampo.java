package com.osmosyscol.datasuite.modelatos.logica.dto;



public class ValidacionCampo {
	
	private Integer id_validacion_estructura;     
	private Integer id_campo;     
	private String primer_valor;     
	private String segundo_valor;
	private String seleccionado="N";
	
	
	public String getSeleccionado() {
		return seleccionado;
	}
	
	public void setSeleccionado(String selecccionado) {
		this.seleccionado = selecccionado;
	}
	
	
	public Integer getId_validacion_estructura() {
		return id_validacion_estructura;
	}
	public void setId_validacion_estructura(Integer id_validacion_estructura) {
		this.id_validacion_estructura = id_validacion_estructura;
	}
	public Integer getId_campo() {
		return id_campo;
	}
	public void setId_campo(Integer id_campo) {
		this.id_campo = id_campo;
	}
	public String getPrimer_valor() {
		return primer_valor;
	}
	public void setPrimer_valor(String primer_valor) {
		this.primer_valor = primer_valor;
	}
	public String getSegundo_valor() {
		return segundo_valor;
	}
	public void setSegundo_valor(String segundo_valor) {
		this.segundo_valor = segundo_valor;
	}  
	
	
	
}
