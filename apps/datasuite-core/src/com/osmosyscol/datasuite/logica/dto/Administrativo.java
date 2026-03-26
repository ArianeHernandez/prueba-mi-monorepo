package com.osmosyscol.datasuite.logica.dto;

import java.util.ArrayList;
import java.util.List;

public class Administrativo {

	private Integer id_administrativo;
	private Integer id_persona;
	private String activo;
	private String codigo;
	private Integer id_usuario;

	private String nombre;
	private String apellido;
	private String identificacion;

	List<ValorAtributo> listValores = new ArrayList<>();

	public List<ValorAtributo> getListValores() {
		return listValores;
	}

	public void setListValores(List<ValorAtributo> listValores) {
		this.listValores = listValores;
	}

	public Integer getId_administrativo() {
		return id_administrativo;
	}

	public void setId_administrativo(Integer idAdministrativo) {
		id_administrativo = idAdministrativo;
	}

	public Integer getId_persona() {
		return id_persona;
	}

	public void setId_persona(Integer idPersona) {
		id_persona = idPersona;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre_completo() {
		return nombre + " " + apellido;
	}

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}

}
