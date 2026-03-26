package com.osmosyscol.datasuite.mein.servicio;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.osmosyscol.datasuite.test.TestUtils;
import com.osmosyscol.datasuite.webdata.logica.acciones.EjecutarSolicitudEnrolamiento;

public class TestEjecutarSolicitudEnrolamiento {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestUtils.startUp();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testEjecutarSolicitudEnrolamiento() throws Exception {
		EjecutarSolicitudEnrolamiento ejec = new EjecutarSolicitudEnrolamiento();
		//ejec.crearProcesoPorDefecto(49458, "12300000000", 1);
		ejec.ejecutar(113116);
	}

}
