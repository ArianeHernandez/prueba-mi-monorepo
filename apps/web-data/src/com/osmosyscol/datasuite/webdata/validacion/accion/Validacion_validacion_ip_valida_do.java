package com.osmosyscol.datasuite.webdata.validacion.accion;

import java.util.List;

import org.apache.cocoon.environment.Request;
import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.validacion.ResultadoValidacion;
import com.osmosyscol.commons.validacion.ValidacionAccion;
import com.osmosyscol.datasuite.logica.dto.Ipprivada;
import com.osmosyscol.datasuite.logica.servicios.IpServicio;

public class Validacion_validacion_ip_valida_do implements ValidacionAccion {

	public ResultadoValidacion validar(Request request) {

		ResultadoValidacion resultadoValidacion = new ResultadoValidacion();

		Session session = request.getSession();

		// verifica que existan rangos para ip privadas

		Integer id_usuario = (Integer) session.getAttribute("id_usuario");
		List<Ipprivada> ipprivadas = IpServicio.getInstance().obtenerIPPrivPorUsuario(id_usuario);

		if (ipprivadas.isEmpty()) {

			String ip_cliente = (String) session.getAttribute("REQUEST.REMOTE.ADDR");

			Boolean permitido = IpServicio.getInstance().validarIP(id_usuario, ip_cliente, null);

			session.setAttribute("IP_VALIDA", permitido ? "S" : "N");

			resultadoValidacion.setPermitido(false);
			resultadoValidacion.setSiguientePagina("/inicio/0.do");
		}

		return resultadoValidacion;
	}
}
