package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.logica.dto.CargaInstancia;
import com.osmosyscol.datasuite.logica.servicios.AdministrativoServicio;
import com.osmosyscol.datasuite.persistencia.dao.CargaInstanciaDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class CargaInstanciaDaoImp extends BaseSqlMapDao implements CargaInstanciaDao {

	public CargaInstanciaDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	public Boolean actualizarFechaSalidaRelacionCargaInstancia(Integer id_carga, Integer id_instancia, Integer id_administrativo) {

		Integer id_persona = -1;

		// Consultamos el id_persona del administrativo
		if (id_administrativo != null) {
			id_persona = AdministrativoServicio.getInstance().obtenerAdministrativoPorID(id_administrativo).getId_persona();
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_carga", id_carga);
		map.put("id_instancia", id_instancia);
		map.put("id_persona", id_persona);

		update("CargaInstancia.actualizarFechaSalidaRelacionCargaInstancia", map);
		return true;
	}

	public Boolean insertarRelacionCargaInstancia(Integer id_carga, Integer id_instancia, Date fecha_llegada) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_carga", id_carga);
		map.put("id_instancia", id_instancia);
		map.put("fecha_llegada", fecha_llegada);

		insert("CargaInstancia.insertarRelacionCargaInstancia", map);

		return true;
	}

	public Integer obtenerIDUsuarioDeRelacionCargaInstancia(Integer id_carga, Integer id_instancia) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_carga", id_carga);
		map.put("id_instancia", id_instancia);

		return (Integer) queryForObject("CargaInstancia.obtenerIDUsuarioDeRelacionCargaInstancia", map);
	}

	public Boolean existeRelacionCargaInstanciaSinFechaSalida(Integer id_carga, Integer id_instancia) {

		Boolean existeRelacion = false;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_carga", id_carga);
		map.put("id_instancia", id_instancia);

		Integer respuesta = (Integer) queryForObject("CargaInstancia.existeRelacionCargaInstanciaSinFechaSalida", map);

		if (respuesta > 0) {
			existeRelacion = true;
		}

		return existeRelacion;

	}

	public Boolean actualizarFechaEntradaRelacionCargaInstancia(Integer id_carga, Integer id_instancia, Date fecha_llegada) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_carga", id_carga);
		map.put("id_instancia", id_instancia);
		map.put("fecha_llegada", fecha_llegada);

		update("CargaInstancia.actualizarFechaLlegadaRelacionCargaInstancia", map);
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<CargaInstancia> obtenerHistorialCargaInstancia(Integer id_carga) {
		return queryForList("CargaInstancia.obtenerHistorialCargaInstancia", id_carga);
	}

	public Boolean eliminarRelacionesCargaInstanciaSinFechaSalida(Integer id_carga) {
		delete("CargaInstancia.eliminarRelacionesCargaInstanciaSinFechaSalida", id_carga);
		return true;
	}

	public Boolean inicializarFechaEntradaRelacionCargaInstancia(Integer id_carga, Date fecha_llegada) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_carga", id_carga);
		map.put("fecha_llegada", fecha_llegada);

		update("CargaInstancia.inicializarFechaLlegadaRelacionCargaInstancia", map);
		return true;
	}

	@Override
	public CargaInstancia obtenerCargaInstanciaPorId(Integer id_carga_instancia) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_carga_instancia", id_carga_instancia);

		return (CargaInstancia) queryForObject("CargaInstancia.obtenerCargaInstanciaPorId", map);
	}

	@SuppressWarnings("unchecked")
	public List<CargaInstancia> obtenerCargaInstanciaActual(Integer id_carga) {
		return queryForList("CargaInstancia.obtenerCargaInstanciaActual", id_carga);
	}

	@SuppressWarnings("unchecked")
	public List<CargaInstancia> obtenerCargasInstanciaActualPorInstanciaFecha(
			String nombre_instancia, Date fecha) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nombre_instancia", nombre_instancia);
		map.put("fecha", fecha);
		
		return queryForList("CargaInstancia.obtenerCargasInstanciaActualPorInstanciaFecha", map);
	}

}
