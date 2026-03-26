package com.osmosyscol.datasuite.near.webdata;

import java.util.Objects;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.mein.dtos.GenerarStickerRequest;
import com.osmosyscol.datasuite.mein.dtos.GenerarStickerServicio;
import com.osmosyscol.datasuite.mein.dtos.RegimenInsolvencia;
import com.osmosyscol.datasuite.mein.dtos.RespuestaRequerimiento;
import com.osmosyscol.datasuite.mein.dtos.SolicitudNearSociedad;
import com.osmosyscol.datasuite.mein.logica.constantes.Constantes;
import com.osmosyscol.datasuite.mein.servicios.RegimenInsolvenciaServicio;
import com.osmosyscol.datasuite.mein.servicios.RespuestaRequerimientoServicio;
import com.osmosyscol.datasuite.mein.servicios.SolicitudNearSociedadServicio;
import com.osmosyscol.datasuite.near.servicios.InfoRadicadoLocalServicio;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

import co.htsoft.commons.util.SMessage;

public class IntegrarSticker implements AccionCarga {

	@Override
	public SMessage ejecutar(Integer id_carga) {
		try {
			String estructura = Objects.toString(InfoRadicadoLocalServicio.getInstance().obtenerNombreEstructuraFormatoPorCarga(id_carga), "");
			
			String numero_proceso = "";
			String numero_radicado = "";
			String tipo_solicitud = "";
			
			// Obtener datos dependiendo del tipo de solicitud
			if( Constantes.ESTRUCTURA_NEAR_SOCIEDAD.equals(estructura)) {
				SolicitudNearSociedad solicitud = SolicitudNearSociedadServicio.getInstance().obtenerSolicitudNearSociedadPorCargaBase(id_carga);
				
				numero_proceso = solicitud.getNumero_proceso();
				numero_radicado = solicitud.getNumero_radicado_postal();
				tipo_solicitud = "near";
				
			} else if (Constantes.ESTRUCTURA_RESPUESTA_REQUERIMIENTO.equals(estructura)) {
				RespuestaRequerimiento solicitud = RespuestaRequerimientoServicio.getInstance().obtenerRespuestaRequerimientoBase(id_carga);
				
				numero_proceso = solicitud.getNumero_proceso();
				numero_radicado = solicitud.getNumero_radicado_postal();
				tipo_solicitud = "rtareq";
				
			} else if (Constantes.ESTRUCTURA_REGIMEN_INSOLVENCIA.equals(estructura)) {
				RegimenInsolvencia solicitud = RegimenInsolvenciaServicio.getInstance().obtenerRegimenInsolvenciaBase(id_carga);
				
				numero_proceso = solicitud.getNumero_proceso();
				numero_radicado = solicitud.getNumero_radicado();
				tipo_solicitud = "regimen";
			}
			
			
			if (numero_proceso != null && numero_radicado != null) {
				
				// Generar sticker e integrar en el PDF
				GenerarStickerRequest request = new GenerarStickerRequest();
				request.setFormatoRequerido(Constantes.GENERACION_STICKER_FORMATO_REQUERIDO);
				request.setNumeroProceso(numero_proceso);
				request.setNumRadicado(numero_radicado);

				SMessage respuesta = GenerarStickerServicio.getInstance().generarStickerDocumento(request, id_carga, tipo_solicitud);
				
				// Actualizar estado de generacion del PDF
				if (Constantes.OPERACION_INTERNA_EXITOSA.equals(respuesta.getMsg())) {
					String query = "update $" + estructura.toLowerCase() + "$ set $" + estructura.toLowerCase() + ".estado generacion sticker$ = $S(" + Constantes.WS_RESPUESTA_EXITOSA + ")$ where idcarga = " + id_carga;
					
					SimpleLogger.setInfo("RegistrarProcesoBpm.ejecutar: Query " + id_carga + " " + query);
					
					DS_SqlUtils.update(query);
				}
				
				return respuesta;
				
			} else {
				SimpleLogger.setError("IntegrarSticker: No se encuentra solicitud para la carga " + id_carga);
				return new SMessage(true, "");
			}
			
		} catch (Exception e) {
			SimpleLogger.setError("Error al generar sticker para la carga " + id_carga, e);
			return new SMessage(true,"");
		}
	}

	@Override
	public Boolean visualizar(Integer id_carga, Integer id_administrativo) {
		
		return null;
	}

}

