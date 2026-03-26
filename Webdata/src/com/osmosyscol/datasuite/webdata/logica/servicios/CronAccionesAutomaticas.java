package com.osmosyscol.datasuite.webdata.logica.servicios;

import java.util.List;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.JobServicio;
import com.osmosyscol.datasuite.logica.dto.CargaAccionAutomatica;
import com.osmosyscol.datasuite.logica.dto.InstanciaAccion;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValorLista;
import com.osmosyscol.datasuite.modelatos.logica.servicios.AccionServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.InstanciaServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ListaDinamicaServicio;
import com.osmosyscol.datasuite.servlet.ListenerServlet;
import com.osmosyscol.datasuite.utils.dummys.DummySession;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

import co.htsoft.commons.lang.P;

public class CronAccionesAutomaticas {

	private static CronAccionesAutomaticas instance;
	private static Boolean EN_USO = false;
	private static Integer INTENTOS = 0;
	
	private CronAccionesAutomaticas() {
	}

	public static CronAccionesAutomaticas getInstance() {
		if (instance == null) {
			instance = new CronAccionesAutomaticas();
		}
		return instance;
	}

	public static void iniciar() {

		SimpleLogger.setDebug("Se inicia cron de acciones automaticas");
		
		String expression = ParametrosInicio.getProperty("CronAccionesAutomaticas");
		
		if (expression == null) {
			expression = "0 0/1 * * * ?";
		}
		
		JobServicio.getInstance().callService(CronAccionesAutomaticas.class, "ejecutar", null, expression);

	}

	public void ejecutar() {
		if (!EN_USO) {
			try {
				EN_USO = true;
				List<CargaAccionAutomatica> cargasAutomaticas = AccionServicio.getInstance().obtenerCargasAccionesAutomaticas();
		
				if (cargasAutomaticas != null && !cargasAutomaticas.isEmpty()) {
					SimpleLogger.setInfo("Ejecutando Cron Acciones Automaticas.");
		
					for (CargaAccionAutomatica ca : cargasAutomaticas) {
						SimpleLogger.setInfo("Iniciando accion automatica " + ca.getId_accion() + " para la carga " + ca.getId_carga());
						DummySession session = new DummySession();
						session.setAttribute("id_carga", ca.getId_carga());
						ListaDinamicaServicio servicio = ListaDinamicaServicio.getInstance();
						List<ValorLista> valores = servicio.obtenerValoresListaDinamica(ca.getId_lista_dinamica(), session);
						if (!valores.isEmpty()) {
							SimpleLogger.setDebug("Ejecucion automatica OK: " + ca.getId_lista_dinamica() + ", id_carga: " + ca.getId_carga());
							Boolean ejecutado = AccionServicio.getInstance().ejecutarAccionDeInstanciaActual(ca.getId_accion(), ca.getId_carga(), ca.getId_instancia(), null).getValid();
		
							if (ejecutado) {
								InstanciaAccion instanciaAccion = new InstanciaAccion();
								instanciaAccion.setId_accion(ca.getId_accion());
								instanciaAccion.setId_administrativo(null);
								instanciaAccion.setId_carga(ca.getId_carga());
								instanciaAccion.setId_instancia(ca.getId_instancia());
								instanciaAccion.setId_proceso_admin(InstanciaServicio.getInstance().obtenerInstancia(ca.getId_instancia()).getId_proceso_admin());
		
								AccionServicio.getInstance().enviarNotificacionesPorAccion(instanciaAccion, null);
							}
		
						} else {
							SimpleLogger.setDebug("Ejecucion automatica NO PERMITIDA: " + ca.getId_lista_dinamica() + ", id_carga: " + ca.getId_carga());
						}
						SimpleLogger.setInfo("Finalizando accion automatica " + ca.getId_accion() + " para la carga " + ca.getId_carga());
						
					}
				}
			} catch (Exception e) {
				SimpleLogger.setError("Error en la ejecución del cron AccionesAutomaticas: ", e);
			} finally {
				EN_USO = false;
				INTENTOS = 0;
			}
		} else {
			INTENTOS++;
			SimpleLogger.setInfo("El Cron de acciones automáticas no se inició debido a que ya existe un proceso en ejecucion. Intentos " + INTENTOS);
			
			try {
				Integer intentos = Integer.parseInt(ParametrosInicio.getProperty("CronAccionesAutomaticas.intentos"));
				
				if (INTENTOS.intValue() >= intentos.intValue()) {
					EN_USO = false;
					INTENTOS = 0;
					SimpleLogger.setInfo("Se desbloquea Cron de acciones automaticas.");
				}
			} catch (Exception e) {
				SimpleLogger.setError("Error en la validacion de intentos para desbloquear.");
			}
			
		}
		
		SimpleLogger.setInfo("Finaliza Cron de acciones automaticas.");
	}

	public static void finalizar() {
		try {
			JobServicio.getScheduler().shutdown();
			SimpleLogger.setDebug("Se finaliza servicio cron");
		} catch (Throwable e) {
			SimpleLogger.setError("Error finalizando job", e);
		}
	}
	
}
