package com.osmosyscol.datasuite.webdata.correval.cargueplano;

import java.util.List;

import com.osmosyscol.datasuite.webdata.correval.cargueplano.dto.Retiro;

public interface LectorArchivoPlano {

	public List<Retiro> leerRetiros(String archivo);
	
	public String getCodBanco();
	
}
