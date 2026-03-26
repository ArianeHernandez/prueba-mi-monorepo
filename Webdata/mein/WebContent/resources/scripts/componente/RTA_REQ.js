var rta_req = null;

$(function(){
	var obj_carga = osm_getObjeto("FormatoDato.formatoDatoList:1.valor");
	if (obj_carga) {
		document.getElementById('FormatoDato.formatoDatoList:1.valor').onchange = function() {
			osm_block_window();
			osm_verLoader();
			var carga = osm_getObjeto("FormatoDato.formatoDatoList:1.valor");
			var req = new Object();
			
			if (carga) {
				req.value = carga.value;
				req.realvalue = carga.realvalue;
			}
			
			
			if (CANT_ARCHIVOS_ADJUNTOS > 0) {
				osm_unblock_window();
				s_accept("Respuesta Requerimiento", "Señor usuario, el cambio de Número de Solicitud borrará los archivos cargados ¿Está seguro de continuar?.", "Aceptar", 
					function(resp){
						if (resp) {
							limpiarAdjuntosFormulario(resp);									
							if(req.value) {
								osm_block_window();
								osm_verLoader();
								jsonrpc._("respuestaRequerimientoServicio.obtenerInformacionSolicitudPadre")(llenarFormulario, req.realvalue);
							}
						} else {
							var numero_solicitud = osm_getObjeto("FormatoDato.formatoDatoList:1.valor");
							numero_solicitud.value = rta_req.numero_solicitud_formato;
							numero_solicitud.realvalue = rta_req.numero_solicitud;								
							
						}
					}
				);
			} else {				
				limpiarFormulario();
				if(req.value) {
					osm_block_window();
					osm_verLoader();
					jsonrpc._("respuestaRequerimientoServicio.obtenerInformacionSolicitudPadre")(llenarFormulario, req.realvalue);
				}
			}
	
		};
	}
	
	var obj_proceso = osm_getObjeto("FormatoDato.formatoDatoList:2.valor");
	if (obj_proceso) {
		document.getElementById('FormatoDato.formatoDatoList:2.valor').onchange = function() {
			osm_block_window();
			osm_verLoader();
			var numero_proceso = osm_getObjeto("FormatoDato.formatoDatoList:2.valor");
			var req = new Object();
			
			if (numero_proceso) {
				req.value = numero_proceso.value;
				req.realvalue = numero_proceso.realvalue;
			}
			
			if (CANT_ARCHIVOS_ADJUNTOS > 0) {
				osm_unblock_window();
				s_accept("Respuesta Requerimiento", "Señor usuario, el cambio de Número de Proceso borrará los archivos cargados ¿Está seguro de continuar?.", "Aceptar", 
					function(resp){
						if (resp) {
							limpiarAdjuntosFormulario(resp);
							if (req.value) {
								osm_block_window();
								osm_verLoader();
								jsonrpc._("respuestaRequerimientoServicio.obtenerSolicitudPorNumeroProceso")(llenarFormulario, req.realvalue);
							}
						} else {
							var numero_proceso = osm_getObjeto("FormatoDato.formatoDatoList:2.valor");
							numero_proceso.value = rta_req.numero_proceso;;
							numero_proceso.realvalue = rta_req.numero_proceso;;
						}
					}
				);
			} else {				
				limpiarFormulario();
				if (req.value) {
					osm_block_window();
					osm_verLoader();
					jsonrpc._("respuestaRequerimientoServicio.obtenerSolicitudPorNumeroProceso")(llenarFormulario, req.realvalue);
				}
			}
			
		};
	}
	if (obj_carga && obj_proceso) {
		bloquearCampos();		
	}
	
});

function bloquearCampos () {
	var tipo_solicitante = document.getElementById("FormatoDato.formatoDatoList:3.valor");
	tipo_solicitante.readOnly  = true;
	var tipo_solicitud = document.getElementById("FormatoDato.formatoDatoList:4.valor");
	tipo_solicitud.readOnly  = true;
	var tipo_identificacion = document.getElementById("FormatoDato.formatoDatoList:6.formatoDatoList:1.valor");
	tipo_identificacion.readOnly  = true;
	var numero_identificacion = document.getElementById("FormatoDato.formatoDatoList:6.formatoDatoList:2.valor");
	numero_identificacion.readOnly  = true;
	var razon_social = document.getElementById("FormatoDato.formatoDatoList:6.formatoDatoList:3.valor");
	razon_social.readOnly  = true;
	var pais = document.getElementById("FormatoDato.formatoDatoList:6.formatoDatoList:7.valor");
	pais.readOnly  = true;
	var departamento = document.getElementById("FormatoDato.formatoDatoList:6.formatoDatoList:8.valor");
	departamento.readOnly  = true;
	var municipio = document.getElementById("FormatoDato.formatoDatoList:6.formatoDatoList:9.valor");
	municipio.readOnly  = true;
	var direccion = document.getElementById("FormatoDato.formatoDatoList:6.formatoDatoList:10.valor");
	direccion.readOnly  = true;
	var correo = document.getElementById("FormatoDato.formatoDatoList:6.formatoDatoList:11.valor");
	correo.readOnly  = true;
	var telefono = document.getElementById("FormatoDato.formatoDatoList:6.formatoDatoList:12.valor");
	telefono.readOnly  = true;
	var tramite = document.getElementById("FormatoDato.formatoDatoList:7.formatoDatoList:1.valor");
	tramite.readOnly  = true;
	
	$("input[type=text][readonly]").attr("style", "background-color: #eee !important; pointer-events:none");
	
	$("#select_tipo_archivo").prop("disabled","true");
	$('#btn_adjuntar_archivo').addClass('disabled');
	$('#btn_adjuntar_archivo').removeAttr('data-toggle');
}

function llenarFormulario (respuesta) {
	if (respuesta) {
		var numero_solicitud = osm_getObjeto("FormatoDato.formatoDatoList:1.valor");
		if (numero_solicitud && respuesta.numero_solicitud) {
			numero_solicitud.value = respuesta.numero_solicitud_formato;
			numero_solicitud.realvalue = respuesta.numero_solicitud;
		}
		
		var numero_proceso = osm_getObjeto("FormatoDato.formatoDatoList:2.valor");
		if (numero_proceso && respuesta.numero_proceso) {
			numero_proceso.value = respuesta.numero_proceso;
			numero_proceso.realvalue = respuesta.numero_proceso;
		}
		
		var tipo_solicitante = osm_getObjeto("FormatoDato.formatoDatoList:3.valor");
		if (tipo_solicitante && respuesta.tipo_solicitante_obj) {
			tipo_solicitante.value = respuesta.tipo_solicitante_obj.nombre;
			tipo_solicitante.realvalue = respuesta.tipo_solicitante_obj.id;
			$(tipo_solicitante).change();
		}
		
		var tipo_solicitud = osm_getObjeto("FormatoDato.formatoDatoList:4.valor");
		if (tipo_solicitud && respuesta.tipo_solicitud_obj) {
			tipo_solicitud.value = respuesta.tipo_solicitud_obj.nombre;
			tipo_solicitud.realvalue = respuesta.tipo_solicitud_obj.id;
			$(tipo_solicitud).change();
		}
		
		var tipo_identificacion = osm_getObjeto("FormatoDato.formatoDatoList:6.formatoDatoList:1.valor");
		if (tipo_identificacion && respuesta.deudor_obj && respuesta.deudor_obj.tipo_identificacion) {
			tipo_identificacion.value = respuesta.deudor_obj.tipo_identificacion.nombre;
			tipo_identificacion.realvalue = respuesta.deudor_obj.tipo_identificacion.cod_hts;
			$(tipo_identificacion).change();
		}
		
		var numero_identificacion = osm_getObjeto("FormatoDato.formatoDatoList:6.formatoDatoList:2.valor");
		if (numero_identificacion && respuesta.deudor_obj && respuesta.deudor_obj.numero_identificacion) {
			numero_identificacion.value = respuesta.deudor_obj.numero_identificacion;
			$(numero_identificacion).change();
		}
		
		var razon_social = osm_getObjeto("FormatoDato.formatoDatoList:6.formatoDatoList:3.valor");
		if (razon_social && respuesta.deudor_obj && respuesta.deudor_obj.razon_social) {
			razon_social.value = respuesta.deudor_obj.razon_social;
			$(razon_social).change();
		}
		
		var pais = osm_getObjeto("FormatoDato.formatoDatoList:6.formatoDatoList:7.valor");
		if (pais && respuesta.deudor_obj && respuesta.deudor_obj.pais_dane) {
			pais.value = respuesta.deudor_obj.pais_dane.nombre;
			pais.realvalue = respuesta.deudor_obj.pais_dane.id;
			$(pais).change();
		}
		
		var departamento = osm_getObjeto("FormatoDato.formatoDatoList:6.formatoDatoList:8.valor");
		if (departamento && respuesta.deudor_obj && respuesta.deudor_obj.departamento) {
			departamento.value = respuesta.deudor_obj.departamento.nombre_departamento;
			departamento.realvalue = respuesta.deudor_obj.departamento.id;
			$(departamento).change();
		}
		
		var municipio = osm_getObjeto("FormatoDato.formatoDatoList:6.formatoDatoList:9.valor");
		if (municipio && respuesta.deudor_obj && respuesta.deudor_obj.municipio) {
			municipio.value = respuesta.deudor_obj.municipio.nombre_ciudad;
			municipio.realvalue = respuesta.deudor_obj.municipio.id;
			$(municipio).change();
		}
		
		var direccion = osm_getObjeto("FormatoDato.formatoDatoList:6.formatoDatoList:10.valor");
		if (direccion && respuesta.deudor_obj && respuesta.deudor_obj.direccion) {
			direccion.value = respuesta.deudor_obj.direccion;
			$(direccion).change();
		}
		
		var correo = osm_getObjeto("FormatoDato.formatoDatoList:6.formatoDatoList:11.valor");
		if (correo && respuesta.deudor_obj && respuesta.deudor_obj.correo_electronico) {
			correo.value = respuesta.deudor_obj.correo_electronico;
			$(correo).change();
		}
		
		var telefono = osm_getObjeto("FormatoDato.formatoDatoList:6.formatoDatoList:12.valor");
		if (telefono && respuesta.deudor_obj && respuesta.deudor_obj.telefono) {
			telefono.value = respuesta.deudor_obj.telefono;
			$(telefono).change();
		}
		
		var tramite = osm_getObjeto("FormatoDato.formatoDatoList:7.formatoDatoList:1.valor");
		if (tramite && respuesta.tramite_otros_documentos_obj) {
			tramite.value = respuesta.tramite_otros_documentos_obj.nombre;
			tramite.realvalue = respuesta.tramite_otros_documentos_obj.id;
			$(tramite).change();
		}
		
		actualizarTiposArchivo(respuesta.tipo_solicitante_obj.id, respuesta.tipo_solicitud_obj.id);
		
		rta_req = respuesta;
		
	}
	
	osm_unblock_window();
}

function actualizarTiposArchivo(tipo_solicitante, tipo_solicitud) {
	if (tipo_solicitante && tipo_solicitud) {
		var tiposArchivo = jsonrpc._("respuestaRequerimientoServicio.obtenerTiposArchivosPorSolicitanteSolicitud")(tipo_solicitante, tipo_solicitud);
		
		if (tiposArchivo) {
			$("#select_tipo_archivo").empty();
			var def = new Option("-- Seleccione --", "");
			$(def).html("-- Seleccione --");
		    $("#select_tipo_archivo").append(def);
			tiposArchivo = tiposArchivo.list;
			for (var i = 0; i < tiposArchivo.length; i++) {
			    var o = new Option(tiposArchivo[i].nombre, tiposArchivo[i].id_tipo_archivo);
			    $(o).html(tiposArchivo[i].nombre);
			    $("#select_tipo_archivo").append(o);
			}
			var otros = new Option("Otro documento", "1");
			$(otros).html("Otro documento");
			$("#select_tipo_archivo").append(otros);
		}
		$('#btn_adjuntar_archivo').removeClass('disabled');
		$('#btn_adjuntar_archivo').attr("data-toggle", "modal");
		
		$("#select_tipo_archivo").prop("disabled",false);
		
	}
}

function limpiarAdjuntosFormulario (resp) {
	osm_block_window();
	osm_verLoader();
	var id_carga = osm_getValor("id_carga");
	borrarAdjuntos(id_carga);
	limpiarFormulario();		
	
}

function limpiarFormulario() {
	NOMBRE_SOLICITUD = "";
	
	var numero_solicitud = osm_getObjeto("FormatoDato.formatoDatoList:1.valor");
		numero_solicitud.value = "";
		numero_solicitud.realvalue = "";
	
	var numero_proceso = osm_getObjeto("FormatoDato.formatoDatoList:2.valor");
		numero_proceso.value = "";
		numero_proceso.realvalue = "";
	
	var tipo_solicitante = osm_getObjeto("FormatoDato.formatoDatoList:3.valor");
		tipo_solicitante.value = "";
		tipo_solicitante.realvalue = "";
	
	var tipo_solicitud = osm_getObjeto("FormatoDato.formatoDatoList:4.valor");
		tipo_solicitud.value = "";
		tipo_solicitud.realvalue = "";
	
	var tipo_identificacion = osm_getObjeto("FormatoDato.formatoDatoList:6.formatoDatoList:1.valor");
		tipo_identificacion.value = "";
		tipo_identificacion.realvalue = "";
	
	var numero_identificacion = osm_getObjeto("FormatoDato.formatoDatoList:6.formatoDatoList:2.valor");
		numero_identificacion.value = "";
	
	var razon_social = osm_getObjeto("FormatoDato.formatoDatoList:6.formatoDatoList:3.valor");
		razon_social.value = "";
	
	var pais = osm_getObjeto("FormatoDato.formatoDatoList:6.formatoDatoList:7.valor");
		pais.value = "";
		pais.realvalue = "";
	
	var departamento = osm_getObjeto("FormatoDato.formatoDatoList:6.formatoDatoList:8.valor");
		departamento.value = "";
		departamento.realvalue = "";
	
	var municipio = osm_getObjeto("FormatoDato.formatoDatoList:6.formatoDatoList:9.valor");
		municipio.value = "";
		municipio.realvalue = "";
	
	var direccion = osm_getObjeto("FormatoDato.formatoDatoList:6.formatoDatoList:10.valor");
		direccion.value = "";
	
	var correo = osm_getObjeto("FormatoDato.formatoDatoList:6.formatoDatoList:11.valor");
		correo.value = "";
	
	var telefono = osm_getObjeto("FormatoDato.formatoDatoList:6.formatoDatoList:12.valor");
		telefono.value = "";
	
	var tramite = osm_getObjeto("FormatoDato.formatoDatoList:7.formatoDatoList:1.valor");
		tramite.value = "";
		tramite.realvalue = "";
	
	$("#select_tipo_archivo").prop("disabled","true");
	$('#btn_adjuntar_archivo').addClass('disabled');
	$('#btn_adjuntar_archivo').removeAttr('data-toggle');
	
	osm_unblock_window();
}

function validacionPersonalizada() {
	if (CANT_ARCHIVOS_ADJUNTOS < 1) {
		s_alert_info('Falta adjuntar documentos', 'Debe adjuntar al menos un documento para enviar la solicitud.');
		return false;
	}
	
	return true;
}