package com.osmosyscol.datasuite.webdata.logica.dto;

import java.util.List;
import java.util.Map;

import com.osmosyscol.datasuite.modelatos.logica.dto.FormatoCampo;

public class Hoja {

	private FormatoCampo formatocampo;

	private List<Map<String, String>> registro;

	// -----------------

	public FormatoCampo getFormatocampo() {
		return formatocampo;
	}

	public void setFormatocampo(FormatoCampo formatocampo) {
		this.formatocampo = formatocampo;
	}

	public List<Map<String, String>> getRegistro() {
		return registro;
	}

	public void setRegistro(List<Map<String, String>> registro) {
		this.registro = registro;
	}

}
