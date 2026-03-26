package com.osmosyscol.datasuite.near.webdata;

import java.util.Objects;
import java.util.regex.Pattern;

import co.htsoft.commons.util.SMessage;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.bpm.servicios.BpmServicios;
import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.logica.servicios.ConfiguracionServicio;
import com.osmosyscol.datasuite.mein.dtos.RegimenInsolvencia;
import com.osmosyscol.datasuite.mein.dtos.WSData;
import com.osmosyscol.datasuite.mein.dtos.SolicitudNearSociedad;
import com.osmosyscol.datasuite.mein.logica.constantes.Constantes;
import com.osmosyscol.datasuite.mein.servicios.NotificacionServicio;
import com.osmosyscol.datasuite.mein.servicios.RegimenInsolvenciaServicio;
import com.osmosyscol.datasuite.mein.servicios.SolicitudNearSociedadServicio;
import com.osmosyscol.datasuite.near.servicios.InfoRadicadoLocalServicio;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

public class GenerarNumeroProcesoBpm implements AccionCarga{
	
	@Override
	public SMessage ejecutar(Integer id_carga){
		
		try {
			String estructura = Objects.toString(InfoRadicadoLocalServicio.getInstance().obtenerNombreEstructuraFormatoPorCarga(id_carga), "");
			String numero_proceso = null;
			String campo_numero_proceso = "";
			
			// Obtener datos dependiendo del tipo de solicitud
			if( Constantes.ESTRUCTURA_NEAR_SOCIEDAD.equals(estructura)) {
				SolicitudNearSociedad solicitud = SolicitudNearSociedadServicio.getInstance().obtenerSolicitudNearSociedadPorCargaBase(id_carga);
				
				if (solicitud == null) {
					SimpleLogger.setError("No se encuentra una solicitud NEAR para la carga " + id_carga);
					return new SMessage(true, "");
				}
				
				numero_proceso = solicitud.getNumero_proceso();
				campo_numero_proceso = "numero proceso";
				
			} else if (Constantes.ESTRUCTURA_REGIMEN_INSOLVENCIA.equals(estructura)) {
				RegimenInsolvencia solicitud = RegimenInsolvenciaServicio.getInstance().obtenerRegimenInsolvenciaBase(id_carga);
				
				if (solicitud == null) {
					SimpleLogger.setError("No se encuentra un registro en la estructura Regimen de Insolvencia para la carga " + id_carga);
					return new SMessage(true, "");
				}
				
				numero_proceso = solicitud.getNumero_proceso();
				campo_numero_proceso = "numero de proceso";
			}
			
			if (numero_proceso == null) {
				
				String regexp = ConfiguracionServicio.getInstance().obtenerValorByEtiqueta("VALIDACION_NUMERO_PROCESO");
				
				WSData integracion = BpmServicios.getInstance().getNumeroPorDependencia(460);
				integracion.setId_carga(id_carga);
				
				SimpleLogger.setInfo("GenerarNumeroProceso Solicitud " + id_carga + " Response: "+ integracion.getResponse());
				
				if (integracion != null && integracion.getResponse() != null && Pattern.matches(regexp, integracion.getResponse())) {
					
					String query = "update $" + estructura.toLowerCase() + "$ set $" + estructura.toLowerCase() + "." + campo_numero_proceso + "$ = $S(" + integracion.getResponse() + ")$ where idcarga = " + id_carga;
					
					SimpleLogger.setDebug("GenerarNumeroProcesoBpm Query: "+ query);
					
					DS_SqlUtils.update(query);
				} else {

					NotificacionServicio.getInstance().notificarErrorWS(integracion);
				}
				
			}
			
			return new SMessage(true, "");
		}catch (Exception e) {
			SimpleLogger.setError("Error al generar nuevo numero de proceso en BPM: Solicitud " + id_carga, e);

			return new SMessage(true, "");
		}
	}
	
	@Override
	public Boolean visualizar(Integer id_carga, Integer id_administrativo) {
		return true;
	}	

}
