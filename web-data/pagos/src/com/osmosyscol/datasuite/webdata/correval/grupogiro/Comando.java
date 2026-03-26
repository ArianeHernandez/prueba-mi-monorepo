package com.osmosyscol.datasuite.webdata.correval.grupogiro;

import java.util.Date;

public class Comando {
	
	private Integer orden;
	
	private Integer id_carga;
	
	private Date fecha_pago;
	
	private Integer codigo_registro;
	
	private Integer cuenta;

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public Integer getId_carga() {
		return id_carga;
	}

	public void setId_carga(Integer id_carga) {
		this.id_carga = id_carga;
	}

	public Date getFecha_pago() {
		return fecha_pago;
	}

	public void setFecha_pago(Date fecha_pago) {
		this.fecha_pago = fecha_pago;
	}

	public Integer getCodigo_registro() {
		return codigo_registro;
	}

	public void setCodigo_registro(Integer codigo_registro) {
		this.codigo_registro = codigo_registro;
	}

	public Integer getCuenta() {
		return cuenta;
	}

	public void setCuenta(Integer cuenta) {
		this.cuenta = cuenta;
	}
	
}
