package com.osmosyscol.datasuite.webdata.correval.confCuentaACH.dto;

public class Producto {

	private Integer id;
	private String nombre;
	private Integer lineaDeProducto;
	private String nombreNegocio;
	private String codigo;
	private String iac;
	private Integer codigoAsobancaria;
	private Integer cuentaDefecto;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getLineaDeProducto() {
		return lineaDeProducto;
	}

	public void setLineaDeProducto(Integer lineaDeProducto) {
		this.lineaDeProducto = lineaDeProducto;
	}

	public String getNombreNegocio() {
		return nombreNegocio;
	}

	public void setNombreNegocio(String nombreNegocio) {
		this.nombreNegocio = nombreNegocio;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getIac() {
		return iac;
	}

	public void setIac(String iac) {
		this.iac = iac;
	}

	public Integer getCodigoAsobancaria() {
		return codigoAsobancaria;
	}

	public void setCodigoAsobancaria(Integer codigoAsobancaria) {
		this.codigoAsobancaria = codigoAsobancaria;
	}

	public Integer getCuentaDefecto() {
		return cuentaDefecto;
	}

	public void setCuentaDefecto(Integer cuentaDefecto) {
		this.cuentaDefecto = cuentaDefecto;
	}



}
