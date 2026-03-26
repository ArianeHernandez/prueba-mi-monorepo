package com.osmosyscol.datasuite.modelatos.logica;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValidacionEstructura;


public class ManejadorDeValidadores {
	private HashMap<String, IValidadorDeRegistrosDeCarga> validadoresDeRegistroDeCargas;
	private static ManejadorDeValidadores manejadorTiposValidacion;
	
	
	
	
	private ManejadorDeValidadores() {
		
		validadoresDeRegistroDeCargas = new HashMap<String, IValidadorDeRegistrosDeCarga>();
		
		//Se registran las implementaciones por tipo de validacion (id_tipovalidacion)
		validadoresDeRegistroDeCargas.put("1000", new ValidadorPorRangoValores());
		validadoresDeRegistroDeCargas.put("1001", new ValidadorCampoVacio());
		validadoresDeRegistroDeCargas.put("1002", new ValidadorConsulta());
		validadoresDeRegistroDeCargas.put("1003", new ValidadorCamposRepetidos());
		
		
	}
	
	public static ManejadorDeValidadores getInstance(){
		if(manejadorTiposValidacion==null){
			manejadorTiposValidacion = new ManejadorDeValidadores();
		}
		
		return manejadorTiposValidacion;
		
	}
	
	
	public void registrarImplementacioPorTipoValidacion(String tipoValidacion, IValidadorDeRegistrosDeCarga validador ){
		validadoresDeRegistroDeCargas.put(tipoValidacion, validador);
		
	}
	
	public IValidadorDeRegistrosDeCarga obtenerValidadorPorTipoValidacion(String tipoValidacion){
		
		IValidadorDeRegistrosDeCarga validador = (IValidadorDeRegistrosDeCarga)validadoresDeRegistroDeCargas.get(tipoValidacion);
		return validador;
	}
	
	public Boolean esRegistroDeLaCargaValido(Map<String, Object> registro, List<Map<String, Object>> registros, ValidacionEstructura validacionEstructura){
		
		Boolean esRegistroDeLaCargaValido = true;
		
		//Se busca la implementacion del validor segun el tipo de validacion
		String tipoValidacion = validacionEstructura.getId_tipovalidacion();
		IValidadorDeRegistrosDeCarga validador = obtenerValidadorPorTipoValidacion(tipoValidacion);
		
		if(validador!=null){
			
			esRegistroDeLaCargaValido = validador.esRegistroValido(registro, registros,validacionEstructura);
			return esRegistroDeLaCargaValido;
			
		}else{
			
			SimpleLogger.setError("No hay implementacion para el tipo de validacion: "+tipoValidacion);
			return null;
		}
		
		
	}
	
	
}
