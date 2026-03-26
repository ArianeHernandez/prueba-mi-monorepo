package com.osmosyscol.commons.utils;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.transaction.NotSupportedException;

import com.osmosyscol.commons.log.SimpleLogger;

public class ListUtils extends org.apache.commons.collections.ListUtils {
	
	/**
	 * Devuelve una lista de sublistas de la lista <code>list</code>, de tamaño
	 * <code>size</code>
	 * 
	 * @param lista lista de entrada
	 * @param tamano tamaño de las listas deseadas
	 * @return	lista de sublistas de tamaño <code>tamano</code>
	 * @throws IllegalArgumentException si <code>tamano</code> es menor a 1
	 * @throws NullPointerException si <code>lista</code> es null
	 * @author hjdiaz
	 */
	public static <T extends Object> List<List<T>> partition(List<T> lista, int tamano) throws IllegalArgumentException, NullPointerException {
		
		if (lista == null){
			throw new NullPointerException();
		}
		
		if (tamano < 1){
			throw new IllegalArgumentException("El tamaño de la lista debe ser mayor a 0");
		}
		
		
		List<List<T>> lists = new ArrayList<List<T>>();
		
		for (int i = 0; i < lista.size(); i += tamano){
			lists.add(lista.subList(i, Math.min(i + tamano, lista.size())));
		}
		
		return lists;
	}
	
	/**
	 * Ordena los objetos de una lista de tipo <code>clase</code> respecto al
	 * método con nombre <code>methodName</code>
	 * 
	 * @param clase 
	 * @param lista
	 * @param methodName
	 * @author hjdiaz
	 */
	public static <T> void sort(Class<T> clase, List<T> lista, String methodName){
		
		try {
			
			Method[] methods = clase.getMethods();
			
			for (final Method method : methods) {
				if (method.getName().equals(methodName)){
					Collections.sort(lista, new Comparator<T>() {
						
						public int compare(T element1, T element2){
							Class <?> rClass = method.getReturnType();
							try {
								if (Integer.class.equals(rClass)){
									return ((Integer) method.invoke(element1)).compareTo((Integer) method.invoke(element2));
								}
								if (String.class.equals(rClass)){
									return ((String) method.invoke(element1)).compareTo((String) method.invoke(element2));
								}
								if (BigDecimal.class.equals(rClass)){
									return ((BigDecimal) method.invoke(element1)).compareTo((BigDecimal) method.invoke(element2));
								}
								if (Date.class.equals(rClass)){
									return ((Date) method.invoke(element1)).compareTo((Date) method.invoke(element2));
								}
								if (Boolean.class.equals(rClass)){
									return ((Boolean) method.invoke(element1)).compareTo((Boolean) method.invoke(element2));
								}
								throw new NotSupportedException("La implementación del método sort no soporta objetos de tipo " + rClass.getName());
							}catch (Exception e){
								throw new RuntimeException(e);
							}
						}
					});
					
				}
			}

		}catch (Exception e){
			SimpleLogger.setError("Error en ListUtils.sort", e);
		}
	}

}
