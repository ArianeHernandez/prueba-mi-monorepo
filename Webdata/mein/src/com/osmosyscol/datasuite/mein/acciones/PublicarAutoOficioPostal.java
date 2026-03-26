package com.osmosyscol.datasuite.mein.acciones;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.BooleanUtils;

import co.htsoft.commons.util.SMessage;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.FileUtils;
import com.osmosyscol.datasuite.logica.dto.ArchivoAdjunto;
import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.logica.servicios.ArchivoAdjuntoServicio;
import com.osmosyscol.datasuite.logica.servicios.ConfiguracionServicio;
import com.osmosyscol.datasuite.logica.servicios.S3Servicio;
import com.osmosyscol.datasuite.mein.dtos.RadicacionAutoOficio;
import com.osmosyscol.datasuite.mein.logica.constantes.Constantes;
import com.osmosyscol.datasuite.mein.servicios.RadicacionAutoOficioServicio;
import com.osmosyscol.datasuite.near.interop.postal.adjuntos.PostalAdjuntoOut;
import com.osmosyscol.datasuite.near.servicios.IPostalInteraccion;
import com.osmosyscol.datasuite.near.servicios.InfoRadicadoLocalServicio;
import com.osmosyscol.datasuite.near.servicios.PostalAdjuntosRestClient;
import com.osmosyscol.datasuite.near.utils.Either;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class PublicarAutoOficioPostal implements AccionCarga {

	@Override
	public SMessage ejecutar(Integer id_carga) {
		
		SMessage respuesta = new SMessage(false, "");
		
		try {
		
			String estructura = InfoRadicadoLocalServicio.getInstance().obtenerNombreEstructuraFormatoPorCarga(id_carga);
			
			String numero_radicado = null;
			RadicacionAutoOficio radicacion = RadicacionAutoOficioServicio.getInstance().obtenerRadicacionAutoOficioBase(id_carga);
			
			if (radicacion != null) {
				numero_radicado = radicacion.getNumeroRadicado();
			}
			
			Collection<ArchivoAdjunto> adjuntos = ArchivoAdjuntoServicio.getInstance().obtenerArchivosAdjuntosPorCargaTipo(id_carga, Constantes.TIPO_ARCHIVO_PDF_AUTO_OFICIO);
			List<ArchivoAdjunto> adjuntosList = (List<ArchivoAdjunto>) adjuntos;
			ArchivoAdjunto adjunto = adjuntosList.get(0);
			
			if (S3Servicio.getInstance().esAlmacenamientoS3() && adjunto.getRuta().startsWith("s3")) {
				String base = ParametrosInicio.getProperty("adjuntos.carpeta") + "/ADJUNTOS/";
				
				String ruta = base + "archivo_" + adjunto.getId_carga() + "_" + adjunto.getId_archivo_adjunto() + ".osm";
						
				File output = new File(ruta);
				if(!output.isFile()) { 
				    File downloaded = S3Servicio.getInstance().descargarArchivo(adjunto.getRuta());
				    FileUtils.copy(downloaded, output);
				    downloaded.delete();
				}
			}
			
			Map<String, Object> payload = new HashMap<>();
			payload.put(IPostalInteraccion.PARAM_ADJUNTOS_CORRELATION_ID1, id_carga);
			payload.put(IPostalInteraccion.PARAM_ADJUNTOS_ESTRUCTURA, estructura);
			payload.put(IPostalInteraccion.PARAM_ADJUNTOS_LIST, adjuntos);
			payload.put(IPostalInteraccion.PARAM_ADJUNTOS_RADICADO_POSTAL, numero_radicado);
			
			Either<Exception, PostalAdjuntoOut> response = PostalAdjuntosRestClient.getInstance().invocar(payload);
			
			if(response.isLeft()) {
				SimpleLogger.setError("Error al enviar el PDF Auto Oficio a Postal ", response.left());
			} else {
				RadicacionAutoOficioServicio.getInstance().actualizarTipoArchivoDocumentoSalida(id_carga);
				respuesta.setValid(true);
			}
			
			
		} catch (Exception e) {
			SimpleLogger.setError("Error en operacion interna PublicAutoOficioPostal para la carga " + id_carga, e);
		}
		
		return respuesta;
	}

	@Override
	public Boolean visualizar(Integer id_carga, Integer id_administrativo) {
		return true;
	}

}
