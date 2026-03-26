package com.osmosyscol.datasuite.logica.dto;

public class RestriccionAdministrativo {

	private Integer id_restriccion_administrativo;
	private Integer id_rol;
	private Integer id_administrativo;
	private Integer id_usuario;
	private Integer id_negocio;

	//------------------
	private String nombres_usuario = "";
	
	public Integer getId_restriccion_administrativo() {
		return id_restriccion_administrativo;
	}

	public void setId_restriccion_administrativo(
			Integer idRestriccionAdministrativo) {
		id_restriccion_administrativo = idRestriccionAdministrativo;
	}

	public Integer getId_rol() {
		return id_rol;
	}

	public void setId_rol(Integer idRol) {
		id_rol = idRol;
	}

	public Integer getId_administrativo() {
		return id_administrativo;
	}

	public void setId_administrativo(Integer idAdministrativo) {
		id_administrativo = idAdministrativo;
	}

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer idUsuario) {
		id_usuario = idUsuario;
	}

	public Integer getId_negocio() {
		return id_negocio;
	}

	public void setId_negocio(Integer idNegocio) {
		id_negocio = idNegocio;
	}

	public String getNombres_usuario() {
		return nombres_usuario;
	}

	public void setNombres_usuario(String nombres_usuario) {
		this.nombres_usuario = nombres_usuario;
	}
	
}
