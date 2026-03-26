function mostrarDetalleCarga(id_carga, id_persona, id_formato_entrada) {
	osm_setValor('id_carga', id_carga);
	osm_setValor('id_persona', id_persona);
	osm_setValor('id_formato_entrada', id_formato_entrada);
	
	osm_enviarFormulario('form_carga');
}

//--------------------------------------------------------

