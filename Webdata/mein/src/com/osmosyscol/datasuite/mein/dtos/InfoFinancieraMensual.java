package com.osmosyscol.datasuite.mein.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class InfoFinancieraMensual {
	private Integer id;
	private Integer idCarga;
	private BigDecimal activos_mes_anterior;
	private BigDecimal pasivos_mes_anterior;
	private BigDecimal patrimonio_mes_anterior;
	private Integer grupo_NIIF;
	private ObjGenerico grupo_NIIF_obj;
	private Date fecha_EEFF_mes;
	private String eeff_mes;
	private String notas_eeff_mes;
	private String dictamen_mes;
	private String certificacion_mes;
	private Integer base_contable;
	private ObjGenerico base_contable_obj;
	private APVistaBaseContable base_contable_apvista;
	
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
	public BigDecimal getActivos_mes_anterior() {
		return activos_mes_anterior;
	}
	public BigDecimal getPasivos_mes_anterior() {
		return pasivos_mes_anterior;
	}
	public BigDecimal getPatrimonio_mes_anterior() {
		return patrimonio_mes_anterior;
	}
	public Integer getGrupo_NIIF() {
		return grupo_NIIF;
	}
	public void setGrupo_NIIF(Integer grupo_NIIF) {
		this.grupo_NIIF = grupo_NIIF;
	}
	public Date getFecha_EEFF_mes() {
		return fecha_EEFF_mes;
	}
	public void setFecha_EEFF_mes(Date fecha_EEFF_mes) {
		this.fecha_EEFF_mes = fecha_EEFF_mes;
	}
	public String getEeff_mes() {
		return eeff_mes;
	}
	public void setEeff_mes(String eeff_mes) {
		this.eeff_mes = eeff_mes;
	}
	public String getNotas_eeff_mes() {
		return notas_eeff_mes;
	}
	public void setNotas_eeff_mes(String notas_eeff_mes) {
		this.notas_eeff_mes = notas_eeff_mes;
	}
	public String getDictamen_mes() {
		return dictamen_mes;
	}
	public void setDictamen_mes(String dictamen_mes) {
		this.dictamen_mes = dictamen_mes;
	}
	public String getCertificacion_mes() {
		return certificacion_mes;
	}
	public void setCertificacion_mes(String certificacion_mes) {
		this.certificacion_mes = certificacion_mes;
	}
	public void setActivos_mes_anterior(BigDecimal activos_mes_anterior) {
		this.activos_mes_anterior = activos_mes_anterior;
	}
	public void setPasivos_mes_anterior(BigDecimal pasivos_mes_anterior) {
		this.pasivos_mes_anterior = pasivos_mes_anterior;
	}
	public void setPatrimonio_mes_anterior(BigDecimal patrimonio_mes_anterior) {
		this.patrimonio_mes_anterior = patrimonio_mes_anterior;
	}
	public Integer getBase_contable() {
		return base_contable;
	}
	public void setBase_contable(Integer base_contable) {
		this.base_contable = base_contable;
	}
	public ObjGenerico getBase_contable_obj() {
		return base_contable_obj;
	}
	public void setBase_contable_obj(ObjGenerico base_contable_obj) {
		this.base_contable_obj = base_contable_obj;
	}
	public APVistaBaseContable getBase_contable_apvista() {
		return base_contable_apvista;
	}
	public void setBase_contable_apvista(APVistaBaseContable base_contable_apvista) {
		this.base_contable_apvista = base_contable_apvista;
	}
	public ObjGenerico getGrupo_NIIF_obj() {
		return grupo_NIIF_obj;
	}
	public void setGrupo_NIIF_obj(ObjGenerico grupo_NIIF_obj) {
		this.grupo_NIIF_obj = grupo_NIIF_obj;
	}
}
