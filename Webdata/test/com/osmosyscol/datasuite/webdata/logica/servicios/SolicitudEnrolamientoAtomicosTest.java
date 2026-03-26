package com.osmosyscol.datasuite.webdata.logica.servicios;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.osmosyscol.datasuite.mein.dtos.ObjGenerico;
import com.osmosyscol.datasuite.mein.dtos.TipoDeDocumento;
import com.osmosyscol.datasuite.mein.servicios.TipoDeDocumentoServicio;
import com.osmosyscol.datasuite.near.servicios.IPostalInteraccion;
import com.osmosyscol.datasuite.servlet.InitApp;

public class SolicitudEnrolamientoAtomicosTest {
	
	public static void main(String[] args) {
		InitApp.startUp();
		
		getAllSolicitantTest();
		
		System.exit(0);
		
	}
	
	public static void getAllSolicitantTest(){
		SolicitudEnrolamientoServicio servicioEnrolamiento = SolicitudEnrolamientoServicio.getInstance();
		
		List<ObjGenerico> listaTipos = servicioEnrolamiento.obtenerTiposSolicitante();
		Map<Integer, String> mapaTipos = new HashMap<Integer, String>();
		
		for(ObjGenerico tipo: listaTipos) {
			mapaTipos.put(tipo.getId(), tipo.getNombre());
		}
		
		for(ObjGenerico tipo : listaTipos ){
			System.out.println(".........");
			System.out.println("ID:                     "+ tipo.getId());
			System.out.println("NOMBRE:                 "+ tipo.getNombre());
			System.out.println("CODIGO:                 "+ tipo.getEstado());
		}
	}
	
}
