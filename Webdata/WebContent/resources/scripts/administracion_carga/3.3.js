$(document).ready(initEjec);

function initEjec() {
	obtenerEjecuciones();
	setTimeout("obtenerEjecuciones()", 5000);
}
// --------------------------------------------------------
function activar(div, id_elemento) {

	$(div).toggleClass("caja_aprobar_ok");
	$(div).toggleClass("caja_aprobar_cancel");

	if ($(div).hasClass("caja_aprobar_ok")) {
		osm_setValor(id_elemento, "");
	} else {
		osm_setValor(id_elemento, id_elemento);
	}

}

// --------------------------------------------------------

function verVentanaAprobarCarga() {
	osm_setVisible("vn_aprobarcarga", true, true);
	osm_ocultarSelects("bodyContent");
}

function cerrarVentanaAprobarCarga() {
	osm_setVisible("vn_aprobarcarga", false);
	osm_mostrarSelects("bodyContent");
}

// --------------------------------------------------------

function verVentanaRechazarCarga() {
	osm_setVisible("vn_rechazarcarga", true, true);
	osm_ocultarSelects("bodyContent");
}

function cerrarVentanaRechazarCarga() {
	osm_setVisible("vn_rechazarcarga", false);
	osm_mostrarSelects("bodyContent");
}

// --------------------------------------------------------
var ID_EJECUCION = false;

function verVentanaDetalleLog(id_ejecucion) {
	ID_EJECUCION = id_ejecucion;
	actualizarDetalleLog();
}

function actualizarDetalleLog() {
	if (ID_EJECUCION) {
		var lista = jsonrpc._("ejecucionServicio.obtenerLogEjecucionesCarga")(
				mostrarDetalle, ID_EJECUCION);

		osm_setVisible("vn_detalleLog", true, true);
		osm_ocultarSelects("bodyContent");
	}

}

function mostrarDetalle(lista) {

	
	if (lista != null) {
		lista = lista.list;

		for ( var i = 0; i < lista.length; i++) {
			var id_log = lista[i].id_log;
			var fila_log = $("#fila_log_"+id_log); 			
			
			if(fila_log.length == 0){
				
				var fecha = lista[i].fecha;
				fecha = osm_esVacio(fecha) ? "" : osm_getFecha(fecha.time) + " "
						+ osm_getHora(fecha.time);
				
				if (lista[i].cod_error == null) {
					lista[i].cod_error = "";
				}
				
				osm_construirHTML("cc_contenido_ventana_detalle", "PLANTILLA_FILA",
						[ fecha, lista[i].cod_error, lista[i].descripcion,
						  "fila_log" , id_log]);
				
			}

		}
	}
	setTimeout("actualizarDetalleLog()", 5000);
}

function cerrarVentanaDetalleLog() {
	osm_setVisible("vn_detalleLog", false);
	osm_mostrarSelects("bodyContent");
	ID_EJECUCION = false;
	var logs = $("#cc_contenido_ventana_detalle").empty();
	$("#file_encabezado").clone().appendTo("#cc_contenido_ventana_detalle");
}

// --------------------------------------------------------

function obtenerEjecuciones() {

	var id_carga = osm_getValor("id_carga");
	jsonrpc._("ejecucionServicio.obtenerEjecucionesPorCarga")(mostrarEjecuciones,
			id_carga);

}

function mostrarEjecuciones(lista) {

	var exito = false;
	if (lista != null) {
		lista = lista.list;

		for ( var i = 0; i < lista.length; i++) {
			
			var id_ejec = lista[i].id_ejecucion;
			var fila = $("#fila_ejec_"+id_ejec);
			
			var fechaI = lista[i].fecha_inicio;
			var fechaF = lista[i].fecha_final;
			fechaF = osm_esVacio(fechaF) ? "" : osm_getFecha(fechaF.time) + " "
					+ osm_getHora(fechaF.time);
			//Solo se agregar filas nuevas
			if(fila.length == 0){
				
				var destino = osm_getValor("destino_" + lista[i].id_destino);
				
				fechaI = osm_esVacio(fechaI) ? "" : osm_getFecha(fechaI.time) + " "
						+ osm_getHora(fechaI.time);
				
				osm_construirHTML("cc_contenido_ejecuciones",
						"PLANTILLA_FILA_EJEC", [ destino, lista[i].estado, fechaI,
						                         fechaF, id_ejec, "fila_ejec" ]);
				if(lista[i].estado && lista[i].estado.toUpperCase().indexOf('EXITO') > 0){
					exito = true;
				}
				
			}else{
				osm_setValor("estado_ejec_" + id_ejec, lista[i].estado);
				osm_setValor("final_ejec_" + id_ejec, fechaF);
			}
			

		}
	}
	if(exito){
		$("[id^='destino_']").hide();
	}
	if (lista == null || lista.length == 0) {
		$("#msj_sinejecucion").show();
		$("#div_ejecuciones").hide();
	} else {
		$("#msj_sinejecucion").hide();
		$("#div_ejecuciones").show();
	}
	setTimeout("obtenerEjecuciones()", 5000);
}
