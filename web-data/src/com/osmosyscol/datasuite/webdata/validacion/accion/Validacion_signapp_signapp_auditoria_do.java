package com.osmosyscol.datasuite.webdata.validacion.accion;

import org.apache.cocoon.environment.Request;

import com.osmosyscol.commons.validacion.ResultadoValidacion;
import com.osmosyscol.commons.validacion.ValidacionAccion;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class Validacion_signapp_signapp_auditoria_do implements ValidacionAccion {

	@Override
	public ResultadoValidacion validar(Request request) {
		
		ResultadoValidacion resultadoValidacion = new ResultadoValidacion();
		
		String signappAuditoria_endpoint = ParametrosInicio.getProperty("signappAuditoria.endpoint");
		
		if (signappAuditoria_endpoint != null) {
			request.setAttribute("signappAuditoriaEndpoint", signappAuditoria_endpoint);
		}
		
		return resultadoValidacion;
	}

	
}
