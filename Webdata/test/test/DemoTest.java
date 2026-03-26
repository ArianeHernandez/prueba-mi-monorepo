package test;

import java.text.ParseException;

import com.osmosyscol.commons.servlet.ContextInfo;
import com.osmosyscol.datasuite.bpm.servicios.BpmServicios;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;
import com.osmosyscol.datasuite.mein.servicios.rest.PersonaNaturalJuridica;
import com.osmosyscol.datasuite.mein.servicios.rest.client.SigsAppRestClient;
import com.osmosyscol.datasuite.near.servicios.radica.LadoRemoto;
import com.osmosyscol.datasuite.near.webdata.EnviarDatosSIGS;
import com.osmosyscol.datasuite.near.webdata.VerificaDocSolicitante;
import com.osmosyscol.datasuite.servlet.InitApp;

public class DemoTest {

	public static void main(String[] args) throws Throwable {
		ContextInfo.getInstance().setRealPath("/home/ec2-user/git/Webdata/WebContent");
		System.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
		System.setProperty("https.protocols", "TLSv1.2");
		//System.setProperty("javax.net.debug", "ssl:handshake:verbose");
		InitApp.startUp();
		
		try {

//			LadoRemoto.getInstance().getDocumentsFromPostal(150261, "2025-01-181597"); 
//			SigsAppRestClient.getInstance().generarToken();
//			SigsAppRestClient.getInstance().consultarPersonaNaturalJuridica(5, (long) 900815197, 126228); 
//			
//			Integer id_tipo_identificacion = 5;
//			Long numero_identificacion = 900815100L;
//			String razon_social = "PALERMO FRUTAS";
//			String correo_electronico = "jessica.castillo@nuvu.cc";
//			String telefono_notificacion = "4485252";
//			String direccion_notificacion = "Cll 100 52 20";
//			Integer id_pais_notificacion = 80;
//			Integer id_depto_notificacion = 41;
//			Integer id_ciudad_notificacion = 524;
//			
//			PersonaNaturalJuridica personaNJ = new PersonaNaturalJuridica();
//			personaNJ.setIdTipoIdentificacion(id_tipo_identificacion);
//			personaNJ.setNumeroIdentificacion(numero_identificacion);
//			personaNJ.setNumeroIdentificacionCaracter(Long.toString(numero_identificacion));
//			personaNJ.setRazonSocial(razon_social);
//			personaNJ.setCorreoElectronico(correo_electronico);
//			personaNJ.setTelefonosNotificacion(telefono_notificacion);
//			personaNJ.setDireccionNotificacion(direccion_notificacion);
//			personaNJ.setIdPaisNotificacion(id_pais_notificacion);
//			personaNJ.setIdDepartamentoNotificacion(id_depto_notificacion);
//			personaNJ.setIdCiudadNotificacion(id_ciudad_notificacion);
//			
//			SigsAppRestClient.getInstance().registrarPersonaNaturalJuridica(personaNJ, 126228);
//			SigsAppRestClient.getInstance().actualizarPersonaNaturalJuridica(personaNJ, 126228);

//			BpmServicios.getInstance().obtenerImagenFirmaFuncionario("79785316"); 
//
//			VerificaDocSolicitante op = new VerificaDocSolicitante (); 
//			op.ejecutar(151880);

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Finaliza");
		System.exit(0);
	}

}

