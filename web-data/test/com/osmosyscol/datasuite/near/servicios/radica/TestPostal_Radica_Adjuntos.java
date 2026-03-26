package com.osmosyscol.datasuite.near.servicios.radica;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import co.htsoft.commons.util.SMessage;

import com.osmosyscol.datasuite.near.servicios.AccionPublicarAdjuntosCarga;
import com.osmosyscol.datasuite.near.webdata.Abstract_Postal_Envio_Adjuntos;
import com.osmosyscol.datasuite.test.TestUtils;

public class TestPostal_Radica_Adjuntos {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestUtils.startUp();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	
	/*
	 	TODO: testReproducible. De momento: 
	 	
		select id_carga
		  from dst_archivo_adjunto
		 where rownum = 1  
		 order by id_carga desc	 
	 */
	@Test
	public void testpublicarAdjuntos_01_Solicitud() {
		SMessage out;
		
		int id_carga = 109897;
		
		Abstract_Postal_Envio_Adjuntos accion_Detail =new AccionPublicarAdjuntosCarga();
		out = accion_Detail.ejecutar(id_carga);
		
		assertNotNull(out);
		assertTrue(out.getValid());
		
	}
	
}
