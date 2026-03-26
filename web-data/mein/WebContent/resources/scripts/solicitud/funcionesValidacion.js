function validarArchivo(fileid) {
	try{
		if ($("#"+fileid).find("#caja_archivo_adjunto").first()[0].files[0] == undefined) {
			$("#label_"+fileid).addClass("ERR_VALIDAR");
			return 1;
		}   	
	}catch(e){
		console.log(e);
	}
	return 0;
	 
}
function singlevalidador(item){
	if(item.tipo == "SIN"){
		if(osm_esVacio(osm_getValor(item.identificador))){
			skin_error(item.identificador);
			return 1;
		}
	}else if(item.tipo == "TEXTO"){
		skin_validar(item.identificador, "TEXTO", true);
	}else if(item.tipo == "NUMERO"){
		skin_validar(item.identificador, "NUMERO", true);
	}else if(item.tipo == "DECIMAL"){
		skin_validar(item.identificador, "DECIMAL", true);
	}else if (item.tipo == "DECIMALSIGNO"){
		skin_validar(item.identificador, "DECIMALSIGNO", true);
	}else if (item.tipo == "CORREO"){
		skin_validar(item.identificador, "CORREO", true);
	}else if(item.tipo == "ARCHIVO"){
		return validarArchivo(item.identificador);
	}else if(item.tipo == "BOTON"){
		if(osm_getValor(item.identificador) == ""){
			$("#"+item.identificador).find(".l-button").addClass("ERR_VALIDAR");
			$("#"+item.identificador+"_div").find(".l-button").addClass("ERR_VALIDAR");
			return 1;
		}
	}else{
		return 0;
		//skin_error(item.identificador);
	}
	return 0;
}
function validarMultiplesEntradas(arregloIds) {
	var validaciones = 0;
	for (var item of arregloIds) {
		validaciones = validaciones + singlevalidador(item);
	}
	return validaciones;
}

function removerErrorMultiplesEntradas(arregloIds) {
	for (var item of arregloIds) {
		if(item.tipo == "ARCHIVO"){
			$("#label_"+item.identificador).removeClass("ERR_VALIDAR");
		}else if(item.tipo == "BOTON"){
			$("#"+item.identificador).find(".l-button").removeClass("ERR_VALIDAR");
			$("#"+item.identificador+"_div").find(".l-button").removeClass("ERR_VALIDAR");
		}else{
			$("#"+item.identificador).removeClass("ERR_VALIDAR");
		}
		
	}
}

