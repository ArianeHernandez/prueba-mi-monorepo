package com.osmosyscol.osmoautenticador.servicio.json;

import java.util.List;

import com.osmosyscol.osmoautenticador.dominio.Aplicacion;
import com.osmosyscol.osmoautenticador.dominio.Rol;
import com.osmosyscol.osmoautenticador.dominio.Servicio;
import com.osmosyscol.osmoautenticador.dominio.UrlServicio;
import com.osmosyscol.osmoautenticador.dominio.Usuario;
import com.osmosyscol.osmoautenticador.servicio.AdministracionServicio;

public class AdministracionServicioJson {

	public static List<Aplicacion> obtenerAplicacionesSimple(){
		return AdministracionServicio.getInstance().obtenerAplicacionesSimple();
	}
	
	public static List<Rol> obtenerRolesAplicacion(Integer idAplicacion) {
		return AdministracionServicio.getInstance().obtenerRolesAplicacion(idAplicacion);
	}
	
	public static List<Servicio> obtenerServiciosRol(Integer idRol) {
		return AdministracionServicio.getInstance().obtenerServiciosRol(idRol);
	}
	
	public static Integer contarUsuariosRol(Integer idRol) {
		return AdministracionServicio.getInstance().contarUsuariosRol(idRol);
	}
	
	public static List<Usuario> obtenerUsuariosRol(Integer idRol, Integer pagina) {
		return AdministracionServicio.getInstance().obtenerUsuariosRol(idRol, pagina);
	}
	
	public static List<UrlServicio> obtenerUrlsServicio(Integer idServicio){
		return AdministracionServicio.getInstance().obtenerUrlsServicio(idServicio);
	}
	
}
