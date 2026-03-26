package com.osmosyscol.datasuite.webdata.correval.servicios.json;

import java.util.HashMap;
import java.util.Map;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.webdata.correval.servicios.SambaServicio;

public class SambaJsonServicio implements JsonService {
	
	private Session session;
	
	public void setSession(Session session) {
		this.session = session;
		
	}
	
	public Boolean verificarDatosConexion(String host, String dominio, String usuario, String clave, String parametros){
		Map<String, String> carpetas = obtenerMapParametros(parametros);
		
		Integer id_usuario = (Integer) session.getAttribute("id_usuario");
		
		SambaServicio sambaServicio = new SambaServicio();
		String estado = sambaServicio.verificarDatos(id_usuario, host, dominio, usuario, clave, carpetas);
		sambaServicio = null;
		return SambaServicio.VERIFICACION_OK.equals(estado);
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
