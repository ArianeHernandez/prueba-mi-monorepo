function leer_notificacion(id_notificacion){
	if( $("#fila_" + id_notificacion).hasClass('sinleer_notificacion')){
	    marcarNotificacionesLeida(id_notificacion);
	}

    
	var id = "#notif_" + id_notificacion;
	if($(id).is(":hidden")){
		$(id).show();
	}else{
		$(id).hide();
	}
    
}

function marcarNotificacionesLeida(id_notificacion){
	var rta = jsonrpc._("notificacionServicio.marcarNotificacionLeida")(id_notificacion);
	
    if (rta) {
        $("#fila_" + id_notificacion).removeClass('sinleer_notificacion');
    }
    else {
        alert("Error de conexi\u00f3n con el servidor");
    }
}

