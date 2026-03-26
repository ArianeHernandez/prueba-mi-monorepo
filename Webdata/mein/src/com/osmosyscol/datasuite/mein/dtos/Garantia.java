package com.osmosyscol.datasuite.mein.dtos;

public class Garantia {
	
	private Integer id;
	private Integer idcarga;
	private Integer obligacion_cubierta;
	private String identificacion;
	private Integer valor_del_bien;
	private Integer valor_maximo;
	private Integer bien_necesario;
	
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
	public Integer getObligacion_cubierta() {
		return obligacion_cubierta;
	}
	public void setObligacion_cubierta(Integer obligacion_cubierta) {
		this.obligacion_cubierta = obligacion_cubierta;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public Integer getValor_del_bien() {
		return valor_del_bien;
	}
	public void setValor_del_bien(Integer valor_del_bien) {
		this.valor_del_bien = valor_del_bien;
	}
	public Integer getValor_maximo() {
		return valor_maximo;
	}
	public void setValor_maximo(Integer valor_maximo) {
		this.valor_maximo = valor_maximo;
	}
	public Integer getBien_necesario() {
		return bien_necesario;
	}
	public void setBien_necesario(Integer bien_necesario) {
		this.bien_necesario = bien_necesario;
	}
	
}
