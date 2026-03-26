package com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores;

import java.io.File;

import com.osmosyscol.datasuite.logica.dto.IPorcentaje;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.SalidaBanco;

public interface Generador {
	
	public static final String LINE = "\r\n";
	public static final String BLANCO = " ";
	public static final String CERO = "0";

	/**
	 * Codigo del cual realiza generacion
	 */
	public String getCodBanco();
	
	public boolean limpiar();

	/**
	 * Retorna contenido del archivo a generar, en caso de fallo retorna null
	 */
	public File generar(SalidaBanco salidabanco, IPorcentaje porcentaje);
	
}
