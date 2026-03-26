package com.osmosyscol.datasuite.near.servicios.radica;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import co.htsoft.commons.util.SMessage;

import com.osmosyscol.datasuite.near.webdata.Abstract_Postal_Envio_Principal;
import com.osmosyscol.datasuite.near.webdata.Postal_OficioInadmision_Principal;
import com.osmosyscol.datasuite.test.TestUtils;

public class TestPostal_OficioInadmision_Principal {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestUtils.startUp();
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test_RespuestaReq_01_RespuestaRadicadoExiste() {
		// fail("Not yet implemented");
		SMessage out;
		
		// requerimiento con solicitud no existente
		Abstract_Postal_Envio_Principal accion = new Postal_OficioInadmision_Principal();
		int id_carga = 110123;//
		out = accion.ejecutar(id_carga);
		
		assertNotNull(out);
		assertFalse(out.getValid());
	}
	

}
