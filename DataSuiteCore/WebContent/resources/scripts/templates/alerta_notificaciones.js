/**
 * @author oaortizs
 */

$(function(){
	setTimeout("iniciarAlertasNotif()",1000);
});

INTERVALO_ANOTI = 120000;
function iniciarAlertasNotif(){
	try{
		if(jsonrpc._("notificacionServicio != null")){
			jsonrpc._("notificacionServicio.obtenerNotificacionesNuevas")(mostrarAlertasNotif);
		}
	}
	catch(ee){
		alert(ee);
	}
}

function mostrarAlertasNotif(lista){
	if (lista != null) {
		lista = lista.list;
		for (var i = 0; i < lista.length ; i++){
			mostrarAlerta(lista[i],i);
		}
	}
	setTimeout("iniciarAlertasNotif()",INTERVALO_ANOTI);
}
function mostrarAlerta(notificacion,i){
	osm_construirHTML("div_anotificaciones", "template_anotificaciones", 
			[notificacion.id_notificacion,
			notificacion.titulo,
			notificacion.emisor,
			notificacion.contenido,
			osm_getFecha(notificacion.fecha_envio.time) + " "+ osm_getHora(notificacion.fecha_envio.time),
			]);
			
	$("#div_notifi_"+notificacion.id_notificacion).fadeIn('slow');
	
	var height = 65;
	$("#div_notifi_"+notificacion.id_notificacion).css('bottom',( i * height));
	
	setTimeout("$('#div_notifi_"+notificacion.id_notificacion +"').fadeOut('slow');",15000);
	jsonrpc._("notificacionServicio.marcarNotificacionNueva")(cbNotificacionNueva, notificacion.id_notificacion);
}

function cbNotificacionNueva(rta){
}

function verAlertaNotificacion(id_notificacion){
	osm_setValor("id_notificacion", id_notificacion);
	$("#form_anotificaciones").submit();
}
