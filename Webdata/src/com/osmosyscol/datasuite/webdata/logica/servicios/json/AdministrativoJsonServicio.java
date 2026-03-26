package com.osmosyscol.datasuite.webdata.logica.servicios.json;

import java.util.List;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.datasuite.logica.dto.Administrativo;
import com.osmosyscol.datasuite.logica.dto.Negocio;
import com.osmosyscol.datasuite.logica.dto.RestriccionAdministrativo;
import com.osmosyscol.datasuite.logica.dto.Rol;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.servicios.AdministrativoServicio;
import com.osmosyscol.datasuite.logica.servicios.NegocioServicio;
import com.osmosyscol.datasuite.logica.servicios.RolServicio;
import com.osmosyscol.datasuite.logica.servicios.UsuarioServicio;


public class AdministrativoJsonServicio {


	private Session session;
	
	public void setSession(Session session) {
		this.session = session;
		
	}
	
	public static List<RestriccionAdministrativo> obtenerRestriccionesAdministrativos() {
		return AdministrativoServicio.getInstance().obtenerRestriccionesAdministrativos();
	}
	
	public static Integer obtenerIdSiguiente() {
		return AdministrativoServicio.getInstance().obtenerIdSiguiente();
	}
	
	public static Boolean crearRestriccionAdministrativo(Integer id_rol, Integer id_administrativo, Integer id_usuario, Integer id_negocio,Integer id_restriccion_administrativo) {
		return AdministrativoServicio.getInstance().crearRestriccionAdministrativo(id_rol, id_administrativo, id_usuario, id_negocio, id_restriccion_administrativo);
	}
	
	public static RestriccionAdministrativo obtenerRestriccionAdministrativo(Integer id_restriccion_administrativo) {
		return AdministrativoServicio.getInstance().obtenerRestriccionAdministrativo(id_restriccion_administrativo);
	}
	
	public static Boolean eliminarRestriccionAdministrativo(Integer id_restriccion_administrativo) {
		return AdministrativoServicio.getInstance().eliminarRestriccionAdministrativo(id_restriccion_administrativo);
	}
	
	public static List<Rol> obtenerRolesActivos() {
		return RolServicio.getInstance().obtenerRolesActivos();
	}
	
	public static Boolean actualizarRol(Integer id_restriccion_administrativo, Integer id_rol){
		return AdministrativoServicio.getInstance().actualizarRol(id_restriccion_administrativo, id_rol);
	}
	
	public static List<Administrativo> obtenerAdministrativosPorRol(Integer id_rol){
		return AdministrativoServicio.getInstance().obtenerAdministrativosPorRol(id_rol);
	}
	
	public static Boolean actualizarAdministrativo(Integer id_restriccion_administrativo, Integer id_administrativo){
		return AdministrativoServicio.getInstance().actualizarAdministrativo(id_restriccion_administrativo, id_administrativo);
	}
	
	public static List<Usuario> obtenerUsuariosActivos(){
		return UsuarioServicio.getInstance().obtenerUsuariosActivos();
	}
	
	public static List<Negocio> obtenerNegociosPorUsuario(Integer id_usuario){
		return NegocioServicio.getInstance().obtenerNegociosPorUsuario(id_usuario);
	}
	
	public static Boolean actualizarCliente(Integer id_restriccion_administrativo,Integer id_usuario){
		return AdministrativoServicio.getInstance().actualizarCliente(id_restriccion_administrativo, id_usuario);
	}
	
	public static Boolean actualizarNegocio(Integer id_restriccion_administrativo,Integer id_negocio){
		return AdministrativoServicio.getInstance().actualizarNegocio(id_restriccion_administrativo, id_negocio);
	}
	
	public static List<Administrativo> obtenerAdministrativosActivos(){
		return AdministrativoServicio.getInstance().obtenerAdministrativosActivos();
	}
	
}
