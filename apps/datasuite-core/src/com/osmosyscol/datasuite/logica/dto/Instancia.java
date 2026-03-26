package com.osmosyscol.datasuite.logica.dto;

import java.util.List;

public class Instancia {

	private Integer id_instancia;
	private String nombre;
	private String aprobar;
	private String rechazar;
	private Integer tiempo;
	private Integer posicion_x;
	private Integer posicion_y;
	private Integer id_proceso_admin;
	private Integer id_formato_salida;
	private Integer id_horario;
	private String inicial;
	private String adicionar_documentos;
	private String gestionar_documentos;
	private String aprobar_automaticamente;
	private String rechazar_automaticamente;
	private String solicitar_otp; 
	private String ocultar_sin_filtro;
	private List<Accion> accionesPorInstancia;

	public String getAdicionar_documentos() {
		return adicionar_documentos;
	}

	public void setAdicionar_documentos(String adicionar_documentos) {
		this.adicionar_documentos = adicionar_documentos;
	}

	public String getGestionar_documentos() {
		return gestionar_documentos;
	}

	public void setGestionar_documentos(String gestionar_documentos) {
		this.gestionar_documentos = gestionar_documentos;
	}


	public String getInicial() {
		return inicial;
	}

	public void setInicial(String inicial) {
		this.inicial = inicial;
	}

	public Integer getId_formato_salida() {
		return id_formato_salida;
	}

	public void setId_formato_salida(Integer id_formato_salida) {
		this.id_formato_salida = id_formato_salida;
	}

	public Integer getId_instancia() {
		return id_instancia;
	}

	public void setId_instancia(Integer idInstancia) {
		id_instancia = idInstancia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAprobar() {
		return aprobar;
	}

	public void setAprobar(String aprobar) {
		this.aprobar = aprobar;
	}

	public String getRechazar() {
		return rechazar;
	}

	public void setRechazar(String rechazar) {
		this.rechazar = rechazar;
	}

	public Integer getTiempo() {
		return tiempo;
	}

	public void setTiempo(Integer tiempo) {
		this.tiempo = tiempo;
	}

	public Integer getPosicion_x() {
		return posicion_x;
	}

	public void setPosicion_x(Integer posicionX) {
		posicion_x = posicionX;
	}

	public Integer getPosicion_y() {
		return posicion_y;
	}

	public void setPosicion_y(Integer posicionY) {
		posicion_y = posicionY;
	}

	public Integer getId_proceso_admin() {
		return id_proceso_admin;
	}

	public void setId_proceso_admin(Integer idProcesoAdmin) {
		id_proceso_admin = idProcesoAdmin;
	}

	public List<Accion> getAccionesPorInstancia() {
		return accionesPorInstancia;
	}

	public void setAccionesPorInstancia(List<Accion> accionesPorInstancia) {
		this.accionesPorInstancia = accionesPorInstancia;
	}

	public Integer getId_horario() {
		return id_horario;
	}

	public void setId_horario(Integer id_horario) {
		this.id_horario = id_horario;
	}

	public String getAprobar_automaticamente() {
		return aprobar_automaticamente;
	}

	public void setAprobar_automaticamente(String aprobar_automaticamente) {
		this.aprobar_automaticamente = aprobar_automaticamente;
	}

	public String getRechazar_automaticamente() {
		return rechazar_automaticamente;
	}

	public void setRechazar_automaticamente(String rechazar_automaticamente) {
		this.rechazar_automaticamente = rechazar_automaticamente;
	}
	
	public String getSolicitar_otp() {
		return solicitar_otp;
	}

	public void setSolicitar_otp(String solicitar_otp) {
		this.solicitar_otp = solicitar_otp;
	}

	public String getOcultar_sin_filtro() {
		return ocultar_sin_filtro;
	}

	public void setOcultar_sin_filtro(String ocultar_sin_filtro) {
		this.ocultar_sin_filtro = ocultar_sin_filtro;
	}

}
