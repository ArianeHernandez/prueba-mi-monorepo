package com.osmosyscol.datasuite.logica.servicios;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cocoon.environment.SourceResolver;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.mensajeria.EnviaMails;
import com.osmosyscol.commons.utils.JavaToXML;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.Administrativo;
import com.osmosyscol.datasuite.logica.dto.Notificacion;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.persistencia.dao.NotificacionDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class NotificacionServicio {

	private static NotificacionServicio instance;

	private NotificacionServicio() {

	}

	public static NotificacionServicio getInstance() {
		if (instance == null) {
			instance = new NotificacionServicio();
		}
		return instance;
	}

	public Boolean guardarNotificacion(Notificacion notificacion) {
		try {
			
			DaoManager daoManager = DaoConfig.getDaoManager();

			NotificacionDao notificacionDao = (NotificacionDao) daoManager.getDao(NotificacionDao.class);
			return notificacionDao.guardarNotificacion(notificacion);
			

		} catch (Exception e) {
			SimpleLogger.setError("Error al obtener notificaciones", e);
		}
		return false;
		

	}
	public Map<String, Object> obtenerNotificacionesAdministrativoPagNotificacion(Integer id_administrativo, Integer id_notificacion) {
		
		try {
			
			DaoManager daoManager = DaoConfig.getDaoManager();

			NotificacionDao notificacionDao = (NotificacionDao) daoManager.getDao(NotificacionDao.class);
			Integer numPagina =  notificacionDao.obtenerPaginaNotificacion(id_notificacion);
			
			Map<String, Object> map = new HashMap<String, Object>(); 
			
			map.put("pagina",numPagina);
			map.put("lista",obtenerNotificacionesAdministrativo(id_administrativo, numPagina));
			
			
			marcarNotificacionLeida(id_notificacion);
			return map; 

		} catch (Exception e) {
			SimpleLogger.setError("Error al obtener notificaciones", e);
		}
		return null;
		
	}
	public List<Notificacion> obtenerNotificacionesAdministrativo(Integer id_administrativo, Integer pagina) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();

			NotificacionDao notificacionDao = (NotificacionDao) daoManager.getDao(NotificacionDao.class);
			return notificacionDao.obtenerNotificacionesAdministrativo(id_administrativo, pagina);

		} catch (Exception e) {
			SimpleLogger.setError("Error al obtener notificaciones", e);
		}
		return null;
	}
	
	public Integer contarNotificacionesAdministrativo(Integer id_administrativo) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();

			NotificacionDao notificacionDao = (NotificacionDao) daoManager.getDao(NotificacionDao.class);
			return notificacionDao.contarNotificacionesAdministrativo(id_administrativo);

		} catch (Exception e) {
			SimpleLogger.setError("Error al obtener notificaciones", e);
		}
		return null;
	}

	public List<Notificacion> obtenerNotificacionesSinLeerAdministrativo(Integer id_administrativo) {

		try {
			
			DaoManager daoManager = DaoConfig.getDaoManager();

			NotificacionDao notificacionDao = (NotificacionDao) daoManager.getDao(NotificacionDao.class);

			return notificacionDao.obtenerNotificacionesSinLeerAdministrativo(id_administrativo);
			
		} catch (Exception e) {
			SimpleLogger.setError("Error al obtener notificaciones", e);
		}
		return null;
	}

	public Integer contarNotificacionesSinLeerAdministrativo(Integer id_administrativo) {

		try {
			
			DaoManager daoManager = DaoConfig.getDaoManager();

			NotificacionDao notificacionDao = (NotificacionDao) daoManager.getDao(NotificacionDao.class);

			return notificacionDao.contarNotificacionesSinLeerAdministrativo(id_administrativo);
			
		} catch (Exception e) {
			SimpleLogger.setError("Error al obtener notificaciones", e);
		}
		return null;
	}
	
	public Notificacion obtenerNotificacion(Integer id_notificacion){
		
		try {
			
			DaoManager daoManager = DaoConfig.getDaoManager();

			NotificacionDao notificacionDao = (NotificacionDao) daoManager.getDao(NotificacionDao.class);

			return notificacionDao.obtenerNotificacion(id_notificacion);
			
		} catch (Exception e) {
			SimpleLogger.setError("Error al obtener notificaciones", e);
		}
		return null;		
		
	}
	
	public Boolean marcarNotificacionLeida(Integer id_notificacion){
		try {
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			NotificacionDao notificacionDao = (NotificacionDao) daoManager.getDao(NotificacionDao.class);

			return notificacionDao.marcarNotificacionLeida(id_notificacion,Constantes.SI);
			
		} catch (Exception e) {
			SimpleLogger.setError("Error al obtener notificaciones", e);
		}
		return false;	
		
	}
	
	public Integer obtenerPaginaNotificacion(Integer id_notificacion){
		try{

			DaoManager daoManager = DaoConfig.getDaoManager();
			NotificacionDao notificacionDao = (NotificacionDao) daoManager.getDao(NotificacionDao.class);
			
			return notificacionDao.obtenerPaginaNotificacion(id_notificacion);
		} catch (Exception e) {
			SimpleLogger.setError("Error al obtener notificaciones", e);
		}
		return null;
	}
	
	public List<Notificacion> obtenerNotificacionesNuevas(Integer id_administrativo) {

		try {
			
			DaoManager daoManager = DaoConfig.getDaoManager();

			NotificacionDao notificacionDao = (NotificacionDao) daoManager.getDao(NotificacionDao.class);

			return notificacionDao.obtenerNotificacionesNuevas(id_administrativo);
			
		} catch (Exception e) {
			SimpleLogger.setError("Error al obtener notificaciones", e);
		}
		return null;
	}
	
	public Boolean marcarNotificacionNueva(Integer id_notificacion){
		try {
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			NotificacionDao notificacionDao = (NotificacionDao) daoManager.getDao(NotificacionDao.class);

			return notificacionDao.marcarNotificacionNueva(id_notificacion,Constantes.NO);
			
		} catch (Exception e) {
			SimpleLogger.setError("Error al obtener notificaciones", e);
		}
		return false;	
		
	}

	public void enviarCorreoNotificacion(String correo, Notificacion notificacion, SourceResolver resolver) {
		
		
		String receptor = "";
		Map<String, String> archivos = new HashMap<String, String>();
		Map<String, String> parametros = JavaToXML.objectToParameters("notificacion", notificacion);
		archivos.put("#logo#", "images/back/logo1.png");
		//archivos.put("#logo_potencia#", "images/back/Logo_potencia.png");
		try {
			EnviaMails.enviar(correo, receptor, notificacion.getTitulo(), "cocoon:/notificacion/envioNotificacion.email", archivos, parametros, resolver);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public Boolean enviarYGuardarNotificacion(Notificacion notificacion, SourceResolver resolver) {
		
		Administrativo administrativo = AdministrativoServicio.getInstance().obtenerAdministrativoPorID(notificacion.getId_administrativo());
		
		Persona persona = PersonaServicio.getInstance().obtenerPersona(administrativo.getId_persona());
		
		enviarCorreoNotificacion(persona.getCorreo(), notificacion, resolver);
		
		
		return guardarNotificacion(notificacion);
	}
	
}
