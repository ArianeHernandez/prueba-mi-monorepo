package com.osmosyscol.datasuite.mein.dtos;

public class FieldData {

	private String field;
	private String value;
	private FieldData[] data;
	private Boolean multiple = false;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public FieldData[] getData() {
		return data;
	}

	public void setData(FieldData[] data) {
		this.data = data;
	}

	public Boolean getMultiple() {
		return multiple;
	}

	public void setMultiple(Boolean multiple) {
		this.multiple = multiple;
	}
	
}
