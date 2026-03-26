package com.osmosyscol.datasuite.near.webdata;

import java.io.File;
import java.util.List;

import co.htsoft.commons.util.SMessage;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.FileUtils;
import com.osmosyscol.datasuite.logica.dto.ArchivoAdjunto;
import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.logica.servicios.ArchivoAdjuntoServicio;
import com.osmosyscol.datasuite.logica.servicios.S3Servicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class PostalPrepararArchivos implements AccionCarga {

	@Override
	public SMessage ejecutar(Integer id_carga) {

		try {
			String base = ParametrosInicio.getProperty("adjuntos.carpeta") + "/ADJUNTOS/";
			List<ArchivoAdjunto> adjuntos = ArchivoAdjuntoServicio.getInstance().obtenerArchivosAdjuntosPorCarga(id_carga, false, null);
			
			for (ArchivoAdjunto adjunto: adjuntos) {
				String ruta = base + "archivo_" + adjunto.getId_carga() + "_" + adjunto.getId_archivo_adjunto() + ".osm";
				
				File output = new File(ruta);
				if(!output.isFile()) { 
				    byte[] downloaded = S3Servicio.getInstance().descargarBytesArchivo(adjunto.getRuta());
				    FileUtils.toFile(downloaded, ruta);
				}
			}
			
			return new SMessage(true, "Ok");
		} catch (Exception e) {
			SimpleLogger.setError("PostalPrepararArchivos: Error ", e);
			return new SMessage(false, "Error");
		}
		
	}

	@Override
	public Boolean visualizar(Integer id_carga, Integer id_administrativo) {
		return true;
	}

}
