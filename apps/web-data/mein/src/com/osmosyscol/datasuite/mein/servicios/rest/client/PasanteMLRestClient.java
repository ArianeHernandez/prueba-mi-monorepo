package com.osmosyscol.datasuite.mein.servicios.rest.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import co.htsoft.commons.net.CallPage;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.osmosyscol.datasuite.mein.dtos.JSONResponse;
import com.osmosyscol.datasuite.mein.dtos.PasanteMLRequest;
import com.osmosyscol.datasuite.mein.dtos.PasanteMLResponse;
import com.osmosyscol.datasuite.mein.logica.constantes.Constantes;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class PasanteMLRestClient {

	private static PasanteMLRestClient pasanteMLRestClient;
	private static Gson gson = new Gson();
	final static Logger logger = Logger.getLogger(PasanteMLRestClient.class);
	
	private PasanteMLRestClient() {}

	public static PasanteMLRestClient getInstance() {

		if (pasanteMLRestClient == null) {
			pasanteMLRestClient = new PasanteMLRestClient();
		}
		return pasanteMLRestClient;
	}
	
	public Boolean validarConexion () {
		try {
			
			CallPage call = new CallPage();
			
			String url = ParametrosInicio.getProperty("pasanteML.endpoint");
			String endpoint = url + "/check";
			
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			
			String responseJson = call.callget(endpoint, headers);
			
			JSONResponse response = gson.fromJson(responseJson, JSONResponse.class);
			
			return Constantes.JSON_RESPONSE_MSG_OK.equals(response.getData().get("status"));
			
		} catch (Exception e) {
			logger.error("PasanteMLRestClient.validarConexion: Error ", e);
			return false;
		}
	}
	
	public PasanteMLResponse procesarDocumento (PasanteMLRequest request) {
		try {
			
			CallPage call = new CallPage();
			call.setTimeout(300000);
			
			String url = ParametrosInicio.getProperty("pasanteML.endpoint");
			String endpoint = url + "/documentos/extraccion";
			
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			
			String requestJson = gson.toJson(request);
			
			logger.info("PasanteMLRestClient.procesarDocumento: Request " + requestJson);
			
			String responseJson = call.callPost(endpoint, requestJson, headers);
			
			logger.info("PasanteMLRestClient.procesarDocumento: Response " + responseJson);
			
			PasanteMLResponse salida = gson.fromJson(responseJson, PasanteMLResponse.class);
			
			return salida;
			
		} catch (JsonSyntaxException e) {
			logger.error("PasanteMLRestClient.procesarDocumento: Error ", e);
			return null;
		}
	}
	
	
}
