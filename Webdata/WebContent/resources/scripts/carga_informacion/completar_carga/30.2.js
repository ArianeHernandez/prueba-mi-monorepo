$(p_load);

function verCarga(id_carga)
{
	osm_setValor("id_carga", id_carga);
	osm_enviarFormulario("form_verCarga");
}

function p_load(){
	osm_timer(contarArchivos, 1000);
}


function contarArchivos(){
	
	var id_carga = osm_getValor("id_carga");
	var archivosadjuntos = jsonrpc._("archivosAdjJsonServicio.obtenerArchivosAdjuntosPorCarga")(id_carga);
	var archivosAdjList = archivosadjuntos.list;;
	
	if(archivosAdjList.length>0){
		$('#div_btn_enviar_carga').show();
		$('#div_mensaje_usuario').hide();
		
	}else{
		$('#div_mensaje_usuario').show();
		$('#div_btn_enviar_carga').hide();
	}
	
}


