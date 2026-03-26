package com.itosmosys.firmadigital.dto;

import java.util.Date;

public class DocumentoFirmado {

	private String texto_claro;
	private String ruta_documento_firmado;
	private String login_usuario_firma;
	private Date fecha_registro;
	private Integer id_carga;
	private String texto_firmado;
	private String id_documento_firmado;
	private String valido = "N";
	private String fase_proceso;
	
	public String getTexto_claro() {
		return texto_claro;
	}
	public void setTexto_claro(String texto_claro) {
		this.texto_claro = texto_claro;
	}
	public String getRuta_documento_firmado() {
		return ruta_documento_firmado;
	}
	public void setRuta_documento_firmado(String ruta_documento_firmado) {
		this.ruta_documento_firmado = ruta_documento_firmado;
	}
	public String getLogin_usuario_firma() {
		return login_usuario_firma;
	}
	public void setLogin_usuario_firma(String login_usuario_firma) {
		this.login_usuario_firma = login_usuario_firma;
	}
	public Date getFecha_registro() {
		return fecha_registro;
	}
	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	public Integer getId_carga() {
		return id_carga;
	}
	public void setId_carga(Integer id_carga) {
		this.id_carga = id_carga;
	}
	public String getTexto_firmado() {
		return texto_firmado;
	}
	public void setTexto_firmado(String texto_firmado) {
		this.texto_firmado = texto_firmado;
	}
	public String getId_documento_firmado() {
		return id_documento_firmado;
	}
	public void setId_documento_firmado(String id_documento_firmado) {
		this.id_documento_firmado = id_documento_firmado;
	}
	
	public String getValido() {
		return valido;
	}
	
	public void setValido(String valido) {
		this.valido = valido;
	}
	
	public String getFase_proceso() {
		return fase_proceso;
	}
	
	public void setFase_proceso(String fase_proceso) {
		this.fase_proceso = fase_proceso;
	}
	
}

