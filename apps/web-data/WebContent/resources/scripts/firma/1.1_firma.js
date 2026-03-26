
function enviar_datos(id_formularrio) {

	var formulario = osm_getObjeto(id_formularrio);
	var inputs = formulario.getElementsByTagName("input");
	

	var rutaKeyStore = osm_getValor("rutaKeyStore");
	var contrasena = osm_getValor("contrasena");

	var formXML = "";
	formXML = "<INFO>";

	for ( var ff = 0; ff < inputs.length; ff++) {

		var inputelement = inputs[ff];
		var vaElement = inputelement.value;

		var etiqueta = inputelement.name;

		etiqueta = osm_remplazar(etiqueta, ":", "_");
		etiqueta = osm_remplazar(etiqueta, ".", "_");

		var tag = "<" + etiqueta + " name=\"" + inputelement.name + "\">" + vaElement + "</" + etiqueta + ">";

		formXML += tag;

	}
	
	
	formXML += "</INFO>";

	var docXmlsignature = null;

	osm_block_window();
	try {
		docXmlsignature = document.appletFirma.enviarFormulario(formXML, rutaKeyStore, contrasena);
		osm_unblock_window();
	} catch (e) {
		alert("No se ha podido realizar la operacion. la version de java instalada debe ser la version 6.");
	}

	if (docXmlsignature != null) {
		var id_p = jsonrpc._("servFirmaJson.validarFormXML")(docXmlsignature);
		if (id_p != null) {

			location.href = formulario.action + "?IDPXFRM=" + id_p;

		} else {
			alert('No se ha podido realizar el env\u00edo de la informaci\u00f3n.\n\n Certificado revocado, vencido o no es de confianza.');
		}
	} else {
		alert('No se pudo firmar el documento');
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
// ------------------------------------------------

function p_load() {
	// osm_setValor("inpuycsac","");
	// osm_setFoco("inpuycsac");
}

osm_listen("load", window, p_load);
