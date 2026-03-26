package com.osmosyscol.datasuite.webdata.logica.dto;

public class FormatoFiltro {

	// -----------------------------------

	private Integer id_formatofiltro;
	private String id_formato;
	private String id_tipocampo;
	private String id_listadinamica;
	private String nombre;
	private String condicion;
	private String ayuda;

	// -----------------------------------

	public Integer getId_formatofiltro() {
		return id_formatofiltro;
	}

	public void setId_formatofiltro(Integer id_formatofiltro) {
		this.id_formatofiltro = id_formatofiltro;
	}

	public String getId_formato() {
		return id_formato;
	}

	public void setId_formato(String id_formato) {
		this.id_formato = id_formato;
	}

	public String getId_tipocampo() {
		return id_tipocampo;
	}

	public void setId_tipocampo(String id_tipocampo) {
		this.id_tipocampo = id_tipocampo;
	}

	public String getId_listadinamica() {
		return id_listadinamica;
	}

	public void setId_listadinamica(String id_listadinamica) {
		this.id_listadinamica = id_listadinamica;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCondicion() {
		return condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	public String getAyuda() {
		return ayuda;
	}

	public void setAyuda(String ayuda) {
		this.ayuda = ayuda;
	}

}
