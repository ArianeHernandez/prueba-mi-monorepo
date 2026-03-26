package com.osmosyscol.datasuite.near.servicios.radica;

import com.osmosyscol.datasuite.near.webdata.ActualizarInadmisionSolicitudInicial;
import com.osmosyscol.datasuite.servlet.InitApp;

public class TestActualizarInadmisionSolicitudInicial {

	public static void main(String[] args) {
			
		try {
			
			InitApp.startUp();
			
			ActualizarInadmisionSolicitudInicial actualizar = new ActualizarInadmisionSolicitudInicial();
			
			actualizar.ejecutar(115688);
			
		} catch (Exception e) {
			System.err.println("Error en ejecucion de prueba");
			e.printStackTrace();
		}
			
		System.exit(0);
		
	}
	
}
