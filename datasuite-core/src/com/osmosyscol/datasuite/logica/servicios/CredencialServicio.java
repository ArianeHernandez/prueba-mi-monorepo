package com.osmosyscol.datasuite.logica.servicios;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.commons.utils.rsa.RSA;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.Credencial;
import com.osmosyscol.datasuite.persistencia.dao.PersonaDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class CredencialServicio {

	private static CredencialServicio instance;

	private CredencialServicio() {
	}

	public static CredencialServicio getInstance() {
		if (instance == null) {
			instance = new CredencialServicio();
		}
		return instance;
	}

	public Credencial obtenerCredencialPersonaUsuario(Integer id_persona, Integer id_usuario){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			PersonaDao personaDao = (PersonaDao) daoManager.getDao(PersonaDao.class);

			return personaDao.obtenerCredencialPersonaUsuario(id_persona, id_usuario);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
		
	}
	
	public String generarCryptoSobreflex(Integer id_persona, Integer id_usuario){
		
		String crypoSobreflex= null;
		
		Credencial credencial = obtenerCredencialPersonaUsuario(id_persona, id_usuario);
		
		String pass = StringUtils.randomString(5);
		
		Boolean exitoso = false;
		
		if(credencial != null){
			exitoso = com.osmosyscol.commons.servicio.UsuarioServicio.getInstance().guardarUsuario(credencial.getLoginUsuario(),  pass , new int[1]);
		}

		if(exitoso){
			PersonaServicio.getInstance().actualizarEstadoCredencial(Constantes.CREDENCIAL_ESTADO_ACTIVO, credencial.getLogin());
			PersonaServicio.getInstance().colocarCambioClave(credencial.getLogin());
			String cryptoPass = RSA.encripta(pass, RSA.clave);
			crypoSobreflex = cryptoPass;
		}
		
		return crypoSobreflex;
		

	}
	
	
	public Boolean aceptaTerminosCondiciones(Credencial credencial){
		
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			PersonaDao personaDao = (PersonaDao) daoManager.getDao(PersonaDao.class);

			return personaDao.aceptarTerminosCondiciones(credencial);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}
		
		
	}
	
}
