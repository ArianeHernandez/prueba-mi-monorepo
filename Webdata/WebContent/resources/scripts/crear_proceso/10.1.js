

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

function crearProceso(id_usuario, id_negocio){
	var nombre = osm_getValor("nombreProceso");
	var id_tipo_proceso = osm_getValor("idTipoProceso");
	var num_procesos = osm_getValor("numeroProceso_" + id_tipo_proceso);
	var nombreTipoProceso = osm_getValor("nombreTipoProceso_" + id_tipo_proceso);

	if(osm_esVacio(nombre)){
		alert("El nombre del proceso no puede ser vacio.");
		return;
	}
	try {
		var validaNumeroTipProcesos = jsonrpc._("procesoJsonServicio.permiteCrearProceso")(id_usuario, id_tipo_proceso);
		if(!validaNumeroTipProcesos && num_procesos != null){
			var tipo_usuario = jsonrpc._("contenido.obtenerContenido")("General", "CLIENTE");
			
			if(tipo_usuario == null){
				tipo_usuario = "Cliente";
			}
			
			alert("No es posible crear otro proceso de tipo " + nombreTipoProceso +  " ya que solo es permitido " + num_procesos + " proceso y el " +  tipo_usuario +" ya lo posee");
			
		}else{
			
			var id_proceso = jsonrpc._("procesoJsonServicio.crearProceso")(id_usuario, id_negocio, nombre, id_tipo_proceso);
			
				if(id_proceso!=null)
				{
					verVentana();
					edicionProceso(id_proceso);
				}
			}
		}catch (e) {
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

function chequeo(id){
	var obj = osm_getObjeto(id);
	var selected = obj.checked;
	var valor = obj.value;
	
	
	$("#area_inputs_proceso input[type=checkbox]").each(function(){
		$(this).removeAttr('checked');
	});
	
	if (selected){
		$(obj).attr("checked", "checked");
		osm_setValor("idTipoProceso", valor);
	}else {
		osm_setValor("idTipoProceso", "");
	}
}
