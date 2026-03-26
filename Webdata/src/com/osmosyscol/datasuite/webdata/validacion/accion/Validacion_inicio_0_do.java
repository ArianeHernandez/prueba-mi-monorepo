package com.osmosyscol.datasuite.webdata.validacion.accion;

import org.apache.cocoon.environment.Request;

import com.osmosyscol.commons.servicio.AutenticacionServicio;
import com.osmosyscol.commons.validacion.ResultadoValidacion;
import com.osmosyscol.commons.validacion.ValidacionAccion;

public class Validacion_inicio_0_do implements ValidacionAccion {

	public ResultadoValidacion validar(Request request) {

		ResultadoValidacion resultadoValidacion = new ResultadoValidacion();

		if (AutenticacionServicio.getInstance().autorizarUrl(request.getSession(), "/administracion_carga/3b.do").booleanValue()) {
			resultadoValidacion.setPermitido(false);
			resultadoValidacion.setSiguientePagina("/administracion_carga/3b.do");
		}
		return resultadoValidacion;
	}
}
