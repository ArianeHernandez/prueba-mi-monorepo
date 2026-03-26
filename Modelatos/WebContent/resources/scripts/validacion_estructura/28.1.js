$(document).ready(init);

function init(){
	var id_estructura = osm_getValor("selector_estructura");
	
	if(!osm_esVacio(id_estructura)){
		listarValidacionesPorEstructura(id_estructura);
	}
	
}


function listarValidacionesPorEstructura(id_estructura){
	
	var validacionesEstructura =jsonrpc._("validacionEstructuraServicio.obtenerValidacionesPorEstructura")(id_estructura);
	var listaValidaciones = validacionesEstructura.list;
	
	$("#lista_validaciones_estructura").empty();
	$("#mensaje_error").hide();
	
	osm_setValor('lista_validaciones_estructura', '');
		
		
	if(listaValidaciones.length>0){
		
		osm_construirHTML('lista_validaciones_estructura', 'PLANTILLA_LISTA_VALIDACIONES_POR_ESTRUCTURA');
		osm_setValor('contenido_lista_validaciones_por_estructura', '');
		
		for(var i=0; i< listaValidaciones.length; i++){
			
			var  validacion = listaValidaciones[i];
			var parametros = [validacion.id_validacion_estructura, validacion.nombre_tipovalidacion, validacion.descripcion];
			
			osm_construirHTML('contenido_lista_validaciones_por_estructura', 'PLANTILLA_ITEM_VALIDACION_ESTRUCTURA', parametros);
			
		}
	}else{
		
		if (!osm_esVacio(id_estructura)) {
			$("#mensaje_error").show();
			
		}
		
	}

	
}


function crearValidacionPorEstructura(){
	var id_estructura = osm_getValor('selector_estructura');
	
	if(osm_esVacio(id_estructura)){
		osm_alert("Debe seleccionar una estructura para crear la validacion");
		osm_setFoco("selector_estructura");
		return false;
	}
	
	osm_setValor('id_estructura', id_estructura);
	osm_setValor('editar','false');
	osm_enviarFormulario("form_edicion_validaciones_estructura");
	
}

function editarValidacionPorEstructura(id_validacion_estructura){
	var id_estructura = osm_getValor('selector_estructura');
	osm_setValor('id_estructura', id_estructura);
	osm_setValor('id_validacion_estructura', id_validacion_estructura);
	osm_setValor('editar','true');
	osm_enviarFormulario("form_edicion_validaciones_estructura");
	
	
}

function eliminarValidacionPorEstructura(id_validacion_estructura){
	osm_setValor('id_validacion_estructura', id_validacion_estructura);
	osm_enviarFormulario("form_eliminar_validacion");
	
	
	
}
