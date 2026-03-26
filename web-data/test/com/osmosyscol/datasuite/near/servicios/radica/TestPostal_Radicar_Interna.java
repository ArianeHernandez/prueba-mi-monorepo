package com.osmosyscol.datasuite.near.servicios.radica;

import com.osmosyscol.datasuite.near.webdata.Postal_RadicarInterna;
import com.osmosyscol.datasuite.servlet.InitApp;

public class TestPostal_Radicar_Interna {

	public static void main(String[] args) {
		
		InitApp.startUp();
		
		Postal_RadicarInterna radicarInterna = new Postal_RadicarInterna();
		
		radicarInterna.ejecutar(112548);
		
		System.exit(0);
		
	}
	
}
