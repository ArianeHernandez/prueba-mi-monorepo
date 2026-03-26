package com.osmosyscol.datasuite.logica.dto;

import com.osmosyscol.datasuite.logica.servicios.ConfiguracionServicio;

public class FormatoConfiguracion {
	
	private Integer id_formato;
	private Integer id_configuracion;
	private String  tipo;

	public Integer getId_formato() {
		return id_formato;
	}

	public void setId_formato(Integer id_formato) {
		this.id_formato = id_formato;
	}

	public Integer getId_configuracion() {
		return id_configuracion;
	}

	public void setId_configuracion(Integer id_configuracion) {
		this.id_configuracion = id_configuracion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getValor(){
		if (id_configuracion != null){
			return ConfiguracionServicio.getInstance().obtenerValorById(id_configuracion);
		}
		return null;
	}

}
