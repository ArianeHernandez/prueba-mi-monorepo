package com.osmosyscol.datasuite.webdata.logica.servicios;

import org.junit.Assert;

import com.google.gson.Gson;
import com.osmosyscol.datasuite.mein.domain.CerlResponse;
import com.osmosyscol.datasuite.mein.servicios.RuesServicio;
import com.osmosyscol.datasuite.near.servicios.IPostalInteraccion;
import com.osmosyscol.datasuite.servlet.InitApp;

public class RuesServicioTest {
	
	public static void main(String[] args) {
		InitApp.startUp();
		
		
		obtenerDatosRuesTest();
		
		System.exit(0);
		
	}
	
	public static void obtenerDatosRuesTest(){
		RuesServicio ruesServicio = RuesServicio.getInstance();
		Gson gson = new Gson();
		
		String nit = "901006946";
		String numDoc = "1016031266";
		
		CerlResponse rtaRues = ruesServicio.obtenerDatosRues(nit, numDoc);
		String strRtaRues = gson.toJson(rtaRues);
		
		System.out.println("RTA RUES: "+strRtaRues);
		
	}
	
	
}
