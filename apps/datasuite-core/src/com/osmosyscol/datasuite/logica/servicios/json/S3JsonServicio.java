package com.osmosyscol.datasuite.logica.servicios.json;

import java.net.URLDecoder;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.logica.dto.ArchivoAdjunto;
import com.osmosyscol.datasuite.logica.dto.S3SignedUrl;
import com.osmosyscol.datasuite.logica.servicios.ArchivoAdjuntoServicio;
import com.osmosyscol.datasuite.logica.servicios.HorarioServicio;
import com.osmosyscol.datasuite.logica.servicios.S3Servicio;

public class S3JsonServicio implements JsonService {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public String obtenerRutaAdjuntosS3 () {
		return S3Servicio.getInstance().obtenerRutaAdjuntosS3();
	}
	
	public S3SignedUrl obtenerUrlFirmadaUpload (String rutaS3) {
		return S3Servicio.getInstance().obtenerUrlFirmadaUpload(rutaS3);
	}
	
	public S3SignedUrl obtenerUrlFirmadaDownload (String rutaS3) {
		return S3Servicio.getInstance().obtenerUrlFirmadaDownload(rutaS3);
	}
	
	public Boolean insertarAdjunto (ArchivoAdjunto adjunto) {
		adjunto.setFecha_subida(HorarioServicio.getInstance().obtenerFechaActual());
		if (adjunto.getId_persona() == null) {
			Integer id_persona = null;
			try {
				id_persona = (Integer)session.getAttribute("id_persona");
			} catch (Exception e) {
				id_persona = -1;
			}
			adjunto.setId_persona(id_persona);			
		}
		try {
			String descripcion = URLDecoder.decode(adjunto.getDescripcion(),"UTF-8");
			String nombre = URLDecoder.decode(adjunto.getNombre_archivo(),"UTF-8");
			String ruta = URLDecoder.decode(adjunto.getRuta(), "UTF-8");
			
			adjunto.setDescripcion(descripcion);
			adjunto.setNombre_archivo(nombre);
			adjunto.setRuta(ruta);
			
		} catch (Exception e) {
			SimpleLogger.setInfo("Error al decodificar nombre, descripcion y ruta del archivo " + adjunto.getId_archivo_adjunto());
		}
		
		return ArchivoAdjuntoServicio.getInstance().agregarArchivoAdjunto(adjunto);
	}
}
