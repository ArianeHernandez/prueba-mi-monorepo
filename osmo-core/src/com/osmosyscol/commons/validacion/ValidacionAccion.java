package com.osmosyscol.commons.validacion;

import org.apache.cocoon.environment.Request;

public interface ValidacionAccion {

	public ResultadoValidacion validar(Request request);
	
}
