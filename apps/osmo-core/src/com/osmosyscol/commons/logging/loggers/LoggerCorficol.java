package com.osmosyscol.commons.logging.loggers;

import java.net.URL;
import java.util.Date;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.logging.dto.LogDto;
import com.osmosyscol.commons.logging.dto.LogDtoG3A;
import com.osmosyscol.commons.logging.dto.RegistroLogG3A;
import com.osmosyscol.commons.logging.loggers.ws.WSLogPortBindingStub;
import com.osmosyscol.commons.servicio.AutenticacionServicio;
import com.osmosyscol.commons.utils.StringUtils;


//TODO: este logger no es de g3a
public class LoggerCorficol implements Logger {

	public void crearLogger() {
		SimpleLogger.setDebug("Creando nuevo logger de G3A");
	}

	public void guardarLog(LogDto dto) {
		if (dto == null) {
			return;
		}

		//TODO transformar el dto
		
		LogDtoG3A dtoG3a = new LogDtoG3A();
		try {

			URL endpointURL = new URL(AutenticacionServicio.getEndpoint() + "/WSLog");
			WSLogPortBindingStub stub = new WSLogPortBindingStub(endpointURL, null);
			
			RegistroLogG3A regLogG3A = crearRegistroLogG3A(dto);
			
			dtoG3a.setRegistro(regLogG3A);

			stub.crearLog(dtoG3a);
		} catch (Exception e) {
			SimpleLogger.setError("Error llamando al servicio de log", e);
		}

	}

	private RegistroLogG3A crearRegistroLogG3A(LogDto dto) {
		System.out.println("==== llenando el crear registro log g3a. ["+dto+"]");
		RegistroLogG3A rpta = new RegistroLogG3A();
		
		rpta.setCanal(StringUtils.defaultString(dto.getCanal()));
		rpta.setCostoTransaccion((dto.getCostoTransaccion()== null)? 0.0 : dto.getCostoTransaccion());
		rpta.setDireccionIp(StringUtils.defaultString(dto.getIp()));
		rpta.setDescripcion(StringUtils.defaultString(dto.getDescripcion()));
		rpta.setEstado(StringUtils.defaultString(dto.getEstado()));
		rpta.setFechaRegistro((dto.getFecha()== null)? new Date() : dto.getFecha());
		rpta.setFunctionalidad(StringUtils.defaultString(dto.getServicio()));
		rpta.setIdCliente(validarIdCliente(dto.getLogin()));
		rpta.setIdConsecutivo(StringUtils.defaultString(dto.getIdConsecutivo()));
		rpta.setIdFilial(StringUtils.defaultString(dto.getIdFilial()));
		rpta.setIdOperacion(StringUtils.defaultString(dto.getIdOperacion()));
		rpta.setIdSistema((dto.getIdSistema()==null)? 0 : dto.getIdSistema() );
		rpta.setIdUsuario(StringUtils.defaultString(validarIdUsuario(dto.getIdUsuario())));
		rpta.setModulo(StringUtils.defaultString(dto.getModulo()));
		rpta.setNumeroCuenta(StringUtils.defaultString(dto.getNumeroCuenta()));
		rpta.setTipoTransaccion(StringUtils.defaultString(dto.getTipoTransaccion()));
		rpta.setUrl(StringUtils.defaultString(dto.getUrl()));
		
		
		return rpta;
		
	}

	private String validarIdUsuario(String login) {
		
		if(login != null)
		{
			if(login.contains("|")){
				return login.substring(login.indexOf("|")+1);
			}
			
		}
		return "";
	}

	private String validarIdCliente(String login) {
		if(login != null)
		{
			if(login.contains("|")){
				return login.substring(0, login.indexOf("|"));
			}
			
		}
		return "";
	}
}
