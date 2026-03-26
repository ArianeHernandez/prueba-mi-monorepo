package com.osmosyscol.datasuite.modelatos.logica.servicios;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.dto.Preparador;
import com.osmosyscol.datasuite.logica.dto.Revisor;
import com.osmosyscol.datasuite.logica.dto.TipoProceso;
import com.osmosyscol.datasuite.logica.dto.TipoProcesoRol;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;
import com.osmosyscol.datasuite.logica.servicios.UsuarioServicio;
import com.osmosyscol.datasuite.test.TestUtils;

public class TestProcesoServicio {

	private static final String REV = "REV";
	private static final int ID_TIPO_PROCESO = 1;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestUtils.startUp();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

//	@Test
//	public void testAsociarRevisorProcesos() {
//		
//		List<TipoProcesoRol> tipoprocesos = null;
//		TipoProceso tipoProceso = ProcesoServicio.getInstance().obtenerTipoProcesoPorIdTipoProceso(ID_TIPO_PROCESO);
//		TipoProcesoRol tipoProcesoRol = new TipoProcesoRol();
//		tipoProcesoRol.setId_tipo_proceso(tipoProceso.getId_tipo_proceso());
//		tipoProcesoRol.setRol(REV);
//		
//		Usuario usuario= UsuarioServicio.getInstance().obtenerUsuarioPorDocumento("900470130");
//		Integer id_usuario = usuario.getId_usuario();
//		
//		List<Preparador> preparadores = PreparadorServicio.getInstance().obtenerPreparadores(usuario.getId_usuario(), ID_TIPO_PROCESO);
//
//		Preparador preparador = preparadores.get(0);
////		RevisorServicio.getInstance().desasociarRevisor(id_revision, id_revisor);
//		PersonaServicio.getInstance().eliminarPersonaRol(preparador.getId_persona(), REV, id_usuario);
//		Persona persona = PersonaServicio.getInstance().obtenerPersona(preparador.getId_persona());
//		String identificacion = persona.getIdentificacion();
//		Integer tipoDocumento = persona.getTipo_documento();
//		Boolean salida = ProcesoServicio.getInstance().asociarRevisorProcesos(tipoprocesos, id_usuario, identificacion, tipoDocumento, REV);
//		assertTrue(salida);
//	}

}
