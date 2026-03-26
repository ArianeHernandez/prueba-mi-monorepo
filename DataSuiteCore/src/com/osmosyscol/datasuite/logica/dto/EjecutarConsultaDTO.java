package com.osmosyscol.datasuite.logica.dto;

import java.util.List;
import java.util.Map;

public class EjecutarConsultaDTO {
	
	private List<Map<String, Object>> resultado;
	
	private List<String> campos;

	public List<Map<String, Object>> getResultado() {
		return resultado;
	}

	public void setResultado(List<Map<String, Object>> resultado) {
		this.resultado = resultado;
	}

	public List<String> getCampos() {
		return campos;
	}

	public void setCampos(List<String> campos) {
		this.campos = campos;
	}

}
