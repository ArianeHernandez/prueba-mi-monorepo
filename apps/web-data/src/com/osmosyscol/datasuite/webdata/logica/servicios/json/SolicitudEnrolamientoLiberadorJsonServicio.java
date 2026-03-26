package com.osmosyscol.datasuite.webdata.logica.servicios.json;

import org.apache.cocoon.environment.Session;

import co.htsoft.commons.lang.P;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.logica.dto.Mensaje;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.webdata.logica.dto.SolicitudEnrolamiento;
import com.osmosyscol.datasuite.webdata.logica.servicios.SolicitudEnrolamientoServicio;

public class SolicitudEnrolamientoLiberadorJsonServicio implements JsonService  {
	
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}
	
	public Mensaje crearSolicitud(SolicitudEnrolamiento solicitud) {
		Integer id_persona = null;
		try{
			id_persona = (Integer) session.getAttribute("id_persona");
		}catch(Exception e){
			e.printStackTrace();
		}
		return SolicitudEnrolamientoServicio.getInstance().crearSolicitud(solicitud, true, id_persona);
	}
	
	public Persona obtenerInformacionUsuario(){
		Integer id_usuario = (Integer) session.getAttribute("id_usuario");
		return SolicitudEnrolamientoServicio.getInstance().obtenerInformacionUsuario(id_usuario);
	}
	
	public Boolean existeLiberadorEnUsuario(SolicitudEnrolamiento solicitud){
		Integer id_usuario = (Integer) session.getAttribute("id_usuario");
		String tipo_persona = "N";
		String identificacion = solicitud.getDatos_usuario().getNumero_de_documento();
		Integer tipoDocumento = solicitud.getDatos_usuario().getTipo_de_documento();
		return SolicitudEnrolamientoServicio.getInstance().existeLiberadorEnUsuario(id_usuario, tipo_persona, identificacion, tipoDocumento);
	}

}
