function generarSolicitud() {
	let garantias = validarCamposNumericos(osm_getValor("garantias_mobiliarias")) != null 
		? validarCamposNumericos(osm_getValor("garantias_mobiliarias")): validarCamposNumericos(osm_getValor("garantias_mobiliariasa"))
	
	solicitud = {
		idcarga: osm_getValorEntero("id_carga"),	
		deudor: {
			datos_basicos: {
				direccion: osm_getValor("deudor_direccion"),
			},
			representante_legal: {
				id_persona_mein: osm_getValor("representante_legal"),
			},
			contador: {
				id_persona_mein: osm_getValor("contador"),
			},
			revisor_fiscal: {
				id_persona_mein: osm_getValor("revisor_fiscal"),
			},
			porcentaje_participacion: validarCamposNumericos(osm_getValor("deudor_porcentaje_estatal")),
			apoderado: {
				id_persona_mein: osm_getValor("apoderado"),
			},
			asistente: {
				id_persona_mein: osm_getValor("asistente"),
			},
			objeto_social: osm_getValor("deudor_razon_social"),
			naturaleza: osm_getValor("deudor_naturaleza"),
			trabajadores_hombres: validarCamposNumericos(osm_getValor("deudor_empleados_hombres")),
			trabajadores_mujeres: validarCamposNumericos(osm_getValor("deudor_empleados_mujeres")),
			macrosector: validarCamposNumericos(osm_getValor("deudor_macrosector")),
			actividad_economica: osm_getValor("deudor_ciiu"),
			departamento_dane: validarCamposNumericos(osm_getValor("deudor_departamento")),
			municipio_dane: validarCamposNumericos(osm_getValor("deudor_ciudad")),
			nit_sociedad_controlada: osm_getValor("nit_sociedad_controlada"),
			name_sociedad_controlada: osm_getValor("nombre_sociedad_controlada"),
			pais_dane: validarCamposNumericos(osm_getValor("deudor_pais")),
			radicado_sociedad_controlada: osm_getValor("radicado_sociedad_controlada"),
			replegal_tiene_limitacion: validarCamposNumericos(osm_getValor("replegal_tiene_limitacion"))
		},
		situacion_presentada: validarCamposNumericos(osm_getValor("situacion_presentada")),
		adel_acreedores: validarCamposNumericos(osm_getValor("negociacion_se_adelanta_por")),
		emergencia_economica: 1,
		informacion_financiera: {
			ultimo_radicado_dictamen: osm_getValor("ultimo_radicado_dictamen"),
			penultimo_radicado_dictamen: osm_getValor("penultimo_radicado_dictamen"),
			antepenultimo_radicado_dictamen: osm_getValor("antepenultimo_radicado_dictamen"),
			fecha_estados_financieros: osm_getValor("fecha_giro"),
			valor_activos: validarCamposNumericos(osm_getValor("informacion_financiera_activos")),
			valor_pasivos: validarCamposNumericos(osm_getValor("informacion_financiera_pasivos")),
			valor_patrimonio: validarCamposNumericos(osm_getValor("informacion_financiera_patrimonio")),
			total_ingresos_ordinarios: validarCamposNumericos(osm_getValor("informacion_financiera_ingresos_ordinarios")),
			fecha_eeff_anio_anterior: osm_getValor("fecha_eeff_anio_anterior"),
			valor_pasivos_ultimoanio: validarCamposNumericos(osm_getValor("informacion_financiera_pasivos_anio")),
			valor_activos_ultimoanio: validarCamposNumericos(osm_getValor("informacion_financiera_activos_anio")),

			total_otros_ingresos: validarCamposNumericos(osm_getValor("informacion_financiera_otros_ingresos")),
			tiene_inversiones: validarCamposNumericos(osm_getValor("sociedad_inversiones")),
			valor_p_participacion: validarCamposNumericos(osm_getValor("total_participacion_metodo_participacion")),
			valor_p_costo: validarCamposNumericos(osm_getValor("total_participacion_metodo_costo")),
			valor_p_razonable: validarCamposNumericos(osm_getValor("total_participacion_metodo_vrazonable")),
			fecha_r_bienes_acreedores: osm_getValor("fecha_relacion_bienes_acreedores"),
		},
		memoria_explicativa: osm_getValor("memoria_explicativa_causas"),
		relacion_de_pasivos: {
			pasivos_por_retenciones: validarCamposNumericos(osm_getValor("relacion_pasivos_retenciones_autoridades_fiscales")),
			pasivos_por_descuentos: validarCamposNumericos(osm_getValor("relacion_pasivos_descuentos_trabajadores")),
			pasivos_por_aportes: validarCamposNumericos(osm_getValor("relacion_pasivos_aportes_seguridad_social")),
		},
		tipo_solicitud: {
			grupo_niif: validarCamposNumericos(osm_getValor("tipo_solicitud_niif"), 10),
			tipo_solicitud: validarCamposNumericos(osm_getValor("tipo_solicitud_near"), 10)
			/* metodo : osm_getValor("tipo_solicitud_metodo"), */
		},
		checklist_solicitud_obj: {
			remision_previa_info: validarCamposNumericos(osm_getValor("remision_previa_informacion")),
			sociedad_en_disolucion: validarCamposNumericos(osm_getValor("sociedad_en_disolucion")),
			sociedad_pasivos_pensiona: validarCamposNumericos(osm_getValor("sociedad_pasivos_pensionales")),
			sociedad_garante_codeudor: validarCamposNumericos(osm_getValor("sociedad_garante_codeudor")),
			garantias_mobiliarias: garantias , 
			pasivos_pensionales: validarCamposNumericos(osm_getValor("pnnoc_pasivos_pensionales")),
			solicitante_controlante: validarCamposNumericos(osm_getValor("controlante_sociedad_en_reorganizacion")),
		},      
		tipo_solicitante: validarCamposNumericos(osm_getValor("tipos_solicitud"), 10)
	};

	return solicitud;
}

function validarCamposNumericos (campo) {
	if (campo) {
		return Number(campo);
	} else {
		return null;
	}
}