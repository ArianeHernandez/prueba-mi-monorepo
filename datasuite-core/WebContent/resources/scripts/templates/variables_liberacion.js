$(actualizarCamposPadre);

function agregarVariableCampo(){
	
	var id_campo = osm_getValor("sel_var_campo");
	
	if($("#variable_campo_" + id_campo).length){
		alert("El campo ya fue agregado");
		return false;
	}
	
	var id_lista_dinamica = osm_getValor("sel_var_lista_din");
	
	var campo = osm_getValorText("sel_var_campo");
	
	var lista_din = osm_getValorText("sel_var_lista_din");
	
	osm_construirHTML("VARIABLES_LIBERACION", "PLANTILLA_VARIABLE_CAMPO", [id_campo, id_lista_dinamica, campo, lista_din ]);
	
	actualizarCamposPadre();
	
	osm_setValor("sel_var_campo", "");
	osm_setValor("sel_var_lista_din", "");
}

function actualizarListaVariablesLiberacion(campos){
	
	var options = osm_getObjeto("sel_var_campo").options;
	options.length = 1;
	
	for ( var i = 0; i < campos.length; i++) {
		options[i+1] = new Option(campos[i].nombre, campos[i].id_campo);
	}
	
	actualizarCamposPadre();
}

function eliminarVariableLiberacion(id){
	
	$("#variable_campo_"+id).remove();
	$("#VARIABLES_LIBERACION").find("option[value='" +id +"']").remove();
}

function actualizarCamposPadre(){
	
	var ids_campos = $("#VARIABLES_LIBERACION").find("input[name='id_campos']");
	
	for(var i = 0; i < ids_campos.length; i++){
		
		var pad = osm_getObjeto("id_campo_padre_" + ids_campos[i].value);
		if(pad != null){
			var selec = osm_esVacio(pad.value) ? osm_getValor("listaDC:" + ids_campos[i].value +".id_campo_padre") : pad.value;
			 
			pad.options.length = 0;
			pad.options[0] = new Option("--Seleccione--","");
			pad.options[1] = new Option("Negocio","-1");
			var k = 2;
			for (var j = 0; j < ids_campos.length; j++) {
				if(ids_campos[i].value != ids_campos[j].value){
					var nombre = osm_getValor("nombre_campo_"+ ids_campos[j].value);
					pad.options[k++] = new Option(nombre, ids_campos[j].value);			
				}
			}
			pad.value = selec;
		}
	}
}
