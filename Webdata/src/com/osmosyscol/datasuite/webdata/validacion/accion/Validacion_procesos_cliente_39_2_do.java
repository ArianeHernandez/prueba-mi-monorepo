package com.osmosyscol.datasuite.webdata.validacion.accion;

import java.util.List;

import org.apache.cocoon.environment.Request;

import com.osmosyscol.commons.utils.CocoonUtils;
import com.osmosyscol.commons.validacion.ResultadoValidacion;
import com.osmosyscol.commons.validacion.ValidacionAccion;
import com.osmosyscol.datasuite.logica.dto.Proceso;
import com.osmosyscol.datasuite.logica.dto.Revision;
import com.osmosyscol.datasuite.modelatos.logica.dto.Formato;
import com.osmosyscol.datasuite.modelatos.logica.servicios.FormatoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ProcesoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.RevisionServicio;

public class Validacion_procesos_cliente_39_2_do implements ValidacionAccion {

	public ResultadoValidacion validar(Request request) {

		ResultadoValidacion resultadoValidacion = new ResultadoValidacion();

		// ------

		CocoonUtils.osmSessionRequest(request, "id_proceso", "-1");

		Integer id_proceso = Integer.parseInt((String) request.getSession().getAttribute("var.id_proceso"));

		// ---------------------

		Proceso proceso = ProcesoServicio.getInstance().obtenerProceso(id_proceso);
		
		request.setAttribute("proceso", proceso);
		
		List<Formato> formatos = FormatoServicio.getInstance().obtenerFormatosPorGrupoFormato(proceso.getId_grupoformato());
		
		request.setAttribute("formatos", formatos);
		
		List<Revision> revisiones = RevisionServicio.getInstance().obtenerRevisiones(id_proceso);
		
		request.setAttribute("revisiones", revisiones);
		
		// ---------------------

		return resultadoValidacion;
	}

}
