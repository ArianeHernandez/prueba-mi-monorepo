package com.osmosyscol.datasuite.webdata.logica.acciones;

import co.htsoft.commons.util.SMessage;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.mein.servicios.SignAppServicio;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.webdata.logica.dto.SolicitudEnrolamiento;
import com.osmosyscol.datasuite.webdata.logica.servicios.SolicitudEnrolamientoServicio;

public class EjecutarEnrolamientoSignapp implements AccionCarga {

	private static final String CONSULTA_TIPO_DOC = "select $TIPO DE DOCUMENTO.CODIGO$ from $TIPO DE DOCUMENTO$ where id = ";
	
	public SMessage ejecutar(Integer id_carga) {
		
		SolicitudEnrolamiento solicitud = null;
		try {
			
			solicitud = SolicitudEnrolamientoServicio.getInstance().obtenerSolicitudEnrolamiento(id_carga);

			SimpleLogger.setInfo("SOLICITUD" + solicitud);

			Integer tipo_identificacion = DS_SqlUtils.queryForObject(Integer.class,CONSULTA_TIPO_DOC + solicitud.getTipo_identificacion());
			solicitud.setTipo_identificacion(tipo_identificacion);
			

			Integer tipo_identificacionRep = DS_SqlUtils.queryForObject(Integer.class, CONSULTA_TIPO_DOC + solicitud.getDatos_representante().getTipo_identificacion());

			solicitud.getDatos_representante().setTipoDocCPSuite(tipo_identificacionRep);

			SimpleLogger.setDebug("Registrando carga en signapp " + id_carga);

			SMessage rtaSignApp = SignAppServicio.getInstance().registrarUsuario(solicitud);

			SimpleLogger.setDebug("Respuesta signapp " + rtaSignApp.getMsg());
			if (!rtaSignApp.getValid()) {
				DS_SqlUtils.update("update $ENROLAMIENTO CLIENTE$ set $ENROLAMIENTO CLIENTE.ESTADO OPERACION$ = $S(" + rtaSignApp.getMsg() + ")$ where ID = " + solicitud.getId());
			}

			return new SMessage(true, "");

		} catch (Exception e) {
			SimpleLogger.setError("Error al ejecutarSolicitud", e);
			if (solicitud != null) {
				DS_SqlUtils.update("update $ENROLAMIENTO CLIENTE$ set $ENROLAMIENTO CLIENTE.ESTADO OPERACION$ = $S(" + e.getMessage() + ")$ where ID = " + solicitud.getId());
			}

			return new SMessage(false, "");
		}

	}


	public Boolean visualizar(Integer id_carga, Integer id_administrativo) {
		return true;
	}

}
