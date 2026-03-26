package com.osmosyscol.datasuite.webdata.validacion.accion;

import org.apache.cocoon.environment.Request;

import com.osmosyscol.commons.mensajeria.EnviaMails;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.commons.validacion.DefaultBaseAccion;
import com.osmosyscol.commons.validacion.ResultadoValidacion;
import com.osmosyscol.commons.validacion.ValidacionAccion;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;

import co.htsoft.commons.lang.P;

public class Validacion_configuracion_80_do implements ValidacionAccion, DefaultBaseAccion {

	public ResultadoValidacion validar(Request request) {

		ResultadoValidacion resultadoValidacion = new ResultadoValidacion();

		Boolean tokenSesionIngresado = (Boolean) request.getSession().getAttribute("tokenSesionIngresado");

		if (tokenSesionIngresado == null) {
			tokenSesionIngresado = false;
		}

		if (tokenSesionIngresado) {
			resultadoValidacion.setPermitido(false);
			resultadoValidacion.setSiguientePagina("/inicio/0.do");
			return resultadoValidacion;
		}

		// ------------------------------- Envia el token de ingreso.. si no lo ha enviado...

		Boolean correoTokenEnviado = (Boolean) request.getSession().getAttribute("correoTokenEnviado");

		if (correoTokenEnviado == null) {
			correoTokenEnviado = false;
		}

		if (!correoTokenEnviado) {

			String tokenIngreso = StringUtils.randomString(6, "1234567890");

			try {
				Integer id_persona = (Integer) request.getSession().getAttribute("id_persona");

				Persona persona = PersonaServicio.getInstance().obtenerPersona(id_persona);

				P.println(persona);
				System.out.println("El token de ingreso es: " + tokenIngreso);

				EnviaMails.enviar(persona.getCorreo(), persona.getCorreo(), "Ingreso App", "plano:El token de Ingreso es <b>" + tokenIngreso + "</b>", null);

				request.getSession().setAttribute("correoTokenEnviado", true);
				request.getSession().setAttribute("tokenIngreso", tokenIngreso);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		// ---------------------------- VAlida que el token ingresado este correcto

		String token = request.getParameter("token");

		if (token != null) {

			if (token.equals(request.getSession().getAttribute("tokenIngreso"))) {

				request.getSession().setAttribute("tokenSesionIngresado", true);

				resultadoValidacion.setPermitido(false);
				resultadoValidacion.setSiguientePagina("/inicio/0.do");
				return resultadoValidacion;

			} else {
				request.setAttribute("errorToken", true);
			}
		}
		
		System.out.println(request.getSession().getAttribute("tokenIngreso"));

		return resultadoValidacion;

	}
}
