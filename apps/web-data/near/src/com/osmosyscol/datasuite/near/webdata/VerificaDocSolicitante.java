package com.osmosyscol.datasuite.near.webdata;

import java.util.List;

import co.htsoft.commons.util.SMessage;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.ArchivoAdjunto;
import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.logica.servicios.ArchivoAdjuntoServicio;
import com.osmosyscol.datasuite.mein.dtos.PasanteMLRequest;
import com.osmosyscol.datasuite.mein.dtos.PasanteMLResponse;
import com.osmosyscol.datasuite.mein.logica.constantes.Constantes;
import com.osmosyscol.datasuite.mein.servicios.NotificacionServicio;
import com.osmosyscol.datasuite.mein.servicios.rest.client.PasanteMLRestClient;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.webdata.logica.dto.SolicitudEnrolamiento;
import com.osmosyscol.datasuite.webdata.logica.servicios.SolicitudEnrolamientoServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class VerificaDocSolicitante implements AccionCarga {

	@Override
	public SMessage ejecutar(Integer id_carga) {
		try {
			
			SimpleLogger.setInfo("VerificaDocSolicitante.ejecutar: Iniciar verificacion del adjunto para la solicitud " + id_carga);
			
			SolicitudEnrolamiento solicitud = SolicitudEnrolamientoServicio.getInstance().obtenerSolicitudEnrolamiento(id_carga);
			List<ArchivoAdjunto> adjuntos = ArchivoAdjuntoServicio.getInstance().obtenerArchivosAdjuntosPorCarga(id_carga, false, null);
			
			if (adjuntos == null || adjuntos.size() == 0) {
				SimpleLogger.setError("VerificaDocSolicitante.ejecutar: No se encontraron adjuntos asociados para la solicitud " + id_carga);
				return new SMessage(true, "");
			}
			ArchivoAdjunto adjunto = adjuntos.get(0);
			Integer tipo_archivo = adjunto.getId_tipo_archivo()-10;
			PasanteMLRequest request = new PasanteMLRequest();
			request.setId(tipo_archivo.toString());
			request.setNombreInterno("archivo_" + id_carga + "_" + adjunto.getId_archivo_adjunto() + ".osm");
			request.setExtension(adjunto.getExtension_archivo());
			request.setUrl(ParametrosInicio.getProperty("pasante.endpoint.descargaarchivos") + "?id_archivo_adjunto=" + adjunto.getId_archivo_adjunto());
			
			PasanteMLResponse response = PasanteMLRestClient.getInstance().procesarDocumento(request);
			SimpleLogger.setInfo("response: " + response);
			if (response == null || !Constantes.JSON_RESPONSE_MSG_OK.equals(response.getStatus())) {
				SimpleLogger.setError("VerificaDocSolicitante.ejecutar: Error en la validacion del adjunto para la solicitud " + id_carga);
				NotificacionServicio.getInstance().notificarRechazoRegistro(solicitud);
				return new SMessage(true, "");
			}
			
			String documentoExtraido = "";
			if (Constantes.TIPO_ARCHIVO_CERTIFICADO_EXISTENCIA.equals(adjunto.getId_tipo_archivo())) {
				documentoExtraido = response.getNit() != null ? response.getNit().split("-")[0]: "";
			} else if (Constantes.TIPO_ARCHIVO_CERTIFICADO_MATRICULA_MERCANTIL.equals(adjunto.getId_tipo_archivo())) {
				documentoExtraido = response.getIdDeudorCedula() != null ? response.getIdDeudorCedula().split("-")[0]: "";
			}
			
			String verificacion = "";
			if (Constantes.PASANTE_CAMPO_SI.equals(response.getResultClasificacionTipoDoc()) && solicitud.getNumero_identificacion().equals(documentoExtraido)) {
				verificacion = Constantes.WS_RESPUESTA_EXITOSA;
			} else {
				verificacion = Constantes.WS_RESPUESTA_FALLIDA;
				NotificacionServicio.getInstance().notificarRechazoRegistro(solicitud);
			}
			
			SimpleLogger.setInfo("VerificaDocSolicitante.ejecutar: Resultado verificacion del adjunto: " + verificacion + " para la solicitud " + id_carga);
			
			DS_SqlUtils.update("update $ENROLAMIENTO CLIENTE$ set $ENROLAMIENTO CLIENTE.ESTADO VERIFICACION DOC$ = $S("+ verificacion + ")$ where ID = "+ solicitud.getId());
			
			SimpleLogger.setInfo("VerificaDocSolicitante.ejecutar: Finaliza verificacion del adjunto para la solicitud " + id_carga);
			
			return new SMessage(true, "");
		} catch (Exception e) {
			SimpleLogger.setError("VerificaDocSolicitante.ejecutar: Error ", e);
			return new SMessage(false, "");
		}
		
	}

	@Override
	public Boolean visualizar(Integer id_carga, Integer id_administrativo) {
		return true;
	}
	
	

}
