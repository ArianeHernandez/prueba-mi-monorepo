package com.osmosyscol.datasuite.webdata.persistencia.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;




public interface SalidaCoreCorrevalDao {

	public List<Map<String, Object>> obtenerRetirosPorTipoArchivoSinDescargar(String tipo_archivo, List<String> retiros_seleccionados, Integer pagina);
	
	public List<Map<String, Object>> obtenerRetirosPorArchivoDescargado(Integer id_archivo, String tipo_archivo);
	
	public List<Map<String, Object>> obtenerArchivosDescargadosPorTipoArchivo(String tipo_archivo, Integer numero_pagina);
	
	public Integer obtenerTotalArchivosDescargadosPorTipoArchivo(String tipo_archivo);
	
	public List<String> obtenerTiposArchivoSalidaCorreval();
	
	public Integer obtenerSiguienteIDArchivoSalidaCore();
	
	public Boolean crearArchivoSalidaCore(Integer id_archivo, String tipo_archivo, Date fecha_creacion, String nombre_generador);
	
	public Boolean actualizarArchivoSalidaORIONPorRegistro(Integer id_registro, Integer id_archivo);
	
	public Boolean actualizarArchivoSalidaSIFPorRegistro(Integer id_registro, Integer id_archivo);

	public Integer contarRetirosPorTipoArchivoSinDescargar(String tipo_archivo);
	
	
}
