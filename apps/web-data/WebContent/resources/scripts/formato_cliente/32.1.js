EXCEPCIONES_DINAMICAS=0;


function crearExcepcion(){
	var excepciones_fijas = osm_getValor("total_excepciones");
	
	if(osm_esVacio(excepciones_fijas) || excepciones_fijas==0 ){
		
		//Se elimina los mensaje
		$("#mensaje_no_hay_excepciones").remove();
		$("#mensaje_seleccione_cliente").remove();
		
		
		//Se crean dinamicamente el html
		if(EXCEPCIONES_DINAMICAS>0){
			
			EXCEPCIONES_DINAMICAS = EXCEPCIONES_DINAMICAS+1;
			osm_construirHTML('contenido_dinamico', 'PLANTILLA_ITEM_LISTA_EXCEPCION', [ EXCEPCIONES_DINAMICAS ]);
			referenciarCampos(EXCEPCIONES_DINAMICAS);
			
			
		}else{
			EXCEPCIONES_DINAMICAS = EXCEPCIONES_DINAMICAS+1;
			osm_construirHTML('EXCEPCIONES', 'PLANTILLA_LISTA_EXCEPCION');
			osm_construirHTML('contenido_dinamico', 'PLANTILLA_ITEM_LISTA_EXCEPCION', [ EXCEPCIONES_DINAMICAS ]);
			referenciarCampos(EXCEPCIONES_DINAMICAS);
		}
		
	}else{
		
		EXCEPCIONES_DINAMICAS = EXCEPCIONES_DINAMICAS+1;
		total = EXCEPCIONES_DINAMICAS+parseInt(excepciones_fijas);
		osm_construirHTML('contenido_estatico', 'PLANTILLA_ITEM_LISTA_EXCEPCION', [ total ]);
		
		referenciarCampos(total);
		
	}
}


function referenciarCampos(posicion){
		obj_formato = osm_getObjeto("#excepciones:["+posicion+"].id_formato");
		$(obj_formato).addClass("select_formato");
		
		obj_tipo_excepcion = osm_getObjeto("#excepciones:["+posicion+"].tipo_excepcion");
		$(obj_tipo_excepcion);
}

function eliminarExcepcion(pos){
	$("#fila_"+pos).remove();
	
}

function guardar(){
	
	//Se valida que no hayan campos vacios
	var selects = $("#EXCEPCIONES .select_tipo_excepcion");
	
	for (var i = 0; i < selects.length; i++){
		if(osm_esVacio(selects[i].value)){
			osm_alert("Por favor, seleccione el tipo de excepción");
			selects[i].focus();
			return false;
		}	
	};
	
	var selects = $("#EXCEPCIONES .select_formato");
	
	for (var i = 0; i < selects.length; i++){
		if(osm_esVacio(selects[i].value)){
			osm_alert("Por favor, seleccione el formato");
			selects[i].focus();
			return false;
		}	
	};
	
	//Si pasa las validaciones se guarda
	osm_enviarFormulario("form_guardar");
	
}

function cambioTipoExcepcion(valor , nid){
	
	var jobj = $(osm_getObjeto('excepciones:['+ nid +'].id_negocio'));
	
	if(valor == 'R'){
		jobj.val('');
		jobj.find('option:not(:empty)').attr("disabled","disabled");
	}else{
		jobj.find('option').removeAttr("disabled");
	}
}

function p_load(){

}

osm_listen("load", window, p_load);

