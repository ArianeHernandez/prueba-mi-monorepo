function validarSolicitud (solicitud) {
	skin_init_validar();
	$("#advertencia_activos").hide();
	
	var camposComunes = [
	                     
	    // Tipo solicitud de insolvencia
        { identificador: "tipo_solicitante_near", tipo: "TEXTO" },
        { identificador: "tipo_solicitud_near", tipo: "NUMERO" },
        { identificador: "norma_aplicable", tipo: "NUMERO" },
        
        // Identificacion del deudor
        { identificador: "tipo_identificacion", tipo: "TEXTO" },
        { identificador: "deudor_pais", tipo: "NUMERO" },	// ID
        { identificador: "deudor_departamento", tipo: "NUMERO" },	// ID
        { identificador: "deudor_departamento_autocomplete", tipo: "TEXTO" },
        { identificador: "deudor_ciudad", tipo: "NUMERO" },	// ID
        { identificador: "deudor_ciudad_autocomplete", tipo: "TEXTO" },
        { identificador: "deudor_direccion", tipo: "TEXTO" },
        { identificador: "deudor_correo", tipo: "CORREO" },
        { identificador: "deudor_telefono", tipo: "TEXTO" },
        
        // Memoria explicativa
        { identificador: "memoria_explicativa_manifiesto", tipo: "BOTON" },
        { identificador: "archivo_memoria_explicativa", tipo: "ARCHIVO" },
        
        // Supuestos de admisibilidad
        { identificador: "situacion_presentada", tipo: "NUMERO" },	// ID
        { identificador: "negociacion_se_adelanta_por", tipo: "NUMERO" },	// ID
        
        // Relacion de Pasivos
        { identificador: "relacion_pasivos_retenciones_autoridades_fiscales", tipo: "BOTON" },
        { identificador: "archivo_retenciones_fisco", tipo: "ARCHIVO" },
        { identificador: "relacion_pasivos_descuentos_trabajadores", tipo: "BOTON" },
        { identificador: "archivo_retenciones_trabajadores", tipo: "ARCHIVO" },
        { identificador: "relacion_pasivos_aportes_seguridad_social", tipo: "BOTON" },
        { identificador: "archivo_retenciones_seguridad_social", tipo: "ARCHIVO" },
        
        // Otros documentos requeridos
        { identificador: "sociedad_pasivos_pensionales", tipo: "BOTON" },
        
        // Reporte de bienes sujetos a garantias mobiliarias
        { identificador: "garantias_mobiliarias", tipo: "BOTON" }
        
	];
	
	SK_ERROR_VALIDACION = validateForm(camposComunes) || SK_ERROR_VALIDACION;
	
	// Casos especiales comunes (campos dependendientes de otros)
	var validaciones = 0;
	
	// Apoderado (Opcional) - Seleccionado
	if (osm_getValor("apoderado")) {
		validaciones += singlevalidador({ identificador: "archivo_poder_abogado", tipo: "ARCHIVO" });
	}
	
	// Certifico que la actividad que desarrollo se encuentra en el supuesto de - CESACION
	if (osm_getValor("situacion_presentada") == 1) {
		validaciones += singlevalidador({ identificador: "archivo_certificado_cesacion_pagos", tipo: "ARCHIVO" });
		validaciones += singlevalidador({ identificador: "archivo_anexo_cesacion_pagos", tipo: "ARCHIVO" });
	}
	
	// Tiene pasivos por retenciones de caracter obligatorio a favor de autoridades fiscales? - SI
	if (osm_getValor("relacion_pasivos_retenciones_autoridades_fiscales") == 1) {
		validaciones += singlevalidador({ identificador: "archivo_plan_fisco", tipo: "ARCHIVO" });
	}
	
	// Tiene pasivos por descuentos efectuados a trabajadores? - SI
	if (osm_getValor("relacion_pasivos_descuentos_trabajadores") == 1) {
		validaciones += singlevalidador({ identificador: "archivo_plan_trabajadores", tipo: "ARCHIVO" });
	}
	
	// Tiene pasivos por aportes al sistema de seguridad social? - SI
	if (osm_getValor("relacion_pasivos_aportes_seguridad_social") == 1) {
		validaciones += singlevalidador({ identificador: "archivo_plan_seguridad_social", tipo: "ARCHIVO" });
	}

	// ¿Tiene pasivos pensionales a cargo? - SI
	if (osm_getValor("sociedad_pasivos_pensionales") == 1) {
		validaciones += singlevalidador({ identificador: "archivo_certificacion_calculo_actuarial", tipo: "ARCHIVO" });
		validaciones += singlevalidador({ identificador: "documento_calculo_actuarial", tipo: "ARCHIVO" });
		validaciones += singlevalidador({ identificador: "archivo_aprobacion_calculo_actuarial", tipo: "ARCHIVO" });
		validaciones += singlevalidador({ identificador: "certificacion_mesadas_al_dia", tipo: "ARCHIVO" });
	}
	
	// ¿Tiene bienes sujetos a garantías mobiliarias? - SI
	if (osm_getValor("garantias_mobiliarias") == 1) {
		validaciones += singlevalidador({ identificador: "certificacion_garantia_mobiliaria", tipo: "ARCHIVO" });
		validaciones += singlevalidador({ identificador: "archivo_avaluo", tipo: "ARCHIVO" });
		validaciones += singlevalidador({ identificador: "archivo_rel_pas", tipo: "ARCHIVO" });
	}
	
	
	// Casos especiales por tipo de solicitante
	if ($("#tipos_solicitud").val() == 1 || $("#tipos_solicitud").val() == 5) {
		validaciones += specialCasesSociedad();
	} else if ($("#tipos_solicitud").val() == 2) {
		validaciones += specialCasesPNC();
	} else if ($('#tipos_solicitud').val() == 3) {
		validaciones += specialCasesPNNC();
	}
	
	SK_ERROR_VALIDACION = SK_ERROR_VALIDACION || (validaciones > 0);
	
	if ($("#tipos_solicitud").val() != "3") {
		if (validarActivoPasivoPatrimonio()) {
			$("#advertencia_activos").show();
			$("#advertencia_activos").addClass("ERR_VALIDAR");
			SK_ERROR_VALIDACION = true;
		} else {
			$("#advertencia_activos").hide();
			$("#advertencia_activos").removeClass("ERR_VALIDAR");
		}
	}
	
	if (SK_ERROR_VALIDACION) {
		osm_alert("Hay campos inv\u00e1lidos o vac\u00edos, por favor verifique el formulario.");
		return false;
	}

	return true;
}

function validateForm(arrayEntries) {	
	removerErrorMultiplesEntradas(arrayEntries);
	return validarMultiplesEntradas(arrayEntries) > 0;
}

function specialCasesSociedad() {
	
	var camposSociedad = [
	                      
	    // Identificacion del deudor
	    { identificador: "deudor_ciiu", tipo: "TEXTO" },
	    { identificador: "deudor_ciiu_autocomplete", tipo: "TEXTO" },
	    { identificador: "deudor_macrosector", tipo: "NUMERO" },
	    { identificador: "deudor_naturaleza", tipo: "NUMERO" },
	    { identificador: "deudor_empleados_mujeres", tipo: "NUMERO" },
	    { identificador: "deudor_empleados_hombres", tipo: "NUMERO" },
	    { identificador: "representante_legal", tipo: "NUMERO" },
	    { identificador: "archivo_certificado_existencia", tipo: "ARCHIVO" },
	    { identificador: "replegal_tiene_limitacion", tipo: "BOTON" },
	    
        // Informacion de profesionales asociados al deudor
        { identificador: "contador", tipo: "NUMERO" },	// ID
	    
	    // Informacion financiera con corte al ultimo dia calendario del mes anterior
	    { identificador: "fecha_giro", tipo: "TEXTO" },
	    { identificador: "informacion_financiera_activos", tipo: "DECIMAL" },
	    { identificador: "informacion_financiera_pasivos", tipo: "DECIMAL" },
	    { identificador: "informacion_financiera_patrimonio", tipo: "DECIMALSIGNO" },
	    { identificador: "tipo_solicitud_niif", tipo: "NUMERO" },
	    { identificador: "archivo_estados_mes", tipo: "ARCHIVO" },
	    { identificador: "archivo_nota_estados_mes", tipo: "ARCHIVO" },
	    
	    // Informacion financiera con corte al ultimo anio anterior
	    { identificador: "fecha_eeff_anio_anterior", tipo: "TEXTO" },
	    { identificador: "informacion_financiera_activos_anio", tipo: "DECIMAL" },
	    { identificador: "informacion_financiera_pasivos_anio", tipo: "DECIMAL" },
	    { identificador: "informacion_financiera_ingresos_ordinarios", tipo: "DECIMAL" },
	    { identificador: "informacion_financiera_otros_ingresos", tipo: "DECIMAL" },
	    { identificador: "sociedad_inversiones", tipo: "BOTON" },
	    
	    // Conjunto completo de estados financieros comparativos
	    { identificador: "remision_previa_informacion", tipo: "BOTON" },
	    
	    // Inventario de activos y pasivos con corte al ultimo dia del mes anterior
	    { identificador: "archivo_inventario_activos", tipo: "ARCHIVO" },
	    { identificador: "archivo_inventario_pasivos", tipo: "ARCHIVO" },
	    
	    // Otros documentos requeridos
	    { identificador: "archivo_cr", tipo: "ARCHIVO" },
	    { identificador: "sociedad_en_disolucion", tipo: "BOTON" },
	    { identificador: "sociedad_garante_codeudor", tipo: "BOTON" },
	    
	    // Plan de negocios
	    { identificador: "archivo_plan_negocios", tipo: "ARCHIVO" },
	    
	    // Proyectos de calificacion y graduacion de creditos y derechos de voto
        { identificador: "archivo_pry_pnoc", tipo: "ARCHIVO" },
	    
	    // Flujo de caja
	    { identificador: "archivo_flujo", tipo: "ARCHIVO" }
    ];

	SK_ERROR_VALIDACION = validateForm(camposSociedad) || SK_ERROR_VALIDACION;
	
	// Campos dependientes de otros
	var validaciones = 0;
	
	// Naturaleza - MIXTA
	if (osm_getValor("deudor_naturaleza") == "1") {
		validaciones += singlevalidador({ identificador: "deudor_porcentaje_estatal", tipo: "DECIMAL" });
	}
	
	// Certifico que la actividad que desarrollo se encuentra en el supuesto de - INCAPACIDAD
	if (osm_getValor("situacion_presentada") == 2) {
		validaciones += singlevalidador({ identificador: "archivo_certificado_incapacidad_pago", tipo: "ARCHIVO" });
	}
	
	// Tiene inversiones en subsidiarias, negocios conjuntos, asociadas u otras inversiones? - SI
	if (osm_getValor("sociedad_inversiones") == "1") {
		validaciones += singlevalidador({ identificador: "total_participacion_metodo_participacion", tipo: "DECIMAL" });
		validaciones += singlevalidador({ identificador: "total_participacion_metodo_costo", tipo: "DECIMAL" });
		validaciones += singlevalidador({ identificador: "total_participacion_metodo_vrazonable", tipo: "DECIMAL" });
	}
	
	// Usted ha remitido previamente informacion financiera a la Superintendencia de sociedades? - NO
	if (osm_getValor("remision_previa_informacion") == "0") {
		validaciones += singlevalidador({ identificador: "archivo_estados_an", tipo: "ARCHIVO" });
		validaciones += singlevalidador({ identificador: "archivo_nota_estados_an", tipo: "ARCHIVO" });
		validaciones += singlevalidador({ identificador: "archivo_estados_pan", tipo: "ARCHIVO" });
		validaciones += singlevalidador({ identificador: "archivo_nota_estados_pan", tipo: "ARCHIVO" });
		validaciones += singlevalidador({ identificador: "archivo_estados_apan", tipo: "ARCHIVO" });
		validaciones += singlevalidador({ identificador: "archivo_nota_estados_apan", tipo: "ARCHIVO" });
	}
	
	// Usted ha remitido previamente informacion financiera a la Superintendencia de sociedades? - SI
	if (osm_getValor("remision_previa_informacion") == "1") {
		validaciones += singlevalidador({ identificador: "ultimo_radicado_dictamen", tipo: "TEXTO" });
		validaciones += singlevalidador({ identificador: "penultimo_radicado_dictamen", tipo: "TEXTO" });
		validaciones += singlevalidador({ identificador: "antepenultimo_radicado_dictamen", tipo: "TEXTO" });
	}
	
	// ¿Es garante, avalista o codeudor de terceros? - SI
	if (osm_getValor("sociedad_garante_codeudor") == "1") {
		validaciones += singlevalidador({ identificador: "certificacion_garante_codeudor", tipo: "ARCHIVO" });
	}
	
	// ¿Se encuentra en causal de disolución? - NO
	if (osm_getValor("sociedad_en_disolucion") == "0") {
		validaciones += singlevalidador({ identificador: "archivo_no_causal_disolucion", tipo: "ARCHIVO" });
	}
	
	// ¿Se encuentra en causal de disolución? - SI
	if (osm_getValor("sociedad_en_disolucion") == "1") {
		validaciones += singlevalidador({ identificador: "archivo_causal_disolucion", tipo: "ARCHIVO" });
	}
	
	return validaciones;
}

function specialCasesPNC() {
	
	var camposPNC = [
	                 
        // Identificacion del deudor
	    { identificador: "deudor_ciiu", tipo: "TEXTO" },
		{ identificador: "deudor_ciiu_autocomplete", tipo: "TEXTO" },
		{ identificador: "deudor_macrosector", tipo: "NUMERO" },
		{ identificador: "deudor_empleados_mujeres", tipo: "NUMERO" },
		{ identificador: "deudor_empleados_hombres", tipo: "NUMERO" },
		
		// Informacion de profesionales asociados al deudor
        { identificador: "contador", tipo: "NUMERO" },	// ID
		
		// Informacion financiera con corte al ultimo dia calendario del mes anterior
		{ identificador: "fecha_giro", tipo: "TEXTO" },
	    { identificador: "informacion_financiera_activos", tipo: "DECIMAL" },
	    { identificador: "informacion_financiera_pasivos", tipo: "DECIMAL" },
	    { identificador: "informacion_financiera_patrimonio", tipo: "DECIMALSIGNO" },
	    { identificador: "tipo_solicitud_niif", tipo: "NUMERO" },
	    { identificador: "archivo_estados_mes", tipo: "ARCHIVO" },
	    { identificador: "archivo_nota_estados_mes", tipo: "ARCHIVO" },
	    
	    // Informacion financiera con corte al ultimo anio anterior
	    { identificador: "fecha_eeff_anio_anterior", tipo: "TEXTO" },
	    { identificador: "informacion_financiera_activos_anio", tipo: "DECIMAL" },
	    { identificador: "informacion_financiera_pasivos_anio", tipo: "DECIMAL" },
	    { identificador: "informacion_financiera_ingresos_ordinarios", tipo: "DECIMAL" },
	    { identificador: "informacion_financiera_otros_ingresos", tipo: "DECIMAL" },
	    { identificador: "sociedad_inversiones", tipo: "BOTON" },
	    
	    // Conjunto completo de estados financieros comparativos
	    { identificador: "remision_previa_informacion", tipo: "BOTON" },
	    
	    // Inventario de activos y pasivos con corte al ultimo dia del mes anterior
	    { identificador: "archivo_inventario_activos", tipo: "ARCHIVO" },
	    { identificador: "archivo_inventario_pasivos", tipo: "ARCHIVO" },
	    
	    // Otros documentos requeridos
	    { identificador: "archivo_cr", tipo: "ARCHIVO" },
	    { identificador: "sociedad_garante_codeudor", tipo: "BOTON" },
	    
	    // Plan de negocios
	    { identificador: "archivo_plan_negocios", tipo: "ARCHIVO" },
	    
	    // Proyectos de calificacion y graduacion de creditos y derechos de voto
        { identificador: "archivo_pry_pnoc", tipo: "ARCHIVO" },
	    
	    // Flujo de caja
	    { identificador: "archivo_flujo", tipo: "ARCHIVO" }
	    
	];
	
	SK_ERROR_VALIDACION = validateForm(camposPNC) || SK_ERROR_VALIDACION;
	
	// Campos dependientes de otros
	var validaciones = 0;
	
	// Tiene inversiones en subsidiarias, negocios conjuntos, asociadas u otras inversiones? - SI
	if (osm_getValor("sociedad_inversiones") == "1") {
		validaciones += singlevalidador({ identificador: "total_participacion_metodo_participacion", tipo: "DECIMAL" });
		validaciones += singlevalidador({ identificador: "total_participacion_metodo_costo", tipo: "DECIMAL" });
		validaciones += singlevalidador({ identificador: "total_participacion_metodo_vrazonable", tipo: "DECIMAL" });
	}
	
	// Usted ha remitido previamente informacion financiera a la Superintendencia de sociedades? - NO
	if (osm_getValor("remision_previa_informacion") == "0") {
		validaciones += singlevalidador({ identificador: "archivo_estados_an", tipo: "ARCHIVO" });
		validaciones += singlevalidador({ identificador: "archivo_nota_estados_an", tipo: "ARCHIVO" });
		validaciones += singlevalidador({ identificador: "archivo_estados_pan", tipo: "ARCHIVO" });
		validaciones += singlevalidador({ identificador: "archivo_nota_estados_pan", tipo: "ARCHIVO" });
		validaciones += singlevalidador({ identificador: "archivo_estados_apan", tipo: "ARCHIVO" });
		validaciones += singlevalidador({ identificador: "archivo_nota_estados_apan", tipo: "ARCHIVO" });
	}
	
	// Usted ha remitido previamente informacion financiera a la Superintendencia de sociedades? - SI
	if (osm_getValor("remision_previa_informacion") == "1") {
		validaciones += singlevalidador({ identificador: "ultimo_radicado_dictamen", tipo: "TEXTO" });
		validaciones += singlevalidador({ identificador: "penultimo_radicado_dictamen", tipo: "TEXTO" });
		validaciones += singlevalidador({ identificador: "antepenultimo_radicado_dictamen", tipo: "TEXTO" });
	}
	
	// ¿Es garante, avalista o codeudor de terceros? - SI
	if (osm_getValor("sociedad_garante_codeudor") == "1") {
		validaciones += singlevalidador({ identificador: "certificacion_garante_codeudor", tipo: "ARCHIVO" });
	}
	
	return validaciones;
}

function specialCasesPNNC() {
	
	var camposPNNC = [
	                  
	    // Condicion controlante
		{ identificador: "controlante_sociedad_en_reorganizacion", tipo: "BOTON" },
		{ identificador: "archivo_certificado_controlante_sociedad", tipo: "ARCHIVO" },
		{ identificador: "archivo_composicion_sociedad_controlada", tipo: "ARCHIVO" },
		{ identificador: "certificado_representacion_legal_pnnoc", tipo: "ARCHIVO" },
		{ identificador: "archivo_prueba_controlante_soc_admitida", tipo: "ARCHIVO" },
		
		// Relacion completa y detallada de sus bienes y acreedores
		{ identificador: "fecha_relacion_bienes_acreedores", tipo: "TEXTO" },
	    { identificador: "detalle_bienes_a", tipo: "ARCHIVO" },
	    { identificador: "relacion_acreedores", tipo: "ARCHIVO" },
	    
	    // Propuesta para la negociacion de deudas
	    { identificador: "propuesta_negociacion_deudas", tipo: "ARCHIVO" },
	    
	    // Proyectos de calificacion y graduacion de creditos y derechos de voto
        { identificador: "archivo_pry", tipo: "ARCHIVO" },
	    
	    // Otros documentos requeridos
        { identificador: "pnnoc_pasivos_pensionales", tipo: "BOTON" },
	    { identificador: "certificacion_procesos_judiciales_administrativos", tipo: "ARCHIVO" },
	    { identificador: "certificacion_ingresos_deudor_juramentada", tipo: "ARCHIVO" },
	    { identificador: "recursos_disponibles_pago_obligaciones", tipo: "ARCHIVO" },
	    { identificador: "certificacion_solteria", tipo: "ARCHIVO" },
	    { identificador: "certificacion_demanda_alimentos", tipo: "ARCHIVO" },
	    { identificador: "renta_anio_anterior", tipo: "ARCHIVO" },
	    
	    // Reporte de bienes sujetos a garantias mobiliarias
        { identificador: "garantias_mobiliariasa", tipo: "BOTON" }
	    
	];	
	
	SK_ERROR_VALIDACION = validateForm(camposPNNC) || SK_ERROR_VALIDACION;
	
	// Campos dependientes de otros
	var validaciones = 0;
	
	// El solicitante es controlante de una sociedad que se encuentra en reorganizacion? - SI
	if (osm_getValor("controlante_sociedad_en_reorganizacion") == "1") {
		validaciones += singlevalidador({ identificador: "nit_sociedad_controlada", tipo: "TEXTO" });
		validaciones += singlevalidador({ identificador: "nombre_sociedad_controlada", tipo: "TEXTO" });
		validaciones += singlevalidador({ identificador: "radicado_sociedad_controlada", tipo: "TEXTO" });
	}
	
	// Tiene pasivos pensionales a cargo? - SI
	if (osm_getValor("pnnoc_pasivos_pensionales") == "1") {
		validaciones += singlevalidador({ identificador: "archivo_certificacion_calculo_actuarial_pnnoc", tipo: "ARCHIVO" });
		validaciones += singlevalidador({ identificador: "documento_calculo_actuarial_pnnoc", tipo: "ARCHIVO" });
		validaciones += singlevalidador({ identificador: "archivo_aprobacion_calculo_actuarial_pnnoc", tipo: "ARCHIVO" });
		validaciones += singlevalidador({ identificador: "certificacion_mesadas_al_dia_pnnoc", tipo: "ARCHIVO" });
	}
	
	// Tiene bienes sujetos a garantias mobiliarias? - SI
	if (osm_getValor("garantias_mobiliariasa") == "1") {
		validaciones += singlevalidador({ identificador: "certificacion_garantia_mobiliaria_pnnoc", tipo: "ARCHIVO" });
		validaciones += singlevalidador({ identificador: "archivo_avaluo_pnnoc", tipo: "ARCHIVO" });
		validaciones += singlevalidador({ identificador: "archivo_rel_pas_pnnoc", tipo: "ARCHIVO" });
	}
	
	return validaciones;
}