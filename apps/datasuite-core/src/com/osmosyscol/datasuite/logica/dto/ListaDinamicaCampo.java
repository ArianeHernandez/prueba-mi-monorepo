package com.osmosyscol.datasuite.logica.dto;

import com.osmosyscol.datasuite.logica.constantes.Constantes;


/***
 * 
 * Relacion un campo de un formato con una lista  de valores dinámica,
 * el liberador puede modificar el valor del campo al seleccionar un valor
 * de la lista de valores dinámica
 * 
 * @author oaortizs
 */
public class ListaDinamicaCampo {

	private Integer id_formato;
	
	private Integer id_campo;
	
	private Integer id_lista_dinamica;
	
	private String nombre_campo;
	
	private String nombre_lista_dinamica;
	
	private Integer id_campo_padre;
	
	private String obligatorio = Constantes.NO;

	public Integer getId_formato() {
		return id_formato;
	}

	public void setId_formato(Integer id_formato) {
		this.id_formato = id_formato;
	}

	public Integer getId_campo() {
		return id_campo;
	}

	public void setId_campo(Integer id_campo) {
		this.id_campo = id_campo;
	}

	public Integer getId_lista_dinamica() {
		return id_lista_dinamica;
	}

	public void setId_lista_dinamica(Integer id_lista_dinamica) {
		this.id_lista_dinamica = id_lista_dinamica;
	}

	public String getNombre_campo() {
		return nombre_campo;
	}

	public void setNombre_campo(String nombre_campo) {
		this.nombre_campo = nombre_campo;
	}

	public String getNombre_lista_dinamica() {
		return nombre_lista_dinamica;
	}

	public void setNombre_lista_dinamica(String nombre_lista_dinamica) {
		this.nombre_lista_dinamica = nombre_lista_dinamica;
	}

	public Integer getId_campo_padre() {
		return id_campo_padre;
	}

	public void setId_campo_padre(Integer id_campo_padre) {
		this.id_campo_padre = id_campo_padre;
	}

	public String getObligatorio() {
		return obligatorio;
	}

	public void setObligatorio(String obligatorio) {
		this.obligatorio = obligatorio;
	}

}
