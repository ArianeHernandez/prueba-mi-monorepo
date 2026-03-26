package com.osmosyscol.datasuite.webdata.validacion.accion;

import org.apache.cocoon.environment.Request;
import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.validacion.ResultadoValidacion;
import com.osmosyscol.commons.validacion.ValidacionAccion;
import com.osmosyscol.datasuite.logica.dto.Liberador;
import com.osmosyscol.datasuite.logica.dto.LiberadorTipoProceso;
import com.osmosyscol.datasuite.modelatos.logica.servicios.LiberadorServicio;

public class Validacion_configuracion_5_1_do implements ValidacionAccion {

	public ResultadoValidacion validar(Request request) {

		ResultadoValidacion resultadoValidacion = new ResultadoValidacion();

		Session session = request.getSession();
		Integer idUsuario = (Integer) session.getAttribute("id_usuario");
		String tipoPersona = (String) session.getAttribute("tipo_persona");
		String identificacion = (String) session.getAttribute("identificacion");
		Integer tipoDocumento = (Integer) session.getAttribute("tipo_documento");

		 Liberador liberador = LiberadorServicio.getInstance().obtenerLiberadorPorIdentificacion( idUsuario,  tipoPersona,  identificacion,  tipoDocumento);
		
		 LiberadorTipoProceso liberadorTipoProceso = LiberadorServicio.getInstance().obtenerLiberadorTipoProceso(liberador.getId_liberador(), 1);
		
		
		 request.setAttribute("liberadorTipoProceso", liberadorTipoProceso);

		
		return resultadoValidacion;
	}
}
