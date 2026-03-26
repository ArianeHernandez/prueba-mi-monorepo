package com.osmosyscol.datasuite.webdata.logica.dto;

import java.util.List;

import com.osmosyscol.datasuite.logica.dto.Proceso;
import com.osmosyscol.datasuite.modelatos.logica.dto.Formato;

public class ProcesoFormato {

	private Proceso proceso;
	private List<Formato> formatoAsociados;
	
	
	public ProcesoFormato() {
	}
	
	public Proceso getProceso() {
		return proceso;
	}
	
	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}
	
	public void setFormatoAsociados(List<Formato> formatoAsociados) {
		this.formatoAsociados = formatoAsociados;
	}
	
	public List<Formato> getFormatoAsociados() {
		return formatoAsociados;
	}
}	