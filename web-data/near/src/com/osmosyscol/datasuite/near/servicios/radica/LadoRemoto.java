package com.osmosyscol.datasuite.near.servicios.radica;

import static com.osmosyscol.datasuite.near.utils.ParametrosInicioUtils.$;
import static com.osmosyscol.datasuite.near.utils.ParametrosInicioUtils.getOrElse;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

import org.apache.axis.message.SOAPHeaderElement;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.jxpath.AbstractFactory;
import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.Pointer;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.datacontract.schemas._2004._07.InteropSuper_Modelos.Ciudad;
import org.datacontract.schemas._2004._07.InteropSuper_Modelos.CiudadId;
import org.datacontract.schemas._2004._07.InteropSuper_Modelos.Corresponsal;
import org.datacontract.schemas._2004._07.InteropSuper_Modelos.Dependencia;
import org.datacontract.schemas._2004._07.InteropSuper_Modelos.Documento;
import org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad;
import org.datacontract.schemas._2004._07.InteropSuper_Modelos.Funcionario;
import org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass;
import org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio;
import org.datacontract.schemas._2004._07.InteropSuper_Modelos.Particular;
import org.datacontract.schemas._2004._07.InteropSuper_Modelos.Proceso;
import org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion;
import org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionInterna;
import org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionSalida;
import org.datacontract.schemas._2004._07.InteropSuper_Modelos.Termino;
import org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoCuaderno;
import org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental;
import org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoIdentificacion;
import org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite;
import org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaRadicacionInterna;
import org.datacontract.schemas._2004._07.System_ServiceModel.FaultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tempuri.BasicHttpBinding_IIntegrationPostalServicesStub;
import org.tempuri.IIntegrationPostalServices;
import org.tempuri.IntegrationPostalServicesLocator;

import com.google.gson.Gson;
import com.microsoft.schemas._2003._10.Serialization.Arrays.ArrayOfKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsIKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsI;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.JavaToXML;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.dto.ArchivoAdjunto;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;
import com.osmosyscol.datasuite.mein.dtos.Deudor;
import com.osmosyscol.datasuite.mein.dtos.RegimenInsolvencia;
import com.osmosyscol.datasuite.mein.dtos.RepartoIntendencias;
import com.osmosyscol.datasuite.mein.dtos.RespuestaRequerimiento;
import com.osmosyscol.datasuite.mein.dtos.SolicitudNearSociedad;
import com.osmosyscol.datasuite.mein.dtos.TipoDeDocumento;
import com.osmosyscol.datasuite.mein.dtos.WSData;
import com.osmosyscol.datasuite.mein.logica.constantes.Constantes;
import com.osmosyscol.datasuite.mein.servicios.CalculoCategoriaTamanoServicio;
import com.osmosyscol.datasuite.mein.servicios.NotificacionServicio;
import com.osmosyscol.datasuite.mein.servicios.SolicitudNearSociedadServicio;
import com.osmosyscol.datasuite.mein.servicios.TipoDeDocumentoServicio;
import com.osmosyscol.datasuite.near.servicios.AccionPublicarAdjuntosCarga;
import com.osmosyscol.datasuite.near.servicios.IPostalConfig;
import com.osmosyscol.datasuite.near.servicios.IPostalInteraccion;
import com.osmosyscol.datasuite.near.servicios.InfoRadicadoLocalServicio;
import com.osmosyscol.datasuite.near.utils.CalendarUtils;
import com.osmosyscol.datasuite.near.utils.Either;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class LadoRemoto {
	private static LadoRemoto _instance = new LadoRemoto();

	private LadoRemoto() {
	}

	public static LadoRemoto getInstance() {
		return _instance;
	}

	static final Logger logger = LoggerFactory.getLogger(LadoRemoto.class);
	Gson gson = new Gson();

	private static Map<String, TipoDeDocumento> MAPA_TIPOS_DOCUMENTO_POSTAL = TipoDeDocumentoServicio.getInstance().getMapForCodHts();

	Either<Exception, Object> radicar_Dummy(Integer id_carga) {
		String radicadoPostal = "123456789";

		return Either.right((Object) radicadoPostal);
	}

	public Either<Exception, IIntegrationPostalServices> createClient() {
		// final String _endpoint_debug =
		// "http://localhost:8888/Interop/Services/IntegrationPostalServices.svc";

		Either<Exception, String> _endpointValue = $(IPostalInteraccion.CONFIGPARAM__INTEGRACION_POSTAL_ENDPOINT);

		if (_endpointValue.isLeft()) {
			return Either.left(_endpointValue.left());
		}

		IntegrationPostalServicesLocator loc = new IntegrationPostalServicesLocator();

		java.net.URL portAddress;
		BasicHttpBinding_IIntegrationPostalServicesStub stub;
		try {
			// portAddress = new java.net.URL(_endpoint);
			portAddress = new java.net.URL(_endpointValue.right());
			stub = (BasicHttpBinding_IIntegrationPostalServicesStub) loc
					.getBasicHttpBinding_IIntegrationPostalServices(portAddress);
			return Either.right((IIntegrationPostalServices) stub);
		} catch (MalformedURLException e) {
			logger.error("creating client / url", e);
			return Either.left(new Exception(e));
		} catch (javax.xml.rpc.ServiceException jre) {
			if (jre.getLinkedCause() != null) {
				logger.error("creating client / service", jre.getLinkedCause());
			} else {
				logger.error("creating client / service", jre.getLinkedCause());
			}
			return Either.left(new Exception(jre));
		}
	}

	static void addHeader(
			IIntegrationPostalServices client,
			String namespace, String partname, Object value) {

		BasicHttpBinding_IIntegrationPostalServicesStub stub = (BasicHttpBinding_IIntegrationPostalServicesStub) client;

		SOAPHeaderElement headerElement = new SOAPHeaderElement(
				new javax.xml.namespace.QName(namespace, partname), value);
		headerElement.setActor(null);
		stub.setHeader(headerElement);

	}

	static void addProperty(
			IIntegrationPostalServices client,
			String propertyName, Object propertyValue) {

		BasicHttpBinding_IIntegrationPostalServicesStub stub = (BasicHttpBinding_IIntegrationPostalServicesStub) client;
		stub._setProperty(propertyName, propertyValue);
	}

	/**
	 * Sincroniza lado postal, archivos
	 * 
	 * @deprecated usar {@link AccionPublicarAdjuntosCarga} en su lugar para
	 *             adjutnar archivos
	 * @param idCarga
	 * @param adjunto
	 * @return
	 */
	@Deprecated
	public Either<Exception, Boolean> sync_Adjunto(Integer idCarga,
			ArchivoAdjunto adjunto, Long currentTipoDocumento) {

		// PRERREQUISITO: cliente creado

		Either<Exception, IIntegrationPostalServices> _clientWrapper = createClient();

		if (_clientWrapper.isLeft()) {
			return Either.left(_clientWrapper.left());
		}

		IIntegrationPostalServices _client = _clientWrapper.right();

		try {

			// IntegrationPostalServicesLocator
			// loc = new
			// IntegrationPostalServicesLocator();
			// IIntegrationPostalServices
			// binding = loc.getBasicHttpBinding_IIntegrationPostalServices()
			// org.apache.axis.client.Stub s = (Stub) binding;
			final Either<Exception, String> currentRadicacion = LadoLocal
					.getInstance().leeRadicadoPostal_UsingMaster(idCarga);
			final String currentExtension = "."
					+ adjunto.getExtension_archivo();

			// addProperty( _client, Call.ATTACHMENT_ENCAPSULATION_FORMAT,
			// Call.ATTACHMENT_ENCAPSULATION_FORMAT_MIME);
			// addProperty( _client, Call.ATTACHMENT_ENCAPSULATION_FORMAT,
			// Call.ATTACHMENT_ENCAPSULATION_FORMAT_MTOM);
			// addProperty( _client, Call.ATTACHMENT_ENCAPSULATION_FORMAT,
			// Call.ATTACHMENT_ENCAPSULATION_FORMAT_DIME);

			byte[] fileBytes = IOUtils.toByteArray(new FileInputStream(adjunto
					.getRuta()));
			byte[] encoded = Base64.encodeBase64(fileBytes);

			// interceptStub(_client, encoded);

			// DataHandler dhSource = new DataHandler( new String(encoded) ,
			// "text/plain"); // multipart-related
			// DataHandler dhSource = new DataHandler( new String( encoded),
			// "tmp" );
			// DataHandler dhSource = new DataHandler( new
			// ByteArrayDataSource(encoded) );
			// DataHandler dhSource = new DataHandler(new
			// FileDataSource(adjunto.getRuta()));
			// DataHandler dhSource = new DataHandler( new String(fileBytes) ,
			// "text/xml");
			// DataHandler dhSource = new DataHandler(new
			// ByteArrayDataSource(encoded, "application/octet-stream"));

			// DataHandler dhSource = new DataHandler(new
			// ByteArrayDataSource(encoded));

			addHeader(_client, "http://tempuri.org/", "TipoDocumento",
					currentTipoDocumento);
			addHeader(_client, "http://tempuri.org/", "Radicacion",
					currentRadicacion.right());
			addHeader(_client, "http://tempuri.org/", "Extension",
					currentExtension);

			_client.archivo_Subir(encoded);// fileBytes);
			// Header??

			// adjunto.getExtension_archivo()
			// adjunto.getNombre_archivo()

			logger.info(
					"syncRemoteSide (end) [idCarga:{},radicadoPostal:{},fileName:{},path:{}]",
					idCarga, currentRadicacion, adjunto.getNombre(),
					adjunto.getRuta());
			return Either.right(true);
		} catch (IOException e) {
			logger.error("syncRemoteSide ", e);
			return Either.left(new Exception(e));
		} catch (Exception e) {
			logger.error("syncRemoteSide ", e);
			return Either.left(e);
		}

	}

	/**
	 * Lanza envio sincronizacion info radicacion hacia sistema documental
	 * (postal)
	 * 
	 * @param idCarga
	 * @param infoSource
	 * @param puntoInteraccion
	 * @return
	 */

	public Either<Exception, Object> sync_Master(Integer idCarga,
			Map<String, Object> payloadSource) {

		// PRERREQUISITO: cliente creado

		Either<Exception, IIntegrationPostalServices> _clientWrapper = createClient();

		if (_clientWrapper.isLeft()) {
			return Either.left(_clientWrapper.left());
		}

		IIntegrationPostalServices _client = _clientWrapper.right();

		// Invocacion cliente para radicar en Postal
		Either<Exception, Radicacion> _mapperWrapper = Either
				.left(new Exception("Error en LadoRemoto.mapRequest"));
		
		String estructura = Objects.toString(InfoRadicadoLocalServicio.getInstance().obtenerNombreEstructuraFormatoPorCarga(idCarga), "");
		
		switch(estructura){
		case Constantes.ESTRUCTURA_NEAR_SOCIEDAD:
			_mapperWrapper = mapRequest(payloadSource);
			break;
			
		case Constantes.ESTRUCTURA_REGIMEN_INSOLVENCIA:
			_mapperWrapper = mapRequestRegimen(payloadSource);
			break;
			
		case Constantes.ESTRUCTURA_RESPUESTA_REQUERIMIENTO:
			_mapperWrapper = mapRequestRtaRequerimiento(payloadSource);
			break;
		}

		if (_mapperWrapper.isLeft()) {
			return Either.left(_mapperWrapper.left());
		}

		Radicacion serviceRequest = _mapperWrapper.right();

		Radicacion serviceResponse;
		
		WSData integracion = new WSData();
		integracion.setSistema(Constantes.SSOC_SISTEMA_POSTAL);
		integracion.setIntegracion(Constantes.WS_INTEGRACION_POSTAL_RADICACION_ENTRADA);
		integracion.setId_carga(idCarga);
		
		try {
			String request = JavaToXML.exe("RadicacionRadicar", serviceRequest).toString();
			logger.debug("Request Radicacion radicar " + idCarga + " - " + request);
			integracion.setRequest(gson.toJson(serviceRequest));
			
			serviceResponse = _client.radicacion_Radicar(serviceRequest);

			logger.debug("Response Radicacion radicar "+ idCarga + " - " + JavaToXML.exe("RadicacionRadicarResponse", serviceResponse).toString());

			return Either.right((Object) serviceResponse);

		} catch (Exception e) {
			logger.error("syncRemoteSide/master (error)", e);
			String error = ExceptionUtils.getStackTrace(e);
			integracion.setResponse("Error generado \n " + error);
			NotificacionServicio.getInstance().notificarErrorWS(integracion);
			return Either.left(e);

		}

	} // End Class

	public Either<Exception, Object> sync_RadicarInterna(Integer idCarga,
			Map<String, Object> payloadSource) {

		// PRERREQUISITO: cliente creado

		Either<Exception, IIntegrationPostalServices> _clientWrapper = LadoRemoto
				.getInstance().createClient();

		if (_clientWrapper.isLeft()) {
			return Either.left(_clientWrapper.left());
		}

		IIntegrationPostalServices _client = _clientWrapper.right();

		RadicacionInterna serviceRequest = (RadicacionInterna) payloadSource
				.get(IPostalInteraccion.PARAM_SOLICITUD);

		// String radicadoPostal;

		RespuestaRadicacionInterna serviceResponse;
		try {
			logger.debug("Request Radicar Interna solicitud "
					+ idCarga
					+ " - "
					+ JavaToXML.exe("RadicacionInterna", serviceRequest)
							.toString());

			serviceResponse = _client.radicacion_RadicarInterna(serviceRequest);

			logger.debug("Response Radicar Interna solicitud "
					+ idCarga
					+ " - "
					+ JavaToXML.exe("RadicacionInternaResponse",
							serviceResponse).toString());

			return Either.right((Object) serviceResponse);

		} catch (FaultException e) {
			logger.error("syncRemoteSide/master (error)", e);
			return Either.left(new Exception(e));

			// e.printStackTrace();
		} catch (RemoteException e) {
			logger.error("syncRemoteSide/master (error) [type=connection]", e);
			// e.printStackTrace();
			return Either.left(new Exception(e));
		}

	}

	public Either<Exception, Object> sync_RadicarSalida(Integer idCarga,
			Map<String, Object> payloadSource) {
		Either<Exception, IIntegrationPostalServices> _clientWrapper = LadoRemoto
				.getInstance().createClient();

		if (_clientWrapper.isLeft()) {
			return Either.left(_clientWrapper.left());
		}

		IIntegrationPostalServices _client = _clientWrapper.right();

		RadicacionSalida serviceRequest = (RadicacionSalida) payloadSource
				.get(IPostalInteraccion.PARAM_SOLICITUD);

		// String radicadoPostal;

		IBPMWSDevolucionClass serviceResponse;
		try {
			logger.debug("Request Radicar Salida solicitud "
					+ idCarga
					+ " - "
					+ JavaToXML.exe("RadicacionSalida", serviceRequest)
							.toString());
			serviceResponse = _client.radicarSalida(serviceRequest);

			logger.debug("Response Radicar Salida solicitud "
					+ idCarga
					+ " - "
					+ JavaToXML
							.exe("RadicacionSalidaResponse", serviceResponse)
							.toString());

			return Either.right((Object) serviceResponse);

		} catch (FaultException e) {
			logger.error("syncRemoteSide/master (error)", e);
			return Either.left(new Exception(e));

			// e.printStackTrace();
		} catch (RemoteException e) {
			logger.error("syncRemoteSide/master (error) [type=connection]", e);
			// e.printStackTrace();
			return Either.left(new Exception(e));
		}
	}
	
	public Documento[] getDocumentsFromPostal(Integer id_carga, String radicado) {
		
		Either<Exception, IIntegrationPostalServices> _clientWrapper = LadoRemoto.getInstance().createClient();

		if (_clientWrapper.isLeft()) {
			logger.error("getDocumentFromPostal (error client)");
			return null;
		}

		IIntegrationPostalServices _client = _clientWrapper.right();

		Documento[] documentos = {};
		
		try {
			logger.info("Obteniendo documentos de Postal: Solicitud " + id_carga + " Radicado " + radicado);
					
			documentos = _client.perfil_ObtenerDocumentos(radicado);

			logger.info("Response Documentos Postal Solicitud " + id_carga + " - " + JavaToXML.exe("Documento", documentos).toString());

			return documentos;

		} catch (Exception e) {
			logger.error("getDocumentFromPostal (error)", e);
			return null;
		}
		
	}

	// solicitud near
	Either<Exception, Radicacion> mapRequest(
			Map<String, Object> payloadSource) {

		String puntoInteraccion = (String) payloadSource
				.get(IPostalInteraccion.PARAM_PUNTOINTERACCION);
		Long adjuntos_Count = (Long) payloadSource
				.get(IPostalInteraccion.PARAM_COUNT_ADJUNTOS);
		SolicitudNearSociedad infoSource = (SolicitudNearSociedad) payloadSource
				.get(IPostalInteraccion.PARAM_SOLICITUD);
		String radicacionAnteriorNumero = getOrElse(payloadSource,
				IPostalInteraccion.PARAM_RADICACIONANTERIORNUMERO, null,
				String.class);
		Integer correlationIdPart1 = getOrElse(payloadSource,
				IPostalInteraccion.PARAM_CORRELATION_ID1, null, Integer.class);
		String numProceso = Objects.toString(infoSource.getNumero_proceso(), "");

		String dependenciaNombre = Objects.toString(ParametrosInicio
				.getProperty("postal.puntointeraccion.radicacion_radicar.dependencia.nombre"), ""); //"GRUPO DE ADMISIONES";
		Long dependenciaId = Long.parseLong(ParametrosInicio
				.getProperty("postal.puntointeraccion.radicacion_radicar.dependencia.id")); //"460";
		String corresponsalDependenciaNombre = Objects.toString(ParametrosInicio
				.getProperty("postal.puntointeraccion.radicacion_radicar.corresponsal.dependencia.nombre"), ""); //"GRUPO DE GESTION DOCUMENTAL";
		Long corresponsalDependenciaId = Long.parseLong(ParametrosInicio
				.getProperty("postal.puntointeraccion.radicacion_radicar.corresponsal.dependencia.id")); //"547;
		
		// TODO modificar esta consulta y cambiar de posicion el reparto de
		// intendencias para hacerlo antes de radicar
		try {

			RepartoIntendencias reparto = SolicitudNearSociedadServicio
					.getInstance().obtenerReparto(infoSource.getIdcarga());

			if (reparto != null) {
				dependenciaNombre = reparto.getDependencia();
				dependenciaId = Long.parseLong(reparto.getCodigo_dependencia());
				//
			} else {
				SimpleLogger
						.setError("No se encuentra la intendencia para la solicitud (radicacion)"
								+ infoSource.getIdcarga());

			}
		} catch (Exception e) {
			SimpleLogger.setError(
					"Error en el reparto de intendencias (radicacion):", e);

		}
		// fin del TODO a remover

		Either<Exception, String> _usuario = $(IPostalInteraccion.CONFIGPARAM__INTEGRACION_POSTAL_USUARIO);

		if (_usuario.isLeft()) {
			return Either.left(_usuario.left());
		}

		Radicacion serviceRequest = new Radicacion();

		IPostalConfig puntoInteraccionProperties = IPostalConfig
				.getInstance(puntoInteraccion);

		if (puntoInteraccionProperties.isLeft()) {
			return Either.left(puntoInteraccionProperties.left());
		}

		Integer terminoDias = infoSource.getTramite_obj().getTermino_dias();

		Either<Exception, String> correlationId = buildCompleteCorrelationId(
				puntoInteraccionProperties,
				(null != correlationIdPart1) ? (correlationIdPart1.toString())
						: (null), numProceso);
		if (correlationId.isLeft()) {
			return Either.left(correlationId.left());
		}

		Date currentDate = new java.util.Date();
		Calendar currentDateAsCalendar = CalendarUtils
				.dateAsCalendar(currentDate);
		Calendar terminoDateAsCalendar = CalendarUtils.calendarAddDays(
				currentDateAsCalendar, terminoDias);
		;

		// Contexto Local
		JXPathContext jxInfoSource = JXPathContext.newContext(infoSource);
		jxInfoSource.setLenient(true);
		
		// Contexto Remoto

		JXPathContext jxServiceRequest = JXPathContext
				.newContext(serviceRequest);
		jxServiceRequest.setFactory(new JxPostalRequestFactory());
		jxServiceRequest.createPath("aplicaA/ciudad/codigo");
		jxServiceRequest.createPath("aplicaA/tipoIdentificacion");
		jxServiceRequest.createPath("corresponsal/dependencia");
		jxServiceRequest.createPath("corresponsal/particular/ciudad/codigo");
		jxServiceRequest
				.createPath("corresponsal/particular/tipoIdentificacion");
		jxServiceRequest.createPath("dependencia");
		jxServiceRequest.createPath("dependenciaAsignada");
		jxServiceRequest.createPath("funcionario");
		jxServiceRequest.createPath("funcionarioAsignado");
		jxServiceRequest.createPath("medioDeEnvio");
		jxServiceRequest.createPath("tipoDocumental");
		jxServiceRequest.createPath("tipoSeguridad");
		jxServiceRequest.createPath("tramite/termino");
		jxServiceRequest.createPath("tramite/proceso");
		jxServiceRequest.createPath("documentosAnexos");

		jxServiceRequest.setValue("anexosFisicos", "Anexos");
		
		jxServiceRequest.setValue("aplicaA/ciudad/codigo/ciudadCodigo", valueOf(jxInfoSource, "deudor/municipioObj/codigoMunicipioPostal", Integer.class));
		jxServiceRequest.setValue("aplicaA/ciudad/codigo/departamentoCodigo", valueOf(jxInfoSource, "deudor/departamentoObj/codigo_departamento", Integer.class));
		jxServiceRequest.setValue("aplicaA/ciudad/codigo/paisCodigo", valueOf(jxInfoSource, "deudor/pais_dane_obj/codigo_ssoc", Integer.class));
		jxServiceRequest.setValue("aplicaA/ciudad/departamento", valueOf(jxInfoSource, "deudor/departamentoObj/nombre_departamento", String.class));
		jxServiceRequest.setValue("aplicaA/ciudad/nombre", valueOf(jxInfoSource, "deudor/municipioObj/nombre_ciudad", String.class));
		jxServiceRequest.setValue("aplicaA/ciudad/pais", valueOf(jxInfoSource, "deudor/pais_dane_obj/nombre_ssoc", String.class));
		jxServiceRequest.setValue("aplicaA/direccion", valueOf(jxInfoSource, "deudor/datos_basicos/direccion", String.class));
		jxServiceRequest.setValue("aplicaA/email", valueOf(jxInfoSource, "deudor/datos_basicos/correo", String.class));
		jxServiceRequest.setValue("aplicaA/identificacion", valueOf(jxInfoSource, "deudor/datos_basicos/identificacion", String.class)); // "999999999"
		jxServiceRequest.setValue("aplicaA/nombre", valueOf(jxInfoSource, "deudor/datos_basicos/nombre_completo", String.class)); // "Sociedad de Pruebas");
		jxServiceRequest.setValue("aplicaA/telefono", valueOf(jxInfoSource, "deudor/datos_basicos/telefono", String.class)); // ""
		
		TipoDeDocumento tipoDocIdentif = MAPA_TIPOS_DOCUMENTO_POSTAL.get(valueOf(jxInfoSource, "deudor/datos_basicos/tipo_identificacion", String.class));
		SimpleLogger.setDebug("Consultando tipo documento "
				+ valueOf(jxInfoSource, "deudor/datos_basicos/tipo_identificacion", String.class)
				+ " Envia: " + tipoDocIdentif.getId_postal());
		jxServiceRequest.setValue("aplicaA/tipoIdentificacion/id", tipoDocIdentif.getCodigo_numerico_postal());
		jxServiceRequest.setValue("aplicaA/tipoIdentificacion/nombre", tipoDocIdentif.getNombre_postal());
		
		// INFORMACIÓN DE CORRESPONSAL (PARTICULAR)
		jxServiceRequest.setValue("corresponsal/dependencia/id", corresponsalDependenciaId);
		jxServiceRequest.setValue("corresponsal/dependencia/nombre", corresponsalDependenciaNombre);
		jxServiceRequest.setValue("corresponsal/particular/ciudad/codigo/ciudadCodigo", 
				valueOf(jxInfoSource, "deudor/municipioObj/codigoMunicipioPostal", Integer.class));
		jxServiceRequest.setValue("corresponsal/particular/ciudad/codigo/departamentoCodigo",
				valueOf(jxInfoSource, "deudor/departamentoObj/codigo_departamento", Integer.class));
		jxServiceRequest.setValue("corresponsal/particular/ciudad/codigo/paisCodigo", 
				valueOf(jxInfoSource, "deudor/pais_dane_obj/codigo_ssoc", Integer.class));
		jxServiceRequest.setValue("corresponsal/particular/ciudad/departamento", 
				valueOf(jxInfoSource, "deudor/departamentoObj/nombre_departamento", String.class));
		jxServiceRequest.setValue("corresponsal/particular/ciudad/nombre",
				valueOf(jxInfoSource, "deudor/municipioObj/nombre_ciudad", String.class));
		jxServiceRequest.setValue("corresponsal/particular/ciudad/pais",
				valueOf(jxInfoSource, "deudor/pais_dane_obj/nombre_ssoc", String.class));
		jxServiceRequest.setValue("corresponsal/particular/direccion", 
				Objects.toString(valueOf(jxInfoSource, "deudor/datos_basicos/direccion",String.class),"")); // "Cra 11 No. 61 - 44 Apto 302"
		jxServiceRequest.setValue("corresponsal/particular/email",
				valueOf(jxInfoSource, "deudor/datos_basicos/correo", String.class)); // "juan_pabloparedes@yahoo.com"
		jxServiceRequest.setValue("corresponsal/particular/telefono", 
				valueOf(jxInfoSource, "deudor/datos_basicos/telefono", String.class)); // "3112234277");
		
		if(null != infoSource.getTipo_solicitante()
				&& (infoSource.getTipo_solicitante().equals(Constantes.TIPO_SOLICITANTE_SOCIEDAD) || infoSource.getTipo_solicitante().equals(Constantes.TIPO_SOLICITANTE_SUCURSALES_EXTRANJERAS))) {
			
			jxServiceRequest.setValue("corresponsal/particular/identificacion",
					valueOf(jxInfoSource, "deudor/representante_legal/identificacion", String.class)); // "80097486");
			jxServiceRequest.setValue("corresponsal/particular/nombre",
					valueOf(jxInfoSource, "deudor/representante_legal/nombre_completo", String.class)); // "JUAN PABLO PAREDES AGUIRRE"
			
			TipoDeDocumento tipoDeDocumentoRepLegal = new TipoDeDocumento();
			if (infoSource.getDeudor().getRepresentante_legal() != null) {
				tipoDeDocumentoRepLegal = MAPA_TIPOS_DOCUMENTO_POSTAL.get(infoSource.getDeudor().getRepresentante_legal().getTipo_identificacion());
			}
			jxServiceRequest.setValue("corresponsal/particular/tipoIdentificacion/id", Objects.toString(tipoDeDocumentoRepLegal.getCodigo_numerico_postal(), ""));
			jxServiceRequest.setValue("corresponsal/particular/tipoIdentificacion/nombre", Objects.toString(tipoDeDocumentoRepLegal.getNombre_postal(), ""));

		} else if(null != infoSource.getTipo_solicitante()
				&& (infoSource.getTipo_solicitante().equals(Constantes.TIPO_SOLICITANTE_PNC) || infoSource.getTipo_solicitante().equals(Constantes.TIPO_SOLICITANTE_PNNC))){
			jxServiceRequest.setValue("corresponsal/particular/identificacion",
					valueOf(jxInfoSource, "deudor/datos_basicos/identificacion", String.class)); // "80097486");
			jxServiceRequest.setValue("corresponsal/particular/nombre",
					valueOf(jxInfoSource, "deudor/datos_basicos/nombre_completo", String.class)); // "JUAN PABLO PAREDES AGUIRRE"
//		jxServiceRequest.setValue("corresponsal/particular/tipoIdentificacion/id", 5L);
			jxServiceRequest.setValue("corresponsal/particular/tipoIdentificacion/id", tipoDocIdentif.getCodigo_numerico_postal());
//		jxServiceRequest.setValue("corresponsal/particular/tipoIdentificacion/nombre", "NIT");
			jxServiceRequest.setValue("corresponsal/particular/tipoIdentificacion/nombre", tipoDocIdentif.getNombre_postal());
		}  		
		jxServiceRequest.setValue("corresponsal/tipo", "Particular");
		
		jxServiceRequest.setValue("dependencia/id", dependenciaId); // 511L
		jxServiceRequest.setValue("dependencia/nombre", dependenciaNombre);
		jxServiceRequest.setValue("dependenciaAsignada/id", dependenciaId); // 430L
		jxServiceRequest.setValue("dependenciaAsignada/nombre", dependenciaNombre); // ""
		jxServiceRequest.setValue("documentoPrincipal", "");
		jxServiceRequest.setValue("documentosAnexos", null);
		jxServiceRequest.setValue("entregaFisica", false);
		jxServiceRequest.setValue("estado", "OFICIAL");
		jxServiceRequest.setValue("fecha", currentDateAsCalendar);
		jxServiceRequest.setValue("folios", adjuntos_Count);
		jxServiceRequest.setValue("funcionario/cargo", "");
		jxServiceRequest.setValue("funcionario/codigo", "");
		jxServiceRequest.setValue(
				"funcionario/id",
				puntoInteraccionProperties.valueOf(
						IPostalInteraccion.SVC_FUNCIONARIO_ID, String.class,
						StringUtils.EMPTY).right());
		jxServiceRequest.setValue("funcionario/nemotecnico", "");
		jxServiceRequest.setValue(
				"funcionario/nombre",
				puntoInteraccionProperties.valueOf(
						IPostalInteraccion.SVC_FUNCIONARIO_NOMBRE,
						String.class, StringUtils.EMPTY).right());
		jxServiceRequest.setValue("funcionario/apellido", "");
		jxServiceRequest.setValue("funcionarioAsignado/cargo", "");
		jxServiceRequest.setValue("funcionarioAsignado/codigo", "");
		jxServiceRequest.setValue("funcionarioAsignado/id", "");
		jxServiceRequest.setValue("funcionarioAsignado/nemotecnico", "");
		jxServiceRequest.setValue("funcionarioAsignado/nombre", "");
		jxServiceRequest.setValue("funcionarioAsignado/apellido", "");
		jxServiceRequest.setValue(
				"medioDeEnvio/codigo",
				puntoInteraccionProperties.valueOf(
						IPostalInteraccion.SVC_MEDIODEENVIO_CODIGO, Long.class)
						.right()); // 12L
		jxServiceRequest.setValue("medioDeEnvio/id", puntoInteraccionProperties
				.valueOf(IPostalInteraccion.SVC_MEDIODEENVIO_ID, Long.class)
				.right()); // 418770956L
		jxServiceRequest.setValue(
				"medioDeEnvio/nombre",
				puntoInteraccionProperties.valueOf(
						IPostalInteraccion.SVC_MEDIODEENVIO_NOMBRE,
						String.class).right()); // "EXPEDIENTE DIGITAL WEB"
		jxServiceRequest.setValue("multa", 0L);
		jxServiceRequest.setValue("numero", null);
		jxServiceRequest.setValue("radicacionAnteriorNumero", radicacionAnteriorNumero);
		jxServiceRequest.setValue("referenciaExterna", correlationId.right());
		jxServiceRequest.setValue("terminoDias", terminoDias);
		jxServiceRequest.setValue("terminoFecha", terminoDateAsCalendar);
		jxServiceRequest.setValue("tipo", puntoInteraccionProperties.valueOf(IPostalInteraccion.SVC_TIPO, String.class).right());// "Entrada" );
		jxServiceRequest.setValue("tipoCuaderno", null);
		jxServiceRequest.setValue("tipoDocumental/codigo", 
				puntoInteraccionProperties.valueOf(IPostalInteraccion.SVC_TIPODOCUMENTAL_CODIGO, String.class).right()); // "ENTRADA"
		jxServiceRequest.setValue("tipoDocumental/id",
				puntoInteraccionProperties.valueOf(IPostalInteraccion.SVC_TIPODOCUMENTAL_ID, Long.class).right()); // 8081L
		jxServiceRequest.setValue("tipoDocumental/nombre",
				puntoInteraccionProperties.valueOf(IPostalInteraccion.SVC_TIPODOCUMENTAL_NOMBRE,String.class).right()); // "ENTRADA"
		jxServiceRequest.setValue("tipoDocumentalConsecutivo", null);

		jxServiceRequest.setValue("tipoSeguridad/codigo",
				puntoInteraccionProperties.valueOf(IPostalInteraccion.SVC_TIPOSEGURIDAD_CODIGO, String.class).right()); // VIP
		jxServiceRequest.setValue("tipoSeguridad/id",
				puntoInteraccionProperties.valueOf(IPostalInteraccion.SVC_TIPOSEGURIDAD_ID, Long.class).right()); // 187392666L
		jxServiceRequest.setValue("tipoSeguridad/nombre",
				puntoInteraccionProperties.valueOf(IPostalInteraccion.SVC_TIPOSEGURIDAD_NOMBRE, String.class).right()); // VIP
		
		jxServiceRequest.setValue("tramite/codigo",valueOf(jxInfoSource, "tramite_obj/idPostal", Long.class)); 
		jxServiceRequest.setValue("tramite/nombre",valueOf(jxInfoSource, "tramite_obj/nombreTramite", String.class));
		jxServiceRequest.setValue("tramite/id",valueOf(jxInfoSource, "tramite_obj/codigo", Long.class));
		
		jxServiceRequest.setValue("tramite/proceso/codigo",puntoInteraccionProperties.valueOf(IPostalInteraccion.SVC_TRAMITE_PROCESO_CODIGO__NEAR,Long.class).right());// 16L
		jxServiceRequest.setValue("tramite/proceso/nombre",puntoInteraccionProperties.valueOf(IPostalInteraccion.SVC_TRAMITE_NOMBRE__NEAR,Long.class).right());// 16L
		jxServiceRequest.setValue("tramite/proceso/id",puntoInteraccionProperties.valueOf(IPostalInteraccion.SVC_TRAMITE_PROCESO_ID__NEAR,Long.class).right());// 16L

		jxServiceRequest.setValue("tramite/termino/dias", terminoDias);
		jxServiceRequest.setValue("tramite/termino/esModificable", false);

		ArrayOfKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsIKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsI element = new ArrayOfKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsIKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsI();
		element.setKey("1");
		element.setValue(new DocumentoTipoSeguridad());
		element.getValue().setCodigo(puntoInteraccionProperties.valueOf(
						IPostalInteraccion.SVC_TIPOSEGURIDAD_CODIGO, String.class).right()); // "ABIERTA"
		element.getValue().setId(puntoInteraccionProperties.valueOf(
						IPostalInteraccion.SVC_TIPOSEGURIDAD_ID, Long.class).right()); // 186736211L
		element.getValue().setNombre(puntoInteraccionProperties.valueOf(
						IPostalInteraccion.SVC_TIPOSEGURIDAD_NOMBRE, String.class).right()); // "ABIERTA"

		// jxServiceRequest.setValue("tramite/tipoSeguridad/key", "1");
		// jxServiceRequest.setValue("tramite/tipoSeguridad/value/codigo",
		// "ABIERTA" );
		// jxServiceRequest.setValue("tramite/tipoSeguridad/value/id",
		// 186736211L );
		// jxServiceRequest.setValue("tramite/tipoSeguridad/value/nombre","ABIERTA"
		// );

		jxServiceRequest.setValue("tramite/tipoSeguridad",
						new ArrayOfKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsIKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsI[] { element });

		jxServiceRequest.setValue("usuario", _usuario.right()); // "APLICACIONES"
		
		Gson gson = new Gson();
		String strServiceRequest = gson.toJson(serviceRequest);
		logger.debug("*******\n"+strServiceRequest);

		return Either.right(serviceRequest);
//		return null; //Usado para pruebas
	}

	// solicitud otros procesos
	Either<Exception, Radicacion> mapRequestRegimen(
			Map<String, Object> payloadSource) {

		String puntoInteraccion = (String) payloadSource.get(IPostalInteraccion.PARAM_PUNTOINTERACCION);
		Long adjuntos_Count = (Long) payloadSource.get(IPostalInteraccion.PARAM_COUNT_ADJUNTOS);
		// datos de la estructura
		RegimenInsolvencia infoSource = (RegimenInsolvencia) payloadSource.get(IPostalInteraccion.PARAM_SOLICITUD);
		
		logger.info("INFO RI -> POSTAL " + gson.toJson(infoSource));

		String dependenciaNombre = Objects.toString(ParametrosInicio
				.getProperty("postal.puntointeraccion.radicacion_radicar.dependencia.nombre"), ""); //"GRUPO DE ADMISIONES";
		Long dependenciaId = Long.parseLong(ParametrosInicio
				.getProperty("postal.puntointeraccion.radicacion_radicar.dependencia.id")); //"460";
		String corresponsalDependenciaNombre = Objects.toString(ParametrosInicio
				.getProperty("postal.puntointeraccion.radicacion_radicar.corresponsal.dependencia.nombre"), ""); //"GRUPO DE GESTION DOCUMENTAL";
		Long corresponsalDependenciaId = Long.parseLong(ParametrosInicio
				.getProperty("postal.puntointeraccion.radicacion_radicar.corresponsal.dependencia.id")); //"547;
		

		// TODO cambiar de orden el flujo para hacer el reparto de intendencias
		// primero y eliminar esta consulta
		try {
		
			// obteniendo la categoria
			String categoria = null;
			if (!Constantes.TIPO_SOLICITANTE_PNNC.equals(infoSource
					.getTipo_de_solicitante())) {
				categoria = CalculoCategoriaTamanoServicio.getInstance()
						.calcularCatEmpresa(
								infoSource
										.getInfo_financiera_anual()
										.getActivos_ano_anterior(), infoSource.getTipo_solicitud());
			}

			// obteniendo al deudor
			Deudor deudor = infoSource.getDeudor();
			// obteniendo codigo dane del departamento
			Integer codDane = deudor.getDepartamento().getCodigo_departamento();

			// obtener reparto de intendencias
			String sql = "";

			// validar categoria y codigo dane
			if (categoria != null && !categoria.equals("A")) {
				sql = "SELECT * FROM $REPARTO DE INTENDENCIAS$  WHERE $REPARTO DE INTENDENCIAS.CATEGORIA$ = $S("
						+ categoria
						+ ")$ AND $REPARTO DE INTENDENCIAS.CODIGO DANE DEPARTAMENTO$ = $I("
						+ codDane + ")$";
			} else if (categoria != null && categoria.equals("A")) {
				codDane = 11;// bogota
				sql = "SELECT * FROM $REPARTO DE INTENDENCIAS$  WHERE $REPARTO DE INTENDENCIAS.CATEGORIA$ = $S("
						+ categoria
						+ ")$ AND $REPARTO DE INTENDENCIAS.CODIGO DANE DEPARTAMENTO$ = $I("
						+ codDane + ")$";
			} else {
				sql = "SELECT * FROM $REPARTO PNNC$  WHERE $REPARTO PNNC.CODIGO DANE DEPARTAMENTO$ = $I("
						+ codDane + ")$";
			}

			RepartoIntendencias reparto = DS_SqlUtils.queryForObject(
					RepartoIntendencias.class, sql);
			dependenciaNombre = reparto.getDependencia();
			dependenciaId = Long.parseLong(reparto.getCodigo_dependencia());

		} catch (Exception e) {
			SimpleLogger.setError("Error en el reparto de intendencias:", e);

		}
		// fin del TODO

		String radicacionAnteriorNumero = getOrElse(payloadSource,
				IPostalInteraccion.PARAM_RADICACIONANTERIORNUMERO, null,
				String.class);
		Integer idCarga = getOrElse(payloadSource,
				IPostalInteraccion.PARAM_CORRELATION_ID1, null, Integer.class);
		String numProceso = Objects.toString(infoSource.getNumero_proceso(), "");

		Either<Exception, String> _usuario = $(IPostalInteraccion.CONFIGPARAM__INTEGRACION_POSTAL_USUARIO);

		if (_usuario.isLeft()) {
			return Either.left(_usuario.left());
		}

		Radicacion serviceRequest = new Radicacion();

		IPostalConfig puntoInteraccionProperties = IPostalConfig.getInstance(puntoInteraccion);

		if (puntoInteraccionProperties.isLeft()) {
			return Either.left(puntoInteraccionProperties.left());
		}

		Integer terminoDias = infoSource.getTramite().getTermino_dias();

		String tramiteProceso = null;
		if (infoSource.getProcesos_clases().getId_vista()
				.equals(Constantes.ID_PROCESOS_CLASES_LIQ_SIMPLIFICADA.toString())) {// Liquidacion simplificada 26
			tramiteProceso = puntoInteraccionProperties.valueOf(
					IPostalInteraccion.SVC_TRAMITE_PROCESO_CODIGO__LSIMPLIFICADA, String.class).right();
		} else if (infoSource.getProcesos_clases().getId_vista()
				.equals(Constantes.ID_PROCESOS_CLASES_REO_ABREVIADA.toString())) {// Reorganizacion abreviada 23
			tramiteProceso = puntoInteraccionProperties.valueOf(
					IPostalInteraccion.SVC_TRAMITE_PROCESO_CODIGO__RABREVIADA, String.class).right();
		} else if (infoSource.getProcesos_clases().getId_vista()
				.equals(Constantes.ID_PROCESOS_CLASES_VAL_JUDICIAL.toString())) {
			tramiteProceso = puntoInteraccionProperties.valueOf(
					IPostalInteraccion.SVC_TRAMITE_PROCESO_CODIGO__RABREVIADA, String.class).right();
		}

		Either<Exception, String> referenciaExterna = obtenerReferenciaExterna(idCarga, numProceso);
		if (referenciaExterna.isLeft()) {
			return Either.left(referenciaExterna.left());
		}

		Date currentDate = new java.util.Date();
		Calendar currentDateAsCalendar = CalendarUtils
				.dateAsCalendar(currentDate);
		Calendar terminoDateAsCalendar = CalendarUtils.calendarAddDays(
				currentDateAsCalendar, terminoDias);
		;

		// Contexto Local
		JXPathContext jxInfoSource = JXPathContext.newContext(infoSource);
		jxInfoSource.setLenient(true);

		// Contexto Remoto

		JXPathContext jxServiceRequest = JXPathContext
				.newContext(serviceRequest);
		jxServiceRequest.setFactory(new JxPostalRequestFactory());
		jxServiceRequest.createPath("aplicaA/ciudad/codigo");
		jxServiceRequest.createPath("aplicaA/tipoIdentificacion");
		jxServiceRequest.createPath("corresponsal/dependencia");
		jxServiceRequest.createPath("corresponsal/particular/ciudad/codigo");
		jxServiceRequest.createPath("corresponsal/particular/tipoIdentificacion");
		jxServiceRequest.createPath("dependencia");
		jxServiceRequest.createPath("dependenciaAsignada");
		jxServiceRequest.createPath("funcionario");
		jxServiceRequest.createPath("funcionarioAsignado");
		jxServiceRequest.createPath("medioDeEnvio");
		jxServiceRequest.createPath("tipoDocumental");
		jxServiceRequest.createPath("tipoSeguridad");
		jxServiceRequest.createPath("tramite/termino");
		jxServiceRequest.createPath("tramite/proceso");
		jxServiceRequest.createPath("documentosAnexos");
		jxServiceRequest.createPath("tipoCuaderno");
		// jxServiceRequest.createPath("tramite/tipoSeguridad");

		jxServiceRequest.setValue("anexosFisicos", "Anexos");
		
		jxServiceRequest.setValue("aplicaA/ciudad/codigo/ciudadCodigo", valueOf(jxInfoSource, "deudor/municipio/codigoMunicipioPostal", Integer.class));
		jxServiceRequest.setValue("aplicaA/ciudad/codigo/departamentoCodigo",  valueOf(jxInfoSource, "deudor/departamento/codigo_departamento", Integer.class));
		jxServiceRequest.setValue("aplicaA/ciudad/codigo/paisCodigo", valueOf(jxInfoSource, "deudor/pais_dane/codigo_ssoc", Integer.class));
		jxServiceRequest.setValue("aplicaA/ciudad/departamento", valueOf(jxInfoSource, "deudor/departamento/nombre_departamento", String.class));
		jxServiceRequest.setValue("aplicaA/ciudad/nombre", valueOf(jxInfoSource, "deudor/municipio/nombre_ciudad", String.class));
		jxServiceRequest.setValue("aplicaA/ciudad/pais", valueOf(jxInfoSource, "deudor/pais_dane/codigoMunicipioPostal", String.class));
		jxServiceRequest.setValue("aplicaA/direccion", valueOf(jxInfoSource, "deudor/direccion", String.class));
		jxServiceRequest.setValue("aplicaA/email", valueOf(jxInfoSource, "deudor/correo_electronico", String.class));
		jxServiceRequest.setValue("aplicaA/identificacion", valueOf(jxInfoSource, "deudor/numero_identificacion", String.class)); // "999999999"
		jxServiceRequest.setValue("aplicaA/nombre", valueOf(jxInfoSource, "deudor/razon_social", String.class)); // "Sociedad de Pruebas");
		jxServiceRequest.setValue("aplicaA/telefono", valueOf(jxInfoSource, "deudor/telefono", String.class)); // ""

		TipoDeDocumento tipoDocIdentif = MAPA_TIPOS_DOCUMENTO_POSTAL.get(valueOf(jxInfoSource, "deudor/tipo_identificacion_id", String.class));
		SimpleLogger.setDebug("Consultando tipo documento "
				+ valueOf(jxInfoSource, "deudor/tipo_identificacion_id", String.class)
				+ " Envia: " + tipoDocIdentif.getId_postal());
		jxServiceRequest.setValue("aplicaA/tipoIdentificacion/id", tipoDocIdentif.getCodigo_numerico_postal());
		jxServiceRequest.setValue("aplicaA/tipoIdentificacion/nombre", tipoDocIdentif.getNombre_postal());

		jxServiceRequest.setValue("corresponsal/dependencia/id", corresponsalDependenciaId);
		jxServiceRequest.setValue("corresponsal/dependencia/nombre", corresponsalDependenciaNombre);
		
		jxServiceRequest.setValue("corresponsal/particular/ciudad/codigo/ciudadCodigo", valueOf(jxInfoSource, "deudor/municipio/codigoMunicipioPostal", Integer.class));
		jxServiceRequest.setValue("corresponsal/particular/ciudad/codigo/departamentoCodigo", valueOf(jxInfoSource, "deudor/departamento/codigo_departamento", Integer.class));
		jxServiceRequest.setValue("corresponsal/particular/ciudad/codigo/paisCodigo", valueOf(jxInfoSource, "deudor/pais_dane/codigo_ssoc", Integer.class));
		jxServiceRequest.setValue("corresponsal/particular/ciudad/departamento", valueOf(jxInfoSource, "deudor/departamento/nombre_departamento", String.class));
		jxServiceRequest.setValue("corresponsal/particular/ciudad/nombre", valueOf(jxInfoSource, "deudor/municipio/nombre_ciudad", String.class));
		jxServiceRequest.setValue("corresponsal/particular/ciudad/pais", valueOf(jxInfoSource, "deudor/pais_dane/nombre_ssoc", String.class));
		jxServiceRequest.setValue("corresponsal/particular/telefono", valueOf(jxInfoSource, "deudor/telefono", String.class)); // "3112234277");
		jxServiceRequest.setValue("corresponsal/particular/direccion", Objects.toString(valueOf(jxInfoSource, "deudor/direccion", String.class), "")); // "Cra 11 No. 61 - 44 Apto 302"
		jxServiceRequest.setValue("corresponsal/particular/email",valueOf(jxInfoSource, "deudor/correo_electronico", String.class)); // "juan_pabloparedes@yahoo.com"
		
		// Si solicitante es sociedad o Sucursal extranjera lleva la info del representante legal
		if(null != infoSource.getTipo_solicitante() 
				&& (infoSource.getTipo_solicitante().equals(Constantes.TIPO_SOLICITUD_NEAR)
						|| infoSource.getTipo_solicitante().equals(Constantes.TIPO_SOLICITANTE_SUCURSALES_EXTRANJERAS))){
			
			if(null != infoSource.getDeudor().getId_representante_legal()){
				Persona datosRepLegal = PersonaServicio.getInstance().obtenerPersona(
						infoSource.getDeudor().getId_representante_legal());
				
				jxServiceRequest.setValue("corresponsal/particular/identificacion", datosRepLegal.getIdentificacion());
				jxServiceRequest.setValue("corresponsal/particular/nombre", datosRepLegal.getNombreCompleto());

				TipoDeDocumento tipoDocumento = DS_SqlUtils.queryForObject(TipoDeDocumento.class, 
						"select * from $tipo de documento$ where $tipo de documento.codigo$ = $I(" + datosRepLegal.getTipo_documento() + ")$");
				
				jxServiceRequest.setValue("corresponsal/particular/tipoIdentificacion/id", tipoDocumento.getCodigo_numerico_postal());
				jxServiceRequest.setValue("corresponsal/particular/tipoIdentificacion/nombre", tipoDocumento.getNombre_postal());


			} else {
				jxServiceRequest.setValue("corresponsal/particular/identificacion", "");
				jxServiceRequest.setValue("corresponsal/particular/nombre", "");
				jxServiceRequest.setValue("corresponsal/particular/tipoIdentificacion/id", "");
				jxServiceRequest.setValue("corresponsal/particular/tipoIdentificacion/nombre", "");
			}
			
		} else if (null != infoSource.getTipo_solicitante() 
				&& (infoSource.getTipo_solicitante().equals(Constantes.TIPO_SOLICITANTE_PNC)
						|| infoSource.getTipo_solicitante().equals(Constantes.TIPO_SOLICITANTE_PNNC))) {
			jxServiceRequest.setValue("corresponsal/particular/identificacion", valueOf(jxInfoSource, "deudor/numero_identificacion", String.class));
			jxServiceRequest.setValue("corresponsal/particular/nombre", valueOf(jxInfoSource, "deudor/razon_social", String.class));
			jxServiceRequest.setValue("corresponsal/particular/tipoIdentificacion/id", tipoDocIdentif.getCodigo_numerico_postal());
			jxServiceRequest.setValue("corresponsal/particular/tipoIdentificacion/nombre", tipoDocIdentif.getNombre_postal());
			
		} else {
			jxServiceRequest.setValue("corresponsal/particular/identificacion", "");
			jxServiceRequest.setValue("corresponsal/particular/nombre", "");
			jxServiceRequest.setValue("corresponsal/particular/tipoIdentificacion/id", "");
			jxServiceRequest.setValue("corresponsal/particular/tipoIdentificacion/nombre", "");
		}
		
		jxServiceRequest.setValue("corresponsal/tipo", "Particular");
		
		jxServiceRequest.setValue("dependencia/id", dependenciaId);
		jxServiceRequest.setValue("dependencia/nombre",dependenciaNombre);
		jxServiceRequest.setValue("dependenciaAsignada/id", dependenciaId);
		jxServiceRequest.setValue("dependenciaAsignada/nombre", dependenciaNombre);
		jxServiceRequest.setValue("documentoPrincipal", "");
		jxServiceRequest.setValue("documentosAnexos", null);
		jxServiceRequest.setValue("entregaFisica", false);
		jxServiceRequest.setValue("estado", "OFICIAL");
		jxServiceRequest.setValue("fecha", currentDateAsCalendar);
		jxServiceRequest.setValue("folios", adjuntos_Count);
		jxServiceRequest.setValue("funcionario/cargo", "");
		jxServiceRequest.setValue("funcionario/codigo", "");
		jxServiceRequest.setValue("funcionario/id",
				puntoInteraccionProperties.valueOf(IPostalInteraccion.SVC_FUNCIONARIO_ID, String.class, StringUtils.EMPTY).right());
		jxServiceRequest.setValue("funcionario/nemotecnico", "");
		jxServiceRequest.setValue("funcionario/nombre", 
				puntoInteraccionProperties.valueOf(IPostalInteraccion.SVC_FUNCIONARIO_NOMBRE, String.class, StringUtils.EMPTY).right());
		jxServiceRequest.setValue("funcionario/apellido", "");
		jxServiceRequest.setValue("funcionarioAsignado/cargo", "");
		jxServiceRequest.setValue("funcionarioAsignado/codigo", "");
		jxServiceRequest.setValue("funcionarioAsignado/id", "");
		jxServiceRequest.setValue("funcionarioAsignado/nemotecnico", "");
		jxServiceRequest.setValue("funcionarioAsignado/nombre", "");
		jxServiceRequest.setValue("funcionarioAsignado/apellido", "");
		
		jxServiceRequest.setValue("medioDeEnvio/codigo",
				valueOf(jxInfoSource, "medio_envio/codigo", Long.class)); // 12L
		jxServiceRequest.setValue("medioDeEnvio/nombre",
				valueOf(jxInfoSource, "medio_envio/medio_envio", String.class)); // "EXPEDIENTE DIGITAL WEB"
		jxServiceRequest.setValue("medioDeEnvio/id", puntoInteraccionProperties
				.valueOf(IPostalInteraccion.SVC_MEDIODEENVIO_ID, Long.class).right()); // 418770956L
		jxServiceRequest.setValue("multa", BigDecimal.ZERO);
		jxServiceRequest.setValue("numero", null);
		jxServiceRequest.setValue("radicacionAnteriorNumero", radicacionAnteriorNumero);
		jxServiceRequest.setValue("referenciaExterna", referenciaExterna.right());
		jxServiceRequest.setValue("terminoDias", terminoDias);
		jxServiceRequest.setValue("terminoFecha", terminoDateAsCalendar);
		jxServiceRequest.setValue("tipo",
				puntoInteraccionProperties.valueOf(IPostalInteraccion.SVC_TIPO, String.class).right());// "Entrada" );
		jxServiceRequest.setValue("tipoCuaderno/codigo",
				valueOf(jxInfoSource, "cuaderno/codigo", String.class));
		jxServiceRequest.setValue("tipoCuaderno/nombre",
				valueOf(jxInfoSource, "cuaderno/cuadernoTipo", String.class));
		jxServiceRequest.setValue("tipoCuaderno/id",
				valueOf(jxInfoSource, "cuaderno/id_vista", Long.class));
		jxServiceRequest.setValue(
				"tipoDocumental/codigo",
				puntoInteraccionProperties.valueOf(
						IPostalInteraccion.SVC_TIPODOCUMENTAL_CODIGO,
						String.class).right()); // "ENTRADA"
		jxServiceRequest.setValue(
				"tipoDocumental/id",
				puntoInteraccionProperties.valueOf(
						IPostalInteraccion.SVC_TIPODOCUMENTAL_ID, Long.class)
						.right()); // 8081L
		jxServiceRequest.setValue(
				"tipoDocumental/nombre",
				puntoInteraccionProperties.valueOf(
						IPostalInteraccion.SVC_TIPODOCUMENTAL_NOMBRE,
						String.class).right()); // "ENTRADA"
		jxServiceRequest.setValue("tipoDocumentalConsecutivo", null);

		jxServiceRequest.setValue(
				"tipoSeguridad/codigo",
				valueOf(jxInfoSource, "tipo_seguridad/codigoAlfanumerico",
						String.class)); // VIP
		// jxServiceRequest.setValue("tipoSeguridad/id", valueOf(jxInfoSource,
		// "tipo_seguridad/id_vista", Long.class) ); // 187392666L
		jxServiceRequest.setValue(
				"tipoSeguridad/nombre",
				valueOf(jxInfoSource, "tipo_seguridad/seguridadTipo",
						String.class)); // VIP

		jxServiceRequest.setValue(
				"tipoSeguridad/id",
				valueOf(jxInfoSource, "tipo_seguridad/id_tipo_seguridad", Long.class)); // 187392666L


		jxServiceRequest.setValue("tramite/codigo",valueOf(jxInfoSource, "tramite/idPostal", Long.class)); 
		jxServiceRequest.setValue("tramite/nombre",valueOf(jxInfoSource, "tramite/nombreTramite", String.class));
		jxServiceRequest.setValue("tramite/id",valueOf(jxInfoSource, "tramite/codigo", Long.class)); 

		jxServiceRequest.setValue("tramite/proceso/codigo", tramiteProceso);
		jxServiceRequest.setValue("tramite/termino/dias", terminoDias);
		jxServiceRequest.setValue("tramite/termino/esModificable", false);

		ArrayOfKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsIKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsI element = new ArrayOfKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsIKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsI();
		element.setKey("1");
		element.setValue(new DocumentoTipoSeguridad());
		element.getValue().setCodigo(valueOf(jxInfoSource, "tipo_seguridad/codigoAlfanumerico", String.class));
		element.getValue().setNombre(valueOf(jxInfoSource, "tipo_seguridad/seguridadTipo", String.class));
		element.getValue().setId(valueOf(jxInfoSource, "tipo_seguridad/id_tipo_seguridad", Long.class));

		jxServiceRequest
				.setValue(
						"tramite/tipoSeguridad",
						new ArrayOfKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsIKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsI[] { element });

		jxServiceRequest.setValue("usuario", _usuario.right()); // "APLICACIONES"
		
		Gson gson = new Gson();
		String strServiceRequest = gson.toJson(serviceRequest);
		logger.debug("*******\n"+strServiceRequest);

		return Either.right(serviceRequest);
//		return null; // Usado para pruebas
	}
	
	// respuesta requerimiento
	Either<Exception, Radicacion> mapRequestRtaRequerimiento(
			Map<String, Object> payloadSource) {

//		String puntoInteraccion = "postal.puntointeraccion.respuesta_requerimiento";
		String puntoInteraccion = (String) payloadSource.get(IPostalInteraccion.PARAM_PUNTOINTERACCION);
		Long adjuntos_Count = (Long) payloadSource
				.get(IPostalInteraccion.PARAM_COUNT_ADJUNTOS);
		RespuestaRequerimiento infoSource = (RespuestaRequerimiento) payloadSource
				.get(IPostalInteraccion.PARAM_SOLICITUD);
		
		Gson gson = new Gson();
		logger.debug("INFO RTA RTO FUENTE:" +  gson.toJson(infoSource));

		Integer idCarga = getOrElse(payloadSource,
				IPostalInteraccion.PARAM_CORRELATION_ID1, null, Integer.class);
		
		String numProceso = Objects.toString(infoSource.getNumero_proceso(), "");
		
		Either<Exception, String> _usuario = $(IPostalInteraccion.CONFIGPARAM__INTEGRACION_POSTAL_USUARIO);

		if (_usuario.isLeft()) {
			return Either.left(_usuario.left());
		}

		Radicacion serviceRequest = new Radicacion();

		IPostalConfig puntoInteraccionProperties = IPostalConfig
				.getInstance(puntoInteraccion);

		if (puntoInteraccionProperties.isLeft()) {
			return Either.left(puntoInteraccionProperties.left());
		}

		Integer terminoDias = infoSource.getTramite_obj().getTermino_dias();

		Date currentDate = new java.util.Date();
		Calendar currentDateAsCalendar = CalendarUtils
				.dateAsCalendar(currentDate);
		Calendar terminoDateAsCalendar = CalendarUtils.calendarAddDays(
				currentDateAsCalendar, terminoDias);
		
		Either<Exception, String> referenciaExterna = obtenerReferenciaExterna(idCarga, numProceso);
		if (referenciaExterna.isLeft()) {
			return Either.left(referenciaExterna.left());
		}
		;

		// Contexto Local
		JXPathContext jxInfoSource = JXPathContext.newContext(infoSource);
		jxInfoSource.setLenient(true);
		
		RespuestaRequerimiento infoPadre = (RespuestaRequerimiento) payloadSource
				.get(IPostalInteraccion.PARAM_SOLICITUD_PADRE);

		// Contexto Remoto
		JXPathContext jxServiceRequest = JXPathContext
				.newContext(serviceRequest);
		jxServiceRequest.setFactory(new JxPostalRequestFactory());
		jxServiceRequest.createPath("aplicaA/ciudad/codigo");
		jxServiceRequest.createPath("aplicaA/tipoIdentificacion");
		
		jxServiceRequest.createPath("corresponsal/dependencia");
		jxServiceRequest.createPath("corresponsal/particular/ciudad/codigo");
		jxServiceRequest.createPath("corresponsal/particular/tipoIdentificacion");
		
		jxServiceRequest.createPath("dependencia");
		jxServiceRequest.createPath("dependenciaAsignada");
		jxServiceRequest.createPath("funcionario");
		jxServiceRequest.createPath("funcionarioAsignado");
		jxServiceRequest.createPath("medioDeEnvio");
		jxServiceRequest.createPath("tipoDocumental");
		jxServiceRequest.createPath("tipoSeguridad");
		jxServiceRequest.createPath("tramite/termino");
		jxServiceRequest.createPath("tramite/proceso");
		jxServiceRequest.createPath("tipoCuaderno");
		jxServiceRequest.createPath("documentosAnexos");
		jxServiceRequest.createPath("tramite/tipoSeguridad");

		jxServiceRequest.setValue("anexosFisicos", "Anexos");
		
		jxServiceRequest.setValue("aplicaA/ciudad/codigo/ciudadCodigo", valueOf(jxInfoSource, "deudor_obj/municipio/codigoMunicipioPostal", Integer.class));
		jxServiceRequest.setValue("aplicaA/ciudad/codigo/departamentoCodigo",  valueOf(jxInfoSource, "deudor_obj/departamento/codigo_departamento", Integer.class));
		jxServiceRequest.setValue("aplicaA/ciudad/codigo/paisCodigo", valueOf(jxInfoSource, "deudor_obj/pais_dane/codigo_ssoc", Integer.class));
		jxServiceRequest.setValue("aplicaA/ciudad/departamento", valueOf(jxInfoSource, "deudor_obj/departamento/nombre_departamento", String.class));
		jxServiceRequest.setValue("aplicaA/ciudad/nombre", valueOf(jxInfoSource, "deudor_obj/municipio/nombre_ciudad", String.class));
		jxServiceRequest.setValue("aplicaA/ciudad/pais", valueOf(jxInfoSource, "deudor_obj/pais_dane/nombre", String.class));
		jxServiceRequest.setValue("aplicaA/direccion", valueOf(jxInfoSource, "deudor_obj/direccion", String.class));
		jxServiceRequest.setValue("aplicaA/email", valueOf(jxInfoSource, "deudor_obj/correo_electronico", String.class));
		jxServiceRequest.setValue("aplicaA/identificacion", valueOf(jxInfoSource, "deudor_obj/numero_identificacion", String.class)); // "999999999"
		jxServiceRequest.setValue("aplicaA/nombre", valueOf(jxInfoSource, "deudor_obj/razon_social", String.class)); // "Sociedad de Pruebas");
		jxServiceRequest.setValue("aplicaA/telefono", valueOf(jxInfoSource, "deudor_obj/telefono", String.class)); // ""
		
		jxServiceRequest.setValue("aplicaA/tipoIdentificacion/id", valueOf(jxInfoSource, "deudor_obj/tipo_identificacion/codigo_numerico_postal", Integer.class));
		jxServiceRequest.setValue("aplicaA/tipoIdentificacion/nombre", valueOf(jxInfoSource, "deudor_obj/tipo_identificacion/nombre_postal", String.class));
		
		// INFORMACIÓN DE CORRESPONSAL (PARTICULAR)
		String corresponsalDependenciaNombre = Objects.toString(ParametrosInicio
				.getProperty("postal.puntointeraccion.radicacion_radicar.corresponsal.dependencia.nombre"), ""); //"GRUPO DE GESTION DOCUMENTAL";
		Long corresponsalDependenciaId = Long.parseLong(ParametrosInicio
				.getProperty("postal.puntointeraccion.radicacion_radicar.corresponsal.dependencia.id")); //"547;
		
		jxServiceRequest.setValue("corresponsal/dependencia/id", corresponsalDependenciaId); 
		jxServiceRequest.setValue("corresponsal/dependencia/nombre", corresponsalDependenciaNombre);
		jxServiceRequest.setValue("corresponsal/particular/ciudad/codigo/ciudadCodigo", 
				valueOf(jxInfoSource, "deudor_obj/municipio/codigoMunicipioPostal", Integer.class));
		jxServiceRequest.setValue("corresponsal/particular/ciudad/codigo/departamentoCodigo",
				valueOf(jxInfoSource, "deudor_obj/departamento/codigo_departamento", Integer.class));
		jxServiceRequest.setValue("corresponsal/particular/ciudad/codigo/paisCodigo", 
				valueOf(jxInfoSource, "deudor_obj/pais_dane/codigo_ssoc", Integer.class));
		jxServiceRequest.setValue("corresponsal/particular/ciudad/departamento", 
				valueOf(jxInfoSource, "deudor_obj/departamento/nombre_departamento", String.class));
		jxServiceRequest.setValue("corresponsal/particular/ciudad/nombre",
				valueOf(jxInfoSource, "deudor_obj/municipio/nombre_ciudad", String.class));
		jxServiceRequest.setValue("corresponsal/particular/ciudad/pais",
				valueOf(jxInfoSource, "deudor_obj/pais_dane/nombre", String.class));		
		jxServiceRequest.setValue("corresponsal/particular/direccion", 
				valueOf(jxInfoSource, "deudor_obj/direccion", String.class));
		jxServiceRequest.setValue("corresponsal/particular/email",
				valueOf(jxInfoSource, "deudor_obj/correo_electronico", String.class));
		jxServiceRequest.setValue("corresponsal/particular/telefono", 
				valueOf(jxInfoSource, "deudor_obj/telefono", String.class));
		
				
		// Si solicitante es sociedad o Sucursal extranjera lleva la info del representante legal
		if(null != infoSource.getTipo_solicitante() 
				&& (infoSource.getTipo_solicitante_obj().getId().equals(Constantes.TIPO_SOLICITUD_NEAR)
						|| infoSource.getTipo_solicitante_obj().getId().equals(Constantes.TIPO_SOLICITANTE_SUCURSALES_EXTRANJERAS))){
			jxServiceRequest.setValue("corresponsal/particular/identificacion", infoPadre.getDeudor_obj().getRepresentante_legal().getIdentificacion());
//					valueOf(jxInfoSource, "deudor_obj/representante_legal/identificacion", String.class));
			jxServiceRequest.setValue("corresponsal/particular/nombre", infoPadre.getDeudor_obj().getRepresentante_legal().getNombre_completo());
//					valueOf(jxInfoSource, "deudor_obj/representante_legal/nombre_completo", String.class));
			
			TipoDeDocumento tipoDeDocumento = MAPA_TIPOS_DOCUMENTO_POSTAL.get(infoPadre.getDeudor_obj().getRepresentante_legal().getTipo_identificacion());
			jxServiceRequest.setValue("corresponsal/particular/tipoIdentificacion/id", tipoDeDocumento.getCodigo_numerico_postal());
			jxServiceRequest.setValue("corresponsal/particular/tipoIdentificacion/nombre", tipoDeDocumento.getNombre_postal());
			
		} else if (null != infoSource.getTipo_solicitante() 
				&& (infoSource.getTipo_solicitante_obj().getId().equals(Constantes.TIPO_SOLICITANTE_PNC)
						|| infoSource.getTipo_solicitante_obj().getId().equals(Constantes.TIPO_SOLICITANTE_PNNC))) {
			jxServiceRequest.setValue("corresponsal/particular/identificacion", valueOf(jxInfoSource, "deudor_obj/numero_identificacion", String.class));
			jxServiceRequest.setValue("corresponsal/particular/nombre", valueOf(jxInfoSource, "deudor_obj/razon_social", String.class));
			jxServiceRequest.setValue("corresponsal/particular/tipoIdentificacion/id", valueOf(jxInfoSource, "deudor_obj/tipo_identificacion/codigo_numerico_postal", Integer.class));
			jxServiceRequest.setValue("corresponsal/particular/tipoIdentificacion/nombre", valueOf(jxInfoSource, "deudor_obj/tipo_identificacion/nombre_postal", String.class));
			
		}
		
		jxServiceRequest.setValue("corresponsal/tipo", "Particular");
			
		jxServiceRequest.setValue("dependencia/id", valueOf(jxInfoSource, "dependencia_obj/codigo", Long.class)); // 511L
		jxServiceRequest.setValue("dependencia/nombre", valueOf(jxInfoSource, "dependencia_obj/nombreDependencia", String.class));
		jxServiceRequest.setValue("dependenciaAsignada/id", valueOf(jxInfoSource, "dependencia_obj/codigo", Long.class)); // 511L
		jxServiceRequest.setValue("dependenciaAsignada/nombre", valueOf(jxInfoSource, "dependencia_obj/nombreDependencia", String.class));
		jxServiceRequest.setValue("documentoPrincipal", ".PDF");
		jxServiceRequest.setValue("documentosAnexos", null);
		jxServiceRequest.setValue("entregaFisica", false);
		jxServiceRequest.setValue("estado", "OFICIAL");
		jxServiceRequest.setValue("fecha", currentDateAsCalendar);
		jxServiceRequest.setValue("folios", adjuntos_Count);
		jxServiceRequest.setValue("funcionario/cargo", "");
		jxServiceRequest.setValue("funcionario/codigo", "");
		jxServiceRequest.setValue("funcionario/id", "");
		jxServiceRequest.setValue("funcionario/nemotecnico", "");
		jxServiceRequest.setValue("funcionario/nombre", "");
		jxServiceRequest.setValue("funcionario/apellido", "");
		jxServiceRequest.setValue("funcionarioAsignado/cargo", "");
		jxServiceRequest.setValue("funcionarioAsignado/codigo", "");
		jxServiceRequest.setValue("funcionarioAsignado/id", obtenerIdFuncionario(infoSource.getNumero_solicitud()).right()); //43997783
		jxServiceRequest.setValue("funcionarioAsignado/nemotecnico", "");
		jxServiceRequest.setValue("funcionarioAsignado/nombre", "");
		jxServiceRequest.setValue("funcionarioAsignado/apellido", "");
		
		jxServiceRequest.setValue("medioDeEnvio/codigo", valueOf(jxInfoSource, "medio_envio_obj/codigo", Long.class)); 
		jxServiceRequest.setValue("medioDeEnvio/nombre", valueOf(jxInfoSource, "medio_envio_obj/medio_envio", String.class)); 
		jxServiceRequest.setValue("medioDeEnvio/id", Constantes.ID_MEDIO_ENVIO_PORTAL_WEB); //1546020L
		
		jxServiceRequest.setValue("multa", BigDecimal.ZERO);
		jxServiceRequest.setValue("numero", null);
		
		jxServiceRequest.setValue("radicacionAnteriorNumero", Objects.toString(infoPadre.getNumero_radicado_postal(), ""));
		jxServiceRequest.setValue("referenciaExterna", referenciaExterna.right());
		jxServiceRequest.setValue("terminoDias", terminoDias);
		jxServiceRequest.setValue("terminoFecha", terminoDateAsCalendar);
		jxServiceRequest.setValue("tipo", puntoInteraccionProperties.valueOf(
				IPostalInteraccion.SVC_TIPO, String.class).right());// "Entrada" );
//		jxServiceRequest.setValue("tipoCuaderno/codigo","" );
		jxServiceRequest.setValue("tipoCuaderno/nombre", valueOf(jxInfoSource, "tipo_cuaderno_obj/cuadernoTipo", String.class));
		jxServiceRequest.setValue("tipoCuaderno/id", valueOf(jxInfoSource, "tipo_cuaderno_obj/codigo", String.class));
		
		jxServiceRequest.setValue("tipoDocumental/codigo", 
				puntoInteraccionProperties.valueOf(IPostalInteraccion.SVC_TIPODOCUMENTAL_CODIGO, String.class).right()); // "ENTRADA"
		jxServiceRequest.setValue("tipoDocumental/id",
				puntoInteraccionProperties.valueOf(IPostalInteraccion.SVC_TIPODOCUMENTAL_ID, Long.class).right()); // 8081L
		jxServiceRequest.setValue("tipoDocumental/nombre",
				puntoInteraccionProperties.valueOf(IPostalInteraccion.SVC_TIPODOCUMENTAL_NOMBRE,String.class).right()); // "ENTRADA"
		jxServiceRequest.setValue("tipoDocumentalConsecutivo", null);

		jxServiceRequest.setValue("tipoSeguridad/codigo", valueOf(jxInfoSource, "tipo_seguridad_obj/codigoAlfanumerico", String.class)); 
		jxServiceRequest.setValue("tipoSeguridad/id", Constantes.ID_TIPO_SEGURIDAD_JERARQUICA); // 186736283L
		jxServiceRequest.setValue("tipoSeguridad/nombre", valueOf(jxInfoSource, "tipo_seguridad_obj/seguridadTipo", String.class)); // TODO: Verificar si no es codigoAlfanumerico

		jxServiceRequest.setValue("tramite/codigo", valueOf(jxInfoSource, "tramite_obj/idPostal", Long.class)); // 16916L // 17500
		jxServiceRequest.setValue("tramite/id", valueOf(jxInfoSource, "tramite_obj/codigo", Long.class)); //	7935L // 1119
		jxServiceRequest.setValue("tramite/nombre", valueOf(jxInfoSource, "tramite_obj/nombreTramite", String.class)); //		"SOLICITUD NEGOCIACION DE EMERGENCIA;
		
		// TODO: Parametrizar valores.
		if(null != infoPadre && null != infoPadre.getTramite_obj() && null != infoPadre.getTramite_obj().getIdPostal()) {
			jxServiceRequest.setValue("tramite/proceso/nombre", infoPadre.getTramite_obj().getNombreTramite());
			switch (infoPadre.getTramite_obj().getIdPostal().toString()){
			case "16916":
				jxServiceRequest.setValue("tramite/proceso/codigo", 
						puntoInteraccionProperties.valueOf(IPostalInteraccion.SVC_TRAMITE_PROCESO_CODIGO__NEAR, Integer.class).right());// 16
				jxServiceRequest.setValue("tramite/proceso/id", 7426);
				break;
			case "16503":
				jxServiceRequest.setValue("tramite/proceso/codigo", 
						puntoInteraccionProperties.valueOf(IPostalInteraccion.SVC_TRAMITE_PROCESO_CODIGO__RABREVIADA, Integer.class).right());// 16
//				jxServiceRequest.setValue("tramite/proceso/id", 7426);
				break;
			case "17503":
				jxServiceRequest.setValue("tramite/proceso/codigo", 
						puntoInteraccionProperties.valueOf(IPostalInteraccion.SVC_TRAMITE_PROCESO_CODIGO__LSIMPLIFICADA, Integer.class).right()); //17
//				jxServiceRequest.setValue("tramite/proceso/id", 7426);
				break;
			}
 
		} 
		
		jxServiceRequest.setValue("tramite/termino/dias", terminoDias);
		jxServiceRequest.setValue("tramite/termino/esModificable", false);
		
		ArrayOfKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsIKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsI element = new ArrayOfKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsIKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsI();
		element.setKey("1");
		element.setValue(new DocumentoTipoSeguridad());
		element.getValue().setCodigo(valueOf(jxInfoSource, "tipo_seguridad_obj/codigoAlfanumerico", String.class));
		element.getValue().setId(Constantes.ID_TIPO_SEGURIDAD_JERARQUICA); // 186736283L
		element.getValue().setNombre(valueOf(jxInfoSource, "tipo_seguridad_obj/seguridadTipo", String.class));
		jxServiceRequest.setValue("tramite/tipoSeguridad",
						new ArrayOfKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsIKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsI[] { element });

		jxServiceRequest.setValue("usuario", _usuario.right()); // "APLICACIONES"
		
		String strServiceRequest = gson.toJson(serviceRequest);
		logger.debug("*******\nREQUEST RTA_RTO MI POSTAL CARGA: "+ idCarga+ ", JSON:"+strServiceRequest);

		return Either.right(serviceRequest);
//		return null;  // Usado para pruebas
	}

	private Either<Exception, String> buildCompleteCorrelationId(
			IPostalConfig puntoInteraccionProperties, String correlationId_Part1, String numProceso) {
		if (StringUtils.isNotEmpty(correlationId_Part1)) {
			Either<Exception, String> correlationId_Part0 = puntoInteraccionProperties
					.getEntidadOfPuntoInteraccion();

			if (correlationId_Part0.isLeft()) {
				return Either.left(correlationId_Part0.left());
			}

			return Either.right(String.format("%s:%s NumProceso:%s",
					correlationId_Part0.right(), correlationId_Part1, numProceso));

		} else {
			return Either.right("");
		}
	}

	private Either<Exception, String> obtenerReferenciaExterna(Integer id_carga, String numProceso) {
		if (id_carga != null) {
			String estructura = InfoRadicadoLocalServicio.getInstance()
					.obtenerNombreEstructuraFormatoPorCarga(id_carga);

			if (estructura != null) {
				String referencia = StringUtils.esNoVacio(numProceso) ? 
						String.format("$%s$:%s NumProceso:%s",estructura.toLowerCase(), id_carga, numProceso)  :
						String.format("$%s$:%s", estructura.toLowerCase(), id_carga);
				
				return Either.right(referencia);
			}

			return Either.left(new Exception(String.format(
					"error obteniendo referencia externa para la solicitud %s",
					id_carga)));

		} else {
			return Either.right("");
		}
	}
	
	private Either<Exception, String> obtenerIdFuncionario(Integer id_carga) {
		if (null != id_carga) {
			String idFuncionario = InfoRadicadoLocalServicio.getInstance()
					.obtenerIdFuncionarioPorCarga(id_carga);

			if (idFuncionario != null) {
				return Either.right(String.format("%s",idFuncionario));
			}

			return Either.left(new Exception(String.format(
					"error obteniendo idFuncionario para la solicitud %s",
					id_carga)));

		} else {
			return Either.right("");
		}
	}

	public <T> T valueOf(JXPathContext jxContext, String objPath,
			Class<T> objClass) {
		Object value = jxContext.getValue(objPath);
		return objClass.cast(value);
	}

	static class JxPostalRequestFactory extends AbstractFactory {
		public boolean createObject(JXPathContext context, Pointer pointer,
				Object parent, String name, int index) {
			if ((parent instanceof Radicacion)
					&& name.equals("aplicaA")) {
				((Radicacion) parent)
						.setAplicaA(new Particular());
				return true;
			} else if ((parent instanceof Particular)
					&& name.equals("tipoIdentificacion")) {
				((Particular) parent)
						.setTipoIdentificacion(new TipoIdentificacion());
				return true;
			} else if ((parent instanceof Particular)
					&& name.equals("ciudad")) {
				((Particular) parent)
						.setCiudad(new Ciudad());
				return true;
			} else if ((parent instanceof Ciudad)
					&& name.equals("codigo")) {
				((Ciudad) parent)
						.setCodigo(new CiudadId());
				return true;
			} else if ((parent instanceof Radicacion)
					&& name.equals("corresponsal")) {
				((Radicacion) parent)
						.setCorresponsal(new Corresponsal());
				return true;
			} else if ((parent instanceof Corresponsal)
					&& name.equals("dependencia")) {
				((Corresponsal) parent)
						.setDependencia(new Dependencia());
				return true;
			} else if ((parent instanceof Radicacion)
					&& name.equals("documentosAnexos")) {
				((Radicacion) parent)
						.setDocumentosAnexos(new String[] {});
				return true;
			} else if ((parent instanceof Corresponsal)
					&& name.equals("particular")) {
				((Corresponsal) parent)
						.setParticular(new Particular());
				return true;
			} else if ((parent instanceof Radicacion)
					&& name.equals("dependencia")) {
				((Radicacion) parent)
						.setDependencia(new Dependencia());
				return true;
			} else if ((parent instanceof Radicacion)
					&& name.equals("dependenciaAsignada")) {
				((Radicacion) parent)
						.setDependenciaAsignada(new Dependencia());
				return true;
			} else if ((parent instanceof Radicacion)
					&& name.equals("funcionario")) {
				((Radicacion) parent)
						.setFuncionario(new Funcionario());
				return true;
			} else if ((parent instanceof Radicacion)
					&& name.equals("funcionarioAsignado")) {
				((Radicacion) parent)
						.setFuncionarioAsignado(new Funcionario());
				return true;

			} else if ((parent instanceof Radicacion)
					&& name.equals("medioDeEnvio")) {
				((Radicacion) parent)
						.setMedioDeEnvio(new MedioDeEnvio());
				return true;
			} else if ((parent instanceof Radicacion)
					&& name.equals("tipoDocumental")) {
				((Radicacion) parent)
						.setTipoDocumental(new TipoDocumental());
				return true;
			} else if ((parent instanceof Radicacion)
					&& name.equals("tipoSeguridad")) {
				((Radicacion) parent)
						.setTipoSeguridad(new DocumentoTipoSeguridad());
				return true;
			} else if ((parent instanceof Radicacion)
					&& name.equals("tipoCuaderno")) {
				((Radicacion) parent)
						.setTipoCuaderno(new TipoCuaderno());
				return true;
			} else if ((parent instanceof Radicacion)
					&& name.equals("tramite")) {
				((Radicacion) parent)
						.setTramite(new Tramite());
				return true;
			} else if ((parent instanceof Tramite)
					&& name.equals("proceso")) {
				((Tramite) parent)
						.setProceso(new Proceso());
				return true;
			} else if ((parent instanceof Tramite)
					&& name.equals("termino")) {
				((Tramite) parent)
						.setTermino(new Termino());
				return true;
			} else if ((parent instanceof Tramite)
					&& name.equals("tipoSeguridad")) {
				((Tramite) parent)
						.setTipoSeguridad(new ArrayOfKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsIKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsI[] { new ArrayOfKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsIKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsI() });
				return true;
			} else if ((parent instanceof ArrayOfKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsIKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsI[] && name
					.equals("value"))) {
				((ArrayOfKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsIKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsI[]) parent)[0]
						.setValue(new DocumentoTipoSeguridad());
			} else if ((parent instanceof ArrayOfKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsIKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsI && name
					.equals("value"))) {
				((ArrayOfKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsIKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsI) parent)
						.setValue(new DocumentoTipoSeguridad());
			}

			// DocumentoTipoSeguridad

			return false;
		}

	} // Endclass

}
