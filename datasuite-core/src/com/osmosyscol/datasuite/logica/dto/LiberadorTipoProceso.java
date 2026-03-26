package com.osmosyscol.datasuite.logica.dto;


public class LiberadorTipoProceso {

	private Integer id_liberador;
	private Integer id_tipo_proceso;
	private Integer peso;
	private Long valor_max_individual;
	private Long valor_max_carga;
	

	// ---------------------------------

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public Long getValor_max_individual() {
		return valor_max_individual;
	}

	public void setValor_max_individual(Long valor_max_individual) {
		this.valor_max_individual = valor_max_individual;
	}

	public Long getValor_max_carga() {
		return valor_max_carga;
	}

	public void setValor_max_carga(Long valor_max_carga) {
		this.valor_max_carga = valor_max_carga;
	}

	public Integer getId_liberador() {
		return id_liberador;
	}
	
	public void setId_liberador(Integer id_liberador) {
		this.id_liberador = id_liberador;
	}
	
	public Integer getId_tipo_proceso() {
		return id_tipo_proceso;
	}

	public void setId_tipo_proceso(Integer id_tipo_proceso) {
		this.id_tipo_proceso = id_tipo_proceso;
	}


	
}
