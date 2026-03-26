package com.osmosyscol.datasuite.webdata.logica.servicios.utils;

import com.osmosyscol.datasuite.logica.constantes.Constantes;

public class XlsUtils {
	
	public static String agregarFormatoFechaOVacio(String campo){
		return si(esVacio(campo),formato(campo,""),formato(campo,Constantes.FORMATO_FECHA_EXCEL));
		
	}
	
	public static String formato(String campo,String formato){
		return "text("+campo+",\""+formato+"\")";		
	}
	
	public static String si(String condicion,String verdadero,String falso){
		return "if("+condicion+","+verdadero+","+falso+")";
	}
	
	public static String esVacio(String campo){
		return "IsBlank("+campo+")";
	}

}
