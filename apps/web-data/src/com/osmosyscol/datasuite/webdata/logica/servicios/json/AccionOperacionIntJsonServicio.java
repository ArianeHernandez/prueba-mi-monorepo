package com.osmosyscol.datasuite.webdata.logica.servicios.json;

import java.util.List;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.logica.dto.Instancia;
import com.osmosyscol.datasuite.logica.dto.OperacionInterna;
import com.osmosyscol.datasuite.modelatos.logica.servicios.AccionServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.OperacionInternaAccionServicio;

public class AccionOperacionIntJsonServicio implements JsonService {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public List<OperacionInterna> obtenerListaOperacionesInternas() {
		return OperacionInternaAccionServicio.getInstance()
				.obtenerListaOperacionesInternas();
	}

	public List<OperacionInterna> obtenerListaOperacionesInternasPorAccion(
			Integer idAccion) {
		return OperacionInternaAccionServicio.getInstance()
				.obtenerOperacionesInternasPorAccion(idAccion);
	}

	public Boolean insertarOperacionInternaAccion(Integer idOperacionInterna,
			Integer idAccion) {
		return OperacionInternaAccionServicio.getInstance()
				.insertarOperacionInternaAccion(idOperacionInterna, idAccion);
	}

	public Boolean borrarOperacionInternaAccion(Integer idOperacionInterna,
			Integer idAccion) {
		return OperacionInternaAccionServicio.getInstance()
				.borrarOperacionInternaAccion(idOperacionInterna, idAccion);
	}

	public Boolean borrarTodasOperacionesInternasAccion(Integer idAccion) {
		return OperacionInternaAccionServicio.getInstance()
				.borrarTodasOperacionesInternasAccion(idAccion);
	}
	
	public OperacionInterna obtenerOperacionInterna(Integer id_operacion_interna) {
		return OperacionInternaAccionServicio.getInstance()
				.obtenerOperacionInterna(id_operacion_interna);
	}
	
	public List<OperacionInterna> obtenerOperacionesDisponiblesParaAsignar(Integer id_accion){
		return OperacionInternaAccionServicio.getInstance().obtenerOperacionesDisponiblesParaAsignar(id_accion);
	}

}
