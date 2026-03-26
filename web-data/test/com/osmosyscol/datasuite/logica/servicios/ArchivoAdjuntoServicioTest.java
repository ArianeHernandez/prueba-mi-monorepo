package com.osmosyscol.datasuite.logica.servicios;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.osmosyscol.datasuite.logica.dto.ArchivoAdjunto;
import com.osmosyscol.datasuite.test.TestUtils;

public class ArchivoAdjuntoServicioTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestUtils.startUp();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Test
	public void test() {
		Boolean rta = ArchivoAdjuntoServicio.getInstance().actualizarRadicado(46080, "65456456", "R");
		assertTrue(rta);
		
		List<ArchivoAdjunto> salida = ArchivoAdjuntoServicio.getInstance().obtenerAdjuntosSegunTipologia(1, "A", null, 4);
		assertNotNull(salida);
		
		Boolean updated = ArchivoAdjuntoServicio.getInstance().actualizarRadicadoAdjuntosSegunTipologia(110001,"test", "R", true, 3 );
		System.out.println(updated);
		assertNotNull(updated);
		
		
	}

}
