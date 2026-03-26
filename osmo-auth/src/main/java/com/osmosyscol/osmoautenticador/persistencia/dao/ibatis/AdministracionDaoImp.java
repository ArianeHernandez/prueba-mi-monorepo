package com.osmosyscol.osmoautenticador.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.osmoautenticador.dominio.Aplicacion;
import com.osmosyscol.osmoautenticador.dominio.Rol;
import com.osmosyscol.osmoautenticador.dominio.RolUs;
import com.osmosyscol.osmoautenticador.dominio.Servicio;
import com.osmosyscol.osmoautenticador.dominio.UrlServicio;
import com.osmosyscol.osmoautenticador.dominio.Usuario;
import com.osmosyscol.osmoautenticador.persistencia.dao.AdministracionDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class AdministracionDaoImp extends BaseSqlMapDao implements AdministracionDao {

	public AdministracionDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	@SuppressWarnings("unchecked")
	public List<Aplicacion> obtenerAplicaciones() {

		List<Aplicacion> aplicaciones = queryForList("Administracion.obtenerAplicaciones", null);

		if (aplicaciones != null) {
			for (Aplicacion aplicacion : aplicaciones) {
				aplicacion.setRoles(queryForList("Administracion.obtenerRoles", aplicacion.getId_aplicacion()));

				if (aplicacion.getRoles() != null) {
					for (Rol rol : aplicacion.getRoles()) {

						rol.setUsuarios(queryForList("Administracion.obtenerUsuarios", rol.getId_rol()));
						rol.setServicios(queryForList("Administracion.obtenerServicios", rol.getId_rol()));

						if (rol.getServicios() != null) {
							for (Servicio servicio : rol.getServicios()) {
								servicio.setUrls(queryForList("Administracion.obtenerUrls", servicio.getId_servicio()));
							}
						}
					}
				}
			}
		}

		return aplicaciones;
	}

	@SuppressWarnings("unchecked")
	public List<Rol> obtenerRoles(Integer idUsuario) {
		return queryForList("Administracion.obtenerRolesUsuario", idUsuario);
	}

	@SuppressWarnings("unchecked")
	public List<RolUs> obtenerRolesLogin(String login) {
		return queryForList("Administracion.obtenerRolesUsuarioLogin", login);
	}

	public Integer contarUsuariosRolAplicacion(String nombreAplicacion, Integer rol) {
		Map<String, Object> mapa_datos = new HashMap<String, Object>();

		mapa_datos.put("nombre_aplicacion", nombreAplicacion);
		mapa_datos.put("id_rol", rol);

		return (Integer) queryForObject("Administracion.contarUsuariosRolAplicacion", mapa_datos);
	}
	
	public Integer numeroUsuariosRolCliente(String nombreApp, int rol, String idCliente) {
		Map<String, Object> mapa_datos = new HashMap<String, Object>();

		mapa_datos.put("nombre_aplicacion", nombreApp);
		mapa_datos.put("id_rol", rol);
		mapa_datos.put("id_cliente", StringUtils.trimToEmpty(idCliente).toUpperCase());

		return (Integer) queryForObject("Administracion.numeroUsuariosRolCliente", mapa_datos);
	}

	@SuppressWarnings("unchecked")
	public List<Aplicacion> obtenerAplicacionesSimple() {
		return queryForList("Administracion.obtenerAplicaciones", null);
	}

	@SuppressWarnings("unchecked")
	public List<Rol> obtenerRolesAplicacion(Integer idAplicacion) {
		return queryForList("Administracion.obtenerRoles", idAplicacion);
	}

	@SuppressWarnings("unchecked")
	public List<Servicio> obtenerServiciosRol(Integer idRol) {
		return queryForList("Administracion.obtenerServicios", idRol);
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> obtenerUsuariosRol(Integer idRol, Integer pagina, Integer cantidadPagina) {
		return queryForListPag("Administracion.obtenerUsuarios", idRol, pagina, cantidadPagina);
	}

	public List<UrlServicio> obtenerUrlsServicio(Integer idServicio) {
		return queryForList("Administracion.obtenerUrls", idServicio);
	}

	public Integer contarUsuariosRol(Integer idRol) {
		return (Integer) queryForObject("Administracion.contarUsuariosRol", idRol);
	}

}
