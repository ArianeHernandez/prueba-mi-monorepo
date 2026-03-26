package com.osmosyscol.datasuite.webdata.correval.servicios.json;

import java.util.List;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.config.Constantes;
import com.osmosyscol.datasuite.logica.dto.Mensaje;
import com.osmosyscol.datasuite.webdata.correval.siforion.salida.ArchivoSalidaCore;
import com.osmosyscol.datasuite.webdata.correval.siforion.salida.SalidaCoreCorreval;
import com.osmosyscol.datasuite.webdata.correval.siforion.salida.SalidaCoreCorrevalServicio;


public class SalidaCoreCorrevalJsonServicio implements JsonService{
	
	private Session session;
	
	public void setSession(Session session) {
		this.session = session;
		
	}
	
	public Integer contarRetirosPorTipoArchivoSinDescargar(String tipo_archivo){
		try {
			return SalidaCoreCorrevalServicio.getInstance().contarRetirosPorTipoArchivoSinDescargar(tipo_archivo);
		} catch (Throwable e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
	public List<SalidaCoreCorreval> obtenerRetirosPorTipoArchivoSinDescargar(String tipo_archivo, Integer pagina){
		try {
			return SalidaCoreCorrevalServicio.getInstance().obtenerRetirosSinDescargarPorTipoArchivoSinFiltrar(tipo_archivo, pagina);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
	public List<String> obtenerTiposArchivoSalidaCorreval(){
		try {
			return SalidaCoreCorrevalServicio.getInstance().obtenerTiposArchivoSalidaCorreval();
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
	public List<ArchivoSalidaCore> obtenerArchivoSalidaCoreDescargadosPorTipoArchivo(String tipo_archivo, Integer numero_pagina){
		try {
			return SalidaCoreCorrevalServicio.getInstance().obtenerArchivoSalidaCoreDescargadosPorTipoArchivo(tipo_archivo, numero_pagina);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
	public Integer totalRegistrosPorPagina(){
		
		return Constantes.PAGINACIONLISTADO;
	}
	
	public Integer obtenerTotalArchivosDescargadosPorTipoArchivo(String tipo_archivo){
		try {
			return SalidaCoreCorrevalServicio.getInstance().obtenerTotalArchivosDescargadosPorTipoArchivo(tipo_archivo);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
		
	}
	
	public Mensaje obtenerEstadoGeneracionArchivo(){
		return (Mensaje) session.getAttribute(SalidaCoreCorrevalServicio.ATRIBUTO_MENSAJE);
	}
	
	
}
