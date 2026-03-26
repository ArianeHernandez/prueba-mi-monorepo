var ID_PERSONA,ID_NEGOCIO,ID_USUARIO,ROL;

function inicioActivarPersona(id_persona, id_negocio, id_usuario, rol){
	
	ID_PERSONA=id_persona;
	ID_NEGOCIO=id_negocio;
	ID_USUARIO=id_usuario;
	ROL=rol;
	
	var id = "cajachuqueo_" + ID_PERSONA;
	var activo = osm_getValor(id);
	
	var msj= "";
	
	var str = (osm_esVacio(id_negocio) ? "clientes":"negocios");
	var act = activo == 'S' ? "activar" : "desactivar";
	if (osm_getValor('aplicar_todos') == 'SI') {
		msj= "\u00bfDesea "+act+" la persona para todos los "+ str +"?";
	}
	else {
		msj= "\u00bfDesea "+act+" la persona?";
	}
	
	
	osm_setValor('mensaje_cambio_activo', msj);
	
	verVentana('vn_cambio_estado_activo');
}

function inicioActivarLiberador(id_persona, id_negocio, id_usuario, rol){
	
	osm_setValor('Procesos_Afectados_Al_Desactivar', '');
	osm_setVisible('Procesos_Afectados_Al_Desactivar', false);
	
	ID_PERSONA=id_persona;
	ID_NEGOCIO=id_negocio;
	ID_USUARIO=id_usuario;
	ROL=rol;
	
	var id = "cajachuqueo_" + ID_PERSONA;
	var activo = osm_getValor(id);
	
	var msj= "";
	
	var str = (osm_esVacio(id_negocio) ? "clientes":"negocios");
	var act = activo == 'S' ? "activar" : "desactivar";
	if (osm_getValor('aplicar_todos') == 'SI') {
		msj= "\u00bfDesea "+act+" la persona para todos los "+ str +"?";
	}
	else {
		msj= "\u00bfDesea "+act+" la persona?";
	}
	
	if(activo != 'S'){
		// agregar al mensaje los procesos afectados...
		var listaProcesos=jsonrpc._("procesoJsonServicio.listarProcesosAfectadosEliminacionLiberador")(id_persona,id_usuario).list;
	    var mensaje="Al desactivar a este liberador, las operaciones relacionadas con los siguientes procesos no podr\u00e1n ser liberadas:<br/>"
	    for(i=0;i<listaProcesos.length;i++){
	    	mensaje+=listaProcesos[i].nombre+"<br/>";
	    }
	    
	    if(listaProcesos.length>0){
	    	osm_setValor('Procesos_Afectados_Al_Desactivar', mensaje);
	    	osm_setVisible('Procesos_Afectados_Al_Desactivar', true, true);
	    }else{
	    	osm_setValor('Procesos_Afectados_Al_Desactivar', '');
	    	osm_setVisible('Procesos_Afectados_Al_Desactivar', false);
	    }
	}
	
	
	osm_setValor('mensaje_cambio_activo', msj);
	
	verVentana('vn_cambio_estado_activo');
}

function activarPersona(todos){
	
	
	var rta ;
	var id = "cajachuqueo_" + ID_PERSONA;
	var activo = osm_getValor(id);
	if (ROL == "DN") {
		rta = jsonrpc._("personaServicio.activarDirectorNegocio")(ID_PERSONA,ID_NEGOCIO, activo, todos);
	}
	else {
		rta = jsonrpc._("personaServicio.activarPersonaUsuario")( ID_PERSONA, ID_USUARIO, activo, ROL, todos);
	}
	if (!rta) {
		osm_setMarca(id, !osm_getMarca(id));
		alert("No es posible actualizar el estado de la persona!");
	}
	cerrarVentana('vn_cambio_estado_activo');
}

function validarEliminacionLiberador(id_persona, id_negocio, id_usuario, rol){
	
	var listaProcesos=jsonrpc._("procesoJsonServicio.listarProcesosAfectadosEliminacionLiberador")(id_persona,id_usuario).list;
    var mensaje="Al eliminar a este liberador, las operaciones relacionadas con los siguientes procesos no podran ser liberadas:<br/>"
    for(i=0;i<listaProcesos.length;i++){
    	mensaje+=listaProcesos[i].nombre+"<br/>";
    }
    
    if(listaProcesos.length>0){
    	osm_setValor('Procesos_Afectados_Al_Eliminar', mensaje);
    	osm_setVisible('Procesos_Afectados_Al_Eliminar', true, true);
    }else{
    	osm_setValor('Procesos_Afectados_Al_Eliminar', '');
    	osm_setVisible('Procesos_Afectados_Al_Eliminar', false);
    }

	inicioEliminarPersona(id_persona, id_negocio, id_usuario, rol)
	
}

function inicioEliminarPersona(id_persona, id_negocio, id_usuario, rol){
	
	var mensaje = osm_getValor('Procesos_Afectados_Al_Eliminar');
	if(osm_esVacio(mensaje)){
		osm_setVisible('Procesos_Afectados_Al_Eliminar', false);
	}
	ID_PERSONA=id_persona;
	ID_NEGOCIO=id_negocio;
	ID_USUARIO=id_usuario;
	ROL=rol;
	
	
	verVentana('vn_eliminar');
}

function eliminarPersona(){
	
	var rta ;
	if (ROL == "DN") {
		rta = jsonrpc._("personaServicio.eliminarDirectorNegocio")(ID_PERSONA,ID_NEGOCIO);
	}
	else if (ROL == "ADV") {
		rta = jsonrpc._("personaServicio.eliminarPersonaRol")(ID_PERSONA,ROL);
	}
	else {
		rta = jsonrpc._("personaServicio.eliminarPersonaUsuario")(ID_PERSONA, ID_USUARIO, ROL);
	}
	if (!rta) {
		alert("No es posible eliminar la persona!");
	}
	cerrarVentana('vn_eliminar');
	var url = osm_remplazar(location.pathname, CONTEXTPATH + '/','');
	osm_go(url);
}

function verVentana(idv) {
	osm_setVisible(idv, true, true);
	osm_ocultarSelects("bodyContent");
}

function cerrarVentana(idv, chk) {
	if (chk) {
		var id = "cajachuqueo_" + ID_PERSONA;
		osm_setMarca(id, !osm_getMarca(id))
	}
	osm_setVisible(idv, false);
	osm_mostrarSelects("bodyContent");
}

function editarPersona(id_persona, identificacion, tipo_persona, tipo_documento, id){
	
	osm_setValor('edit.id_persona', id_persona);
	osm_setValor('edit.identificacion', identificacion);
	osm_setValor('edit.tipo_persona', tipo_persona);	
	osm_setValor('edit.tipo_documento', tipo_documento);
	osm_setValor('edit.id', id);
	
	osm_getObjeto('edit.identificacion').name = 'identificacion';
	osm_getObjeto('edit.tipo_persona').name = 'tipo_persona';
	osm_getObjeto('edit.id_persona').name = 'id_persona';
	osm_getObjeto('edit.tipo_documento').name = 'tipo_documento';
	osm_getObjeto('edit.id').name = 'id';
	
	osm_enviarFormulario('form_editarpersona');
	
}


function generarSobreflex(id_persona, id_usuario){
	osm_setValor('id_usuario_sel', id_usuario);
	osm_setValor('id_persona_sel', id_persona);

	if(confirm("\u00bfEsta seguro que desea generar SOBREFLEX?")){
		osm_enviarFormulario('form_sobreflex');	
	}else{
		
		return;
	}
	
}

function regenerarClave(id_persona, id_rol){
	osm_block_window();
	
	if(confirm("\u00bfEsta seguro que desea regenerar La clave?")){
		var rta = jsonrpc._("personaServicio.regenerarClaveUsuario")(id_persona,id_rol);
		osm_unblock_window();
		
		if(rta){
			osm_alert("Se ha regenerado la clave exitosamente.");
		}else{
			osm_alert("Lo sentimos, no se puede regenerar la clave del usuario en este momento.");
		}
	}
	osm_unblock_window();
}

function init() {
	osm_setValor('Procesos_Afectados_Al_Desactivar', '');
	osm_setVisible('Procesos_Afectados_Al_Desactivar', false);
	osm_setValor('Procesos_Afectados_Al_Eliminar', '');
	osm_setVisible('Procesos_Afectados_Al_Eliminar', false);
}
window.onload = init; 
