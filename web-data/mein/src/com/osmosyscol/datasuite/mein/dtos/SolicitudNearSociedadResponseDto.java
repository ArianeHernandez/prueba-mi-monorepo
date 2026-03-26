package com.osmosyscol.datasuite.mein.dtos;

import java.util.List;

public class SolicitudNearSociedadResponseDto {
	private List<DataSolicitudNearSociedadDto> data;
	private PasanteError error;
	
	public List<DataSolicitudNearSociedadDto> getData() {
		return data;
	}
	public void setData(List<DataSolicitudNearSociedadDto> data) {
		this.data = data;
	}
	public PasanteError getError() {
		return error;
	}
	public void setError(PasanteError error) {
		this.error = error;
	}
	
}
