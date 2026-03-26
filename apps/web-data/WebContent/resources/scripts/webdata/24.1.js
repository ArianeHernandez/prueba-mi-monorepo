
var ACTUALIZAR_ESTADO = true;

function obtenerEstado(){
    if (ACTUALIZAR_ESTADO) {
        var mensaje = jsonrpc._("grupoGiroServicio.obtenerMensajeCreacionArchivo")();
        if (mensaje != null) {
			
            osm_setValor("mensaje", mensaje.mensaje);
			
			$("#progreso").css("width", (mensaje.porcentaje * 2) + "px");
			
            if (mensaje.estado != "F") {
                setTimeout("obtenerEstado()", 1000);
            }
            else {
				$("#barra-progreso").hide();
                var exito = mensaje.exitoso ? " exitosamente" : " con  errores, intente nuevamente";
                osm_setValor("mensaje", mensaje.mensaje + "</br>" + exito);
                if (mensaje.exitoso) {
                    $("#btn_archivo").show();
                }
            }
        }
		else{
			osm_setValor("mensaje", "Error consultando el estado del proceso.");
			setTimeout("obtenerEstado()", 1000);
		}
        
    }
}

$(obtenerEstado);
