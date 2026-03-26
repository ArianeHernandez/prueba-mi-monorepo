package com.osmosyscol.datasuite.mein.servicio;

import com.osmosyscol.datasuite.mein.builder.RegimenInsolvenciaFetchBuilder;
import com.osmosyscol.datasuite.mein.dtos.RegimenInsolvencia;
import com.osmosyscol.datasuite.mein.servicios.RegimenInsolvenciaServicio;
import com.osmosyscol.datasuite.near.webdata.Postal_RadicarInterna;
import com.osmosyscol.datasuite.servlet.InitApp;

public class TestRegimenInsolvencia {

	public static void main(String[] args) {
		
		try {
			InitApp.startUp();
			
			RegimenInsolvencia regimen = RegimenInsolvenciaServicio.getInstance().obtenerInfoRegimenInsolvenciaPorIdCarga(124874);
			
			System.out.println(regimen.getIntendencia_regional_id());
			System.out.println(regimen.getIntendencia_regional().getNombre());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Finaliza");
		
		System.exit(0);
		
	}
	
}
