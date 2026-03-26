package com.osmosyscol.datasuite.webdata.validacion.accion;

import java.util.List;

import org.apache.cocoon.environment.Request;
import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.validacion.ResultadoValidacion;
import com.osmosyscol.commons.validacion.ValidacionAccion;
import com.osmosyscol.datasuite.logica.dto.AccionRedireccion;
import com.osmosyscol.datasuite.logica.dto.Rol;
import com.osmosyscol.datasuite.logica.servicios.RolServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.AccionRedireccionServicio;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class Validacion_pasante_pasante_do implements ValidacionAccion{

	@SuppressWarnings("unchecked")
	public ResultadoValidacion validar(Request request) {
		
		
		ResultadoValidacion resultadoValidacion = new ResultadoValidacion();
		Session session = request.getSession();
		
		Integer id_administrativo = Integer.parseInt(request.getParameter("id_administrativo"));
		Integer id_instancia = Integer.parseInt(request.getParameter("id_instancia"));
		
		session.setAttribute("instancia_actual_pasante", id_instancia);
		
		List<Rol> rolesAdministrativo = RolServicio.getInstance().obtenerRolesPorAdministrativoInstancia(id_administrativo, id_instancia);
		
		String rolPasante = "";
		
		if (rolesAdministrativo != null && rolesAdministrativo.size() > 0) {
			for (Rol rol: rolesAdministrativo) {
				String sql = "SELECT $HOMOLOGACION ROLES PASANTE.ROL PASANTE$ FROM $HOMOLOGACION ROLES PASANTE$ WHERE $HOMOLOGACION ROLES PASANTE.ID ROL ADMINISTRATIVO$ = $I(" + rol.getId_rol() + ")$";
				rolPasante = DS_SqlUtils.queryForObject(String.class, sql);
				if (rolPasante != null) {
					break;
				}
			}
		}
		request.setAttribute("rol_pasante", rolPasante);
		
		String pasanteFront_endpoint = ParametrosInicio.getProperty("pasanteFront.endpoint");
		
		if (pasanteFront_endpoint != null) {
			request.setAttribute("pasanteFront", pasanteFront_endpoint);
		}
		
		return resultadoValidacion;
	}
	

	
}
