package com.osmosyscol.datasuite.mein.servicios.json;

import org.junit.Assert;

import com.osmosyscol.datasuite.mein.servicios.SolicitudNearSociedadServicio;
import com.osmosyscol.datasuite.mein.servicios.rest.client.SigsAppRestClient;
import com.osmosyscol.datasuite.servlet.InitApp;
import com.osmosyscol.datasuite.webdata.logica.dto.SolicitudEnrolamiento;
import com.osmosyscol.datasuite.webdata.logica.servicios.SolicitudEnrolamientoServicio;

public class solicitudNearSociedadTest {
	public static void main(String[] args) {
		InitApp.startUp();
		String numero_identificacion = "1212";
		
		Integer id_tipo_identificacion = 5;
		String usuario = "Aplicacion";
		
		SolicitudEnrolamiento solicitud = SolicitudEnrolamientoServicio.getInstance().SolicitudEnrolamientoIdentificacion(numero_identificacion);
		Assert.assertEquals("andres.guevara@nuvu.cc", solicitud.getCorreo_electronico());
		System.exit(0);
	}
}
