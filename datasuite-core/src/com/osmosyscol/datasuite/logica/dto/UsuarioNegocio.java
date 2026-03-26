package com.osmosyscol.datasuite.logica.dto;

public class UsuarioNegocio {

	private Integer id_usuario; 

	private String activo; 

	private String observacion; 

	private Integer id_persona; 

	private String nombre; 

	private String apellido; 

	private String identificacion; 

	private String telefono; 

	private String direccion; 

	private String correo; 

	private String login; 

	private String genero; 
	
	private String tipo_persona;
	
	private Integer id_negocio;
	
	private Integer tipo_documento;

	public Integer getTipo_documento() {
		return tipo_documento;
	}

	public void setTipo_documento(Integer tipoDocumento) {
		tipo_documento = tipoDocumento;
	}

	public Integer getId_negocio() {
		return id_negocio;
	}

	public void setId_negocio(Integer idNegocio) {
		id_negocio = idNegocio;
	}

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer idUsuario) {
		id_usuario = idUsuario;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Integer getId_persona() {
		return id_persona;
	}

	public void setId_persona(Integer idPersona) {
		id_persona = idPersona;
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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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
	
	
}
