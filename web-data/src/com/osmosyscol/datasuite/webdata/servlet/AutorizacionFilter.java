package com.osmosyscol.datasuite.webdata.servlet;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.cocoon.environment.Request;
import org.apache.cocoon.environment.Session;
import org.apache.commons.io.IOUtils;

import co.htsoft.commons.lang.P;

import com.google.gson.Gson;
import com.osmosyscol.commons.utils.CocoonUtils;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.dto.Credencial;
import com.osmosyscol.datasuite.webdata.logica.dto.MethodRPC;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

@WebFilter(filterName = "AutorizacionFilter", urlPatterns = { "/JSON-RPC" })
public class AutorizacionFilter implements Filter {

	static Set<String> urls = null;
	static Set<String> public_urls = null;
	static Set<String> private_urls = null;

	// Se inicializan los metodos de uso publico
	static {
		public_urls = new HashSet<String>();
		public_urls.add("system.listMethods");
		public_urls.add("pcore.getCurrentTime");
		public_urls.add("pcore.calcularDigitoVerificacion");
		public_urls.add("pcore.getAplicationName");
		public_urls.add("pcore.setErrorLog");
		public_urls.add("pcore.validarCorreo");
		public_urls.add("usuarioNegocioServicio.estaUsuarioBloqueado");
		public_urls.add("cuentaJSONServicio.aceptaTerminosCondiciones");
		public_urls.add("archivosAdjJsonServicio.obtenerSiguiente");
		
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		SMRequest nrequest = new SMRequest((HttpServletRequest) request);
		SMResponse eresponse = new SMResponse((HttpServletResponse) response);
		
		try {
			
			Boolean tokenSesionIngresado = null;
			
			Session session = CocoonUtils.getCocoonSession((HttpServletRequest) request);
			if(session != null){
				tokenSesionIngresado = (Boolean) session.getAttribute("tokenSesionIngresado");
			}
			
			// Mapear el objeto request del JSON-RPC
			String json = new String(nrequest.getBody());
			MethodRPC objetoRequest = new Gson().fromJson(json,MethodRPC.class);
			
			// Validar si la aplicacion tiene configurado solicitar token de ingreso
			
			if (!StringUtils.esVerdad(ParametrosInicio.getProperty("tokeningreso.cliente")) || (session != null && session.getAttribute("id_usuario") == null ) || (tokenSesionIngresado != null && tokenSesionIngresado) || public_urls.contains(objetoRequest.getMethod()) ) {
				chain.doFilter(nrequest, eresponse);
				OutputStream out = response.getOutputStream();
				byte[] bytes = eresponse.toByteArray();
				out.write(bytes);
				return;
			}
			
			// No se permite el paso en cualquier otro caso
			OutputStream out = response.getOutputStream();
			byte[] bytes = "{ \"info\": \"Permisos insuficientes\" }".getBytes();
			out.write(bytes);
			return;
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}

class SMRequest extends HttpServletRequestWrapper {

	private byte[] body;

	public SMRequest(HttpServletRequest request) {
		super(request);

		try {
			body = IOUtils.toByteArray(request.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public byte[] getBody() {
		return body;
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		return new CachedServletInputStream();
	}

	public class CachedServletInputStream extends ServletInputStream {
		private ByteArrayInputStream input;

		public CachedServletInputStream() {
			input = new ByteArrayInputStream(body);
		}

		@Override
		public int read() throws IOException {
			return input.read();
		}
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(getInputStream()));
	}

}

class SMResponse extends HttpServletResponseWrapper {

	private ByteArrayOutputStream output;
	private CharArrayWriter c_output;

	public byte[] toByteArray() {

		if (output != null) {
			return output.toByteArray();
		}

		if (c_output != null) {
			return c_output.toString().getBytes();
		}

		return new byte[] {};
	}

	public SMResponse(HttpServletResponse response) {
		super(response);
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {

		if (output == null) {
			output = new ByteArrayOutputStream();
		}

		return new ServletOutputStream() {
			@Override
			public void write(int b) throws IOException {
				output.write(b);
			}
		};
	}

	@Override
	public PrintWriter getWriter() throws IOException {

		if (c_output == null) {
			c_output = new CharArrayWriter();
		}

		return new PrintWriter(c_output);
	}

}