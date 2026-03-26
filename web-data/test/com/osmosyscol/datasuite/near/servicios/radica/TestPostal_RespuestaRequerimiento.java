package com.osmosyscol.datasuite.near.servicios.radica;

import com.osmosyscol.datasuite.near.webdata.Postal_RespuestaRequerimiento_Principal;
import com.osmosyscol.datasuite.servlet.InitApp;

public class TestPostal_RespuestaRequerimiento {

public static void main(String[] args) {
		
		InitApp.startUp();
		
		Postal_RespuestaRequerimiento_Principal radicar = new Postal_RespuestaRequerimiento_Principal();
		
//		radicar.ejecutar(113625);
		radicar.ejecutar(113789); // NEAR
//		radicar.ejecutar(113774); // LIQUIDACION
//		radicar.ejecutar(113773); // REORGANIZACION
		
		System.exit(0);
		
	}
	
}
