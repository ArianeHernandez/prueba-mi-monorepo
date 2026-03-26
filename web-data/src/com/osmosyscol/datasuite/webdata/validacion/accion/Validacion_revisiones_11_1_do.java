package com.osmosyscol.datasuite.webdata.validacion.accion;

import java.util.List;

import org.apache.cocoon.environment.Request;

import com.osmosyscol.commons.validacion.ResultadoValidacion;
import com.osmosyscol.commons.validacion.ValidacionAccion;
import com.osmosyscol.datasuite.logica.dto.Proceso;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ProcesoServicio;

public class Validacion_revisiones_11_1_do implements ValidacionAccion {

	public ResultadoValidacion validar(Request request) {

		ResultadoValidacion resultadoValidacion = new ResultadoValidacion();

		// ------

		Integer id_negocio = (Integer) request.getSession().getAttribute("id_negocio");
		Integer id_usuario = (Integer) request.getSession().getAttribute("id_usuario");

		List<Proceso> lista_procesos = null;

		if (id_usuario != null) {

			lista_procesos = ProcesoServicio.getInstance().listarProcesos(id_usuario, id_negocio);
			List<Proceso> lista_procesos2 = ProcesoServicio.getInstance().listarProcesos(null, null);
			lista_procesos.addAll(lista_procesos2);
		}

		request.setAttribute("lista_procesos", lista_procesos);

		// ---------------------

		return resultadoValidacion;
	}

}
