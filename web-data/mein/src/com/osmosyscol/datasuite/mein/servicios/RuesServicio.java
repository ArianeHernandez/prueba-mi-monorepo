package com.osmosyscol.datasuite.mein.servicios;

import java.util.HashMap;
import java.util.Map;

import co.htsoft.commons.net.CallPage;

import com.google.gson.Gson;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.mein.domain.CerlIn;
import com.osmosyscol.datasuite.mein.domain.CerlResponse;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class RuesServicio {

	private static RuesServicio instance;

	private static Gson gson = new Gson();

	public static RuesServicio getInstance() {
		if (instance == null) {
			instance = new RuesServicio();
		}
		return instance;
	}

	public CerlResponse obtenerDatosRues(String nit, String nroDoc) {

		CerlResponse cerlResp = null;
		
		try {
			CallPage call = new CallPage();
			CerlIn cerlIn = new CerlIn();
			cerlIn.setNit(nit);
			cerlIn.setTipoDoc("C");
			cerlIn.setNumDoc(nroDoc);
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");

			String endpoint = ParametrosInicio.getProperty("cerl.endpoint");
			SimpleLogger.setDebug("Consultando servicio: " + endpoint);

			if (endpoint == null) {
				SimpleLogger.setError("Variable cerl.endpoint no configurada en el archivo de propiedades de la carpeta DATASUITE");
			}

			SimpleLogger.setInfo("Consulta Servicio RUES: Nit " + nit + ", Documento_replegal " + nroDoc);
			
			String response = call.callPost(endpoint, gson.toJson(cerlIn), headers);
			
			SimpleLogger.setInfo("Respuesta Servicio RUES: Nit " + nit + " - " + response);
			
			cerlResp = gson.fromJson(response, CerlResponse.class);
			
		} catch (Exception e) {
			SimpleLogger.setError("RuesServicio.obtenerDatosRues: Error ", e);
		}
		
		return cerlResp;
	}

}
