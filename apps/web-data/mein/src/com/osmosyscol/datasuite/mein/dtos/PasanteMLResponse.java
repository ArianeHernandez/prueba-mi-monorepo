package com.osmosyscol.datasuite.mein.dtos;

import com.google.gson.annotations.SerializedName;

public class PasanteMLResponse {

	@SerializedName("Result_Clasificacion_Tipo_Doc")
	private String resultClasificacionTipoDoc;
	
	@SerializedName("NIT")
	private String nit;
	
	@SerializedName("Cedula_Representante_Legal")
	private String cedulaRepresentanteLegal;
	
	@SerializedName("Status")
	private String status;
	
	@SerializedName("ID_Deudor_59_Cedula")
	private String idDeudorCedula;

	public String getResultClasificacionTipoDoc() {
		return resultClasificacionTipoDoc;
	}

	public void setResultClasificacionTipoDoc(String resultClasificacionTipoDoc) {
		this.resultClasificacionTipoDoc = resultClasificacionTipoDoc;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getCedulaRepresentanteLegal() {
		return cedulaRepresentanteLegal;
	}

	public void setCedulaRepresentanteLegal(String cedulaRepresentanteLegal) {
		this.cedulaRepresentanteLegal = cedulaRepresentanteLegal;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIdDeudorCedula() {
		return idDeudorCedula;
	}

	public void setIdDeudorCedula(String idDeudorCedula) {
		this.idDeudorCedula = idDeudorCedula;
	}

	@Override
	public String toString() {
		return "PasanteMLResponse [resultClasificacionTipoDoc="
				+ resultClasificacionTipoDoc + ", nit=" + nit
				+ ", cedulaRepresentanteLegal=" + cedulaRepresentanteLegal
				+ ", status=" + status + ", idDeudorCedula=" + idDeudorCedula
				+ "]";
	}
		
}
