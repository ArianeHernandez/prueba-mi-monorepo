function rolCambiado(){
	option = parseInt(osm_getValor("Asignacion.id_rol"))
	//alert("rol num: " + option)
	switch(option){
		case 0: // Administrador Sistema Modelatos
		case 1: // Administrador Usuarios Webdata
		case 2: // Director de Negocio Webdata
			quitarExtras();
			break;
		case 3: // Administrador Cliente Webdata
		case 4: // Preparador Cliente Webdata
		case 5: // Revisor Cliente Webdata
		case 6: // Liberador Cliente Webdata
		case 7: // Cliente Natural
			quitarExtras();
			mostrarBuscarCliente();
			break;
		case 8: // Administrativo
			quitarExtras();
			mostrarRolesAuth();
			break;
		default:
			alert("Rol no esperado...");
			break;
	}
}

function quitarExtras(){
	osm_setVisible("registro_auth", false, true);
	osm_setVisible("registro_cliente", false, true);
	osm_setVisible("asignarTodosClientes", false, true);
}

function mostrarBuscarCliente(){
	osm_setVisible("registro_cliente", true, true);
	osm_setVisible("asignarTodosClientes", true, true);
}

function mostrarRolesAuth(){
	osm_setVisible("registro_auth", true, true);
}

function desasignarCliente(){
	osm_setValor('Asignacion.id_cliente', '');
	osm_setValor('id_usuario', '');
	osm_setValor('sel_cliente', "");
}

function validar(){
	// nos aseguramos que el titulo no esta vacio
	var nombre = osm_getValor("Asignacion.titulo")
	if (osm_esVacio(nombre)){
		alert("El Nombre de la Asignaci\u00f3n no puede ser vacio");
		return false;
	}
	
	// verifica si se esta editando una asignacion o si es una nueva
	var id = osm_getValor("Asignacion.id_asignacion")
	if (osm_esVacio(id)){
		id = jsonrpc._("asignacionReporteJsonServicio.obtenerSiguienteAsignacionReporteId")()
		osm_setValor("Asignacion.id_asignacion", id)
		osm_setValor("nuevo", "true")
	}	
	
	// quitar rol auth cuando id_rol seleccionado no concuerde
	if(osm_getValor("Asignacion.id_rol") != 8){ // si no es rol administrador
		osm_setValor("Asignacion.id_rol_auth", null)
	}else{
		osm_setValor("Asignacion.id_rol_auth", osm_getValor("id_rol_auth"))
	}
	
	// quitar id_cliente cuando id_rol seleccionado no concuerde
	if(parseInt(osm_getValor("Asignacion.id_rol")) >=3 && parseInt(osm_getValor("Asignacion.id_rol")) <=7){
		osm_setValor("Asignacion.id_cliente", osm_getValor("id_usuario"))
	}else{
		osm_setValor("Asignacion.id_cliente", null)
	}
	
	//alert("id cliente: " + osm_getValor("Asignacion.id_cliente"))
	
	osm_enviarFormulario("form_crear_asignacion");
}

function check(){
	var activo = osm_getValor("Asignacion.activo")
	if(activo == "N"){
		osm_setValor("Asignacion.activo", "S")
	}else{
		osm_setValor("Asignacion.activo", "N")
	}
}

function p_load(){
	var id = osm_getValor("Asignacion.id_asignacion")
	
	if (!osm_esVacio(id)){
		asignacion = jsonrpc._("asignacionReporteJsonServicio.obtenerReporteAsignado")(id)
		
		if(asignacion != null){
			osm_setValor('Asignacion.id_rol', asignacion.id_rol);
			rolCambiado();
			
			osm_setValor('Asignacion.activo', asignacion.activo);
			document.getElementById('Asignacion.activo').checked = (asignacion.activo == 'N')?false:true;
			
			osm_setValor('Asignacion.titulo', asignacion.titulo);
			osm_setValor('Asignacion.id_reporte', asignacion.id_reporte);			
			osm_setValor('Asignacion.id_rol_auth', asignacion.id_rol_auth);
			osm_setValor('Asignacion.id_asignacion', id);
			osm_setValor('Asignacion.id_cliente', asignacion.id_cliente);
			
			osm_setValor('id_rol_auth', asignacion.id_rol_auth);
			osm_setValor('id_usuario', asignacion.id_cliente);
			
			usuario = jsonrpc._("usuarioServicio.obtenerUsuario")(asignacion.id_cliente)
			osm_setValor('sel_cliente', usuario.identificacion + " - " + usuario.nombre);
		}
		
	}
}

osm_listen("load", window, p_load);
