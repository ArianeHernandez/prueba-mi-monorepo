package com.osmosyscol.datasuite.near.webdata;

import co.htsoft.commons.util.SMessage;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.mein.dtos.RadicacionAutoOficio;
import com.osmosyscol.datasuite.mein.dtos.ResponsePasante;
import com.osmosyscol.datasuite.mein.logica.constantes.Constantes;
import com.osmosyscol.datasuite.mein.servicios.RadicacionAutoOficioServicio;
import com.osmosyscol.datasuite.mein.servicios.rest.client.PasanteAppRestClient;

public class AnularRadicadoPasante implements AccionCarga {

	@Override
	public SMessage ejecutar(Integer id_carga) {
		
		SimpleLogger.setInfo("AnularRadicadoPasante: Se inicia anulación del radicado para la carga " + id_carga);
		
		RadicacionAutoOficio solicitud = RadicacionAutoOficioServicio.getInstance().obtenerRadicacionAutoOficioBase(id_carga);		
		
		ResponsePasante respuesta = PasanteAppRestClient.getInstance().anularRadicado(id_carga);
		
		if (respuesta == null || !Constantes.PASANTE_SUCCESS_CODE.equals(respuesta.getError().getCode())) {
			SimpleLogger.setError("AnularRadicadoPasante: Error en la anulacion del radicado para la carga " + id_carga);
			
			return new SMessage(false, "");
		}
		
		Boolean actualizacion = true;
		
		if (solicitud != null) {
			SimpleLogger.setInfo("AnularRadicadoPasante: Anulando radicado " + solicitud.getNumeroRadicado() + " con fecha " + solicitud.getFechaRadicado() + " a la carga " + id_carga);
			
			solicitud.setNumeroRadicado(null);
			solicitud.setFechaRadicado(null);
			
			actualizacion = RadicacionAutoOficioServicio.getInstance().actualizarRadicacionAutoOficio(solicitud);
		
		}
		
		return new SMessage(actualizacion, "");
		
	}

	@Override
	public Boolean visualizar(Integer id_carga, Integer id_administrativo) {
		return true;
	}

	
	
}
