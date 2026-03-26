package com.osmosyscol.datasuite.bpm;

import com.osmosyscol.datasuite.bpm.acciones.ApVistaMedioEnvioAcc;
import com.osmosyscol.datasuite.servlet.InitApp;

public class ApVistaMedioEnvioTest {
	
	public static void main(String[] args) throws Exception {
		InitApp.startUp();

		getListaGruposTest();
		getGrupoPorIdTest();
		getListaGruposPorParametroTest();
		
		System.exit(0);
		
	}
	
	public static void getListaGruposTest(){
		ApVistaMedioEnvioAcc instance = ApVistaMedioEnvioAcc.getInstance();
		
		String salida = instance.getAll();
		
		System.out.println("SALIDA: "+salida);
	}
	
	public static void getGrupoPorIdTest(){
		ApVistaMedioEnvioAcc instance = ApVistaMedioEnvioAcc.getInstance();
		
		String salida = instance.getPorId(5);
		
		System.out.println("SALIDA: "+salida);
	}
	
	public static void getListaGruposPorParametroTest() throws Exception{
		ApVistaMedioEnvioAcc instance = ApVistaMedioEnvioAcc.getInstance();
		
		String salida = instance.getPorMedioEnvio("CORREO");
		
		System.out.println("SALIDA: "+salida);
	}

}
