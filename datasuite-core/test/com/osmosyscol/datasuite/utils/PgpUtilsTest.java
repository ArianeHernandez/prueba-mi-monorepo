package com.osmosyscol.datasuite.utils;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class PgpUtilsTest {

	@Test
	public void testDesencriptarArchivo() throws Exception {
		
		File file = new File("../CentralRecaudos/files/Davivienda1-cif.txt");
		InputStream in = new FileInputStream(file);
		InputStream keyIn =  new FileInputStream(new File("../CentralRecaudos/files/Privada"));
		char[] passwd = "meconio3".toCharArray();
		File out = PgpUtils.desencriptarArchivo(in, keyIn, passwd, file);
		assertNotNull(out);
		String contenido = FileUtils.readFileToString(out);
		System.out.println(contenido);
	}

}
