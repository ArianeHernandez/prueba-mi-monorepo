package com.osmosyscol.datasuite.reportedim.dto;

import java.util.List;
import java.util.Map;

import com.osmosyscol.datasuite.config.Constantes;

public class ResultadoConsulta {

	private List<Map<String, Object>> datos;
	private Integer total_registros = 0;
	private Integer cantidad_pagina = Constantes.PAGINACIONLISTADO + 0;

	public List<Map<String, Object>> getDatos() {
		return datos;
	}

	public void setDatos(List<Map<String, Object>> datos) {
		this.datos = datos;
	}

	public Integer getTotal_paginas() {

		if (total_registros == 0) {
			return 0;
		}

		return ((total_registros - 1) / cantidad_pagina) + 1;
	}

	public Integer getTotal_registros() {
		return total_registros;
	}

	public void setTotal_registros(Integer total_registros) {
		this.total_registros = total_registros;
	}

	public Integer getCantidad_pagina() {
		return cantidad_pagina;
	}

	public void setCantidad_pagina(Integer cantidad_pagina) {
		this.cantidad_pagina = cantidad_pagina;
	}

}
