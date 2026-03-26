package test;

import co.htsoft.commons.lang.P;

import com.osmosyscol.datasuite.pagos.services.IntegracionPagosService;

public class TestIntegracionPagos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IntegracionPagosService pagos = new IntegracionPagosService("http://192.168.1.52:9090/SipPte");
		testAplicarDebito(pagos);
		
	}
	
	public static void testMovimientosTesoreria(IntegracionPagosService pagos){
		P.print(pagos.enviarMovimientoTesoreria("123", "345", "343", "10", "CC", "0809340293", new Double(908429348), "Neto", "S"));
	}
	
	public static void testAplicarDevolucion(IntegracionPagosService pagos){
		P.print(pagos.aplicarDevolucion("1232", "12345", "110011343", "10000001", new Double(640000), "Bruto", "N", "dqwdqw", "PAGO", "CC", "1015438112", "35333234"));
	} 
	
	public static void testAplicarDebito(IntegracionPagosService pagos){
		P.print(pagos.enviarAplicarDebito("1234", "4567", "PAGO", new Double(908429348), "PAGO", "bruto", "S", "CC", "654789321", "4567", "1747", "CORRIENTE", 654, "A12"));
	}
}
