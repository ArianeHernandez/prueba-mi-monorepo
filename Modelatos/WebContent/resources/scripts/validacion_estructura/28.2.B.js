function activarValoresPorTipoValidacion(id_tipovalidacion){
	var tipovalidacion= jsonrpc._("validacionEstructuraServicio.obtenerTipoValidacion")(id_tipovalidacion);
	
	TIPOVALIDACION=tipovalidacion;
	
	if(tipovalidacion.requiere_primer_valor="S"){
		$(".primer_valor").show();
		
	}else{
		$(".primer_valor input").val("");
		$(".primer_valor").hide();
	}
	
	if(tipovalidacion.requiere_segundo_valor="S"){
		$(".segundo_valor").show();
		
	}else{
		$(".segundo_valor input").val("");
		$(".primer_valor").hide();
	}
	
	
}

function guardarValidacionPorEstructura(){
	
	var id_validacion_estructura = osm_getValor('id_validacion_estructura');
	var id_tipovalidacion = osm_getValor('selector_tipovalidacion');
	var nombreValidacion = osm_getValor('text_nombre_validacion');
	
	var hayValidacion;
	
	if(osm_esVacio(id_validacion_estructura)){
		//Si el id_validacion_estructura es vacio se debe crear la validacion
		
		id_validacion_estructura = jsonrpc._("validacionEstructuraServicio.obtenerSiguienteIDValidacionEstructura")();
		
		//Se crea el encabezado de la validacion
		hayValidacion = jsonrpc._("validacionEstructuraServicio.crearValidacionPorEstructura")(id_validacion_estructura, 
				id_tipovalidacion, nombreValidacion);
		
	}else{
		//Si el id_validacion_estructura NO es vacio se debe actualizar la validacion
		hayValidacion = jsonrpc._("validacionEstructuraServicio.actualizarValidacionPorEstructura")(id_tipovalidacion,
				nombreValidacion);
	}
	
	//Si hay validacion se crean las relaciones
	if(hayValidacion){
		//Se eliminan todas la relaciones validacion -campo
		var eliminacionExisota = jsonrpc._("validacionEstructuraServicio.eliminarTodasLasRelacionesCampoValidacion")(id_validacion_estructura);
		
		if(eliminacionExisota){
			// Se deben crear las relaciones validacion-campo
			var creacionExitosa =  crearRelacionesCampoValidacion(id_validacion_estructura);
			
			if(creacionExitosa){
				
				alert("Creacion exitosa");
			}else{
				//Se eliminan todas las relaciones validacion-campo que se hayan podido crear
				jsonrpc._("validacionEstructuraServicio.eliminarTodasLasRelacionesCampoValidacion")(id_validacion_estructura);
				
				//Se elimina el encabezado de la validacion
				jsonrpc._("validacionEstructuraServicio.eliminarValidacionPorEstructura")(id_validacion_estructura);
				
				alert("NO fue posible crear la validacion");
			}
			
		}else{
			alert("Error: No se pueden crear las relaciones entre la validacion y los campos de la estructura");
			
		}
		
	}else{
		alert("Error: No es posible guardar la validacion");
				
	}
	
	
}

function crearRelacionesCampoValidacion(id_validacion_estructura){
	
	var camposPorEstructura = jsonrpc._("validacionEstructuraServicio.obtenerCamposPorEstructura")();
	var listaCampos = camposPorEstructura.list;
	var camposSeleccionados = 0;
	var creacionExitosa = true;
	
	if(listaCampos.length>0){
		
		for(var i=0; i< listaCampos.length; i++){
			
			var campo = listaCampos[i];
			
			if(osm_getObjeto('check_asociar_'+campo.id_campo).checked){
				camposSeleccionados++;
				//Si esta seleccionado se crea la relacion con la la validacion
				
				var primerValor = osm_getValor('cajatexto_primer_valor_campo_'+campo.id_campo);
				var segundoValor = osm_getValor('cajatexto_segundo_valor_campo_'+campo.id_campo);
				
				if(osm_esVacio(primerValor) ){
					primerValor = null;
				}
				
				if(osm_esVacio(segundoValor)){
					segundoValor = null
				}
				
				//Si esta seleccionado se crea la relacion con la la validacion
				alert("campo:"+campo.nombre+" esta seleccionado. Campo 1:"+primerValor+" Campo2:"+segundoValor);
					
				
				var exitosa = jsonrpc._("validacionEstructuraServicio.crearRelacionCampoValidacion")(id_validacion_estructura, campo.id_campo, primerValor, segundoValor );
				
				if(!exitosa){
					creacionExitosa=false;
				}
				
			}
			
		}
		
		if(camposSeleccionados==0){
			creacionExitosa=false;
		}
		
	}else{
		creacionExitosa=false;
		
	}
	
	return creacionExitosa;
	
}






