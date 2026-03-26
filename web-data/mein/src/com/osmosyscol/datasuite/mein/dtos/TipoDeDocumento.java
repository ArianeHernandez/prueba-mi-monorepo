package com.osmosyscol.datasuite.mein.dtos;

public class TipoDeDocumento {
	private Integer id;
	private String nombre;
	private String codigo;
	private String id_postal;
	private String nombre_postal;
	private String codigo_alfanumerico;
	private Integer codigo_numerico_postal;
	private String cod_hts;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getId_postal() {
		return id_postal;
	}
	public void setId_postal(String id_postal) {
		this.id_postal = id_postal;
	}
	public String getNombre_postal() {
		return nombre_postal;
	}
	public void setNombre_postal(String nombre_postal) {
		this.nombre_postal = nombre_postal;
	}
	public String getCodigo_alfanumerico() {
		return codigo_alfanumerico;
	}
	public void setCodigo_alfanumerico(String codigo_alfanumerico) {
		this.codigo_alfanumerico = codigo_alfanumerico;
	}
	public Integer getCodigo_numerico_postal() {
		return codigo_numerico_postal;
	}
	public void setCodigo_numerico_postal(Integer codigo_numerico_postal) {
		this.codigo_numerico_postal = codigo_numerico_postal;
	}
	public String getCod_hts() {
		return cod_hts;
	}
	public void setCod_hts(String cod_hts) {
		this.cod_hts = cod_hts;
	}
	
	@Override
	public String toString() {
		return "TipoDeDocumento [id=" + id + ", nombre=" + nombre + ", codigo="
				+ codigo + ", id_postal=" + id_postal + ", nombre_postal="
				+ nombre_postal + ", codigo_alfanumerico="
				+ codigo_alfanumerico + ", codigo_numerico_postal="
				+ codigo_numerico_postal + ", cod_hts=" + cod_hts + "]";
	}
	
}
