package com.osmosyscol.datasuite.mein.servicios;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.net.aso.r;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.dto.TipoArchivo;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;
import com.osmosyscol.datasuite.mein.builder.RespuestaRequerimientoFetchBuilder;
import com.osmosyscol.datasuite.mein.domain.Sociedad;
import com.osmosyscol.datasuite.mein.dtos.Deudor;
import com.osmosyscol.datasuite.mein.dtos.ObjGenerico;
import com.osmosyscol.datasuite.mein.dtos.PersonaOP;
import com.osmosyscol.datasuite.mein.dtos.RegimenInsolvencia;
import com.osmosyscol.datasuite.mein.dtos.RespuestaRequerimiento;
import com.osmosyscol.datasuite.mein.dtos.SolicitudNearSociedad;
import com.osmosyscol.datasuite.mein.dtos.TipoDeDocumento;
import com.osmosyscol.datasuite.mein.logica.constantes.Constantes;
import com.osmosyscol.datasuite.near.servicios.InfoRadicadoLocalServicio;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

public class RespuestaRequerimientoServicio {

	private static RespuestaRequerimientoServicio instance;
	static final Logger logger = LoggerFactory.getLogger(RespuestaRequerimientoServicio.class);
	static final Gson gson = new Gson();
	static final Map<String, TipoDeDocumento> MAPA_TIPOS_DOC = TipoDeDocumentoServicio.getInstance().getMapForCodHts();
	
	private RespuestaRequerimientoServicio () {
	}
	
	public static RespuestaRequerimientoServicio getInstance () {
		if (instance == null) {
			instance = new RespuestaRequerimientoServicio();
		}
		
		return instance;
	}
	
	public List<Integer> obtenerRespuestasRequerimientoPorSolicitudInicial (Integer solicitud_inicial) {
		List<Integer> solicitudes = null;
		
		try {
			
			solicitudes = DS_SqlUtils.queryForList(Integer.class, 
							"SELECT IDCARGA FROM $RESPUESTA REQUERIMIENTO$ WHERE $RESPUESTA REQUERIMIENTO.NUMERO SOLICITUD$ = $I(" + solicitud_inicial + ")$ ORDER BY IDCARGA");
			
		} catch (Exception e) {
			SimpleLogger.setError("Error al obtener las respuestas de requerimiento de la solicitud " + solicitud_inicial, e);
		}
		
		return solicitudes;
	}
	
	public RespuestaRequerimiento obtenerRespuestaRequerimientoBase (Integer id_carga) {
		RespuestaRequerimientoFetchBuilder builder = RespuestaRequerimientoFetchBuilder.newInstance();
		RespuestaRequerimiento solicitud = builder
				.withIdCarga(id_carga)
				.fetchRespuestaRequerimiento()
				.getSolicitud();
		
		return solicitud;
	}
	
	public RespuestaRequerimiento obtenerRespuestaRequerimientoCompleto (Integer id_carga) {
		RespuestaRequerimientoFetchBuilder builder = RespuestaRequerimientoFetchBuilder.newInstance();
		RespuestaRequerimiento solicitud = builder
				.withIdCarga(id_carga)
				.fetchRespuestaRequerimiento()
				.fetchRespuestaRequerimiento_Deudor()
				.fetchRespuestaRequerimiento_Deudor_TipoIdentificacion()
				.fetchRespuestaRequerimiento_Deudor_Pais()
				.fetchRespuestaRequerimiento_Deudor_Departamento()
				.fetchRespuestaRequerimiento_DeudorMunicipio()
				.fetchRespuestaRequerimiento_Dependencia()
				.fetchRespuestaRequerimiento_IntendenciaRegional()
				.fetchRespuestaRequerimiento_MedioEnvio()
				.fetchRespuestaRequerimiento_ProcesoClase()
				.fetchRespuestaRequerimiento_TipoCuaderno()
				.fetchRespuestaRequerimiento_TipoSeguridad()
				.fetchRespuestaRequerimiento_TipoSolicitante()
				.fetchRespuestaRequerimiento_TipoSolicitud()
				.fetchRespuestaRequerimiento_Tramite()
				.fetchRespuestaRequerimiento_TramiteOtrosDocumentos()
				.fetchRespuestaRequerimiento_TipoAutoActa()
				.fetchRespuestaRequerimiento_Serie()
				.fetchRespuestaRequerimiento_SubSerie()
				.getSolicitud();
		
		return solicitud;
	}
	
	public RespuestaRequerimiento obtenerSolicitudPorNumeroProceso (String numero_proceso) {
		RespuestaRequerimiento solicitud = null;
		try {
			Integer id_carga = DS_SqlUtils.queryForObject(Integer.class, 
				"select idcarga from $solicitud near sociedad$ where $solicitud near sociedad.numero proceso$ = $S(" + numero_proceso + ")$ "
			+	"union "
			+	"select idcarga from $regimen de insolvencia$ where $regimen de insolvencia.numero de proceso$ = $S(" + numero_proceso + ")$");
			
			if (id_carga != null) {
				solicitud = obtenerInformacionSolicitudPadre(id_carga);
			}
		} catch(Exception e) {
			SimpleLogger.setError("Ha ocurrido un error al obtener la solicitud por numero de proceso " + numero_proceso, e);
		}
		
		return solicitud;
	}
	
	public RespuestaRequerimiento obtenerInformacionSolicitudPadre (Integer id_carga) {
		RespuestaRequerimiento solicitud = null;
		try {
			String estructura = InfoRadicadoLocalServicio.getInstance().obtenerNombreEstructuraFormatoPorCarga(id_carga);
			
			if (Constantes.ESTRUCTURA_NEAR_SOCIEDAD.equals(estructura)) {
				solicitud = obtenerInformacionSolicitudNear(id_carga);
			} else if (Constantes.ESTRUCTURA_REGIMEN_INSOLVENCIA.equals(estructura)) {
				solicitud = obtenerInformacionSolicitudRegimen(id_carga);
			}
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error al obtener la informacion de la solicitud padre " + id_carga, e);
		}
		
		logger.debug("SOLICITUD PADRE: "+gson.toJson(solicitud));
				
		return solicitud;
	}
	
	public RespuestaRequerimiento obtenerInformacionSolicitudNear(Integer id_carga) {
		RespuestaRequerimiento solicitud = new RespuestaRequerimiento();
		
		SolicitudNearSociedad solicitud_padre = SolicitudNearSociedadServicio.getInstance().obtenerSolicitudNearSociedadPorCarga(id_carga);
		
		logger.debug("SOLICITUD NEAR PADRE: " + gson.toJson(solicitud_padre));
		
		Deudor deudor = new Deudor();

		if (null != solicitud_padre) {
			
			com.osmosyscol.datasuite.mein.dtos.Sociedad deudorPadre = null != solicitud_padre.getDeudor() ? solicitud_padre.getDeudor() : null;
			
			if(null != deudorPadre) {
			
				TipoDeDocumento tipoDocumento = DS_SqlUtils.queryForObject(TipoDeDocumento.class, 
						"select * from $tipo de documento$ where $tipo de documento.cod_hts$ = $S(" + deudorPadre.getDatos_basicos().getTipo_identificacion() + ")$");
				
				deudor.setTipo_identificacion(tipoDocumento);
				deudor.setNumero_identificacion(deudorPadre.getDatos_basicos().getIdentificacion());
				deudor.setRazon_social(deudorPadre.getDatos_basicos().getNombre_completo());
				deudor.setTelefono(deudorPadre.getDatos_basicos().getTelefono());
				deudor.setPais_dane(deudorPadre.getPais_dane_obj());
				deudor.setDepartamento(deudorPadre.getDepartamentoObj());
				deudor.setMunicipio(deudorPadre.getMunicipioObj());
				deudor.setDireccion(deudorPadre.getDatos_basicos().getDireccion());
				deudor.setCorreo_electronico(deudorPadre.getDatos_basicos().getCorreo());
				
				PersonaOP repLegal = new PersonaOP();
				if(null != deudorPadre.getRepresentante_legal()){
					repLegal.setIdentificacion(deudorPadre.getRepresentante_legal().getIdentificacion());
					repLegal.setTipo_identificacion(deudorPadre.getRepresentante_legal().getTipo_identificacion());
					repLegal.setNombre_completo(deudorPadre.getRepresentante_legal().getNombre_completo());
				} else {
					repLegal.setIdentificacion("");
					repLegal.setTipo_identificacion("");
					repLegal.setNombre_completo("");
				}
				deudor.setRepresentante_legal(repLegal);
			} else {
				deudor.setTipo_identificacion(null);
				deudor.setNumero_identificacion("");
				deudor.setRazon_social("");
				deudor.setTelefono("");
				deudor.setPais_dane(null);
				deudor.setDepartamento(null);
				deudor.setMunicipio(null);
				deudor.setDireccion("");
				deudor.setCorreo_electronico("");
			}
			
			solicitud.setDeudor_obj(deudor);
			
			solicitud.setNumero_solicitud(id_carga);
			solicitud.setNumero_proceso(solicitud_padre.getNumero_proceso());
			solicitud.setNumero_radicado_postal(solicitud_padre.getNumero_radicado_postal());
			solicitud.setTramite_obj(solicitud_padre.getTramite_obj());
			
			ObjGenerico tipoSolicitante = DS_SqlUtils.queryForObject(ObjGenerico.class, 
				"select * from $tipo solicitante$ where id = $I(" + solicitud_padre.getTipo_solicitante() + ")$");
			
			solicitud.setTipo_solicitante_obj(tipoSolicitante);
			
			solicitud.setTipo_solicitud_obj(solicitud_padre.getTipo_solicitud().getTipo_solicitud_obj());
			
			
			
			ObjGenerico tramiteOtrosDocumentos = DS_SqlUtils.queryForObject(ObjGenerico.class, 
				"select * from $tramite otros documentos$ where id = $I(" + Constantes.TRAMITE_RTA_INADMISION + ")$");
			
			solicitud.setTramite_otros_documentos_obj(tramiteOtrosDocumentos);
			
			return solicitud;
		} else {
			return null;
		}
	}
	
	public RespuestaRequerimiento obtenerInformacionSolicitudRegimen (Integer id_carga) {
		RespuestaRequerimiento solicitud = new RespuestaRequerimiento();
		
		RegimenInsolvencia solicitud_padre = RegimenInsolvenciaServicio.getInstance().obtenerInfoRegimenInsolvenciaPorIdCarga(id_carga);
		
		
		
		logger.debug("SOLICITUD RI PADRE: " + gson.toJson(solicitud_padre));
		
		if (null != solicitud_padre) {
		
//			Deudor deudorPadre = null != solicitud_padre.getDeudor() ? solicitud_padre.getDeudor() : null;
			
			solicitud.setDeudor_obj(solicitud_padre.getDeudor());
			
			solicitud.setNumero_solicitud(id_carga);
			solicitud.setNumero_proceso(solicitud_padre.getNumero_proceso());
			solicitud.setNumero_radicado_postal(solicitud_padre.getNumero_radicado());
			solicitud.setTramite_obj(solicitud_padre.getTramite());
			
			ObjGenerico tipoSolicitante = DS_SqlUtils.queryForObject(ObjGenerico.class, 
					"select * from $tipo solicitante$ where id = $I(" + solicitud_padre.getTipo_solicitante() + ")$");
			
			solicitud.setTipo_solicitante_obj(tipoSolicitante);
			
			ObjGenerico tipoSolicitud = DS_SqlUtils.queryForObject(ObjGenerico.class, 
					"select * from $tipo de solicitud$ where id = $I(" + solicitud_padre.getTipo_solicitud() +")$"); 
			
			solicitud.setTipo_solicitud_obj(tipoSolicitud);
			
			PersonaOP repLegal = new PersonaOP();
			if(null != solicitud_padre.getDeudor() && null != solicitud_padre.getDeudor().getId_representante_legal()){
				Persona datosRepLegal = PersonaServicio.getInstance().obtenerPersona(solicitud_padre.getDeudor().getId_representante_legal());
				TipoDeDocumento tipoDocumento = DS_SqlUtils.queryForObject(TipoDeDocumento.class, 
						"select * from $tipo de documento$ where $tipo de documento.codigo$ = $I(" + datosRepLegal.getTipo_documento().toString() + ")$");
				
				repLegal.setIdentificacion(datosRepLegal.getIdentificacion());
				repLegal.setTipo_identificacion(tipoDocumento.getCod_hts());
				repLegal.setNombre_completo(datosRepLegal.getNombreCompleto());
			} else {
				repLegal.setIdentificacion("");
				repLegal.setTipo_identificacion("");
				repLegal.setNombre_completo("");
			}
			solicitud.getDeudor_obj().setRepresentante_legal(repLegal);
						
			ObjGenerico tramiteOtrosDocumentos = DS_SqlUtils.queryForObject(ObjGenerico.class, 
				"select * from $tramite otros documentos$ where id = $I(" + Constantes.TRAMITE_RTA_INADMISION + ")$");
			
			solicitud.setTramite_otros_documentos_obj(tramiteOtrosDocumentos);
			
			return solicitud;
		} else {
			return null;
		}
	}
	
	public List<TipoArchivo> obtenerTiposArchivosPorSolicitanteSolicitud (Integer tipo_solicitante, Integer tipo_solicitud) {
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("tipo_solicitante", tipo_solicitante);
			params.put("tipo_solicitud", tipo_solicitud);
			List<TipoArchivo> tipos = DS_SqlUtils.queryForList(TipoArchivo.class, 
				"select $tipos archivo$.id id_tipo_archivo, $tipos archivo$.nombre nombre, 'S' activo "
				+ "from $homologacion documentos$ inner join $tipos archivo$ on $homologacion documentos.tipo archivo$ = $tipos archivo$.id "
				+ "where $homologacion documentos.tipo solicitud$ = #tipo_solicitud# and $homologacion documentos.tipo de solicitante$ = #tipo_solicitante# "
				+ "order by $tipos archivo$.id", 
			params);
			
			return tipos;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en obtenerTiposArchivosPorSolicitanteSolicitud - "
					+ "tipo_solicitante: " + tipo_solicitante + ", tipo_solicitud: " + tipo_solicitud, e);
			return null;
		}
	}
	
	public Boolean actualizarRespuestaRequerimiento (RespuestaRequerimiento solicitud) {
		
		String sql_update = 
			"UPDATE $RESPUESTA REQUERIMIENTO$ SET "
			+ "$RESPUESTA REQUERIMIENTO.DEPENDENCIA$ = $dependencia$, "
			+ "$RESPUESTA REQUERIMIENTO.INTENDENCIA REGIONAL$ = #intendencia_regional#, "
			+ "$RESPUESTA REQUERIMIENTO.MEDIO ENVIO$ = $medio_envio$, "
			+ "$RESPUESTA REQUERIMIENTO.TIPO SEGURIDAD$ = $tipo_seguridad$, "
			+ "$RESPUESTA REQUERIMIENTO.TRAMITE$ = $tramite$, "
			+ "$RESPUESTA REQUERIMIENTO.PROCESO CLASE$ = $proceso_clase$, "
			+ "$RESPUESTA REQUERIMIENTO.TIPO CUADERNO$ = $tipo_cuaderno$, "
			+ "$RESPUESTA REQUERIMIENTO.TIPO AUTO ACTA$ = $tipo_auto_acta$, "
			+ "$RESPUESTA REQUERIMIENTO.SERIE$ = $serie$, " 
			+ "$RESPUESTA REQUERIMIENTO.SUBSERIE$ = $subserie$ " 
			+ "WHERE ID = $id$";
		
		return DS_SqlUtils.update(sql_update, solicitud);
	}
	
	public Integer obtenerDependencia (Integer id_carga) {
		
		String sql = "SELECT $RESPUESTA REQUERIMIENTO.DEPENDENCIA$ FROM $RESPUESTA REQUERIMIENTO$ WHERE IDCARGA = " + id_carga;
		
		try {
			return DS_SqlUtils.queryForObject(Integer.class, sql);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error al obtener la dependencia de la solicitud " + id_carga, e);
			return null;
		}
		
	}
	
}
