package com.osmosyscol.datasuite.logica.dto;

import java.util.Date;
import java.util.List;

import com.osmosyscol.commons.utils.StringUtils;

public class Persona {

	private Integer id_persona;
	private String nombre;
	private String apellido;
	private String correo;
	private String identificacion;
	private String telefono;
	private String direccion;
	private String genero;
	private String tipo_persona;
	private String activo;
	private Credencial credencial;
	private Integer tipo_documento;
	private Date fecha_creacion;
	private Integer id;
	private Integer pais_id;
	private Integer departamento_id;
	private Integer municipio_id;
	
	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	private List<TipoProcesoRol> tiposProcesoRol;

	// ---------------------------------

	public Integer getId_persona() {
		return id_persona;
	}

	public void setId_persona(Integer id_persona) {
		this.id_persona = id_persona;
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

	public String getNombreCompleto() {
		return StringUtils.trim(StringUtils.trim(nombre) + " " + StringUtils.trim(apellido));
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getTipo_persona() {
		return tipo_persona;
	}

	public void setTipo_persona(String tipoPersona) {
		tipo_persona = tipoPersona;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getActivo() {
		return activo;
	}

	public Credencial getCredencial() {
		return credencial;
	}

	public void setCredencial(Credencial credencial) {
		this.credencial = credencial;
	}

	public Integer getTipo_documento() {
		return tipo_documento;
	}

	public void setTipo_documento(Integer tipoDocumento) {
		tipo_documento = tipoDocumento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer idRol) {
		id = idRol;
	}

	public List<TipoProcesoRol> getTiposProcesoRol() {
		return tiposProcesoRol;
	}

	public void setTiposProcesoRol(List<TipoProcesoRol> tipoProcesoRol) {
		this.tiposProcesoRol = tipoProcesoRol;
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
		return "Persona [id_persona=" + id_persona + ", nombre=" + nombre
				+ ", apellido=" + apellido + ", correo=" + correo
				+ ", identificacion=" + identificacion + ", telefono="
				+ telefono + ", direccion=" + direccion + ", genero=" + genero
				+ ", tipo_persona=" + tipo_persona + ", activo=" + activo
				+ ", credencial=" + credencial + ", tipo_documento="
				+ tipo_documento + ", fecha_creacion=" + fecha_creacion
				+ ", id=" + id + ", tiposProcesoRol=" + tiposProcesoRol + "]";
	}

}
