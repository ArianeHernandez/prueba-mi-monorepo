package com.osmosyscol.datasuite.mein.dtos;

import java.math.BigDecimal;
public class InformacionFinanciera {
	
	private Integer id;
	private Integer idcarga;
	private BigDecimal valor_activos;
	private BigDecimal valor_pasivos;
	private BigDecimal valor_patrimonio;
	private BigDecimal total_ingresos_ordinarios;
	private BigDecimal total_otros_ingresos;
	private Integer tiene_inversiones;
	private ObjGenerico tiene_inversionesObj;
	private BigDecimal valor_p_participacion;
	private BigDecimal valor_p_costo;
	private BigDecimal valor_p_razonable;
	private String fecha_estados_financieros;
	private BigDecimal valor_pasivos_ultimoanio;
	private BigDecimal valor_activos_ultimoanio;
	private String ultimo_radicado_dictamen;
	private String penultimo_radicado_dictamen;
	private String antepenultimo_radicado_dictamen;
	private String fecha_eeff_anio_anterior;
	private String fecha_r_bienes_acreedores;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdcarga() {
		return idcarga;
	}
	public void setIdcarga(Integer idcarga) {
		this.idcarga = idcarga;
	}
	public BigDecimal getValor_activos() {
		return valor_activos;
	}
	public void setValor_activos(BigDecimal valor_activos) {
		this.valor_activos = valor_activos;
	}
	public BigDecimal getValor_pasivos() {
		return valor_pasivos;
	}
	public void setValor_pasivos(BigDecimal valor_pasivos) {
		this.valor_pasivos = valor_pasivos;
	}
	public BigDecimal getValor_patrimonio() {
		return valor_patrimonio;
	}
	public void setValor_patrimonio(BigDecimal valor_patrimonio) {
		this.valor_patrimonio = valor_patrimonio;
	}
	public BigDecimal getTotal_ingresos_ordinarios() {
		return total_ingresos_ordinarios;
	}
	public void setTotal_ingresos_ordinarios(BigDecimal total_ingresos_ordinarios) {
		this.total_ingresos_ordinarios = total_ingresos_ordinarios;
	}
	public BigDecimal getTotal_otros_ingresos() {
		return total_otros_ingresos;
	}
	public void setTotal_otros_ingresos(BigDecimal total_otros_ingresos) {
		this.total_otros_ingresos = total_otros_ingresos;
	}
	public Integer getTiene_inversiones() {
		return tiene_inversiones;
	}
	public void setTiene_inversiones(Integer tiene_inversiones) {
		this.tiene_inversiones = tiene_inversiones;
	}
	public BigDecimal getValor_p_participacion() {
		return valor_p_participacion;
	}
	public void setValor_p_participacion(BigDecimal valor_p_participacion) {
		this.valor_p_participacion = valor_p_participacion;
	}
	public BigDecimal getValor_p_costo() {
		return valor_p_costo;
	}
	public void setValor_p_costo(BigDecimal valor_p_costo) {
		this.valor_p_costo = valor_p_costo;
	}
	public BigDecimal getValor_p_razonable() {
		return valor_p_razonable;
	}
	public void setValor_p_razonable(BigDecimal valor_p_razonable) {
		this.valor_p_razonable = valor_p_razonable;
	}
	public String getFecha_estados_financieros() {
		return fecha_estados_financieros;
	}
	public void setFecha_estados_financieros(String fecha_estados_financieros) {
		this.fecha_estados_financieros = fecha_estados_financieros;
	}
	public BigDecimal getValor_pasivos_ultimoanio() {
		return valor_pasivos_ultimoanio;
	}
	public void setValor_pasivos_ultimoanio(BigDecimal valor_pasivos_ultimoanio) {
		this.valor_pasivos_ultimoanio = valor_pasivos_ultimoanio;
	}
	public BigDecimal getValor_activos_ultimoanio() {
		return valor_activos_ultimoanio;
	}
	public void setValor_activos_ultimoanio(BigDecimal valor_activos_ultimoanio) {
		this.valor_activos_ultimoanio = valor_activos_ultimoanio;
	}
	public String getUltimo_radicado_dictamen() {
		return ultimo_radicado_dictamen;
	}
	public void setUltimo_radicado_dictamen(String ultimo_radicado_dictamen) {
		this.ultimo_radicado_dictamen = ultimo_radicado_dictamen;
	}
	public String getPenultimo_radicado_dictamen() {
		return penultimo_radicado_dictamen;
	}
	public void setPenultimo_radicado_dictamen(String penultimo_radicado_dictamen) {
		this.penultimo_radicado_dictamen = penultimo_radicado_dictamen;
	}
	public String getAntepenultimo_radicado_dictamen() {
		return antepenultimo_radicado_dictamen;
	}
	public void setAntepenultimo_radicado_dictamen(
			String antepenultimo_radicado_dictamen) {
		this.antepenultimo_radicado_dictamen = antepenultimo_radicado_dictamen;
	}
	public String getFecha_eeff_anio_anterior() {
		return fecha_eeff_anio_anterior;
	}
	public void setFecha_eeff_anio_anterior(String fecha_eeff_anio_anterior) {
		this.fecha_eeff_anio_anterior = fecha_eeff_anio_anterior;
	}
	public String getFecha_r_bienes_acreedores() {
		return fecha_r_bienes_acreedores;
	}
	public void setFecha_r_bienes_acreedores(String fecha_r_bienes_acreedores) {
		this.fecha_r_bienes_acreedores = fecha_r_bienes_acreedores;
	}
	public ObjGenerico getTiene_inversionesObj() {
		return tiene_inversionesObj;
	}
	public void setTiene_inversionesObj(ObjGenerico tiene_inversionesObj) {
		this.tiene_inversionesObj = tiene_inversionesObj;
	}	
	
}
