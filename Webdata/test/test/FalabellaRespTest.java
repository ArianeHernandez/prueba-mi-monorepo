package test;

import java.io.File;

import com.osmosyscol.datasuite.webdata.correval.bancos.entrada.IInterpreteRespuestaBanco;
import com.osmosyscol.datasuite.webdata.correval.bancos.entrada.InterpreteRespuestaFalabella;

import co.htsoft.commons.lang.P;

public class FalabellaRespTest {

	public static void main(String[] args) {
		IInterpreteRespuestaBanco ai = new InterpreteRespuestaFalabella();

		File a = new File("test/test/PROCESO PAGO FALABELLA.xlsx");

		P.println(ai.validarArchivo(a.getAbsolutePath(), "48"));

		P.println(ai.obtenerRespuestasPorBanco(a.getAbsolutePath(), "48"));

	}

}
