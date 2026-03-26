package com.osmosyscol.datasuite.near.servicios;

import static com.osmosyscol.datasuite.near.utils.ParametrosInicioUtils.$;

import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.htsoft.commons.net.CallPage;

import com.google.gson.Gson;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.rpc.jsonrpc.JSONObject;
import com.osmosyscol.datasuite.logica.dto.ArchivoAdjunto;
import com.osmosyscol.datasuite.logica.servicios.ArchivoAdjuntoServicio;
import com.osmosyscol.datasuite.mein.logica.constantes.Constantes;
import com.osmosyscol.datasuite.near.interop.postal.adjuntos.PostalAdjuntoOut;
import com.osmosyscol.datasuite.near.interop.postal.adjuntos.PostalAdjuntosIn;
import com.osmosyscol.datasuite.near.interop.postal.adjuntos.PostalInfo;
import com.osmosyscol.datasuite.near.interop.postal.adjuntos.WebDataInfo;
import com.osmosyscol.datasuite.near.utils.Either;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class PostalAdjuntosRestClient {

	static final Logger logger = LoggerFactory.getLogger(PostalAdjuntosRestClient.class);

	
	private static final String ACCEPT = "Accept";
	private static final String CONTENT_TYPE = "Content-Type";
	private static final String APPLICATION_JSON = "application/json";
	private static Gson gson = new Gson();
	
	private static PostalAdjuntosRestClient instance;
	
	private PostalAdjuntosRestClient() {
	}
	
	public static PostalAdjuntosRestClient getInstance() {
		if (instance == null) {
			instance = new PostalAdjuntosRestClient();
		}
		return instance;
	}
	
	protected Either<Exception, PostalAdjuntoOut> invocar(PostalAdjuntosIn in) {
		CallPage call = new CallPage();
		
		String id_carga = null;
		if (in.getWebDataInfo() != null) {
			id_carga = in.getWebDataInfo().getIdCarga();
		}
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put(CONTENT_TYPE, APPLICATION_JSON);
		headers.put(ACCEPT, APPLICATION_JSON);
		
		final String endpointPropertyKey = IPostalInteraccion.INTEGRADORPOSTAL_ENDPOINT;
		final Either<Exception, String> endpoint = $(endpointPropertyKey); 
		if( endpoint.isLeft()) {
			logger.error("parametro no configrado {}", endpointPropertyKey );
			return Either.left( endpoint.left() );
		}

		SimpleLogger.setDebug("Consultando servicio: " + endpoint.right());
		String response = "";
		try {
			String content = new JSONObject(gson.toJson(in)).toString();
			logger.info("Solicitud postal adjunto " + id_carga + " - " + content);
			response = call.callPost(endpoint.right(), content, headers);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		logger.info("Respuesta postal adjuntos " + id_carga + " - " + response);
		if (StringUtils.isEmpty(response)) {
			return Either.left(new Exception("Empty response from server"));
		}
		try {
			PostalAdjuntoOut cerlResp = gson.fromJson(response, PostalAdjuntoOut.class);
			return Either.right(cerlResp);
		} catch (Exception e) {
			logger.error("invocacion servicio / Parsing (error) , [key: {}, value: {} ]", endpointPropertyKey, endpoint.right(), e );
			return Either.left( new Exception( "errores parsing: "+  response, e));
			
		}
		
	}

	public Either<Exception, PostalAdjuntoOut> invocar(Map<String, Object> payload) {
		PostalAdjuntosIn in = buildInputPayload(payload);
		return invocar(in);
	}
	
	private PostalAdjuntosIn buildInputPayload(Map<String, Object> payload) {
		String estructura = (String) payload.get(IPostalInteraccion.PARAM_ADJUNTOS_ESTRUCTURA);
		List<ArchivoAdjunto> adjuntos = (List<ArchivoAdjunto>) payload.get(IPostalInteraccion.PARAM_ADJUNTOS_LIST);
		String radicado	= (String) payload.get(IPostalInteraccion.PARAM_ADJUNTOS_RADICADO_POSTAL);
		Integer idCarga =  (Integer) payload.get(IPostalInteraccion.PARAM_ADJUNTOS_CORRELATION_ID1);

		String TIPODOC_PRINCIPAL = ParametrosInicio.getProperty(IPostalInteraccion.INTEGRADORPOSTAL_TIPODOC_PRINCIPAL);
		String TIPODOC_ANEXO = ParametrosInicio.getProperty(IPostalInteraccion.INTEGRADORPOSTAL_TIPODOC_ANEXO);
		
		PostalAdjuntosIn in = new PostalAdjuntosIn();
		String adjuntosMI = String.valueOf(ArchivoAdjuntoServicio.getInstance().contarArchivosAdjuntosPorCarga(idCarga, false, null));
		in.setPostalInfo(new PostalInfo(radicado, "UploadBy", adjuntosMI));
		in.setWebDataInfo(new WebDataInfo(""+idCarga, estructura));

		Collections.reverse(adjuntos); // con el fin de no perder el orden de llegada de documetnos
		
		for (ArchivoAdjunto archivoAdjunto : adjuntos ) {
			String tipoDocumento;
			if( Constantes.TIPO_ARCHIVO_PDF_FORMULARIO.equals(archivoAdjunto.getId_tipo_archivo()) 
					|| Constantes.TIPO_ARCHIVO_PDF_AUTO_OFICIO.equals(archivoAdjunto.getId_tipo_archivo()) ) {
				tipoDocumento = TIPODOC_PRINCIPAL;
			} else {
				tipoDocumento = TIPODOC_ANEXO;
			}
			
			String ruta = ParametrosInicio.getProperty("adjuntos.carpeta") + "/ADJUNTOS/archivo_" + archivoAdjunto.getId_carga() + "_" + archivoAdjunto.getId_archivo_adjunto() + ".osm";
			String rutaS3 = obtenerRutaS3(archivoAdjunto.getRuta());
			
			in.addAdjunto(tipoDocumento, ruta, rutaS3, archivoAdjunto.getExtension_archivo());
		}
		return in;
		
	}
	
	private String obtenerRutaS3 (String ruta) {
		
		return ruta.replace("&", " ")
				.replace("=", " ")
				.replace("'", " ")
				.replace("~", " ");
		
	}
	
	
}
