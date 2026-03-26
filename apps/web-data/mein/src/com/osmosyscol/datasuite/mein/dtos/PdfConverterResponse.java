package com.osmosyscol.datasuite.mein.dtos;

public class PdfConverterResponse {
	private String pdf_base64;
	private String codigo;
	private String descripcion;
	
	public PdfConverterResponse(String pdf_base64, String codigo, String descripcion) {
		super();
		this.pdf_base64 = pdf_base64;
		this.codigo = codigo;
		this.descripcion = descripcion;
	}
	public String getPdf_base64() {
		return pdf_base64;
	}
	public void setPdf_base64(String pdf_base64) {
		this.pdf_base64 = pdf_base64;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
