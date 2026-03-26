package com.osmosyscol.datasuite.pagos.acciones.aplicardebito;

public class CuentaBeneficiario {

	private String codigo_banco;
	private String tipo_cuenta;
	private String numero_cuenta;

	public String getCodigo_banco() {
		return codigo_banco;
	}

	public void setCodigo_banco(String codigo_banco) {
		this.codigo_banco = codigo_banco;
	}

	public String getTipo_cuenta() {
		return tipo_cuenta;
	}

	public void setTipo_cuenta(String tipo_cuenta) {
		this.tipo_cuenta = tipo_cuenta;
	}

	public String getNumero_cuenta() {
		return numero_cuenta;
	}

	public void setNumero_cuenta(String numero_cuenta) {
		this.numero_cuenta = numero_cuenta;
	}

}
