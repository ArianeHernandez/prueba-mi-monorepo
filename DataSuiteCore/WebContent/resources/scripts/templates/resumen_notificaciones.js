$(document).ready(cargarNotif);

INTERVALO_RNOTI = 120000;
NOTIF_SIN_LEER = 5;

function cargarNotif() {
	jsonrpc._("notificacionServicio.obtenerNotificacionesSinLeerAdministrativo")(mostrarResumenNotif);
}

function mostrarResumenNotif(lista) {
	try {

		$("[name='rnotificacion']").remove();
		var totalNotif = 0;
		if (lista != null) {
			lista = lista.list;
			totalNotif = lista.length;
			for ( var i = 0; i < lista.length && i < NOTIF_SIN_LEER; i++) {
				var notif = lista[i];

				osm_construirHTML("lista_rnotificaciones",
						"template_rnotificaciones",
						[ notif.id_notificacion, notif.titulo, notif.contenido,
								"rnotificacion" ]);
			}

		}
		if (totalNotif > 0) {
			$("#sin_rnotificaciones").hide();
		} else {
			$("#sin_rnotificaciones").show();
		}
		osm_setValor("sinleer_rnotificaciones", totalNotif);
	} catch (e) {
		//--
	}
	setTimeout("cargarNotif()", INTERVALO_RNOTI);
}

function verNotificacion(id_notificacion) {
	osm_setValor("id_notificacion", id_notificacion);
	$("#form_rnotificaciones").submit();

}
