package com.osmosyscol.datasuite.webdata.correval.siforion.salida.generador;

import java.io.File;
import java.util.List;

import com.osmosyscol.datasuite.webdata.correval.siforion.salida.SalidaCoreCorreval;

public interface IGeneradorSalidaSistemaCorreval {
	
	public static final String LINE = System.getProperty("line.separator");
	public static final String BLANCO = " ";
	public static final String CERO = "0";

	/**
	 * Retorna contenido del archivo a generar, en caso de fallo retorna null
	 */
	public File generar(List<SalidaCoreCorreval> listaSalidaSistemaCorreval);
	
	/**
	 * Retorna el tipom de archivo a generar
	 */
	public String getTipo_arhivo();
}
