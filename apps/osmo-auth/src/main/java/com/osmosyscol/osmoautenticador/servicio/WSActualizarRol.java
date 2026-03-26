// 
package com.osmosyscol.osmoautenticador.servicio;

import java.util.ArrayList;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.osmoautenticador.persistencia.dto.RolDto;
import com.osmosyscol.osmoautenticador.persistencia.dto.ServicioDto;
import com.osmosyscol.osmoautenticador.servicioweb.actualizarRol.TipoRolDto;
import com.osmosyscol.osmoautenticador.servicioweb.actualizarRol.TipoServicioDto;

public class WSActualizarRol {

	public static String[] crearRol(TipoRolDto tipoRol,TipoServicioDto[] tipoServicio, String naturaleza) {
		SimpleLogger.setDebug(">> wsActualizarRol Creando rol...");
		String[] respuesta = null;
		try {
			RolDto rolDto = new RolDto();
			rolDto.setIdAplicacion(tipoRol.getIdAplicacion());
			rolDto.setIdRol(tipoRol.getIdRol());
			rolDto.setNombre(tipoRol.getNombre());
			
			ArrayList<ServicioDto> servicioDto = new ArrayList<ServicioDto>();
			
			for (TipoServicioDto vtipoServicio : tipoServicio) {
				ServicioDto servicio = new ServicioDto();
				servicio.setActivo(vtipoServicio.getActivo());
				servicio.setIdRol(vtipoServicio.getIdRol());
				servicio.setIdServicio(vtipoServicio.getIdServicio());
				servicio.setNombre(vtipoServicio.getNombre());
				servicioDto.add(servicio);
			}
			respuesta = AdministracionServicioRol.getInstance().crearRol(rolDto, servicioDto, naturaleza);

			return respuesta;
		} catch (Exception e) {
			SimpleLogger.setError("Error en WSActualizarRol - crearRol", e);
			respuesta = new String[1];
			respuesta[0] = "FAILED";
			return respuesta;
		}
	}

	public static String[] modificarRol(Integer idRol,TipoServicioDto[] tipoServicio, String naturaleza, String accion) {
		SimpleLogger.setDebug(">> wsActualizarRol Modificando rol...");
		String[] result = null;
		try {
			ArrayList<ServicioDto> servicioDto = new ArrayList<ServicioDto>();
			String tipoOperacion="";
			for (TipoServicioDto vtipoServicio : tipoServicio) {
				ServicioDto servicio = new ServicioDto();
				servicio.setActivo(vtipoServicio.getActivo());
				servicio.setIdRol(vtipoServicio.getIdRol());
				servicio.setIdServicio(vtipoServicio.getIdServicio());
				servicio.setNombre(vtipoServicio.getNombre());
				servicioDto.add(servicio);
				if((vtipoServicio.getActivo()).equalsIgnoreCase("E")){
					tipoOperacion = vtipoServicio.getActivo();
				}
			}
			if(tipoOperacion.equalsIgnoreCase("E")){
				Boolean elimina = AdministracionServicioRol.getInstance().eliminarRol(idRol,naturaleza);
				if(elimina){
					result = new String[1];
					result[0] = "OK";
				}
			}else{
				result = AdministracionServicioRol.getInstance().modificarRol(idRol, servicioDto, naturaleza); 
			}
			return result;
			
		} catch (Exception e) {
			SimpleLogger.setError("Error en WSActualizarRol - modificarRol", e);
			result = new String[1];
			result[0] = "FAILED";
			return result;
		}
	}
}
