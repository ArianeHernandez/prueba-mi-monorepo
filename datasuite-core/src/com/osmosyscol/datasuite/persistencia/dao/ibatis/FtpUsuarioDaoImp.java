package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.FtpUsuario;
import com.osmosyscol.datasuite.persistencia.dao.FtpUsuarioDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

/**
 * @author hjdiaz
 *
 */

public class FtpUsuarioDaoImp extends BaseSqlMapDao implements FtpUsuarioDao {
	
	public FtpUsuarioDaoImp (DaoManager daoManager){
		super(daoManager);
	}
	
	public Boolean insertarFtpUsuario(FtpUsuario ftpUsuario){
		try {
			insert("FtpUsuario.insertarFtpUsuario", ftpUsuario);
			return true;
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsarioDaoImp.insertarFtpUsuario:", e);
			return false;
		}
	}
	
	public Integer obtenerSiguienteId(){
		try {
			return (Integer) queryForObject("FtpUsuario.obtenerSiguienteId");
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsarioDaoImp.obtenerSiguienteId:", e);
			return null;
		}
	}
	
	public FtpUsuario obtenerFtpUsuario(Integer id_ftp_usuario){
		try {
			return (FtpUsuario) queryForObject("FtpUsuario.obtenerFtpUsuario", id_ftp_usuario);
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsarioDaoImp.obtenerFtpUsuario:", e);
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<FtpUsuario> obtenerFtpUsuarioPorUsuario(Integer id_usuario){
		try {
			return (List<FtpUsuario>) queryForList("FtpUsuario.obtenerFtpUsuarioPorUsuario", id_usuario);
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsarioDaoImp.obtenerFtpUsuarioPorUsuario:", e);
			return null;
		}
	}
	
	public Boolean borrarFtpUsuario(Integer id_ftp_usuario){
		try {
			Integer cant = delete("FtpUsuario.borrarFtpUsuario", id_ftp_usuario);
			return cant>0;
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsarioDaoImp.borrarFtpUsuario:", e);
			return false;
		}
	}
	
	public Boolean borrarFtpUsuarioPorUsuario(Integer id_usuario){
		try {
			Integer cant = delete("FtpUsuario.borrarFtpUsuarioPorUsuario", id_usuario);
			return cant>0;
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsarioDaoImp.borrarFtpUsuarioPorUsuario:", e);
			return false;
		}
	}
	
	public Boolean actualizarFtpUsuario(FtpUsuario ftpUsuario){
		try {
			Integer cant = update("FtpUsuario.actualizarFtpUsuario", ftpUsuario);
			return cant>0;
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsarioDaoImp.actualizarFtpUsuario:", e);
			return false;
		}
	}
	
	public Boolean guardarConfiguracionCliente(FtpUsuario ftpUsuario){
		try {
			Integer cant = update("FtpUsuario.guardarConfiguracionCliente", ftpUsuario);
			return cant>0;
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsarioDaoImp.guardarConfiguracionCliente:", e);
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<FtpUsuario> obtenerFtpUsuarioActivos(){
		try {
			return (List<FtpUsuario>) queryForList("FtpUsuario.obtenerFtpUsuarioActivos");
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsarioDaoImp.obtenerFtpUsuarioActivos:", e);
			return null;
		}
	}

}
