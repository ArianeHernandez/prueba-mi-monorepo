package com.osmosyscol.datasuite.reportedim.dto;

public class AsignacionReporte {

	private Integer id_asignacion;
	private String id_reporte;
	private Integer id_rol; // tipo de administrativo en fidupagos
	private String titulo;
	private String activo;
	private Integer id_rol_auth; // rol general
	private Integer id_cliente;
	
	public AsignacionReporte(){
		
	}
		
	public AsignacionReporte(Integer id_asignacion, String id_reporte,
			Integer id_rol, String titulo, String activo, Integer id_rol_auth,
			Integer id_cliente) {
		super();
		this.id_asignacion = id_asignacion;
		this.id_reporte = id_reporte;
		this.id_rol = id_rol;
		this.titulo = titulo;
		this.activo = activo;
		this.id_rol_auth = id_rol_auth;
		this.id_cliente = id_cliente;
	}

	public Integer getId_asignacion() {
		return id_asignacion;
	}
	public void setId_asignacion(Integer id_asignacion) {
		this.id_asignacion = id_asignacion;
	}
	public String getId_reporte() {
		return id_reporte;
	}
	public void setId_reporte(String id_reporte) {
		this.id_reporte = id_reporte;
	}
	public Integer getId_rol() {
		return id_rol;
	}
	public void setId_rol(Integer id_rol) {
		this.id_rol = id_rol;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}
	public Integer getId_rol_auth() {
		return id_rol_auth;
	}
	public void setId_rol_auth(Integer id_rol_auth) {
		this.id_rol_auth = id_rol_auth;
	}
	public Integer getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}
	@Override
	public String toString() {
		return "AsignacionReporte [id_asignacion=" + id_asignacion
				+ ", id_reporte=" + id_reporte + ", id_rol=" + id_rol
				+ ", titulo=" + titulo + ", activo=" + activo
				+ ", id_cliente=" + id_cliente
				+ ", id_rol_auth=" + id_rol_auth + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activo == null) ? 0 : activo.hashCode());
		result = prime * result
				+ ((id_asignacion == null) ? 0 : id_asignacion.hashCode());
		result = prime * result
				+ ((id_cliente == null) ? 0 : id_cliente.hashCode());
		result = prime * result
				+ ((id_reporte == null) ? 0 : id_reporte.hashCode());
		result = prime * result + ((id_rol == null) ? 0 : id_rol.hashCode());
		result = prime * result
				+ ((id_rol_auth == null) ? 0 : id_rol_auth.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AsignacionReporte other = (AsignacionReporte) obj;
		if (activo == null) {
			if (other.activo != null)
				return false;
		} else if (!activo.equals(other.activo))
			return false;
		if (id_asignacion == null) {
			if (other.id_asignacion != null)
				return false;
		} else if (!id_asignacion.equals(other.id_asignacion))
			return false;
		if (id_cliente == null) {
			if (other.id_cliente != null)
				return false;
		} else if (!id_cliente.equals(other.id_cliente))
			return false;
		if (id_reporte == null) {
			if (other.id_reporte != null)
				return false;
		} else if (!id_reporte.equals(other.id_reporte))
			return false;
		if (id_rol == null) {
			if (other.id_rol != null)
				return false;
		} else if (!id_rol.equals(other.id_rol))
			return false;
		if (id_rol_auth == null) {
			if (other.id_rol_auth != null)
				return false;
		} else if (!id_rol_auth.equals(other.id_rol_auth))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

	
	
}
