package test;

import co.htsoft.commons.lang.P;

import com.osmosyscol.datasuite.logica.servicios.ArchivoAdjuntoServicio;
import com.osmosyscol.datasuite.servlet.InitApp;
import com.osmosyscol.datasuite.test.TestUtils;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class CopiaArchivosAdjuntosTest {
	public static void main(String[] args) {
		TestUtils.startUp();
		
		Integer id_carga_origen = 109501;
		Integer id_carga_destino = 109665;
		
		P.println(ArchivoAdjuntoServicio.getInstance().copiarArchivosAdjuntos(id_carga_origen, id_carga_destino ) );
		System.exit(0);
	}
}
