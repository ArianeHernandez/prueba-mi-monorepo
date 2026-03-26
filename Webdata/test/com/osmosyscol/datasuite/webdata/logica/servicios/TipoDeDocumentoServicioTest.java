package com.osmosyscol.datasuite.webdata.logica.servicios;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.osmosyscol.datasuite.mein.dtos.TipoDeDocumento;
import com.osmosyscol.datasuite.mein.servicios.TipoDeDocumentoServicio;
import com.osmosyscol.datasuite.near.servicios.IPostalInteraccion;
import com.osmosyscol.datasuite.servlet.InitApp;

public class TipoDeDocumentoServicioTest {
	
	public static void main(String[] args) {
		InitApp.startUp();
		
//		getAllDocumentsTypeTest();
		getMapForCodHtsTest();
		
		System.exit(0);
		
	}
	
	public static void getAllDocumentsTypeTest(){
		TipoDeDocumentoServicio tipos = TipoDeDocumentoServicio.getInstance();
		
		List<TipoDeDocumento> listaTipos = tipos.getAllDocumentsType();
		
		for(TipoDeDocumento tipo : listaTipos ){
			System.out.println(".........");
			System.out.println("ID:                     "+ tipo.getId());
			System.out.println("NOMBRE:                 "+ tipo.getNombre());
			System.out.println("CODIGO:                 "+ tipo.getCodigo());
			System.out.println("ID POSTAL:              "+ tipo.getId_postal());
			System.out.println("NOMBRE POSTAL:          "+ tipo.getNombre_postal());
			System.out.println("CODIGO ALFANUMERICO:    "+ tipo.getCodigo_alfanumerico());
			System.out.println("CODIGO NUMERICO POSTAL: "+ tipo.getCodigo_numerico_postal());
			System.out.println("COD_HTS:                "+ tipo.getCod_hts());
		}
		
		
//		System.exit(0);
	}
	
	public static void getMapForCodHtsTest() {
		TipoDeDocumentoServicio tipos = TipoDeDocumentoServicio.getInstance();
		Map<String, TipoDeDocumento> mapaTipos = tipos.getMapForCodHts();
		
		try {
			TipoDeDocumento tipo = mapaTipos.get("N");
			
			System.out.println(".........");
			System.out.println("ID:                     "+ tipo.getId());
			System.out.println("NOMBRE:                 "+ tipo.getNombre());
			System.out.println("CODIGO:                 "+ tipo.getCodigo());
			System.out.println("ID POSTAL:              "+ tipo.getId_postal());
			System.out.println("NOMBRE POSTAL:          "+ tipo.getNombre_postal());
			System.out.println("CODIGO ALFANUMERICO:    "+ tipo.getCodigo_alfanumerico());
			System.out.println("CODIGO NUMERICO POSTAL: "+ tipo.getCodigo_numerico_postal());
			System.out.println("COD_HTS:                "+ tipo.getCod_hts());
			
		} catch (Exception e) {
			System.out.println("No existe dato");
		}
		
	}
	
	
}
