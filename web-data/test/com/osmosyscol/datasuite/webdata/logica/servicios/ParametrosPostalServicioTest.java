package com.osmosyscol.datasuite.webdata.logica.servicios;

import org.junit.Assert;

import com.osmosyscol.datasuite.near.servicios.IPostalInteraccion;
import com.osmosyscol.datasuite.servlet.InitApp;

public class ParametrosPostalServicioTest {
	
	public static void main(String[] args) {
		InitApp.startUp();
		
		
		obtenerParametroTest();
		obtenerParametroEntero();
		
		System.exit(0);
		
	}
	
	public static void obtenerParametroTest(){
		ParametrosPostalServicio parametro = ParametrosPostalServicio.getInstance();
		
		String nombreParametro = "postal.puntointeraccion.radicacion.MEDIODEENVIO.nombre";
		String proceso = "1";
		
		String dato = parametro.getDato(nombreParametro, proceso);
		
		if(dato.equals("EXPEDIENTE DIGITAL WEB")){
			System.out.println("TEST DIRECTO OK");
		} else {
			System.out.println("TEST DIRECTO BAD");
		}
		
		Assert.assertEquals("EXPEDIENTE DIGITAL WEB", dato);
//		System.exit(0);
	}
	
	public static void obtenerParametroEntero(){
		ParametrosPostalServicio parametro = ParametrosPostalServicio.getInstance();
		
//		String nombreParametro = parametro"postal.puntointeraccion.oficio_inadmision.funcionario.nombre";
		
		String proceso = "1";
		
		String dato = parametro.getDato(IPostalInteraccion.SVC_TRAMITE_NOMBRE, proceso);
		
		if(dato.equals("HIGHTECH")){
			System.out.println("TEST MEDIANTE INTERFAZ OK");
		} else {
			System.out.println("TEST MEDIANTE INTERFAZ BAD");
		}
		
		Assert.assertEquals("HIGHTECH", dato);
//		System.exit(0);
	}

}
