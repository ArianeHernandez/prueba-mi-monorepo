package test;

import java.io.FileReader;

import au.com.bytecode.opencsv.CSVReader;
import co.htsoft.commons.lang.P;

public class OpenCSVTest {

	public static void main(String[] args) throws Throwable {

		CSVReader reader = new CSVReader(new FileReader("test/test/RESPUESTA BOGOTA.txt"), ',');

		P.println(reader.readAll());
		
		reader.close();

	}
}
