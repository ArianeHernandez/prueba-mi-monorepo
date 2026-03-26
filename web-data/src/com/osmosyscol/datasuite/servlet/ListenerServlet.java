package com.osmosyscol.datasuite.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;

import com.osmosyscol.commons.servicio.AutenticacionServicio;
import com.osmosyscol.datasuite.mein.servicios.cron.CronActualizarCargasPendienteAnulacion;
import com.osmosyscol.datasuite.mein.servicios.cron.CronActualizarDocumentoSalida;
import com.osmosyscol.datasuite.near.servicios.ReporteSolicitudNEARServicio;
import com.osmosyscol.datasuite.pagos.services.ActualizadorEstadoOrden;
import com.osmosyscol.datasuite.webdata.correval.ordenespago.CronReportePagos;
import com.osmosyscol.datasuite.webdata.correval.ordenespago.EnvioNotificacionBeneficiario;
import com.osmosyscol.datasuite.webdata.logica.servicios.CronAccionesAutomaticas;
import com.osmosyscol.datasuite.webdata.logica.servicios.CronServicio;

@SuppressWarnings("serial")
public class ListenerServlet extends javax.servlet.http.HttpServlet implements ServletContextListener{

	public static boolean running = true; 
	
	public void contextDestroyed(ServletContextEvent arg0) {
		
		running = false;
		
		CronServicio.finalizar();
		CronAccionesAutomaticas.finalizar();
		AutenticacionServicio.getInstance().finalizarHilos();
//		CronReportePagos.finalizar();
		CronActualizarDocumentoSalida.finalizar();
		CronActualizarCargasPendienteAnulacion.finalizar();
		
	}

	public void contextInitialized(ServletContextEvent arg0) {
	}

	public void init() throws ServletException {
		
		CronServicio.iniciar();
		CronAccionesAutomaticas.iniciar();
		CronActualizarDocumentoSalida.iniciar();
		CronActualizarCargasPendienteAnulacion.iniciar();
		// Se comenta cron CronReportePagos para proyecto SSOC
//		CronReportePagos.iniciar();
		// Se comenta cron EnvioNotificacionBeneficiario para proyecto SSOC
//		EnvioNotificacionBeneficiario.iniciar();
		ReporteSolicitudNEARServicio.getInstance().regenerarVista();
		ReporteSolicitudNEARServicio.getInstance().regenerarVistaHistorico();
		
		// Se comenta cron ActualizadorEstadoOrden para proyecto SSOC
//		ActualizadorEstadoOrden.iniciar();
		
	}
	
}
