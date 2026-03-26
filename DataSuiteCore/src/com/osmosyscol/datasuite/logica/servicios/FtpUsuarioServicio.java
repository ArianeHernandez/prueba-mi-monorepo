package com.osmosyscol.datasuite.logica.servicios;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.Franja;
import com.osmosyscol.datasuite.logica.dto.FtpUsuario;
import com.osmosyscol.datasuite.logica.dto.FtpUsuarioCorreo;
import com.osmosyscol.datasuite.logica.dto.Horario;
import com.osmosyscol.datasuite.persistencia.dao.FtpUsuarioDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

/**
 * Clase que contiene los métodos para trabajar con los objetos <code>FtpUsuario</code>
 * 
 * @author hjdiaz
 */

public class FtpUsuarioServicio {
	
	private static FtpUsuarioServicio instance = new FtpUsuarioServicio();
	
	private static Map<String, Boolean> mapaRespuesta = new HashMap<String, Boolean>();
	
	private static Map<String, Integer> mapaId = new HashMap<String, Integer>();
	
	private static Map<String, Integer> mapaIdHorario = new HashMap<String, Integer>();
	
	private FtpUsuarioServicio(){
	}
	
	public static FtpUsuarioServicio getInstance(){
		return instance;
	}
	
	public Boolean guardarFtpUsuarioRespuesta(FtpUsuario ftpUsuario, String id_respuesta){
		return guardarFtpUsuario(ftpUsuario, id_respuesta);
	}
	
	public Boolean guardarFtpUsuario(FtpUsuario ftpUsuario){
		return guardarFtpUsuario(ftpUsuario, null);
	}
	
	public Boolean guardarFtpUsuario(FtpUsuario ftpUsuario, String id_respuesta){
		try {
						
			FtpUsuario ftpUsuario_act = obtenerFtpUsuario(ftpUsuario.getId_ftp_usuario());
			
			if(ftpUsuario_act != null){
				ftpUsuario.setId_ftp_usuario(ftpUsuario_act.getId_ftp_usuario());
				return actualizarFtpUsuario(ftpUsuario, id_respuesta);
			}
			
			Integer id = obtenerSiguienteIdFtpUsuario();
			
			ftpUsuario.setId_ftp_usuario(id);
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			
			FtpUsuarioDao ftpUsuarioDao = (FtpUsuarioDao) daoManager.getDao(FtpUsuarioDao.class);
			
			if (id_respuesta != null){
				Boolean respuesta = ftpUsuarioDao.insertarFtpUsuario(ftpUsuario) && actualizarCorreosCorreval(ftpUsuario);
				mapaId.put(id_respuesta, id);
				mapaRespuesta.put(id_respuesta, respuesta);
				return respuesta;
			}else {
				return ftpUsuarioDao.insertarFtpUsuario(ftpUsuario) && actualizarCorreosCorreval(ftpUsuario);	
			}
			
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsuarioServicio.crearFtpUsuario:", e);
			return false;
		}
	}
	
	public FtpUsuario obtenerFtpUsuario(Integer id_ftp_usuario){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			
			FtpUsuarioDao ftpUsuarioDao = (FtpUsuarioDao) daoManager.getDao(FtpUsuarioDao.class);
			
			FtpUsuario ftpUsuario = ftpUsuarioDao.obtenerFtpUsuario(id_ftp_usuario);
			
			if (ftpUsuario != null){
				ftpUsuario.setCorreos_correval(FtpUsuarioCorreoServicio.getInstance().obtenerFtpUsuarioCorreoPorFtpUsuarioTipo(id_ftp_usuario, FtpUsuarioCorreo.CORREO_CORREVAL));
				ftpUsuario.setCorreos_usuario(FtpUsuarioCorreoServicio.getInstance().obtenerFtpUsuarioCorreoPorFtpUsuarioTipo(id_ftp_usuario, FtpUsuarioCorreo.CORREO_USUARIO));
			}
			
			return ftpUsuario;
			
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsuarioServicio.obtenerFtpUsuario:", e);
			return null;
		}
	}
	
	public List<FtpUsuario> obtenerFtpUsuarioPorUsuario(Integer id_usuario){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			
			FtpUsuarioDao ftpUsuarioDao = (FtpUsuarioDao) daoManager.getDao(FtpUsuarioDao.class);
		
			List<FtpUsuario> ftpUsuarios = ftpUsuarioDao.obtenerFtpUsuarioPorUsuario(id_usuario);
			
			if (ftpUsuarios != null && !ftpUsuarios.isEmpty()){
				for (FtpUsuario ftpUsuario : ftpUsuarios) {
					ftpUsuario.setCorreos_correval(FtpUsuarioCorreoServicio.getInstance().obtenerFtpUsuarioCorreoPorFtpUsuarioTipo(ftpUsuario.getId_ftp_usuario(), FtpUsuarioCorreo.CORREO_CORREVAL));
					ftpUsuario.setCorreos_usuario(FtpUsuarioCorreoServicio.getInstance().obtenerFtpUsuarioCorreoPorFtpUsuarioTipo(ftpUsuario.getId_ftp_usuario(), FtpUsuarioCorreo.CORREO_USUARIO));
				}
			}
			
			return ftpUsuarios;
			
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsuarioServicio.obtenerFtpUsuarioPorUsuario:", e);
			return null;
		}
	}
	
	public FtpUsuario obtenerUnoFtpUsuarioPorUsuario(Integer id_usuario){
		List<FtpUsuario> lista = obtenerFtpUsuarioPorUsuario(id_usuario);
		if (lista != null && !lista.isEmpty()){
			for(FtpUsuario item : lista){
				if (item != null){
					item.setCorreos_correval(FtpUsuarioCorreoServicio.getInstance().obtenerFtpUsuarioCorreoPorFtpUsuarioTipo(item.getId_ftp_usuario(), FtpUsuarioCorreo.CORREO_CORREVAL));
					item.setCorreos_usuario(FtpUsuarioCorreoServicio.getInstance().obtenerFtpUsuarioCorreoPorFtpUsuarioTipo(item.getId_ftp_usuario(), FtpUsuarioCorreo.CORREO_USUARIO));
				}
				return item;
			}
		}
		SimpleLogger.setDebug("FtpUsuarioServicio.obtenerUnoFtpUsuarioPorUsuario: no hay configuraciones para el usuario con id " + id_usuario);
		return null;
	}
	
	public Boolean actualizarFtpUsuario(FtpUsuario ftpUsuario){
		return actualizarFtpUsuario(ftpUsuario, null);
	}
	
	public Boolean actualizarFtpUsuario(FtpUsuario ftpUsuario, String id_respuesta){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			
			FtpUsuarioDao ftpUsuarioDao = (FtpUsuarioDao) daoManager.getDao(FtpUsuarioDao.class);
			
			if (id_respuesta != null){
				Boolean respuesta = ftpUsuarioDao.actualizarFtpUsuario(ftpUsuario) && actualizarCorreosCorreval(ftpUsuario);
				mapaRespuesta.put(id_respuesta, respuesta);
				mapaId.put(id_respuesta, ftpUsuario.getId_ftp_usuario());
				return respuesta;
			}else {
				return ftpUsuarioDao.actualizarFtpUsuario(ftpUsuario) && actualizarCorreosCorreval(ftpUsuario);
			}
		
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsuarioServicio.actualizarFtpUsuario:", e);
			return false;
		}
	}
	
	private Integer obtenerSiguienteIdFtpUsuario() throws Exception{
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			
			FtpUsuarioDao ftpUsuarioDao = (FtpUsuarioDao) daoManager.getDao(FtpUsuarioDao.class);
			
			return ftpUsuarioDao.obtenerSiguienteId();
		
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsuarioServicio.obtenerSiguienteIdFtpUsuario:", e);
			throw new RuntimeException(e);
		}
		
	}
	
	public Boolean obtenerRespuesta(String id_respuesta){
		return mapaRespuesta.get(id_respuesta);
	}
	
	public Integer obtenerId(String id_respuesta){
		return mapaId.get(id_respuesta);
	}
	
	public Integer obtenerIdHorario(String id_respuesta){
		return mapaIdHorario.get(id_respuesta);
	}
	
	public Boolean asociarFtpUsuarioProceso(Integer id_ftp_usuario, Integer id_proceso){
		try {
			FtpUsuario ftpUsuario = obtenerFtpUsuario(id_ftp_usuario);
			if (ftpUsuario != null){
				ftpUsuario.setId_proceso(id_proceso);
				return actualizarFtpUsuario(ftpUsuario);
			}
			return false;
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsuarioServicio.asociarFtpUsuarioProceso:", e);
			return false;
		}
	}
	
	public Boolean actualizarCorreosCorreval(FtpUsuario ftpUsuario){
		try {
			List<FtpUsuarioCorreo> correos = ftpUsuario.getCorreos_correval();
			if (correos != null && !correos.isEmpty()){
				for (FtpUsuarioCorreo correo : correos) {
					if (correo.getCorreo() != null){
						correo.setId_ftp_usuario(ftpUsuario.getId_ftp_usuario());
						if (!FtpUsuarioCorreoServicio.getInstance().guardarFtpUsuarioCorreo(correo)){
							return false;
						}
					}
				}
			}
			return true;
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsuarioServicio.actualizarCorreos:", e);
			return false;
		}
	}
	
	public Boolean actualizarCorreosUsuario(FtpUsuario ftpUsuario){
		try {
			List<FtpUsuarioCorreo> correos = ftpUsuario.getCorreos_usuario();
			if (correos != null && !correos.isEmpty()){
				for (FtpUsuarioCorreo correo : correos) {
					if (correo.getCorreo() != null){
						correo.setId_ftp_usuario(ftpUsuario.getId_ftp_usuario());
						if (!FtpUsuarioCorreoServicio.getInstance().guardarFtpUsuarioCorreo(correo)){
							return false;
						}
					}
				}
			}
			return true;
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsuarioServicio.actualizarCorreos:", e);
			return false;
		}
	}
	
	public Boolean guardarConfiguracionClienteRespuesta(FtpUsuario ftpUsuario, String franjas, String id_respuesta){
		return guardarConfiguracionCliente(ftpUsuario, franjas, id_respuesta);
	}
	
	public Boolean guardarConfiguracionCliente(FtpUsuario ftpUsuario, String franjas){
		return guardarConfiguracionCliente(ftpUsuario, franjas, null);
	}
	
	public Boolean guardarConfiguracionCliente(FtpUsuario ftpUsuario_new, String franjas, String id_respuesta){
		try {
			
			FtpUsuario ftpUsuario = obtenerFtpUsuario(ftpUsuario_new.getId_ftp_usuario());
			
			if(ftpUsuario != null){
				
				if (ftpUsuario.getId_horario() == null){
					HorarioServicio horarioServicio = HorarioServicio.getInstance();
					Integer horarioCargue = horarioServicio.obtenerSiguienteIDHorario();
					Horario nuevoHorario = new Horario();
					nuevoHorario.setId_horario(horarioCargue);

					if (horarioServicio.crearHorario(nuevoHorario)) {
						ftpUsuario.setId_horario(nuevoHorario.getId_horario());
					}
				}
				
				Boolean rta = HorarioServicio.getInstance().guardarFranjas(franjas, ftpUsuario.getId_horario());
				if (!rta) {
					SimpleLogger.setError("Error en FtpUsuarioServicio.guardarConfiguracionCliente: No es posible guardar las franjas");
				}
				
				ftpUsuario.setCorreos_usuario(ftpUsuario_new.getCorreos_usuario());
				
				ftpUsuario.setFrecuencia_lectura(ftpUsuario_new.getFrecuencia_lectura());
					
				DaoManager daoManager = DaoConfig.getDaoManager();
				
				FtpUsuarioDao ftpUsuarioDao = (FtpUsuarioDao) daoManager.getDao(FtpUsuarioDao.class);				
				
				if (id_respuesta != null){
					Boolean respuesta = ftpUsuarioDao.guardarConfiguracionCliente(ftpUsuario) && actualizarCorreosUsuario(ftpUsuario);
					mapaRespuesta.put(id_respuesta, respuesta);
					mapaIdHorario.put(id_respuesta, ftpUsuario.getId_horario());
					return respuesta;
				}else {
					return ftpUsuarioDao.guardarConfiguracionCliente(ftpUsuario) && actualizarCorreosUsuario(ftpUsuario);	
				}
				
			}else {
				SimpleLogger.setDebug("Error en FtpUsuarioServicio.guardarConfiguracionCliente: No existe la configuración para el cliente");
				return false;
			}
			
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsuarioServicio.guardarConfiguracionCliente:", e);
			return false;
		}		
	}
	
	public List<FtpUsuario> obtenerFtpUsuarioActivos(){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			
			FtpUsuarioDao ftpUsuarioDao = (FtpUsuarioDao) daoManager.getDao(FtpUsuarioDao.class);
			
			return ftpUsuarioDao.obtenerFtpUsuarioActivos();
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsarioServicio.obtenerFtpUsuarioActivos:", e);
			return null;
		}
	}
	
	public Boolean validarHorario(FtpUsuario ftpUsuario){
		try {
			Date fecha = new Date();
			List<Franja> franjas = HorarioServicio.getInstance().obtenerFranjas(ftpUsuario.getId_horario());
			if (franjas != null && !franjas.isEmpty()) {
				return HorarioServicio.getInstance().estaDentroDeHorario(ftpUsuario.getId_horario(), fecha);
			} else {

				Calendar calendar = Calendar.getInstance();
				calendar.setTime(fecha);

				Integer hora = calendar.get(Calendar.HOUR_OF_DAY);

				return (hora >= 8 && hora < 18);
			}			
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsarioServicio.validarHorario:", e);
			return false;	
		}

	}

}
