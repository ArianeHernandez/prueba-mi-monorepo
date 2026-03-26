function cargarFormatos(id_persona, id_negocio) {
	var formatos = jsonrpc._("formatoBaseServicio.obtenerFormatosClienteActivo")(id_persona, id_negocio);
	
	$("#cont_formatos_" + id_negocio).empty();

	if (formatos != null) {
		formatos = formatos.list;
		
		if (formatos.length) {

			for (var i = 0; i < formatos.length; i++) {
				osm_construirHTML("cont_formatos_" + id_negocio, "PLANTILLA_FILA", [ formatos[i].id_formato, formatos[i].nombre, formatos[i].descripcion, id_negocio ]);
			}
			
		}
	}

}

function enviar(id_formato, id_negocio) {
	osm_setValor('id_formato', id_formato);
	osm_setValor('id_negocio', id_negocio);
	osm_enviarFormulario('form_formato');
}
