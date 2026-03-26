package com.osmosyscol.datasuite.webdata.correval.cargueplano;

import java.math.BigDecimal;
import java.util.List;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.FileUtils;
import com.osmosyscol.commons.utils.StringUtils;

public class LectorArchivoPlanoUtils {
	
	
	public static String[][] leerArchivo(String archivo,Integer longitudes[]){

		try {
			
			List<String> lineas = FileUtils.getContentFileList(archivo);
			
			if(lineas!=null){
			
				String [][] textos = new String[lineas.size()][longitudes.length];
				
				int j = 0;
				for (String linea : lineas) {
					
					int posicion = 0;
					
					for (int i = 0; i < longitudes.length; i++) {

						if (posicion + longitudes[i] <= linea.length() && longitudes[i] != 0) {
							textos[j][i] = linea.substring(posicion, posicion + longitudes[i]);
						}else if(posicion + longitudes[i] <= linea.length() && longitudes[i] == 0) {
							textos[j][i] = linea.substring(posicion);	
						}
						
						posicion = posicion + longitudes[i];

					}
					j++;
				}
				return textos;
			
			}else{
				
				return null;
				
			}
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		
		return null;
	}
	
	public static BigDecimal obtenerNumero2Decimales(String str){
		if(StringUtils.esVacio(str) || str.length() < 2 ){
			return null;
		}
		str = str.substring(0, str.length() - 2) + "." + str.substring(str.length() - 2);
		return new BigDecimal(str);
	}
	
}
