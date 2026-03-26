package com.osmosyscol.datasuite.system.dto;

import java.text.DateFormat;
import java.util.Date;


public class LogFile {

	private String lastModified;
	private String name;
	private String size;

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getLastModified() {
		return lastModified;
	}

	public void setLastModified(Long lastModified) {
		Date fecha= new Date(lastModified);
		String fechaMod= DateFormat.getInstance().format(fecha);
		if(fechaMod.length()==16)
		{
			fechaMod="0"+fechaMod;
		}
		this.lastModified = fechaMod;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "LogFile [lastModified=" + lastModified + ", name=" + name
				+ ", size=" + size + "]";
	}


	
}
