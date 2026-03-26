package com.osmosyscol.datasuite.pagos.acciones.validacionsarlaft;


public class Beneficiario {
	
	private Integer id_beneficiario;
	private String tipo_documento;
	private String numero_documento;
	private String primer_nombre;
	private String segundo_nombre;
	private String primer_apellido;
	private String segundo_apellido;
	
	public String getTipo_documento() {
		return tipo_documento;
	}
	public void setTipo_documento(String tipo_documento) {
		this.tipo_documento = tipo_documento;
	}
	public String getNumero_documento() {
		return numero_documento;
	}
	public void setNumero_documento(String numero_documento) {
		this.numero_documento = numero_documento;
	}
	public String getPrimer_nombre() {
		return primer_nombre;
	}
	public void setPrimer_nombre(String primer_nombre) {
		this.primer_nombre = primer_nombre;
	}
	public String getSegundo_nombre() {
		return segundo_nombre;
	}
	public void setSegundo_nombre(String segundo_nombre) {
		this.segundo_nombre = segundo_nombre;
	}
	public String getPrimer_apellido() {
		return primer_apellido;
	}
	public void setPrimer_apellido(String primer_apellido) {
		this.primer_apellido = primer_apellido;
	}
	public String getSegundo_apellido() {
		return segundo_apellido;
	}
	public void setSegundo_apellido(String segundo_apellido) {
		this.segundo_apellido = segundo_apellido;
	}
	public Integer getId_beneficiario() {
		return id_beneficiario;
	}
	public void setId_beneficiario(Integer id_beneficiario) {
		this.id_beneficiario = id_beneficiario;
	}
	
}
