
function cerrarVentanaEdicion(idVentana){
	osm_setVisible(idVentana, false);
	osm_mostrarSelects("bodyContent");
}

// ************************ Detalle Accion
// ************************ General

function mostrarVentanaEdicionAccion(idAccion, idInstancia){
	var accion = instanciasDestino[idAccion];
	var parametros = [accion.id_accion, accion.nombre, accion.id_instancia, accion.mensaje_ejecucion];
	osm_setValor("div_contenido_ventana_accion", '');
	pintarVentanaAccion('div_contenido_ventana_accion', parametros);
	
	if(accion.oculto == 'S'){
		var obj = osm_getObjeto('oculto_accion_' + accion.id_accion);
		obj.checked = true;
	}
	osm_setVisible("VN_EDICION_ACCIONES", true, true);
	osm_ocultarSelects("bodyContent");
	$("#div_contenido_ventana_accion").scrollTop(0);
}

function pintarVentanaAccion(idPadre, parametros) {
	osm_construirHTML(idPadre, 'PLANTILLA_ITEM_ACCION_INST', parametros);
	listarAccionesAutomaticas(parametros[0]);
	listarInstanciasDestino(parametros[0]);
	listarNotificacionesAccion(parametros[0]);
	listarOperacionesInternas(parametros[0]);
	listarCamposInfoAdicional(parametros[0]);
	listarRolesResponsable(parametros[0]);
}

function listarAccionesAutomaticas(idAccion) {
	var accionElegida = null;
	var respuesta = jsonrpc._("accionJsonServicio.obtenerListasDinamicas")();
	var listasDinamicas = respuesta != null ? respuesta.list : [];
	if (instanciasDestino[idAccion].lista_dinamica) {
		for (var i = 0; i < listasDinamicas.length; i++) {
			var item = listasDinamicas[i];
			if (item.id_lista_dinamica === instanciasDestino[idAccion].lista_dinamica) {
				item.elegido = true;
				break;
			}
		}
	}
	actualizarSelectListasDinamicas(idAccion, listasDinamicas);
}

function actualizarSelectListasDinamicas(idAccion, listasDinamicas){
	osm_setValor('select_aa_lista_dinamica_' + idAccion, '');
	var selectListasDinamicas = osm_getObjeto('select_aa_lista_dinamica_' + idAccion);
	
	var option_ninguno = crearOption('option_aa_lista_dinamica_ninguno', '-- Ninguna --', '');
	selectListasDinamicas.appendChild(option_ninguno);
	for ( var i = 0; i < listasDinamicas.length; i++) {
		var option = crearOption('option_aa_lista_dinamica_' + listasDinamicas[i].id_lista_dinamica, 
				listasDinamicas[i].nombre, listasDinamicas[i].id_lista_dinamica);
		selectListasDinamicas.appendChild(option);
		if (listasDinamicas[i].elegido) {
			osm_setValor('select_aa_lista_dinamica_' + idAccion, listasDinamicas[i].id_lista_dinamica);
		}
	}
}

function actualizarAccion(idAccion, idInstancia, actualizarGrafico) {
	var nombre = osm_getValor('nombre_accion_' + idAccion);
	var oculto = 'N';
	if (osm_getObjeto('oculto_accion_' + idAccion).checked) {
		oculto = 'S';
	}
	instanciasDestino[idAccion].nombre = nombre;
	instanciasDestino[idAccion].oculto = oculto;
	instanciasDestino[idAccion].lista_dinamica = osm_getValor('select_aa_lista_dinamica_' + idAccion);
	instanciasDestino[idAccion].mensaje_ejecucion = osm_getValor("textarea_mensaje_ejecucion");
	
	var exitoActualizar = jsonrpc._("accionJsonServicio.actualizarAccion")(instanciasDestino[idAccion]);

	if (exitoActualizar) {
		nombre = nombre.length > 20 ? nombre.substr(0, 20) + '...': nombre;
		osm_setValor('titulo_item_menu_acciones_' + idAccion, nombre);
		var acciones = jsonrpc._("accionJsonServicio.obtenerAccionesPorInstancia")(idInstancia);
		instancias[idInstancia].accionesPorInstancia = acciones;
		if (actualizarGrafico) {
			actualizarAccionGrafico(idAccion);
		}
	} else {
		alert('No se pudo actualizar la instancia.');
	}
}

function actualizarAccionGrafico (idAccion) {
	accionesAsociadasDraggable = new Set();
	accionesAsociadasDraggable.add(idAccion);
	limpiarAccionesAsociadas();
	actualizarAccionesAsociadas();
}

// *************************** Instancias Destino

function listarInstanciasDestino(idAccion) {
	actualizarSelectInstanciasDestino(idAccion);
	var divContenidoInstDest = osm_getObjeto('contenido_instancias_dest_' + idAccion);

	var instanciasDestinoList = instanciasDestino[idAccion].instancias_destino.list;
	divContenidoInstDest.cantidad_inst_dest = instanciasDestinoList.length;
	if (instanciasDestinoList.length > 0) {
		$("#mensaje_accion_sin_instancias_destino").hide();
		for ( var i = 0; i < instanciasDestinoList.length; i++) {
			var idInstanciaDestino = instanciasDestinoList[i];
			var parametros = [ idAccion, instancias[idInstanciaDestino].id_instancia, instancias[idInstanciaDestino].nombre ];
			osm_construirHTML('contenido_instancias_dest_' + idAccion, 'PLANTILLA_ITEM_INST_DEST', parametros);
		}
	}
}

function actualizarSelectInstanciasDestino(idAccion) {
	var instanciasDestinoList = jsonrpc._("accionJsonServicio.obtenerInstDisponiblesParaAsignar")(idProcesoAdmin, idAccion).list;

	osm_setValor('select_inst_dest_' + idAccion, '');
	$('#select_inst_dest_' + idAccion).find('option').remove();
	var selectInstDest = osm_getObjeto('select_inst_dest_' + idAccion);

	var optionNinguno = crearOption('option_inst_dest_ninguno', '-- seleccione --', '');
	selectInstDest.appendChild(optionNinguno);
	for ( var i = 0; i < instanciasDestinoList.length; i++) {
		var option = crearOption('option_inst_dest_' + instanciasDestinoList[i].id_instancia, 
				instanciasDestinoList[i].nombre, instanciasDestinoList[i].id_instancia);
		selectInstDest.appendChild(option);
	}
}

function asignarInstanciaDestino(idAccion, idInstancia) {
	var idInstanciaDestino = osm_getValor('select_inst_dest_' + idAccion);
	var exitoActualizar = false;

	if (!osm_esVacio(idInstanciaDestino)) {
		exitoActualizar = jsonrpc._("accionJsonServicio.insertarInstaciaDestino")(idAccion, idInstanciaDestino);

		if (exitoActualizar) {
			var instanciaDestino = instancias[idInstanciaDestino];
			var divContenidoInstDest = osm_getObjeto('contenido_instancias_dest_' + idAccion);
			$("#mensaje_accion_sin_instancias_destino").hide();

			var parametros = [ idAccion, instanciaDestino.id_instancia, instanciaDestino.nombre ];
			osm_construirHTML('contenido_instancias_dest_' + idAccion, 'PLANTILLA_ITEM_INST_DEST', parametros);
			divContenidoInstDest.cantidad_inst_dest++;
			
			instanciasDestino[idAccion].instancias_destino = jsonrpc._("accionJsonServicio.obtenerIdInstanciasDestinoPorAccion")(idAccion);
			actualizarAccionGrafico(idAccion);
			actualizarSelectInstanciasDestino(idAccion);
		} else {
			alert('No se pudo asignar la instancia.');
		}
	} else {
		alert('Debe seleccionar una instancia.');
	}
}

function eliminarInstanciaDestino(idAccion, idInstanciaDestino) {
	var exitoEliminar = jsonrpc._("accionJsonServicio.borrarInstanciaDestino")(idAccion, idInstanciaDestino);
	var divContenidoInstDest = osm_getObjeto('contenido_instancias_dest_' + idAccion);

	if (exitoEliminar) {
		$('#item_accion_' + idAccion + '_inst_dest_' + idInstanciaDestino).remove();
		divContenidoInstDest.cantidad_inst_dest--;
		
		instanciasDestino[idAccion].instancias_destino = jsonrpc._("accionJsonServicio.obtenerIdInstanciasDestinoPorAccion")(idAccion);
		actualizarAccionGrafico(idAccion);
		actualizarSelectInstanciasDestino(idAccion);
	} else {
		alert('Ha ocurrido un error al eliminar la instancia.');
	}
	if (divContenidoInstDest.cantidad_inst_dest == 0) {
		$("#mensaje_accion_sin_instancias_destino").show();
	}
}

// ************************ Notificaciones

function listarNotificacionesAccion(idAccion) {
	var divContenidoNotiAcc = osm_getObjeto('contenido_notificaciones_accion_' + idAccion);
	var notificacionesList = jsonrpc._("notificacionAccionJsonServicio.obtenerNotificacionesAccPorAccion")(idAccion).list;
	divContenidoNotiAcc.cantidad_notificaciones_accion = notificacionesList.length;
	if (notificacionesList.length > 0) {
		$("#mensaje_accion_sin_notificaciones").hide();
		for ( var i = 0; i < notificacionesList.length; i++) {
			var notificacion = notificacionesList[i];
			var mensaje = !osm_esVacio(notificacion.mensaje) ? notificacion.mensaje: '';
			var parametros = [ notificacion.id_notificacion_accion, notificacion.tipo, notificacion.correo, 
			                   notificacion.id_administrativo, mensaje, idAccion ];
			pintarNotificacionAccion('contenido_notificaciones_accion_' + idAccion, parametros);
		}
	}
}

function pintarNotificacionAccion(id_padre, parametros) {
	osm_construirHTML(id_padre, 'PLANTILLA_ITEM_NOTI_ACC', parametros);
	var selectTipoNotiAcc = osm_getObjeto('select_tipo_noti_acc_' + parametros[0]);
	if (selectTipoNotiAcc != null && selectTipoNotiAcc.hasChildNodes()) {
		for ( var i = 0; i < selectTipoNotiAcc.childNodes.length; i++) {
			if (selectTipoNotiAcc.options[i].value == parametros[1]) {
				selectTipoNotiAcc.options[i].selected = true;
			}
		}
	}
	if (parametros[1] == 'C') {
		osm_setValor('titulo_tipo_noti_acc_' + parametros[0], 'Correo Especifico');
		construirInputTipoCorreo(parametros[0], parametros[2]);
	} else if (parametros[1] == 'A') {
		osm_setValor('titulo_tipo_noti_acc_' + parametros[0], 'Administrativo');
		construirSelectTipoAdministrativo(parametros[0], parametros[3]);
	} else if (parametros[1] == 'L') {
		osm_setValor('titulo_tipo_noti_acc_' + parametros[0], 'Liberador');
	} else if (parametros[1] == 'Z') {
		osm_setValor('titulo_tipo_noti_acc_' + parametros[0], 'Asesor Comercial');
	}
}

function crearNotificacionAccion(idAccion) {
	var idNotificacionAccion = jsonrpc._("notificacionAccionJsonServicio.obtenerSiguienteIDNotificacionAccion")();
	var exitoCrear = jsonrpc._("notificacionAccionJsonServicio.insertarNotificacionAccion")(idNotificacionAccion, idAccion, '', null, null, '');
	if (exitoCrear) {
		$("#mensaje_accion_sin_notificaciones").hide();
		var divContenidoNotiAcc = osm_getObjeto('contenido_notificaciones_accion_' + idAccion);
		var parametros = [ idNotificacionAccion, '', null, null, '', idAccion ];
		pintarNotificacionAccion('contenido_notificaciones_accion_' + idAccion, parametros);
		divContenidoNotiAcc.cantidad_notificaciones_accion++;
	} else {
		alert('Ha ocurrido un error al crear la notificacion.');
	}
}

function eliminarNotificacionAccion(idNotificacionAccion, idAccion) {
	var exitoEliminar = jsonrpc._("notificacionAccionJsonServicio.borrarNotificacionAccion")(idNotificacionAccion);
	var divContenidoNotiAcc = osm_getObjeto('contenido_notificaciones_accion_' + idAccion);
	if (exitoEliminar) {
		$("#item_noti_acci_" + idNotificacionAccion).remove();
		divContenidoNotiAcc.cantidad_notificaciones_accion--;
	} else {
		alert('Ha ocurrido un error al eliminar la notificacion.');
	}
	if (divContenidoNotiAcc.cantidad_notificaciones_accion == 0) {
		$("#mensaje_accion_sin_notificaciones").show();
	}
}

function actualizarTipoNotificacionAccion(idNotificacionAccion) {
	var tipo = osm_getValor('select_tipo_noti_acc_' + idNotificacionAccion);
	if (tipo == 'C') {
		osm_setValor('titulo_tipo_noti_acc_' + idNotificacionAccion, 'Correo');
		construirInputTipoCorreo(idNotificacionAccion, '');
	} else if (tipo == 'A') {
		osm_setValor('titulo_tipo_noti_acc_' + idNotificacionAccion, 'Administrativo');
		construirSelectTipoAdministrativo(idNotificacionAccion, null);
	} else if (tipo != 'C' && tipo != 'A') {
		osm_setValor('titulo_tipo_noti_acc_' + idNotificacionAccion, '');
		osm_setValor('valor_tipo_noti_acc_' + idNotificacionAccion, '');
	}
	actualizarNotificacionAccion(idNotificacionAccion);
}

function actualizarNotificacionAccion(idNotificacionAccion) {
	var tipo = osm_getValor('select_tipo_noti_acc_' + idNotificacionAccion);
	var correo = null;
	if (tipo == 'C') {
		correo = osm_getValor('input_correo_noti_acc_' + idNotificacionAccion);
	}
	var id_administrativo = null;
	if (tipo == 'A') {
		id_administrativo = osm_getValor('select_adminv_noti_acc_' + idNotificacionAccion);
	}
	var mensaje = osm_getValor('areatexto_mensaje_noti_acc_' + idNotificacionAccion);
	jsonrpc._("notificacionAccionJsonServicio.actualizarNotificacionAccion")(idNotificacionAccion, tipo, correo, id_administrativo, mensaje);
}

function construirInputTipoCorreo(idNotificacionAccion, correo) {
	var inputCorreo = document.createElement('INPUT');
	inputCorreo.onchange = new Function('actualizarNotificacionAccion(' + idNotificacionAccion + ');');
	inputCorreo.className = "form-control";
	inputCorreo.id = "input_correo_noti_acc_" + idNotificacionAccion;
	osm_setValor('valor_tipo_noti_acc_' + idNotificacionAccion, '');
	osm_getObjeto('valor_tipo_noti_acc_' + idNotificacionAccion).appendChild(inputCorreo);
	inputCorreo.value = correo;
}

function construirSelectTipoAdministrativo(idNotificacionAccion, idAdministrativo) {
	var adminList = jsonrpc._("administrativoJsonServicio.obtenerAdministrativosActivos")().list;

	var selectAdmin = document.createElement('SELECT')
	selectAdmin.className = "cajatextoselector w140";
	selectAdmin.id = "select_adminv_noti_acc_" + idNotificacionAccion;
	selectAdmin.onchange = new Function('actualizarNotificacionAccion(' + idNotificacionAccion + ');');
	
	osm_setValor('valor_tipo_noti_acc_' + idNotificacionAccion, '');
	osm_getObjeto('valor_tipo_noti_acc_' + idNotificacionAccion).appendChild(selectAdmin);
	osm_setValor('select_adminv_noti_acc_' + idNotificacionAccion, '');
	
	var optionNinguno = crearOption('option_adminv_noti_acc_ninguno', '-- Ninguno --', '');
	selectAdmin.appendChild(optionNinguno);
	for (var i = 0; i < adminList.length; i++) {
		var option = crearOption('option_adminv_noti_acc_' + adminList[i].id_administrativo, 
				adminList[i].nombre + ' ' + adminList[i].apellido, adminList[i].id_administrativo);
		selectAdmin.appendChild(option);
		if (adminList[i].id_administrativo == idAdministrativo) {
			option.selected = true;
		}
	}
}

// ************************ Operaciones internas

function listarOperacionesInternas(idAccion) {
	actualizarSelectOperacionInterna(idAccion);

	var divContenidoOperacionInterna = osm_getObjeto('contenido_operacion_interna_' + idAccion);
	var listaOperacionesInternasAccion = jsonrpc._("accionOperacionInterna.obtenerListaOperacionesInternasPorAccion")(idAccion).list;

	divContenidoOperacionInterna.cantidad_operaciones_internas = listaOperacionesInternasAccion.length;
	if (listaOperacionesInternasAccion.length > 0) {
		$("#mensaje_accion_sin_opinternas").hide();
		for (var i = 0; i < listaOperacionesInternasAccion.length; i++) {
			var operacionInterna = listaOperacionesInternasAccion[i];
			var parametros = [ idAccion, operacionInterna.id_operacion_interna, operacionInterna.nombre ];
			osm_construirHTML('contenido_operacion_interna_' + idAccion, 'PLANTILLA_ITEM_OPERACION_INTERNA', parametros);
		}
	}
}

function actualizarSelectOperacionInterna(idAccion) {
	var listaOperacionesInternas = jsonrpc._("accionOperacionInterna.obtenerOperacionesDisponiblesParaAsignar")(idAccion).list;
	
	osm_setValor('select_operacion_interna_' + idAccion, '');
	$('#select_operacion_interna_' + idAccion).find('option').remove();
	var selectOperacionInterna = osm_getObjeto('select_operacion_interna_' + idAccion);

	var option_ninguno = crearOption('option_opinterna_ninguno', '-- Seleccione --', '');
	selectOperacionInterna.appendChild(option_ninguno);
	for (var i = 0; i < listaOperacionesInternas.length; i++) {
		var option = crearOption('option_operacion_interna_' + listaOperacionesInternas[i].id_operacion_interna,
				listaOperacionesInternas[i].nombre, listaOperacionesInternas[i].id_operacion_interna);
		selectOperacionInterna.appendChild(option);
	}
}

function asignarOperacionInterna(idAccion) {
	var idOperacionInterna = osm_getValor('select_operacion_interna_' + idAccion);
	var exitoActualizar = false;

	if (!osm_esVacio(idOperacionInterna)) {
		exitoActualizar = jsonrpc._("accionOperacionInterna.insertarOperacionInternaAccion")(idOperacionInterna, idAccion);

		if (exitoActualizar) {
			$("#mensaje_accion_sin_opinternas").hide();
			var operacionInterna = jsonrpc._("accionOperacionInterna.obtenerOperacionInterna")(idOperacionInterna);

			var divContenidoOperacionInterna = osm_getObjeto('contenido_operacion_interna_' + idAccion);
			var parametros = [ idAccion, operacionInterna.id_operacion_interna, operacionInterna.nombre ];
			osm_construirHTML('contenido_operacion_interna_' + idAccion, 'PLANTILLA_ITEM_OPERACION_INTERNA', parametros);

			divContenidoOperacionInterna.cantidad_operaciones_internas++;
			actualizarSelectOperacionInterna(idAccion);
		} else {
			alert('No se pudo asignar la operacion.');
		}
	} else {
		alert('Debe seleccionar una operacion.');
	}
}

function eliminarOperacionInterna(idAccion, idOperacionInterna) {
	var exitoEliminar = jsonrpc._("accionOperacionInterna.borrarOperacionInternaAccion")( idOperacionInterna, idAccion);
	var divContenidoOperacionInterna = osm_getObjeto('contenido_operacion_interna_' + idAccion);
	
	if (exitoEliminar) {
		$("#item_accion_" + idAccion + "_operacion_interna_" + idOperacionInterna).remove();
		divContenidoOperacionInterna.cantidad_operaciones_internas--;
		if (divContenidoOperacionInterna.cantidad_operaciones_internas == 0) {
			$("#mensaje_accion_sin_opinternas").show();
		}
		actualizarSelectOperacionInterna(idAccion);
	} else {
		alert('Ha ocurrido un error al eliminar la operacion.');
	}
}

// ************************ Informacion Adicional

function listarCamposInfoAdicional(idAccion) {
	actualizarSelectInfoAdicional(idAccion);
	var divContenidoInfoAdicional = osm_getObjeto('contenido_info_adicional_' + idAccion);
	var listaCamposInfoAdicional = jsonrpc._("accionInfoAdicionalServicio.obtenerCamposInfoAdicionalPorAccion")(idAccion).list;
	divContenidoInfoAdicional.cantidad_campos_info_adicional = listaCamposInfoAdicional.length;

	if (listaCamposInfoAdicional.length > 0) {
		$("#mensaje_accion_sin_info_adicional").hide();
		for (var i = 0; i < listaCamposInfoAdicional.length; i++) {
			var parametros = [ idAccion, listaCamposInfoAdicional[i].id_campo, listaCamposInfoAdicional[i].nombre ];
			osm_construirHTML('contenido_info_adicional_' + idAccion, 'PLANTILLA_ITEM_INFO_ADICIONAL', parametros);
		}
	}
	
}

function actualizarSelectInfoAdicional(idAccion) {
	var listaCamposInfoAdicional = jsonrpc._("accionInfoAdicionalServicio.obtenerListaCamposInfoAdicionalDisponiblesPorAccion")(idAccion).list;
	
	osm_setValor('select_info_adicional_' + idAccion, '');
	$('#select_info_adicional_' + idAccion).find('option').remove();
	var selectInfoAdicional = osm_getObjeto('select_info_adicional_' + idAccion);

	var optionNinguno = crearOption('option_info_ninguno', '-- Seleccione --', '');
	selectInfoAdicional.appendChild(optionNinguno);
	for ( var i = 0; i < listaCamposInfoAdicional.length; i++) {
		var option = crearOption('option_info_adicional_' + listaCamposInfoAdicional[i].id_campo, 
				listaCamposInfoAdicional[i].nombre, listaCamposInfoAdicional[i].id_campo);
		selectInfoAdicional.appendChild(option);
	}
}

function asignarCamposInfoAdicional(idAccion) {
	var idCampo = osm_getValor('select_info_adicional_' + idAccion);
	var exitoActualizar = false;
	
	if (!osm_esVacio(idCampo)) {
		exitoActualizar = jsonrpc._("accionInfoAdicionalServicio.insertarAccionCampoInfoAdicional")(idCampo, idAccion);
		
		if (exitoActualizar) {
			$("#mensaje_accion_sin_info_adicional").hide();
			var campo = jsonrpc._("accionInfoAdicionalServicio.obtenerCampoInfoAdicional")(idCampo);
			var parametros = [ idAccion, campo.id_campo, campo.nombre ];
			osm_construirHTML('contenido_info_adicional_' + idAccion, 'PLANTILLA_ITEM_INFO_ADICIONAL', parametros);

			var divContenidoInfoAdicional = osm_getObjeto('contenido_info_adicional_' + idAccion);
			divContenidoInfoAdicional.cantidad_campos_info_adicional++;
			actualizarSelectInfoAdicional(idAccion);
		} else {
			alert('No se pudo seleccionar el campo.');
		}
	} else {
		alert('Debe seleccionar un campo.');
	}
}

function eliminarCampoInfoAdicional(idAccion, idCampo) {
	var exitoEliminar = jsonrpc._("accionInfoAdicionalServicio.borrarAccionCampoInfoAdicional")(idCampo, idAccion);
	var divContenidoInfoAdicional = $('#contenido_info_adicional_' + idAccion).get(0);

	if (exitoEliminar) {
		$('#item_accion_' + idAccion + '_info_adicional_' + idCampo).remove();
		divContenidoInfoAdicional.cantidad_campos_info_adicional--;
		if (divContenidoInfoAdicional.cantidad_campos_info_adicional == 0) {
			$("#mensaje_accion_sin_info_adicional").show();
		}
		actualizarSelectInfoAdicional(idAccion);
	} else {
		alert('Ha ocurrido un error al eliminar el campo.');
	}
}

// ************************ Asignacion Responsable

function listarRolesResponsable(idAccion) {
	actualizarSelectRolesResponsable(idAccion);
	var divContenidoResponsable = osm_getObjeto('contenido_responsable_' + idAccion);
	var listaRolesResponsable = jsonrpc._("accionResponsableServicio.obtenerRolesPorAccion")(idAccion).list;
	divContenidoResponsable.cantidad_roles_responsable = listaRolesResponsable.length;

	if (listaRolesResponsable.length > 0) {
		$('#mensaje_accion_sin_responsable').hide();
		for (var i = 0; i < listaRolesResponsable.length; i++) {
			var parametros = [ idAccion, listaRolesResponsable[i].id_rol, listaRolesResponsable[i].nombre_rol ];
			osm_construirHTML('contenido_responsable_' + idAccion, 'PLANTILLA_ITEM_ROL_RESPONSABLE', parametros);
		}
	}
}

function actualizarSelectRolesResponsable(idAccion) {
	var listaRolesResponsable = jsonrpc._("accionResponsableServicio.obtenerRolesActivosDisponiblesAccion")(idAccion).list;
	
	osm_setValor('select_responsable_' + idAccion, '');
	$('#select_responsable_' + idAccion).find('option').remove();
	var selectRolesResponsable = osm_getObjeto('select_responsable_' + idAccion);

	var optionNinguno = crearOption('option_info_ninguno', '-- Seleccione --', '');
	selectRolesResponsable.appendChild(optionNinguno);
	for (var i = 0; i < listaRolesResponsable.length; i++) {
		var option = crearOption('option_responsable_' + listaRolesResponsable[i].id_rol, 
				listaRolesResponsable[i].nombre_rol, listaRolesResponsable[i].id_rol);
		selectRolesResponsable.appendChild(option);
	}
}

function asignarRolResponsable(idAccion) {
	var idRol = osm_getValor('select_responsable_' + idAccion);
	var exitoActualizar = false;

	if (!osm_esVacio(idRol)) {
		exitoActualizar = jsonrpc._("accionResponsableServicio.insertarAccionRol")(idRol, idAccion);

		if (exitoActualizar) {
			$('#mensaje_accion_sin_responsable').hide();
			var campo = jsonrpc._("accionResponsableServicio.obtenerRol")(idRol);
			var parametros = [ idAccion, campo.id_rol, campo.nombre_rol ];
			osm_construirHTML('contenido_responsable_' + idAccion, 'PLANTILLA_ITEM_ROL_RESPONSABLE', parametros);

			var divContenidoResponsable = osm_getObjeto('contenido_responsable_' + idAccion);
			divContenidoResponsable.cantidad_roles_responsable++;
			actualizarSelectRolesResponsable(idAccion);
		} else {
			alert('No se pudo seleccionar el rol.');
		}
	} else {
		alert('Debe seleccionar un rol.');
	}
}

function eliminarRol(idAccion, idRol) {
	var exitoEliminar = jsonrpc._("accionResponsableServicio.borrarAccionRol")( idRol, idAccion);
	var divContenidoResponsable = osm_getObjeto('contenido_responsable_' + idAccion);

	if (exitoEliminar) {
		$('#item_accion_' + idAccion + '_responsable_' + idRol).remove();
		divContenidoResponsable.cantidad_roles_responsable--;
		if (divContenidoResponsable.cantidad_roles_responsable == 0) {
			$('#mensaje_accion_sin_responsable').show();
		}
		actualizarSelectRolesResponsable(idAccion);
	} else {
		alert('Ha ocurrido un error al eliminar el rol.');
	}
}

// ************************ Detalle Accion Redireccion

function mostrarVentanaEdicionAccionRedireccion(idAccionRedireccion){
	var accionRedireccion = jsonrpc._("accionRedireccionServicio.obtenerAccionRedireccionPorId")(idAccionRedireccion);
	var parametros = [ accionRedireccion.id_accion_redireccion, accionRedireccion.nombre, accionRedireccion.id_instancia, accionRedireccion.endpoint ];
	
	osm_setValor("div_contenido_ventana_accion_redireccion", '');
	osm_construirHTML('div_contenido_ventana_accion_redireccion', 'PLANTILLA_ITEM_ACCION_REDIRECCION_INST', parametros);
	
	osm_setVisible("VN_EDICION_ACCIONES_REDIRECCION", true, true);
	osm_ocultarSelects("bodyContent");
}

function actualizarAccionRedireccion (idAccionRedireccion, idInstancia) {
	var nombre = osm_getValor('nombre_accion_redireccion_' + idAccionRedireccion);
	var endpoint = osm_getValor('endpoint_accion_redireccion_' + idAccionRedireccion);
	
	var accionRedireccion = {
		id_accion_redireccion: idAccionRedireccion,
		id_instancia: idInstancia,
		nombre: nombre,
		endpoint: endpoint
	};
	
	var exitoActualizar = jsonrpc._("accionRedireccionServicio.actualizarAccionRedireccion")(accionRedireccion);
	
	if (exitoActualizar) {
		nombre = nombre.length > 20 ? nombre.substr(0,20) + '...': nombre;
		osm_setValor('titulo_item_menu_acciones_redireccion_' + idAccionRedireccion, nombre);
	} else {
		alert('No se pudo actualizar la accion de redireccion.');
	}
}
