package com.osmosyscol.datasuite.near.webdata;

import org.datacontract.schemas._2004._07.InteropSuper_Modelos.Documento;

import co.htsoft.commons.util.SMessage;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.logica.servicios.ArchivoAdjuntoServicio;
import com.osmosyscol.datasuite.mein.interfaces.SolicitudMI;
import com.osmosyscol.datasuite.mein.servicios.SolicitudMIServicio;
import com.osmosyscol.datasuite.near.servicios.radica.LadoRemoto2;

public class PostalVerificarArchivos implements AccionCarga {

	@Override
	public SMessage ejecutar(Integer id_carga) {
		
		SimpleLogger.setInfo("PostalVerificarArchivos: Inicia verificacion de documentos para la carga " + id_carga);
		
		SolicitudMI solicitud = SolicitudMIServicio.getInstance().obtenerSolicitudPorCarga(id_carga);
		
		if (solicitud == null) {
			SimpleLogger.setError("PostalVerificarArchivos: No se encuentra una solicitud asociada a la carga " + id_carga);
			return new SMessage(false, "");
			}
		if (solicitud != null) {
			
			Documento[] documentosPostal = LadoRemoto2.getInstance().getDocumentsFromPostal(id_carga, solicitud.obtenerNumeroRadicado());
			
			Long adjuntosMI = ArchivoAdjuntoServicio.getInstance().contarArchivosAdjuntosPorCarga(id_carga, false, null);
			
			Boolean comparacion = adjuntosMI.intValue() <= documentosPostal.length;
			
			SimpleLogger.setInfo("PostalVerificarArchivos" + id_carga + ": Comparando archivos MI " + adjuntosMI + " Postal " + documentosPostal.length + " - Resultado " + comparacion);
			
			return new SMessage(comparacion, "");
		}
		
		@SuppressWarnings("unused")
		Documento[] documentosPostal = LadoRemoto2.getInstance().getDocumentsFromPostal(id_carga, solicitud.obtenerNumeroRadicado());
		
		if (documentosPostal == null) {
			SimpleLogger.setError("PostalVerificarArchivos: Ocurrio un error en la consulta de archivos en Postal para la carga " 
					+ id_carga + ", radicado " + solicitud.obtenerNumeroRadicado());
			return new SMessage(false, "");
		}
		
		Long adjuntosMI = ArchivoAdjuntoServicio.getInstance().contarArchivosAdjuntosPorCarga(id_carga, false, null);
		
		Boolean comparacion = adjuntosMI.intValue() <= documentosPostal.length;
		
		SimpleLogger.setInfo("PostalVerificarArchivos " + id_carga + ": Comparando archivos MI " + adjuntosMI + " Postal " + documentosPostal.length + " - Resultado " + comparacion);
		
		return new SMessage(comparacion, "");
			
	}

	@Override
	public Boolean visualizar(Integer id_carga, Integer id_administrativo) {
		return true;
	}
	
	
	

}
