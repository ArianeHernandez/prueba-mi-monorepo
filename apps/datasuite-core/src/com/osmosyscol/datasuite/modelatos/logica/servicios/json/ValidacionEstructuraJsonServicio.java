package com.osmosyscol.datasuite.modelatos.logica.servicios.json;

import java.util.List;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.modelatos.logica.dto.Campo;
import com.osmosyscol.datasuite.modelatos.logica.dto.TipoValidacion;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValidacionEstructura;
import com.osmosyscol.datasuite.modelatos.logica.servicios.CampoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ValidacionEstructuraServicio;


public class ValidacionEstructuraJsonServicio implements JsonService{
	
	
	private Session session;
	
	public void setSession(Session session) {
		this.session = session;
		
	}
	
	public List<ValidacionEstructura> obtenerValidacionesPorEstructura(Integer id_estructura){
		
		return ValidacionEstructuraServicio.getInstance().obtenerValidacionesPorEstructura(id_estructura);
		
	}
	
	public TipoValidacion obtenerTipoValidacion(String id_tipovalidacion){
		
		TipoValidacion tipoValidacion =   ValidacionEstructuraServicio.getInstance().obtenerTipoValidacion(id_tipovalidacion);
		
		return tipoValidacion;
		
	}
	
	public List<Campo> obtenerCamposPorEstructura(){
		
		Integer id_estructura = obtenerIdEstructura();
		return CampoServicio.getInstance().obtenerCamposPorEstructura(id_estructura);
	}
	
	private Integer obtenerIdEstructura(){
		try {
			
			
			String id =  (String)session.getAttribute("var.id_estructura");
			if(id!=null){
				
				return Integer.parseInt(id);
				
			}

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error.", e);
		}
		return null;
		
	}
	
	
}
