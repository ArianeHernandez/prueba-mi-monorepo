package com.osmosyscol.datasuite.bpm.dto;

public class ApVistaIdentificacionTiposDto {
	private Integer id;
	private String identificacion_tipo;
	private String codigo_alfanumerico;
	
	
	public String getIdentificacion_tipo() {
		return identificacion_tipo;
	}
	public void setIdentificacion_tipo(String identificacion_tipo) {
		this.identificacion_tipo = identificacion_tipo;
	}
	public String getCodigo_alfanumerico() {
		return codigo_alfanumerico;
	}
	public void setCodigo_alfanumerico(String codigo_alfanumerico) {
		this.codigo_alfanumerico = codigo_alfanumerico;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
