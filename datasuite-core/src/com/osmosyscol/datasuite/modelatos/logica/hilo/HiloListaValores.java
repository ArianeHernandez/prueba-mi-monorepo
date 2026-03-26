package com.osmosyscol.datasuite.modelatos.logica.hilo;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.AutenticacionServicio;
import com.osmosyscol.datasuite.modelatos.logica.dto.ListaValores;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ListaValoresServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class HiloListaValores implements Runnable {

	public void run() {

		
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e2) {
			return;
		}

		// -------------------

		SimpleLogger.setInfo("Iniciando Carga automatica de listas de valores");

		Long espera = new Long(60);
		Long minutosEjecucion = new Long(0);

		String strInt = ParametrosInicio.getProperty("listavalores.intervalo");

		if (StringUtils.isNotEmpty(strInt) && StringUtils.isNumeric(strInt)) {
			espera = new Long(strInt);
		} else {
			SimpleLogger.setError("La propiedad de inicio 'listavalores.intervalo' no es valida: " + strInt);
		}

		try {
			while (!AutenticacionServicio.SHUTDOWN) {

				ListaValoresServicio instance = ListaValoresServicio.getInstance();
				List<ListaValores> listasValores = instance.obtenerListasDeValores(1);

				boolean existefallo = false;

				if (listasValores != null) {

					for (ListaValores listaValores : listasValores) {
						Integer intervalo = listaValores.getIntervalo_actualizacion();
						if (intervalo != null && intervalo > 0 
								&& com.osmosyscol.commons.utils.StringUtils.esNoVacio(listaValores.getC_id(), listaValores.getC_nombre(), listaValores.getConsulta())) {
							Boolean rta = instance.actualizarValoresBD(listaValores);
							SimpleLogger.setDebug("Lista de Valores (" + listaValores.getNombre() + ") actualizada: " + (rta ? "EXITO" : "FALLO"));
							if (!rta) {
								existefallo = true;
							}
						}

					}

				}
				if (existefallo) {
					SimpleLogger.setInfo("Ha finalizado la sincronizacion con errores.. Proxima ejecución en 10 segundos.");

					Thread.sleep(10000);

				} else {
					SimpleLogger.setInfo("Ha finalizado la sincronizacion exitosamente. Proxima ejecución en " + espera + " minutos.");

					Thread.sleep(espera * 60 * 1000);
				}
				minutosEjecucion += espera;

			}
		} catch (InterruptedException e1) {
			SimpleLogger.setInfo("Ha finalizado el hilo de HiloListaValores");
			return;
		
		} catch (Exception e) {
			SimpleLogger.setError("Error ejecutando hilo de listas de valores", e);
		}

	}

}
