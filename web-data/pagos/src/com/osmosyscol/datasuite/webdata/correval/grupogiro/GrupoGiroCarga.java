package com.osmosyscol.datasuite.webdata.correval.grupogiro;

import java.math.BigDecimal;


public class GrupoGiroCarga {
	
	private Integer id_carga;
	
	private Integer tipo_carga;
	
	private ArchivoGrupoGiro grupoGiro;
	
	private Integer id_estructura;
	
	private BigDecimal valorTotal;

	public Integer getId_carga() {
		return id_carga;
	}

	public void setId_carga(Integer idCarga) {
		id_carga = idCarga;
	}


	public ArchivoGrupoGiro getGrupoGiro() {
		return grupoGiro;
	}

	public void setGrupoGiro(ArchivoGrupoGiro grupoGiro) {
		this.grupoGiro = grupoGiro;
	}

	public Integer getTipo_carga() {
		return tipo_carga;
	}

	public void setTipo_carga(Integer tipoCarga) {
		tipo_carga = tipoCarga;
	}

	public Integer getId_estructura() {
		return id_estructura;
	}

	public void setId_estructura(Integer idEstructura) {
		id_estructura = idEstructura;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

}
