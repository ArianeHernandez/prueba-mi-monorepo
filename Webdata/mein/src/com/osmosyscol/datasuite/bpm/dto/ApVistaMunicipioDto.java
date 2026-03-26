package com.osmosyscol.datasuite.bpm.dto;

import com.osmosyscol.datasuite.mein.dtos.MunicipioDane;

public class ApVistaMunicipioDto {
	private String id_vista;
	private String nombreMunicipio;
	private String codigoMunicipio;
	private String codigoDepartamento;
	private String municipios_dane_id;
	private MunicipioDane municipios_dane;
	
	public String getId_vista() {
		return id_vista;
	}
	public void setId_vista(String id_vista) {
		this.id_vista = id_vista;
	}
	public String getNombreMunicipio() {
		return nombreMunicipio;
	}
	public void setNombreMunicipio(String nombreMunicipio) {
		this.nombreMunicipio = nombreMunicipio;
	}
	public String getCodigoMunicipio() {
		return codigoMunicipio;
	}
	public void setCodigoMunicipio(String codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}
	public String getCodigoDepartamento() {
		return codigoDepartamento;
	}
	public void setCodigoDepartamento(String codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
	}
	public MunicipioDane getMunicipios_dane() {
		return municipios_dane;
	}
	public void setMunicipios_dane(MunicipioDane municipios_dane) {
		this.municipios_dane = municipios_dane;
	}
	public String getMunicipios_dane_id() {
		return municipios_dane_id;
	}
	public void setMunicipios_dane_id(String municipios_dane_id) {
		this.municipios_dane_id = municipios_dane_id;
	}
	
	
	
}
