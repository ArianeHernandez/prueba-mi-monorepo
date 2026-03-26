package com.osmosyscol.datasuite.mein.acciones;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.htsoft.commons.util.SMessage;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.CargaInstancia;
import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.mein.dtos.SolicitudNearSociedad;
import com.osmosyscol.datasuite.mein.logica.constantes.Constantes;
import com.osmosyscol.datasuite.modelatos.logica.servicios.CargaInstanciaServicio;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

public class ValidarEstadoRequerimientoOP implements AccionCarga {

	public SMessage ejecutar(Integer id_carga) {
		String query = "SELECT * FROM $SOLICITUD NEAR SOCIEDAD$ WHERE IDCARGA = " + id_carga;
		SolicitudNearSociedad solicitud = DS_SqlUtils.queryForObject(SolicitudNearSociedad.class, query);
		
		String msg = "";
		Boolean exito = false;
		
		if ( expiroFechaVencimiento(solicitud.getFecha_vencimiento() ) ) {
			exito = actualizarEstadoRequerimiento(id_carga, Constantes.VENCIDO);
			msg = "La solicitud " + id_carga + " tiene estado de requerimiento " + Constantes.VENCIDO;
		} else if ( tieneRespuestaRequerimiento(id_carga) ){
			exito = actualizarEstadoRequerimiento(id_carga, Constantes.CREADO);
			msg = "La solicitud " + id_carga + " tiene estado de requerimiento " + Constantes.CREADO;
		}
		
		if(exito){
			SimpleLogger.setInfo(msg);
		}
		
		return new SMessage(exito, msg);
	}

	private Boolean actualizarEstadoRequerimiento(Integer id_carga, String estado) {
		String update = "UPDATE $SOLICITUD NEAR SOCIEDAD$ "
						+ "SET $SOLICITUD NEAR SOCIEDAD.ESTADO REQUERIMIENTO$ = $S(" + estado + ")$, "
						+ " $SOLICITUD NEAR SOCIEDAD.FECHA VENCIMIENTO$ = NULL "
						+ "WHERE IDCARGA = " + id_carga;
		return DS_SqlUtils.update(update);
	}

	private boolean tieneRespuestaRequerimiento(Integer id_carga) {
		//Se obtiene el historial de la carga en el proceso
		List<CargaInstancia> historial = CargaInstanciaServicio.getInstance().obtenerHistorialCargaInstancia(id_carga);
		CargaInstancia actual = null;
		
		//Buscamos la instancia actual de la carga
		for (CargaInstancia cargaInstancia : historial) {
			if(cargaInstancia.getFecha_salida() == null){
				actual = cargaInstancia;
			}
		}
		
		//Buscamos las solicitudes de $RESPUESTA REQUERIMIENTO$ que hayan sido creadas despues de la fecha de llegada a la instancia actual 
		String query = "SELECT IDCARGA "
						+ " FROM $RESPUESTA REQUERIMIENTO$ r "
						+ " INNER JOIN TS01 t on r.IDCARGA = t.ID_CARGA "
						+ " WHERE $RESPUESTA REQUERIMIENTO.NUMERO SOLICITUD$ = #id_carga# "
						+ " AND t.fecha_creacion > $fecha$";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fecha", actual.getFecha_llegada());
		params.put("id_carga", id_carga);
		
		Integer id_carga_respuesta = DS_SqlUtils.queryForObject(Integer.class, query, params);
		
		//Si se encuentra una solicitud returna true
		if(id_carga_respuesta != null){
			SimpleLogger.setInfo("La carga " + id_carga + " tiene respuesta en la solicitud " + id_carga_respuesta);
			return true;
		}
		return false;
	}

	public boolean expiroFechaVencimiento(Date fecha_vencimiento) {
		Date hoy = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(hoy); 
		c.add(Calendar.DATE, -1);
		hoy = c.getTime();
		return hoy.after(fecha_vencimiento);
	}

	public Boolean visualizar(Integer id_carga, Integer id_administrativo) {
		return true;
	}

}
