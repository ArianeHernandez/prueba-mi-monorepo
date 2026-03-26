package com.osmosyscol.datasuite.bpm;

import com.osmosyscol.datasuite.bpm.acciones.ApVistaIdentificacionTiposAcc;
import com.osmosyscol.datasuite.servlet.InitApp;

public class ApVistaIdentificacionTiposTest {
	
	public static void main(String[] args) throws Exception {
		InitApp.startUp();

		getListaGruposTest();
		getGrupoPorIdTest();
		getListaGruposPorParametroTest();
		
		System.exit(0);
		
	}
	
	public static void getListaGruposTest(){
		ApVistaIdentificacionTiposAcc instance = ApVistaIdentificacionTiposAcc.getInstance();
		
		String salida = instance.getAll();
		
		System.out.println("SALIDA: "+salida);
	}
	
	public static void getGrupoPorIdTest(){
		ApVistaIdentificacionTiposAcc instance = ApVistaIdentificacionTiposAcc.getInstance();
		
		String salida = instance.getPorId(5);
		
		System.out.println("SALIDA: "+salida);
	}
	
	public static void getListaGruposPorParametroTest() throws Exception{
		ApVistaIdentificacionTiposAcc instance = ApVistaIdentificacionTiposAcc.getInstance();
		
		String salida = instance.getPorIdentificacionTipos("PASAPORTE");
		
		System.out.println("SALIDA: "+salida);
	}

}
