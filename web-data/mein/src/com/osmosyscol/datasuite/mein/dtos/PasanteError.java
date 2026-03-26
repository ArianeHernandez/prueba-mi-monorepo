package com.osmosyscol.datasuite.mein.dtos;

public class PasanteError {
	
	private Integer code;
	private String message;
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}

/*
{
"code": 0,
"message": "success"
}
*/