package com.osmosyscol.datasuite.mein.servicios;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.mein.builder.RegimenInsolvenciaFetchBuilder;
import com.osmosyscol.datasuite.mein.dtos.RegimenInsolvencia;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

public class RegimenInsolvenciaServicio {

	private static RegimenInsolvenciaServicio instance;
	
	private RegimenInsolvenciaServicio () {
	}
	
	public static RegimenInsolvenciaServicio getInstance () {
		if (instance == null) {
			instance = new RegimenInsolvenciaServicio();
		}
		
		return instance;
	}
	
	public RegimenInsolvencia obtenerRegimenInsolvenciaBase (Integer id_carga) {
		RegimenInsolvenciaFetchBuilder builder = RegimenInsolvenciaFetchBuilder.newInstance();
		RegimenInsolvencia solicitud = builder
			.withIdCarga(id_carga)
			.fetchRegimenInsolvencia()
			.getInformacion();
		
		return solicitud;
	}
	
	public RegimenInsolvencia buscarSolicitudLocalRegimen(Integer idCarga) {
		
//		RegimenInsolvencia infoRegimenInsolvencia = RegimenInsolvenciaFetchBuilder.newInstance()
//				.obtenerInfoRegimenInsolvenciaPorIdCarga(idCarga);
//		
//		return infoRegimenInsolvencia;

		RegimenInsolvenciaFetchBuilder builder = RegimenInsolvenciaFetchBuilder.newInstance();
		RegimenInsolvencia solicitud = builder
			.withIdCarga(idCarga)
			.fetchRegimenInsolvencia()
			.fetchRegimenInsolvencia_Deudor()
			.fetchRegimenInsolvencia_Deudor_Departamento()
			.fetchRegimenInsolvencia_Deudor_Municipio()
			.fetchRegimenInsolvencia_Deudor_TipoIdentificacion()
			.fetchRegimenInsolvencia_Dependencia()
			.fetchRegimenInsolvencia_MedioEnvio()
			.fetchRegimenInsolvencia_Cuaderno()
			.fetchRegimenInsolvencia_Serie()
			.fetchRegimenInsolvencia_Subserie()
			.fetchRegimenInsolvencia_TipoSeguridad()
			.fetchRegimenInsolvencia_Tramite()
			.getInformacion();
				
		return solicitud;
	}
	
	public RegimenInsolvencia obtenerInfoRegimenInsolvenciaPorIdCarga(Integer id_carga){
		RegimenInsolvenciaFetchBuilder builder = RegimenInsolvenciaFetchBuilder.newInstance();
		RegimenInsolvencia infoRegimenInsolvencia = builder.withIdCarga(id_carga)
				.fetchRegimenInsolvencia()
				.fetchRegimenInsolvencia_AcuerdoExtraJudicial()
				.fetchRegimenInsolvencia_AcuerdoRecuperacion()
				.fetchRegimenInsolvencia_BienesAcreedores()
				.fetchRegimenInsolvencia_BienesGarantiaPNNC()
				.fetchRegimenInsolvencia_BienesSujetosGarantia()
				.fetchRegimenInsolvencia_ClaseProceso()
				.fetchRegimenInsolvencia_ComunicacionAcreedores()
				.fetchRegimenInsolvencia_Condicion_controlante()
				.fetchRegimenInsolvencia_Condicion_controlante_deudor()
				.fetchRegimenInsolvencia_ConjuntoEEFF()
				.fetchRegimenInsolvencia_ConjuntoEEFFBasesContables()
				.fetchRegimenInsolvencia_Cuaderno()
				.fetchRegimenInsolvencia_Serie()
				.fetchRegimenInsolvencia_Subserie()
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
				.fetchRegimenInsolvencia_InfoFinancieraMensualGrupoNIIF()
				.fetchRegimenInsolvencia_InfoFinancieraMensualBaseContable()
				.fetchRegimenInsolvencia_InfoFinancieraMensualPNC()
				.fetchRegimenInsolvencia_InventarioActivoPasivo()
				.fetchRegimenInsolvencia_OtraInfoRequerida()
				.fetchRegimenInsolvencia_MedioEnvio()
				.fetchRegimenInsolvencia_Memoria_Explicativa()
				.fetchRegimenInsolvencia_PlanNegocios()
				.fetchRegimenInsolvencia_ProfesionalesAsociados()
				.fetchRegimenInsolvencia_PropuestaNegociacion()
				.fetchRegimenInsolvencia_ProyectoCalificacion()
				.fetchRegimenInsolvencia_PruebaAprobacion()
				.fetchRegimenInsolvencia_Relacion_pasivos()
				.fetchRegimenInsolvencia_Supuestos_admisibilidad()
				.fetchRegimenInsolvencia_Situacion_presentada()
				.fetchRegimenInsolvencia_Situacion_presentada_PNNC()
				.fetchRegimenInsolvencia_TipoSeguridad()
				.fetchRegimenInsolvencia_TipoSolicitud()
				.fetchRegimenInsolvencia_Tramite()
				.fetchRegimenInsolvencia_Autorizacion_junta()
				.fetchRegimenInsolvencia_Hipotesis()
				.fetchRegimenInsolvencia_Intendencia_regional()
				.getInformacion();
		
		return infoRegimenInsolvencia;
	}
	
	public Integer obtenerDependencia (Integer id_carga) {
		
		String sql = "SELECT $REGIMEN DE INSOLVENCIA.DEPENDENCIA$ FROM $REGIMEN DE INSOLVENCIA$ WHERE IDCARGA = " + id_carga;
		
		try {
			return DS_SqlUtils.queryForObject(Integer.class, sql);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error al obtener la dependencia de la solicitud " + id_carga, e);
			return null;
		}
		
	}
	
	
}
