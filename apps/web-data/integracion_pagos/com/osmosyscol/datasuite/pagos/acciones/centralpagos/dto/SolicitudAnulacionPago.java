package com.osmosyscol.datasuite.pagos.acciones.centralpagos.dto;

public class SolicitudAnulacionPago {

	private Integer id;
	private Integer empresa_orden_pago;
	private Integer vigencia_orden_pago;
	private Integer numero_orden_pago;
	private String codigo;
	private Integer idcarga;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEmpresa_orden_pago() {
		return empresa_orden_pago;
	}

	public void setEmpresa_orden_pago(Integer empresa_orden_pago) {
		this.empresa_orden_pago = empresa_orden_pago;
	}

	public Integer getVigencia_orden_pago() {
		return vigencia_orden_pago;
	}

	public void setVigencia_orden_pago(Integer vigencia_orden_pago) {
		this.vigencia_orden_pago = vigencia_orden_pago;
	}

	public Integer getNumero_orden_pago() {
		return numero_orden_pago;
	}

	public void setNumero_orden_pago(Integer numero_orden_pago) {
		this.numero_orden_pago = numero_orden_pago;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Integer getIdcarga() {
		return idcarga;
	}

	public void setIdcarga(Integer idcarga) {
		this.idcarga = idcarga;
	}

}
