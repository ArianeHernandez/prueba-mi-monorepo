package com.osmosyscol.datasuite.mein.servicio;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.mein.domain.CerlResponse;
import com.osmosyscol.datasuite.mein.servicios.RuesServicio;
import com.osmosyscol.datasuite.test.TestUtils;

public class TestRuesServicio {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestUtils.startUp();
	}

	@Test
	public void testObtenerDatosRues() {
		CerlResponse out = RuesServicio.getInstance().obtenerDatosRues("901084310", "1032385845");
		assertNotNull(out);
		assertTrue(out.isRespuesta());
		assertNotNull(out.getSociedad());
		assertNotNull(out.getSociedad().getRazon_social());
		SimpleLogger.setDebug("Exitoso");
		SimpleLogger.setDebug(out.getSociedad().toString());
	}

}
