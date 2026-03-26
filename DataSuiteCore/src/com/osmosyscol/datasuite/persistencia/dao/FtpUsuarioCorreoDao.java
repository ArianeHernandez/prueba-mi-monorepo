package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.logica.dto.FtpUsuarioCorreo;

public interface FtpUsuarioCorreoDao {
	
	public Boolean insertarFtpUsuarioCorreo(FtpUsuarioCorreo ftpUsuarioCorreo);
	
	public Integer obtenerSiguienteId();
	
	public FtpUsuarioCorreo obtenerFtpUsuarioCorreo(Integer id_ftp_usuario_correo);
	
	public List<FtpUsuarioCorreo> obtenerFtpUsuarioCorreoPorFtpUsuario(Integer id_ftp_usuario);
	
	public Boolean borrarFtpUsuarioCorreo(Integer id_ftp_usuario_correo);
	
	public Boolean borrarFtpUsuarioCorreoPorFtpUsuario (Integer id_ftp_usuario);
	
	public Boolean actualizarFtpUsuarioCorreo(FtpUsuarioCorreo ftpUsuarioCorreo);
	
	public List<FtpUsuarioCorreo> obtenerFtpUsuarioCorreoPorFtpUsuarioTipo(Integer id_ftp_usuario, String tipo);

}
