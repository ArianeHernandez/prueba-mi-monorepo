package com.osmosyscol.datasuite.near.interop.postal.adjuntos;

public class PostalInfo {

	private String Radicacion;
	private String UploadBy;
	private String TotalDocumentos;

	public String getRadicacion() {
		return Radicacion;
	}

	public void setRadicacion(String radicacion) {
		Radicacion = radicacion;
	}
	
	public String getUploadBy() {
		return UploadBy;
	}

	public void setUploadBy(String uploadBy) {
		UploadBy = uploadBy;
	}
	
	
	public String getTotalDocumentos() {
		return TotalDocumentos;
	}

	public void setTotalDocumentos(String totalDocumentos) {
		TotalDocumentos = totalDocumentos;
	}

	public PostalInfo() {
		
	}
	public PostalInfo(String radicacion, String uploadBy, String totalDocumentos) {
		super();
		Radicacion = radicacion;
		UploadBy = uploadBy;
		TotalDocumentos = totalDocumentos;
	}
	
}
