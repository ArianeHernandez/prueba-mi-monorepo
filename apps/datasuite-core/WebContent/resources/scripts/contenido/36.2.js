var ste;

function guardarContenido(){
	ste.submit();
	ste.toggleSource();
	var texto = osm_getValor("caja_texto");
	
	if(osm_esVacio(texto)){		
		texto = "";
	}
	
	osm_setValor("contenido.texto", texto);
	osm_enviarFormulario("form_guardar_contenido");	
}


function toogleSeleccionarTodos(){
	
		
	$("#LISTA_REGISTROS input:checkbox").each(function(i){
		
		$(this).attr("checked", osm_getObjeto("check_todos").checked);
	});	
	
}

function p_load(){
	
	var texto = osm_getValor("texto_caja_texto");
	$("#caja_texto").html(texto);
	ste = new SimpleTextEditor("caja_texto", "ste");
	ste.init();
	
}

osm_listen("load", window, p_load);

