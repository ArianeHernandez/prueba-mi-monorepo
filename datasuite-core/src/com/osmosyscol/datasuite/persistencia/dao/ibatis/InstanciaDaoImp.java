package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.Instancia;
import com.osmosyscol.datasuite.logica.dto.Rol;
import com.osmosyscol.datasuite.persistencia.dao.InstanciaDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class InstanciaDaoImp extends BaseSqlMapDao implements InstanciaDao {

	public InstanciaDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	public Boolean insertarInstancia(Integer id_instancia, String nombre, String aprobar, String rechazar, Integer tiempo, Integer posicion_x, Integer posicion_y, Integer id_proceso_admin, String inicial, Integer id_formato_salida, Integer id_horario, String aprobar_automaticamente, String rechazar_automaticamente, String solicitar_otp) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_instancia", id_instancia);
		map.put("nombre", nombre);
		map.put("aprobar", aprobar);
		map.put("rechazar", rechazar);
		map.put("tiempo", tiempo);
		map.put("posicion_x", posicion_x);
		map.put("posicion_y", posicion_y);
		map.put("id_proceso_admin", id_proceso_admin);
		map.put("inicial", inicial);
		map.put("id_formato_salida", id_formato_salida);
		map.put("id_horario", id_horario);
		map.put("aprobar_automaticamente", aprobar_automaticamente);
		map.put("rechazar_automaticamente", rechazar_automaticamente);
		map.put("solicitar_otp", solicitar_otp);

		Boolean conf = false;
		try {
			insert("Instancia.insertarInstancia", map);
			conf = true;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en InstanciaDaoImp.insertarInstancia", e);
		}
		return conf;

	}

	public Instancia obtenerInstancia(Integer id_instancia) {
		return (Instancia) queryForObject("Instancia.obtenerInstancia", id_instancia);
	}

	@SuppressWarnings("unchecked")
	public List<Instancia> obtenerInstanciasPorProceso(Integer id_proceso_admin) {
		return queryForList("Instancia.obtenerInstanciasPorProceso", id_proceso_admin);
	}

	public Integer obtenerSiguienteId() {
		return (Integer) queryForObject("Instancia.obtenerSiguienteId", null);
	}

	public Boolean borrarInstancia(Integer id_instancia) {
		delete("Instancia.borrarCargaInstancia", id_instancia);
		delete("Instancia.borrarAccionNotificacionesInstancia", id_instancia);
		delete("Instancia.borrarAccionDestinoInstancia", id_instancia);
		delete("Instancia.borrarAccionesInstancia", id_instancia);
		delete("Instancia.borrarRolesInstancia", id_instancia);
		delete("Instancia.borrarInstancia", id_instancia);
		return true;
	}

	public Boolean actualizarPosicionInstancia(Integer id_instancia, Integer posicion_x, Integer posicion_y) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_instancia", id_instancia);
		map.put("posicion_x", posicion_x);
		map.put("posicion_y", posicion_y);

		Integer cantidad = update("Instancia.actualizarPosicionInstancia", map);

		return cantidad > 0;

	}

	public Boolean actualizarPermisosInstancia(Integer id_instancia, String aprobar, String rechazar, String inicial, String aprobar_automaticamente, String rechazar_automaticamente, String solicitar_otp, String ocultar_sin_filtro) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_instancia", id_instancia);
		map.put("aprobar", aprobar);
		map.put("rechazar", rechazar);
		map.put("inicial", inicial);
		map.put("aprobar_automaticamente", aprobar_automaticamente);
		map.put("rechazar_automaticamente", rechazar_automaticamente);
		map.put("solicitar_otp", solicitar_otp);
		map.put("ocultar_sin_filtro", ocultar_sin_filtro);

		Integer cantidad = update("Instancia.actualizarPermisosInstancia", map);

		return cantidad > 0;

	}

	public Boolean actualizarTiempo(Integer id_instancia, Integer tiempo) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_instancia", id_instancia);
		map.put("tiempo", tiempo);

		Integer cantidad = update("Instancia.actualizarTiempo", map);

		return cantidad > 0;
	}

	public Boolean actualizarHorario(Integer id_instancia, Integer id_horario) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_instancia", id_instancia);
		map.put("id_horario", id_horario);

		Integer cantidad = update("Instancia.actualizarHorario", map);

		return cantidad > 0;
	}

	public Boolean actualizarNombre(Integer id_instancia, String nombre) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_instancia", id_instancia);
		map.put("nombre", nombre);

		Integer cantidad = update("Instancia.actualizarNombre", map);

		return cantidad > 0;

	}

	public Boolean actualizarFormatoSalida(Integer id_instancia, Integer id_formato_salida) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_instancia", id_instancia);
		map.put("id_formato_salida", id_formato_salida);

		Integer cantidad = update("Instancia.actualizarFormatoSalida", map);

		return cantidad > 0;
	}

	public Boolean insertarRolInstancia(Integer id_rol, Integer id_instancia) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_rol", id_rol);
		map.put("id_instancia", id_instancia);

		try {
			insert("Instancia.insertarRolInstancia", map);
			return true;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en InstanciaDaoImp.insertarRolInstancia", e);
			return false;
		}

	}

	public Rol obtenerRolInstancia(Integer id_rol, Integer id_instancia) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_rol", id_rol);
		map.put("id_instancia", id_instancia);

		return (Rol) queryForObject("Instancia.obtenerRolInstancia", map);
	}

	@SuppressWarnings("unchecked")
	public List<Rol> obtenerRolesInstancia(Integer id_instancia) {
		return queryForList("Instancia.obtenerRolesInstancia", id_instancia);
	}

	public Boolean borrarRolInstancia(Integer id_rol, Integer id_instancia) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_rol", id_rol);
		map.put("id_instancia", id_instancia);

		Integer cant = delete("Instancia.borrarRolInstancia", map);

		return cant > 0;
	}

	@SuppressWarnings("unchecked")
	public List<Rol> obtenerRolesDispParaAsignar(Integer id_instancia) {
		return queryForList("Instancia.obtenerRolesDispParaAsignar", id_instancia);
	}

	public Instancia obtenerInstanciaDelProcesoPorCarga(Integer id_carga, Integer id_instancia) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_carga", id_carga);
		map.put("id_instancia", id_instancia);

		return (Instancia) queryForObject("Instancia.obtenerInstanciaDelProcesoPorCarga", map);
	}

	@SuppressWarnings("unchecked")
	public List<Instancia> obtenerInstanciasInicialesPorProceso(Integer id_proceso_admin) {
		return queryForList("Instancia.obtenerInstanciasInicialesPorProceso", id_proceso_admin);
	}

	@SuppressWarnings("unchecked")
	public List<Instancia> obtenerInstanciasDelProcesoPorAdministrativo(Integer id_administrativo, Integer id_proceso_admin) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_administrativo", id_administrativo);
		map.put("id_proceso_admin", id_proceso_admin);

		return queryForList("Instancia.obtenerInstanciasDelProcesoPorAdministrativo", map);
	}

	@SuppressWarnings("unchecked")
	public List<Instancia> obtenerInstanciasDelProcesoParaRolesHijo(Integer id_proceso_admin, Integer id_administrativo_padre) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_proceso_admin", id_proceso_admin);
		map.put("id_administrativo_padre", id_administrativo_padre);

		return queryForList("Instancia.obtenerInstanciasDelProcesoParaRolesHijo", map);
	}

	@SuppressWarnings("unchecked")
	public List<Instancia> obtenerInstanciasPreviasConCargaActualPendiente(Integer id_instancia_actual, Integer id_carga) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_instancia_actual", id_instancia_actual);
		map.put("id_carga", id_carga);

		return queryForList("Instancia.obtenerInstanciasPreviasConCargaActualPendiente", map);
	}
	
	public Boolean actualizarAdicionarDocs(Integer id_instancia, String adiciona) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_instancia", id_instancia);
		map.put("adiciona_docs", adiciona);

		Integer cantidad = update("Instancia.actualizarAdicionarDocs", map);

		return cantidad > 0;
	}
	
	public Boolean actualizarGestionarDocs(Integer id_instancia, String gestiona) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_instancia", id_instancia);
		map.put("gestiona_docs", gestiona);

		Integer cantidad = update("Instancia.actualizarGestionarDocs", map);

		return cantidad > 0;
	}

	@Override
	public Instancia obtenerInstanciaActual(Integer id_carga) {
		return (Instancia) queryForObject("Instancia.obtenerInstanciaActual", id_carga);
	}

	public Boolean actualizarInstancia(Instancia instancia) {
		return update("Instancia.actualizarInstancia", instancia) > 0;
	}
}
