package com.osmosyscol.datasuite.webdata.correval.servicios.json;

import java.util.List;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.webdata.correval.cargueplano.RetiroCargueServicio;
import com.osmosyscol.datasuite.webdata.correval.cargueplano.dto.Retiro;

public class ProgresoArchivoExternoJsonServicio implements JsonService {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	// -----------------------

	public Integer avanceProgreso() {

		try {
			Integer id_carga = (Integer) session.getAttribute("id_carga");

			return RetiroCargueServicio.getInstance().obtenerPorcentajeProceso(id_carga);

		} catch (Exception e) {
			return null;
		}
	}

	// -----------------------

	public List<Retiro> obtenerRetirosPaginado(Integer num_pagina) {

		try {
			Integer id_carga = (Integer) session.getAttribute("id_carga");

			return RetiroCargueServicio.getInstance().obtenerRetirosPaginado(id_carga, num_pagina);

		} catch (Exception e) {
			return null;
		}
	}

}
