$(document).ready(function() {
	$("#msg_modal").hide();
	cargarTabla();
});
function cargarTabla() {
	var situacion = jsonrpc._("tipoArchivoServicio.obtenerTodoTipoArchivo")().list;

	situacion
			.forEach(function(i) {
				$("#tabla_tipo_archivo")
						.append(
								"<tr onmouseout=\"this.className='tabla_fila '\" "
										+ "onmouseover=\"this.className='tabla_fila_over '\" "
										+ "onclick=\"abrirModalDatos('"
										+ i.id_tipo_archivo
										+ "','"
										+ i.nombre
										+ "','"
										+ i.activo
										+ "')\" "
										+ "class=\"tabla_fila \">"
										+ "<td><b>"
										+ i.id_tipo_archivo
										+ "</b></td>"
										+ "<td>"
										+ i.nombre
										+ "</td>"
										+ "<td>"
										+ "<input onmouseover=\"\" onclick=\"\" name=\"\" id=\"\" "
										+ "autocomplete=\"off\" value=\"\" type=\"checkbox\" "
										+ "class=\"cajachequeo \" onchange=\"cambiarEstado(this.checked, 45951);\" "
										+ "checked=\""
										+ (i.activo == "S" ? "true" : "false")
										+ "\"/>"

										+ "</td></tr>");
			});
	Ï
}
function enviarSolicitud() {
	osm_verLoader();
	osm_block_window();

	if (validarSolicitud()) {
		var solicitud = generarSolicitud();

		jsonrpc._("tipoArchivoServicio.guardarTipoArchivo")(
				manejarRespuestaSolicitud, solicitud);
	}
}
function enviarActualizarSolicitud() {
	osm_verLoader();
	osm_block_window();

	if (validarSolicitud()) {
		var solicitud = generarSolicitud();

		jsonrpc._("tipoArchivoServicio.actualizarTipoArchivo")(
				manejarRespuestaSolicitud, solicitud);
	}
}

function cambiarEstado() {
	osm_verLoader();
	osm_block_window();

	if (validarSolicitud()) {
		var solicitud = generarSolicitud();

		jsonrpc._("tipoArchivoServicio.actualizarTipoArchivo")(
				manejarRespuestaSolicitud, solicitud);
	}
}

function manejarRespuestaSolicitud(respuesta) {
	console.log(respuesta);
	$('#msg_modal').hide();
	osm_ocultarLoader();
	osm_unblock_window();
	$("#tabla_tipo_archivo").find("tr.tabla_fila").remove();
	cargarTabla();
	$('#msg_modal_result').show();
	if (respuesta == null) {
		$('#message_result').text("No se pudo procesar la solicitud.");
	} else {
		$('#message_result').text("Solicitud procesada.");
	}
}

function cerrarMsg() {
	$('#msg_modal_result').hide();
}

function generarSolicitud() {
	solicitud = {
		id_tipo_archivo : osm_getValor("tipo_archivo_id"),
		nombre : osm_getValor("tipo_archivo_nombre"),
		activo : osm_getValor("tipo_archivo_estado")
	}
	return solicitud;
}

function validarSolicitud(solicitud) {
	var tipo_archivo_id = Number(osm_getValor("tipo_archivo_id"));
	var tipo_archivo_nombre = osm_getValor("tipo_archivo_nombre");
	var tipo_archivo_estado = osm_getValor("tipo_archivo_estado");

	skin_init_validar();
	skin_validar("tipo_archivo_id", "NUMERO", true);
	skin_validar("tipo_archivo_nombre", "TEXTO", true);
	skin_validar("tipo_archivo_estado", "TEXTO", true);

	if (SK_ERROR_VALIDACION) {
		osm_alert("Hay campos inválidos o vacíos, por favor verifique el formulario.");
		return false;
	}

	$("#tipo_archivo_id").removeClass("ERR_VALIDAR");
	$("#tipo_archivo_nombre").removeClass("ERR_VALIDAR");
	$("#tipo_archivo_estado").removeClass("ERR_VALIDAR");

	return true;
}

function abrirModalDatos(id, nombre, estado) {
	osm_setValor("tipo_archivo_id", id);
	osm_setValor("tipo_archivo_nombre", nombre);
	osm_setValor("tipo_archivo_estado", estado);
	$("#boton_enviar").hide();
	$("#boton_actualizar").show();
	$("#msg_modal").show();
}
function abrirModal() {
	osm_setValor("tipo_archivo_id", "");
	osm_setValor("tipo_archivo_nombre", "");
	osm_setValor("tipo_archivo_estado", "");
	$("#boton_enviar").show();
	$("#boton_actualizar").hide();
	$("#msg_modal").show();
}
function cerrarModal() {
	$("#msg_modal").hide();
}