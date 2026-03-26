package com.osmosyscol.datasuite.webdata.correval.confCuentaACH.dto;

public class CuentaBanco {

	private Integer id; // (id sin cifrado)

	private String codigo;
	private String cuentaBancaria;
	private Integer banco; // (id sin cifrado)
	private Integer producto;// (id sin cifrado)
	private Boolean porDefecto;
	private String cuentaContable;
	private Integer tipoDeCuenta;// (id sin cifrado)
	private String cuentaBancariaPrincipal;
	private String estado; // (vacio sin cifrado)

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCuentaBancaria() {
		return cuentaBancaria;
	}

	public void setCuentaBancaria(String cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	public Integer getBanco() {
		return banco;
	}

	public void setBanco(Integer banco) {
		this.banco = banco;
	}

	public Integer getProducto() {
		return producto;
	}

	public void setProducto(Integer producto) {
		this.producto = producto;
	}

	public Boolean getPorDefecto() {
		return porDefecto;
	}

	public void setPorDefecto(Boolean porDefecto) {
		this.porDefecto = porDefecto;
	}

	public String getCuentaContable() {
		return cuentaContable;
	}

	public void setCuentaContable(String cuentaContable) {
		this.cuentaContable = cuentaContable;
	}

	public Integer getTipoDeCuenta() {
		return tipoDeCuenta;
	}

	public void setTipoDeCuenta(Integer tipoDeCuenta) {
		this.tipoDeCuenta = tipoDeCuenta;
	}

	public String getCuentaBancariaPrincipal() {
		return cuentaBancariaPrincipal;
	}

	public void setCuentaBancariaPrincipal(String cuentaBancariaPrincipal) {
		this.cuentaBancariaPrincipal = cuentaBancariaPrincipal;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getHash() {
		return cuentaBancaria + ":" + cuentaBancariaPrincipal + ":" + banco + ":" + tipoDeCuenta;
	}

}
