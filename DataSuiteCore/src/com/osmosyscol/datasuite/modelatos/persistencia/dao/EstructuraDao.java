package com.osmosyscol.datasuite.modelatos.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.modelatos.logica.dto.Estructura;

public interface EstructuraDao {

	public List<Estructura> obtenerEstructurasPorPersona(Integer id_modelo, Integer numero_pagina);
	
	public List<Estructura> obtenerEstructurasAccesiblesPorPersona(Integer id_modelo, String filtro, Integer numero_pagina);
	
	public List<Estructura> obtenerEstructurasAccesiblesPorPersona(Integer id_modelo);

	public Integer totalEstructurasPorPersona(Integer id_negocio);
	
	public Integer totalEstructurasAccesiblesPorPersona(Integer id_negocio, String filtro);
	
	public List<Estructura> obtenerEstructurasPorGrupo(Integer id_grupo);

	public Estructura obtenerEstructura(Integer id_estructura);

	public Estructura guardarEstructura(Estructura estructura, Integer id_persona, Integer id_modelo);
	
	public Integer contarCamposPorEstructura(Integer id_estructura);

	public Integer getSiguienteID_Objeto();

	public List<Estructura> obtenerEstructurasPadre(Integer id_estructura);
	
	public List<Estructura> obtenerEstructurasRelacionadas(Integer id_estructura);

	public Boolean eliminarEstructura(Integer id_estructura);

	public Estructura obtenerEstructuraPorNombre(String nombreEstructura, Integer id_modelo);

	public List<Estructura> obtenerEstructurasPorEstado(String estado);

	public List<Estructura> obtenerEstructuras();
	
	public List<Estructura> obtenerEstructurasPorFormatoDeSalida (Integer id_formato_salida);

	public Boolean actualizarPosicion(Integer id_estructura, Integer xpos, Integer ypos);

	public Boolean actualizarPosicionGrupo(Integer id_estructura, Integer xpos, Integer ypos, Integer id_grupo);

	public List<String> buscarNombresEstructurasAccesibles(String filtro);
}
