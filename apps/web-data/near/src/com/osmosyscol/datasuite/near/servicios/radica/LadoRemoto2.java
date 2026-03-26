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
import com.osmosyscol.datasuite.mein.dtos.RadicaEntradaParametros;

public class LadoRemoto2 {
	private static LadoRemoto2 _instance = new LadoRemoto2();

	private LadoRemoto2() {
	}

	public static LadoRemoto2 getInstance() {
		return _instance;
	}

	static final Logger logger = LoggerFactory.getLogger(LadoRemoto2.class);
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
		Either<Exception, RadicaEntradaParametros> _mapperWrapper = Either
				.left(new Exception("Error en LadoRemoto.mapRequest"));
		
		String estructura = Objects.toString(InfoRadicadoLocalServicio.getInstance().obtenerNombreEstructuraFormatoPorCarga(idCarga), "");
		logger.debug("Verificar estructura " + estructura);
		String estadoSwitch = null;
		try {
			//estadoSwitch = _client.determinarEstadoSwitch();
			estadoSwitch = "CLOUDPAK";
			SimpleLogger.setInfo("Verificar estado Switch" + idCarga + ": " + estadoSwitch);
			
		} catch (Exception e) {
			SimpleLogger.setError("Error en el estado Switch:", e);
		}
		
		switch(estructura){
		case Constantes.ESTRUCTURA_NEAR_SOCIEDAD:
			_mapperWrapper = mapRequest(payloadSource, estadoSwitch);
			break;
		case Constantes.ESTRUCTURA_REGIMEN_INSOLVENCIA:
			_mapperWrapper = mapRequestRegimen(payloadSource, estadoSwitch);
			break;
			
		case Constantes.ESTRUCTURA_RESPUESTA_REQUERIMIENTO:
			_mapperWrapper = mapRequestRtaRequerimiento(payloadSource, estadoSwitch);
			break;
		}

		if (_mapperWrapper.isLeft()) {
			return Either.left(_mapperWrapper.left());
		}

		RadicaEntradaParametros serviceRequest = _mapperWrapper.right();

		IBPMWSDevolucionClass serviceResponse;
		
		WSData integracion = new WSData();
		integracion.setSistema(Constantes.SSOC_SISTEMA_POSTAL);
		integracion.setIntegracion(Constantes.WS_INTEGRACION_POSTAL_RADICACION_ENTRADA);
		integracion.setId_carga(idCarga);
		
		
		
		try {
			String request = JavaToXML.exe("RadicarEntrada", serviceRequest).toString();
			logger.debug("Request Radicacion entrada " + idCarga + " - " + request);
			integracion.setRequest(gson.toJson(serviceRequest));
			
			serviceResponse = _client.radicarEntrada(serviceRequest.getAnexosFisicos(), serviceRequest.getAplicaCiudadCodigo(), 
					serviceRequest.getAplicaDepartamentoCodigo(), serviceRequest.getAplicaPaisCodigo(), serviceRequest.getAplicaEmail(), 
					serviceRequest.getAplicaDireccion(), serviceRequest.getAplicaNombre(), serviceRequest.getAplicaTelefono(), 
					serviceRequest.getAplicaIdentificacion(), serviceRequest.getAplicaTipoIdentificacionId(), serviceRequest.getAplicaTipoIdentificacionNombre(), 
					serviceRequest.getParticularIdentificacion(), serviceRequest.getParticularNombre(), serviceRequest.getParticularTipoIdentificacionId(), 
					serviceRequest.getParticularTipoIdentificacionNombre(), serviceRequest.getParticularCiudadCodigo(), 
					serviceRequest.getParticularDepartamentoCodigo(), serviceRequest.getParticularPaisCodigo(), serviceRequest.getParticularDireccion(), 
					serviceRequest.getParticularTelefono(), serviceRequest.getParticularEmail(), serviceRequest.getDependenciaId(), 
					serviceRequest.getDependenciaNombre(), serviceRequest.getEntregaFisica(), serviceRequest.getFoliosNumero(), serviceRequest.getReferenciaExterna(), 
					serviceRequest.getCuadernoTipoId(), serviceRequest.getCuadernoCodigo(), serviceRequest.getDocumentalTipoCodigo(), serviceRequest.getTramiteId(), 
					serviceRequest.getTramiteCodigo(), serviceRequest.getExtensionArchivo(), serviceRequest.getCodigoMedioEnvio(), serviceRequest.getCodigoTipoSeguridad(), 
					serviceRequest.getCodigoSerie(), serviceRequest.getCodigoSubSerie(), serviceRequest.getLoginUsuario(), serviceRequest.getRadicacionAnterior(), 
					serviceRequest.getNombreSerie(), serviceRequest.getNombreSubSerie());

			logger.debug("Response Radicacion entrada "+ idCarga + " - " + JavaToXML.exe("RadicarEntradaResponse", serviceResponse).toString());

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

	public Either<Exception, Object> sync_RadicarSalida(Integer idCarga, Map<String, Object> payloadSource) {
	    Either<Exception, IIntegrationPostalServices> _clientWrapper = LadoRemoto.getInstance().createClient();

	    if (_clientWrapper.isLeft()) {
	        return Either.left(_clientWrapper.left());
	    }

	    IIntegrationPostalServices _client = _clientWrapper.right();

	    RadicacionSalida serviceRequest = (RadicacionSalida) payloadSource.get(IPostalInteraccion.PARAM_SOLICITUD);
	    IBPMWSDevolucionClass serviceResponse;

	    try {
	        logger.debug("Request Radicar Salida solicitud " + idCarga + " - " + JavaToXML.exe("RadicacionSalida", serviceRequest).toString());
	        serviceResponse = _client.radicarSalida(serviceRequest);

	        logger.debug("Response Radicar Salida solicitud " + idCarga + " - " + JavaToXML.exe("RadicacionSalidaResponse", serviceResponse).toString());

	        // Verifica el código de devolución de la respuesta
	        if (!IPostalInteraccion.CODIGO_ERROR_RADICADO.equals(serviceResponse.getCodigoDevolucion().toString().trim())) {
	            return Either.right((Object) serviceResponse);
	        } else {
	            return Either.left(new Exception("Error en el servicio postal: " + serviceResponse.getMensajeDevolucion()));
	        }

	    } catch (FaultException e) {
	        logger.error("syncRemoteSide/master (error)", e);
	        return Either.left(new Exception(e));

	    } catch (RemoteException e) {
	        logger.error("syncRemoteSide/master (error) [type=connection]", e);
	        return Either.left(new Exception(e));
	    }
	}
	
	public Documento[] getDocumentsFromPostal(Integer id_carga, String radicado) {
		
		Either<Exception, IIntegrationPostalServices> _clientWrapper = LadoRemoto2.getInstance().createClient();

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

	// Solicitud NEAR
	Either<Exception, RadicaEntradaParametros> mapRequest(
			Map<String, Object> payloadSource, String estadoSwitch) {

		String puntoInteraccion = (String) payloadSource
				.get(IPostalInteraccion.PARAM_PUNTOINTERACCION);
		Long adjuntos_Count = (Long) payloadSource
				.get(IPostalInteraccion.PARAM_COUNT_ADJUNTOS);
		SolicitudNearSociedad infoSource = (SolicitudNearSociedad) payloadSource
				.get(IPostalInteraccion.PARAM_SOLICITUD);
		String radicacionAnteriorNumero = getOrElse(payloadSource,
				IPostalInteraccion.PARAM_RADICACIONANTERIORNUMERO, "0",
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

		Either<Exception, String> _usuario = (Either<Exception, String>) $(IPostalInteraccion.CONFIGPARAM__INTEGRACION_POSTAL_USUARIO);

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

		Either<Exception, String> correlationId = (Either<Exception, String>) buildCompleteCorrelationId(
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

		RadicaEntradaParametros radicaEntradaParametros = new RadicaEntradaParametros();
		
		// Contexto Remoto
		radicaEntradaParametros.setAnexosFisicos("Si");
		radicaEntradaParametros.setAplicaCiudadCodigo(infoSource.getDeudor().getMunicipioObj().getCodigoMunicipioPostal().toString());
		radicaEntradaParametros.setAplicaDepartamentoCodigo(infoSource.getDeudor().getDepartamentoObj().getCodigo_departamento().toString());
		radicaEntradaParametros.setAplicaPaisCodigo(infoSource.getDeudor().getPais_dane_obj().getCodigo_ssoc().toString());
		radicaEntradaParametros.setAplicaEmail(infoSource.getDeudor().getDatos_basicos().getCorreo());
		radicaEntradaParametros.setAplicaDireccion(infoSource.getDeudor().getDatos_basicos().getDireccion());
		radicaEntradaParametros.setAplicaIdentificacion(infoSource.getDeudor().getDatos_basicos().getIdentificacion());
		radicaEntradaParametros.setAplicaNombre(infoSource.getDeudor().getDatos_basicos().getNombre_completo());
		radicaEntradaParametros.setAplicaTelefono(infoSource.getDeudor().getDatos_basicos().getTelefono());
		
		TipoDeDocumento tipoDocIdentif = MAPA_TIPOS_DOCUMENTO_POSTAL.get(valueOf(jxInfoSource, "deudor/datos_basicos/tipo_identificacion", String.class));
		SimpleLogger.setDebug("Consultando tipo documento "
				+ valueOf(jxInfoSource, "deudor/datos_basicos/tipo_identificacion", String.class)
				+ " Envia: " + tipoDocIdentif.getId_postal());
		radicaEntradaParametros.setAplicaTipoIdentificacionId(tipoDocIdentif.getCodigo_numerico_postal());
		radicaEntradaParametros.setAplicaTipoIdentificacionNombre(tipoDocIdentif.getNombre_postal());
		
		// INFORMACIï¿½N DE CORRESPONSAL (PARTICULAR)
		if(null != infoSource.getTipo_solicitante()
				&& (infoSource.getTipo_solicitante().equals(Constantes.TIPO_SOLICITANTE_SOCIEDAD) || infoSource.getTipo_solicitante().equals(Constantes.TIPO_SOLICITANTE_SUCURSALES_EXTRANJERAS))) {
			radicaEntradaParametros.setParticularIdentificacion(infoSource.getDeudor().getRepresentante_legal().getIdentificacion());
			radicaEntradaParametros.setParticularNombre(infoSource.getDeudor().getRepresentante_legal().getNombre_completo());
			TipoDeDocumento tipoDeDocumentoRepLegal = new TipoDeDocumento();
			if (infoSource.getDeudor().getRepresentante_legal() != null) {
				tipoDeDocumentoRepLegal = MAPA_TIPOS_DOCUMENTO_POSTAL.get(infoSource.getDeudor().getRepresentante_legal().getTipo_identificacion());
			}
			radicaEntradaParametros.setParticularTipoIdentificacionId(tipoDeDocumentoRepLegal.getCodigo_numerico_postal());
			radicaEntradaParametros.setParticularTipoIdentificacionNombre(tipoDeDocumentoRepLegal.getNombre_postal());

		} else if(null != infoSource.getTipo_solicitante()
				&& (infoSource.getTipo_solicitante().equals(Constantes.TIPO_SOLICITANTE_PNC) || infoSource.getTipo_solicitante().equals(Constantes.TIPO_SOLICITANTE_PNNC))){
			radicaEntradaParametros.setParticularIdentificacion(infoSource.getDeudor().getDatos_basicos().getIdentificacion());
			radicaEntradaParametros.setParticularNombre(infoSource.getDeudor().getDatos_basicos().getNombre_completo());
			radicaEntradaParametros.setParticularTipoIdentificacionId(tipoDocIdentif.getCodigo_numerico_postal());
			radicaEntradaParametros.setParticularTipoIdentificacionNombre(tipoDocIdentif.getNombre_postal());
		} 
		radicaEntradaParametros.setParticularCiudadCodigo(infoSource.getDeudor().getMunicipioObj().getCodigoMunicipioPostal().toString());
		radicaEntradaParametros.setParticularDepartamentoCodigo(infoSource.getDeudor().getDepartamentoObj().getCodigo_departamento().toString());
		radicaEntradaParametros.setParticularPaisCodigo(infoSource.getDeudor().getPais_dane_obj().getCodigo_ssoc().toString());
		radicaEntradaParametros.setParticularDireccion(infoSource.getDeudor().getDatos_basicos().getDireccion());
		radicaEntradaParametros.setParticularTelefono(infoSource.getDeudor().getDatos_basicos().getTelefono());
		radicaEntradaParametros.setParticularEmail(infoSource.getDeudor().getDatos_basicos().getCorreo());
		radicaEntradaParametros.setDependenciaId(Long.valueOf(dependenciaId).intValue());
		radicaEntradaParametros.setDependenciaNombre(dependenciaNombre);
		radicaEntradaParametros.setEntregaFisica(false);
		radicaEntradaParametros.setFoliosNumero(Long.valueOf(adjuntos_Count).intValue());
		radicaEntradaParametros.setReferenciaExterna(correlationId.right());
		if ("Postal".equals(estadoSwitch)) {
			SimpleLogger.setInfo("El estado Switch debe ser postal : " + estadoSwitch);
			radicaEntradaParametros.setCuadernoTipoId(Integer.parseInt(infoSource.getCuaderno_obj().getCodigo()));
			radicaEntradaParametros.setCuadernoCodigo("0");
			radicaEntradaParametros.setDocumentalTipoCodigo("0");
			radicaEntradaParametros.setTramiteId(Long.valueOf(infoSource.getTramite_obj().getCodigo()).intValue()); 
			radicaEntradaParametros.setTramiteCodigo((long) 0);
			radicaEntradaParametros.setNombreSubSerie("");
			radicaEntradaParametros.setCodigoSerie("");
			radicaEntradaParametros.setNombreSerie("");
			radicaEntradaParametros.setCodigoSubSerie("");
			
		} else {
			SimpleLogger.setInfo("El estado Switch debe ser Cloudpak : " + estadoSwitch);
			radicaEntradaParametros.setCuadernoTipoId(0);
			radicaEntradaParametros.setCuadernoCodigo(infoSource.getCuaderno_obj().getCodigo());
			radicaEntradaParametros.setDocumentalTipoCodigo(puntoInteraccionProperties.valueOf(IPostalInteraccion.SVC_TIPODOCUMENTAL_NOMBRE,String.class).right());
			radicaEntradaParametros.setTramiteId(0); 
			radicaEntradaParametros.setTramiteCodigo(infoSource.getTramite_obj().getIdPostal());
			radicaEntradaParametros.setNombreSubSerie("PROCESOS DE NEGOCIACIÓN DE EMERGENCIA DE UN ACUERDO DE REORGANIZACIÓN");
			radicaEntradaParametros.setCodigoSerie("60");
			radicaEntradaParametros.setNombreSerie("PROCESOS JUDICIALES DE INSOLVENCIA");
			radicaEntradaParametros.setCodigoSubSerie("214");
		}
		radicaEntradaParametros.setCodigoTipoSeguridad(puntoInteraccionProperties.valueOf(IPostalInteraccion.SVC_TIPOSEGURIDAD_CODIGO, String.class).right());
		radicaEntradaParametros.setLoginUsuario(_usuario.right());
		radicaEntradaParametros.setRadicacionAnterior(radicacionAnteriorNumero);
		radicaEntradaParametros.setCodigoMedioEnvio(puntoInteraccionProperties.valueOf(
				IPostalInteraccion.SVC_MEDIODEENVIO_CODIGO, Integer.class)
				.right());
		radicaEntradaParametros.setExtensionArchivo(".pdf");
		
		ArrayOfKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsIKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsI element = new ArrayOfKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsIKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsI();
		element.setKey("1");
		element.setValue(new DocumentoTipoSeguridad());
		element.getValue().setCodigo(puntoInteraccionProperties.valueOf(
						IPostalInteraccion.SVC_TIPOSEGURIDAD_CODIGO, String.class).right()); // "ABIERTA"
		element.getValue().setId(puntoInteraccionProperties.valueOf(
						IPostalInteraccion.SVC_TIPOSEGURIDAD_ID, Long.class).right()); // 186736211L
		element.getValue().setNombre(puntoInteraccionProperties.valueOf(
						IPostalInteraccion.SVC_TIPOSEGURIDAD_NOMBRE, String.class).right()); // "ABIERTA"
		
		
		
		
		
		Gson gson = new Gson();
		String strServiceRequest = gson.toJson(radicaEntradaParametros);
		logger.debug("strServiceRequest "+strServiceRequest);
		
		logger.debug("strServiceRequest "+strServiceRequest);

		return Either.right(radicaEntradaParametros);
		
//		Gson gson = new Gson();
//		String strServiceRequest = gson.toJson(serviceRequest);
//		logger.debug("*******\n"+strServiceRequest);
//
//		return Either.right(serviceRequest);
//		return null; //Usado para pruebas
	}


	// solicitud otros procesos
	Either<Exception, RadicaEntradaParametros> mapRequestRegimen(
			Map<String, Object> payloadSource, String estadoSwitch) {

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
				logger.info("Activos " + infoSource
										.getInfo_financiera_anual()
										.getActivos_ano_anterior());
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
				IPostalInteraccion.PARAM_RADICACIONANTERIORNUMERO, "0",
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

		RadicaEntradaParametros radicaEntradaParametros = new RadicaEntradaParametros();
		
		radicaEntradaParametros.setAnexosFisicos("Si");
		radicaEntradaParametros.setAplicaCiudadCodigo(infoSource.getDeudor().getMunicipio().getCodigoMunicipioPostal().toString());
		radicaEntradaParametros.setAplicaDepartamentoCodigo(infoSource.getDeudor().getDepartamento().getCodigo_departamento().toString());
		radicaEntradaParametros.setAplicaPaisCodigo(infoSource.getDeudor().getPais_dane().getCodigo_ssoc().toString());
		radicaEntradaParametros.setAplicaEmail(infoSource.getDeudor().getCorreo_electronico());
		radicaEntradaParametros.setAplicaDireccion(infoSource.getDeudor().getDireccion());
		radicaEntradaParametros.setAplicaNombre(infoSource.getDeudor().getRazon_social());
		radicaEntradaParametros.setAplicaTelefono(infoSource.getDeudor().getTelefono());
		radicaEntradaParametros.setAplicaIdentificacion(infoSource.getDeudor().getNumero_identificacion());
		TipoDeDocumento tipoDocIdentif = MAPA_TIPOS_DOCUMENTO_POSTAL.get(valueOf(jxInfoSource, "deudor/tipo_identificacion_id", String.class));
		SimpleLogger.setDebug("Consultando tipo documento "
				+ valueOf(jxInfoSource, "deudor/tipo_identificacion_id", String.class)
				+ " Envia: " + tipoDocIdentif.getId_postal());
		radicaEntradaParametros.setAplicaTipoIdentificacionId(tipoDocIdentif.getCodigo_numerico_postal());
		radicaEntradaParametros.setAplicaTipoIdentificacionNombre(tipoDocIdentif.getNombre_postal());
		if(null != infoSource.getTipo_solicitante() 
				&& (infoSource.getTipo_solicitante().equals(Constantes.TIPO_SOLICITUD_NEAR)
						|| infoSource.getTipo_solicitante().equals(Constantes.TIPO_SOLICITANTE_SUCURSALES_EXTRANJERAS))){
			
			if(null != infoSource.getDeudor().getId_representante_legal()){
				Persona datosRepLegal = PersonaServicio.getInstance().obtenerPersona(
						infoSource.getDeudor().getId_representante_legal());
				
				radicaEntradaParametros.setParticularIdentificacion(datosRepLegal.getIdentificacion());
				radicaEntradaParametros.setParticularNombre(datosRepLegal.getNombreCompleto());

				TipoDeDocumento tipoDocumento = DS_SqlUtils.queryForObject(TipoDeDocumento.class, 
						"select * from $tipo de documento$ where $tipo de documento.codigo$ = $I(" + datosRepLegal.getTipo_documento() + ")$");
				
				radicaEntradaParametros.setParticularTipoIdentificacionId(tipoDocumento.getCodigo_numerico_postal());
				radicaEntradaParametros.setParticularTipoIdentificacionNombre(tipoDocumento.getNombre_postal());


			} else {
				radicaEntradaParametros.setParticularIdentificacion("");
				radicaEntradaParametros.setParticularNombre("");
				radicaEntradaParametros.setParticularTipoIdentificacionId(null);
				radicaEntradaParametros.setParticularTipoIdentificacionNombre("");
			}
			
		} else if (null != infoSource.getTipo_solicitante() 
				&& (infoSource.getTipo_solicitante().equals(Constantes.TIPO_SOLICITANTE_PNC)
						|| infoSource.getTipo_solicitante().equals(Constantes.TIPO_SOLICITANTE_PNNC))) {

			radicaEntradaParametros.setParticularIdentificacion(infoSource.getDeudor().getNumero_identificacion());
			radicaEntradaParametros.setParticularNombre(infoSource.getDeudor().getRazon_social());
			radicaEntradaParametros.setParticularTipoIdentificacionId(tipoDocIdentif.getCodigo_numerico_postal());
			radicaEntradaParametros.setParticularTipoIdentificacionNombre(tipoDocIdentif.getNombre_postal());
			
		} else {
			radicaEntradaParametros.setParticularIdentificacion("");
			radicaEntradaParametros.setParticularNombre("");
			radicaEntradaParametros.setParticularTipoIdentificacionId(null);
			radicaEntradaParametros.setParticularTipoIdentificacionNombre("");
		}
		radicaEntradaParametros.setParticularCiudadCodigo(infoSource.getDeudor().getMunicipio().getCodigoMunicipioPostal().toString());
		radicaEntradaParametros.setParticularDepartamentoCodigo(infoSource.getDeudor().getDepartamento().getCodigo_departamento().toString());
		radicaEntradaParametros.setParticularPaisCodigo(infoSource.getDeudor().getPais_dane().getCodigo_ssoc().toString());
		radicaEntradaParametros.setParticularDireccion(infoSource.getDeudor().getDireccion());
		radicaEntradaParametros.setParticularTelefono(infoSource.getDeudor().getTelefono());
		radicaEntradaParametros.setParticularEmail(infoSource.getDeudor().getCorreo_electronico());
		radicaEntradaParametros.setDependenciaId(Long.valueOf(dependenciaId).intValue());
		radicaEntradaParametros.setDependenciaNombre(dependenciaNombre);
		radicaEntradaParametros.setEntregaFisica(false);
		radicaEntradaParametros.setFoliosNumero(adjuntos_Count.intValue());
		radicaEntradaParametros.setReferenciaExterna(referenciaExterna.right());
		if ("Postal".equals(estadoSwitch)) {
			radicaEntradaParametros.setCuadernoTipoId(Integer.parseInt(infoSource.getCuaderno().getCodigo()));
			radicaEntradaParametros.setCuadernoCodigo("0");
			
			radicaEntradaParametros.setDocumentalTipoCodigo("0");
			radicaEntradaParametros.setTramiteId(infoSource.getTramite().getCodigo().intValue()); 
			radicaEntradaParametros.setTramiteCodigo((long) 0);
			
			radicaEntradaParametros.setNombreSerie("");
			radicaEntradaParametros.setCodigoSerie("");
			radicaEntradaParametros.setNombreSubSerie("");
			radicaEntradaParametros.setCodigoSubSerie("");
		} else {
			radicaEntradaParametros.setCuadernoCodigo(infoSource.getCuaderno().getCodigo());
			radicaEntradaParametros.setCuadernoTipoId(0);
			radicaEntradaParametros.setDocumentalTipoCodigo("ENTRADA");
//			radicaEntradaParametros.setDocumentalTipoCodigo(puntoInteraccionProperties.valueOf(
//					IPostalInteraccion.SVC_TIPODOCUMENTAL_CODIGO,
//					String.class).right());
			radicaEntradaParametros.setTramiteId(0); 
			radicaEntradaParametros.setTramiteCodigo(Long.valueOf(infoSource.getTramite().getIdPostal()));
			radicaEntradaParametros.setCodigoSerie(String.valueOf(infoSource.getSerie().getCodigo()));
			radicaEntradaParametros.setCodigoSubSerie(String.valueOf(infoSource.getSubserie().getCodigo()));
			radicaEntradaParametros.setNombreSerie(infoSource.getSerie().getNombre());
			radicaEntradaParametros.setNombreSubSerie(infoSource.getSubserie().getNombre());
			
		}
		radicaEntradaParametros.setCodigoTipoSeguridad(infoSource.getTipo_seguridad().getCodigoAlfanumerico());
		radicaEntradaParametros.setLoginUsuario(_usuario.right());
		radicaEntradaParametros.setRadicacionAnterior(radicacionAnteriorNumero);
		radicaEntradaParametros.setCodigoMedioEnvio(infoSource.getMedio_envio().getCodigo().intValue());

		radicaEntradaParametros.setExtensionArchivo(".pdf");
		
		ArrayOfKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsIKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsI element = new ArrayOfKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsIKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsI();
		element.setKey("1");
		element.setValue(new DocumentoTipoSeguridad());
		element.getValue().setCodigo(valueOf(jxInfoSource, "tipo_seguridad/codigoAlfanumerico", String.class));
		element.getValue().setNombre(valueOf(jxInfoSource, "tipo_seguridad/seguridadTipo", String.class));
		element.getValue().setId(valueOf(jxInfoSource, "tipo_seguridad/id_tipo_seguridad", Long.class));

		
		Gson gson = new Gson();
		String strServiceRequest = gson.toJson(radicaEntradaParametros);
		logger.debug("strServiceRequest "+strServiceRequest);
		
		logger.debug("strServiceRequest "+strServiceRequest);

		return Either.right(radicaEntradaParametros);
//		return null; // Usado para pruebas
	}
	
	// respuesta requerimiento
	Either<Exception, RadicaEntradaParametros> mapRequestRtaRequerimiento(
			Map<String, Object> payloadSource, String estadoSwitch) {
		

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
		
		
		RadicaEntradaParametros radicaEntradaParametros = new RadicaEntradaParametros();

		// Contexto Remoto
		
		radicaEntradaParametros.setAnexosFisicos("Si");
		radicaEntradaParametros.setAplicaCiudadCodigo(infoSource.getDeudor_obj().getMunicipio().getCodigoMunicipioPostal().toString());
		radicaEntradaParametros.setAplicaDepartamentoCodigo(infoSource.getDeudor_obj().getDepartamento().getCodigo_departamento().toString());
		radicaEntradaParametros.setAplicaPaisCodigo(infoSource.getDeudor_obj().getPais_dane().getCodigo_ssoc().toString());
		radicaEntradaParametros.setAplicaEmail(infoSource.getDeudor_obj().getCorreo_electronico());
		radicaEntradaParametros.setAplicaDireccion(infoSource.getDeudor_obj().getDireccion());
		radicaEntradaParametros.setAplicaNombre(infoSource.getDeudor_obj().getRazon_social());
		radicaEntradaParametros.setAplicaTelefono(infoSource.getDeudor_obj().getTelefono());
		radicaEntradaParametros.setAplicaIdentificacion(infoSource.getDeudor_obj().getNumero_identificacion());
		radicaEntradaParametros.setAplicaTipoIdentificacionId(infoSource.getDeudor_obj().getTipo_identificacion().getCodigo_numerico_postal());
		radicaEntradaParametros.setAplicaTipoIdentificacionNombre(infoSource.getDeudor_obj().getTipo_identificacion().getNombre_postal());
		// Si solicitante es sociedad o Sucursal extranjera lleva la info del representante legal
		if(null != infoSource.getTipo_solicitante() 
				&& (infoSource.getTipo_solicitante_obj().getId().equals(Constantes.TIPO_SOLICITUD_NEAR)
						|| infoSource.getTipo_solicitante_obj().getId().equals(Constantes.TIPO_SOLICITANTE_SUCURSALES_EXTRANJERAS))){
			radicaEntradaParametros.setParticularIdentificacion(infoPadre.getDeudor_obj().getRepresentante_legal().getIdentificacion());
			radicaEntradaParametros.setParticularNombre(infoPadre.getDeudor_obj().getRepresentante_legal().getNombre_completo());
			
			TipoDeDocumento tipoDeDocumento = MAPA_TIPOS_DOCUMENTO_POSTAL.get(infoPadre.getDeudor_obj().getRepresentante_legal().getTipo_identificacion());
			radicaEntradaParametros.setParticularTipoIdentificacionId(Integer.valueOf(tipoDeDocumento.getCodigo_numerico_postal()));
			radicaEntradaParametros.setParticularTipoIdentificacionNombre(tipoDeDocumento.getNombre_postal());
			
		} else if (null != infoSource.getTipo_solicitante() 
				&& (infoSource.getTipo_solicitante_obj().getId().equals(Constantes.TIPO_SOLICITANTE_PNC)
						|| infoSource.getTipo_solicitante_obj().getId().equals(Constantes.TIPO_SOLICITANTE_PNNC))) {
			radicaEntradaParametros.setParticularIdentificacion(infoSource.getDeudor_obj().getNumero_identificacion());
			radicaEntradaParametros.setParticularNombre(infoSource.getDeudor_obj().getRazon_social());
			radicaEntradaParametros.setParticularTipoIdentificacionId(infoSource.getDeudor_obj().getTipo_identificacion().getCodigo_numerico_postal());
			radicaEntradaParametros.setParticularTipoIdentificacionNombre(infoSource.getDeudor_obj().getTipo_identificacion().getNombre_postal());
			
		}
		radicaEntradaParametros.setParticularCiudadCodigo(infoSource.getDeudor_obj().getMunicipio().getCodigoMunicipioPostal().toString());
		radicaEntradaParametros.setParticularDepartamentoCodigo(infoSource.getDeudor_obj().getDepartamento().getCodigo_departamento().toString());
		radicaEntradaParametros.setParticularPaisCodigo(infoSource.getDeudor_obj().getPais_dane().getCodigo_ssoc().toString());
		radicaEntradaParametros.setParticularDireccion(infoSource.getDeudor_obj().getDireccion());
		radicaEntradaParametros.setParticularTelefono(infoSource.getDeudor_obj().getTelefono());
		radicaEntradaParametros.setParticularEmail(infoSource.getDeudor_obj().getCorreo_electronico());
		radicaEntradaParametros.setDependenciaId(Long.valueOf(infoSource.getDependencia_obj().getCodigo()).intValue());
		radicaEntradaParametros.setDependenciaNombre(infoSource.getDependencia_obj().getNombreDependencia());
		radicaEntradaParametros.setEntregaFisica(false);
		radicaEntradaParametros.setFoliosNumero(adjuntos_Count.intValue());
		radicaEntradaParametros.setReferenciaExterna(referenciaExterna.right());
		if ("Postal".equals(estadoSwitch)) {
			radicaEntradaParametros.setCuadernoTipoId(Integer.valueOf(infoSource.getTipo_cuaderno_obj().getCodigo()));
			radicaEntradaParametros.setCuadernoCodigo("0");
			radicaEntradaParametros.setDocumentalTipoCodigo("0");
			radicaEntradaParametros.setTramiteId(infoSource.getTramite_obj().getCodigo().intValue()); 
			radicaEntradaParametros.setTramiteCodigo((long) 0);
			radicaEntradaParametros.setNombreSerie("");
			radicaEntradaParametros.setCodigoSerie("");
			radicaEntradaParametros.setNombreSubSerie("");
			radicaEntradaParametros.setCodigoSubSerie("");
		} else {
			radicaEntradaParametros.setCuadernoCodigo(infoSource.getTipo_cuaderno_obj().getCodigo());
			radicaEntradaParametros.setCuadernoTipoId(0);
			radicaEntradaParametros.setDocumentalTipoCodigo("ENTRADA");
//			radicaEntradaParametros.setDocumentalTipoCodigo(puntoInteraccionProperties.valueOf(IPostalInteraccion.SVC_TIPODOCUMENTAL_CODIGO, String.class).right());
			radicaEntradaParametros.setTramiteId(0); 
			radicaEntradaParametros.setTramiteCodigo(Long.valueOf(infoSource.getTramite_obj().getIdPostal()));
			radicaEntradaParametros.setCodigoSerie(String.valueOf(infoSource.getTipo_serie_obj().getCodigo()));
			radicaEntradaParametros.setCodigoSubSerie(String.valueOf(infoSource.getTipo_subserie_obj().getCodigo()));
			radicaEntradaParametros.setNombreSerie(infoSource.getTipo_serie_obj().getNombre());
			radicaEntradaParametros.setNombreSubSerie(infoSource.getTipo_subserie_obj().getNombre());
			
		}
		
		radicaEntradaParametros.setCodigoTipoSeguridad(infoSource.getTipo_seguridad_obj().getCodigoAlfanumerico());
		radicaEntradaParametros.setLoginUsuario(_usuario.right());
		radicaEntradaParametros.setRadicacionAnterior(infoPadre.getNumero_radicado_postal());
		radicaEntradaParametros.setCodigoMedioEnvio(infoSource.getMedio_envio_obj().getCodigo().intValue());
		// TODO: Parametrizar valores.
//		if(null != infoPadre && null != infoPadre.getTramite_obj() && null != infoPadre.getTramite_obj().getIdPostal()) {
//			jxServiceRequest.setValue("tramite/proceso/nombre", infoPadre.getTramite_obj().getNombreTramite());
//			switch (infoPadre.getTramite_obj().getIdPostal().toString()){
//			case "16916":
//				jxServiceRequest.setValue("tramite/proceso/codigo", 
//						puntoInteraccionProperties.valueOf(IPostalInteraccion.SVC_TRAMITE_PROCESO_CODIGO__NEAR, Integer.class).right());// 16
//				jxServiceRequest.setValue("tramite/proceso/id", 7426);
//				break;
//			case "16503":
//				jxServiceRequest.setValue("tramite/proceso/codigo", 
//						puntoInteraccionProperties.valueOf(IPostalInteraccion.SVC_TRAMITE_PROCESO_CODIGO__RABREVIADA, Integer.class).right());// 16
///			jxServiceRequest.setValue("tramite/proceso/id", 7426);
//				break;
//			case "17503":
//				jxServiceRequest.setValue("tramite/proceso/codigo", 
//						puntoInteraccionProperties.valueOf(IPostalInteraccion.SVC_TRAMITE_PROCESO_CODIGO__LSIMPLIFICADA, Integer.class).right()); //17
//				jxServiceRequest.setValue("tramite/proceso/id", 7426);
//				break;
//			}
// 
//		} 
		
		ArrayOfKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsIKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsI element = new ArrayOfKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsIKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsI();
		element.setKey("1");
		element.setValue(new DocumentoTipoSeguridad());
		element.getValue().setCodigo(valueOf(jxInfoSource, "tipo_seguridad_obj/codigoAlfanumerico", String.class));
		element.getValue().setId(Constantes.ID_TIPO_SEGURIDAD_JERARQUICA); // 186736283L
		element.getValue().setNombre(valueOf(jxInfoSource, "tipo_seguridad_obj/seguridadTipo", String.class));
//		jxServiceRequest.setValue("tramite/tipoSeguridad",
//						new ArrayOfKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsIKeyValueOfstringDocumentoTipoSeguridadUMuZ5CsI[] { element });

		
		
		String strServiceRequest = gson.toJson(radicaEntradaParametros);
		logger.debug("*******\nREQUEST RTA_RTO MI POSTAL CARGA: "+ idCarga+ ", JSON:"+strServiceRequest);

		return Either.right(radicaEntradaParametros);
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
