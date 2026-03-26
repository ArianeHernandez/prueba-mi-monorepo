$(consultarMensaje);

function consultarMensaje() {
	
	var mensaje = jsonrpc._("salidaSistemaExterno.obtenerEstadoGeneracionArchivo")();
	if(mensaje){
		
		if(mensaje.estado != "F"){
			
			osm_setValor("mensaje", mensaje.mensaje);
			$("#progreso").css("width", (mensaje.porcentaje * 2) + "px");
			setTimeout("consultarMensaje()", 1000);			
		}
		else if(mensaje.exitoso){
			$("#mensaje, #barra-progreso").hide();
			$("#mensaje2, #boton_descargar").show();
		}else{
			$("#barra-progreso").hide();
			osm_setValor("mensaje", mensaje.mensaje);
		}
	}
}
