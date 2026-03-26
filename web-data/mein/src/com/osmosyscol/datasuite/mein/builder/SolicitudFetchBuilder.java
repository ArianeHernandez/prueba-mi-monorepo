package com.osmosyscol.datasuite.mein.builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.bpm.dto.ApVistaMacrosectorDto;
import com.osmosyscol.datasuite.mein.dtos.APVistaCuaderno;
import com.osmosyscol.datasuite.mein.dtos.APVistaDependencias;
import com.osmosyscol.datasuite.mein.dtos.APVistaMedioEnvio;
import com.osmosyscol.datasuite.mein.dtos.APVistaProcesosClases;
import com.osmosyscol.datasuite.mein.dtos.APVistaSeguridadTipo;
import com.osmosyscol.datasuite.mein.dtos.APVistaSerie;
import com.osmosyscol.datasuite.mein.dtos.APVistaSubserie;
import com.osmosyscol.datasuite.mein.dtos.APVistaTipoAutoActa;
import com.osmosyscol.datasuite.mein.dtos.APVistaTramite;
import com.osmosyscol.datasuite.mein.dtos.Checklist;
import com.osmosyscol.datasuite.mein.dtos.DepartamentoCiudad;
import com.osmosyscol.datasuite.mein.dtos.DepartamentoDane;
import com.osmosyscol.datasuite.mein.dtos.InformacionFinanciera;
import com.osmosyscol.datasuite.mein.dtos.MunicipioDane;
import com.osmosyscol.datasuite.mein.dtos.ObjGenerico;
import com.osmosyscol.datasuite.mein.dtos.PaisDane;
import com.osmosyscol.datasuite.mein.dtos.PersonaMein;
import com.osmosyscol.datasuite.mein.dtos.RelacionPasivos;
import com.osmosyscol.datasuite.mein.dtos.Sociedad;
import com.osmosyscol.datasuite.mein.dtos.SolicitudNearSociedad;
import com.osmosyscol.datasuite.mein.dtos.TipoSolicitud;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

public class SolicitudFetchBuilder {
	private SolicitudFetchBuilder(){}
	public static SolicitudFetchBuilder newInstance() {
		return new SolicitudFetchBuilder();
	}
	
	static final Logger logger = LoggerFactory.getLogger(SolicitudFetchBuilder.class);
	
	private SolicitudNearSociedad solicitud = new SolicitudNearSociedad();
	
	private Integer idCarga;
	
	public SolicitudFetchBuilder withIdCarga(Integer idCarga) {
		this.idCarga = idCarga;
		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud() {
		this.solicitud = DS_SqlUtils.queryForObject(SolicitudNearSociedad.class, 
				"select solicitud.* " + 
				"  from $solicitud near sociedad$ solicitud " +
				" left join $SOCIEDAD$ sociedad on solicitud.$solicitud near sociedad.deudor$ = sociedad.id " +
				" where solicitud.idcarga = " + idCarga);

		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud_TipoSolicitante() {
		if (this.solicitud != null && this.solicitud.getTipo_solicitante() != null) {
			ObjGenerico tipoSolicitante = DS_SqlUtils.queryForObject(ObjGenerico.class, 
					"select tipoSolicitante.* from $solicitud near sociedad$ solicitud "
					+ "left join $tipo solicitante$ tipoSolicitante on solicitud.$solicitud near sociedad.tipo solicitante$ = tipoSolicitante.id "
					+ "where solicitud.idcarga =" + idCarga);
				
				this.solicitud.setTipo_solicitante_obj(tipoSolicitante);
		}
		
		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud_TipoSolicitud() {
		
		if( null != this.solicitud ) {
			
			TipoSolicitud tipoSolicitud = DS_SqlUtils.queryForObject(TipoSolicitud.class, 
				"select tipoSolicitud.* " + 
				"  from $solicitud near sociedad$ solicitud " +
				" left join $TIPO SOLICITUD$ tipoSolicitud on solicitud.$solicitud near sociedad.tipo solicitud$ = tipoSolicitud.id " +
				" where solicitud.idcarga = " + idCarga);
			
			this.solicitud.setTipo_solicitud(tipoSolicitud);
			
		}
		
		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud_TipoSolicitud_GrupoNIIF() {
		
		if( null != this.solicitud && null != this.solicitud.getTipo_solicitud()) {
			ObjGenerico gruponiifObj = DS_SqlUtils.queryForObject(ObjGenerico.class, 
				"select grupoNIIF.* from $solicitud near sociedad$ solicitud "
				+ "left join $tipo solicitud$ tipoSolicitud on solicitud.$solicitud near sociedad.tipo solicitud$ = tipoSolicitud.id "
				+ "left join $grupo niif$ grupoNIIF on tipoSolicitud.$tipo solicitud.grupo niif$ = grupoNIIF.id "
				+ "where solicitud.idcarga =" + idCarga);
			
			this.solicitud.getTipo_solicitud().setGrupo_niif_obj(gruponiifObj);
		}
		
		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud_TipoSolicitud_TipoSolicitud() {
		
		if( null != this.solicitud && null != this.solicitud.getTipo_solicitud()) {
			ObjGenerico tipoSolicitudObj = DS_SqlUtils.queryForObject(ObjGenerico.class, 
				"select tipoSolicitudObj.* from $solicitud near sociedad$ solicitud "
				+ "left join $tipo solicitud$ tipoSolicitud on solicitud.$solicitud near sociedad.tipo solicitud$ = tipoSolicitud.id "
				+ "left join $tipo de solicitud$ tipoSolicitudObj on tipoSolicitud.$tipo solicitud.tipo solicitud$ = tipoSolicitudObj.id "
				+ "where solicitud.idcarga =" + idCarga);
			
			this.solicitud.getTipo_solicitud().setTipo_solicitud_obj(tipoSolicitudObj);
		}
		
		return this;
	}


	
	public SolicitudFetchBuilder fetchSolicitud_Deudor() {
		
		Sociedad deudor = DS_SqlUtils.queryForObject(Sociedad.class, 
				"select deudor.*, "
				+ "deudor.$sociedad.representante legal$ replegal_id, "
				+ "deudor.$sociedad.contador$ contador_id, "
				+ "deudor.$sociedad.revisor fiscal$ revfiscal_id, "
				+ "deudor.$sociedad.apoderado$ apoderado_id, "
				+ "ciiu.nombre actividad_economica_nombre "
				+ "from $solicitud near sociedad$ solicitud left join $sociedad$ deudor "
				+ "on solicitud.$solicitud near sociedad.deudor$ = deudor.id "
				+ " left join $codigo ciiu concatenado$ ciiu on deudor.$sociedad.actividad economica$ = ciiu.id "
				+ " where solicitud.idcarga = " + idCarga);
		if(null != deudor) {
			this.solicitud.setDeudor(deudor);
		}
		Gson gson = new Gson();
		SimpleLogger.setDebug("DEUDOR: "+gson.toJson(deudor));
		
		return this;
	}
	
	
	public SolicitudFetchBuilder fetchSolicitud_Deudor_DatosBasicos() {
		
		if( (null != this.solicitud) 
			&& null != this.solicitud.getDeudor() ) {
			
			PersonaMein datosBasicos = DS_SqlUtils.queryForObject(PersonaMein.class, 
				"select datos_basicos.*, tipo_documento.$tipo de documento.nombre$ tipo_identificacion_nombre  " + 
				"  from $solicitud near sociedad$ solicitud " +
				" left join $SOCIEDAD$ deudor on solicitud.$solicitud near sociedad.deudor$ = deudor.id " +
				" left join $PERSONA$ datos_basicos on deudor.$sociedad.datos basicos$ = datos_basicos.id " +
				" left join $tipo de documento$ tipo_documento on datos_basicos.$persona.tipo identificacion$ = tipo_documento.$tipo de documento.cod_hts$  " +
				" where solicitud.idcarga = " + idCarga);
			
			this.solicitud.getDeudor().setDatos_basicos(datosBasicos);
			
		}
		
		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud_Deudor_Contador() {
		
		if( (null != this.solicitud) 
			&& null != this.solicitud.getDeudor() && this.solicitud.getDeudor().getContador_id() != null) {
			
			PersonaMein contador = DS_SqlUtils.queryForObject(PersonaMein.class, 
					"select contador.*, " + 
				            " contador.$PERSONA.PAIS$ as pais_id, " + 
				            " contador.$PERSONA.DEPARTAMENTO$ as departamento_id, " + 
				            " contador.$PERSONA.MUNICIPIO$ as municipio_id " + 
				            " from $solicitud near sociedad$ solicitud " +
				            " left join $SOCIEDAD$ deudor on solicitud.$solicitud near sociedad.deudor$ = deudor.id " +
				            " left join $PERSONA$ contador on deudor.$sociedad.contador$ = contador.id " +
				            " where solicitud.idcarga = " + idCarga);
			
			this.solicitud.getDeudor().setContador(contador);
		}
		
		return this;
	}
	
	
	public SolicitudFetchBuilder fetchSolicitud_Deudor_RepresentanteLegal() {
		
		if( (null != this.solicitud) 
			 && null != this.solicitud.getDeudor() && this.solicitud.getDeudor().getRepLegal_id() != null) {

			PersonaMein representante_legal = DS_SqlUtils.queryForObject(PersonaMein.class, 
				"select representante_legal.* " + 
				"  from $solicitud near sociedad$ solicitud " +
				" left join $SOCIEDAD$ deudor on solicitud.$solicitud near sociedad.deudor$ = deudor.id " +
				" left join $PERSONA$ representante_legal on deudor.$sociedad.representante legal$ = representante_legal.id " +
				" where solicitud.idcarga = " + idCarga);
			
			this.solicitud.getDeudor().setRepresentante_legal(representante_legal);
			
		}
		
		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud_Deudor_RevisorFiscal() {
		
		if( (null != this.solicitud) 
			 && null != this.solicitud.getDeudor() && this.solicitud.getDeudor().getRevFiscal_id() != null) {
	
			PersonaMein revisor_fiscal = DS_SqlUtils.queryForObject(PersonaMein.class,
		            "select revisor_fiscal.*, " + 
		            " revisor_fiscal.$PERSONA.PAIS$ as pais_id, " +        
		            " revisor_fiscal.$PERSONA.DEPARTAMENTO$ as departamento_id, " + 
		            " revisor_fiscal.$PERSONA.MUNICIPIO$ as municipio_id " +   
		            "  from $solicitud near sociedad$ solicitud " +
		            " left join $SOCIEDAD$ deudor on solicitud.$solicitud near sociedad.deudor$ = deudor.id " +
		            " left join $PERSONA$ revisor_fiscal on deudor.$sociedad.revisor fiscal$ = revisor_fiscal.id " +
		            " where solicitud.idcarga = " + idCarga);
			
			this.solicitud.getDeudor().setRevisor_fiscal(revisor_fiscal);
			
		}
		
		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud_Deudor_Apoderado() {
		
		if( (null != this.solicitud) 
			 && null != this.solicitud.getDeudor() && this.solicitud.getDeudor().getApoderado_id() != null) {
	
			PersonaMein apoderado = DS_SqlUtils.queryForObject(PersonaMein.class, 
				"select apoderado.* " + 
				"  from $solicitud near sociedad$ solicitud " +
				" left join $SOCIEDAD$ deudor on solicitud.$solicitud near sociedad.deudor$ = deudor.id " +
				" left join $PERSONA$ apoderado on deudor.$sociedad.apoderado$ = apoderado.id " +
				" where solicitud.idcarga = " + idCarga);
			
			this.solicitud.getDeudor().setApoderado(apoderado);
			
		}
		
		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud_Deudor_DeptoCiudad(){
		if( (null != this.solicitud) && null != this.solicitud.getDeudor()) {
	
			DepartamentoCiudad depto_ciudad = DS_SqlUtils.queryForObject(DepartamentoCiudad.class, 
				"select depto_ciudad.* " + 
				"  from $solicitud near sociedad$ solicitud " +
				" left join $sociedad$ deudor on solicitud.$solicitud near sociedad.deudor$ = deudor.id " +
				" left join $departamento ciudad$ depto_ciudad on deudor.$sociedad.ciudad$ = depto_ciudad.id " +
				" where solicitud.idcarga = " + idCarga);
			
			this.solicitud.getDeudor().setCiudad(depto_ciudad);
			
		}
		
		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud_Deudor_DeptoDane () {
		if ( this.solicitud != null && this.solicitud.getDeudor() != null
				&& this.solicitud.getDeudor().getDepartamento_dane() != null) {
			DepartamentoDane departamento = DS_SqlUtils.queryForObject(DepartamentoDane.class, 
					"select departamento.* "
					+ "from $sociedad$ deudor "
					+ "left join $departamentos dane$ departamento on deudor.$sociedad.departamento dane$ = departamento.id "
					+ "where deudor.id = " + this.solicitud.getDeudor().getId());
			
			this.solicitud.getDeudor().setDepartamentoObj(departamento);
		}
		
		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud_Deudor_MunicipioDane () {
		if (this.solicitud != null && this.solicitud.getDeudor() != null
				&& this.solicitud.getDeudor().getMunicipio_dane() != null) {
			MunicipioDane municipio = DS_SqlUtils.queryForObject(MunicipioDane.class, 
					"select municipio.* "
					+ "from $sociedad$ deudor "
					+ "left join $municipios dane$ municipio on deudor.$sociedad.municipio dane$ = municipio.id "
					+ "where deudor.id = " + this.solicitud.getDeudor().getId());
			
			this.solicitud.getDeudor().setMunicipioObj(municipio);
		}
		
		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud_Deudor_PaisDane () {
		if (this.solicitud != null && this.solicitud.getDeudor() != null
				&& this.solicitud.getDeudor().getPais_dane() != null) {
			PaisDane pais = DS_SqlUtils.queryForObject(PaisDane.class, 
					"select pais.* "
					+ "from $sociedad$ deudor "
					+ "left join $paises dane$ pais on deudor.$sociedad.pais dane$ = pais.id "
					+ "where deudor.id = " + this.solicitud.getDeudor().getId());
			
			this.solicitud.getDeudor().setPais_dane_obj(pais);
		}
		
		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud_Deudor_ReplegalLimitacion () {
		if (this.solicitud != null && this.solicitud.getDeudor() != null
				&& this.solicitud.getDeudor().getReplegal_tiene_limitacion() != null) {
			ObjGenerico tieneLimitacion = DS_SqlUtils.queryForObject(ObjGenerico.class, 
					"select lista.* "
					+ "from $sociedad$ deudor "
					+ "left join $seleccion si/no$ lista on deudor.$sociedad.replegal tiene limitacion$ = lista.id "
					+ "where deudor.idcarga = " + this.idCarga);
			
			this.solicitud.getDeudor().setReplegal_tiene_limitacionObj(tieneLimitacion);
		}
		
		return this;
	}
	
	
	
	public SolicitudFetchBuilder fetchSolicitud_Checklist(){
		if( (null != this.solicitud && null != this.solicitud.getChecklist_solicitud()) ) {
	
			Checklist checklist = DS_SqlUtils.queryForObject(Checklist.class, 
				"select checklist.* " + 
				"  from $solicitud near sociedad$ solicitud " +
				" left join $checklist$ checklist on solicitud.$solicitud near sociedad.checklist solicitud$ = checklist.id " +
				" where solicitud.idcarga = " + idCarga);
			
			this.solicitud.setChecklist_solicitud_obj(checklist);
			
		}
		
		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud_Checklist_RemisionPrevia () {
		if( null != this.solicitud && null != this.solicitud.getChecklist_solicitud_obj()
				&& this.solicitud.getChecklist_solicitud_obj().getRemision_previa_info() != null) {
			
			ObjGenerico remision = DS_SqlUtils.queryForObject(ObjGenerico.class, 
				"select remision.* " + 
				"  from $checklist$ checklist " +
				" left join $seleccion si/no$ remision on checklist.$checklist.remision previa info$ = remision.id " +
				" where checklist.idcarga = " + idCarga);
			
			this.solicitud.getChecklist_solicitud_obj().setRemision_previa_infoObj(remision);
			
		}
		
		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud_Checklist_SociedadDisolucion () {
		if( null != this.solicitud && null != this.solicitud.getChecklist_solicitud_obj()
				&& this.solicitud.getChecklist_solicitud_obj().getSociedad_en_disolucion() != null) {
			
			ObjGenerico disolucion = DS_SqlUtils.queryForObject(ObjGenerico.class, 
				"select disolucion.* " + 
				"  from $checklist$ checklist " +
				" left join $seleccion si/no$ disolucion on checklist.$checklist.sociedad en disolucion$ = disolucion.id " +
				" where checklist.idcarga = " + idCarga);
			
			this.solicitud.getChecklist_solicitud_obj().setSociedad_en_disolucionObj(disolucion);
			
		}
		
		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud_Checklist_SociedadPasivosPensionales () {
		if( null != this.solicitud && null != this.solicitud.getChecklist_solicitud_obj()
				&& this.solicitud.getChecklist_solicitud_obj().getSociedad_pasivos_pensiona() != null) {
			
			ObjGenerico pasivos = DS_SqlUtils.queryForObject(ObjGenerico.class, 
				"select pasivos.* " + 
				"  from $checklist$ checklist " +
				" left join $seleccion si/no$ pasivos on checklist.$checklist.sociedad pasivos pensiona$ = pasivos.id " +
				" where checklist.idcarga = " + idCarga);
			
			this.solicitud.getChecklist_solicitud_obj().setSociedad_pasivos_pensionaObj(pasivos);
			
		}
		
		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud_Checklist_PasivosPensionales () {
		if( null != this.solicitud && null != this.solicitud.getChecklist_solicitud_obj()
				&& this.solicitud.getChecklist_solicitud_obj().getPasivos_pensionales() != null) {
			
			ObjGenerico pasivos = DS_SqlUtils.queryForObject(ObjGenerico.class, 
				"select pasivos.* " + 
				"  from $checklist$ checklist " +
				" left join $seleccion si/no$ pasivos on checklist.$checklist.pasivos pensionales$ = pasivos.id " +
				" where checklist.idcarga = " + idCarga);
			
			this.solicitud.getChecklist_solicitud_obj().setPasivos_pensionalesObj(pasivos);
			
		}
		
		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud_Checklist_SociedadGaranteCodeudor () {
		if( null != this.solicitud && null != this.solicitud.getChecklist_solicitud_obj()
				&& this.solicitud.getChecklist_solicitud_obj().getSociedad_garante_codeudor() != null) {
			
			ObjGenerico sociedad = DS_SqlUtils.queryForObject(ObjGenerico.class, 
				"select sociedad.* " + 
				"  from $checklist$ checklist " +
				" left join $seleccion si/no$ sociedad on checklist.$checklist.sociedad garante codeudor$ = sociedad.id " +
				" where checklist.idcarga = " + idCarga);
			
			this.solicitud.getChecklist_solicitud_obj().setSociedad_garante_codeudorObj(sociedad);
			
		}
		
		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud_Checklist_Garantias () {
		if( null != this.solicitud && null != this.solicitud.getChecklist_solicitud_obj()
				&& this.solicitud.getChecklist_solicitud_obj().getGarantias_mobiliarias() != null) {
			
			ObjGenerico garantias = DS_SqlUtils.queryForObject(ObjGenerico.class, 
				"select garantias.* " + 
				"  from $checklist$ checklist " +
				" left join $seleccion si/no$ garantias on checklist.$checklist.garantias mobiliarias$ = garantias.id " +
				" where checklist.idcarga = " + idCarga);
			
			this.solicitud.getChecklist_solicitud_obj().setGarantias_mobiliariasObj(garantias);
			
		}
		
		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud_Checklist_Controlante () {
		if( null != this.solicitud && null != this.solicitud.getChecklist_solicitud_obj()
				&& this.solicitud.getChecklist_solicitud_obj().getSolicitante_controlante() != null) {
			
			ObjGenerico controlante = DS_SqlUtils.queryForObject(ObjGenerico.class, 
				"select controlante.* " + 
				"  from $checklist$ checklist " +
				" left join $seleccion si/no$ controlante on checklist.$checklist.solicitante controlante$ = controlante.id " +
				" where checklist.idcarga = " + idCarga);
			
			this.solicitud.getChecklist_solicitud_obj().setSolicitante_controlanteObj(controlante);
			
		}
		
		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud_PasivosRetenciones() {
			
		if( null != this.solicitud) {
			RelacionPasivos rPasivos = DS_SqlUtils.queryForObject(RelacionPasivos.class, 
				"select pasivos.* from $solicitud near sociedad$ solicitud "
				+ "left join $RELACION PASIVOS$ pasivos on "
				+ "solicitud.$solicitud near sociedad.relacion de pasivos$ = "
				+ "pasivos.id where solicitud.idcarga =" + idCarga);
			
			this.solicitud.setRelacion_de_pasivos(rPasivos);
		}
		
		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud_RelacionPasivos_Retenciones() {
		if (this.solicitud != null & this.solicitud.getRelacion_de_pasivos() != null
				&& this.solicitud.getRelacion_de_pasivos().getPasivos_por_retenciones() != null) {
			ObjGenerico retenciones = DS_SqlUtils.queryForObject(ObjGenerico.class, 
					"select retenciones.* from $relacion pasivos$ relacion "
					+ " left join $seleccion si/no$ retenciones on relacion.$relacion pasivos.pasivos por retenciones$ = retenciones.id "
					+ " where relacion.id = " + this.solicitud.getRelacion_de_pasivos().getId());
			
			this.solicitud.getRelacion_de_pasivos().setPasivos_por_retencionesObj(retenciones);
		}
		
		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud_RelacionPasivos_Descuentos() {
		if (this.solicitud != null & this.solicitud.getRelacion_de_pasivos() != null
				&& this.solicitud.getRelacion_de_pasivos().getPasivos_por_descuentos() != null) {
			ObjGenerico descuentos = DS_SqlUtils.queryForObject(ObjGenerico.class, 
					"select descuentos.* from $relacion pasivos$ relacion "
					+ " left join $seleccion si/no$ descuentos on relacion.$relacion pasivos.pasivos por descuentos$ = descuentos.id "
					+ " where relacion.id = " + this.solicitud.getRelacion_de_pasivos().getId());
			
			this.solicitud.getRelacion_de_pasivos().setPasivos_por_descuentosObj(descuentos);
		}
		
		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud_RelacionPasivos_Aportes() {
		if (this.solicitud != null & this.solicitud.getRelacion_de_pasivos() != null
				&& this.solicitud.getRelacion_de_pasivos().getPasivos_por_aportes() != null) {
			ObjGenerico aportes = DS_SqlUtils.queryForObject(ObjGenerico.class, 
					"select aportes.* from $relacion pasivos$ relacion "
					+ " left join $seleccion si/no$ aportes on relacion.$relacion pasivos.pasivos por aportes$ = aportes.id "
					+ " where relacion.id = " + this.solicitud.getRelacion_de_pasivos().getId());
			
			this.solicitud.getRelacion_de_pasivos().setPasivos_por_aportesObj(aportes);
		}
		
		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud_Deudor_Macrosector() {
			
		if( null != this.solicitud) {
			ObjGenerico macrosectorObj = DS_SqlUtils.queryForObject(ObjGenerico.class, 
				"select macrosector.* from $solicitud near sociedad$ solicitud "
				+ "left join $SOCIEDAD$ deudor on solicitud.$solicitud near sociedad.deudor$ = deudor.id "
				+ "left join $MACROSECTOR$ macrosector on deudor.$sociedad.macrosector$ = macrosector.id "
				+ "where solicitud.idcarga =" + idCarga);
			
			this.solicitud.getDeudor().setMacrosectorObj(macrosectorObj);
		}
		
		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud_Deudor_MacrosectorAPVista() {
		
		if( null != this.solicitud) {
			ApVistaMacrosectorDto macrosectorObj = DS_SqlUtils.queryForObject(ApVistaMacrosectorDto.class, 
				"select macrosector.* from $solicitud near sociedad$ solicitud "
				+ "left join $SOCIEDAD$ deudor on solicitud.$solicitud near sociedad.deudor$ = deudor.id "
				+ "left join $APVISTA MACROSECTOR$ macrosector on deudor.$sociedad.macrosector$ = macrosector.$apvista macrosector.lista$ "
				+ "where solicitud.idcarga =" + idCarga);
			
			this.solicitud.getDeudor().setMacrosectorAPVista(macrosectorObj);
		}
		
		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud_Deudor_ActividadEconomica() {
		
		if( null != this.solicitud) {
			ObjGenerico macrosectorObj = DS_SqlUtils.queryForObject(ObjGenerico.class, 
				"select macrosector.* from $solicitud near sociedad$ solicitud "
				+ "left join $SOCIEDAD$ deudor on solicitud.$solicitud near sociedad.deudor$ = deudor.id "
				+ "left join $MACROSECTOR$ macrosector on deudor.$sociedad.macrosector$ = macrosector.id "
				+ "where solicitud.idcarga =" + idCarga);
			
			this.solicitud.getDeudor().setMacrosectorObj(macrosectorObj);
		}
		
		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud_Deudor_Naturaleza() {
		
		if( null != this.solicitud) {
			ObjGenerico naturalezaObj = DS_SqlUtils.queryForObject(ObjGenerico.class, 
				"select naturaleza.* from $solicitud near sociedad$ solicitud "
				+ "left join $SOCIEDAD$ deudor on solicitud.$solicitud near sociedad.deudor$ = deudor.id "
				+ "left join $NATURALEZA SOCIEDAD$ naturaleza on deudor.$sociedad.naturaleza$ = naturaleza.id "
				+ "where solicitud.idcarga =" + idCarga);
			
			this.solicitud.getDeudor().setNaturalezaObj(naturalezaObj);
		}
		
		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud_PorcentajeParticipacion() {
		
		if( null != this.solicitud) {
			String porcentajeParticipacion = DS_SqlUtils.queryForObject(String.class, 
				"select $sociedad.porcentaje participacion$ "
				+ "from $solicitud near sociedad$ solicitud  left join $SOCIEDAD$ deudor "
				+ "on solicitud.$solicitud near sociedad.deudor$ = deudor.id  "
				+ "left join $PERSONA$ datos_basicos on deudor.$sociedad.datos basicos$ = datos_basicos.id "
				+ "where solicitud.idcarga=" + idCarga);
			
			this.solicitud.getDeudor().setPorcentaje_participacion(porcentajeParticipacion);
		}
		
		return this;
	}
	
	
	public SolicitudFetchBuilder fetchSolicitud_SituacionPresentada() {
		
		if( null != this.solicitud) {
			ObjGenerico situacion_presentada_obj = DS_SqlUtils.queryForObject(ObjGenerico.class, 
				"select situacion_presentada.* from $solicitud near sociedad$ solicitud left join $SITUACION PRESENTADA$ situacion_presentada on solicitud.$solicitud near sociedad.situacion presentada$ = situacion_presentada.id where solicitud.idcarga =" + idCarga);
			
			this.solicitud.setSituacion_presentada_obj(situacion_presentada_obj);
		}
		
		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud_AdelAcreedores() {
		
		if( null != this.solicitud) {
			ObjGenerico adel_acreedores_obj = DS_SqlUtils.queryForObject(ObjGenerico.class, 
				"select adel_acreedores.* from $solicitud near sociedad$ solicitud left join $adel acreedores$ adel_acreedores"
				+ " on solicitud.$solicitud near sociedad.adel acreedores$ = adel_acreedores.id where solicitud.idcarga =" + idCarga);
			
			this.solicitud.setAdel_acreedores_obj(adel_acreedores_obj);
		}
		
		return this;
	}
	
	//Adicion post-produccion
	public SolicitudFetchBuilder fetchSolicitud_Dependencia() {
		
		if( null != this.solicitud && null != this.solicitud.getDependencia()) {
			APVistaDependencias dependencia = DS_SqlUtils.queryForObject(APVistaDependencias.class, 
				"select dependencia.* from $solicitud near sociedad$ solicitud left join $apvista dependencias$ dependencia"
				+ " on solicitud.$solicitud near sociedad.dependencia$ = dependencia.id where solicitud.idcarga =" + idCarga);
			
			this.solicitud.setDependencia_obj(dependencia);
		}
		
		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud_MedioEnvio() {
		
		if( null != this.solicitud && null != this.solicitud.getMedio_envio()) {
			APVistaMedioEnvio medio_envio = DS_SqlUtils.queryForObject(APVistaMedioEnvio.class, 
				"select medio_envio.* from $solicitud near sociedad$ solicitud left join $apvista medio envio$ medio_envio"
				+ " on solicitud.$solicitud near sociedad.medio envio$ = medio_envio.id where solicitud.idcarga =" + idCarga);
			
			this.solicitud.setMedio_envio_obj(medio_envio);
		}
		
		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud_Cuaderno() {
	
		if( null != this.solicitud && null != this.solicitud.getCuaderno()) {
			APVistaCuaderno cuaderno = DS_SqlUtils.queryForObject(APVistaCuaderno.class, 
				"select cuaderno.* from $solicitud near sociedad$ solicitud left join $apvista cuaderno$ cuaderno"
				+ " on solicitud.$solicitud near sociedad.cuaderno$ = cuaderno.id where solicitud.idcarga =" + idCarga);
			
			this.solicitud.setCuaderno_obj(cuaderno);
		}
		
		return this;
	}

	public SolicitudFetchBuilder fetchSolicitud_TipoSeguridad() {
		
		if( null != this.solicitud && null != this.solicitud.getTipo_seguridad()) {
			APVistaSeguridadTipo tipoSeguridad = DS_SqlUtils.queryForObject(APVistaSeguridadTipo.class, 
				"select tipo_seguridad.* from $solicitud near sociedad$ solicitud left join $apvista seguridad tipo$ tipo_seguridad"
				+ " on solicitud.$solicitud near sociedad.tipo seguridad$ = tipo_seguridad.id where solicitud.idcarga =" + idCarga);
			
			this.solicitud.setTipo_seguridad_obj(tipoSeguridad);
		}
		
		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud_Tramite() {
		
		if( null != this.solicitud && null != this.solicitud.getTramite()) {
			APVistaTramite tramite = DS_SqlUtils.queryForObject(APVistaTramite.class, 
				"select tramite.* from $solicitud near sociedad$ solicitud left join $apvista tramite$ tramite"
				+ " on solicitud.$solicitud near sociedad.tramite$ = tramite.id where solicitud.idcarga =" + idCarga);
			
			this.solicitud.setTramite_obj(tramite);
		}
		
		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud_ProcesosClases() {
		
		if( null != this.solicitud && null != this.solicitud.getProcesos_clases()) {
			APVistaProcesosClases procesosClases = DS_SqlUtils.queryForObject(APVistaProcesosClases.class, 
				"select procesos_clases.* from $solicitud near sociedad$ solicitud left join $apvista procesosclases$ procesos_clases"
				+ " on solicitud.$solicitud near sociedad.procesos clases$ = procesos_clases.id where solicitud.idcarga =" + idCarga);
			
			this.solicitud.setProcesos_clases_obj(procesosClases);
		}
		
		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud_TipoAutoActa() {
		
		if( null != this.solicitud && null != this.solicitud.getTipo_auto_acta()) {
			APVistaTipoAutoActa tipoAutoActa = DS_SqlUtils.queryForObject(APVistaTipoAutoActa.class, 
				"select tipo_auto_acta.* from $solicitud near sociedad$ solicitud left join $apvista tipo auto acta$ tipo_auto_acta"
				+ " on solicitud.$solicitud near sociedad.tipo auto acta$ = tipo_auto_acta.id where solicitud.idcarga =" + idCarga);
			
			this.solicitud.setTipo_auto_acta_obj(tipoAutoActa);
		}
		
		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud_Tamanio() {
		
		if( null != this.solicitud) {
			String tamanio_obj = DS_SqlUtils.queryForObject(String.class, 
				"select tamano.nombre from $solicitud near sociedad$ solicitud left join $SOCIEDAD$ deudor on solicitud.$solicitud near sociedad.deudor$ = deudor.id left join $TAMANO EMPRESA$ tamano on deudor.$sociedad.tamano empresa$ = tamano.id where solicitud.idcarga =" + idCarga);
			
			this.solicitud.getDeudor().setTamano_empresa_nombre(tamanio_obj);
		}
		
		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud_Serie() {
		
		if( null != this.solicitud && null != this.solicitud.getSerieId()) {
			APVistaSerie serie = DS_SqlUtils.queryForObject(APVistaSerie.class, 
				"select serie.* from $solicitud near sociedad$ solicitud left join $apvista serie$ serie"
				+ " on solicitud.$solicitud near sociedad.serie$ = serie.id where solicitud.idcarga =" + idCarga);
			
			this.solicitud.setSerie(serie);
		}
		
		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud_Subserie() {
		
		if( null != this.solicitud && null != this.solicitud.getSubserieId()) {
			APVistaSubserie subserie = DS_SqlUtils.queryForObject(APVistaSubserie.class, 
				"select subserie.* from $solicitud near sociedad$ solicitud left join $apvista subserie$ subserie"
				+ " on solicitud.$solicitud near sociedad.subserie$ = subserie.id where solicitud.idcarga =" + idCarga);
			
			this.solicitud.setSubserie(subserie);
		}
		
		return this;
	}
		
	public SolicitudFetchBuilder fetchSolicitud_InformacionFinanciera() {
		
		if( null != this.solicitud) {
			String query = "select $informacion financiera.ultimo radicado dictamen$ ultimo_radicado_dictamen, " 
					+ "$informacion financiera.penultimo radicado dictam$ penultimo_radicado_dictamen, "
					+ "$informacion financiera.antepenultimo radica dict$ antepenultimo_radicado_dictamen, "
					+ "$informacion financiera.fecha estados financieros$ fecha_estados_financieros, "
					+ "$informacion financiera.valor activos$ valor_activos, "
					+ "$informacion financiera.valor pasivos$ valor_pasivos, "
					+ "$informacion financiera.valor patrimonio$ valor_patrimonio, "
					+ "$informacion financiera.total ingresos ordinarios$ total_ingresos_ordinarios, "
					+ "$informacion financiera.fecha eeff anio anterior$ fecha_eeff_anio_anterior, "
					+ "$informacion financiera.valor pasivos ultimo anio$ valor_pasivos_ultimoanio, "
					+ "$informacion financiera.valor activos ultimo anio$ valor_activos_ultimoanio, "
					+ "$informacion financiera.total otros ingresos$ 	total_otros_ingresos, "
					+ "$informacion financiera.tiene inversiones$ tiene_inversiones, "
					+ "$informacion financiera.valor p participacion$ valor_p_participacion, "
					+ "$informacion financiera.valor p costo$ valor_p_costo, "
					+ "$informacion financiera.valor p razonable$ valor_p_razonable, "
					+ "$informacion financiera.fecha r bienes acreedores$ fecha_r_bienes_acreedores "
					+ "from $solicitud near sociedad$ solicitud left join $informacion financiera$ financiera "
					+ "on solicitud.$solicitud near sociedad.informacion financiera$ = financiera.id "
					+ "where solicitud.idcarga =" + idCarga;
			
			System.out.println("CONSULTA FINANCIERA: " + query);
			
			InformacionFinanciera informacion_financiera = DS_SqlUtils.queryForObject(InformacionFinanciera.class, query);
			
			this.solicitud.setInformacion_financiera(informacion_financiera);
		}
		
		return this;
	}
	
	public SolicitudFetchBuilder fetchSolicitud_InfoFinanciera_TieneInversiones () {
		if (this.solicitud != null && this.solicitud.getInformacion_financiera() != null 
				&& this.solicitud.getInformacion_financiera().getTiene_inversiones() != null) {
			ObjGenerico tieneInversiones = DS_SqlUtils.queryForObject(ObjGenerico.class, 
					"select inversiones.* from $informacion financiera$ financiera "
					+ " left join $seleccion si/no$ inversiones on financiera.$informacion financiera.tiene inversiones$ = inversiones.id "
					+ " where financiera.idcarga = " + this.solicitud.getIdcarga());
			
			this.solicitud.getInformacion_financiera().setTiene_inversionesObj(tieneInversiones);
		}
		
		return this;
	}

	public SolicitudFetchBuilder fetchSolicitud_intendencia_regional() {
		
		if( (null != this.solicitud) ) {
			
			ObjGenerico intendencia_regional = DS_SqlUtils.queryForObject(ObjGenerico.class, 
				"SELECT INTENDENCIA_REGIONAL.* FROM $SOLICITUD NEAR SOCIEDAD$ SOLICITUD "
				+ "LEFT JOIN $INTENDENCIAS$ INTENDENCIA_REGIONAL "
				+ "ON SOLICITUD.$SOLICITUD NEAR SOCIEDAD.INTENDENCIA REGIONAL$ = INTENDENCIA_REGIONAL.ID "
				+ "WHERE SOLICITUD.IDCARGA=" + idCarga);
			
			if (intendencia_regional != null){
				this.solicitud.setIntendencia_regional_obj(intendencia_regional);
			}
			
		}
		
		return this;
	}

	
	public SolicitudNearSociedad getSolicitud() {
		return this.solicitud;
	}

}
