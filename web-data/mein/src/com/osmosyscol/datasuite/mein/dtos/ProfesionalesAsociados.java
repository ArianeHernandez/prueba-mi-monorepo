package com.osmosyscol.datasuite.mein.dtos;

import java.util.List;

public class ProfesionalesAsociados {
	
	private Integer id;
	private PersonaOP contador;
	private Integer id_contador;
	private Integer id_contador_pnnc;
	private PersonaOP revisor_fiscal;
	private Integer id_revisor_fiscal;
	private PersonaOP apoderado;
	private Integer id_apoderado;
	private String poder_abogado;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public PersonaOP getContador() {
		return contador;
	}
	public void setContador(PersonaOP contador) {
		this.contador = contador;
	}
	public Integer getId_contador() {
		return id_contador;
	}
	public void setId_contador(Integer id_contador) {
		this.id_contador = id_contador;
	}
	public PersonaOP getRevisor_fiscal() {
		return revisor_fiscal;
	}
	public void setRevisor_fiscal(PersonaOP revisor_fiscal) {
		this.revisor_fiscal = revisor_fiscal;
	}
	public Integer getId_revisor_fiscal() {
		return id_revisor_fiscal;
	}
	public void setId_revisor_fiscal(Integer id_revisor_fiscal) {
		this.id_revisor_fiscal = id_revisor_fiscal;
	}
	public PersonaOP getApoderado() {
		return apoderado;
	}
	public void setApoderado(PersonaOP apoderado) {
		this.apoderado = apoderado;
	}
	public Integer getId_apoderado() {
		return id_apoderado;
	}
	public void setId_apoderado(Integer id_apoderado) {
		this.id_apoderado = id_apoderado;
	}
	public String getPoder_abogado() {
		return poder_abogado;
	}
	public void setPoder_abogado(String poder_abogado) {
		this.poder_abogado = poder_abogado;
	}
	public Integer getId_contador_pnnc() {
		return id_contador_pnnc;
	}
	public void setId_contador_pnnc(Integer id_contador_pnnc) {
		this.id_contador_pnnc = id_contador_pnnc;
	}
	
	
}
