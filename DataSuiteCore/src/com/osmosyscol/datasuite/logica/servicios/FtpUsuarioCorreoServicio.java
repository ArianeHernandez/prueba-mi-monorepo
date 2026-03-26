package com.osmosyscol.datasuite.logica.servicios;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.FtpUsuarioCorreo;
import com.osmosyscol.datasuite.persistencia.dao.FtpUsuarioCorreoDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

/**
 * Clase que contiene los métodos para trabajar con los objetos <code>FtpUsuarioCorreo</code>
 * 
 * @author hjdiaz
 */

public class FtpUsuarioCorreoServicio {

	private static FtpUsuarioCorreoServicio instance = new FtpUsuarioCorreoServicio();

	private FtpUsuarioCorreoServicio() {
	}

	public static FtpUsuarioCorreoServicio getInstance() {
		return instance;
	}

	public FtpUsuarioCorreo obtenerFtpUsuarioCorreo(Integer id_ftp_usuario_correo) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			
			FtpUsuarioCorreoDao dao = (FtpUsuarioCorreoDao) daoManager.getDao(FtpUsuarioCorreoDao.class);
			
			return dao.obtenerFtpUsuarioCorreo(id_ftp_usuario_correo);

		} catch (Exception e) {
			SimpleLogger.setError("Error en FtpUsuarioCorreoServicio.obtenerFtpUsuarioCorreo:", e);
			return null;
		}
	}
	
	public List<FtpUsuarioCorreo> obtenerFtpUsuarioCorreoPorFtpUsuario(Integer id_ftp_usuario){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			
			FtpUsuarioCorreoDao dao = (FtpUsuarioCorreoDao) daoManager.getDao(FtpUsuarioCorreoDao.class);
			
			return dao.obtenerFtpUsuarioCorreoPorFtpUsuario(id_ftp_usuario);

		} catch (Exception e) {
			SimpleLogger.setError("Error en FtpUsuarioCorreoServicio.obtenerFtpUsuarioCorreoPorFtpUsuario:", e);
			return null;
		}
	}
	
	public Boolean guardarFtpUsuarioCorreo(FtpUsuarioCorreo ftpUsuarioCorreo){
		try {
			
			FtpUsuarioCorreo ftpUsuarioCorreo_act = obtenerFtpUsuarioCorreo(ftpUsuarioCorreo.getId_ftp_usuario_correo());
			
			if (ftpUsuarioCorreo_act != null){
				return actualizarFtpUsuarioCorreo(ftpUsuarioCorreo);
			}
			
			Integer id = obtenerSiguienteId();
			
			ftpUsuarioCorreo.setId_ftp_usuario_correo(id);
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			
			FtpUsuarioCorreoDao dao = (FtpUsuarioCorreoDao) daoManager.getDao(FtpUsuarioCorreoDao.class);
			
			return dao.insertarFtpUsuarioCorreo(ftpUsuarioCorreo);
			
		} catch (Exception e) {
			SimpleLogger.setError("Error en FtpUsuarioCorreoServicio.guardarFtpUsuarioCorreo:", e);
			return null;
		}
	}
	
	public Boolean actualizarFtpUsuarioCorreo(FtpUsuarioCorreo ftpUsuarioCorreo){
		try {
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			
			FtpUsuarioCorreoDao dao = (FtpUsuarioCorreoDao) daoManager.getDao(FtpUsuarioCorreoDao.class);
			
			return dao.actualizarFtpUsuarioCorreo(ftpUsuarioCorreo);
			
		} catch (Exception e) {
			SimpleLogger.setError("Error en FtpUsuarioCorreoServicio.actualizarFtpUsuarioCorreo:", e);
			return null;
		}
	}
	
	public Integer obtenerSiguienteId(){
		try {
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			
			FtpUsuarioCorreoDao dao = (FtpUsuarioCorreoDao) daoManager.getDao(FtpUsuarioCorreoDao.class);
			
			return dao.obtenerSiguienteId();
			
		} catch (Exception e) {
			SimpleLogger.setError("Error en FtpUsuarioCorreoServicio.obtenerSiguienteId:", e);
			return null;
		}
	}
	
	public Boolean borrarFptUsuarioCorreo(Integer id_ftp_usuario_correo){
		try {
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			
			FtpUsuarioCorreoDao dao = (FtpUsuarioCorreoDao) daoManager.getDao(FtpUsuarioCorreoDao.class);
			
			return dao.borrarFtpUsuarioCorreo(id_ftp_usuario_correo);
			
		} catch (Exception e) {
			SimpleLogger.setError("Error en FtpUsuarioCorreoServicio.borrarFptUsuarioCorreoPorFtpUsuario:", e);
			return null;
		}
	}
	
	public List<FtpUsuarioCorreo> obtenerFtpUsuarioCorreoPorFtpUsuarioTipo(Integer id_ftp_usuario, String tipo){
		try {
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			
			FtpUsuarioCorreoDao dao = (FtpUsuarioCorreoDao) daoManager.getDao(FtpUsuarioCorreoDao.class);
			
			return dao.obtenerFtpUsuarioCorreoPorFtpUsuarioTipo(id_ftp_usuario, tipo);

		} catch (Exception e) {
			SimpleLogger.setError("Error en FtpUsuarioCorreoServicio.obtenerFtpUsuarioCorreoPorFtpUsuario:", e);
			return null;
		}
	}

}
