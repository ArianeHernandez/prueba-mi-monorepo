var CMP_IDREGISTRO = null;
// -----------------------------------------

function CMP_VISIBLE(id_registro) {
	var registrado = jsonrpc._("ordenPagoServicio.tieneOrdenPago")(id_registro);

	return (registrado);
}

// -------------------------------------------

function CMP_ACCION(id_registro) {

	var existe = jsonrpc._("ordenPagoServicio.existeArchivo")(id_registro);

	if (!existe) {
		alert("El archivo no se encuentra disponible para descargar");
		return;
	}

	window.location = "../descarga.ordenpago?id=" + id_registro;

}
