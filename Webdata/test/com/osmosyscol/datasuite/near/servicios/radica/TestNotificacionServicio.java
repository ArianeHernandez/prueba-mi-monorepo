package com.osmosyscol.datasuite.near.servicios.radica;

import com.osmosyscol.commons.servlet.ContextInfo;
import com.osmosyscol.datasuite.mein.servicios.NotificacionServicio;
import com.osmosyscol.datasuite.servlet.InitApp;

public class TestNotificacionServicio {

	public static void main(String[] args) {
		ContextInfo.getInstance().setRealPath("/home/ec2-user/git/Webdata/WebContent");
		InitApp.startUp();
		
		String[] archivos = {"archivo_100.pdf", "documento_200.xlsx"};
		
		NotificacionServicio.getInstance().notificarErrorCargueArchivos(116432, archivos);
		
		System.exit(0);
		
	}
	
}
