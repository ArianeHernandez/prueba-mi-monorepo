package com.osmosyscol.datasuite.near.servicios.radica;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import co.htsoft.commons.util.SMessage;

import com.google.gson.Gson;
import com.osmosyscol.datasuite.near.webdata.Abstract_Postal_Envio_Principal;
import com.osmosyscol.datasuite.near.webdata.Postal_AutoAdmision_Principal;
import com.osmosyscol.datasuite.near.webdata.Postal_RadicaSolicitud2;
import com.osmosyscol.datasuite.servlet.InitApp;

public class TestAbstract_Postal_Envio_Principal {
	
	private static Gson gson = new Gson();
	
	public static void main(String[] args) {
		InitApp.startUp();
		
		testEjecutar();
		System.exit(0);
	}
	
	public static void testEjecutar() {
		
		// fail("Not yet implemented");
		SMessage out;
				
		// requerimiento con solicitud no existente
//		Abstract_Postal_Envio_Principal accion = new Postal_AutoAdmision_Principal();
		
		Postal_RadicaSolicitud2 accion = new Postal_RadicaSolicitud2();
		
		
		
		int id_carga = 111289;//
		out = accion.ejecutar(id_carga);
		
		System.out.println("SALIDA: "+out);
		
//				assertNotNull(out);
//				assertFalse(out.getValid());
				
	}

}
