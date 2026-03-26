package com.osmosyscol.datasuite.webdata.webservices;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ListaValoresServicio;

public class WSListaValores {

	public Boolean actualizarListaValores(Integer idListaValores){
		
		try {
			
			ListaValoresServicio.getInstance().eliminarListaValoresCache(idListaValores);
			
			return true;
		} catch (Exception e) {
			SimpleLogger.setError("Error actualizando cache de lista de valores", e);
		}
		return false;
	}
	
}
