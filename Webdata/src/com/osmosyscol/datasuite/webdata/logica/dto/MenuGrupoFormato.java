package com.osmosyscol.datasuite.webdata.logica.dto;

import java.util.List;

import com.osmosyscol.datasuite.modelatos.logica.dto.GrupoFormato;

public class MenuGrupoFormato {

	private GrupoFormato grupoFormato;
	private List<ProcesoFormato> listaProcesoFormato;
	
	public GrupoFormato getGrupoFormato() {
		return grupoFormato;
	}
	
	public void setGrupoFormato(GrupoFormato grupoFormato) {
		this.grupoFormato = grupoFormato;
	}
	
	public List<ProcesoFormato> getListaProcesoFormato() {
		return listaProcesoFormato;
	}
	
	public void setListaProcesoFormato(List<ProcesoFormato> listaProcesoFormato) {
		this.listaProcesoFormato = listaProcesoFormato;
	}
}
