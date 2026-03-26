
function mostrarContenido(idkey){
	
	var contenido = unescape(osm_getValor("texto_parametrizable_"+idkey));
	var valor = osm_getValor("texto_parametrizable_"+idkey+"_valor");
	
	contenido = contenido.replace("{VALOR}", valor);
	
	//Se remplaza  el input por el texto
	$("#texto_parametrizable_"+idkey).after(contenido);
	$("#texto_parametrizable_"+idkey).remove();
	
}


