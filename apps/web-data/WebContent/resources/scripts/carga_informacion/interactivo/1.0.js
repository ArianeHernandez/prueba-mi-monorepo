function guardarCargaRelaciona(id_carga){
	var respuesta = jsonrpc._("cargaRelacionada.guardarCargaRelacionada")(id_carga);
	
	if(!respuesta){
		alert('No se pudo guardar la carga relacionada en la session');
	}
	
}
