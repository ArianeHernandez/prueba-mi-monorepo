package com.osmosyscol.datasuite.near.webdata;

import co.htsoft.commons.util.SMessage;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.mein.dtos.RepartoIntendencias;
import com.osmosyscol.datasuite.mein.dtos.SolicitudNearSociedad;
import com.osmosyscol.datasuite.mein.servicios.SolicitudNearSociedadServicio;
import com.osmosyscol.datasuite.mein.servicios.rest.client.PasanteAppRestClient;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

public class RepartoAutomaticoIntendencias implements AccionCarga{
	
	@Override
	public SMessage ejecutar(Integer id_carga) {
		SolicitudNearSociedad solicitud = null;
		try {
			solicitud = DS_SqlUtils.queryForObject(SolicitudNearSociedad.class, 
					"select solicitud.* " + 
					"  from $solicitud near sociedad$ solicitud " +
					" left join $SOCIEDAD$ sociedad on solicitud.$solicitud near sociedad.deudor$ = sociedad.id " +
					" where solicitud.idcarga = " + id_carga);
			if (solicitud == null) {
				SimpleLogger.setError("No se encuentra una solicitud NEAR para la carga " + id_carga);

				return new SMessage(false, "");
			}
			if (solicitud.getIntendencia_regional() == null) {	
				
				RepartoIntendencias reparto = SolicitudNearSociedadServicio.getInstance().obtenerReparto(id_carga);
				
				if (reparto != null) {
					DS_SqlUtils
					.update("update $solicitud near sociedad$ set $solicitud near sociedad.intendencia regional$ = $I("+reparto.getIntendencia_asignada()+")$, "
							+ "$solicitud near sociedad.dependencia$ = " + reparto.getApvista_dependencias() + " where ID = " + solicitud.getId());
					
				} else {
					SimpleLogger.setError("No se encuentra la intendencia para la solicitud " + id_carga);

					return new SMessage(false, "");
				}
			}
			
			return new SMessage(true, "");
		} catch (Exception e) {
			SimpleLogger.setError("Error en el reparto de intendencias:", e);

			return new SMessage(false, "");
		}
	}

	private PasanteAppRestClient PasanteAppRestClient() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean visualizar(Integer id_carga, Integer id_administrativo) {
		return true;
	}	

}
