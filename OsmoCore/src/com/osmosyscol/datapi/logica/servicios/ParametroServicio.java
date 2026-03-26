package com.osmosyscol.datapi.logica.servicios;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datapi.logica.dto.Parametro;


public class ParametroServicio {

	private static ParametroServicio ParametroServicio;

	private ParametroServicio() {
	}

	public static ParametroServicio getInstance() {
		if (ParametroServicio == null) {
			ParametroServicio = new ParametroServicio();
		}
		return ParametroServicio;
	}
	
	public List<String> getTiposDeDato() {
		List<String> lista = new ArrayList<String>();
		Class<Parametro> c = Parametro.class;
		for (Field f : c.getDeclaredFields()) {
		    int mod = f.getModifiers();
		    if (Modifier.isStatic(mod) && Modifier.isPublic(mod) && Modifier.isFinal(mod)) {
		      try {
		    	  lista.add((String) f.get(null));
		      } catch (IllegalAccessException e) {
		    	  SimpleLogger.setError("Error en ParametroServicio.getTiposDeDato: ", e);
		      }
		    }
		  }
		return lista;
	}

}
