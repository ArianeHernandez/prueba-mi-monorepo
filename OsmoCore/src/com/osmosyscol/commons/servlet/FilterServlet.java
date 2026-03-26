package com.osmosyscol.commons.servlet;

//import org.apache.cocoon.environment.Request;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cocoon.servlet.CocoonServlet;

import com.osmosyscol.commons.utils.CocoonUtils;
import com.osmosyscol.commons.utils.ServletUtils;

public class FilterServlet extends CocoonServlet  {
	
	private static final long serialVersionUID = 1L;

	
	public void init() throws ServletException{
		//void
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		boolean res = filtrarParametros(request, response);
		
		if (res) {
			super.doGet(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		boolean res = filtrarParametros(request, response);
		
		if (res) {
			super.doPost(request, response);
		}
	}
	
	protected void doTrace(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ip = CocoonUtils.getRemoteAddr(request);
		
		System.out.println("CSRF -- división de cabeceras HTTP detenida. Petición TRACE sospechosa recibida desde: " + ip);
	}
	
	
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ip = CocoonUtils.getRemoteAddr(request);

		System.out.println("CSRF -- división de cabeceras HTTP detenida. Petición OPTIONS sospechosa recibida desde: " + ip);
	}
	
	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean res = filtrarParametros(request, response);
		if (res) {
			super.service(request, response);
		} else {
			String ip = CocoonUtils.getRemoteAddr(request);

			System.out.println("CSRF -- división de cabeceras HTTP detenida. Petición sospechosa recibida desde: " + ip);
		}
	}
	
	
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {

		boolean res = filtrarParametros(request, response);
		if (res) {
			super.service(request, response);
		} else {
			
			String ip = CocoonUtils.getRemoteAddr(request);
			
			System.out.println("CSRF -- división de cabeceras HTTP detenida. Petición sospechosa recibida desde: " + ip);
		}
	}
	
	private boolean filtrarParametros(ServletRequest request,
			ServletResponse response) {
		
		return filtrarParametros((HttpServletRequest)request, (HttpServletResponse) response);
	}

	private boolean filtrarParametros(HttpServletRequest request, HttpServletResponse response) {
		
		// Parámetro sospechoso = false
		boolean validacionParametros = ServletUtils.validarRequestParameter(request);
		
		return validacionParametros;
	}
	
}