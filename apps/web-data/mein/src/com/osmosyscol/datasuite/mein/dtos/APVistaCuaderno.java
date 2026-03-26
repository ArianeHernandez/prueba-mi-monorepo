package com.osmosyscol.datasuite.mein.dtos;

public class APVistaCuaderno {

	private Integer id;
	private Long id_vista;
	private String codigo;
	private String cuadernoTipo;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getId_vista() {
		return id_vista;
	}
	public void setId_vista(Long id_vista) {
		this.id_vista = id_vista;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getCuadernoTipo() {
		return cuadernoTipo;
	}
	public void setCuadernoTipo(String cuadernoTipo) {
		this.cuadernoTipo = cuadernoTipo;
	}
	
}
