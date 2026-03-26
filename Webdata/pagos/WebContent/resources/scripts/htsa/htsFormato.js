
// -----------

function descargar_formato() {

	var id = jsonrpc._("FormatoHTS.downloadFormatoHTS")();
	$("#id_archivo").val(id);
	$("#form_download_format").get(0).submit();
	
	osm_unblock_window();

}

// -----------
