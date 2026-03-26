var CANT_ARCHIVOS_ADJUNTOS = 0;
var LIM_ARCHIVOS_ADJUNTOS = null;
var EXTENSIONES_VALIDAS = null;

//$(window).load(validarArchivo);
$(window).on('load', validarArchivo);

function validarArchivo () {
	LIM_ARCHIVOS_ADJUNTOS = osm_getValor("limite_adjuntos");
	EXTENSIONES_VALIDAS = osm_getValor("extensionesValidas");
	EXTENSIONES_VALIDAS != null && extensionesAceptadas(); 
	$("#caja_archivo_adjunto").change(function(e) {
		if (e) {
			var file = e.target.files[0];
			var fileNameElement = document.getElementById('file-name'); 
			if (file && fileNameElement) {
                fileNameElement.textContent = file.name;
            } else if (!file && fileNameElement) {
                fileNameElement.textContent = 'Ningún archivo seleccionado';
            }
			if (!file) {
				$("#caja_archivo_adjunto").val(null);
				return;
			}
			if(LIM_ARCHIVOS_ADJUNTOS != null) {
				if(parseInt(LIM_ARCHIVOS_ADJUNTOS) <= CANT_ARCHIVOS_ADJUNTOS) {
					s_alert_info('L\u00edmite de archivos adicionales', 'No puede cargar m\u00e1s de ' + LIM_ARCHIVOS_ADJUNTOS + ' documentos adicionales en esta secci\u00f3n.');
					$("#caja_archivo_adjunto").val(null);
				}
			}
			if (EXTENSIONES_VALIDAS != null) {
				if(!validarExtensiones(file)){
					$("#caja_archivo_adjunto").val(null);
					 $("#file-name").text("Arrastre o seleccione un archivo");
				}; 
			}
			if (file.size > 104857600) {
				alert("El archivo seleccionado supera el tama\u00f1o permitido (100 MB)");
				$("#caja_archivo_adjunto").val(null);
			}
		}
	});
}

function validarExtensiones (file) {
	
	var valid = false;
	
	var filename = file.name.split(/\./g);
	var extension = filename.length > 1 ? filename.pop(): "";
	var textoExtensiones = "";
	
	var extensiones = EXTENSIONES_VALIDAS.split(", ");
	
	for (var i = 0; i < extensiones.length; i++) {
		
	    if (extensiones[i].toLowerCase() == extension.toLowerCase()) {
	    	valid = true;
	    	break;
	    }
	    
	    textoExtensiones += extensiones[i].toLowerCase();
	    if (i != extensiones.length - 1) textoExtensiones += ", ";  
	}
	
	if (!valid) {
		s_alert_info('Archivo no v\u00e1lido', 'El archivo que intenta cargar tiene una extensi\u00f3n no v\u00e1lida. Recuerde que puede cargar documentos con extensi\u00f3n ' + textoExtensiones + '.');
		return false;
	}
	
	return true;
}

function extensionesAceptadas () {
	var extensiones = EXTENSIONES_VALIDAS;
	
	var campoArchivo = document.getElementById("caja_archivo_adjunto"); 
	
	if(campoArchivo){
		campoArchivo.accept = extensiones?.replace(/(\w+)/g, ".$1").toLowerCase(); }
}

function listarArchivosAdjuntos(id_carga) {

	var listaValores = jsonrpc._("archivosAdjJsonServicio.obtenerTiposArchivo")();
	if(listaValores.list){
		listaValores = listaValores.list;
		for (var i = 0; i < listaValores.length; i++) {
		    console.log(listaValores[i]);
		    var o = new Option(listaValores[i].nombre, listaValores[i].id_tipo_archivo);
		    $(o).html(listaValores[i].nombre);
		    $("#select_tipo_archivo").append(o);
		}
	}
	
	var gruposAdj = jsonrpc._("archivosAdjJsonServicio.obtenerGruposArchivoPorCarga")(id_carga);
	gruposAdj = !gruposAdj ? [] : gruposAdj.list;
	
	if (gruposAdj.length > 0) {
		osm_setValor('contenido_archivos_adjuntos', '');
		for (var i = 0; i < gruposAdj.length; i++) {
			var grupoArchivo = gruposAdj[i];
			var id = grupoArchivo.id_grupo_archivo? grupoArchivo.id_grupo_archivo: 0;
			var nombre = grupoArchivo.nombre? grupoArchivo.nombre: "";
			osm_construirHTML('contenido_archivos_adjuntos', 'PLANTILLA_ARCHIVOS_ADJUNTOS', [id, nombre]);
			
			var archivosAdj = jsonrpc._("archivosAdjJsonServicio.obtenerArchivosPorGrupoCarga")(id_carga, grupoArchivo.id_grupo_archivo);
			archivosAdj = !archivosAdj ? [] : archivosAdj.list;
			
			for (var j = 0; j < archivosAdj.length; j++) {
				var archivo = archivosAdj[j];
				pintarArchivoAdj('lista_archivos_adjuntos_' + id, archivo);
				verArchivoAdj(archivo.id_archivo_adjunto)
			}
		}
		osm_setValor('existen_archivos_adj', 'true');
	} else {
		osm_setValor('existen_archivos_adj', 'false');
	}
	/*
	var archivosAdj = jsonrpc._("archivosAdjJsonServicio.obtenerArchivosAdjuntosPorCarga")(id_carga);
	var archivosAdjList = !archivosAdj ? [] : archivosAdj.list;

	if (archivosAdjList.length > 0) {
		osm_setValor('contenido_archivos_adjuntos', '');
		osm_construirHTML('contenido_archivos_adjuntos', 'PLANTILLA_ARCHIVOS_ADJUNTOS', []);
		for (var i = 0; i < archivosAdjList.length; i++) {
			var archivo = archivosAdjList[i];
			pintarArchivoAdj('lista_archivos_adjuntos', archivo);
			verArchivoAdj(archivo.id_archivo_adjunto)
		}

		osm_setValor('existen_archivos_adj', 'true');
	} else {
		osm_setValor('existen_archivos_adj', 'false');
	}*/

}

function pintarArchivoAdj(id_padre, archivoAdj) {

	var nombre = archivoAdj.nombre_archivo + '.' + archivoAdj.extension_archivo;
	var descripcion = jQuery.trim($("<div>").text(archivoAdj.descripcion).html());

	if (osm_esVacio(descripcion)) {
		descripcion = '[sin descripci&oacute;n]';
	}

	var nombre_apellido = osm_trimToEmpty(archivoAdj.nombre) + ' ' + osm_trimToEmpty(archivoAdj.apellido);

	osm_construirHTML(id_padre, 'PLANTILLA_ITEM_ARCHIVO_ADJ', 
			[
			archivoAdj.id_archivo_adjunto, nombre,
			archivoAdj.fecha_subidaString, descripcion, archivoAdj.long_bytes,
			archivoAdj.resumen_hash, archivoAdj.interno, nombre_apellido,
			archivoAdj.nombre_instancia, archivoAdj.estado,
			osm_esVacio(archivoAdj.nombre_instancia) ? "S" : "N",
			archivoAdj.etiqueta,
			archivoAdj.radicado]);

	$("#icono_adj" + archivoAdj.id_archivo_adjunto).addClass("bloqueestilo_" + archivoAdj.extension_archivo);

	if (!archivoAdj.checkSumValido) {
		$("#link_adj_" + archivoAdj.id_archivo_adjunto).hide();
		$("#div_alerta_" + archivoAdj.id_archivo_adjunto).show();

	}

}

function adjuntarArchivo() {

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
	
	
	var id_archivo_adjunto = jsonrpc._("archivosAdjJsonServicio.obtenerSiguiente")();
	osm_setValor('id_archivo_adjunto', id_archivo_adjunto);
	osm_setValor('descripcion_archivo_adj', encodeURIComponent(osm_getValor('descripcion_archivo_adj')));

	var options = {
		success : fin_carga_arcihvo
	};

	$("#bloque_form_adjuntar_archivos").fadeOut(100);
	$("#loading_form_adjuntar_archivos").fadeIn(100);

	$('#form_adjuntar_archivos').ajaxSubmit(options);
	
	osm_setValor("select_tipo_archivo", "");
	if (osm_esVacio(LIM_ARCHIVOS_ADJUNTOS)) {
	    var $bloque = $('#bloque_form_adjuntar_archivos'); 
	    if ($bloque.find('#id_tipo_archivo').length) { 
	        osm_setValor("id_tipo_archivo", ''); 
	    }
	}
}

var ADJ_IDADJUNTO = null;

function aprobarAdjunto(id_adjunto) {
	
	ADJ_IDADJUNTO = id_adjunto;
	
	s_accept("\u00BFDesea Aprobar este documento?", "Al realizar la aprobación ya no se podrá cambiar el estado.", "Aprobar", function(event) {
		if (event) {
			
			var resp = jsonrpc._("archivosAdjJsonServicio.aprobarAdjunto")(ADJ_IDADJUNTO);

			if (resp) {
				s_alert_info('Aprobacion Exitosa', 'El documento se ha aprobado exitosamente.');
			} else {
				s_alert_info('Error al autorizar', 'Lo sentimos, no es posible realizar la autorizacion de este adjunto.');
			}

			var id_carga = $("#id_carga_adj").val();

			listarArchivosAdjuntos(id_carga);
			
		}
	});
	
}

function descartarAdjunto(id_adjunto) {
	
	ADJ_IDADJUNTO = id_adjunto;
	
	s_accept("\u00BFDesea Descartar este documento?", "Al realizar el rechazo ya no se podrá cambiar el estado.", "Descartar", function(event) {
		if (event) {
			
			var resp = jsonrpc._("archivosAdjJsonServicio.descartarAdjunto")(ADJ_IDADJUNTO);

			if (resp) {
				s_alert_info('Aprobación Exitosa', 'El documento se ha descartado exitosamente.');
			} else {
				s_alert_info('Error al autorizar', 'Lo sentimos, no es posible realizar la autorización de este adjunto.');
			}

			var id_carga = $("#id_carga_adj").val();

			listarArchivosAdjuntos(id_carga);
			
		}
	});
	
}

function borrarAdjuntos(id_carga) {
	var archivosAdj = jsonrpc._("archivosAdjJsonServicio.obtenerArchivosAdjuntosPorCarga")(id_carga);
	var archivosAdjList = !archivosAdj ? [] : archivosAdj.list;

	if (archivosAdjList.length > 0) {
		for (var i = 0; i < archivosAdjList.length; i++) {
			borrarAdjunto(archivosAdjList[i].id_archivo_adjunto);
		}
	}
}

function borrarAdjunto(id_archivo_adjunto) {
	jsonrpc._("archivosAdjJsonServicio.borrarAdjunto")(id_archivo_adjunto);
	$("#item_archivo_adj_"+id_archivo_adjunto).remove();
	disminuirCantidadAdjuntos();
}

function fin_carga_arcihvo() {

	$("#bloque_form_adjuntar_archivos").fadeIn(100);
	$("#loading_form_adjuntar_archivos").fadeOut(100);

	var id_archivo_adjunto = osm_getValor('id_archivo_adjunto');

	archivo = jsonrpc._("archivosAdjJsonServicio.obtenerArchivoAdjunto")(id_archivo_adjunto);

	if (osm_getValor('existen_archivos_adj') == 'false') {

		osm_setValor('contenido_archivos_adjuntos', '');
		osm_construirHTML('contenido_archivos_adjuntos', 'PLANTILLA_ARCHIVOS_ADJUNTOS', ["", ""]);

	}

	if (archivo == null) {
		alert("No fue posible agregar el archivo.")
		return;
	}
	pintarArchivoAdj('lista_archivos_adjuntos_', archivo);
	osm_setValor('existen_archivos_adj', 'true');

	osm_setValor('id_archivo_adjunto', '');
	osm_setValor('caja_archivo_adjunto', '');
	osm_setValor('descripcion_archivo_adj', '');
	
	aumentarCantidadAdjuntos();

}

function verArchivoAdj(id_archivo){
	var iframe = '<iframe src="https://docs.google.com/gview?url=' + window.location.origin + '/WebData/adjunto.bajararchivo?id_archivo_adjunto=' + id_archivo + '&amp;embedded=true" style="width: 80%; height: 400px;"></iframe>';

//	$('#visualizador_file_' + id_archivo).append(iframe);
}

function setTipoArchivo(obj) {
	var text = obj.options[obj.selectedIndex].text;
	var value = osm_getValor("select_tipo_archivo");
	osm_setValor("id_tipo_archivo", value);
	osm_setValor("descripcion_archivo_adj", text);
}

function aumentarCantidadAdjuntos () {
	$("#cantidad_adjuntos").text(++CANT_ARCHIVOS_ADJUNTOS);
	$("#contenido_archivos_subidos").show();
	$("#adjuntos-titulo-div").hide();
}

function disminuirCantidadAdjuntos () {
	if (--CANT_ARCHIVOS_ADJUNTOS) {
		$("#cantidad_adjuntos").text(CANT_ARCHIVOS_ADJUNTOS);
		$("#adjuntos-titulo-div").hide();
	} else {
		$("#contenido_archivos_subidos").hide();
		$("#adjuntos-titulo-div").show();
	}
}

function listarTiposArchivo() {
	
	var tiposArchivo = jsonrpc._("archivosAdjJsonServicio.obtenerTiposArchivo")();
	
	if (tiposArchivo && tiposArchivo.list.length > 0) {
		tiposArchivo.list.sort(compare);
		tiposArchivo.list.forEach(function (i) {
			$("#id_tipo_archivo_adjunto").append(
				$("<option>", {
					value: i.id_tipo_archivo,
					text: i.nombre,
				}));
		});
		$("#id_tipo_archivo_adjunto")
			.each(
				function (i) {
					var input = selectToAutoComplete(this);
				});

		$("#id_tipo_archivo_adjunto_autocomplete")
			.removeClass();
		$("#id_tipo_archivo_adjunto_autocomplete")
			.addClass(
				"input-default form-input");

		$(".ui-autocomplete").css("height",
			"10em");
		$(".ui-autocomplete").css("overflow",
			"scroll");
	} else {
		$("#bloque_tipo_archivo").remove();
	}
	
}

function compare( a, b ) {
	  if ( a.nombre < b.nombre ){
	    return -1;
	  }
	  if ( a.nombre > b.nombre ){
	    return 1;
	  }
	  return 0;
}

function validarS3Adjuntos () {
	var almacenamientoS3 = jsonrpc._("configuracionServicio.obtenerValorByEtiqueta")("ALMACENAMIENTO_ARCHIVOS_S3");
	if (almacenamientoS3 && almacenamientoS3 == "true") {
		return true;
	} else {
		return false;
	}
}

function validarAdjuntarArchivo() {
	var fileNameElement = document.getElementById('file-name'); 
	if (fileNameElement) {
        fileNameElement.textContent = "Arrastre o seleccione un archivo";
    }
	if (validarS3Adjuntos()) {
		if (!osm_esVacio(LIM_ARCHIVOS_ADJUNTOS)) {
		    var $bloque = $('#bloque_form_adjuntar_archivos'); 
		    if ($bloque.find('#id_tipo_archivo').length) { 
		        osm_setValor("id_tipo_archivo", '1'); 
		    }
		}
		adjuntarArchivoS3();
	} else {
		adjuntarArchivo();
	}
}