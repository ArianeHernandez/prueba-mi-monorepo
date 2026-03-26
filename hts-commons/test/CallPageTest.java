import java.util.HashMap;
import java.util.Map;

import co.htsoft.commons.net.CallPage;

public class CallPageTest {

	public static void main(String[] args) {

		String json = "{\"PersonaNaturalJuridica\": { \"IdTipoIdentificacion\": 5, \"NumeroIdentificacion\": 80700053655, \"NumeroIdentificacionCaracter\": \"80700053655\", \"RazonSocial\": \"Prueba\", \"FechaIngreso\": \"\\/Date(1614269613669-0500)\\/\","
				+ "\"Sigla\": \"\", \"IdPaisNotificacion\": 85, \"TelefonosNotificacion\": \"123\", \"IdDepartamentoNotificacion\": 256, \"IdCiudadNotificacion\": 16610, \"CorreoElectronico\": \"algo.com\", \"DireccionNotificacion\": \"\""
				+ "}, \"Usuario\": \"123\" }";

//		String json = "{}";
		String endpoint = "http://pinteroperabilidad.supersociedades.gov.co:8085/Services/IntegrationSIGS_APP.svc/RegistrarPersonaNaturalJuridica/";
		
		CallPage cp = new CallPage();
		Map<String,String> headers = new HashMap<>();

		headers.put("Content-Type", "application/json");
		String result = cp.callPost(endpoint, json, headers);
		System.out.println(result);

		
	}

}
