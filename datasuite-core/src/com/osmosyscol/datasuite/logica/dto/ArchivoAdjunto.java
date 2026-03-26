package com.osmosyscol.datasuite.logica.dto;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.osmosyscol.commons.utils.FileUtils;

/**
 * @author oaortizs
 *
 */
public class ArchivoAdjunto {

	private Integer id_archivo_adjunto;

	private String ruta;

	private Integer id_carga;

	private String nombre_archivo;

	private String extension_archivo;

	private Date fecha_subida;

	private String descripcion;

	private String long_bytes;

	private String resumen_hash;

	private String fecha_subidaString;

	private String interno;

	private Integer id_persona;

	private String nombre;
	
	private String apellido;
	
	private String estado;
	
	private String nombre_instancia;
	
	private String radicado;
	
	private Integer id_tipo_archivo;
	
	private String etiqueta;

	public Integer getId_persona() {
		return id_persona;
	}

	public void setId_persona(Integer id_persona) {
		this.id_persona = id_persona;
	}

	public String getInterno() {
		return interno;
	}

	public void setInterno(String interno) {
		this.interno = interno;
	}

	public String getFecha_subidaString() {
		return fecha_subidaString;
	}

	public void setFecha_subidaString(String fechaSubidaString) {
		fecha_subidaString = fechaSubidaString;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getId_archivo_adjunto() {
		return id_archivo_adjunto;
	}

	public void setId_archivo_adjunto(Integer idArchivoAdjunto) {
		id_archivo_adjunto = idArchivoAdjunto;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public Integer getId_carga() {
		return id_carga;
	}

	public void setId_carga(Integer idCarga) {
		id_carga = idCarga;
	}

	public String getNombre_archivo() {
		return nombre_archivo;
	}

	public void setNombre_archivo(String nombreArchivo) {
		nombre_archivo = nombreArchivo;
	}

	public String getExtension_archivo() {
		return extension_archivo;
	}

	public void setExtension_archivo(String extensionArchivo) {
		extension_archivo = extensionArchivo;
	}

	public Date getFecha_subida() {
		return fecha_subida;
	}

	public void setFecha_subida(Date fechaSubida) {

		fecha_subida = fechaSubida;

		SimpleDateFormat formateador = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
		this.fecha_subidaString = formateador.format(fechaSubida);
	}

	public String getLong_bytes() {
		return long_bytes;
	}

	public void setLong_bytes(String longBytes) {
		long_bytes = longBytes;
	}

	public String getResumen_hash() {
		return resumen_hash;
	}

	public void setResumen_hash(String resumenHash) {
		resumen_hash = resumenHash;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Boolean getCheckSumValido() {
		try {
			if (!ruta.startsWith("s3://")) {
				File file = new File(ruta);
				Long cs = FileUtils.checksumCRC32(file);
				if (resumen_hash.equals(cs.toString())) {
					return true;
				}				
			} else {
				return true;
			}
		} catch (Exception e) {
		}
		return false;
	}

	public String getNombre_instancia() {
		return nombre_instancia;
	}

	public void setNombre_instancia(String nombre_instancia) {
		this.nombre_instancia = nombre_instancia;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getRadicado() {
		return radicado;
	}

	public void setRadicado(String radicado) {
		this.radicado = radicado;
	}

	public Integer getId_tipo_archivo() {
		return id_tipo_archivo;
	}

	public void setId_tipo_archivo(Integer id_tipo_archivo) {
		this.id_tipo_archivo = id_tipo_archivo;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	
}
