package com.osmosyscol.datasuite.near.dtos;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SolicitudNEAR {
	
	private String radicado;
	private Date fecha_solicitud;
	private Date fecha_radicado;
	private String tipo_solicitud;
	private String tipo_solicitante;
	private String solicitante;
	private String identificacion;
	private String municipio;
	private String departamento;
	private Integer t_hombres;
	private Integer t_mujeres;
	private String categoria;
	private String fecha_ef;
	private String fecha_ef_anio;
	private BigDecimal total_activos;
	private BigDecimal total_pasivos;
	private BigDecimal activos_mes;
	private BigDecimal pasivos_mes;
	private String ciiu;
	private String macrosector;
	private String supuesto_insolvencia;
	private String ponente;
	private Integer id_carga;
	private Integer id_instancia;
	private String estado;
	private String estado_nombre;
	private String intendencia;
	private String numero_proceso;
	private String proceso;
	private String tramite;
	
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private static DecimalFormat df = new DecimalFormat("#,##0");

	public String getRadicado() {
		return radicado;
	}

	public void setRadicado(String radicado) {
		this.radicado = radicado;
	}

	public Date getFecha_solicitud() {
		return fecha_solicitud;
	}

	public void setFecha_solicitud(Date fecha_solicitud) {
		this.fecha_solicitud = fecha_solicitud;
	}

	public Date getFecha_radicado() {
		return fecha_radicado;
	}

	public void setFecha_radicado(Date fecha_radicado) {
		this.fecha_radicado = fecha_radicado;
	}

	public String getTipo_solicitud() {
		return tipo_solicitud;
	}

	public void setTipo_solicitud(String tipo_solicitud) {
		this.tipo_solicitud = tipo_solicitud;
	}

	public String getTipo_solicitante() {
		return tipo_solicitante;
	}

	public void setTipo_solicitante(String tipo_solicitante) {
		this.tipo_solicitante = tipo_solicitante;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public Integer getT_hombres() {
		return t_hombres;
	}

	public void setT_hombres(Integer t_hombres) {
		this.t_hombres = t_hombres;
	}

	public Integer getT_mujeres() {
		return t_mujeres;
	}

	public void setT_mujeres(Integer t_mujeres) {
		this.t_mujeres = t_mujeres;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getFecha_ef() {
		return fecha_ef;
	}

	public void setFecha_ef(String fecha_ef) {
		this.fecha_ef = fecha_ef;
	}

	public BigDecimal getTotal_activos() {
		return total_activos;
	}

	public void setTotal_activos(BigDecimal total_activos) {
		this.total_activos = total_activos;
	}

	public BigDecimal getTotal_pasivos() {
		return total_pasivos;
	}

	public void setTotal_pasivos(BigDecimal total_pasivos) {
		this.total_pasivos = total_pasivos;
	}

	public BigDecimal getActivos_mes() {
		return activos_mes;
	}

	public void setActivos_mes(BigDecimal activos_mes) {
		this.activos_mes = activos_mes;
	}

	public BigDecimal getPasivos_mes() {
		return pasivos_mes;
	}

	public void setPasivos_mes(BigDecimal pasivos_mes) {
		this.pasivos_mes = pasivos_mes;
	}

	public String getCiiu() {
		return ciiu;
	}

	public void setCiiu(String ciiu) {
		this.ciiu = ciiu;
	}

	public String getMacrosector() {
		return macrosector;
	}

	public void setMacrosector(String macrosector) {
		this.macrosector = macrosector;
	}

	public String getSupuesto_insolvencia() {
		return supuesto_insolvencia;
	}

	public void setSupuesto_insolvencia(String supuesto_insolvencia) {
		this.supuesto_insolvencia = supuesto_insolvencia;
	}

	public String getPonente() {
		return ponente;
	}

	public void setPonente(String ponente) {
		this.ponente = ponente;
	}

	public Integer getId_carga() {
		return id_carga;
	}

	public void setId_carga(Integer id_carga) {
		this.id_carga = id_carga;
	}
	
	public Integer getId_instancia() {
		return id_instancia;
	}

	public void setId_instancia(Integer id_instancia) {
		this.id_instancia = id_instancia;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstado_nombre() {
		return estado_nombre;
	}

	public void setEstado_nombre(String estado_nombre) {
		this.estado_nombre = estado_nombre;
	}

	public String getIntendencia() {
		return intendencia;
	}

	public void setIntendencia(String intendencia) {
		this.intendencia = intendencia;
	}

	public String getNumero_proceso() {
		return numero_proceso;
	}

	public void setNumero_proceso(String numero_proceso) {
		this.numero_proceso = numero_proceso;
	}

	public String getFecha_solicitud_formato(){
		return (fecha_solicitud != null) ? sdf.format(fecha_solicitud) : "";
	}
	
	public String getFecha_radicado_formato(){
		return (fecha_radicado != null) ? sdf.format(fecha_radicado) : "";
	}
	
	public String getTotal_activos_formato(){
		return (total_activos != null) ? df.format(total_activos) : "";
	}
	
	public String getTotal_pasivos_formato(){
		return (total_pasivos != null) ? df.format(total_pasivos) : "";
	}
	
	public String getActivos_mes_formato(){
		return (activos_mes != null) ? df.format(activos_mes) : "";
	}
	
	public String getPasivos_mes_formato(){
		return (pasivos_mes != null) ? df.format(pasivos_mes) : "";
	}
	
	public String getT_hombres_formato(){
		return (t_hombres != null) ? df.format(t_hombres) : "";
	}
	
	public String getT_mujeres_formato(){
		return (t_mujeres != null) ? df.format(t_mujeres) : "";
	}
	
	public Integer getTotal_trabajadores(){
		Integer hombres = (t_hombres != null) ? t_hombres : 0;
		Integer mujeres = (t_mujeres != null) ? t_mujeres : 0;
		return hombres + mujeres;
	}
	
	public String getTotal_trabajadores_formato(){
		return df.format(getTotal_trabajadores());
	}

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public String getTramite() {
		return tramite;
	}

	public void setTramite(String tramite) {
		this.tramite = tramite;
	}

	public String getFecha_ef_anio() {
		return fecha_ef_anio;
	}

	public void setFecha_ef_anio(String fecha_ef_anio) {
		this.fecha_ef_anio = fecha_ef_anio;
	}
	
}
