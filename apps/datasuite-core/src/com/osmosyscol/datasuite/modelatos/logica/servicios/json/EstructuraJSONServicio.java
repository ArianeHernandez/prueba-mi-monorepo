package com.osmosyscol.datasuite.modelatos.logica.servicios.json;

import java.util.List;
import java.util.Map;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.logica.servicios.DatosEstructuraServicio;
import com.osmosyscol.datasuite.modelatos.logica.dto.Estructura;
import com.osmosyscol.datasuite.modelatos.logica.servicios.EstructuraServicio;

public class EstructuraJSONServicio implements JsonService {

	public Estructura[] obtenerEstructurasPadre(Integer id_estructura) {

		List<Estructura> estructuras = EstructuraServicio.getInstance().obtenerEstructurasPadre(id_estructura);

		Estructura[] ret = null;

		if (estructuras != null) {
			ret = new Estructura[estructuras.size()];
			int i = 0;
			for (Estructura estructura : estructuras) {
				ret[i++] = estructura;
			}
		}

		return ret;
	}

	public Estructura obtenerEstructuraPorNombre(String nombreEstructura, Integer id_modelo) {

		Estructura estructura = EstructuraServicio.getInstance().obtenerEstructuraPorNombre(nombreEstructura, id_modelo);
		return estructura;

	}

	public void setSession(Session session) {

	}

	public List<Map<String, Object>> obtenerDatosEstructura(Integer id_estructura, Integer orderby, Integer id_campo, String filtro, Integer id_pagina) {
		return DatosEstructuraServicio.getInstance().obtenerDatosEstructuraPag(id_estructura, orderby, id_campo, filtro, id_pagina);
	}

	public Integer contarDatosEstructuraPag(Integer id_estructura, Integer orderby, Integer id_campo, String filtro) {
		return DatosEstructuraServicio.getInstance().contarDatosEstructuraPag(id_estructura, orderby, id_campo, filtro);
	}

	public Boolean actualizarPosicion(Integer id_estructura, Integer xpos, Integer ypos) {
		return EstructuraServicio.getInstance().actualizarPosicion(id_estructura, xpos, ypos);
	}
	
	public Boolean actualizarPosicionGrupo(Integer id_estructura, Integer xpos, Integer ypos, Integer id_grupo) {
		return EstructuraServicio.getInstance().actualizarPosicionGrupo(id_estructura, xpos, ypos, id_grupo);
	}
	
	public Boolean validarEliminarRegistro(Integer id_estructura, Integer id_registro){
		return DatosEstructuraServicio.getInstance().validarEliminarRegistro(id_estructura, id_registro);
	}

	public Boolean validarRemplazarDatos(Integer id_estructura){
		return DatosEstructuraServicio.getInstance().validarRemplazarDatos(id_estructura);
	}
	
	public List<String> buscarNombresEstructuras(String filtro){
		return EstructuraServicio.getInstance().buscarNombresEstructurasAccesibles(filtro);
	}
}
