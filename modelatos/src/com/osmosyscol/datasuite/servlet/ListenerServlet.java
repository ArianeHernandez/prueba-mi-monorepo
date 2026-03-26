package com.osmosyscol.datasuite.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.AutenticacionServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.TareaServicio;

@SuppressWarnings("serial")
public class ListenerServlet extends javax.servlet.http.HttpServlet implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		if (TareaServicio.getInstance().iniciarTareasProgramadas()) {
			TareaServicio.getInstance().finalizarTareas();
			SimpleLogger.setInfo("Las tareas se finalizaron con exito");
		}
		
		AutenticacionServicio.getInstance().finalizarHilos();
	}

	public void contextInitialized(ServletContextEvent arg0) {

	}

	public void init() throws ServletException {

		if (TareaServicio.getInstance().iniciarTareasProgramadas()) {

			Boolean rta = TareaServicio.getInstance().iniciarTareas();
			if (rta) {
				SimpleLogger.setInfo("Todas las tareas se iniciaron con éxito");
			} else {
				SimpleLogger.setInfo("Las tareas no han sido iniciadas");
			}
		}
		

	}
}
