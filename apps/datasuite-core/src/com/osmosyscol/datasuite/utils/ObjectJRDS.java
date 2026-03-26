package com.osmosyscol.datasuite.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.osmosyscol.commons.log.SimpleLogger;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class ObjectJRDS implements JRDataSource {

	private Iterator<?> it;
	private Object obj;
	private Object baseobj;

	public ObjectJRDS(Object obj) {

		baseobj = obj;

		SimpleLogger.setDebug("Creando Datasource de " + obj + " - " + this);

		if (obj instanceof List) {
			this.it = ((List<?>) obj).iterator();
		} else {
			List<Object> l = new ArrayList<Object>();
			l.add(obj);
			this.it = l.iterator();
		}
	}

	public Object getFieldValue(JRField field) throws JRException {

		try {

			SimpleLogger.setDebug("Obteniendo campo " + field.getName() + " de " + obj + " - " + this);

			boolean isDatasource = false;

			String fieldName = field.getName();

			if (fieldName.equals("ThisObject")) {
				return obj;
			}

			if (fieldName.endsWith("_JRDS")) {
				fieldName = fieldName.substring(0, fieldName.length() - 5);
				isDatasource = true;
			}

			Method m = obj.getClass().getMethod("get" + fieldName);

			Object o = m.invoke(obj, new Object[0]);

			if (isDatasource) {
				return new ObjectJRDS(o);
			} else {
				return o;
			}

		} catch (Throwable e) {
			SimpleLogger.setError(e.getMessage(), e);
		}
		finally{
			SimpleLogger.setDebug("fin getFieldValue");
		}
		

		return null;
	}

	public boolean next() throws JRException {

		try {

			SimpleLogger.setDebug("Obteniendo siguiente de " + baseobj + " - " + this);
			
			if (it.hasNext()) {
				obj = it.next();

				SimpleLogger.setDebug("true");
				
				return true;
			}

			SimpleLogger.setDebug("Fin de registros.. " + baseobj + " - " + this);
			return false;

		} catch (Throwable e) {
			SimpleLogger.setError(e.getMessage(), e);
		}
		
		SimpleLogger.setDebug("error");

		return false;
	}

}
