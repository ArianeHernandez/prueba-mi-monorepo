package com.osmosyscol.datasuite.webdata.correval.confCuentaACH.dto;

import java.util.List;

public class ProductoCuentas {

	private Producto producto;

	private List<CuentaBanco> cuentas;

	public List<CuentaBanco> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<CuentaBanco> cuentas) {
		this.cuentas = cuentas;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}
