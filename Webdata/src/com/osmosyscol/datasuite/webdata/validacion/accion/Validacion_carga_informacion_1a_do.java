package com.osmosyscol.datasuite.webdata.validacion.accion;

import org.apache.cocoon.environment.Request;
import org.apache.cocoon.environment.Session;

import co.htsoft.commons.lang.P;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.commons.validacion.ResultadoValidacion;
import com.osmosyscol.commons.validacion.ValidacionAccion;
import com.osmosyscol.datasuite.modelatos.logica.dto.Formato;
import com.osmosyscol.datasuite.modelatos.logica.servicios.FormatoServicio;

public class Validacion_carga_informacion_1a_do implements ValidacionAccion {

	public ResultadoValidacion validar(Request request) {

		ResultadoValidacion resultadoValidacion = new ResultadoValidacion();

		Session session = request.getSession();

		String id_proceso = request.getParameter("id_proceso");
		session.setAttribute("id_proceso", id_proceso);

		String idFormatoString = request.getParameter("id_formato");

		Integer id_formato = null;

		if (idFormatoString != null) {
			id_formato = new Integer(idFormatoString);
		} else {
			id_formato = (Integer) session.getAttribute("id_formato");
		}

		String idNegocioString = request.getParameter("id_negocio");

		if (StringUtils.esNoVacio(idNegocioString)) {
			session.setAttribute("id_negocio", new Integer(idNegocioString));
			
			if( StringUtils.esVerdad(session.getAttribute("var.estransaccionmanual") +"" )){
				session.setAttribute("var.id_negocio_carga", new Integer(idNegocioString));
			}
		}

		Formato formato = FormatoServicio.getInstance().obtenerFormato(id_formato);

		if (formato != null) {

			session.setAttribute("id_formato", formato.getId_formato());
			session.setAttribute("var.id_formato", formato.getId_formato());

			// si es un formato inactivo
			if (!StringUtils.esVerdad(formato.getActivo())) {
				resultadoValidacion.setSiguientePagina("/inicio/0.do");
				resultadoValidacion.setPermitido(false);
				resultadoValidacion.setMensaje("Lo sentimos, en el momento no se encuentra disponible.");
				
				return resultadoValidacion;
			}

			// si es un formato externo..
			if (StringUtils.esNoVacio(formato.getFormato_externo())) {
				// DFRODRIGUEZ: CAMBIO PARA SOPORTE DE FORMATOS PERSONALIZADOS EXTERNOS EN RUTAS DISTINTAS A BANCOS
//				resultadoValidacion.setSiguientePagina("/carga_informacion/bancos/" + formato.getFormato_externo() + ".do");
				resultadoValidacion.setSiguientePagina(formato.getFormato_externo());
				// DFRODRIGUEZ: CAMBIO PARA SOPORTE DE FORMATOS PERSONALIZADOS EXTERNOS EN RUTAS DISTINTAS A BANCOS
				resultadoValidacion.setPermitido(false);
				
				return resultadoValidacion;
			}

			// si tiene una sola forma de ingreso de info
			if (formato.getMedios_total() == 1) {

				session.setAttribute("var.id_formato", formato.getId_formato());
				resultadoValidacion.setPermitido(false);

				if (formato.getMedio_formulario().equals("S")) {

					resultadoValidacion.setSiguientePagina("/carga_informacion/interactivo/1.0.do");

				} else if (formato.getMedio_excel().equals("S")) {
					resultadoValidacion.setSiguientePagina("/carga_informacion/lotes/1.2.do");
				} else {
					SimpleLogger.setError("El medio de este formato no está implementado... id = " + formato.getId_formato());
				}

				return resultadoValidacion;
			}
		}

		return resultadoValidacion;
	}

}
