package com.osmosyscol.datasuite.modelatos.logica.hilo;

import java.net.URL;
import java.util.Date;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.AutenticacionServicio;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.modelatos.logica.dto.Tarea;
import com.osmosyscol.datasuite.modelatos.logica.servicios.TareaServicio;
import com.osmosyscol.datasuite.wsclient.ServicioSoapBindingStub;

/**
 * @author oaortizs
 * 
 */

public class TareaHilo extends Thread {

	private Integer id_tarea;

	private Boolean ejecutar = false;

	private Boolean iniciado = false;

	private Boolean enEjecucion = false;

	private Date proximaEjecucion = null;

	public TareaHilo(Integer id_tarea) {

		this.id_tarea = id_tarea;

	}

	@Override
	public void run() {
		iniciarEjecucion();
		finEjecucion();
	}


	private void finEjecucion() {
		TareaServicio.getInstance().finalizaTarea(id_tarea);
	}

	//
	private void iniciarEjecucion() {

		try {
			while (iniciado) {

				Tarea tarea = TareaServicio.getInstance().obtenerTarea(id_tarea);
				Date date = new Date();
				if (date.compareTo(tarea.getFecha_inicio()) >= 0) {
					ejecutar = true;
					proximaEjecucion = new Date();
					ejecutarOperacion();
				}
				Thread.sleep(Constantes.TAREA_TIEMPO_SLEEP * 1000);
			}
		} catch (Exception e) {
			SimpleLogger.setError("Error en el inicio de la ejecucion", e);
		}

	}

	private Date inicioEjecucion = null;
	
	public Date getInicioEjecucion() {
		return inicioEjecucion;
	}

	private void ejecutarOperacion() {

		inicioEjecucion = new Date();
		
		while (ejecutar && !AutenticacionServicio.SHUTDOWN) {

			try {

				while (proximaEjecucion.compareTo(new Date()) > 0 && ejecutar ) {
					Thread.sleep(Constantes.TAREA_TIEMPO_SLEEP * 1000);
				}

				if (ejecutar) {
					// Llamado al WS
					enEjecucion = true;

					ejecutarWS();

					enEjecucion = false;

				}
				proximaEjecucion = TareaServicio.getInstance().calcularProximaEjecucion(inicioEjecucion, id_tarea);

				SimpleLogger.setInfo("Proxima ejecución de tarea " + id_tarea + " " + proximaEjecucion);
			} catch (Throwable e) {
				SimpleLogger.setError("Error durante la ejecucion de la operacion", e);
				ejecutar = false;
			}

		}

	}

	private void ejecutarWS() {

		try {

			Tarea tarea = TareaServicio.getInstance().obtenerTarea(id_tarea);

			if (tarea == null || !StringUtils.esVerdad(tarea.getActivar())) {
				return;
			}

			inicioEjecucion = new Date();
			
			URL url = new URL(tarea.getEnd_point());
			ServicioSoapBindingStub stub = new ServicioSoapBindingStub(url, null);

			Boolean rta = stub.operacion();
			SimpleLogger.setInfo("Finaliza ejecucion de tarea: " + tarea.getNombre() + " con respuesta: " + rta);

		} catch (Exception e) {
			SimpleLogger.setError("Error llamando webservice", e);
		}
	}

	public void detener() {
		iniciado = false;
		ejecutar = false;
	}

	public void reiniciar() {
		ejecutar = false;
		iniciado = true;
	}

	public Boolean esProcesoEnEjecucion() {
		return enEjecucion;
	}

	public Boolean enEjecucion() {
		return enEjecucion || ejecutar;
	}

	public Boolean iniciar() {
		Tarea tarea = TareaServicio.getInstance().obtenerTarea(id_tarea);
		if (tarea == null || tarea.getTipo_intervalo() == null || tarea.getIntervalo_ejecucion() == null) {
			return false;
		}
		if (!enEjecucion) {
			iniciado = true;
			start();
			return true;
		}
		return false;
	}
	
	public Boolean iniciado(){
		return iniciado;
	}
}
