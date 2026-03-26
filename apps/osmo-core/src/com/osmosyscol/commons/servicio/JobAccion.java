// CVS $Id$

package com.osmosyscol.commons.servicio;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.osmosyscol.commons.log.SimpleLogger;

public class JobAccion implements Job {

	public static Map<String, Object> dataJob = new HashMap<String, Object>();

	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();

		try {
			String ticket = dataMap.getString("ticket");
			String className = dataMap.getString("classname");
			String operation = dataMap.getString("operation");
			Object[] parameters = (Object[]) dataMap.get("parameters");

			Class<?> clazz = Class.forName(className);
			Method method = clazz.getMethod("getInstance", new Class[0]);
			Object object = method.invoke(null, new Object[0]);

			Method[] methods = clazz.getMethods();

			for (Method metodo : methods) {

				if (metodo.getName().equals(operation)) {
					dataJob.put(ticket, metodo.invoke(object, parameters));
				}
			}

		} catch (Exception e) {
			SimpleLogger.setError("Error al ejecutar servicio Asincronico", e);
		}

	}
}
