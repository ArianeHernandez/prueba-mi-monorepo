package com.osmosyscol.datasuite.near.servicios.lectorcerl;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.osmosyscol.datasuite.near.interop.lectorcerl.rest_cli.json.DataIn;
import com.osmosyscol.datasuite.near.interop.lectorcerl.rest_cli.json.DataOut;
import com.osmosyscol.datasuite.near.utils.Either;
import com.osmosyscol.datasuite.test.TestUtils;

public class TestLectorCerlServicio {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestUtils.startUp();
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testInvocar() {
		LectorCerlServicio _service = LectorCerlServicio.getInstance();
		
		DataIn in = new DataIn(
			"https://static-mein-prueba-rues.s3.amazonaws.com/rues/services.pdf",
			"https://static-mein-prueba-rues.s3.amazonaws.com/html/services.html");
		Either<Exception, DataOut> out = _service.invocar(in);
		
		assertTrue(out.isRight());
		assertEquals("2019", out.right().getUltimoAORenovado());
		
	}

}
