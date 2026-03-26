$(obtenerTiposDocumento);

function obtenerTiposDocumento(){
	
	var lista = jsonrpc._("personaServicio.obtenerTiposDocumento")().list;
	var objeto = osm_getObjeto("tipo_documento");
	
	for ( var i = 0; i < lista.length; i++) {
		objeto.options[objeto.options.length] = new Option(lista[i].nombre, lista[i].id);
	}
	
}

function eliminarUsuarioNegocio(id_usuario, id_negocio, id_persona){
	
	osm_setValor('accion.enviar','E');
	osm_setValor('id_usuario', id_usuario);
	osm_setValor('id_negocio', id_negocio);
	osm_setValor('id_persona', id_persona);
	
	osm_setVisible('vn_eliminar', true, true);
	osm_ocultarSelects("bodyContent");
	
}

function activarUsuario(id_usuario, id){
	
	var rta = jsonrpc._("usuarioNegocioServicio.activarUsuario")(id_usuario, osm_getValor(id));
	if(!rta){
		return false;
	}
	
}

function confirmaUsuarioNegocio(){
	osm_enviarFormulario('form_usuarionegocio');
}


function cancelar_E(){
	var cc_id = "cajachuqueo_" + osm_getValor("id_persona");
	osm_setMarca(cc_id, !osm_getMarca(cc_id));
	osm_setVisible('vn_eliminar', false);
	osm_mostrarSelects("bodyContent");
}


function validarIdentificacion(){
	if(!osm_esVacio(osm_getValor('tipo_persona')) && !osm_esVacio(osm_getValor('identificacion'))){
		osm_enviarFormulario('form_crear');
	}
}

function editarUsuario(id_persona,id_usuario, identificacion, tipo_persona, tipo_documento){
	
	osm_setValor('edit.id_persona', id_persona);
	osm_setValor('edit.id_usuario', id_usuario);
	osm_setValor('edit.identificacion', identificacion);
	osm_setValor('edit.tipo_persona', tipo_persona);
	osm_setValor('edit.tipo_documento', tipo_documento);
	osm_getObjeto('edit.identificacion').name = 'identificacion';
	osm_getObjeto('edit.tipo_persona').name = 'tipo_persona';
	osm_getObjeto('edit.id_persona').name = 'id_persona';
	osm_getObjeto('edit.id_usuario').name = 'id_usuario';
	osm_getObjeto('edit.tipo_documento').name = 'tipo_documento';
	osm_enviarFormulario('form_editarusuario');
	
}




function callbackSeleccionCliente(cliente){
	editarUsuario(cliente.id_persona,
			cliente.id_usuario,
		  	cliente.identificacion, 
		  	cliente.tipo_persona,
		  	cliente.tipo_documento
		  	);
}
