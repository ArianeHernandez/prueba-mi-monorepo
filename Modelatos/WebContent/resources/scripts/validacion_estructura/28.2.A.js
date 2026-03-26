$(document).ready(init);

TIPO_VALIDACION=null;

function init(){
	TIPO_VALIDACION=null;
	revisarCamposPorTipoValidacion();
}

function revisarCamposPorTipoValidacion(){
	var id_tipovalidacion = osm_getValor('ValidacionEstructura.id_tipovalidacion');
	
	if(!osm_esVacio(id_tipovalidacion)){
		
		activarValoresPorTipoValidacion(id_tipovalidacion);
	}
	
}

function activarValoresPorTipoValidacion(id_tipovalidacion){
	
	if(!osm_esVacio(id_tipovalidacion)){
		TIPO_VALIDACION= jsonrpc._("validacionEstructuraServicio.obtenerTipoValidacion")(id_tipovalidacion);
		
		if(TIPO_VALIDACION.requiere_primer_valor=="S"){
			$(".primer_valor").show();
			
		}else{
			$(".primer_valor input").val("");
			$(".primer_valor").hide();
		}
		
		if(TIPO_VALIDACION.requiere_segundo_valor=="S"){
			$(".segundo_valor").show();
			
		}else{
			$(".segundo_valor input").val("");
			$(".segundo_valor").hide();
		}
	}else{
		TIPO_VALIDACION=null;
		$(".segundo_valor input").val("");
		$(".segundo_valor").hide();
		$(".primer_valor input").val("");
		$(".primer_valor").hide();
	}
}

function guardarValidacionPorEstructura(){
	
	if(sonCamposValidos()){
		osm_enviarFormulario("form_siguiente");
	}
	
	
}

function sonCamposValidos(){
	
	//Se verifica si tiene nombre de validacion
	if(osm_esVacio(osm_getValor('ValidacionEstructura.descripcion'))){
		osm_alert("La validacion debe tener un nombre descriptivo");
		osm_setFoco('ValidacionEstructura.descripcion');
		return false;
	}
	
	//Se verifica si hay tipo de validacion
	if(TIPO_VALIDACION==null){
		osm_alert("Debe seleccionar un tipo de validacion");
		osm_setFoco('ValidacionEstructura.id_tipovalidacion');
		return false;
		
	}
	
	//Se verifica la informacion de los campos
	var camposPorEstructura = jsonrpc._("validacionEstructuraServicio.obtenerCamposPorEstructura")();
	var listaCampos = camposPorEstructura.list;
	var camposSeleccionados = 0;
	
	if(listaCampos.length>0){
		
		for(var i=0; i< listaCampos.length; i++){
			
			var campo = listaCampos[i];
			
			if(osm_getObjeto('campos:['+campo.id_campo+'].seleccionado').checked){
				camposSeleccionados++;
				osm_setValor('campos:['+campo.id_campo+'].seleccionado', 'S');
				
				//Se verifica el valor del primer_valor
				if(TIPO_VALIDACION.requiere_primer_valor=="S"){
					
					if(osm_esVacio(osm_getValor('campos:['+campo.id_campo+'].primer_valor'))){
						osm_alert("El primer valor no puede ser vacio");
						osm_setFoco("campos:["+campo.id_campo+"].primer_valor");
						return false;
					}
				}		
						
				//Se verifica el valor del segundo_valor
				if(TIPO_VALIDACION.requiere_segundo_valor=="S"){
					
					if(osm_esVacio(osm_getValor('campos:['+campo.id_campo+'].segundo_valor'))){
						osm_alert("El segundo valor no puede ser vacio");
						osm_setFoco("campos:["+campo.id_campo+"].segundo_valor");
						return false;
					}
				}	
				
			}
			
		}
	}
	
	if(camposSeleccionados == 0){
		alert("Debe seleccionar por lo menos un campo para crear la validacion")
		return false;
		
	}
	
	return true;
	
}




