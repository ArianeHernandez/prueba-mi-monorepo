package com.osmosyscol.datasuite.logica.servicios.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cocoon.environment.Session;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.GsonBuilder;
import com.osmosyscol.datasuite.logica.servicios.ReporteDimServicio;
import com.osmosyscol.datasuite.reportedim.dto.ResultadoConsulta;
import com.osmosyscol.datasuite.test.TestUtils;

public class TestReporteDimServicio {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestUtils.startUp();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testobtenerDatosReporte() {
		Map<String, Object> parametrosReporte = new HashMap<String, Object>();
		String id_reporte = "1589818126756357";
		
		parametrosReporte.put("ID_CARGA", 109787);
		
		
		Map out = new GsonBuilder().setPrettyPrinting().serializeNulls().create().fromJson("{'a':1}", Map.class);
		System.out.println(out);
		
		ResultadoConsulta salida = ReporteDimServicio.getInstance().obtenerDatosReporte(parametrosReporte);
		
		assertNotNull(salida);
		List<Map<String, Object>> datos = salida.getDatos();
		assertNotNull(datos);
		for (Map<String, Object> map : datos) {
			System.out.println(map);
		}
	}

}
