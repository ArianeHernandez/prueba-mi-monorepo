package com.osmosyscol.datasuite.logica.dto;


public class TipoProceso {

	private Integer id_tipo_proceso;
	private String nombre;
	private String proceso_por_defecto;
	private Integer numero_procesos_por_cliente;
	private String valor_maximo_liberador;
	private String usa_formato;
	
	// ---------------------------------

	public Integer getId_tipo_proceso() {
		return id_tipo_proceso;
	}

	public void setId_tipo_proceso(Integer id_tipo_proceso) {
		this.id_tipo_proceso = id_tipo_proceso;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getProceso_por_defecto() {
		return proceso_por_defecto;
	}

	public void setProceso_por_defecto(String proceso_por_defecto) {
		this.proceso_por_defecto = proceso_por_defecto;
	}

	public Integer getNumero_procesos_por_cliente() {
		return numero_procesos_por_cliente;
	}

	public void setNumero_procesos_por_cliente(Integer numero_procesos_por_cliente) {
		this.numero_procesos_por_cliente = numero_procesos_por_cliente;
	}

	public String getValor_maximo_liberador() {
		return valor_maximo_liberador;
	}

	public void setValor_maximo_liberador(String valor_maximo_liberador) {
		this.valor_maximo_liberador = valor_maximo_liberador;
	}

	public String getUsa_formato() {
		return usa_formato;
	}

	public void setUsa_formato(String usa_formato) {
		this.usa_formato = usa_formato;
	}

}
