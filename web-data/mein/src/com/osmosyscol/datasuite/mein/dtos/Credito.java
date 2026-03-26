package com.osmosyscol.datasuite.mein.dtos;

import java.util.Date;

public class Credito {
	
	private Integer id;
	private Integer idcarga;
	private Integer clase;
	private Float valor_indexado;
	private String documento_soporte;
	private Float derechos_de_voto;
	private Date fecha_vencimiento;
	private Date fecha_corte;
	private Integer obligacion_vencida;
	private Integer dias_vencidos;
	private Float porcentaje_participacion;
	private Garantia garantia;
	
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
	public Integer getClase() {
		return clase;
	}
	public void setClase(Integer clase) {
		this.clase = clase;
	}
	public Float getValor_indexado() {
		return valor_indexado;
	}
	public void setValor_indexado(Float valor_indexado) {
		this.valor_indexado = valor_indexado;
	}
	public String getDocumento_soporte() {
		return documento_soporte;
	}
	public void setDocumento_soporte(String documento_soporte) {
		this.documento_soporte = documento_soporte;
	}
	public Float getDerechos_de_voto() {
		return derechos_de_voto;
	}
	public void setDerechos_de_voto(Float derechos_de_voto) {
		this.derechos_de_voto = derechos_de_voto;
	}
	public Date getFecha_vencimiento() {
		return fecha_vencimiento;
	}
	public void setFecha_vencimiento(Date fecha_vencimiento) {
		this.fecha_vencimiento = fecha_vencimiento;
	}
	public Date getFecha_corte() {
		return fecha_corte;
	}
	public void setFecha_corte(Date fecha_corte) {
		this.fecha_corte = fecha_corte;
	}
	public Integer getObligacion_vencida() {
		return obligacion_vencida;
	}
	public void setObligacion_vencida(Integer obligacion_vencida) {
		this.obligacion_vencida = obligacion_vencida;
	}
	public Integer getDias_vencidos() {
		return dias_vencidos;
	}
	public void setDias_vencidos(Integer dias_vencidos) {
		this.dias_vencidos = dias_vencidos;
	}
	public Float getPorcentaje_participacion() {
		return porcentaje_participacion;
	}
	public void setPorcentaje_participacion(Float porcentaje_participacion) {
		this.porcentaje_participacion = porcentaje_participacion;
	}
	public Garantia getGarantia() {
		return garantia;
	}
	public void setGarantia(Garantia garantia) {
		this.garantia = garantia;
	}
	
}
