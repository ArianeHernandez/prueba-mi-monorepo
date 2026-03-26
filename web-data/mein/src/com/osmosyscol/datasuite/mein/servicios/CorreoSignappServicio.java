package com.osmosyscol.datasuite.mein.servicios;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;

import co.htsoft.commons.file.FileUtils;
import co.htsoft.commons.lang.P;
import co.htsoft.commons.net.CallPage;
import co.htsoft.commons.util.SMessage;

import com.google.gson.Gson;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.mensajeria.EnviaMails;
import com.osmosyscol.commons.utils.JavaToXML;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.ArchivoAdjunto;
import com.osmosyscol.datasuite.logica.dto.NotificacionSociedad;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.servicios.ArchivoAdjuntoServicio;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;
import com.osmosyscol.datasuite.mein.domain.SignAppRegistroIn;
import com.osmosyscol.datasuite.mein.domain.SignAppRegistroOut;
import com.osmosyscol.datasuite.mein.dtos.SolicitudNearSociedad;
import com.osmosyscol.datasuite.mein.dtos.UsoToken;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.webdata.logica.dto.DatosRepresentante;
import com.osmosyscol.datasuite.webdata.logica.dto.SolicitudEnrolamiento;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class CorreoSignappServicio {

	private static CorreoSignappServicio instance = new CorreoSignappServicio();
	private static CallPage CALLPAGE = new CallPage();

	
	private static Map<Integer, String> MAPEO_TIPO_DOCUMENTO = new HashMap<Integer, String>();
	static{
		MAPEO_TIPO_DOCUMENTO.put(1, "CC");
		MAPEO_TIPO_DOCUMENTO.put(2, "TI");
		MAPEO_TIPO_DOCUMENTO.put(3, "CE");
	}	
	private CorreoSignappServicio(){
	}
	
	public static CorreoSignappServicio getInstance() {
		return instance;
	}

	
	
	public SMessage EnviarCorreo(String id_carga) {
		try {
			
			String correoElectronico = DS_SqlUtils.queryForObject(String.class, "select ec.$enrolamiento cliente.correo electronico$ from $enrolamiento cliente$ ec where ec.idcarga ="+id_carga);
			
			
			SimpleLogger.setDebug("Enviando correo de rechazo enrolamiento signapp flujo alterno a "+correoElectronico);
							
			NotificacionSociedad notificacion = new NotificacionSociedad();
			notificacion.setTitulo("Rechazo Signapp");
			Map<String, String> parametros = JavaToXML.objectToParameters(
					"notificacion", notificacion);
			Map<String, String> archivos = new HashMap<String, String>();
			archivos.put("#logo#", "images/back/logo1.png");
			//archivos.put("#logo_potencia#", "images/back/Logo_potencia.png");
			
			EnviaMails.enviar(correoElectronico, correoElectronico, "Notificación Recibido",
					"notificacion/envioNotificacionSignapp.xsl.email",
					archivos, parametros);
				
			return new SMessage(true, "No se encuentra configurado el endpoint para validar token.");
			
		} catch (Exception e) {
			SimpleLogger.setError("Error enviando el correo de rechazo enrolamiento signapp flujo alterno " + e);
			return new SMessage(false, "");
		}
	}
	
}
