package com.osmosyscol.datasuite.mein.dtos;

public class APVistaDependencias {
	private Integer id;
	private Integer id_vista;
	private String nombreDependencia;
	private Long codigo;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId_vista() {
		return id_vista;
	}
	public void setId_vista(Integer id_vista) {
		this.id_vista = id_vista;
	}
	public String getNombreDependencia() {
		return nombreDependencia;
	}
	public void setNombreDependencia(String nombreDependencia) {
		this.nombreDependencia = nombreDependencia;
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
}
