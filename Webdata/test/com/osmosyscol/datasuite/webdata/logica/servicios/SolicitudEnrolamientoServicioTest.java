package com.osmosyscol.datasuite.webdata.logica.servicios;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.Mensaje;
import com.osmosyscol.datasuite.test.TestUtils;
import com.osmosyscol.datasuite.webdata.logica.dto.DatosRepresentante;
import com.osmosyscol.datasuite.webdata.logica.dto.Pais;
import com.osmosyscol.datasuite.webdata.logica.dto.SolicitudEnrolamiento;

public class SolicitudEnrolamientoServicioTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestUtils.startUp();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testCrearSolicitud() {
		TestUtils.startUp();
		Pais pais = SolicitudEnrolamientoServicio.getInstance().obtenerPaisPorCodigo("169");
		SolicitudEnrolamiento solicitud = new SolicitudEnrolamiento();
		solicitud.setRazon_social("inaction");
		solicitud.setNumero_identificacion("12321321");
		solicitud.setPais(pais.getId());
		solicitud.setTipo_formulario("1");
		DatosRepresentante datosRep = new DatosRepresentante();
		datosRep.setApellidos("apellidos");
		datosRep.setNombres("nombre");
		datosRep.setFecha_expedicion("01-01-1990");
		datosRep.setFecha_nacimiento("01-01-1990");
		datosRep.setLugar_nacimiento("Bogota");
		datosRep.setNumero_identificacion("1261501564");
		datosRep.setCorreo_electronico("info@empresa.com");
		datosRep.setCelular("300000000");
		datosRep.setSexo("M");
		datosRep.setTipo_identificacion(1);
		
		solicitud.setDatos_representante(datosRep);
		
		Mensaje salida = SolicitudEnrolamientoServicio.getInstance().crearSolicitud(solicitud, false, null);
		SimpleLogger.setDebug("Salida: " + salida.getEstado() + " "  + salida.getMensaje());
		assertTrue(salida.getExitoso());
		System.exit(0);
	}

}
