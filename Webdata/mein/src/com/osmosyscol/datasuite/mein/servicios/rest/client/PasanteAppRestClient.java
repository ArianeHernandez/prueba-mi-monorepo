package com.osmosyscol.datasuite.mein.servicios.rest.client;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.log4j.Logger;

import co.htsoft.commons.net.CallPage;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.rpc.jsonrpc.JSONObject;
import com.osmosyscol.datasuite.logica.constantes.ConstantesNombreArchivos;
import com.osmosyscol.datasuite.logica.dto.ArchivoAdjunto;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.dto.TipoArchivo;
import com.osmosyscol.datasuite.logica.servicios.ArchivoAdjuntoServicio;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;
import com.osmosyscol.datasuite.logica.servicios.TipoArchivoServicio;
import com.osmosyscol.datasuite.mein.dtos.DocumentoAdjunto;
import com.osmosyscol.datasuite.mein.dtos.EnterpriseInfoDto;
import com.osmosyscol.datasuite.mein.dtos.EntradaRegistrarRequisito;
import com.osmosyscol.datasuite.mein.dtos.Interviniente;
import com.osmosyscol.datasuite.mein.dtos.ProcesoPasante;
import com.osmosyscol.datasuite.mein.dtos.RegimenInsolvencia;
import com.osmosyscol.datasuite.mein.dtos.RespuestaRequerimiento;
import com.osmosyscol.datasuite.mein.dtos.ResponsePasante;
import com.osmosyscol.datasuite.mein.dtos.SalidaObtenerRequisito;
import com.osmosyscol.datasuite.mein.dtos.SalidaRegistrarRequisito;
import com.osmosyscol.datasuite.mein.dtos.SolicitudNearSociedad;
import com.osmosyscol.datasuite.mein.dtos.SolicitudNearSociedadDto;
import com.osmosyscol.datasuite.mein.logica.constantes.Constantes;
import com.osmosyscol.datasuite.mein.servicios.CalculoCategoriaTamanoServicio;
import com.osmosyscol.datasuite.mein.servicios.ProcesoPasanteServicio;
import com.osmosyscol.datasuite.mein.servicios.RegimenInsolvenciaServicio;
import com.osmosyscol.datasuite.mein.servicios.RespuestaRequerimientoServicio;
import com.osmosyscol.datasuite.mein.servicios.SolicitudNearSociedadServicio;
import com.osmosyscol.datasuite.near.servicios.InfoRadicadoLocalServicio;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class PasanteAppRestClient {

	private static PasanteAppRestClient pasanteAppRestClient;
	private static Gson gson = new Gson();
	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	final static Logger logger = Logger.getLogger(PasanteAppRestClient.class);
	
	
	private PasanteAppRestClient() {

	}

	public static PasanteAppRestClient getInstance() {

		if (pasanteAppRestClient == null) {
			pasanteAppRestClient = new PasanteAppRestClient();
		}
		return pasanteAppRestClient;
	}
	
	public SalidaRegistrarRequisito registrarRequisito(String id, String name) {
		try {
			
			CallPage call = new CallPage();
			
			String endpoint = getEndpoint("pasante.endpoint");
			String uri = endpoint + "/requisitos";
			
			EntradaRegistrarRequisito entrada = new EntradaRegistrarRequisito();
			
			entrada.setId(id);
			entrada.setName(name);
			
			SalidaRegistrarRequisito salida = call.callJSON(uri, entrada, SalidaRegistrarRequisito.class);
			
			return salida;
			
		} catch (JsonSyntaxException e) {
			SimpleLogger.setError(e.getMessage());
			return null;
		}
		
	}
	
	public SalidaObtenerRequisito obtenerRequisitos(){
		try {
			
			CallPage call = new CallPage();
			
			Map<String, String> headers = setupHeaders();
			String endpoint = getEndpoint("pasante.endpoint");
			
			String uri = endpoint + "/requisitos";
			
			String response = call.callGet(uri, headers);
			SalidaObtenerRequisito salidaRequisito = gson.fromJson(response, SalidaObtenerRequisito.class);
			
			return salidaRequisito;
			
		} catch (JsonSyntaxException e) {
			SimpleLogger.setError(e.getMessage());
			return null;
		}
	}
	
	public SalidaObtenerRequisito obtenerRequisitoPorId(String id){
		try {
			
			CallPage call = new CallPage();
			
			Map<String, String> headers = setupHeaders();
			String endpoint = getEndpoint("pasante.endpoint");
			
			String uri = endpoint + "/requisitos/" + id;
			
			String response = call.callGet(uri, headers);
			SalidaObtenerRequisito salidaRequisito = gson.fromJson(response, SalidaObtenerRequisito.class);
			
			return salidaRequisito;
			
		} catch (JsonSyntaxException e) {
			SimpleLogger.setError(e.getMessage());
			return null;
		}
	}
	
	private Map<String, String> setupHeaders(){
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		
		return headers;
	}
	
	private String getEndpoint(String property){
		String endpoint = ParametrosInicio.getProperty(property);
		SimpleLogger.setDebug("Consultando servicio: " + endpoint);

		if (endpoint == null) {
			SimpleLogger.setError("Variable "+property+" no configurada en el archivo de propiedades de la carpeta $DATASUITE");
		}
		
		return endpoint;
	}
	
	//enviar datos NEAR a pasante
	public ResponsePasante obtenerDatosRegistro(Integer idCarga){
		try {
			
			CallPage call = new CallPage();
			
			EnterpriseInfoDto enterpriseInfo = obtenerEnterpriseInfoNear(idCarga);
			
			Interviniente intervinientePonente = new Interviniente();
			intervinientePonente.setName("Ponente");
			
			SolicitudNearSociedadDto entrada = new SolicitudNearSociedadDto();
			
			entrada.setIsNear(true);
			entrada.setEnterpriseInfo(enterpriseInfo);
			entrada.setProcessId(1);
			entrada.setValidatingBy(intervinientePonente);
			entrada.setId(idCarga.toString());
			
			String endpoint = getEndpoint("pasante.endpoint");			
			String uri = endpoint + "/oficio";
			
			String toUnicode = new JSONObject(gson.toJson(entrada)).toString();
			
			logger.info("NEAR Pasante Request " + idCarga + " : " + toUnicode);
			
			Map<String, String> headers = setupHeaders();
			String response = call.callPost(uri, toUnicode, headers);
			
			ResponsePasante salida = gson.fromJson(response, ResponsePasante.class);
			
			logger.info("NEAR Pasante Response " + idCarga + " : " + response);
			
			return salida;
			
		} catch (JsonSyntaxException e) {
			SimpleLogger.setError(e.getMessage());
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private EnterpriseInfoDto obtenerEnterpriseInfoNear (Integer idCarga) {
		EnterpriseInfoDto enterpriseInfo = new EnterpriseInfoDto();
		
		try {
			SolicitudNearSociedad solicitudNear = SolicitudNearSociedadServicio.getInstance().obtenerSolicitudNearSociedadPorCarga(idCarga);
			String dataSolicitud = gson.toJson(solicitudNear);
			logger.info("INFO NEAR CARGA " + idCarga + " - " + dataSolicitud);
			
			Carga cargaImp = CargaServicio.getInstance().obtenerCarga(idCarga);
			logger.info("INFO DE CARGA " + idCarga + " - " + cargaImp);
			
			Map<String, DocumentoAdjunto> documentos = new HashMap<String, DocumentoAdjunto>();
			documentos = obtenerDocumentosCarga(idCarga);			
			
			enterpriseInfo.setDocumentos(documentos);
			
			if(null != solicitudNear.getDeudor().getRepresentante_legal() && null != solicitudNear.getDeudor().getRepresentante_legal().getNombre_completo()){
				enterpriseInfo.setRepresentanteLegal_radicado(solicitudNear.getDeudor().getRepresentante_legal().getNombre_completo());
				enterpriseInfo.setCedulaRepresentanteLegal_radicado(solicitudNear.getDeudor().getRepresentante_legal().getIdentificacion());
			}else{
				enterpriseInfo.setRepresentanteLegal_radicado("");
				enterpriseInfo.setCedulaRepresentanteLegal_radicado("");
			}
			
			if(null != solicitudNear.getDeudor().getRevisor_fiscal() && null != solicitudNear.getDeudor().getRevisor_fiscal().getNombre_completo()){
				enterpriseInfo.setRevisorFiscal_radicado(solicitudNear.getDeudor().getRevisor_fiscal().getNombre_completo());
				enterpriseInfo.setCedulaRevisorFiscal_radicado(solicitudNear.getDeudor().getRevisor_fiscal().getIdentificacion());
			}else{
				enterpriseInfo.setRevisorFiscal_radicado("");
				enterpriseInfo.setCedulaRevisorFiscal_radicado("");
			}
			if(null != solicitudNear.getDeudor().getApoderado() && null != solicitudNear.getDeudor().getApoderado().getNombre_completo()){
				enterpriseInfo.setApoderado_radicado(solicitudNear.getDeudor().getApoderado().getNombre_completo());
				enterpriseInfo.setCedulaApoderado_radicado(solicitudNear.getDeudor().getApoderado().getIdentificacion());
			}else{
				enterpriseInfo.setApoderado_radicado("");
				enterpriseInfo.setCedulaApoderado_radicado("");
			}
			
			enterpriseInfo.setFechaSolicitud_radicado(format.format(cargaImp.getFecha_liberacion()));
	
			if(null != solicitudNear.getChecklist_solicitud() && null != solicitudNear.getChecklist_solicitud_obj().getSociedad_pasivos_pensiona()
					&& Constantes.MI_CAMPO_SI.equals(solicitudNear.getChecklist_solicitud_obj().getSociedad_pasivos_pensiona())){
				enterpriseInfo.setPasivosPensionalesCargo_radicado(Constantes.PASANTE_CAMPO_SI);
			} else {
				enterpriseInfo.setPasivosPensionalesCargo_radicado("No");
			}
	
			
			if (solicitudNear.getInformacion_financiera() != null) {
				
				enterpriseInfo.setEstadosFinancierosAnioAnterior_radicado(solicitudNear.getInformacion_financiera().getUltimo_radicado_dictamen());
				enterpriseInfo.setEstadosFinancierosPenultimoAnio_radicado(solicitudNear.getInformacion_financiera().getPenultimo_radicado_dictamen());
				enterpriseInfo.setEstadosFinancierosAntepenultimoAnio_radicado(solicitudNear.getInformacion_financiera().getAntepenultimo_radicado_dictamen());
				enterpriseInfo.setFechaEstadosFinancierosMesAnterior_radicado(solicitudNear.getInformacion_financiera().getFecha_estados_financieros());

				String activos_mes_anterior = solicitudNear.getInformacion_financiera().getValor_activos() != null
						? solicitudNear.getInformacion_financiera().getValor_activos().stripTrailingZeros().toPlainString(): "";
				enterpriseInfo.setValorTotalActivosCorteMesAnterior_radicado(activos_mes_anterior);
				
				String pasivos_mes_anterior = solicitudNear.getInformacion_financiera().getValor_pasivos() != null
						? solicitudNear.getInformacion_financiera().getValor_pasivos().stripTrailingZeros().toPlainString(): "";
				enterpriseInfo.setValorTotalPasivosCorteMesAnterior_radicado(pasivos_mes_anterior);
				
				String patrimonio_mes_anterior = solicitudNear.getInformacion_financiera().getValor_patrimonio() != null
						? solicitudNear.getInformacion_financiera().getValor_patrimonio().stripTrailingZeros().toPlainString(): "";
				enterpriseInfo.setValorTotalPatrimonioCorteMesAnterior_radicado(patrimonio_mes_anterior);
				
				String activos_ano_anterior = solicitudNear.getInformacion_financiera().getValor_activos_ultimoanio() != null
						? solicitudNear.getInformacion_financiera().getValor_activos_ultimoanio().stripTrailingZeros().toPlainString(): "";
				enterpriseInfo.setValorTotalActivosAnoAnterior_radicado(activos_ano_anterior);
				
				String pasivos_ano_anterior = solicitudNear.getInformacion_financiera().getValor_pasivos_ultimoanio() != null
						? solicitudNear.getInformacion_financiera().getValor_pasivos_ultimoanio().stripTrailingZeros().toPlainString(): "";
				enterpriseInfo.setValorTotalPasivosAnoAnterior_radicado(pasivos_ano_anterior);		
				
				String ingresos_ordinarios = solicitudNear.getInformacion_financiera().getTotal_ingresos_ordinarios() != null
						? solicitudNear.getInformacion_financiera().getTotal_ingresos_ordinarios().stripTrailingZeros().toPlainString(): "";
				enterpriseInfo.setValorTotalIngresosOrdinariosAnoAnterior_radicado(ingresos_ordinarios);
				
				String otros_ingresos = solicitudNear.getInformacion_financiera().getTotal_otros_ingresos() != null
						? solicitudNear.getInformacion_financiera().getTotal_otros_ingresos().stripTrailingZeros().toPlainString(): "";
				enterpriseInfo.setValorTotalOtrosIngresosAnoAnterior_radicado(otros_ingresos);
				
				String fecha_bienes_acreedores = solicitudNear.getInformacion_financiera().getFecha_r_bienes_acreedores();
				enterpriseInfo.setFechaRelacionBienesAcreedores_radicado(fecha_bienes_acreedores);
			}
			
			if(null != solicitudNear.getChecklist_solicitud() && null!= solicitudNear.getChecklist_solicitud_obj().getGarantias_mobiliarias()
					&& Constantes.MI_CAMPO_SI.equals(solicitudNear.getChecklist_solicitud_obj().getGarantias_mobiliarias())){
				enterpriseInfo.setBienesSujetosGarantiasMobiliarias_radicado(Constantes.PASANTE_CAMPO_SI);
			} else {
				enterpriseInfo.setBienesSujetosGarantiasMobiliarias_radicado(Constantes.PASANTE_CAMPO_NO);
			}
			
			enterpriseInfo.setNumeroRadicado_radicado(Objects.toString(solicitudNear.getNumero_radicado_postal(),""));
			enterpriseInfo.setNumeroProceso_radicado(Objects.toString(solicitudNear.getNumero_proceso(), ""));
	
			if(null != solicitudNear.getTipo_solicitud() && null != solicitudNear.getTipo_solicitud().getTipo_solicitud_obj()
					&& null != solicitudNear.getTipo_solicitud().getTipo_solicitud_obj().getNombre()){
				enterpriseInfo.setTipoSolicitud_radicado(solicitudNear.getTipo_solicitud().getTipo_solicitud_obj().getNombre());
			} else {
				enterpriseInfo.setTipoSolicitud_radicado("");
			}
			
			if(solicitudNear.getTipo_solicitud() != null && solicitudNear.getTipo_solicitud().getGrupo_niif_obj() != null){
				enterpriseInfo.setGrupoNIIF_radicado(solicitudNear.getTipo_solicitud().getGrupo_niif_obj().getNombre());
			} else {
				enterpriseInfo.setGrupoNIIF_radicado("");
			}
			
			
			enterpriseInfo.setTipoSolicitante_radicado(solicitudNear.getTipo_solicitante().toString());
			enterpriseInfo.setNombreSolicitante_radicado(solicitudNear.getDeudor().getDatos_basicos().getNombre_completo());
			
			if(null != solicitudNear.getChecklist_solicitud() && null!= solicitudNear.getChecklist_solicitud_obj().getSociedad_garante_codeudor()
					&& Constantes.MI_CAMPO_SI.equals(solicitudNear.getChecklist_solicitud_obj().getSociedad_garante_codeudor())){
				enterpriseInfo.setGaranteAvalistaCodeudorTerceros_radicado(Constantes.PASANTE_CAMPO_SI);
			} else {
				enterpriseInfo.setGaranteAvalistaCodeudorTerceros_radicado(Constantes.PASANTE_CAMPO_NO);
			}
			
			enterpriseInfo.setNumeroDeIdentificacion_radicado(solicitudNear.getDeudor().getDatos_basicos().getIdentificacion());
			enterpriseInfo.setDomicilioPrincipal_radicado(solicitudNear.getDeudor().getMunicipioObj().getNombre_ciudad());
			enterpriseInfo.setCiudad_radicado(solicitudNear.getDeudor().getMunicipioObj().getNombre_ciudad());
			enterpriseInfo.setDireccionNotificacionIdentificacion_radicado(solicitudNear.getDeudor().getDatos_basicos().getDireccion());
			enterpriseInfo.setTelefonoNotificacion_radicado(solicitudNear.getDeudor().getDatos_basicos().getTelefono());
			enterpriseInfo.setEmail_radicado(solicitudNear.getDeudor().getDatos_basicos().getCorreo());
			enterpriseInfo.setIdDelDeudor_radicado(solicitudNear.getDeudor().getDatos_basicos().getId().toString());
			
			if (solicitudNear.getChecklist_solicitud_obj() != null && Constantes.MI_CAMPO_SI.equals(solicitudNear.getChecklist_solicitud_obj().getSociedad_en_disolucion())) {
				enterpriseInfo.setTieneCausalDeDisolucion_radicado(Constantes.PASANTE_CAMPO_SI);
			} else {
				enterpriseInfo.setTieneCausalDeDisolucion_radicado(Constantes.PASANTE_CAMPO_NO);
			}
			
			if (solicitudNear.getChecklist_solicitud_obj() != null && Constantes.MI_CAMPO_SI.equals(solicitudNear.getChecklist_solicitud_obj().getRemision_previa_info())) {
				enterpriseInfo.setEstadosFinancierosRemitidosAnterioridad_radicado(Constantes.PASANTE_CAMPO_SI);
			} else {
				enterpriseInfo.setEstadosFinancierosRemitidosAnterioridad_radicado(Constantes.PASANTE_CAMPO_NO);
			}
			
			if (solicitudNear.getChecklist_solicitud_obj() != null && solicitudNear.getChecklist_solicitud_obj().getSolicitante_controlante() != null) {
				String es_controlante = Constantes.MI_CAMPO_SI.equals(solicitudNear.getChecklist_solicitud_obj().getSolicitante_controlante())
						? Constantes.PASANTE_CAMPO_SI: Constantes.PASANTE_CAMPO_NO;
				enterpriseInfo.setEsControlanteDeSociedad_radicado(es_controlante);
			}
			
			if (Constantes.LISTA_VALORES_SITUACION_PRESENTADA_CESACION.equals(solicitudNear.getSituacion_presentada())) {
				enterpriseInfo.setCesacionPagos_radicado(Constantes.PASANTE_CAMPO_SI);
			} else {
				enterpriseInfo.setCesacionPagos_radicado(Constantes.PASANTE_CAMPO_NO);
			}
			
			if (solicitudNear.getRelacion_de_pasivos() != null) {
				Integer pasivos_retenciones = solicitudNear.getRelacion_de_pasivos().getPasivos_por_retenciones();
				String tiene_pasivos_retenciones = Constantes.MI_CAMPO_SI.equals(pasivos_retenciones)
						? Constantes.PASANTE_CAMPO_SI: Constantes.PASANTE_CAMPO_NO;
				enterpriseInfo.setTienePasivosPorRetencionesConFisco_radicado(tiene_pasivos_retenciones);
				
				Integer pasivos_descuentos = solicitudNear.getRelacion_de_pasivos().getPasivos_por_descuentos();
				String tiene_pasivos_descuentos = Constantes.MI_CAMPO_SI.equals(pasivos_descuentos)
						? Constantes.PASANTE_CAMPO_SI: Constantes.PASANTE_CAMPO_NO;
				enterpriseInfo.setTienePasivosPorDescuentosTrabajadores_radicado(tiene_pasivos_descuentos);
				
				Integer pasivos_aportes = solicitudNear.getRelacion_de_pasivos().getPasivos_por_aportes();
				String tiene_pasivos_aportes = Constantes.MI_CAMPO_SI.equals(pasivos_aportes) 
						? Constantes.PASANTE_CAMPO_SI: Constantes.PASANTE_CAMPO_NO;
				enterpriseInfo.setTienePasivosPorSeguridadSocial_radicado(tiene_pasivos_aportes);
			}
			
			if (solicitudNear.getDeudor() != null) {
				String tiene_contador = solicitudNear.getDeudor().getContador() != null
						? Constantes.PASANTE_CAMPO_SI: Constantes.PASANTE_CAMPO_NO;
				enterpriseInfo.setTieneContador_radicado(tiene_contador);
				
				String tiene_revisor = solicitudNear.getDeudor().getRevisor_fiscal() != null
						? Constantes.PASANTE_CAMPO_SI: Constantes.PASANTE_CAMPO_NO;
				enterpriseInfo.setTieneRevisorFiscal_radicado(tiene_revisor);
				
				String tiene_apoderado = solicitudNear.getDeudor().getApoderado() != null
						? Constantes.PASANTE_CAMPO_SI: Constantes.PASANTE_CAMPO_NO;
				enterpriseInfo.setTieneApoderado_radicado(tiene_apoderado);
				
				String nit_pnnc = Objects.toString(solicitudNear.getDeudor().getNit_sociedad_controlada(), "");
				enterpriseInfo.setNitPNNC_radicado(nit_pnnc);
				
				String sociedad_pnnc = Objects.toString(solicitudNear.getDeudor().getName_sociedad_controlada(), "");
				enterpriseInfo.setNombreSociedadPNNC_radicado(sociedad_pnnc);
				
				String radicado_pnnc = Objects.toString(solicitudNear.getDeudor().getRadicado_sociedad_controlada(), "");
				enterpriseInfo.setNumeroRadicadoSolicitudoAuto_radicado(radicado_pnnc);
				
				String replegal_limitaciones = Constantes.MI_CAMPO_SI.equals(solicitudNear.getDeudor().getReplegal_tiene_limitacion())
						? Constantes.PASANTE_CAMPO_SI: Constantes.PASANTE_CAMPO_NO;
				enterpriseInfo.setRepresentanteConLimitaciones_radicado(replegal_limitaciones);
				
			}
			
			List<ArchivoAdjunto> adjuntos_documento_autorizacion = ArchivoAdjuntoServicio.getInstance().obtenerArchivosAdjuntosPorCargaTipo(idCarga, Constantes.TIPO_ARCHIVO_DOCUMENTO_AUTORIZACION);
			
			if (adjuntos_documento_autorizacion != null && adjuntos_documento_autorizacion.size() > 0) {
				enterpriseInfo.setAdjuntaDocumentoConAutorizacion_radicado(Constantes.PASANTE_CAMPO_SI);
			} else {
				enterpriseInfo.setAdjuntaDocumentoConAutorizacion_radicado(Constantes.PASANTE_CAMPO_NO);
			}
			
			List<ArchivoAdjunto> adjuntos_composicion_accionaria = ArchivoAdjuntoServicio.getInstance().obtenerArchivosAdjuntosPorCargaTipo(idCarga, Constantes.TIPO_ARCHIVO_DOCUMENTO_COMPOSICION_ACCIONARIA);
			
			if (adjuntos_composicion_accionaria != null && adjuntos_composicion_accionaria.size() > 0) {
				enterpriseInfo.setAdjuntaComposicionAccionaria_radicado(Constantes.PASANTE_CAMPO_SI);
			} else {
				enterpriseInfo.setAdjuntaComposicionAccionaria_radicado(Constantes.PASANTE_CAMPO_NO);
			}
			
			BigDecimal salario_minimo = CalculoCategoriaTamanoServicio.getInstance().obtenerSalarioMinimo();
			String salario = salario_minimo != null ? salario_minimo.stripTrailingZeros().toPlainString(): "";
			enterpriseInfo.setSalarioMinimo_radicado(salario);
			
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error al obtener enterpriseInfo para la solicitud NEAR " + idCarga, e);
		}
			
		return enterpriseInfo;
	}
	
	//enviar datos de otros procesos a pasante
		public ResponsePasante obtenerDatosRegistroOtrosProcesos(Integer idCarga){
			try {
				
				EnterpriseInfoDto enterpriseInfo = armarObjOtrosProcesos(idCarga);
				
				Carga cargaImp = CargaServicio.getInstance().obtenerCarga(idCarga);
				logger.info("INFO CARGA " + idCarga + ": " + gson.toJson(cargaImp));
				enterpriseInfo.setFechaSolicitud_radicado(format.format(cargaImp.getFecha_liberacion()));
				
				Interviniente intervinientePonente = new Interviniente();
				intervinientePonente.setName("Ponente");
				
				ProcesoPasante procesoPasante = ProcesoPasanteServicio.getInstance().obtenerProcesoPasante(cargaImp.getId_formato());
				
				// Aunque el objeto DTO se llama SolicitudNearSociedadDto
				// aplica tanto para near como para otros procesos
				SolicitudNearSociedadDto entrada = new SolicitudNearSociedadDto();
				
				if (procesoPasante != null) {
					entrada.setIsNear("S".equals(procesoPasante.getProceso_near()));
					entrada.setProcessId(procesoPasante.getId_proceso_pasante());
				} else {
					entrada.setIsNear(false);
					entrada.setProcessId(1);
				}
				
				entrada.setEnterpriseInfo(enterpriseInfo);
				entrada.setValidatingBy(intervinientePonente);
				entrada.setId(idCarga.toString());
				
//				logger.info("REQUEST PASANTE CARGA " + idCarga + " - " + gson.toJson(entrada));
				
				CallPage call = new CallPage();
				
				String endpoint = getEndpoint("pasante.endpoint");			
				String uri = endpoint + "/oficio";
				
//				SalidaObtenerDatosRegistro salida = call.callJSON(uri, entrada, SalidaObtenerDatosRegistro.class);
//				logger.info("RESPONSE PASANTE CARGA " + idCarga + " - " + gson.toJson(salida));
				
				
				String toUnicode = new JSONObject(gson.toJson(entrada)).toString();
				
				logger.info("Otros Procesos Pasante Request " + idCarga + " : " + toUnicode);
				
				Map<String, String> headers = setupHeaders();
				String response = call.callPost(uri, toUnicode, headers);
				
				ResponsePasante salida = gson.fromJson(response, ResponsePasante.class);
				
				logger.info("Otros Procesos Pasante Response " + idCarga + " : " + response);
				
				return salida;
				
			} catch (JsonSyntaxException e) {
				SimpleLogger.setError(e.getMessage());
				return null;
			} catch (Exception e) {
				SimpleLogger.setError("Error al enviar informacion a pasante para la carga: " + idCarga, e);
				return null;
			}
		}
		
	private EnterpriseInfoDto armarObjOtrosProcesos (Integer idCarga) { 
		
		EnterpriseInfoDto enterpriseInfo= new EnterpriseInfoDto();
		
		Map<String, DocumentoAdjunto> documentos = new HashMap<String, DocumentoAdjunto>();
		documentos = obtenerDocumentosCarga(idCarga);
		enterpriseInfo.setDocumentos(documentos);
		
		Carga cargaImp = CargaServicio.getInstance().obtenerCarga(idCarga);
		logger.debug("INFOCARGA RI CARGA " + idCarga + ": " + gson.toJson(cargaImp));
		
		RegimenInsolvencia infoRegimenInsolvencia = RegimenInsolvenciaServicio.getInstance()
				.obtenerInfoRegimenInsolvenciaPorIdCarga(idCarga);
		logger.debug("INFO SOLICITUD RI CARGA " + idCarga + ": " + gson.toJson(infoRegimenInsolvencia));
		
		
		Persona dst_personaRepLegal = new Persona();
		dst_personaRepLegal = PersonaServicio.getInstance().obtenerPersona(
				infoRegimenInsolvencia.getDeudor().getId_representante_legal());
		
		Persona dst_personaRevFiscal = new Persona(); 
		dst_personaRevFiscal = PersonaServicio.getInstance().obtenerPersona(
					infoRegimenInsolvencia.getProfesionales_asociados().getId_revisor_fiscal());
		
		Persona dst_personaApoderado = new Persona(); 
		dst_personaApoderado = PersonaServicio.getInstance().obtenerPersona(
					infoRegimenInsolvencia.getProfesionales_asociados().getId_apoderado());
		
		String fechaEeffMes = "";
		String vlActivosMes = "";
		String vlPasivosMes = "";
		String vlPatrimonioMes = "";
		String baseContableMes = "";
		String grupoNIIF = "";
		
		if (null != infoRegimenInsolvencia.getInfo_financiera_mensual() && null != infoRegimenInsolvencia.getInfo_financiera_mensual().getFecha_EEFF_mes()) {
			
			
			fechaEeffMes = toFormatyyyyMMDdHHmmss(infoRegimenInsolvencia.getInfo_financiera_mensual().getFecha_EEFF_mes());
			vlActivosMes = infoRegimenInsolvencia.getInfo_financiera_mensual().getActivos_mes_anterior() != null ? 
					infoRegimenInsolvencia.getInfo_financiera_mensual().getActivos_mes_anterior().stripTrailingZeros().toPlainString() : "";
					
			vlPasivosMes = infoRegimenInsolvencia.getInfo_financiera_mensual().getPasivos_mes_anterior() != null ? 
					infoRegimenInsolvencia.getInfo_financiera_mensual().getPasivos_mes_anterior().stripTrailingZeros().toPlainString() : "";
					
			vlPatrimonioMes = infoRegimenInsolvencia.getInfo_financiera_mensual().getPatrimonio_mes_anterior() != null ? 
					infoRegimenInsolvencia.getInfo_financiera_mensual().getPatrimonio_mes_anterior().stripTrailingZeros().toPlainString() : "";	
					
			if (infoRegimenInsolvencia.getInfo_financiera_mensual().getBase_contable_obj() != null) {
				baseContableMes = infoRegimenInsolvencia.getInfo_financiera_mensual().getBase_contable_obj().getNombre();
			}
			
			if (infoRegimenInsolvencia.getInfo_financiera_mensual().getGrupo_NIIF_obj() != null) {
				grupoNIIF = infoRegimenInsolvencia.getInfo_financiera_mensual().getGrupo_NIIF_obj().getNombre();
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
			} 
			
		}
		
		if (infoRegimenInsolvencia.getBienes_sujetos_garantia() != null && Constantes.MI_CAMPO_SI.equals(infoRegimenInsolvencia.getBienes_sujetos_garantia().getBienes_sujetos_garantia())){
			enterpriseInfo.setBienesSujetosGarantiasMobiliarias_radicado(Constantes.PASANTE_CAMPO_SI);
		} else {
			enterpriseInfo.setBienesSujetosGarantiasMobiliarias_radicado(Constantes.PASANTE_CAMPO_NO);
		}
		
		if(infoRegimenInsolvencia.getOtra_informacion() != null && Constantes.MI_CAMPO_SI.equals(infoRegimenInsolvencia.getOtra_informacion().getGarante_terceros())) {
			enterpriseInfo.setGaranteAvalistaCodeudorTerceros_radicado(Constantes.PASANTE_CAMPO_SI);
		} else {
			enterpriseInfo.setGaranteAvalistaCodeudorTerceros_radicado(Constantes.PASANTE_CAMPO_NO);
		}
		
		if(infoRegimenInsolvencia.getOtra_informacion() != null && Constantes.MI_CAMPO_SI.equals(infoRegimenInsolvencia.getOtra_informacion().getPasivos_pensionales())) {
			enterpriseInfo.setPasivosPensionalesCargo_radicado(Constantes.PASANTE_CAMPO_SI);
		} else {
			enterpriseInfo.setPasivosPensionalesCargo_radicado(Constantes.PASANTE_CAMPO_NO);
		}
		
		if (infoRegimenInsolvencia.getOtra_informacion() != null && Constantes.MI_CAMPO_SI.equals(infoRegimenInsolvencia.getOtra_informacion().getCausal_disolucion())){
			enterpriseInfo.setTieneCausalDeDisolucion_radicado(Constantes.PASANTE_CAMPO_SI);
		} else {
			enterpriseInfo.setTieneCausalDeDisolucion_radicado(Constantes.PASANTE_CAMPO_NO);
		}
		
		if (infoRegimenInsolvencia.getOtra_informacion().getComposicion_accionaria() != null) {
			enterpriseInfo.setAdjuntaComposicionAccionaria_radicado(Constantes.PASANTE_CAMPO_SI);
		} else {
			enterpriseInfo.setAdjuntaComposicionAccionaria_radicado(Constantes.PASANTE_CAMPO_NO);
		}
		
		if (infoRegimenInsolvencia.getConjunto_eeff() != null) {
			if (Constantes.MI_CAMPO_SI.equals(infoRegimenInsolvencia.getConjunto_eeff().getReportado_informacion())) {
				enterpriseInfo.setEstadosFinancierosRemitidosAnterioridad_radicado(Constantes.PASANTE_CAMPO_SI);
			} else if (Constantes.MI_CAMPO_NO.equals(infoRegimenInsolvencia.getConjunto_eeff().getReportado_informacion())) {
				enterpriseInfo.setEstadosFinancierosRemitidosAnterioridad_radicado(Constantes.PASANTE_CAMPO_NO);
			}
			
			String baseUltimo = "";
			String basePenultimo = "";
			String baseAntepenultimo = "";
			
			if (infoRegimenInsolvencia.getConjunto_eeff().getBase_contable_ultimo_obj() != null) {
				baseUltimo = infoRegimenInsolvencia.getConjunto_eeff().getBase_contable_ultimo_obj().getNombre();
			}
			if (infoRegimenInsolvencia.getConjunto_eeff().getBase_contable_penultimo_obj() != null) {
				basePenultimo = infoRegimenInsolvencia.getConjunto_eeff().getBase_contable_penultimo_obj().getNombre();
			}
			if (infoRegimenInsolvencia.getConjunto_eeff().getBase_contable_antepenulti_obj() != null) {
				baseAntepenultimo = infoRegimenInsolvencia.getConjunto_eeff().getBase_contable_antepenulti_obj().getNombre();
			}
			
			enterpriseInfo.setBaseGravableUltimoAnio_radicado(baseUltimo);
			enterpriseInfo.setBaseGravablePenultimoAnio_radicado(basePenultimo);
			enterpriseInfo.setBaseGravableAntepenultimoAnio_radicado(baseAntepenultimo);
		}
		
		if (infoRegimenInsolvencia.getSupuestos_admisibilidad() != null) {
			String cesacion_pagos = "";
			
			if (Constantes.TIPO_SOLICITANTE_PNNC.equals(infoRegimenInsolvencia.getTipo_solicitante())) {
				cesacion_pagos = Constantes.LISTA_VALORES_SUPUESTOS_CESACION_PAGO.equals(infoRegimenInsolvencia.getSupuestos_admisibilidad().getSupuestos_pnnc().getId().toString()) ?
						Constantes.PASANTE_CAMPO_SI: Constantes.PASANTE_CAMPO_NO;
			} else {
				cesacion_pagos = Constantes.LISTA_VALORES_SUPUESTOS_CESACION_PAGO.equals(infoRegimenInsolvencia.getSupuestos_admisibilidad().getSupuestos_admisibilidad().getId().toString()) ?
						Constantes.PASANTE_CAMPO_SI: Constantes.PASANTE_CAMPO_NO;
			}
			
			enterpriseInfo.setCesacionPagos_radicado(cesacion_pagos);
		}
		
		String identificacion_apoderado = "";
		String nombre_apoderado = "";
		
		if (dst_personaApoderado != null) {
			identificacion_apoderado = Objects.toString(dst_personaApoderado.getIdentificacion(), "");
			nombre_apoderado = Objects.toString(dst_personaApoderado.getNombreCompleto(), "");
			
		}

		String repLegal_identificacion = "";
		String repLegal_nombre = "";
		
		if (dst_personaRepLegal != null) {
			repLegal_identificacion = Objects.toString(dst_personaRepLegal.getIdentificacion(), "");
			repLegal_nombre = Objects.toString(dst_personaRepLegal.getNombreCompleto(), "");
		}
		
		String revFiscal_identificacion = "";
		String revFiscal_nombre = "";
		
		if (dst_personaRevFiscal != null) {
			revFiscal_identificacion = Objects.toString(dst_personaRevFiscal.getIdentificacion(), "");
			revFiscal_nombre = Objects.toString(dst_personaRevFiscal.getNombreCompleto(), "");
		}
		
		enterpriseInfo.setFechaSolicitud_radicado("");
		enterpriseInfo.setCedulaRepresentanteLegal_radicado(repLegal_identificacion);
		enterpriseInfo.setCedulaRevisorFiscal_radicado(revFiscal_identificacion);
		enterpriseInfo.setRepresentanteLegal_radicado(repLegal_nombre);
		enterpriseInfo.setRevisorFiscal_radicado(revFiscal_nombre);
		enterpriseInfo.setCedulaApoderado_radicado(identificacion_apoderado);
		enterpriseInfo.setApoderado_radicado(nombre_apoderado);			
		
//		enterpriseInfo.setPasivosPensionalesCargo_radicado(infoRegimenInsolvencia.getOtra_informacion().getPasivos_pensionales().toString());

		if(infoRegimenInsolvencia.getConjunto_eeff_id() != null && infoRegimenInsolvencia.getConjunto_eeff().getRadicado_eeff_2019() != null) {
			enterpriseInfo.setEstadosFinancierosAnioAnterior_radicado(infoRegimenInsolvencia.getConjunto_eeff().getRadicado_eeff_2019());
		} else {
			enterpriseInfo.setEstadosFinancierosAnioAnterior_radicado("");
		}
		if(infoRegimenInsolvencia.getConjunto_eeff_id() != null && infoRegimenInsolvencia.getConjunto_eeff().getRadicado_eeff_2018() != null) {
			enterpriseInfo.setEstadosFinancierosPenultimoAnio_radicado(infoRegimenInsolvencia.getConjunto_eeff().getRadicado_eeff_2018());
		} else {
			enterpriseInfo.setEstadosFinancierosPenultimoAnio_radicado("");
		}
		if(infoRegimenInsolvencia.getConjunto_eeff_id() != null && infoRegimenInsolvencia.getConjunto_eeff().getRadicado_eeff_2017() != null) {
			enterpriseInfo.setEstadosFinancierosAntepenultimoAnio_radicado(infoRegimenInsolvencia.getConjunto_eeff().getRadicado_eeff_2017());
		} else {
			enterpriseInfo.setEstadosFinancierosAntepenultimoAnio_radicado("");
		}
		
		enterpriseInfo.setFechaEstadosFinancierosMesAnterior_radicado(fechaEeffMes);
		enterpriseInfo.setValorTotalActivosCorteMesAnterior_radicado(vlActivosMes);
		enterpriseInfo.setValorTotalPasivosCorteMesAnterior_radicado(vlPasivosMes);
		enterpriseInfo.setValorTotalPatrimonioCorteMesAnterior_radicado(vlPatrimonioMes);
		enterpriseInfo.setBaseGravableMesAnterior_radicado(baseContableMes);
		enterpriseInfo.setGrupoNIIF_radicado(grupoNIIF);
		
		enterpriseInfo.setNumeroDeIdentificacion_radicado(infoRegimenInsolvencia.getDeudor().getNumero_identificacion());
		enterpriseInfo.setDomicilioPrincipal_radicado(infoRegimenInsolvencia.getDeudor().getMunicipio().getNombre_ciudad());
		enterpriseInfo.setCiudad_radicado(infoRegimenInsolvencia.getDeudor().getMunicipio().getNombre_ciudad());
		enterpriseInfo.setEmail_radicado(infoRegimenInsolvencia.getDeudor().getCorreo_electronico());
		enterpriseInfo.setIdDelDeudor_radicado(infoRegimenInsolvencia.getDeudorId().toString());
		enterpriseInfo.setTelefonoNotificacion_radicado(infoRegimenInsolvencia.getDeudor().getTelefono());
		
		enterpriseInfo.setNumeroRadicado_radicado(Objects.toString(infoRegimenInsolvencia.getNumero_radicado(), ""));
		enterpriseInfo.setNumeroProceso_radicado(Objects.toString(infoRegimenInsolvencia.getNumero_proceso(), ""));
		enterpriseInfo.setTipoSolicitante_radicado(Objects.toString(infoRegimenInsolvencia.getTipo_de_solicitante(), ""));
		enterpriseInfo.setTipoSolicitud_radicado(Objects.toString(infoRegimenInsolvencia.getTipo_solicitud_obj().getNombre(), ""));
		enterpriseInfo.setNombreSolicitante_radicado(Objects.toString(infoRegimenInsolvencia.getDeudor().getRazon_social(), ""));
		enterpriseInfo.setDireccionNotificacionIdentificacion_radicado(Objects.toString(infoRegimenInsolvencia.getDeudor().getDireccion(), ""));
		
		if (Constantes.MI_CAMPO_SI.equals(infoRegimenInsolvencia.getDeudor().getRepresentante_limitacion())) {
			enterpriseInfo.setRepresentanteConLimitaciones_radicado(Constantes.PASANTE_CAMPO_SI);
		} else if (Constantes.MI_CAMPO_NO.equals(infoRegimenInsolvencia.getDeudor().getRepresentante_limitacion())) {
			enterpriseInfo.setRepresentanteConLimitaciones_radicado(Constantes.PASANTE_CAMPO_NO);
		}
		
		if (infoRegimenInsolvencia.getDeudor().getArchivo_autoriza_replegal() != null) {
			enterpriseInfo.setAdjuntaDocumentoConAutorizacion_radicado(Constantes.PASANTE_CAMPO_SI);
		} else {
			enterpriseInfo.setAdjuntaDocumentoConAutorizacion_radicado(Constantes.PASANTE_CAMPO_NO);
		}
		
		if (infoRegimenInsolvencia.getAutorizacion_junta() != null) {
			String fecha_acta = infoRegimenInsolvencia.getAutorizacion_junta().getFecha_del_acta() != null? 
					toFormatyyyyMMDdHHmmss(infoRegimenInsolvencia.getAutorizacion_junta().getFecha_del_acta()): "";
			enterpriseInfo.setFechaActa_radicado(fecha_acta);
		}
		
		if (infoRegimenInsolvencia.getRelacion_de_pasivos() != null) {
			Integer pasivos_por_retenciones = infoRegimenInsolvencia.getRelacion_de_pasivos().getPasivos_por_retenciones();
			String tiene_pasivos_retenciones = (pasivos_por_retenciones != null && Constantes.MI_CAMPO_SI.equals(pasivos_por_retenciones))? Constantes.PASANTE_CAMPO_SI: Constantes.PASANTE_CAMPO_NO;
			enterpriseInfo.setTienePasivosPorRetencionesConFisco_radicado(tiene_pasivos_retenciones);
			
			Integer pasivos_descuentos = infoRegimenInsolvencia.getRelacion_de_pasivos().getPasivos_por_descuentos();
			String tiene_pasivos_descuentos = (pasivos_descuentos != null && Constantes.MI_CAMPO_SI.equals(pasivos_descuentos))? Constantes.PASANTE_CAMPO_SI: Constantes.PASANTE_CAMPO_NO;
			enterpriseInfo.setTienePasivosPorDescuentosTrabajadores_radicado(tiene_pasivos_descuentos);
			
			Integer pasivos_aportes = infoRegimenInsolvencia.getRelacion_de_pasivos().getPasivos_por_aportes();
			String tiene_pasivos_aportes = (pasivos_aportes != null && Constantes.MI_CAMPO_SI.equals(pasivos_aportes))? Constantes.PASANTE_CAMPO_SI: Constantes.PASANTE_CAMPO_NO;
			enterpriseInfo.setTienePasivosPorSeguridadSocial_radicado(tiene_pasivos_aportes);
		}
		
		if (infoRegimenInsolvencia.getInfo_financiera_anual() != null) {
			String valor_activos = infoRegimenInsolvencia.getInfo_financiera_anual().getActivos_ano_anterior() != null ?
					infoRegimenInsolvencia.getInfo_financiera_anual().getActivos_ano_anterior().stripTrailingZeros().toPlainString():"";
			enterpriseInfo.setValorTotalActivosAnoAnterior_radicado(valor_activos);
			
			String valor_pasivos = infoRegimenInsolvencia.getInfo_financiera_anual().getPasivos_ano_anterior() != null ?
					infoRegimenInsolvencia.getInfo_financiera_anual().getPasivos_ano_anterior().stripTrailingZeros().toPlainString():"";
			enterpriseInfo.setValorTotalPasivosAnoAnterior_radicado(valor_pasivos);
					
			String valor_ingresos = infoRegimenInsolvencia.getInfo_financiera_anual().getIngresos_ano_anterior() != null ?
					infoRegimenInsolvencia.getInfo_financiera_anual().getIngresos_ano_anterior().stripTrailingZeros().toPlainString():"";
			enterpriseInfo.setValorTotalIngresosOrdinariosAnoAnterior_radicado(valor_ingresos);
			
			String valor_otros = infoRegimenInsolvencia.getInfo_financiera_anual().getOtros_ing_ano_anterior() != null ?
					infoRegimenInsolvencia.getInfo_financiera_anual().getOtros_ing_ano_anterior().stripTrailingZeros().toPlainString():"";
			enterpriseInfo.setValorTotalOtrosIngresosAnoAnterior_radicado(valor_otros);
			
		}
		
		if (infoRegimenInsolvencia.getProfesionales_asociados() != null) {
			String tiene_contador = infoRegimenInsolvencia.getProfesionales_asociados().getId_contador() != null || infoRegimenInsolvencia.getProfesionales_asociados().getId_contador_pnnc() != null 
					? Constantes.PASANTE_CAMPO_SI: Constantes.PASANTE_CAMPO_NO;
			enterpriseInfo.setTieneContador_radicado(tiene_contador);
			
			String tiene_revisor = infoRegimenInsolvencia.getProfesionales_asociados().getId_revisor_fiscal() != null ? Constantes.PASANTE_CAMPO_SI: Constantes.PASANTE_CAMPO_NO;
			enterpriseInfo.setTieneRevisorFiscal_radicado(tiene_revisor);
			
			String tiene_apoderado = infoRegimenInsolvencia.getProfesionales_asociados().getId_apoderado() != null ? Constantes.PASANTE_CAMPO_SI: Constantes.PASANTE_CAMPO_NO;
			enterpriseInfo.setTieneApoderado_radicado(tiene_apoderado);
		}
		
		BigDecimal salario_minimo = CalculoCategoriaTamanoServicio.getInstance().obtenerSalarioMinimo();
		String salario = salario_minimo != null ? salario_minimo.stripTrailingZeros().toPlainString(): "";
		enterpriseInfo.setSalarioMinimo_radicado(salario);
		
		if (infoRegimenInsolvencia.getCondicion_controlante() != null) {
			if (infoRegimenInsolvencia.getCondicion_controlante().getEs_controlante_de_socieda() != null) {
				String controlante = Constantes.MI_CAMPO_SI.toString().equals(infoRegimenInsolvencia.getCondicion_controlante().getEs_controlante_de_socieda()) ? Constantes.PASANTE_CAMPO_SI: Constantes.PASANTE_CAMPO_NO;
				enterpriseInfo.setEsControlanteDeSociedad_radicado(controlante);				
			}
			
			String nit_pnnc = Objects.toString(infoRegimenInsolvencia.getCondicion_controlante().getNit(), "");
			enterpriseInfo.setNitPNNC_radicado(nit_pnnc);
			
			String nombre_sociedad_pnnc = Objects.toString(infoRegimenInsolvencia.getCondicion_controlante().getNombre_de_la_sociedad(), "");
			enterpriseInfo.setNombreSociedadPNNC_radicado(nombre_sociedad_pnnc);
			
			String numero_radicado_pnnc = Objects.toString(infoRegimenInsolvencia.getCondicion_controlante().getRadicado_solicitud_auto(), "");
			enterpriseInfo.setNumeroRadicadoSolicitudoAuto_radicado(numero_radicado_pnnc);
			
			String condicion_deudor = "";
			if (infoRegimenInsolvencia.getCondicion_controlante().getCondicion_deudor_obj() != null) {
				condicion_deudor = infoRegimenInsolvencia.getCondicion_controlante().getCondicion_deudor_obj().getNombre();
			}
			enterpriseInfo.setCondicionDeudor_radicado(condicion_deudor);				
			
		}
		
		if (infoRegimenInsolvencia.getBienes_acreedores() != null) {
			Date fecha_bienes_acreedores = infoRegimenInsolvencia.getBienes_acreedores().getFecha_bienes_acreedores();
			String formato_fecha_bienes = fecha_bienes_acreedores != null ? format.format(fecha_bienes_acreedores) : "";
			enterpriseInfo.setFechaRelacionBienesAcreedores_radicado(formato_fecha_bienes);
			
		}
		
		if(null != infoRegimenInsolvencia.getTipo_solicitud() 
				&& infoRegimenInsolvencia.getTipo_solicitud().equals(Constantes.TIPO_SOLICITUD_LIQ_JUDICIAL)){
			if(null != infoRegimenInsolvencia.getAutorizacion_junta() && null != infoRegimenInsolvencia.getAutorizacion_junta().getFecha_del_acta()) {
				Date fecha_acta = infoRegimenInsolvencia.getAutorizacion_junta().getFecha_del_acta();
				Date fecha_presentacion = cargaImp.getFecha_carga();
				
				Calendar fechaActaCalendar = Calendar.getInstance();
				fechaActaCalendar.setTime(fecha_acta);
				Calendar fechaPresentacionCalendar = Calendar.getInstance();
				fechaPresentacionCalendar.setTime(fecha_presentacion);
				
				logger.debug("Fecha Acta: " + fechaActaCalendar + " Fecha presentacion: " + fechaPresentacionCalendar);
				
			}
			
		}
		
		
		return enterpriseInfo;
	}
	
	// Funcion para pruebas de desarrollo
	// Entrega el objeto enterpriseInfo para solicitudes de RI
	public EnterpriseInfoDto obtenerInfoSolicitudesRI(Integer idCarga) {
		EnterpriseInfoDto enterpriseInfoDto = armarObjOtrosProcesos(idCarga);
		
		logger.debug("INFO RI CARGA " + idCarga + " : " + gson.toJson(enterpriseInfoDto));
		
		return enterpriseInfoDto;
	}
	
	private Map<String, DocumentoAdjunto> obtenerDocumentosCarga(Integer idCarga){
		Map<String, DocumentoAdjunto> documentos = new HashMap<String, DocumentoAdjunto>();
		List<ArchivoAdjunto> archivosSolicitud = ArchivoAdjuntoServicio.getInstance()
				.obtenerArchivosAdjuntosPorCarga(idCarga, null, null);
		
		for(ArchivoAdjunto archivoAdjunto : archivosSolicitud){
			DocumentoAdjunto documentoAdjunto = new DocumentoAdjunto();
			
			String descripcionArchivo = null;
			if (archivoAdjunto.getId_tipo_archivo() != null) {
				TipoArchivo tipo = TipoArchivoServicio.getInstance().obtenerTipoArchivo(archivoAdjunto.getId_tipo_archivo());
				descripcionArchivo = tipo.getNombre();
				
			} else {				
				descripcionArchivo = archivoAdjunto.getDescripcion();
			}
				
			documentoAdjunto.setDescripcion(descripcionArchivo);
			documentoAdjunto.setUrl(ParametrosInicio.getProperty("pasante.endpoint.descargaarchivos")+"?id_archivo_adjunto="+archivoAdjunto.getId_archivo_adjunto());
			documentoAdjunto.setNombreArchivo(archivoAdjunto.getNombre_archivo());
			documentoAdjunto.setNombreInterno("archivo_" + archivoAdjunto.getId_carga() + "_" + archivoAdjunto.getId_archivo_adjunto() + ".osm");
			documentoAdjunto.setExtension(archivoAdjunto.getExtension_archivo());
			documentoAdjunto.setIdCarga(archivoAdjunto.getId_carga());
			documentoAdjunto.setIdArchivoInterno(archivoAdjunto.getId_archivo_adjunto().toString());
			
			if(archivoAdjunto.getId_archivo_adjunto()< 11){
				documentos.put("0", documentoAdjunto);
			}else{
				String contador = ConstantesNombreArchivos.getInstance().getContador().toString();
				String id_documento = Objects.toString(archivoAdjunto.getId_tipo_archivo()!=null?(archivoAdjunto.getId_tipo_archivo()-10):null,contador);
				if (documentos.containsKey(id_documento)){
					documentos.put(contador, documentoAdjunto);
				} else {
					documentos.put(id_documento, documentoAdjunto);					
				}
			}
							
		}
		return documentos;
	}
	
	public SolicitudNearSociedadDto ConsultaMiPasante(Integer idCarga){
		try {
			
			EnterpriseInfoDto enterpriseInfo = null;
			SolicitudNearSociedadDto entrada = new SolicitudNearSociedadDto();
			
			Carga cargaImp = CargaServicio.getInstance().obtenerCarga(idCarga);
			
			Integer id_carga_proceso = idCarga;
			
			if (Constantes.ESTRUCTURA_NEAR_SOCIEDAD.equals(InfoRadicadoLocalServicio.getInstance().obtenerNombreEstructuraFormatoPorCarga(idCarga))) {
				enterpriseInfo = obtenerEnterpriseInfoNear(idCarga);				
			} else if (Constantes.ESTRUCTURA_REGIMEN_INSOLVENCIA.equals(InfoRadicadoLocalServicio.getInstance().obtenerNombreEstructuraFormatoPorCarga(idCarga))) {
				enterpriseInfo = armarObjOtrosProcesos(idCarga);
			} else if (Constantes.ESTRUCTURA_RESPUESTA_REQUERIMIENTO.equals(InfoRadicadoLocalServicio.getInstance().obtenerNombreEstructuraFormatoPorCarga(idCarga))){
				enterpriseInfo = obtenerInfoRtaRequerimiento(idCarga);
				
				RespuestaRequerimiento solicitud = RespuestaRequerimientoServicio.getInstance().obtenerRespuestaRequerimientoBase(idCarga);
				
				id_carga_proceso = solicitud.getNumero_solicitud();
				
			}
			enterpriseInfo.setFechaSolicitud_radicado(format.format(cargaImp.getFecha_liberacion()));
						
			Interviniente uno = new Interviniente();
			uno.setName("");
			entrada.setValidatingBy(uno);
			entrada.setEnterpriseInfo(enterpriseInfo);
			entrada.setId(idCarga.toString());
			
			Carga cargaProceso = CargaServicio.getInstance().obtenerCarga(id_carga_proceso);
			
			ProcesoPasante procesoPasante = ProcesoPasanteServicio.getInstance().obtenerProcesoPasante(cargaProceso.getId_formato());
						
			if (procesoPasante != null) {
				entrada.setIsNear("S".equals(procesoPasante.getProceso_near()));
				entrada.setProcessId(procesoPasante.getId_proceso_pasante());
			} else {
				entrada.setIsNear(false);
				entrada.setProcessId(1);
			}
			
			
			return entrada;
			
		} catch (JsonSyntaxException e) {
			SimpleLogger.setError(e.getMessage());
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//enviar datos Respuesta Requerimiento a pasante
	public ResponsePasante enviarRtaRequerimiento(Integer idCarga){
		try {
			
			CallPage call = new CallPage();
			
			EnterpriseInfoDto enterpriseInfo = obtenerInfoRtaRequerimiento(idCarga);
			RespuestaRequerimiento infoSolicitud = RespuestaRequerimientoServicio.getInstance().obtenerRespuestaRequerimientoBase(idCarga);
			
			logger.debug("INFO SOLICITUD BASE: "+ gson.toJson(infoSolicitud));
			
			Interviniente intervinientePonente = new Interviniente();
			intervinientePonente.setName("Ponente");
			
			SolicitudNearSociedadDto entrada = new SolicitudNearSociedadDto();
			
			String estructura = InfoRadicadoLocalServicio.getInstance().obtenerNombreEstructuraFormatoPorCarga(infoSolicitud.getNumero_solicitud());
			
			logger.debug("ESTRUCTURA: "+estructura);
			
			entrada.setIsNear(estructura.equals(Constantes.ESTRUCTURA_NEAR_SOCIEDAD));
			entrada.setEnterpriseInfo(enterpriseInfo);
			entrada.setProcessId(1);
			entrada.setValidatingBy(intervinientePonente);
			entrada.setId(idCarga.toString());
			entrada.setIdPadre(Objects.toString(infoSolicitud.getNumero_solicitud(), ""));
			
			String endpoint = getEndpoint("pasante.endpoint");			
			String uri = endpoint + "/oficio";
			
			String toUnicode = new JSONObject(gson.toJson(entrada)).toString();
			
			logger.info("RTA_REQ Pasante Request " + idCarga + " : " + toUnicode);
			
			Map<String, String> headers = setupHeaders();
			String response = call.callPost(uri, toUnicode, headers);
			
			ResponsePasante salida = gson.fromJson(response, ResponsePasante.class);
			
			logger.info("RTA_REQ Pasante Response " + idCarga + " : " + response);
			
			return salida;
			
		} catch (JsonSyntaxException e) {
			SimpleLogger.setError(e.getMessage());
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// Obtener info Respueta Requerimiento
	public EnterpriseInfoDto obtenerInfoRtaRequerimiento (Integer idCarga) {
		EnterpriseInfoDto enterpriseInfo = new EnterpriseInfoDto();
		
		try {
			RespuestaRequerimiento rtaRequerimiento = RespuestaRequerimientoServicio.getInstance().obtenerRespuestaRequerimientoCompleto(idCarga);
			String dataSolicitud = gson.toJson(rtaRequerimiento);
			logger.info("INFO SOLICITUD RTA_RQ IDCARGA: "+idCarga+"\n"+dataSolicitud);
			
			Carga cargaImp = CargaServicio.getInstance().obtenerCarga(idCarga);
			logger.info("INFO CARGA RTA_RQ IDCARGA: "+idCarga+"\n"+gson.toJson(cargaImp));
			
			if(null != cargaImp && null != cargaImp.getFecha_liberacion()) {
				enterpriseInfo.setFechaSolicitud_radicado(format.format(cargaImp.getFecha_liberacion()));
			} else {
				enterpriseInfo.setFechaSolicitud_radicado("");
			}
			
			if(null != rtaRequerimiento) {
				enterpriseInfo.setNumeroProceso_radicado(Objects.toString(rtaRequerimiento.getNumero_proceso(), ""));
				enterpriseInfo.setNumeroRadicado_radicado(Objects.toString(rtaRequerimiento.getNumero_radicado_postal(),""));
				if(null != rtaRequerimiento.getDependencia()) {
					enterpriseInfo.setDependencia_radicado(Objects.toString(rtaRequerimiento.getDependencia_obj().getNombreDependencia(),""));
				} else {
					enterpriseInfo.setDependencia_radicado("");
				}
			} else {
				enterpriseInfo.setNumeroProceso_radicado("");
				enterpriseInfo.setNumeroRadicado_radicado("");
				enterpriseInfo.setDependencia_radicado("");
			}
			
			Map<String, DocumentoAdjunto> documentos = new HashMap<String, DocumentoAdjunto>();
			documentos = obtenerDocumentosCarga(idCarga);
			
			enterpriseInfo.setDocumentos(documentos);
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error al obtener enterpriseInfo para Respueta Requerimiento " + idCarga, e);
		}
			
		return enterpriseInfo;
	}

	//envio de informacion a pasante
	public String obtenterSolicitudPorId(String id) {
		
		try {
			
			CallPage call = new CallPage();
			
			Map<String, String> headers = setupHeaders();
			
			String endpoint = getEndpoint("pasante.endpoint");			
			String uri = endpoint + "/oficio/"+id;
			SimpleLogger.setDebug("URI: " + uri);
			
			String response = call.callGet(uri, headers);
			SimpleLogger.setDebug("RESPONSE: " + response);
			
			return response;
			
		} catch (JsonSyntaxException e) {
			SimpleLogger.setError(e.getMessage());
			return null;
		}
	}
	
	public String validarReglaSolicitud(String idOficio, String idRequisito, String bodyJSON){
		CallPage call = new CallPage();
		
		String endpoint = getEndpoint("pasante.endpoint");			
		String uri = endpoint + "/oficio/"+idOficio+"/requisito/"+idRequisito+"/validar";
		SimpleLogger.setDebug("URI: " + uri);
		
		String response = call.callJSON(uri, bodyJSON, String.class);
		SimpleLogger.setDebug("RESPONSE: " + response);
		
		return response;
	}
	
	/*
	 * recibe url o endpoint sin la base ejemplo /oficio/110555/requisito/001/validar
	 * method puede ser get o post
	 * body de la solicitud si aplica
	 * */
	public String comunicacionPasante(String url, String method, String body) throws ParseException{
		CallPage call = new CallPage();
		
		Map<String, String> headers = setupHeaders();
		
		String endpoint = getEndpoint("pasante.endpoint");			
		String uri = endpoint + url;
		SimpleLogger.setDebug("URI: " + uri);
		
		String response = "{}";
		if(method.equals("get")){
			response = call.callGet(uri, headers);
		}else if(method.equals("post")){
			
			String toUnicode = new JSONObject(body).toString();
			
			response = call.callPost(uri, toUnicode, headers);
		}else{
			response ="{\"error\":\"Metodo no soportado\"}";
		}
		SimpleLogger.setDebug("RESPONSE: " + response);
		
		return response;
	}
	
	private String toFormatyyyyMMDdHHmmss(Date valor) {

		if (valor == null) {
			return "";
		}

		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) valor).trim();
		} catch (Exception e) {
		}

		return valor.toString();
	}	
	
	public ResponsePasante anularRadicado (Integer id_carga) {
		
		try {
			CallPage call = new CallPage();
			
			String endpoint = getEndpoint("pasante.endpoint");			
			String url = endpoint + "/oficio/" + id_carga + "/radicado";
			
			Map<String, String> headers = setupHeaders();
			
			String resp = call.call(url, null, headers, "DELETE");
			
			SimpleLogger.setInfo("PasanteAppRestClient.anularRadicado: Response " + id_carga + " - " + resp);
			
			return gson.fromJson(resp, ResponsePasante.class);
			
		} catch (Throwable e) {
			SimpleLogger.setError("PasanteAppRestClient.anularRadicado: Error ", e);
			return null;
		}
		
		
	}
	
}
		

