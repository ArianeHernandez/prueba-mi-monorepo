package com.osmosyscol.commons.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * SimpleFilter to exclude jsessionid from requests.
 * Don't forget to include the declaaration in web.xml file (filter + filter-mapping). 
 * @see http://www.masterdlabs.es/eliminar-el-parametro-jsessionid-en-aplicaciones-web/
 * Used to exclude the jsessionid parameter from requests.
 * Other option is urlrewritefilter hosted at code.google.com  
 * @see https://code.google.com/p/urlrewritefilter/
 */
public class SkipJsessionidUrlRewriteFilter implements Filter {
	
	/**
	 * Logger declaration
	 */
	private static final Logger logger = LoggerFactory.getLogger(SkipJsessionidUrlRewriteFilter.class);

	/**
	 * Destroy implementation
	 */
	public void destroy() {
		if (logger.isInfoEnabled()) {
            logger.info("Filter (destroy)");
        }
	}

	/**
	 * Execute filter to remove jsessionid
	 */
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
		
		
		if (logger.isDebugEnabled()) {
            logger.debug("checking for status path on " + httpServletRequest.getRequestURL());
        }


		// Wrap and exclude jsessionidparam
		HttpServletResponseWrapper httpServletResponseWrapper = new HttpServletResponseWrapper(httpServletResponse)	{

			@Override
			public String encodeRedirectURL(String url) {
				return url;
			}

			@Override
			public String encodeRedirectUrl(String url) {
				return url;
			}

			@Override
			public String encodeURL(String url) {
				return url;
			}

			@Override
			public String encodeUrl(String url) {
				return url;
			}
			
		};
		
		// continue filter chain pattern ...
		filterChain.doFilter(httpServletRequest, httpServletResponseWrapper);
	}

	/**
	 * Init implementation
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		if (logger.isInfoEnabled()) {
            logger.info("Filter (init)");
        }
	}

}
