package com.osmosyscol.datasuite.logica.servicios;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.TipoArchivo;
import com.osmosyscol.datasuite.test.TestUtils;

public class TipoArchivoServicioTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestUtils.startUp();
	}

	@Test
	public void test() {
		List<TipoArchivo> lista = TipoArchivoServicio.getInstance().obtenerTiposArchivo();
		assertNotNull(lista);
		SimpleLogger.setDebug(lista.toString());
	}

}
