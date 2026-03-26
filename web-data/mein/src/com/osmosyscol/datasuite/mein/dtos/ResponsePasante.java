package com.osmosyscol.datasuite.mein.dtos;

public class ResponsePasante {
	private DataDto data;
	private ErrorDto error;
	public DataDto getData() {
		return data;
	}
	public void setData(DataDto data) {
		this.data = data;
	}
	public ErrorDto getError() {
		return error;
	}
	public void setError(ErrorDto error) {
		this.error = error;
	}
	
	
}
