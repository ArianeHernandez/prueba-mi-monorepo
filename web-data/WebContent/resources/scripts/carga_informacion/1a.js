function enviarFormato(tipo){
	if (tipo=="I") {
		osm_setDestinoFormulario('form_formato','interactivo/1.0.do');
	}
	else if (tipo=="L") {
		osm_setDestinoFormulario('form_formato','lotes/1.2.do');
	}
	osm_enviarFormulario('form_formato');
}

