function cargarFormatos(id_persona, id_negocio) {
	var formatos = jsonrpc._("formatoBaseServicio.obtenerFormatosClienteActivo")(id_persona, id_negocio);
	AFORM = formatos;
	if (formatos != null) {
		formatos = formatos.list;
		
		if (formatos.length == 1) {
			enviar(formatos[0].id_formato, id_negocio);
			return;
		}
		
		if (formatos.length > 0) {
			$("#cont_formatos").empty();
			for (var i = 0; i < formatos.length; i++) {
				osm_construirHTML("cont_formatos", "PLANTILLA_FILA", [ formatos[i].id_formato, formatos[i].nombre, formatos[i].descripcion, id_negocio ]);
			}
			$("#div_formatos").show();
			$("#div_formatos").appendTo("#div_negocio_" + id_negocio);
		} else {
			alert("No hay formatos asociados al negocio...");
		}
	}

}

function enviar(id_formato, id_negocio) {
	osm_setValor('id_formato', id_formato);
	osm_setValor('id_negocio', id_negocio);
	osm_enviarFormulario('form_formato');
}
