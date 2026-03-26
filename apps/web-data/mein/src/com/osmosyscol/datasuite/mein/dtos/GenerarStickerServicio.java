package com.osmosyscol.datasuite.mein.dtos;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.rpc.jsonrpc.JSONObject;
import com.osmosyscol.commons.utils.FileUtils;
import com.osmosyscol.datasuite.bpm.servicios.BpmServicios;
import com.osmosyscol.datasuite.logica.dto.ArchivoAdjunto;
import com.osmosyscol.datasuite.logica.servicios.ArchivoAdjuntoServicio;
import com.osmosyscol.datasuite.logica.servicios.S3Servicio;
import com.osmosyscol.datasuite.mein.logica.constantes.Constantes;
import com.osmosyscol.datasuite.mein.servicios.NotificacionServicio;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

import co.htsoft.commons.net.CallPage;
import co.htsoft.commons.util.SMessage;
public class GenerarStickerServicio {
	
	static final Logger logger = LoggerFactory.getLogger(GenerarStickerServicio.class);
	private static Gson gson = new Gson();
	private static GenerarStickerServicio instance;
	
	private GenerarStickerServicio () {
	}
	
	public static GenerarStickerServicio getInstance () {
		if (instance == null) {
			instance = new GenerarStickerServicio();
		}
		
		return instance;
	}
	
	public WSData generarSticker(GenerarStickerRequest data, Integer id_carga){

		WSData integracion = new WSData();
		integracion.setId_carga(id_carga);
		integracion.setIntegracion(Constantes.WS_INTEGRACION_STICKER);
		integracion.setSistema(Constantes.SSOC_SISTEMA_BPM);
		
		try {
			CallPage call = new CallPage();
			String token = BpmServicios.getInstance().generarToken(integracion);
			
			Map<String, String> headers = setupHeaders();
			headers.put("Authorization", token);
			
			String json_headers = gson.toJson(headers);
			String content_unicode = new JSONObject(gson.toJson(data)).toString();
			logger.info("Request GenerarSticker " + id_carga + " - headers " + json_headers + " - content " + content_unicode);
			integracion.setRequest("Headers: \n " + json_headers + "\n" + "Body: \n " + content_unicode);
			
			String response = call.call(ParametrosInicio.getProperty("sticker.endpoint") + ParametrosInicio.getProperty("bpm.api") + "RadicacionPostal/GenerarSticker", content_unicode, headers, "POST");
			logger.info("Response GenerarSticker " + id_carga + " - " + response);
			integracion.setResponse(response);
			
			return integracion;
			
		} catch (Throwable e) {
			logger.error("Ha ocurrido un error al obtener el sticker para la carga " + id_carga, e);
			String error = ExceptionUtils.getStackTrace(e);
			integracion.setResponse("Error generado \n " + error);
			return integracion; 
		}
	}
	
	public List<Imagen> obtenerImagenSticker(GenerarStickerResponse response, String tipo_solicitud) {
		
		List<Imagen> imagenes = new ArrayList<Imagen>();
		
		String posX = ParametrosInicio.getProperty("sticker." + tipo_solicitud + ".posX");
		String posY = ParametrosInicio.getProperty("sticker." + tipo_solicitud + ".posY");
		String width = ParametrosInicio.getProperty("sticker." + tipo_solicitud + ".width");
		
		if (posX != null && posY != null && width != null) {
			imagenes.add(new Imagen(response.get_buffer(),1,Integer.parseInt(posX),Integer.parseInt(posY),Integer.parseInt(width),null));			
		} else {
			// Default
			imagenes.add(new Imagen(response.get_buffer(),1,800,30,300,null));
		}
		
		return imagenes;
		
	}
	
	public PdfConverterResponse integrarStickerPDF (String ruta, List<Imagen> imagenes) {
		
		try {
			File inp = ArchivoAdjuntoServicio.getInstance().obtenerArchivo(ruta);
			String origenB64 = FileUtils.encodeBase64(inp);
			Origen origen = new Origen(origenB64);
			
			Map<String, String> headers = new HashMap<>();

			headers.put("Content-Type", "application/json");
			
			PdfConverterRequest req = new PdfConverterRequest(origen,imagenes);
			
			String response = new CallPage().callHttps(ParametrosInicio.getProperty("officetools.endpoint")+"/api/PDFconverter", gson.toJson(req), headers, "POST");
			//PdfConverterResponse pdfconvertido = new CallPage().callJSON(ParametrosInicio.getProperty("officetools.endpoint")+"/api/PDFconverter", req,PdfConverterResponse.class );
			if (S3Servicio.getInstance().esAlmacenamientoS3()) inp.delete();
			return gson.fromJson(response, PdfConverterResponse.class);
		} catch (Throwable e) {
			SimpleLogger.setError("GenerarStickerServicio.integrarStickerPDF: Error ", e);
			return null;
		}
		
	}
	
	public SMessage generarStickerDocumento (GenerarStickerRequest request, Integer id_carga, String tipo_solicitud) {
		WSData integracion = GenerarStickerServicio.getInstance().generarSticker(request, id_carga);
		
		GenerarStickerResponse response = null;
		
		try {
			response = gson.fromJson(integracion.getResponse(), GenerarStickerResponse.class);
			
			if (response == null || response.get_buffer() == null) {
				SimpleLogger.setError("generarStickerDocumento: Error al obtener el sticker para la solicitud " + id_carga);
				NotificacionServicio.getInstance().notificarErrorWS(integracion);
				return new SMessage(true, "Operacion no Exitosa");
			}
						
		} catch (Exception e) {
			SimpleLogger.setError("generarStickerDocumento: Error al validar respuesta de Generacion de Sticker " + id_carga, e);
			NotificacionServicio.getInstance().notificarErrorWS(integracion);
			return new SMessage(true, "Operacion no Exitosa");
		}
		
		List<Imagen> imagenes = GenerarStickerServicio.getInstance().obtenerImagenSticker(response, tipo_solicitud);
		
		if (imagenes == null || imagenes.size() < 1) {
			SimpleLogger.setError("Error al crear objeto imagen sticker para la solicitud " + id_carga);
			return new SMessage(true, "Operacion no Exitosa");
		}
		
		List<ArchivoAdjunto> adjuntos = ArchivoAdjuntoServicio.getInstance().obtenerArchivosAdjuntosPorCargaTipo(id_carga, Constantes.TIPO_ARCHIVO_PDF_FORMULARIO);
		if(adjuntos == null || adjuntos.size() < 1){
			SimpleLogger.setError("No se encuentra el archivo para agregar Sticker para la solicitud " + id_carga);
			return new SMessage(true, "Operacion no Exitosa");
		}
		
		PdfConverterResponse pdf = GenerarStickerServicio.getInstance().integrarStickerPDF(adjuntos.get(0).getRuta(), imagenes);
		
		if (pdf == null || pdf.getPdf_base64() == null) {
			SimpleLogger.setError("Error generando el PDF con sticker para la solicitud " + id_carga);
			return new SMessage(true, "Operacion no Exitosa");
		}
		File file = FileUtils.toFile(pdf.getPdf_base64());
		
		Boolean actualizacion = ArchivoAdjuntoServicio.getInstance().actualizarDocumento(adjuntos.get(0), file);
		
		if (actualizacion) {
			SimpleLogger.setInfo("Se genera correctamente el documento con Sticker para la solicitud " + id_carga);
			return new SMessage(true, "Operacion Exitosa");
		} else {
			SimpleLogger.setError("Error reemplazando el PDF con sticker para la solicitud " + id_carga);
			return new SMessage(true, "Operacion no Exitosa");
		}
	}
	
	public GenerarStickerResponse generarStickerNear(Integer idcarga){
		String sql = "select $SOLICITUD NEAR SOCIEDAD.NUMERO RADICADO POSTAL$ numRadicado, $SOLICITUD NEAR SOCIEDAD.NUMERO PROCESO$ numeroProceso from  $SOLICITUD NEAR SOCIEDAD$ where idcarga = $$";
		GenerarStickerRequest solicitud = DS_SqlUtils.queryForObject(GenerarStickerRequest.class, sql,idcarga);
		solicitud.setFormatoRequerido(1);
		WSData integracion = generarSticker(solicitud, idcarga);
		GenerarStickerResponse sticker = gson.fromJson(integracion.getResponse(), GenerarStickerResponse.class);
		return sticker;
	}
	
	public GenerarStickerResponse generarStickerInsolvencia(Integer idcarga){
		String sql = "select $REGIMEN DE INSOLVENCIA.NUMERO DE RADICADO$ numRadicado, $REGIMEN DE INSOLVENCIA.NUMERO DE PROCESO$ numeroProceso from  $REGIMEN DE INSOLVENCIA$ where idcarga = $$";
		GenerarStickerRequest solicitud = DS_SqlUtils.queryForObject(GenerarStickerRequest.class, sql,idcarga);
		solicitud.setFormatoRequerido(1);
		WSData integracion = generarSticker(solicitud, idcarga);
		GenerarStickerResponse sticker = gson.fromJson(integracion.getResponse(), GenerarStickerResponse.class);
		return sticker;
	}
	
	private static Map<String, String> setupHeaders(){
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		return headers;
	}

}
