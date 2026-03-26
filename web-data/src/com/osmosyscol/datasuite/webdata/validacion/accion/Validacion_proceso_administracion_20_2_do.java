package com.osmosyscol.datasuite.webdata.validacion.accion;

import org.apache.cocoon.environment.Request;
import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.validacion.ResultadoValidacion;
import com.osmosyscol.commons.validacion.ValidacionAccion;
import com.osmosyscol.datasuite.logica.dto.ProcesoAdmin;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ProcesoAdminServicio;

public class Validacion_proceso_administracion_20_2_do implements ValidacionAccion{

	public ResultadoValidacion validar(Request request) {
		
		ResultadoValidacion resultadoValidacion = new ResultadoValidacion();
		
		Session session = request.getSession();
		
		String id_proceso_admin = request.getParameter("id_proceso_admin");
		String id_negocio = request.getParameter("id_negocio");
		
		if(id_proceso_admin!=null){
			session.setAttribute("id_proceso_admin", id_proceso_admin);
			
		} else {
			id_proceso_admin = (String) session.getAttribute("id_proceso_admin");
			ProcesoAdmin proceso = ProcesoAdminServicio.getInstance().obtenerProcesoAdmin(Integer.parseInt(id_proceso_admin));
			id_negocio = proceso.getId_negocio().toString();
		}
		
		
		if(id_negocio!=null){
			session.setAttribute("id_negocio_proceadmin", id_negocio);
		}
		
		return resultadoValidacion;
	}

}
