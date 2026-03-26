package com.osmosyscol.datasuite.webdata.correval.grupogiro;


public class ComandoBanco {
	
	private Integer orden;
	
	private Integer id_banco;
	
	private Integer id_grupo_giro;
	
	private Boolean agregar;

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public Integer getId_banco() {
		return id_banco;
	}

	public void setId_banco(Integer id_banco) {
		this.id_banco = id_banco;
	}

	public Integer getId_grupo_giro() {
		return id_grupo_giro;
	}

	public void setId_grupo_giro(Integer id_registro) {
		this.id_grupo_giro = id_registro;
	}

	public Boolean isAgregar() {
		return agregar;
	}
	public void setAgregar(Boolean agregar) {
		this.agregar = agregar;
	}
}
