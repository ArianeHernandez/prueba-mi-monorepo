package com.osmosyscol.datasuite.modelatos.validacion.accion;

import org.apache.cocoon.environment.Request;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.AutenticacionServicio;
import com.osmosyscol.commons.validacion.ResultadoValidacion;
import com.osmosyscol.commons.validacion.ValidacionAccion;
import com.osmosyscol.datasuite.logica.servicios.NegocioServicio;

public class Validacion implements ValidacionAccion {

	public ResultadoValidacion validar(Request request) {

		ResultadoValidacion resultadoValidacion = new ResultadoValidacion();

		try {
			if (request.getSession(false).getAttribute("persona") == null) {
				resultadoValidacion.setPermitido(false);
				resultadoValidacion.setSiguientePagina(AutenticacionServicio.PAGINA_INICIO);
				resultadoValidacion.setMensaje("Se han presentado Inconsistencias con la informacion de la session. La session se ha cerrado.\\nSi el problema continua, consulte con el administrador.");
			}
		} catch (Exception e) {
			SimpleLogger.setError("Problemas al realizar la validacion general", e);
			resultadoValidacion.setPermitido(false);
			resultadoValidacion.setSiguientePagina(AutenticacionServicio.PAGINA_ACCESODENEGADO);
		}

		return resultadoValidacion;
	}

	public static ResultadoValidacion validacionAdministrador(Request request) {
		ResultadoValidacion rv = new ResultadoValidacion();

		Integer id_persona = (Integer) request.getSession().getAttribute("id_persona");
		Integer id_negocio = (Integer) request.getSession().getAttribute("id_negocio");
		String nombre_negocio = (String) request.getSession().getAttribute("nombre_negocio");

		if (nombre_negocio == null) {
			nombre_negocio = "seleccionado";
		} else {
			nombre_negocio = "'" + nombre_negocio + "'";
		}

		if (!NegocioServicio.getInstance().esPersonaAdministrador(id_persona, id_negocio)) {
			rv.setMensaje("Lo Sentimos, Usted no tiene permisos de administrador sobre el negocio " + nombre_negocio + ".");
			rv.setPermitido(false);
			rv.setSiguientePagina("/inicio/0.do");
		}

		return rv;
	}

	public static ResultadoValidacion validacionCliente(Request request) {
		ResultadoValidacion rv = new ResultadoValidacion();

		Integer id_persona = (Integer) request.getSession().getAttribute("id_persona");
		Integer id_negocio = (Integer) request.getSession().getAttribute("id_negocio");
		String nombre_negocio = (String) request.getSession().getAttribute("nombre_negocio");

		if (nombre_negocio == null) {
			nombre_negocio = "seleccionado";
		} else {
			nombre_negocio = "'" + nombre_negocio + "'";
		}

		if (!NegocioServicio.getInstance().esPersonaCliente(id_persona, id_negocio)) {
			rv.setMensaje("Lo Sentimos, Usted no es cliente del negocio " + nombre_negocio + ".");
			rv.setPermitido(false);
			rv.setSiguientePagina("/inicio/0.do");
		}

		return rv;
	}

}
