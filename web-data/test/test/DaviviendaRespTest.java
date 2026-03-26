package test;

import java.io.File;

import com.osmosyscol.datasuite.webdata.correval.bancos.entrada.IInterpreteRespuestaBanco;
import com.osmosyscol.datasuite.webdata.correval.bancos.entrada.InterpreteRespuestaDavivienda;

import co.htsoft.commons.lang.P;

public class DaviviendaRespTest {

	public static void main(String[] args) {
		IInterpreteRespuestaBanco ai = new InterpreteRespuestaDavivienda();

		File a = new File("test/test/PagoPROV1908107.xls");

		P.println(ai.validarArchivo(a.getAbsolutePath(), "6"));

		P.println(ai.obtenerRespuestasPorBanco(a.getAbsolutePath(), "6"));

	}

}
