
$(init_carga_excel);

function init_carga_excel(){
	consultarMensaje();
}

function consultarMensaje(){
	
	var mensaje = jsonrpc._("estructuraExcelServicio.obtenerMensajeCarga")();
	A =mensaje;
	if(mensaje != null){
		if(mensaje.estado != 'F'){
			$(".area_botones").hide();
			setTimeout("consultarMensaje()",600);
		}
		else{
			$(".area_botones").show();
		}
		osm_setValor('mensaje_carga', mensaje.mensaje);
		
	}else{
		osm_setValor('mensaje_carga', "Error en la carga del archivo.");
	}
}
