package test;

import org.junit.Assert;

import com.google.gson.Gson;
import com.osmosyscol.datasuite.mein.dtos.RuleDto;
import com.osmosyscol.datasuite.mein.dtos.ResponsePasante;
import com.osmosyscol.datasuite.mein.dtos.SalidaObtenerRequisito;
import com.osmosyscol.datasuite.mein.dtos.SalidaRegistrarRequisito;
import com.osmosyscol.datasuite.mein.dtos.TagDto;
import com.osmosyscol.datasuite.mein.servicios.rest.client.PasanteAppRestClient;
import com.osmosyscol.datasuite.near.webdata.RegistroPasante;
import com.osmosyscol.datasuite.servlet.InitApp;

public class RegistroPasanteTest {
	
	private static Gson gson = new Gson();
	
	public static void main(String[] args) {
		InitApp.startUp();
		
		enviarDatosPasanteTest();  
		
		System.exit(0);
		
	}
	
	public static void enviarDatosPasanteTest() {
		Integer idCarga = 112139;
		RegistroPasante pasante = new RegistroPasante();
		pasante.ejecutar(idCarga);
		
	}
	
	
}
