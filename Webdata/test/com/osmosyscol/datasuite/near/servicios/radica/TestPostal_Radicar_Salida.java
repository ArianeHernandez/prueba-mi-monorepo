package com.osmosyscol.datasuite.near.servicios.radica;

import com.osmosyscol.datasuite.near.webdata.Postal_RadicarSalida;
import com.osmosyscol.datasuite.servlet.InitApp;

public class TestPostal_Radicar_Salida {

	public static void main(String[] args) {
		
		InitApp.startUp();
		
		Postal_RadicarSalida radicarSalida = new Postal_RadicarSalida();
		
		radicarSalida.ejecutar(113343);
		
		System.exit(0);
		
	}
	
}
