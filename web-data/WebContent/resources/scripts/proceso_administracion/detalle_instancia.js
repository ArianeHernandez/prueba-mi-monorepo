let formatosSalida;

function toggleSeccion(elemento){
	$(".area_contenido_inst").hide();
	
	var contenidoInstancia = osm_getObjeto(elemento);
	
	if($(contenidoInstancia).is(":hidden")){
		$(contenidoInstancia).slideDown(200);
		$(contenidoInstancia).css("overflow-y", "auto");
	}else{
		$(contenidoInstancia).slideUp(200);
	}
}

function construirSeccionDetalleInstancia() {
	osm_setValor('lista_inst_proceadmin', '');
	osm_construirHTML('lista_inst_proceadmin', 'PLANTILLA_LISTA_INST_PROCEADMIN');
	if (Object.keys(instancias).length !== 0) {
		$("#mensaje_seleccionar_instancia").show();
		$("#mensaje_sin_instancias").hide();
	} else {
		$("#mensaje_sin_instancias").show();
		$("#mensaje_seleccionar_instancia").hide();
	}
}

// ****************** Seccion Informacion General

function consultarFormatosSalida() {
	jsonrpc._("instanciaJsonServicio.obtenerFormatosSalidaPorNegocio")(actualizarFormatosSalida, idNegocio);
}

function actualizarFormatosSalida (formatos) {
	if (formatos) {
		formatosSalida = formatos.list;		
	}
}

function actualizarObjetoInstancia(idInstancia) {
	var nombre = osm_getValor('nombre_inst_' + idInstancia);
	var tiempo = osm_getValor('tiempo_inst_' + idInstancia);
	var formatoSalida = osm_getValor('formato_salida_inst_' + idInstancia);
	
	instancias[idInstancia].nombre = nombre;
	instancias[idInstancia].tiempo = tiempo;
	instancias[idInstancia].id_formato_salida = formatoSalida;
	
	validarCheckboxes(idInstancia);
	
	var exito = actualizarInstancia(idInstancia);
	
	if (exito) {
		$("#header_instancia_" + idInstancia).find("div").text(nombre);
		$("#titulo_info_instancia_" + idInstancia).text(nombre);
		$("#tiempo_instancia_"+idInstancia).text(tiempo+" min");
		ajustarContenidoInstancia(osm_getWindowSize()[1]);
	}
}

function validarCheckboxes(idInstancia) {
	var [aprobar, rechazar, inicial, aprobar_automaticamente, rechazar_automaticamente, 
	     solicitar_otp, adicionar_docs, gestionar_docs, ocultar_sin_filtro] = Array(9).fill('N');
	
	if (osm_getObjeto('perm_aprobar_inst_' + idInstancia).checked) {
		aprobar = 'S';
	}
	if (osm_getObjeto('perm_aprobar_auto_inst_' + idInstancia).checked) {
		aprobar_automaticamente = 'S';
		$("#perm_rechazar_inst_" + idInstancia).prop("checked", false);
		$("#perm_rechazar_auto_inst_" + idInstancia).prop("checked", false);
		$("#perm_rechazar_inst_" + idInstancia).attr("disabled", true);
	}else {
		$("#perm_rechazar_inst_" + idInstancia).attr("disabled", false);
	}
	if (osm_getObjeto('perm_rechazar_auto_inst_' + idInstancia).checked) {
		rechazar_automaticamente = 'S';
		$("#perm_aprobar_inst_" + idInstancia).prop("checked", false);
		$("#perm_aprobar_auto_inst_" + idInstancia).prop("checked", false);
		$("#perm_aprobar_inst_" + idInstancia).attr("disabled", true);
	}else {
		$("#perm_aprobar_inst_" + idInstancia).attr("disabled", false);
	}
	if (osm_getObjeto('perm_rechazar_inst_' + idInstancia).checked) {
		rechazar = 'S';
	}
	if (osm_getObjeto('inicial_inst_' + idInstancia).checked) {
		inicial = 'S';
	}
	if (osm_getObjeto('perm_adicionar_docs_' + idInstancia).checked) {
		adicionar_docs = 'S';
	}
	if (osm_getObjeto('perm_gestionar_docs_' + idInstancia).checked) {
		gestionar_docs = 'S';
	}
	
	if (osm_getObjeto('perm_ocultar_cargas_' + idInstancia).checked) {
		ocultar_sin_filtro = 'S';
	}
	
	instancias[idInstancia].aprobar = aprobar;
	instancias[idInstancia].rechazar = rechazar;
	instancias[idInstancia].inicial = inicial;
	instancias[idInstancia].aprobar_automaticamente = aprobar_automaticamente;
	instancias[idInstancia].rechazar_automaticamente = rechazar_automaticamente;
	instancias[idInstancia].solicitar_otp = solicitar_otp;
	instancias[idInstancia].adicionar_documentos = adicionar_docs;
	instancias[idInstancia].gestionar_documentos = gestionar_docs;
	instancias[idInstancia].ocultar_sin_filtro = ocultar_sin_filtro;
	
	$("#instancia_inicial_" + idInstancia).removeClass('instancia_inicial_N').removeClass('instancia_inicial_S');
	$("#instancia_inicial_" + idInstancia).addClass('instancia_inicial_'  + inicial);
		
	$("#instancia_final_" + idInstancia).removeClass('instancia_final_NNNN').removeClass('instancia_final_SSNN').removeClass('instancia_final_SNNN').removeClass('instancia_final_NSNN').removeClass('instancia_final_SNSN').removeClass('instancia_final_NSNS');
	$("#instancia_final_" + idInstancia).addClass('instancia_final_' + aprobar + rechazar + aprobar_automaticamente + rechazar_automaticamente);
}

function actualizarInstancia(idInstancia) {
	var exito = jsonrpc._("instanciaJsonServicio.actualizarInstancia")(instancias[idInstancia]);
	if (!exito) {
		alert("Hubo un error actualizando la instancia con id " + idInstancia);
	}
	return exito;
}

function pintarDetalleInstancia (instancia) {
	var parametros = obtenerParametrosInstancia (instancia);
	pintarInstancia('contenido_lista_inst_proceadmin', parametros);
}

function pintarInstancia(idPadre, parametros) {
	
	osm_construirHTML(idPadre, 'PLANTILLA_ITEM_INST_PROCEADMIN', parametros);

	var checkInicial = osm_getObjeto('inicial_inst_' + parametros[0]);
	if (parametros[2] == 'S') {
		checkInicial.checked = true;
	}

	var checkAprobar = osm_getObjeto('perm_aprobar_inst_' + parametros[0]);
	if (parametros[3] == 'S') {
		checkAprobar.checked = true;
	}

	var checkRechazar = osm_getObjeto('perm_rechazar_inst_' + parametros[0]);
	if (parametros[4] == 'S') {
		checkRechazar.checked = true;
	}
	
	var checkAdicionarDoc = osm_getObjeto('perm_adicionar_docs_' + parametros[0]);
	if (parametros[10] == 'S') {
		checkAdicionarDoc.checked = true;
	}
	var checkGestionarDoc = osm_getObjeto('perm_gestionar_docs_' + parametros[0]);
	if (parametros[11] == 'S') {
		checkGestionarDoc.checked = true;
	}
	
	var checkAprobarAuto = osm_getObjeto('perm_aprobar_auto_inst_' + parametros[0]);
	if (parametros[12] == 'S') {
		checkAprobarAuto.checked = true;
		$("#perm_rechazar_inst_" + parametros[0]).attr("disabled", true);
	}
	
	var checkRechazarAuto = osm_getObjeto('perm_rechazar_auto_inst_' + parametros[0]);
	if (parametros[13] == 'S') {
		checkRechazarAuto.checked = true;
		$("#perm_aprobar_inst_" + parametros[0]).attr("disabled", true);
	}
	
	var checkOcultarSinFiltro = osm_getObjeto('perm_ocultar_cargas_' + parametros[0]);
	if (parametros[15] == 'S') {
		checkOcultarSinFiltro.checked = true;
	}

	var selectFormSalida = osm_getObjeto('formato_salida_inst_' + parametros[0]);

	var optionNinguno = crearOption('option_form_sal_ninguno', '-- ninguno --', '');
	selectFormSalida.appendChild(optionNinguno);

	for ( var i = 0; i < formatosSalida.length; i++) {
		var formato = formatosSalida[i];
		var option = crearOption('option_form_sal_' + formato.id_formato, formato.nombre, formato.id_formato);
		selectFormSalida.appendChild(option);

		if (formato.id_formato == parametros[6]) {
			option.selected = true;
		}
	}

	listarAccionesInstancia(parametros[0]);
	listarAccionesRedireccionInstancia(parametros[0]);
	listarRolesInstancia(parametros[0]);
	
	aprobarRechazarToggle(parametros[0]);
	
	$("#div_info_instancia_" + parametros[0]).show();
	$("#blq_instancia_" + parametros[0]).show();
	
	$("#mensaje_sin_instancias").hide();
	$("#mensaje_seleccionar_instancia").hide();
	ajustarContenidoInstancia(osm_getWindowSize()[1]);
}

function aprobarRechazarToggle(idInstancia){
	var aprobar = osm_getObjeto('perm_aprobar_inst_' + idInstancia).checked;
	var rechazar = osm_getObjeto('perm_rechazar_inst_' + idInstancia).checked;
	if (aprobar == true && rechazar == true){
		$("#contenedor_aprobar_aut_" + idInstancia).hide();
		$("#perm_aprobar_auto_inst_" + idInstancia).prop("checked", false);
		$("#contenedor_rechazar_aut_" + idInstancia).hide();
		$("#perm_rechazar_auto_inst_" + idInstancia).prop("checked", false);
	}else if (aprobar == true) {
		$("#contenedor_aprobar_aut_" + idInstancia).show();
		$("#contenedor_rechazar_aut_" + idInstancia).hide();
		$("#perm_rechazar_auto_inst_" + idInstancia).prop("checked", false);
	}else if (rechazar == true) {
		$("#contenedor_rechazar_aut_" + idInstancia).show();
		$("#contenedor_aprobar_aut_" + idInstancia).hide();
		$("#perm_aprobar_auto_inst_" + idInstancia).prop("checked", false);
	}
}

// ***************** Seccion Acciones

function listarAccionesInstancia(idInstancia) {
	var accionesList = instancias[idInstancia].accionesPorInstancia.list;
	osm_setValor('contenido_acciones_inst_' + idInstancia, '');
	osm_setValor('menu_acciones_por_instancia_'+ idInstancia, '');
	if (accionesList.length > 0) {
		for (var i = 0; i < accionesList.length; i++) {
			var accion = accionesList[i];
			var nombre = accion.nombre.length > 20 ? accion.nombre.substr(0,20) + '...': accion.nombre;
			var parametros = [ accion.id_accion, nombre, accion.id_instancia, accion.mensaje_ejecucion];
			osm_construirHTML('menu_acciones_por_instancia_'+ idInstancia, 'PLANTILLA_ITEM_MENU_ACCIONES_INSTANCIA', parametros);
			
			$("#blq_acciones_instancia_" + idInstancia).get(0).idInstancia = idInstancia;
		}
	}
}

function crearAccion(idInstancia) {
	var idAccion = jsonrpc._("accionJsonServicio.obtenerSiguienteIdAccion")();
	var exitoCrear = jsonrpc._("accionJsonServicio.insertarAccion")(idAccion, 'Nueva', idInstancia);

	if (exitoCrear) {
		var accion = jsonrpc._("accionJsonServicio.obtenerAccion")(idAccion);

		instancias[idInstancia].accionesPorInstancia.list.push(accion); 
		instanciasDestino[idAccion] = accion;
		instanciasDestino[idAccion].instancias_destino = {javaClass: 'java.util.ArrayList', list: new Array()};

		var parametros = [ accion.id_accion, accion.nombre, accion.id_instancia, accion.mensaje_ejecucion ];
		osm_construirHTML('menu_acciones_por_instancia_'+ idInstancia, 'PLANTILLA_ITEM_MENU_ACCIONES_INSTANCIA', parametros);
	} else {
		alert('Ha ocurrido un error al crear la accion.');
	}
}

function eliminarAccion(idAccion, idInstancia) {
	var exitoEliminar = jsonrpc._("accionJsonServicio.borrarAccion")(idAccion);

	if (exitoEliminar) {
		$('#item_menu_accion_' + idAccion).remove();
		
		var acciones = jsonrpc._("accionJsonServicio.obtenerAccionesPorInstancia")(idInstancia);
		instancias[idInstancia].accionesPorInstancia = acciones;
		delete instanciasDestino[idAccion];
		
		$("[accion='" + idAccion + "']").remove();
	} else {
		alert('Ha ocurrido un error al eliminar la accion.');
	}
}

// ******************** Seccion Acciones Redireccion

function listarAccionesRedireccionInstancia(idInstancia) {
	var accionesRedireccion = jsonrpc._("accionRedireccionServicio.obtenerAccionesRedireccionPorInstancia")(idInstancia).list;
	osm_setValor('contenido_acciones_redireccion_inst_' + idInstancia, '');
	osm_setValor('menu_acciones_redireccion_por_instancia_' + idInstancia, '');

	if (accionesRedireccion.length > 0) {
		for (var i = 0; i < accionesRedireccion.length; i++) {
			var accionRedireccion = accionesRedireccion[i];
			var nombre = accionRedireccion.nombre.length > 20 ? accionRedireccion.nombre.substr(0,20) + '...': accionRedireccion.nombre;
			var parametros = [ accionRedireccion.id_accion_redireccion, nombre, 
			                   accionRedireccion.id_instancia, accionRedireccion.endpoint];
			
			osm_construirHTML('menu_acciones_redireccion_por_instancia_'+ idInstancia, 'PLANTILLA_ITEM_MENU_ACCIONES_REDIRECCION_INSTANCIA', parametros);
			
			$("#blq_acciones_redireccion_instancia_"+idInstancia).get(0).idInstancia = idInstancia;
		}
	}
}

function crearAccionRedireccion(idInstancia) {
	var idAccionRedireccion = jsonrpc._("accionRedireccionServicio.obtenerSiguienteId")();
	var accionRedireccion = {
		id_accion_redireccion: 	idAccionRedireccion,
		id_instancia: idInstancia,
		nombre: 'Nueva',
		endpoint: 'Nueva'
	};
	
	var exitoCrear = jsonrpc._("accionRedireccionServicio.guardarAccionRedireccion")(accionRedireccion);

	if (exitoCrear) {
		var parametros = [ accionRedireccion.id_accion_redireccion, accionRedireccion.nombre, accionRedireccion.id_instancia, accionRedireccion.endpoint ];
		osm_construirHTML('menu_acciones_redireccion_por_instancia_'+ idInstancia, 'PLANTILLA_ITEM_MENU_ACCIONES_REDIRECCION_INSTANCIA', parametros);
	} else {
		alert('Ha ocurrido un error al crear la accion de redireccion.');
	}
}

function eliminarAccionRedireccion(idAccionRedireccion, idInstancia) {
	var exitoEliminar = jsonrpc._("accionRedireccionServicio.borrarAccionRedireccion")(idAccionRedireccion);

	if (exitoEliminar) {
		$("#item_menu_accion_redireccion_" + idAccionRedireccion).remove();
	} else {
		alert('Ha ocurrido un error al eliminar la accion de redireccion.');
	}
}

// ******************** Seccion Roles

function listarRolesInstancia(idInstancia) {
	actualizarRolesDisponibles(idInstancia);
	var divContenidoRoles = osm_getObjeto('contenido_roles_inst_' + idInstancia);
	divContenidoRoles.cantidad_roles_inst = 0;

	var rolesInstancia = jsonrpc._("instanciaJsonServicio.obtenerRolesInstancia")(idInstancia);
	var rolesInstList = rolesInstancia.list;

	if (rolesInstList.length > 0) {
		$("#mensaje_sin_roles").hide();

		for ( var i = 0; i < rolesInstList.length; i++) {
			var rolInst = rolesInstList[i];
			var parametros = [ rolInst.id_rol, rolInst.nombre_rol, idInstancia ];

			osm_construirHTML('contenido_roles_inst_' + idInstancia, 'PLANTILLA_ITEM_ROL_INST', parametros);
		}
	}
	divContenidoRoles.cantidad_roles_inst = rolesInstList.length;
}

function actualizarRolesDisponibles(idInstancia) {
	var roles = jsonrpc._("instanciaJsonServicio.obtenerRolesDispParaAsignar")(idInstancia).list;

	osm_setValor('select_rol_inst_' + idInstancia, '');
	$('#select_rol_inst_' + idInstancia).find('option').remove();
	var selectRol = osm_getObjeto('select_rol_inst_' + idInstancia);

	var optionNinguno = crearOption('option_rol_ninguno', '-- seleccione --', '');
	selectRol.appendChild(optionNinguno);

	for (var i = 0; i < roles.length; i++) {
		var option = crearOption('option_rol_' + roles[i].id_rol, roles[i].nombre_rol, roles[i].id_rol);
		selectRol.appendChild(option);
	}
}

function asignarRol(idInstancia) {
	var idRol = osm_getValor("select_rol_inst_" + idInstancia);
	var exitoActualizar = false;

	if (!osm_esVacio(idRol)) {
		exitoActualizar = jsonrpc._("instanciaJsonServicio.insertarRolInstancia")(idRol, idInstancia);

		if (exitoActualizar) {
			var rolInst = jsonrpc._("instanciaJsonServicio.obtenerRolInstancia")(idRol, idInstancia);
			var parametros = [ rolInst.id_rol, rolInst.nombre_rol, idInstancia ];
			osm_construirHTML('contenido_roles_inst_' + idInstancia, 'PLANTILLA_ITEM_ROL_INST', parametros);
			
			var divContenidoRoles = osm_getObjeto('contenido_roles_inst_' + idInstancia);
			divContenidoRoles.cantidad_roles_inst++;
			actualizarRolesDisponibles(idInstancia);
			$("#mensaje_sin_roles").hide();
		} else {
			alert('No se pudo asignar el rol.');
		}
	} else {
		alert('Debe seleccionar un rol.');
	}
}

function eliminarRolInstancia(idRol, idInstancia) {
	var exitoEliminar = jsonrpc._("instanciaJsonServicio.borrarRolInstancia")(idRol, idInstancia);
	if (exitoEliminar) {
		$("#item_rol_" + idRol + "_inst_" + idInstancia).remove();

		var divContenidoRoles = osm_getObjeto('contenido_roles_inst_' + idInstancia);
		divContenidoRoles.cantidad_roles_inst--;
		if (divContenidoRoles.cantidad_roles_inst == 0) {
			$("#mensaje_sin_roles").show();
		}
		actualizarRolesDisponibles(idInstancia);

	} else {
		alert('Ha ocurrido un error al eliminar el rol.');
	}
}
