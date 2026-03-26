package com.osmosyscol.datasuite.webdata.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.logica.dto.Rol;
import com.osmosyscol.datasuite.webdata.logica.dto.Servicio;

public interface AccesoRolDao {

	public List<Servicio> obtenerServicios();

	public List<Rol> obtenerRolesPorServicio(Integer id_usuario, Integer id_servicio);

	public Boolean guardarAccesoRol(Integer id_servicio, Integer id_rol);

	public Boolean eliminarAccesoRol(Integer id_servicio, Integer id_rol);

	public List<Servicio> obtenerServiciosAdministrativo(Integer id_administrativo);
	
	public Integer obtenerIdServicioPorURL(String url);
	
	public Boolean validarAccesoAdministrativo(Integer id_servicio, Integer id_administrativo);
	
	public Boolean validarAccesoAdministrativoPorUrl(Integer id_administrativo, String url);

	public Servicio obtenerServicio(Integer id_servicio);
	
}
