package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.FtpUsuarioArchivo;
import com.osmosyscol.datasuite.persistencia.dao.FtpUsuarioArchivoDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

/**
 * @author hjdiaz
 *
 */

public class FtpUsuarioArchivoDaoImp extends BaseSqlMapDao implements FtpUsuarioArchivoDao {
	
	public FtpUsuarioArchivoDaoImp (DaoManager daoManager){
		super(daoManager);
	}
	
	public Boolean insertarFtpUsuarioArchivo(FtpUsuarioArchivo ftpUsuarioArchivo){
		try {
			insert("FtpUsuarioArchivo.insertarFtpUsuarioArchivo", ftpUsuarioArchivo);
			return true;
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsarioArchivoDaoImp.insertarFtpUsuarioArchivo:", e);
			return false;
		}
	}
	
	public Integer obtenerSiguienteId(){
		try {
			return (Integer) queryForObject("FtpUsuarioArchivo.obtenerSiguienteId");
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsarioArchivoDaoImp.obtenerSiguienteId:", e);
			return null;
		}
	}
	
	public FtpUsuarioArchivo obtenerFtpUsuarioArchivo(Integer id_ftp_usuario_archivo){
		try {
			return (FtpUsuarioArchivo) queryForObject("FtpUsuarioArchivo.obtenerFtpUsuarioArchivo", id_ftp_usuario_archivo);
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsarioArchivoDaoImp.obtenerFtpUsuarioArchivo:", e);
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<FtpUsuarioArchivo> obtenerFtpUsuarioArchivoPorFtpUsuario(Integer id_ftp_usuario){
		try {
			return (List<FtpUsuarioArchivo>) queryForList("FtpUsuarioArchivo.obtenerFtpUsuarioArchivoPorFtpUsuario", id_ftp_usuario);
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsarioArchivoDaoImp.obtenerFtpUsuarioArchivoPorFtpUsuario:", e);
			return null;
		}
	}
	
	public Boolean borrarFtpUsuarioArchivo(Integer id_ftp_usuario_archivo){
		try {
			Integer cant = delete("FtpUsuarioArchivo.borrarFtpUsuarioArchivo", id_ftp_usuario_archivo);
			return cant>0;
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsarioArchivoDaoImp.borrarFtpUsuarioArchivo:", e);
			return false;
		}
	}
	
	public Boolean borrarFtpUsuarioArchivoPorFtpUsuario(Integer id_ftp_usuario){
		try {
			Integer cant = delete("FtpUsuarioArchivo.borrarFtpUsuarioArchivoPorFtpUsuario", id_ftp_usuario);
			return cant>0;
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsarioArchivoDaoImp.borrarFtpUsuarioArchivoPorFtpUsuario:", e);
			return false;
		}
	}
	
	public Boolean actualizarFtpUsuarioArchivo(FtpUsuarioArchivo ftpUsuarioArchivo){
		try {
			Integer cant = update("FtpUsuarioArchivo.actualizarFtpUsuarioArchivo", ftpUsuarioArchivo);
			return cant>0;
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsarioArchivoDaoImp.actualizarFtpUsuarioArchivo:", e);
			return false;
		}
	}
	
	public Boolean actualizarEstado(Integer id_ftp_usuario_archivo, String estado){
		try {
			Map<String, Object> parametros = new HashMap<String, Object>();
			
			parametros.put("id_ftp_usuario_archivo", id_ftp_usuario_archivo);
			parametros.put("estado", estado);
			
			Integer cant = update("FtpUsuarioArchivo.actualizarEstado", parametros);
			return cant>0;
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsarioArchivoDaoImp.actualizarEstado:", e);
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<FtpUsuarioArchivo> obtenerDuplicados(String md5, Date fecha_cargue){
		try {
			Map<String, Object> parametros = new HashMap<String, Object>();
			
			parametros.put("md5", md5);
			parametros.put("fecha_cargue", fecha_cargue);
			
			return (List<FtpUsuarioArchivo>) queryForList("FtpUsuarioArchivo.obtenerDuplicados", parametros);
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsarioArchivoDaoImp.obtenerDuplicados:", e);
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<FtpUsuarioArchivo> buscarArchivos(Integer id_ftp_usuario_archivo, Integer id_formato, Integer id_carga,
			                                      String fecha_inicial, String fecha_final, String nombre, String estado,
			                                      Integer id_usuario, String estado_carga,  Integer numero_pagina, Integer tamano_pagina){
		try {
			Map<String, Object> parametros = new HashMap<String, Object>();
			
			parametros.put("id_ftp_usuario_archivo", id_ftp_usuario_archivo);
			parametros.put("id_formato", id_formato);
			parametros.put("id_carga", id_carga);
			parametros.put("fecha_inicial", fecha_inicial);
			parametros.put("fecha_final", fecha_final);
			parametros.put("nombre", nombre);
			parametros.put("estado", estado);
			parametros.put("id_usuario", id_usuario);
			parametros.put("estado_carga", estado_carga);
			
			if (numero_pagina != null && tamano_pagina != null){
				return (List<FtpUsuarioArchivo>) queryForListPag("FtpUsuarioArchivo.buscarArchivos", parametros, numero_pagina, tamano_pagina);	
			}else {
				return (List<FtpUsuarioArchivo>) queryForList("FtpUsuarioArchivo.buscarArchivos", parametros);
			}
			
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsarioArchivoDaoImp.buscarArchivos:", e);
			return null;
		}
	}
	
	public Integer totalArchivos(Integer id_ftp_usuario_archivo, Integer id_formato, Integer id_carga, String fecha_inicial, 
                                 String fecha_final, String nombre, String estado, Integer id_usuario, String estado_carga){
		try {
			Map<String, Object> parametros = new HashMap<String, Object>();
			
			parametros.put("id_ftp_usuario_archivo", id_ftp_usuario_archivo);
			parametros.put("id_formato", id_formato);
			parametros.put("id_carga", id_carga);
			parametros.put("fecha_inicial", fecha_inicial);
			parametros.put("fecha_final", fecha_final);
			parametros.put("nombre", nombre);
			parametros.put("estado", estado);
			parametros.put("id_usuario", id_usuario);
			parametros.put("estado_carga", estado_carga);
			
			return (Integer) queryForObject("FtpUsuarioArchivo.contarBusqArchivos", parametros);
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsarioArchivoDaoImp.totalArchivos:", e);
			return null;
		}
	}
	

}
