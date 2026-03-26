package com.osmosyscol.datasuite.servlet;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

import co.htsoft.htsauditoria.client.HTSAuditoriaServicio;
import co.htsoft.htsauditoria.client.dto.ERequest;

public class AuditoriaFilter implements javax.servlet.Filter {

	public AuditoriaFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		try {

			HttpServletRequest http_request = (HttpServletRequest) request;
			
			String data = new Gson().toJson(request.getParameterMap());
			
			System.out.println(data);

			ERequest erequest = HTSAuditoriaServicio.setRequest(http_request, data.getBytes());

			chain.doFilter(request, response);

			HTSAuditoriaServicio.endRequest(http_request, erequest, null);

		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}
