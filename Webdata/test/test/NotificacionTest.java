package test;

import org.apache.cocoon.environment.SourceResolver;

import com.osmosyscol.datasuite.logica.dto.InstanciaAccion;
import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.modelatos.logica.servicios.AccionServicio;
import com.osmosyscol.datasuite.pagos.acciones.validacionsarlaft.ValidacionSarlaft;
import com.osmosyscol.datasuite.test.TestUtils;

public class NotificacionTest {
	
	
	public static void main(String[] args) {

		TestUtils.startUp();
		
		InstanciaAccion instanciaAccion = new InstanciaAccion(); 
		SourceResolver resolver = null ;
		
		instanciaAccion.setId_accion(40528);
		instanciaAccion.setId_carga(106776);
		instanciaAccion.setId_proceso_admin(40442);
		instanciaAccion.setId_instancia(40547);
		instanciaAccion.setId_administrativo(0);
		
		AccionServicio.getInstance().enviarNotificacionesPorAccion(instanciaAccion, resolver);
		
		
//		AccionCarga ac = new ValidacionSarlaft();
//		
//		System.out.println(ac.ejecutar(14933010));

		System.exit(0);
	}

}
