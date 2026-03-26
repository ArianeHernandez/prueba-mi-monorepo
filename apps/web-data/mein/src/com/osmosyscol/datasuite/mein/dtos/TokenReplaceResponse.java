package com.osmosyscol.datasuite.mein.dtos;

public class TokenReplaceResponse {

	private String wordbase64;
	private Boolean valid = false;
	private String message;

	public String getWordbase64() {
		return wordbase64;
	}

	public void setWordbase64(String wordbase64) {
		this.wordbase64 = wordbase64;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
