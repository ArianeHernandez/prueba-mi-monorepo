package com.osmosyscol.datasuite.webdata.logica.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Nota {

	private Integer id_nota;
	private String nota;
	private Date fecha;
	private Integer id_persona;
	private Integer id_carga;

	private String nombre;
	private String apellido;

	private String estado;
	private Integer id_revision;
	
	private String fechaString;
	
	

	public String getFechaString() {
		return fechaString;
	}

	public void setFechaString(String fechaString) {
		this.fechaString = fechaString;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getId_revision() {
		return id_revision;
	}

	public void setId_revision(Integer idRevision) {
		id_revision = idRevision;
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

	public Integer getId_nota() {
		return id_nota;
	}

	public void setId_nota(Integer idNota) {
		id_nota = idNota;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {

		this.fecha = fecha;
		
		SimpleDateFormat formateador = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
		this.fechaString = formateador.format(fecha);
	}

	public Integer getId_persona() {
		return id_persona;
	}

	public void setId_persona(Integer idPersona) {
		id_persona = idPersona;
	}

	public Integer getId_carga() {
		return id_carga;
	}

	public void setId_carga(Integer idCarga) {
		id_carga = idCarga;
	}

}
