package com.osmosyscol.datasuite.mein.dtos;

public class TokenMapping {

	private String field;
	private String xpath;

	public TokenMapping() {
	}

	public TokenMapping(String field, String xpath) {
		this.field = field;
		this.xpath = xpath;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getXpath() {
		return xpath;
	}

	public void setXpath(String xpath) {
		this.xpath = xpath;
	}

	
}
