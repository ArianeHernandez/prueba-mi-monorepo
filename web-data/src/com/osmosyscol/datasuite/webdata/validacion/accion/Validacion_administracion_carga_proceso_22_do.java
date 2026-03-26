package com.osmosyscol.datasuite.webdata.validacion.accion;

import org.apache.cocoon.environment.Request;
import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.validacion.ResultadoValidacion;
import com.osmosyscol.commons.validacion.ValidacionAccion;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;

public class Validacion_administracion_carga_proceso_22_do implements ValidacionAccion {

	public ResultadoValidacion validar(Request request) {

		ResultadoValidacion resultadoValidacion = new ResultadoValidacion();

		Session session = request.getSession();

		Integer id_persona = (Integer) session.getAttribute("id_persona");

		Boolean rta = PersonaServicio.getInstance().esPersonaRolActiva(id_persona, Constantes.ROL_ADMINISTRATIVO );
		
		resultadoValidacion.setPermitido(rta);
		
		resultadoValidacion.setSiguientePagina("/inicio/0.do?error=1");
		
		return resultadoValidacion;
	}
}
