package com.osmosyscol.datasuite.logica.dto;

import java.util.Date;

public class AuditoriaSql {
	
	private Integer id_auditoria_Sql;
	private String login;
	private Date fecha;
	private String sql;
	private Integer nro_registros_afectados;
	private String excepciones;
	private String ip;
	
	
	
	//----------------------------------------------
	
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getIp_ingreso() {
		return ip_ingreso;
	}
	public void setIp_ingreso(String ip_ingreso) {
		this.ip_ingreso = ip_ingreso;
	}
	public Integer getId_auditoria_Sql() {
		return id_auditoria_Sql;
	}
	public void setId_auditoria_Sql(Integer id_auditoria_Sql) {
		this.id_auditoria_Sql = id_auditoria_Sql;
	}
	public Integer getNro_registros_afectados() {
		return nro_registros_afectados;
	}
	public void setNro_registros_afectados(Integer nro_registros_afectados) {
		this.nro_registros_afectados = nro_registros_afectados;
	}
	public String getExcepciones() {
		return excepciones;
	}
	public void setExcepciones(String excepciones) {
		this.excepciones = excepciones;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	private String ip_ingreso;

}
