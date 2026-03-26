package com.osmosyscol.datasuite.webdata.correval.servicios.json;

import java.util.HashMap;
import java.util.Map;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.webdata.correval.servicios.FTPServicio;

public class FTPJsonServicio implements JsonService {
	
	private Session session;
	
	public void setSession(Session session) {
		this.session = session;
		
	}
	
	public Boolean verificarDatosConexion(String host, Integer puerto, String usuario, String clave, String parametros){
		Map<String, String> carpetas = obtenerMapParametros(parametros);
		Boolean ok = FTPServicio.getInstance().verificarDatos(host, puerto, usuario, clave, carpetas);
		FTPServicio.getInstance().desconectar();
		return ok;
	}
	
	private Map<String, String> obtenerMapParametros(String parametros) {
		Map<String, String> map = new HashMap<String, String>();

		if (!StringUtils.esVacio(parametros)) {

			String[] pares = parametros.split(",");

			for (String par : pares) {
				String pareja[] = par.split(":");
				if (pareja.length == 2) {
					map.put(pareja[0], pareja[1]);
				}
			}
		}

		return map;
	}

}
