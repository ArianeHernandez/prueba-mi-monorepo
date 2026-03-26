var PROCESOS_EJECUTIVOS = 0;
var PASIVOS = 0;
var ACREENCIAS = 0;
var ACREEDORES = 0;
var ARCHIVOS_ADJUNTADOS = 0;
var ARCHIVOS_FINALIZADOS = 0;
var TOTAL_ARCHIVOS_ADJUNTOS = 0;
var ANCHO_BARRA = 0;
var ARCHIVOS_ADJUNTADOS_MEMORIA = 0;
var info_enrolamiento = null;
var ADJUNTOS_PENDIENTES = [];
var SIGUIENTE_ADJUNTO = 0;
var ADJUNTOS_FALLIDOS = [];
osm_verLoader();
osm_block_window();
// Evitar abrir archivos mal arrastrados
window.addEventListener("dragover", function (e) {
	e = e || event;
	console.log(e);
	if (e.target.tagName != "INPUT") { // check which element is our target
		e.preventDefault();
	}
}, false); 
window.addEventListener("drop", function (e) {
	e = e || event;
	console.log(e);
	if (e.target.tagName != "INPUT") { // check which element is our target
		e.preventDefault();
	}
}, false);

function agregarProcesoEjecutivo() {
	var sig_proceso = PROCESOS_EJECUTIVOS + 1;

	osm_construirHTML("procesos_ejecutivos", "PLANTILLA_PROCESO_EJECUTIVO", [
		sig_proceso,]);

	PROCESOS_EJECUTIVOS = sig_proceso;
}
function agregarPasivos() {
	var sig_proceso = PASIVOS + 1;

	osm_construirHTML("relacion_pasivos", "PLANTILLA_PASIVOS", [sig_proceso]);

	PASIVOS = sig_proceso;
}
function agregarAcreencia() {
	var sig_proceso = ACREENCIAS + 1;

	osm_construirHTML("votos_credito_acreedor_acreencia",
		"PLANTILLA_ACREENCIA", [sig_proceso,]);

	ACREENCIAS = sig_proceso;
}
function agregarAcreedor() {
	var sig_proceso = ACREEDORES + 1;

	osm_construirHTML("votos_credito_acreedor",
		"PLANTILLA_VOTOS_CREDITO_ACREEDOR", [sig_proceso]);

	ACREEDORES = sig_proceso;
}

function descargaPlantilla(id_sociedad = 0,id_pnc = 0,id_pnnoc =0 ,id_sucu = 0 ){
	var fake_form_url = "../DescargaArchivoServlet";
	var arrayID= { 1: id_sociedad, 2: id_pnc, 3: id_pnnoc, 5: id_sucu};
	var tpsol = $("#tipos_solicitud").val();
	var id_plantilla = arrayID[tpsol];
	if (id_plantilla) {
		var $fake_form = $("<form>", {
			action: fake_form_url,
			method: "post",
		});
	
		$("<input>").attr({
			name: "id_archivo",
			value: id_plantilla,
		}).appendTo($fake_form);
	
		$(document.body).append($fake_form);
	
		$fake_form.submit();
	} else {
		alert("No se encuentra plantilla para el tipo de solicitante");
	}
}

function validarCampos() {
	skin_init_validar();

	if (SK_ERROR_VALIDACION) {
		return false;
	}

	return true;
}

function enviar() {
	if (validarSolicitud()) {
		osm_block_window();
		TOTAL_ARCHIVOS_ADJUNTOS = 0;
		ADJUNTOS_PENDIENTES = [];
		ADJUNTOS_FALLIDOS = [];
		let esSolicitudInicial = $("#contenido_archivos_subidos").data("solicitud-inicial");
		$("#formulario_completo")
		.find(".formulario_archivo")
		.each(
			function (index, value) {
				if ($(value).find("#caja_archivo_adjunto_form").first()[0].files[0] != undefined) {
					ADJUNTOS_PENDIENTES.push(value);
					TOTAL_ARCHIVOS_ADJUNTOS++;
				}
			});
		let totalyadicionales = TOTAL_ARCHIVOS_ADJUNTOS;
		if(esSolicitudInicial){
			totalyadicionales += CANT_ARCHIVOS_ADJUNTOS
		}
		$("#total_adjuntos").text(totalyadicionales);
		
		mostrarModalAdjuntos();
		
		setTimeout(function() {
			enviarSolicitud();
		}, 500);
	}
}

function enviarSolicitud() {
	
	prepararFormulario();
	prepararCargueArchivos();
		
}

function crearSolicitud () {
	var solicitud = generarSolicitud();
	jsonrpc._("solicitudNearSociedadServicio.crearSolicitud")(
			manejarRespuestaSolicitud, solicitud);
}

function validarActivoPasivoPatrimonio() {
	return parseFloat(osm_getValor("informacion_financiera_activos")) != (parseFloat(osm_getValor("informacion_financiera_pasivos")) + parseFloat(osm_getValor("informacion_financiera_patrimonio")));
}

function manejarRespuestaSolicitud(respuesta) {
	console.log(respuesta);
	if (respuesta && !respuesta.exitoso) {
		$("#formulario_completo").show();
		$("#div_exito").hide();
		$("#div_error").show();
		$("#boton_enviar").show();
		$("#boton_volver").hide();
	} else {
		jsonrpc._("generadorFormularioServicio.generarFormulario")(osm_getValorEntero("id_carga"));
		$("#formulario_completo").hide();
		$("#div_exito").show();
		$("#div_error").hide();
		$("#boton_volver").show();
		$("#boton_enviar").hide();
		validar_redireccion();
	}
	osm_unblock_window();
	osm_ocultarLoader();
}

function ver_ayuda_campo(ref_formato_campo) {

	try {
		var ico_obj = osm_getObjeto(ref_formato_campo + ".ico_ayuda");

		$(".area_ayuda").hide();

		if (ico_obj == null) {
			$(".ico_ayuda").fadeTo(200, 0.7);
		} else {
			$(".ico_ayuda").not(ico_obj).fadeTo(200, 0.7);
			$(ico_obj).fadeTo(200, 1);
			$(osm_getObjeto(ref_formato_campo + ".area_ayuda")).show();
		}
	} catch (e) {
	}

}
function ocultar_ayuda_campo () {
	$(".area_ayuda").hide();
}

$(function() {
	$(".ico_ayuda").fadeTo(200, 0.7);
});

$(document)
	.ready(

		function () {
			osm_ocultarLoader();
			osm_unblock_window();

			

			var a = jsonrpc
				._(
					"solicitudNearSociedadServicio.obtenerListaNaturalezaSociedad")
				().list;

			a.forEach(function (i) {
				$("#deudor_naturaleza_div")
					.append(
						$(
							"<div>",
							{
								class: "l-button s-buttonenable-primary "
									+ i.nombre,
								onclick: "setButtonValue('deudor_naturaleza',"
									+ i.id
									+ ", '"
									+ i.nombre
									+ "');actualizarNaturaleza()",
								text: i.nombre,
							}));
			});

			var situacion = jsonrpc
				._(
					"solicitudNearSociedadServicio.obtenerListaSituacionPresentada")
				().list;
			
				var tpsol = $("#tipos_solicitud").val();
			situacion.forEach(function (i) {
				var str = i.nombre+"";
				if( !(tpsol==2 && str.includes('minente')) ){ // si no es pnc y no incluye inminente
					$("#situacion_presentada").append($("<option>", {
						value: i.id,
						text: i.nombre,
					}));
				}
			});
			
			// obtenerListaAdelAcreedores
			var lista_negociacion_por = jsonrpc
			._(
				"solicitudNearSociedadServicio.obtenerListaAdelAcreedores")
			();
		
			if (lista_negociacion_por && lista_negociacion_por.list){
				lista_negociacion_por.list.forEach(function (i) {
					$("#negociacion_se_adelanta_por").append(
							$("<option>", {
								value: i.id,
								text: i.nombre,
							}));
				});				
			}

			var representantes = jsonrpc
				._(
					"solicitudNearSociedadServicio.obtenerListaRepresentanteLegal")
				().list;

			representantes.forEach(function (i) {
				$("#representante_legal").append($("<option>", {
					value: i.id,
					text: i.nombre,
				}));
			});

			var contadores = jsonrpc
				._(
					"solicitudNearSociedadServicio.obtenerListaContador")
				();

			if (contadores.list) {
				contadores.list.forEach(function (i) {
					$("#contador").append($("<option>", {
						value: i.id,
						text: i.nombre,
					}));
				});
			}

			var revisores = jsonrpc
				._(
					"solicitudNearSociedadServicio.obtenerListaRevisor")
				().list;

			revisores.forEach(function (i) {
				$("#revisor_fiscal").append($("<option>", {
					value: i.id,
					text: i.nombre,
				}));
			});

			var apoderados = jsonrpc
				._(
					"solicitudNearSociedadServicio.obtenerListaApoderado")
				().list;

			apoderados.forEach(function (i) {

				// setButtonOptions(i, "apoderado");
				$("#apoderado").append($("<option>", {
					value: i.id,
					text: i.nombre,
					onclick: "hideAppendYesOrNo('apoderado ',"
						+ i.id
						+ ");",
				}));
			});

			var deudor = jsonrpc._(
				"solicitudNearSociedadServicio.obtenerInfoCliente")
				();

			info_enrolamiento = jsonrpc._(
				"solicitudNearSociedadServicio.obtenerInfoClienteEnrolamiento")
				();
			
			var depto_ciudad = null;
			
			consultarDepartamentos();
			console.log("Cargando departamentos...");

			// ----------------------------------------------------------------------
			// LÓGICA DE PRECARGA (INICIALIZACIÓN)
			// ----------------------------------------------------------------------
			var _precargaCompleta = false;

			if (info_enrolamiento && info_enrolamiento.departamento_obj) {
			    console.log("info_enrolamiento detectado:", info_enrolamiento);

			    // Pre-cargar departamento
			    osm_setValor("deudor_departamento", info_enrolamiento.departamento_obj.id);
			    osm_setValor("deudor_departamento_autocomplete", info_enrolamiento.departamento_obj.nombre_departamento);

			    // Cargar ciudades inmediatamente
			    consultarCiudades();

			    // Habilitar selects
			    $("#deudor_ciudad").prop("disabled", false);
			    $("#deudor_ciudad_autocomplete").prop("disabled", false);

			    // Reaplicar municipio después de que se carguen las ciudades
			    setTimeout(function () {
			        if (info_enrolamiento.municipio_obj && info_enrolamiento.municipio_obj.id) {
			            osm_setValor("deudor_ciudad", info_enrolamiento.municipio_obj.id);
			            osm_setValor("deudor_ciudad_autocomplete", info_enrolamiento.municipio_obj.nombre_ciudad);
			            $("#deudor_ciudad").trigger("change");
			            console.log("Municipio seteado:", info_enrolamiento.municipio_obj.id, info_enrolamiento.municipio_obj.nombre_ciudad);
			        }
			        _precargaCompleta = true;
			    }, 500);
			} else {
			    _precargaCompleta = true;
			}

			// ----------------------------------------------------------------------
			// MANEJADOR DE AUTOCOMPLETE DE DEPARTAMENTO
			// ----------------------------------------------------------------------
			var _cambiandoDepartamento = false;

			$("#deudor_departamento_autocomplete").on("change blur", function () {
			    if (_cambiandoDepartamento) return;
			    var nombreDepartamento = $(this).val().trim();
			    var idSeleccionado = $('#deudor_departamento option').filter(function () {
			        return $(this).text().trim() === nombreDepartamento;
			    }).val();

			    if (idSeleccionado && idSeleccionado !== $("#deudor_departamento").val()) {
			        $("#deudor_departamento").val(idSeleccionado).trigger("change");
			    }
			});

			$("#deudor_departamento").on("change", function () {
			    if (_cambiandoDepartamento) return;
			    _cambiandoDepartamento = true;

			    var nuevoDepartamento = $(this).val();
			    console.log("Departamento cambiado:", nuevoDepartamento);

			    // Resetear flag para que consultarCiudades recree el autocomplete
			    $("#deudor_ciudad").data("autocomplete-init", false);

			    try {
			        consultarCiudades();
			    } catch (err) {
			        console.error("Error llamando a consultarCiudades:", err);
			    }

			    setTimeout(function () {
			        var $inputAC = $("#deudor_ciudad_autocomplete");
			        if ($inputAC.length) {
			            $inputAC.val("");
			            $inputAC.prop("disabled", false);
			        }
			        $("#deudor_ciudad").prop("disabled", false);
			        _cambiandoDepartamento = false;
			    }, 300);
			});

			// Manejo de autocomplete dinamico
			$(document).on("change blur", "#deudor_ciudad + input", function () {
			    const nombreCiudad = $(this).val().trim();
			    const $sel = $("#deudor_ciudad");

			    const idSeleccionado = $sel.find("option").filter(function () {
			        return $(this).text().trim() === nombreCiudad;
			    }).val();

			    if (idSeleccionado) {
			        $sel.val(idSeleccionado).trigger("change");
			        const scope = angular.element($sel).scope();
			        if (scope && scope.$applyAsync) {
			            scope.$applyAsync(() => { scope.solicitud.ciudad = idSeleccionado; });
			        }
			        console.log("? Ciudad seleccionada desde autocomplete:", idSeleccionado, nombreCiudad);
			    } else {
			        $sel.val("").trigger("change");
			        console.log("? Ciudad no encontrada en la lista.");
			    }
			});
			
			var tipo_identificacion = jsonrpc._(
			"solicitudNearSociedadServicio.obtenerNombreTipoDocumento")(info_enrolamiento.tipo_identificacion);
			$("#tipo_identificacion").val(tipo_identificacion);

			$("#deudor_nit").val(deudor.identificacion);

			$("#deudor_razon_social").val(deudor.nombreCompleto);

			
			$("#deudor_direccion").val(info_enrolamiento.direccion);
			$("#deudor_correo").val(deudor.correo);
			$("#deudor_telefono").val(info_enrolamiento.datos_representante.celular);

			var grupos_NIIF = jsonrpc._(

				"solicitudNearSociedadServicio.obtenerGruposNIF")().list;
				grupos_NIIF.forEach(function (i) {
				$("#tipo_solicitud_niif").append($("<option>", {
					value: i.id,
					text: i.nombre,
				}));
			});

			var codigos_ciiu = jsonrpc._(
				"solicitudNearSociedadServicio.obtenerCodigosCIIU")
				().list;

			codigos_ciiu.forEach(function (i) {
				var nombre_tag = "";

				if (i.nombre.length > 70) {
					nombre_tag = i.nombre.slice(0, 67)	+ "...";
				} else {
					nombre_tag = i.nombre;
				}

				$("#deudor_ciiu").append($("<option>", {
					value: i.id,
					text: nombre_tag,
				}));
			});

			$("#deudor_ciiu").each(function (i) {
				var input = selectToAutoComplete(this);
			});

			$("#deudor_ciiu_autocomplete").removeClass();
			$("#deudor_ciiu_autocomplete").addClass(
				"input-default form-input");

			var macrosectores = jsonrpc
				._(
					"solicitudNearSociedadServicio.obtenerListaMacrosector")
				().list;

			macrosectores.forEach(function (i) {
				$("#deudor_macrosector").append($("<option>", {
					value: i.id,
					text: i.nombre,
				}));
			});
			// /////////////////////////////////////////////////////////////////////////

			var sociedad_deudora = jsonrpc._(
				"solicitudNearSociedadServicio.obtenerSociedad")();

			if (sociedad_deudora != undefined
				&& sociedad_deudora != null) {
				// console.log(sociedad_deudora);
				if (sociedad_deudora.codigo_ciiu != null) {
					osm_setValor("deudor_ciiu", sociedad_deudora.ciiu);
				}

				if (sociedad_deudora.macrosector != null) {
					osm_setValor("deudor_macrosector",
						sociedad_deudora.macrosector);
				}
			}

			// valores para los nombres de archivos adjuntos

			// comportamiento para selectores de archivos
			manageDomForSelectorFile("archivo_memoria_explicativa");
			manageDomForSelectorFile("archivo_pasivos_mes");
			manageDomForSelectorFile("archivo_calculo");
			manageDomForSelectorFile("archivo_existencia_inexistencia_pasivos_pensionales");
			manageDomForSelectorFile("archivo_retenciones_descuentos_aportes");
			manageDomForSelectorFile("archivo_plan_atencion_pasivos");
			manageDomForSelectorFile("archivo_inventario");
			manageDomForSelectorFile("archivo_estados_mes");
			manageDomForSelectorFile("archivo_nota_estados_mes");
			manageDomForSelectorFile("archivo_cr");
			manageDomForSelectorFile("archivo_causal_disolucion");
			manageDomForSelectorFile("archivo_dictamen_revisor_fiscal");
			manageDomForSelectorFile("archivo_certificado_existencia");
			manageDomForSelectorFile("archivo_estados_an");
			manageDomForSelectorFile("archivo_nota_estados_an");
			manageDomForSelectorFile("archivo_estados_pan");
			manageDomForSelectorFile("archivo_nota_estados_pan");
			manageDomForSelectorFile("archivo_estados_apan");
			manageDomForSelectorFile("archivo_nota_estados_apan");
			manageDomForSelectorFile("archivo_capacidad_rp");
			manageDomForSelectorFile("archivo_soporte_capacidad_rp");
			manageDomForSelectorFile("archivo_poder_abogado");
			manageDomForSelectorFile("archivo_flujo");
			manageDomForSelectorFile("archivo_pry");
			manageDomForSelectorFile("archivo_plan_negocios");
			manageDomForSelectorFile("archivo_avaluo");
			manageDomForSelectorFile("archivo_avaluo_pnnoc");
			
			manageDomForSelectorFile("archivo_rel_pas");
			manageDomForSelectorFile("archivo_rel_pas_pnnoc");
			
			// inicio archivos adicionales para personas naturales
			manageDomForSelectorFile("archivo_informe");
			manageDomForSelectorFile("archivo_inventario_bienes_solicitante");
			manageDomForSelectorFile("archivo_certificacion_procesos_judiciales");
			manageDomForSelectorFile("archivo_renta_anio_anterior");
			manageDomForSelectorFile("archivo_declaracion_ingresos_deudor");
			manageDomForSelectorFile("archivo_certificacion_ingresos_disponibles");
			manageDomForSelectorFile("archivo_certificacion_sociedad_conyugal_patrimonial");
			manageDomForSelectorFile("archivo_certificacion_obligaciones_alimentarias");
			manageDomForSelectorFile("archivo_certificado_controlante_sociedad");
			
			manageDomForSelectorFile("certificacion_garantia_mobiliaria");
			manageDomForSelectorFile("certificacion_garantia_mobiliaria_pnnoc");

			manageDomForSelectorFile("certificacion_garante_codeudor");
			manageDomForSelectorFile("archivo_certificacion_calculo_actuarial");
			manageDomForSelectorFile("archivo_certificacion_calculo_actuarial_pnnoc");

			// manageDomForSelectorFile("certificacion_calculo_actuarial");
			manageDomForSelectorFile("documento_calculo_actuarial");
			manageDomForSelectorFile("documento_calculo_actuarial_pnnoc");
			
			manageDomForSelectorFile("certificacion_mesadas_al_dia");
			manageDomForSelectorFile("certificacion_mesadas_al_dia_pnnoc");
			

			manageDomForSelectorFile("archivo_no_causal_disolucion");
			manageDomForSelectorFile("archivo_aprobacion_calculo_actuarial");
			manageDomForSelectorFile("archivo_aprobacion_calculo_actuarial_pnnoc");

			manageDomForSelectorFile("archivo_inventario_pasivos");
			manageDomForSelectorFile("archivo_inventario_activos");

			manageDomForSelectorFile("archivo_dictamen_revisorf_apan");
			manageDomForSelectorFile("archivo_dictamen_revisorf_pan");
			
			manageDomForSelectorFile("archivo_dictamen_revisorf_an");

			manageDomForSelectorFile("archivo_plan_seguridad_social");
			manageDomForSelectorFile("archivo_retenciones_seguridad_social");

			manageDomForSelectorFile("archivo_retenciones_trabajadores");
			manageDomForSelectorFile("archivo_plan_trabajadores");
			
			manageDomForSelectorFile("archivo_retenciones_fisco");
			manageDomForSelectorFile("archivo_plan_fisco");

			manageDomForSelectorFile("archivo_certificado_incapacidad_pago");
			manageDomForSelectorFile("archivo_anexo_cesacion_pagos");
			
			// manageDomForSelectorFile("archivo_certificado_cesacion_pagos");
			manageDomForSelectorFile("archivo_certificado_cesacion_pagos");
			
			manageDomForSelectorFile("propuesta_negociacion_deudas");
			manageDomForSelectorFile("archivo_memoria_explicativa");
			manageDomForSelectorFile("archivo_pry_pnoc");
			manageDomForSelectorFile("archivo_composicion_sociedad_controlada");
			manageDomForSelectorFile("archivo_prueba_controlante_soc_admitida");
			manageDomForSelectorFile("detalle_bienes");
			manageDomForSelectorFile("detalle_bienes_pnnoc");
			manageDomForSelectorFile("detalle_bienes_a");
			
			manageDomForSelectorFile("relacion_acreedores");
			manageDomForSelectorFile("relacion_acreedores_pnnoc");
			manageDomForSelectorFile("certificacion_procesos_judiciales_administrativos");
			manageDomForSelectorFile("certificacion_ingresos_deudor_juramentada");
			manageDomForSelectorFile("recursos_disponibles_pago_obligaciones");
			manageDomForSelectorFile("certificacion_solteria");
			manageDomForSelectorFile("certificacion_demanda_alimentos");
			manageDomForSelectorFile("renta_anio_anterior");
			manageDomForSelectorFile("certificado_representacion_legal_pnnoc");
			
			manageDomForSelectorFile("archivo_maximo_organo_autoriza_replegal");
			manageDomForSelectorFile("archivo_composicion_accionaria");
			// fin archivos adicionales para personas naturales
			// fin valores para los nombres de archivos adjuntos

			// llenar nuevos campos

			var tipos_solicitud = jsonrpc
				._(
					"solicitudNearSociedadServicio.obtenerTiposSolicitud")
				().list;

			tipos_solicitud.forEach(function (i) {
				$("#tipo_solicitud_near").append($("<option>", {
					value: i.id,
					text: i.nombre,
				}));
			});

			osm_setValor("tipo_solicitud_near", 1);

			// SE AGREGA TIPO SOLICITANTE
			var tipo_solicitante = jsonrpc
				._(
					"solicitudNearSociedadServicio.obtenerNombreTipoSolicitante")
				(parseInt(osm_getValor("tipos_solicitud"), 10));

			//					if (tipo_solicitante != undefined
			//							&& tipo_solicitante != null) {
			//						$("#tipo_solicitante_near").val(tipo_solicitante);
			//					} else {
			//						$("#tipo_solicitante_near").val("Valor no definido...");
			//					}
			osm_setValor("tipo_solicitante_near", tipo_solicitante);

			//					$("#tipo_solicitante_near").append($("<option>", {
			//						value : tipo_solicitante,
			//						text : tipo_solicitante,
			//					}));

			var paises = jsonrpc._("solicitudEnrolamiento.obtenerPaises")().list;

			paises.forEach(function (i) {
				$("#deudor_pais").append($("<option>", {
					value: i.id,
					text: i.nombre,
				}));
			});
			$("#deudor_pais").val(info_enrolamiento.pais);

			var normas_aplicables = jsonrpc
				._(
					"solicitudNearSociedadServicio.obtenerNormasAplicables")
				().list;

			normas_aplicables.forEach(function (i) {
				$("#norma_aplicable").append($("<option>", {
					value: i.id,
					text: i.nombre,
				}));
			});

			osm_setValor("norma_aplicable", 1);

			var sino = jsonrpc._(
				"solicitudNearSociedadServicio.obtenerListaSiNO")().list;

			sino
				.forEach(function (i) {
					setButtonOptions(i, "relacion_pasivos_retenciones_autoridades_fiscales");
					setButtonOptions(i, "relacion_pasivos_descuentos_trabajadores");
					setButtonOptions(i, "relacion_pasivos_aportes_seguridad_social");
					setButtonOptions(i, "sociedad_inversiones");
					setButtonOptions(i, "garantias_mobiliarias");
					setButtonOptions(i, "garantias_mobiliariasa");
					setButtonOptions(i, "sociedad_garante_codeudor");
					setButtonOptions(i, "sociedad_pasivos_pensionales");
					setButtonOptions(i, "pnnoc_pasivos_pensionales");
					setButtonOptions(i, "sociedad_en_disolucion", 1);
					setButtonOptions(i, "remision_previa_informacion", 1);
					setButtonOptions(i, "controlante_sociedad_en_reorganizacion");
					setButtonOptions(i, "replegal_tiene_limitacion");
				});

			setTipoSolicitud();
			
			
		});


function actualizarNaturaleza() {
	if (osm_getValor("deudor_naturaleza") == 1) {
		$("#div_porcentaje_p").show();
	} else {
		$("#div_porcentaje_p").hide();
	}
}



function move() {
	var elem = document.getElementById("barra_contenido");
	var width = 0;
	// var id = setInterval(frame, 50);
	// function frame() {
	// if (ARCHIVOS_FINALIZADOS >= TOTAL_ARCHIVOS_ADJUNTOS) {
	// clearInterval(id);
	// // document.getElementById("textBar").className = "w3-text-green
	// // w3-animate-opacity";
	// // document.getElementById("textBar").innerHTML = "Carga exitosa!";
	// } else {
	// elem.style.width = (ARCHIVOS_FINALIZADOS * 100 / TOTAL_ARCHIVOS_ADJUNTOS
	// ) + '%';
	// console.log("ARCHIVOS_FINALIZADOS " + ARCHIVOS_FINALIZADOS + "
	// ARCHIVOS_FINALIZADOS " + ARCHIVOS_FINALIZADOS)
	// if(document.getElementById("archivos_adjuntados"))
	// document.getElementById("archivos_adjuntados").innerHTML =
	// ARCHIVOS_FINALIZADOS;
	// }
	// }
	// frame();
}
function fin_carga_archivo() {

	var elem = document.getElementById("barra_contenido");
	ARCHIVOS_FINALIZADOS++;
	if (ARCHIVOS_FINALIZADOS >= TOTAL_ARCHIVOS_ADJUNTOS) {
		cerrarModalAdjuntos();
		generarPdfFormulario();
	} else {
		var num = ARCHIVOS_FINALIZADOS * 100 / TOTAL_ARCHIVOS_ADJUNTOS;
		num = num.toFixed(0)
		elem.style.width = num + '%';
		document.getElementById("archivos_adjuntados").innerHTML = ARCHIVOS_FINALIZADOS;
	}

}

function generarPdfFormulario() {
	osm_block_window();
	osm_verLoader();
//	alert("Archivos cargados se procede a crear pdf. Esto puede durar unos minutos, por favor espere...");
	mostrarFormulario();
	var capturar_pdf = jsonrpc._("configuracionServicio.obtenerValorByEtiqueta")("CAPTURAR_FORMULARIO");
	if ( capturar_pdf && capturar_pdf === "true") {
		capturarFormulario("formulario_carga", ".input-caja", crearSolicitud);			
	} else {
		crearSolicitud();
	}
}

function mostrarFormulario() {
	$("#formulario_completo").show();
	$("#div_exito").hide();
	$("#div_error").show();
	$("#boton_enviar").show();
	$("#boton_volver").hide();
	$("#barra_contenido").hide();
}

function mostrarModalPdf(){
	$("#msg_modal_pdf").show();
}

function cerrarModalPdf(){
	$("#msg_modal_pdf").hide();
}

function mostrarModalAdjuntos(){
	$("#msg_modal_adjuntos").show();
}

function cerrarModalAdjuntos(){
	$("#msg_modal_adjuntos").hide();
}

function mostrarModalErrorAdjuntos(){
	$("#msg_modal_error_adjuntos").show();
}

function cerrarModalErrorAdjuntos(){
	$("#msg_modal_error_adjuntos").hide();
}

function mostrarModalCancelacionSolicitud() {
	$("#msg_modal_cancelar_solicitud").show();
}

function cerrarModalCancelacionSolicitud() {
	$("#msg_modal_cancelar_solicitud").hide();
}

function mostrarModalDescartarSolicitud() {
	cerrarModalErrorAdjuntos();
	$("#msg_modal_descartar_solicitud").show();
}

function cerrarModalDescartarSolicitud() {
	$("#msg_modal_descartar_solicitud").hide();
	mostrarModalErrorAdjuntos();
}

function finalizarCarga() {
	ocultarFormulario();
	mostrarCargaExitosa();
}

function ocultarFormulario() {
	$("#formulario_completo").hide();
	$("#div_exito").show();
	$("#div_error").hide();
	$("#boton_volver").show();
	$("#boton_enviar").hide();
	$("#div_exito").hide();
	$("#barra_contenido").show();
	osm_unblock_window();
	osm_ocultarLoader();
}

function mostrarCargaExitosa() {
	var elem = document.getElementById("barra_contenido");
	document.getElementById("textBar").innerHTML = "Carga exitosa!";
	$("#div_exito").show();
	elem.style.width = '100%';
	var idcarga = $("#id_carga").val();
}

function prepararCargueArchivos() {
	
	ARCHIVOS_ADJUNTADOS = 0;
	SIGUIENTE_ADJUNTO = -1;
	
	var elem = document.getElementById("barra_contenido");
	elem.style.width = '0%';

	subirArchivos();
}

function subirArchivos() {
	var options = {
			success: subirArchivos,
			error: errorCargueArchivos,
			async: true,
		};
	
	SIGUIENTE_ADJUNTO++;
	
	var elem = document.getElementById("barra_contenido");
	if (SIGUIENTE_ADJUNTO >= TOTAL_ARCHIVOS_ADJUNTOS) {
		validarCargueArchivosExitoso();
	} else {
		var num = SIGUIENTE_ADJUNTO * 100 / TOTAL_ARCHIVOS_ADJUNTOS;
		num = num.toFixed(0)
		elem.style.width = num + '%';
		document.getElementById("archivos_adjuntados").innerHTML = SIGUIENTE_ADJUNTO;
		
		var value = ADJUNTOS_PENDIENTES[SIGUIENTE_ADJUNTO];
		
		if (validarS3Adjuntos()) {
			prepararAdjuntoS3(value, subirArchivos, errorCargueArchivos, "caja_archivo_adjunto_form", "descripcion_archivo_adj_form", "id_tipo_archivo_form");
		} else {
			var descripcion = $(value).find("#descripcion_archivo_adj_form").val();
			var encodedDescripcion = encodeURIComponent(descripcion);
			$(value).find("#descripcion_archivo_adj_form").val(encodedDescripcion);
			$(value).ajaxSubmit(options);	
			$(value).find("#descripcion_archivo_adj_form").val(descripcion);
		}
		
	}
	
	
}

function validarCargueArchivosExitoso() {
	ADJUNTOS_FALLIDOS = [];
	var element = document.getElementById("lista_error_adjuntos");
	element.innerHTML = '';
	for (var i = 0; i < ADJUNTOS_PENDIENTES.length; i++) {
		var form = ADJUNTOS_PENDIENTES[i];
		var id_archivo_adjunto = $(form).find("#id_archivo_adjunto").first()[0].value;
		if (id_archivo_adjunto) {
			var validacion = null
			try {
				validacion = jsonrpc._("archivosAdjJsonServicio.obtenerArchivoAdjunto")(id_archivo_adjunto);					
			} catch (e) {
				console.log(e);
				i--;
				continue;
			}
			if(!validacion) {
				var archivo_errado = $(form).find("#caja_archivo_adjunto_form").first()[0].files[0];
				var tag_item = document.createElement("li");
				var size = archivo_errado.size/1024/1024;
				size = size.toFixed(2);
				var texto_archivo = document.createTextNode(archivo_errado.name + " - " + size + " MB");
				tag_item.appendChild(texto_archivo);
				
				element.appendChild(tag_item);
				
				ADJUNTOS_FALLIDOS.push(form);
			}
		}
	}
	cerrarModalAdjuntos();
	if (ADJUNTOS_FALLIDOS.length > 0) {
		mostrarModalErrorAdjuntos();
	} else {
		generarPdfFormulario();	
	}
}

function reintentarArchivosFallidos() {
	ADJUNTOS_PENDIENTES = ADJUNTOS_FALLIDOS;
	TOTAL_ARCHIVOS_ADJUNTOS = ADJUNTOS_FALLIDOS.length;
	$("#total_adjuntos").text(TOTAL_ARCHIVOS_ADJUNTOS);
	$("#archivos_adjuntados").text(0);
	SIGUIENTE_ADJUNTO = -1;
	
	cerrarModalErrorAdjuntos();
	mostrarModalAdjuntos();
	prepararCargueArchivos();
}

function validarCargueArchivos() {
	cerrarModalAdjuntos();

	if (ADJUNTOS_FALLIDOS.length > 0) {
		var no_encontrados = 0;
		var adjuntos_notificacion = [];
		for (var i = 0; i < ADJUNTOS_FALLIDOS.length; i++) {
			var form = ADJUNTOS_FALLIDOS[i];
			
			var id = $(form).find("#id_archivo_adjunto").first()[0].value;
			
			var validacion = null;
			
			if (id) {
				try {
					validacion = jsonrpc._("archivosAdjJsonServicio.obtenerArchivoAdjunto")(id);					
				} catch (e) {
					console.log(e);
					i--;
					continue;
				}
			}
			
			if(!validacion) {
				var archivo_errado = $(form).find("#caja_archivo_adjunto_form").first()[0].files[0];
				var tag_item = document.createElement("li");
				var size = archivo_errado.size/1024/1024;
				size = size.toFixed(2);
				var texto_archivo = document.createTextNode(archivo_errado.name + " - " + size + " MB");
				tag_item.appendChild(texto_archivo);
				var element = document.getElementById("lista_error_adjuntos");
				element.appendChild(tag_item);
				no_encontrados++;
				
				adjuntos_notificacion.push(archivo_errado.name);
			}
		}
		
		if (no_encontrados > 0) {
			var idcarga = $("#id_carga").val();
			jsonrpc._("notificacionMIServicio.notificarErrorCargueArchivos")(idcarga, adjuntos_notificacion);
			mostrarModalErrorAdjuntos();
			return;
		}
		
	}
	
	generarPdfFormulario();		
	
}

function errorCargueArchivos() {
	var form_error = ADJUNTOS_PENDIENTES[SIGUIENTE_ADJUNTO];
	var adjunto = $(form_error).find("#caja_archivo_adjunto_form").first()[0].files[0];
	console.log("Error cargue archivo " + adjunto.name + " - " + adjunto.size);
	ADJUNTOS_FALLIDOS.push(form_error);
	subirArchivos();
}

function prepararFormulario() {
	var id_carga = osm_getValorEntero("id_carga");
	
	$("#formulario_completo")
		.find(".formulario_archivo")
		.each(
			function (index, value) {
				var id_archivo_adjunto = jsonrpc._(
					"archivosAdjJsonServicio.obtenerSiguiente")();

				$(value).append(
					'<input type="hidden" id="id_carga" name="id_carga" value="'
					+ id_carga + '"/>');
				$(value)
					.append(
						'<input type="hidden" id="id_archivo_adjunto" name="id_archivo_adjunto" value="'
						+ id_archivo_adjunto + '"/>');
				$(value)
					.append(
						'<input type="hidden" id="id_instancia" name="id_instancia" value=""/>');
			});
}

function validarValorNumerico(campo){
	
	var textareasin = osm_getObjeto("" + campo);
	var valor = osm_getValor("" + campo);
	
	if(isNaN(valor)){
		osm_setValor("" + campo, "Error. Ingrese N\u00fameros ");
//		osm_setFoco("" + campo)
	}
}

function mostrarDoubleConFormato(formatoDato, formatoSin) {
	// Se oculta el campo
	var textareasin = osm_getObjeto("" + formatoSin);
	$(textareasin).hide();

	// Se muestra el div con el dato formateado
	var valor = osm_getValor("" + formatoSin);

	if (osm_esVacio(valor)){
		$(textareasin).show();
		return;
	}else if (!isNaN(valor)) {
		valor = osm_formatoMoneda(valor);

		osm_setValor("" + formatoDato, valor);
	} else {
		osm_setValor("" + formatoDato, "Error");
	}

	var textarea = osm_getObjeto("" + formatoDato);
	$(textarea).show();
}

function mostrarDoubleSinFormato(formatoDato, formatoDatoSin) {
	// Se oculta el div con formato
	var div = osm_getObjeto("" + formatoDato);
	$(div).hide();

	// Se muestra el campo con el valor sin formato
	var div = osm_getObjeto("" + formatoDatoSin);
	$(div).show();

	// Se obtiene el foco
	osm_setFoco("" + formatoDatoSin);
}

function mostrarPorcentajeConFormato(formatoDato, formatoSin) {
	// Se oculta el campo
	var textareasin = osm_getObjeto("" + formatoSin);
	$(textareasin).hide();

	// Se muestra el div con el dato formateado
	var valor = osm_getValor("" + formatoSin);

	if (valor && valor.includes("%")) {
		valor = valor.replace("%","");
		osm_setValor("" + formatoSin, valor);
	}
	
	if (valor) {
		
		if (!isNaN(valor)) {
			valor = valor + "%";
	
			osm_setValor("" + formatoDato, valor);
		} else {
			osm_setValor("" + formatoDato, "Error");
		}
	
		var textarea = osm_getObjeto("" + formatoDato);
		$(textarea).show();
	} else {
		$(textareasin).show();
	}
}

function mostrarProcentajeSinFormato(formatoDato, formatoDatoSin) {
	// Se oculta el div con formato
	var div = osm_getObjeto("" + formatoDato);
	$(div).hide();

	// Se muestra el campo con el valor sin formato
	var div = osm_getObjeto("" + formatoDatoSin);
	$(div).show();

	// Se obtiene el foco
	osm_setFoco("" + formatoDatoSin);
}

function validarCambioNaturaleza() {
	if (osm_getValor("deudor_naturaleza") == "1") {
		$("#participacion_estatal_label").show();
		$("#participacion_estatal_input").show();
	} else {
		osm_setValor("deudor_porcentaje_estatal", "");
		osm_setValor("deudor_porcentaje_estatal_formato", "");
		$("#participacion_estatal_label").hide();
		$("#participacion_estatal_input").hide();
	}
}
function mostrarOpcionesadmisibilidad() {
	if (osm_getValor("situacion_presentada")!== "" && osm_getValor("situacion_presentada") !== null) {
		$("#contenedor_situaciones").show();
		if (osm_getValor("situacion_presentada")==1){
			$("#contenedor_Cesacion").show();
			$("#contenedor_Incapacidad").hide();
		} 
		if (osm_getValor("situacion_presentada")==2){
			$("#contenedor_Cesacion").hide();
			$("#contenedor_Incapacidad").show();
		} 
	} else {
		$("#contenedor_apoderado").hide();
	}
}
function mostrarPoderAbogado() {
	if (osm_getValor("apoderado") !== "" && osm_getValor("apoderado") !== null) {
		$("#contenedor_apoderado").show();
	} else {
		$("#contenedor_apoderado").hide();
	}
}


function removeFile(id_file) {
	$("#" + id_file).find("#caja_archivo_adjunto_form").val(null);
	$("#trash_" + id_file).addClass("ic-hide");
	osm_setValor("label_" + id_file, "");
}
function cerrarModal() {
	$("#msg_modal").hide();
}

function getActualYear() {
	var d = new Date();
	var n = d.getFullYear();
	return n;
}
function getUltimoAnho(){
	return getActualYear() - 1;
}
function getPenultimoAnho(){
	return getActualYear() - 2;
}
function getAntepenultimoAnho(){
	return getActualYear() - 3;
}

function docWrite(anything) {
    document.write(anything);
}
