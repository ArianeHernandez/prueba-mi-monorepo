package com.osmosyscol.datasuite.pagos.acciones.centralpagos.dto;

import java.util.Date;

public class SolicitudEntradaConsultaAlmacenes {

	private Integer id;
	private Integer id_cliente;
	private Integer num_factura;
	private Date fecha_documento;
	private String ptipo_contrato;
	private Integer num_contrato;
	private String cod_contrato; // ENCARGO / CODIGO // (TIPO-CONTRATO/VIGENCIA/NUMERO)

	public Integer getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}

	public Integer getNum_factura() {
		return num_factura;
	}

	public void setNum_factura(Integer num_factura) {
		this.num_factura = num_factura;
	}

	public Date getFecha_documento() {
		return fecha_documento;
	}

	public void setFecha_documento(Date fecha_documento) {
		this.fecha_documento = fecha_documento;
	}

	public String getPtipo_contrato() {
		return ptipo_contrato;
	}

	public void setPtipo_contrato(String ptipo_contrato) {
		this.ptipo_contrato = ptipo_contrato;
	}

	public Integer getNum_contrato() {
		return num_contrato;
	}

	public void setNum_contrato(Integer num_contrato) {
		this.num_contrato = num_contrato;
	}

	public String getCod_contrato() {
		return cod_contrato;
	}

	public void setCod_contrato(String cod_contrato) {
		this.cod_contrato = cod_contrato;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
