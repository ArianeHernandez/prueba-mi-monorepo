package com.osmosyscol.datasuite.mein.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class InfoFinancieraAnual {
	private Integer id;
	private Integer idCarga;
	private Date fecha_eeff_anual;
	private BigDecimal activos_ano_anterior;
	private BigDecimal pasivos_ano_anterior;
	private BigDecimal ingresos_ano_anterior;
	private BigDecimal otros_ing_ano_anterior;
	private Integer inversiones_subsidiarias;
	private BigDecimal ing_metodo_participacion;
	private BigDecimal ingresos_metodo_costo;
	private BigDecimal ingresos_metodo_razonable;
	
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
	public BigDecimal getActivos_ano_anterior() {
		return activos_ano_anterior;
	}
	public void setActivos_ano_anterior(BigDecimal activos_ano_anterior) {
		this.activos_ano_anterior = activos_ano_anterior;
	}
	public BigDecimal getPasivos_ano_anterior() {
		return pasivos_ano_anterior;
	}
	public void setPasivos_ano_anterior(BigDecimal pasivos_ano_anterior) {
		this.pasivos_ano_anterior = pasivos_ano_anterior;
	}
	public BigDecimal getIngresos_ano_anterior() {
		return ingresos_ano_anterior;
	}
	public void setIngresos_ano_anterior(BigDecimal ingresos_ano_anterior) {
		this.ingresos_ano_anterior = ingresos_ano_anterior;
	}
	public BigDecimal getOtros_ing_ano_anterior() {
		return otros_ing_ano_anterior;
	}
	public void setOtros_ing_ano_anterior(BigDecimal otros_ing_ano_anterior) {
		this.otros_ing_ano_anterior = otros_ing_ano_anterior;
	}
	public Integer getInversiones_subsidiarias() {
		return inversiones_subsidiarias;
	}
	public void setInversiones_subsidiarias(Integer inversiones_subsidiarias) {
		this.inversiones_subsidiarias = inversiones_subsidiarias;
	}
	public BigDecimal getIng_metodo_participacion() {
		return ing_metodo_participacion;
	}
	public void setIng_metodo_participacion(BigDecimal ing_metodo_participacion) {
		this.ing_metodo_participacion = ing_metodo_participacion;
	}
	public BigDecimal getIngresos_metodo_costo() {
		return ingresos_metodo_costo;
	}
	public void setIngresos_metodo_costo(BigDecimal ingresos_metodo_costo) {
		this.ingresos_metodo_costo = ingresos_metodo_costo;
	}
	public BigDecimal getIngresos_metodo_razonable() {
		return ingresos_metodo_razonable;
	}
	public void setIngresos_metodo_razonable(BigDecimal ingresos_metodo_razonable) {
		this.ingresos_metodo_razonable = ingresos_metodo_razonable;
	}
	public Date getFecha_eeff_anual() {
		return fecha_eeff_anual;
	}
	public void setFecha_eeff_anual(Date fecha_eeff_anual) {
		this.fecha_eeff_anual = fecha_eeff_anual;
	}
}
