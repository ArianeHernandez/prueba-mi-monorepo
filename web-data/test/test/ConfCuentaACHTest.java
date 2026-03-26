package test;
import java.util.List;

import com.osmosyscol.datasuite.servlet.InitApp;
import com.osmosyscol.datasuite.webdata.correval.confCuentaACH.dto.CuentaBanco;
import com.osmosyscol.datasuite.webdata.correval.confCuentaACH.dto.Producto;
import com.osmosyscol.datasuite.webdata.correval.servicios.ConfCuentaACHServicio;
import com.osmosyscol.datasuite.webdata.correval.servicios.ProductoServicio;

import co.htsoft.commons.lang.P;

public class ConfCuentaACHTest {

	public static void main(String[] args) {
		
		InitApp.startUp();
		
//		List<CuentaBanco> lista = ConfCuentaACHServicio.getInstance().obtenerListadoCuentaACH();
//		CuentaBanco lista = ConfCuentaACH.getInstance().obtenerCuentaACH(14918538);
//		
//		P.p(lista);
		List<Producto> lista = ProductoServicio.getInstance().obtenerProductos();
		for (Producto prod : lista) {
			List<CuentaBanco> cuentaS = ConfCuentaACHServicio.getInstance().obtenerCuentasPorProducto(prod.getId());
			P.println(cuentaS);
		}
		
//		ConfCuentaACHServicio.getInstance().actualizarCuentaPorDefecto(14905088, 14918534);
		
//		ConfCuentaACHServicio.getInstance().actualizarCuentaPorDefecto(14905088, 14918537);
		
		System.exit(0);
		
	}
	
}
