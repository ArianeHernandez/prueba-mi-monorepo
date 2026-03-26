package test;

import java.util.List;

import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.dto.CargaAccionAutomatica;
import com.osmosyscol.datasuite.modelatos.logica.dto.ListaDinamica;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValorLista;
import com.osmosyscol.datasuite.modelatos.logica.servicios.AccionServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ListaDinamicaServicio;
import com.osmosyscol.datasuite.test.TestUtils;
import com.osmosyscol.datasuite.utils.dummys.DummySession;
import com.osmosyscol.datasuite.webdata.logica.servicios.EstadoCargaServicio;

public class AccionAutomaticaTest {

	
	public static void main(String[] args) {
		TestUtils.startUp();
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
		
	}
}
