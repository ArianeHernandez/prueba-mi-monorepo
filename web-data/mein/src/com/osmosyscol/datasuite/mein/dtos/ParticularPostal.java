package com.osmosyscol.datasuite.mein.dtos;

public class ParticularPostal {
	
	private Integer id;
	private Integer idcarga;
	private String nombre;
	private TipoDeDocumento tipo_identificacion;
	private String identificacion;
	private String telefono;
	private String direccion;
	private String email;
	private MunicipioDane ciudadCodigo;
	private Integer tipo_identificacion_id;
	private Integer ciudad_codigo_id;
	
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
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public TipoDeDocumento getTipo_identificacion() {
		return tipo_identificacion;
	}
	public void setTipo_identificacion(TipoDeDocumento tipo_identificacion) {
		this.tipo_identificacion = tipo_identificacion;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public MunicipioDane getCiudad() {
		return ciudadCodigo;
	}
	public void setCiudad(MunicipioDane ciudad) {
		this.ciudadCodigo = ciudad;
	}
	public Integer getTipo_identificacion_id() {
		return tipo_identificacion_id;
	}
	public void setTipo_identificacion_id(Integer tipo_identificacion_id) {
		this.tipo_identificacion_id = tipo_identificacion_id;
	}
	public Integer getCiudad_codigo_id() {
		return ciudad_codigo_id;
	}
	public void setCiudad_codigo_id(Integer ciudad_codigo_id) {
		this.ciudad_codigo_id = ciudad_codigo_id;
	}
	
}
