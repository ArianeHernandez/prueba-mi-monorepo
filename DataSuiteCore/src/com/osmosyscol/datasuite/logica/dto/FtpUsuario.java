package com.osmosyscol.datasuite.logica.dto;

import java.util.List;

import com.osmosyscol.commons.utils.JavaToXML;
import com.osmosyscol.commons.utils.XMLFormat;

public class FtpUsuario {
	
	private Integer id_ftp_usuario;
	private Integer id_usuario;
	private String host;
	private Integer puerto;
	private String nombre_usuario;
	private String clave;
	private String carpeta_transito;
	private String carpeta_fallidos;
	private String carpeta_procesados;
	private String carpeta_bk_transito;
	private String carpeta_bk_fallidos;
	private String carpeta_bk_procesados;
	private String encripcion;
	private String clave_encripcion;
	private String llave_privada;
	private Integer dias_validacion;
	private String nombre_configuracion;
	private Integer id_proceso;
	private Integer frecuencia_lectura;
	private Integer id_horario;
	private String  dominio;
	
	private List<FtpUsuarioCorreo> correos_correval;
	private List<FtpUsuarioCorreo> correos_usuario;

	public Integer getId_ftp_usuario() {
		return id_ftp_usuario;
	}

	public void setId_ftp_usuario(Integer id_ftp_usuario) {
		this.id_ftp_usuario = id_ftp_usuario;
	}

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPuerto() {
		return puerto;
	}

	public void setPuerto(Integer puerto) {
		this.puerto = puerto;
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getCarpeta_transito() {
		return carpeta_transito;
	}

	public void setCarpeta_transito(String carpeta_transito) {
		this.carpeta_transito = carpeta_transito;
	}

	public String getCarpeta_fallidos() {
		return carpeta_fallidos;
	}

	public void setCarpeta_fallidos(String carpeta_fallidos) {
		this.carpeta_fallidos = carpeta_fallidos;
	}

	public String getCarpeta_procesados() {
		return carpeta_procesados;
	}

	public void setCarpeta_procesados(String carpeta_procesados) {
		this.carpeta_procesados = carpeta_procesados;
	}

	public String getCarpeta_bk_transito() {
		return carpeta_bk_transito;
	}

	public void setCarpeta_bk_transito(String carpeta_bk_transito) {
		this.carpeta_bk_transito = carpeta_bk_transito;
	}

	public String getCarpeta_bk_fallidos() {
		return carpeta_bk_fallidos;
	}

	public void setCarpeta_bk_fallidos(String carpeta_bk_fallidos) {
		this.carpeta_bk_fallidos = carpeta_bk_fallidos;
	}

	public String getCarpeta_bk_procesados() {
		return carpeta_bk_procesados;
	}

	public void setCarpeta_bk_procesados(String carpeta_bk_procesados) {
		this.carpeta_bk_procesados = carpeta_bk_procesados;
	}

	public String getEncripcion() {
		return encripcion;
	}

	public void setEncripcion(String encripcion) {
		this.encripcion = encripcion;
	}

	public String getClave_encripcion() {
		return clave_encripcion;
	}

	public void setClave_encripcion(String clave_encripcion) {
		this.clave_encripcion = clave_encripcion;
	}

	public String getLlave_privada() {
		return llave_privada;
	}

	public void setLlave_privada(String llave_privada) {
		this.llave_privada = llave_privada;
	}

	public Integer getDias_validacion() {
		return dias_validacion;
	}

	public void setDias_validacion(Integer dias_validacion) {
		this.dias_validacion = dias_validacion;
	}

	public String getNombre_configuracion() {
		return nombre_configuracion;
	}

	public void setNombre_configuracion(String nombre_configuracion) {
		this.nombre_configuracion = nombre_configuracion;
	}

	public Integer getId_proceso() {
		return id_proceso;
	}

	public void setId_proceso(Integer id_proceso) {
		this.id_proceso = id_proceso;
	}

	public Integer getFrecuencia_lectura() {
		return frecuencia_lectura;
	}

	public void setFrecuencia_lectura(Integer frecuencia_lectura) {
		this.frecuencia_lectura = frecuencia_lectura;
	}

	public Integer getId_horario() {
		return id_horario;
	}

	public void setId_horario(Integer id_horario) {
		this.id_horario = id_horario;
	}

	public List<FtpUsuarioCorreo> getCorreos_correval() {
		return correos_correval;
	}

	public void setCorreos_correval(List<FtpUsuarioCorreo> correos) {
		this.correos_correval = correos;
	}

	public List<FtpUsuarioCorreo> getCorreos_usuario() {
		return correos_usuario;
	}

	public void setCorreos_usuario(List<FtpUsuarioCorreo> correos_usuario) {
		this.correos_usuario = correos_usuario;
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}
	
	public String toString(){
		return XMLFormat.format(JavaToXML.exe("FtpUsuario", this).toString());
	}

}
