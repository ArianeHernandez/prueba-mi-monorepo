package com.osmosyscol.datasuite.logica.servicios;

import com.osmosyscol.datasuite.servlet.InitApp;
import com.osmosyscol.datasuite.webdata.logica.servicios.CronAccionesAutomaticas;

public class CronAccionesAutomaticasTest {

	public static void main(String[] args) {
		InitApp.startUp();
		
		CronAccionesAutomaticas.getInstance().ejecutar();
		
		System.exit(0);
		
	}
}
