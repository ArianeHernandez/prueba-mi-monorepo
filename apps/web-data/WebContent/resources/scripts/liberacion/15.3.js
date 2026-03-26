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

