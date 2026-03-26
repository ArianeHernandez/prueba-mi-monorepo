
function editarContenido(id_contenido){

	osm_setValor("id_contenido", id_contenido);
	osm_enviarFormulario("form_editar_contenido");	
}


function toggleContenido (servicio){
	
	var id_div="div_servicio_"+servicio; 
	var id_tr="body_"+servicio; 
	_obj =osm_getObjeto("body_"+servicio);
	
	if($(_obj).is(":hidden")){
		
		//Ocultar todos
		$("div[id*='div_servicio']").each(function(i){
			if(this.id!=id_div){
				$(this).hide();
				
			}
		});
		
		
		$("tr[id*='body_']").each(function(i){
			if(this.id!=id_tr){
				$(this).hide();
				
			}
		});
		
		//Se muestra la fila correspondiente
		
		$(_obj).show();
		
		_div =osm_getObjeto("div_servicio_"+servicio);
		$(_div).slideDown();
	}else{
		
		_div =osm_getObjeto("div_servicio_"+servicio);
		$(_div).slideUp();
		
		$(_obj).hide();
	}
	
}


