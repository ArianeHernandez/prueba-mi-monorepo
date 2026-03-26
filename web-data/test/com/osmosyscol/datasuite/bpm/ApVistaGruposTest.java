package com.osmosyscol.datasuite.bpm;

import junit.framework.Assert;

import com.osmosyscol.datasuite.bpm.acciones.ApVistasGruposAcc;
import com.osmosyscol.datasuite.servlet.InitApp;

public class ApVistaGruposTest {
	
	public static void main(String[] args) throws Exception {
		InitApp.startUp();
		
		getListaGruposTest();
//		getGrupoPorIdTest();
//		getListaGruposPorParametroTest();
		getNombreForIdVistaAndGrupoTest();
		
		System.exit(0);
		
	}
	
	public static void getListaGruposTest(){
		ApVistasGruposAcc instance = ApVistasGruposAcc.getInstance();
		
		String salida = instance.getAll();
		
		System.out.println("SALIDA: "+salida);
	}
	
	public static void getGrupoPorIdTest(){
		ApVistasGruposAcc instance = ApVistasGruposAcc.getInstance();
		
		String salida = instance.getPorId(5);
		
		System.out.println("SALIDA: "+salida);
		
//		Assert.assertEquals("115515151", salida);
	}
	
	public static void getListaGruposPorParametroTest() throws Exception{
		ApVistasGruposAcc instance = ApVistasGruposAcc.getInstance();
		
		String salida = instance.getListaGruposPorParametro("id vista","4412");
		
		System.out.println("SALIDA: "+salida);
	}
	
	public static void getNombreForIdVistaAndGrupoTest(){
		ApVistasGruposAcc instance = ApVistasGruposAcc.getInstance();
		
		String salida = instance.getNombreForIdVistaAndGrupo("4412","Naturaleza");
		
		System.out.println("SALIDA: "+salida);
	}
	
	
	
}
