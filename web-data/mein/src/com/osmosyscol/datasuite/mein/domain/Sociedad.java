package com.osmosyscol.datasuite.mein.domain;

import java.util.ArrayList;
import java.util.List;

public class Sociedad {

	private String razon_social;
	private String nit;
	private String objeto_social;
	private String matricula;
	private List<Representante> representantes_legales = new ArrayList<Representante>();
	
	public String getRazon_social() {
		return razon_social;
	}
	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
	}
	public String getNit() {
		return nit;
	}
	public void setNit(String nit) {
		this.nit = nit;
	}
	public String getObjeto_social() {
		return objeto_social;
	}
	public void setObjeto_social(String objeto_social) {
		this.objeto_social = objeto_social;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public List<Representante> getRepresentantes_legales() {
		return representantes_legales;
	}
	public void setRepresentantes_legales(List<Representante> representantes_legales) {
		this.representantes_legales = representantes_legales;
	}
	@Override
	public String toString() {
		return "Sociedad [razon_social=" + razon_social + ", nit=" + nit
				+ ", objeto_social=" + objeto_social + ", matricula="
				+ matricula + ", representantes_legales="
				+ representantes_legales + "]";
	}
	
}
