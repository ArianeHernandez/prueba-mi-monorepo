package com.osmosyscol.datasuite.near.webdata;

import java.util.List;

import co.htsoft.commons.util.SMessage;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.Accion;
import com.osmosyscol.datasuite.logica.dto.CargaInstancia;
import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.modelatos.logica.servicios.AccionResponsableServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.AccionServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.CargaInstanciaServicio;

public class AsociarPonenteNEAR implements AccionCarga {

	@Override
	public SMessage ejecutar(Integer id_carga) {
		
		
		
		try {
			
				List<CargaInstancia> carga_instancias = CargaInstanciaServicio.getInstance().obtenerCargaInstanciaActual(id_carga);
				
				if (carga_instancias != null && carga_instancias.size() > 0) {
					
					Integer id_instancia = null;
					
					for (CargaInstancia carga_instancia: carga_instancias) {
						id_instancia = carga_instancia.getId_instancia();
					}
					
					
					List<Accion> acciones = AccionServicio.getInstance().obtenerAccionesPorInstancia(id_instancia);
										
					List<Integer> personas = AccionResponsableServicio.getInstance().obtenerResponsablesPorCarga(id_carga);
					
					if (acciones != null && acciones.size() > 0 && personas != null && personas.size() > 0) {
					
						AccionResponsableServicio.getInstance().guardarAccionResponsable(id_carga, acciones.get(0).getId_accion(), personas.get(0));
					
						return new SMessage(true, "Ok");
						
					} else {
						SimpleLogger.setError("No se encontraron acciones o persona responsable asociada "
								+ "a la solicitud " + id_carga);
						
						return new SMessage(false, "");
					}
				} else {
					SimpleLogger.setError("No se encuentra instancia actual para la carga " + id_carga);

					return new SMessage(false, "");
				}
				
						
			
		} catch ( Exception e) {
			SimpleLogger.setError("Error en la asociacion de ponente solicitud " + id_carga, e);

			return new SMessage(false, "");
		}
		
	}

	@Override
	public Boolean visualizar(Integer id_carga, Integer id_administrativo) {
		return true;
	}
	
}
