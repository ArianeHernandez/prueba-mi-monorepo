var CAMPOS_INFO_ADICIONAL = null;

function verVentanaEjecutarAccion(id_accion, nombre_accion, mensaje_ejecucion){
	
	osm_setValor("id_accion", id_accion);
	osm_setValor("InstanciaAccion.id_accion", id_accion);
	
	mostrarCamposAdicionales(id_accion);
	mostrarSelectResponsable(id_accion);
	
	osm_setVisible("vn_accion_instancia", true, true);
	osm_ocultarSelects("bodyContent");
	$("#nombre_accion").text(nombre_accion);
	
	if (mensaje_ejecucion) {
		$("#mensaje_ejecucion").html(mensaje_ejecucion);
		$("#bloque_mensaje_ejecucion").show();
	}
	
}

function ejecutarAccionRedireccion (id_accion_redireccion) {
	osm_enviarFormulario("frm_" + id_accion_redireccion);
}

function mostrarCamposAdicionales(id_accion){
	$("#div_campos_adicionales").hide();
	var camposInfoAdicional = jsonrpc._("accionInfoAdicionalServicio.obtenerCamposInfoAdicionalPorAccion")(id_accion);
	 
	CAMPOS_INFO_ADICIONAL = camposInfoAdicional.list;
	
	console.log(CAMPOS_INFO_ADICIONAL);
	
	if (CAMPOS_INFO_ADICIONAL.length > 0) {

		for ( var i = 0; i < CAMPOS_INFO_ADICIONAL.length; i++) {

			var campoInfoAdicional = CAMPOS_INFO_ADICIONAL[i];

			pintarCampoInfoAdicional('contenido_campos_adicionales' , campoInfoAdicional);
		}
		
        $("#div_campos_adicionales").show();
	}
	
}

function pintarCampoInfoAdicional(id_padre, campo) {
	
	if(campo.id_tipocampo == 1){ // Texto
		osm_construirHTML(id_padre, 'PLANTILLA_CAMPO_TEXTO', [ campo.nombre , campo.id_campo ] );
	} else if(campo.id_tipocampo == 2){ // Booleano
		osm_construirHTML(id_padre, 'PLANTILLA_CAMPO_CHECK', [ campo.nombre , campo.id_campo ]);
	} else if(campo.id_tipocampo == 3){ // Correo Electronico
		osm_construirHTML(id_padre, 'PLANTILLA_CAMPO_CORREO', [ campo.nombre , campo.id_campo ]);
	} else if(campo.id_tipocampo == 4){ // Fecha
		osm_construirHTML(id_padre, 'PLANTILLA_CAMPO_FECHA', [ campo.nombre , campo.id_campo ]);
	} else if(campo.id_tipocampo == 5 || campo.id_tipocampo == 7){ // Entero - Flotante
		if(campo.id_lista_valores){
			var listaValores = jsonrpc._("accionInfoAdicionalServicio.obtenerListaValores")(campo.id_lista_valores);
			if(listaValores.list){
				listaValores = listaValores.list;
				osm_construirHTML(id_padre, 'PLANTILLA_CAMPO_LISTA_VALORES', [ campo.nombre , campo.id_campo ]);
				for (var i = 0; i < listaValores.length; i++) {
				    console.log(listaValores[i]);
				    var o = new Option(listaValores[i].nombre, listaValores[i].id);
				    $(o).html(listaValores[i].nombre);
				    $("#info_adicional_campo_" + campo.id_campo).append(o);
				}
			}else{
				osm_alert("No se pudo obtener la lista de valores para el campo " + campo.nombre);
			}			
		}else{
			osm_construirHTML(id_padre, 'PLANTILLA_CAMPO_ENTERO', [ campo.nombre , campo.id_campo ]);
		}
	} else if(campo.id_tipocampo == 6){ // Texto Largo
		osm_construirHTML(id_padre, 'PLANTILLA_CAMPO_TEXTO_LARGO', [ campo.nombre , campo.id_campo ]);
	} else if(campo.id_tipocampo == -1){ // Lista de valores
		
	} else {			// No es tipo campo valido
		osm_alert("El campo " + campo.nombre + " no tiene un tipo de dato valido para informacion adicional.");
	}

}

function mostrarSelectResponsable(id_accion){
	$("#div_responsable").hide();
	
	var seleccionarResponsable = jsonrpc._("accionResponsableServicio.tieneRolesAsociados")(id_accion);
	
	if(seleccionarResponsable){
		var id_carga = osm_getValor("InstanciaAccion.id_carga");
		var responsables = jsonrpc._("accionResponsableServicio.obtenerListaResponsablesPorAccion")(id_accion);
		var responsable_guardado = jsonrpc._("accionResponsableServicio.obtenerResponsable")(id_carga, id_accion);
		listaResponsables = responsables.list;

		$('#select_responsable')
	    .find('option')
	    .remove();
		
		var o = new Option("--Seleccione--", "");
		$(o).html("--Seleccione--");
		$("#select_responsable").append(o);
		
		for (var i = 0; i < listaResponsables.length; i++) {
			console.log(listaResponsables[i]);
			var o = new Option(listaResponsables[i].nombreCompleto, listaResponsables[i].id_persona);
			$(o).html(listaResponsables[i].nombreCompleto);
			$("#select_responsable").append(o);
		}
		if(responsable_guardado){
			$('#select_responsable').val(responsable_guardado);
		}
		
		$("#div_responsable").show();
	}else{
		console.log("no tiene roles asociados.")
	}
}

function cerrarVentanaEjecutarAccion() {
	osm_setVisible("vn_accion_instancia", false);
	osm_mostrarSelects("bodyContent");
	
	 $("#contenido_campos_adicionales").empty();
	 $("#bloque_mensaje_ejecucion").hide();
}

// --------------------------------------------------------
function activar(div, id_elemento){
	
	$(div).toggleClass("caja_aprobar_ok");
	$(div).toggleClass("caja_aprobar_cancel");
	
	if ($(div).hasClass("caja_aprobar_ok")) {
		osm_setValor(id_elemento, "");
	}
	else {
		osm_setValor(id_elemento, id_elemento);
	}
	
}

// --------------------------------------------------------

function verVentanaAprobarCarga() {
	osm_setVisible("vn_aprobarcarga", true, true);
	osm_ocultarSelects("bodyContent");
}

function cerrarVentanaAprobarCarga() {
	osm_setVisible("vn_aprobarcarga", false);
	osm_mostrarSelects("bodyContent");
}

// --------------------------------------------------------

function verVentanaRechazarCarga() {
	osm_setVisible("vn_rechazarcarga", true, true);
	osm_ocultarSelects("bodyContent");
}

function cerrarVentanaRechazarCarga() {
	osm_setVisible("vn_rechazarcarga", false);
	osm_mostrarSelects("bodyContent");
}

function aceptarRechazarCarga(){
	
	var tx_motivorechazo = osm_trimToNull($("#tx_motivorechazo").val());
	
	if(tx_motivorechazo == null){
		alert("Ingrese el motivo del rechazo.");
		return;
	}
	
	$("#motivorechazo").val(tx_motivorechazo);
	
	cerrarVentanaRechazarCarga();
	
	osm_enviarFormulario("frm_rechazar");
}

//--------------------------------------------------------

function verVentanaDetalleLog(id_ejecucion) {
	
	osm_setValor("cc_contenido_ventana_detalle", osm_getValor("detalle_ejecucion_" + id_ejecucion));
	
	osm_setVisible("vn_detalleLog", true, true);
	osm_ocultarSelects("bodyContent");
}

function cerrarVentanaDetalleLog() {
	osm_setVisible("vn_detalleLog", false);
	osm_mostrarSelects("bodyContent");
}

//--------------------------------------------------------

function guardarCamposAdicionales() {
	osm_setVisible("vn_accion_instancia", false);
	osm_mostrarSelects("bodyContent");
	
	if(!guardarResponsable()){
		alert("Error al guardar responsable.");
		return false;
	}
	
	if (CAMPOS_INFO_ADICIONAL.length > 0) {
		var id_carga = osm_getValor("InstanciaAccion.id_carga");
		var exito = true;
		for ( var i = 0; i < CAMPOS_INFO_ADICIONAL.length; i++) {

			var campo = CAMPOS_INFO_ADICIONAL[i];
			var valor = osm_getValor("info_adicional_campo_" + campo.id_campo);
			exito = exito && jsonrpc._("accionInfoAdicionalServicio.guardarCamposAdicionales")(id_carga, campo.id_campo, valor);
		}
        if(!exito){
        	osm_alert("Ocurrio un error al guardar los datos.");
        }
        return exito;
	} else{
		return true;
	}
	
}

function guardarResponsable() {
	var id_accion = osm_getValor("id_accion");
	var seleccionarResponsable = jsonrpc._("accionResponsableServicio.tieneRolesAsociados")(id_accion);
	if (seleccionarResponsable){
		var id_carga = osm_getValor("InstanciaAccion.id_carga");
		var id_persona = osm_getValor("select_responsable")
		return jsonrpc._("accionResponsableServicio.guardarAccionResponsable")(id_carga, id_accion, id_persona);
	}else{
		return true;
	}
	
	
}

function validarArchivosPendientes(){
	if ($(".adj_gestion_C")[0] && $(".adj_gestion_true")[0]){ //Si existen archivos en estado c y se muestra el bloque de gestion de archivos
	    osm_alert("Existen archivos adjuntos pendientes por gestionar.");
	    return false;
	} else {
		return true;
	}
}

function validarArchivosCargados(){
	if (!$(".adj_gestion_C")[0] && $(".adj_adjuntar_true")[0] && !$(".adj_gestion_true")[0]){ //Si no existen archivos en estado c y se muestra el bloque de gestion de archivos
	    osm_alert("Por favor adjunte un archivo.");
	    return false;
	} else {
		return true;
	}
}

function validarResponsable(){
	var id_accion = osm_getValor("id_accion");
	var seleccionarResponsable = jsonrpc._("accionResponsableServicio.tieneRolesAsociados")(id_accion);
	if (seleccionarResponsable && !osm_getValor("select_responsable")){
		 osm_alert("Por favor seleccione un responsable.");
		 return false;
	}else{
		return true;
	}
}


function validarCamposAdicionales(){
	
	if(!validarArchivosPendientes()){
		return false;
	}
	
	if(!validarArchivosCargados()){
		return false;
	}
	
	if(!validarResponsable()){
		return false;
	}
	
	if (CAMPOS_INFO_ADICIONAL.length > 0) {
		
		skin_init_validar();

		for ( var i = 0; i < CAMPOS_INFO_ADICIONAL.length; i++) {
			
			var campo = CAMPOS_INFO_ADICIONAL[i];
			
			if(campo.id_tipocampo == 1){ // Texto
				skin_validar("info_adicional_campo_" + campo.id_campo, "TEXTO", true);
			} else if(campo.id_tipocampo == 2){ // Booleano
				skin_validar("info_adicional_campo_" + campo.id_campo, "TEXTO", true);
			} else if(campo.id_tipocampo == 3){ // Correo Electronico
				skin_validar("info_adicional_campo_" + campo.id_campo, "TEXTO", true);
			} else if(campo.id_tipocampo == 4){ // Fecha
				skin_validar("info_adicional_campo_" + campo.id_campo, "FECHA", true);
			} else if(campo.id_tipocampo == 5){ // Entero
				skin_validar("info_adicional_campo_" + campo.id_campo, "NUMERO", true);
			} else if(campo.id_tipocampo == 6){ // Texto Largo
				skin_validar("info_adicional_campo_" + campo.id_campo, "TEXTO", true);
			}
			
		}
		
		if (SK_ERROR_VALIDACION) {
			osm_alert("Existen datos sin diligenciar o inv\u00E1lidos. Por favor, verifique los campos resaltados.\n");
			$(".put").removeClass("on");
			$(".ERR_VALIDAR").addClass("on");
			return false;
		}
		return true;
		
	} else{
		return true;
	}
	
};

function ajusteVentana() {
	var pos = osm_getWindowSize();
	$("#contenido_ventana_ejecutar_accion").css("height", (pos[1] - 200) + "px").css("overflow", "auto");
}

osm_listen("resize", window, ajusteVentana);
osm_listen("load", window, ajusteVentana);
