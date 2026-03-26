

function cambiarEstado(checked, id_proceso){
	
	var estado = checked?"A":"I";
	
	try {
		var ok = jsonrpc._("procesoJsonServicio.cambiarEstadoProceso")(id_proceso, estado);
		
		if(!ok){
			alert("No se pudo realizar la operaci\u00f3n.");
			osm_go('crear_proceso/10.1.do');
		}
	} catch (e) {
		alert("Ha ocurrido un error al realizar la operaci\u00f3n..");
		osm_go('crear_proceso/10.1.do');
	}
	
}

function crearProceso(){
	var nombre = osm_getValor("nombreProceso");
	
	if(osm_esVacio(nombre)){
		alert("El nombre del proceso no puede ser vacio.");
		return;
	}
	
	try {
		var id_proceso = jsonrpc._("procesoJsonServicio.crearProceso")(null, null, nombre);
		
		if(id_proceso!=null)
		{
			verVentana();
			edicionProceso(id_proceso);
		}
	} catch (e) {
		alert("Ha ocurrido un error al realizar la operaci\u00f3n..");
		osm_go('crear_proceso/10.1.do');
	}

}


function edicionProceso(id_proceso){
	osm_setValor('id_proceso', id_proceso);
	osm_enviarFormulario('form_siguiente');
}

function verVentana(){
	osm_setVisible("ventanaProceso", true, true);
	osm_ocultarSelects("bodyContent");
}

function cerrarVentana(){
	osm_setVisible("ventanaProceso", false, false);
	osm_mostrarSelects("bodyContent");
}

function eliminarProceso(id_proceso){
	
	//Se consulta si el proceso tiene cargas en transito
	var cargasEnProceso = jsonrpc._("procesoJsonServicio.obtenerCargasPorProcesoClienteEnTransito")(id_proceso);
	
	if(cargasEnProceso!=null){
		if(cargasEnProceso>0){
			osm_alert("No se puede eliminar el proceso porque aun tiene solicitudes en proceso");
			
		}else{
			if(confirm("Esta seguro que desea eliminar el proceso?")){
				//Se debe eliminar el proceso 
				osm_setValor("id_proceso_eliminar", id_proceso);
				osm_enviarFormulario("form_eliminar_proceso");	
				
			}
			
		}
		
	}
	
	
	
}
