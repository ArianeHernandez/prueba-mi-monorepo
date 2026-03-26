package com.osmosyscol.datasuite.utils;

import java.lang.reflect.Method;
import java.util.Date;

public class ThreadUtils implements Runnable {

	private Object objeto;
	private Object[] parametros;
	private Method metodo;

	public ThreadUtils() {
	}
	
	public ThreadUtils(Object objeto, Method metodo, Object[] parametros) {
		this.objeto = objeto;
		this.parametros = parametros;
		this.metodo = metodo;
	}

	public static Boolean newThread(Object obj, String methodName, Object... params) {

		if (obj != null) {
			Method[] methods = obj.getClass().getMethods();

			for (Method method : methods) {

				if (method.getName().equals(methodName)) {

					boolean equ = true;

					Class<?>[] paramsclass = method.getParameterTypes();

					if ((params != null && params.length > 0) || (paramsclass != null && paramsclass.length > 0)) {

						if (params != null && paramsclass != null && params.length == paramsclass.length) {

							equ = true;

						} else {
							equ = false;
						}
					}

					if (equ) {

						new Thread(new ThreadUtils(obj, method, params)).start();

						return true;
					}

				}
			}

		}

		return false;
	}

	@Override
	public void run() {

		
		try {

			metodo.invoke(objeto, parametros);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
