package com.osmosyscol.datasuite.mein.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class EnrolamientoCliente {

	private Integer id;
	private Integer idcarga;
	private PaisDane Pais;
	private TipoDeDocumento tipo_identificacion;
	private BigDecimal valor_individual;
	private BigDecimal valor_lote;
	private String severidad;
	private String nombres;
	private String apellidos;
	private String razon_social;
	private String numero_identificacion;
	private String celular;
	private Date fecha_de_expedicion;
	private String direccion;
	private String telefono;
	private String estado_operacion;
	private String login;
	private String tipo_formulario;
	private String correo_electronico;
	private Integer pais_id;
	private Integer tipo_identificacion_id;
	private Integer departamento_dane;
	private DepartamentoDane departamento_dane_obj;
	private Integer municipio_dane;
	private MunicipioDane municipio_dane_obj;
	private Integer datos_representante;
	
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
	public PaisDane getPais() {
		return Pais;
	}
	public void setPais(PaisDane pais) {
		Pais = pais;
	}
	public TipoDeDocumento getTipo_identificacion() {
		return tipo_identificacion;
	}
	public void setTipo_identificacion(TipoDeDocumento tipo_identificacion) {
		this.tipo_identificacion = tipo_identificacion;
	}
	public BigDecimal getValor_individual() {
		return valor_individual;
	}
	public void setValor_individual(BigDecimal valor_individual) {
		this.valor_individual = valor_individual;
	}
	public BigDecimal getValor_lote() {
		return valor_lote;
	}
	public void setValor_lote(BigDecimal valor_lote) {
		this.valor_lote = valor_lote;
	}
	public String getSeveridad() {
		return severidad;
	}
	public void setSeveridad(String severidad) {
		this.severidad = severidad;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getRazon_social() {
		return razon_social;
	}
	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
	}
	public String getNumero_identificacion() {
		return numero_identificacion;
	}
	public void setNumero_identificacion(String numero_identificacion) {
		this.numero_identificacion = numero_identificacion;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public Date getFecha_de_expedicion() {
		return fecha_de_expedicion;
	}
	public void setFecha_de_expedicion(Date fecha_de_expedicion) {
		this.fecha_de_expedicion = fecha_de_expedicion;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEstado_operacion() {
		return estado_operacion;
	}
	public void setEstado_operacion(String estado_operacion) {
		this.estado_operacion = estado_operacion;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getTipo_formulario() {
		return tipo_formulario;
	}
	public void setTipo_formulario(String tipo_formulario) {
		this.tipo_formulario = tipo_formulario;
	}
	public String getCorreo_electronico() {
		return correo_electronico;
	}
	public void setCorreo_electronico(String correo_electronico) {
		this.correo_electronico = correo_electronico;
	}
	public Integer getPais_id() {
		return pais_id;
	}
	public void setPais_id(Integer pais_id) {
		this.pais_id = pais_id;
	}
	public Integer getTipo_identificacion_id() {
		return tipo_identificacion_id;
	}
	public void setTipo_identificacion_id(Integer tipo_identificacion_id) {
		this.tipo_identificacion_id = tipo_identificacion_id;
	}
	public Integer getDepartamento_dane() {
		return departamento_dane;
	}
	public void setDepartamento_dane(Integer departamento_dane) {
		this.departamento_dane = departamento_dane;
	}
	public Integer getMunicipio_dane() {
		return municipio_dane;
	}
	public void setMunicipio_dane(Integer municipio_dane) {
		this.municipio_dane = municipio_dane;
	}
	public DepartamentoDane getDepartamento_dane_obj() {
		return departamento_dane_obj;
	}
	public void setDepartamento_dane_obj(DepartamentoDane departamento_dane_obj) {
		this.departamento_dane_obj = departamento_dane_obj;
	}
	public MunicipioDane getMunicipio_dane_obj() {
		return municipio_dane_obj;
	}
	public void setMunicipio_dane_obj(MunicipioDane municipio_dane_obj) {
		this.municipio_dane_obj = municipio_dane_obj;
	}
	public Integer getDatos_representante() {
		return datos_representante;
	}
	public void setDatos_representante(Integer datos_representante) {
		this.datos_representante = datos_representante;
	}
	
}
