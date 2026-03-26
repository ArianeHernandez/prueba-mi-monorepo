package com.osmosyscol.datasuite.persistencia.dao;

import com.osmosyscol.datasuite.reportedim.dto.AccionFila;


public interface AccionFilaDao {

	public AccionFila obtenerAccionFila(String id_reporte);
	
	public Integer totalAccionesFila();
	
	public Boolean crearAccionFila(String destino, String subreporte, String id_reporte);
	
	public Boolean actualizarAccionFila(AccionFila accionFila);
	
	public Boolean eliminarAccionFila(String id_reporte);
	
	public AccionFila obtenerAccionByAccion(AccionFila accionFila);
	
}
