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

//--------------------------------------------------------
