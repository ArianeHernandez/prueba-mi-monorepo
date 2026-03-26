var LIST_REST_ADMINV = null;
var CANTIDAD_REST_ADMINV = 0;
var ROLES_ACTIVOS = null;

var POS_PINTAR = 0;

osm_listen("load", window, function(){
	
	var restAdminvs = jsonrpc._("administrativoJsonServicio.obtenerRestriccionesAdministrativos")();
	LIST_REST_ADMINV = restAdminvs.list;
	
	var roles = jsonrpc._("administrativoJsonServicio.obtenerRolesActivos")();
	ROLES_ACTIVOS = roles.list;
	
	// --
	
	CANTIDAD_REST_ADMINV = LIST_REST_ADMINV.length;
	
	if(CANTIDAD_REST_ADMINV>0){
		
		osm_block_window();
		
		osm_setValor('restricciones_administrativos', '');
		osm_construirHTML('restricciones_administrativos', 'PLANTILLA_LISTA_REST_ADMINV');
		osm_setValor('contenido_lista_rest_adminv', '');
		
		POS_PINTAR = 0;
		
		pintarRestriccionHilo();	
	}
});

function pintarRestriccionHilo(){
	
	if(POS_PINTAR < CANTIDAD_REST_ADMINV){
		
		pintarRestriccion('contenido_lista_rest_adminv', LIST_REST_ADMINV[POS_PINTAR]);
		
		POS_PINTAR++;
		
		window.setTimeout("pintarRestriccionHilo()", 1);
	}else{
		POS_PINTAR = 0;
		
		osm_unblock_window();
	}
}

function pintarRestriccion(id_padre, restAdminv){
	var cliente = restAdminv.nombres_usuario;
	if(osm_esVacio(cliente)){
		cliente = "";
	}
	osm_construirHTML(id_padre, 'PLANTILLA_ITEM_REST_ADMINV', 
		[restAdminv.id_restriccion_administrativo,
		cliente
		]
		);
	
	construirSelectRol(restAdminv, ROLES_ACTIVOS);

	construirSelectAdminv(restAdminv);
	
	construirSelectNegocio(restAdminv);
}

function crearRestriccion(){
	
	var id_restriccion_administrativo = jsonrpc._("administrativoJsonServicio.obtenerIdSiguiente")();
	
	var exitoCrear = jsonrpc._("administrativoJsonServicio.crearRestriccionAdministrativo")(ROLES_ACTIVOS[0].id_rol, null, null, null,id_restriccion_administrativo);

	if(exitoCrear){
	
		var restAdminv = jsonrpc._("administrativoJsonServicio.obtenerRestriccionAdministrativo")(id_restriccion_administrativo);
		
		if(CANTIDAD_REST_ADMINV==0){
			osm_setValor('restricciones_administrativos', '');
			osm_construirHTML('restricciones_administrativos', 'PLANTILLA_LISTA_REST_ADMINV');
			osm_setValor('contenido_lista_rest_adminv', '');
		}
		
		pintarRestriccion('contenido_lista_rest_adminv', restAdminv);
		CANTIDAD_REST_ADMINV++;
		
	}
}

function eliminarRestriccion(id_restriccion_administrativo){
	
	var exitoEliminar = jsonrpc._("administrativoJsonServicio.eliminarRestriccionAdministrativo")(id_restriccion_administrativo);
	
	if(exitoEliminar){
		osm_setValor('item_rest_adminv_'+id_restriccion_administrativo, '');
		var objItemRestAdminv = osm_getObjeto('item_rest_adminv_'+id_restriccion_administrativo);
		objItemRestAdminv.className = "display:none";
		CANTIDAD_REST_ADMINV--;
	}
	
	if(CANTIDAD_REST_ADMINV==0){
		osm_setValor('restricciones_administrativos', 'No existen restricciones');
	}
		
}

function construirSelectRol(restAdminv, rolList) {
	
	var id_restriccion_administrativo = restAdminv.id_restriccion_administrativo;
	var id_rol = restAdminv.id_rol;
	
	var selectRol = document.createElement('SELECT')
	selectRol.className = "form-control w140";
	selectRol.id = "select_rol_rest_adminv_"+id_restriccion_administrativo;
	
	selectRol.onchange = new Function("actualizarRol("+id_restriccion_administrativo+")");
	
	osm_setValor('rol_rest_adminv_'+id_restriccion_administrativo,'');
	osm_getObjeto('rol_rest_adminv_'+id_restriccion_administrativo).appendChild(selectRol);
	
	osm_setValor("select_rol_rest_adminv_"+id_restriccion_administrativo, '');
		
	
	for ( var i = 0; i < rolList.length; i++) {
		
		var option = document.createElement("OPTION");

		var rol=rolList[i];
		
		option.value = rol.id_rol;
		option.id='option_rol_'+id_restriccion_administrativo+'_'+rol.id_rol;
		option.innerHTML = rol.nombre_rol;
		
		selectRol.appendChild(option);
		
		if(rol.id_rol == id_rol){
			option.selected = true;
		}
		
	}


}


function construirSelectAdminv(restAdminv) {
	
	var id_restriccion_administrativo = restAdminv.id_restriccion_administrativo;		
	
	var selectAdminv = document.createElement('SELECT')
	selectAdminv.className = 'form-control w140';
	selectAdminv.id = 'select_adminv_rest_adminv_'+id_restriccion_administrativo;
	
	selectAdminv.onchange = new Function("actualizarAdministrativo("+id_restriccion_administrativo+")");
	
	osm_setValor('adminv_rest_adminv_'+id_restriccion_administrativo,'');
	osm_getObjeto('adminv_rest_adminv_'+id_restriccion_administrativo).appendChild(selectAdminv);
	
	osm_setValor('select_adminv_rest_adminv_'+id_restriccion_administrativo, '');
	
	
	var administrativos = jsonrpc._("administrativoJsonServicio.obtenerAdministrativosPorRol")(restAdminv.id_rol);
	var adminvList = administrativos.list;
	
	
	var option_todos = document.createElement("OPTION");
	option_todos.value = "";
	option_todos.id='option_administrativo_'+id_restriccion_administrativo+'_todos';
	option_todos.innerHTML = '*';
	selectAdminv.appendChild(option_todos);
	
	if(restAdminv.id_administrativo==null){
		option_todos.selected = true;
	}
	
	for ( var i = 0; i < adminvList.length; i++) {
		
		var option = document.createElement("OPTION");

		var adminv=adminvList[i];
		
		option.value = adminv.id_administrativo;
		option.id='option_administrativo_'+id_restriccion_administrativo+'_'+adminv.id_administrativo;
		
		var nombre = adminv.nombre;
		var apellido = adminv.apellido;
		var separador = '';
		
		if(osm_esVacio(nombre)){nombre='';}
		if(osm_esVacio(apellido)){apellido='';}
		
		if(!osm_esVacio(nombre) && !osm_esVacio(apellido)){
			separador = ' ';
		}
		
		option.innerHTML = nombre+separador+apellido;
		
		selectAdminv.appendChild(option);
		
		if(restAdminv.id_administrativo == adminv.id_administrativo){
			option.selected = true;
		} 
		
	}
	
}

function construirSelectCliente(restAdminv, clientesList) {
	
	var id_restriccion_administrativo = restAdminv.id_restriccion_administrativo;
	var id_usuario = restAdminv.id_usuario;
	
	var selectCliente = document.createElement('SELECT')
	selectCliente.className = "form-control w140";
	selectCliente.id = "select_cliente_rest_adminv_"+id_restriccion_administrativo;
	
	selectCliente.onchange = new Function("actualizarCliente("+id_restriccion_administrativo+")");
	
	osm_setValor('cliente_rest_adminv_'+id_restriccion_administrativo,'');
	osm_getObjeto('cliente_rest_adminv_'+id_restriccion_administrativo).appendChild(selectCliente);
	
	osm_setValor("select_cliente_rest_adminv_"+id_restriccion_administrativo, '');
		
	var option_todos = document.createElement("OPTION");
	option_todos.value = "";
	option_todos.id='option_cliente_'+id_restriccion_administrativo+'_todos';
	option_todos.innerHTML = '*';
	selectCliente.appendChild(option_todos);
	
	if(restAdminv.id_usuario==null){
		option_todos.selected = true;
	}

	for ( var i = 0; i < clientesList.length; i++) {
		
		var option = document.createElement("OPTION");

		var cliente=clientesList[i];
		
		option.value = cliente.id_usuario;
		option.id='option_cliente_'+id_restriccion_administrativo+'_'+cliente.id_usuario;
		
		var nombre = cliente.nombre;
		var apellido = cliente.apellido;
		var separador = '';
		
		if(osm_esVacio(nombre)){nombre='';}
		if(osm_esVacio(apellido)){apellido='';}
		
		if(!osm_esVacio(nombre) && !osm_esVacio(apellido)){
			separador = ' ';
		}
		
		option.innerHTML = nombre+separador+apellido;
		
		selectCliente.appendChild(option);
		
		if(cliente.id_usuario == id_usuario){
			option.selected = true;
		}
		
	}

}

function construirSelectNegocio(restAdminv) {
	
	var id_restriccion_administrativo = restAdminv.id_restriccion_administrativo;
	var id_negocio = restAdminv.id_negocio;
	
	var selectNegocio = document.createElement('SELECT')
	selectNegocio.className = "form-control w140";
	selectNegocio.id = "select_negocio_rest_adminv_"+id_restriccion_administrativo;
	
	selectNegocio.onchange = new Function("actualizarNegocio("+id_restriccion_administrativo+")");
	
	osm_setValor('negocio_rest_adminv_'+id_restriccion_administrativo,'');
	osm_getObjeto('negocio_rest_adminv_'+id_restriccion_administrativo).appendChild(selectNegocio);
	
	osm_setValor("select_negocio_rest_adminv_"+id_restriccion_administrativo, '');
		
	var option_todos = document.createElement("OPTION");
	option_todos.value = "";
	option_todos.id='option_negocio_'+id_restriccion_administrativo+'_todos';
	option_todos.innerHTML = '*';
	selectNegocio.appendChild(option_todos);
	
	if(restAdminv.id_negocio==null){
		option_todos.selected = true;
	}

	var id_usuario = restAdminv.id_usuario;
	
	var negocios = jsonrpc._("administrativoJsonServicio.obtenerNegociosPorUsuario")(id_usuario);
	var negociosList = negocios.list;
	
	for ( var i = 0; i < negociosList.length; i++) {
		
		var option = document.createElement("OPTION");

		var negocio=negociosList[i];
		
		option.value = negocio.id_negocio;
		option.id='option_negocio_'+id_restriccion_administrativo+'_'+negocio.id_negocio;
		
		var codigo = negocio.codigo;
		var nombre = negocio.nombre;
		
		if(osm_esVacio(codigo)){codigo='';}
		if(osm_esVacio(nombre)){nombre=''}	
		
		var separador='';
		if(!osm_esVacio(nombre) && !osm_esVacio(codigo)){
			separador = ' ';
		}
		
		option.innerHTML = codigo+separador+nombre;
		
		selectNegocio.appendChild(option);
		
		if(negocio.id_negocio == id_negocio){
			option.selected = true;
		}
		
	}

}

function actualizarRol(id_restriccion_administrativo){
	
	
	var selectRol = osm_getObjeto("select_rol_rest_adminv_"+id_restriccion_administrativo);
	var indiceSeleccionado = selectRol.selectedIndex;
	var opcionSeleccionada = selectRol.options[indiceSeleccionado];
	 
	var id_rol = parseInt(opcionSeleccionada.value);

	var confActualizarRol = jsonrpc._("administrativoJsonServicio.actualizarRol")(id_restriccion_administrativo, id_rol);
	
	var restAdminv = jsonrpc._("administrativoJsonServicio.obtenerRestriccionAdministrativo")(id_restriccion_administrativo);
	construirSelectAdminv(restAdminv);
	
}

function actualizarAdministrativo(id_restriccion_administrativo){
	
	
	var selectAdminv = osm_getObjeto('select_adminv_rest_adminv_'+id_restriccion_administrativo);
	var indiceSeleccionado = selectAdminv.selectedIndex;
	var opcionSeleccionada = selectAdminv.options[indiceSeleccionado];
	 
	var id_administrativo = opcionSeleccionada.value;
	
	var confActualizarAdminv = jsonrpc._("administrativoJsonServicio.actualizarAdministrativo")(id_restriccion_administrativo, id_administrativo);
	
	
}

function actualizarCliente(id_restriccion_administrativo, id_usuario){
	
	var confActualizarCliente = jsonrpc._("administrativoJsonServicio.actualizarCliente")(id_restriccion_administrativo, id_usuario);
	
	var restAdminv = jsonrpc._("administrativoJsonServicio.obtenerRestriccionAdministrativo")(id_restriccion_administrativo);
	construirSelectNegocio(restAdminv);
	
}

function actualizarNegocio(id_restriccion_administrativo){
	
	var selectNegocio = osm_getObjeto('select_negocio_rest_adminv_'+id_restriccion_administrativo);
	var indiceSeleccionado = selectNegocio.selectedIndex;
	var opcionSeleccionada = selectNegocio.options[indiceSeleccionado];
	 
	var id_negocio = opcionSeleccionada.value;
	
	var confActualizarNegocio = jsonrpc._("administrativoJsonServicio.actualizarNegocio")(id_restriccion_administrativo, id_negocio);
	
}

function callbackSeleccionCliente(usuario, id_rest_admin){
	if(usuario){
		actualizarCliente(id_rest_admin, usuario.id_usuario);
	}
}

function eliminarUsuario(id_rest_admin){
	actualizarCliente(id_rest_admin, null);
}
