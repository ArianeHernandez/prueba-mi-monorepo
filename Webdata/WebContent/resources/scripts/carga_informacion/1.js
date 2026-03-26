function cargarFormatos(id_proceso){
	var formatos = jsonrpc._("formatoBaseServicio.obtenerFormatosProceso")(id_proceso);
	if(formatos != null){
		formatos = formatos.list;
		if(formatos.length == 1){
			enviar(formatos[0].id_formato, id_proceso)
		}
		else if(formatos.length){
			$("#cont_formatos").empty();
			for ( var i = 0; i < formatos.length; i++) {
				osm_construirHTML("cont_formatos", "PLANTILLA_FILA",
						[formatos[i].id_formato,
						 formatos[i].nombre,
						 formatos[i].descripcion,
						 id_proceso
						 ]
				);
			}
			$("#div_formatos").show();
			$("#div_formatos").appendTo("#div_proceso_"+id_proceso);
		}
		else{
			alert("No hay formatos asociados al proceos");
		}
	}
}

function enviar(id_formato,id_proceso){
	
	osm_setValor('id_formato', id_formato);
	osm_setValor('id_proceso', id_proceso);
	
	osm_enviarFormulario('form_formato');
}

