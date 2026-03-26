package com.osmosyscol.datasuite.webdata.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.webdata.logica.dto.HistorialCarga;

public interface HistorialDao {

	public Boolean guardarHistorialCarga(HistorialCarga historialCarga);

	public List<HistorialCarga> obtenerHistorialCarga(Integer id_carga);
	
	public HistorialCarga obtenerUltimoHistorialCarga(Integer id_carga);
	
	public Integer obtenerPesoHistorialCarga(Integer id_carga, String estado_inicial);
	
	public Integer obtenerVecesLiberadoHistorialCarga(Integer id_persona, Integer id_carga, String estado_inicial);
	
}
