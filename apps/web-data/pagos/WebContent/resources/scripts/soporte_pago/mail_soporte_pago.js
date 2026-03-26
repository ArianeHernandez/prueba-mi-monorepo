function enviarCorreo(idRetiro){
	
	var paras = osm_getValor('para');
	var asunto = osm_getValor('asunto');
	var mensaje = osm_getValor('mensaje');
	
	paraArray = paras.split(";");
	
	for(var i = 0; i<paraArray.length; i++){
		if(!osm_validarCorreo(osm_trim(paraArray[i]))){
			alert("El formato del correo "+ paraArray[i] +" es inv\u00e1lido... Los correos deben tener @ y estar separados por ;");
			return;
		}
	}
	
	if(osm_esVacio(asunto)){
		alert("El asunto del correo no puede ser vacio...");
		return;
	}
	
	if(osm_esVacio(mensaje)){
		alert("El mensaje del correo no puede ser vacio...");
		return;
	}
	
	jsonrpc._("soportePagoServicio.generarPdfSoportePagoyEnviarCorreo")(respuestaEnviarCorreo, idRetiro, paras, asunto, mensaje);
	
	$("#boton_envio").hide();
	$("#nota_envio").show();
}

function respuestaEnviarCorreo(exitoso){
	
	if(exitoso){
		alert("El correo fue enviado con \u00e9xito.");
	}else{
		alert("Problemas enviando el correo. Por favor int\u00e9ntelo m\u00e1s tarde o comun\u00edquese con el administrador...");
	}
	
	$("#boton_envio").show();
	$("#nota_envio").hide();
}


