package com.osmosyscol.datasuite.webdata.validacion.accion;

import org.apache.cocoon.environment.Request;
import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.validacion.ResultadoValidacion;
import com.osmosyscol.commons.validacion.ValidacionAccion;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.Proceso;
import com.osmosyscol.datasuite.modelatos.logica.dto.Formato;
import com.osmosyscol.datasuite.modelatos.logica.servicios.FormatoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ProcesoServicio;

public class Validacion_carga_informacion_interactivo_1_0_do implements ValidacionAccion {

	public ResultadoValidacion validar(Request request) {

		ResultadoValidacion resultadoValidacion = new ResultadoValidacion();

		Session session = request.getSession();

		String id_proceso = (String) session.getAttribute("id_proceso");

		if (id_proceso == null) {
			id_proceso = request.getParameter("id_proceso");
			session.setAttribute("id_proceso", id_proceso);
		}

		String idFormatoString = request.getParameter("id_formato");

		Integer id_formato = null;

		if (idFormatoString != null) {
			id_formato = new Integer(idFormatoString);
		} else {
			id_formato = (Integer) session.getAttribute("id_formato");
		}

		Formato formato = FormatoServicio.getInstance().obtenerFormato(id_formato);

		if (formato == null && id_proceso != null) {
			Proceso proceso = ProcesoServicio.getInstance().obtenerProceso(new Integer(id_proceso));
			//TODO EL USUARIO DEBE SELECCIONAR SI HAY MAS DE UNO
//			formato = FormatoServicio.getInstance().obtenerFormato(proceso.getId_formato_entrada());
		}

		if (formato != null) {

			session.setAttribute("id_formato", formato.getId_formato());

			/*
			 * Si el formato no tiene cargas relacionadas no denbe cargar la
			 * pagina para la seleccion de cargas
			 */
			if (!formato.getCargas_relacionadas().equals(Constantes.SI)) {
				session.setAttribute("var.id_formato", formato.getId_formato());
				resultadoValidacion.setPermitido(false);
				resultadoValidacion.setSiguientePagina("/carga_informacion/interactivo/1.1.do");

			} else {
				resultadoValidacion.setPermitido(true);
			}

		}

		return resultadoValidacion;

	}

}
