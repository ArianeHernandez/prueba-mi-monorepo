//-----------------------------------------------------------------------------

function validarDatos(){
	if (osm_esVacio(osm_getValor("classname")) || osm_esVacio(osm_getValor("method"))){
		osm_alert("Debe diligenciar una clase y un m\u00e9todo");
		return false;
	}
	return true;
}

//-----------------------------------------------------------------------------

function exeService(){
	osm_verLoader();
	
	var classname = osm_getValor("classname");
	var method = osm_getValor("method");
	var params = obtenerParametros();
	
	jsonrpc._("infoServicio.exeService")(mostrarRespuesta, classname, method, params);
}

//-----------------------------------------------------------------------------

function obtenerParametros(){
	var params = new Array();
	var sParams = osm_getValor("params");
	
	if (!osm_esVacio(sParams)){
		var arr = sParams.split(",");
		for (var i = 0; i < arr.length; i++){
			params[i] = osm_trim(arr[i]);
		}
	}
	
	return params;
}

//-----------------------------------------------------------------------------

function mostrarRespuesta(respuesta){
	$("#cajaService").val(respuesta);
	osm_unblock_window();
}

//-----------------------------------------------------------------------------
