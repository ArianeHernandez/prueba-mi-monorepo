package com.osmosyscol.datasuite.logica.servicios.json;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.logica.servicios.FtpUsuarioCorreoServicio;

public class FtpUsuarioCorreoJsonServicio implements JsonService{
	
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}
	
	public Boolean borrarFtpUsuario(Integer id_ftp_usuario_correo){
		return FtpUsuarioCorreoServicio.getInstance().borrarFptUsuarioCorreo(id_ftp_usuario_correo);
	}

}
