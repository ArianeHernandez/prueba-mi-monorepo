package com.osmosyscol.datasuite.webdata.logica.dto;

import java.util.Date;

import com.osmosyscol.datasuite.mein.dtos.DepartamentoDane;
import com.osmosyscol.datasuite.mein.dtos.MunicipioDane;
import com.osmosyscol.datasuite.mein.dtos.TipoDeDocumento;

public class SolicitudEnrolamiento {
	
	private Integer id;
	private Integer idcarga;
	private String nombres;
	private String apellidos;
	private String razon_social;
	private Integer tipo_identificacion;			// SE DEJA PERO SIEMPRE SE ALMACENARA COMO NIT
	private TipoDeDocumento tipo_identificacion_obj;
	private String numero_identificacion;			// NIT DE LA SOCIEDAD
	private Date fecha_de_expedicion;	
	private String fecha_de_expedicion_string;	
	private String direccion;
	private String telefono;
	private Integer pais;
	private String pais_nombre;
	private Integer departamento_dane;
	private DepartamentoDane departamento_obj;
	private Integer municipio_dane;
	private MunicipioDane municipio_obj;
	private String correo_electronico;	
	private String celular;
	private String login;
	private Long valor_individual;
	private Long valor_lote;
	private DatosUsuario datos_usuario;
	private DatosRepresentante datos_representante;	// YA PERMITE TIPO DE DOCUMENTO, NUMERO Y CORREO DEL REP. LEGAL
	private String otp;
	private String estado_operacion;
	private String mensaje_error;
	//tipo_formulario y enviar el nro que corresponda: 1 - sociedad, 2, persona natural comerciante, 3. no comerciante
	private String tipo_formulario;
	private Integer tipo_solicitante;
	
	
	public String getEstado_operacion() {
		return estado_operacion;
	}
	public void setEstado_operacion(String estado_operacion) {
		this.estado_operacion = estado_operacion;
	}
	
	public String getMensajeError() {
		return mensaje_error;
	}
	public void setMensajeError(String mensaje_error) {
		this.mensaje_error = mensaje_error;
	}
	
	
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
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
	public Integer getTipo_identificacion() {
		return tipo_identificacion;
	}
	public void setTipo_identificacion(Integer tipo_identificacion) {
		this.tipo_identificacion = tipo_identificacion;
	}
	public String getNumero_identificacion() {
		return numero_identificacion;
	}
	public void setNumero_identificacion(String numero_identificacion) {
		this.numero_identificacion = numero_identificacion;
	}
	public Date getFecha_de_expedicion() {
		return fecha_de_expedicion;
	}
	public void setFecha_de_expedicion(Date fecha_de_expedicion) {
		this.fecha_de_expedicion = fecha_de_expedicion;
	}
	public String getFecha_de_expedicion_string() {
		return fecha_de_expedicion_string;
	}
	public void setFecha_de_expedicion_string(String fecha_de_expedicion_string) {
		this.fecha_de_expedicion_string = fecha_de_expedicion_string;
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
	public Integer getPais() {
		return pais;
	}
	public void setPais(Integer pais) {
		this.pais = pais;
	}
	public String getPais_nombre() {
		return pais_nombre;
	}
	public void setPais_nombre(String pais_nombre) {
		this.pais_nombre = pais_nombre;
	}
	public String getCorreo_electronico() {
		return correo_electronico;
	}
	public void setCorreo_electronico(String correo_electronico) {
		this.correo_electronico = correo_electronico;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public Long getValor_individual() {
		return valor_individual;
	}
	public void setValor_individual(Long valor_individual) {
		this.valor_individual = valor_individual;
	}
	public Long getValor_lote() {
		return valor_lote;
	}
	public void setValor_lote(Long valor_lote) {
		this.valor_lote = valor_lote;
	}
	public DatosUsuario getDatos_usuario() {
		return datos_usuario;
	}
	public void setDatos_usuario(DatosUsuario datos_usuario) {
		this.datos_usuario = datos_usuario;
	}
	public DatosRepresentante getDatos_representante() {
		return datos_representante;
	}
	public void setDatos_representante(DatosRepresentante datos_representante) {
		this.datos_representante = datos_representante;
	}
	public String getTipo_formulario() {
		return tipo_formulario;
	}
	public void setTipo_formulario(String tipo_formulario) {
		this.tipo_formulario = tipo_formulario;
	}
	public Integer getTipo_solicitante() {
		return tipo_solicitante;
	}
	public void setTipo_solicitante(Integer tipo_solicitante) {
		this.tipo_solicitante = tipo_solicitante;
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
	public TipoDeDocumento getTipo_identificacion_obj() {
		return tipo_identificacion_obj;
	}
	public void setTipo_identificacion_obj(TipoDeDocumento tipo_identificacion_obj) {
		this.tipo_identificacion_obj = tipo_identificacion_obj;
	}
	public DepartamentoDane getDepartamento_obj() {
		return departamento_obj;
	}
	public void setDepartamento_obj(DepartamentoDane departamento_obj) {
		this.departamento_obj = departamento_obj;
	}
	public MunicipioDane getMunicipio_obj() {
		return municipio_obj;
	}
	public void setMunicipio_obj(MunicipioDane municipio_obj) {
		this.municipio_obj = municipio_obj;
	}
	@Override
	public String toString() {
		return "SolicitudEnrolamiento [id=" + id + ", idcarga=" + idcarga
				+ ", nombres=" + nombres + ", apellidos=" + apellidos
				+ ", razon_social=" + razon_social + ", tipo_identificacion="
				+ tipo_identificacion + ", numero_identificacion="
				+ numero_identificacion + ", fecha_de_expedicion="
				+ fecha_de_expedicion + ", fecha_de_expedicion_string="
				+ fecha_de_expedicion_string + ", direccion=" + direccion
				+ ", telefono=" + telefono + ", pais=" + pais
				+ ", pais_nombre=" + pais_nombre + ", departamento_id=" 
				+ departamento_dane + ", municipio_id=" + municipio_dane 
				+ ", correo_electronico=" + correo_electronico 
				+ ", celular=" + celular + ", login=" + login 
				+ ", valor_individual=" + valor_individual
				+ ", valor_lote=" + valor_lote + ", datos_usuario="
				+ datos_usuario + ", datos_representante="
				+ datos_representante + ", otp=" + otp + "]";
	}
}
