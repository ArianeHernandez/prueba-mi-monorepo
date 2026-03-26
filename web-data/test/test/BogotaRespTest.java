package test;

import java.io.File;

import com.osmosyscol.datasuite.webdata.correval.bancos.entrada.IInterpreteRespuestaBanco;
import com.osmosyscol.datasuite.webdata.correval.bancos.entrada.InterpreteRespuestaBancoBogota;

import co.htsoft.commons.lang.P;

public class BogotaRespTest {

	public static void main(String[] args) {
		IInterpreteRespuestaBanco ai = new InterpreteRespuestaBancoBogota();

		File a = new File("test/test/RESPUESTA BOGOTA.txt");

		P.println(ai.validarArchivo(a.getAbsolutePath(), "1"));

		P.println(ai.obtenerRespuestasPorBanco(a.getAbsolutePath(), "1"));

	}

}
