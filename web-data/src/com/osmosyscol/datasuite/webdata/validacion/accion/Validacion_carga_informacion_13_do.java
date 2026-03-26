package com.osmosyscol.datasuite.webdata.validacion.accion;

import org.apache.cocoon.environment.Request;

import com.osmosyscol.commons.validacion.ResultadoValidacion;
import com.osmosyscol.commons.validacion.ValidacionAccion;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class Validacion_carga_informacion_13_do implements ValidacionAccion {

	public ResultadoValidacion validar(Request request) {

		ResultadoValidacion resultadoValidacion = new ResultadoValidacion();

		Boolean confListNeg = Boolean.parseBoolean(ParametrosInicio.getProperty("DisponibleListadoNegocios"));
		
		resultadoValidacion.setPermitido(confListNeg);
		resultadoValidacion.setSiguientePagina("/carga_informacion/13.1.do");

		return resultadoValidacion;

	}

}
