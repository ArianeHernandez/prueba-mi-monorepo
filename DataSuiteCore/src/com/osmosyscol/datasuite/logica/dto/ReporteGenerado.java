package com.osmosyscol.datasuite.logica.dto;


public class ReporteGenerado {

	private String contentType;
	private byte[] reportAsBytes;
	
	public static void main(String[] args) {
		 //Writes the bytes[] in the response
//        r.setContentType(contentType);
//        r.setHeader("Content-Disposition", "inline");
//        r.setContentLength(reportAsBytes.length);
//
//        ServletOutputStream s = r.getOutputStream();
//
//        s.write(reportAsBytes, 0, reportAsBytes.length);
//
//        s.flush();
//        s.close();
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public byte[] getReportAsBytes() {
		return reportAsBytes;
	}

	public void setReportAsBytes(byte[] reportAsBytes) {
		this.reportAsBytes = reportAsBytes;
	}
}
