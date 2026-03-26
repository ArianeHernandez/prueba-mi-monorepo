package test;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.BeforeClass;
import org.junit.Test;


import co.htsoft.commons.util.SMessage;

import com.osmosyscol.datasuite.near.webdata.PostalVerificarArchivos;
import com.osmosyscol.datasuite.servlet.InitApp;

public class PostalVerificadorArchivosTest {
	
	PostalVerificarArchivos op = new PostalVerificarArchivos();
	
	@BeforeClass
	public static void setUpClass() {
		InitApp.startUp();
	}
	
	@Test
	public void cantidad_archivos_solicitud_near_igual_en_postal() {
		
		SMessage resp = op.ejecutar(123888);
		
		MatcherAssert.assertThat(resp.getValid(), CoreMatchers.is(true));
		
	}
	
	@Test
	public void cantidad_archivos_solicitud_near_menor_en_postal() {
		
		SMessage resp = op.ejecutar(123957);
		
		MatcherAssert.assertThat(resp.getValid(), CoreMatchers.is(false));
		
	}
	
	@Test
	public void cantidad_archivos_solicitud_otros_procesos_igual_en_postal() {
		
		SMessage resp = op.ejecutar(123958);
		
		MatcherAssert.assertThat(resp.getValid(), CoreMatchers.is(true));
		
	}
	
	@Test
	public void cantidad_archivos_solicitud_otros_procesos_mayor_en_postal() {
		
		SMessage resp = op.ejecutar(123936);
		
		MatcherAssert.assertThat(resp.getValid(), CoreMatchers.is(true));
		
	}
	
	@Test
	public void cantidad_archivos_solicitud_otros_procesos_menor_en_postal() {
		
		SMessage resp = op.ejecutar(123918);
		
		MatcherAssert.assertThat(resp.getValid(), CoreMatchers.is(false));
		
	}
	
	@Test
	public void cantidad_archivos_solicitud_rta_req_igual_en_postal() {
		
		SMessage resp = op.ejecutar(123933); 
		
		MatcherAssert.assertThat(resp.getValid(), CoreMatchers.is(true));
		
	}
	
	@Test
	public void cantidad_archivos_solicitud_rta_req_mayor_en_postal() {
		
		SMessage resp = op.ejecutar(123927); 
		
		MatcherAssert.assertThat(resp.getValid(), CoreMatchers.is(true));
		
	}
	
	@Test
	public void cantidad_archivos_solicitud_rta_req_menor_en_postal() {
		
		SMessage resp = op.ejecutar(123840);
		
		MatcherAssert.assertThat(resp.getValid(), CoreMatchers.is(false));
		
	}
	
	@Test
	public void solicitud_no_corresponde_a_ningun_formulario() {
		
		SMessage resp = op.ejecutar(123803);
		
		MatcherAssert.assertThat(resp.getValid(), CoreMatchers.is(false));
		
	}

}
