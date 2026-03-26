package com.osmosyscol.datasuite.mein.dtos;

import java.util.Date;

public class PersonaMein {
	
	private Integer id;
	private Integer idcarga;
	private String tipo_identificacion;
	private Integer tipo_identificacion_id;
	private String tipo_identificacion_nombre;
	private String identificacion;
	private Date fecha_expedicion_doc;
	private String fecha_expedicion_doc_string;
	private String direccion;
	private String nombre_completo;
	private String correo;
	private String celular;
	private String telefono;
	private String tarjeta_profesional;
	private Integer id_persona_mein;
	private Integer pais_id;
	private Integer departamento_id;
	private Integer municipio_id;
	
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
	public String getTipo_identificacion() {
		return tipo_identificacion;
	}
	public void setTipo_identificacion(String tipo_identificacion) {
		this.tipo_identificacion = tipo_identificacion;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public Date getFecha_expedicion_doc() {
		return fecha_expedicion_doc;
	}
	public void setFecha_expedicion_doc(Date fecha_expedicion_doc) {
		this.fecha_expedicion_doc = fecha_expedicion_doc;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getNombre_completo() {
		return nombre_completo;
	}
	public void setNombre_completo(String nombre_completo) {
		this.nombre_completo = nombre_completo;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getTarjeta_profesional() {
		return tarjeta_profesional;
	}
	public void setTarjeta_profesional(String tarjeta_profesional) {
		this.tarjeta_profesional = tarjeta_profesional;
	}
	public String getFecha_expedicion_doc_string() {
		return fecha_expedicion_doc_string;
	}
	public void setFecha_expedicion_doc_string(
			String fecha_expedicion_doc_string) {
		this.fecha_expedicion_doc_string = fecha_expedicion_doc_string;
	}
	public Integer getId_persona_mein() {
		return id_persona_mein;
	}
	public void setId_persona_mein(Integer id_persona_mein) {
		this.id_persona_mein = id_persona_mein;
	}
	public String getTipo_identificacion_nombre() {
		return tipo_identificacion_nombre;
	}
	public void setTipo_identificacion_nombre(String tipo_identificacion_nombre) {
		this.tipo_identificacion_nombre = tipo_identificacion_nombre;
	}
	public Integer getPais_id() {
        return pais_id;
    }

    public void setPais_id(Integer pais_id) {
        this.pais_id = pais_id;
    }

    // Getters y Setters para departamento_id
    public Integer getDepartamento_id() {
        return departamento_id;
    }

    public void setDepartamento_id(Integer departamento_id) {
        this.departamento_id = departamento_id;
    }

    // Getters y Setters para municipio_id
    public Integer getMunicipio_id() {
        return municipio_id;
    }

    public void setMunicipio_id(Integer municipio_id) {
        this.municipio_id = municipio_id;
    }
	@Override
	public String toString() {
		return "PersonaMein [id=" + id + ", idcarga=" + idcarga
				+ ", tipo_identificacion=" + tipo_identificacion
				+ ", tipo_identificacion_nombre=" + tipo_identificacion_nombre
				+ ", identificacion=" + identificacion
				+ ", fecha_expedicion_doc=" + fecha_expedicion_doc
				+ ", fecha_expedicion_doc_string="
				+ fecha_expedicion_doc_string + ", direccion=" + direccion
				+ ", nombre_completo=" + nombre_completo + ", correo=" + correo
				+ ", celular=" + celular + ", telefono=" + telefono
				+ ", tarjeta_profesional=" + tarjeta_profesional
				+ ", id_persona_mein=" + id_persona_mein + ", pais_id="
				+ pais_id + ", departamento_id=" + departamento_id
				+ ", municipio_id=" + municipio_id + "]";
	}
	
}