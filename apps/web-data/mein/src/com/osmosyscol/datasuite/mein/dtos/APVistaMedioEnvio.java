package com.osmosyscol.datasuite.mein.dtos;

public class APVistaMedioEnvio {

	private Integer id;
	private Long id_vista;
	private String medio_envio;
	private Long codigo;
	
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
	public String getMedio_envio() {
		return medio_envio;
	}
	public void setMedio_envio(String medio_envio) {
		this.medio_envio = medio_envio;
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
}
