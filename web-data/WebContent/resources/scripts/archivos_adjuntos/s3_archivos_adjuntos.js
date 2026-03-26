function adjuntarArchivoS3() {
	
	var caja = osm_getValor('caja_archivo_adjunto');
	caja = jQuery.trim(caja);
	if (osm_esVacio(caja)) {
		s_alert_info('Falta seleccionar el archivo', 'Debe seleccionar el archivo a adjuntar.');
		return;
	}

	var tipo_archivo = osm_getObjeto("bloque_tipo_archivo");
	if (tipo_archivo) {
		var id_tipo_archivo = osm_getValor("id_tipo_archivo");
		if (osm_esVacio(id_tipo_archivo)) {
			s_alert_info('Falta seleccionar el tipo de archivo', 'Debe seleccionar el tipo de archivo a adjuntar.');
			return;
		}
	}

	$("#bloque_form_adjuntar_archivos").fadeOut(100);
	$("#loading_form_adjuntar_archivos").fadeIn(100);
	
	var form = $("#form_adjuntar_archivos");
	var idArchivoAdjunto = jsonrpc._("archivosAdjJsonServicio.obtenerSiguiente")();
	osm_setValor("id_archivo_adjunto", idArchivoAdjunto);
	
	prepararAdjuntoS3(form, fin_carga_arcihvo, function(){}, "caja_archivo_adjunto", "descripcion_archivo_adj", "id_tipo_archivo");
	
	osm_setValor("select_tipo_archivo", "");
	osm_setValor("id_tipo_archivo", "");
	
}

function generarAdjunto (form, id_descripcion_adjuntos, id_tipo_archivo) {
	var idCarga = osm_getValor("id_carga");
	var idArchivoAdjunto = $(form).find("#id_archivo_adjunto").val();
	var descripcion = $(form).find("#"+ id_descripcion_adjuntos).val();
	descripcion = encodeURIComponent(descripcion);
	var idTipoArchivo = $(form).find("#" + id_tipo_archivo).val();
	var idPersona = osm_getValor("id_persona");
	
	var adjunto = {
			id_archivo_adjunto: idArchivoAdjunto,
			ruta: "",
			id_carga: idCarga,
			nombre_archivo: "",
			extension_archivo: "",
			descripcion: descripcion,
			long_bytes: "",
			resumen_hash: "",
			interno: "N",
			id_persona: idPersona,
			estado: "A",
			nombre_instancia: "",
			radicado: "",
			id_tipo_archivo: idTipoArchivo 
	};
	return adjunto;
}

function prepararAdjuntoS3 (form, callback_success, callback_error, id_caja_adjuntos, id_descripcion_adjuntos, id_tipo_archivo) {
	var adjunto = generarAdjunto(form, id_descripcion_adjuntos, id_tipo_archivo);
	var file = $(form).find("#"+id_caja_adjuntos).first()[0].files[0];
	enviarAdjuntoS3(file, adjunto, callback_success, callback_error);
}

function enviarAdjuntoS3 (file, adjunto, callback_success, callback_error) {
	var s3Path = jsonrpc._("S3Servicio.obtenerRutaAdjuntosS3")();
	var s3PathFile = s3Path + "/" + adjunto.id_carga + "/" + adjunto.id_archivo_adjunto + "_" + file.name;
	var encodedS3Path = encodeURIComponent(s3PathFile);
	
	var filename = file.name.split(/\./g);
	var extension = filename.length > 1 ? filename.pop(): "";
	adjunto.ruta = encodedS3Path;
	adjunto.nombre_archivo = encodeURIComponent(filename.join("."));
	adjunto.extension_archivo = extension;
	adjunto.long_bytes = file.size;
	
	var signedUrlResponse = jsonrpc._("S3Servicio.obtenerUrlFirmadaUpload")(encodedS3Path);
	
	if (signedUrlResponse) {
		$.ajax({
			async: true,
			method : "PUT",
			url : signedUrlResponse.signedUrl,
			data : file,
			processData: false,
			contentType: false,
			cache: false,
		}).done(function () {
			jsonrpc._("S3Servicio.insertarAdjunto")(callback_success, adjunto);
		}).fail(function() {
		    callback_error();
		});
	}
}