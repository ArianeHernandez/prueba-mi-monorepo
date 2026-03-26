package com.osmosyscol.datasuite.bpm;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import co.htsoft.commons.util.SMessage;

import com.google.gson.Gson;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.bpm.dto.UsuarioDto;
import com.osmosyscol.datasuite.bpm.servicios.BpmServicios;
import com.osmosyscol.datasuite.bpm.servicios.BpmServiciosQuerys;
import com.osmosyscol.datasuite.mein.domain.CerlIn;
import com.osmosyscol.datasuite.mein.domain.CerlResponse;
import com.osmosyscol.datasuite.mein.dtos.WSData;
import com.osmosyscol.datasuite.mein.dtos.GenerarStickerRequest;
import com.osmosyscol.datasuite.mein.dtos.GenerarStickerResponse;
import com.osmosyscol.datasuite.mein.dtos.GenerarStickerServicio;
import com.osmosyscol.datasuite.mein.servicios.NotificacionServicio;
import com.osmosyscol.datasuite.mein.servicios.RuesServicio;
import com.osmosyscol.datasuite.near.webdata.GenerarNumeroProcesoBpm;
import com.osmosyscol.datasuite.near.webdata.RegistrarActuacionRiBpm;
import com.osmosyscol.datasuite.near.webdata.RepartoAutomaticoIntendencias;
import com.osmosyscol.datasuite.servlet.InitApp;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

public class BpmServiciosTest {

	private static BpmServiciosQuerys querys = BpmServiciosQuerys.getInstance();
	private static Gson gson = new Gson();
	final static Logger logger = Logger.getLogger(BpmServicios.class);

	public static void main(String[] args) throws Throwable {
		InitApp.startUp();
		try {
			
			// getValorListaTest();
	
			 getTokenTest();
	//		 getNumeroPorDependenciaTest();
			// infoRegimenInsolvenciaWebTest();
			
	//		consultarInfoRegistroRegimenInsolvenciaTest();
	//		consultarInfoNearTest();
	//		consultarInfoRtaReqNearTest();
	//		consultarProcesoActuacionesRITest();
	
	
//			actualizarPanelProcesoRadicacionWebTest();
	//		registrarProcesoRegInsolvenciaBpmTest();
			enviarProcesoActuacionRItoBPMTest();
	//		registrarActuacionRiBpmTest();
			
			// ruesTest();
			// getGenerarNumeroenSNSTest();
			// getRepartoAutomaticoTest();
	
			// otros procesos
			// generarImagenFirma();
			// generarImagenSticker();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	public static void getTokenTest() {
		try {	
			BpmServicios instance = BpmServicios.getInstance();
	
			String username = "NUVU";
			String password = "cualquiera123";
			UsuarioDto usuario = new UsuarioDto();
			usuario.setEmpresa(1);
	
			// Se codifican los datos del usuario
			usuario.setUsername(new String(Base64.encodeBase64(username.getBytes())));
			usuario.setPassword(new String(Base64.encodeBase64(password.getBytes())));
	
			// Usuario sin codificiar
			// usuario.setUsername(username);
			// usuario.setPassword(password);
			//
			String respuesta = instance.generarToken(null);
	
			if (null != respuesta && respuesta != "") {
				System.out.println("Nuevo token: " + respuesta);
			} else {
				System.out.println("TOKEN VACIO" + respuesta);
			}
	
			// Assert.assertEquals("115515151", respuesta);
		} catch (Throwable e) {
			
		}
	}

	public static void getNumeroPorDependenciaTest() throws SAXException,
			IOException, ParserConfigurationException {
		BpmServicios instance = BpmServicios.getInstance();

		int dependencia = 460;

		WSData integracion = instance.getNumeroPorDependencia(dependencia);
		if (integracion != null && integracion.getResponse() != null && Pattern.matches("\\d{4}-INS-\\d+", integracion.getResponse())) {
			System.out.println("OK" + integracion.getResponse());
		} else {
			System.out.println("BAD");
		}

		// Assert.assertEquals("115515151", respuesta);

	}

	/**
	 * Se recrea informacion enviada a BPM
	 * 
	 */
	public static void consultarInfoRegistroRegimenInsolvenciaTest() {
		BpmServicios instance = BpmServicios.getInstance();

		 
		 int idCarga = 116666;


		String jsonRegistro = instance
				.consultarInfoRegistroRegimenInsolvencia(idCarga);
		logger.debug("JSON REGISTRO RI CARGA " + idCarga + ": " + jsonRegistro);

	}
	
	
	/**
	 * Se recrea informacion enviada a BPM
	 * 
	 */
	public static void consultarInfoNearTest() {
		BpmServicios instance = BpmServicios.getInstance();
		
		/*
		 * 		Test
		 *	115433 	- 	Admision 
		 * 	115395 	- 	Inadmision
		 * 	115100	-	Rechazo 
		 */
		
		/*
		 * 		Dev3
		 * 	115133 	-	Inadmision
		 */
		
		int idCarga = 115743; 

		String jsonRegistro = instance.consultarInfoNear(idCarga);
//		System.out.println("JSON REGISTRO NEAR CARGA "+idCarga+": " + jsonRegistro);
		logger.debug("JSON REGISTRO NEAR CARGA " + idCarga + ": " + jsonRegistro);

	}

	public static void consultarInfoRtaReqNearTest() {
		BpmServicios instance = BpmServicios.getInstance();
		
		int idCarga = 115681; 

		String jsonRegistro = instance.consultarInfoRtaReqNear(idCarga);
//		System.out.println("JSON REGISTRO NEAR CARGA "+idCarga+": " + jsonRegistro);
		logger.debug("JSON REGISTRO NEAR CARGA " + idCarga + ": " + jsonRegistro);

	}
	
	
	/**
	 * Se recrea informacion de actuaciones sobre IR enviada a BPM
	 * 
	 */
	public static void consultarProcesoActuacionesRITest() {
		BpmServicios instance = BpmServicios.getInstance();

		int idCarga = 113871; // Liquidacion Simplificada SOC
		
		String jsonRegistro = instance.consultarProcesoActuacionesRI(idCarga);
		System.out.println("JSON ACTUACIONES CARGA "+idCarga+": " + jsonRegistro);

	}
	/**
	 * Se prueba Actuaciones de Regimen de Insolvencia sobre BPM 
	 * enviando información desde el servicio BPM. El token se genera aquí.
	 */
	public static void enviarProcesoActuacionRItoBPMTest() throws Throwable{
		BpmServicios instance = BpmServicios.getInstance();
		instance.generarToken(null);
		
		Integer idCarga = 125304; // REORG aBR
		
		WSData mensajes = instance.enviarProcesoActuacionABPM(idCarga);
		
		System.out.println("RESP ENVIO ACTUACION CARGA " + idCarga);
		System.out.println(mensajes.getResponse());
	}
	
	/**
	 * Se prueba Actuaciones de Regimen de Insolvencia sobre BPM 
	 * enviando información desde la operacion interna. El token lo genera la Op. Interna
	 */
	public static void registrarActuacionRiBpmTest(){
		RegistrarActuacionRiBpm opInterna = new RegistrarActuacionRiBpm();

		int idCarga = 113318;
		SMessage mensaje = opInterna.ejecutar(idCarga);

		System.out.println("RESPONSE OP_INTERNA ACTUACION: " +idCarga +", CODIGO: "+ mensaje.getValid()
				+ ", MENSAJE: " + mensaje.getMsg());
	}
	
	/**
	 * Se prueba enviado informacion de NEAR a BPM
	 */
	public static void actualizarPanelProcesoRadicacionWebTest() {
		Integer idCarga = 125296;

		WSData respuesta = BpmServicios.getInstance().enviarDatosBPM(idCarga);

		System.out.println("RESP SOLICITUD NEAR:  " + respuesta.getResponse());
	}

	/**
	 * Se prueba enviando informacion de Regimen de insolvencia a BPM
	 */
	public static void infoRegimenInsolvenciaWebTest() {
		int idCarga = 111275;

		WSData respuesta = BpmServicios.getInstance().enviarDatosBPM(idCarga);

		System.out.println("RESP REGIMEN INSOLVENCIA:  " + respuesta.getResponse());
	}

	public static void getGenerarNumeroenSNSTest() {
		// BpmServicios instance = BpmServicios.getInstance();

		GenerarNumeroProcesoBpm opInterna = new GenerarNumeroProcesoBpm();

		SMessage mensaje = opInterna.ejecutar(112139);

		System.out.println("Codigo: " + mensaje.getValid() + " Mensaje: "
				+ mensaje.getMsg());

	}

	public static void getRepartoAutomaticoTest() {
		// BpmServicios instance = BpmServicios.getInstance();

		RepartoAutomaticoIntendencias reparto = new RepartoAutomaticoIntendencias();

		reparto.ejecutar(111128);

		// System.out.println("Codigo: "+mensaje.getValid()+" Mensaje: "+mensaje.getMsg());

	}

	public static void getValorListaTest() {
		String salida = querys.getValorLista("TIPO SOLICITANTE", "1");

		System.out.println("salida: " + salida);

	}

	public static void generarImagenFirma() {
		System.out.println(BpmServicios.getInstance()
				.obtenerImagenFirmaFuncionario("1132"));

	}

	public static void ruesTest() {
		RuesServicio instance = RuesServicio.getInstance();

		CerlIn rues = new CerlIn();

		rues.setNit("900108590");
		rues.setTipoDoc("C");
		rues.setNumDoc("80723091");

		CerlResponse response = instance.obtenerDatosRues(rues.getNit(),
				rues.getNumDoc());
		// String strResponse = gson.toJson(response);
		System.out.println(gson.toJson(response));

	}

	public static void generarImagenSticker() {
		Integer id_carga = 0;
		
		GenerarStickerRequest data = null;
		
		WSData integracion = GenerarStickerServicio.getInstance().generarSticker(data, id_carga);
		GenerarStickerResponse sticker = gson.fromJson(integracion.getResponse(), GenerarStickerResponse.class);
		System.out.println(sticker.get_buffer());
	}

}
