package com.osmosyscol.datasuite.near.servicios.radica;

import com.osmosyscol.datasuite.near.webdata.Postal_RadicaSolicitud2;		
import com.osmosyscol.datasuite.servlet.InitApp;

public class TestPostal_Radicacion_radicar {

	public static void main(String[] args) {
		
		InitApp.startUp();
		
		Postal_RadicaSolicitud2 radicar = new Postal_RadicaSolicitud2();
		
//		radicar.ejecutar(113625);
//		radicar.ejecutar(112816); //NEAR
//		 Los términos de la radicación no pueden ser modificados
//		radicar.ejecutar(113258); //RI - REORGANIZACION ABREVIADA 
		
		//
//		radicar.ejecutar(113803); //RI - LIQUIDACION SIMPLIF 
		
//		radicar.ejecutar(113133); // Liquidacion Simplificada
//		radicar.ejecutar(113109); // NEAR
//		radicar.ejecutar(113248); // Reorganización Abreviada
		
//		radicar.ejecutar(113270); // liquid pnc
		radicar.ejecutar(116330); // near pnnc 
		
		
		System.exit(0);
		
	}
	
}
