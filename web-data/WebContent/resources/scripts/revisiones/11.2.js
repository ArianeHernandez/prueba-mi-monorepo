function mostrarDetalleCarga(id_carga, id_formato) {
	osm_setValor('id_carga', id_carga);
	osm_setValor('id_formato', id_formato);
	osm_enviarFormulario('form_carga');
}

//--------------------------------------------------------

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
