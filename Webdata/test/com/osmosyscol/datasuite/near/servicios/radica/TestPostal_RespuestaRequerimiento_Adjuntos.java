package com.osmosyscol.datasuite.near.servicios.radica;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import co.htsoft.commons.util.SMessage;

import com.osmosyscol.datasuite.near.webdata.Abstract_Postal_Envio_Adjuntos;
import com.osmosyscol.datasuite.near.webdata.Postal_RespuestaRequerimiento_Adjuntos;
import com.osmosyscol.datasuite.test.TestUtils;

public class TestPostal_RespuestaRequerimiento_Adjuntos {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestUtils.startUp();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	
	@Test
	public void testpublicarAdjuntos_02_RespuestaReq() {
		SMessage out;
		
		int accionCarga_TipoRespuestaReq = 110012;
		Abstract_Postal_Envio_Adjuntos accion_Detail =new Postal_RespuestaRequerimiento_Adjuntos();
		out = accion_Detail.ejecutar(accionCarga_TipoRespuestaReq);
		
		assertNotNull(out);
		//assertTrue(out.getValid()); // validar un id de carga existente y descomentar
	}
	
	@Test
	public void testpublicarAdjuntos_01_IdCargaNoEncontrado() {
		SMessage out;
		
		int accionCarga_TipoRespuestaReq = -1;
		Abstract_Postal_Envio_Adjuntos accion_Detail =new Postal_RespuestaRequerimiento_Adjuntos();
		out = accion_Detail.ejecutar(accionCarga_TipoRespuestaReq);
		
		assertNotNull(out);
		assertFalse(out.getValid());
	}
	
}
