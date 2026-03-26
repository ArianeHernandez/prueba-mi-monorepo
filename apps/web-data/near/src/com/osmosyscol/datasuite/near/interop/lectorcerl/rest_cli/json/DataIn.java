package com.osmosyscol.datasuite.near.interop.lectorcerl.rest_cli.json;

public class DataIn {
	
	String url_s3;
	String html_s3;
	
	public DataIn(String url_s3, String html_s3) {
		this.url_s3=url_s3;
		this.html_s3= html_s3;
	}

	public String getUrl_s3() {
		return url_s3;
	}
	public String getHtml_s3() {
		return html_s3;
	}

}
