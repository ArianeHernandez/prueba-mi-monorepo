function buscarDocumento() {
	document.appletFirma.openFileChooser('.*');

	window.setTimeout("esperaRespuestaDocumento()", 100);
}

function esperaRespuestaDocumento() {

	var accion = document.appletFirma.getAccion();

	if (accion == "OK") {
		var sf = document.appletFirma.getFileSelected();
		if (!osm_esVacio(sf)) {
			osm_setValor("rutaArchivoPlano", sf);
		}
	} else {
		window.setTimeout("esperaRespuestaDocumento()", 100);
	}
}

function buscarFirma() {
	document.appletFirma.openFileChooser('.p12');
	window.setTimeout("esperaRespuestaFirma()", 100);
}

function esperaRespuestaFirma() {

	var accion = document.appletFirma.getAccion();

	if (accion == "OK") {
		var sf = document.appletFirma.getFileSelected();
		if (!osm_esVacio(sf)) {
			osm_setValor("rutaKeyStore", sf);
		}
	} else {
		window.setTimeout("esperaRespuestaFirma()", 100);
	}
}

function enviar_firma(id_formulario) {

	osm_block_window();

	window.setTimeout("enviar_firmaAction('"+ id_formulario +"')", 200);
}

function enviar_firmaAction(id_formulario) {

	var rutaKeyStore = osm_getValor("rutaKeyStore");
	var rutaArchivoPlano = osm_getValor("rutaArchivoPlano");
	var contrasena = osm_getValor("contrasena");

	var docMultiPartes = null;
	osm_block_window();
	try {
		docMultiPartes = document.appletFirma.enviarDocumento(rutaKeyStore, rutaArchivoPlano, contrasena);
		osm_unblock_window();
	} catch (e) {
		alert("No se ha podido realizar la operacion. la version de java instalada debe ser la version 6.");
	}
	
	var id_archivo = jsonrpc._("servFirmaJson.obtenerIdArchivo")();
	
	try{
		for(var i=0; i<docMultiPartes.length;i++){
			jsonrpc._("servFirmaJson.recibirParte")(docMultiPartes[i],i,docMultiPartes.length, id_archivo);
		}
		
		var formulario = osm_getObjeto(id_formulario);
		location.href = formulario.action + "?IDPXFRM=" + id_archivo+"&IDF="+osm_getValor("id_formato"); 
	}catch (e) {
		alert(e);
	}
	
}
