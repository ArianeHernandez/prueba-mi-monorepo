package com.osmosyscol.datasuite.logica.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datapi.logica.servicios.LogServicio.Estado;
import com.osmosyscol.datasuite.logica.servicios.FtpUsuarioServicio;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.FormatoServicio;

public class FtpUsuarioArchivo {
	
	public static final String ESTADO_PROCESADO = "P";
	public static final String ESTADO_FALLIDO   = "F";
	public static final String ESTADO_TRANSITO  = "T";
	
	private Integer id_ftp_usuario_archivo;
	private Date    fecha_cargue;
	private String  estado;
	private Integer id_ftp_usuario;
	private String  md5;
	private String  duplicado;
	private String  formato_invalido;
	private String  datos_invalidos;
	private String  nombre;
	private Integer id_carga;
	private Integer id_formato;
	
	//----------
	
	private Integer    total_registros;
	private BigDecimal valor_total;
	private String     estado_carga;
	
	
	public Integer getId_ftp_usuario_archivo() {
		return id_ftp_usuario_archivo;
	}

	public void setId_ftp_usuario_archivo(Integer id_ftp_usuario_archivo) {
		this.id_ftp_usuario_archivo = id_ftp_usuario_archivo;
	}

	public Date getFecha_cargue() {
		return fecha_cargue;
	}

	public void setFecha_cargue(Date fecha_cargue) {
		this.fecha_cargue = fecha_cargue;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getId_ftp_usuario() {
		return id_ftp_usuario;
	}

	public void setId_ftp_usuario(Integer id_ftp_usuario) {
		this.id_ftp_usuario = id_ftp_usuario;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getDuplicado() {
		return duplicado;
	}

	public void setDuplicado(String duplicado) {
		this.duplicado = duplicado;
	}

	public String getFormato_invalido() {
		return formato_invalido;
	}

	public void setFormato_invalido(String formato_invalido) {
		this.formato_invalido = formato_invalido;
	}

	public String getDatos_invalidos() {
		return datos_invalidos;
	}

	public void setDatos_invalidos(String datos_invalidos) {
		this.datos_invalidos = datos_invalidos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getFecha_cargue_formato(){
		if (fecha_cargue != null){
			return StringUtils.toStringFormat(fecha_cargue);
		}
		return null;
	}
	
	public Integer getId_carga() {
		return id_carga;
	}

	public void setId_carga(Integer id_carga) {
		this.id_carga = id_carga;
	}

	public String getEstado_formato(){
		if (estado != null){
			if (estado.equals(ESTADO_FALLIDO)){
				return "Fallido";
			}else if (estado.equals(ESTADO_PROCESADO)){
				return "Procesado";
			}else if (estado.equals(ESTADO_TRANSITO)){
				return "En Proceso";
			}
		}
		return null;
	}

	public Integer getId_formato() {
		return id_formato;
	}

	public void setId_formato(Integer id_formato) {
		this.id_formato = id_formato;
	}

	public Integer getTotal_registros() {
		return total_registros;
	}

	public void setTotal_registros(Integer total_registros) {
		this.total_registros = total_registros;
	}

	public BigDecimal getValor_total() {
		return valor_total;
	}

	public void setValor_total(BigDecimal valor_total) {
		this.valor_total = valor_total;
	}

	public String getEstado_carga() {
		return estado_carga;
	}

	public void setEstado_carga(String estado_carga) {
		this.estado_carga = estado_carga;
	}

	public String getNombre_formato() {
		if (id_formato != null){
			return FormatoServicio.getInstance().obtenerFormato(id_formato).getNombre();
		}
		return null;
	}

	public String getCaracteristica() {
		String caracteristica = "";
		String separator = "";
		if ("S".equals(duplicado)){
			caracteristica = caracteristica + separator + "Duplicado";
			separator = " - ";
		}
		if ("S".equals(datos_invalidos)){
			caracteristica = caracteristica + separator + "Datos Inválidos";
			separator = " - ";
		}
		if ("S".equals(formato_invalido)){
			caracteristica = caracteristica + separator + "Formato Inválido";
			separator = " - ";
		}
		return caracteristica;
	}
	
	public String getNombre_usuario(){
		FtpUsuario ftpUsuario = FtpUsuarioServicio.getInstance().obtenerFtpUsuario(id_ftp_usuario);
		if (ftpUsuario != null){
			Persona persona = PersonaServicio.getInstance().obtenerPersona(com.osmosyscol.datasuite.logica.servicios.UsuarioServicio.getInstance().obtenerUsuario(ftpUsuario.getId_usuario()).getId_persona());
			if (persona != null){
				return persona.getNombreCompleto();
			}
		}
		return null;
	}
	
}
