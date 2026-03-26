package com.osmosyscol.datasuite.mein.dtos;

public class TokenReplaceRequest {

	private TokenMapping[] tokenMapping;
	private FieldData[] data;
	private String base64;

	public TokenMapping[] getTokenMapping() {
		return tokenMapping;
	}

	public void setTokenMapping(TokenMapping[] tokenMapping) {
		this.tokenMapping = tokenMapping;
	}

	public FieldData[] getData() {
		return data;
	}

	public void setData(FieldData[] data) {
		this.data = data;
	}

	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}
		
}
