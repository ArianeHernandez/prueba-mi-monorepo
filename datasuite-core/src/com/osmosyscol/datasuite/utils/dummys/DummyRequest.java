package com.osmosyscol.datasuite.utils.dummys;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import org.apache.cocoon.environment.Cookie;
import org.apache.cocoon.environment.Request;
import org.apache.cocoon.environment.Session;

public class DummyRequest implements Request{

	public Object get(String arg0) {
		
		return null;
	}

	public Object getAttribute(String arg0) {
		
		return null;
	}

	@SuppressWarnings("rawtypes")
	public Enumeration getAttributeNames() {
		
		return null;
	}

	public String getAuthType() {
		
		return null;
	}

	public String getCharacterEncoding() {
		
		return null;
	}

	public int getContentLength() {
		
		return 0;
	}

	public String getContentType() {
		
		return null;
	}

	public String getContextPath() {
		
		return null;
	}

	@SuppressWarnings("rawtypes")
	public Map getCookieMap() {
		
		return null;
	}

	public Cookie[] getCookies() {
		
		return null;
	}

	public long getDateHeader(String arg0) {
		
		return 0;
	}

	public String getHeader(String arg0) {
		
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public Enumeration getHeaderNames() {
		
		return null;
	}

	@SuppressWarnings("rawtypes")
	public Enumeration getHeaders(String arg0) {
		
		return null;
	}

	public Locale getLocale() {
		
		return null;
	}

	@SuppressWarnings("rawtypes")
	public Enumeration getLocales() {
		
		return null;
	}

	public String getMethod() {
		
		return null;
	}

	public String getParameter(String arg0) {
		
		return null;
	}

	@SuppressWarnings("rawtypes")
	public Enumeration getParameterNames() {
		
		return null;
	}

	public String[] getParameterValues(String arg0) {
		
		return null;
	}

	public String getPathInfo() {
		
		return null;
	}

	public String getPathTranslated() {
		
		return null;
	}

	public String getProtocol() {
		
		return null;
	}

	public String getQueryString() {
		
		return null;
	}

	public String getRemoteAddr() {
		
		return null;
	}

	public String getRemoteHost() {
		return "0.0.0.0";
	}

	public String getRemoteUser() {
		
		return null;
	}

	public String getRequestURI() {
		
		return null;
	}

	public String getRequestedSessionId() {
		
		return null;
	}

	public String getScheme() {
		
		return null;
	}

	public String getServerName() {
		
		return null;
	}

	public int getServerPort() {
		
		return 0;
	}

	public String getServletPath() {
		
		return null;
	}

	public Session getSession() {
		
		return null;
	}

	public Session getSession(boolean arg0) {
		
		return null;
	}

	public String getSitemapURI() {
		
		return null;
	}

	public String getSitemapURIPrefix() {
		
		return null;
	}

	public Principal getUserPrincipal() {
		
		return null;
	}

	public boolean isRequestedSessionIdFromCookie() {
		
		return false;
	}

	public boolean isRequestedSessionIdFromURL() {
		
		return false;
	}

	public boolean isRequestedSessionIdValid() {
		
		return false;
	}

	public boolean isSecure() {
		
		return false;
	}

	public boolean isUserInRole(String arg0) {
		
		return false;
	}

	public void removeAttribute(String arg0) {
		
		
	}

	public void setAttribute(String arg0, Object arg1) {
		
		
	}

	public void setCharacterEncoding(String arg0) throws UnsupportedEncodingException {
		
		
	}
	
}
