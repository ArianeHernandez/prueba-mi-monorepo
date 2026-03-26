package com.osmosyscol.datasuite.bpm;

import com.osmosyscol.datasuite.bpm.acciones.ApVistaDocumentoTipoAcc;
import com.osmosyscol.datasuite.bpm.dto.ApVistaDocumentoTipoDto;
import com.osmosyscol.datasuite.servlet.InitApp;

public class ApVistaDocumentoTipoTest {
	
	public static void main(String[] args) throws Exception {
		InitApp.startUp();

		getListaGruposTest();
		getGrupoPorIdTest();
		getListaGruposPorParametroTest();
		
		System.exit(0);
		
	}
	
	public static void getListaGruposTest(){
		ApVistaDocumentoTipoAcc instance = ApVistaDocumentoTipoAcc.getInstance();
		
		String salida = instance.getAll();
		
		System.out.println("SALIDA: "+salida);
	}
	
	public static void getGrupoPorIdTest(){
		ApVistaDocumentoTipoAcc instance = ApVistaDocumentoTipoAcc.getInstance();
		
		ApVistaDocumentoTipoDto salida = instance.getPorId(66);
		
		System.out.println("SALIDA: "+salida);
	}
	
	public static void getListaGruposPorParametroTest() throws Exception{
		ApVistaDocumentoTipoAcc instance = ApVistaDocumentoTipoAcc.getInstance();
		
		String salida = instance.getPorDocumentoTipo("OFICIO");
		
		System.out.println("SALIDA: "+salida);
	}

}
