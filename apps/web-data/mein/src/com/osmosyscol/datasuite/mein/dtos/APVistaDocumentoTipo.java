package com.osmosyscol.datasuite.mein.dtos;

public class APVistaDocumentoTipo {
	private Integer id;
	private Integer id_vista;
	private String documento_tipo;
	private String codigo_alfanumerico;
	private Integer codigo_numerico;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId_vista() {
		return id_vista;
	}
	public void setId_vista(Integer id_vista) {
		this.id_vista = id_vista;
	}
	public String getDocumento_tipo() {
		return documento_tipo;
	}
	public void setDocumento_tipo(String documento_tipo) {
		this.documento_tipo = documento_tipo;
	}
	public String getCodigo_alfanumerico() {
		return codigo_alfanumerico;
	}
	public void setCodigo_alfanumerico(String codigo_alfanumerico) {
		this.codigo_alfanumerico = codigo_alfanumerico;
	}
	public Integer getCodigo_numerico() {
		return codigo_numerico;
	}
	public void setCodigo_numerico(Integer codigo_numerico) {
		this.codigo_numerico = codigo_numerico;
	}
}
