// 

package com.osmosyscol.commons.servicio;

import java.net.URL;
import java.util.List;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datapi.logica.dto.ActualizarRolDto;
import com.osmosyscol.osmoautenticador.servicioweb.actualizarRol.TipoRolDto;
import com.osmosyscol.osmoautenticador.servicioweb.actualizarRol.TipoServicioDto;
import com.osmosyscol.osmoautenticador.servicioweb.actualizarRol.WSActualizarRolSOAPSoapBindingStub;

public class ActualizarRol {

	// -----------------------------------------------------------------

	private static final String SERVICES_WS_ACTUALIZARROL = "/services2/WSActualizarRol";
	
	private static ActualizarRol usuarioServicio;

	public static ActualizarRol getInstance() {
		if (usuarioServicio == null) {
			usuarioServicio = new ActualizarRol();
		}
		return usuarioServicio;
	}

	// -----------------------------------------------------------------
	// -----------------------------------------------------------------

	public String[] crearRol(List<ActualizarRolDto> actualizarRol, String nombreRol,String naturaleza) {
		SimpleLogger.setDebug("Creando Rol...");
		String idAplicacion = "1";
		URL url;
		String[] resp = null;
		try {
			url = new URL(AutenticacionServicio.getEndpoint() + SERVICES_WS_ACTUALIZARROL);

			WSActualizarRolSOAPSoapBindingStub stub = new WSActualizarRolSOAPSoapBindingStub(url, null);

			TipoRolDto tipoRol = new TipoRolDto();
			tipoRol.setIdAplicacion(Integer.parseInt(idAplicacion));
			tipoRol.setIdRol(0);
			tipoRol.setNombre(nombreRol);
			
			TipoServicioDto[] tipoServ = new TipoServicioDto[actualizarRol.size()];
			int i=0;
			for (ActualizarRolDto actRol : actualizarRol) {
				TipoServicioDto servicio = new TipoServicioDto();
				servicio.setIdRol(0);
				servicio.setIdServicio(Integer.parseInt(actRol.getIdServicio()));
				servicio.setActivo("");
				servicio.setNombre("");
				tipoServ[i] = servicio;
				i++;
			}
			
			resp = stub.crearRol(tipoRol,tipoServ, naturaleza);
			SimpleLogger.setInfo("Resultado Operacion: " + resp[0]);
	
			return resp;

		} catch (Exception e) {
			SimpleLogger.setError(">> Ha ocurrido al intentar crear el Rol. ", e);
			resp = new String[1];
			resp[0] = "FAILED";
			return resp;
		}
	}
	
	public String[] modificarRol(List<ActualizarRolDto> actualizarRol, String tipoOperacion, String naturaleza) {
		SimpleLogger.setDebug("Modificando Rol...");
		String[] resp = null;
		URL url;
		try {
			url = new URL(AutenticacionServicio.getEndpoint() + SERVICES_WS_ACTUALIZARROL);

			WSActualizarRolSOAPSoapBindingStub stub = new WSActualizarRolSOAPSoapBindingStub(url, null);
			TipoServicioDto[] tipoServ = new TipoServicioDto[actualizarRol.size()];
			String operacion = "";
			if(tipoOperacion.equalsIgnoreCase("E")){
				operacion = tipoOperacion;
			}
			
			int idRol=0;
			int i=0;
			for (ActualizarRolDto actRol : actualizarRol) {
				TipoServicioDto servicio = new TipoServicioDto();
				servicio.setIdRol(Integer.parseInt(actRol.getIdRol()));
				servicio.setIdServicio(Integer.parseInt(actRol.getIdServicio()));
				servicio.setActivo(operacion);
				servicio.setNombre("");
				tipoServ[i] = servicio;
				idRol =Integer.parseInt(actRol.getIdRol());
				i++;
			}
			SimpleLogger.setDebug(" - " + idRol);
			resp = stub.modificarRol(idRol,tipoServ, naturaleza,tipoOperacion);
			SimpleLogger.setInfo("Resultado Operacion: " + resp[0]);
	
			return resp;

		} catch (Exception e) {
			SimpleLogger.setError(">> Ha ocurrido al intentar Modificar el Rol. ", e);
			resp = new String[1];
			resp[0] = "FAILED";
			return resp;
		}
	}


}
