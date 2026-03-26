package com.osmosyscol.datasuite.pagos.acciones.centralpagos.dto;

public class SolicitudEstadoPago {

	private Integer id;
	private Integer empresa;
	private String codigo_contrato;
	private Integer numero_orden_pago;

	public String getCodigo_contrato() {
		return codigo_contrato;
	}

	public void setCodigo_contrato(String codigo_contrato) {
		this.codigo_contrato = codigo_contrato;
	}

	public Integer getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Integer empresa) {
		this.empresa = empresa;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumero_orden_pago() {
		return numero_orden_pago;
	}

	public void setNumero_orden_pago(Integer numero_orden_pago) {
		this.numero_orden_pago = numero_orden_pago;
	}

}
