package com.osmosyscol.datasuite.bpm;

import com.osmosyscol.datasuite.bpm.acciones.ApVistaSeguridadTipoAcc;
import com.osmosyscol.datasuite.servlet.InitApp;

public class ApVistaSeguridadTipoTest {
	
	public static void main(String[] args) throws Exception {
		InitApp.startUp();

		getListaGruposTest();
		getGrupoPorIdTest();
		getListaGruposPorParametroTest();
		
		System.exit(0);
		
	}
	
	public static void getListaGruposTest(){
		ApVistaSeguridadTipoAcc instance = ApVistaSeguridadTipoAcc.getInstance();
		
		String salida = instance.getAll();
		
		System.out.println("SALIDA: "+salida);
	}
	
	public static void getGrupoPorIdTest(){
		ApVistaSeguridadTipoAcc instance = ApVistaSeguridadTipoAcc.getInstance();
		
		String salida = instance.getPorId(5);
		
		System.out.println("SALIDA: "+salida);
	}
	
	public static void getListaGruposPorParametroTest() throws Exception{
		ApVistaSeguridadTipoAcc instance = ApVistaSeguridadTipoAcc.getInstance();
		
		String salida = instance.getPorSeguridadTipo("VIP");
		
		System.out.println("SALIDA: "+salida);
	}

}
