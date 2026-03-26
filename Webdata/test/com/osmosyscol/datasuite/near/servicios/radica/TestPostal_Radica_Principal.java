package com.osmosyscol.datasuite.near.servicios.radica;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.jxpath.JXPathContext;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.mockito.PowerMockito.mockStatic;
import co.htsoft.commons.util.SMessage;

import com.osmosyscol.datasuite.mein.dtos.SolicitudNearSociedad;
import org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion;
import com.osmosyscol.datasuite.near.servicios.AccionPublicarAdjuntosCarga;
import com.osmosyscol.datasuite.near.servicios.IPostalInteraccion;
import com.osmosyscol.datasuite.near.servicios.radica.LadoRemoto;
import com.osmosyscol.datasuite.near.utils.Either;
import com.osmosyscol.datasuite.near.utils.ObjectUtils;
import com.osmosyscol.datasuite.near.webdata.Abstract_Postal_Envio_Adjuntos;
import com.osmosyscol.datasuite.near.webdata.Abstract_Postal_Envio_Principal;
import com.osmosyscol.datasuite.near.webdata.Postal_RadicaSolicitud2;
import com.osmosyscol.datasuite.test.TestUtils;
import com.osmosyscol.datasuite.webdata.logica.servicios.ParametrosPostalServicio;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ LadoRemoto.class })
@PowerMockIgnore("javax.management.*")
public class TestPostal_Radica_Principal {
	
	public static void interceptMethod_Servicio_LadoRemoto_valueOf() throws Exception {
        // given
        LadoRemoto ladoRemotoMock = spy(LadoRemoto.getInstance());
        mockStatic(LadoRemoto.class);
        when(LadoRemoto.getInstance()).thenReturn(ladoRemotoMock);

        //when
        doReturn("9999999").when(ladoRemotoMock).valueOf(any(JXPathContext.class), eq("deudor/datos_basicos/identificacion"), eq(String.class) );
        doReturn("mock-intercept@hightech.com.co").when(ladoRemotoMock).valueOf(any(JXPathContext.class), eq("deudor/datos_basicos/correo"), eq(String.class) );

        // then
        assertEquals("9999999",LadoRemoto.getInstance().valueOf(JXPathContext.newContext(null), "deudor/datos_basicos/identificacion", String.class));
        
	
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestUtils.startUp();
		
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	
	@Test
	public void test03_RadicarConArchivos() throws Exception {
		
		////  create mock objects
		interceptMethod_Servicio_LadoRemoto_valueOf();
		
		// execute tests
		int id_carga = 109897;//109563;//
		SMessage out;

		Abstract_Postal_Envio_Principal accion_Master = new Postal_RadicaSolicitud2();
		out = accion_Master.ejecutar(id_carga);
		assertNotNull(out);
		assertTrue(out.getValid());
		
		
		Abstract_Postal_Envio_Adjuntos accion_Detail =new AccionPublicarAdjuntosCarga();
		out = accion_Detail.ejecutar(id_carga);
		assertNotNull(out);
		assertTrue(out.getValid());
		
	}
	
	
	@Test
	public void test02_Radicar() throws Exception {
		////  create mock objects
		interceptMethod_Servicio_LadoRemoto_valueOf();
		
		SMessage out;

		Abstract_Postal_Envio_Principal accion = new Postal_RadicaSolicitud2();
		
		int id_carga = 109897;//109563;//
		out = accion.ejecutar(id_carga);
		
		assertNotNull(out);
		assertTrue(out.getValid());
		
		/*
		
		List<ListaDinamica> listas = AccionServicio.getInstance().obtenerListasDinamicas();
		ListaDinamica ld;
		ld = AccionServicio.getInstance().obtenerAccionAutomatica(40551);
		System.out.println(AccionServicio.getInstance().asignarListaDinamica(40551, 20));;
		ld = AccionServicio.getInstance().obtenerAccionAutomatica(40551);
		
		List<CargaAccionAutomatica> cargasAutomaticas = AccionServicio.getInstance().obtenerCargasAccionesAutomaticas();
		System.out.println(cargasAutomaticas);
		
		for (CargaAccionAutomatica ca : cargasAutomaticas) {
			DummySession session = new DummySession();
			session.setAttribute("id_carga", ca.getId_carga());
			ListaDinamicaServicio servicio = ListaDinamicaServicio.getInstance();
			List<ValorLista> valores = servicio.obtenerValoresListaDinamica(ca.getId_lista_dinamica(), session);
			if (valores.isEmpty()) {
				AccionServicio.getInstance().ejecutarAccionDeInstanciaActual(ca.getId_accion(), ca.getId_carga(), ca.getId_instancia(), null);
			}
		}
		*/		
		
	}
	
	@Test
	public void test01_MapRequest() {
		LadoRemoto _service = LadoRemoto.getInstance();
		
		SolicitudNearSociedad infoSource = new SolicitudNearSociedad();
		
		System.out.printf("solicitud:%s", ObjectUtils.toJson(infoSource) );
		
		Map<String,Object> payloadSource = new HashMap<String,Object>(); // Stream.of
		
		payloadSource.put(IPostalInteraccion.PARAM_PUNTOINTERACCION, IPostalInteraccion.PUNTOINTERACCION_RADICACION);
		payloadSource.put(IPostalInteraccion.PARAM_COUNT_ADJUNTOS, 0L);
		payloadSource.put(IPostalInteraccion.PARAM_SOLICITUD, infoSource);
		
		
		Either<Exception, Radicacion> out = _service.mapRequest(payloadSource);
		assertTrue(out.isRight());
		
		
	}
	

}
