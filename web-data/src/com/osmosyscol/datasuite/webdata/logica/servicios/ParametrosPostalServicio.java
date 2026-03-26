package com.osmosyscol.datasuite.webdata.logica.servicios;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.osmosyscol.datasuite.modelatos.logica.dto.ValorLista;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.webdata.logica.dto.ParametrosPostalDto;

public class ParametrosPostalServicio {
	
	private static ParametrosPostalServicio parametrosPostalServicio;
	
	private ParametrosPostalServicio(){
		
	}
	
	public static ParametrosPostalServicio getInstance(){
		if( parametrosPostalServicio == null ) {
			parametrosPostalServicio = new ParametrosPostalServicio();
		}
		return parametrosPostalServicio;
	}
	
	public String getDato(String nombre, String proceso) {
		List<ParametrosPostalDto> parametro = obtenerParametro(nombre, proceso);
		
		String dato = "";
		
		if (null != parametro && !parametro.isEmpty()){
			dato = parametro.get(0).getValor();
		} 
		
		return dato;
	}
	
	public List<ParametrosPostalDto> obtenerParametro(String nombre, String proceso) {
		String sql = "SELECT * FROM $PARAMETROS POSTAL$ WHERE $PARAMETROS POSTAL.NOMBRE$ = $S("+nombre+")$ AND $PARAMETROS POSTAL.PROCESO$ = $I("+proceso+")$";
		
		Map<String, Object> mapParam = new HashMap<String, Object>();
		
//		mapParam.put("nombre", nombre);
		
		List<ParametrosPostalDto> parametro = DS_SqlUtils.queryForList(ParametrosPostalDto.class, sql);
		
		return parametro;
	}
	
	public Map<String, Object> getMapDato(String nombre, String proceso) {
		Map<String, Object> mapDato = new HashMap<String, Object>();
		List<ParametrosPostalDto> parametro = obtenerParametro(nombre, proceso);
		
		String dato = "";
		String tipoDato = "";
		
		if (null != parametro && !parametro.isEmpty()){
			dato = parametro.get(0).getValor();
			tipoDato = parametro.get(0).getTipoDato();
			
			switch(tipoDato) {
			case "b": mapDato.put(tipoDato, Boolean.parseBoolean(dato));
					break;
			case "e": mapDato.put(tipoDato, Integer.parseInt(dato));
					break;
			case "l": mapDato.put(tipoDato, Long.parseLong(dato));
					break;
			default : mapDato.put("s", dato);
					
			}
			
		} 
		
		return mapDato;
	}
	
	
	

}
