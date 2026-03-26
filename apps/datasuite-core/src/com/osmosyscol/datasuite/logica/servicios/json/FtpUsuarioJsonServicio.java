package com.osmosyscol.datasuite.logica.servicios.json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.dto.FtpUsuario;
import com.osmosyscol.datasuite.logica.servicios.FtpUsuarioServicio;

public class FtpUsuarioJsonServicio implements JsonService {
	
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}
	
	public FtpUsuario obtenerFtpUsuario(Integer id_ftp_usuario){
		return FtpUsuarioServicio.getInstance().obtenerFtpUsuario(id_ftp_usuario);
	}
	
	public List<FtpUsuario> obtenerFtpUsuarioPorUsuario(Integer id_usuario){
		return FtpUsuarioServicio.getInstance().obtenerFtpUsuarioPorUsuario(id_usuario);
	}
	
	public String registrarIdRespuesta(){
		String id_respuesta = StringUtils.randomString(5) + StringUtils.MD5(System.currentTimeMillis() + "");
		return id_respuesta;
	}
	
	public Map<String, Object> obtenerRespuesta(String id_respuesta){
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("exito", FtpUsuarioServicio.getInstance().obtenerRespuesta(id_respuesta));
		mapa.put("id_ftp_usuario", FtpUsuarioServicio.getInstance().obtenerId(id_respuesta));
		mapa.put("id_horario", FtpUsuarioServicio.getInstance().obtenerIdHorario(id_respuesta));
		return mapa;
	}
	
	public Boolean asociarFtpUsuarioProceso(Integer id_ftp_usuario, Integer id_proceso){
		return FtpUsuarioServicio.getInstance().asociarFtpUsuarioProceso(id_ftp_usuario, id_proceso);
	}

}
