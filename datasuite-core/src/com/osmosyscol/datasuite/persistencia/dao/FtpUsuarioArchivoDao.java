package com.osmosyscol.datasuite.persistencia.dao;

import java.util.Date;
import java.util.List;

import com.osmosyscol.datasuite.logica.dto.FtpUsuarioArchivo;

public interface FtpUsuarioArchivoDao {
	
	public Boolean insertarFtpUsuarioArchivo(FtpUsuarioArchivo ftpUsuarioArchivo);
	
	public Integer obtenerSiguienteId();
	
	public FtpUsuarioArchivo obtenerFtpUsuarioArchivo(Integer id_ftp_usuario_archivo);
	
	public List<FtpUsuarioArchivo> obtenerFtpUsuarioArchivoPorFtpUsuario(Integer id_ftp_usuario);
	
	public Boolean borrarFtpUsuarioArchivo(Integer id_ftp_usuario_archivo);
	
	public Boolean borrarFtpUsuarioArchivoPorFtpUsuario (Integer id_ftp_usuario);
	
	public Boolean actualizarFtpUsuarioArchivo(FtpUsuarioArchivo ftpUsuarioArchivo);
	
	public Boolean actualizarEstado(Integer id_ftp_usuario_archivo, String estado);
	
	public List<FtpUsuarioArchivo> obtenerDuplicados(String md5, Date fecha_cargue);
	
	public List<FtpUsuarioArchivo> buscarArchivos(Integer id_ftp_usuario_archivo, Integer id_formato, Integer id_carga,
			                                      String fecha_inicial, String fecha_final, String nombre, String estado,
			                                      Integer id_usuario, String estado_carga, Integer numero_pagina, Integer tamano_pagina);
	
	public Integer totalArchivos(Integer id_ftp_usuario_archivo, Integer id_formato, Integer id_carga, String fecha_inicial, 
                                 String fecha_final, String nombre, String estado, Integer id_usuario, String estado_carga);
	

}
