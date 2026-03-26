package com.osmosyscol.datasuite.mein.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class InfoFinancMensualPNC {
	private Integer id;
	private Integer idCarga;
	private Date fecha_eeff_mes_pnc;
	private BigDecimal activos_mes_anterior_pnc;
	private BigDecimal pasivos_mes_anterior_pnc;
	private BigDecimal patrimonio_mes_anter_pnc;
	private Integer grupo_niif_pnc;
	private Integer decreto_2101_de_2016;
	private String estado_activos_netos_pnc;
	private String estado_cambios_acti_netos;
	private String conciliacion_entre_saldos;
	private String eeff_mes_pnc;
	private String notas_eeff_mes_pnc;
	private String dictamen_mes_pnc;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdCarga() {
		return idCarga;
	}
	public void setIdCarga(Integer idCarga) {
		this.idCarga = idCarga;
	}
	public Date getFecha_eeff_mes_pnc() {
		return fecha_eeff_mes_pnc;
	}
	public void setFecha_eeff_mes_pnc(Date fecha_eeff_mes_pnc) {
		this.fecha_eeff_mes_pnc = fecha_eeff_mes_pnc;
	}
	public BigDecimal getActivos_mes_anterior_pnc() {
		return activos_mes_anterior_pnc;
	}
	public void setActivos_mes_anterior_pnc(BigDecimal activos_mes_anterior_pnc) {
		this.activos_mes_anterior_pnc = activos_mes_anterior_pnc;
	}
	public BigDecimal getPasivos_mes_anterior_pnc() {
		return pasivos_mes_anterior_pnc;
	}
	public void setPasivos_mes_anterior_pnc(BigDecimal pasivos_mes_anterior_pnc) {
		this.pasivos_mes_anterior_pnc = pasivos_mes_anterior_pnc;
	}
	public BigDecimal getPatrimonio_mes_anter_pnc() {
		return patrimonio_mes_anter_pnc;
	}
	public void setPatrimonio_mes_anter_pnc(BigDecimal patrimonio_mes_anter_pnc) {
		this.patrimonio_mes_anter_pnc = patrimonio_mes_anter_pnc;
	}
	public Integer getGrupo_niif_pnc() {
		return grupo_niif_pnc;
	}
	public void setGrupo_niif_pnc(Integer grupo_niif_pnc) {
		this.grupo_niif_pnc = grupo_niif_pnc;
	}
	public Integer getDecreto_2101_de_2016() {
		return decreto_2101_de_2016;
	}
	public void setDecreto_2101_de_2016(Integer decreto_2101_de_2016) {
		this.decreto_2101_de_2016 = decreto_2101_de_2016;
	}
	public String getEstado_activos_netos_pnc() {
		return estado_activos_netos_pnc;
	}
	public void setEstado_activos_netos_pnc(String estado_activos_netos_pnc) {
		this.estado_activos_netos_pnc = estado_activos_netos_pnc;
	}
	public String getEstado_cambios_acti_netos() {
		return estado_cambios_acti_netos;
	}
	public void setEstado_cambios_acti_netos(String estado_cambios_acti_netos) {
		this.estado_cambios_acti_netos = estado_cambios_acti_netos;
	}
	public String getConciliacion_entre_saldos() {
		return conciliacion_entre_saldos;
	}
	public void setConciliacion_entre_saldos(String conciliacion_entre_saldos) {
		this.conciliacion_entre_saldos = conciliacion_entre_saldos;
	}
	public String getEeff_mes_pnc() {
		return eeff_mes_pnc;
	}
	public void setEeff_mes_pnc(String eeff_mes_pnc) {
		this.eeff_mes_pnc = eeff_mes_pnc;
	}
	public String getNotas_eeff_mes_pnc() {
		return notas_eeff_mes_pnc;
	}
	public void setNotas_eeff_mes_pnc(String notas_eeff_mes_pnc) {
		this.notas_eeff_mes_pnc = notas_eeff_mes_pnc;
	}
	public String getDictamen_mes_pnc() {
		return dictamen_mes_pnc;
	}
	public void setDictamen_mes_pnc(String dictamen_mes_pnc) {
		this.dictamen_mes_pnc = dictamen_mes_pnc;
	}
	
	
}
