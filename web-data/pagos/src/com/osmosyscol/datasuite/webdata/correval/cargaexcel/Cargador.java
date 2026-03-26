package com.osmosyscol.datasuite.webdata.correval.cargaexcel;

import java.util.Map;

public interface Cargador {
	
	public void cargar(String ruta, Integer idCargue, Map<Integer, Object> mapaRespuestas );

	public String getCodigo();
}
