package com.osmosyscol.datasuite.webdata.logica.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.commons.utils.StringUtils;

public class Carga {

	private Integer id_carga;
	private Integer id_negocio;
	private String nombre;
	private String extension;
	private String ruta;
	private Integer id_formato;
	private Integer id_persona;
	private String nombre_persona;
	private String apellido_persona;
	private String estado;
	private Date fecha_carga;
	private Date fecha_subida;
	private Date fecha_notificacion;
	private Integer id_tipocarga;
	private Integer id_proceso;
	private Integer id_revision;
	private Integer id_usuario;
	private Integer id_persona_usuario;
	private String nombre_usuario;
	private String apellido_usuario;
	private Date fecha_liberacion;
	private String ip_usuario;
	private String valor_total;
	private String numero_registros;
	private Integer id_tipo_proceso;
	private String motivo_estado;
	private Integer duplicados;
	//se agrega para modulo de insolvencia
	private String numero_proceso;
	private String radicado;
	

	public String getNumero_registros() {
		return numero_registros;
	}

	public void setNumero_registros(String numero_registros) {
		this.numero_registros = numero_registros;
	}

	public Long getNumero_registros_bigdecimal() {
		return (Long) Crypto.DF(numero_registros);
	}

	public String getValor_total() {
		return valor_total;
	}

	public BigDecimal getValor_total_bigdecimal() {
		return (BigDecimal) Crypto.DF(valor_total);
	}

	public void setValor_total(String valor_total) {
		this.valor_total = valor_total;
	}

	// --- informacion de la instancia en la que esta la carga
	private Integer id_instancia;
	private String nombre_instancia;

	// ---------------------
	private Integer tiemporestante;
	private Integer porcentajeTiempoTranscurrido;

	// ---------------------

	private Date fecha_llegada;

	// ---------------------

	private Date fecha_cambioestado;

	// ---------------------

	private String nombre_formato;

	public Date getFecha_cambioestado() {
		return fecha_cambioestado;
	}

	public void setFecha_cambioestado(Date fecha_cambioestado) {
		this.fecha_cambioestado = fecha_cambioestado;
	}

	public Integer getId_instancia() {
		return id_instancia;
	}

	public void setId_instancia(Integer id_instancia) {
		this.id_instancia = id_instancia;
	}

	public String getNombre_instancia() {
		return nombre_instancia;
	}

	public void setNombre_instancia(String nombre_instancia) {
		this.nombre_instancia = nombre_instancia;
	}

	public Integer getId_negocio() {
		return id_negocio;
	}

	public void setId_negocio(Integer id_negocio) {
		this.id_negocio = id_negocio;
	}

	public Date getFecha_liberacion() {
		return fecha_liberacion;
	}
	
	public String getFecha_liberacion_string() {
		return StringUtils.toStringFormat(fecha_liberacion);
	}

	public void setFecha_liberacion(Date fechaLiberacion) {
		fecha_liberacion = fechaLiberacion;
	}

	public Integer getId_persona_usuario() {
		return id_persona_usuario;
	}

	public void setId_persona_usuario(Integer idPersonaUsuario) {
		id_persona_usuario = idPersonaUsuario;
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombreUsuario) {
		nombre_usuario = nombreUsuario;
	}

	public String getApellido_usuario() {
		return apellido_usuario;
	}

	public void setApellido_usuario(String apellidoUsuario) {
		apellido_usuario = apellidoUsuario;
	}

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer idUsuario) {
		id_usuario = idUsuario;
	}

	public Date getFecha_carga() {
		return fecha_carga;
	}

	public void setFecha_carga(Date fecha_carga) {
		this.fecha_carga = fecha_carga;
	}

	public Integer getId_carga() {
		return id_carga;
	}

	public void setId_carga(Integer id) {
		this.id_carga = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getExtension() {
		if (extension != null) {
			return extension.trim().toLowerCase();
		}

		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public Integer getId_formato() {
		return id_formato;
	}

	public void setId_formato(Integer id_formato) {
		this.id_formato = id_formato;
	}

	public Integer getId_persona() {
		return id_persona;
	}

	public void setId_persona(Integer id_persona) {
		this.id_persona = id_persona;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFecha_subida() {
		return fecha_subida;
	}

	public String getFecha_subida_string() {
		return StringUtils.toStringFormat(fecha_subida);
	}

	public void setFecha_subida(Date fecha_subida) {
		this.fecha_subida = fecha_subida;
	}

	public Integer getId_tipocarga() {
		return id_tipocarga;
	}

	public void setId_tipocarga(Integer id_tipocarga) {
		this.id_tipocarga = id_tipocarga;
	}

	public Integer getId_proceso() {
		return id_proceso;
	}

	public void setId_proceso(Integer idProceso) {
		id_proceso = idProceso;
	}

	public Integer getId_revision() {
		return id_revision;
	}

	public void setId_revision(Integer idUltimaRevision) {
		id_revision = idUltimaRevision;
	}

	public void setApellido_persona(String apellido_persona) {
		this.apellido_persona = apellido_persona;
	}

	public String getApellido_persona() {
		return apellido_persona;
	}

	public void setNombre_persona(String nombre_persona) {
		this.nombre_persona = nombre_persona;
	}

	public String getNombre_persona() {
		return nombre_persona;
	}

	public Integer getTiemporestante() {
		return tiemporestante;
	}

	public void setTiemporestante(Integer tiemporestante) {
		this.tiemporestante = tiemporestante;
	}

	public Integer getPorcentajeTiempoTranscurrido() {
		return porcentajeTiempoTranscurrido;
	}

	public void setPorcentajeTiempoTranscurrido(Integer porcentajeTiempoTranscurrido) {
		this.porcentajeTiempoTranscurrido = porcentajeTiempoTranscurrido;
	}

	public String toString() {
		return this.id_carga + " " + nombre;
	}

	public void setIp_usuario(String ip_usuario) {
		this.ip_usuario = ip_usuario;
	}

	public String getIp_usuario() {
		return ip_usuario;
	}

	public String getNombre_formato() {
		return nombre_formato;
	}

	public void setNombre_formato(String nombre_formato) {
		this.nombre_formato = nombre_formato;
	}

	public Integer getId_tipo_proceso() {
		return id_tipo_proceso;
	}

	public void setId_tipo_proceso(Integer id_tipo_proceso) {
		this.id_tipo_proceso = id_tipo_proceso;
	}

	public Date getFecha_llegada() {
		return fecha_llegada;
	}

	public void setFecha_llegada(Date fecha_llegada) {
		this.fecha_llegada = fecha_llegada;
	}

	public void setFecha_llegada_str(String fecha_llegada) {
		this.fecha_llegada = StringUtils.toDate(fecha_llegada);
	}

	public String getFecha_llegada_str() {
		if (fecha_llegada != null) {
			return StringUtils.toString(fecha_llegada);
		}
		return null;
	}

	public Date getFecha_notificacion() {
		return fecha_notificacion;
	}

	public void setFecha_notificacion(Date fecha_notificacion) {
		this.fecha_notificacion = fecha_notificacion;
	}

	public String getMotivo_estado() {
		return motivo_estado;
	}

	public void setMotivo_estado(String motivo_estado) {
		this.motivo_estado = motivo_estado;
	}

	public Integer getDuplicados() {
		return duplicados;
	}

	public void setDuplicados(Integer duplicados) {
		this.duplicados = duplicados;
	}

	public String getNumero_proceso() {
		return numero_proceso;
	}

	public void setNumero_proceso(String numero_proceso) {
		this.numero_proceso = numero_proceso;
	}

	public String getRadicado() {
		return radicado;
	}

	public void setRadicado(String radicado) {
		this.radicado = radicado;
	}

}
