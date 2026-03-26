package com.osmosyscol.datasuite.mein.dtos;

import java.util.List;

public class SalidaObtenerRequisito {
	
	public List<Requisito> data;
    public PasanteError error;
    
	public List<Requisito> getData() {
		return data;
	}
	public void setData(List<Requisito> data) {
		this.data = data;
	}
	public PasanteError getError() {
		return error;
	}
	public void setError(PasanteError error) {
		this.error = error;
	}

}
