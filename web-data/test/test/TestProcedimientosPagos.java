package test;

import co.htsoft.commons.lang.P;

import com.osmosyscol.datasuite.pagos.acciones.centralpagos.AnularOrdenPago;
import com.osmosyscol.datasuite.pagos.acciones.centralpagos.ConsultarEntradaAlmacen;
import com.osmosyscol.datasuite.pagos.acciones.centralpagos.ObtenerOrdenPagoPorRadicacion;
import com.osmosyscol.datasuite.pagos.acciones.centralpagos.RadicarSolicitudPago;
import com.osmosyscol.datasuite.pagos.services.IntegracionPagosService;
import com.osmosyscol.datasuite.servlet.InitApp;

public class TestProcedimientosPagos {
	public static final Integer id_carga = 0;

	// 0
	// 106932
	// 106933

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InitApp.startUp();
		IntegracionPagosService pagos = new IntegracionPagosService("http://hts-001:9090/SipPte");
		testRadicar(pagos);
		System.exit(0);
	}

	public static void testRadicar(IntegracionPagosService pagos) {
		// P.print(pagos.enviarMovimientoTesoreria("123", "345", "343", "10", "CC", "0809340293", new Double(908429348), "Neto", "S"));
		P.print(new RadicarSolicitudPago().ejecutar(id_carga));

	}

	public static void testAnular(IntegracionPagosService pagos) {
		// P.print(pagos.aplicarDevolucion("1232", "12345", "110011343", "10000001", new Double(640000), "Bruto", "N", "dqwdqw", "PAGO", "CC", "1015438112", "35333234"));
		P.print(new AnularOrdenPago().ejecutar(id_carga));
	}

	public static void testConsultAlmacen(IntegracionPagosService pagos) {
		// P.print(pagos.enviarAplicarDebito("1234", "4567", "PAGO", new Double(908429348), "PAGO", "bruto", "S", "CC", "654789321", "4567", "1747", "CORRIENTE", 654, "A12"));
		P.print(new ConsultarEntradaAlmacen().ejecutar(id_carga));
	}

	public static void testObtenerOrdenPagoPorRadicacion(IntegracionPagosService pagos) {
		P.print(new ObtenerOrdenPagoPorRadicacion().ejecutar(id_carga));
	}

}
