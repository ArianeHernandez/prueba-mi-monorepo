package com.osmosyscol.datasuite.reportedim.dto;

import com.osmosyscol.datasuite.logica.constantes.Constantes;

public class Resultado {

	private String titulo;
	private String nombre;
	private String tipo;
	private Integer orden;
	private String encriptado; // {S,N}
	private String oculto = Constantes.NO; // {S,N}
	private Integer id_resultado;
	private String id_reporte;
	
	public Resultado(){
		
	}
	
	public Resultado(Resultado r){
		this.titulo = (r.getTitulo()==null)?null:new String(r.getTitulo());
		this.nombre = (r.getNombre()==null)?null:new String(r.getNombre());
		this.tipo = (r.getTipo()==null)?null:new String(r.getTipo());
		this.orden = (r.getOrden()==null)?null:new Integer(r.getOrden());
		this.encriptado = (r.getEncriptado()==null)?null:new String(r.getEncriptado());
		this.oculto = (r.getOculto()==null)?null:new String(r.getOculto());
		this.id_resultado = (r.getId_resultado()==null)?null:new Integer(r.getId_resultado());
		this.id_reporte = (r.getId_reporte()==null)?null:new String(r.getId_reporte());
	}

	public String getId_reporte() {
		return id_reporte;
	}

	public void setId_reporte(String id_reporte) {
		this.id_reporte = id_reporte;
	}

	public Integer getId_resultado() {
		return id_resultado;
	}

	public void setId_resultado(Integer id_resultado) {
		this.id_resultado = id_resultado;
	}

	public String getTitulo() {

		if (titulo == null) {
			return nombre;
		}

		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public String getEncriptado() {
		return encriptado;
	}

	public void setEncriptado(String encriptado) {
		if(encriptado.matches("s|S|n|N"))
			this.encriptado = encriptado;
	}

	public String getOculto() {
		return oculto;
	}

	public void setOculto(String oculto) {
		if(oculto.matches("s|S|n|N"))
			this.oculto = oculto;
	}

	@Override
	public String toString() {
		return "Resultado [titulo=" + titulo + ", nombre=" + nombre + ", tipo="
				+ tipo + ", orden=" + orden + ", encriptado=" + encriptado
				+ ", oculto=" + oculto + ", id_resultado=" + id_resultado
				+ ", id_reporte=" + id_reporte + "]";
	}

	
	
}
