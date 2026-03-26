package test;

import org.apache.log4j.Logger;
import org.junit.Assert;

import com.google.gson.Gson;
import com.osmosyscol.datasuite.mein.dtos.EnterpriseInfoDto;
import com.osmosyscol.datasuite.mein.dtos.RuleDto;
import com.osmosyscol.datasuite.mein.dtos.ResponsePasante;
import com.osmosyscol.datasuite.mein.dtos.SalidaObtenerRequisito;
import com.osmosyscol.datasuite.mein.dtos.SalidaRegistrarRequisito;
import com.osmosyscol.datasuite.mein.dtos.TagDto;
import com.osmosyscol.datasuite.mein.servicios.rest.client.PasanteAppRestClient;
import com.osmosyscol.datasuite.servlet.InitApp;

public class PasanteAppRestClientTest {
	
	private static Gson gson = new Gson();
	final static Logger logger = Logger.getLogger(PasanteAppRestClientTest.class);
	
	public static void main(String[] args) {
		InitApp.startUp();
		
//		PasanteObtenerRequisitoTest();
//		PasanteRegistrarRequisitoTest();
		
//		obtenerInfoSolicitudesRITest(); //Obtener enterpriseInfo para request de Regimen de Insolvencia.
//		obtenerDatosRegistroTest();   // enviar informacion a pasante para solicitud near
		
//		obtenterSolicitudPorIdTest();
//		validarComunicacionPasanteGetTest();
//		validarComunicacionPasantePostTest();
		//validarReglaSolicitudTest();
		obtenerDatosRegistroOtrosProcesosTest();//enviar informacion a pasante para otros procesos
//		obtenerInfoRtaRequerimientoTest();
//		enviarRtaRequerimientoTest();
		
		System.exit(0);
		
	}
	
	public static void obtenerInfoSolicitudesRITest(){
		Integer idCarga = 114855;
		PasanteAppRestClient pasante = PasanteAppRestClient.getInstance();
		EnterpriseInfoDto enterpriseInfoDto = pasante.obtenerInfoSolicitudesRI(idCarga);
		
		logger.debug("INFO RI CARGA " + idCarga + " : " + gson.toJson(enterpriseInfoDto));
	}
	
	public static void obtenerDatosRegistroOtrosProcesosTest() {
		Integer idCarga = 123741;
		PasanteAppRestClient pasante = PasanteAppRestClient.getInstance();
		ResponsePasante salidaRequisito = pasante.obtenerDatosRegistroOtrosProcesos(idCarga);
		
		Assert.assertEquals("success", salidaRequisito.getError().getMessage());
		
	}
	
	public static void PasanteObtenerRequisitoTest() {
		PasanteAppRestClient pasante = PasanteAppRestClient.getInstance();
		SalidaObtenerRequisito salidaRequisito = pasante.obtenerRequisitos();
		
		Assert.assertEquals("success", salidaRequisito.getError().getMessage());
		
	}
	
	public static void PasanteRegistrarRequisitoTest() {
		PasanteAppRestClient pasante = PasanteAppRestClient.getInstance();
		
		String id = "004";
		String name = "Sujeto al regimen de insolvencia cuatro";
		
		SalidaRegistrarRequisito respuesta = pasante.registrarRequisito(id, name);
		
		Assert.assertEquals("success", respuesta.getError().getMessage());
		
	}
	
	public static void obtenerDatosRegistroTest() {
		//enviar datos pasante near
		PasanteAppRestClient pasante = PasanteAppRestClient.getInstance();
		
		Integer idCarga = 123757;
		
		ResponsePasante respuesta = pasante.obtenerDatosRegistro(idCarga);
		
//		Assert.assertEquals("110359", respuesta.getData().getId());
		
	}
	
	public static void obtenterSolicitudPorIdTest() {
		PasanteAppRestClient pasante = PasanteAppRestClient.getInstance();
		
		String id= "110889";
		String respuesta = pasante.obtenterSolicitudPorId(id);
		
//		System.out.println("ID-PROCESOS:  "+ respuesta.getData().get(0).getProcessId() );
		
		
		
	}
	
	public static void validarReglaSolicitudTest() {
		PasanteAppRestClient pasante = PasanteAppRestClient.getInstance();
		
		String idOficio= "110889";
		String idRequisito="001";
		
		RuleDto regla = new RuleDto();
		regla.setId(1);
		regla.setObservations("Las observaciones");
		regla.setValidated(true);
		regla.setApplies(true);

		TagDto tag = new TagDto();
		tag.setNombre_Representante_Legal("Luis");
		regla.setTags(tag);
		
//		String entrada = gson.toJson(regla);
		String entrada = "{\"id\":1,\"tags\":{\"Nombre_Representante_Legal\":\"Luis\"},\"observations\":\"Las observaciones\",\"validated\":true,\"applies\":true } }";

		String respuesta = pasante.validarReglaSolicitud(idOficio, idRequisito, entrada);
		
		System.out.println("RESP VALIDAR:  "+ respuesta);
		
	}
	
	public static void validarComunicacionPasantePostTest() {
		PasanteAppRestClient pasante = PasanteAppRestClient.getInstance();
		
		String url = "/oficio/110889/requisito/001/validar";
		String body = "{\"id\": 13,\"validated\": true}";
		
		

//		String respuesta = pasante.comunicacionPasante(url, "post", body);
		
//		System.out.println("RESP VALIDAR:  "+ respuesta);
		
	}
	
	public static void validarComunicacionPasanteGetTest() {
		PasanteAppRestClient pasante = PasanteAppRestClient.getInstance();
		
		String url = "/oficio/110889";
		
		

//		String respuesta = pasante.comunicacionPasante(url, "get", null);
		
//		System.out.println("RESP VALIDAR:  "+ respuesta);
		
	}
	
	// Obtener info de respuesta requerimiento por id
	public static void obtenerInfoRtaRequerimientoTest(){
		PasanteAppRestClient pasante = PasanteAppRestClient.getInstance();
		
		Integer idCarga = 113842; //110162;
		
		EnterpriseInfoDto enterpriseInfo = pasante.obtenerInfoRtaRequerimiento(idCarga);
		
		String strEnterpriseInfo = gson.toJson(enterpriseInfo);
		
		System.out.println("JSON RTA_RQTO CARGA: "+idCarga + "\n" + strEnterpriseInfo);
		
	}
	
	public static void enviarRtaRequerimientoTest(){
		PasanteAppRestClient pasante = PasanteAppRestClient.getInstance();
		
		Integer idCarga = 116013; // 110162;
		
		ResponsePasante salida = pasante.enviarRtaRequerimiento(idCarga);
		
		String strSalida = gson.toJson(salida);
		
		System.out.println("RESPUESTA PASANTES RTA_RTO: "+idCarga + "\n" + strSalida);
	}
	
	
	
}
