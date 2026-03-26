package com.osmosyscol.datasuite.webdata.correval.bancos.entrada;

public class RespuestaBanco {

	private String codigo_referencia;
	private String estado_respuesta;
	private Boolean tieneErrores = false; 
	private String filaArchivo ;
	private String mensajeRespuesta;
	
	public String getCodigo_referencia() {
		return codigo_referencia;
	}
	public void setCodigo_referencia(String codigo_referencia) {
		this.codigo_referencia = codigo_referencia;
	}
	public String getEstado_respuesta() {
		return estado_respuesta;
	}
	public void setEstado_respuesta(String estado_respuesta) {
		this.estado_respuesta = estado_respuesta;
	}
	
	public void setTieneErrores(Boolean tieneErrores) {
		this.tieneErrores = tieneErrores;
	}
	
	public Boolean getTieneErrores() {
		return tieneErrores;
	}
	
	public String getFilaArchivo() {
		return filaArchivo;
	}
	
	public void setFilaArchivo(String filaArchivo) {
		this.filaArchivo = filaArchivo;
	}
	
	public String getMensajeRespuesta() {
		return mensajeRespuesta;
	}
	
	public void setMensajeRespuesta(String mensaje) {
		this.mensajeRespuesta = mensaje;
	}
	
}
