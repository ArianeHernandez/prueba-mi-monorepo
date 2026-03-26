function verVentanaEjecutarAccion(id_accion, nombre_accion){
	
	osm_setValor("id_accion_admin_hijo", id_accion);
	osm_setValor("InstanciaAccion.id_accion", id_accion);
	osm_setVisible("vn_accion_instancia", true, true);
	osm_ocultarSelects("bodyContent");
	$("#nombre_accion").text(nombre_accion);
	
	
}

function cerrarVentanaEjecutarAccion() {
	osm_setVisible("vn_accion_instancia", false);
	osm_mostrarSelects("bodyContent");
}

// --------------------------------------------------------
function activar(div, id_elemento){
	
	$(div).toggleClass("caja_aprobar_ok");
	$(div).toggleClass("caja_aprobar_cancel");
	
	if ($(div).hasClass("caja_aprobar_ok")) {
		osm_setValor(id_elemento, "");
	}
	else {
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

//--------------------------------------------------------

function verVentanaDetalleLog(id_ejecucion) {
	
	osm_setValor("cc_contenido_ventana_detalle", osm_getValor("detalle_ejecucion_" + id_ejecucion));
	
	osm_setVisible("vn_detalleLog", true, true);
	osm_ocultarSelects("bodyContent");
}

function cerrarVentanaDetalleLog() {
	osm_setVisible("vn_detalleLog", false);
	osm_mostrarSelects("bodyContent");
}

function validarArchivosPendientes(){
	if ($(".adj_gestion_C")[0]){
	    console.log("Pendientes")
	    return false;
	} else {
		console.log("No pendientes")
		return true;
	}
}

//--------------------------------------------------------
