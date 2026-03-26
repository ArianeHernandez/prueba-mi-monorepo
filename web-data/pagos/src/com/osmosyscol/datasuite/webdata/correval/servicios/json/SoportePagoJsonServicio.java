package com.osmosyscol.datasuite.webdata.correval.servicios.json;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.webdata.correval.soportepago.SoportePagoServicio;
import com.osmosyscol.datasuite.webdata.correval.soportepago.dto.SoportePago;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;

public class SoportePagoJsonServicio implements JsonService {

	public void setSession(Session session) {
	}

	// ----------------------------------------

	public Boolean generarPdfSoportePagoyEnviarCorreo(Integer idRetiro, String para, String asunto, String mensaje) {		
		return SoportePagoServicio.getInstance().generarPdfSoportePagoyEnviarCorreo(idRetiro, para, asunto, mensaje);
	}

	// ----------------------------------------

	public Boolean esRegistroAplicado(Integer idRetiro) {
		SoportePago soportepago = SoportePagoServicio.getInstance().obtenerSoportePago(idRetiro);

		Carga carga = CargaServicio.getInstance().obtenerCarga(soportepago.getId_carga());
		
		return soportepago != null && Constantes.CARGA_ESTADO_APROBADO.equalsIgnoreCase(StringUtils.trimToEmpty(carga.getEstado()));
	}

	// ----------------------------------------

}
