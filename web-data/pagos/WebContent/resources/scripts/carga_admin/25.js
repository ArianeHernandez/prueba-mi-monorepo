$(init_carga_admin);

function init_carga_admin() {

	// Asigna funcion para buscar usuarios sin credenciales al template ListadoClientes
	BUSCAR_CLIENTE_JSON = buscarClienteSinCredenciales;

}

// Sobreescribe la funcion por defecto para buscar clientes
function buscarClienteSinCredenciales(callBack, valor_buscar, tipo) {
	jsonrpc._("usuarioServicio.buscarUsuariosSinCredenciales")(callBack, valor_buscar);
}

function cargarFormatos(id_persona, id_negocio) {
	var formatos = jsonrpc._("formatoBaseServicio.obtenerFormatosClienteActivo")(id_persona, id_negocio);
	if (formatos != null) {
		formatos = formatos.list;

		var mostrados = 0;

		if (formatos.length == 1) {
			enviar(formatos[0].id_formato, id_negocio);
			return;
		}

		if (formatos.length > 0) {
			$("#cont_formatos").empty();

			for (var i = 0; i < formatos.length; i++) {
				if (formatos[i].archivos_adjuntos_posteriores != "S") {
					osm_construirHTML("cont_formatos", "PLANTILLA_FILA", [ formatos[i].id_formato, formatos[i].nombre, formatos[i].descripcion, id_negocio ]);
					mostrados++;
				}
			}
			if (mostrados > 0) {
				$("#div_formatos").show();
				$("#div_formatos").appendTo("#div_negocio_" + id_negocio);
			}

		}

		if (mostrados == 0) {
			alert("No hay formatos asociados al negocio..");
		}
	}

}

function enviar(id_formato, id_negocio) {
	osm_setValor('id_formato', id_formato);
	osm_setValor('id_negocio', id_negocio);
	osm_enviarFormulario('form_formato');
}
