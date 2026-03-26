package com.osmosyscol.datasuite.logica.dto;

public class FtpUsuarioCorreo {

	public static final String CORREO_CORREVAL = "C"; //Identifica los correos de correval
	public static final String CORREO_USUARIO  = "U"; //Identifica los correos del cliente
	
	private Integer id_ftp_usuario_correo;
	private Integer id_ftp_usuario;
	private String  correo;
	private String  tipo;

	public Integer getId_ftp_usuario_correo() {
		return id_ftp_usuario_correo;
	}

	public void setId_ftp_usuario_correo(Integer id_ftp_usuario_correo) {
		this.id_ftp_usuario_correo = id_ftp_usuario_correo;
	}

	public Integer getId_ftp_usuario() {
		return id_ftp_usuario;
	}

	public void setId_ftp_usuario(Integer id_ftp_usuario) {
		this.id_ftp_usuario = id_ftp_usuario;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
