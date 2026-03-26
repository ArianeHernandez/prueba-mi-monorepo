package com.osmosyscol.datasuite.webdata.correval.grupogiro;

import java.util.Date;

public class GrupoGiro {

	public final static String ESTADO_NUEVO = "E";
	
	public final static String ESTADO_DEBITADO = "D";
	
	private Integer codigo_registro;
	
	private Date fecha_pago;
	
	private Integer cuenta;
	
	private Integer id_estructura;
	
	private Integer id_carga;
	
	private Integer id;
	
	// -----------------------

	public Integer getCodigo_registro() {
		return codigo_registro;
	}

	public void setCodigo_registro(Integer codigoRegistro) {
		codigo_registro = codigoRegistro;
	}

	public Date getFecha_pago() {
		return fecha_pago;
	}

	public void setFecha_pago(Date fechaPago) {
		fecha_pago = fechaPago;
	}

	public Integer getCuenta() {
		return cuenta;
	}

	public void setCuenta(Integer idCuenta) {
		cuenta = idCuenta;
	}

	public Integer getId_estructura() {
		return id_estructura;
	}

	public void setId_estructura(Integer idEstructura) {
		id_estructura = idEstructura;
	}


	public Integer getId_carga() {
		return id_carga;
	}

	public void setId_carga(Integer idCarga) {
		id_carga = idCarga;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


}
