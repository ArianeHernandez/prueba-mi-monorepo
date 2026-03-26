package com.osmosyscol.datapi.csv;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class Filtro {
	
	/**
	 * * Posibles operadores
	 * 
	 */
	public static final String OPERADOR_IGUAL = "=";
	public static final String OPERADOR_MENOR = "<";
	public static final String OPERADOR_MAYOR = ">";
	public static final String OPERADOR_MENOR_IGUAL = "<=";
	public static final String OPERADOR_MAYOR_IGUAL = ">=";
	
	public static final String OPERADORES[] = new String[] { OPERADOR_IGUAL, OPERADOR_MENOR, OPERADOR_MAYOR };
	
	public static Operador PRIMER_OPERADOR ;

	private String campo;
	private String operador;
	private String valor;

	public Boolean calcOperador(String string) {

		String list[] = StringUtils.split(string);
		if (list != null && list.length == 3) {
			operador = list[1];
			return true;
		}
		return false;
	}

	public Filtro(String campo, String valor, Map<String, Object> parametros) {
		
		this.campo = campo.toLowerCase().trim();
		this.valor = org.apache.commons.lang.StringUtils.replace(valor, "'", "").trim();
		
		if (this.valor.contains("#")) {
			this.valor = StringUtils.replace(this.valor, "#", "");
			this.valor = parametros.get(this.valor) + "";
		}
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public Boolean ejecutar(String valor){
		
		return PRIMER_OPERADOR.comparar(this, valor);
	}

}