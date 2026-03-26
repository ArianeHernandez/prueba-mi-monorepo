package test;

import java.util.List;

import co.htsoft.commons.lang.P;

import com.google.gson.Gson;
import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.datasuite.mein.servicios.SolicitudNearSociedadServicio;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValorLista;
import com.osmosyscol.datasuite.pagos.acciones.aplicardevolucion.AplicarDevolucion;
import com.osmosyscol.datasuite.servlet.InitApp;


public class ETest {

	public static void main(String[] args) {

		InitApp.startUp();

		// ---------------------------------

		// P.println(EdicionGrupoServicio.listarArchivosGenerados("SUPER"));

		// AplicarDevolucion.ejecutar();
		
//		P.println(Crypto.D("SBRRAqC.C8CACABADeAIBDBRDICKDoChCmD:"));
		
		// ------------------

		Integer id_carga = 113450;
		
		List<ValorLista> lista = SolicitudNearSociedadServicio.getInstance().obtenerTipoSolicitudPorCarga(id_carga);
		
		System.out.println(lista.size() + " - " +lista.get(0).getId() + " - " + lista.get(0).getNombre());
		
		System.exit(0);

	}

	public static void pl(Object o) {
		System.out.println(new Gson().toJson(o));
	}

}
