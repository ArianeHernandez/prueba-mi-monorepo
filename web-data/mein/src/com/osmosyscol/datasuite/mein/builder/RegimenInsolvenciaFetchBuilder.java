package com.osmosyscol.datasuite.mein.builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.osmosyscol.datasuite.mein.dtos.APVistaBaseContable;
import com.osmosyscol.datasuite.mein.dtos.APVistaCuaderno;
import com.osmosyscol.datasuite.mein.dtos.APVistaDependencias;
import com.osmosyscol.datasuite.mein.dtos.APVistaDeudorCondicion;
import com.osmosyscol.datasuite.mein.dtos.APVistaMedioEnvio;
import com.osmosyscol.datasuite.mein.dtos.APVistaProcesosClases;
import com.osmosyscol.datasuite.mein.dtos.APVistaSeguridadTipo;
import com.osmosyscol.datasuite.mein.dtos.APVistaSerie;
import com.osmosyscol.datasuite.mein.dtos.APVistaSubserie;
import com.osmosyscol.datasuite.mein.dtos.APVistaTramite;
import com.osmosyscol.datasuite.mein.dtos.AcuerdoExtrajudicial;
import com.osmosyscol.datasuite.mein.dtos.AcuerdoRecuperacion;
import com.osmosyscol.datasuite.mein.dtos.AutorizacionJunta;
import com.osmosyscol.datasuite.mein.dtos.BienesAcreedores;
import com.osmosyscol.datasuite.mein.dtos.BienesGarantiaPNNC;
import com.osmosyscol.datasuite.mein.dtos.BienesPnnc;
import com.osmosyscol.datasuite.mein.dtos.BienesSujetosGarantia;
import com.osmosyscol.datasuite.mein.dtos.CausasCesacionPagos;
import com.osmosyscol.datasuite.mein.dtos.CodigoCIIU;
import com.osmosyscol.datasuite.mein.dtos.ComunicacionAcreedores;
import com.osmosyscol.datasuite.mein.dtos.CondicionControlante;
import com.osmosyscol.datasuite.mein.dtos.ConjuntoEEFF;
import com.osmosyscol.datasuite.mein.dtos.CumplimientoHipotesis;
import com.osmosyscol.datasuite.mein.dtos.DatosBasicosPres;
import com.osmosyscol.datasuite.mein.dtos.DepartamentoDane;
import com.osmosyscol.datasuite.mein.dtos.Deudor;
import com.osmosyscol.datasuite.mein.dtos.FlujoCaja;
import com.osmosyscol.datasuite.mein.dtos.InfoFinancMensualPNC;
import com.osmosyscol.datasuite.mein.dtos.InfoFinancieraAnual;
import com.osmosyscol.datasuite.mein.dtos.InfoFinancieraMensual;
import com.osmosyscol.datasuite.mein.dtos.InventarioActivoPasivo;
import com.osmosyscol.datasuite.mein.dtos.MemoriaExplicativa;
import com.osmosyscol.datasuite.mein.dtos.MunicipioDane;
import com.osmosyscol.datasuite.mein.dtos.ObjGenerico;
import com.osmosyscol.datasuite.mein.dtos.OtraInfoRequerida;
import com.osmosyscol.datasuite.mein.dtos.PaisDane;
import com.osmosyscol.datasuite.mein.dtos.PersonaOP;
import com.osmosyscol.datasuite.mein.dtos.PlanNegocios;
import com.osmosyscol.datasuite.mein.dtos.ProfesionalesAsociados;
import com.osmosyscol.datasuite.mein.dtos.PropuestaNegociacion;
import com.osmosyscol.datasuite.mein.dtos.ProyectoCalificacion;
import com.osmosyscol.datasuite.mein.dtos.PruebaAprobacion;
import com.osmosyscol.datasuite.mein.dtos.RegimenInsolvencia;
import com.osmosyscol.datasuite.mein.dtos.RelacionDePasivos;
import com.osmosyscol.datasuite.mein.dtos.SupuestosAdmisibilidad;
import com.osmosyscol.datasuite.mein.dtos.TipoDeDocumento;
import com.osmosyscol.datasuite.near.servicios.radica.LadoRemoto2;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

public class RegimenInsolvenciaFetchBuilder {
	
	private RegimenInsolvenciaFetchBuilder () {}
	
	public static RegimenInsolvenciaFetchBuilder newInstance() {
		return new RegimenInsolvenciaFetchBuilder();
	}
	static final Logger logger = LoggerFactory.getLogger(LadoRemoto2.class);
	private RegimenInsolvencia informacion = new RegimenInsolvencia();
	private Integer idCarga;
	
	public RegimenInsolvenciaFetchBuilder withIdCarga (Integer idCarga) {
		this.idCarga = idCarga;
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia() {
		this.informacion = DS_SqlUtils.queryForObject(RegimenInsolvencia.class, 
			"select regimen.*, " +
			" regimen.$regimen de insolvencia.tipo de solicitante$ tipo_solicitante, " +
			" regimen.$regimen de insolvencia.tipo de solicitud$ tipo_solicitud, " +
			" regimen.$regimen de insolvencia.numero de proceso$ numero_proceso, " +
			" regimen.$regimen de insolvencia.numero de radicado$ numero_radicado, " +
			" regimen.$regimen de insolvencia.deudor$ deudorId, " +
			" regimen.$regimen de insolvencia.bienes y acreedores$ bienes_acreedores_id, " +
			" regimen.$regimen de insolvencia.propuesta de negociacion$ propuesta_negociacion_id, " +
			" regimen.$regimen de insolvencia.info financiera mensual$ info_financiera_mensual_id, " + 
			" regimen.$regimen de insolvencia.info financiera mes pnc$ info_financiera_mes_pnc_id, " + 
			" regimen.$regimen de insolvencia.info financiera anual$ info_financiera_anual_id, " +
			" regimen.$regimen de insolvencia.conjunto eeff$ conjunto_eeff_id, " +
			" regimen.$regimen de insolvencia.inventario activos pasivo$ inventario_activos_pasivos_id, " +
			" regimen.$regimen de insolvencia.otra informacion$ otra_informacion_id, " +
			" regimen.$regimen de insolvencia.plan de negocios$ plan_negocios_id, " +
			" regimen.$regimen de insolvencia.flujo de caja$ flujo_caja_id, " +
			" regimen.$regimen de insolvencia.proyecto de calificacion$ proyecto_calificacion_id, " +
			" regimen.$regimen de insolvencia.acuerdo extrajudicial$ acuerdo_extrajudicial_id, " +
			" regimen.$regimen de insolvencia.acuerdo de recuperacion$ acuerdo_recuperacion_id, " +
			" regimen.$regimen de insolvencia.prueba de aprobacion$ prueba_aprobacion_id, " +
			" regimen.$regimen de insolvencia.comunicacion acreedores$ comunicacion_acreedores_id, " +
			" regimen.$regimen de insolvencia.bienes sujetos garantia$ bienes_sujetos_garantia_id, " +
			" regimen.$regimen de insolvencia.bienes garantia pnnc$ bienes_garantia_pnnc_id, " +
			" regimen.$regimen de insolvencia.dependencia$ dependencia_id, " +
			" regimen.$regimen de insolvencia.medio envio$ medio_envio_id, " +
			" regimen.$regimen de insolvencia.cuaderno$ cuaderno_id, " +
			" regimen.$regimen de insolvencia.serie$ serie_id, " +
			" regimen.$regimen de insolvencia.subserie$ subserie_id, " +
			" regimen.$regimen de insolvencia.tipo seguridad$ tipo_seguridad_id, " +
			" regimen.$regimen de insolvencia.tramite$ tramite_id, " +
			" regimen.$regimen de insolvencia.intendencia regional$ intendencia_regional_id, " +
			" regimen.$regimen de insolvencia.profesionales asociados$ profesionales_asociados_id, " +
			" regimen.$regimen de insolvencia.datos basicos pres$ datos_basicos_pres_id, " +
			" regimen.$regimen de insolvencia.memoria explicativa$ memoria_explicativa_id, " +
			" regimen.$regimen de insolvencia.causas cesacion pagos$ causas_cesacion_pagos_id, " +
			" regimen.$regimen de insolvencia.supuestos admisibilidad$ supuestos_admisibilidad_id, " +
			" regimen.$regimen de insolvencia.autorizacion junta$ autorizacion_junta_id, " +
			" regimen.$regimen de insolvencia.condicion de controlante$ condicion_controlante_id, " +
			" regimen.$regimen de insolvencia.bienes pnnc$ bienes_pnnc_id, " +
			" regimen.$regimen de insolvencia.relacion de pasivos$ relacion_de_pasivos_id, " +
			" regimen.$regimen de insolvencia.procesos clases$ procesos_clases_id, " +
			" regimen.$regimen de insolvencia.intendencia regional$ intendencia_regional_id " +
			" from $regimen de insolvencia$ regimen " +
			" where regimen.idcarga = "	+ idCarga);
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_TipoSolicitud () {
		if (this.informacion != null && this.informacion.getTipo_solicitud() != null) {
			ObjGenerico tipoSolicitud = DS_SqlUtils.queryForObject(ObjGenerico.class, 
				"select tipo.* "
				+ "from $regimen de insolvencia$ regimen "
				+ "left join $tipo de solicitud$ tipo on regimen.$regimen de insolvencia.tipo de solicitud$ = tipo.id "
				+ "where regimen.idcarga = " + idCarga);
			
			this.informacion.setTipo_solicitud_obj(tipoSolicitud);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_Deudor () {
		
		if (this.informacion != null && this.informacion.getDeudorId() != null) {
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
				" from $regimen de insolvencia$ regimen " +
				" left join $deudor$ deudor on regimen.$regimen de insolvencia.deudor$ = deudor.id " +
				" where regimen.idCarga = " + idCarga);
			
			this.informacion.setDeudor(deudor);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_Deudor_TipoIdentificacion () {
		
		if (this.informacion != null && this.informacion.getDeudor() != null
				&& this.informacion.getDeudor().getTipo_identificacion_id() != null ) {
			String queryTipoDoc = "select tipo_documento.* " +
					" from $tipo de documento$ tipo_documento " +
					" where tipo_documento.$tipo de documento.cod_hts$ = " + 
					"	$S(" + this.informacion.getDeudor().getTipo_identificacion_id() + ")$";
					
			TipoDeDocumento tipo_de_documento = DS_SqlUtils.queryForObject(TipoDeDocumento.class, queryTipoDoc);
			
			this.informacion.getDeudor().setTipo_identificacion(tipo_de_documento);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_Deudor_Municipio () {
		if (this.informacion != null && this.informacion.getDeudor() != null
				&& this.informacion.getDeudor().getMunicipio_id() != null) {	
			MunicipioDane municipio = DS_SqlUtils.queryForObject(MunicipioDane.class, 
				"select ciudad.* " +
				" from $deudor$ deudor " +
				" left join $municipios dane$ ciudad on deudor.$deudor.municipio dane$ = ciudad.id " +
				" where deudor.id = " + this.informacion.getDeudor().getId());
		
			this.informacion.getDeudor().setMunicipio(municipio);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_Deudor_Pais_Dane () {
		if (this.informacion != null && this.informacion.getDeudor() != null
				&& this.informacion.getDeudor().getPais_dane_id() != null) {	
			PaisDane pais_dane = DS_SqlUtils.queryForObject(PaisDane.class, 
				"select pais_dane.* " +
				" from $deudor$ deudor " +
				" left join $paises dane$ pais_dane on deudor.$deudor.pais dane$ = pais_dane.id " +
				" where deudor.id = " + this.informacion.getDeudor().getId());
		
			this.informacion.getDeudor().setPais_dane(pais_dane);
		}
		
		return this;
	}

	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_Deudor_Codigo_ciiu () {
		if (this.informacion != null && this.informacion.getDeudor() != null) {	
			CodigoCIIU codigo_ciiu = DS_SqlUtils.queryForObject(CodigoCIIU.class, 
				"select codigo_ciiu.* " +
				" from $deudor$ deudor " +
				" left join $codigo ciiu concatenado$ codigo_ciiu on deudor.$deudor.codigo ciiu$ = codigo_ciiu.id" +
				" where deudor.id = " + this.informacion.getDeudor().getId());
		
			this.informacion.getDeudor().setCodigo_ciiu(codigo_ciiu);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_Deudor_Macrosector () {
		if (this.informacion != null && this.informacion.getDeudor() != null) {	
			ObjGenerico macrosector = DS_SqlUtils.queryForObject(ObjGenerico.class, 
				"select macrosector.* " +
				" from $deudor$ deudor " +
				" left join $macrosector$ macrosector on deudor.$deudor.macrosector$ = macrosector.id " +
				" where deudor.id = " + this.informacion.getDeudor().getId());
		
			this.informacion.getDeudor().setMacrosector(macrosector);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_Deudor_Naturaleza () {
		if (this.informacion != null && this.informacion.getDeudor() != null) {	
			ObjGenerico naturaleza = DS_SqlUtils.queryForObject(ObjGenerico.class, 
				"select naturaleza.* " +
				" from $deudor$ deudor " +
				" left join $naturaleza sociedad$ naturaleza on deudor.$deudor.naturaleza$ = naturaleza.id " +
				" where deudor.id = " + this.informacion.getDeudor().getId());
		
			this.informacion.getDeudor().setNaturaleza(naturaleza);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_Deudor_Departamento () {
		if (this.informacion != null && this.informacion.getDeudor() != null
				&& this.informacion.getDeudor().getDepartamento_id() != null) {
			DepartamentoDane departamento = DS_SqlUtils.queryForObject(DepartamentoDane.class, 
				"select departamento.* " +
				" from $deudor$ deudor " +
				" left join $departamentos dane$ departamento on deudor.$deudor.departamento dane$ = departamento.id " +
				" where deudor.id = " + this.informacion.getDeudor().getId());
			
			this.informacion.getDeudor().setDepartamento(departamento);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_Deudor_RepLegal() {
		if (this.informacion != null && this.informacion.getDeudor() != null
				&& this.informacion.getDeudor().getId_representante_legal() != null) {
			PersonaOP representante_legal = DS_SqlUtils.queryForObject(PersonaOP.class, 
				"select representante_legal.* " +
				" from $deudor$ deudor " +
				" left join $persona$ representante_legal on deudor.$deudor.representante legal$ = representante_legal.id " +
				" where deudor.id = " + this.informacion.getDeudor().getId());
			
			this.informacion.getDeudor().setRepresentante_legal(representante_legal);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_BienesAcreedores () {
		if (this.informacion != null && this.informacion.getBienes_acreedores_id() != null) {
			 BienesAcreedores bienesAcreedores = DS_SqlUtils.queryForObject(BienesAcreedores.class, 
				"select bienes.* " +
				" from $regimen de insolvencia$ regimen " +
				" left join $bienes y acreedores$ bienes on regimen.$regimen de insolvencia.bienes y acreedores$ = bienes.id " +
				" where regimen.idCarga = " + idCarga);
			
			this.informacion.setBienes_acreedores(bienesAcreedores);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_PropuestaNegociacion () {
		if (this.informacion != null && this.informacion.getPropuesta_negociacion_id() != null) {
			PropuestaNegociacion propuestaNegociacion = DS_SqlUtils.queryForObject(PropuestaNegociacion.class, 
				"select propuesta.* " +
				" from $regimen de insolvencia$ regimen " +
				" left join $propuesta negociacion$ propuesta on regimen.$regimen de insolvencia.propuesta de negociacion$ = propuesta.id " +
				" where regimen.idCarga = " + idCarga);
			
			this.informacion.setPropuesta_negociacion(propuestaNegociacion);;
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_InfoFinancieraMensual () {
		if (this.informacion != null && this.informacion.getInfo_financiera_mensual_id() != null) {
			InfoFinancieraMensual infoMensual = DS_SqlUtils.queryForObject(InfoFinancieraMensual.class, 
				"select info.* " +
				" from $regimen de insolvencia$ regimen " +
				" left join $info financiera mensual$ info on regimen.$regimen de insolvencia.info financiera mensual$ = info.id " +
				" where regimen.idCarga = " + idCarga);
			
			this.informacion.setInfo_financiera_mensual(infoMensual);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_InfoFinancieraMensualGrupoNIIF () {
		if (this.informacion != null && this.informacion.getInfo_financiera_mensual() != null
				&& this.informacion.getInfo_financiera_mensual().getGrupo_NIIF() != null) {
			ObjGenerico grupoNIIF = DS_SqlUtils.queryForObject(ObjGenerico.class, 
				"select grupo.* " + 
				" from $info financiera mensual$ info " +
				" left join $grupo niif$ grupo on info.$info financiera mensual.grupo niif$ = grupo.id " +
				" where info.id = "	+ this.informacion.getInfo_financiera_mensual().getId());
			
			this.informacion.getInfo_financiera_mensual().setGrupo_NIIF_obj(grupoNIIF);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_InfoFinancieraMensualBaseContable () {
		if (this.informacion != null && this.informacion.getInfo_financiera_mensual() != null
				&& this.informacion.getInfo_financiera_mensual().getBase_contable() != null) {
			ObjGenerico base_contable = DS_SqlUtils.queryForObject(ObjGenerico.class, 
				"select base.* " + 
				" from $info financiera mensual$ info " +
				" left join $base contable$ base on info.$info financiera mensual.base contable$ = base.id " +
				" where info.id = "	+ this.informacion.getInfo_financiera_mensual().getId());
			
			this.informacion.getInfo_financiera_mensual().setBase_contable_obj(base_contable);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_InfoFinancieraMensualBaseContableAPVista () {
		if (this.informacion != null && this.informacion.getInfo_financiera_mensual() != null
				&& this.informacion.getInfo_financiera_mensual().getBase_contable() != null) {
			APVistaBaseContable base_contable = DS_SqlUtils.queryForObject(APVistaBaseContable.class, 
				"select base.* " + 
				" from $info financiera mensual$ info " +
				" left join $apvista base contable$ base on info.$info financiera mensual.base contable$ = base.$apvista base contable.id lista$ " +
				" where info.id = "	+ this.informacion.getInfo_financiera_mensual().getId());
			
			this.informacion.getInfo_financiera_mensual().setBase_contable_apvista(base_contable);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_InfoFinancieraMensualPNC () {
		if (null != this.informacion && null != this.informacion.getInfo_financiera_mes_pnc_id()) {
			InfoFinancMensualPNC infoMensual = DS_SqlUtils.queryForObject(InfoFinancMensualPNC.class, 
				"select info.* " +
				" from $regimen de insolvencia$ regimen " +
				" left join $info financ mensual pnc$ info on regimen.$regimen de insolvencia.info financiera mes pnc$ = info.id " +
				" where regimen.idCarga = " + idCarga);
			
			this.informacion.setInfo_financiera_mes_pnc(infoMensual);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_InfoFinancieraAnual () {
		if (this.informacion != null && this.informacion.getInfo_financiera_anual_id() != null) {
			InfoFinancieraAnual infoAnual = DS_SqlUtils.queryForObject(InfoFinancieraAnual.class, 
				"select info.*, " +
				" info.$info financiera anual.fecha de los estados fina$ fecha_eeff_anual, " +
				" info.$info financiera anual.activos año anterior$ activos_ano_anterior, " +
				" info.$info financiera anual.pasivos año anterior$ pasivos_ano_anterior, " +
				" info.$info financiera anual.ingresos año anterior$ ingresos_ano_anterior, " +
				" info.$info financiera anual.otros ing año anterior$ otros_ing_ano_anterior " +
				" from $regimen de insolvencia$ regimen " +
				" left join $info financiera anual$ info on regimen.$regimen de insolvencia.info financiera anual$ = info.id " +
				" where regimen.idCarga = " + idCarga);
			
			this.informacion.setInfo_financiera_anual(infoAnual);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_ConjuntoEEFF () {
		if (this.informacion != null && this.informacion.getConjunto_eeff_id() != null) {
			ConjuntoEEFF conjuntoEEFF = DS_SqlUtils.queryForObject(ConjuntoEEFF.class, 
				"select conjunto.*, " +
				" conjunto.$conjunto eeff.ha reportado informacion$ reportado_informacion, " +
				" conjunto.$conjunto eeff.eeff ultimo año$ eeff_ultimo_ano, " +
				" conjunto.$conjunto eeff.notas ultimo año$ notas_ultimo_ano, " +
				" conjunto.$conjunto eeff.dictamen ultimo año$ dictamen_ultimo_ano, " +
				" conjunto.$conjunto eeff.certificacion ultimo año$ certificacion_ultimo_ano, " +
				" conjunto.$conjunto eeff.eeff penultimo año$ eeff_penultimo_ano, " +
				" conjunto.$conjunto eeff.notas penultimo año$ notas_penultimo_ano, " +
				" conjunto.$conjunto eeff.dictamen penultimo año$ dictamen_penultimo_ano, " +
				" conjunto.$conjunto eeff.certificacion penult año$ certificacion_penult_ano, " +
				" conjunto.$conjunto eeff.eeff antepenultimo año$ eeff_antepenultimo_ano, " +
				" conjunto.$conjunto eeff.notas antepenultimo año$ notas_antepenultimo_ano, " +
				" conjunto.$conjunto eeff.dictamen antepenult año$ dictamen_antepenult_ano, " +
				" conjunto.$conjunto eeff.certificacion antepen año$ certificacion_antepen_ano, " +
				" conjunto.$conjunto eeff.radicado eeff 2019$ radicado_eeff_2019, " +
				" conjunto.$conjunto eeff.radicado eeff 2018$ radicado_eeff_2018, " +
				" conjunto.$conjunto eeff.radicado eeff 2017$ radicado_eeff_2017 " +
				" from $regimen de insolvencia$ regimen " +
				" left join $conjunto eeff$ conjunto on regimen.$regimen de insolvencia.conjunto eeff$ = conjunto.id " +
				" where regimen.idCarga = " + idCarga);
			
			this.informacion.setConjunto_eeff(conjuntoEEFF);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_ConjuntoEEFFBasesContables () {
		if (this.informacion != null && this.informacion.getConjunto_eeff() != null) {
			if (this.informacion.getConjunto_eeff().getBase_contable_ultimo() != null) {
				ObjGenerico base_contable = DS_SqlUtils.queryForObject(ObjGenerico.class, 
					"select base.* " +
					" from $conjunto eeff$ conjunto " +
					" left join $base contable$ base on conjunto.$conjunto eeff.base contable ultimo$ = base.id" +
					" where conjunto.id = " + this.informacion.getConjunto_eeff_id());
				
				this.informacion.getConjunto_eeff().setBase_contable_ultimo_obj(base_contable);
			}
			if (this.informacion.getConjunto_eeff().getBase_contable_penultimo() != null) {
				ObjGenerico base_contable = DS_SqlUtils.queryForObject(ObjGenerico.class, 
					"select base.* " +
					" from $conjunto eeff$ conjunto " +
					" left join $base contable$ base on conjunto.$conjunto eeff.base contable penultimo$ = base.id" +
					" where conjunto.id = " + this.informacion.getConjunto_eeff_id());
				
				this.informacion.getConjunto_eeff().setBase_contable_penultimo_obj(base_contable);
			}
			if (this.informacion.getConjunto_eeff().getBase_contable_antepenulti() != null) {
				ObjGenerico base_contable = DS_SqlUtils.queryForObject(ObjGenerico.class, 
					"select base.* " +
					" from $conjunto eeff$ conjunto " +
					" left join $base contable$ base on conjunto.$conjunto eeff.base contable antepenulti$ = base.id" +
					" where conjunto.id = " + this.informacion.getConjunto_eeff_id());
				
				this.informacion.getConjunto_eeff().setBase_contable_antepenulti_obj(base_contable);
			}
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_InventarioActivoPasivo () {
		if (this.informacion != null && this.informacion.getInventario_activos_pasivos_id() != null) {
			InventarioActivoPasivo inventario = DS_SqlUtils.queryForObject(InventarioActivoPasivo.class, 
				"select inventario.*, " +
				" inventario.$inventario activo pasivo.bienes en garantia$ bienes_garantia " +
				" from $regimen de insolvencia$ regimen " +
				" left join $inventario activo pasivo$ inventario on regimen.$regimen de insolvencia.inventario activos pasivo$ = inventario.id " +
				" where regimen.idCarga = " + idCarga);
			
			this.informacion.setInventario_activos_pasivos(inventario);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_OtraInfoRequerida () {
		if (this.informacion != null && this.informacion.getOtra_informacion_id() != null) {
			OtraInfoRequerida otraInfo = DS_SqlUtils.queryForObject(OtraInfoRequerida.class, 
				"select info.*, " +
				" info.$otra info requerida.declaracion de renta$ declaracion_renta, " +
				" info.$otra info requerida.garante de terceros$ garante_terceros " +
				" from $regimen de insolvencia$ regimen " +
				" left join $otra info requerida$ info on regimen.$regimen de insolvencia.otra informacion$ = info.id " +
				" where regimen.idCarga = " + idCarga);
			
			this.informacion.setOtra_informacion(otraInfo);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_PlanNegocios () {
		if (this.informacion != null && this.informacion.getPlan_negocios_id() != null) {
			PlanNegocios planNegocios = DS_SqlUtils.queryForObject(PlanNegocios.class, 
				"select plan.*, " +
				" plan.$plan de negocios.plan de negocios$ plan_negocios " +
				" from $regimen de insolvencia$ regimen " +
				" left join $plan de negocios$ plan on regimen.$regimen de insolvencia.plan de negocios$ = plan.id " + 
				" where regimen.idCarga = " + idCarga);
			
			this.informacion.setPlan_negocios(planNegocios);;
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_FlujoCaja () {
		if (this.informacion != null && this.informacion.getFlujo_caja_id() != null) {
			FlujoCaja flujoCaja = DS_SqlUtils.queryForObject(FlujoCaja.class, 
				"select flujo.*, " +
			    " flujo.$flujo de caja.flujo de caja$ flujo_caja " +
				" from $regimen de insolvencia$ regimen " +
				" left join $flujo de caja$ flujo on regimen.$regimen de insolvencia.flujo de caja$ = flujo.id " +
				" where regimen.idCarga = " + idCarga);
			
			this.informacion.setFlujo_caja(flujoCaja);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_ProyectoCalificacion () {
		if (this.informacion != null && this.informacion.getProyecto_calificacion_id() != null) {
			ProyectoCalificacion proyectoCalificacion = DS_SqlUtils.queryForObject(ProyectoCalificacion.class, 
				"select proyecto.*, " +
				" proyecto.$proyecto de calificacion.proyecto de calificacion$ proyecto_calificacion " +
				" from $regimen de insolvencia$ regimen " +
				" left join $proyecto de calificacion$ proyecto on regimen.$regimen de insolvencia.proyecto de calificacion$ = proyecto.id " +
				" where regimen.idCarga = " + idCarga);
			
			this.informacion.setProyecto_calificacion(proyectoCalificacion);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_AcuerdoExtraJudicial () {
		if (this.informacion != null && this.informacion.getAcuerdo_extrajudicial_id() != null) {
			AcuerdoExtrajudicial acuerdoExtrajudicial = DS_SqlUtils.queryForObject(AcuerdoExtrajudicial.class, 
				"select acuerdo.* " +
				" from $regimen de insolvencia$ regimen " +
				" left join $acuerdo extrajudicial$ acuerdo on regimen.$regimen de insolvencia.acuerdo extrajudicial$ = acuerdo.id " +
				" where regimen.idCarga = " + idCarga);
			
			this.informacion.setAcuerdo_extrajudicial(acuerdoExtrajudicial);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_AcuerdoRecuperacion () {
		if (this.informacion != null && this.informacion.getAcuerdo_recuperacion_id() != null) {
			AcuerdoRecuperacion acuerdoRecuperacion = DS_SqlUtils.queryForObject(AcuerdoRecuperacion.class, 
				"select acuerdo.*, " +
				" acuerdo.$acuerdo de recuperacion.acuerdo de recuperacion$ acuerdo_recuperacion " +
				" from $regimen de insolvencia$ regimen " +
				" left join $acuerdo de recuperacion$ acuerdo on regimen.$regimen de insolvencia.acuerdo de recuperacion$ = acuerdo.id " +
				" where regimen.idCarga = " + idCarga);
			
			this.informacion.setAcuerdo_recuperacion(acuerdoRecuperacion);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_PruebaAprobacion () {
		if (this.informacion != null && this.informacion.getPrueba_aprobacion_id() != null) {
			PruebaAprobacion pruebaAprobacion = DS_SqlUtils.queryForObject(PruebaAprobacion.class, 
				"select prueba.* " +
				" from $regimen de insolvencia$ regimen " + 
				" left join $prueba de aprobacion$ prueba on regimen.$regimen de insolvencia.prueba de aprobacion$ = prueba.id " +
				" where regimen.idCarga = " + idCarga);
			
			this.informacion.setPrueba_aprobacion(pruebaAprobacion);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_ComunicacionAcreedores () {
		if (this.informacion != null && this.informacion.getComunicacion_acreedores_id() != null) {
			ComunicacionAcreedores comunicacionAcreedores = DS_SqlUtils.queryForObject(ComunicacionAcreedores.class, 
				"select comunicacion.* " +
				" from $regimen de insolvencia$ regimen " +
				" left join $comunicacion acreedores$ comunicacion on regimen.$regimen de insolvencia.comunicacion acreedores$ = comunicacion.id " +
				" where regimen.idCarga = " + idCarga);
			
			this.informacion.setComunicacion_acreedores(comunicacionAcreedores);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_BienesSujetosGarantia () {
		if (this.informacion != null && this.informacion.getBienes_sujetos_garantia_id() != null) {
			BienesSujetosGarantia bienesSujetosGarantia = DS_SqlUtils.queryForObject(BienesSujetosGarantia.class, 
				"select bienes.* " +
				" from $regimen de insolvencia$ regimen " +
				" left join $bienes sujetos garantia$ bienes on regimen.$regimen de insolvencia.bienes sujetos garantia$ = bienes.id " +
				" where regimen.idCarga = " + idCarga);
			
			this.informacion.setBienes_sujetos_garantia(bienesSujetosGarantia);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_BienesGarantiaPNNC () {
		if (this.informacion != null && this.informacion.getBienes_garantia_pnnc_id() != null) {
			BienesGarantiaPNNC bienesGarantiaPNNC = DS_SqlUtils.queryForObject(BienesGarantiaPNNC.class, 
				"select bienes.* " +
				" from $regimen de insolvencia$ regimen " +
				" left join $bienes garantia pnnc$ bienes on regimen.$regimen de insolvencia.bienes garantia pnnc$ = bienes.id " +
				" where regimen.idCarga = " + idCarga);
			
			this.informacion.setBienes_garantia_pnnc(bienesGarantiaPNNC);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_BienesPNNC () {
		if (this.informacion != null && this.informacion.getBienes_pnnc_id() != null) {
			BienesPnnc bienesPNNC = DS_SqlUtils.queryForObject(BienesPnnc.class, 
				"select bienes.* " +
				" from $regimen de insolvencia$ regimen " +
				" left join $bienes pnnc$ bienes on regimen.$regimen de insolvencia.bienes pnnc$ = bienes.id " +
				" where regimen.idCarga = " + idCarga);
			
			this.informacion.setBienes_pnnc(bienesPNNC);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_Dependencia () {
		if (this.informacion != null && this.informacion.getDependencia_id() != null) {
			APVistaDependencias dependencia = DS_SqlUtils.queryForObject(APVistaDependencias.class, 
				"select dependencia.* " +
				" from $regimen de insolvencia$ regimen " +
				" left join $apvista dependencias$ dependencia on regimen.$regimen de insolvencia.dependencia$ = dependencia.id " +
				" where regimen.idCarga = " + idCarga);
			
			this.informacion.setDependencia(dependencia);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_ProfesionalesAsociados() {
		if (this.informacion != null && this.informacion.getProfesionales_asociados_id() != null) {
			ProfesionalesAsociados profesionales = DS_SqlUtils.queryForObject(ProfesionalesAsociados.class, 
				"select profesionales_asociados.* " +
				" from $regimen de insolvencia$ regimen " +
				" left join $profesionales asociados$ profesionales_asociados on regimen.$regimen de insolvencia.profesionales asociados$ = profesionales_asociados.id " +
				" where regimen.idCarga = " + idCarga);
			
			this.informacion.setProfesionales_asociados(profesionales);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_ProfesionalesAsociados_Apoderado() {
		if (this.informacion != null && this.informacion.getProfesionales_asociados() != null 
				&& this.informacion.getProfesionales_asociados().getId_apoderado() != null) {
			String queryProfesional = "select profesional.* " +
					" from $profesionales asociados$ profesionales " +
					" left join $persona$ profesional on profesionales.$profesionales asociados.id apoderado$ = profesional.id " +
					" where profesionales.id = " + this.informacion.getProfesionales_asociados_id();
			
			PersonaOP profesional = DS_SqlUtils.queryForObject(PersonaOP.class, queryProfesional);
			
			this.informacion.getProfesionales_asociados().setApoderado(profesional);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_DatosBasicosPres() {
		if (this.informacion != null && this.informacion.getDatos_basicos_pres_id() != null) {
			DatosBasicosPres datos = DS_SqlUtils.queryForObject(DatosBasicosPres.class, 
				"select datos.* " +
				" from $regimen de insolvencia$ regimen " +
				" left join $datos basicos pres$ datos on regimen.$regimen de insolvencia.datos basicos pres$ = datos.id " +
				" where regimen.idCarga = " + idCarga);
			
			this.informacion.setDatos_basicos_pres(datos);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_MedioEnvio () {
		if (this.informacion != null && this.informacion.getMedio_envio_id() != null) {
			APVistaMedioEnvio medioEnvio = DS_SqlUtils.queryForObject(APVistaMedioEnvio.class, 
				"select medio.* " +
				" from $regimen de insolvencia$ regimen " +
				" left join $apvista medio envio$ medio on regimen.$regimen de insolvencia.medio envio$ = medio.id " +
				" where regimen.idCarga = " + idCarga);
			
			this.informacion.setMedio_envio(medioEnvio);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_Cuaderno () {
		if (this.informacion != null && this.informacion.getCuaderno_id() != null) {
			APVistaCuaderno cuaderno = DS_SqlUtils.queryForObject(APVistaCuaderno.class, 
				"select cuaderno.* " +
				" from $regimen de insolvencia$ regimen " +
				" left join $apvista cuaderno$ cuaderno on regimen.$regimen de insolvencia.cuaderno$ = cuaderno.id " +
				" where regimen.idCarga = " + idCarga);
			
			this.informacion.setCuaderno(cuaderno);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_Serie () {
		if (this.informacion != null && this.informacion.getSerie_id() != null) {
			APVistaSerie serie = DS_SqlUtils.queryForObject(APVistaSerie.class, 
				"select serie.* " +
				" from $regimen de insolvencia$ regimen " +
				" left join $apvista serie$ serie on regimen.$regimen de insolvencia.serie$ = serie.id " +
				" where regimen.idCarga = " + idCarga);
			
			this.informacion.setSerie(serie);
			logger.info("serie" + serie);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_Subserie () {
		if (this.informacion != null && this.informacion.getSubserie_id() != null) {
			APVistaSubserie subserie = DS_SqlUtils.queryForObject(APVistaSubserie.class, 
				"select subserie.* " +
				" from $regimen de insolvencia$ regimen " +
				" left join $apvista subserie$ subserie on regimen.$regimen de insolvencia.subserie$ = subserie.id " +
				" where regimen.idCarga = " + idCarga);
			
			this.informacion.setSubserie(subserie);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_TipoSeguridad () {
		if (this.informacion != null && this.informacion.getTipo_seguridad_id() != null) {
			APVistaSeguridadTipo tipoSeguridad = DS_SqlUtils.queryForObject(APVistaSeguridadTipo.class, 
				"select tipo.* " +
				" from $regimen de insolvencia$ regimen " +
				" left join $apvista seguridad tipo$ tipo on regimen.$regimen de insolvencia.tipo seguridad$ = tipo.id " +
				" where regimen.idCarga = " + idCarga);
			
			this.informacion.setTipo_seguridad(tipoSeguridad);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_Tramite () {
		if (this.informacion != null && this.informacion.getTramite_id() != null) {
			APVistaTramite tramite = DS_SqlUtils.queryForObject(APVistaTramite.class, 
				"select tramite.* " +
				" from $regimen de insolvencia$ regimen " +
				" left join $apvista tramite$ tramite on regimen.$regimen de insolvencia.tramite$ = tramite.id " +
				" where regimen.idCarga = " + idCarga);
			
			this.informacion.setTramite(tramite);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_ClaseProceso () {
		if (this.informacion != null && this.informacion.getProcesos_clases_id() != null) {
			APVistaProcesosClases procesos_clases = DS_SqlUtils.queryForObject(APVistaProcesosClases.class, 
				"select procesoclase.* " +
				" from $regimen de insolvencia$ regimen " +
				" left join $apvista procesosclases$ procesoclase on regimen.$regimen de insolvencia.procesos clases$ = procesoclase.id " +
				" where regimen.idCarga = " + idCarga);
			
			this.informacion.setProcesos_clases(procesos_clases);
		}
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_Medio_envio () {
		if (this.informacion != null && this.informacion.getMedio_envio_id() != null) {
			APVistaMedioEnvio medio_envio = DS_SqlUtils.queryForObject(APVistaMedioEnvio.class, 
				"select medio_envio.* " +
				" from $regimen de insolvencia$ regimen " +
				" left join $apvista medio envio$ medio_envio on regimen.$regimen de insolvencia.medio envio$ = medio_envio.id " +
				" where regimen.idCarga = " + idCarga);
			
			this.informacion.setMedio_envio(medio_envio);
		}
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_Tipo_seguridad () {
		if (this.informacion != null && this.informacion.getTipo_seguridad_id() != null) {
			APVistaSeguridadTipo tipo_seguridad = DS_SqlUtils.queryForObject(APVistaSeguridadTipo.class, 
				"select tipo_seguridad.* " +
				" from $regimen de insolvencia$ regimen " +
				" left join $apvista seguridad tipo$ tipo_seguridad on regimen.$regimen de insolvencia.tipo seguridad$ = tipo_seguridad.id " +
				" where regimen.idCarga = " + idCarga);
			
			this.informacion.setTipo_seguridad(tipo_seguridad);
		}
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_Memoria_Explicativa () {
		if (this.informacion != null && this.informacion.getMemoria_explicativa_id() != null) {
			MemoriaExplicativa memoria_explicativa = DS_SqlUtils.queryForObject(MemoriaExplicativa.class, 
				"select memoria_explicativa.* " +
				" from $regimen de insolvencia$ regimen " +
				" left join $memoria explicativa$ memoria_explicativa on regimen.$regimen de insolvencia.memoria explicativa$ = memoria_explicativa.id " +
				" where regimen.idCarga = " + idCarga);
			
			this.informacion.setMemoria_explicativa(memoria_explicativa);
		}
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_Causas_cesacion_pagos () {
		if (this.informacion != null && this.informacion.getCausas_cesacion_pagos_id() != null) {
			CausasCesacionPagos causas_cesacion_pagos = DS_SqlUtils.queryForObject(CausasCesacionPagos.class, 
				"select causas_cesacion_pagos.* " +
				" from $regimen de insolvencia$ regimen " +
				" left join $causas cesacion pagos$ causas_cesacion_pagos on regimen.$regimen de insolvencia.causas cesacion pagos$ = causas_cesacion_pagos.id " +
				" where regimen.idCarga = " + idCarga);
			
			this.informacion.setCausas_cesacion_pagos(causas_cesacion_pagos);
		}
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_Supuestos_admisibilidad () {
		if (this.informacion != null && this.informacion.getSupuestos_admisibilidad_id() != null) {
			SupuestosAdmisibilidad supuestos_admisibilidad = DS_SqlUtils.queryForObject(SupuestosAdmisibilidad.class, 
				"select supuestos_admisibilidad.* " +
				" from $regimen de insolvencia$ regimen " +
				" left join $supuestos admisibilidad$ supuestos_admisibilidad on regimen.$regimen de insolvencia.supuestos admisibilidad$ = supuestos_admisibilidad.id " +
				" where regimen.idCarga = " + idCarga);
			
			this.informacion.setSupuestos_admisibilidad(supuestos_admisibilidad);
		}
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_Situacion_presentada () {
		if (this.informacion != null && this.informacion.getSupuestos_admisibilidad_id() != null) {	
			ObjGenerico situacion_presentada = DS_SqlUtils.queryForObject(ObjGenerico.class, 
				"select situacion_presentada.* " +
				" from $supuestos admisibilidad$ supuestos_admisibilidad " +
				" left join $situacion presentada$ situacion_presentada on supuestos_admisibilidad.$supuestos admisibilidad.supuestos admisibilidad$ = situacion_presentada.id " +
				" where supuestos_admisibilidad.id = " + this.informacion.getSupuestos_admisibilidad_id());
		
			this.informacion.getSupuestos_admisibilidad().setSupuestos_admisibilidad(situacion_presentada);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_Situacion_presentada_PNNC () {
		if (this.informacion != null && this.informacion.getSupuestos_admisibilidad_id() != null) {	
			ObjGenerico situacion_presentada_pnnc = DS_SqlUtils.queryForObject(ObjGenerico.class, 
				"select situacion_presentada.* " +
				" from $supuestos admisibilidad$ supuestos_admisibilidad " +
				" left join $situacion presentada$ situacion_presentada on supuestos_admisibilidad.$supuestos admisibilidad.supuestos pnnc$ = situacion_presentada.id " +
				" where supuestos_admisibilidad.id = " + this.informacion.getSupuestos_admisibilidad_id());
		
			this.informacion.getSupuestos_admisibilidad().setSupuestos_pnnc(situacion_presentada_pnnc);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_Condicion_controlante () {
		if (this.informacion != null && this.informacion.getCondicion_controlante_id() != null) {
			CondicionControlante condicion_controlante = DS_SqlUtils.queryForObject(CondicionControlante.class, 
				"select condicion_controlante.*, " +
				" condicion_controlante.$condición de controlante.radicado solicitud o auto$ radicado_solicitud_auto " +
				" from $regimen de insolvencia$ regimen " +
				" left join $condición de controlante$ condicion_controlante on regimen.$regimen de insolvencia.condicion de controlante$ = condicion_controlante.id " +
				" where regimen.idCarga = " + idCarga);
			
			this.informacion.setCondicion_controlante(condicion_controlante);
		}
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_Condicion_controlante_deudor () {
		if (this.informacion != null && this.informacion.getCondicion_controlante() != null
				&& this.informacion.getCondicion_controlante().getCondicion_deudor() != null) {
			ObjGenerico condicion_deudor = DS_SqlUtils.queryForObject(ObjGenerico.class, 
				"select condicion_deudor.* " +
				" from $condición de controlante$ condicion_controlante " +
				" left join $condicion deudor$ condicion_deudor on condicion_controlante.$condición de controlante.condicion deudor$ = condicion_deudor.id " +
				" where condicion_controlante.id = " + this.informacion.getCondicion_controlante_id());
			
			this.informacion.getCondicion_controlante().setCondicion_deudor_obj(condicion_deudor);
		}
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_Condicion_controlante_deudor_apvista () {
		if (this.informacion != null && this.informacion.getCondicion_controlante() != null
				&& this.informacion.getCondicion_controlante().getCondicion_deudor() != null) {
			APVistaDeudorCondicion condicion_deudor = DS_SqlUtils.queryForObject(APVistaDeudorCondicion.class, 
				"select condicion_deudor.* " +
				" from $condición de controlante$ condicion_controlante " +
				" left join $apvista deudor condicion$ condicion_deudor on condicion_controlante.$condición de controlante.condicion deudor$ = condicion_deudor.$apvista deudor condicion.id lista$ " +
				" where condicion_controlante.id = " + this.informacion.getCondicion_controlante_id());
			
			this.informacion.getCondicion_controlante().setCondicion_deudor_apvista(condicion_deudor);
		}
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_Hipotesis () {
		if (this.informacion != null && this.informacion.getHipotesis() != null) {
			CumplimientoHipotesis hipotesis = DS_SqlUtils.queryForObject(CumplimientoHipotesis.class, 
				"select hipotesis.* "
				+ "from $regimen de insolvencia$ regimen "
				+ "left join $cumplimiento hipotesis$ hipotesis on regimen.$regimen de insolvencia.hipotesis$ = hipotesis.id "
				+ "where regimen.idcarga = " + idCarga);
			
			this.informacion.setHipotesis_obj(hipotesis);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_Relacion_pasivos () {
		if (this.informacion != null && this.informacion.getRelacion_de_pasivos_id() != null) {
			RelacionDePasivos relacion_de_pasivos = DS_SqlUtils.queryForObject(RelacionDePasivos.class, 
				"select relacion_de_pasivos.* " +
				" from $regimen de insolvencia$ regimen " +
				" left join $relacion de pasivos$ relacion_de_pasivos on regimen.$regimen de insolvencia.relacion de pasivos$ = relacion_de_pasivos.id " +
				" where regimen.idCarga = " + idCarga);
			
			this.informacion.setRelacion_de_pasivos(relacion_de_pasivos);
		}
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_Autorizacion_junta () {
		if (this.informacion != null && this.informacion.getAutorizacion_junta_id() != null) {
			AutorizacionJunta autorizacion_junta = DS_SqlUtils.queryForObject(AutorizacionJunta.class, 
					"select autorizacion.* "
					+ "from $regimen de insolvencia$ regimen "
					+ "left join $autorizacion junta$ autorizacion on regimen.$regimen de insolvencia.autorizacion junta$ = autorizacion.id "
					+ "where regimen.idcarga = " + idCarga);
			
			this.informacion.setAutorizacion_junta(autorizacion_junta);
		}
		
		return this;
	}
	
	public RegimenInsolvenciaFetchBuilder fetchRegimenInsolvencia_Intendencia_regional () {
		if (this.informacion != null && this.informacion.getIntendencia_regional_id() != null) {
			ObjGenerico intendencia = DS_SqlUtils.queryForObject(ObjGenerico.class, 
					"select intendencia.* "
					+ "from $regimen de insolvencia$ regimen "
					+ "left join $intendencias$ intendencia on regimen.$regimen de insolvencia.intendencia regional$ = intendencia.id "
					+ "where regimen.idcarga = " + idCarga);
			
			this.informacion.setIntendencia_regional(intendencia);
		}
		
		return this;
	}
	
	public RegimenInsolvencia getInformacion() {
		return this.informacion;
	}
	
	public RegimenInsolvencia obtenerInfoRegimenInsolvenciaPorIdCarga(Integer id_carga){
		RegimenInsolvenciaFetchBuilder builder = RegimenInsolvenciaFetchBuilder.newInstance();
		RegimenInsolvencia infoRegimenInsolvencia = builder.withIdCarga(id_carga)
				.fetchRegimenInsolvencia()
				.fetchRegimenInsolvencia_AcuerdoExtraJudicial()
				.fetchRegimenInsolvencia_AcuerdoRecuperacion()
				.fetchRegimenInsolvencia_BienesAcreedores()
				.fetchRegimenInsolvencia_BienesGarantiaPNNC()
				.fetchRegimenInsolvencia_BienesPNNC()
				.fetchRegimenInsolvencia_BienesSujetosGarantia()
//				.fetchRegimenInsolvencia_Causas_cesacion_pagos()
				.fetchRegimenInsolvencia_ClaseProceso()
				.fetchRegimenInsolvencia_ComunicacionAcreedores()
				.fetchRegimenInsolvencia_Condicion_controlante()
				.fetchRegimenInsolvencia_Condicion_controlante_deudor()
				.fetchRegimenInsolvencia_Condicion_controlante_deudor_apvista()
				.fetchRegimenInsolvencia_ConjuntoEEFF()
				.fetchRegimenInsolvencia_ConjuntoEEFFBasesContables()
				.fetchRegimenInsolvencia_Cuaderno()
//				.fetchRegimenInsolvencia_Serie()
//				.fetchRegimenInsolvencia_Subserie()
				.fetchRegimenInsolvencia_DatosBasicosPres()
				.fetchRegimenInsolvencia_Dependencia()
				.fetchRegimenInsolvencia_Deudor()
				.fetchRegimenInsolvencia_Deudor_Codigo_ciiu()
				.fetchRegimenInsolvencia_Deudor_Departamento()
				.fetchRegimenInsolvencia_Deudor_Macrosector()
				.fetchRegimenInsolvencia_Deudor_Municipio()
				.fetchRegimenInsolvencia_Deudor_Naturaleza()
				.fetchRegimenInsolvencia_Deudor_Pais_Dane()
				.fetchRegimenInsolvencia_Deudor_TipoIdentificacion()
				.fetchRegimenInsolvencia_Deudor_RepLegal()
				.fetchRegimenInsolvencia_FlujoCaja()
				.fetchRegimenInsolvencia_InfoFinancieraAnual()
				.fetchRegimenInsolvencia_InfoFinancieraMensual()
				.fetchRegimenInsolvencia_InfoFinancieraMensualBaseContable()
				.fetchRegimenInsolvencia_InfoFinancieraMensualBaseContableAPVista()
//				.fetchRegimenInsolvencia_InfoFinancieraMensualPNC()
				.fetchRegimenInsolvencia_InventarioActivoPasivo()
				.fetchRegimenInsolvencia_OtraInfoRequerida()
				.fetchRegimenInsolvencia_MedioEnvio()
				.fetchRegimenInsolvencia_Memoria_Explicativa()
				.fetchRegimenInsolvencia_PlanNegocios()
				.fetchRegimenInsolvencia_ProfesionalesAsociados()
//				.fetchRegimenInsolvencia_ProfesionalesAsociados_Apoderado()
				.fetchRegimenInsolvencia_PropuestaNegociacion()
				.fetchRegimenInsolvencia_ProyectoCalificacion()
				.fetchRegimenInsolvencia_PruebaAprobacion()
				.fetchRegimenInsolvencia_Relacion_pasivos()
				.fetchRegimenInsolvencia_Supuestos_admisibilidad()
				.fetchRegimenInsolvencia_Situacion_presentada()
				.fetchRegimenInsolvencia_TipoSeguridad()
				.fetchRegimenInsolvencia_TipoSolicitud()
				.fetchRegimenInsolvencia_Tramite()
				.fetchRegimenInsolvencia_Hipotesis()
				.fetchRegimenInsolvencia_Intendencia_regional()
				.getInformacion();
		
		return infoRegimenInsolvencia;
	}
	
	public RegimenInsolvencia obtenerInfoRegimenInsolvenciaPorIdCargaParaActuaciones(Integer id_carga){
        RegimenInsolvenciaFetchBuilder builder = RegimenInsolvenciaFetchBuilder.newInstance();
        RegimenInsolvencia infoRegimenInsolvencia = builder.withIdCarga(id_carga)
                .fetchRegimenInsolvencia()
//              .fetchRegimenInsolvencia_AcuerdoExtraJudicial()
//              .fetchRegimenInsolvencia_AcuerdoRecuperacion()
//              .fetchRegimenInsolvencia_BienesAcreedores()
//              .fetchRegimenInsolvencia_BienesGarantiaPNNC()
//              .fetchRegimenInsolvencia_BienesSujetosGarantia()
//              .fetchRegimenInsolvencia_Causas_cesacion_pagos()
                .fetchRegimenInsolvencia_ClaseProceso()
//              .fetchRegimenInsolvencia_ComunicacionAcreedores()
//              .fetchRegimenInsolvencia_Condicion_controlante()
//              .fetchRegimenInsolvencia_ConjuntoEEFF()
                .fetchRegimenInsolvencia_Cuaderno()
//              .fetchRegimenInsolvencia_DatosBasicosPres()
                .fetchRegimenInsolvencia_Dependencia()
                .fetchRegimenInsolvencia_Deudor()
//              .fetchRegimenInsolvencia_Deudor_Codigo_ciiu()
                .fetchRegimenInsolvencia_Deudor_Departamento()
//              .fetchRegimenInsolvencia_Deudor_Macrosector()
                .fetchRegimenInsolvencia_Deudor_Municipio()
//              .fetchRegimenInsolvencia_Deudor_Naturaleza()
                .fetchRegimenInsolvencia_Deudor_Pais_Dane()
                .fetchRegimenInsolvencia_Deudor_TipoIdentificacion()
                .fetchRegimenInsolvencia_Deudor_RepLegal()
//              .fetchRegimenInsolvencia_FlujoCaja()
//              .fetchRegimenInsolvencia_InfoFinancieraAnual()
//              .fetchRegimenInsolvencia_InfoFinancieraMensual()
//              .fetchRegimenInsolvencia_InventarioActivoPasivo()
//              .fetchRegimenInsolvencia_OtraInfoRequerida()
                .fetchRegimenInsolvencia_MedioEnvio()
//              .fetchRegimenInsolvencia_Memoria_Explicativa()
//              .fetchRegimenInsolvencia_PlanNegocios()
                .fetchRegimenInsolvencia_ProfesionalesAsociados()
//              .fetchRegimenInsolvencia_ProfesionalesAsociados_Apoderado()
//              .fetchRegimenInsolvencia_PropuestaNegociacion()
//              .fetchRegimenInsolvencia_ProyectoCalificacion()
//              .fetchRegimenInsolvencia_PruebaAprobacion()
//              .fetchRegimenInsolvencia_Relacion_pasivos()
//              .fetchRegimenInsolvencia_Supuestos_admisibilidad()
//              .fetchRegimenInsolvencia_Situacion_presentada()
                .fetchRegimenInsolvencia_TipoSeguridad()
                .fetchRegimenInsolvencia_Tramite()
                .getInformacion();
        
        return infoRegimenInsolvencia;
    }
}
