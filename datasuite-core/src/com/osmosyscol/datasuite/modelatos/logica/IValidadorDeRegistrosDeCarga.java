package com.osmosyscol.datasuite.modelatos.logica;
import java.util.List;
import java.util.Map;

import com.osmosyscol.datasuite.modelatos.logica.dto.ValidacionEstructura;


public interface IValidadorDeRegistrosDeCarga {
	
	public Boolean esRegistroValido(Map<String, Object> registro, List<Map<String, Object>> registros, ValidacionEstructura validacionEstructura);

}
