//----------------Acciones Instancia
var ARRAY_ACCIONES = new Array();

function listarAccionesInst(id_instancia) {

	var acciones = jsonrpc._("accionJsonServicio.obtenerAccionesPorInstancia")(id_instancia);
	ARRAY_ACCIONES[id_instancia]=acciones;
	var accionesList = acciones.list;

	var divContenidoAcciones = osm_getObjeto('contenido_acciones_inst_' + id_instancia);
	divContenidoAcciones.cantidad_acciones_inst = 0;


	if (accionesList.length > 0) {

		osm_setValor('contenido_acciones_inst_' + id_instancia, '');
		osm_setValor('menu_acciones_por_instancia_'+ id_instancia, '');

		for ( var i = 0; i < accionesList.length; i++) {

			var accion = accionesList[i];
			

			var parametros = [ accion.id_accion, accion.nombre, accion.id_instancia, accion.mensaje_ejecucion];
			
			pintarAccion('contenido_acciones_inst_' + id_instancia, parametros);
			
			//Si la accion es oculta se activa el check
			if(accion.oculto=='S'){
				var obj = osm_getObjeto('oculto_accion_'+accion.id_accion);
				obj.checked = true;
				
			}
			
			pintarItemMenuAccion('menu_acciones_por_instancia_'+ id_instancia, parametros);
			
			$("#blq_acciones_instancia_"+id_instancia).get(0).id_instancia = id_instancia;
			

		}
	}

	divContenidoAcciones.cantidad_acciones_inst = accionesList.length;

}

function listarAccionesRedireccionInst(id_instancia) {

	var acciones_redireccion = jsonrpc._("accionRedireccionServicio.obtenerAccionesRedireccionPorInstancia")(id_instancia);
	var accionesRedireccionList = acciones_redireccion.list;

	var divContenidoAccionesRedireccion = osm_getObjeto('contenido_acciones_redireccion_inst_' + id_instancia);
	divContenidoAccionesRedireccion.cantidad_acciones_redireccion_inst = 0;


	if (accionesRedireccionList.length > 0) {

		osm_setValor('contenido_acciones_redireccion_inst_' + id_instancia, '');
		osm_setValor('menu_acciones_redireccion_por_instancia_'+ id_instancia, '');

		for ( var i = 0; i < accionesRedireccionList.length; i++) {

			var accionRedireccion = accionesRedireccionList[i];
			

			var parametros = [ accionRedireccion.id_accion_redireccion, accionRedireccion.nombre, accionRedireccion.id_instancia, accionRedireccion.endpoint];
			
			pintarAccionRedireccion('contenido_acciones_redireccion_inst_' + id_instancia, parametros);
			
			pintarItemMenuAccionRedireccion('menu_acciones_redireccion_por_instancia_'+ id_instancia, parametros);
			
			$("#blq_acciones_redireccion_instancia_"+id_instancia).get(0).id_instancia = id_instancia;
			

		}
	}

	divContenidoAccionesRedireccion.cantidad_acciones_redireccion_inst = accionesRedireccionList.length;

}

function toggleAccion(id_instancia){
	$(".area_contenido_inst").hide();
				
	var contenidoAccion=osm_getObjeto("acciones_instancia_"+id_instancia);
	
	if($(contenidoAccion).is(":hidden")){
		$(contenidoAccion).slideDown(200);
		$(contenidoAccion).css("overflow-y", "auto");
	}else{
		$(contenidoAccion).slideUp(200);
	}
	
}

function toggleAccionRedireccion(id_instancia){
	$(".area_contenido_inst").hide();
				
	var contenidoAccionRedireccion=osm_getObjeto("acciones_redireccion_instancia_"+id_instancia);
	
	if($(contenidoAccionRedireccion).is(":hidden")){
		$(contenidoAccionRedireccion).slideDown(200);
		$(contenidoAccionRedireccion).css("overflow-y", "auto");
	}else{
		$(contenidoAccionRedireccion).slideUp(200);
	}
	
}

function toggleRoles(id_instancia){
	$(".area_contenido_inst").hide();
	
				
	var contenidoRoles=osm_getObjeto("roles_instancia_"+id_instancia);
	
	if($(contenidoRoles).is(":hidden")){
		$(contenidoRoles).slideDown(200);
		$(contenidoRoles).css("overflow-y", "auto");
	}else{
		$(contenidoRoles).slideUp(200);
	}
	
}

function toggleItemAccion(id_accion){
	
	
	var contenidoAccion=osm_getObjeto("div_accion_"+id_accion);
	
	if($(contenidoAccion).is(":hidden")){
		$(contenidoAccion).slideDown(200);
		$(contenidoAccion).css("overflow-y", "auto");
	}else{
		$(contenidoAccion).slideUp(200);
	}
	
}

function toggleItemAccionRedireccion(id_accion_redireccion){
	
	
	var contenidoAccionRedireccion=osm_getObjeto("div_accion_redireccion_"+id_accion_redireccion);
	
	if($(contenidoAccionRedireccion).is(":hidden")){
		$(contenidoAccionRedireccion).slideDown(200);
		$(contenidoAccionRedireccion).css("overflow-y", "auto");
	}else{
		$(contenidoAccionRedireccion).slideUp(200);
	}
	
}

function toggleInfoInstancia(id_instancia){
	
	$(".area_contenido_inst").hide();
	
	var contenidoInstancia=osm_getObjeto("div_info_instancia_"+id_instancia);
	
	if($(contenidoInstancia).is(":hidden")){
		$(contenidoInstancia).slideDown(200);
		$(contenidoInstancia).css("overflow-y", "auto");
	}else{
		$(contenidoInstancia).slideUp(200);
	}
}



function pintarAccion(id_padre, parametros) {

	osm_construirHTML(id_padre, 'PLANTILLA_ITEM_ACCION_INST', parametros);
	listarInstDest(parametros[0]);
	listarNotificacionesAccion(parametros[0]);
	listarAccionesAutomaticas(parametros[0]);
	listarOperacionesInternas(parametros[0]);
	listarCamposInfoAdicional(parametros[0]);
	listarRolesResponsable(parametros[0]);

}

function pintarAccionRedireccion(id_padre, parametros) {

	osm_construirHTML(id_padre, 'PLANTILLA_ITEM_ACCION_REDIRECCION_INST', parametros);

}

function pintarItemMenuAccion(id_padre, parametros){
	
	osm_construirHTML(id_padre, 'PLANTILLA_ITEM_MENU_ACCIONES_INSTANCIA', parametros);

}

function pintarItemMenuAccionRedireccion(id_padre, parametros){
	
	osm_construirHTML(id_padre, 'PLANTILLA_ITEM_MENU_ACCIONES_REDIRECCION_INSTANCIA', parametros);

}

function crearAccion(id_instancia) {

	var id_accion = jsonrpc._("accionJsonServicio.obtenerSiguienteIdAccion")();

	var exitoCrear = jsonrpc._("accionJsonServicio.insertarAccion")(id_accion, 'Nueva', id_instancia);

	if (exitoCrear) {

		var divContenidoAcciones = osm_getObjeto('contenido_acciones_inst_' + id_instancia);

		if (divContenidoAcciones.cantidad_acciones_inst == 0) {
			osm_setValor('contenido_acciones_inst_' + id_instancia, '');
		}

		var accion = jsonrpc._("accionJsonServicio.obtenerAccion")(id_accion);

		var parametros = [ accion.id_accion, accion.nombre, accion.id_instancia, accion.mensaje_ejecucion ];

		pintarAccion('contenido_acciones_inst_' + id_instancia, parametros);
		
		pintarItemMenuAccion('menu_acciones_por_instancia_'+ id_instancia, parametros);
		
		divContenidoAcciones.cantidad_acciones_inst++;

	} else {
		alert('Ha ocurrido un error al crear la accion.');
	}

}

function crearAccionRedireccion(id_instancia) {

	var id_accion_redireccion = jsonrpc._("accionRedireccionServicio.obtenerSiguienteId")();
	
	var accion_redireccion = {
		id_accion_redireccion: 	id_accion_redireccion,
		id_instancia: id_instancia,
		nombre: 'Nueva',
		endpoint: 'Nueva'
	};
	
	var exitoCrear = jsonrpc._("accionRedireccionServicio.guardarAccionRedireccion")(accion_redireccion);

	if (exitoCrear) {

		var divContenidoAccionesRedireccion = osm_getObjeto('contenido_acciones_redireccion_inst_' + id_instancia);

		if (divContenidoAccionesRedireccion.cantidad_acciones_redireccion_inst == 0) {
			osm_setValor('contenido_acciones_redireccion_inst_' + id_instancia, '');
		}

		var accion_redireccion = jsonrpc._("accionRedireccionServicio.obtenerAccionRedireccionPorId")(id_accion_redireccion);

		var parametros = [ accion_redireccion.id_accion_redireccion, accion_redireccion.nombre, accion_redireccion.id_instancia, accion_redireccion.endpoint ];

		pintarAccionRedireccion('contenido_acciones_redireccion_inst_' + id_instancia, parametros);
		
		pintarItemMenuAccionRedireccion('menu_acciones_redireccion_por_instancia_'+ id_instancia, parametros);
		
		divContenidoAccionesRedireccion.cantidad_acciones_redireccion_inst++;

	} else {
		alert('Ha ocurrido un error al crear la accion de redireccion.');
	}

}

ACCION_SELECCIONADA=null;
function mostrarVentanaEdicionAccion(id_accion){
	
	ACCION_SELECCIONADA=id_accion;
	
	osm_setValor("div_contenido_ventana_accion", '');
	$("#item_accion_"+id_accion).appendTo("#div_contenido_ventana_accion");
	
	osm_setVisible("VN_EDICION_ACCIONES", true, true);
	osm_ocultarSelects("bodyContent");
	
	
}

ACCION_REDIRECCION_SELECCIONADA=null;
function mostrarVentanaEdicionAccionRedireccion(id_accion_redireccion){
	
	ACCION_REDIRECCION_SELECCIONADA=id_accion_redireccion;
	
	osm_setValor("div_contenido_ventana_accion_redireccion", '');
	$("#item_accion_redireccion_"+id_accion_redireccion).appendTo("#div_contenido_ventana_accion_redireccion");
	
	osm_setVisible("VN_EDICION_ACCIONES_REDIRECCION", true, true);
	osm_ocultarSelects("bodyContent");
	
	
}

function cerrarVentanaEdicionAccion(){
	
	$("#item_accion_"+ACCION_SELECCIONADA).appendTo("#contenido_acciones_inst_"+INSTANCIA_SELECCIONADA);
	osm_setVisible("VN_EDICION_ACCIONES", false);
	osm_mostrarSelects("bodyContent");
	
	ACCION_SELECCIONADA=null;
	
}

function cerrarVentanaEdicionAccionRedireccion(){
	
	$("#item_accion_redireccion_"+ACCION_REDIRECCION_SELECCIONADA).appendTo("#contenido_acciones_redireccion_inst_"+INSTANCIA_SELECCIONADA);
	osm_setVisible("VN_EDICION_ACCIONES_REDIRECCION", false);
	osm_mostrarSelects("bodyContent");
	
	ACCION_REDIRECCION_SELECCIONADA=null;
	
}

function actualizarNombreAccion(id_accion, id_instancia) {

	var nombre = osm_getValor('nombre_accion_' + id_accion);

	var exitoActualizar = jsonrpc._("accionJsonServicio.actualizarNombre")(id_accion, nombre);

	if (exitoActualizar) {

		osm_setValor('subtitulo_item_accion_' + id_accion, nombre);
		
		osm_setValor('titulo_item_menu_acciones_' + id_accion, nombre);
		
		
		var acciones = jsonrpc._("accionJsonServicio.obtenerAccionesPorInstancia")(id_instancia);
		ARRAY_ACCIONES[id_instancia]=acciones;
		
		pintarLineasInstanciasDestino();
		
	} else {
		alert('No se pudo actualizar la instancia.');
	}

}

function actualizarNombreAccionRedireccion(id_accion_redireccion, id_instancia) {

	var nombre = osm_getValor('nombre_accion_redireccion_' + id_accion_redireccion);
	
	var exitoActualizar = jsonrpc._("accionRedireccionServicio.actualizarNombreAccionRedireccion")(id_accion_redireccion, nombre);

	if (exitoActualizar) {

		osm_setValor('subtitulo_item_accion_redireccion_' + id_accion_redireccion, nombre);
		
		osm_setValor('titulo_item_menu_acciones_redireccion_' + id_accion_redireccion, nombre);
				
	} else {
		alert('No se pudo actualizar la instancia.');
	}

}

function actualizarEndPointAccionRedireccion(id_accion_redireccion, id_instancia) {

	var endpoint = osm_getValor('endpoint_accion_redireccion_' + id_accion_redireccion);
	
	var exitoActualizar = jsonrpc._("accionRedireccionServicio.actualizarEndpointAccionRedireccion")(id_accion_redireccion, endpoint);

}

function actualizarTipoAccion(id_accion, id_instancia){
	
	var oculto = null;
	
	//Si esta seleccionada
	if(osm_getObjeto('oculto_accion_'+id_accion).checked == true){
		oculto = "S"
		
	}
	
	var exitoso =  jsonrpc._("accionJsonServicio.actualizarTipoAccion")(id_accion, oculto);
	
	if(!exitoso){
		osm_alert("No se pudo actualizar el tipo de accion");
	}else{
		
		var acciones = jsonrpc._("accionJsonServicio.obtenerAccionesPorInstancia")(id_instancia);
		ARRAY_ACCIONES[id_instancia]=acciones;
		
		pintarLineasInstanciasDestino();
		
	}
	
	
}

function eliminaAccion(id_accion, id_instancia) {

	var exitoEliminar = jsonrpc._("accionJsonServicio.borrarAccion")(id_accion);

	var divContenidoAcciones = osm_getObjeto('contenido_acciones_inst_' + id_instancia);

	if (exitoEliminar) {

		var objAccion = osm_getObjeto('item_accion_' + id_accion);
		var objPadre = osm_getObjeto('contenido_acciones_inst_' + id_instancia);
		objPadre.removeChild(objAccion);
		
		//Se elimina del menu
		var objMenuAccion = osm_getObjeto('item_menu_accion_' + id_accion);
		var objPadreMenu = osm_getObjeto('menu_acciones_por_instancia_' + id_instancia);
		objPadreMenu.removeChild(objMenuAccion);
		
		
		divContenidoAcciones.cantidad_acciones_inst--;
		
		var acciones = jsonrpc._("accionJsonServicio.obtenerAccionesPorInstancia")(id_instancia);
		ARRAY_ACCIONES[id_instancia]=acciones;
		
		pintarLineasInstanciasDestino();
	} else {
		alert('Ha ocurrido un error al eliminar la accion.');
	}

	if (divContenidoAcciones.cantidad_acciones_inst == 0) {
		osm_setValor('contenido_acciones_inst_' + id_instancia, 'No existen acciones.');
	}

}

function eliminaAccionRedireccion(id_accion_redireccion, id_instancia) {

	var exitoEliminar = jsonrpc._("accionRedireccionServicio.borrarAccionRedireccion")(id_accion_redireccion);

	var divContenidoAccionesRedireccion = osm_getObjeto('contenido_acciones_redireccion_inst_' + id_instancia);

	if (exitoEliminar) {

		var objAccionRedireccion = osm_getObjeto('item_accion_redireccion_' + id_accion_redireccion);
		var objPadre = osm_getObjeto('contenido_acciones_redireccion_inst_' + id_instancia);
		objPadre.removeChild(objAccionRedireccion);
		
		//Se elimina del menu
		var objMenuAccionRedireccion = osm_getObjeto('item_menu_accion_redireccion_' + id_accion_redireccion);
		var objPadreMenu = osm_getObjeto('menu_acciones_redireccion_por_instancia_' + id_instancia);
		objPadreMenu.removeChild(objMenuAccionRedireccion);
		
		
		divContenidoAccionesRedireccion.cantidad_acciones_redireccion_inst--;
		
	} else {
		alert('Ha ocurrido un error al eliminar la accion de redireccion.');
	}

	if (divContenidoAccionesRedireccion.cantidad_acciones_inst == 0) {
		osm_setValor('contenido_acciones_redireccion_inst_' + id_instancia, 'No existen acciones de redireccion.');
	}

}

// ----------------------------Roles Instancia-----------------------------

function listarRolesInst(id_instancia) {

	actualizarRolesDisponibles(id_instancia);
	var divContenidoRoles = osm_getObjeto('contenido_roles_inst_' + id_instancia);
	divContenidoRoles.cantidad_roles_inst = 0;

	var rolesInstancia = jsonrpc._("instanciaJsonServicio.obtenerRolesInstancia")(id_instancia);
	var rolesInstList = rolesInstancia.list;

	if (rolesInstList.length > 0) {

		osm_setValor('contenido_roles_inst_' + id_instancia, '');

		for ( var i = 0; i < rolesInstList.length; i++) {

			var rolInst = rolesInstList[i];

			var parametros = [ rolInst.id_rol, rolInst.nombre_rol, id_instancia ];

			pintarRol('contenido_roles_inst_' + id_instancia, parametros);

		}
	}

	divContenidoRoles.cantidad_roles_inst = rolesInstList.length;

}

function actualizarRolesDisponibles(id_instancia) {

	var roles = jsonrpc._("instanciaJsonServicio.obtenerRolesDispParaAsignar")(id_instancia);
	var rolList = roles.list;

	osm_setValor('select_rol_inst_' + id_instancia, '');
	var selectRol = osm_getObjeto('select_rol_inst_' + id_instancia);

	if (selectRol.hasChildNodes()) {
		while (selectRol.childNodes.length >= 1) {
			selectRol.removeChild(selectRol.firstChild);
		}
	}

	var option_ninguno = document.createElement("OPTION");
	option_ninguno.value = null;
	option_ninguno.id = 'option_rol_ninguno';
	option_ninguno.innerHTML = '-- seleccione --';
	selectRol.appendChild(option_ninguno);

	for ( var i = 0; i < rolList.length; i++) {

		var option = document.createElement("OPTION");

		var rol = rolList[i];

		option.value = rol.id_rol;
		option.id = 'option_rol_' + rol.id_rol;
		option.innerHTML = rol.nombre_rol;

		selectRol.appendChild(option);

	}

}

function pintarRol(id_padre, parametros) {

	osm_construirHTML(id_padre, 'PLANTILLA_ITEM_ROL_INST', parametros);

}

function asignarRol(id_instancia) {

	var selectRol = osm_getObjeto('select_rol_inst_' + id_instancia);
	var indiceSeleccionado = selectRol.selectedIndex;
	var opcionSeleccionada = selectRol.options[indiceSeleccionado];
	var id_rol_inst = opcionSeleccionada.value;

	var exitoActualizar = false;

	if (!osm_esVacio(id_rol_inst)) {

		exitoActualizar = jsonrpc._("instanciaJsonServicio.insertarRolInstancia")(id_rol_inst, id_instancia);

		if (exitoActualizar) {

			var rolInst = jsonrpc._("instanciaJsonServicio.obtenerRolInstancia")(id_rol_inst, id_instancia);

			var divContenidoRoles = osm_getObjeto('contenido_roles_inst_' + id_instancia);
			if (divContenidoRoles.cantidad_roles_inst == 0) {
				osm_setValor('contenido_roles_inst_' + id_instancia, '');
			}

			var parametros = [ rolInst.id_rol, rolInst.nombre_rol, id_instancia ];
			pintarRol('contenido_roles_inst_' + id_instancia, parametros);

			divContenidoRoles.cantidad_roles_inst++;

			actualizarRolesDisponibles(id_instancia);

		} else {
			alert('No se pudo asignar el rol.');
		}

	} else {
		alert('Debe seleccionar un rol.');
	}

}

function eliminarRolInst(id_rol, id_instancia) {

	var exitoEliminar = jsonrpc._("instanciaJsonServicio.borrarRolInstancia")(id_rol, id_instancia);

	var divContenidoRoles = $('#contenido_roles_inst_' + id_instancia).get(0);

	if (exitoEliminar) {

		var objRol = $('#contenido_roles_inst_' + id_instancia + ' > #item_rol_' + id_rol + '_inst_' + id_instancia).get(0);

		divContenidoRoles.removeChild(objRol);
		divContenidoRoles.cantidad_roles_inst--;

		actualizarRolesDisponibles(id_instancia);

	} else {
		alert('Ha ocurrido un error al eliminar el rol.');
	}

	if (divContenidoRoles.cantidad_roles_inst == 0) {
		osm_setValor('contenido_roles_inst_' + id_instancia, 'No existen roles.');
	}
}

// -----------------------------Instancias Destino---------------------------

var ID_PROCESO_ADMIN_SELECCIONADO = 0;

function setIdProcesoAdminSeleccionado(id_proceso_admin) {

	ID_PROCESO_ADMIN_SELECCIONADO = id_proceso_admin;

}

var ARRAY_INST_DESTINO =  new Object();
function listarInstDest(id_accion) {

	actualizarSelectInstDest(id_accion);
	var divContenidoInstDest = osm_getObjeto('contenido_instancias_dest_' + id_accion);
	divContenidoInstDest.cantidad_inst_dest = 0;

	var instanciasDestino = jsonrpc._("accionJsonServicio.obtenerInstanciasDestinoPorAccion")(id_accion);
	ARRAY_INST_DESTINO[id_accion]= instanciasDestino;
	 
	var instDestList = instanciasDestino.list;

	if (instDestList.length > 0) {

		osm_setValor('contenido_instancias_dest_' + id_accion, '');

		for ( var i = 0; i < instDestList.length; i++) {

			var instDest = instDestList[i];

			var parametros = [ id_accion, instDest.id_instancia, instDest.nombre ];

			pintarInstDest('contenido_instancias_dest_' + id_accion, parametros);

		
		}
	}

	divContenidoInstDest.cantidad_inst_dest = instDestList.length;

}


function actualizarTodosSelectInstDest() {

	var acciones = jsonrpc._("accionJsonServicio.obtenerAccionesPorProceso")(ID_PROCESO_ADMIN_SELECCIONADO);
	var accionesList = acciones.list;

	for ( var i = 0; i < accionesList.length; i++) {

		var accion = accionesList[i];

		actualizarSelectInstDest(accion.id_accion);

	}

}

function actualizarSelectInstDest(id_accion) {

	var instanciasDest = jsonrpc._("accionJsonServicio.obtenerInstDisponiblesParaAsignar")(ID_PROCESO_ADMIN_SELECCIONADO, id_accion);
	var instDestList = instanciasDest.list;

	osm_setValor('select_inst_dest_' + id_accion, '');
	var selectInstDest = osm_getObjeto('select_inst_dest_' + id_accion);

	if (selectInstDest.hasChildNodes()) {
		while (selectInstDest.childNodes.length >= 1) {
			selectInstDest.removeChild(selectInstDest.firstChild);
		}
	}

	var option_ninguno = document.createElement("OPTION");
	option_ninguno.value = null;
	option_ninguno.id = 'option_rol_ninguno';
	option_ninguno.innerHTML = '-- seleccione --';
	selectInstDest.appendChild(option_ninguno);

	for ( var i = 0; i < instDestList.length; i++) {

		var option = document.createElement("OPTION");

		var instDest = instDestList[i];

		option.value = instDest.id_instancia;
		option.id = 'option_inst_dest_' + instDest.id_instancia;
		option.innerHTML = instDest.nombre;

		selectInstDest.appendChild(option);

	}

}

function pintarInstDest(id_padre, parametros) {

	osm_construirHTML(id_padre, 'PLANTILLA_ITEM_INST_DEST', parametros);

}

function asignarInstDest(id_accion) {

	var selectInstDest = osm_getObjeto('select_inst_dest_' + id_accion);
	var indiceSeleccionado = selectInstDest.selectedIndex;
	var opcionSeleccionada = selectInstDest.options[indiceSeleccionado];
	var id_instancia_destino = opcionSeleccionada.value;

	var exitoActualizar = false;

	if (!osm_esVacio(id_instancia_destino)) {

		exitoActualizar = jsonrpc._("accionJsonServicio.insertarInstaciaDestino")(id_accion, id_instancia_destino);

		if (exitoActualizar) {

			var instDest = jsonrpc._("accionJsonServicio.obtenerInstanciaDestino")(id_accion, id_instancia_destino);

			var divContenidoInstDest = osm_getObjeto('contenido_instancias_dest_' + id_accion);

			if (divContenidoInstDest.cantidad_inst_dest == 0) {
				osm_setValor('contenido_instancias_dest_' + id_accion, '');
			}

			var parametros = [ id_accion, instDest.id_instancia, instDest.nombre ];
			pintarInstDest('contenido_instancias_dest_' + id_accion, parametros);

			divContenidoInstDest.cantidad_inst_dest++;
			
			var instanciasDestino = jsonrpc._("accionJsonServicio.obtenerInstanciasDestinoPorAccion")(id_accion);
			ARRAY_INST_DESTINO[id_accion]= instanciasDestino;
			
			pintarLineasInstanciasDestino();

			actualizarSelectInstDest(id_accion);

		} else {
			alert('No se pudo asignar la instancia.');
		}

	} else {
		alert('Debe seleccionar una instancia.');
	}

}

function eliminarInstDest(id_accion, id_instancia_destino) {

	var exitoEliminar = jsonrpc._("accionJsonServicio.borrarInstanciaDestino")(id_accion, id_instancia_destino);

	var divContenidoInstDest = $('#contenido_instancias_dest_' + id_accion).get(0);

	if (exitoEliminar) {

		var objInstDest = $('#contenido_instancias_dest_' + id_accion + ' > #item_accion_' + id_accion + '_inst_dest_' + id_instancia_destino).get(0);

		divContenidoInstDest.removeChild(objInstDest);
		divContenidoInstDest.cantidad_inst_dest--;
		
		var instanciasDestino = jsonrpc._("accionJsonServicio.obtenerInstanciasDestinoPorAccion")(id_accion);
		ARRAY_INST_DESTINO[id_accion]= instanciasDestino;
		
		pintarLineasInstanciasDestino();

		actualizarSelectInstDest(id_accion);

	} else {
		alert('Ha ocurrido un error al eliminar la instancia.');
	}

	if (divContenidoInstDest.cantidad_inst_dest == 0) {
		osm_setValor('contenido_instancias_dest_' + id_accion, 'No existen instancias de destino.');
	}
}

// -----------------------Notificaciones Accion---------------------------------

function listarNotificacionesAccion(id_accion) {

	var divContenidoNotiAcc = osm_getObjeto('contenido_notificaciones_accion_' + id_accion);
	divContenidoNotiAcc.cantidad_notificaciones_accion = 0;

	var notificaciones = jsonrpc._("notificacionAccionJsonServicio.obtenerNotificacionesAccPorAccion")(id_accion);
	var notiAccList = notificaciones.list;

	if (notiAccList.length > 0) {

		osm_setValor('contenido_notificaciones_accion_' + id_accion, '');

		for ( var i = 0; i < notiAccList.length; i++) {

			var notiAcc = notiAccList[i];

			var mensaje = '';
			if (!osm_esVacio(notiAcc.mensaje)) {
				mensaje = notiAcc.mensaje;
			}

			var parametros = [ notiAcc.id_notificacion_accion, notiAcc.tipo, notiAcc.correo, notiAcc.id_administrativo, mensaje, id_accion ];

			pintarNotificacionAccion('contenido_notificaciones_accion_' + id_accion, parametros);

		}
	}

	divContenidoNotiAcc.cantidad_notificaciones_accion = notiAccList.length;

}

function pintarNotificacionAccion(id_padre, parametros) {

	osm_construirHTML(id_padre, 'PLANTILLA_ITEM_NOTI_ACC', parametros);

	var selectTipoNotiAcc = osm_getObjeto('select_tipo_noti_acc_' + parametros[0]);
	if (selectTipoNotiAcc!=null && selectTipoNotiAcc.hasChildNodes()) {
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

function construirInputTipoCorreo(id_notificacion_accion, correo) {
	var inputCorreo = document.createElement('INPUT');
	inputCorreo.onchange = new Function('actualizarNotiAcc(' + id_notificacion_accion + ');');
	inputCorreo.className = "form-control";
	inputCorreo.id = "input_correo_noti_acc_" + id_notificacion_accion;
	osm_setValor('valor_tipo_noti_acc_' + id_notificacion_accion, '');
	osm_getObjeto('valor_tipo_noti_acc_' + id_notificacion_accion).appendChild(inputCorreo);
	inputCorreo.value = correo;
}

function construirSelectTipoAdministrativo(id_notificacion_accion, id_administrativo) {

	var administrativos = jsonrpc._("administrativoJsonServicio.obtenerAdministrativosActivos")();
	var adminvList = administrativos.list;

	var selectAdminv = document.createElement('SELECT')
	selectAdminv.className = "cajatextoselector w140";
	selectAdminv.id = "select_adminv_noti_acc_" + id_notificacion_accion;

	selectAdminv.onchange = new Function('actualizarNotiAcc(' + id_notificacion_accion + ');');

	osm_setValor('valor_tipo_noti_acc_' + id_notificacion_accion, '');
	osm_getObjeto('valor_tipo_noti_acc_' + id_notificacion_accion).appendChild(selectAdminv);

	osm_setValor('select_adminv_noti_acc_' + id_notificacion_accion, '');

	var option_ninguno = document.createElement("OPTION");
	option_ninguno.value = null;
	option_ninguno.id = 'option_adminv_noti_acc_ninguno';
	option_ninguno.innerHTML = '-- ninguno --';
	selectAdminv.appendChild(option_ninguno);

	for ( var i = 0; i < adminvList.length; i++) {

		var option = document.createElement("OPTION");

		var administrativo = adminvList[i];

		option.value = administrativo.id_administrativo;
		option.id = 'option_adminv_noti_acc_' + administrativo.id_administrativo;
		option.innerHTML = administrativo.nombre + ' ' + administrativo.apellido;

		selectAdminv.appendChild(option);

		if (administrativo.id_administrativo == id_administrativo) {
			option.selected = true;
		}

	}

}

function actualizarTipoNotiAcc(id_notificacion_accion) {

	var selectTipoNotiAcc = osm_getObjeto('select_tipo_noti_acc_' + id_notificacion_accion);
	var indiceSeleccionado = selectTipoNotiAcc.selectedIndex;
	var opcionSeleccionada = selectTipoNotiAcc.options[indiceSeleccionado];
	var tipo = opcionSeleccionada.value;

	if (tipo == 'C') {
		osm_setValor('titulo_tipo_noti_acc_' + id_notificacion_accion, 'Correo');
		construirInputTipoCorreo(id_notificacion_accion, '');
	} else if (tipo == 'A') {
		osm_setValor('titulo_tipo_noti_acc_' + id_notificacion_accion, 'Administrativo');
		construirSelectTipoAdministrativo(id_notificacion_accion, null);
	} else if (tipo != 'C' && tipo != 'A') {
		osm_setValor('titulo_tipo_noti_acc_' + id_notificacion_accion, '');
		osm_setValor('valor_tipo_noti_acc_' + id_notificacion_accion, '');
	}

	actualizarNotiAcc(id_notificacion_accion);

}

function crearNotiAcc(id_accion) {

	var id_notificacion_accion = jsonrpc._("notificacionAccionJsonServicio.obtenerSiguienteIDNotificacionAccion")();

	var exitoCrear = jsonrpc._("notificacionAccionJsonServicio.insertarNotificacionAccion")(id_notificacion_accion, id_accion, '', null, null, '');

	if (exitoCrear) {

		var divContenidoNotiAcc = osm_getObjeto('contenido_notificaciones_accion_' + id_accion);

		if (divContenidoNotiAcc.cantidad_notificaciones_accion == 0) {
			osm_setValor('contenido_notificaciones_accion_' + id_accion, '');
		}

		var parametros = [ id_notificacion_accion, '', null, null, '', id_accion ];

		pintarNotificacionAccion('contenido_notificaciones_accion_' + id_accion, parametros);

		divContenidoNotiAcc.cantidad_notificaciones_accion++;

	} else {
		alert('Ha ocurrido un error al crear la notificacion.');
	}

}

function aliminarNotiAcc(id_notificacion_accion, id_accion) {

	var exitoEliminar = jsonrpc._("notificacionAccionJsonServicio.borrarNotificacionAccion")(id_notificacion_accion);

	var divContenidoNotiAcc = osm_getObjeto('contenido_notificaciones_accion_' + id_accion);

	if (exitoEliminar) {

		var objNotiAcc = osm_getObjeto('item_noti_acci_' + id_notificacion_accion);

		divContenidoNotiAcc.removeChild(objNotiAcc);

		divContenidoNotiAcc.cantidad_notificaciones_accion--;

		if (divContenidoNotiAcc.cantidad_notificaciones_accion == 0) {
			osm_setValor('contenido_notificaciones_accion_' + id_accion, 'No existen notificaciones.');
		}

	} else {
		alert('Ha ocurrido un error al eliminar la notificacion.');
	}

}

function actualizarNotiAcc(id_notificacion_accion) {

	var selectTipoNotiAcc = osm_getObjeto('select_tipo_noti_acc_' + id_notificacion_accion);
	var indiceSeleccionadoTipo = selectTipoNotiAcc.selectedIndex;
	var opcionSeleccionadaTipo = selectTipoNotiAcc.options[indiceSeleccionadoTipo];
	var tipo = opcionSeleccionadaTipo.value;

	var correo = null;
	if (tipo == 'C') {
		correo = osm_getValor('input_correo_noti_acc_' + id_notificacion_accion);
	}

	var id_administrativo = null;
	if (tipo == 'A') {
		var selectAdminv = osm_getObjeto('select_adminv_noti_acc_' + id_notificacion_accion);
		var indiceSeleccionadoAdminv = selectAdminv.selectedIndex;
		var opcionSeleccionadaAdminv = selectAdminv.options[indiceSeleccionadoAdminv];
		id_administrativo = opcionSeleccionadaAdminv.value;
		id_administrativo = (id_administrativo == "null")?null:parseInt(id_administrativo);
	}

	var mensaje = osm_getValor('areatexto_mensaje_noti_acc_' + id_notificacion_accion);

	jsonrpc._("notificacionAccionJsonServicio.actualizarNotificacionAccion")(id_notificacion_accion, tipo, correo, id_administrativo, mensaje)
}


//-----------------------------OPERACIONES INTERNAS---------------------------

var ID_PROCESO_ADMIN_SELECCIONADO = 0;

function setIdProcesoAdminSeleccionado(id_proceso_admin) {

	ID_PROCESO_ADMIN_SELECCIONADO = id_proceso_admin;

}

var ARRAY_OPERACION_INTERNA =  new Object();

function listarOperacionesInternas(id_accion) {

	actualizarSelectOperacionInterna(id_accion);
	var divContenidoOperacionInterna = osm_getObjeto('contenido_operacion_interna_' + id_accion);
	divContenidoOperacionInterna.cantidad_operaciones_internas = 0;

	var operacionesInternasAccion = jsonrpc._("accionOperacionInterna.obtenerListaOperacionesInternasPorAccion")(id_accion);
//	ARRAY_OPERACION_INTERNA[id_accion]= operacionesInternasAccion;
	 
	var listaOperacionesInternasAccion = operacionesInternasAccion.list;

	if (listaOperacionesInternasAccion.length > 0) {

		osm_setValor('contenido_operacion_interna_' + id_accion, '');

		for ( var i = 0; i < listaOperacionesInternasAccion.length; i++) {

			var operacionInterna = listaOperacionesInternasAccion[i];

			var parametros = [ id_accion, operacionInterna.id_operacion_interna, operacionInterna.nombre ];

			pintarOperacionesInternas('contenido_operacion_interna_' + id_accion, parametros);
		}
	}

	divContenidoOperacionInterna.cantidad_operaciones_internas = listaOperacionesInternasAccion.length;

}

function actualizarSelectOperacionInterna(id_accion) {

	var operacionesInternas = jsonrpc._("accionOperacionInterna.obtenerListaOperacionesInternas")();
	var listaOperacionesInternas = operacionesInternas.list;
	var operacionesInternasPorAccion = jsonrpc._("accionOperacionInterna.obtenerListaOperacionesInternasPorAccion")(id_accion);
	var listaOperacionesInternasPorAccion = operacionesInternasPorAccion.list;
		for ( var opiAc = 0 ; opiAc < listaOperacionesInternasPorAccion.length ; opiAc++ ){
			for ( var opi = 0 ; opi < listaOperacionesInternas.length ; opi++ ){
				if(listaOperacionesInternasPorAccion[opiAc].id_operacion_interna == listaOperacionesInternas[opi].id_operacion_interna){
					listaOperacionesInternas.splice(opi,1);
				}
			}
		}
	
	osm_setValor('select_operacion_interna_' + id_accion, '');
	var selectOperacionInterna = osm_getObjeto('select_operacion_interna_' + id_accion);

	if (selectOperacionInterna.hasChildNodes()) {
		while (selectOperacionInterna.childNodes.length >= 1) {
			selectOperacionInterna.removeChild(selectOperacionInterna.firstChild);
		}
	}

	var option_ninguno = document.createElement("OPTION");
	option_ninguno.value = null;
	option_ninguno.id = 'option_rol_ninguno';
	option_ninguno.innerHTML = '-- seleccione --';
	selectOperacionInterna.appendChild(option_ninguno);

	for ( var i = 0; i < listaOperacionesInternas.length; i++) {

		var option = document.createElement("OPTION");

		var operacionInterna = listaOperacionesInternas[i];

		option.value = operacionInterna.id_operacion_interna;
		option.id = 'option_operacion_interna_' + operacionInterna.id_operacion_interna;
		option.innerHTML = operacionInterna.nombre;

		selectOperacionInterna.appendChild(option);

	}

}

function pintarOperacionesInternas(id_padre, parametros) {

	osm_construirHTML(id_padre, 'PLANTILLA_ITEM_OPERACION_INTERNA', parametros);

}

function asignarOperacionInterna(id_accion) {

	var selectOperacionInterna = osm_getObjeto('select_operacion_interna_' + id_accion);
	var indiceSeleccionado = selectOperacionInterna.selectedIndex;
	var opcionSeleccionada = selectOperacionInterna.options[indiceSeleccionado];
	var id_operacion_interna = opcionSeleccionada.value;

	var exitoActualizar = false;

	if (!osm_esVacio(id_operacion_interna)) {

		exitoActualizar = jsonrpc._("accionOperacionInterna.insertarOperacionInternaAccion")(id_operacion_interna, id_accion);

		if (exitoActualizar) {

			var operacionInterna = jsonrpc._("accionOperacionInterna.obtenerOperacionInterna")(id_operacion_interna);

			var divContenidoOperacionInterna = osm_getObjeto('contenido_operacion_interna_' + id_accion);

			if (divContenidoOperacionInterna.cantidad_inst_dest == 0) {
				osm_setValor('contenido_operacion_interna_' + id_accion, '');
			}

			var parametros = [ id_accion, operacionInterna.id_operacion_interna, operacionInterna.nombre ];
			pintarOperacionesInternas('contenido_operacion_interna_' + id_accion, parametros);

			divContenidoOperacionInterna.cantidad_inst_dest++;
			
//			var operacionesInternasAccion = jsonrpc._("accionOperacionInterna.obtenerListaOperacionesInternasPorAccion")(id_accion);
//			ARRAY_OPERACION_INTERNA[id_accion]= operacionesInternasAccion;
			
			//pintarLineasoperacionesInternasAccion();

			actualizarSelectOperacionInterna(id_accion);

		} else {
			alert('No se pudo asignar la operacion.');
		}

	} else {
		alert('Debe seleccionar una operacion.');
	}

}

function eliminarOperacionInterna(id_accion, id_operacion_interna) {

	var exitoEliminar = jsonrpc._("accionOperacionInterna.borrarOperacionInternaAccion")( id_operacion_interna, id_accion);

	var divContenidoOperacionInterna = $('#contenido_operacion_interna_' + id_accion).get(0);

	if (exitoEliminar) {

		var objOperacionInterna = $('#contenido_operacion_interna_' + id_accion + ' > #item_accion_' + id_accion + '_operacion_interna_' + id_operacion_interna).get(0);

		divContenidoOperacionInterna.removeChild(objOperacionInterna);
		divContenidoOperacionInterna.cantidad_operaciones_internas--;
		
//		var operacionesInternasAccion = jsonrpc._("accionOperacionInterna.obtenerListaOperacionesInternasPorAccion")(id_accion);
//		ARRAY_OPERACION_INTERNA[id_accion]= operacionesInternasAccion;
		
		//pintarLineasoperacionesInternasAccion();

		actualizarSelectOperacionInterna(id_accion);

	} else {
		alert('Ha ocurrido un error al eliminar la operacion.');
	}

	if (divContenidoOperacionInterna.cantidad_operaciones_internas == 0) {
		osm_setValor('contenido_operacion_interna_' + id_accion, 'No existen operaciones internas.');
	}
}

//-----------------------------Campos de informacion adicional----------------

var ID_PROCESO_ADMIN_SELECCIONADO = 0;

function setIdProcesoAdminSeleccionado(id_proceso_admin) {

	ID_PROCESO_ADMIN_SELECCIONADO = id_proceso_admin;

}

function listarCamposInfoAdicional(id_accion) {

	actualizarSelectInfoAdicional(id_accion);
	var divContenidoInfoAdicional = osm_getObjeto('contenido_info_adicional_' + id_accion);
	divContenidoInfoAdicional.cantidad_campos_info_adicional = 0;

	var camposInfoAdicional = jsonrpc._("accionInfoAdicionalServicio.obtenerCamposInfoAdicionalPorAccion")(id_accion);
	 
	var listaCamposInfoAdicional = camposInfoAdicional.list;

	if (listaCamposInfoAdicional.length > 0) {

		osm_setValor('contenido_info_adicional_' + id_accion, '');

		for ( var i = 0; i < listaCamposInfoAdicional.length; i++) {

			var campoInfoAdicional = listaCamposInfoAdicional[i];

			var parametros = [ id_accion, campoInfoAdicional.id_campo, campoInfoAdicional.nombre ];

			pintarInfoAdicional('contenido_info_adicional_' + id_accion, parametros);
		}
	}

	divContenidoInfoAdicional.cantidad_campos_info_adicional = listaCamposInfoAdicional.length;

}

function actualizarSelectInfoAdicional(id_accion) {

	var camposInfoAdicional = jsonrpc._("accionInfoAdicionalServicio.obtenerListaCamposInfoAdicionalDisponiblesPorAccion")(id_accion);
	var listaCamposInfoAdicional = camposInfoAdicional.list;
	
	osm_setValor('select_info_adicional_' + id_accion, '');
	var selectInfoAdicional = osm_getObjeto('select_info_adicional_' + id_accion);

	if (selectInfoAdicional.hasChildNodes()) {
		while (selectInfoAdicional.childNodes.length >= 1) {
			selectInfoAdicional.removeChild(selectInfoAdicional.firstChild);
		}
	}

	var option_ninguno = document.createElement("OPTION");
	option_ninguno.value = null;
	option_ninguno.id = 'option_info_ninguno';
	option_ninguno.innerHTML = '-- seleccione --';
	selectInfoAdicional.appendChild(option_ninguno);

	for ( var i = 0; i < listaCamposInfoAdicional.length; i++) {

		var option = document.createElement("OPTION");

		var campoInfoAdicional = listaCamposInfoAdicional[i];

		option.value = campoInfoAdicional.id_campo;
		option.id = 'option_info_adicional_' + campoInfoAdicional.id_campo;
		option.innerHTML = campoInfoAdicional.nombre;

		selectInfoAdicional.appendChild(option);

	}

}

function pintarInfoAdicional(id_padre, parametros) {

	osm_construirHTML(id_padre, 'PLANTILLA_ITEM_INFO_ADICIONAL', parametros);

}

function asignarCamposInfoAdicional(id_accion) {

	var selectInfoAdicional = osm_getObjeto('select_info_adicional_' + id_accion);
	var indiceSeleccionado = selectInfoAdicional.selectedIndex;
	var opcionSeleccionada = selectInfoAdicional.options[indiceSeleccionado];
	var id_campo = opcionSeleccionada.value;

	var exitoActualizar = false;

	if (!osm_esVacio(id_campo)) {

		exitoActualizar = jsonrpc._("accionInfoAdicionalServicio.insertarAccionCampoInfoAdicional")(id_campo, id_accion);

		if (exitoActualizar) {

			var campo = jsonrpc._("accionInfoAdicionalServicio.obtenerCampoInfoAdicional")(id_campo);

			var divContenidoInfoAdicional = osm_getObjeto('contenido_info_adicional_' + id_accion);

			if (divContenidoInfoAdicional.cantidad_inst_dest == 0) {
				osm_setValor('contenido_info_adicional_' + id_accion, '');
			}

			var parametros = [ id_accion, campo.id_campo, campo.nombre ];
			pintarInfoAdicional('contenido_info_adicional_' + id_accion, parametros);

			divContenidoInfoAdicional.cantidad_inst_dest++;

			actualizarSelectInfoAdicional(id_accion);

		} else {
			alert('No se pudo seleccionar el campo.');
		}

	} else {
		alert('Debe seleccionar un campo.');
	}

}

function eliminarCampoInfoAdicional(id_accion, id_campo) {

	var exitoEliminar = jsonrpc._("accionInfoAdicionalServicio.borrarAccionCampoInfoAdicional")( id_campo, id_accion);

	var divContenidoInfoAdicional = $('#contenido_info_adicional_' + id_accion).get(0);

	if (exitoEliminar) {

		var objCampoInfoAdicional = $('#contenido_info_adicional_' + id_accion + ' > #item_accion_' + id_accion + '_info_adicional_' + id_campo).get(0);

		divContenidoInfoAdicional.removeChild(objCampoInfoAdicional);
		divContenidoInfoAdicional.cantidad_campos_info_adicional--;

		actualizarSelectInfoAdicional(id_accion);

	} else {
		alert('Ha ocurrido un error al eliminar el campo.');
	}

	if (divContenidoInfoAdicional.cantidad_campos_info_adicional == 0) {
		osm_setValor('contenido_info_adicional_' + id_accion, 'No existen campos seleccionados para diligenciar informacion adicional.');
	}
}

//-----------------------------Asigancion responsable ----------------------------


function listarRolesResponsable(id_accion) {

	actualizarSelectRolesResponsable(id_accion);
	var divContenidoResponsable = osm_getObjeto('contenido_responsable_' + id_accion);
	divContenidoResponsable.cantidad_roles_responsable = 0;

	var rolesResponsable = jsonrpc._("accionResponsableServicio.obtenerRolesPorAccion")(id_accion);
	 
	var listaRolesResponsable = rolesResponsable.list;

	if (listaRolesResponsable.length > 0) {

		osm_setValor('contenido_responsable_' + id_accion, '');

		for ( var i = 0; i < listaRolesResponsable.length; i++) {

			var rol = listaRolesResponsable[i];

			var parametros = [ id_accion, rol.id_rol, rol.nombre_rol ];

			pintarRolesResponsable('contenido_responsable_' + id_accion, parametros);
		}
	}

	divContenidoResponsable.cantidad_roles_responsable = listaRolesResponsable.length;

}

function actualizarSelectRolesResponsable(id_accion) {

	var rolesResponsable = jsonrpc._("accionResponsableServicio.obtenerRolesActivosDisponiblesAccion")(id_accion);
	var listaRolesResponsable = rolesResponsable.list;
	
	osm_setValor('select_responsable_' + id_accion, '');
	var selectRolesResponsable = osm_getObjeto('select_responsable_' + id_accion);

	if (selectRolesResponsable.hasChildNodes()) {
		while (selectRolesResponsable.childNodes.length >= 1) {
			selectRolesResponsable.removeChild(selectRolesResponsable.firstChild);
		}
	}

	var option_ninguno = document.createElement("OPTION");
	option_ninguno.value = null;
	option_ninguno.id = 'option_info_ninguno';
	option_ninguno.innerHTML = '-- seleccione --';
	selectRolesResponsable.appendChild(option_ninguno);

	for ( var i = 0; i < listaRolesResponsable.length; i++) {

		var option = document.createElement("OPTION");

		var rol = listaRolesResponsable[i];

		option.value = rol.id_rol;
		option.id = 'option_responsable_' + rol.id_rol;
		option.innerHTML = rol.nombre_rol;

		selectRolesResponsable.appendChild(option);

	}

}

function pintarRolesResponsable(id_padre, parametros) {
	osm_construirHTML(id_padre, 'PLANTILLA_ITEM_ROL_RESPONSABLE', parametros);
}

function asignarRolResponsable(id_accion) {

	var selectRolesResponsable = osm_getObjeto('select_responsable_' + id_accion);
	var indiceSeleccionado = selectRolesResponsable.selectedIndex;
	var opcionSeleccionada = selectRolesResponsable.options[indiceSeleccionado];
	var id_rol = opcionSeleccionada.value;

	var exitoActualizar = false;

	if (!osm_esVacio(id_rol)) {

		exitoActualizar = jsonrpc._("accionResponsableServicio.insertarAccionRol")(id_rol, id_accion);

		if (exitoActualizar) {

			var campo = jsonrpc._("accionResponsableServicio.obtenerRol")(id_rol);

			var divContenidoResponsable = osm_getObjeto('contenido_responsable_' + id_accion);

			if (divContenidoResponsable.cantidad_inst_dest == 0) {
				osm_setValor('contenido_responsable_' + id_accion, '');
			}

			var parametros = [ id_accion, campo.id_rol, campo.nombre_rol ];
			pintarRolesResponsable('contenido_responsable_' + id_accion, parametros);

			divContenidoResponsable.cantidad_inst_dest++;

			actualizarSelectRolesResponsable(id_accion);

		} else {
			alert('No se pudo seleccionar el rol.');
		}

	} else {
		alert('Debe seleccionar un rol.');
	}

}

function eliminarRol(id_accion, id_rol) {

	var exitoEliminar = jsonrpc._("accionResponsableServicio.borrarAccionRol")( id_rol, id_accion);

	var divContenidoResponsable = $('#contenido_responsable_' + id_accion).get(0);

	if (exitoEliminar) {

		var objrol = $('#contenido_responsable_' + id_accion + ' > #item_accion_' + id_accion + '_responsable_' + id_rol).get(0);

		divContenidoResponsable.removeChild(objrol);
		divContenidoResponsable.cantidad_campos_info_adicional--;

		actualizarSelectRolesResponsable(id_accion);

	} else {
		alert('Ha ocurrido un error al eliminar el rol.');
	}

	if (divContenidoResponsable.cantidad_campos_info_adicional == 0) {
		osm_setValor('contenido_responsable_' + id_accion, 'No existen roles seleccionados para diligenciar asignacion de responsable.');
	}
}



//-----------------------------ACCION AUTOM\u00E1TICA---------------------------


function listarAccionesAutomaticas(id_accion) {
	var listasDinamicas = [];
	var ac_elegida = null;
//obtenerListasDinamicas
	var respuesta = jsonrpc._("accionJsonServicio.obtenerListasDinamicas")();
	listasDinamicas = respuesta != null ? respuesta.list : [];
	ac_elegida = jsonrpc._("accionJsonServicio.obtenerAccionAutomatica")(id_accion);
	if (ac_elegida) {
		for (var int = 0; int < listasDinamicas.length; int++) {
			var item = listasDinamicas[int];
			if (item.id_lista_dinamica === ac_elegida.id_lista_dinamica) {
				item.elegido = true;
				break;
			}
		}
	}
	actualizarSelectListasDinamicas(id_accion, listasDinamicas, ac_elegida);
}

function actualizarSelectListasDinamicas(id_accion, listasDinamicas, ac_elegida){
	osm_setValor('select_aa_lista_dinamica_' + id_accion, '');
	var selectListasDinamicas = osm_getObjeto('select_aa_lista_dinamica_' + id_accion);

	if (selectListasDinamicas.hasChildNodes()) {
		while (selectListasDinamicas.childNodes.length >= 1) {
			selectListasDinamicas.removeChild(selectListasDinamicas.firstChild);
		}
	}

	var option_ninguno = document.createElement("OPTION");
	option_ninguno.value = "";
	option_ninguno.id = 'option_aa_lista_dinamica_ninguno';
	option_ninguno.innerHTML = '-- Ninguna --';
	selectListasDinamicas.appendChild(option_ninguno);

	for ( var i = 0; i < listasDinamicas.length; i++) {

		var option = document.createElement("OPTION");

		var element = listasDinamicas[i];

		option.value = element.id_lista_dinamica;
		option.id = 'option_aa_lista_dinamica_' + element.id_lista_dinamica;
		option.innerHTML = element.nombre;

		selectListasDinamicas.appendChild(option);

	}
	if(ac_elegida){
		osm_setValor('select_aa_lista_dinamica_' + id_accion, ac_elegida.id_lista_dinamica);		
	}
	
}

function asignarListaDinamica(id_accion){
	var selectListasDinamicas = osm_getObjeto('select_aa_lista_dinamica_' + id_accion);
	var indiceSeleccionado = selectListasDinamicas.selectedIndex;
	var opcionSeleccionada = selectListasDinamicas.options[indiceSeleccionado];
	var id_lista_dinamica = opcionSeleccionada.value;
	jsonrpc._("accionJsonServicio.asignarListaDinamica")(id_accion, id_lista_dinamica);
}

function quitarListaDinamica(id_accion){
	
} 

function actualizarMensajeEjecucion(id_accion) {
	var mensaje = osm_getValor("textarea_mensaje_ejecucion");
	jsonrpc._("accionJsonServicio.actualizarMensajeEjecucion")(id_accion, mensaje);		
}

