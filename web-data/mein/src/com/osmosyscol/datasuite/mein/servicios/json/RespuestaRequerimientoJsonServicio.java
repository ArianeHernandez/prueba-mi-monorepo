package com.osmosyscol.datasuite.mein.servicios.json;

import java.util.List;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.logica.dto.TipoArchivo;
import com.osmosyscol.datasuite.mein.dtos.RespuestaRequerimiento;
import com.osmosyscol.datasuite.mein.servicios.RespuestaRequerimientoServicio;

public class RespuestaRequerimientoJsonServicio implements JsonService  {

	private Session session;
	
	@Override
	public void setSession(Session session) {
		this.session = session;
	}
	
	public RespuestaRequerimiento obtenerInformacionSolicitudPadre (Integer id_carga) {
		return RespuestaRequerimientoServicio.getInstance().obtenerInformacionSolicitudPadre(id_carga);
	}
	
	public RespuestaRequerimiento obtenerSolicitudPorNumeroProceso (String numero_proceso) {
		return RespuestaRequerimientoServicio.getInstance().obtenerSolicitudPorNumeroProceso(numero_proceso);
	}

	public List<TipoArchivo> obtenerTiposArchivosPorSolicitanteSolicitud (Integer tipo_solicitante, Integer tipo_solicitud) {
		return RespuestaRequerimientoServicio.getInstance().obtenerTiposArchivosPorSolicitanteSolicitud(tipo_solicitante, tipo_solicitud);
	}
	
}
