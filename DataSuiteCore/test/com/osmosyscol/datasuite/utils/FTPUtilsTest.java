package com.osmosyscol.datasuite.utils;

import java.io.File;

import com.osmosyscol.commons.servlet.ContextInfo;

public class FTPUtilsTest {

	public static void main(String[] args) {
		
		
		FtpUtils client = new FtpUtils("192.168.1.53", 21, "testapp", "Meconio3");
		
		System.out.println(client.connect());
		
		client.getFtpFiles("/files/Bogota/Backup/");
		
		
		client.getFile("/files/Bogota/Backup/", "F20170111.094415.LH4.prueba_01.txt", new File("prueba.txt"));
		
		
		client.disconnect();
		
	}
	
}
