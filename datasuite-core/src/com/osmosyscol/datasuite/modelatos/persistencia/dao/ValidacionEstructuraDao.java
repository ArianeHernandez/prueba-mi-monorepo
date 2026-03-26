package com.osmosyscol.datasuite.modelatos.persistencia.dao;

import java.util.List;
import java.util.Map;

import com.osmosyscol.datasuite.modelatos.logica.dto.ResultadoValidacion;
import com.osmosyscol.datasuite.modelatos.logica.dto.TipoValidacion;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValidacionCampo;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValidacionEstructura;

public interface ValidacionEstructuraDao {

	public List<Map<String, Object>> obtenerRegistrosDeEstructuraPorCarga(Integer id_carga, Integer id_estructura);
	
	public List<ValidacionEstructura> obtenerValidacionesPorEstructura (Integer id_estructura);
	
	public List<ValidacionCampo> obtenerValidacionCampoPorValidacion (Integer id_validacion_estructura);
	
	public List<TipoValidacion> obtenerTiposDeValidacion();
	
	public TipoValidacion obtenerTipoDeValidacion(String id_tipovalidacion);
	
	public Boolean crearValidacionPorEstructura(ValidacionEstructura validacionEstructura);
	
	public Integer obtenerSiguienteIDValidacionEstructura();
	
	public Boolean crearRelacionCampoValidacion(ValidacionCampo validacionCampo);
	
	public Boolean eliminarTodasLasRelacionesCampoValidacion(Integer id_validacion_estructura);
	
	public Boolean actualizarValidacionPorEstructura(ValidacionEstructura validacionEstructura);
	
	public Boolean eliminarValidacionPorEstructura(Integer id_validacion_estructura);
	
	public ValidacionEstructura obtenerValidacionPorEstructura (Integer id_validacion_estructura);

	public Boolean borrarResultadoValidacion(Integer id_carga);

	public Boolean guardarResultadoValidacion(Integer id_carga, Integer id_registro, Integer id_validacion);

	public List<ResultadoValidacion> obtenerResultadoValidacion(Integer id_carga);

	public List<Map<String, Object>> obtenerRegistrosDeEstructuraPorCargaVista(Integer id_carga, Integer id_estructura);
	
}
