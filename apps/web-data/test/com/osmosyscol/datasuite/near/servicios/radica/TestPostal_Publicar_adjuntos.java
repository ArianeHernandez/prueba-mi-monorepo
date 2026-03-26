package com.osmosyscol.datasuite.near.servicios.radica;

import com.osmosyscol.datasuite.near.servicios.AccionPublicarAdjuntosCarga;
import com.osmosyscol.datasuite.servlet.InitApp;

public class TestPostal_Publicar_adjuntos {

public static void main(String[] args) {
		
		InitApp.startUp();
		
		AccionPublicarAdjuntosCarga publicarAdjuntos = new AccionPublicarAdjuntosCarga();
		
		// publicarAdjuntos.ejecutar(111477); // Near
		publicarAdjuntos.ejecutar(111515); //Regimen
		
		System.exit(0);
		
	}
	
}
