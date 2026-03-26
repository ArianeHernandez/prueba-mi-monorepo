package test;

import co.htsoft.commons.util.SMessage;

import com.osmosyscol.datasuite.near.webdata.InfoAdicionalRtaRequerimiento;
import com.osmosyscol.datasuite.servlet.InitApp;

public class InfoAdicionalRtaReqTest {

	public static void main(String[] args) {
		InitApp.startUp();
		
		InfoAdicionalRtaReq();  
		
		System.exit(0);
		
	}
	
	public static void InfoAdicionalRtaReq() {
		Integer id_carga = 113633;
		InfoAdicionalRtaRequerimiento op = new InfoAdicionalRtaRequerimiento();
		SMessage result = op.ejecutar(id_carga);
		System.out.println(result.getValid());
	}
	
}
