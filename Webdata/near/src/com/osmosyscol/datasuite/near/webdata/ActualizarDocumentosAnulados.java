package com.osmosyscol.datasuite.near.webdata;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.htsoft.commons.util.SMessage;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.ArchivoAdjunto;
import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.logica.servicios.ArchivoAdjuntoServicio;
import com.osmosyscol.datasuite.mein.logica.constantes.Constantes;

public class ActualizarDocumentosAnulados implements AccionCarga {

	@Override
	public SMessage ejecutar(Integer id_carga) {
		
		List<Integer> tipos_archivo = new ArrayList<>();
		tipos_archivo.add(Constantes.TIPO_ARCHIVO_ADMISION);
		tipos_archivo.add(Constantes.TIPO_ARCHIVO_RECHAZO);
		tipos_archivo.add(Constantes.TIPO_ARCHIVO_BORRADOR_FORMULARIO);
		
		for (Integer tipo_archivo: tipos_archivo) {
			
			List<ArchivoAdjunto> documentos = ArchivoAdjuntoServicio.getInstance().obtenerArchivosAdjuntosPorCargaTipo(id_carga, tipo_archivo);
			
			if (documentos != null && documentos.size() > 0) {
				
				Date fecha = new Date();
				SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				
				
				ArchivoAdjunto adjunto = documentos.get(0);
				adjunto.setId_tipo_archivo(Constantes.TIPO_ARCHIVO_DOCUMENTO_ANULADO);
				adjunto.setInterno("S");
				
				if (Constantes.TIPO_ARCHIVO_BORRADOR_FORMULARIO.equals(tipo_archivo)) {
					adjunto.setNombre_archivo("Borrador Auto Oficio - Anulado [" + formato.format(fecha) + "]");					
				} else if (Constantes.TIPO_ARCHIVO_ADMISION.equals(tipo_archivo)){
					adjunto.setNombre_archivo("Auto de admisión - Anulado [" + formato.format(fecha) + "]");
				} else if (Constantes.TIPO_ARCHIVO_RECHAZO.equals(tipo_archivo)) {
					adjunto.setNombre_archivo("Auto de rechazo - Anulado [" + formato.format(fecha) + "]");
				}
				
				ArchivoAdjuntoServicio.getInstance().actualizarArchivoAdjunto(adjunto);
				
				SimpleLogger.setInfo("ActualizarDocumentosAnulados: Se realiza la actualización del documento anulado " + adjunto.getId_archivo_adjunto() + " para la carga " + id_carga);
				
			} else {
				SimpleLogger.setInfo("ActualizarDocumentosAnulados: No se encuentran documentos tipo " + tipo_archivo + " para anular para la carga " + id_carga);
			}
						
		}
		
		return new SMessage(true, "");
	}

	@Override
	public Boolean visualizar(Integer id_carga, Integer id_administrativo) {
		return true;
	}

	
	
}
