package com.osmosyscol.datasuite.webdata.logica.servicios.json;

import java.util.List;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.logica.dto.Rol;
import com.osmosyscol.datasuite.webdata.logica.servicios.AccesoRolServicio;

public class AccesoRolJsonServicio  implements JsonService {

	private Session session;
	
	public List<Rol> obtenerRolesPorServicio(Integer id_servicio){
		
		Integer id_usuario = (Integer) session.getAttribute("id_usuario");
		
		return AccesoRolServicio.getInstance().obtenerRolesPorServicio(id_usuario, id_servicio);
	}
	
	public Boolean guardarAccesoRol(Integer id_servicio, Integer id_rol){
		return AccesoRolServicio.getInstance().guardarAccesoRol(id_servicio, id_rol);
	}
	
	public Boolean eliminarAccesoRol(Integer id_servicio, Integer id_rol){
		return AccesoRolServicio.getInstance().eliminarAccesoRol(id_servicio, id_rol);
	}

	public void setSession(Session session) {
		this.session = session;
	}
	
	public Boolean validarAccesoAdministrativo(Integer id_servicio){
		Integer id_administrativo = (Integer) session.getAttribute("id_administrativo");;
		return AccesoRolServicio.getInstance().validarAccesoAdministrativo(id_servicio, id_administrativo);
	}
}
