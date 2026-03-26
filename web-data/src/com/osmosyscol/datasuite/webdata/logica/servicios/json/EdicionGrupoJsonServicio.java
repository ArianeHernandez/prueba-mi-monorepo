package com.osmosyscol.datasuite.webdata.logica.servicios.json;

import java.util.List;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.webdata.correval.grupogiro.ArchivoGrupoGiro;
import com.osmosyscol.datasuite.webdata.correval.grupogiro.EdicionGrupoServicio;
import com.osmosyscol.datasuite.webdata.correval.grupogiro.GrupoGiroRegistro;

import co.htsoft.commons.lang.StringUtils;

public class EdicionGrupoJsonServicio implements JsonService {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public List<ArchivoGrupoGiro> listarArchivosGenerados() {
		String login = StringUtils.stripToEmpty((String) this.session.getAttribute("login")).toUpperCase();
		return EdicionGrupoServicio.listarArchivosGenerados(login);
	}

	public List<GrupoGiroRegistro> listarGruposGiro(Integer id_archivo) {
		return EdicionGrupoServicio.listarGruposGiro(id_archivo);
	}

	public static Boolean rechazarGrupoGiro(Integer id_grupo) {
		return EdicionGrupoServicio.rechazarGrupoGiro(id_grupo);
	}

}
