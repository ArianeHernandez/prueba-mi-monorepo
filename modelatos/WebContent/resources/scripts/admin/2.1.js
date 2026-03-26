function verVentana(){
	
	osm_setVisible("ventanaImport", true, true);
	osm_ocultarSelects("bodyContent");
}

function cerrarVentana(){
	osm_setVisible("ventanaImport", false, false);
	osm_mostrarSelects("bodyContent");
}

function verDiagrama(id){
	osm_setValor("id_grupo", id);
	osm_setDestinoFormulario("form_grupos",CONTEXTPATH + "/grupos/2.6.do");
	osm_enviarFormulario("form_grupos");
}

function verGrupo(id){
	osm_setValor("id_grupo", id);
	osm_setDestinoFormulario("form_grupos",CONTEXTPATH + "/grupos/2.2.do");
	osm_enviarFormulario("form_grupos");
}

function descargarERM(id){
	osm_setValor("id_grupo", id);
	osm_setDestinoFormulario("form_grupos",CONTEXTPATH + "/DescargarERM");
	osm_enviarFormulario("form_grupos");
}

