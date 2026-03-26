package test;

import co.htsoft.commons.util.SMessage;

import com.google.gson.Gson;
import com.osmosyscol.datasuite.near.webdata.EnviarDatosPasanteOtrosProcesos;
import com.osmosyscol.datasuite.near.webdata.EnviarRtaRequerimientoPasante;
import com.osmosyscol.datasuite.servlet.InitApp;

public class EnviarRtaRequerimientoPasanteTest {

private static Gson gson = new Gson();
	
	public static void main(String[] args) {
		InitApp.startUp();
		
		enviarDatosPasanteTest();
		
		System.exit(0);
		
	}
	
	public static void enviarDatosPasanteTest() {
		Integer idCarga = 113849;
		
		EnviarRtaRequerimientoPasante otrosP = new EnviarRtaRequerimientoPasante();
		SMessage message = otrosP.ejecutar(idCarga);
		
		System.out.println(message.getMsg());
				
	}
	
}
