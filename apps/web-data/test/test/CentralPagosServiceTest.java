package test;

import java.util.List;

import co.htsoft.commons.lang.P;

import com.osmosyscol.datasuite.pagos.acciones.centralpagos.ObtenerOrdenPagoPorRadicacion;
import com.osmosyscol.datasuite.pagos.acciones.centralpagos.dto.SolicitudGeneralPago;
import com.osmosyscol.datasuite.servlet.InitApp;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

public class CentralPagosServiceTest {

	public static void main(String[] args) {
		InitApp.startUp();
		
		ObtenerOrdenPagoPorRadicacion oppr = new ObtenerOrdenPagoPorRadicacion();
		
		oppr.ejecutar(106933);
		
		System.exit(0);
		
	}
	
}
