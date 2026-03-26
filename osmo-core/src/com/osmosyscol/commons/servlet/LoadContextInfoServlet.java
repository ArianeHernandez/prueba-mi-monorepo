package com.osmosyscol.commons.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.osmosyscol.datapi.constantes.VariablesAplicacion;

public class LoadContextInfoServlet extends HttpServlet {
	
	
	
	public LoadContextInfoServlet() {
		super();
	}


	// #region 01 logger declaration ---------------------
	private static final Logger logger = LoggerFactory.getLogger(VariablesAplicacion.class);
	// #endregion 01 -------------------------------------
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Override
	public void init() throws ServletException {
		super.init();
		
		if( logger.isInfoEnabled() ) {
			logger.info("loading context information: servlet init() ...");
		}
		
		logger.info(String.format( "loading context information method=%s" , "servlet init()" ));
		
		ContextInfo.getInstance().autoConfigure( getServletConfig().getServletContext());
		
	}

}
