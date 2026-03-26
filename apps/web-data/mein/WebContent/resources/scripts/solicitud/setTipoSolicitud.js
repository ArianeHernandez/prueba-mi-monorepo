function setTipoSolicitud() {
	// $("#tipos_solicitud").val(2); // Desarrollo
	var tipoSolicitudValor = $("#tipos_solicitud").val();
	console.log("tipos sol");
	console.log(tipoSolicitudValor);
	$("#contenedor_apoderado").hide();
	$("#contenedor_situaciones").hide();
	$("#contenedor_yes_sociedad_en_disolucion").hide();
	$("#contenedor_not_sociedad_en_disolucion").hide();
	$("#contenedor_yes_remision_previa_informacion").hide();
	$("#contenedor_not_remision_previa_informacion").hide();
	if (tipoSolicitudValor == "" || tipoSolicitudValor == null) {
		tipoSolicitudValor = "1";
	}
	if (tipoSolicitudValor == "1" || tipoSolicitudValor == "5") { // sociedad
		var container_otros_documentos_requeridos_pnnoc = document.getElementById("container_otros_documentos_requeridos_pnnoc");
		container_otros_documentos_requeridos_pnnoc.remove();

		var fehcaestadosfinancierospnc = document.getElementById("fechaestadosfinancierospnc");
		fehcaestadosfinancierospnc.remove();
		$("#tipo_de_identificacion_label").text("Tipo de identificación");
		$("#identificacion_solicitante_label").text(
			"NIT del deudor solicitante");
		$("#nombre_solicitante_label").text(
			"Razón Social del Deudor Solicitante");
		$("#row_ciiu").show();
		$("#row_naturaleza").show();
		$("#archivo_certificado_existencia_row").show();
		$("#row_archivo_certificado_matricula_mercantil").hide();
		$("#row_representante_legal").show();
		$("#row_revisor_fiscal").show();
		$("#row_asistente").hide();
		$("#situacion_presentada_label")
			.text(
				"Certifico que la actividad que desarrollo se encuentra en el supuesto de");
		$("#relacion_pasivos_retenciones_autoridades_fiscales_label")
			.text(
				"¿Tiene pasivos por retenciones de carácter obligatorio a favor de autoridades fiscales?");
		$("#relacion_pasivos_descuentos_trabajadores_label")
			.text(
				"¿Tiene pasivos por descuentos efectuados a trabajadores?");
		$("#relacion_pasivos_aportes_seguridad_social_label")
			.text(
				"¿Tiene pasivos por aportes al sistema de seguridad social?");
		$("#inventario_label")
			.html(
				"Inventario de activos del mes anterior (Descargar <a onclick=\"descargaPlantilla(6)\">plantilla aquí</a>)");
		$("#contador_label").text("Contador");
		$("#seccion_informacion_financiera").show();
		$("#seccion_estados_financieros_anteriores").show();
		$("#seccion_otros_documentos_no_comerciante").hide();
		$("#archivo_capacidad_rp_row").show();
		$("#archivo_soporte_capacidad_rp_row").show();
		$("#archivo_informe_row").show();
		$("#poder_abogado_row").show();
		$("#sociedad_inversiones_label")
			.text(
				"¿Tiene inversiones en subsidiarias, negocios conjuntos, asociadas u otras inversiones?");
		$("#seccion_ingresos_actividades_anuales").show();
		$("#archivo_contabilidad_regular").show();
		$("#archivo_causal_disolucion_row").show();
		$("#plan_negocios_reorganizacion_label").text(
			"Plan de negocios de reorganización");
		$("#archivo_plan_negocios").find("#descripcion_archivo_adj").val(
			"Plan de negocios de reorganización");
		$("#empleadas_row").show();
		$("#empleados_row").show();
		$("#archivo_dictamen_revisor_fiscal_row").show();
		$("#archivo_certificado_existencia_row").show();
		$("#archivo_certificado_controlante_sociedad_row").hide();
		// $("#garantias_mobiliariasa_label").show();
		$("#sociedad_garantias_sujetas_mobiliario_label").hide();
		// $("#garantias_mobiliariasa_label").text("¿La sociedad tiene bienes sujetos a garantías mobiliarias?");
		$("#card_negociacion_deudas").hide();
		$("#sociedad_garantias_sujetas_mobiliario_label").show();
		$("#row_replegal_tiene_limitacion").show();
		$("#row_archivo_maximo_organo_autoriza_replegal").show();
		$("#row_archivo_composicion_accionaria").show();
	} else if (tipoSolicitudValor == "2") { // persona natural comerciante PNC
		$("#card_negociacion_deudas").hide();
		$("#sociedad_garantias_sujetas_mobiliario_label").show();
		var fehcaestadosfinancierospnc = document.getElementById("fechaestadosfinancierospnc");
		fehcaestadosfinancierospnc.remove();
		var container_otros_documentos_requeridos_pnnoc = document.getElementById("container_otros_documentos_requeridos_pnnoc");
		container_otros_documentos_requeridos_pnnoc.remove();
		
		$("#rowSociedadCausalDisolucion").hide();
		$("#archivo_certificado_existencia_row").hide();
		$("#row_archivo_certificado_matricula_mercantil").show();

		$("#identificacion_solicitante_label").text(
			"Cédula del deudor solicitante");
		$("#nombre_solicitante_label").text("Nombre del deudor solicitante");
		$("#row_ciiu").show();
		$("#row_naturaleza").hide();
		$("#row_representante_legal").hide();
		$("#row_revisor_fiscal").show();
		$("#row_asistente").show();
		$("#situacion_presentada_label")
			.text(
				"La persona natural comerciante se encuentra en el supuesto de");
		$("#relacion_pasivos_retenciones_autoridades_fiscales_label")
			.text(
				"¿Tiene pasivos por retenciones de carácter obligatorio a favor de autoridades fiscales?");
		$("#relacion_pasivos_descuentos_trabajadores_label").text(
			"¿Tiene pasivos por descuentos efectuados a trabajadores?");
		$("#relacion_pasivos_aportes_seguridad_social_label").text(
			"¿Tiene pasivos por aportes al sistema de seguridad social?");
		$("#contador_label").text("Contador");
		$("#seccion_informacion_financiera").show();
		$("#inventario_label").text("Inventario de activos");
		$("#seccion_estados_financieros_anteriores").show();
		$("#seccion_otros_documentos_no_comerciante").hide();
		$("#archivo_capacidad_rp_row").hide();
		$("#archivo_soporte_capacidad_rp_row").hide();
		$("#archivo_informe_row").show();
		$("#poder_abogado_row").show();
		$("#sociedad_inversiones_label")
			.text(
				"¿Tiene inversiones en subsidiarias, negocios conjuntos, asociadas u otras inversiones?");
		$("#seccion_ingresos_actividades_anuales").show();
		$("#archivo_contabilidad_regular").show();
		$("#archivo_causal_disolucion_row").hide();
		$("#plan_negocios_reorganizacion_label").text("Plan de negocios de reorganización");
		$("#archivo_plan_negocios").find("#descripcion_archivo_adj").val(
			"Plan de negocios");
		$("#empleadas_row").show();
		$("#empleados_row").show();
		$("#archivo_dictamen_revisor_fiscal_row").hide();
		$("#archivo_certificado_controlante_sociedad_row").hide();
		$("#sociedad_garantias_sujetas_mobiliario_label").text("¿Tiene bienes sujetos a garantías?");
		$("#sociedad_causal_disolucion_label").text("¿Tiene pasivos pensionales a cargo?");
		$("#sociedad_garante_aalista_label").text("¿Es garante, avalista o codeudor de terceros?");
		$("#row_replegal_tiene_limitacion").hide();
		$("#row_archivo_maximo_organo_autoriza_replegal").hide();
		$("#row_archivo_composicion_accionaria").hide();
	} else if (tipoSolicitudValor == "3") { // persona natural no comerciante
		// PNNCC
		// $("#sociedad_causal_disolucion_label").text("¿Tiene pasivos pensionales a cargo?");
		// $("contenedor_not_sociedad_en_disolucion").hide();
		$("#archivo_certificado_existencia_row").show();
		$("#row_archivo_certificado_matricula_mercantil").hide();
		$("#label_revisor_fiscal").text("Revisor fiscal (Opcional)");
		$("#div_card_relacion_bienes_pnnoc").show();
		$("#fechaestadosfinancieros_pnnoc").show();
		$("#text_tooltip_memoria_explicativa").html('<p>El régimen de insolvencia regulado en el Decreto Legislativo 560 de 2020, tiene por objeto mitigar la extensión de los efectos sobre las empresas afectadas por las causas que motivaron la declaratoria del Estado de Emergencia Económica, Social y Ecológica de que trata el Decreto 417 del 17 de marzo de 2020, y la recuperación y conservación de la empresa como unidad de explotación económica y fuente generadora de empleo, a través de los mecanismos de salvamento y recuperación aquí previstos. ' +  
				'Las herramientas allí previstas, entre ellas el trámite de Negociación de Emergencia de Acuerdos de Reorganización, serán aplicables a las empresas que se han afectado como consecuencia de la emergencia antes mencionada, y estarán disponibles desde la entrada en vigencia del mencionado Decreto Legislativo, hasta dos (2) años contados a partir de la entrada en vigencia del mismo.</p>');
		var contenedor_inventarios = document.getElementById("contenedor_inventarios");
		contenedor_inventarios.remove();
		var estados_financieros=document.getElementById("conjunto_completo_estados_financieros");
		estados_financieros.remove();
		var container_otros_documentos_requeridos = document.getElementById("container_otros_documentos_requeridos");
		container_otros_documentos_requeridos.remove();
		

		$("#row_deudor_macrosector").hide();
		$("container_otros_documentos_requeridos").hide();
		$("#rowFlujo_caja").hide();
		$("#rowPlan_negocios").hide();
		$("#rowCondicionControlante").show();
		$("#contenedor_not_sociedad_en_disolucion").show();
		
		
		$("#rowSociedadCausalDisolucion").hide();
		$("#tittle_card_memoria_explicativa").text("Causas de la cesación de pagos");
		$("#tittle_card_memoria_explicativa").find("#text_tooltip_memoria_explicativa")
			.html("<p>El régimen de insolvencia regulado en el Decreto Legislativo 560 de 2020, tiene por objeto mitigar " +
					"la extensión de los efectos sobre las empresas afectadas por las causas que motivaron la declaratoria " +
					"del Estado de Emergencia Económica, Social y Ecológica de que trata el Decreto 417 del 17 de marzo de " +
					"2020, y la recuperación y conservación de la empresa como unidad de explotación económica y fuente " +
					"generadora de empleo, a través de los mecanismos de salvamento y recuperación aquí previstos. <br>" +
					"Las herramientas allí previstas, entre ellas el trámite de Negociación de Emergencia de Acuerdos " +
					"de Reorganización, serán aplicables a las empresas que se han afectado como consecuencia de la " +
					"emergencia antes mencionada, y estarán disponibles desde la entrada en vigencia del mencionado " +
					"Decreto Legislativo, hasta dos (2) años contados a partir de la entrada en vigencia del mismo.</p>");
		$("#identificacion_solicitante_label").text(
			"Cédula del deudor solicitante");
		$("#nombre_solicitante_label").text("Nombre del deudor solicitante");
		$("#row_ciiu").hide();
		$("#contenedor_situaciones").show();
		$("#situacion_presentada").val(1);
		$( "#situacion_presentada").prop( "disabled", true );
		mostrarOpcionesadmisibilidad();
		$("#row_naturaleza").hide();
		$("#row_representante_legal").hide();
		$("#row_revisor_fiscal").show();
		$("#row_asistente").show();
		$("#situacion_presentada_label")
			.text(
				"La persona natural no comerciante se encuentra en el supuesto de");
		$("#relacion_pasivos_retenciones_autoridades_fiscales_label")
			.text(
				"¿Tiene pasivos por retenciones de carácter obligatorio a favor de autoridades fiscales?");
		$("#relacion_pasivos_descuentos_trabajadores_label").text(
			"¿Tiene pasivos por descuentos efectuados a trabajadores?");
		$("#relacion_pasivos_aportes_seguridad_social_label").text(
			"¿Tiene pasivos por aportes al sistema de seguridad social?");
		$("#contador_label").text("Contador (Opcional)");
		$("#seccion_informacion_financiera").hide();
		$("#inventario_label").text("Inventario de activos");
		$("#seccion_estados_financieros_anteriores").hide();
		$("#seccion_otros_documentos_no_comerciante").show();
		$("#archivo_capacidad_rp_row").hide();
		$("#archivo_soporte_capacidad_rp_row").hide();
		$("#archivo_informe_row").hide();
		$("#poder_abogado_row").show();
		$("#sociedad_inversiones_label")
			.text(
				"¿Tiene inversiones en subsidiarias, negocios conjuntos, asociadas u otras inversiones?");
		$("#seccion_ingresos_actividades_anuales").hide();
		$("#archivo_contabilidad_regular").hide();
		$("#archivo_causal_disolucion_row").show();
		$("#plan_negocios_reorganizacion_label").text(
			"Propuesta para la negociación de deudas");
		$("#archivo_plan_negocios").find("#descripcion_archivo_adj").val(
			"Propuesta para la negociación de deudas");
		$("#empleadas_row").hide();
		$("#empleados_row").hide();
		$("#archivo_dictamen_revisor_fiscal_row").hide();
		$("#archivo_certificado_existencia_row").hide();
		$("#archivo_certificado_controlante_sociedad_row").show();
		$("#row_detalle_bienes").show();
		$("#row_certificacion_procesos_judiciales_administrativos").show();
		$("#row_certificacion_ingresos_deudor_juramentada").show();
		$("#row_recursos_disponibles_pago_obligaciones").show();
		$("#row_certificacion_solteria").show();
		$("#row_certificacion_demanda_alimentos").show();
		$("#row_renta_anio_anterior").show();
		$("#sociedad_bienes_sujetos_garantias_label").text("¿Tiene bienes sujetos a garantías mobiliarias?");
		$("#garantias_mobiliariasa_label").text("¿Tiene bienes sujetos a garantías mobiliarias?");
		$("#row_certificado_representacion_legal_pnnoc").show();
		$("#row_relacion_acreedores").show();
		$("#contenido_parrafo_cesacion_pagos").text("Manifiesto que, de conformidad con el artículo 1 del Decreto Legislativo 560 de 15 de abril de 2020, las causas que dan inicio a este proceso de Negociación de Emergencia de un Acuerdo de Reorganización son consecuencia de los hechos que dieron lugar a la Emergencia Económica, Social y Ecológica declarada mediante el Decreto 417 de 17 de marzo de 2020");
		$("#row_replegal_tiene_limitacion").hide();
		$("#row_archivo_maximo_organo_autoriza_replegal").hide();
		$("#row_archivo_composicion_accionaria").hide();
	}
}
