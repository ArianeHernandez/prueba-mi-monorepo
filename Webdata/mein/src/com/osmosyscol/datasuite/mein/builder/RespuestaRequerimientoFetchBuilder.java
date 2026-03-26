package com.osmosyscol.datasuite.mein.builder;

import com.osmosyscol.datasuite.mein.dtos.APVistaCuaderno;
import com.osmosyscol.datasuite.mein.dtos.APVistaDependencias;
import com.osmosyscol.datasuite.mein.dtos.APVistaMedioEnvio;
import com.osmosyscol.datasuite.mein.dtos.APVistaProcesosClases;
import com.osmosyscol.datasuite.mein.dtos.APVistaSeguridadTipo;
import com.osmosyscol.datasuite.mein.dtos.APVistaSerie;
import com.osmosyscol.datasuite.mein.dtos.APVistaSubserie;
import com.osmosyscol.datasuite.mein.dtos.APVistaTipoAutoActa;
import com.osmosyscol.datasuite.mein.dtos.APVistaTramite;
import com.osmosyscol.datasuite.mein.dtos.DepartamentoDane;
import com.osmosyscol.datasuite.mein.dtos.Deudor;
import com.osmosyscol.datasuite.mein.dtos.MunicipioDane;
import com.osmosyscol.datasuite.mein.dtos.ObjGenerico;
import com.osmosyscol.datasuite.mein.dtos.PaisDane;
import com.osmosyscol.datasuite.mein.dtos.RespuestaRequerimiento;
import com.osmosyscol.datasuite.mein.dtos.TipoDeDocumento;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

public class RespuestaRequerimientoFetchBuilder {

	private RespuestaRequerimientoFetchBuilder () {}
	
	public static RespuestaRequerimientoFetchBuilder newInstance () {
		return new RespuestaRequerimientoFetchBuilder();
	}
	
	private RespuestaRequerimiento solicitud = new RespuestaRequerimiento();
	private Integer idCarga;
	
	public RespuestaRequerimientoFetchBuilder withIdCarga ( Integer idCarga ) {
		this.idCarga = idCarga;
		return this;
	}
	
	public RespuestaRequerimientoFetchBuilder fetchRespuestaRequerimiento () {
		this.solicitud = DS_SqlUtils.queryForObject(RespuestaRequerimiento.class, 
			"select respuesta.* "
			+ "from $respuesta requerimiento$ respuesta "
			+ "where respuesta.idcarga = " + idCarga);
		
		return this;
	}
	
	public RespuestaRequerimientoFetchBuilder fetchRespuestaRequerimiento_Deudor () {
		if (this.solicitud != null && this.solicitud.getDeudor() != null) {
			Deudor deudor = DS_SqlUtils.queryForObject(Deudor.class, 
				"select deudor.*, " +
				" deudor.$deudor.numero de identificacion$ numero_identificacion, " +
				" deudor.$deudor.nombre o razon social$ razon_social, " +
				" deudor.$deudor.tipo de identificacion$ tipo_identificacion_id, " +
				" deudor.$deudor.municipio dane$ municipio_id, " +
				" deudor.$deudor.codigo ciiu$ codigo_ciiu, " +
				" deudor.$deudor.macrosector$ macrosector, " +
				" deudor.$deudor.pais$ pais, " +
				" deudor.$deudor.pais dane$ pais_dane_id, " +
				" deudor.$deudor.direccion$ direccion, " +
				" deudor.$deudor.correo electronico$ correo_electronico, " +
				" deudor.$deudor.telefono$ telefono, " +
				" deudor.$deudor.naturaleza$ naturaleza, " +
				" deudor.$deudor.porcentaje participacion$ porcentaje_participacion, " +
				" deudor.$deudor.representante legal$ id_representante_legal, " +
				" deudor.$deudor.departamento dane$ departamento_id " +
				" from $respuesta requerimiento$ respuesta " +
				" left join $deudor$ deudor on respuesta.$respuesta requerimiento.deudor$ = deudor.id " +
				" where respuesta.idCarga = " + idCarga);
			
			this.solicitud.setDeudor_obj(deudor);
		}
		
		return this;
	}
	
	public RespuestaRequerimientoFetchBuilder fetchRespuestaRequerimiento_Deudor_TipoIdentificacion () {
		
		if (this.solicitud != null && this.solicitud.getDeudor_obj() != null
				&& this.solicitud.getDeudor_obj().getTipo_identificacion_id() != null ) {
			String queryTipoDoc = "select tipo_documento.id, " +
					" tipo_documento.$tipo de documento.nombre$," +
					" tipo_documento.$tipo de documento.codigo$," +
					" tipo_documento.$tipo de documento.id postal$," +
					" tipo_documento.$tipo de documento.nombre postal$, " +
					" tipo_documento.$tipo de documento.codigo alfanumerico$, " +
					" tipo_documento.$tipo de documento.codigo numerico postal$ " +
					" from $tipo de documento$ tipo_documento " +
					" where tipo_documento.$tipo de documento.cod_hts$ = " + 
					"	$S(" + this.solicitud.getDeudor_obj().getTipo_identificacion_id() + ")$";
					
			TipoDeDocumento tipo_de_documento = DS_SqlUtils.queryForObject(TipoDeDocumento.class, queryTipoDoc);
			
			this.solicitud.getDeudor_obj().setTipo_identificacion(tipo_de_documento);
		}
		
		return this;
	}
	
	public RespuestaRequerimientoFetchBuilder fetchRespuestaRequerimiento_Deudor_Pais () {
		if (this.solicitud != null && this.solicitud.getDeudor_obj() != null
				&& this.solicitud.getDeudor_obj().getPais_dane_id() != null) {	
			PaisDane pais_dane = DS_SqlUtils.queryForObject(PaisDane.class, 
				"select pais_dane.* " +
				" from $deudor$ deudor " +
				" left join $paises dane$ pais_dane on deudor.$deudor.pais dane$ = pais_dane.id " +
				" where deudor.id = " + this.solicitud.getDeudor_obj().getId());
		
			this.solicitud.getDeudor_obj().setPais_dane(pais_dane);
		}
		
		return this;
	}
	
	public RespuestaRequerimientoFetchBuilder fetchRespuestaRequerimiento_Deudor_Departamento () {
		if (this.solicitud != null && this.solicitud.getDeudor_obj() != null
				&& this.solicitud.getDeudor_obj().getDepartamento_id() != null) {
			DepartamentoDane departamento = DS_SqlUtils.queryForObject(DepartamentoDane.class, 
				"select departamento.* " +
				" from $deudor$ deudor " +
				" left join $departamentos dane$ departamento on deudor.$deudor.departamento dane$ = departamento.id " +
				" where deudor.id = " + this.solicitud.getDeudor_obj().getId());
			
			this.solicitud.getDeudor_obj().setDepartamento(departamento);
		}
		
		return this;
	}
	
	public RespuestaRequerimientoFetchBuilder fetchRespuestaRequerimiento_DeudorMunicipio () {
		if (this.solicitud != null && this.solicitud.getDeudor_obj() != null 
				&& this.solicitud.getDeudor_obj().getMunicipio_id() != null) {
			MunicipioDane municipio = DS_SqlUtils.queryForObject(MunicipioDane.class, 
				"select ciudad.* " +
				" from $deudor$ deudor " +
				" left join $municipios dane$ ciudad on deudor.$deudor.municipio dane$ = ciudad.id " +
				" where deudor.id = " + this.solicitud.getDeudor_obj().getId());
			
			this.solicitud.getDeudor_obj().setMunicipio(municipio);
		}
		
		return this;
	}
	
	public RespuestaRequerimientoFetchBuilder fetchRespuestaRequerimiento_TipoSolicitante () {
		if (this.solicitud != null && this.solicitud.getTipo_solicitante() != null) {
			ObjGenerico tipoSolicitante = DS_SqlUtils.queryForObject(ObjGenerico.class, 
				"select tipo.* "
				+ "from $respuesta requerimiento$ respuesta "
				+ "left join $tipo solicitante$ tipo on respuesta.$respuesta requerimiento.tipo solicitante$ = tipo.id "
				+ "where respuesta.idCarga = " + idCarga);
			
			this.solicitud.setTipo_solicitante_obj(tipoSolicitante);
		}
		
		return this;
	}
	
	public RespuestaRequerimientoFetchBuilder fetchRespuestaRequerimiento_TipoSolicitud () {
		if (this.solicitud != null && this.solicitud.getTipo_solicitud() != null) {
			ObjGenerico tipoSolicitud = DS_SqlUtils.queryForObject(ObjGenerico.class, 
				"select tipo.* "
				+ "from $respuesta requerimiento$ respuesta "
				+ "left join $tipo de solicitud$ tipo on respuesta.$respuesta requerimiento.tipo solicitud$ = tipo.id "
				+ "where respuesta.idCarga = " + idCarga);
			
			this.solicitud.setTipo_solicitud_obj(tipoSolicitud);
		}
		
		return this;
	}
	
	public RespuestaRequerimientoFetchBuilder fetchRespuestaRequerimiento_TramiteOtrosDocumentos () {
		if (this.solicitud != null && this.solicitud.getTramite_otros_documentos() != null) {
			ObjGenerico tramite = DS_SqlUtils.queryForObject(ObjGenerico.class, 
				"select tramite.* "
				+ "from $respuesta requerimiento$ respuesta "
				+ "left join $tramite otros documentos$ tramite on respuesta.$respuesta requerimiento.tramite otros documentos$ = tramite.id "
				+ "where respuesta.idCarga = " + idCarga);
			
			this.solicitud.setTramite_otros_documentos_obj(tramite);
		}
		
		return this;
	}
	
	public RespuestaRequerimientoFetchBuilder fetchRespuestaRequerimiento_Dependencia () {
		if (this.solicitud != null && this.solicitud.getDependencia() != null) {
			APVistaDependencias dependencia = DS_SqlUtils.queryForObject(APVistaDependencias.class, 
				"select dependencia.* "
				+ "from $respuesta requerimiento$ respuesta "
				+ "left join $apvista dependencias$ dependencia on respuesta.$respuesta requerimiento.dependencia$ = dependencia.id "
				+ "where respuesta.idCarga = " + idCarga);
			
			this.solicitud.setDependencia_obj(dependencia);
		}
		
		return this;
	}
	
	public RespuestaRequerimientoFetchBuilder fetchRespuestaRequerimiento_IntendenciaRegional () {
		if (this.solicitud != null && this.solicitud.getIntendencia_regional() != null) {
			ObjGenerico intendencia = DS_SqlUtils.queryForObject(ObjGenerico.class, 
				"select intendencia.* "
				+ "from $respuesta requerimiento$ respuesta "
				+ "left join $intendencias$ intendencia on respuesta.$respuesta requerimiento.intendencia regional$ = intendencia.id "
				+ "where respuesta.idCarga = " + idCarga);
			
			this.solicitud.setIntendencia_regional_obj(intendencia);
		}
		
		return this;
	}
	
	public RespuestaRequerimientoFetchBuilder fetchRespuestaRequerimiento_MedioEnvio () {
		if (this.solicitud != null && this.solicitud.getMedio_envio() != null) {
			APVistaMedioEnvio medioEnvio = DS_SqlUtils.queryForObject(APVistaMedioEnvio.class, 
				"select medio.* "
				+ "from $respuesta requerimiento$ respuesta "
				+ "left join $apvista medio envio$ medio on respuesta.$respuesta requerimiento.medio envio$ = medio.id "
				+ "where respuesta.idCarga = " + idCarga);
			
			this.solicitud.setMedio_envio_obj(medioEnvio);
		}
		
		return this;
	}
	
	public RespuestaRequerimientoFetchBuilder fetchRespuestaRequerimiento_TipoSeguridad () {
		if (this.solicitud != null && this.solicitud.getTipo_seguridad() != null) {
			APVistaSeguridadTipo tipoSeguridad = DS_SqlUtils.queryForObject(APVistaSeguridadTipo.class, 
				"select tipo.* "
				+ "from $respuesta requerimiento$ respuesta "
				+ "left join $apvista seguridad tipo$ tipo on respuesta.$respuesta requerimiento.tipo seguridad$ = tipo.id "
				+ "where respuesta.idCarga = " + idCarga);
			
			this.solicitud.setTipo_seguridad_obj(tipoSeguridad);
		}
		
		return this;
	}
	
	public RespuestaRequerimientoFetchBuilder fetchRespuestaRequerimiento_Tramite () {
		if (this.solicitud != null && this.solicitud.getTramite() != null) {
			APVistaTramite tramite = DS_SqlUtils.queryForObject(APVistaTramite.class, 
				"select tramite.* "
				+ "from $respuesta requerimiento$ respuesta "
				+ "left join $apvista tramite$ tramite on respuesta.$respuesta requerimiento.tramite$ = tramite.id "
				+ "where respuesta.idCarga = " + idCarga);
			
			this.solicitud.setTramite_obj(tramite);
		}
		
		return this;
	}
	
	public RespuestaRequerimientoFetchBuilder fetchRespuestaRequerimiento_TipoAutoActa () {
		if (this.solicitud != null && this.solicitud.getTipo_auto_acta() != null) {
			APVistaTipoAutoActa tipoAutoActa = DS_SqlUtils.queryForObject(APVistaTipoAutoActa.class, 
				"select tipo_auto_acta.* "
				+ "from $respuesta requerimiento$ respuesta "
				+ "left join $apvista tipo auto acta$ tipo_auto_acta on respuesta.$respuesta requerimiento.tipo auto acta$ = tipo_auto_acta.id "
				+ "where respuesta.idCarga = " + idCarga);
			
			this.solicitud.setTipo_auto_acta_obj(tipoAutoActa);
		}
		
		return this;
	}
	
	public RespuestaRequerimientoFetchBuilder fetchRespuestaRequerimiento_ProcesoClase () {
		if (this.solicitud != null && this.solicitud.getProceso_clase() != null) {
			APVistaProcesosClases proceso = DS_SqlUtils.queryForObject(APVistaProcesosClases.class, 
				"select proceso.* "
				+ "from $respuesta requerimiento$ respuesta "
				+ "left join $apvista procesosclases$ proceso on respuesta.$respuesta requerimiento.proceso clase$ = proceso.id "
				+ "where respuesta.idCarga = " + idCarga);
			
			this.solicitud.setProceso_clase_obj(proceso);
		}
		
		return this;
	}
	
	public RespuestaRequerimientoFetchBuilder fetchRespuestaRequerimiento_TipoCuaderno () {
		if (this.solicitud != null && this.solicitud.getTipo_cuaderno() != null) {
			APVistaCuaderno cuaderno = DS_SqlUtils.queryForObject(APVistaCuaderno.class, 
				"select cuaderno.* "
				+ "from $respuesta requerimiento$ respuesta "
				+ "left join $apvista cuaderno$ cuaderno on respuesta.$respuesta requerimiento.tipo cuaderno$ = cuaderno.id "
				+ "where respuesta.idCarga = " + idCarga);
			
			this.solicitud.setTipo_cuaderno_obj(cuaderno);
		}
		
		return this;
	}
	
	public RespuestaRequerimientoFetchBuilder fetchRespuestaRequerimiento_Serie () {
		if (this.solicitud != null && this.solicitud.getSerie() != null) {
			APVistaSerie serie = DS_SqlUtils.queryForObject(APVistaSerie.class, 
				"select serie.* "
				+ "from $respuesta requerimiento$ respuesta "
				+ "left join $apvista serie$ serie on respuesta.$respuesta requerimiento.serie$ = serie.id "
				+ "where respuesta.idCarga = " + idCarga);
			
			this.solicitud.setTipo_serie_obj(serie);
		}
		
		return this;
	}
	
	public RespuestaRequerimientoFetchBuilder fetchRespuestaRequerimiento_SubSerie () {
		if (this.solicitud != null && this.solicitud.getSubserie() != null) {
			APVistaSubserie subserie = DS_SqlUtils.queryForObject(APVistaSubserie.class, 
				"select subserie.* "
				+ "from $respuesta requerimiento$ respuesta "
				+ "left join $apvista subserie$ subserie on respuesta.$respuesta requerimiento.subserie$ = subserie.id "
				+ "where respuesta.idCarga = " + idCarga);
			
			this.solicitud.setTipo_subserie_obj(subserie);
		}
		
		return this;
	}
	
	public RespuestaRequerimiento getSolicitud () {
		return this.solicitud;
	}
}
