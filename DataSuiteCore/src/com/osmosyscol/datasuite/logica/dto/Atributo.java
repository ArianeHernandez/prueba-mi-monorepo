package com.osmosyscol.datasuite.logica.dto;

import java.util.ArrayList;
import java.util.List;

public class Atributo {

	private Integer id_atributo;
	private String nombre;
	private Integer id_usuario;
	List<Administrativo> listAdministrativos = new ArrayList<>();

	public List<Administrativo> getListAdministrativos() {
		return listAdministrativos;
	}

	public void setListAdministrativos(List<Administrativo> listAdministrativos) {
		this.listAdministrativos = listAdministrativos;
	}

	public Integer getId_atributo() {
		return id_atributo;
	}

	public void setId_atributo(Integer id_atributo) {
		this.id_atributo = id_atributo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}

}
