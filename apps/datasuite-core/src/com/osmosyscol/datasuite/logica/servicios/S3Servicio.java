package com.osmosyscol.datasuite.logica.servicios;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tuckey.noclash.gzipfilter.org.apache.commons.lang.BooleanUtils;

import co.htsoft.commons.file.FileUtils;
import co.htsoft.commons.net.CallPage;

import com.google.gson.Gson;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.ArchivoAdjunto;
import com.osmosyscol.datasuite.logica.dto.S3SignedUrl;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class S3Servicio {

	private static S3Servicio instance;
	
	private S3Servicio () {
	}
	
	public static S3Servicio getInstance () {
		if (instance == null) {
			instance = new S3Servicio();
		}
		
		return instance;
	}
	
	public String obtenerRutaAdjuntosS3 () {
		String bucket = ParametrosInicio.getProperty("S3.bucketName");
		String carpeta_adjuntos = ParametrosInicio.getProperty("S3.adjuntosCarpeta");
		
		if (bucket != null && carpeta_adjuntos != null) {
			return "s3://" + bucket + carpeta_adjuntos;
		} else {
			return null;
		}
	}
	
	public S3SignedUrl obtenerUrlFirmadaUpload (String rutaS3) {
		
		try {
			
			String url = ParametrosInicio.getProperty("S3.signUrlUpload");
			
			if (url != null) {
				
				url = url + rutaS3;
				CallPage call = new CallPage();
				
				String response = call.callget(url, null);
				S3SignedUrl signedUrl = new Gson().fromJson(response, S3SignedUrl.class);
				
				return signedUrl;
			}
			
		} catch (Exception e) {
			SimpleLogger.setError("S3Servicio.obtenerUrlFirmadaUpload: Error ", e);
		}
		return null;
		
	}
	
	public S3SignedUrl obtenerUrlFirmadaDownload (String rutaS3) {
		
		try {
			
			String url = ParametrosInicio.getProperty("S3.signUrlDownload");
			
			if (url != null) {
				
				url = url + rutaS3;
				CallPage call = new CallPage();
				
				String response = call.callget(url, null);
				S3SignedUrl signedUrl = new Gson().fromJson(response, S3SignedUrl.class);
				
				return signedUrl;
			}
			
		} catch (Exception e) {
			SimpleLogger.setError("S3Servicio.obtenerUrlFirmadaDownload: Error ", e);
		}
		return null;
		
	}
	
	public File descargarArchivo(String rutaS3) {
		
		try {
			String rutaS3Encoded = encodeString(rutaS3);
			S3SignedUrl url = obtenerUrlFirmadaDownload(rutaS3Encoded);
			Map<String, String> headers = new HashMap<String, String>();
			
			System.out.println("S3 Encoded " + rutaS3Encoded);
			System.out.println("Url firmada " + url.getSignedUrl());
			
			byte[] bytes = FileUtils.getFile(url.getSignedUrl(), headers);
			
			String ruta = ParametrosInicio.getProperty("file.carpeta");
			return FileUtils.toFile(bytes, ruta + "/file_" + System.currentTimeMillis() + "_" + Thread.currentThread().getId() + "_" + Math.round(1000d * Math.random()) + ".tmp" );
			
		} catch (Exception e) {
			SimpleLogger.setError("S3Servicio.descargarArchivo: error", e);
		}
		
		return null;
	}
	
	public byte[] descargarBytesArchivo(String rutaS3) {
		
		try {
			String rutaS3Encoded = encodeString(rutaS3);
			S3SignedUrl url = obtenerUrlFirmadaDownload(rutaS3Encoded);
			Map<String, String> headers = new HashMap<String, String>();
			
			System.out.println("S3 Encoded " + rutaS3Encoded);
			System.out.println("Url firmada " + url.getSignedUrl());
			
			return FileUtils.getFile(url.getSignedUrl(), headers);
			
		} catch (Exception e) {
			SimpleLogger.setError("S3Servicio.descargarBytesArchivo: error", e);
		}
		
		return null;
	}
	
	public Boolean cargarArchivo(File f, ArchivoAdjunto adjunto) {
		try {
			String encoded = encodeString(adjunto.getRuta());
			S3SignedUrl su = obtenerUrlFirmadaUpload(encoded);
			
			Map<String, List<Object>> params = new HashMap<String, List<Object>>();
			List<Object> data = new ArrayList<Object>();
			data.add(f);
			params.put("data", data);
			Map<String, String> headers = new HashMap<String, String>();
			
			CallPage cp = new CallPage();
			cp.callPost(su.getSignedUrl(), params, headers, true, "PUT");
			
			return true;
		} catch (Exception e) {
			SimpleLogger.setError("S3Servicio.cargarArchivo: Error ", e);
			return false;
		}
	}
	
	public String encodeString (String s) {
		try {
			return URLEncoder.encode(s, "UTF-8")
		        .replaceAll("\\+", "%20")
		        .replaceAll("\\%21", "!")
		        .replaceAll("\\%27", "'")
		        .replaceAll("\\%28", "(")
		        .replaceAll("\\%29", ")")
		        .replaceAll("\\%7E", "~");
		} catch (Exception e) {
			return s;
		}
	}
	
	public Boolean esAlmacenamientoS3 () {
		
		String S3 = ConfiguracionServicio.getInstance().obtenerValorByEtiqueta("ALMACENAMIENTO_ARCHIVOS_S3");
		return BooleanUtils.toBoolean(S3);
		
	}
	
}
