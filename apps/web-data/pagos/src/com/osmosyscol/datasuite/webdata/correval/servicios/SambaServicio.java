package com.osmosyscol.datasuite.webdata.correval.servicios;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.utils.SambaUtils;

public class SambaServicio {

	public String verificarDatos(Integer id_cliente, String host, String dominio, String usuario, String clave, Map<String, String> carpetas) {
		try {

			Set<Entry<String, String>> s = carpetas.entrySet();
			Iterator<Entry<String, String>> i = s.iterator();
			while (i.hasNext()) {
				Entry<String, String> carpeta = i.next();
				String nombre = carpeta.getKey();
				String path = carpeta.getValue();
				SimpleLogger.setDebug("SambaServicio.verificarDatos: verificando carpeta " + path + " para " + nombre);

				String estado = verificarCarpeta(id_cliente, host, dominio, usuario, clave, nombre, path);

				if (!VERIFICACION_OK.equals(estado)) {
					return estado;
				}
			}

			return VERIFICACION_OK;

		} catch (Exception e) {
			SimpleLogger.setError("Error en SambaServicio.verificarDatos:", e);
			return VERIFICACION_ERROR_CONEXION;
		}

	}

	// --------------------------------------
	
	public static final String VERIFICACION_OK = "OK";
	public static final String VERIFICACION_ERROR_CONEXION = "EC"; 
	public static final String VERIFICACION_ERROR_RUTAS = "ER"; 
	public static final String VERIFICACION_ERROR_PERMISOS = "EP"; 

	public String verificarCarpeta(Integer id_cliente, String host, String dominio, String usuario, String clave, String nombre, String path) {

		SambaUtils client = new SambaUtils(host, path, dominio, usuario, clave);

		if (!client.connect()) {
			return VERIFICACION_ERROR_CONEXION;
		}

		if (!client.isDirectory()) {
			SimpleLogger.setError("Error en SambaServicio.verificarCarpeta: La ruta no es una carpeta existentes. Carpeta " + path + " configurada como " + nombre);
			return VERIFICACION_ERROR_RUTAS;
		}
		
		if (!client.canWrite()) {
			SimpleLogger.setError("Error en SambaServicio.verificarCarpeta: No se puede escribir en la carpeta " + path + " configurada como " + nombre);
			return VERIFICACION_ERROR_PERMISOS;
		}

		if (!client.canRead()) {
			SimpleLogger.setError("Error en SambaServicio.verificarCarpeta: No se puede leer en la carpeta " + path + " configurada como " + nombre);
			return VERIFICACION_ERROR_PERMISOS;
		}

		return VERIFICACION_OK;
	}

}
