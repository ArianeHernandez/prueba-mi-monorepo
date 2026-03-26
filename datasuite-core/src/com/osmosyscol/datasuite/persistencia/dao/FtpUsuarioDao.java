package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.logica.dto.FtpUsuario;


public interface FtpUsuarioDao {

	public Boolean insertarFtpUsuario(FtpUsuario ftpUsuario);
	
	public Integer obtenerSiguienteId();
	
	public FtpUsuario obtenerFtpUsuario(Integer id_ftp_usuario);
	
	public List<FtpUsuario> obtenerFtpUsuarioPorUsuario(Integer id_usuario);
	
	public Boolean borrarFtpUsuario(Integer id_ftp_usuario);
	
	public Boolean borrarFtpUsuarioPorUsuario(Integer id_usuario);
	
	public Boolean actualizarFtpUsuario(FtpUsuario ftpUsuario);
	
	public Boolean guardarConfiguracionCliente(FtpUsuario ftpUsuario);
	
	public List<FtpUsuario> obtenerFtpUsuarioActivos();
	
}
