package com.osmosyscol.datasuite.webdata.correval.tablero_control;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Carga {

	private Long id_carga;
	private String nombre_cliente;
	private String apellido_cliente;
	private String nombre_proceso;
	private BigDecimal valor_lote;
	private String nombre_instancia;
	private Date fecha_llegada;
	private Long tiempo_cola;
	private Long tiempo_instancia;
	private Long porcentaje;

	// ---------------

	public Long getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(Long porcentaje) {
		this.porcentaje = porcentaje;
	}

	public Long getId_carga() {
		return id_carga;
	}

	public void setId_carga(Long id_carga) {
		this.id_carga = id_carga;
	}

	public String getNombre_cliente() {
		return nombre_cliente;
	}

	public void setNombre_cliente(String nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}

	public String getApellido_cliente() {
		return apellido_cliente;
	}

	public void setApellido_cliente(String apellido_cliente) {
		this.apellido_cliente = apellido_cliente;
	}

	public String getNombre_proceso() {
		return nombre_proceso;
	}

	public void setNombre_proceso(String nombre_proceso) {
		this.nombre_proceso = nombre_proceso;
	}

	public BigDecimal getValor_lote() {
		return valor_lote;
	}

	public void setValor_lote(BigDecimal valor_lote) {
		this.valor_lote = valor_lote;
	}

	public String getNombre_instancia() {
		return nombre_instancia;
	}

	public void setNombre_instancia(String nombre_instancia) {
		this.nombre_instancia = nombre_instancia;
	}

	public Date getFecha_llegada() {
		return fecha_llegada;
	}

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm a");

	public String getFecha_llegadaStr() {

		if (fecha_llegada == null) {
			return "";
		}

		return dateFormat.format(fecha_llegada);
	}

	public void setFecha_llegada(Date fecha_llegada) {
		this.fecha_llegada = fecha_llegada;
	}

	public Long getTiempo_cola() {
		return tiempo_cola;
	}

	public void setTiempo_cola(Long tiempo_cola) {
		this.tiempo_cola = tiempo_cola;
	}

	public Long getTiempo_instancia() {
		return tiempo_instancia;
	}

	public void setTiempo_instancia(Long tiempo_instancia) {
		this.tiempo_instancia = tiempo_instancia;
	}

}
