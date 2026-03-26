package com.osmosyscol.datasuite.webdata.validacion.accion;

import java.util.List;

import org.apache.cocoon.environment.Request;

import com.osmosyscol.commons.validacion.ResultadoValidacion;
import com.osmosyscol.commons.validacion.ValidacionAccion;
import com.osmosyscol.datasuite.logica.dto.Proceso;
import com.osmosyscol.datasuite.logica.dto.TipoProceso;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ProcesoServicio;

public class Validacion_crear_proceso_10_1_do implements ValidacionAccion {

	public ResultadoValidacion validar(Request request) {

		ResultadoValidacion resultadoValidacion = new ResultadoValidacion();

		// ------

		Integer id_negocio = (Integer) request.getSession().getAttribute("id_negocio");
		Integer id_usuario = (Integer) request.getSession().getAttribute("id_usuario");
		String nombre_usuario = (String) request.getSession().getAttribute("nombre_usuario");

		List<Proceso> lista_procesos = null;
		List<TipoProceso> tipoProceso = null;

		if (id_usuario != null) {

			request.getSession().setAttribute("id_usuario_seleccionado", id_usuario);
			request.getSession().setAttribute("nombre_usuario_seleccionado", nombre_usuario);
			lista_procesos = ProcesoServicio.getInstance().listarProcesos(id_usuario, id_negocio);
			tipoProceso = PersonaServicio.getInstance().obtenerTiposProcesosPorIdUsuario(id_usuario);
		}

		
		
		
		// ---------------------
		
		request.setAttribute("lista_procesos", lista_procesos);
		request.setAttribute("tipoProceso", tipoProceso);
		

		
		// ---------------------

		return resultadoValidacion;
	}

}
