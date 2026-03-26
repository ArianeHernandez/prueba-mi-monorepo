package com.osmosyscol.datasuite.near.servicios.radica;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.lenient;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.jxpath.JXPathContext;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import co.htsoft.commons.util.SMessage;

import org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion;
import com.osmosyscol.datasuite.near.utils.Either;
import com.osmosyscol.datasuite.near.webdata.Postal_RespuestaRequerimiento_Principal;
import com.osmosyscol.datasuite.test.TestUtils;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ LadoRemoto.class , LadoLocal.class})
@PowerMockIgnore("javax.management.*")
public class TestPostal_RespuestaRequerimiento_Principal {
	
	
	public static void intercept_AdjuntosServicio() throws Exception {
		
		
		
	}
	
	public static void intercept_AccionCarga() throws Exception {
		Postal_RespuestaRequerimiento_Principal obj2 = PowerMockito.mock(Postal_RespuestaRequerimiento_Principal.class, Answers.RETURNS_DEEP_STUBS);
		//Radicacion obj2 = PowerMockito.spy(Radicacion.class); //, Answers.RETURNS_DEEP_STUBS);
        
        PowerMockito.whenNew(Postal_RespuestaRequerimiento_Principal.class)
        	.withNoArguments()
        	.thenReturn(obj2);	        
		
		doReturn(Either.right(1)).when( obj2 ).ladoLocal_LeeAdjuntosCount(any(Integer.class));
		
		/*
		Postal_RespuestaRequerimiento_Principal accionCargaMock = mock(Postal_RespuestaRequerimiento_Principal.class);
        mockStatic(Postal_RespuestaRequerimiento_Principal.class);
        when();

        //when
        doReturn("9999999").when(ladoRemotoMock).valueOf(any(JXPathContext.class), eq("deudor/datos_basicos/identificacion"), eq(String.class) );
        doReturn("mock-intercept@hightech.com.co").when(ladoRemotoMock).valueOf(any(JXPathContext.class), eq("deudor/datos_basicos/correo"), eq(String.class) );

        // then
        assertEquals("9999999",LadoRemoto.getInstance().valueOf(JXPathContext.newContext(null), "deudor/datos_basicos/identificacion", String.class));
        */
        
	}
	
	public static void interceptMethod_Servicio_LadoLocal_leeAdjuntos_Count() throws Exception {
        // given
        LadoLocal _serviceMock = spy(LadoLocal.getInstance());
        mockStatic(LadoLocal.class);
        when(LadoLocal.getInstance()).thenReturn(_serviceMock);

        //when
        doReturn(1L).when(_serviceMock).leeAdjuntos_Count(any(Integer.class));

        // then
        assertTrue(1L==LadoLocal.getInstance().leeAdjuntos_Count(-100) );
		
	}
	
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
	
	
	public static void intercept_ObjRadicacion() throws Exception {
        Radicacion obj2 = PowerMockito.mock(Radicacion.class, Answers.RETURNS_DEEP_STUBS);
		//Radicacion obj2 = PowerMockito.spy(Radicacion.class); //, Answers.RETURNS_DEEP_STUBS);
        
        PowerMockito.whenNew(Radicacion.class)
        	.withNoArguments()
        	.thenReturn(obj2);	        

        // when
        //doReturn(1).when(obj2).getFolios();
		//doReturn(1).when( obj2 ).getFolios();
		
		
		doReturn(1).when( obj2 ).getFolios();
		
		//when(any(File.class).canWrite()).thenReturn(Boolean.FALSE)
        
        // then
        assertTrue( 1 == new Radicacion().getFolios());
	}
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestUtils.startUp();
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test_RespuestaReq_01_RespuestaRadicadoNoExiste() {
		// fail("Not yet implemented");
		SMessage out;
		
		// requerimiento con solicitud no existente
		Postal_RespuestaRequerimiento_Principal accion = new Postal_RespuestaRequerimiento_Principal();
		int id_carga = 0;//
		out = accion.ejecutar(id_carga);
		
		assertNotNull(out);
		assertTrue(out.getValid()); // se solicita que devuelva true coando error
	}
	
	@Test
	public void test_RespuestaReq_02_SolicitudLinkedNoExiste() {
		// fail("Not yet implemented");
		SMessage out;
		
		// requerimiento con solicitud no existente
		Postal_RespuestaRequerimiento_Principal accion = new Postal_RespuestaRequerimiento_Principal();
		int id_carga = 109990;//
		out = accion.ejecutar(id_carga);
		
		assertNotNull(out);
		assertTrue(out.getValid()); // se solicita que devuelva true coando error
	}

	/**
	 * buscar respuesta radicado con solicitud existente: 
	  
	  select $respuesta requerimiento$.idcarga
	    from $respuesta requerimiento$
	   where exists (select 1 
	                 from $solicitud near sociedad$
                     where $solicitud near sociedad$.idcarga = $respuesta requerimiento.numero solicitud$)
                     
	   select $respuesta requerimiento$.idcarga
	     from $respuesta requerimiento$, $solicitud near sociedad$
  		where $respuesta requerimiento.numero solicitud$ = $solicitud near sociedad$.idcarga                     
	 */
	private SMessage test_RespuestaReq_03_WhenSolicitud_SinFolio_Logic() {
		SMessage out;
		int id_carga = 109835;//
		Postal_RespuestaRequerimiento_Principal accion;
		
		// requerimiento con solicitud existente
		accion = new Postal_RespuestaRequerimiento_Principal();
		out = accion.ejecutar(id_carga);
		return out;
	}
	
	
	@Test
	public void test_RespuestaReq_03a_WhenSolicitud_SinFoliosPostal() throws Exception {
		SMessage out = test_RespuestaReq_03_WhenSolicitud_SinFolio_Logic();
		assertNotNull(out);
		assertTrue(out.getValid()); // se solicita que devuelva true coando error
		
		//assertNotNull(out);
		//assertTrue(out.getValid());
		
	}
	
	@Test
	public void test_RespuestaReq_03b_WhenSolicitud_ConFoliosPostal() throws Exception {
		// correr interceptor para cambiar servicio que entrega numfolios y ejecutar nuevamente
		interceptMethod_Servicio_LadoLocal_leeAdjuntos_Count();
		
		// correr la logica del test nuevamente
		SMessage out = test_RespuestaReq_03_WhenSolicitud_SinFolio_Logic();
		assertNotNull(out);
		assertTrue(out.getValid());
		
	}

}
