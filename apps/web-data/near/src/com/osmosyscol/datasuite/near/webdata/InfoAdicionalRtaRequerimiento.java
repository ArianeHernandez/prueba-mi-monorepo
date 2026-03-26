package com.osmosyscol.datasuite.near.webdata;

import net.sf.pizzacompiler.lang.List.Cons;
import co.htsoft.commons.util.SMessage;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.mein.dtos.RegimenInsolvencia;
import com.osmosyscol.datasuite.mein.dtos.RespuestaRequerimiento;
import com.osmosyscol.datasuite.mein.dtos.SolicitudNearSociedad;
import com.osmosyscol.datasuite.mein.logica.constantes.Constantes;
import com.osmosyscol.datasuite.mein.servicios.RegimenInsolvenciaServicio;
import com.osmosyscol.datasuite.mein.servicios.RespuestaRequerimientoServicio;
import com.osmosyscol.datasuite.mein.servicios.SolicitudNearSociedadServicio;
import com.osmosyscol.datasuite.near.servicios.InfoRadicadoLocalServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class InfoAdicionalRtaRequerimiento implements AccionCarga{

	@Override
	public SMessage ejecutar(Integer id_carga) {
		try {
			
			RespuestaRequerimiento solicitud = RespuestaRequerimientoServicio.getInstance().obtenerRespuestaRequerimientoBase(id_carga);
			
			if (solicitud == null) {
				SimpleLogger.setError("No se encuentra informacion de solicitud para la carga " + id_carga);

				return new SMessage(false, "");
			} else {
				Integer id_carga_padre = solicitud.getNumero_solicitud();
				Integer tipo_seguridad = Integer.parseInt(ParametrosInicio.getProperty("respuesta_requerimiento.apvista_seguridad"));
				Integer tipo_auto_acta = Integer.parseInt(ParametrosInicio.getProperty("respuesta_requerimiento.apvista_tipoautoacta"));
				
				if (Constantes.ESTRUCTURA_NEAR_SOCIEDAD.equals(InfoRadicadoLocalServicio.getInstance().obtenerNombreEstructuraFormatoPorCarga(id_carga_padre))) {
					
					SolicitudNearSociedad solicitud_padre = SolicitudNearSociedadServicio.getInstance().obtenerSolicitudNearSociedadPorCargaBase(id_carga_padre);
					
					Integer tramite = Integer.parseInt(ParametrosInicio.getProperty("respuesta_requerimiento.apvista_tramite_near"));
					Integer serie = Integer.parseInt(ParametrosInicio.getProperty("respuesta_requerimiento.apvista_serie"));
					Integer subserie = Integer.parseInt(ParametrosInicio.getProperty("respuesta_requerimiento.apvista_subserie_near"));
					
					solicitud.setDependencia(solicitud_padre.getDependencia());
					solicitud.setIntendencia_regional(solicitud_padre.getIntendencia_regional());
					solicitud.setMedio_envio(solicitud_padre.getMedio_envio());
					solicitud.setTipo_seguridad(tipo_seguridad);
					solicitud.setTramite(tramite);
					solicitud.setProceso_clase(solicitud_padre.getProcesos_clases());
					solicitud.setTipo_cuaderno(solicitud_padre.getCuaderno());
					solicitud.setTipo_auto_acta(tipo_auto_acta);
					solicitud.setSerie(serie);
					solicitud.setSubserie(subserie);
					
				} else if (Constantes.ESTRUCTURA_REGIMEN_INSOLVENCIA.equals(InfoRadicadoLocalServicio.getInstance().obtenerNombreEstructuraFormatoPorCarga(id_carga_padre))){
					
					RegimenInsolvencia solicitud_padre = RegimenInsolvenciaServicio.getInstance().obtenerRegimenInsolvenciaBase(id_carga_padre);
					
					Integer tramite = null;
					Integer subserie = null; 
					Integer serie = Integer.parseInt(ParametrosInicio.getProperty("respuesta_requerimiento.apvista_serie"));
					if (Constantes.TIPO_SOLICITUD_REORG_ABREVIADA.equals(solicitud.getTipo_solicitud())) {
						tramite = Integer.parseInt(ParametrosInicio.getProperty("respuesta_requerimiento.apvista_tramite_reo_abreviada"));
						subserie = Integer.parseInt(ParametrosInicio.getProperty("respuesta_requerimiento.apvista_subserie_reo_abreviada"));
					} else if (Constantes.TIPO_SOLICITUD_LIQ_SIMPLIFICADA.equals(solicitud.getTipo_solicitud())) {
						tramite = Integer.parseInt(ParametrosInicio.getProperty("respuesta_requerimiento.apvista_tramite_liquidacion"));
						subserie = Integer.parseInt(ParametrosInicio.getProperty("respuesta_requerimiento.apvista_subserie_liq_simplificada"));
					} else if (Constantes.TIPO_SOLICITUD_REORG_ORDINARIA.equals(solicitud.getTipo_solicitud())) {  
						tramite = Integer.parseInt(ParametrosInicio.getProperty("respuesta_requerimiento.apvista_tramite_reo_ordinaria"));
						subserie = Integer.parseInt(ParametrosInicio.getProperty("respuesta_requerimiento.apvista_subserie_reo_ordinaria"));
					} else if (Constantes.TIPO_SOLICITUD_LIQ_JUDICIAL.equals(solicitud.getTipo_solicitud())) {
						tramite = Integer.parseInt(ParametrosInicio.getProperty("respuesta_requerimiento.apvista_tramite_liq_judicial"));
						subserie = Integer.parseInt(ParametrosInicio.getProperty("respuesta_requerimiento.apvista_subserie_liq_judicial"));
					} else if (Constantes.TIPO_SOLICITUD_VAL_JUDICIAL.equals(solicitud.getTipo_solicitud())) {
						tramite = Integer.parseInt(ParametrosInicio.getProperty("respuesta_requerimiento.apvista_tramite_val_judicial"));
						subserie = Integer.parseInt(ParametrosInicio.getProperty("respuesta_requerimiento.apvista_subserie_val_judicial"));
					} else {
						SimpleLogger.setError("El tipo de solicitud no tiene tramite configurado para respuesta de requerimiento. Id: " + id_carga);
						return new SMessage(false, "");
					}
					
					solicitud.setDependencia(solicitud_padre.getDependencia_id());
					solicitud.setIntendencia_regional(solicitud_padre.getIntendencia_regional_id());
					solicitud.setMedio_envio(solicitud_padre.getMedio_envio_id());
					solicitud.setTipo_seguridad(tipo_seguridad);
					solicitud.setTramite(tramite);
					solicitud.setProceso_clase(solicitud_padre.getProcesos_clases_id());
					solicitud.setTipo_cuaderno(solicitud_padre.getCuaderno_id());
					solicitud.setTipo_auto_acta(tipo_auto_acta);
					solicitud.setSerie(serie);
					solicitud.setSubserie(subserie);
				}
				
				Boolean update = RespuestaRequerimientoServicio.getInstance().actualizarRespuestaRequerimiento(solicitud);
				
				if (update) {
					return new SMessage(true, "Ok");
				} else {
					return new SMessage(false, "Error al actualizar la respuesta de requerimiento con idcarga " + solicitud.getIdcarga());
				}
			}
			
		} catch (Exception e) {
			SimpleLogger.setError("Error al completar la solicitud de respuesta de requerimiento:", e);

			return new SMessage(false, "");
		}
		
	}
	@Override
	public Boolean visualizar(Integer id_carga, Integer id_administrativo) {
		return true;
	}

}
