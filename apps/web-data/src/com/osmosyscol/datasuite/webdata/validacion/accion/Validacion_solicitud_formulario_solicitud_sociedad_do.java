package com.osmosyscol.datasuite.webdata.validacion.accion;

import org.apache.cocoon.environment.Request;
import org.apache.cocoon.environment.Session;
import org.apache.commons.lang.StringUtils;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.validacion.ResultadoValidacion;
import com.osmosyscol.commons.validacion.ValidacionAccion;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;
import com.osmosyscol.datasuite.logica.servicios.UsuarioServicio;
import com.osmosyscol.datasuite.webdata.logica.dto.SolicitudEnrolamiento;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;
import com.osmosyscol.datasuite.webdata.logica.servicios.SolicitudEnrolamientoServicio;

public class Validacion_solicitud_formulario_solicitud_sociedad_do implements ValidacionAccion {

	public ResultadoValidacion validar(Request request) {

		ResultadoValidacion resultadoValidacion = new ResultadoValidacion();

		// ------

		Session session = request.getSession();
		
		Integer id_usuario = (Integer) session.getAttribute("id_usuario");
		Integer id_persona = (Integer) session.getAttribute("id_persona");
		
		String id_formato = request.getParameter("id_formato");
		id_formato = id_formato == null ? (session.getAttribute("var.id_formato") +""): id_formato;
		
		Integer idFormato = null; 
		try {
			idFormato = new Integer(id_formato);
		} catch (Exception e) {
			SimpleLogger.setError("Validacion_solicitud_formulario_solicitud_sociedad_do : No se pudo obtener el formato ", e);
		}
		
		Object idproc = session.getAttribute("id_proceso");
		Integer id_proceso = null;
		if (idproc != null) {
			id_proceso = new Integer((String)idproc);
		}
		
		Integer id_negocio = (Integer)session.getAttribute("id_negocio");
		
		Usuario usuario = UsuarioServicio.getInstance().obtenerUsuario(id_usuario);
		Persona persona = PersonaServicio.getInstance().obtenerPersona(usuario.getId_persona());
		
		SolicitudEnrolamiento solicitud = SolicitudEnrolamientoServicio.getInstance().obtenerSolicitudPersona(persona.getIdentificacion());
		// ---------------------

		request.setAttribute("solicitud", solicitud);
		request.setAttribute("id_formato_entrada", idFormato);
		
		Integer id_carga = CargaServicio.getInstance().crearCargaInteractiva("Solicitud NEAR", id_persona,
				idFormato, id_proceso, usuario.getId_usuario(), id_negocio, request.getRemoteHost());
		if (id_carga != null) {
			session.setAttribute("var.id_carga", id_carga);
			session.setAttribute("id_formato_entrada", idFormato);
		}
		
		

		return resultadoValidacion;
	}

}
