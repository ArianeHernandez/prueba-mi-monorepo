package com.osmosyscol.datasuite.bpm.servicios;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import co.htsoft.commons.net.CallPage;

import com.google.gson.Gson;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.rpc.jsonrpc.JSONObject;
import com.osmosyscol.datasuite.bpm.acciones.ApVistaDocumentoTipoAcc;
import com.osmosyscol.datasuite.bpm.acciones.ApVistaIdentificacionTiposAcc;
import com.osmosyscol.datasuite.bpm.acciones.ApVistaMacrosectorAcc;
import com.osmosyscol.datasuite.bpm.acciones.ApVistaNaturalezaAcc;
import com.osmosyscol.datasuite.bpm.acciones.ApVistaSupuestoAcc;
import com.osmosyscol.datasuite.bpm.acciones.ApVistasGrupoNiifAcc;
import com.osmosyscol.datasuite.bpm.acciones.ApVistasGruposAcc;
import com.osmosyscol.datasuite.bpm.dto.ApVistaDocumentoTipoDto;
import com.osmosyscol.datasuite.bpm.dto.ApVistaGrupoNiifDto;
import com.osmosyscol.datasuite.bpm.dto.ApVistaMacrosectorDto;
import com.osmosyscol.datasuite.bpm.dto.ApVistaNaturalezaDto;
import com.osmosyscol.datasuite.bpm.dto.ApVistaSupuestoDto;
import com.osmosyscol.datasuite.bpm.dto.CamposAdicionalesDto;
import com.osmosyscol.datasuite.bpm.dto.ProcesoActuacionesDto;
import com.osmosyscol.datasuite.bpm.dto.ProcesoRadicacionDto;
import com.osmosyscol.datasuite.bpm.dto.PropiedadesPartesDto;
import com.osmosyscol.datasuite.bpm.dto.PropiedadesProcesoActuacionesDto;
import com.osmosyscol.datasuite.bpm.dto.UsuarioDto;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;
import com.osmosyscol.datasuite.mein.builder.RegimenInsolvenciaFetchBuilder;
import com.osmosyscol.datasuite.mein.dtos.APVistaCuaderno;
import com.osmosyscol.datasuite.mein.dtos.APVistaDependencias;
import com.osmosyscol.datasuite.mein.dtos.APVistaDocumentoTipo;
import com.osmosyscol.datasuite.mein.dtos.APVistaMedioEnvio;
import com.osmosyscol.datasuite.mein.dtos.APVistaSeguridadTipo;
import com.osmosyscol.datasuite.mein.dtos.APVistaTramite;
import com.osmosyscol.datasuite.mein.dtos.Checklist;
import com.osmosyscol.datasuite.mein.dtos.Deudor;
import com.osmosyscol.datasuite.mein.dtos.InfoFinancieraAnual;
import com.osmosyscol.datasuite.mein.dtos.InformacionFinanciera;
import com.osmosyscol.datasuite.mein.dtos.PaisDane;
import com.osmosyscol.datasuite.mein.dtos.PersonaMein;
import com.osmosyscol.datasuite.mein.dtos.RadicacionAutoOficio;
import com.osmosyscol.datasuite.mein.dtos.RegimenInsolvencia;
import com.osmosyscol.datasuite.mein.dtos.RelacionPasivos;
import com.osmosyscol.datasuite.mein.dtos.RequestFirmaFuncionario;
import com.osmosyscol.datasuite.mein.dtos.ResponseFirmaFuncionario;
import com.osmosyscol.datasuite.mein.dtos.RespuestaRequerimiento;
import com.osmosyscol.datasuite.mein.dtos.Sociedad;
import com.osmosyscol.datasuite.mein.dtos.SolicitudNearSociedad;
import com.osmosyscol.datasuite.mein.dtos.TipoDeDocumento;
import com.osmosyscol.datasuite.mein.dtos.TipoSolicitud;
import com.osmosyscol.datasuite.mein.dtos.WSData;
import com.osmosyscol.datasuite.mein.logica.constantes.Constantes;
import com.osmosyscol.datasuite.mein.servicios.RadicacionAutoOficioServicio;
import com.osmosyscol.datasuite.mein.servicios.RegimenInsolvenciaServicio;
import com.osmosyscol.datasuite.mein.servicios.RespuestaRequerimientoServicio;
import com.osmosyscol.datasuite.mein.servicios.SolicitudNearSociedadServicio;
import com.osmosyscol.datasuite.mein.servicios.TipoDeDocumentoServicio;
import com.osmosyscol.datasuite.near.interop.lectorcerl.rest_cli.json.RepresentantesLegale;
import com.osmosyscol.datasuite.near.servicios.InfoRadicadoLocalServicio;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class BpmServicios {
	
	private static BpmServicios instance;
	private static Gson gson = new Gson();
	final static Logger logger = Logger.getLogger(BpmServicios.class);
	
	private BpmServicios() {
		
	}
	
	public static BpmServicios getInstance() {
		if (instance == null) {
			instance = new BpmServicios();
		}
		return instance;
	}
	
	private static Map<String, TipoDeDocumento> MAPA_TIPOS_DOCUMENTO = TipoDeDocumentoServicio.getInstance().getMapForCodHts();
	
	// Utils
	
	private String getEndPoint(String property) {
		
		String endpoint = ParametrosInicio.getProperty(property);
		
		SimpleLogger.setDebug("Consultando servicio: " + endpoint);

		if (endpoint == null) {
			SimpleLogger.setError("BpmServicios.getEndPoint: Variable " + property
							+ " no configurada en el archivo de propiedades de la carpeta $DATASUITE");
		}

		return endpoint;
	}
	
	public UsuarioDto obtenerUsuarioCodificado() {
		
		String username = ParametrosInicio.getProperty("bpm.username");
		String password = ParametrosInicio.getProperty("bpm.password");
		
		UsuarioDto usuario = null;
		
		if (username != null && password != null) {
			usuario = new UsuarioDto();
			usuario.setUsername(new String(Base64.encodeBase64(username.getBytes())));
			usuario.setPassword(new String(Base64.encodeBase64(password.getBytes())));
			usuario.setEmpresa(1);
		} else {
			SimpleLogger.setError("BpmServicios.obtenerUsuarioCodificado: No se encuentran configurados los parametros de usuario y contraseña para el servicio de token");
		}

		return usuario;

	}
	
	private Map<String, String> setupHeaders() {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");

		return headers;
	}
	
	private Persona llenarPersona() {
		Persona nuevaP = new Persona();
		nuevaP.setTelefono("");
		nuevaP.setCorreo("");
		nuevaP.setNombre("");
		nuevaP.setIdentificacion("");
		nuevaP.setDireccion("");
		nuevaP.setTipo_documento(null);

		return nuevaP;

	}

	private String toFormatyyyyMMDdHHmmss(Date valor) {

		if (valor == null) {
			return "";
		}

		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(
					(Date) valor).trim();
		} catch (Exception e) {
		}

		return valor.toString();
	}
	
	// WS
	
	public String generarToken(WSData integracion) throws Throwable{
		
		UsuarioDto usuario = obtenerUsuarioCodificado();

		CallPage call = new CallPage();
		Map<String, String> headers = setupHeaders();
		String endpoint = getEndPoint("bpm.endpoint") + getEndPoint("bpm.api") + "login/key/";
		
		if (integracion != null) {
			integracion.setRequest("Endpoint Token: " + endpoint);
		}
		
		String resp = call.call(endpoint, gson.toJson(usuario), headers, "POST");
		return gson.fromJson(resp, String.class);
			
	}
	
	public WSData getNumeroPorDependencia(Integer dependencia) throws SAXException, IOException, ParserConfigurationException{
		
		WSData integracion = new WSData();
		integracion.setIntegracion(Constantes.WS_INTEGRACION_NUMERO_PROCESO);
		integracion.setSistema(Constantes.SSOC_SISTEMA_BPM);

		try {
			CallPage call = new CallPage();
			
			String token = generarToken(integracion);
			
			Map<String, String> headers = setupHeaders();
			headers.put("Authorization", token);
			
			String json_headers = gson.toJson(headers);
			
			String endpoint = getEndPoint("bpm.endpoint");
			endpoint = endpoint + getEndPoint("bpm.api") + "ConsultasSUIT/GenerarNumeroProcesoPorDependencia?codigoDependencia="+dependencia;
			integracion.setRequest("Headers: \n " + json_headers + "\n" + "Endpoint: " + endpoint);
			
			String response = call.callget(endpoint, headers);
			
			// Si tiene formato xml se extrae el valor
			Integer indice_cierre_etiqueta = response.indexOf(">");			
			if(indice_cierre_etiqueta > 0) {
				response = response.substring(indice_cierre_etiqueta+1, response.lastIndexOf("<"));
			}
			
			integracion.setResponse(response);
			
			return integracion;
			
		} catch (Throwable e) {
			SimpleLogger.setError("BpmServicios.getNumeroPorDependencia", e);
			String error = ExceptionUtils.getStackTrace(e);
			integracion.setResponse("Error generado \n" + error);
			return integracion;
		}
	}
	
	public WSData enviarDatosBPM (Integer id_carga) {
		String estructura = Objects.toString(InfoRadicadoLocalServicio.getInstance().obtenerNombreEstructuraFormatoPorCarga(id_carga), "");
		
		return enviarDatosABPM (id_carga, estructura);
	}
	
	public WSData enviarDatosABPM (Integer id_carga, String estructura) {
		
		ProcesoRadicacionDto procesoRadicacion = null;
		
		if( Constantes.ESTRUCTURA_NEAR_SOCIEDAD.equals(estructura)) {
			procesoRadicacion = getProcesoRadicacionNear(id_carga);			
		} else if (Constantes.ESTRUCTURA_RESPUESTA_REQUERIMIENTO.equals(estructura)) {
			procesoRadicacion = getProcesoRadicacionRtaReqNear(id_carga);
		} else if (Constantes.ESTRUCTURA_REGIMEN_INSOLVENCIA.equals(estructura)) {
			procesoRadicacion = getProcesoRadicacionRegimenInsolvencia(id_carga);
		}
		
		return actualizarPanelProcesoRadicacionWeb (id_carga, procesoRadicacion);
		
	}
	private String obtenerNombreLugarPorCodigo(String codigo, String estructura, String nombreColumna){
		String nombre = DS_SqlUtils.queryForObject(String.class, "select $" + estructura + "." + nombreColumna + "$ from $" + estructura + "$ C WHERE C.$" + estructura + "." + nombreColumna + "$ = $S("+codigo+")$");
		return nombre;
	}
	
	private String obtenerNombrePais(String codigo, String estructura, String nombreColumna, String codigoSosc ){
		String nombre = DS_SqlUtils.queryForObject(String.class, "select $" + estructura + "." + nombreColumna + "$ from $" + estructura + "$ WHERE $" + estructura + "." + codigoSosc + "$ = $I("+codigo+")$");
		return nombre;
	}
	
	public String obtenerCodigoLugarPorId(String id, String estructura, String nombreColumna){
		String codigo = DS_SqlUtils.queryForObject(String.class, "select $" + estructura + "." + nombreColumna + "$ from $" + estructura + "$  WHERE ID = "+id+"");
		return codigo;
	}
	
	public String obtenerNombreDepartamento(String id, String estructura, String nombreColumna, String codigoSosc){
		String codigo = DS_SqlUtils.queryForObject(String.class, "select $" + estructura + "." + nombreColumna + "$ from $" + estructura + "$  WHERE $" + estructura + "." + codigoSosc + "$ = $I("+id+")$");
		return codigo;
	}
	
	public String obtenerNombreMunicipio(
		    String codigoDepartamento, 
		    String estructura,         
		    String nombreColumnaNombreMunicipio, 
		    String nombreColumnaCodigoDepartamento, 
		    String nombreColumnaCodigoPostalMunicipio, 
		    String codigoMunicipioPostal 
		){
		    String mun = DS_SqlUtils.queryForObject(String.class,
		        "select $" + estructura + "." + nombreColumnaNombreMunicipio + "$ " +
		        "from $" + estructura + "$ " +
		        "WHERE $" + estructura + "." + nombreColumnaCodigoDepartamento + "$ = " + codigoDepartamento + " " + 
		        "AND $" + estructura + "." + nombreColumnaCodigoPostalMunicipio + "$ = $I(" + codigoMunicipioPostal + ")$"); 
		    return mun;
		}
	
//	public String obtenerIdentificacionPorCodigo(String codigo, String estructura, String nombreColumna, String nombreId){
//		String id = DS_SqlUtils.queryForObject(String.class, "select $" + estructura + "." + nombreColumna + "$ from $" + estructura + "$ WHERE $" + estructura + "." + nombreId + "$ = $I("+codigo+")$");
//		return id;
//	}
	
	public String obtenerIdentificacionPorNombre(String codigo, String estructura, String nombreColumna, String nombreId){
		String id = DS_SqlUtils.queryForObject(String.class, "select $" + estructura + "." + nombreColumna + "$ from $" + estructura + "$ WHERE $" + estructura + "." + nombreId + "$ = $I("+codigo+")$");
		return id;
	}
		
	/**
	 * Se envia informacion de solicitud a BPM
	 * 
	 * @param id_carga
	 * @param procesoRadicacion
	 * @return
	 */
	public WSData actualizarPanelProcesoRadicacionWeb(Integer id_carga, ProcesoRadicacionDto procesoRadicacion) {
		
		CallPage call = new CallPage();
		
		WSData integracion = new WSData();
		integracion.setId_carga(id_carga);
		integracion.setIntegracion(Constantes.WS_INTEGRACION_BPM_SOLICITUD_INICIAL);
		integracion.setSistema(Constantes.SSOC_SISTEMA_BPM);
		
		try {
			String token = generarToken(integracion);
		
			Map<String, String> headers = setupHeaders();
			headers.put("Authorization", token);
		
			String endpoint = getEndPoint("bpm.endpoint") + getEndPoint("bpm.api") + "BPMWP/ActualizarPanelProcesoRadicacionWeb";
		
			String json_headers = gson.toJson(headers);
			String content_unicode = new JSONObject(gson.toJson(procesoRadicacion)).toString();
			
			logger.debug("actualizarPanelProcesoRadicacionWeb request solicitud " + id_carga + " - headers " + json_headers + " - content " + content_unicode);
			integracion.setRequest("Headers: \n " + json_headers + "\n" + "Body: \n " + content_unicode);

			String response = call.call(endpoint, content_unicode, headers, "POST");
			
			logger.debug("actualizarPanelProcesoRadicacionWeb response solicitud " + id_carga + ": " + response);
			integracion.setResponse(response);
			
			return integracion;
		} catch (Throwable e) {
			logger.debug("BpmServicios.actualizarPanelProcesoRadicacionWeb: Solicitud " + id_carga, e);
			String error = ExceptionUtils.getStackTrace(e);
			integracion.setResponse("Error generado \n " + error);
			return integracion; 
		}
		
	}
		
	public WSData enviarProcesoActuacionABPM(Integer id_carga) {
		
		ProcesoActuacionesDto procesoActuacion = getProcesoActuaciones(id_carga);
		CallPage call = new CallPage();
		
		WSData integracion = new WSData();
		integracion.setId_carga(id_carga);
		integracion.setIntegracion(Constantes.WS_INTEGRACION_BPM_RTA_REQ);
		integracion.setSistema(Constantes.SSOC_SISTEMA_BPM);
		
		try {
			String token = generarToken(integracion);
		
			Map<String, String> headers = setupHeaders();
			headers.put("Authorization", token);
		
			String endpoint = getEndPoint("bpm.endpoint") + getEndPoint("bpm.api") + "BPMWP/RecibirActuacionProcesoExistente";
		
			String json_headers = gson.toJson(headers);
			String content_unicode = new JSONObject(gson.toJson(procesoActuacion)).toString();
			
			logger.debug("enviarProcesoActuacionABPM request solicitud " + id_carga + " - headers " + json_headers + " - content " + content_unicode);
			integracion.setRequest("Headers: \n " + json_headers + "\n" + "Body: \n " + content_unicode);
			
			String response = call.call(endpoint, content_unicode, headers, "POST");
			logger.debug("enviarProcesoActuacionABPM response solicitud "+ id_carga + ": " + response);
			integracion.setResponse(response);
			
			return integracion;
		
		} catch (Throwable e) {
			logger.error("BpmServicios.enviarProcesoActuacionABPM: Solicitud " + id_carga, e);
			String error = ExceptionUtils.getStackTrace(e);
			integracion.setResponse("Error generado \n " + error);
			return integracion; 
		}
	}
	
	/**
	 * Permite recrear la informacion enviada a bpm para registrar una solicitud de otros procesos.
	 * Usado para test
	 * 
	 * @param idCarga
	 */
	public String consultarInfoRegistroRegimenInsolvencia(int idCarga){
		ProcesoRadicacionDto procesoRadicacion = null;
		String infoRegistro = "";
		try {
			procesoRadicacion = getProcesoRadicacionRegimenInsolvencia(idCarga);
			infoRegistro = new Gson().toJson(procesoRadicacion);
			
		}catch (Exception e) {
			SimpleLogger.setError("Error al consultar informacion de registro", e);
		}
		return infoRegistro; 
	}
	
	public String consultarInfoNear(int idCarga){
		ProcesoRadicacionDto procesoRadicacion = null;
		String infoRegistro = "";

		try {
			
			procesoRadicacion = getProcesoRadicacionNear(idCarga);
			infoRegistro = new Gson().toJson(procesoRadicacion);
			
		}catch (Exception e) {
			SimpleLogger.setError("Error al consultar informacion de registro", e);
		}
		return infoRegistro; 
	}
	
	public String consultarInfoRtaReqNear(int idCarga){
		ProcesoRadicacionDto procesoRadicacion = null;
		String infoRegistro = "";

		try {
			
			procesoRadicacion = getProcesoRadicacionRtaReqNear(idCarga);
			infoRegistro = new Gson().toJson(procesoRadicacion);
			
		}catch (Exception e) {
			SimpleLogger.setError("Error al consultar informacion de registro", e);
		}
		return infoRegistro; 
	}
	
	public String consultarProcesoActuacionesRI(int idCarga){
		ProcesoActuacionesDto procesoActuacion = null;
		String infoRegistro = "";

		try {
			
			procesoActuacion = getProcesoActuaciones(idCarga);
			infoRegistro = new Gson().toJson(procesoActuacion);
			
		}catch (Exception e) {
			SimpleLogger.setError("Error al consultar informacion de registro", e);
		}
		return infoRegistro; 
	}
	
	private ProcesoRadicacionDto getProcesoRadicacionNear(Integer id_carga){
		ProcesoRadicacionDto procesoRadicacion = new ProcesoRadicacionDto();
		BpmServiciosQuerys querys = BpmServiciosQuerys.getInstance();
		Gson gson = new Gson();

		SolicitudNearSociedad solicitud = null;
		RadicacionAutoOficio radicacion = null;
		
		try {
			solicitud = SolicitudNearSociedadServicio.getInstance().obtenerSolicitudNearSociedadPorCarga(id_carga);
			radicacion = RadicacionAutoOficioServicio.getInstance().obtenerRadicacionAutoOficio(id_carga);
			
		}catch (Exception e) {
			SimpleLogger.setError("Error al consultar solicitud con id:" + id_carga + ". Descripción: ", e);
		}
		
		String dataSolicitud = gson.toJson(solicitud);
		logger.info("NEAR BPM SOLICITUD CARGA "+ id_carga + ": " + dataSolicitud);
		
		
		Carga carga = CargaServicio.getInstance().obtenerCarga(id_carga);
		String dataCarga = gson.toJson(carga);
		logger.info("NEAR BPM CARGA "+ id_carga + ": " + dataCarga);
		
		Sociedad deudor = null;
		if(null != solicitud && null != solicitud.getDeudor()){
			deudor = solicitud.getDeudor();
		}
		
		RelacionPasivos relacionPasivos = null;
		if(null != solicitud && null != solicitud.getRelacion_de_pasivos()){
			relacionPasivos = solicitud.getRelacion_de_pasivos();
		}
		
		InformacionFinanciera informacionFinanciera = null;
		if(null != solicitud && null != solicitud.getInformacion_financiera()){
			informacionFinanciera = solicitud.getInformacion_financiera();
		}

		Checklist checklist = null;
		if(null != solicitud && null != solicitud.getChecklist_solicitud()){
			checklist = solicitud.getChecklist_solicitud_obj();
		}
		
		TipoSolicitud tipoSolicitud = null;
		if(null != solicitud && null != solicitud.getTipo_solicitud()){
			tipoSolicitud = solicitud.getTipo_solicitud();
		}
		
		Integer tipoSolicitante = null;
		if(null != solicitud && null != solicitud.getTipo_solicitante()){
			tipoSolicitante = solicitud.getTipo_solicitante();
		}
		
		String estadoRadicadoId	= ParametrosInicio.getProperty("bpm.apvista.grupos.estado.radicado");
		String tipoDocumentoId	= ParametrosInicio.getProperty("bpm.apvista.documento_tipo.entrada.id"); //66
		
		PersonaMein datosBasicos = null != deudor.getDatos_basicos() ? deudor.getDatos_basicos() : null;
		PersonaMein representanteLegal = null != deudor.getRepresentante_legal() ? deudor.getRepresentante_legal() : null;
		PersonaMein contador = null != deudor.getContador() ? deudor.getContador() : null;
		PersonaMein revisorFiscal = null != deudor.getRevisor_fiscal() ? deudor.getRevisor_fiscal() : null;
		PersonaMein apoderado = null != deudor.getApoderado() ? deudor.getApoderado() : null;
		
		PaisDane paisDane = null != deudor.getPais_dane() ? deudor.getPais_dane_obj() : null;

		// Inicio de seteo de Información
		if(null != solicitud){
			procesoRadicacion.setNumeroProc(Objects.toString(solicitud.getNumero_proceso(),""));

			if(null != solicitud.getDependencia_obj()){
				procesoRadicacion.setDependenciaId(Objects.toString(solicitud.getDependencia_obj().getId_vista(), ""));
				procesoRadicacion.setDependenciaNombre(Objects.toString(solicitud.getDependencia_obj().getNombreDependencia(), ""));
			}
			if(null != solicitud.getProcesos_clases()){
				procesoRadicacion.setClaseProcesoId(Objects.toString(solicitud.getProcesos_clases_obj().getId_vista(), "")); //apvista proceso clase id 24
				procesoRadicacion.setClaseProcesoNombre(Objects.toString(solicitud.getProcesos_clases_obj().getNombreclase(), ""));
			}
			procesoRadicacion.setRadicadoInicial(Objects.toString(solicitud.getNumero_radicado_postal(), ""));
		} else {
			procesoRadicacion.setNumeroProc("");
			procesoRadicacion.setDependenciaId("");
			procesoRadicacion.setDependenciaNombre("");
			procesoRadicacion.setClaseProcesoId("");
			procesoRadicacion.setClaseProcesoNombre("");
			procesoRadicacion.setRadicadoInicial("");
		}
		
		if(null != carga){
			procesoRadicacion.setFechaInicio(Objects.toString(toFormatyyyyMMDdHHmmss(carga.getFecha_liberacion()),"")); //Fecha de liberacion. 
		} else {
			procesoRadicacion.setFechaInicio("");
		}
		
		if(null != datosBasicos){
			procesoRadicacion.setIdentificacion(Objects.toString(datosBasicos.getIdentificacion(),""));
			procesoRadicacion.setPersonaNombre(Objects.toString(datosBasicos.getNombre_completo(),""));
			procesoRadicacion.setPersonaDereccionNotificacion(Objects.toString(datosBasicos.getDireccion(),""));
			procesoRadicacion.setCorreo(Objects.toString(datosBasicos.getCorreo(),""));
			procesoRadicacion.setTelefono(Objects.toString(datosBasicos.getTelefono(),""));
			
			TipoDeDocumento tipoDeDocumento = MAPA_TIPOS_DOCUMENTO.get(datosBasicos.getTipo_identificacion());
			procesoRadicacion.setIDET_Id(Objects.toString(tipoDeDocumento.getId_postal(), ""));
			procesoRadicacion.setIDET_Nombre(Objects.toString(tipoDeDocumento.getNombre_postal(), ""));
			procesoRadicacion.setIDET_Codigo(Objects.toString(tipoDeDocumento.getCodigo_numerico_postal(), ""));
		} else {
			procesoRadicacion.setIdentificacion("");
			procesoRadicacion.setPersonaNombre("");
			procesoRadicacion.setPersonaDereccionNotificacion("");
			procesoRadicacion.setCorreo("");
			procesoRadicacion.setTelefono("");
			procesoRadicacion.setIDET_Id("");
			procesoRadicacion.setIDET_Nombre("");
			procesoRadicacion.setIDET_Codigo("");
		}
			
		if(null != paisDane){
			procesoRadicacion.setPAI_Codigo(paisDane.getCodigo_ssoc().toString());
		} else {
			procesoRadicacion.setPAI_Codigo("");
		}
		
		if(null != deudor && null != deudor.getDepartamentoObj()) {
			procesoRadicacion.setDEP_Codigo(Objects.toString(deudor.getDepartamentoObj().getCodigo_departamento(), "")); //11
		} else {
			procesoRadicacion.setDEP_Codigo("");
		}
		
//		String codigoMunicipio = "";
		if(null != deudor && null != deudor.getMunicipioObj()) {
			procesoRadicacion.setMUN_Codigo(Objects.toString(deudor.getMunicipioObj().getCodigoMunicipioPostal(), ""));
		} else {
			procesoRadicacion.setMUN_Codigo("");
		}
		
		// Apoderado tiene los mismos datos de ubicación de la empresa.
		procesoRadicacion.setApoderadoPaisCodigo(procesoRadicacion.getPAI_Codigo());
		procesoRadicacion.setApoderadoDepartamentoCodigo(procesoRadicacion.getDEP_Codigo());
		procesoRadicacion.setApoderadoCiudadCodigo(procesoRadicacion.getMUN_Codigo());
		procesoRadicacion.setApoderadoTelefono(procesoRadicacion.getTelefono());
		procesoRadicacion.setApoderadoEmail(procesoRadicacion.getCorreo());
		procesoRadicacion.setApoderadoDireccion(procesoRadicacion.getPersonaDereccionNotificacion());
		procesoRadicacion.setApoderadoTipoIdentificacionId("");
		procesoRadicacion.setApoderadoTipoIdentificacionNombre("");	
		procesoRadicacion.setApoderadoIdentificacion("");
		procesoRadicacion.setApoderadoNombre("");
		
		String tipoIdentificionApoderado = "";
		
		if(null != apoderado) {
			
			procesoRadicacion.setApoderadoNombre(Objects.toString(apoderado.getNombre_completo(), ""));
			procesoRadicacion.setApoderadoIdentificacion(Objects.toString(apoderado.getIdentificacion()));
			tipoIdentificionApoderado = apoderado.getTipo_identificacion();
			
		} else if(tipoSolicitante.equals(Constantes.TIPO_SOLICITANTE_SOCIEDAD) || tipoSolicitante.equals(Constantes.TIPO_SOLICITANTE_SUCURSALES_EXTRANJERAS)){
			procesoRadicacion.setApoderadoNombre(Objects.toString(representanteLegal.getNombre_completo(),""));
			procesoRadicacion.setApoderadoIdentificacion(Objects.toString(representanteLegal.getIdentificacion()));
			tipoIdentificionApoderado = Objects.toString(representanteLegal.getTipo_identificacion(),"");
		} else if (tipoSolicitante.equals(Constantes.TIPO_SOLICITANTE_PNC) || tipoSolicitante.equals(Constantes.TIPO_SOLICITANTE_PNNC)){
			procesoRadicacion.setApoderadoNombre(Objects.toString(datosBasicos.getNombre_completo(),""));
			procesoRadicacion.setApoderadoIdentificacion(Objects.toString(datosBasicos.getIdentificacion()));
			tipoIdentificionApoderado = Objects.toString(datosBasicos.getTipo_identificacion(),"");
		} 
		
		if(!tipoIdentificionApoderado.isEmpty()){
			TipoDeDocumento tipoDeDocumento = MAPA_TIPOS_DOCUMENTO.get(tipoIdentificionApoderado);
			procesoRadicacion.setApoderadoTipoIdentificacionId(tipoDeDocumento.getId_postal());
			procesoRadicacion.setApoderadoTipoIdentificacionNombre(tipoDeDocumento.getNombre_postal());	
		}	
		
		List<PropiedadesPartesDto> listaPropiedadesPartes = new ArrayList<PropiedadesPartesDto>();
		
		PropiedadesPartesDto partesDeudor = new PropiedadesPartesDto();
		
		if (null != datosBasicos){
			partesDeudor.setTipoIdenParteId(procesoRadicacion.getIDET_Id());
			partesDeudor.setTipoIdenParteNombre(procesoRadicacion.getIDET_Nombre());
			partesDeudor.setNmProcesoPartes(procesoRadicacion.getIdentificacion());
			partesDeudor.setRazonSocial(procesoRadicacion.getPersonaNombre()); 
			partesDeudor.setDirNotificacion(procesoRadicacion.getPersonaDereccionNotificacion());
			partesDeudor.setPaisId(procesoRadicacion.getPAI_Codigo());
			partesDeudor.setPaisNombre(paisDane.getNombre_ssoc());
			partesDeudor.setDepId(procesoRadicacion.getDEP_Codigo());
			partesDeudor.setDepNombre(deudor.getDepartamentoObj().getNombre_departamento());
			partesDeudor.setMunId(procesoRadicacion.getMUN_Codigo());
			partesDeudor.setMunNombre(deudor.getMunicipioObj().getNombre_ciudad());
			partesDeudor.setEmailNotificacion(procesoRadicacion.getCorreo());
			partesDeudor.setTelefono(procesoRadicacion.getTelefono());
			partesDeudor.setSeleccionId(ParametrosInicio.getProperty("bpm.apvista.grupos.deudor.id"));  // 3460
			partesDeudor.setSeleccionNombre(ApVistasGruposAcc.getInstance().getNombreForIdVistaAndGrupo(
					ParametrosInicio.getProperty("bpm.apvista.grupos.deudor.id"), ParametrosInicio.getProperty("bpm.apvista.grupos.parte_tipo.grupo"))); // (3460, "ParteTipo
		} else {
			partesDeudor.setNmProcesoPartes("");
			partesDeudor.setSeleccionId(ParametrosInicio.getProperty("bpm.apvista.grupos.deudor.id"));  // 3460
			partesDeudor.setSeleccionNombre(ApVistasGruposAcc.getInstance().getNombreForIdVistaAndGrupo(
					ParametrosInicio.getProperty("bpm.apvista.grupos.deudor.id"), ParametrosInicio.getProperty("bpm.apvista.grupos.parte_tipo.grupo"))); // (3460, "ParteTipo
			
		}		
		listaPropiedadesPartes.add(partesDeudor);

		
		// Parte Representante Legal
		// El representante legal toma la información de ubicación de la empresa (Dirección, tele´fono...)
		PropiedadesPartesDto partesRepresentanteLegal = new PropiedadesPartesDto();
		partesRepresentanteLegal.setSeleccionId(ParametrosInicio.getProperty("bpm.apvista.grupos.rep_legal.id"));  //4150
		partesRepresentanteLegal.setSeleccionNombre(ApVistasGruposAcc.getInstance().getNombreForIdVistaAndGrupo(
				ParametrosInicio.getProperty("bpm.apvista.grupos.rep_legal.id"), ParametrosInicio.getProperty("bpm.apvista.grupos.parte_tipo.grupo"))); // (4150, "ParteTipo
		listaPropiedadesPartes.add(partesRepresentanteLegal);
				
		if(null != representanteLegal) {
			TipoDeDocumento tipoDeDocumento = MAPA_TIPOS_DOCUMENTO.get(representanteLegal.getTipo_identificacion());
			if(null != tipoDeDocumento){
				partesRepresentanteLegal.setTipoIdenParteId(Objects.toString(tipoDeDocumento.getId_postal(), ""));
				partesRepresentanteLegal.setTipoIdenParteNombre(Objects.toString(tipoDeDocumento.getNombre_postal(), ""));
			} else {
				partesRepresentanteLegal.setTipoIdenParteId("");
				partesRepresentanteLegal.setTipoIdenParteNombre("");
			}
			
			partesRepresentanteLegal.setNmProcesoPartes(Objects.toString(representanteLegal.getIdentificacion(), ""));
			partesRepresentanteLegal.setRazonSocial(Objects.toString(representanteLegal.getNombre_completo(), ""));
			
			if(tipoSolicitante.equals(Constantes.TIPO_SOLICITANTE_SOCIEDAD) || tipoSolicitante.equals(Constantes.TIPO_SOLICITANTE_SUCURSALES_EXTRANJERAS)){
				partesRepresentanteLegal.setDirNotificacion(procesoRadicacion.getPersonaDereccionNotificacion());
				partesRepresentanteLegal.setPaisId(procesoRadicacion.getPAI_Codigo());
				partesRepresentanteLegal.setPaisNombre(paisDane.getNombre_ssoc());
				partesRepresentanteLegal.setDepId(procesoRadicacion.getDEP_Codigo());
				partesRepresentanteLegal.setDepNombre(deudor.getDepartamentoObj().getNombre_departamento());
				partesRepresentanteLegal.setMunId(procesoRadicacion.getMUN_Codigo());
				partesRepresentanteLegal.setMunNombre(deudor.getMunicipioObj().getNombre_ciudad()); 
				partesRepresentanteLegal.setEmailNotificacion(procesoRadicacion.getCorreo());
				partesRepresentanteLegal.setTelefono(procesoRadicacion.getTelefono());
			} else {
				partesRepresentanteLegal.setDirNotificacion("");
				partesRepresentanteLegal.setPaisId("");
				partesRepresentanteLegal.setPaisNombre("");
				partesRepresentanteLegal.setDepId("");
				partesRepresentanteLegal.setDepNombre("");
				partesRepresentanteLegal.setMunId("");
				partesRepresentanteLegal.setMunNombre("");
				partesRepresentanteLegal.setEmailNotificacion("");
				partesRepresentanteLegal.setTelefono("");
			}
		} else {
			partesRepresentanteLegal.setTipoIdenParteId("");
			partesRepresentanteLegal.setTipoIdenParteNombre("");
			partesRepresentanteLegal.setNmProcesoPartes("");
			partesRepresentanteLegal.setRazonSocial("");
			partesRepresentanteLegal.setDirNotificacion("");
			partesRepresentanteLegal.setPaisId("");
			partesRepresentanteLegal.setPaisNombre("");
			partesRepresentanteLegal.setDepId("");
			partesRepresentanteLegal.setDepNombre("");
			partesRepresentanteLegal.setMunId("");
			partesRepresentanteLegal.setMunNombre("");
			partesRepresentanteLegal.setEmailNotificacion("");
			partesRepresentanteLegal.setTelefono("");
		}
		
		// Partes Contador
		PropiedadesPartesDto partesContador = new PropiedadesPartesDto();
		
		partesContador.setPaisId("");
		partesContador.setPaisNombre("");
		partesContador.setDepId("");
		partesContador.setDepNombre("");
		partesContador.setMunId("");
		partesContador.setMunNombre("");
		partesContador.setSeleccionId(ParametrosInicio.getProperty("bpm.apvista.grupos.contador.id")); // 4467
		partesContador.setSeleccionNombre(ApVistasGruposAcc.getInstance().getNombreForIdVistaAndGrupo(
				ParametrosInicio.getProperty("bpm.apvista.grupos.contador.id"),
				ParametrosInicio.getProperty("bpm.apvista.grupos.parte_tipo.grupo")));

		if(null != contador) {
			partesContador.setNmProcesoPartes(Objects.toString(contador.getIdentificacion(), ""));
			partesContador.setRazonSocial(Objects.toString(contador.getNombre_completo(), ""));
			partesContador.setDirNotificacion(Objects.toString(contador.getDireccion(), ""));
			partesContador.setEmailNotificacion(Objects.toString(contador.getCorreo(), ""));
			partesContador.setTelefono(Objects.toString(contador.getTelefono(), ""));
			partesContador.setPaisId(obtenerCodigoLugarPorId(contador.getPais_id().toString(), "PAISES DANE", "CODIGO SSOC"));
			partesContador.setPaisNombre(obtenerNombrePais(partesContador.getPaisId().toString(), "PAISES DANE", "NOMBRE SSOC", "CODIGO SSOC"));
			partesContador.setDepId(obtenerCodigoLugarPorId(contador.getDepartamento_id().toString(), "DEPARTAMENTOS DANE", "CODIGO DEPARTAMENTO"));
			partesContador.setDepNombre(obtenerNombreDepartamento(partesContador.getDepId().toString(), "DEPARTAMENTOS DANE", "NOMBRE DEPARTAMENTO", "CODIGO DEPARTAMENTO"));
			partesContador.setMunId(obtenerCodigoLugarPorId(contador.getMunicipio_id().toString(), "MUNICIPIOS DANE", "CODIGOMUNICIPIOPOSTAL"));
			partesContador.setMunNombre(obtenerNombreMunicipio(contador.getDepartamento_id().toString(),  
					"MUNICIPIOS DANE",
				    "NOMBRE CIUDAD",
				    "CODIGO DEPARTAMENTO",
				    "CODIGOMUNICIPIOPOSTAL", partesContador.getMunId().toString()));
			TipoDeDocumento tipoDeDocumento = MAPA_TIPOS_DOCUMENTO.get(contador.getTipo_identificacion());
			if(null != tipoDeDocumento){
				partesContador.setTipoIdenParteId(Objects.toString(tipoDeDocumento.getCodigo_numerico_postal(), ""));
				partesContador.setTipoIdenParteNombre(Objects.toString(tipoDeDocumento.getNombre_postal(), ""));
			} else {
				partesContador.setTipoIdenParteId("");
				partesContador.setTipoIdenParteNombre("");
			}
		} else {
			partesContador.setTipoIdenParteId("");
			partesContador.setTipoIdenParteNombre("");
			partesContador.setNmProcesoPartes("");
			partesContador.setRazonSocial("");
			partesContador.setDirNotificacion("");
			partesContador.setEmailNotificacion("");
			partesContador.setTelefono("");
		}

		listaPropiedadesPartes.add(partesContador);

		// Partes Revisor Fiscal
		PropiedadesPartesDto partesRevFiscal = new PropiedadesPartesDto();
		partesRevFiscal.setPaisId("");
		partesRevFiscal.setPaisNombre("");
		partesRevFiscal.setDepId("");
		partesRevFiscal.setDepNombre("");
		partesRevFiscal.setMunId("");
		partesRevFiscal.setMunNombre("");
		partesRevFiscal.setSeleccionId(ParametrosInicio.getProperty("bpm.apvista.grupos.rev_fiscal.id")); // 3471
		partesRevFiscal.setSeleccionNombre(ApVistasGruposAcc.getInstance().getNombreForIdVistaAndGrupo(
				ParametrosInicio.getProperty("bpm.apvista.grupos.rev_fiscal.id"),
				ParametrosInicio.getProperty("bpm.apvista.grupos.parte_tipo.grupo")));
				
		if(null != revisorFiscal) {
			partesRevFiscal.setNmProcesoPartes(Objects.toString(revisorFiscal.getIdentificacion(), ""));
			partesRevFiscal.setRazonSocial(Objects.toString(revisorFiscal.getNombre_completo(), ""));
			partesRevFiscal.setDirNotificacion(Objects.toString(revisorFiscal.getDireccion(), ""));
			partesRevFiscal.setEmailNotificacion(Objects.toString(revisorFiscal.getCorreo(), ""));
			partesRevFiscal.setTelefono(Objects.toString(revisorFiscal.getTelefono(), ""));
			partesRevFiscal.setPaisId(obtenerCodigoLugarPorId(revisorFiscal.getPais_id().toString(), "PAISES DANE", "CODIGO SSOC"));
			partesRevFiscal.setPaisNombre(obtenerNombrePais(partesRevFiscal.getPaisId().toString(), "PAISES DANE", "NOMBRE SSOC", "CODIGO SSOC"));
			partesRevFiscal.setDepId(obtenerCodigoLugarPorId(revisorFiscal.getDepartamento_id().toString(), "DEPARTAMENTOS DANE", "CODIGO DEPARTAMENTO"));
			partesRevFiscal.setDepNombre(obtenerNombreDepartamento(partesRevFiscal.getDepId().toString(), "DEPARTAMENTOS DANE", "NOMBRE DEPARTAMENTO", "CODIGO DEPARTAMENTO"));
			partesRevFiscal.setMunId(obtenerCodigoLugarPorId(revisorFiscal.getMunicipio_id().toString(), "MUNICIPIOS DANE", "CODIGOMUNICIPIOPOSTAL"));
			partesRevFiscal.setMunNombre(obtenerNombreMunicipio(revisorFiscal.getDepartamento_id().toString(),  
					"MUNICIPIOS DANE",
				    "NOMBRE CIUDAD",
				    "CODIGO DEPARTAMENTO",
				    "CODIGOMUNICIPIOPOSTAL", partesRevFiscal.getMunId().toString()));
		
			TipoDeDocumento tipoDeDocumento = MAPA_TIPOS_DOCUMENTO.get(revisorFiscal.getTipo_identificacion());
			if(null != tipoDeDocumento){
				partesRevFiscal.setTipoIdenParteId(Objects.toString(tipoDeDocumento.getCodigo_numerico_postal(), ""));
				partesRevFiscal.setTipoIdenParteNombre(Objects.toString(tipoDeDocumento.getNombre_postal(), ""));
			} else {
				partesRevFiscal.setTipoIdenParteId("");
				partesRevFiscal.setTipoIdenParteNombre("");
			}
			
		} else {
			partesRevFiscal.setTipoIdenParteId("");
			partesRevFiscal.setTipoIdenParteNombre("");
			partesRevFiscal.setNmProcesoPartes("");
			partesRevFiscal.setRazonSocial("");
			partesRevFiscal.setDirNotificacion("");
			partesRevFiscal.setEmailNotificacion("");
			partesRevFiscal.setTelefono("");
			
		}
		listaPropiedadesPartes.add(partesRevFiscal);

		procesoRadicacion.setListaPropiedadesPartes(listaPropiedadesPartes);

		// Actuaciones
		PropiedadesProcesoActuacionesDto procesoEntrada = new PropiedadesProcesoActuacionesDto();

		procesoEntrada.setEstadoId(estadoRadicadoId); // 3138
		procesoEntrada.setEstadoNombre(ApVistasGruposAcc.getInstance().getNombreForIdVistaAndGrupo(estadoRadicadoId, "ActuacionEstado"));
		
		if(null != solicitud) {
			procesoEntrada.setNumeroRadicado(Objects.toString(solicitud.getNumero_radicado_postal(), ""));
		} else { 
			procesoEntrada.setNumeroRadicado("");
		}
		
		if(null != carga.getFecha_liberacion()) {	
			procesoEntrada.setFechaHora(toFormatyyyyMMDdHHmmss(carga.getFecha_liberacion()));
		} else {
			procesoEntrada.setFechaHora("");
		}

		APVistaTramite tramite = null != solicitud.getTramite() ? solicitud.getTramite_obj() : null;
		procesoEntrada.setTramite(tramite.getNombreTramite());
		procesoEntrada.setTramiteCod(tramite.getIdPostal().toString());
		
		APVistaMedioEnvio medioEnvio = null != solicitud.getMedio_envio() ? solicitud.getMedio_envio_obj() : null;
		procesoEntrada.setMedioEnvio(medioEnvio.getMedio_envio()); //Portal Web
		procesoEntrada.setMedioEnvioCod(medioEnvio.getCodigo().toString());
		
		APVistaSeguridadTipo tipoSeguridad = null != solicitud.getTipo_seguridad() ? solicitud.getTipo_seguridad_obj() : null;
		procesoEntrada.setTipoSeguridad(tipoSeguridad.getSeguridadTipo()); 
		procesoEntrada.setTipoSeguridadCodTxt(tipoSeguridad.getCodigoAlfanumerico());
		
		APVistaCuaderno cuaderno = null != solicitud.getCuaderno() ? solicitud.getCuaderno_obj() : null;
		procesoEntrada.setCuaderno(cuaderno.getCuadernoTipo());
		procesoEntrada.setCuadernoCod(cuaderno.getCodigo());
		
		APVistaDependencias dependencia = null != solicitud.getDependencia() ? solicitud.getDependencia_obj() : null;
		procesoEntrada.setDependenciaID(Objects.toString(dependencia.getCodigo()));
		
		APVistaDocumentoTipo documentoTipo = querys.getDocumentoTipoPorIdVista(tipoDocumentoId);
		procesoEntrada.setTipoDocumento(documentoTipo.getDocumento_tipo()); 
		procesoEntrada.setTipoDocumentoCod(documentoTipo.getCodigo_numerico().toString()); 
//		procesoUno.setTipoDocumentoId(documentoTipo.getId_vista().toString());
		procesoEntrada.setTipoDocEntradaSalida(Constantes.BPM_TIPO_DOCUMENTO_ENTRADA); 

		procesoEntrada.setIDSolicitudMI(id_carga.toString());
		
		if (solicitud.getTipo_auto_acta_obj() != null) {
			procesoEntrada.setTipoActuacionID(Objects.toString(solicitud.getTipo_auto_acta_obj().getId_bpm(),""));
			procesoEntrada.setTipoActuacion(Objects.toString(solicitud.getTipo_auto_acta_obj().getTipo_actuacion(),""));
		} else {
			procesoEntrada.setTipoActuacionID("");
			procesoEntrada.setTipoActuacion("");
		}
		
		PropiedadesProcesoActuacionesDto procesoSalida = new PropiedadesProcesoActuacionesDto();
		
		procesoSalida.setEstadoId(estadoRadicadoId); // 3138
		procesoSalida.setEstadoNombre(ApVistasGruposAcc.getInstance().getNombreForIdVistaAndGrupo(estadoRadicadoId, "ActuacionEstado"));
		
		if(null != radicacion) {
			
			procesoSalida.setNumeroRadicado(Objects.toString(radicacion.getNumeroRadicado(), ""));
			procesoSalida.setFechaHora(toFormatyyyyMMDdHHmmss(radicacion.getFechaRadicado()));
		
			APVistaTramite tramiteSalida = radicacion.getTramite();
			
			if (tramiteSalida != null) {
				procesoSalida.setTramite(tramiteSalida.getNombreTramite());
				procesoSalida.setTramiteCod(tramiteSalida.getIdPostal().toString());
			}
			
			APVistaMedioEnvio medioEnvioSalida = radicacion.getCodigoMedioEnvio();
			
			if (medioEnvioSalida != null) {
				procesoSalida.setMedioEnvio(medioEnvioSalida.getMedio_envio());
				procesoSalida.setMedioEnvioCod(medioEnvioSalida.getCodigo().toString());				
			}
			
			APVistaSeguridadTipo tipoSeguridadSalida = radicacion.getCodigoTipoSeguridad();
			
			if (tipoSeguridadSalida != null) {
				procesoSalida.setTipoSeguridad(tipoSeguridadSalida.getSeguridadTipo()); 
				procesoSalida.setTipoSeguridadCodTxt(tipoSeguridadSalida.getCodigoAlfanumerico());				
			}
			
			APVistaCuaderno cuadernoSalida = radicacion.getCuadernoTipo();
			
			if (cuadernoSalida != null) {
				procesoSalida.setCuaderno(cuadernoSalida.getCuadernoTipo());
				procesoSalida.setCuadernoCod(cuadernoSalida.getCodigo());				
			}
			
			APVistaDependencias dependenciaSalida = radicacion.getDependencia();
			
			if (dependenciaSalida != null) {
				procesoSalida.setDependenciaID(Objects.toString(dependenciaSalida.getCodigo()));				
			}
			
			APVistaDocumentoTipo documentoTipoSalida = radicacion.getDocumentalTipo();
			
			if (documentoTipoSalida != null) {
				procesoSalida.setTipoDocumento(documentoTipoSalida.getDocumento_tipo()); 
				procesoSalida.setTipoDocumentoCod(documentoTipoSalida.getCodigo_numerico().toString());				
			}
			
			if (radicacion.getTipoAutoActa() != null) {
				procesoSalida.setTipoActuacionID(Objects.toString(radicacion.getTipoAutoActa().getId_bpm(),""));
				procesoSalida.setTipoActuacion(Objects.toString(radicacion.getTipoAutoActa().getTipo_actuacion(),""));
			} else {
				procesoSalida.setTipoActuacionID("");
				procesoSalida.setTipoActuacion("");
			}
			
			procesoSalida.setTipoDocEntradaSalida(Constantes.BPM_TIPO_DOCUMENTO_SALIDA);
		} 
		
				
		List<PropiedadesProcesoActuacionesDto> listaPropiedadesProceso = new ArrayList<PropiedadesProcesoActuacionesDto>();
		listaPropiedadesProceso.add(procesoEntrada);
		listaPropiedadesProceso.add(procesoSalida);
		
		procesoRadicacion.setListaPropiedadesProcesoActuaciones(listaPropiedadesProceso);
		
		// Campos adicionales
		CamposAdicionalesDto camposAdicionales = new CamposAdicionalesDto();
		camposAdicionales.setInsTipoSolicitanteID(Objects.toString(tipoSolicitante.toString(), ""));
		camposAdicionales.setInsTipoSolicitante(Objects.toString(
				querys.getValorLista("tipo solicitante", tipoSolicitante.toString()), ""));
		if(null != deudor){
			camposAdicionales.setSEC_CodCIIU(Objects.toString(deudor.getActividad_economica(),""));
			if(null != deudor.getMacrosector()){
				camposAdicionales.setProc_Macrosector(Objects.toString(deudor.getMacrosectorAPVista().getNombre(), ""));
				camposAdicionales.setProc_MacrosectorID(Objects.toString(deudor.getMacrosectorAPVista().getId_vista(), ""));
			} else {
				camposAdicionales.setProc_Macrosector("");
				camposAdicionales.setProc_MacrosectorID("");
			}
			if(null != deudor.getNaturaleza()){
				ApVistaNaturalezaDto vistaNaturaleza = ApVistaNaturalezaAcc.getInstance().getPorLista(
								deudor.getNaturaleza().toString());
				camposAdicionales.setProc_Naturaleza(vistaNaturaleza.getNombre());
				camposAdicionales.setProc_NaturalezaID(vistaNaturaleza.getId_vista());
				
			} else {
				camposAdicionales.setProc_Naturaleza("");
				camposAdicionales.setProc_NaturalezaID("");
			}
			camposAdicionales.setProc_PorcPartEst(Objects.toString(deudor.getPorcentaje_participacion(), "0.0"));
			camposAdicionales.setProc_NumEmplMujeres(Objects.toString(deudor.getTrabajadores_mujeres(), ""));
			camposAdicionales.setProc_NumEmplHombres(Objects.toString(deudor.getTrabajadores_hombres(), ""));
			
			camposAdicionales.setCategoriaSociedadPersona(Objects.toString(deudor.getCategoria(),""));
		}
		
		if(null != solicitud.getSituacion_presentada()){
			ApVistaSupuestoDto vistaSupuesto = ApVistaSupuestoAcc.getInstance().getPorLista(
					solicitud.getSituacion_presentada().toString());
			camposAdicionales.setProc_Supuesto(vistaSupuesto.getNombre());
			camposAdicionales.setProc_SupuestoID(vistaSupuesto.getId_vista());
		} else {
			camposAdicionales.setProc_Supuesto("");
			camposAdicionales.setProc_SupuestoID("");
		}
		
		if(null != solicitud.getAdel_acreedores()){
			camposAdicionales.setProc_AdelAcreedores(solicitud.getAdel_acreedores_obj().getNombre());
			camposAdicionales.setProc_AdelAcreedoresID(solicitud.getAdel_acreedores_obj().getId().toString());
		} else {
			camposAdicionales.setProc_AdelAcreedores("");
			camposAdicionales.setProc_AdelAcreedoresID("");
		}
		
		if(null != relacionPasivos){
			camposAdicionales.setProc_PasFavorAutFiscales(Objects.toString(relacionPasivos.getPasivos_por_retenciones(), ""));
			camposAdicionales.setProc_PasDesTrabajadores(Objects.toString(relacionPasivos.getPasivos_por_descuentos(), ""));
			camposAdicionales.setProc_PasAportesSeguiridad(Objects.toString(relacionPasivos.getPasivos_por_aportes(), ""));
		} else {
			camposAdicionales.setProc_PasFavorAutFiscales("");
			camposAdicionales.setProc_PasDesTrabajadores("");
			camposAdicionales.setProc_PasAportesSeguiridad("");
		}
		
		if (null != informacionFinanciera){
			camposAdicionales.setProc_Activos(Objects.toString(informacionFinanciera.getValor_activos(), ""));
			camposAdicionales.setProc_Pasivos(Objects.toString(informacionFinanciera.getValor_pasivos(), ""));
			camposAdicionales.setProc_Patrimonio(Objects.toString(informacionFinanciera.getValor_patrimonio(), ""));
			camposAdicionales.setProc_IngresosOrdinarios(Objects.toString(informacionFinanciera.getTotal_ingresos_ordinarios(), ""));
			camposAdicionales.setProc_OtrosIngresos(Objects.toString(informacionFinanciera.getTotal_otros_ingresos(), ""));
			camposAdicionales.setProc_Inversiones(Objects.toString(informacionFinanciera.getTiene_inversiones(), ""));
			camposAdicionales.setProc_GananciasMetPArticipacion(Objects.toString(informacionFinanciera.getValor_p_participacion(), ""));
			camposAdicionales.setProc_GananciasMetCosto(Objects.toString(informacionFinanciera.getValor_p_costo(), ""));
			camposAdicionales.setProc_GananciasMetValorRazonable(Objects.toString(informacionFinanciera.getValor_p_razonable(), ""));
			camposAdicionales.setFechaEstadosFinancieros(Objects.toString(informacionFinanciera.getFecha_eeff_anio_anterior(), ""));
			camposAdicionales.setProc_FechaEstadosMesAnterior(Objects.toString(informacionFinanciera.getFecha_estados_financieros(), ""));
			camposAdicionales.setProc_ActivosAnt(Objects.toString(informacionFinanciera.getValor_activos_ultimoanio(), "")); 
			camposAdicionales.setProc_PasivosAnt(Objects.toString(informacionFinanciera.getValor_pasivos_ultimoanio(), "")); 
			camposAdicionales.setProc_RadEstadosFinNmenos1(Objects.toString(
					informacionFinanciera.getUltimo_radicado_dictamen(), ""));
			camposAdicionales.setProc_RadEstadosFinNmenos2(Objects.toString(
					informacionFinanciera.getPenultimo_radicado_dictamen(), ""));
			camposAdicionales.setProc_RadEstadosFinNmenos3(Objects.toString(
					informacionFinanciera.getAntepenultimo_radicado_dictamen(), ""));
			camposAdicionales.setFechaRelBienesAcciones(Objects.toString(informacionFinanciera.getFecha_r_bienes_acreedores(), ""));
			
		} else {
			camposAdicionales.setProc_Activos("");
			camposAdicionales.setProc_Pasivos("");
			camposAdicionales.setProc_Patrimonio("");
			camposAdicionales.setProc_IngresosOrdinarios("");
			camposAdicionales.setProc_OtrosIngresos("");
			camposAdicionales.setProc_Inversiones("");
			camposAdicionales.setProc_GananciasMetPArticipacion("");
			camposAdicionales.setProc_GananciasMetCosto("");
			camposAdicionales.setProc_GananciasMetValorRazonable("");
			camposAdicionales.setFechaEstadosFinancieros("");
			camposAdicionales.setProc_FechaEstadosMesAnterior("");
			camposAdicionales.setProc_ActivosAnt("");
			camposAdicionales.setProc_PasivosAnt("");
			camposAdicionales.setProc_RadEstadosFinNmenos1("");
			camposAdicionales.setProc_RadEstadosFinNmenos2("");
			camposAdicionales.setProc_RadEstadosFinNmenos3("");
			camposAdicionales.setFechaRelBienesAcciones("");
		}

		if(null != tipoSolicitud){

			if (null != tipoSolicitud.getGrupo_niif()) {
				try {
					String grupoString = ApVistasGrupoNiifAcc.getInstance().getPorId(tipoSolicitud.getGrupo_niif());
					ApVistaGrupoNiifDto grupoJson = gson.fromJson(grupoString, ApVistaGrupoNiifDto.class);
					camposAdicionales.setProc_GrupoNIIF(grupoJson.getNombre());
					camposAdicionales.setProc_GrupoNIIFID(grupoJson.getId_vista());
				} catch (Exception e) {
					SimpleLogger.setError(
							"Error al consultar la vista APVISTA GRUPO NIIF con id:"
									+ tipoSolicitud.getGrupo_niif() + ". Descripcion: ", e);
				}
			} else {
				camposAdicionales.setProc_GrupoNIIF("");
				camposAdicionales.setProc_GrupoNIIFID("");
			}
		} else {
			camposAdicionales.setProc_GrupoNIIF("");
			camposAdicionales.setProc_GrupoNIIFID("");
		}
		
		if(null != checklist){
			camposAdicionales.setProc_Causal(Objects.toString(checklist.getSociedad_en_disolucion(), ""));
			if(tipoSolicitante.equals(Constantes.TIPO_SOLICITANTE_SOCIEDAD) || tipoSolicitante.equals(Constantes.TIPO_SOLICITANTE_SUCURSALES_EXTRANJERAS)){
				camposAdicionales.setProc_PasPen(Objects.toString(checklist.getSociedad_pasivos_pensiona(), ""));
			} else {
				camposAdicionales.setProc_PasPen(Objects.toString(checklist.getPasivos_pensionales(), ""));
			}
			camposAdicionales.setProc_Garante(Objects.toString(checklist.getSociedad_garante_codeudor(), ""));
			camposAdicionales.setProc_BienesSuj(Objects.toString(checklist.getGarantias_mobiliarias(), ""));
			camposAdicionales.setProc_InfoPrevia(Objects.toString(checklist.getRemision_previa_info(), ""));
			
			//Controlante
			camposAdicionales.setProc_ControlaSociedadReorg(Objects.toString(checklist.getSolicitante_controlante(), ""));
			if("1".equals(camposAdicionales.getProc_ControlaSociedadReorg())){
				camposAdicionales.setProc_NITSociedadControlada(Objects.toString(deudor.getNit_sociedad_controlada(), ""));
				camposAdicionales.setProc_NombreSociedadControlada(Objects.toString(deudor.getName_sociedad_controlada(), ""));
				camposAdicionales.setNumRadSolAutoAdm(Objects.toString(deudor.getRadicado_sociedad_controlada(), "")); // TODO Falta implementar en formulario y db. 
			} else {
				camposAdicionales.setProc_NITSociedadControlada("");
				camposAdicionales.setProc_NombreSociedadControlada("");
				camposAdicionales.setNumRadSolAutoAdm("");
			}
		} else {
			camposAdicionales.setProc_Causal("");
			camposAdicionales.setProc_PasPen("");
			camposAdicionales.setProc_PasPen("");
			camposAdicionales.setProc_Garante("");
			camposAdicionales.setProc_BienesSuj("");
			camposAdicionales.setProc_InfoPrevia("");
			camposAdicionales.setProc_ControlaSociedadReorg("");
			camposAdicionales.setProc_NITSociedadControlada("");
			camposAdicionales.setProc_NombreSociedadControlada("");
			camposAdicionales.setNumRadSolAutoAdm("");
		}
		camposAdicionales.setProc_NombrePRES("");
		camposAdicionales.setProc_NomMediador("");
		camposAdicionales.setProc_ValBiePres("");
		camposAdicionales.setProc_ValOblPres("");
		camposAdicionales.setProc_IdSolicitud(id_carga.toString());
		
		procesoRadicacion.setCamposAdicionales(camposAdicionales);

		return procesoRadicacion;
	}

	private ProcesoRadicacionDto getProcesoRadicacionRegimenInsolvencia(
			Integer id_carga) {

		ProcesoRadicacionDto procesoRadicacion = new ProcesoRadicacionDto();

		RegimenInsolvencia infoRegimenInsolvencia = RegimenInsolvenciaFetchBuilder
				.newInstance()
				.obtenerInfoRegimenInsolvenciaPorIdCarga(id_carga);

		BpmServiciosQuerys querys = BpmServiciosQuerys.getInstance();
		Gson gson = new Gson();

		Carga carga = CargaServicio.getInstance().obtenerCarga(id_carga);

		procesoRadicacion.setNumeroProc(Objects.toString(
				infoRegimenInsolvencia.getNumero_proceso(), ""));
		procesoRadicacion.setDependenciaId(infoRegimenInsolvencia
				.getDependencia().getId_vista().toString());
		procesoRadicacion.setDependenciaNombre(infoRegimenInsolvencia
				.getDependencia().getNombreDependencia());
		if (null != infoRegimenInsolvencia.getProcesos_clases_id()) {
			procesoRadicacion.setClaseProcesoId(infoRegimenInsolvencia
					.getProcesos_clases().getId_vista());
			procesoRadicacion.setClaseProcesoNombre(infoRegimenInsolvencia
					.getProcesos_clases().getNombreclase());
		} else {
			procesoRadicacion.setClaseProcesoId("");
			procesoRadicacion.setClaseProcesoNombre("");
		}

		procesoRadicacion.setFechaInicio(toFormatyyyyMMDdHHmmss(carga
				.getFecha_liberacion()));

		procesoRadicacion.setIdentificacion(infoRegimenInsolvencia.getDeudor()
				.getNumero_identificacion());
		procesoRadicacion.setPersonaNombre(infoRegimenInsolvencia.getDeudor()
				.getRazon_social());
		procesoRadicacion
				.setPersonaDereccionNotificacion(infoRegimenInsolvencia
						.getDeudor().getDireccion());
		procesoRadicacion.setCorreo(infoRegimenInsolvencia.getDeudor()
				.getCorreo_electronico());
		procesoRadicacion.setTelefono(infoRegimenInsolvencia.getDeudor()
				.getTelefono());
		if (null != infoRegimenInsolvencia.getDeudor()
				&& null != infoRegimenInsolvencia.getDeudor().getPais_dane_id()
				&& null != infoRegimenInsolvencia.getDeudor().getPais_dane()) {
			procesoRadicacion.setPAI_Codigo(infoRegimenInsolvencia.getDeudor()
					.getPais_dane().getCodigo_ssoc().toString()); // 80
		} else {
			procesoRadicacion.setPAI_Codigo("");
		}
		procesoRadicacion.setDEP_Codigo(infoRegimenInsolvencia.getDeudor()
				.getDepartamento().getCodigo_departamento().toString()); // 11
		String municipio = "";
		if (infoRegimenInsolvencia.getDeudor().getMunicipio().getId() != null) {
			municipio = Objects
					.toString(querys
							.getCodigoMunicipioPorId(infoRegimenInsolvencia
									.getDeudor().getMunicipio().getId()
									.toString()), "");
			procesoRadicacion.setMUN_Codigo(municipio); // 1
		} else {
			procesoRadicacion.setMUN_Codigo("");
		}

		if (infoRegimenInsolvencia.getDeudor().getTipo_identificacion_id() != null) {
			procesoRadicacion.setIDET_Codigo(infoRegimenInsolvencia.getDeudor()
					.getTipo_identificacion().getCodigo_numerico_postal()
					.toString()); //
			procesoRadicacion.setIDET_Nombre(infoRegimenInsolvencia.getDeudor()
					.getTipo_identificacion().getNombre_postal());
			procesoRadicacion.setIDET_Id(infoRegimenInsolvencia.getDeudor()
					.getTipo_identificacion().getId_postal());
		} else {
			procesoRadicacion.setIDET_Codigo(""); //
			procesoRadicacion.setIDET_Nombre("");
			procesoRadicacion.setIDET_Id("");
		}
		procesoRadicacion.setRadicadoInicial(Objects.toString(
				infoRegimenInsolvencia.getNumero_radicado(), ""));

		Persona dst_personaApoderado = new Persona();
		dst_personaApoderado = llenarPersona();
		procesoRadicacion.setApoderadoTelefono(""
				+ infoRegimenInsolvencia.getDeudor().getTelefono());
		procesoRadicacion.setApoderadoDireccion(""
				+ infoRegimenInsolvencia.getDeudor().getDireccion());
		procesoRadicacion.setApoderadoDepartamentoCodigo(""
				+ infoRegimenInsolvencia.getDeudor().getDepartamento()
						.getCodigo_departamento());
		procesoRadicacion.setApoderadoCiudadCodigo(Objects.toString(
				procesoRadicacion.getMUN_Codigo(), ""));
		procesoRadicacion.setApoderadoPaisCodigo(""
				+ infoRegimenInsolvencia.getDeudor().getPais_dane()
						.getCodigo_ssoc());
		
		if(null != infoRegimenInsolvencia.getProfesionales_asociados_id() && null != infoRegimenInsolvencia.getProfesionales_asociados().getId_apoderado()) {
			dst_personaApoderado = PersonaServicio.getInstance()
					.obtenerPersona(infoRegimenInsolvencia.getProfesionales_asociados().getId_apoderado());
		} else {
			
			if ((infoRegimenInsolvencia.getTipo_de_solicitante().equals(Constantes.TIPO_SOLICITANTE_SOCIEDAD)
					|| infoRegimenInsolvencia.getTipo_de_solicitante().equals(Constantes.TIPO_SOLICITANTE_SUCURSALES_EXTRANJERAS))
					&& null != infoRegimenInsolvencia.getDeudor() && null != infoRegimenInsolvencia.getDeudor().getId_representante_legal()) {
				dst_personaApoderado = PersonaServicio.getInstance().obtenerPersona(infoRegimenInsolvencia.getDeudor().getId_representante_legal());
				SimpleLogger.setInfo("BpmServicios (Carga " + id_carga + "): Cargada persona desde Representante Legal ID: " + infoRegimenInsolvencia.getDeudor().getId_representante_legal());
			} else if((infoRegimenInsolvencia.getTipo_de_solicitante().equals(Constantes.TIPO_SOLICITANTE_PNC)
					|| infoRegimenInsolvencia.getTipo_de_solicitante().equals(Constantes.TIPO_SOLICITANTE_PNNC))
					&& null != infoRegimenInsolvencia.getDeudor() && null != infoRegimenInsolvencia.getDeudor().getRazon_social()) {
				
				dst_personaApoderado.setNombre(infoRegimenInsolvencia.getDeudor().getRazon_social());
				dst_personaApoderado.setIdentificacion(infoRegimenInsolvencia.getDeudor().getNumero_identificacion());
				dst_personaApoderado.setTipo_documento(Integer.parseInt(infoRegimenInsolvencia.getDeudor().getTipo_identificacion().getCodigo()));
				dst_personaApoderado.setCorreo(infoRegimenInsolvencia.getDeudor().getCorreo_electronico());
				
				SimpleLogger.setInfo("--- DIAGNÓSTICO PNNC (Carga " + id_carga + ") ---");
		        SimpleLogger.setInfo("Nombre Deudor: " + infoRegimenInsolvencia.getDeudor().getRazon_social());
		        SimpleLogger.setInfo("Pais ID en objeto Persona: " + dst_personaApoderado.getPais_id());
		        SimpleLogger.setInfo("Dpto ID en objeto Persona: " + dst_personaApoderado.getDepartamento_id());
		        SimpleLogger.setInfo("Mun ID en objeto Persona: " + dst_personaApoderado.getMunicipio_id());
		        
		        // Logs de la fuente de datos (Deudor)
		        SimpleLogger.setInfo("Pais ID en Fuente (Deudor): " + infoRegimenInsolvencia.getDeudor().getPais_dane_id());
		        if(infoRegimenInsolvencia.getDeudor().getDepartamento() != null) {
		            SimpleLogger.setInfo("Dpto ID en Fuente (Deudor): " + infoRegimenInsolvencia.getDeudor().getDepartamento().getId());
		        }
		        if(infoRegimenInsolvencia.getDeudor().getMunicipio() != null) {
		            SimpleLogger.setInfo("Mun ID en Fuente (Deudor): " + infoRegimenInsolvencia.getDeudor().getMunicipio().getId());
		        }
		        SimpleLogger.setInfo("-------------------------------------------");
			}
		}

		procesoRadicacion.setApoderadoNombre(""
					+ dst_personaApoderado.getNombreCompleto());
		procesoRadicacion.setApoderadoIdentificacion(""
				+ dst_personaApoderado.getIdentificacion());
		procesoRadicacion.setApoderadoEmail(""
				+ dst_personaApoderado.getCorreo());
		
		procesoRadicacion.setApoderadoTipoIdentificacionId(Objects.toString(querys.getIdPostalDocumento(Objects.toString(dst_personaApoderado.getTipo_documento(), "")), ""));
		procesoRadicacion.setApoderadoTipoIdentificacionNombre(Objects.toString(querys.getNombrePostalDocumento(Objects.toString(dst_personaApoderado.getTipo_documento(), "")), ""));
		
		List<PropiedadesPartesDto> listaPropiedadesPartes = new ArrayList<PropiedadesPartesDto>();

		PropiedadesPartesDto partesDeudor = new PropiedadesPartesDto();
		if (infoRegimenInsolvencia.getDeudor() != null) {
			partesDeudor.setTipoIdenParteId(Objects.toString(
					infoRegimenInsolvencia.getDeudor().getTipo_identificacion()
							.getId_postal(), ""));
			partesDeudor.setTipoIdenParteNombre(Objects.toString(
					procesoRadicacion.getIDET_Nombre(), ""));
			partesDeudor.setNmProcesoPartes(Objects.toString(
					procesoRadicacion.getIdentificacion(), ""));
			partesDeudor.setRazonSocial(Objects.toString(
					procesoRadicacion.getPersonaNombre(), ""));
			partesDeudor.setDirNotificacion(Objects.toString(
					procesoRadicacion.getPersonaDereccionNotificacion(), ""));
			partesDeudor.setPaisId(Objects.toString(
					procesoRadicacion.getPAI_Codigo(), ""));
			partesDeudor.setPaisNombre(Objects.toString(Objects.toString(
					infoRegimenInsolvencia.getDeudor().getPais_dane()
							.getNombre_ssoc(), "")));
			partesDeudor.setDepId(Objects.toString(
					procesoRadicacion.getDEP_Codigo(), ""));
			partesDeudor.setDepNombre(Objects.toString(querys
					.getNombreDepartamentoPorCodigo(procesoRadicacion
							.getDEP_Codigo()), ""));
			partesDeudor.setMunId(Objects.toString(
					procesoRadicacion.getMUN_Codigo(), ""));
			partesDeudor.setMunNombre(Objects.toString(querys
					.getNombreMunicipioPorCodigo(
							procesoRadicacion.getMUN_Codigo(),
							procesoRadicacion.getDEP_Codigo()), ""));
			partesDeudor.setEmailNotificacion(Objects.toString(
					procesoRadicacion.getCorreo(), ""));
			partesDeudor.setTelefono(Objects.toString(
					procesoRadicacion.getTelefono(), ""));
			partesDeudor.setSeleccionId(ParametrosInicio
					.getProperty("bpm.apvista.grupos.deudor.id"));
			partesDeudor
					.setSeleccionNombre(ApVistasGruposAcc
							.getInstance()
							.getNombreForIdVistaAndGrupo(
									ParametrosInicio
											.getProperty("bpm.apvista.grupos.deudor.id"),
									ParametrosInicio
											.getProperty("bpm.apvista.grupos.parte_tipo.grupo")));
		}
		listaPropiedadesPartes.add(partesDeudor);

		Persona dst_personaRepLegal = llenarPersona();
		PropiedadesPartesDto partesRepresentanteLegal = new PropiedadesPartesDto();

		if (infoRegimenInsolvencia.getDeudor() != null
				&& infoRegimenInsolvencia.getDeudor()
						.getId_representante_legal() != null) {
			dst_personaRepLegal = PersonaServicio.getInstance().obtenerPersona(
					infoRegimenInsolvencia.getDeudor()
							.getId_representante_legal());
		}

		partesRepresentanteLegal.setTipoIdenParteId(Objects.toString(querys
				.getIdPostalDocumento(Objects.toString(
						dst_personaRepLegal.getTipo_documento(), "")), ""));
		partesRepresentanteLegal.setTipoIdenParteNombre(Objects.toString(querys
				.getNombrePostalDocumento(Objects.toString(
						dst_personaRepLegal.getTipo_documento(), "")), ""));
		partesRepresentanteLegal.setNmProcesoPartes(""
				+ dst_personaRepLegal.getIdentificacion());
		partesRepresentanteLegal.setRazonSocial(""
				+ dst_personaRepLegal.getNombreCompleto());
		if (infoRegimenInsolvencia.getTipo_de_solicitante().equals(Constantes.TIPO_SOLICITANTE_SOCIEDAD)
				|| infoRegimenInsolvencia.getTipo_de_solicitante().equals(Constantes.TIPO_SOLICITANTE_SUCURSALES_EXTRANJERAS)) {
			partesRepresentanteLegal.setDirNotificacion(Objects.toString(
					procesoRadicacion.getPersonaDereccionNotificacion(), ""));
			partesRepresentanteLegal.setPaisId(partesDeudor.getPaisId());
			partesRepresentanteLegal
					.setPaisNombre(partesDeudor.getPaisNombre());
			partesRepresentanteLegal.setDepId(partesDeudor.getDepId());
			partesRepresentanteLegal.setDepNombre(partesDeudor.getDepNombre());
			partesRepresentanteLegal.setMunId(partesDeudor.getMunId());
			partesRepresentanteLegal.setMunNombre(partesDeudor.getMunNombre());
		} else {
			partesRepresentanteLegal.setDirNotificacion("");
			partesRepresentanteLegal.setPaisId("");
			partesRepresentanteLegal.setPaisNombre("");
			partesRepresentanteLegal.setDepId("");
			partesRepresentanteLegal.setDepNombre("");
			partesRepresentanteLegal.setMunId("");
			partesRepresentanteLegal.setMunNombre("");
		}
		partesRepresentanteLegal.setEmailNotificacion(Objects.toString(
				dst_personaRepLegal.getCorreo(), ""));
		partesRepresentanteLegal.setTelefono(Objects.toString(
				dst_personaRepLegal.getTelefono(), ""));

		partesRepresentanteLegal.setSeleccionId(ParametrosInicio
				.getProperty("bpm.apvista.grupos.rep_legal.id"));
		partesRepresentanteLegal
				.setSeleccionNombre(ApVistasGruposAcc
						.getInstance()
						.getNombreForIdVistaAndGrupo(
								ParametrosInicio
										.getProperty("bpm.apvista.grupos.rep_legal.id"),
								ParametrosInicio
										.getProperty("bpm.apvista.grupos.parte_tipo.grupo")));

		listaPropiedadesPartes.add(partesRepresentanteLegal);

		Persona dst_personaContador = llenarPersona();
		PropiedadesPartesDto partesContador = new PropiedadesPartesDto();

		if (null !=  infoRegimenInsolvencia.getProfesionales_asociados()){
			if(null  != infoRegimenInsolvencia.getProfesionales_asociados(). getId_contador()){
				dst_personaContador = PersonaServicio.getInstance().obtenerPersona(
						infoRegimenInsolvencia.getProfesionales_asociados()
						.getId_contador());
			} else {
				if(null != infoRegimenInsolvencia.getProfesionales_asociados().getId_contador_pnnc()){
					dst_personaContador = PersonaServicio.getInstance().obtenerPersona(
							infoRegimenInsolvencia.getProfesionales_asociados()
							.getId_contador_pnnc());
				}
			}
		}
		
		if (dst_personaContador != null && dst_personaContador.getIdentificacion() != null && !dst_personaContador.getIdentificacion().isEmpty()) {
		
		partesContador.setTipoIdenParteId(Objects.toString(querys
				.getIdPostalDocumento(Objects.toString(dst_personaContador
						.getTipo_documento(), "")), ""));
		partesContador.setTipoIdenParteNombre(Objects.toString(querys
				.getNombrePostalDocumento(Objects.toString(dst_personaContador
						.getTipo_documento(), "")), ""));
		partesContador.setNmProcesoPartes(Objects.toString(
				dst_personaContador.getIdentificacion(), ""));
		partesContador.setRazonSocial(Objects.toString(
				dst_personaContador.getNombreCompleto(), ""));
		partesContador.setDirNotificacion(Objects.toString(
				dst_personaContador.getDireccion(), ""));
		partesContador.setPaisId(obtenerCodigoLugarPorId(dst_personaContador.getPais_id().toString(),"PAISES DANE", "CODIGO SSOC"));
		partesContador.setPaisNombre(obtenerNombrePais(partesContador.getPaisId().toString(), "PAISES DANE", "NOMBRE SSOC", "CODIGO SSOC"));
		partesContador.setDepId(obtenerCodigoLugarPorId(dst_personaContador.getDepartamento_id().toString(), "DEPARTAMENTOS DANE", "CODIGO DEPARTAMENTO"));
		partesContador.setDepNombre(obtenerNombreDepartamento(partesContador.getDepId().toString(), "DEPARTAMENTOS DANE", "NOMBRE DEPARTAMENTO", "CODIGO DEPARTAMENTO"));
		partesContador.setMunId(obtenerCodigoLugarPorId(dst_personaContador.getMunicipio_id().toString(), "MUNICIPIOS DANE", "CODIGOMUNICIPIOPOSTAL"));
		partesContador.setMunNombre(obtenerNombreMunicipio(
				dst_personaContador.getDepartamento_id().toString(),    
			    "MUNICIPIOS DANE",
			    "NOMBRE CIUDAD",
			    "CODIGO DEPARTAMENTO",
			    "CODIGOMUNICIPIOPOSTAL",
			    partesContador.getMunId().toString()      
			));
		partesContador.setEmailNotificacion(Objects.toString(
				dst_personaContador.getCorreo(), ""));
		partesContador.setTelefono(Objects.toString(
				dst_personaContador.getTelefono(), ""));
		partesContador.setSeleccionId(ParametrosInicio
				.getProperty("bpm.apvista.grupos.contador.id")); // 4467
		partesContador.setSeleccionNombre(ApVistasGruposAcc.getInstance()
				.getNombreForIdVistaAndGrupo(ParametrosInicio.getProperty("bpm.apvista.grupos.contador.id"),
						ParametrosInicio.getProperty("bpm.apvista.grupos.parte_tipo.grupo")));
		listaPropiedadesPartes.add(partesContador);
		
		} else {
		    // Si no hay identificación, se considera que no hay contador y se limpian los campos
		    SimpleLogger.setInfo("BpmServicios: Saltando Contador (No presente o datos incompletos en la solicitud)");
		}

		Persona dst_personaRevFiscal = llenarPersona();
		if (infoRegimenInsolvencia.getProfesionales_asociados() != null
				&& infoRegimenInsolvencia.getProfesionales_asociados()
						.getId_revisor_fiscal() != null) {
			dst_personaRevFiscal = PersonaServicio.getInstance()
					.obtenerPersona(
							infoRegimenInsolvencia.getProfesionales_asociados()
									.getId_revisor_fiscal());
		}
		
		if (dst_personaRevFiscal != null && dst_personaRevFiscal.getIdentificacion() != null && !dst_personaRevFiscal.getIdentificacion().isEmpty()) {

			PropiedadesPartesDto partesRevFiscal = new PropiedadesPartesDto();
			partesRevFiscal.setTipoIdenParteId(Objects.toString(querys
					.getIdPostalDocumento(Objects.toString(
							dst_personaRevFiscal.getTipo_documento(), "")), ""));
			partesRevFiscal.setTipoIdenParteNombre(Objects.toString(querys
					.getNombrePostalDocumento(Objects.toString(
							dst_personaRevFiscal.getTipo_documento(), "")), ""));
			partesRevFiscal.setNmProcesoPartes(Objects.toString(
					dst_personaRevFiscal.getIdentificacion(), ""));
			partesRevFiscal.setRazonSocial(Objects.toString(
					dst_personaRevFiscal.getNombreCompleto(), ""));
			partesRevFiscal.setDirNotificacion(Objects.toString(
					dst_personaRevFiscal.getDireccion(), ""));
			partesRevFiscal.setPaisId(obtenerCodigoLugarPorId(dst_personaRevFiscal.getPais_id().toString(),"PAISES DANE", "CODIGO SSOC"));
			partesRevFiscal.setPaisNombre(obtenerNombrePais(partesRevFiscal.getPaisId().toString(), "PAISES DANE", "NOMBRE SSOC", "CODIGO SSOC"));
			partesRevFiscal.setDepId(obtenerCodigoLugarPorId(dst_personaRevFiscal.getDepartamento_id().toString(), "DEPARTAMENTOS DANE", "CODIGO DEPARTAMENTO"));
			partesRevFiscal.setDepNombre(obtenerNombreDepartamento(partesRevFiscal.getDepId().toString(), "DEPARTAMENTOS DANE", "NOMBRE DEPARTAMENTO", "CODIGO DEPARTAMENTO"));
			partesRevFiscal.setMunId(obtenerCodigoLugarPorId(dst_personaRevFiscal.getMunicipio_id().toString(), "MUNICIPIOS DANE", "CODIGOMUNICIPIOPOSTAL"));
			partesRevFiscal.setMunNombre(obtenerNombreMunicipio(
					dst_personaRevFiscal.getDepartamento_id().toString(),    
				    "MUNICIPIOS DANE",
				    "NOMBRE CIUDAD",
				    "CODIGO DEPARTAMENTO",
				    "CODIGOMUNICIPIOPOSTAL",
				    partesRevFiscal.getMunId().toString()      
				));
			partesRevFiscal.setEmailNotificacion(Objects.toString(
					dst_personaRevFiscal.getCorreo(), ""));
			partesRevFiscal.setTelefono(Objects.toString(
					dst_personaRevFiscal.getTelefono(), ""));
			partesRevFiscal.setSeleccionId(ParametrosInicio
					.getProperty("bpm.apvista.grupos.rev_fiscal.id")); // 3471
			partesRevFiscal.setSeleccionNombre(ApVistasGruposAcc.getInstance().getNombreForIdVistaAndGrupo(
					ParametrosInicio.getProperty("bpm.apvista.grupos.rev_fiscal.id"),
					ParametrosInicio.getProperty("bpm.apvista.grupos.parte_tipo.grupo")));
			listaPropiedadesPartes.add(partesRevFiscal);
		} else {
		    SimpleLogger.setInfo("BpmServicios: Saltando Revisor Fiscal (No presente en la solicitud)");
		}

		procesoRadicacion.setListaPropiedadesPartes(listaPropiedadesPartes);

		PropiedadesProcesoActuacionesDto procesoUno = new PropiedadesProcesoActuacionesDto();

		procesoUno.setEstadoId(ParametrosInicio
				.getProperty("bpm.apvista.grupos.estado.radicado")); // 3138
		procesoUno.setEstadoNombre(ApVistasGruposAcc.getInstance().getNombreForIdVistaAndGrupo(
				ParametrosInicio.getProperty("bpm.apvista.grupos.estado.radicado"),
				"ActuacionEstado"));
		procesoUno.setNumeroRadicado(Objects.toString(infoRegimenInsolvencia.getNumero_radicado(), ""));
		procesoUno.setFechaHora(toFormatyyyyMMDdHHmmss(carga.getFecha_liberacion()));
		if (null != infoRegimenInsolvencia.getTramite()) {
			procesoUno.setTramite(infoRegimenInsolvencia.getTramite()
					.getNombreTramite());
			procesoUno.setTramiteCod(infoRegimenInsolvencia.getTramite()
					.getIdPostal().toString());
		} else {
			procesoUno.setTramite("");
			procesoUno.setTramiteCod("");
		}
		procesoUno.setMedioEnvioCod(infoRegimenInsolvencia.getMedio_envio()
				.getCodigo().toString());
		procesoUno.setMedioEnvio(infoRegimenInsolvencia.getMedio_envio()
				.getMedio_envio());
		procesoUno.setTipoSeguridad(infoRegimenInsolvencia.getTipo_seguridad().getSeguridadTipo());
		procesoUno.setTipoSeguridadCodTxt(infoRegimenInsolvencia.getTipo_seguridad().getCodigoAlfanumerico());
		procesoUno.setCuaderno(infoRegimenInsolvencia.getCuaderno()
				.getCuadernoTipo());
		procesoUno.setCuadernoCod(infoRegimenInsolvencia.getCuaderno()
				.getCodigo());
		procesoUno.setDependenciaID(infoRegimenInsolvencia.getDependencia()
				.getCodigo().toString());

		int idDocumentoTipoEntrada = Integer.parseInt(ParametrosInicio
				.getProperty("bpm.apvista.documento_tipo.entrada.id")); // 66
		ApVistaDocumentoTipoDto documentoTipo = ApVistaDocumentoTipoAcc
				.getInstance().getPorIdVista(idDocumentoTipoEntrada);
		procesoUno.setTipoDocumento(documentoTipo.getDocumento_tipo()); // 66
		procesoUno.setTipoDocumentoCod(documentoTipo.getCodigo_numerico());
//		procesoUno.setTipoDocumentoId(documentoTipo.getId());
		procesoUno.setTipoDocEntradaSalida("");
		RegimenInsolvencia infoPadre = RegimenInsolvenciaServicio.getInstance().obtenerInfoRegimenInsolvenciaPorIdCarga(id_carga);
		procesoUno.setCodigoSerie(infoPadre.getSerie().getCodigo().toString());
		procesoUno.setCodigoSubSerie(infoPadre.getSubserie().getCodigo().toString());

		List<PropiedadesProcesoActuacionesDto> listaPropiedadesProceso = new ArrayList<PropiedadesProcesoActuacionesDto>();
		listaPropiedadesProceso.add(procesoUno);
		procesoRadicacion
				.setListaPropiedadesProcesoActuaciones(listaPropiedadesProceso);

		CamposAdicionalesDto camposAdicionales = new CamposAdicionalesDto();
		camposAdicionales.setInsTipoSolicitanteID(infoRegimenInsolvencia
				.getTipo_de_solicitante().toString());
		camposAdicionales.setInsTipoSolicitante(querys.getValorLista(
				"TIPO SOLICITANTE", infoRegimenInsolvencia
						.getTipo_de_solicitante().toString()));
		
		camposAdicionales.setCategoriaSociedadPersona(Objects.toString(infoRegimenInsolvencia.getDeudor().getCategoria(),""));

		if (infoRegimenInsolvencia.getDeudor() != null
				&& infoRegimenInsolvencia.getDeudor().getCodigo_ciiu() != null) {
			camposAdicionales.setSEC_CodCIIU(infoRegimenInsolvencia.getDeudor()
					.getCodigo_ciiu().getId());
		} else {
			camposAdicionales.setSEC_CodCIIU("");
		}

		if (infoRegimenInsolvencia.getDeudor() != null
				&& infoRegimenInsolvencia.getDeudor().getMacrosector() != null
				&& infoRegimenInsolvencia.getDeudor().getMacrosector().getId() != null) {
			ApVistaMacrosectorDto vistaMacrosector = ApVistaMacrosectorAcc
					.getInstance().getPorLista(
							infoRegimenInsolvencia.getDeudor().getMacrosector()
									.getId().toString());
			camposAdicionales.setProc_Macrosector(vistaMacrosector.getNombre());
			camposAdicionales.setProc_MacrosectorID(vistaMacrosector
					.getId_vista());

		} else {
			camposAdicionales.setProc_Macrosector("");
			camposAdicionales.setProc_MacrosectorID("");
		}

		if (infoRegimenInsolvencia.getDeudor() != null
				&& infoRegimenInsolvencia.getDeudor().getNaturaleza() != null
				&& infoRegimenInsolvencia.getDeudor().getNaturaleza().getId() != null) {
			ApVistaNaturalezaDto vistaNaturaleza = ApVistaNaturalezaAcc
					.getInstance().getPorLista(
							infoRegimenInsolvencia.getDeudor().getNaturaleza()
									.getId().toString());
			camposAdicionales.setProc_Naturaleza(vistaNaturaleza.getNombre());
			camposAdicionales.setProc_NaturalezaID(vistaNaturaleza
					.getId_vista());

		} else {
			camposAdicionales.setProc_Naturaleza("");
			camposAdicionales.setProc_NaturalezaID("");
		}

		if (infoRegimenInsolvencia.getSupuestos_admisibilidad_id() != null
				&& infoRegimenInsolvencia.getSupuestos_admisibilidad()
						.getSupuestos_admisibilidad() != null
				&& infoRegimenInsolvencia.getSupuestos_admisibilidad()
						.getSupuestos_admisibilidad().getId() != null) {
			ApVistaSupuestoDto vistaSupuesto = ApVistaSupuestoAcc.getInstance()
					.getPorLista(
							infoRegimenInsolvencia.getSupuestos_admisibilidad()
									.getSupuestos_admisibilidad().getId()
									.toString());
			camposAdicionales.setProc_Supuesto(vistaSupuesto.getNombre());
			camposAdicionales.setProc_SupuestoID(vistaSupuesto.getId_vista());

		} else {
			camposAdicionales.setProc_Supuesto("");
			camposAdicionales.setProc_SupuestoID("");
		}

		String porce_part = infoRegimenInsolvencia.getDeudor()
				.getPorcentaje_participacion() != null ? infoRegimenInsolvencia
				.getDeudor().getPorcentaje_participacion() : "0.0";
		camposAdicionales.setProc_PorcPartEst(porce_part);

		if (infoRegimenInsolvencia.getDeudor().getEmpleados_mujeres() != null) {
			camposAdicionales.setProc_NumEmplMujeres(infoRegimenInsolvencia
					.getDeudor().getEmpleados_mujeres().toString());
		} else {
			camposAdicionales.setProc_NumEmplMujeres("0");
		}

		if (infoRegimenInsolvencia.getDeudor().getEmpleados_hombres() != null) {
			camposAdicionales.setProc_NumEmplHombres(infoRegimenInsolvencia
					.getDeudor().getEmpleados_hombres().toString());
		} else {
			camposAdicionales.setProc_NumEmplHombres("0");
		}
		
		if(infoRegimenInsolvencia.getDeudor().getRepresentante_limitacion() != null) {
			camposAdicionales.setRepLegal_Limitacion_Proceso(infoRegimenInsolvencia.getDeudor().getRepresentante_limitacion().toString());
		}

		camposAdicionales.setProc_AdelAcreedores("");
		camposAdicionales.setProc_AdelAcreedoresID("");

		Boolean existeRelacionPasivos = null != infoRegimenInsolvencia.getRelacion_de_pasivos();

		camposAdicionales
				.setProc_PasFavorAutFiscales(existeRelacionPasivos ? Objects
						.toString(infoRegimenInsolvencia
								.getRelacion_de_pasivos()
								.getPasivos_por_retenciones(), "") : "");
		camposAdicionales
				.setProc_PasDesTrabajadores(existeRelacionPasivos ? Objects
						.toString(infoRegimenInsolvencia
								.getRelacion_de_pasivos()
								.getPasivos_por_descuentos(), "") : "");
		camposAdicionales
				.setProc_PasAportesSeguiridad(existeRelacionPasivos ? Objects
						.toString(infoRegimenInsolvencia
								.getRelacion_de_pasivos()
								.getPasivos_por_aportes(), "") : "");

		// info financiera menual
		String fechaEeffMes = "";
		String vlActivosMes = "";
		String vlPasivosMes = "";
		String vlPatrimonioMes = "";
		int grupoNiif = 0;
		
		String idBaseContable = "";
		String baseContable = "";
		
		if (null != infoRegimenInsolvencia.getInfo_financiera_mensual() && null != infoRegimenInsolvencia.getInfo_financiera_mensual().getFecha_EEFF_mes()) {
			fechaEeffMes = toFormatyyyyMMDdHHmmss(infoRegimenInsolvencia.getInfo_financiera_mensual().getFecha_EEFF_mes());
			vlActivosMes = infoRegimenInsolvencia.getInfo_financiera_mensual().getActivos_mes_anterior() != null ? 
					infoRegimenInsolvencia.getInfo_financiera_mensual().getActivos_mes_anterior().stripTrailingZeros().toPlainString() : "";
					
			vlPasivosMes = infoRegimenInsolvencia.getInfo_financiera_mensual().getPasivos_mes_anterior() != null ? 
					infoRegimenInsolvencia.getInfo_financiera_mensual().getPasivos_mes_anterior().stripTrailingZeros().toPlainString() : "";
					
			vlPatrimonioMes = infoRegimenInsolvencia.getInfo_financiera_mensual().getPatrimonio_mes_anterior() != null ? 
					infoRegimenInsolvencia.getInfo_financiera_mensual().getPatrimonio_mes_anterior().stripTrailingZeros().toPlainString() : "";
			
			grupoNiif = infoRegimenInsolvencia.getInfo_financiera_mensual().getGrupo_NIIF() != null ? 
					infoRegimenInsolvencia.getInfo_financiera_mensual().getGrupo_NIIF(): 0;

			if (infoRegimenInsolvencia.getInfo_financiera_mensual().getBase_contable_apvista() != null) {
					
				idBaseContable = Objects.toString(
						infoRegimenInsolvencia.getInfo_financiera_mensual().getBase_contable_apvista().getId_vista(), "");
				baseContable = Objects.toString(
						infoRegimenInsolvencia.getInfo_financiera_mensual().getBase_contable_apvista().getBase_contable(), "");
			}
				
		} else {
		
			if(null != infoRegimenInsolvencia.getInfo_financiera_mes_pnc() && null !=  infoRegimenInsolvencia.getInfo_financiera_mes_pnc().getFecha_eeff_mes_pnc()){
				fechaEeffMes = toFormatyyyyMMDdHHmmss(infoRegimenInsolvencia.getInfo_financiera_mes_pnc().getFecha_eeff_mes_pnc());
				vlActivosMes = infoRegimenInsolvencia.getInfo_financiera_mes_pnc().getActivos_mes_anterior_pnc() != null ? 
						infoRegimenInsolvencia.getInfo_financiera_mes_pnc().getActivos_mes_anterior_pnc().stripTrailingZeros().toPlainString() : "";
						
				vlPasivosMes = infoRegimenInsolvencia.getInfo_financiera_mes_pnc().getPasivos_mes_anterior_pnc() != null ? 
						infoRegimenInsolvencia.getInfo_financiera_mes_pnc().getPasivos_mes_anterior_pnc().stripTrailingZeros().toPlainString() : "";
						
				vlPatrimonioMes = infoRegimenInsolvencia.getInfo_financiera_mes_pnc().getPatrimonio_mes_anter_pnc() != null ? 
						infoRegimenInsolvencia.getInfo_financiera_mes_pnc().getPatrimonio_mes_anter_pnc().stripTrailingZeros().toPlainString() : "";
						
				grupoNiif = infoRegimenInsolvencia.getInfo_financiera_mes_pnc().getGrupo_niif_pnc() != null ? 
						infoRegimenInsolvencia.getInfo_financiera_mes_pnc().getGrupo_niif_pnc(): 0;
			} 
			
		}
		camposAdicionales.setProc_FechaEstadosMesAnterior(fechaEeffMes);
		camposAdicionales.setProc_Activos(vlActivosMes);
		camposAdicionales.setProc_Pasivos(vlPasivosMes);
		camposAdicionales.setProc_Patrimonio(vlPatrimonioMes);
		
		camposAdicionales.setIdBaseContable(idBaseContable);
		camposAdicionales.setBaseContable(baseContable);
		
		if (grupoNiif != 0) {
			try {
				String grupoString = ApVistasGrupoNiifAcc.getInstance().getPorId(grupoNiif);
				ApVistaGrupoNiifDto grupoJson = gson.fromJson(grupoString,
						ApVistaGrupoNiifDto.class);
				camposAdicionales.setProc_GrupoNIIF(grupoJson.getNombre());
				camposAdicionales.setProc_GrupoNIIFID(grupoJson.getId_vista());
			} catch (Exception e) {
				SimpleLogger.setError(
						"Error al consultar la vista APVISTA GRUPO NIIF con id:"
								+ grupoNiif + ". Descripcion: ", e);
			}

		} else {
			camposAdicionales.setProc_GrupoNIIF("");
			camposAdicionales.setProc_GrupoNIIFID("");
		}
		
		// info financiera anual
		if (null != infoRegimenInsolvencia.getInfo_financiera_anual()) {

			InfoFinancieraAnual infoAnual = infoRegimenInsolvencia.getInfo_financiera_anual();
			
			String ingresos_ano_anterior = null != infoAnual.getIngresos_ano_anterior() ? infoAnual
					.getIngresos_ano_anterior().toPlainString() : "";
			String otros_ing_ano_anterior = null != infoAnual.getOtros_ing_ano_anterior() ? infoAnual
					.getOtros_ing_ano_anterior().toPlainString() : "";
			String activos_ano_anterior = null != infoAnual.getActivos_ano_anterior() ? infoAnual
					.getActivos_ano_anterior().toPlainString() : "";
			String pasivos_ano_anterior = null != infoAnual.getPasivos_ano_anterior()? infoAnual
					.getPasivos_ano_anterior().toPlainString() : "";
			
			String inversionesSubsidiarias = null != infoAnual.getInversiones_subsidiarias() ? infoAnual
					.getInversiones_subsidiarias().toString() : "";
			String gananciaMetPart = null != infoAnual.getIng_metodo_participacion() ? infoAnual
					.getIng_metodo_participacion().toPlainString() : "";
			String gananciaMetCosto = null != infoAnual.getIngresos_metodo_costo() ? infoAnual
					.getIngresos_metodo_costo().toPlainString() : "";
			String gananciaMetVRaz = null != infoAnual.getIngresos_metodo_razonable() ? infoAnual
					.getIngresos_metodo_razonable().toPlainString() : "";
			String fechaEFAnual = null != infoAnual.getFecha_eeff_anual() ? toFormatyyyyMMDdHHmmss(
					infoAnual.getFecha_eeff_anual()) : "";
					

			camposAdicionales.setProc_IngresosOrdinarios(ingresos_ano_anterior);
			camposAdicionales.setProc_OtrosIngresos(otros_ing_ano_anterior);
			camposAdicionales.setProc_ActivosAnt(activos_ano_anterior);
			camposAdicionales.setProc_PasivosAnt(pasivos_ano_anterior);
			camposAdicionales.setProc_Inversiones(inversionesSubsidiarias);
			if("1".equals(inversionesSubsidiarias)) {
				camposAdicionales.setProc_GananciasMetPArticipacion(gananciaMetPart);
				camposAdicionales.setProc_GananciasMetCosto(gananciaMetCosto);
				camposAdicionales.setProc_GananciasMetValorRazonable(gananciaMetVRaz);
			} else {
				camposAdicionales.setProc_GananciasMetPArticipacion("");
				camposAdicionales.setProc_GananciasMetCosto("");
				camposAdicionales.setProc_GananciasMetValorRazonable("");
			}
			camposAdicionales.setFechaEstadosFinancieros(fechaEFAnual);

		} else {
			camposAdicionales.setProc_IngresosOrdinarios("");
			camposAdicionales.setProc_OtrosIngresos("");
			camposAdicionales.setProc_ActivosAnt("");
			camposAdicionales.setProc_PasivosAnt("");
			camposAdicionales.setProc_Inversiones("");
			camposAdicionales.setProc_GananciasMetPArticipacion("");
			camposAdicionales.setProc_GananciasMetCosto("");
			camposAdicionales.setProc_GananciasMetValorRazonable("");
			camposAdicionales.setFechaEstadosFinancieros("");
		}

		// otra informacion
		Boolean otraInformacion = null != infoRegimenInsolvencia.getOtra_informacion();
		camposAdicionales.setProc_Causal(otraInformacion ? Objects.toString(
				infoRegimenInsolvencia.getOtra_informacion()
						.getCausal_disolucion(), "") : "");
		camposAdicionales.setProc_PasPen(otraInformacion ? Objects.toString(
				infoRegimenInsolvencia.getOtra_informacion()
						.getPasivos_pensionales(), "") : "");
		camposAdicionales.setProc_Garante(otraInformacion ? Objects.toString(
				infoRegimenInsolvencia.getOtra_informacion()
						.getGarante_terceros(), "") : "");

		// bienes sujetos a garantia
		Boolean bienesSuj = null != infoRegimenInsolvencia.getBienes_sujetos_garantia();
		camposAdicionales.setProc_BienesSuj(bienesSuj ? Objects.toString(
				infoRegimenInsolvencia.getBienes_sujetos_garantia()
						.getBienes_sujetos_garantia(), "") : "");

		// datos basicos press
		camposAdicionales.setProc_NombrePRES("");
		camposAdicionales.setProc_NomMediador("");
		camposAdicionales.setProc_ValBiePres("");
		camposAdicionales.setProc_ValOblPres("");
		if (infoRegimenInsolvencia != null
				&& infoRegimenInsolvencia.getDatos_basicos_pres_id() != null) {
			camposAdicionales.setProc_NombrePRES(infoRegimenInsolvencia
					.getDatos_basicos_pres().getNombre_camara_comercio());
			camposAdicionales.setProc_NomMediador(infoRegimenInsolvencia
					.getDatos_basicos_pres().getNombre_mediador());
			camposAdicionales.setProc_ValBiePres(infoRegimenInsolvencia
					.getDatos_basicos_pres().getValor_activos_reportados());
			camposAdicionales.setProc_ValOblPres(infoRegimenInsolvencia
					.getDatos_basicos_pres().getValor_pasivos_reportados());
		}

		// conjunto estados financieros
		Boolean conjuntoEstadosFinancieros = null != infoRegimenInsolvencia.getConjunto_eeff();
		camposAdicionales.setProc_InfoPrevia("");
		camposAdicionales.setProc_RadEstadosFinNmenos1("");
		camposAdicionales.setProc_RadEstadosFinNmenos2("");
		camposAdicionales.setProc_RadEstadosFinNmenos3("");
		
		if (conjuntoEstadosFinancieros && null != infoRegimenInsolvencia.getConjunto_eeff().getReportado_informacion()) {
			String infoPrevia = infoRegimenInsolvencia.getConjunto_eeff().getReportado_informacion().toString();
			camposAdicionales.setProc_InfoPrevia(infoPrevia);
			if ("1".equals(infoPrevia)) {
				camposAdicionales
						.setProc_RadEstadosFinNmenos1(infoRegimenInsolvencia
								.getConjunto_eeff().getRadicado_eeff_2019());
				camposAdicionales
						.setProc_RadEstadosFinNmenos2(infoRegimenInsolvencia
								.getConjunto_eeff().getRadicado_eeff_2018());
				camposAdicionales
						.setProc_RadEstadosFinNmenos3(infoRegimenInsolvencia
								.getConjunto_eeff().getRadicado_eeff_2017());

			}
		}
		//Bienes Acreedores
		if(null != infoRegimenInsolvencia && null != infoRegimenInsolvencia.getBienes_acreedores_id() 
				&& null != infoRegimenInsolvencia.getBienes_acreedores().getFecha_bienes_acreedores()){
			camposAdicionales.setFechaRelBienesAcciones(toFormatyyyyMMDdHHmmss(
					infoRegimenInsolvencia.getBienes_acreedores().getFecha_bienes_acreedores()));
		} else {
			camposAdicionales.setFechaRelBienesAcciones("");
		}

		camposAdicionales.setProc_ControlaSociedadReorg("");
		camposAdicionales.setProc_NITSociedadControlada("");
		camposAdicionales.setProc_NombreSociedadControlada("");
		camposAdicionales.setNumRadSolAutoAdm("");

		if (null != infoRegimenInsolvencia.getCondicion_controlante_id()
				&& null != infoRegimenInsolvencia.getCondicion_controlante()) {
			
//			String esControlante = infoRegimenInsolvencia.getCondicion_controlante().getEs_controlante_de_socieda().toString();
//			camposAdicionales.setProc_ControlaSociedadReorg(esControlante);
			
			camposAdicionales.setProc_NITSociedadControlada(Objects.toString(
					infoRegimenInsolvencia.getCondicion_controlante().getNit(), ""));
			camposAdicionales.setProc_NombreSociedadControlada(Objects.toString(
					infoRegimenInsolvencia.getCondicion_controlante().getNombre_de_la_sociedad(), ""));
			camposAdicionales.setNumRadSolAutoAdm(Objects.toString(
					infoRegimenInsolvencia.getCondicion_controlante().getRadicado_solicitud_auto(), ""));
			
			String idDeudorCondicion = "";
			String deudorCondicion = "";
			
			if (infoRegimenInsolvencia.getCondicion_controlante().getCondicion_deudor_apvista() != null) {
				idDeudorCondicion = Objects.toString(
						infoRegimenInsolvencia.getCondicion_controlante().getCondicion_deudor_apvista().getId_vista(),"");
				deudorCondicion = Objects.toString(
						infoRegimenInsolvencia.getCondicion_controlante().getCondicion_deudor_apvista().getDeudor_condicion(),"");
			}
			
			camposAdicionales.setIdDeudorCondicion(idDeudorCondicion);
			camposAdicionales.setDeudorCondicion(deudorCondicion);
			
		}
		camposAdicionales.setProc_IdSolicitud(id_carga.toString());

		String fecha_hipotesis = "";
		if (infoRegimenInsolvencia.getHipotesis_obj() != null) {
			fecha_hipotesis = toFormatyyyyMMDdHHmmss(infoRegimenInsolvencia.getHipotesis_obj().getFecha_verificacion());
		}
		
		camposAdicionales.setFechaVerificacionHipotesis(fecha_hipotesis);
		
		String fecha_relacion = "";
		if (infoRegimenInsolvencia.getBienes_pnnc() != null) {
			fecha_relacion = toFormatyyyyMMDdHHmmss(infoRegimenInsolvencia.getBienes_pnnc().getFecha_relacion());
		}
		
		camposAdicionales.setFechaRelBienesAcciones(fecha_relacion);
		
		procesoRadicacion.setCamposAdicionales(camposAdicionales);

		return procesoRadicacion;
	}
	
	private ProcesoActuacionesDto getProcesoActuaciones(
			Integer id_carga) {

		ProcesoActuacionesDto procesoActuaciones = new ProcesoActuacionesDto();
		
		RespuestaRequerimiento rtaRequerimiento = RespuestaRequerimientoServicio.getInstance().obtenerRespuestaRequerimientoCompleto(id_carga);
		
		RespuestaRequerimiento infoSolicitudPadre = RespuestaRequerimientoServicio.getInstance().obtenerInformacionSolicitudPadre(rtaRequerimiento.getNumero_solicitud());
		
		Deudor deudor = infoSolicitudPadre.getDeudor_obj();
		
		Carga carga = CargaServicio.getInstance().obtenerCarga(id_carga);

		// INFORMACION GENERAL
		procesoActuaciones.setNumeroProc(Objects.toString(infoSolicitudPadre.getNumero_proceso(), ""));
		
		if(null != deudor){
			procesoActuaciones.setIdentificacion(Objects.toString(deudor.getNumero_identificacion(), ""));
			procesoActuaciones.setPersonaNombre(Objects.toString(deudor.getRazon_social(), ""));
			procesoActuaciones.setPersonaDereccionNotificacion(Objects.toString(deudor.getDireccion(), ""));
			procesoActuaciones.setApoderadoDireccion(Objects.toString(deudor.getDireccion(), ""));
			procesoActuaciones.setCorreo(Objects.toString(deudor.getCorreo_electronico(), ""));
			procesoActuaciones.setTelefono(Objects.toString(deudor.getTelefono(), ""));
			procesoActuaciones.setApoderadoTelefono(Objects.toString(deudor.getTelefono(), ""));
			
			
			if (null != deudor.getPais_dane_id() && null != deudor.getPais_dane().getCodigo_ssoc()) {
				procesoActuaciones.setPAI_Codigo(deudor.getPais_dane().getCodigo_ssoc().toString()); // 80
				procesoActuaciones.setApoderadoPaisCodigo(deudor.getPais_dane().getCodigo_ssoc().toString()); 
			} else {
				procesoActuaciones.setPAI_Codigo("");
				procesoActuaciones.setApoderadoPaisCodigo("");
			}
			
			if(null != deudor.getDepartamento_id() && null != deudor.getDepartamento().getCodigo_departamento() ){
				procesoActuaciones.setDEP_Codigo(deudor.getDepartamento().getCodigo_departamento().toString()); // 11
				procesoActuaciones.setApoderadoDepartamentoCodigo(deudor.getDepartamento().getCodigo_departamento().toString()); // 11
			} else {
				procesoActuaciones.setDEP_Codigo("");
				procesoActuaciones.setApoderadoDepartamentoCodigo("");
			}
			
			if(null != deudor.getMunicipio_id() && null != deudor.getMunicipio().getCodigoMunicipioPostal()){
				procesoActuaciones.setMUN_Codigo(deudor.getMunicipio().getCodigoMunicipioPostal().toString());
				procesoActuaciones.setApoderadoCiudadCodigo(deudor.getMunicipio().getCodigoMunicipioPostal().toString());
			
			} else {
				procesoActuaciones.setMUN_Codigo("");
				procesoActuaciones.setApoderadoCiudadCodigo("");
			}
			
			if(null != deudor.getTipo_identificacion_id()) {
				procesoActuaciones.setIDET_Codigo(
						deudor.getTipo_identificacion().getCodigo_numerico_postal().toString()); //
				procesoActuaciones.setIDET_Nombre(
						deudor.getTipo_identificacion().getNombre_postal());
			} else {
				procesoActuaciones.setIDET_Codigo(""); //
				procesoActuaciones.setIDET_Nombre("");
			}
		} else {
			procesoActuaciones.setIdentificacion("");
			procesoActuaciones.setPersonaNombre("");
			procesoActuaciones.setPersonaDereccionNotificacion("");
			procesoActuaciones.setCorreo("");
			procesoActuaciones.setTelefono("");
			procesoActuaciones.setApoderadoDireccion("");
			procesoActuaciones.setApoderadoTelefono("");
			procesoActuaciones.setApoderadoPaisCodigo("");
			procesoActuaciones.setApoderadoDepartamentoCodigo("");
			procesoActuaciones.setApoderadoCiudadCodigo("");
			procesoActuaciones.setPAI_Codigo("");
			procesoActuaciones.setDEP_Codigo("");
			procesoActuaciones.setMUN_Codigo("");
			procesoActuaciones.setIDET_Codigo("");
			procesoActuaciones.setIDET_Nombre("");
		}
		
		procesoActuaciones.setIdTipoPonente(
				Objects.toString(ParametrosInicio.getProperty("bpm.actuaciones.subsanacion.tipo_ponente_id"), ""));
		procesoActuaciones.setValorTipoPonente(
				Objects.toString(ParametrosInicio.getProperty("bpm.actuaciones.subsanacion.tipo_ponente_valor"), ""));
		
		// COMPLEMENTO A INFORMACION DE APODERADO
		procesoActuaciones.setApoderadoNombre("");
		procesoActuaciones.setApoderadoIdentificacion("");
		procesoActuaciones.setApoderadoEmail("");
		procesoActuaciones.setApoderadoTipoIdentificacionId("");
		procesoActuaciones.setApoderadoTipoIdentificacionNombre("");
		
		if(null != deudor){
			Persona dst_personaApoderado = new Persona();
			dst_personaApoderado = llenarPersona();
			
			if (infoSolicitudPadre.getTipo_solicitante_obj().getId().equals(Constantes.TIPO_SOLICITANTE_SOCIEDAD)
					|| infoSolicitudPadre.getTipo_solicitante_obj().getId().equals(Constantes.TIPO_SOLICITANTE_SUCURSALES_EXTRANJERAS)) {
				if (null != deudor.getId_representante_legal()) {
					dst_personaApoderado = PersonaServicio.getInstance().obtenerPersona(
									deudor.getId_representante_legal());
				}
				procesoActuaciones.setApoderadoNombre(Objects.toString(dst_personaApoderado.getNombreCompleto(), ""));
				procesoActuaciones.setApoderadoIdentificacion(Objects.toString(dst_personaApoderado.getIdentificacion(), ""));
				procesoActuaciones.setApoderadoEmail(Objects.toString(dst_personaApoderado.getCorreo(), ""));
				procesoActuaciones.setApoderadoTipoIdentificacionId(Objects.toString(dst_personaApoderado.getTipo_documento(), ""));
				
				procesoActuaciones.setApoderadoTipoIdentificacionNombre(""
						+ ApVistaIdentificacionTiposAcc.getInstance()
						.getNombrePorIdVista(
								Objects.toString(dst_personaApoderado
										.getTipo_documento(), "")));
			} else if (infoSolicitudPadre.getTipo_solicitante_obj().getId().equals(Constantes.TIPO_SOLICITANTE_PNC)
					|| infoSolicitudPadre.getTipo_solicitante_obj().getId().equals(Constantes.TIPO_SOLICITANTE_PNNC)) {
				procesoActuaciones.setApoderadoNombre(Objects.toString(deudor.getRazon_social(), ""));
				procesoActuaciones.setApoderadoIdentificacion(Objects.toString(deudor.getNumero_identificacion(), ""));
				procesoActuaciones.setApoderadoEmail(Objects.toString(deudor.getCorreo_electronico(), ""));
				procesoActuaciones.setApoderadoTipoIdentificacionId(Objects.toString(deudor.getTipo_identificacion().getId_postal(), ""));
				procesoActuaciones.setApoderadoTipoIdentificacionNombre(Objects.toString(deudor.getTipo_identificacion().getNombre_postal(), ""));
			} 
			
		}

		// INFO ACTUACIONES
		PropiedadesProcesoActuacionesDto procesoUno = new PropiedadesProcesoActuacionesDto();

		procesoUno.setEstadoId(ParametrosInicio
				.getProperty("bpm.apvista.grupos.estado.radicado")); // 3138
		procesoUno.setEstadoNombre(ApVistasGruposAcc.getInstance().getNombreForIdVistaAndGrupo(
				ParametrosInicio.getProperty("bpm.apvista.grupos.estado.radicado"),
				"ActuacionEstado"));
		procesoUno.setNumeroRadicado(Objects.toString(
				rtaRequerimiento.getNumero_radicado_postal(), ""));
		procesoUno.setFechaHora(toFormatyyyyMMDdHHmmss(carga.getFecha_liberacion()));
		
		if (null != rtaRequerimiento.getTramite()) {
			procesoUno.setTramite(rtaRequerimiento.getTramite_obj()
					.getNombreTramite());
			procesoUno.setTramiteCod(rtaRequerimiento.getTramite_obj()
					.getIdPostal().toString());
		} else {
			procesoUno.setTramite("");
			procesoUno.setTramiteCod("");
		}
		
		if(null != rtaRequerimiento.getMedio_envio()) {
			procesoUno.setMedioEnvioCod(rtaRequerimiento.getMedio_envio_obj()
					.getCodigo().toString());
			procesoUno.setMedioEnvio(rtaRequerimiento.getMedio_envio_obj()
					.getMedio_envio());
		} else {
			procesoUno.setMedioEnvioCod("");
			procesoUno.setMedioEnvio("");
		}

		if(null != rtaRequerimiento.getTipo_seguridad()) {
			procesoUno.setTipoSeguridad(rtaRequerimiento.getTipo_seguridad_obj().getSeguridadTipo());
			procesoUno.setTipoSeguridadCodTxt(rtaRequerimiento.getTipo_seguridad_obj().getCodigoAlfanumerico());
		} else {
			procesoUno.setTipoSeguridad("");
			procesoUno.setTipoSeguridadCodTxt("");
		}

		if(null != rtaRequerimiento.getTipo_cuaderno()){
			procesoUno.setCuaderno(rtaRequerimiento.getTipo_cuaderno_obj().getCuadernoTipo());
			procesoUno.setCuadernoCod(rtaRequerimiento.getTipo_cuaderno_obj().getCodigo());
		} else {
			procesoUno.setCuaderno("");
			procesoUno.setCuadernoCod("");
		}
		
		if(null != rtaRequerimiento.getDependencia()) {
			procesoUno.setDependenciaID(rtaRequerimiento.getDependencia_obj().getCodigo().toString());
		} else {
			procesoUno.setDependenciaID("");
		}
				
		int idDocumentoTipoEntrada = Integer.parseInt(ParametrosInicio
				.getProperty("bpm.apvista.documento_tipo.entrada.id")); // 66
		ApVistaDocumentoTipoDto documentoTipo = ApVistaDocumentoTipoAcc
				.getInstance().getPorIdVista(idDocumentoTipoEntrada);
		procesoUno.setTipoDocumentoCod(documentoTipo.getCodigo_numerico());
		procesoUno.setTipoDocumento(documentoTipo.getDocumento_tipo()); // 66
		procesoUno.setCodigoSerie(rtaRequerimiento.getTipo_serie_obj().getCodigo().toString());
		procesoUno.setCodigoSubSerie(rtaRequerimiento.getTipo_subserie_obj().getCodigo().toString());
		procesoUno.setIDSolicitudMI(id_carga.toString());
		procesoUno.setTipoActuacionID(Objects.toString(
				ParametrosInicio.getProperty("bpm.actuaciones.subsanacion.tipo_actuacion_id"), ""));
		procesoUno.setTipoActuacion(Objects.toString(ParametrosInicio.getProperty("bpm.actuaciones.subsanacion.tipo_actuacion"), ""));

		List<PropiedadesProcesoActuacionesDto> listaPropiedadesProceso = new ArrayList<PropiedadesProcesoActuacionesDto>();
		listaPropiedadesProceso.add(procesoUno);
		procesoActuaciones
				.setListaPropiedadesProcesoActuaciones(listaPropiedadesProceso);

		return procesoActuaciones;
	}

	private ProcesoRadicacionDto getProcesoRadicacionRtaReqNear(Integer id_carga){
		
		try {
			
			RespuestaRequerimiento rta_req = RespuestaRequerimientoServicio.getInstance().obtenerRespuestaRequerimientoBase(id_carga);
						
			if (rta_req != null) {
				List<Integer> ids_solicitud = RespuestaRequerimientoServicio.getInstance().obtenerRespuestasRequerimientoPorSolicitudInicial(rta_req.getNumero_solicitud());
				ProcesoRadicacionDto procesoRadicacion = getProcesoRadicacionNear(rta_req.getNumero_solicitud());
				
				for (Integer id_solicitud: ids_solicitud) {
					RespuestaRequerimiento solicitud = RespuestaRequerimientoServicio.getInstance().obtenerRespuestaRequerimientoCompleto(id_solicitud);
					RadicacionAutoOficio radicacion = RadicacionAutoOficioServicio.getInstance().obtenerRadicacionAutoOficio(id_solicitud);
					
					if (solicitud != null && radicacion != null) {
						Carga carga = CargaServicio.getInstance().obtenerCarga(id_solicitud);
						
						PropiedadesProcesoActuacionesDto procesoEntrada = new PropiedadesProcesoActuacionesDto();
						
						String estadoRadicadoId	= ParametrosInicio.getProperty("bpm.apvista.grupos.estado.radicado");
						String tipoDocumentoId	= ParametrosInicio.getProperty("bpm.apvista.documento_tipo.entrada.id"); //66
						
						procesoEntrada.setEstadoId(estadoRadicadoId); // 3138
						procesoEntrada.setEstadoNombre(ApVistasGruposAcc.getInstance().getNombreForIdVistaAndGrupo(estadoRadicadoId, "ActuacionEstado"));
						
						procesoEntrada.setNumeroRadicado(Objects.toString(solicitud.getNumero_radicado_postal(), ""));
						
						if(carga.getFecha_liberacion() != null) {	
							procesoEntrada.setFechaHora(toFormatyyyyMMDdHHmmss(carga.getFecha_liberacion()));
						} else {
							procesoEntrada.setFechaHora("");
						}
						
						APVistaTramite tramite = null != solicitud.getTramite() ? solicitud.getTramite_obj() : null;
						procesoEntrada.setTramite(tramite.getNombreTramite());
						procesoEntrada.setTramiteCod(tramite.getIdPostal().toString());
						
						APVistaMedioEnvio medioEnvio = null != solicitud.getMedio_envio() ? solicitud.getMedio_envio_obj() : null;
						procesoEntrada.setMedioEnvio(medioEnvio.getMedio_envio()); //Portal Web
						procesoEntrada.setMedioEnvioCod(medioEnvio.getCodigo().toString());
						
						APVistaSeguridadTipo tipoSeguridad = null != solicitud.getTipo_seguridad() ? solicitud.getTipo_seguridad_obj() : null;
						procesoEntrada.setTipoSeguridad(tipoSeguridad.getSeguridadTipo()); 
						procesoEntrada.setTipoSeguridadCodTxt(tipoSeguridad.getCodigoAlfanumerico());
						
						APVistaCuaderno cuaderno = null != solicitud.getTipo_cuaderno_obj() ? solicitud.getTipo_cuaderno_obj() : null;
						procesoEntrada.setCuaderno(cuaderno.getCuadernoTipo());
						procesoEntrada.setCuadernoCod(cuaderno.getCodigo());
						
						APVistaDependencias dependencia = null != solicitud.getDependencia() ? solicitud.getDependencia_obj() : null;
						procesoEntrada.setDependenciaID(Objects.toString(dependencia.getCodigo()));
						
						APVistaDocumentoTipo documentoTipo = BpmServiciosQuerys.getInstance().getDocumentoTipoPorIdVista(tipoDocumentoId);
						procesoEntrada.setTipoDocumento(documentoTipo.getDocumento_tipo()); 
						procesoEntrada.setTipoDocumentoCod(documentoTipo.getCodigo_numerico().toString()); 
						procesoEntrada.setTipoDocEntradaSalida(Constantes.BPM_TIPO_DOCUMENTO_ENTRADA); 
						
						procesoEntrada.setIDSolicitudMI(id_solicitud.toString());
						
						if (solicitud.getTipo_auto_acta_obj() != null) {
							procesoEntrada.setTipoActuacionID(Objects.toString(solicitud.getTipo_auto_acta_obj().getId_bpm(),""));
							procesoEntrada.setTipoActuacion(Objects.toString(solicitud.getTipo_auto_acta_obj().getTipo_actuacion(),""));
						} else {
							procesoEntrada.setTipoActuacionID("");
							procesoEntrada.setTipoActuacion("");
						}
						
						procesoEntrada.setCodigoSerie(solicitud.getTipo_serie_obj().getCodigo().toString());
						procesoEntrada.setCodigoSubSerie(solicitud.getTipo_subserie_obj().getCodigo().toString());
						
						PropiedadesProcesoActuacionesDto procesoSalida = new PropiedadesProcesoActuacionesDto();
						
						procesoSalida.setEstadoId(estadoRadicadoId); // 3138
						procesoSalida.setEstadoNombre(ApVistasGruposAcc.getInstance().getNombreForIdVistaAndGrupo(estadoRadicadoId, "ActuacionEstado"));
						
						procesoSalida.setNumeroRadicado(Objects.toString(radicacion.getNumeroRadicado(), ""));
						procesoSalida.setFechaHora(toFormatyyyyMMDdHHmmss(radicacion.getFechaRadicado()));
						
						APVistaTramite tramiteSalida = radicacion.getTramite();
						
						if (tramiteSalida != null) {
							procesoSalida.setTramite(tramiteSalida.getNombreTramite());
							procesoSalida.setTramiteCod(tramiteSalida.getIdPostal().toString());
						}
						
						APVistaMedioEnvio medioEnvioSalida = radicacion.getCodigoMedioEnvio();
						
						if (medioEnvioSalida != null) {
							procesoSalida.setMedioEnvio(medioEnvioSalida.getMedio_envio());
							procesoSalida.setMedioEnvioCod(medioEnvioSalida.getCodigo().toString());				
						}
						
						APVistaSeguridadTipo tipoSeguridadSalida = radicacion.getCodigoTipoSeguridad();
						
						if (tipoSeguridadSalida != null) {
							procesoSalida.setTipoSeguridad(tipoSeguridadSalida.getSeguridadTipo()); 
							procesoSalida.setTipoSeguridadCodTxt(tipoSeguridadSalida.getCodigoAlfanumerico());				
						}
						
						APVistaCuaderno cuadernoSalida = radicacion.getCuadernoTipo();
						
						if (cuadernoSalida != null) {
							procesoSalida.setCuaderno(cuadernoSalida.getCuadernoTipo());
							procesoSalida.setCuadernoCod(cuadernoSalida.getCodigo());				
						}
						
						APVistaDependencias dependenciaSalida = radicacion.getDependencia();
						
						if (dependenciaSalida != null) {
							procesoSalida.setDependenciaID(Objects.toString(dependenciaSalida.getCodigo()));				
						}
						
						APVistaDocumentoTipo documentoTipoSalida = radicacion.getDocumentalTipo();
						
						if (documentoTipoSalida != null) {
							procesoSalida.setTipoDocumento(documentoTipoSalida.getDocumento_tipo()); 
							procesoSalida.setTipoDocumentoCod(documentoTipoSalida.getCodigo_numerico().toString());				
						}
						
						if (radicacion.getTipoAutoActa() != null) {
							procesoSalida.setTipoActuacionID(Objects.toString(radicacion.getTipoAutoActa().getId_bpm(),""));
							procesoSalida.setTipoActuacion(Objects.toString(radicacion.getTipoAutoActa().getTipo_actuacion(),""));
						} else {
							procesoSalida.setTipoActuacionID("");
							procesoSalida.setTipoActuacion("");
						}
						
						procesoSalida.setTipoDocEntradaSalida(Constantes.BPM_TIPO_DOCUMENTO_SALIDA);
						
						procesoRadicacion.getListaPropiedadesProcesoActuaciones().add(procesoEntrada);
						procesoRadicacion.getListaPropiedadesProcesoActuaciones().add(procesoSalida);
					} else {
						SimpleLogger.setInfo("No se encuentra informacion completa de la respuesta de requerimiento " + id_solicitud + " para enviar a BPM");
					}
					
				}
					
				return procesoRadicacion;
			}
		
		} catch (Exception e) {
			SimpleLogger.setError("Error en el servicio getProcesoRadicacionRINear para la carga " + id_carga + ": ", e);
		}
		
		return null;
	}

	public ResponseFirmaFuncionario obtenerImagenFirmaFuncionario(
			String cedula_usuario) {
		RequestFirmaFuncionario request = new RequestFirmaFuncionario();
		request.setAlto(200);
		request.setAncho(500);
		request.setCedula_usuario(cedula_usuario);
	
		try {
		
			CallPage call = new CallPage();
	
			Map<String, String> headers = setupHeaders();
			String token = generarToken(null);
			headers.put("Authorization", token);
	
			String endpoint = getEndPoint("sticker.endpoint");
			String uri = endpoint
					+ getEndPoint("bpm.api") + "ConsultasInformix/ObtenerImagenFirmaFuncionarioBL2";
	
			String content = new Gson().toJson(request);
	
			logger.info("obtenerImagenFirmaFuncionario: BODY: " + content);
	
			String json_response = call.callPost(uri, content, headers);
	
			logger.info("BpmServicios.obtenerImagenFirmaFuncionario Response: " + json_response);
			
			ResponseFirmaFuncionario response = new Gson().fromJson(json_response,
					ResponseFirmaFuncionario.class);
	
			return response;
		} catch (Throwable e) {
			SimpleLogger.setError("Error en la generacion de la firma ", e);
			return null;
		}

	}

}
