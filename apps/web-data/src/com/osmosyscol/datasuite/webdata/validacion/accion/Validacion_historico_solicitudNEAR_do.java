package com.osmosyscol.datasuite.webdata.validacion.accion;

import java.util.List;

import org.apache.cocoon.environment.Request;

import com.osmosyscol.commons.validacion.ResultadoValidacion;
import com.osmosyscol.commons.validacion.ValidacionAccion;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValorLista;
import com.osmosyscol.datasuite.near.servicios.ReporteSolicitudNEARServicio;
import com.osmosyscol.datasuite.utils.ValidacionUtils;

public class Validacion_historico_solicitudNEAR_do implements ValidacionAccion {

	public ResultadoValidacion validar(Request request) {
		
		ResultadoValidacion resultadoValidacion = new ResultadoValidacion();
		
		List<ValorLista> tipoSolicitante = ValidacionUtils.obtenerValoresLista("Tipo Solicitante");
		List<ValorLista> categoria = ValidacionUtils.obtenerValoresLista("Categoria");
		List<ValorLista> estado = ReporteSolicitudNEARServicio.getInstance().obtenerInstancias();
		List<ValorLista> intendencia = ValidacionUtils.obtenerValoresLista("Intendencias");
		
		request.setAttribute("listaTipoSolicitante", tipoSolicitante);
		
		request.setAttribute("listaCategoria", categoria);
		
		request.setAttribute("listaEstados", estado);
		
		request.setAttribute("listaIntendencias", intendencia);
		
		return resultadoValidacion;
	}

}
