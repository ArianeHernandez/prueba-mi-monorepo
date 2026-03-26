package com.osmosyscol.datasuite.mein.servicios.rest.client;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.htsoft.commons.net.CallPage;

import com.google.gson.Gson;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.rpc.jsonrpc.JSONObject;
import com.osmosyscol.commons.utils.FileUtils;
import com.osmosyscol.datasuite.logica.servicios.ArchivoAdjuntoServicio;
import com.osmosyscol.datasuite.logica.servicios.S3Servicio;
import com.osmosyscol.datasuite.mein.dtos.FieldData;
import com.osmosyscol.datasuite.mein.dtos.Imagen;
import com.osmosyscol.datasuite.mein.dtos.Origen;
import com.osmosyscol.datasuite.mein.dtos.PdfConverterRequest;
import com.osmosyscol.datasuite.mein.dtos.PdfConverterResponse;
import com.osmosyscol.datasuite.mein.dtos.TokenMapping;
import com.osmosyscol.datasuite.mein.dtos.TokenReplaceRequest;
import com.osmosyscol.datasuite.mein.dtos.TokenReplaceResponse;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class OfficeToolsRestClient {

	private final static Gson gson = new Gson(); 
	static final Logger logger = LoggerFactory.getLogger(OfficeToolsRestClient.class);
	private static OfficeToolsRestClient instance;
	
	private OfficeToolsRestClient(){}
	
	public static OfficeToolsRestClient getInstance() {
		
		if (instance == null) {
			instance = new OfficeToolsRestClient();
		}
		
		return instance;
		
	}

	public TokenReplaceResponse applyTokens (String ruta, TokenMapping[] tokenMapping) {
		
		try {
			File inp = ArchivoAdjuntoServicio.getInstance().obtenerArchivo(ruta);
			String base64 = FileUtils.encodeBase64(inp);
			
			TokenReplaceResponse response = applyTokensB64 (base64, tokenMapping); 
			
			return response;
			
		} catch (Throwable e) {
			SimpleLogger.setError("GenerarStickerServicio.applyTokens: Error ", e);
			return null;
		}
		
	}
	
	public TokenReplaceResponse applyTokensB64 (String base64, TokenMapping[] tokenMapping) {
		try {
			Map<String, String> headers = new HashMap<>();
			headers.put("Content-Type", "application/json");
			
			TokenReplaceRequest req = new TokenReplaceRequest();
			req.setBase64(base64);
			req.setData(new FieldData[]{});
			req.setTokenMapping(tokenMapping);
			
			String request = gson.toJson(req);
			String content_unicode = new JSONObject(request).toString();
			String response = new CallPage().callHttps(ParametrosInicio.getProperty("officetools.endpoint")+"/rest/tokens", content_unicode, headers, "POST");

			return gson.fromJson(response, TokenReplaceResponse.class);
			
		} catch (Throwable e) {
			SimpleLogger.setError("GenerarStickerServicio.applyTokensB64: Error ", e);
			return null;
		}
	}
	
	public PdfConverterResponse pdfConverter (String ruta, List<Imagen> imagenes) {
		
		try {
			File inp = ArchivoAdjuntoServicio.getInstance().obtenerArchivo(ruta);
			String origenB64 = FileUtils.encodeBase64(inp);
			
			PdfConverterResponse response = pdfConverterB64(origenB64, imagenes);
			
			if (S3Servicio.getInstance().esAlmacenamientoS3()) inp.delete();
			
			return response;
		} catch (Throwable e) {
			SimpleLogger.setError("OfficeToolsRestClient.pdfConverter: Error ", e);
			return null;
		}
		
	}
	
	public PdfConverterResponse pdfConverterB64 (String base64, List<Imagen> imagenes) {
		
		try {
			Origen origen = new Origen(base64);
			
			Map<String, String> headers = new HashMap<>();

			headers.put("Content-Type", "application/json");
			
			PdfConverterRequest req = new PdfConverterRequest(origen,imagenes);
			
			String request = gson.toJson(req);
			
			String response = new CallPage().callHttps(ParametrosInicio.getProperty("officetools.endpoint")+"/api/PDFconverter", request, headers, "POST");

			return gson.fromJson(response, PdfConverterResponse.class);
			
		} catch (Throwable e) {
			SimpleLogger.setError("OfficeToolsRestClient.pdfConverterB64: Error ", e);
			return null;
		}

		
		
	}
	
}
