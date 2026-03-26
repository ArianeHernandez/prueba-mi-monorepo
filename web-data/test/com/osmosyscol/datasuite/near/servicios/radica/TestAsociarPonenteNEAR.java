package com.osmosyscol.datasuite.near.servicios.radica;

import com.osmosyscol.datasuite.near.webdata.AsociarPonenteNEAR;
import com.osmosyscol.datasuite.servlet.InitApp;

public class TestAsociarPonenteNEAR {

	public static void main(String[] args) {
		
		InitApp.startUp();
		
		AsociarPonenteNEAR asociar = new AsociarPonenteNEAR();
		
		asociar.ejecutar(114062);
		
		System.exit(0);
		
	}
	
}
