package com.osmosyscol.datasuite.mein.servicios;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.mein.interfaces.SolicitudMI;
import com.osmosyscol.datasuite.mein.logica.constantes.Constantes;
import com.osmosyscol.datasuite.near.servicios.InfoRadicadoLocalServicio;

public class SolicitudMIServicio {

	private static SolicitudMIServicio instance;
	
	private SolicitudMIServicio () {
	}
	
	public static SolicitudMIServicio getInstance () {
		if (instance == null) {
			instance = new SolicitudMIServicio();
		}
		
		return instance;
	}
	
	public SolicitudMI obtenerSolicitudPorCarga (Integer id_carga) {
		
		SolicitudMI solicitud = null;

		try {
			String estructura = InfoRadicadoLocalServicio.getInstance().obtenerNombreEstructuraFormatoPorCarga(id_carga);
			
			// Obtener datos dependiendo del tipo de solicitud
			if( Constantes.ESTRUCTURA_NEAR_SOCIEDAD.equals(estructura)) {
				solicitud = SolicitudNearSociedadServicio.getInstance().obtenerSolicitudNearSociedadPorCargaBase(id_carga);
				
			} else if (Constantes.ESTRUCTURA_REGIMEN_INSOLVENCIA.equals(estructura)) {
				solicitud = RegimenInsolvenciaServicio.getInstance().obtenerRegimenInsolvenciaBase(id_carga);
				
			} else if (Constantes.ESTRUCTURA_RESPUESTA_REQUERIMIENTO.equals(estructura)) {
				solicitud = RespuestaRequerimientoServicio.getInstance().obtenerRespuestaRequerimientoBase(id_carga);
			}
		}catch (Exception e) {
			SimpleLogger.setError("Error al obtener Solicitud: " + id_carga, e);
		}
		
		return solicitud;
		
	}
	
}
