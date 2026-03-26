package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.logica.dto.OperacionInterna;

public interface OperacionInternaDao {

	public List<OperacionInterna> listarOperacionesInternas();

	public List<OperacionInterna> listarOperacionesInternasPorAccion(Integer id_accion);
	
	public Boolean eliminarOperacionInternaDeAccion(Integer id_accion, Integer id_operacion_interna);
	
	public Boolean eliminarTodasOperacionInternaDeAccion(Integer id_accion);
	
	public Boolean insertarOperacionInternaDeAccion(Integer id_accion, Integer id_operacion_interna);

	public OperacionInterna obtenerOperacionInterna(Integer id_operacion_interna);
	
	public List<OperacionInterna> obtenerOperacionesDisponiblesParaAsignar(Integer id_accion);

}
