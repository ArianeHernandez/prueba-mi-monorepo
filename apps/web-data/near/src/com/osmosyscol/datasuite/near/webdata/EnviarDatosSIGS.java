package com.osmosyscol.datasuite.near.webdata;

import co.htsoft.commons.util.SMessage;

import com.google.gson.Gson;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.mein.dtos.WSData;
import com.osmosyscol.datasuite.mein.logica.constantes.Constantes;
import com.osmosyscol.datasuite.mein.servicios.NotificacionServicio;
import com.osmosyscol.datasuite.mein.servicios.rest.PersonaNaturalJuridica;
import com.osmosyscol.datasuite.mein.servicios.rest.RespuestaRegistrarPersonaNaturalJuridica;
import com.osmosyscol.datasuite.mein.servicios.rest.client.SigsAppRestClient;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.webdata.logica.dto.SolicitudEnrolamiento;
import com.osmosyscol.datasuite.webdata.logica.servicios.SolicitudEnrolamientoServicio;

public class EnviarDatosSIGS implements AccionCarga {

	Gson gson = new Gson();
	
	@Override
	public SMessage ejecutar(Integer id_carga) {
		
		try {

			SolicitudEnrolamiento solicitud = SolicitudEnrolamientoServicio.getInstance().obtenerSolicitudEnrolamiento(id_carga);

			Integer tipoDocSolicitud = solicitud.getTipo_identificacion_obj().getCodigo_numerico_postal();

			PersonaNaturalJuridica personaNaturalJuridica = new PersonaNaturalJuridica();
			personaNaturalJuridica.setIdTipoIdentificacion(tipoDocSolicitud);
			personaNaturalJuridica.setNumeroIdentificacion(new Long(solicitud.getNumero_identificacion()));
			personaNaturalJuridica.setNumeroIdentificacionCaracter(solicitud.getNumero_identificacion());
			personaNaturalJuridica.setCorreoElectronico(solicitud.getCorreo_electronico());
			personaNaturalJuridica.setDireccionNotificacion(solicitud.getDireccion());
			personaNaturalJuridica.setSigla("");
			
			personaNaturalJuridica.setTelefonosNotificacion(""+solicitud.getDatos_representante().getCelular());
			personaNaturalJuridica.setTelefonosResidencia(""+solicitud.getDatos_representante().getCelular());
		    personaNaturalJuridica.setVersionActividadEconomica("");
		    personaNaturalJuridica.setPaginaWeb("");
		    personaNaturalJuridica.setFaxesNotificacion("");
		    personaNaturalJuridica.setActividadEconomicaCIIU("");
		    personaNaturalJuridica.setApartadoAereo("");
		    personaNaturalJuridica.setDireccionResidencia("");
		    personaNaturalJuridica.setSigla(""); 
		    
			personaNaturalJuridica.setRazonSocial(solicitud.getRazon_social());
			personaNaturalJuridica.setIdPaisNotificacion(80); // TODO: modificar por dato extraido de la estructura
			personaNaturalJuridica.setIdDepartamentoNotificacion(solicitud.getDepartamento_obj().getCodigo_departamento());
			personaNaturalJuridica.setIdCiudadNotificacion(solicitud.getMunicipio_obj().getCodigoMunicipioPostal());
			
			WSData consultaSIGS = SigsAppRestClient.getInstance().consultarPersonaNaturalJuridica(tipoDocSolicitud, new Long(solicitud.getNumero_identificacion()), id_carga);
			System.out.println("ConsultaSigs:" + consultaSIGS.getResponse().toString());
			System.out.println("ConsultaSigs:" + consultaSIGS.getRequest().toString());
			Integer estado = null; 
			if(consultaSIGS.getResponse() != null) {
				try {
					RespuestaRegistrarPersonaNaturalJuridica respuesta = gson.fromJson(consultaSIGS.getResponse(), RespuestaRegistrarPersonaNaturalJuridica.class);
					estado = respuesta.getEstado();					
				} catch (Exception e) {
					SimpleLogger.setError("EnviarDatosSIGS: Error al consultar en SIGS " + id_carga, e);
				}
				
			}
			
			WSData respuestaSIGS = null;
			
			if (estado == null) {
				SimpleLogger.setInfo("SIGS Servicio no disponible");
				NotificacionServicio.getInstance().notificarErrorWS(consultaSIGS);
				return new SMessage(true, "");
			} else if (estado == 0) {			    
				respuestaSIGS = SigsAppRestClient.getInstance().actualizarPersonaNaturalJuridica(personaNaturalJuridica, id_carga);
				DS_SqlUtils.update("update $ENROLAMIENTO CLIENTE$ set $ENROLAMIENTO CLIENTE.ESTADO RESPUESTA SIGS$ = $S(" + 
						Constantes.WS_RESPUESTA_EXITOSA + ")$ where idcarga = " + id_carga);
			} else {
				respuestaSIGS = SigsAppRestClient.getInstance().registrarPersonaNaturalJuridica(personaNaturalJuridica, id_carga);
			}
			
			Boolean notificarError = false;
			
			try {
				RespuestaRegistrarPersonaNaturalJuridica respuesta = gson.fromJson(respuestaSIGS.getResponse(), RespuestaRegistrarPersonaNaturalJuridica.class);
				
				if (estado == 0  || (respuesta != null && respuesta.getEstado() != null && respuesta.getEstado() == 0)) {
					
					DS_SqlUtils.update("update $ENROLAMIENTO CLIENTE$ set $ENROLAMIENTO CLIENTE.ESTADO RESPUESTA SIGS$ = $S(" + 
							Constantes.WS_RESPUESTA_EXITOSA + ")$ where idcarga = " + id_carga);
					
					if (estado == 0 && (respuesta != null && respuesta.getEstado() != null && respuesta.getEstado() != 0)) {
						notificarError = true;
					}
					
				} else {
					
					DS_SqlUtils.update("update $ENROLAMIENTO CLIENTE$ set $ENROLAMIENTO CLIENTE.ESTADO RESPUESTA SIGS$ = $S(" + 
							Constantes.WS_RESPUESTA_FALLIDA + ")$ where idcarga = " + id_carga);
					notificarError = true;
				}
				
			} catch (Exception e) {
				SimpleLogger.setError("EnviarDatosSIGS: Error al registrar/actualizar en SIGS " + id_carga, e);
				notificarError = true;
			}
			
			if (notificarError) {
				NotificacionServicio.getInstance().notificarErrorWS(respuestaSIGS);
			}
			
		} catch (Exception e) {
			SimpleLogger.setError("Error al ejecutarSolicitud", e);
	
			DS_SqlUtils.update("update $ENROLAMIENTO CLIENTE$ set $ENROLAMIENTO CLIENTE.ESTADO RESPUESTA SIGS$ = $S(" + 
					Constantes.WS_RESPUESTA_FALLIDA + ")$ where IDCARGA = " + id_carga);
	
		} catch (Throwable e1) {
			e1.printStackTrace();
		}
		
		return new SMessage(true, "");
	}

	@Override
	public Boolean visualizar(Integer id_carga, Integer id_administrativo) {
		return true;
	}

}
