package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.FtpUsuarioCorreo;
import com.osmosyscol.datasuite.persistencia.dao.FtpUsuarioCorreoDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

/**
 * @author hjdiaz
 *
 */

public class FtpUsuarioCorreoDaoImp extends BaseSqlMapDao implements FtpUsuarioCorreoDao {
	
	public FtpUsuarioCorreoDaoImp (DaoManager daoManager){
		super(daoManager);
	}
	
	public Boolean insertarFtpUsuarioCorreo(FtpUsuarioCorreo ftpUsuarioCorreo){
		try {
			insert("FtpUsuarioCorreo.insertarFtpUsuarioCorreo", ftpUsuarioCorreo);
			return true;
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsarioCorreoDaoImp.insertarFtpUsuarioCorreo:", e);
			return false;
		}
	}
	
	public Integer obtenerSiguienteId(){
		try {
			return (Integer) queryForObject("FtpUsuarioCorreo.obtenerSiguienteId");
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsarioCorreoDaoImp.obtenerSiguienteId:", e);
			return null;
		}
	}
	
	public FtpUsuarioCorreo obtenerFtpUsuarioCorreo(Integer id_ftp_usuario_correo){
		try {
			return (FtpUsuarioCorreo) queryForObject("FtpUsuarioCorreo.obtenerFtpUsuarioCorreo", id_ftp_usuario_correo);
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsarioCorreoDaoImp.obtenerFtpUsuarioCorreo:", e);
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<FtpUsuarioCorreo> obtenerFtpUsuarioCorreoPorFtpUsuario(Integer id_ftp_usuario){
		try {
			return (List<FtpUsuarioCorreo>) queryForList("FtpUsuarioCorreo.obtenerFtpUsuarioCorreoPorFtpUsuario", id_ftp_usuario);
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsarioCorreoDaoImp.obtenerFtpUsuarioCorreoPorFtpUsuario:", e);
			return null;
		}
	}
	
	public Boolean borrarFtpUsuarioCorreo(Integer id_ftp_usuario_correo){
		try {
			Integer cant = delete("FtpUsuarioCorreo.borrarFtpUsuarioCorreo", id_ftp_usuario_correo);
			return cant>0;
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsarioCorreoDaoImp.borrarFtpUsuarioCorreo:", e);
			return false;
		}
	}
	
	public Boolean borrarFtpUsuarioCorreoPorFtpUsuario(Integer id_ftp_usuario){
		try {
			Integer cant = delete("FtpUsuarioCorreo.borrarFtpUsuarioCorreoPorFtpUsuario", id_ftp_usuario);
			return cant>0;
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsarioCorreoDaoImp.borrarFtpUsuarioCorreoPorFtpUsuario:", e);
			return false;
		}
	}
	
	public Boolean actualizarFtpUsuarioCorreo(FtpUsuarioCorreo ftpUsuarioCorreo){
		try {
			Integer cant = update("FtpUsuarioCorreo.actualizarFtpUsuarioCorreo", ftpUsuarioCorreo);
			return cant>0;
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsarioCorreoDaoImp.actualizarFtpUsuarioCorreo:", e);
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<FtpUsuarioCorreo> obtenerFtpUsuarioCorreoPorFtpUsuarioTipo(Integer id_ftp_usuario, String tipo) {
		try {
			Map<String, Object> parametros = new HashMap<String, Object>();
			
			parametros.put("id_ftp_usuario", id_ftp_usuario);
			parametros.put("tipo", tipo);
			
			return (List<FtpUsuarioCorreo>) queryForList("FtpUsuarioCorreo.obtenerFtpUsuarioCorreoPorFtpUsuarioTipo", parametros);	
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsarioCorreoDaoImp.obtenerFtpUsuarioCorreoPorFtpUsuarioTipo:", e);
			return null;
		}
		
	}

}
