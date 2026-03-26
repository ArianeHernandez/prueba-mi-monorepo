package com.osmosyscol.datasuite.near.servicios.radica;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import co.htsoft.commons.util.SMessage;

import com.osmosyscol.datasuite.near.webdata.Abstract_Postal_Envio_Principal;
import com.osmosyscol.datasuite.near.webdata.Postal_AutoAdmision_Principal;
import com.osmosyscol.datasuite.test.TestUtils;

public class TestPostal_AutoAdmision_Principal {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestUtils.startUp();
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test_RespuestaReq_01_RespuestaRadicadoNoExiste() {
		// fail("Not yet implemented");
		SMessage out;
		
		// requerimiento con solicitud no existente
		Abstract_Postal_Envio_Principal accion = new Postal_AutoAdmision_Principal();
		int id_carga = 110873;//
		out = accion.ejecutar(id_carga);
		
		assertNotNull(out);
		assertFalse(out.getValid());
	}
	

}
