package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.reportedim.dto.Resultado;


public interface ResultadoDao {

	public Resultado obtenerResultado(Integer id_resultado);
	
	public Integer totalResultados();
	
	public Integer obtenerSiguienteResultadoId();
	
	public Boolean crearResultado(Integer id_resultado, String titulo, String nombre, String tipo, Integer orden, String encriptado, String oculto, String id_reporte);
	
	public Boolean actualizarResultado(Resultado resultado);
	
	public Boolean eliminarResultado(Integer id_resultado);
	
	public List<Resultado> obtenerResultadosByReporte(String id_reporte);
	
}
