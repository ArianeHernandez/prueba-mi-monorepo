package com.osmosyscol.osmoautenticador.persistencia.dao;

import java.util.List;

import com.osmosyscol.osmoautenticador.dominio.Aplicacion;
import com.osmosyscol.osmoautenticador.dominio.Rol;
import com.osmosyscol.osmoautenticador.dominio.RolUs;
import com.osmosyscol.osmoautenticador.dominio.Servicio;
import com.osmosyscol.osmoautenticador.dominio.UrlServicio;
import com.osmosyscol.osmoautenticador.dominio.Usuario;

public interface AdministracionDao {

	public List<Aplicacion> obtenerAplicaciones();

	public List<Rol> obtenerRoles(Integer idUsuario);

	public List<RolUs> obtenerRolesLogin(String login);

	public Integer contarUsuariosRolAplicacion(String nombreAplicacion, Integer rol);

	public Integer numeroUsuariosRolCliente(String nombreApp, int rol, String idCliente);

	public List<Aplicacion> obtenerAplicacionesSimple();

	public List<Rol> obtenerRolesAplicacion(Integer idAplicacion);

	public List<Servicio> obtenerServiciosRol(Integer idRol);

	public List<Usuario> obtenerUsuariosRol(Integer idRol, Integer pagina, Integer cantidadPagina);

	public List<UrlServicio> obtenerUrlsServicio(Integer idServicio);

	public Integer contarUsuariosRol(Integer idRol);
}
