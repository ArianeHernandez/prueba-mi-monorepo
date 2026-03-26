package com.osmosyscol.datasuite.reportedim.dto;

public class ParametroReporte {

	public static final String TIPO_STRING = "string";
	public static final String TIPO_INT = "integer";
	public static final String TIPO_DATE = "date";
	public static final String TIPO_FLOAT = "float";
	public static final String TIPO_DOUBLE = "double";
	public static final String TIPO_BOOLEAN = "boolean";
	public static final String TIPO_MONEY = "money";
	
	private String nombre;
	private String tipo;
	private String encriptado; // {S,N}
	private Boolean filtro = false;
	private String titulo;
	private Integer orden;
	private Integer id_parametro;
	private String id_reporte;
	
	public ParametroReporte(){
		
	}
	
	public ParametroReporte(ParametroReporte pr){
		this.nombre = (pr.getNombre()==null)?null:new String(pr.getNombre());
		this.tipo = (pr.getTipo()==null)?null:new String(pr.getTipo());
		this.encriptado = (pr.getEncriptado()==null)?null:new String(pr.getEncriptado());
		this.filtro = (pr.getFiltro()==null)?null:new Boolean(pr.getFiltro());
		this.titulo = (pr.getTitulo()==null)?null:new String(pr.getTitulo());
		this.orden = (pr.getOrden()==null)?null:new Integer(pr.getOrden());
		this.id_parametro = (pr.getId_parametro()==null)?null:new Integer(pr.getId_parametro());
		this.id_reporte = (pr.getId_reporte()==null)?null:new String(pr.getId_reporte());
	}
	
	public String getId_reporte() {
		return id_reporte;
	}

	public void setId_reporte(String id_reporte) {
		this.id_reporte = id_reporte;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEncriptado() {
		return encriptado;
	}

	public void setEncriptado(String encriptado) {
		this.encriptado = encriptado;
	}

	public Boolean getFiltro() {
		return filtro;
	}

	public void setFiltro(Boolean esFiltro) {
		this.filtro = esFiltro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public void setId_parametro(Integer id_parametro) {
		this.id_parametro = id_parametro;
	}

	public Integer getId_parametro() {
		return id_parametro;
	}

	@Override
	public String toString() {
		return "ParametroReporte [nombre=" + nombre + ", tipo=" + tipo
				+ ", encriptado=" + encriptado + ", filtro=" + filtro
				+ ", titulo=" + titulo + ", orden=" + orden + ", id_parametro="
				+ id_parametro + ", id_reporte=" + id_reporte + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((encriptado == null) ? 0 : encriptado.hashCode());
		result = prime * result + ((filtro == null) ? 0 : filtro.hashCode());
		result = prime * result
				+ ((id_parametro == null) ? 0 : id_parametro.hashCode());
		result = prime * result
				+ ((id_reporte == null) ? 0 : id_reporte.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((orden == null) ? 0 : orden.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		ParametroReporte other = (ParametroReporte) obj;
		if (encriptado == null) {
			if (other.encriptado != null)
				return false;
		} else if (!encriptado.equals(other.encriptado))
			return false;
		if (filtro == null) {
			if (other.filtro != null)
				return false;
		} else if (!filtro.equals(other.filtro))
			return false;
		if (id_parametro == null) {
			if (other.id_parametro != null)
				return false;
		} else if (!id_parametro.equals(other.id_parametro))
			return false;
		if (id_reporte == null) {
			if (other.id_reporte != null)
				return false;
		} else if (!id_reporte.equals(other.id_reporte))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (orden == null) {
			if (other.orden != null)
				return false;
		} else if (!orden.equals(other.orden))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}
	
}
