package com.osmosyscol.datasuite.modelatos.logica.dto;

import java.util.Date;

public class Log {

	private Integer id_carga;
	private Integer id_ejecucion;
	private Integer cod_error;
	private String descripcion;
	private Date fecha;
	private Integer id_log;

	private String ref1;
	private String ref2;
	private String ref3;

	public Integer getId_carga() {
		return id_carga;
	}

	public void setId_carga(Integer id_carga) {
		this.id_carga = id_carga;
	}

	public Integer getId_ejecucion() {
		return id_ejecucion;
	}

	public void setId_ejecucion(Integer id_ejecucion) {
		this.id_ejecucion = id_ejecucion;
	}

	public Integer getCod_error() {
		return cod_error;
	}

	public void setCod_error(Integer cod_error) {
		this.cod_error = cod_error;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getId_log() {
		return id_log;
	}

	public void setId_log(Integer id_log) {
		this.id_log = id_log;
	}

	public String getRef1() {
		return ref1;
	}

	public void setRef1(String ref1) {
		this.ref1 = ref1;
	}

	public String getRef2() {
		return ref2;
	}

	public void setRef2(String ref2) {
		this.ref2 = ref2;
	}

	public String getRef3() {
		return ref3;
	}

	public void setRef3(String ref3) {
		this.ref3 = ref3;
	}

}
