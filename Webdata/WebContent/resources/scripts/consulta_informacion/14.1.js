function enviar(id_proceso, id_formato_salida){
	osm_setValor('id_proceso', id_proceso);
	osm_setValor('id_formato_salida', id_formato_salida);
	
	osm_enviarFormulario('form_proceso');
}
