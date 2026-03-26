package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import co.htsoft.commons.util.SMessage;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.osmosyscol.datasuite.mein.dtos.RuleDto;
import com.osmosyscol.datasuite.mein.dtos.ResponsePasante;
import com.osmosyscol.datasuite.mein.dtos.SalidaObtenerRequisito;
import com.osmosyscol.datasuite.mein.dtos.SalidaRegistrarRequisito;
import com.osmosyscol.datasuite.mein.dtos.SolicitudNearSociedadResponseDto;
import com.osmosyscol.datasuite.mein.dtos.TagDto;
import com.osmosyscol.datasuite.mein.servicios.rest.client.PasanteAppRestClient;
import com.osmosyscol.datasuite.near.webdata.RepartoAutomaticoIntendenciasOtrosProcesos;
import com.osmosyscol.datasuite.servlet.InitApp;

public class RepartoAutomaticoIntendenciasOtrosProcesosTest {
	
	private static Gson gson = new Gson();
	
	public static void main(String[] args) {
		InitApp.startUp();
		
		repartoOtrosProcesosTest();
		
		System.exit(0);
		
	}
	
	public static void repartoOtrosProcesosTest() {
		Integer idCarga = 124782;
		
		RepartoAutomaticoIntendenciasOtrosProcesos otrosP = new RepartoAutomaticoIntendenciasOtrosProcesos();
		SMessage message = otrosP.ejecutar(idCarga);
		
		System.out.println(message.getValid() + " - " + message.getMsg());
		
		
	}	
	
}
