package test;

import com.osmosyscol.datasuite.mein.servicios.rest.PersonaNaturalJuridica;
import com.osmosyscol.datasuite.mein.servicios.rest.client.SigsAppRestClient;
import com.osmosyscol.datasuite.servlet.InitApp;

public class SiggsAppRestClientTest {
	public static void main(String[] args) throws Throwable {
		InitApp.startUp();
		registrarPersonaNaturalJuridicaTest();
		consultarPersonaNaturalJuridicaTest();
		actualizarPersonaNaturalJuridicaTest();
		
		System.exit(0);
	}
	
	private static void registrarPersonaNaturalJuridicaTest(){
		Integer id_tipo_identificacion = 5;
		Long numero_identificacion = 9011939178l; //8070985365l;
		String razon_social = "test SISTEMAS COLOMBIA S.A.S";
		Integer id_pais_notificacion = 80;
		String telefono_notificacion = "5689785";
		Integer id_depto_notificacion = 11;
		Integer id_ciudad_notificacion = 2;
		String correo_electronico = "correo@algo.com";
		String direccion_notificacion = "Diagonal 1 con Carrera 2";
		
		PersonaNaturalJuridica personaNJ = new PersonaNaturalJuridica();
		personaNJ.setIdTipoIdentificacion(id_tipo_identificacion);
		personaNJ.setNumeroIdentificacionCaracter(Long.toString(numero_identificacion));
		personaNJ.setRazonSocial(razon_social);
		personaNJ.setIdPaisNotificacion(id_pais_notificacion);
		personaNJ.setTelefonosNotificacion(telefono_notificacion);
		personaNJ.setIdDepartamentoNotificacion(id_depto_notificacion);
		personaNJ.setIdCiudadNotificacion(id_ciudad_notificacion);
		personaNJ.setCorreoElectronico(correo_electronico);
		personaNJ.setDireccionNotificacion(direccion_notificacion);
		
		SigsAppRestClient.getInstance().registrarPersonaNaturalJuridica(personaNJ, 0);
	}
	
	private static void consultarPersonaNaturalJuridicaTest() throws Throwable{
		Integer tipoIdentificacion = 5;
//		Long numeroIdentificacion = 900743771l;  // 900743771l; // 901312101l; //901399985l;
//		Long numeroIdentificacion = 901312101l; //901399985l;
//		Long numeroIdentificacion = 7766885566l;  // 900743771l; // 901312101l; //901399985l;
		Long numeroIdentificacion = 901128158L;
		SigsAppRestClient.getInstance().consultarPersonaNaturalJuridica(tipoIdentificacion, numeroIdentificacion, 0);
	}
	
	private static void actualizarPersonaNaturalJuridicaTest(){
		PersonaNaturalJuridica personaNJ = new PersonaNaturalJuridica();
		personaNJ.setIdTipoIdentificacion(5);
		personaNJ.setNumeroIdentificacionCaracter(Long.toString(800123324l));
		personaNJ.setCorreoElectronico("correo@correoprueba.com");
		personaNJ.setDireccionNotificacion("Carrera 30 No. 30 - 30 Apto 30");
		personaNJ.setRazonSocial("NUEVA RAZON SOCIAL");
		personaNJ.setSigla("NEW_SIGLA");
		
		SigsAppRestClient.getInstance().actualizarPersonaNaturalJuridica(personaNJ, 0);
	}
	
	
}
