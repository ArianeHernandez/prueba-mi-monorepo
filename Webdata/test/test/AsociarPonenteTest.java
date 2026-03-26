package test;

import co.htsoft.commons.util.SMessage;

import com.osmosyscol.datasuite.near.webdata.AsociarPonenteRtaRequerimiento;
import com.osmosyscol.datasuite.servlet.InitApp;

public class AsociarPonenteTest {

	public static void main(String[] args) {
		InitApp.startUp();
		
		asociarPonenteRtaTest();  
		
		System.exit(0);
		
	}
	
	public static void asociarPonenteRtaTest() {
		Integer id_carga = 113641;
		AsociarPonenteRtaRequerimiento op = new AsociarPonenteRtaRequerimiento();
		SMessage result = op.ejecutar(id_carga);
		System.out.println(result.getValid());
	}
	
}
