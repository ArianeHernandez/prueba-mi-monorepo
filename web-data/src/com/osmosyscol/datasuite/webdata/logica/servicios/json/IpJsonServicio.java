package com.osmosyscol.datasuite.webdata.logica.servicios.json;

import java.util.List;

import org.apache.cocoon.environment.Session;

import co.htsoft.commons.lang.P;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.logica.dto.Ipprivada;
import com.osmosyscol.datasuite.logica.dto.Ippublica;
import com.osmosyscol.datasuite.logica.servicios.IpServicio;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;

public class IpJsonServicio implements JsonService {
	
	private static Session session;

	@SuppressWarnings("static-access")
	public void setSession(Session session) {
	this.session = session;

	}
	
	public static List<Ippublica> obtenerIPPublicas(Integer id_usuario){
		return IpServicio.getInstance().obtenerIPPublicas(id_usuario);
	}
	
	public static List<Ipprivada> obtenerIPPrivadas(Integer id_ippublica){
		return IpServicio.getInstance().obtenerIPPrivadas(id_ippublica);
	}

	public static Integer guardarIPPrivada(Integer idIpprivada, Integer idIppublica, String desde, String hasta){
		Ipprivada ipprivada = new Ipprivada();
		ipprivada.setDesde(desde);
		ipprivada.setHasta(hasta);
		ipprivada.setId_ipprivada(idIpprivada);
		ipprivada.setId_ippublica(idIppublica);
		return IpServicio.getInstance().guardarIPPrivada(ipprivada);
	}

	public static Integer guardarIPPublica(Integer idIppublica, String desde, String hasta, String estado){
		
		Integer id_persona_us = (Integer)session.getAttribute("id_persona");				
		
		Integer id_usuario = (Integer) session.getAttribute("id_usuario");
		
		Boolean guardar = false;
		
		List<Ippublica> ips = IpServicio.getInstance().obtenerIPPublicas(id_usuario);

		for (Ippublica ippublica : ips) {
			if(ippublica.getId_ippublica().equals(idIppublica)){
				guardar = true;
			}
		}
		
		if(guardar || idIppublica == null){
			Ippublica ip_publica = new Ippublica();
			ip_publica.setDesde(desde);
			ip_publica.setHasta(hasta);
			ip_publica.setEstado(estado);
			ip_publica.setId_ippublica(idIppublica);
			ip_publica.setId_usuario(id_usuario);
			return IpServicio.getInstance().guardarIPPublica(ip_publica);			
		}else{
			return null;
		}
	}

	public static Boolean eliminarIPPublica(Integer idIppublica){
		return IpServicio.getInstance().eliminarIPPublica(idIppublica);
	}

	public static Boolean eliminarIPPrivada(Integer idIpprivada){
		return IpServicio.getInstance().eliminarIPPrivada(idIpprivada);
	}
	
}
