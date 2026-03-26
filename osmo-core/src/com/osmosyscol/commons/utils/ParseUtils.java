package com.osmosyscol.commons.utils;

import java.util.Date;

public class ParseUtils {

	public static Date toUtilDate(java.sql.Date date) {

		if (date != null) {
			return new Date(date.getTime());
		}

		return null;
	}

	public static java.sql.Date toSQLDate(Date date) {

		if (date != null) {
			return new java.sql.Date(date.getTime());
		}

		return null;
	}

	public static Date toUtilDate(java.sql.Timestamp date) {

		if (date != null) {
			return new Date(date.getTime());
		}

		return null;
	}

	public static java.sql.Timestamp toSQLTimestamp(Date date) {

		if (date != null) {
			return new java.sql.Timestamp(date.getTime());
		}

		return null;
	}
	
	/**
	 * convierte un string a Integer
	 * @param str string a ser convertido
	 * @return el valor del string en integer o null si se le pasa null
	 * @throws NumberFormatException si no se le pasa un entero valido
	 */
	public static Integer parseInt(String str){
		return parseInt(str, null,false);
	}
	
	/**
	 * convierte un string a Integer
	 * @param str string a convertir
	 * @param siNullDef integer por defecto si se le pasa null
	 * @param defSiNoInt si es /true/ retorna /siNullDef/ en caso de no pasarle un entero valido, si es /false/ arroja NumberFormatException en caso de no pasarle un entero valido
	 * @return el entero convertido
	 * @throws NumberFormatException si /defSiNoInt/ es true se arroja en caso de un entero no valido diferente a null
	 */
	public static Integer parseInt(String str, Integer siNullDef, boolean defSiNoInt) throws NumberFormatException{
		if( ! defSiNoInt ){
			
			if(StringUtils.esVacio(str)){
				return siNullDef;
			}else{
				return Integer.parseInt(str);
			}
			
		}else{
			Integer rpta;
			try {
				rpta = Integer.parseInt(str);
			} catch (Exception e) {
				rpta = siNullDef;
			}
			return rpta;
		}
	}
	
	/**
	 * convierte un string a Integer
	 * @param str string a ser convertido
	 * @return el valor del string en integer o null si se le pasa null
	 * @throws NumberFormatException si no se le pasa un entero valido
	 */
	public static Double parseDouble(String str){
		return parseDouble(str, null,false);
	}
	
	/**
	 * convierte un string a Double
	 * @param str string a convertir
	 * @param siNullDef Double por defecto si se le pasa null
	 * @param defSiNoDouble si es /true/ retorna /siNullDef/ en caso de no pasarle un Double valido, si es /false/ arroja NumberFormatException en caso de no pasarle un Double valido
	 * @return el Double convertido
	 * @throws NumberFormatException si /defSiNoDouble/ es true se arroja en caso de un entero no valido diferente a null
	 */
	public static Double parseDouble(String str, Double siNullDef, boolean defSiNoDouble) throws NumberFormatException{
		if( ! defSiNoDouble ){
			
			if(StringUtils.esVacio(str)){
				return siNullDef;
			}else{
				return Double.parseDouble(str);
			}
			
		}else{
			Double rpta;
			try {
				rpta = Double.parseDouble(str);
			} catch (Exception e) {
				rpta = siNullDef;
			}
			return rpta;
		}
	}
	
	/**
	 * convierte un string a Integer
	 * @param str string a ser convertido
	 * @return el valor del string en integer o null si se le pasa null
	 * @throws NumberFormatException si no se le pasa un entero valido
	 */
	public static Float parseFloat(String str){
		return parseFloat(str, null,false);
	}
	
	/**
	 * convierte un string a Float
	 * @param str string a convertir
	 * @param siNullDef Float por defecto si se le pasa null
	 * @param defSiNoFloat si es /true/ retorna /siNullDef/ en caso de no pasarle un Float valido, si es /false/ arroja NumberFormatException en caso de no pasarle un Float valido
	 * @return el Float convertido
	 * @throws NumberFormatException si /defSiNoFloat/ es true se arroja en caso de un Float no valido diferente a null
	 */
	public static Float parseFloat(String str, Float siNullDef, boolean defSiNoFloat) throws NumberFormatException{
		if( ! defSiNoFloat ){
			
			if(StringUtils.esVacio(str)){
				return siNullDef;
			}else{
				return Float.parseFloat(str);
			}
			
		}else{
			Float rpta;
			try {
				rpta = Float.parseFloat(str);
			} catch (Exception e) {
				rpta = siNullDef;
			}
			return rpta;
		}
	}
	
	/**
	 * convierte un string a Long
	 * @param str string a ser convertido
	 * @return el valor del string en long o null si se le pasa null
	 * @throws NumberFormatException si no se le pasa un entero valido
	 */
	public static Long parseLong(String str){
		return parseLong(str, null,false);
	}
	
	/**
	 * convierte un string a Long
	 * @param str string a convertir
	 * @param siNullDef Long por defecto si se le pasa null
	 * @param defSiNoLong si es /true/ retorna /siNullDef/ en caso de no pasarle un Long valido, si es /false/ arroja NumberFormatException en caso de no pasarle un Float valido
	 * @return el Long convertido
	 * @throws NumberFormatException si /defSiNoFloat/ es true se arroja en caso de un Long no valido diferente a null
	 */
	public static Long parseLong(String str, Long siNullDef, boolean defSiNoLong) throws NumberFormatException{
		if( ! defSiNoLong ){
			
			if(StringUtils.esVacio(str)){
				return siNullDef;
			}else{
				return Long.parseLong(str);
			}
			
		}else{
			Long rpta;
			try {
				rpta = Long.parseLong(str);
			} catch (Exception e) {
				rpta = siNullDef;
			}
			return rpta;
		}
	}
	
}
