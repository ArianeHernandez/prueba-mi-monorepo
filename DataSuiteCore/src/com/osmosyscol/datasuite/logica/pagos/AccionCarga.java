package com.osmosyscol.datasuite.logica.pagos;

import co.htsoft.commons.util.SMessage;

public interface AccionCarga {

	public SMessage ejecutar(Integer id_carga); 
	
	public Boolean visualizar(Integer id_carga, Integer id_administrativo);
	
}
