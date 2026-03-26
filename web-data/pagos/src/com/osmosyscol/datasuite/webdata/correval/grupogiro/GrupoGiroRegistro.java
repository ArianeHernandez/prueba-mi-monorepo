package com.osmosyscol.datasuite.webdata.correval.grupogiro;

import java.math.BigDecimal;

public class GrupoGiroRegistro {

	private Integer id;
	private Integer id_carga;
	private String nombre_cliente;
	private String identificacion_cliente;
	private Integer id_cliente;
	private BigDecimal valor;
	private Long banco_destino;
	private String nombre_banco_destino;
	private String cuenta_destino;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_carga() {
		return id_carga;
	}

	public void setId_carga(Integer id_carga) {
		this.id_carga = id_carga;
	}

	public String getNombre_cliente() {
		return nombre_cliente;
	}

	public void setNombre_cliente(String nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}

	public String getIdentificacion_cliente() {
		return identificacion_cliente;
	}

	public void setIdentificacion_cliente(String identificacion_cliente) {
		this.identificacion_cliente = identificacion_cliente;
	}

	public Integer getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Long getBanco_destino() {
		return banco_destino;
	}

	public void setBanco_destino(Long banco_destino) {
		this.banco_destino = banco_destino;
	}

	public String getNombre_banco_destino() {
		return nombre_banco_destino;
	}

	public void setNombre_banco_destino(String nombre_banco_destino) {
		this.nombre_banco_destino = nombre_banco_destino;
	}

	public String getCuenta_destino() {
		return cuenta_destino;
	}

	public void setCuenta_destino(String cuenta_destino) {
		this.cuenta_destino = cuenta_destino;
	}

}
