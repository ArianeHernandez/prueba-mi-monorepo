package com.osmosyscol.osmoautenticador.seguridad.ldap;

public class ConfiguracionLDAP {
	
	private String nombre;
	private Integer instalacion;
	private String baseName;
	private String serverIP;
	private String puerto;
	private String documento;
	private String nombreCompleto;
	private String nombres;
	private String apellidos;
	private String versionldap;
	private String identificador;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getInstalacion() {
		return instalacion;
	}
	public void setInstalacion(Integer instalacion) {
		this.instalacion = instalacion;
	}
	public String getBaseName() {
		return baseName;
	}
	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}
	public String getServerIP() {
		return serverIP;
	}
	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}
	public String getPuerto() {
		return puerto;
	}
	public void setPuerto(String puerto) {
		this.puerto = puerto;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getVersionldap() {
		return versionldap;
	}
	public void setVersionldap(String versionldap) {
		this.versionldap = versionldap;
	}

	
	public String toString() {
		return "configuracion="+nombre + "=[instalacion=" + instalacion + ", baseName="
				+ baseName + ", serverIP=" + serverIP + ", puerto=" + puerto
				+ ", documento=" + documento + ", nombreCompleto;="
				+ nombreCompleto + ", nombres=" + nombres + ", apellidos="
				+ apellidos + ", versionldap=" + versionldap+"]";
	}
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
}