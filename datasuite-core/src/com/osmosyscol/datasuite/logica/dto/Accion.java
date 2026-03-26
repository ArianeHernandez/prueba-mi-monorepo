package com.osmosyscol.datasuite.logica.dto;

import java.util.List;

public class Accion {

	private Integer id_accion;
	private String nombre;
	private Integer id_instancia;
	private String oculto;
	private Integer lista_dinamica;
	private String mensaje_ejecucion;
	private List<Integer> instancias_destino;
	
	public Integer getId_accion() {
		return id_accion;
	}

	public void setId_accion(Integer idAccion) {
		id_accion = idAccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getId_instancia() {
		return id_instancia;
	}

	public void setId_instancia(Integer idInstancia) {
		id_instancia = idInstancia;
	}

	public String getOculto() {
		return oculto;
	}

	public void setOculto(String oculto) {
		this.oculto = oculto;
	}

	public Integer getLista_dinamica() {
		return lista_dinamica;
	}

	public void setLista_dinamica(Integer lista_dinamica) {
		this.lista_dinamica = lista_dinamica;
	}

	public String getMensaje_ejecucion() {
		return mensaje_ejecucion;
	}

	public void setMensaje_ejecucion(String mensaje_ejecucion) {
		this.mensaje_ejecucion = mensaje_ejecucion;
	}

	public List<Integer> getInstancias_destino() {
		return instancias_destino;
	}

	public void setInstancias_destino(List<Integer> instancias_destino) {
		this.instancias_destino = instancias_destino;
	}

}
