package com.osmosyscol.datasuite.webdata.correval.siforion.salida;

import java.math.BigDecimal;
import java.util.Date;

public class SalidaCoreCorreval {

	// Tipo de archivo
	private String tipo_archivo_salida_correval;
	private String tipo_retiro;
	private String sistema_core;

	// Retiro
	private String id_retiro;
	private String estado_retiro;

	// Cliente
	private String nombre_cliente;
	private String identificacion_cliente;
	private String num_cuenta_cliente_correval;
	private Integer digito_verificacion_identifiacion_cliente;

	// Beneficiario
	private BigDecimal valor_retiro;
	private Long num_identificacion_beneficiario;
	private String nombre_beneficiario;

	// Banco destino
	private String nombre_banco_destino_beneficiario;
	private String num_cuenta_destino_beneficiario;
	private String tipo_cuenta_destino_beneficiario;

	// Banco origen
	private String nombre_banco_origen_correval;
	private String num_cuenta_origen_correval;
	private String tipo_cuenta_origen_correval;
	private String codigo_contable_cuenta_origen_correval;

	// Lote
	private String lote_origen;
	private Date fecha_liberacion_lote_origen;
	private String nombre_agente_comercial;
	private String descripcion_negocio;
	private String descripcion_producto;

	// Medio fisico
	private String tipo_de_medio_fisico;
	private String tipo_de_entrega_medio_fisico;
	private String direccion_entrega_medio_fisico;
	private String tipo_identificacion_autorizado_para_recoger;
	private Long num_identificacion_autorizado_para_recoger;
	private String nombre_autorizado_para_recoger;

	// ------------------

	public String getEstado_retiro() {
		return estado_retiro;
	}

	public void setEstado_retiro(String estado_retiro) {
		this.estado_retiro = estado_retiro;
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

	public Integer getDigito_verificacion_identifiacion_cliente() {
		return digito_verificacion_identifiacion_cliente;
	}

	public void setDigito_verificacion_identifiacion_cliente(Integer digito_verificacion_identifiacion_cliente) {
		this.digito_verificacion_identifiacion_cliente = digito_verificacion_identifiacion_cliente;
	}

	public BigDecimal getValor_retiro() {
		return valor_retiro;
	}

	public void setValor_retiro(BigDecimal valor_retiro) {
		this.valor_retiro = valor_retiro;
	}

	public Long getNum_identificacion_beneficiario() {
		return num_identificacion_beneficiario;
	}

	public void setIdentificacion_beneficiario(Long identificacion_beneficiario) {
		this.num_identificacion_beneficiario = identificacion_beneficiario;
	}

	public String getNombre_beneficiario() {
		return nombre_beneficiario;
	}

	public void setNombre_beneficiario(String nombre_beneficiario) {
		this.nombre_beneficiario = nombre_beneficiario;
	}

	public String getNombre_banco_destino_beneficiario() {
		return nombre_banco_destino_beneficiario;
	}

	public void setNombre_banco_destino_beneficiario(String nombre_banco_destino) {
		this.nombre_banco_destino_beneficiario = nombre_banco_destino;
	}

	public String getNum_cuenta_destino_beneficiario() {
		return num_cuenta_destino_beneficiario;
	}

	public void setNum_cuenta_destino_beneficiario(String numero_cuenta_destino) {
		this.num_cuenta_destino_beneficiario = numero_cuenta_destino;
	}

	public String getTipo_cuenta_destino_beneficiario() {
		return tipo_cuenta_destino_beneficiario;
	}

	public void setTipo_cuenta_destino_beneficiario(String tipo_cuenta_destino) {
		this.tipo_cuenta_destino_beneficiario = tipo_cuenta_destino;
	}

	public String getNombre_banco_origen_correval() {
		return nombre_banco_origen_correval;
	}

	public void setNombre_banco_origen_correval(String nombre_banco_origen) {
		this.nombre_banco_origen_correval = nombre_banco_origen;
	}

	public String getNum_cuenta_origen_correval() {
		return num_cuenta_origen_correval;
	}

	public void setNum_cuenta_origen_correval(String num_cuenta_origen) {
		this.num_cuenta_origen_correval = num_cuenta_origen;
	}

	public String getTipo_cuenta_origen_correval() {
		return tipo_cuenta_origen_correval;
	}

	public void setTipo_cuenta_origen_correval(String tipo_cuenta_origen) {
		this.tipo_cuenta_origen_correval = tipo_cuenta_origen;
	}

	public String getCodigo_contable_cuenta_origen_correval() {
		return codigo_contable_cuenta_origen_correval;
	}

	public void setCodigo_contable_cuenta_origen_correval(String codigo_contable_cuenta_origen) {
		this.codigo_contable_cuenta_origen_correval = codigo_contable_cuenta_origen;
	}

	public String getLote_origen() {
		return lote_origen;
	}

	public void setLote_origen(String lote_origen) {
		this.lote_origen = lote_origen;
	}

	public Date getFecha_liberacion_lote_origen() {
		return fecha_liberacion_lote_origen;
	}

	public void setFecha_liberacion_lote_origen(Date fecha_liberacion_lote_origen) {
		this.fecha_liberacion_lote_origen = fecha_liberacion_lote_origen;
	}

	public String getNombre_agente_comercial() {
		return nombre_agente_comercial;
	}

	public void setNombre_agente_comercial(String nombre_agente_comercial) {
		this.nombre_agente_comercial = nombre_agente_comercial;
	}

	public String getDescripcion_negocio() {
		return descripcion_negocio;
	}

	public void setDescripcion_negocio(String descripcion_negocio) {
		this.descripcion_negocio = descripcion_negocio;
	}

	public String getDescripcion_producto() {
		return descripcion_producto;
	}

	public void setDescripcion_producto(String descripcion_producto) {
		this.descripcion_producto = descripcion_producto;
	}

	public String getTipo_de_medio_fisico() {
		return tipo_de_medio_fisico;
	}

	public void setTipo_de_medio_fisico(String tipo_de_medio_fisico) {
		this.tipo_de_medio_fisico = tipo_de_medio_fisico;
	}

	public String getTipo_de_entrega_medio_fisico() {
		return tipo_de_entrega_medio_fisico;
	}

	public void setTipo_de_entrega_medio_fisico(String tipo_de_entrega_medio_fisico) {
		this.tipo_de_entrega_medio_fisico = tipo_de_entrega_medio_fisico;
	}

	public String getDireccion_entrega_medio_fisico() {
		return direccion_entrega_medio_fisico;
	}

	public void setDireccion_entrega_medio_fisico(String direccion_entrega_medio_fisico) {
		this.direccion_entrega_medio_fisico = direccion_entrega_medio_fisico;
	}

	public String getTipo_identificacion_autorizado_para_recoger() {
		return tipo_identificacion_autorizado_para_recoger;
	}

	public void setTipo_identificacion_autorizado_para_recoger(String tipo_identificacion_autorizado_para_recoger) {
		this.tipo_identificacion_autorizado_para_recoger = tipo_identificacion_autorizado_para_recoger;
	}

	public Long getNum_identificacion_autorizado_para_recoger() {
		return num_identificacion_autorizado_para_recoger;
	}

	public void setNum_identificacion_autorizado_para_recoger(Long num_identificacion_autorizado_para_recoger) {
		this.num_identificacion_autorizado_para_recoger = num_identificacion_autorizado_para_recoger;
	}

	public String getNombre_autorizado_para_recoger() {
		return nombre_autorizado_para_recoger;
	}

	public void setNombre_autorizado_para_recoger(String nombre_autorizado_para_recoger) {
		this.nombre_autorizado_para_recoger = nombre_autorizado_para_recoger;
	}

	public String getTipo_archivo_salida_correval() {
		return tipo_archivo_salida_correval;
	}

	public void setTipo_archivo_salida_correval(String tipo_archivo_salida_correval) {
		this.tipo_archivo_salida_correval = tipo_archivo_salida_correval;
	}

	public String getNum_cuenta_cliente_correval() {
		return num_cuenta_cliente_correval;
	}

	public void setNum_cuenta_cliente_correval(String num_cuenta_cliente_correval) {
		this.num_cuenta_cliente_correval = num_cuenta_cliente_correval;
	}

	public String getId_retiro() {
		return id_retiro;
	}

	public void setId_retiro(String id_retiro) {
		this.id_retiro = id_retiro;
	}

	public String getTipo_retiro() {
		return tipo_retiro;
	}

	public void setTipo_retiro(String tipo_retiro) {
		this.tipo_retiro = tipo_retiro;
	}

	public String getSistema_core() {
		return sistema_core;
	}

	public void setSistema_core(String sistema_core) {
		this.sistema_core = sistema_core;
	}

}
