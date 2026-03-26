package test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import co.htsoft.commons.lang.P;

import com.osmosyscol.datasuite.test.TestUtils;
import com.osmosyscol.datasuite.webdata.correval.bancos.entrada.IInterpreteRespuestaBanco;
import com.osmosyscol.datasuite.webdata.correval.bancos.entrada.InterpreteRespuestaBancoColpatria;
import com.osmosyscol.datasuite.webdata.correval.bancos.entrada.ManejadorDeInterpretesRespuestaBanco;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;

public class InterpreteColpatriaTest {

	public static void main(String[] args) throws Exception {
		
		TestUtils.startUp();

//		IInterpreteRespuestaBanco ai = ManejadorDeInterpretesRespuestaBanco.getInstance().obtenerInterpretePorIDBanco("4");
//
//		File a = new File("test/test/respuesta_colpatria.csv");
//
//		P.println(ai.validarArchivo(a.getAbsolutePath(), "4"));
//
//		P.println(ai.obtenerRespuestasPorBanco(a.getAbsolutePath(), "4"));
//		
		Integer id_administrativo = 0;
		Integer id_proceso_admin = 40944;
		String parametro_ordenamiento = "porcentajeTiempoTranscurrido";
		String tipo_ordenamiento = "desc";
		Map<String, Object> filtros = new HashMap<String, Object>();
		CargaServicio.getInstance().obtenerCargasPorProcesoAdmin(id_administrativo, id_proceso_admin, parametro_ordenamiento, tipo_ordenamiento, filtros, null);
		System.exit(0);
		
	}

}
