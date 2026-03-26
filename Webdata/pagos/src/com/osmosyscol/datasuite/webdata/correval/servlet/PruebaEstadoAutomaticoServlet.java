package com.osmosyscol.datasuite.webdata.correval.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.correval.thread.ActualizadorEstadoRetiro;
import com.osmosyscol.datasuite.webdata.correval.servicios.RetiroServicio;

public class PruebaEstadoAutomaticoServlet  extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doDownload(request, response);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
	}

	private void doDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Integer cantidadDiasHabilesT = Integer.parseInt(request.getParameter("dias"));
		
		Integer cantidadActualizados = RetiroServicio.getInstance().actualizarEstadosRetirosAntiguos(cantidadDiasHabilesT);
		
		 response.setContentType( "text/html" );
	      
	      // Always set the Content Type before data is printed
	      PrintWriter out = response.getWriter();
	      out.println( "<html>" );
	      out.println( "<head>" );
	      out.println( "<title>A Sample Servlet</title>" );
	      out.println( "</head>" );
	      out.println( "<body>" );
	      out.println( "<h1>Actualizados " + cantidadActualizados + "</h1>");
	      out.println( "</body>" );
	      out.println( "</html>" );
	      out.close();
		
	}
	
}
