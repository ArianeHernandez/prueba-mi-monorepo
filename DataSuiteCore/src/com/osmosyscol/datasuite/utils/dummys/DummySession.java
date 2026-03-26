package com.osmosyscol.datasuite.utils.dummys;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.apache.cocoon.environment.Session;
import org.apache.commons.collections.iterators.IteratorEnumeration;

public class DummySession implements Session{
	
	private Map<String, Object> variables = new HashMap<String, Object>();
	
	private String id = null;

	public DummySession(){
		
	}
	
	@SuppressWarnings("rawtypes")
	public DummySession(Session session){
		Enumeration attrs = session.getAttributeNames();
		while (attrs.hasMoreElements()){
			String att = (String) attrs.nextElement();
			setAttribute(att, session.getAttribute(att));
		}
		id = session.getId();
	}
	
	public Object getAttribute(String arg0) {
		return variables.get(arg0);
	}

	@SuppressWarnings("rawtypes")
	public Enumeration getAttributeNames() {
		return new IteratorEnumeration(variables.keySet().iterator());
	}

	public long getCreationTime() {
		return 0;
	}

	public String getId() {
		return id;
	}

	public long getLastAccessedTime() {
		return 0;
	}

	public int getMaxInactiveInterval() {
		return 0;
	}

	public void invalidate() {
	}

	public boolean isNew() {
		return false;
	}

	public void removeAttribute(String arg0) {
		variables.remove(arg0);
		
	}

	public void setAttribute(String arg0, Object arg1) {
		variables.put(arg0, arg1);
	}

	public void setMaxInactiveInterval(int arg0) {
	}
	
}