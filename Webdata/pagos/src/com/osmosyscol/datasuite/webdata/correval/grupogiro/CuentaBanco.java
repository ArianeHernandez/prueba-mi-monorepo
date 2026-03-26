package com.osmosyscol.datasuite.webdata.correval.grupogiro;

public class CuentaBanco {

	private Integer id;
	private String cuentaBancaria;
	private String cuentaBancariaPrincipal;
	private Integer tipoDeCuenta;
	private Integer banco;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCuentaBancaria() {
		return cuentaBancaria;
	}

	public void setCuentaBancaria(String cuenta_bancaria) {
		this.cuentaBancaria = cuenta_bancaria;
	}

	public String getCuentaBancariaPrincipal() {
		return cuentaBancariaPrincipal;
	}

	public void setCuentaBancariaPrincipal(String cuenta_bancaria_principal) {
		this.cuentaBancariaPrincipal = cuenta_bancaria_principal;
	}

	public Integer getTipoDeCuenta() {
		return tipoDeCuenta;
	}

	public void setTipoDeCuenta(Integer tipo_de_cuenta) {
		this.tipoDeCuenta = tipo_de_cuenta;
	}

	public Integer getBanco() {
		return banco;
	}

	public void setBanco(Integer banco) {
		this.banco = banco;
	}

	public String getHash() {
		return cuentaBancaria + ":" + cuentaBancariaPrincipal + ":" + banco + ":" + tipoDeCuenta;
	}

}
