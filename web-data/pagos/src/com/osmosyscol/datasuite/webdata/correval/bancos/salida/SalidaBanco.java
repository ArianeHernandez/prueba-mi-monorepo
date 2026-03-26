package com.osmosyscol.datasuite.webdata.correval.bancos.salida;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class SalidaBanco {

	private String cod_banco_empresa;
	private String tipo_cuenta_empresa;
	private Long num_cuenta_empresa;
	private Long num_cuenta_principal_empresa;
	private Long nit_empresa = new Long(ParametrosInicio.getProperty("nitEmpresa"));
	private String nombre_empresa = ParametrosInicio.getProperty("nombreEmpresa");
	private Date fecha_hoy = new Date();
	private Integer num_cargadeldia = 0;
	private Date fecha_grupo;
	private Date fecha_creacion;
	private String codigo_oficina_empresa;
	private String direccion_empresa;

	private List<RegistroEntradaBanco> registro = new ArrayList<RegistroEntradaBanco>();

	// ------------------------------------------

	public String getDireccion_empresa() {
		direccion_empresa = ParametrosInicio.getProperty("direccionEmpresa");
		return direccion_empresa;
	}

	public void setDireccion_empresa(String direccion_empresa) {
		this.direccion_empresa = direccion_empresa;
	}

	public String getCod_banco_empresa() {
		return cod_banco_empresa;
	}

	public void setCod_banco_empresa(String cod_banco_empresa) {
		this.cod_banco_empresa = cod_banco_empresa;
	}

	public String getTipo_cuenta_empresa() {
		return tipo_cuenta_empresa;
	}

	public void setTipo_cuenta_empresa(String tipo_cuenta_empresa) {
		this.tipo_cuenta_empresa = tipo_cuenta_empresa;
	}

	public Long getNum_cuenta_empresa() {
		return num_cuenta_empresa;
	}

	public void setNum_cuenta_empresa(Long num_cuenta_empresa) {
		this.num_cuenta_empresa = num_cuenta_empresa;
	}

	public Long getNum_cuenta_principal_empresa() {
		return num_cuenta_principal_empresa;
	}

	public void setNum_cuenta_principal_empresa(Long num_cuenta_principal_empresa) {
		this.num_cuenta_principal_empresa = num_cuenta_principal_empresa;
	}

	public Long getNit_empresa() {

		Long nit = new Long(ParametrosInicio.getProperty("nitEmpresa"));

		if (nit != null && nit > 0) {
			nit_empresa = nit;
		} else {
			nit_empresa = 8600681825L;
		}

		return nit_empresa;
	}

	public void setNit_empresa(Long nit_empresa) {
		this.nit_empresa = nit_empresa;
	}

	public String getNombre_empresa() {
		return nombre_empresa;
	}

	public void setNombre_empresa(String nombre_empresa) {
		this.nombre_empresa = nombre_empresa;
	}

	public Date getFecha_hoy() {
		return fecha_hoy;
	}

	public void setFecha_hoy(Date fecha_hoy) {
		this.fecha_hoy = fecha_hoy;
	}

	public Integer getNum_cargadeldia() {
		return num_cargadeldia;
	}

	public void setNum_cargadeldia(Integer num_cargadeldia) {
		this.num_cargadeldia = num_cargadeldia;
	}

	public Date getFecha_grupo() {
		return fecha_grupo;
	}

	public void setFecha_grupo(Date fecha_grupo) {
		this.fecha_grupo = fecha_grupo;
	}

	public String getCodigo_oficina_empresa() {
		return codigo_oficina_empresa;
	}

	public void setCodigo_oficina_empresa(String codigo_oficina_empresa) {
		this.codigo_oficina_empresa = codigo_oficina_empresa;
	}

	public List<RegistroEntradaBanco> getRegistro() {
		return registro;
	}

	public void setRegistro(List<RegistroEntradaBanco> registro) {
		this.registro = registro;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

}
