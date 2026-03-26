package com.osmosyscol.datasuite.reportedim.dto;

import java.util.ArrayList;
import java.util.List;

public class AccionFila {

	private String destino;
	private List<ParametroAccion> parametros = new ArrayList<ParametroAccion>();
	private String subreporte;
	private String id_reporte;
	private String javascript;

	public AccionFila(){
		
	}
	
	public AccionFila(AccionFila af){
		this.destino = (af.getDestino()==null)?null:new String(af.getDestino());
		
		this.parametros = new ArrayList<ParametroAccion>();
		for(ParametroAccion pa : af.getParametros()){
			this.parametros.add(new ParametroAccion(pa));
		}
		
		this.subreporte = (af.getSubreporte()==null)?null:new String(af.getSubreporte());
		this.id_reporte = (af.getId_reporte()==null)?null:new String(af.getId_reporte());
		this.javascript = (af.getJavascript()==null)?null:new String(af.getJavascript());
	}
	
	public String getDestino() {
		return destino;
	}

	public String getId_reporte() {
		return id_reporte;
	}

	public void setId_reporte(String id_reporte) {
		this.id_reporte = id_reporte;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public List<ParametroAccion> getParametros() {
		return parametros;
	}

	public void setParametros(List<ParametroAccion> parametros) {
		this.parametros = parametros;
	}

	public String getSubreporte() {
		return subreporte;
	}

	public void setSubreporte(String subreporte) {
		this.subreporte = subreporte;
	}

	@Override
	public String toString() {
		return "AccionFila [destino=" + destino + ", parametros=" + parametros + ", subreporte=" + subreporte + ", id_reporte=" + id_reporte + "]";
	}

	public void setJavascript(String javascript) {
		this.javascript = javascript;
	}

	public String getJavascript() {
		return javascript;
	}

}
