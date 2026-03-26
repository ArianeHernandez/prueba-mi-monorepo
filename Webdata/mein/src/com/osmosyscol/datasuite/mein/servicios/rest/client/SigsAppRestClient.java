package com.osmosyscol.datasuite.mein.servicios.rest.client;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jxl.common.Logger;

import org.apache.commons.lang3.exception.ExceptionUtils;

import co.htsoft.commons.net.CallPage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.osmosyscol.commons.rpc.jsonrpc.JSONObject;
import com.osmosyscol.datasuite.mein.acciones.DateSigsSerializer;
import com.osmosyscol.datasuite.mein.dtos.WSData;
import com.osmosyscol.datasuite.mein.logica.constantes.Constantes;
import com.osmosyscol.datasuite.mein.servicios.rest.EntradaRegistrarPersonaNaturalJuridica;
import com.osmosyscol.datasuite.mein.servicios.rest.PersonaNaturalJuridica;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;


public class SigsAppRestClient {
	private static SigsAppRestClient sigsAppRestClient;
//	Gson gson = new Gson();
	Gson gson = new GsonBuilder()
    .registerTypeAdapter(Date.class, new DateSigsSerializer())
    .create();
	
	final static Logger logger = Logger.getLogger(SigsAppRestClient.class);
	
	private static String token;
	private static long expiracion;
		
	private SigsAppRestClient() {
	}

	public static SigsAppRestClient getInstance() {

		if (sigsAppRestClient == null) {
			sigsAppRestClient = new SigsAppRestClient();
		}
		return sigsAppRestClient;
	}
	
	private String getTokenOAuth() throws Throwable {
        if (token == null || System.currentTimeMillis() >= expiracion) {
            token = generarToken();
            expiracion = System.currentTimeMillis() + (3600 * 1000) - (60 * 1000); 
        }
        return token;
    }
	
	public String generarToken() throws Throwable {
		String clientId = ParametrosInicio.getProperty("siggs_app.user");
		String clientSecret = ParametrosInicio.getProperty("siggs_app.password");
		String endpoint = ParametrosInicio.getProperty("siggs_app.endpoint") + "/oauth/token";

		try {
			String credentials = clientId + ":" + clientSecret;
			String encoded = new String(org.apache.commons.codec.binary.Base64.encodeBase64(credentials.getBytes("UTF-8")), "UTF-8");
			String authHeader = "Basic " + encoded;

			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Authorization", authHeader);
			headers.put("Content-Type", "application/x-www-form-urlencoded");

			String body = "grant_type=client_credentials";

			CallPage cp = new CallPage();
			String respuesta = cp.call(endpoint, body, headers, "POST");

			if (respuesta != null) {
				Map parsed = gson.fromJson(respuesta, Map.class);
				return (String) parsed.get("access_token");
			} else {
				logger.warn("No se recibio respuesta del servidor de autenticacion.");
			}
		} catch (Exception e) {
			logger.error("Error al generar el token OAuth: " + ExceptionUtils.getStackTrace(e));
		}

		return null;
	}

	public WSData registrarPersonaNaturalJuridica(PersonaNaturalJuridica personaNaturalJuridica, Integer id_carga) {
	    WSData integracion = new WSData();
	    integracion.setIntegracion(Constantes.WS_INTEGRACION_SIGS_REGISTRO);
	    integracion.setSistema(Constantes.SSOC_SISTEMA_SIGS);
	    integracion.setId_carga(id_carga);

	    try {
	        personaNaturalJuridica.setFechaIngreso(new Date()); 
	        System.out.println("[" + id_carga + "] PersonaNaturalJuridica entrada: " + gson.toJson(personaNaturalJuridica));

	        String url = ParametrosInicio.getProperty("siggs_app.endpoint") + ParametrosInicio.getProperty("siggs_app.ruta") + "/registrarPersonaNaturalJuridica/";
	        System.out.println("[" + id_carga + "] URL completa: " + url);

	        Map<String, String> headers = new HashMap<>();
	        headers.put("Content-Type", "application/json");
	        headers.put("Authorization", "Bearer " + getTokenOAuth());
	        headers.put("aplicacion", "genesys");
	        headers.put("usuario", "yquinttana");
	        headers.put("ipUsuario", "localhost");

	        System.out.println("[" + id_carga + "] Headers: " + headers);

	        String jsonBody = gson.toJson(personaNaturalJuridica);
	        System.out.println("[" + id_carga + "] Body enviado al servicio SIGS: " + jsonBody);
	        integracion.setRequest("Body: \n" + jsonBody);

	        CallPage cp = new CallPage();
	        String rtaServicioSigs = cp.call(url, jsonBody, headers, "POST");

	        System.out.println("[" + id_carga + "] Respuesta del servicio SIGS: " + rtaServicioSigs);
	        integracion.setResponse(rtaServicioSigs);

	    } catch (Throwable e) {
	        System.out.println("[" + id_carga + "] Error en registrarPersonaNaturalJuridica: " + e.getMessage());
	        e.printStackTrace();
	        String error = ExceptionUtils.getStackTrace(e);
	        integracion.setResponse("Error generado \n" + error);
	    }

	    return integracion;
	}

	public WSData consultarPersonaNaturalJuridica(Integer tipoIdentificacion, Long numeroIdentificacion, Integer id_carga) throws Throwable{
		
		WSData integracion = new WSData();
		integracion.setIntegracion(Constantes.WS_INTEGRACION_SIGS_CONSULTA);
		integracion.setSistema(Constantes.SSOC_SISTEMA_SIGS);
		integracion.setId_carga(id_carga);
		try {
			String url = ParametrosInicio.getProperty("siggs_app.endpoint" ) + ParametrosInicio.getProperty("siggs_app.ruta" )+ "/consultarSociedadSupervisada/"
							+ tipoIdentificacion + "/" + numeroIdentificacion;
			integracion.setRequest("Endpoint: " + url);
			
			logger.info("SIGS Consulta URL: " + id_carga + " - " + url);
			
			CallPage cp = new CallPage();
			
			Map<String,String> headers = new HashMap<>();
			headers.put("Content-Type", "application/json");
			headers.put("Authorization", "Bearer " + getTokenOAuth());
			headers.put("aplicacion", "genesys");
			headers.put("usuario", "yquintana");
			headers.put("ipUsuario", "190.1.1.0");
			
			String rtaServicioSigs = cp.callget(url, headers);
			
			logger.info("SIGS Consulta Response: " + id_carga + " - " + rtaServicioSigs);
			integracion.setResponse(rtaServicioSigs);
				
			
		} catch (Exception e) {
			logger.error("SigsAppRestClient.consultarPersonaNaturalJuridica: Error ", e);
			String error = ExceptionUtils.getStackTrace(e);
			integracion.setResponse("Error generado \n" + error);
		}
		return integracion;
	}
		
	public WSData actualizarPersonaNaturalJuridica(PersonaNaturalJuridica personaNaturalJuridica, Integer id_carga){
		WSData integracion = new WSData();
		integracion.setIntegracion(Constantes.WS_INTEGRACION_SIGS_ACTUALIZACION);
		integracion.setSistema(Constantes.SSOC_SISTEMA_SIGS);
		integracion.setId_carga(id_carga);
		try {
			
			String url = ParametrosInicio.getProperty("siggs_app.endpoint") + ParametrosInicio.getProperty("siggs_app.ruta" )+ "/actualizarPersonaNaturalJuridica/";
			logger.info("SIGS Consulta URL: " + id_carga + " - " + url);
			
			CallPage cp = new CallPage();
			
			Map<String,String> headers = new HashMap<>();
			headers.put("Content-Type", "application/json");
			headers.put("Authorization", "Bearer " + getTokenOAuth());
			headers.put("aplicacion", "genesys");
			headers.put("usuario", "yquintana");
			headers.put("ipUsuario", "localhost");
			
			String content_unicode = gson.toJson(personaNaturalJuridica);
			logger.info("SIGS Actualizar Request: " + id_carga + " - " + content_unicode);
			integracion.setRequest("Body: \n " + content_unicode);
			
			String rtaServicioSigs = cp.call(url, content_unicode, headers, "POST");

			logger.info("SIGS Actualizar Response: " + id_carga + " - " + rtaServicioSigs);
			integracion.setResponse(rtaServicioSigs);
			
						
		} catch (Throwable e) {
			logger.error("SigsAppRestClient.actualizarPersonaNaturalJuridica: Error " + id_carga, e);
			String error = ExceptionUtils.getStackTrace(e);
			integracion.setResponse("Error generado \n " + error);
		}	
		return integracion;
	}
		
}
